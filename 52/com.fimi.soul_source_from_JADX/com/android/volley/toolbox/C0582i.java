package com.android.volley.toolbox;

import com.android.volley.C0555c;
import com.android.volley.ah;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.p122a.p123a.C2915a;

/* renamed from: com.android.volley.toolbox.i */
class C0582i {
    public long f3619a;
    public String f3620b;
    public String f3621c;
    public long f3622d;
    public long f3623e;
    public long f3624f;
    public long f3625g;
    public Map<String, String> f3626h;

    private C0582i() {
    }

    public C0582i(String str, C0555c c0555c) {
        this.f3620b = str;
        this.f3619a = (long) c0555c.f3506a.length;
        this.f3621c = c0555c.f3507b;
        this.f3622d = c0555c.f3508c;
        this.f3623e = c0555c.f3509d;
        this.f3624f = c0555c.f3510e;
        this.f3625g = c0555c.f3511f;
        this.f3626h = c0555c.f3512g;
    }

    public static C0582i m5214a(InputStream inputStream) {
        C0582i c0582i = new C0582i();
        if (C0580g.m5193a(inputStream) != 538247942) {
            throw new IOException();
        }
        c0582i.f3620b = C0580g.m5202c(inputStream);
        c0582i.f3621c = C0580g.m5202c(inputStream);
        if (c0582i.f3621c.equals(C2915a.f14760f)) {
            c0582i.f3621c = null;
        }
        c0582i.f3622d = C0580g.m5201b(inputStream);
        c0582i.f3623e = C0580g.m5201b(inputStream);
        c0582i.f3624f = C0580g.m5201b(inputStream);
        c0582i.f3625g = C0580g.m5201b(inputStream);
        c0582i.f3626h = C0580g.m5204d(inputStream);
        return c0582i;
    }

    public C0555c m5215a(byte[] bArr) {
        C0555c c0555c = new C0555c();
        c0555c.f3506a = bArr;
        c0555c.f3507b = this.f3621c;
        c0555c.f3508c = this.f3622d;
        c0555c.f3509d = this.f3623e;
        c0555c.f3510e = this.f3624f;
        c0555c.f3511f = this.f3625g;
        c0555c.f3512g = this.f3626h;
        return c0555c;
    }

    public boolean m5216a(OutputStream outputStream) {
        try {
            C0580g.m5195a(outputStream, 538247942);
            C0580g.m5197a(outputStream, this.f3620b);
            C0580g.m5197a(outputStream, this.f3621c == null ? C2915a.f14760f : this.f3621c);
            C0580g.m5196a(outputStream, this.f3622d);
            C0580g.m5196a(outputStream, this.f3623e);
            C0580g.m5196a(outputStream, this.f3624f);
            C0580g.m5196a(outputStream, this.f3625g);
            C0580g.m5199a(this.f3626h, outputStream);
            outputStream.flush();
            return true;
        } catch (IOException e) {
            ah.m5058b("%s", e.toString());
            return false;
        }
    }
}
