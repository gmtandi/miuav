package com.fimi.kernel.p076b.p077a;

import android.os.Message;
import android.util.Log;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.p076b.p079c.C1134b;
import it.p074a.p075a.C1096l;
import it.p074a.p075a.C2787b;
import java.io.File;

/* renamed from: com.fimi.kernel.b.a.c */
public class C1102c extends C1099d implements C1101k {
    private static C1102c f5100b;
    private final String f5101a;
    private C2787b f5102c;
    private C1110j f5103d;
    private C1134b f5104e;
    private C1097a f5105f;
    private String f5106g;
    private String f5107h;
    private File f5108i;

    private C1102c() {
        this.f5101a = C1102c.class.getName();
        this.f5102c = null;
        this.f5103d = null;
        this.f5104e = null;
        this.f5105f = null;
        this.f5102c = new C2787b();
        this.f5103d = new C1110j();
    }

    private boolean m7689a(String str, int i, String str2, String str3) {
        try {
            if (this.f5102c.m15943l()) {
                return true;
            }
            this.f5102c.m15912a(str, i);
            this.f5102c.m15908a(str2, str3);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized C1102c m7690b() {
        C1102c c1102c;
        synchronized (C1102c.class) {
            f5100b = new C1102c();
            c1102c = f5100b;
        }
        return c1102c;
    }

    protected void m7698a(Message message) {
        boolean z = true;
        if (message.what == C1108i.ConnectServer.ordinal()) {
            if (message.arg1 != 1) {
                z = false;
            }
            if (this.f5104e != null) {
                this.f5104e.m7859a(z, "SUCCESS");
            }
        }
        if ((message.what == C1108i.DownloadFile.ordinal() || message.what == C1108i.UploadFile.ordinal()) && this.f5105f != null) {
            this.f5105f.m7681a(C1098b.values()[message.arg1], message.arg2);
        }
    }

    public void m7699a(C1110j c1110j) {
        this.f5103d = c1110j;
    }

    public void m7700a(C1134b c1134b) {
        this.f5104e = c1134b;
        m7687a(new C1103d(this));
    }

    public void m7701a(File file, C1097a c1097a) {
        try {
            this.f5102c.m15899a(file, (C1096l) c1097a);
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
        }
    }

    public void m7702a(String str, int i, C1134b c1134b) {
        this.f5103d.m7723a(str);
        this.f5103d.m7722a(i);
        m7700a(c1134b);
    }

    public void m7703a(String str, C1097a c1097a) {
        File file = new File(str);
        if (c1097a != null) {
            try {
                this.f5102c.m15899a(file, (C1096l) c1097a);
                return;
            } catch (Exception e) {
                Log.d(this.f5101a, e.toString());
                return;
            }
        }
        this.f5102c.m15896a(file);
    }

    public void m7704a(String str, C1134b c1134b) {
        this.f5103d.m7723a(str);
        m7700a(c1134b);
    }

    public void m7705a(String str, String str2, C1097a c1097a) {
        try {
            this.f5102c.m15904a(str, new File(str2), (C1096l) c1097a);
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
        }
    }

    public boolean m7706a(String str) {
        return m7689a(str, this.f5103d.m7724b(), this.f5103d.m7726c(), this.f5103d.m7728d());
    }

    public boolean m7707a(String str, int i) {
        return m7689a(str, i, this.f5103d.m7726c(), this.f5103d.m7728d());
    }

    public boolean m7708a(String str, String str2) {
        try {
            this.f5102c.m15919b(str, str2);
            return true;
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
            return false;
        }
    }

    public void m7709b(File file, C1097a c1097a) {
        this.f5108i = file;
        this.f5105f = c1097a;
        m7687a(new C1106g(this));
    }

    public void m7710b(String str, C1097a c1097a) {
        this.f5105f = c1097a;
        if (new File(str).isFile()) {
            m7709b(this.f5108i, c1097a);
        }
    }

    public void m7711b(String str, String str2, C1097a c1097a) {
        this.f5105f = c1097a;
        this.f5106g = str;
        this.f5107h = str2;
        m7687a(new C1104e(this));
    }

    public boolean m7712b(String str) {
        try {
            this.f5102c.m15930e(str);
            return true;
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
            return false;
        }
    }

    public C1110j m7713c() {
        return this.f5103d;
    }

    public boolean m7714d() {
        return this.f5102c.m15943l();
    }

    public boolean m7715e() {
        return m7689a(this.f5103d.m7721a(), this.f5103d.m7724b(), this.f5103d.m7726c(), this.f5103d.m7728d());
    }

    public String m7716f() {
        try {
            return this.f5102c.m15957y();
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
            return null;
        }
    }

    public String[] m7717g() {
        try {
            return this.f5102c.m15888D();
        } catch (Exception e) {
            Log.d(this.f5101a, e.toString());
            return null;
        }
    }

    public void m7718h() {
        try {
            this.f5102c.m15925c(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
