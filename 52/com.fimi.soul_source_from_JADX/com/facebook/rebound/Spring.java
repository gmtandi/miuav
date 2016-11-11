package com.facebook.rebound;

import com.amap.api.maps.model.WeightedLatLng;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class Spring {
    private static int ID = 0;
    private static final double MAX_DELTA_TIME_SEC = 0.064d;
    private static final double SOLVER_TIMESTEP_SEC = 0.001d;
    private final PhysicsState mCurrentState;
    private double mDisplacementFromRestThreshold;
    private double mEndValue;
    private final String mId;
    private CopyOnWriteArraySet<SpringListener> mListeners;
    private boolean mOvershootClampingEnabled;
    private final PhysicsState mPreviousState;
    private double mRestSpeedThreshold;
    private SpringConfig mSpringConfig;
    private final BaseSpringSystem mSpringSystem;
    private double mStartValue;
    private final PhysicsState mTempState;
    private double mTimeAccumulator;
    private boolean mWasAtRest;

    class PhysicsState {
        double position;
        double velocity;

        private PhysicsState() {
        }
    }

    static {
        ID = 0;
    }

    Spring(BaseSpringSystem baseSpringSystem) {
        this.mCurrentState = new PhysicsState();
        this.mPreviousState = new PhysicsState();
        this.mTempState = new PhysicsState();
        this.mWasAtRest = true;
        this.mRestSpeedThreshold = 0.005d;
        this.mDisplacementFromRestThreshold = 0.005d;
        this.mListeners = new CopyOnWriteArraySet();
        this.mTimeAccumulator = 0.0d;
        if (baseSpringSystem == null) {
            throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
        }
        this.mSpringSystem = baseSpringSystem;
        StringBuilder append = new StringBuilder().append("spring:");
        int i = ID;
        ID = i + 1;
        this.mId = append.append(i).toString();
        setSpringConfig(SpringConfig.defaultConfig);
    }

    private double getDisplacementDistanceForState(PhysicsState physicsState) {
        return Math.abs(this.mEndValue - physicsState.position);
    }

    private void interpolate(double d) {
        this.mCurrentState.position = (this.mCurrentState.position * d) + (this.mPreviousState.position * (WeightedLatLng.DEFAULT_INTENSITY - d));
        this.mCurrentState.velocity = (this.mCurrentState.velocity * d) + (this.mPreviousState.velocity * (WeightedLatLng.DEFAULT_INTENSITY - d));
    }

    public Spring addListener(SpringListener springListener) {
        if (springListener == null) {
            throw new IllegalArgumentException("newListener is required");
        }
        this.mListeners.add(springListener);
        return this;
    }

    void advance(double d) {
        boolean isAtRest = isAtRest();
        if (!isAtRest || !this.mWasAtRest) {
            boolean z;
            Object obj;
            if (d > MAX_DELTA_TIME_SEC) {
                d = MAX_DELTA_TIME_SEC;
            }
            this.mTimeAccumulator += d;
            double d2 = this.mSpringConfig.tension;
            double d3 = this.mSpringConfig.friction;
            double d4 = this.mCurrentState.position;
            double d5 = this.mCurrentState.velocity;
            double d6 = this.mTempState.position;
            double d7 = this.mTempState.velocity;
            while (true) {
                if (this.mTimeAccumulator < SOLVER_TIMESTEP_SEC) {
                    break;
                }
                this.mTimeAccumulator -= SOLVER_TIMESTEP_SEC;
                if (this.mTimeAccumulator < SOLVER_TIMESTEP_SEC) {
                    this.mPreviousState.position = d4;
                    this.mPreviousState.velocity = d5;
                }
                double d8 = ((this.mEndValue - d6) * d2) - (d3 * d5);
                double d9 = d5 + ((SOLVER_TIMESTEP_SEC * d8) * 0.5d);
                double d10 = ((this.mEndValue - (((SOLVER_TIMESTEP_SEC * d5) * 0.5d) + d4)) * d2) - (d3 * d9);
                double d11 = d5 + ((SOLVER_TIMESTEP_SEC * d10) * 0.5d);
                double d12 = ((this.mEndValue - (((SOLVER_TIMESTEP_SEC * d9) * 0.5d) + d4)) * d2) - (d3 * d11);
                d6 = d4 + (SOLVER_TIMESTEP_SEC * d11);
                d7 = (SOLVER_TIMESTEP_SEC * d12) + d5;
                d8 += (d10 + d12) * 2.0d;
                d4 += (((((d9 + d11) * 2.0d) + d5) + d7) * 0.16666666666666666d) * SOLVER_TIMESTEP_SEC;
                d5 += ((d8 + (((this.mEndValue - d6) * d2) - (d3 * d7))) * 0.16666666666666666d) * SOLVER_TIMESTEP_SEC;
            }
            this.mTempState.position = d6;
            this.mTempState.velocity = d7;
            this.mCurrentState.position = d4;
            this.mCurrentState.velocity = d5;
            if (this.mTimeAccumulator > 0.0d) {
                interpolate(this.mTimeAccumulator / SOLVER_TIMESTEP_SEC);
            }
            if (isAtRest() || (this.mOvershootClampingEnabled && isOvershooting())) {
                if (d2 > 0.0d) {
                    this.mStartValue = this.mEndValue;
                    this.mCurrentState.position = this.mEndValue;
                } else {
                    this.mEndValue = this.mCurrentState.position;
                    this.mStartValue = this.mEndValue;
                }
                setVelocity(0.0d);
                z = true;
            } else {
                z = isAtRest;
            }
            if (this.mWasAtRest) {
                this.mWasAtRest = false;
                obj = 1;
            } else {
                obj = null;
            }
            Object obj2 = null;
            if (z) {
                this.mWasAtRest = true;
                obj2 = 1;
            }
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                SpringListener springListener = (SpringListener) it.next();
                if (obj != null) {
                    springListener.onSpringActivate(this);
                }
                springListener.onSpringUpdate(this);
                if (obj2 != null) {
                    springListener.onSpringAtRest(this);
                }
            }
        }
    }

    public boolean currentValueIsApproximately(double d) {
        return Math.abs(getCurrentValue() - d) <= getRestDisplacementThreshold();
    }

    public void destroy() {
        this.mListeners.clear();
        this.mSpringSystem.deregisterSpring(this);
    }

    public double getCurrentDisplacementDistance() {
        return getDisplacementDistanceForState(this.mCurrentState);
    }

    public double getCurrentValue() {
        return this.mCurrentState.position;
    }

    public double getEndValue() {
        return this.mEndValue;
    }

    public String getId() {
        return this.mId;
    }

    public double getRestDisplacementThreshold() {
        return this.mDisplacementFromRestThreshold;
    }

    public double getRestSpeedThreshold() {
        return this.mRestSpeedThreshold;
    }

    public SpringConfig getSpringConfig() {
        return this.mSpringConfig;
    }

    public double getStartValue() {
        return this.mStartValue;
    }

    public double getVelocity() {
        return this.mCurrentState.velocity;
    }

    public boolean isAtRest() {
        return Math.abs(this.mCurrentState.velocity) <= this.mRestSpeedThreshold && (getDisplacementDistanceForState(this.mCurrentState) <= this.mDisplacementFromRestThreshold || this.mSpringConfig.tension == 0.0d);
    }

    public boolean isOvershootClampingEnabled() {
        return this.mOvershootClampingEnabled;
    }

    public boolean isOvershooting() {
        return this.mSpringConfig.tension > 0.0d && ((this.mStartValue < this.mEndValue && getCurrentValue() > this.mEndValue) || (this.mStartValue > this.mEndValue && getCurrentValue() < this.mEndValue));
    }

    public Spring removeAllListeners() {
        this.mListeners.clear();
        return this;
    }

    public Spring removeListener(SpringListener springListener) {
        if (springListener == null) {
            throw new IllegalArgumentException("listenerToRemove is required");
        }
        this.mListeners.remove(springListener);
        return this;
    }

    public Spring setAtRest() {
        this.mEndValue = this.mCurrentState.position;
        this.mTempState.position = this.mCurrentState.position;
        this.mCurrentState.velocity = 0.0d;
        return this;
    }

    public Spring setCurrentValue(double d) {
        return setCurrentValue(d, true);
    }

    public Spring setCurrentValue(double d, boolean z) {
        this.mStartValue = d;
        this.mCurrentState.position = d;
        this.mSpringSystem.activateSpring(getId());
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((SpringListener) it.next()).onSpringUpdate(this);
        }
        if (z) {
            setAtRest();
        }
        return this;
    }

    public Spring setEndValue(double d) {
        if (!(this.mEndValue == d && isAtRest())) {
            this.mStartValue = getCurrentValue();
            this.mEndValue = d;
            this.mSpringSystem.activateSpring(getId());
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((SpringListener) it.next()).onSpringEndStateChange(this);
            }
        }
        return this;
    }

    public Spring setOvershootClampingEnabled(boolean z) {
        this.mOvershootClampingEnabled = z;
        return this;
    }

    public Spring setRestDisplacementThreshold(double d) {
        this.mDisplacementFromRestThreshold = d;
        return this;
    }

    public Spring setRestSpeedThreshold(double d) {
        this.mRestSpeedThreshold = d;
        return this;
    }

    public Spring setSpringConfig(SpringConfig springConfig) {
        if (springConfig == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        this.mSpringConfig = springConfig;
        return this;
    }

    public Spring setVelocity(double d) {
        if (d != this.mCurrentState.velocity) {
            this.mCurrentState.velocity = d;
            this.mSpringSystem.activateSpring(getId());
        }
        return this;
    }

    public boolean systemShouldAdvance() {
        return (isAtRest() && wasAtRest()) ? false : true;
    }

    public boolean wasAtRest() {
        return this.mWasAtRest;
    }
}
