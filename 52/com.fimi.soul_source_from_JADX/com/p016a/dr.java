package com.p016a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import com.amap.api.location.APSServiceBase;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;

/* renamed from: com.a.dr */
public class dr implements APSServiceBase {
    private boolean f933A;
    private bs f934B;
    private boolean f935C;
    private String f936D;
    Context f937a;
    boolean f938b;
    Messenger f939c;
    String f940d;
    dt f941e;
    ds f942f;
    boolean f943g;
    Vector<Messenger> f944h;
    Vector<Messenger> f945i;
    volatile boolean f946j;
    boolean f947k;
    Object f948l;
    AmapLoc f949m;
    long f950n;
    JSONObject f951o;
    AmapLoc f952p;
    ServerSocket f953q;
    boolean f954r;
    Socket f955s;
    boolean f956t;
    du f957u;
    private volatile boolean f958v;
    private boolean f959w;
    private boolean f960x;
    private int f961y;
    private boolean f962z;

    public dr(Context context) {
        this.f938b = false;
        this.f939c = null;
        this.f958v = false;
        this.f959w = false;
        this.f960x = false;
        this.f940d = null;
        this.f941e = new dt(this, this);
        this.f942f = null;
        this.f943g = false;
        this.f944h = new Vector();
        this.f945i = new Vector();
        this.f961y = 0;
        this.f962z = false;
        this.f933A = false;
        this.f934B = null;
        this.f946j = false;
        this.f947k = false;
        this.f948l = new Object();
        this.f950n = dn.m1519b();
        this.f935C = true;
        this.f936D = C2915a.f14760f;
        this.f951o = new JSONObject();
        this.f953q = null;
        this.f954r = false;
        this.f955s = null;
        this.f956t = false;
        this.f937a = context.getApplicationContext();
    }

