package com.fimi.soul.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: com.fimi.soul.utils.e */
public class C1965e {
    private static C1965e f10145d;
    private BufferedWriter f10146a;
    private File f10147b;
    private StringBuffer f10148c;

    static {
        f10145d = new C1965e();
    }

    private C1965e() {
        this.f10148c = new StringBuffer();
    }

    public static C1965e m12461a() {
        if (f10145d == null) {
            f10145d = new C1965e();
        }
        return f10145d;
    }

    public void m12462a(String str) {
        this.f10148c.append(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSSS", Locale.US).format(new Date())).append("   ");
        this.f10148c.append(str);
        this.f10148c.append("\n");
        try {
            this.f10146a.write(this.f10148c.toString());
            this.f10146a.flush();
            this.f10148c = this.f10148c.delete(0, this.f10148c.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void m12463b() {
        this.f10147b = new File(C1969i.m12494q());
        if (!this.f10147b.exists()) {
            this.f10147b.getParentFile().mkdirs();
            try {
                this.f10147b.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.f10146a == null) {
            try {
                this.f10146a = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f10147b, true)));
                this.f10146a.flush();
                this.f10148c = this.f10148c.delete(0, this.f10148c.length());
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public boolean m12464c() {
        if (!(this.f10147b == null || this.f10147b.exists())) {
            try {
                this.f10146a.close();
                this.f10146a = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (this.f10146a == null || this.f10147b == null || !this.f10147b.exists()) ? false : true;
    }
}
