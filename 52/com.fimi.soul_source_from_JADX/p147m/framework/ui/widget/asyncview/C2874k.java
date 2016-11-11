package p147m.framework.ui.widget.asyncview;

import android.graphics.Bitmap;

/* renamed from: m.framework.ui.widget.asyncview.k */
public class C2874k implements C2873j {
    public static final C2874k f14639a;

    static {
        f14639a = new C2874k();
    }

    private C2874k() {
    }

    public Bitmap m16569a(C2864a c2864a, Bitmap bitmap, String str) {
        return (str == null || str.trim().length() <= 0 || !str.equals(c2864a.getUrl())) ? null : bitmap;
    }
}
