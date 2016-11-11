package com.xiaomi.xmpush.thrift;

/* renamed from: com.xiaomi.xmpush.thrift.b */
public enum C2730b {
    TCP_CONN_FAIL(1),
    TCP_CONN_TIME(2),
    PING_RTT(3);
    
    private final int f13483d;

    private C2730b(int i) {
        this.f13483d = i;
    }

    public int m15401a() {
        return this.f13483d;
    }
}
