package com.fimi.kernel.p076b.p078b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.fimi.kernel.p084e.C1174m;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.p122a.p123a.p152c.p156c.C2951i;

/* renamed from: com.fimi.kernel.b.b.b */
public class C1113b {
    private static ExecutorService f5125b = null;
    private static final int f5126c = 1;
    private static final int f5127d = 1;
    private static final int f5128e = 2;
    private static final int f5129f = 3;
    float f5130a;
    private C1120i f5131g;
    private C1119h f5132h;
    private C1119h f5133i;
    private long f5134j;
    private long f5135k;
    private long f5136l;
    private long f5137m;
    private String f5138n;
    private String f5139o;
    private String f5140p;
    private List<C1128p> f5141q;
    private List<C1117f> f5142r;
    private Context f5143s;
    private C1121j f5144t;
    private C1115d f5145u;
    private Object f5146v;
    private Bitmap f5147w;
    private String f5148x;
    private long f5149y;

    static {
        f5125b = Executors.newCachedThreadPool();
    }

    private C1113b() {
        this.f5136l = this.f5135k;
        this.f5137m = 0;
        this.f5142r = new ArrayList();
        this.f5145u = C1115d.Wait;
        this.f5149y = System.currentTimeMillis();
        this.f5130a = C2020f.f10933c;
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.f5144t = new C1121j();
        }
    }

    protected C1113b(String str, String str2) {
        this();
        this.f5138n = str2;
        this.f5139o = str;
    }

    protected static C1113b m7741a(Context context, String str) {
        C1113b b = C1131s.m7844a(context).m7852b(str);
        if (b != null) {
            b.f5143s = context;
            b.f5141q = C1131s.m7844a(context).m7854c(b.m7791m());
            long j = 0;
            long j2 = 0;
            for (C1128p c1128p : b.f5141q) {
                j2 += c1128p.m7828e();
                j = (c1128p.m7827d() - c1128p.m7825c()) + j;
            }
            b.f5135k = j2;
            b.f5134j = j;
        }
        return b;
    }

    protected static C1113b m7742a(Context context, String str, long j, Boolean bool, String str2, C1120i c1120i) {
        if (c1120i == null || context == null) {
            return null;
        }
        C1113b a = C1113b.m7741a(context, str);
        if (a != null) {
            a.f5140p = str.substring(str.lastIndexOf("/") + f5127d);
            a.f5143s = context;
            c1120i.m7797a(a);
            return a;
        }
        a = new C1113b();
        a.f5143s = context;
        a.f5139o = str;
        a.f5138n = str2;
        a.f5140p = str.substring(str.lastIndexOf("/") + f5127d);
        a.f5131g = c1120i;
        if (bool.booleanValue()) {
            a.m7769p();
            return a;
        }
        a.m7748a(Long.valueOf(j));
        return a;
    }

    protected static C1113b m7743a(Context context, String str, String str2, long j, Boolean bool, String str3, C1120i c1120i) {
        if (c1120i == null || context == null) {
            return null;
        }
        C1113b c1113b = new C1113b();
        c1113b.f5143s = context;
        c1113b.f5139o = str;
        c1113b.f5138n = str3;
        c1113b.f5140p = str.substring(str.lastIndexOf("/") + f5127d);
        c1113b.f5131g = c1120i;
        if (C1174m.m8196g(str3)) {
            c1113b.f5148x = str2;
        }
        if (bool.booleanValue()) {
            c1113b.m7769p();
            return c1113b;
        }
        c1113b.m7748a(Long.valueOf(j));
        return c1113b;
    }

    private void m7748a(Long l) {
        new C1116e(this, l.longValue()).start();
    }

    protected static void m7750b(Context context, String str, long j, Boolean bool, String str2, C1120i c1120i) {
        if (c1120i != null && context != null) {
            C1113b a = C1113b.m7741a(context, str);
            if (a != null) {
                a.f5143s = context;
                c1120i.m7797a(a);
                return;
            }
            a = new C1113b();
            a.f5143s = context;
            a.f5139o = str;
            a.f5138n = str2;
            a.f5140p = str.substring(str.lastIndexOf("/") + f5127d);
            a.f5131g = c1120i;
            if (bool.booleanValue()) {
                a.m7769p();
            } else {
                a.m7748a(Long.valueOf(j));
            }
        }
    }

    private void m7769p() {
        new C1116e(this).start();
    }

    private boolean m7771q() {
        File file = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f5139o).openConnection();
            httpURLConnection.setConnectTimeout(FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
            httpURLConnection.setRequestMethod(C2951i.f14860a);
            this.f5134j = (long) httpURLConnection.getContentLength();
            Log.d("Good", "\u83b7\u53d6\u6587\u4ef6\u5927\u5c0f" + this.f5139o + ":" + this.f5134j);
            file = C1174m.m8194f(this.f5138n);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.setLength(FimiMediaMeta.AV_CH_SIDE_RIGHT);
            randomAccessFile.close();
            httpURLConnection.disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Good", "File \u83b7\u53d6\u6587\u4ef6\u9519\u8bef" + e);
            if (file != null) {
                file.delete();
            }
            return false;
        }
    }

    private boolean m7772r() {
        File file = null;
        try {
            file = C1174m.m8194f(this.f5138n);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.setLength(FimiMediaMeta.AV_CH_SIDE_RIGHT);
            randomAccessFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Good", "File \u83b7\u53d6\u6587\u4ef6\u9519\u8bef" + e);
            if (file != null) {
                file.delete();
            }
            return false;
        }
    }

    public String m7773a() {
        return this.f5148x;
    }

    public void m7774a(Handler handler) {
        handler.post(new C1114c(this));
    }

    public void m7775a(C1113b c1113b) {
        if (c1113b != null && c1113b.f5142r != null && c1113b.f5142r.size() > 0) {
            for (int size = this.f5142r.size() - 1; size >= 0; size--) {
                ((C1117f) this.f5142r.get(size)).f5161b = true;
            }
            this.f5142r.clear();
        }
    }

    public void m7776a(C1119h c1119h) {
        this.f5132h = c1119h;
    }

    public void m7777a(Object obj) {
        this.f5146v = obj;
    }

    public void m7778a(String str) {
        this.f5148x = str;
    }

    public String m7779b() {
        return this.f5140p;
    }

    protected void m7780b(C1119h c1119h) {
        this.f5133i = c1119h;
    }

    public void m7781c() {
        if (this.f5141q != null) {
            if (this.f5145u != C1115d.Downloading) {
                this.f5145u = C1115d.Downloading;
                for (C1128p c1128p : this.f5141q) {
                    if (c1128p.m7825c() + c1128p.m7828e() < c1128p.m7827d()) {
                        Runnable c1117f = new C1117f(this, c1128p);
                        f5125b.execute(c1117f);
                        this.f5142r.add(c1117f);
                    }
                }
            } else {
                return;
            }
        }
        if (this.f5145u == C1115d.Error) {
            Log.d("Good", "\u91cd\u65b0\u521d\u59cb\u5316\u4efb\u52a1\u4fe1\u606f");
            m7769p();
        }
    }

    public void m7782d() {
        this.f5145u = C1115d.Wait;
    }

    public void m7783e() {
        this.f5145u = C1115d.Pause;
    }

    public void m7784f() {
        this.f5145u = C1115d.Completed;
    }

    public C1115d m7785g() {
        return this.f5145u;
    }

    public String m7786h() {
        return this.f5138n;
    }

    public long m7787i() {
        return this.f5134j;
    }

    public Object m7788j() {
        return this.f5146v;
    }

    public long m7789k() {
        return this.f5135k;
    }

    public long m7790l() {
        return this.f5137m;
    }

    public String m7791m() {
        return this.f5139o;
    }

    public Bitmap m7792n() {
        return this.f5147w;
    }

    public C1119h m7793o() {
        return this.f5132h;
    }

    public String toString() {
        return "DownloadTaskInfo{fileSize=" + this.f5134j + ", complete=" + this.f5135k + ", urlstring='" + this.f5139o + '\'' + '}';
    }
}
