package p000a;

/* renamed from: a.ad */
final class ad implements Runnable {
    final /* synthetic */ C0001q f9a;
    final /* synthetic */ C0018s f10b;
    final /* synthetic */ ae f11c;

    ad(C0001q c0001q, C0018s c0018s, ae aeVar) {
        this.f9a = c0001q;
        this.f10b = c0018s;
        this.f11c = aeVar;
    }

    public void run() {
        try {
            this.f11c.m9b(this.f9a.then(this.f10b));
        } catch (Exception e) {
            this.f11c.m8b(e);
        }
    }
}
