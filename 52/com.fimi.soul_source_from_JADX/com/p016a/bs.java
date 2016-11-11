package com.p016a;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.bs */
public class bs {
    private static int ae;
    public static final StringBuilder f647c;
    private boolean f648A;
    private boolean f649B;
    private long f650C;
    private long f651D;
    private int f652E;
    private String f653F;
    private String f654G;
    private di f655H;
    private Timer f656I;
    private TimerTask f657J;
    private int f658K;
    private dw f659L;
    private ec f660M;
    private int[] f661N;
    private String f662O;
    private String f663P;
    private long f664Q;
    private long f665R;
    private String f666S;
    private cn f667T;
    private AmapLoc f668U;
    private String f669V;
    private Timer f670W;
    private TimerTask f671X;
    private String f672Y;
    private int f673Z;
    public boolean f674a;
    private int aa;
    private boolean ab;
    private boolean ac;
    private long ad;
    dd f675b;
    bx f676d;
    int f677e;
    boolean f678f;
    AmapLoc f679g;
    Object f680h;
    public boolean f681i;
    int f682j;
    boolean f683k;
    bv f684l;
    private Context f685m;
    private ConnectivityManager f686n;
    private cr f687o;
    private co f688p;
    private ArrayList<ScanResult> f689q;
    private ArrayList<ScanResult> f690r;
    private HashMap<String, ArrayList<ScanResult>> f691s;
    private bw f692t;
    private WifiInfo f693u;
    private JSONObject f694v;
    private AmapLoc f695w;
    private long f696x;
    private long f697y;
    private long f698z;

    static {
        f647c = new StringBuilder();
        ae = -1;
    }

    public bs() {
        this.f685m = null;
        this.f686n = null;
        this.f687o = null;
        this.f689q = new ArrayList();
        this.f690r = new ArrayList();
        this.f691s = new HashMap();
        this.f692t = new bw();
        this.f693u = null;
        this.f694v = null;
        this.f695w = null;
        this.f696x = 0;
        this.f697y = 0;
        this.f698z = 0;
        this.f648A = false;
        this.f649B = false;
        this.f650C = 0;
        this.f651D = 0;
        this.f652E = 0;
        this.f653F = "00:00:00:00:00:00";
        this.f654G = null;
        this.f655H = null;
        this.f656I = null;
        this.f657J = null;
        this.f658K = 0;
        this.f659L = null;
        this.f660M = null;
        this.f674a = false;
        this.f661N = new int[]{0, 0, 0};
        this.f662O = null;
        this.f663P = null;
        this.f664Q = 0;
        this.f665R = 0;
        this.f666S = null;
        this.f667T = null;
        this.f675b = null;
        this.f677e = -1;
        this.f678f = false;
        this.f668U = null;
        this.f669V = null;
        this.f670W = null;
        this.f671X = null;
        this.f672Y = null;
        this.f673Z = 0;
        this.aa = 0;
        this.f679g = null;
        this.ab = true;
        this.ac = true;
        this.ad = 0;
        this.f680h = new Object();
        this.f681i = false;
        this.f682j = 12;
        this.f683k = true;
        this.f684l = new bv(this);
    }

    private boolean m1186A() {
        return (this.f687o == null || this.f686n == null) ? false : this.f687o.m1368a(this.f686n);
    }

