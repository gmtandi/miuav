package p147m.framework.ui.widget.asyncview;

import android.graphics.Bitmap;
import org.p122a.p123a.C2915a;

/* renamed from: m.framework.ui.widget.asyncview.d */
public class C2867d {
    private String f14623a;
    private C2865b f14624b;
    private C2871h f14625c;
    private long f14626d;
    private Bitmap f14627e;

    public C2867d() {
        this.f14626d = System.currentTimeMillis();
    }

    private void m16549a(Bitmap bitmap) {
        this.f14627e = bitmap;
        if (this.f14624b != null) {
            this.f14624b.m16531a(this.f14623a, this.f14627e);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("url=").append(this.f14623a);
        stringBuilder.append("time=").append(this.f14626d);
        stringBuilder.append("worker=").append(this.f14625c.getName()).append(" (").append(this.f14625c.getId()).append(C2915a.f14760f);
        return stringBuilder.toString();
    }
}
