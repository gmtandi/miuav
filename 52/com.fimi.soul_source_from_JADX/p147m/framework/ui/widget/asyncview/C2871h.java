package p147m.framework.ui.widget.asyncview;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import p147m.framework.p148a.C2852g;
import p147m.framework.p149b.C2857a;
import p147m.framework.p149b.C2863g;

/* renamed from: m.framework.ui.widget.asyncview.h */
class C2871h extends Thread {
    private C2866c f14632a;
    private long f14633b;
    private boolean f14634c;
    private C2867d f14635d;

    public C2871h(C2866c c2866c) {
        this.f14632a = c2866c;
        this.f14633b = System.currentTimeMillis();
    }

    private void m16558a() {
        int size = this.f14632a.f14619h.size();
        C2867d c2867d = size > 0 ? (C2867d) this.f14632a.f14619h.remove(size - 1) : null;
        if (c2867d != null) {
            Bitmap bitmap = (Bitmap) this.f14632a.f14617f.get(c2867d.f14623a);
            if (bitmap != null) {
                this.f14635d = c2867d;
                this.f14635d.f14625c = this;
                c2867d.m16549a(bitmap);
            } else if (new File(this.f14632a.f14620i, C2857a.m16456c(c2867d.f14623a)).exists()) {
                m16560a(c2867d);
                this.f14633b = System.currentTimeMillis();
                return;
            } else {
                if (this.f14632a.f14622k.size() > 40) {
                    while (this.f14632a.f14619h.size() > 0) {
                        this.f14632a.f14619h.remove(0);
                    }
                    this.f14632a.f14622k.remove(0);
                }
                this.f14632a.f14622k.add(c2867d);
            }
            this.f14633b = System.currentTimeMillis();
            return;
        }
        this.f14633b = System.currentTimeMillis();
        Thread.sleep(30);
    }

    private void m16559a(Bitmap bitmap, File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            CompressFormat compressFormat = CompressFormat.JPEG;
            String c = C2863g.m16529c(file.getAbsolutePath());
            if (c != null && (c.endsWith("png") || c.endsWith("gif"))) {
                compressFormat = CompressFormat.PNG;
            }
            OutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Throwable th) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void m16560a(C2867d c2867d) {
        Bitmap b;
        this.f14635d = c2867d;
        this.f14635d.f14625c = this;
        File file = new File(this.f14632a.f14620i, C2857a.m16456c(c2867d.f14623a));
        if (file.exists()) {
            b = C2863g.m16528b(file.getAbsolutePath());
            if (b != null) {
                this.f14632a.f14617f.put(c2867d.f14623a, b);
                c2867d.m16549a(b);
            }
            this.f14635d = null;
        } else {
            new C2852g().m16440a(c2867d.f14623a, new C2872i(this, file, c2867d));
            b = null;
        }
        if (b != null) {
            this.f14632a.f14617f.put(c2867d.f14623a, b);
            c2867d.m16549a(b);
        }
        this.f14635d = null;
    }

    private void m16564b() {
        C2867d c2867d;
        Bitmap bitmap;
        C2867d c2867d2 = null;
        if (this.f14632a.f14622k.size() > 0) {
            c2867d2 = (C2867d) this.f14632a.f14622k.remove(0);
        }
        if (c2867d2 == null) {
            int size = this.f14632a.f14619h.size();
            if (size > 0) {
                c2867d = (C2867d) this.f14632a.f14619h.remove(size - 1);
                if (c2867d == null) {
                    bitmap = (Bitmap) this.f14632a.f14617f.get(c2867d.f14623a);
                    if (bitmap == null) {
                        this.f14635d = c2867d;
                        this.f14635d.f14625c = this;
                        c2867d.m16549a(bitmap);
                    } else {
                        m16560a(c2867d);
                    }
                    this.f14633b = System.currentTimeMillis();
                }
                this.f14633b = System.currentTimeMillis();
                Thread.sleep(30);
                return;
            }
        }
        c2867d = c2867d2;
        if (c2867d == null) {
            this.f14633b = System.currentTimeMillis();
            Thread.sleep(30);
            return;
        }
        bitmap = (Bitmap) this.f14632a.f14617f.get(c2867d.f14623a);
        if (bitmap == null) {
            m16560a(c2867d);
        } else {
            this.f14635d = c2867d;
            this.f14635d.f14625c = this;
            c2867d.m16549a(bitmap);
        }
        this.f14633b = System.currentTimeMillis();
    }

    public void interrupt() {
        try {
            super.interrupt();
        } catch (Throwable th) {
        }
    }

    public void run() {
        while (this.f14632a.f14618g) {
            try {
                if (this.f14634c) {
                    m16558a();
                } else {
                    m16564b();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