    private void m1187B() {
        if (dn.m1514a(this.f694v, "poiid")) {
            try {
                String string = this.f694v.getString("poiid");
                if (TextUtils.isEmpty(string)) {
                    this.f654G = null;
                    return;
                } else if (string.length() > 32) {
                    this.f654G = null;
                    return;
                } else {
                    this.f654G = string;
                    return;
                }
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "setPoiid");
                return;
            }
        }
        this.f654G = null;
    }

    private String m1188C() {
        String str = null;
        try {
            str = dw.m1594a(C2537j.aq);
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "getCollVer");
        }
        return str;
    }

    private void m1189D() {
        if (this.f687o != null && this.f685m != null && this.f674a) {
            this.f687o.m1367a(this.f674a);
        }
    }

    private boolean m1190E() {
        if (this.f685m == null) {
            f647c.append("context is null");
            return false;
        } else if (TextUtils.isEmpty(ev.f1146e)) {
            f647c.append("src is null");
            return false;
        } else if (!TextUtils.isEmpty(ev.f1147f)) {
            return true;
        } else {
            f647c.append("license is null");
            return false;
        }
    }

    private void m1191F() {
        if (this.f685m != null && this.f661N[0] != 0) {
            SharedPreferences sharedPreferences = this.f685m.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int append : this.f661N) {
                    stringBuilder.append(append).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                try {
                    stringBuilder.deleteCharAt(this.f661N.length - 1);
                    sharedPreferences.edit().putString("coluphist", fy.m1904b(stringBuilder.toString().getBytes(C1142e.f5201a)));
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "setColUpHist");
                }
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
    }

    private AmapLoc m1192G() {
        cn cnVar;
        Throwable th;
        String b;
        AmapLoc amapLoc;
        String str;
        StringBuilder c;
        AmapLoc a;
        if (f647c.length() > 0) {
            f647c.delete(0, f647c.length());
        }
        try {
            if (this.f648A) {
                cnVar = null;
            } else {
                this.f688p.m1359f();
                this.f688p.m1357d();
                cnVar = this.f688p.m1355b();
            }
            try {
                m1262d();
                ArrayList arrayList = this.f689q;
                if (arrayList != null && arrayList.isEmpty()) {
                    this.f651D = dn.m1519b();
                    Collection a2 = this.f687o.m1365a();
                    if (a2 != null) {
                        arrayList.addAll(a2);
                        synchronized (this.f680h) {
                            if (this.f690r != null && this.f690r.isEmpty()) {
                                this.f690r.addAll(a2);
                            }
                        }
                    }
                }
                m1263e();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            cnVar = null;
            ev.m1777a(th, "APS", "doFirstLocate");
            b = m1257b(false);
            if (TextUtils.isEmpty(b)) {
                amapLoc = null != null ? null : new AmapLoc();
                amapLoc.m5321b(this.f682j);
                amapLoc.m5322b(f647c.toString());
            } else {
                str = b + "&" + this.ac + "&" + this.ab;
                c = m1261c(true);
                a = cv.m1394a().m1399a(str, c);
                if (dn.m1512a(a)) {
                    this.f665R = 0;
                    a.m5313a(4);
                    this.f695w = a;
                    m1193H();
                    return a;
                }
                a = m1204a(m1264f(), false, true);
                if (dn.m1512a(a)) {
                    a.m5333f("new");
                    this.f666S = c.toString();
                    this.f667T = cnVar;
                    this.f696x = dn.m1519b();
                    this.f695w = a;
                    cv.m1394a().m1401a(str, c, this.f695w, this.f685m, true);
                    m1193H();
                    amapLoc = a;
                } else {
                    amapLoc = m1202a(b, c.toString());
                    if (dn.m1512a(amapLoc)) {
                        return a;
                    }
                    this.f666S = c.toString();
                    amapLoc.m5333f(UriUtil.LOCAL_FILE_SCHEME);
                    amapLoc.m5313a(8);
                    amapLoc.m5322b("\u79bb\u7ebf\u5b9a\u4f4d\u7ed3\u679c\uff0c\u5728\u7ebf\u5b9a\u4f4d\u5931\u8d25\u539f\u56e0:" + a.m5329d());
                    this.f695w = amapLoc;
                }
            }
            return amapLoc;
        }
        b = m1257b(false);
        if (TextUtils.isEmpty(b)) {
            if (null != null) {
            }
            amapLoc.m5321b(this.f682j);
            amapLoc.m5322b(f647c.toString());
        } else {
            str = b + "&" + this.ac + "&" + this.ab;
            c = m1261c(true);
            a = cv.m1394a().m1399a(str, c);
            if (dn.m1512a(a)) {
                this.f665R = 0;
                a.m5313a(4);
                this.f695w = a;
                m1193H();
                return a;
            }
            a = m1204a(m1264f(), false, true);
            if (dn.m1512a(a)) {
                a.m5333f("new");
                this.f666S = c.toString();
                this.f667T = cnVar;
                this.f696x = dn.m1519b();
                this.f695w = a;
                cv.m1394a().m1401a(str, c, this.f695w, this.f685m, true);
                m1193H();
                amapLoc = a;
            } else {
                amapLoc = m1202a(b, c.toString());
                if (dn.m1512a(amapLoc)) {
                    return a;
                }
                this.f666S = c.toString();
                amapLoc.m5333f(UriUtil.LOCAL_FILE_SCHEME);
                amapLoc.m5313a(8);
                amapLoc.m5322b("\u79bb\u7ebf\u5b9a\u4f4d\u7ed3\u679c\uff0c\u5728\u7ebf\u5b9a\u4f4d\u5931\u8d25\u539f\u56e0:" + a.m5329d());
                this.f695w = amapLoc;
            }
        }
        return amapLoc;
    }

    private void m1193H() {
        this.f668U = null;
        this.f669V = null;
    }

    private void m1194I() {
        if (!dj.m1476i()) {
            m1195J();
        } else if (cm.f752a[1] > BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN) {
            m1195J();
        } else if (this.f670W == null || this.f671X == null) {
            this.f671X = new bu(this);
            this.f670W = new Timer("T-O", false);
            this.f670W.schedule(this.f671X, 0, Util.MILLSECONDS_OF_MINUTE);
        }
    }

    private void m1195J() {
        if (this.f671X != null) {
            this.f671X.cancel();
            this.f671X = null;
        }
        if (this.f670W != null) {
            this.f670W.cancel();
            this.f670W.purge();
            this.f670W = null;
        }
    }

    private void m1196K() {
        this.f673Z = 0;
        this.aa = 0;
    }

    private void m1197L() {
        if (this.f685m != null && cm.f752a[0] != 0) {
            SharedPreferences sharedPreferences = this.f685m.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                StringBuilder stringBuilder = new StringBuilder();
                String str = "activityoffdl";
                for (int append : cm.f752a) {
                    stringBuilder.append(append).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                try {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    sharedPreferences.edit().putString(str, dn.m1529c(stringBuilder.toString())).commit();
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "setOffDlHist");
                }
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
    }

    private double[] m1198M() {
        double[] dArr = new double[2];
        if (dn.m1512a(this.f695w)) {
            dArr[0] = this.f695w.m5339i();
            dArr[1] = this.f695w.m5337h();
        } else if (dn.m1512a(this.f679g)) {
            dArr[0] = this.f679g.m5339i();
            dArr[1] = this.f679g.m5337h();
        } else {
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
        }
        return dArr;
    }

    private void m1199N() {
        try {
            this.f666S = null;
            this.f695w = null;
            this.f665R = 0;
            this.f696x = 0;
            cg.m1293a().m1297b();
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "cleanCache");
        }
    }

    private AmapLoc m1202a(String str, String str2) {
        int i = 0;
        if (!dj.m1476i()) {
            return null;
        }
        if (str != null && str.equals(this.f669V) && this.f668U != null) {
            return this.f668U;
        }
        m1194I();
        ArrayList b = cz.m1420a().m1425b();
        try {
            int i2;
            AmapLoc a;
            if (cm.m1322b()) {
                ArrayList a2 = cm.m1310a(str, false);
                if (a2 != null) {
                    int size = a2.size();
                    for (i2 = 0; i2 < size; i2++) {
                        String str3 = (String) a2.get(i2);
                        a = m1203a(str, str2, null, str3.substring(str3.lastIndexOf(File.separator) + 1, str3.length()), 0);
                        if (dn.m1512a(a)) {
                            this.f669V = str;
                            this.f668U = a;
                            return a;
                        }
                    }
                }
            }
            i2 = b.size();
            if (i2 != 0) {
                while (i < i2) {
                    a = m1203a(str, str2, null, ((cy) b.get(i)).m1418a(), 0);
                    if (dn.m1512a(a)) {
                        this.f669V = str;
                        this.f668U = a;
                        return a;
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "getPureOfflineLocation");
        }
        return null;
    }

    private AmapLoc m1203a(String str, String str2, double[] dArr, String str3, int i) {
        if (!dn.m1543k()) {
            return null;
        }
        if (TextUtils.isEmpty(str3)) {
            if (dArr == null) {
                dArr = m1198M();
            }
            if (dArr[0] == 0.0d || dArr[1] == 0.0d) {
                return null;
            }
        }
        double[] dArr2 = dArr;
        dn.m1519b();
        return cm.m1307a(dArr2, str3, str, str2, i, this.f685m, new int[]{this.aa, this.f673Z});
    }

    private AmapLoc m1204a(String str, boolean z, boolean z2) {
        AmapLoc amapLoc;
        if (this.f685m == null) {
            f647c.append("context is null");
            amapLoc = new AmapLoc();
            amapLoc.m5321b(1);
            amapLoc.m5322b(f647c.toString());
            return amapLoc;
        } else if (str == null || str.length() == 0) {
            amapLoc = new AmapLoc();
            amapLoc.m5321b(3);
            amapLoc.m5322b(f647c.toString());
            return amapLoc;
        } else {
            amapLoc = new AmapLoc();
            df dfVar = new df();
            try {
                byte[] a = this.f675b.m1445a(this.f685m, this.f694v, this.f655H, ev.m1775a());
                if (a == null) {
                    amapLoc = new AmapLoc();
                    amapLoc.m5321b(4);
                    f647c.append("please check the network");
                    amapLoc.m5322b(f647c.toString());
                    return amapLoc;
                }
                this.ad = dn.m1502a();
                String str2 = new String(a, C1142e.f5201a);
                if (str2.contains("\"status\":\"0\"")) {
                    return dfVar.m1454b(str2);
                }
                String a2 = cs.m1376a(a);
                if (a2 == null) {
                    amapLoc = new AmapLoc();
                    amapLoc.m5321b(5);
                    f647c.append("decrypt response data error");
                    amapLoc.m5322b(f647c.toString());
                    return amapLoc;
                }
                AmapLoc a3 = dfVar.m1453a(a2);
                if (dn.m1512a(a3)) {
                    if (a3.m5308E() != null) {
                    }
                    if (a3.m5310a() == 0 && a3.m5318b() == 0) {
                        if ("-5".equals(a3.m5347m()) || Constants.VIA_TO_TYPE_QQ_GROUP.equals(a3.m5347m()) || Constants.VIA_SSO_LOGIN.equals(a3.m5347m()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(a3.m5347m()) || "24".equals(a3.m5347m()) || "-1".equals(a3.m5347m())) {
                            a3.m5313a(5);
                        } else {
                            a3.m5313a(6);
                        }
                        a3.m5322b(a3.m5347m());
                    }
                    a3.m5317a(this.ac);
                    a3.m5323b(this.ab);
                    return a3;
                } else if (a3 != null) {
                    this.f662O = a3.m5349n();
                    a3.m5321b(6);
                    f647c.append("location faile retype:" + a3.m5347m() + " rdesc:" + (this.f662O != null ? this.f662O : "null"));
                    a3.m5322b(f647c.toString());
                    return a3;
                } else {
                    amapLoc = new AmapLoc();
                    amapLoc.m5321b(6);
                    f647c.append("location is null");
                    amapLoc.m5322b(f647c.toString());
                    return amapLoc;
                }
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "getApsLoc");
                amapLoc = new AmapLoc();
                amapLoc.m5321b(4);
                f647c.append("please check the network");
                amapLoc.m5322b(f647c.toString());
                return amapLoc;
            }
        }
    }

    private String m1205a(int i, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", i);
        jSONObject.put("d", i2);
        jSONObject.put("u", i3);
        return jSONObject.toString();
    }

    private StringBuilder m1207a(Object obj) {
        String str;
        boolean z;
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = Constants.VIA_RESULT_SUCCESS;
        String str3 = Constants.VIA_RESULT_SUCCESS;
        String str4 = Constants.VIA_RESULT_SUCCESS;
        String str5 = Constants.VIA_RESULT_SUCCESS;
        String str6 = Constants.VIA_RESULT_SUCCESS;
        String str7 = ev.f1150i;
        ev.f1143b = "888888888888888";
        ev.f1144c = "888888888888888";
        ev.f1145d = C2915a.f14760f;
        int a = dn.m1499a(-32768, 32767);
        String str8 = C2915a.f14760f;
        String str9 = C2915a.f14760f;
        String str10 = C2915a.f14760f;
        String str11 = ev.f1146e;
        String str12 = ev.f1147f;
        if (this.ac) {
            str = str11;
            str11 = str12;
        } else {
            str = "UC_nlp_20131029";
            str11 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        StringBuilder stringBuilder4 = new StringBuilder();
        co coVar = this.f688p;
        int c = coVar.m1356c();
        TelephonyManager e = coVar.m1358e();
        ArrayList a2 = coVar.m1350a();
        String str13 = c == 2 ? Constants.VIA_TO_TYPE_QQ_GROUP : str2;
        if (e != null) {
            if (TextUtils.isEmpty(ev.f1143b)) {
                ev.f1143b = "888888888888888";
                try {
                    ev.f1143b = fw.m1892q(this.f685m);
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "getApsReq part4");
                }
            } else if ("888888888888888".equals(ev.f1143b)) {
                ev.f1143b = "888888888888888";
                try {
                    ev.f1143b = fw.m1892q(this.f685m);
                } catch (Throwable th2) {
                    ev.m1777a(th2, "APS", "getApsReq part3");
                }
            }
            if (TextUtils.isEmpty(ev.f1143b)) {
                ev.f1143b = "888888888888888";
            }
            if (TextUtils.isEmpty(ev.f1144c)) {
                ev.f1144c = "888888888888888";
                try {
                    ev.f1144c = e.getSubscriberId();
                } catch (Throwable th22) {
                    ev.m1777a(th22, "APS", "getApsReq part2");
                }
            } else if ("888888888888888".equals(ev.f1144c)) {
                ev.f1144c = "888888888888888";
                try {
                    ev.f1144c = e.getSubscriberId();
                } catch (Throwable th222) {
                    ev.m1777a(th222, "APS", "getApsReq part1");
                }
            }
            if (TextUtils.isEmpty(ev.f1144c)) {
                ev.f1144c = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = this.f686n.getActiveNetworkInfo();
        } catch (Throwable th3) {
            ev.m1777a(th3, "APS", "getApsReq part");
        }
        if (dn.m1500a(networkInfo) != -1) {
            str9 = dn.m1522b(e);
            if (m1240p()) {
                if (m1216a(this.f693u)) {
                    str12 = Constants.VIA_SSO_LOGIN;
                    if (m1240p()) {
                        m1238n();
                        str8 = str9;
                        str9 = str12;
                    } else {
                        str8 = str9;
                        str9 = str12;
                    }
                }
            }
            str12 = Constants.VIA_TO_TYPE_QQ_GROUP;
            if (m1240p()) {
                str8 = str9;
                str9 = str12;
            } else {
                m1238n();
                str8 = str9;
                str9 = str12;
            }
        } else {
            this.f693u = null;
        }
        m1187B();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"");
        stringBuilder.append("GBK").append("\"?>");
        stringBuilder.append("<Cell_Req ver=\"3.0\"><HDR version=\"3.0\" cdma=\"");
        stringBuilder.append(str13);
        stringBuilder.append("\" gtype=\"").append(str3);
        if (str3.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
            stringBuilder.append("\" gmock=\"").append(this.f649B ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS);
        }
        stringBuilder.append("\" glong=\"").append(str4);
        stringBuilder.append("\" glat=\"").append(str5);
        stringBuilder.append("\" precision=\"").append(str6);
        stringBuilder.append("\"><src>").append(str);
        stringBuilder.append("</src><license>").append(str11);
        stringBuilder.append("</license><key>").append(str7);
        stringBuilder.append("</key><clientid>").append(ev.f1149h);
        stringBuilder.append("</clientid><imei>").append(ev.f1143b);
        stringBuilder.append("</imei><imsi>").append(ev.f1144c);
        stringBuilder.append("</imsi><reqid>").append(a);
        stringBuilder.append("</reqid><smac>").append(this.f653F);
        stringBuilder.append("</smac><sdkv>").append(m1260c());
        stringBuilder.append("</sdkv><corv>").append(m1188C());
        stringBuilder.append("</corv><poiid>").append(this.f654G);
        stringBuilder.append("</poiid></HDR><DRR phnum=\"").append(ev.f1145d);
        stringBuilder.append("\" nettype=\"").append(str8);
        stringBuilder.append("\" inftype=\"").append(str9).append("\">");
        if (!a2.isEmpty()) {
            StringBuilder stringBuilder5 = new StringBuilder();
            cn cnVar;
            switch (c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m1196K();
                    cnVar = (cn) a2.get(0);
                    stringBuilder5.delete(0, stringBuilder5.length());
                    stringBuilder5.append("<mcc>").append(cnVar.f759a).append("</mcc>");
                    stringBuilder5.append("<mnc>").append(cnVar.f760b).append("</mnc>");
                    stringBuilder5.append("<lac>").append(cnVar.f761c).append("</lac>");
                    stringBuilder5.append("<cellid>").append(cnVar.f762d);
                    stringBuilder5.append("</cellid>");
                    stringBuilder5.append("<signal>").append(cnVar.f768j);
                    stringBuilder5.append("</signal>");
                    str2 = stringBuilder5.toString();
                    for (int i = 1; i < a2.size(); i++) {
                        cnVar = (cn) a2.get(i);
                        stringBuilder2.append(cnVar.f761c).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        stringBuilder2.append(cnVar.f762d).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        stringBuilder2.append(cnVar.f768j);
                        if (i < a2.size() - 1) {
                            stringBuilder2.append("*");
                        }
                    }
                    str12 = str2;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    cnVar = (cn) a2.get(0);
                    stringBuilder5.delete(0, stringBuilder5.length());
                    stringBuilder5.append("<mcc>").append(cnVar.f759a).append("</mcc>");
                    stringBuilder5.append("<sid>").append(cnVar.f765g).append("</sid>");
                    stringBuilder5.append("<nid>").append(cnVar.f766h).append("</nid>");
                    stringBuilder5.append("<bid>").append(cnVar.f767i).append("</bid>");
                    if (cnVar.f764f <= 0 || cnVar.f763e <= 0) {
                        m1196K();
                    } else {
                        this.f673Z = cnVar.f764f;
                        this.aa = cnVar.f763e;
                        stringBuilder5.append("<lon>").append(cnVar.f764f).append("</lon>");
                        stringBuilder5.append("<lat>").append(cnVar.f763e).append("</lat>");
                    }
                    stringBuilder5.append("<signal>").append(cnVar.f768j).append("</signal>");
                    str12 = stringBuilder5.toString();
                    break;
                default:
                    m1196K();
                    str12 = str10;
                    break;
            }
            stringBuilder5.delete(0, stringBuilder5.length());
            str10 = str12;
        }
        if (m1240p()) {
            int length;
            if (m1216a(this.f693u)) {
                stringBuilder4.append(this.f693u.getBSSID()).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                int rssi = this.f693u.getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > Opcodes.LAND) {
                    rssi = 0;
                }
                stringBuilder4.append(rssi).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                str12 = this.f693u.getSSID();
                try {
                    length = this.f693u.getSSID().getBytes(C1142e.f5201a).length;
                } catch (Throwable th32) {
                    ev.m1777a(th32, "APS", "getApsReq");
                    length = 32;
                }
                if (length >= 32) {
                    str12 = "unkwn";
                }
                stringBuilder4.append(str12.replace("*", "."));
            }
            List list = this.f689q;
            int min = Math.min(list.size(), 15);
            for (length = 0; length < min; length++) {
                ScanResult scanResult = (ScanResult) list.get(length);
                stringBuilder3.append(scanResult.BSSID).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                stringBuilder3.append(scanResult.level).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                stringBuilder3.append(scanResult.SSID).append("*");
            }
        } else {
            m1238n();
        }
        stringBuilder.append(str10);
        stringBuilder.append(String.format(Locale.US, "<nb>%s</nb>", new Object[]{stringBuilder2}));
        if (stringBuilder3.length() == 0) {
            stringBuilder3.append(stringBuilder4);
            stringBuilder.append("<macs>");
            stringBuilder.append(String.format(Locale.US, "<![CDATA[%s]]>", new Object[]{stringBuilder4}));
            stringBuilder.append("</macs>");
        } else {
            stringBuilder3.deleteCharAt(stringBuilder3.length() - 1);
            stringBuilder.append("<macs>");
            stringBuilder.append(String.format(Locale.US, "<![CDATA[%s]]>", new Object[]{stringBuilder3}));
            stringBuilder.append("</macs>");
            stringBuilder.append("<macsage>").append(dn.m1519b() - this.f651D);
            stringBuilder.append("</macsage>");
        }
        stringBuilder.append("<mmac>");
        stringBuilder.append(String.format(Locale.US, "<![CDATA[%s]]>", new Object[]{stringBuilder4}));
        stringBuilder.append("</mmac>").append("</DRR></Cell_Req>");
        m1212a(stringBuilder);
        if (dn.m1514a(this.f694v, "reversegeo")) {
            try {
                z = this.f694v.getBoolean("reversegeo");
            } catch (Throwable th2222) {
                ev.m1777a(th2222, "APS", "getApsReq part");
            }
            if (z) {
                this.f655H.f854b = (short) 2;
            } else {
                this.f655H.f854b = (short) 0;
            }
            if (dn.m1514a(this.f694v, "multi")) {
                try {
                    if (this.f694v.getString("multi").equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                        this.f655H.f854b = (short) 1;
                    }
                } catch (Throwable th22222) {
                    ev.m1777a(th22222, "APS", "getApsReq");
                }
            }
            this.f655H.f855c = str;
            this.f655H.f856d = str11;
            this.f655H.f858f = dn.m1537f();
            this.f655H.f859g = "android" + dn.m1539g();
            if (TextUtils.isEmpty(ev.f1152k)) {
                ev.f1152k = dn.m1521b(this.f685m);
            }
            this.f655H.f860h = ev.f1152k;
            this.f655H.f861i = str13;
            this.f655H.f862j = str3;
            this.f655H.f863k = this.f649B ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS;
            this.f655H.f864l = str4;
            this.f655H.f865m = str5;
            this.f655H.f866n = str6;
            this.f655H.f867o = str7;
            this.f655H.f868p = ev.f1143b;
            this.f655H.f869q = ev.f1144c;
            this.f655H.f871s = String.valueOf(a);
            this.f655H.f872t = this.f653F;
            this.f655H.f874v = m1260c();
            this.f655H.f875w = m1188C();
            this.f655H.f851F = this.f654G;
            this.f655H.f873u = ev.f1145d;
            this.f655H.f876x = str8;
            this.f655H.f877y = str9;
            this.f655H.f878z = String.valueOf(c);
            this.f655H.f846A = str10;
            this.f655H.f847B = stringBuilder2.toString();
            this.f655H.f849D = stringBuilder3.toString();
            this.f655H.f850E = String.valueOf(dn.m1519b() - this.f651D);
            this.f655H.f848C = stringBuilder4.toString();
            stringBuilder2.delete(0, stringBuilder2.length());
            stringBuilder3.delete(0, stringBuilder3.length());
            stringBuilder4.delete(0, stringBuilder4.length());
            return stringBuilder;
        }
        z = true;
        if (z) {
            this.f655H.f854b = (short) 0;
        } else {
            this.f655H.f854b = (short) 2;
        }
        if (dn.m1514a(this.f694v, "multi")) {
            if (this.f694v.getString("multi").equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                this.f655H.f854b = (short) 1;
            }
        }
        this.f655H.f855c = str;
        this.f655H.f856d = str11;
        this.f655H.f858f = dn.m1537f();
        this.f655H.f859g = "android" + dn.m1539g();
        if (TextUtils.isEmpty(ev.f1152k)) {
            ev.f1152k = dn.m1521b(this.f685m);
        }
        this.f655H.f860h = ev.f1152k;
        this.f655H.f861i = str13;
        this.f655H.f862j = str3;
        if (this.f649B) {
        }
        this.f655H.f863k = this.f649B ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS;
        this.f655H.f864l = str4;
        this.f655H.f865m = str5;
        this.f655H.f866n = str6;
        this.f655H.f867o = str7;
        this.f655H.f868p = ev.f1143b;
        this.f655H.f869q = ev.f1144c;
        this.f655H.f871s = String.valueOf(a);
        this.f655H.f872t = this.f653F;
        this.f655H.f874v = m1260c();
        this.f655H.f875w = m1188C();
        this.f655H.f851F = this.f654G;
        this.f655H.f873u = ev.f1145d;
        this.f655H.f876x = str8;
        this.f655H.f877y = str9;
        this.f655H.f878z = String.valueOf(c);
        this.f655H.f846A = str10;
        this.f655H.f847B = stringBuilder2.toString();
        this.f655H.f849D = stringBuilder3.toString();
        this.f655H.f850E = String.valueOf(dn.m1519b() - this.f651D);
        this.f655H.f848C = stringBuilder4.toString();
        stringBuilder2.delete(0, stringBuilder2.length());
        stringBuilder3.delete(0, stringBuilder3.length());
        stringBuilder4.delete(0, stringBuilder4.length());
        return stringBuilder;
    }

    private void m1209a(SharedPreferences sharedPreferences) {
        String str = null;
        if (this.f685m != null && sharedPreferences != null) {
            String str2 = "smac";
            if (sharedPreferences.contains(str2)) {
                try {
                    str = fy.m1904b(sharedPreferences.getString(str2, null).getBytes(C1142e.f5201a));
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "getSmac");
                    sharedPreferences.edit().remove(str2).commit();
                }
            }
            if (!TextUtils.isEmpty(str) && !str.equals("00:00:00:00:00:00")) {
                this.f653F = str;
            }
        }
    }

    private void m1212a(StringBuilder stringBuilder) {
        int i = 0;
        if (stringBuilder != null) {
            String[] strArr = new String[]{" phnum=\"\"", " nettype=\"\"", " nettype=\"UNKWN\"", " inftype=\"\"", "<macs><![CDATA[]]></macs>", "<nb></nb>", "<mmac><![CDATA[]]></mmac>", " gtype=\"0\"", " gmock=\"0\"", " glong=\"0.0\"", " glat=\"0.0\"", " precision=\"0.0\"", " glong=\"0\"", " glat=\"0\"", " precision=\"0\"", "<smac>null</smac>", "<smac>00:00:00:00:00:00</smac>", "<imei>000000000000000</imei>", "<imsi>000000000000000</imsi>", "<mcc>000</mcc>", "<mcc>0</mcc>", "<lac>0</lac>", "<cellid>0</cellid>", "<key></key>", "<poiid></poiid>", "<poiid>null</poiid>"};
            int length = strArr.length;
            while (i < length) {
                String str = strArr[i];
                while (stringBuilder.indexOf(str) != -1) {
                    int indexOf = stringBuilder.indexOf(str);
                    stringBuilder.delete(indexOf, str.length() + indexOf);
                }
                i++;
            }
            while (stringBuilder.indexOf("*<") != -1) {
                stringBuilder.deleteCharAt(stringBuilder.indexOf("*<"));
            }
        }
    }

    private void m1213a(boolean z, int i) {
        if (z) {
            m1220c(i);
        } else {
            m1249y();
        }
    }

    private boolean m1214a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (Throwable e) {
            ev.m1777a(e, "APS", "wifiSigFine");
        }
        return i2 >= 1;
    }

    private boolean m1215a(long j) {
        if (dn.m1519b() - j >= 800) {
            return false;
        }
        long j2 = 0;
        if (dn.m1512a(this.f695w)) {
            j2 = dn.m1502a() - this.f695w.m5343k();
        }
        return j2 <= 10000;
    }

    private boolean m1216a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getSSID() == null || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :") || TextUtils.isEmpty(wifiInfo.getSSID())) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1218b(int r7) {
        /*
        r6 = this;
        r1 = 674234367; // 0x282fffff float:9.769962E-15 double:3.33116038E-315;
        r0 = 70254591; // 0x42fffff float:2.0688699E-36 double:3.471038E-316;
        r2 = r6.m1241q();
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r6.m1250z();	 Catch:{ Throwable -> 0x00a2 }
        switch(r7) {
            case 0: goto L_0x0013;
            case 1: goto L_0x00ac;
            case 2: goto L_0x00af;
            default: goto L_0x0013;
        };	 Catch:{ Throwable -> 0x00a2 }
    L_0x0013:
        r1 = r6.f659L;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r3 = 1;
        r4 = 1;
        r3 = r6.m1205a(r3, r0, r4);	 Catch:{ Throwable -> 0x00a2 }
        r1.m1627a(r2, r3);	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.f659L;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.m1632e();	 Catch:{ Throwable -> 0x00a2 }
        r6.f660M = r1;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.f660M;	 Catch:{ Throwable -> 0x00a2 }
        if (r1 == 0) goto L_0x008c;
    L_0x002b:
        r1 = r6.f660M;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.m1639a();	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.f675b;	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.f685m;	 Catch:{ Throwable -> 0x00a2 }
        r4 = "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&";
        r5 = 0;
        r2 = r2.m1443a(r1, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.m1241q();	 Catch:{ Throwable -> 0x00a2 }
        if (r3 == 0) goto L_0x008c;
    L_0x0042:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x00a2 }
        if (r3 != 0) goto L_0x00e8;
    L_0x0048:
        r3 = "true";
        r2 = r2.equals(r3);	 Catch:{ Throwable -> 0x00a2 }
        if (r2 == 0) goto L_0x00e8;
    L_0x0050:
        r2 = r6.f659L;	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.f660M;	 Catch:{ Throwable -> 0x00a2 }
        r4 = 1;
        r5 = 1;
        r0 = r6.m1205a(r4, r0, r5);	 Catch:{ Throwable -> 0x00a2 }
        r2.m1627a(r3, r0);	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r0 = "yyyyMMdd";
        r0 = com.p016a.dn.m1504a(r2, r0);	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ Throwable -> 0x00a2 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Throwable -> 0x00a2 }
        r2 = r0.equals(r2);	 Catch:{ Throwable -> 0x00a2 }
        if (r2 == 0) goto L_0x00bd;
    L_0x0074:
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r3 = r0[r2];	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.length;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1 + r3;
        r0[r2] = r1;	 Catch:{ Throwable -> 0x00a2 }
    L_0x007d:
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r1 = 2;
        r2 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 2;
        r2 = r2[r3];	 Catch:{ Throwable -> 0x00a2 }
        r2 = r2 + 1;
        r0[r1] = r2;	 Catch:{ Throwable -> 0x00a2 }
        r6.m1191F();	 Catch:{ Throwable -> 0x00a2 }
    L_0x008c:
        r6.m1244t();	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.m1241q();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 == 0) goto L_0x00fc;
    L_0x0095:
        r0 = r6.f659L;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r0.m1634g();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 != 0) goto L_0x00fc;
    L_0x009d:
        r6.m1249y();	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x000c;
    L_0x00a2:
        r0 = move-exception;
        r1 = "APS";
        r2 = "up";
        com.p016a.ev.m1777a(r0, r1, r2);
        goto L_0x000c;
    L_0x00ac:
        r0 = r1;
        goto L_0x0013;
    L_0x00af:
        r0 = r6.m1186A();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 != 0) goto L_0x00b8;
    L_0x00b5:
        r0 = r1;
        goto L_0x0013;
    L_0x00b8:
        r0 = 2083520511; // 0x7c2fffff float:3.6553767E36 double:1.029395907E-314;
        goto L_0x0013;
    L_0x00bd:
        r2 = r6.f661N;	 Catch:{ Throwable -> 0x00cd }
        r3 = 0;
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x00cd }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x00cd }
    L_0x00c6:
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r1 = r1.length;	 Catch:{ Throwable -> 0x00a2 }
        r0[r2] = r1;	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x007d;
    L_0x00cd:
        r0 = move-exception;
        r2 = "APS";
        r3 = "up part";
        com.p016a.ev.m1777a(r0, r2, r3);	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.f661N;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 2;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x00c6;
    L_0x00e8:
        r1 = r6.f658K;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1 + 1;
        r6.f658K = r1;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.f659L;	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.f660M;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 1;
        r4 = 0;
        r0 = r6.m1205a(r3, r0, r4);	 Catch:{ Throwable -> 0x00a2 }
        r1.m1627a(r2, r0);	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x008c;
    L_0x00fc:
        r0 = r6.f658K;	 Catch:{ Throwable -> 0x00a2 }
        r1 = 3;
        if (r0 < r1) goto L_0x000c;
    L_0x0101:
        r6.m1249y();	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.bs.b(int):void");
    }

    private void m1219b(SharedPreferences sharedPreferences) {
        int i = 0;
        if (this.f685m != null) {
            SharedPreferences sharedPreferences2 = this.f685m.getSharedPreferences("pref", 0);
            if (sharedPreferences2 != null && sharedPreferences2.contains("coluphist")) {
                try {
                    String[] split = fy.m1904b(sharedPreferences2.getString("coluphist", null).getBytes(C1142e.f5201a)).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    while (i < 3) {
                        this.f661N[i] = Integer.parseInt(split[i]);
                        i++;
                    }
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "getColUpHist");
                    sharedPreferences2.edit().remove("coluphist").commit();
                }
            }
        }
    }

    private void m1220c(int i) {
        if (this.f657J == null) {
            this.f657J = new bt(this, i);
        }
        if (this.f656I == null) {
            this.f656I = new Timer("T-U", false);
            this.f656I.schedule(this.f657J, 2000, 2000);
        }
    }

    private void m1221c(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            String str = "activityoffdl";
            if (sharedPreferences.contains(str)) {
                try {
                    String[] split = dn.m1533d(sharedPreferences.getString(str, null)).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    for (int i = 0; i < 2; i++) {
                        cm.f752a[i] = Integer.parseInt(split[i]);
                    }
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "getOffDlHist");
                    sharedPreferences.edit().remove(str).commit();
                }
            }
        }
    }

    private void m1228i() {
        try {
            this.f687o = new cr(this.f685m, (WifiManager) dn.m1503a(this.f685m, "wifi"), this.f694v);
            this.f686n = (ConnectivityManager) dn.m1503a(this.f685m, "connectivity");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            intentFilter.addAction("android.location.GPS_FIX_CHANGE");
            intentFilter.addAction(NetworkStateReceiver.f9876a);
            this.f685m.registerReceiver(this.f692t, intentFilter);
            m1239o();
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "initBroadcastListener");
        }
    }

    private void m1231j() {
        this.f688p = new co(this.f685m, this.f694v);
        this.f688p.m1361h();
    }

    private void m1233k() {
        long b = dn.m1519b();
        if (m1235l()) {
            List list = this.f690r;
            if (b - this.f697y >= 10000) {
                synchronized (this.f680h) {
                    list.clear();
                }
            }
            m1239o();
            if (b - this.f697y >= 10000) {
                for (int i = 20; i > 0 && list.isEmpty(); i--) {
                    try {
                        Thread.sleep(3000 / ((long) 20));
                    } catch (Throwable th) {
                        ev.m1777a(th, "APS", "mayWaitForWifi");
                    }
                }
            }
            synchronized (this.f680h) {
            }
            if (list.isEmpty() && this.f687o != null) {
                Collection a = this.f687o.m1365a();
                if (a != null) {
                    list.addAll(a);
                }
            }
        }
    }

    private boolean m1235l() {
        boolean z = false;
        if (!TextUtils.isEmpty(this.f654G)) {
            return true;
        }
        if (m1240p()) {
            if (this.f650C == 0) {
                z = true;
            } else if (dn.m1519b() - this.f650C >= 3000 && dn.m1519b() - this.f651D >= 1500) {
                z = true;
            }
        }
        return z;
    }

    private boolean m1237m() {
        return this.f696x == 0 || dn.m1519b() - this.f696x > 20000;
    }

    private void m1238n() {
        this.f651D = 0;
        this.f689q.clear();
        this.f693u = null;
        synchronized (this.f680h) {
            this.f690r.clear();
            this.f691s.clear();
        }
    }

    private void m1239o() {
        boolean z = false;
        if (m1240p()) {
            boolean equals;
            if (dn.m1526c() < 18 && dn.m1526c() > 3 && dn.m1514a(this.f694v, "wifiactivescan")) {
                try {
                    equals = Constants.VIA_TO_TYPE_QQ_GROUP.equals(this.f694v.getString("wifiactivescan"));
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "updateWifi part1");
                }
                if (equals) {
                    try {
                        z = this.f687o.m1372e();
                        if (z) {
                            this.f650C = dn.m1519b();
                        }
                    } catch (Throwable th2) {
                        ev.m1777a(th2, "APS", "updateWifi part");
                    }
                }
                if (!z) {
                    try {
                        if (this.f687o.m1371d()) {
                            this.f650C = dn.m1519b();
                        }
                    } catch (Throwable th22) {
                        ev.m1777a(th22, "APS", "updateWifi");
                        return;
                    }
                }
            }
            equals = false;
            if (equals) {
                z = this.f687o.m1372e();
                if (z) {
                    this.f650C = dn.m1519b();
                }
            }
            if (!z) {
                if (this.f687o.m1371d()) {
                    this.f650C = dn.m1519b();
                }
            }
        }
    }

    private boolean m1240p() {
        return this.f687o != null ? this.f687o.m1373f() : false;
    }

    private boolean m1241q() {
        return this.f659L != null;
    }

    private boolean m1242r() {
        boolean z = false;
        try {
            if (m1241q()) {
                z = this.f659L.m1631d();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "collStarted");
        }
        return z;
    }

    private void m1243s() {
        if (m1241q()) {
            Object obj = 1;
            if (dn.m1514a(this.f694v, "coll")) {
                try {
                    if (this.f694v.getString("coll").equals(Constants.VIA_RESULT_SUCCESS)) {
                        obj = null;
                    }
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "start3rdCM");
                }
            }
            if (obj == null) {
                m1245u();
            } else if (!m1242r()) {
                try {
                    this.f659L.m1629b(ev.f1154m * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
                    m1250z();
                    m1244t();
                    this.f659L.m1625a();
                } catch (Throwable th2) {
                    ev.m1777a(th2, "APS", "start3rdCM");
                }
            }
        }
    }

    private void m1244t() {
        if (!m1241q()) {
            return;
        }
        if (!m1241q() || this.f659L.m1634g() <= 0) {
            try {
                if (!m1241q() || !this.f659L.m1633f()) {
                }
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "collFileSwitch");
            }
        }
    }

    private void m1245u() {
        if (m1242r()) {
            ev.f1154m = 20;
            try {
                this.f659L.m1630c();
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "stop3rdCM");
            }
        }
    }

    private void m1246v() {
        if (this.f685m != null && !TextUtils.isEmpty(this.f653F)) {
            SharedPreferences sharedPreferences = this.f685m.getSharedPreferences("pref", 0);
            Object obj = null;
            try {
                obj = fy.m1904b(this.f653F.getBytes(C1142e.f5201a));
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "setSmac");
            }
            if (!TextUtils.isEmpty(obj)) {
                sharedPreferences.edit().putString("smac", obj).commit();
            }
        }
    }

    private void m1247w() {
        ev.f1146e = C2915a.f14760f;
        ev.f1147f = C2915a.f14760f;
        ev.f1149h = C2915a.f14760f;
    }

    private void m1248x() {
        List list = this.f690r;
        Object obj = null;
        try {
            if (dn.m1514a(this.f694v, "wait1stwifi")) {
                obj = this.f694v.getString("wait1stwifi");
            }
            if (TextUtils.isEmpty(obj) || !obj.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                return;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "wait1StWifi part");
        }
        synchronized (this.f680h) {
            list.clear();
        }
        m1239o();
        for (int i = 20; i > 0 && list.isEmpty(); i--) {
            try {
                Thread.sleep(3000 / ((long) 20));
            } catch (Throwable th2) {
                ev.m1777a(th2, "APS", "wait1StWifi");
            }
        }
        synchronized (this.f680h) {
        }
        if (list.isEmpty() && this.f687o != null) {
            list.addAll(this.f687o.m1365a());
        }
    }

    private void m1249y() {
        if (this.f657J != null) {
            this.f657J.cancel();
            this.f657J = null;
        }
        if (this.f656I != null) {
            this.f656I.cancel();
            this.f656I.purge();
            this.f656I = null;
        }
    }

    private void m1250z() {
        if (m1241q()) {
            try {
                this.f659L.m1626a(768);
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "setCollSize");
            }
        }
    }

    public AmapLoc m1251a(AmapLoc amapLoc, String... strArr) {
        return (strArr == null || strArr.length == 0) ? cg.m1293a().m1295a(amapLoc) : strArr[0].equals("shake") ? cg.m1293a().m1295a(amapLoc) : strArr[0].equals("fusion") ? cg.m1293a().m1296b(amapLoc) : amapLoc;
    }

    public synchronized AmapLoc m1252a(boolean z) {
        AmapLoc G;
        boolean z2 = true;
        boolean z3 = false;
        synchronized (this) {
            if (f647c.length() > 0) {
                f647c.delete(0, f647c.length());
            }
            if (m1190E()) {
                boolean z4 = dn.m1514a(this.f694v, "reversegeo") ? this.f694v.getBoolean("reversegeo") : true;
                boolean z5 = dn.m1514a(this.f694v, "isOffset") ? this.f694v.getBoolean("isOffset") : true;
                if (!(z5 == this.ac && z4 == this.ab)) {
                    m1199N();
                }
                this.ac = z5;
                this.ab = z4;
                this.f652E++;
                this.f648A = dn.m1509a(this.f685m);
                if (z) {
                    G = m1192G();
                } else {
                    if (this.f652E == 2) {
                        m1244t();
                        m1189D();
                        if (this.f685m != null) {
                            SharedPreferences sharedPreferences = this.f685m.getSharedPreferences("pref", 0);
                            m1219b(sharedPreferences);
                            m1221c(sharedPreferences);
                            m1209a(sharedPreferences);
                        }
                        m1194I();
                    }
                    if (this.f652E == 1 && m1240p()) {
                        if (this.f690r.isEmpty()) {
                            this.f651D = dn.m1519b();
                            Collection a = this.f687o.m1365a();
                            synchronized (this.f680h) {
                                if (!(this.f690r == null || a == null)) {
                                    this.f690r.addAll(a);
                                }
                            }
                        }
                        m1248x();
                    }
                    if (m1215a(this.f696x) && dn.m1512a(this.f695w)) {
                        this.f695w.m5313a(2);
                        G = this.f695w;
                    } else {
                        this.f688p.m1359f();
                        if (!z) {
                            try {
                                m1233k();
                                this.f697y = dn.m1519b();
                            } catch (Throwable th) {
                                ev.m1777a(th, "APS", "getLocation");
                            }
                        }
                        m1262d();
                        m1263e();
                        String b = m1257b(false);
                        if (TextUtils.isEmpty(b)) {
                            if (!this.f678f) {
                                m1265g();
                            }
                            for (int i = 4; i > 0 && this.f677e != 0; i--) {
                                SystemClock.sleep(500);
                            }
                            if (this.f677e == 0) {
                                this.f695w = this.f676d.m1275d();
                                if (this.f695w != null) {
                                    G = this.f695w;
                                }
                            }
                            G = new AmapLoc();
                            G.m5321b(this.f682j);
                            G.m5322b(f647c.toString());
                        } else {
                            boolean z6;
                            String str;
                            String str2 = C2915a.f14760f;
                            StringBuilder c = m1261c(false);
                            cn b2 = !this.f648A ? this.f688p.m1355b() : null;
                            boolean z7 = !(b2 == null && this.f667T == null) && (this.f667T == null || !this.f667T.m1324a(b2));
                            boolean m = m1237m();
                            if (this.f695w != null) {
                                int size = this.f689q.size();
                                if (this.f695w.m5341j() <= 299.0f || size <= 5) {
                                    z2 = false;
                                }
                                z6 = z2;
                            } else {
                                z6 = false;
                            }
                            if (!(this.f695w == null || this.f666S == null || z6 || z7)) {
                                z3 = cv.m1394a().m1404b(this.f666S, c);
                                if (z3 || (this.f665R != 0 && dn.m1519b() - this.f665R < 3000)) {
                                    if (this.f688p.m1354a(this.f648A)) {
                                        this.f688p.m1361h();
                                    }
                                    if (dn.m1512a(this.f695w)) {
                                        this.f695w.m5333f("mem");
                                        this.f695w.m5313a(2);
                                        G = this.f695w;
                                    }
                                }
                            }
                            if (z3) {
                                this.f665R = 0;
                            } else {
                                this.f665R = dn.m1519b();
                            }
                            if (this.f663P == null || b.equals(this.f663P)) {
                                if (this.f663P == null) {
                                    this.f664Q = dn.m1502a();
                                    this.f663P = b;
                                    str = b;
                                } else {
                                    this.f664Q = dn.m1502a();
                                    str = b;
                                }
                            } else if (dn.m1502a() - this.f664Q < 3000) {
                                str = this.f663P;
                            } else {
                                this.f664Q = dn.m1502a();
                                this.f663P = b;
                                str = b;
                            }
                            G = null;
                            String str3 = str + "&" + this.ac + "&" + this.ab;
                            if (!(z6 || m)) {
                                G = cv.m1394a().m1399a(str3, c);
                            }
                            if ((!m && !dn.m1512a(G)) || z6) {
                                this.f695w = m1204a(m1264f(), false, false);
                                if (dn.m1512a(this.f695w)) {
                                    this.f695w.m5333f("new");
                                    this.f666S = c.toString();
                                    this.f667T = b2;
                                    this.f696x = dn.m1519b();
                                    m1193H();
                                }
                            } else if (m) {
                                this.f695w = m1204a(m1264f(), false, false);
                                if (dn.m1512a(this.f695w)) {
                                    this.f666S = c.toString();
                                    this.f667T = b2;
                                    this.f696x = dn.m1519b();
                                    m1193H();
                                }
                            } else {
                                this.f665R = 0;
                                G.m5313a(4);
                                this.f695w = G;
                                m1193H();
                            }
                            cv.m1394a().m1401a(str3, c, this.f695w, this.f685m, true);
                            cz.m1420a().m1423a(this.f685m, str, this.f695w);
                            if (!dn.m1512a(this.f695w)) {
                                G = m1202a(str, c.toString());
                                if (dn.m1512a(G)) {
                                    this.f666S = c.toString();
                                    AmapLoc amapLoc = this.f695w;
                                    this.f695w = G;
                                    this.f695w.m5313a(8);
                                    this.f695w.m5322b("\u79bb\u7ebf\u5b9a\u4f4d\uff0c\u5728\u7ebf\u5b9a\u4f4d\u5931\u8d25\u539f\u56e0:" + amapLoc.m5329d());
                                }
                            }
                            c.delete(0, c.length());
                            G = this.f695w;
                        }
                    }
                }
            } else {
                G = new AmapLoc();
                G.m5321b(1);
                G.m5322b(f647c.toString());
            }
        }
        return G;
    }

    public void m1253a() {
        if (dj.m1463a() && dn.m1519b() - dj.m1469c() < dj.m1466b() && this.f695w != null) {
            if (this.f695w.m5318b() == 2 || this.f695w.m5318b() == 4) {
                try {
                    m1257b(false);
                    m1261c(true);
                    m1204a(m1264f(), false, true);
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "fusionLocation");
                }
            }
        }
    }

    public synchronized void m1254a(Context context) {
        if (context != null) {
            if (TextUtils.isEmpty(ev.f1152k)) {
                ev.f1152k = dn.m1521b(context);
            }
            if (this.f685m == null) {
                this.f685m = context.getApplicationContext();
                this.f675b = dd.m1442a(this.f685m);
                try {
                    this.f659L = dw.m1591a(this.f685m);
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "setExtra");
                }
                this.f698z = dn.m1519b();
                m1228i();
                m1231j();
                ev.f1155n = true;
                this.f655H = new di();
                this.f688p.m1357d();
                cv.m1394a().m1400a(this.f685m);
                cz.m1420a().m1422a(this.f685m);
                this.f681i = true;
            }
        }
    }

    public void m1255a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("##")) {
            m1247w();
            return;
        }
        String[] split = str.split("##");
        if (split.length != 4) {
            m1247w();
            return;
        }
        ev.f1146e = split[0];
        ev.f1147f = split[1];
        ev.f1149h = split[2];
        ev.f1150i = split[3];
    }

    public void m1256a(JSONObject jSONObject) {
        this.f694v = jSONObject;
        if (dn.m1514a(jSONObject, "collwifiscan")) {
            try {
                Object string = jSONObject.getString("collwifiscan");
                if (TextUtils.isEmpty(string)) {
                    ev.f1154m = 20;
                } else {
                    ev.f1154m = Integer.parseInt(string) / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
                }
                if (m1242r()) {
                    this.f659L.m1629b(ev.f1154m * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
                }
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "setExtra");
            }
        }
        if (this.f688p != null) {
            this.f688p.m1351a(jSONObject);
        }
        if (this.f687o != null) {
            this.f687o.m1366a(jSONObject);
        }
    }

    public synchronized String m1257b(boolean z) {
        String str;
        Object obj = null;
        synchronized (this) {
            if (this.f648A) {
                this.f688p.m1360g();
            } else {
                this.f688p.m1363j();
            }
            str = C2915a.f14760f;
            String str2 = C2915a.f14760f;
            String str3 = "network";
            if (m1240p()) {
                this.f693u = this.f687o.m1369b();
            } else {
                m1238n();
            }
            str2 = C2915a.f14760f;
            int c = this.f688p.m1356c();
            ArrayList a = this.f688p.m1350a();
            List list = this.f689q;
            if ((a == null || a.isEmpty()) && (list == null || list.isEmpty())) {
                f647c.append("\u2297 lstCgi & \u2297 wifis");
                this.f682j = 12;
            } else {
                cn cnVar;
                StringBuilder stringBuilder;
                switch (c) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        if (!a.isEmpty()) {
                            cnVar = (cn) a.get(0);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(cnVar.f759a).append("#");
                            stringBuilder.append(cnVar.f760b).append("#");
                            stringBuilder.append(cnVar.f761c).append("#");
                            stringBuilder.append(cnVar.f762d).append("#");
                            stringBuilder.append(str3).append("#");
                            str = (!list.isEmpty() || m1216a(this.f693u)) ? "cgiwifi" : "cgi";
                            stringBuilder.append(str);
                            str = stringBuilder.toString();
                            break;
                        }
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        if (!a.isEmpty()) {
                            cnVar = (cn) a.get(0);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(cnVar.f759a).append("#");
                            stringBuilder.append(cnVar.f760b).append("#");
                            stringBuilder.append(cnVar.f765g).append("#");
                            stringBuilder.append(cnVar.f766h).append("#");
                            stringBuilder.append(cnVar.f767i).append("#");
                            stringBuilder.append(str3).append("#");
                            str = (!list.isEmpty() || m1216a(this.f693u)) ? "cgiwifi" : "cgi";
                            stringBuilder.append(str);
                            str = stringBuilder.toString();
                            break;
                        }
                        break;
                    case Type.ARRAY /*9*/:
                        Object obj2 = (!list.isEmpty() || m1216a(this.f693u)) ? 1 : null;
                        if (!z) {
                            if (!m1216a(this.f693u) || !list.isEmpty()) {
                                if (list.size() == 1) {
                                    this.f682j = 2;
                                    if (m1216a(this.f693u)) {
                                        if (this.f693u.getBSSID().equals(((ScanResult) list.get(0)).BSSID)) {
                                            f647c.append("same access wifi & around wifi 1");
                                        }
                                    } else {
                                        f647c.append("\u2297 access wifi & around wifi 1");
                                    }
                                    str = String.format(Locale.US, "#%s#", new Object[]{str3});
                                    if (obj != null) {
                                        if (str3.equals("network")) {
                                            str = C2915a.f14760f;
                                            this.f682j = 2;
                                            f647c.append("is network & no wifi");
                                            break;
                                        }
                                    }
                                    str = str + "wifi";
                                    break;
                                }
                            }
                            this.f682j = 2;
                            f647c.append("\u2297 around wifi(s) & has access wifi");
                            str = String.format(Locale.US, "#%s#", new Object[]{str3});
                            if (obj != null) {
                                str = str + "wifi";
                            } else if (str3.equals("network")) {
                                str = C2915a.f14760f;
                                this.f682j = 2;
                                f647c.append("is network & no wifi");
                            }
                        }
                        obj = obj2;
                        str = String.format(Locale.US, "#%s#", new Object[]{str3});
                        if (obj != null) {
                            str = str + "wifi";
                        } else if (str3.equals("network")) {
                            str = C2915a.f14760f;
                            this.f682j = 2;
                            f647c.append("is network & no wifi");
                        }
                        break;
                    default:
                        this.f682j = 11;
                        f647c.append("get cgi failure");
                        break;
                }
                if (!TextUtils.isEmpty(str)) {
                    if (!str.startsWith("#")) {
                        str = "#" + str;
                    }
                    str = dn.m1542j() + str;
                }
            }
        }
        return str;
    }

    public synchronized void m1258b() {
        this.f681i = false;
        ev.f1155n = false;
        m1245u();
        this.f659L = null;
        this.f660M = null;
        this.f666S = null;
        m1193H();
        if (this.f676d != null) {
            this.f676d.m1271a();
            this.f676d = null;
            this.f678f = false;
            this.f677e = -1;
        }
        m1249y();
        try {
            cx.m1410a().m1413a(this.f685m, 1);
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "destroy part");
        }
        cg.m1293a().m1297b();
        dn.m1541i();
        try {
            if (this.f685m != null) {
                this.f685m.unregisterReceiver(this.f692t);
            }
            this.f692t = null;
        } catch (Throwable th2) {
            this.f692t = null;
        }
        if (this.f688p != null) {
            this.f688p.m1362i();
        }
        cv.m1394a().m1405c();
        cz.m1420a().m1426c();
        cm.m1311a();
        m1195J();
        this.f696x = 0;
        this.f664Q = 0;
        m1238n();
        this.f695w = null;
        this.f685m = null;
        ae = -1;
    }

    public synchronized void m1259b(Context context) {
        try {
            if (ae == -1) {
                ae = 1;
                fo.m1847a(this.f685m, ev.m1772a("2.4.1"));
                dj.m1464a(context);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "APS", "initAuth");
        }
    }

    public String m1260c() {
        return "2.4.1";
    }

    public synchronized StringBuilder m1261c(boolean z) {
        StringBuilder stringBuilder;
        Object obj = null;
        synchronized (this) {
            co coVar = this.f688p;
            if (this.f648A) {
                coVar.m1360g();
            }
            stringBuilder = new StringBuilder(IMediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING);
            int c = coVar.m1356c();
            ArrayList a = coVar.m1350a();
            switch (c) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    for (c = 1; c < a.size(); c++) {
                        stringBuilder.append("#").append(((cn) a.get(c)).f760b);
                        stringBuilder.append("|").append(((cn) a.get(c)).f761c);
                        stringBuilder.append("|").append(((cn) a.get(c)).f762d);
                    }
                    break;
            }
            if (((!z && TextUtils.isEmpty(this.f653F)) || this.f653F.equals("00:00:00:00:00:00")) && this.f693u != null) {
                this.f653F = this.f693u.getMacAddress();
                m1246v();
                if (TextUtils.isEmpty(this.f653F)) {
                    this.f653F = "00:00:00:00:00:00";
                }
            }
            if (m1240p()) {
                Object bssid;
                String str = C2915a.f14760f;
                if (m1216a(this.f693u)) {
                    bssid = this.f693u.getBSSID();
                } else {
                    String str2 = str;
                }
                List list = this.f689q;
                int size = list.size();
                for (c = 0; c < size; c++) {
                    str = "nb";
                    if (bssid.equals(((ScanResult) list.get(c)).BSSID)) {
                        str = "access";
                        obj = 1;
                    }
                    stringBuilder.append(String.format(Locale.US, "#%s,%s", new Object[]{((ScanResult) list.get(c)).BSSID, str}));
                }
                if (obj == null && !TextUtils.isEmpty(bssid)) {
                    stringBuilder.append("#").append(bssid);
                    stringBuilder.append(",access");
                }
            } else {
                m1238n();
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(0);
            }
        }
        return stringBuilder;
    }

    public synchronized void m1262d() {
        List list = this.f689q;
        Collection collection = this.f690r;
        list.clear();
        synchronized (this.f680h) {
            if (collection != null) {
                if (collection.size() > 0) {
                    list.addAll(collection);
                }
            }
        }
    }

    public synchronized void m1263e() {
        if (!(this.f689q == null || this.f689q.isEmpty())) {
            boolean z;
            Object hashtable;
            List list;
            int size;
            int i;
            ScanResult scanResult;
            int length;
            TreeMap treeMap;
            if (dn.m1519b() - this.f651D > Util.MILLSECONDS_OF_HOUR) {
                m1238n();
            }
            boolean h = dn.m1540h();
            if (dn.m1514a(this.f694v, "nbssid")) {
                try {
                    if (this.f694v.getString("nbssid").equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                        h = true;
                    } else if (this.f694v.getString("nbssid").equals(Constants.VIA_RESULT_SUCCESS)) {
                        h = false;
                    }
                    z = h;
                } catch (Throwable th) {
                    ev.m1777a(th, "APS", "setWifiOrder part");
                }
                hashtable = new Hashtable();
                list = this.f689q;
                size = list.size();
                for (i = 0; i < size; i++) {
                    scanResult = (ScanResult) list.get(i);
                    if (dn.m1510a(scanResult) && (size <= 20 || m1214a(scanResult.level))) {
                        if (!TextUtils.isEmpty(scanResult.SSID)) {
                            scanResult.SSID = "unkwn";
                        } else if (z) {
                            scanResult.SSID = String.valueOf(i);
                        } else {
                            scanResult.SSID = scanResult.SSID.replace("*", ".");
                            try {
                                length = scanResult.SSID.getBytes(C1142e.f5201a).length;
                            } catch (Throwable th2) {
                                ev.m1777a(th2, "APS", "setWifiOrder");
                                length = 32;
                            }
                            if (length >= 32) {
                                scanResult.SSID = String.valueOf(i);
                            }
                        }
                        hashtable.put(Integer.valueOf((scanResult.level * 30) + i), scanResult);
                    }
                }
                treeMap = new TreeMap(Collections.reverseOrder());
                treeMap.putAll(hashtable);
                list.clear();
                for (Object obj : treeMap.keySet()) {
                    list.add(treeMap.get(obj));
                }
                hashtable.clear();
                treeMap.clear();
            }
            z = h;
            hashtable = new Hashtable();
            list = this.f689q;
            size = list.size();
            for (i = 0; i < size; i++) {
                scanResult = (ScanResult) list.get(i);
                if (!TextUtils.isEmpty(scanResult.SSID)) {
                    scanResult.SSID = "unkwn";
                } else if (z) {
                    scanResult.SSID = String.valueOf(i);
                } else {
                    scanResult.SSID = scanResult.SSID.replace("*", ".");
                    length = scanResult.SSID.getBytes(C1142e.f5201a).length;
                    if (length >= 32) {
                        scanResult.SSID = String.valueOf(i);
                    }
                }
                hashtable.put(Integer.valueOf((scanResult.level * 30) + i), scanResult);
            }
            treeMap = new TreeMap(Collections.reverseOrder());
            treeMap.putAll(hashtable);
            list.clear();
            while (r1.hasNext()) {
                list.add(treeMap.get(obj));
            }
            hashtable.clear();
            treeMap.clear();
        }
    }

    public synchronized String m1264f() {
        String str = null;
        synchronized (this) {
            if (this.f688p.m1354a(this.f648A)) {
                this.f688p.m1361h();
            }
            try {
                StringBuilder a = m1207a(null);
                if (a != null) {
                    str = a.toString();
                } else {
                    f647c.append("get parames is null");
                }
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "getApsReq");
                f647c.append("get parames error:" + th.getMessage());
            }
        }
        return str;
    }

    public synchronized void m1265g() {
        if (this.f652E >= 1 && !this.f678f) {
            if (this.f676d == null) {
                this.f676d = new bx(this.f685m.getApplicationContext());
                this.f676d.m1272a(this.f684l);
            }
            try {
                if (this.f676d != null) {
                    this.f676d.m1273b();
                }
                this.f678f = true;
            } catch (Throwable th) {
                ev.m1777a(th, "APS", "bindService");
                this.f678f = true;
            }
        }
    }

    public void m1266h() {
        if (this.f683k && !m1242r()) {
            m1243s();
        }
    }
}
