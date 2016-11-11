package com.fimi.kernel.p076b.p078b;

/* renamed from: com.fimi.kernel.b.b.p */
final class C1128p {
    private int f5175a;
    private long f5176b;
    private long f5177c;
    private long f5178d;
    private String f5179e;

    public C1128p(int i, long j, long j2, long j3, String str) {
        this.f5175a = i;
        this.f5176b = j;
        this.f5177c = j2;
        this.f5178d = j3;
        this.f5179e = str;
    }

    public String m7819a() {
        return this.f5179e;
    }

    public void m7820a(int i) {
        this.f5175a = i;
    }

    public void m7821a(long j) {
        this.f5176b = j;
    }

    public void m7822a(String str) {
        this.f5179e = str;
    }

    public int m7823b() {
        return this.f5175a;
    }

    public void m7824b(long j) {
        this.f5177c = j;
    }

    public long m7825c() {
        return this.f5176b;
    }

    public void m7826c(long j) {
        this.f5178d = j;
    }

    public long m7827d() {
        return this.f5177c;
    }

    public long m7828e() {
        return this.f5178d;
    }

    public String toString() {
        return "DownloadThreadInfo{threadId=" + this.f5175a + ", startPos=" + this.f5176b + ", endPos=" + this.f5177c + ", compeleteSize=" + this.f5178d + ", url='" + this.f5179e + '\'' + '}';
    }
}
