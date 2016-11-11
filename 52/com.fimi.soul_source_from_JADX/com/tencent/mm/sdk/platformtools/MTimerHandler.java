package com.tencent.mm.sdk.platformtools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class MTimerHandler extends Handler {
    private static int av;
    private final boolean aG;
    private long aH;
    private final CallBack aI;
    private final int aw;

    public interface CallBack {
        boolean onTimerExpired();
    }

    public MTimerHandler(Looper looper, CallBack callBack, boolean z) {
        super(looper);
        this.aH = 0;
        this.aI = callBack;
        this.aw = m13551d();
        this.aG = z;
    }

    public MTimerHandler(CallBack callBack, boolean z) {
        this.aH = 0;
        this.aI = callBack;
        this.aw = m13551d();
        this.aG = z;
    }

    private static int m13551d() {
        if (av >= Opcodes.ACC_ANNOTATION) {
            av = 0;
        }
        int i = av + 1;
        av = i;
        return i;
    }

    protected void finalize() {
        stopTimer();
        super.finalize();
    }

    public void handleMessage(Message message) {
        if (message.what == this.aw && this.aI != null && this.aI.onTimerExpired() && this.aG) {
            sendEmptyMessageDelayed(this.aw, this.aH);
        }
    }

    public void startTimer(long j) {
        this.aH = j;
        stopTimer();
        sendEmptyMessageDelayed(this.aw, j);
    }

    public void stopTimer() {
        removeMessages(this.aw);
    }

    public boolean stopped() {
        return !hasMessages(this.aw);
    }
}
