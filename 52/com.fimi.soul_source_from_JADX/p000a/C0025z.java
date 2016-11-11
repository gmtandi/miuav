package p000a;

import java.util.concurrent.Executor;

/* renamed from: a.z */
class C0025z implements C0001q<TResult, Void> {
    final /* synthetic */ ae f114a;
    final /* synthetic */ C0001q f115b;
    final /* synthetic */ Executor f116c;
    final /* synthetic */ C0018s f117d;

    C0025z(C0018s c0018s, ae aeVar, C0001q c0001q, Executor executor) {
        this.f117d = c0018s;
        this.f114a = aeVar;
        this.f115b = c0001q;
        this.f116c = executor;
    }

    public Void m117a(C0018s<TResult> c0018s) {
        C0018s.m90c(this.f114a, this.f115b, c0018s, this.f116c);
        return null;
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m117a(c0018s);
    }
}
