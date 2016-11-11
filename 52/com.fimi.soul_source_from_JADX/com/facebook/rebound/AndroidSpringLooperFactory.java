package com.facebook.rebound;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

abstract class AndroidSpringLooperFactory {

    @TargetApi(16)
    class ChoreographerAndroidSpringLooper extends SpringLooper {
        private final Choreographer mChoreographer;
        private final FrameCallback mFrameCallback;
        private long mLastTime;
        private boolean mStarted;

        /* renamed from: com.facebook.rebound.AndroidSpringLooperFactory.ChoreographerAndroidSpringLooper.1 */
        class C10801 implements FrameCallback {
            C10801() {
            }

            public void doFrame(long j) {
                if (ChoreographerAndroidSpringLooper.this.mStarted && ChoreographerAndroidSpringLooper.this.mSpringSystem != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    ChoreographerAndroidSpringLooper.this.mSpringSystem.loop((double) (uptimeMillis - ChoreographerAndroidSpringLooper.this.mLastTime));
                    ChoreographerAndroidSpringLooper.this.mLastTime = uptimeMillis;
                    ChoreographerAndroidSpringLooper.this.mChoreographer.postFrameCallback(ChoreographerAndroidSpringLooper.this.mFrameCallback);
                }
            }
        }

        public ChoreographerAndroidSpringLooper(Choreographer choreographer) {
            this.mChoreographer = choreographer;
            this.mFrameCallback = new C10801();
        }

        public static ChoreographerAndroidSpringLooper create() {
            return new ChoreographerAndroidSpringLooper(Choreographer.getInstance());
        }

        public void start() {
            if (!this.mStarted) {
                this.mStarted = true;
                this.mLastTime = SystemClock.uptimeMillis();
                this.mChoreographer.removeFrameCallback(this.mFrameCallback);
                this.mChoreographer.postFrameCallback(this.mFrameCallback);
            }
        }

        public void stop() {
            this.mStarted = false;
            this.mChoreographer.removeFrameCallback(this.mFrameCallback);
        }
    }

    class LegacyAndroidSpringLooper extends SpringLooper {
        private final Handler mHandler;
        private long mLastTime;
        private final Runnable mLooperRunnable;
        private boolean mStarted;

        /* renamed from: com.facebook.rebound.AndroidSpringLooperFactory.LegacyAndroidSpringLooper.1 */
        class C10811 implements Runnable {
            C10811() {
            }

            public void run() {
                if (LegacyAndroidSpringLooper.this.mStarted && LegacyAndroidSpringLooper.this.mSpringSystem != null) {
                    LegacyAndroidSpringLooper.this.mSpringSystem.loop((double) (SystemClock.uptimeMillis() - LegacyAndroidSpringLooper.this.mLastTime));
                    LegacyAndroidSpringLooper.this.mHandler.post(LegacyAndroidSpringLooper.this.mLooperRunnable);
                }
            }
        }

        public LegacyAndroidSpringLooper(Handler handler) {
            this.mHandler = handler;
            this.mLooperRunnable = new C10811();
        }

        public static SpringLooper create() {
            return new LegacyAndroidSpringLooper(new Handler());
        }

        public void start() {
            if (!this.mStarted) {
                this.mStarted = true;
                this.mLastTime = SystemClock.uptimeMillis();
                this.mHandler.removeCallbacks(this.mLooperRunnable);
                this.mHandler.post(this.mLooperRunnable);
            }
        }

        public void stop() {
            this.mStarted = false;
            this.mHandler.removeCallbacks(this.mLooperRunnable);
        }
    }

    AndroidSpringLooperFactory() {
    }

    public static SpringLooper createSpringLooper() {
        return VERSION.SDK_INT >= 16 ? ChoreographerAndroidSpringLooper.create() : LegacyAndroidSpringLooper.create();
    }
}
