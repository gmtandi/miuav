package p147m.framework.ui.widget.asyncview;

import java.util.Timer;

/* renamed from: m.framework.ui.widget.asyncview.e */
class C2868e extends Timer {
    private C2866c f14628a;

    public C2868e(C2866c c2866c) {
        this.f14628a = c2866c;
        schedule(new C2869f(this), 0, 200);
    }

    private void m16555a() {
        if (this.f14628a.f14618g) {
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            while (i < this.f14628a.f14621j.length) {
                if (this.f14628a.f14621j[i] == null) {
                    this.f14628a.f14621j[i] = new C2871h(this.f14628a);
                    this.f14628a.f14621j[i].setName("worker " + i);
                    this.f14628a.f14621j[i].f14634c = i == 0;
                    this.f14628a.f14621j[i].start();
                } else if (currentTimeMillis - this.f14628a.f14621j[i].f14633b > 20000) {
                    this.f14628a.f14621j[i].interrupt();
                    boolean b = this.f14628a.f14621j[i].f14634c;
                    this.f14628a.f14621j[i] = new C2871h(this.f14628a);
                    this.f14628a.f14621j[i].setName("worker " + i);
                    this.f14628a.f14621j[i].f14634c = b;
                    this.f14628a.f14621j[i].start();
                }
                i++;
            }
        }
    }
}
