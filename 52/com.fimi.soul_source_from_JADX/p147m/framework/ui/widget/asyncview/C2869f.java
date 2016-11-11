package p147m.framework.ui.widget.asyncview;

import java.util.TimerTask;

/* renamed from: m.framework.ui.widget.asyncview.f */
class C2869f extends TimerTask {
    final /* synthetic */ C2868e f14629a;
    private int f14630b;

    C2869f(C2868e c2868e) {
        this.f14629a = c2868e;
    }

    public void run() {
        if (this.f14629a.f14628a.f14618g) {
            this.f14630b--;
            if (this.f14630b <= 0) {
                this.f14630b = 100;
                this.f14629a.m16555a();
            }
        }
    }
}