    private AmapLoc m1548a(int i, String str) {
        try {
            AmapLoc amapLoc = new AmapLoc();
            amapLoc.m5321b(i);
            amapLoc.m5322b(str);
            return amapLoc;
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    private AmapLoc m1550a(boolean z) {
        return this.f934B.m1252a(z);
    }

    private void m1552a(Messenger messenger) {
        try {
            Message obtain;
            if (dj.m1485r() && messenger != null) {
                dj.m1462a(Constants.VIA_RESULT_SUCCESS);
                obtain = Message.obtain();
                obtain.what = 100;
                messenger.send(obtain);
            }
            if (this.f939c != null) {
                obtain = Message.obtain();
                obtain.what = 6;
                this.f939c.send(obtain);
                this.f939c = null;
            }
            if (dj.m1463a()) {
                this.f934B.m1253a();
            }
            if (dj.m1471d() && !this.f962z) {
                this.f962z = true;
                this.f941e.sendEmptyMessage(4);
            }
            if (dj.m1473f() && !this.f933A) {
                this.f933A = true;
                this.f941e.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "checkConfig");
        }
    }

    private void m1556a(Socket socket) {
        BufferedReader bufferedReader;
        Throwable th;
        PrintStream printStream;
        PrintStream printStream2;
        String str = null;
        int i = 0;
        if (socket != null) {
            int i2 = 30000;
            try {
                String str2 = "jsonp1";
                System.currentTimeMillis();
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), C1142e.f5201a));
                    String readLine = bufferedReader.readLine();
                    if (readLine != null && readLine.length() > 0) {
                        String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        if (split != null && split.length > 1) {
                            split = split[1].split("\\?");
                            if (split != null && split.length > 1) {
                                split = split[1].split("&");
                                if (split != null && split.length > 0) {
                                    String str3 = str2;
                                    int i3 = 30000;
                                    String str4 = str3;
                                    while (i < split.length) {
                                        try {
                                            String[] split2 = split[i].split("=");
                                            if (split2 != null && split2.length > 1) {
                                                if ("to".equals(split2[0])) {
                                                    i3 = Integer.parseInt(split2[1]);
                                                }
                                                if ("callback".equals(split2[0])) {
                                                    str4 = split2[1];
                                                }
                                                if ("_".equals(split2[0])) {
                                                    Long.parseLong(split2[1]);
                                                }
                                            }
                                            i++;
                                        } catch (Throwable th2) {
                                            Throwable th3 = th2;
                                            str2 = str4;
                                            th = th3;
                                        }
                                    }
                                    str3 = str4;
                                    i2 = i3;
                                    str2 = str3;
                                }
                            }
                        }
                    }
                    try {
                        i = ev.f1151j;
                        ev.f1151j = i2;
                        long currentTimeMillis = System.currentTimeMillis();
                        if ((this.f952p == null || currentTimeMillis - this.f952p.m5343k() > 5000) && !dn.m1534d(this.f937a)) {
                            this.f952p = this.f934B.m1252a(false);
                            if (this.f952p.m5310a() != 0) {
                                str = str2 + "&&" + str2 + "({'package':'" + this.f940d + "','error_code':" + this.f952p.m5310a() + ",'error':'" + this.f952p.m5324c() + "'})";
                            }
                            ev.f1151j = i;
                        }
                    } catch (Throwable th4) {
                        ev.m1777a(th4, "APSServiceCore", "invoke part1");
                        ev.f1151j = i;
                    } catch (Throwable th5) {
                        th4 = th5;
                        try {
                            str = str2 + "&&" + str2 + "({'package':'" + this.f940d + "','error_code':1,'error':'params error'})";
                            ev.m1777a(th4, "APSServiceCore", "invoke part2");
                            try {
                                printStream = new PrintStream(socket.getOutputStream(), true, C1142e.f5201a);
                                printStream.println("HTTP/1.0 200 OK");
                                printStream.println("Content-Length:" + str.getBytes(C1142e.f5201a).length);
                                printStream.println();
                                printStream.println(str);
                                printStream.close();
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th42) {
                                    ev.m1777a(th42, "APSServiceCore", "invoke part4");
                                    return;
                                }
                            } catch (Throwable th422) {
                                ev.m1777a(th422, "APSServiceCore", "invoke part4");
                                return;
                            }
                        } catch (Throwable th6) {
                            th422 = th6;
                            try {
                                printStream2 = new PrintStream(socket.getOutputStream(), true, C1142e.f5201a);
                                printStream2.println("HTTP/1.0 200 OK");
                                printStream2.println("Content-Length:" + str.getBytes(C1142e.f5201a).length);
                                printStream2.println();
                                printStream2.println(str);
                                printStream2.close();
                                try {
                                    bufferedReader.close();
                                    socket.close();
                                } catch (Throwable th22) {
                                    ev.m1777a(th22, "APSServiceCore", "invoke part4");
                                }
                            } catch (Throwable th222) {
                                ev.m1777a(th222, "APSServiceCore", "invoke part4");
                            }
                            throw th422;
                        }
                    }
                    if (str == null) {
                        if (this.f952p == null) {
                            str = str2 + "&&" + str2 + "({'package':'" + this.f940d + "','error_code':8,'error':'unknown error'})";
                        } else {
                            AmapLoc amapLoc = this.f952p;
                            str = str2 + "&&" + str2 + "({'package':'" + this.f940d + "','error_code':0,'error':'','location':{'y':" + amapLoc.m5339i() + ",'precision':" + amapLoc.m5341j() + ",'x':" + amapLoc.m5337h() + "},'version_code':'" + "2.4.1" + "','version':'" + "2.4.1" + "'})";
                        }
                        if (dn.m1534d(this.f937a)) {
                            str = str2 + "&&" + str2 + "({'package':'" + this.f940d + "','error_code':36,'error':'app is background'})";
                        }
                    }
                    try {
                        printStream = new PrintStream(socket.getOutputStream(), true, C1142e.f5201a);
                        printStream.println("HTTP/1.0 200 OK");
                        printStream.println("Content-Length:" + str.getBytes(C1142e.f5201a).length);
                        printStream.println();
                        printStream.println(str);
                        printStream.close();
                        bufferedReader.close();
                        socket.close();
                    } catch (Throwable th4222) {
                        ev.m1777a(th4222, "APSServiceCore", "invoke part4");
                    }
                } catch (Throwable th7) {
                    th4222 = th7;
                    bufferedReader = null;
                    printStream2 = new PrintStream(socket.getOutputStream(), true, C1142e.f5201a);
                    printStream2.println("HTTP/1.0 200 OK");
                    printStream2.println("Content-Length:" + str.getBytes(C1142e.f5201a).length);
                    printStream2.println();
                    printStream2.println(str);
                    printStream2.close();
                    bufferedReader.close();
                    socket.close();
                    throw th4222;
                }
            } catch (Throwable th42222) {
                ev.m1777a(th42222, "APSServiceCore", "invoke part5");
            }
        }
    }

    private void m1557a(JSONObject jSONObject) {
        try {
            if (this.f934B != null) {
                this.f934B.m1256a(jSONObject);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "setExtra");
        }
    }

    private boolean m1563c() {
        boolean z;
        synchronized (this.f948l) {
            z = this.f946j;
        }
        return z;
    }

    private void m1564d() {
        try {
            this.f934B.m1265g();
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "bindService");
        }
    }

    private void m1567e() {
        try {
            if (!this.f959w) {
                this.f959w = true;
                this.f934B.m1259b(this.f937a);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "initAuth");
        }
    }

    private void m1569f() {
        try {
            if (!this.f958v) {
                m1570g();
            }
        } catch (Throwable th) {
            this.f935C = false;
            this.f936D = th.getMessage();
            ev.m1777a(th, "APSServiceCore", "init");
        }
    }

    private void m1570g() {
        try {
            ev.m1776a(this.f937a);
            this.f934B.m1255a("api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##" + fn.m1838c(this.f937a) + "##" + fn.m1842f(this.f937a));
            Object a = fp.m1853a(this.f937a, ev.m1772a("2.4.1"), null, true);
        } catch (Throwable th) {
            this.f936D = th.getMessage();
            this.f935C = false;
            ev.m1777a(th, "APSServiceCore", "doInit part3");
            return;
        }
        try {
            this.f951o.put(SharedPref.KEY, fn.m1842f(this.f937a));
            this.f951o.put("X-INFO", a);
            this.f951o.put(C3004e.f15013Y, "AMAP_Location_SDK_Android 2.4.1");
            this.f951o.put("netloc", Constants.VIA_RESULT_SUCCESS);
            this.f951o.put("gpsstatus", Constants.VIA_RESULT_SUCCESS);
            this.f951o.put("nbssid", Constants.VIA_RESULT_SUCCESS);
            if (!this.f951o.has("reversegeo")) {
                this.f951o.put("reversegeo", true);
            }
            if (!this.f951o.has("isOffset")) {
                this.f951o.put("isOffset", true);
            }
            this.f951o.put("wait1stwifi", Constants.VIA_RESULT_SUCCESS);
            this.f951o.put("autoup", Constants.VIA_RESULT_SUCCESS);
            this.f951o.put("upcolmobile", 1);
            this.f951o.put("enablegetreq", 1);
            if (!this.f951o.has("wifiactivescan")) {
                this.f951o.put("wifiactivescan", 1);
            }
        } catch (Throwable th2) {
            this.f936D = th2.getMessage();
            this.f935C = false;
            ev.m1777a(th2, "APSServiceCore", "doInit part2");
        }
        this.f958v = true;
        this.f934B.m1256a(this.f951o);
        this.f934B.m1254a(this.f937a);
    }

    private void m1572h() {
        try {
            m1582b();
            this.f958v = false;
            this.f959w = false;
            this.f960x = false;
            this.f962z = false;
            this.f933A = false;
            this.f961y = 0;
            this.f934B.m1258b();
            this.f944h.clear();
            C0248h.m1977a();
            if (this.f938b) {
                Process.killProcess(Process.myPid());
            }
            if (this.f941e != null) {
                this.f941e.removeCallbacksAndMessages(null);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "threadDestroy");
        }
    }

    private void m1574i() {
        try {
            if (this.f934B != null) {
                this.f934B.m1266h();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "startColl");
        }
    }

    public synchronized void m1581a() {
        try {
            if (!this.f956t) {
                this.f957u = new du(this);
                this.f957u.setPriority(5);
                this.f957u.start();
                this.f956t = true;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "startSocket");
        }
    }

    public synchronized void m1582b() {
        try {
            if (this.f953q != null) {
                this.f953q.close();
            }
            if (this.f955s != null) {
                this.f955s.close();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "stopScocket");
        }
        this.f953q = null;
        this.f957u = null;
        this.f956t = false;
        this.f954r = false;
    }

    public Handler getHandler() {
        return this.f941e;
    }

    public void onCreate() {
        try {
            this.f934B = new bs();
            this.f940d = this.f937a.getApplicationContext().getPackageName();
            this.f946j = true;
            this.f942f = new ds(this);
            this.f942f.setPriority(5);
            this.f942f.setName("serviceThread");
            this.f942f.start();
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "onCreate");
        }
    }

    public void onDestroy() {
        try {
            synchronized (this.f948l) {
                this.f946j = false;
                this.f948l.notify();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APSServiceCore", "onDestroy");
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
