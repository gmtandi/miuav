package com.facebook.rebound;

public class SynchronousLooper extends SpringLooper {
    public static double SIXTY_FPS;
    private boolean mRunning;
    private double mTimeStep;

    static {
        SIXTY_FPS = 16.6667d;
    }

    public SynchronousLooper() {
        this.mTimeStep = SIXTY_FPS;
    }

    public double getTimeStep() {
        return this.mTimeStep;
    }

    public void setTimeStep(double d) {
        this.mTimeStep = d;
    }

    public void start() {
        this.mRunning = true;
        while (!this.mSpringSystem.getIsIdle() && this.mRunning) {
            this.mSpringSystem.loop(this.mTimeStep);
        }
    }

    public void stop() {
        this.mRunning = false;
    }
}
