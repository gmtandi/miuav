package p000a;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: a.y */
class C0024y implements C0001q<Void, C0018s<Void>> {
    final /* synthetic */ Callable f109a;
    final /* synthetic */ C0001q f110b;
    final /* synthetic */ Executor f111c;
    final /* synthetic */ C0016p f112d;
    final /* synthetic */ C0018s f113e;

    C0024y(C0018s c0018s, Callable callable, C0001q c0001q, Executor executor, C0016p c0016p) {
        this.f113e = c0018s;
        this.f109a = callable;
        this.f110b = c0001q;
        this.f111c = executor;
        this.f112d = c0016p;
    }

    public C0018s<Void> m116a(C0018s<Void> c0018s) {
        return ((Boolean) this.f109a.call()).booleanValue() ? C0018s.m77a(null).m106d(this.f110b, this.f111c).m106d((C0001q) this.f112d.m69a(), this.f111c) : C0018s.m77a(null);
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m116a(c0018s);
    }
}
