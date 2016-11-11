package com.fimi.kernel.p076b.p080d;

import android.os.Message;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.p076b.p079c.C1133a;
import com.fimi.kernel.p076b.p079c.C1136d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.kernel.b.d.c */
public class C1140c extends C1099d implements C1138a {
    private static C1140c f5190f;
    boolean f5191a;
    private C1142e f5192b;
    private Socket f5193c;
    private DataOutputStream f5194d;
    private DataInputStream f5195e;
    private List<C1136d> f5196g;
    private List<C1133a> f5197h;
    private boolean f5198i;
    private StringBuffer f5199j;

    static {
        f5190f = null;
    }

    public C1140c() {
        this.f5193c = null;
        this.f5196g = new ArrayList();
        this.f5197h = new ArrayList();
        this.f5198i = true;
        this.f5191a = true;
        this.f5199j = new StringBuffer();
        this.f5192b = new C1142e();
    }

    public static synchronized C1140c m7868c() {
        C1140c c1140c;
        synchronized (C1140c.class) {
            if (f5190f == null) {
                f5190f = new C1140c();
            }
            c1140c = f5190f;
        }
        return c1140c;
    }

    private void m7870c(String str) {
        this.f5191a = false;
        Message message = new Message();
        message.obj = str;
        m7685a().sendMessage(message);
    }

    protected void m7874a(Message message) {
        if (message != null && message.obj != null) {
            for (C1136d a : this.f5196g) {
                a.m7861a(message.obj.toString());
            }
        }
    }

    public void m7875a(C1133a c1133a) {
        if (this.f5197h.contains(c1133a)) {
            this.f5197h.remove(c1133a);
        }
    }

    public void m7876a(C1136d c1136d) {
        if (this.f5196g.contains(c1136d)) {
            this.f5196g.remove(c1136d);
        }
    }

    public void m7877a(C1142e c1142e) {
        this.f5192b = c1142e;
    }

    public void m7878a(byte[] bArr) {
        try {
            if (this.f5198i && this.f5192b.m7895c()) {
                m7890h();
            }
            if (this.f5194d != null) {
                this.f5194d.write(bArr);
                this.f5194d.flush();
            }
        } catch (IOException e) {
            this.f5198i = true;
            e.printStackTrace();
        }
    }

    public void m7879a(byte[] bArr, int i, int i2) {
        try {
            if (this.f5198i && this.f5192b.m7895c()) {
                m7890h();
            }
            this.f5194d.write(bArr, i, i2);
            this.f5194d.flush();
        } catch (IOException e) {
            this.f5198i = true;
        }
    }

    public boolean m7880a(String str) {
        this.f5192b.m7723a(str);
        return m7886d();
    }

    public boolean m7881a(String str, int i) {
        this.f5192b.m7723a(str);
        this.f5192b.m7722a(i);
        return m7886d();
    }

    public void m7882b(C1133a c1133a) {
        if (c1133a != null) {
            this.f5197h.add(c1133a);
        }
    }

    public void m7883b(C1136d c1136d) {
        if (c1136d != null) {
            this.f5196g.add(c1136d);
        }
    }

    public void m7884b(String str) {
        m7878a(str.getBytes());
    }

    public boolean m7885b() {
        return this.f5193c == null ? false : this.f5193c.isConnected();
    }

    public boolean m7886d() {
        try {
            this.f5193c = new Socket(this.f5192b.m7721a(), this.f5192b.m7724b());
            this.f5193c.setSoLinger(true, 0);
            this.f5194d = new DataOutputStream(this.f5193c.getOutputStream());
            this.f5195e = new DataInputStream(this.f5193c.getInputStream());
            m7687a(new C1141d(this));
            return true;
        } catch (Exception e) {
            this.f5198i = true;
            e.printStackTrace();
            return false;
        }
    }

    public boolean m7887e() {
        return this.f5198i;
    }

    public C1142e m7888f() {
        return this.f5192b;
    }

    public void m7889g() {
        try {
            this.f5194d.close();
            this.f5194d = null;
        } catch (Exception e) {
        }
        try {
            this.f5195e.close();
            this.f5195e = null;
        } catch (Exception e2) {
        }
        try {
            this.f5193c.close();
        } catch (Exception e3) {
        }
        this.f5198i = true;
    }

    protected boolean m7890h() {
        m7889g();
        m7886d();
        return false;
    }
}
