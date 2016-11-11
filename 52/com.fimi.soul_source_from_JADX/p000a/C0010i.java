package p000a;

/* renamed from: a.i */
public enum C0010i {
    FAILED("failed", false),
    WEB("web", true),
    APP("app", true);
    
    private String f68d;
    private boolean f69e;

    private C0010i(String str, boolean z) {
        this.f68d = str;
        this.f69e = z;
    }

    public String m58a() {
        return this.f68d;
    }

    public boolean m59b() {
        return this.f69e;
    }
}
