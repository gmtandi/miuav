package com.tencent.stat;

public class StatAppMonitor implements Cloneable {
    public static final int FAILURE_RESULT_TYPE = 1;
    public static final int LOGIC_FAILURE_RESULT_TYPE = 2;
    public static final int SUCCESS_RESULT_TYPE = 0;
    private String f12191a;
    private long f12192b;
    private long f12193c;
    private int f12194d;
    private long f12195e;
    private int f12196f;
    private int f12197g;

    public StatAppMonitor(String str) {
        this.f12191a = null;
        this.f12192b = 0;
        this.f12193c = 0;
        this.f12194d = 0;
        this.f12195e = 0;
        this.f12196f = 0;
        this.f12197g = FAILURE_RESULT_TYPE;
        this.f12191a = str;
    }

    public StatAppMonitor(String str, int i, int i2, long j, long j2, long j3, int i3) {
        this.f12191a = null;
        this.f12192b = 0;
        this.f12193c = 0;
        this.f12194d = 0;
        this.f12195e = 0;
        this.f12196f = 0;
        this.f12197g = FAILURE_RESULT_TYPE;
        this.f12191a = str;
        this.f12192b = j;
        this.f12193c = j2;
        this.f12194d = i;
        this.f12195e = j3;
        this.f12196f = i2;
        this.f12197g = i3;
    }

    public StatAppMonitor clone() {
        try {
            return (StatAppMonitor) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getInterfaceName() {
        return this.f12191a;
    }

    public long getMillisecondsConsume() {
        return this.f12195e;
    }

    public long getReqSize() {
        return this.f12192b;
    }

    public long getRespSize() {
        return this.f12193c;
    }

    public int getResultType() {
        return this.f12194d;
    }

    public int getReturnCode() {
        return this.f12196f;
    }

    public int getSampling() {
        return this.f12197g;
    }

    public void setInterfaceName(String str) {
        this.f12191a = str;
    }

    public void setMillisecondsConsume(long j) {
        this.f12195e = j;
    }

    public void setReqSize(long j) {
        this.f12192b = j;
    }

    public void setRespSize(long j) {
        this.f12193c = j;
    }

    public void setResultType(int i) {
        this.f12194d = i;
    }

    public void setReturnCode(int i) {
        this.f12196f = i;
    }

    public void setSampling(int i) {
        if (i <= 0) {
            i = FAILURE_RESULT_TYPE;
        }
        this.f12197g = i;
    }
}
