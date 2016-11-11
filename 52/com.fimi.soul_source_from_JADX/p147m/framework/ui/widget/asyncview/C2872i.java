package p147m.framework.ui.widget.asyncview;

import android.graphics.Bitmap;
import java.io.File;
import java.io.InputStream;
import p147m.framework.p148a.C2853h;
import p147m.framework.p149b.C2863g;

/* renamed from: m.framework.ui.widget.asyncview.i */
class C2872i implements C2853h {
    final /* synthetic */ C2871h f14636a;
    private final /* synthetic */ File f14637b;
    private final /* synthetic */ C2867d f14638c;

    C2872i(C2871h c2871h, File file, C2867d c2867d) {
        this.f14636a = c2871h;
        this.f14637b = file;
        this.f14638c = c2867d;
    }

    public void m16567a(InputStream inputStream) {
        Bitmap a = C2863g.m16522a(new C2870g(inputStream));
        if (a == null || a.isRecycled()) {
            this.f14636a.f14635d = null;
            return;
        }
        this.f14636a.m16559a(a, this.f14637b);
        if (a != null) {
            this.f14636a.f14632a.f14617f.put(this.f14638c.f14623a, a);
            this.f14638c.m16549a(a);
        }
        this.f14636a.f14635d = null;
    }
}
