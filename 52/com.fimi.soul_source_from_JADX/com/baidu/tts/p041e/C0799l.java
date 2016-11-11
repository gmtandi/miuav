package com.baidu.tts.p041e;

/* renamed from: com.baidu.tts.e.l */
public enum C0799l {
    DEFAULT(6, 6000),
    MIX_ONLINE_REQUEST_TIMEOUT(4, 4000);
    
    private final long f4478c;
    private final long f4479d;

    private C0799l(long j, long j2) {
        this.f4478c = j;
        this.f4479d = j2;
    }

    public long m6747a() {
        return this.f4479d;
    }

    public int m6748b() {
        return (int) m6747a();
    }
}
