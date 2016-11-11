package p000a;

/* renamed from: a.ae */
public class ae {
    final /* synthetic */ C0018s f12a;

    private ae(C0018s c0018s) {
        this.f12a = c0018s;
    }

    public C0018s<TResult> m5a() {
        return this.f12a;
    }

    public boolean m6a(Exception exception) {
        boolean z = true;
        synchronized (this.f12a.f91d) {
            if (this.f12a.f92e) {
                z = false;
            } else {
                this.f12a.f92e = true;
                this.f12a.f95h = exception;
                this.f12a.f91d.notifyAll();
                this.f12a.m94k();
            }
        }
        return z;
    }

    public boolean m7a(TResult tResult) {
        boolean z = true;
        synchronized (this.f12a.f91d) {
            if (this.f12a.f92e) {
                z = false;
            } else {
                this.f12a.f92e = true;
                this.f12a.f94g = tResult;
                this.f12a.f91d.notifyAll();
                this.f12a.m94k();
            }
        }
        return z;
    }

    public void m8b(Exception exception) {
        if (!m6a(exception)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }

    public void m9b(TResult tResult) {
        if (!m7a((Object) tResult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public boolean m10b() {
        boolean z = true;
        synchronized (this.f12a.f91d) {
            if (this.f12a.f92e) {
                z = false;
            } else {
                this.f12a.f92e = true;
                this.f12a.f93f = true;
                this.f12a.f91d.notifyAll();
                this.f12a.m94k();
            }
        }
        return z;
    }

    public void m11c() {
        if (!m10b()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }
}
