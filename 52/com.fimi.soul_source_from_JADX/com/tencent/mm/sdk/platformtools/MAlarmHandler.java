package com.tencent.mm.sdk.platformtools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import p001b.p002b.C0119a;

public class MAlarmHandler {
    public static final long NEXT_FIRE_INTERVAL = Long.MAX_VALUE;
    private static Map<Integer, MAlarmHandler> aA;
    private static IBumper aC;
    private static boolean aD;
    private static int av;
    private final CallBack aB;
    private final int aw;
    private final boolean ax;
    private long ay;
    private long az;

    public interface CallBack {
        boolean onTimerExpired();
    }

    public interface IBumper {
        void cancel();

        void prepare();
    }

    static {
        aA = new HashMap();
        aD = false;
    }

    public MAlarmHandler(CallBack callBack, boolean z) {
        this.ay = 0;
        this.az = 0;
        C0119a.m172a("bumper not initialized", aD);
        this.aB = callBack;
        this.ax = z;
        if (av >= Opcodes.ACC_ANNOTATION) {
            av = 0;
        }
        int i = av + 1;
        av = i;
        this.aw = i;
    }

    public static long fire() {
        List linkedList = new LinkedList();
        Set<Integer> hashSet = new HashSet();
        hashSet.addAll(aA.keySet());
        long j = NEXT_FIRE_INTERVAL;
        for (Integer num : hashSet) {
            long j2;
            MAlarmHandler mAlarmHandler = (MAlarmHandler) aA.get(num);
            if (mAlarmHandler != null) {
                long ticksToNow = Util.ticksToNow(mAlarmHandler.ay);
                if (ticksToNow < 0) {
                    ticksToNow = 0;
                }
                if (ticksToNow > mAlarmHandler.az) {
                    if (mAlarmHandler.aB.onTimerExpired() && mAlarmHandler.ax) {
                        j = mAlarmHandler.az;
                    } else {
                        linkedList.add(num);
                    }
                    mAlarmHandler.ay = Util.currentTicks();
                } else if (mAlarmHandler.az - ticksToNow < j) {
                    j2 = mAlarmHandler.az - ticksToNow;
                    j = j2;
                }
            }
            j2 = j;
            j = j2;
        }
        for (int i = 0; i < linkedList.size(); i++) {
            aA.remove(linkedList.get(i));
        }
        if (j == NEXT_FIRE_INTERVAL && aC != null) {
            aC.cancel();
            Log.m13547v("MicroMsg.MAlarmHandler", "cancel bumper for no more handler");
        }
        return j;
    }

    public static void initAlarmBumper(IBumper iBumper) {
        aD = true;
        aC = iBumper;
    }

    protected void finalize() {
        stopTimer();
        super.finalize();
    }

    public void startTimer(long j) {
        this.az = j;
        this.ay = Util.currentTicks();
        long j2 = this.az;
        Log.m13539d("MicroMsg.MAlarmHandler", "check need prepare: check=" + j2);
        long j3 = NEXT_FIRE_INTERVAL;
        for (Entry value : aA.entrySet()) {
            long j4;
            MAlarmHandler mAlarmHandler = (MAlarmHandler) value.getValue();
            if (mAlarmHandler != null) {
                long ticksToNow = Util.ticksToNow(mAlarmHandler.ay);
                if (ticksToNow < 0) {
                    ticksToNow = 0;
                }
                if (ticksToNow > mAlarmHandler.az) {
                    j3 = mAlarmHandler.az;
                } else if (mAlarmHandler.az - ticksToNow < j3) {
                    j4 = mAlarmHandler.az - ticksToNow;
                    j3 = j4;
                }
            }
            j4 = j3;
            j3 = j4;
        }
        Object obj = j3 > j2 ? 1 : null;
        stopTimer();
        aA.put(Integer.valueOf(this.aw), this);
        if (aC != null && obj != null) {
            Log.m13547v("MicroMsg.MAlarmHandler", "prepare bumper");
            aC.prepare();
        }
    }

    public void stopTimer() {
        aA.remove(Integer.valueOf(this.aw));
    }

    public boolean stopped() {
        return !aA.containsKey(Integer.valueOf(this.aw));
    }
}
