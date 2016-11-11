package p000a;

import java.util.concurrent.Executor;

/* renamed from: a.aa */
class aa implements C0001q<TResult, Void> {
    final /* synthetic */ ae f1a;
    final /* synthetic */ C0001q f2b;
    final /* synthetic */ Executor f3c;
    final /* synthetic */ C0018s f4d;

    aa(C0018s c0018s, ae aeVar, C0001q c0001q, Executor executor) {
        this.f4d = c0018s;
        this.f1a = aeVar;
        this.f2b = c0001q;
        this.f3c = executor;
    }

    public Void m2a(C0018s<TResult> c0018s) {
        C0018s.m92d(this.f1a, this.f2b, c0018s, this.f3c);
        return null;
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m2a(c0018s);
    }
}
