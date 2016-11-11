package p000a;

/* renamed from: a.v */
class C0021v implements C0001q<TContinuationResult, Void> {
    final /* synthetic */ C0020u f101a;

    C0021v(C0020u c0020u) {
        this.f101a = c0020u;
    }

    public Void m114a(C0018s<TContinuationResult> c0018s) {
        if (c0018s.m104c()) {
            this.f101a.f100c.m11c();
        } else if (c0018s.m107d()) {
            this.f101a.f100c.m8b(c0018s.m109f());
        } else {
            this.f101a.f100c.m9b(c0018s.m108e());
        }
        return null;
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m114a(c0018s);
    }
}
