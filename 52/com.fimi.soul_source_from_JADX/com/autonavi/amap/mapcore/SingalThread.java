package com.autonavi.amap.mapcore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingalThread extends Thread {
    private boolean isWaiting;
    String logTag;
    private final Lock mLock;
    private final Condition mWaiting;

    public SingalThread() {
        this.mLock = new ReentrantLock();
        this.mWaiting = this.mLock.newCondition();
        this.isWaiting = true;
        this.logTag = "SingalThread";
    }

    void doAwake() {
        if (this.isWaiting) {
            this.mLock.lock();
            this.isWaiting = false;
            this.mWaiting.signal();
            this.mLock.unlock();
        }
    }

    void doWait() {
        this.mLock.lock();
        this.isWaiting = true;
        this.mWaiting.await();
        this.mLock.unlock();
    }
}
