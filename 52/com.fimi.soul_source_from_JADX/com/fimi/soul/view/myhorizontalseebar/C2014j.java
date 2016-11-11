package com.fimi.soul.view.myhorizontalseebar;

/* renamed from: com.fimi.soul.view.myhorizontalseebar.j */
class C2014j implements Runnable {
    final /* synthetic */ ProgressBar f10914a;

    private C2014j(ProgressBar progressBar) {
        this.f10914a = progressBar;
    }

    public void run() {
        synchronized (this.f10914a) {
            int size = this.f10914a.f10861I.size();
            for (int i = 0; i < size; i++) {
                C2012h c2012h = (C2012h) this.f10914a.f10861I.get(i);
                this.f10914a.m12857a(c2012h.f10909a, c2012h.f10910b, c2012h.f10911c, true);
                c2012h.m12904c();
            }
            this.f10914a.f10861I.clear();
            this.f10914a.f10860H = false;
        }
    }
}
