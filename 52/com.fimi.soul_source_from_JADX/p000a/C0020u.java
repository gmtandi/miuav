package p000a;

/* renamed from: a.u */
final class C0020u implements Runnable {
    final /* synthetic */ C0001q f98a;
    final /* synthetic */ C0018s f99b;
    final /* synthetic */ ae f100c;

    C0020u(C0001q c0001q, C0018s c0018s, ae aeVar) {
        this.f98a = c0001q;
        this.f99b = c0018s;
        this.f100c = aeVar;
    }

    public void run() {
        try {
            C0018s c0018s = (C0018s) this.f98a.then(this.f99b);
            if (c0018s == null) {
                this.f100c.m9b(null);
            } else {
                c0018s.m95a(new C0021v(this));
            }
        } catch (Exception e) {
            this.f100c.m8b(e);
        }
    }
}
