package com.facebook.rebound;

import com.facebook.rebound.ChoreographerCompat.FrameCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AnimationQueue {
    private final Queue<Double> mAnimationQueue;
    private final List<Callback> mCallbacks;
    private final ChoreographerCompat mChoreographer;
    private final FrameCallback mChoreographerCallback;
    private final Queue<Double> mPendingQueue;
    private boolean mRunning;
    private final ArrayList<Double> mTempValues;

    /* renamed from: com.facebook.rebound.AnimationQueue.1 */
    class C10821 extends FrameCallback {
        C10821() {
        }

        public void doFrame(long j) {
            AnimationQueue.this.onFrame(j);
        }
    }

    public interface Callback {
        void onFrame(Double d);
    }

    public AnimationQueue() {
        this.mPendingQueue = new LinkedList();
        this.mAnimationQueue = new LinkedList();
        this.mCallbacks = new ArrayList();
        this.mTempValues = new ArrayList();
        this.mChoreographer = ChoreographerCompat.getInstance();
        this.mChoreographerCallback = new C10821();
    }

    private void onFrame(long j) {
        int i;
        Double d = (Double) this.mPendingQueue.poll();
        if (d != null) {
            this.mAnimationQueue.offer(d);
            i = 0;
        } else {
            i = Math.max(this.mCallbacks.size() - this.mAnimationQueue.size(), 0);
        }
        this.mTempValues.addAll(this.mAnimationQueue);
        for (int size = this.mTempValues.size() - 1; size > -1; size--) {
            d = (Double) this.mTempValues.get(size);
            int size2 = ((this.mTempValues.size() - 1) - size) + i;
            if (this.mCallbacks.size() > size2) {
                ((Callback) this.mCallbacks.get(size2)).onFrame(d);
            }
        }
        this.mTempValues.clear();
        while (this.mAnimationQueue.size() + i >= this.mCallbacks.size()) {
            this.mAnimationQueue.poll();
        }
        if (this.mAnimationQueue.isEmpty() && this.mPendingQueue.isEmpty()) {
            this.mRunning = false;
        } else {
            this.mChoreographer.postFrameCallback(this.mChoreographerCallback);
        }
    }

    private void runIfIdle() {
        if (!this.mRunning) {
            this.mRunning = true;
            this.mChoreographer.postFrameCallback(this.mChoreographerCallback);
        }
    }

    public void addAllValues(Collection<Double> collection) {
        this.mPendingQueue.addAll(collection);
        runIfIdle();
    }

    public void addCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void addValue(Double d) {
        this.mPendingQueue.add(d);
        runIfIdle();
    }

    public void clearCallbacks() {
        this.mCallbacks.clear();
    }

    public void clearValues() {
        this.mPendingQueue.clear();
    }

    public void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }
}
