package p000a;

/* renamed from: a.ac */
class ac implements C0001q<TResult, C0018s<TContinuationResult>> {
    final /* synthetic */ C0001q f7a;
    final /* synthetic */ C0018s f8b;

    ac(C0018s c0018s, C0001q c0001q) {
        this.f8b = c0018s;
        this.f7a = c0001q;
    }

    public C0018s<TContinuationResult> m4a(C0018s<TResult> c0018s) {
        return c0018s.m107d() ? C0018s.m76a(c0018s.m109f()) : c0018s.m104c() ? C0018s.m93h() : c0018s.m99b(this.f7a);
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m4a(c0018s);
    }
}
