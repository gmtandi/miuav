package p000a;

/* renamed from: a.ab */
class ab implements C0001q<TResult, C0018s<TContinuationResult>> {
    final /* synthetic */ C0001q f5a;
    final /* synthetic */ C0018s f6b;

    ab(C0018s c0018s, C0001q c0001q) {
        this.f6b = c0018s;
        this.f5a = c0001q;
    }

    public C0018s<TContinuationResult> m3a(C0018s<TResult> c0018s) {
        return c0018s.m107d() ? C0018s.m76a(c0018s.m109f()) : c0018s.m104c() ? C0018s.m93h() : c0018s.m95a(this.f5a);
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m3a(c0018s);
    }
}
