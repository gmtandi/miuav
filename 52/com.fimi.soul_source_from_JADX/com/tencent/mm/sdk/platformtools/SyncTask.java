package com.tencent.mm.sdk.platformtools;

import android.os.Handler;

public abstract class SyncTask<R> {
    private final long ba;
    private long bb;
    private long bc;
    private Runnable bd;
    private Object lock;
    private R result;

    /* renamed from: com.tencent.mm.sdk.platformtools.SyncTask.1 */
    class C22731 implements Runnable {
        final /* synthetic */ SyncTask be;

        C22731(SyncTask syncTask) {
            this.be = syncTask;
        }

        public void run() {
            this.be.bc = Util.ticksToNow(this.be.bb);
            this.be.setResult(this.be.run());
        }
    }

    public SyncTask() {
        this(0, null);
    }

    public SyncTask(long j, R r) {
        this.lock = new Object();
        this.bd = new C22731(this);
        this.ba = j;
        this.result = r;
    }

    public R exec(Handler handler) {
        if (handler == null) {
            Log.m13539d("MicroMsg.SDK.SyncTask", "null handler, task in exec thread, return now");
            return run();
        } else if (Thread.currentThread().getId() == handler.getLooper().getThread().getId()) {
            Log.m13539d("MicroMsg.SDK.SyncTask", "same tid, task in exec thread, return now");
            return run();
        } else {
            this.bb = Util.currentTicks();
            handler.post(this.bd);
            try {
                synchronized (this.lock) {
                    this.lock.wait(this.ba);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long ticksToNow = Util.ticksToNow(this.bb);
            Log.m13548v("MicroMsg.SDK.SyncTask", "sync task done, return=%s, cost=%d(wait=%d, run=%d)", this.result, Long.valueOf(ticksToNow), Long.valueOf(this.bc), Long.valueOf(ticksToNow - this.bc));
            return this.result;
        }
    }

    protected abstract R run();

    public void setResult(R r) {
        this.result = r;
        synchronized (this.lock) {
            this.lock.notify();
        }
    }
}
