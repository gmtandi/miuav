package com.tencent.map.p131b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.entity.User;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.fimi.soul.utils.bq;
import com.tencent.map.p129a.p130a.C2224b;
import com.tencent.map.p129a.p130a.C2225c;
import com.tencent.map.p129a.p130a.C2226d;
import com.tencent.map.p131b.C2231b.C2229a;
import com.tencent.map.p131b.C2239d.C2237b;
import com.tencent.map.p131b.C2239d.C2238c;
import com.tencent.map.p131b.C2243e.C2241b;
import com.tencent.map.p131b.C2243e.C2242c;
import com.tencent.map.p131b.C2254g.C2249c;
import com.tencent.map.p131b.C2254g.C2253b;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.map.b.f */
public final class C2250f implements C2229a, C2238c, C2242c, C2249c {
    private static boolean f11646t;
    private static C2250f f11647u;
    private C2226d f11648A;
    private int f11649B;
    private int f11650C;
    private int f11651D;
    private String f11652E;
    private String f11653F;
    private String f11654G;
    private String f11655H;
    private String f11656I;
    private String f11657J;
    private boolean f11658K;
    private boolean f11659L;
    private long f11660M;
    private Handler f11661N;
    private Runnable f11662O;
    private final BroadcastReceiver f11663P;
    private long f11664a;
    private Context f11665b;
    private C2243e f11666c;
    private C2239d f11667d;
    private C2254g f11668e;
    private int f11669f;
    private int f11670g;
    private C2234c f11671h;
    private C2231b f11672i;
    private C2224b f11673j;
    private int f11674k;
    private int f11675l;
    private int f11676m;
    private byte[] f11677n;
    private byte[] f11678o;
    private boolean f11679p;
    private C2248c f11680q;
    private C2247b f11681r;
    private C2246a f11682s;
    private long f11683v;
    private C2241b f11684w;
    private C2237b f11685x;
    private C2253b f11686y;
    private C2226d f11687z;

    /* renamed from: com.tencent.map.b.f.1 */
    final class C22441 implements Runnable {
        private /* synthetic */ C2250f f11635a;

        C22441(C2250f c2250f) {
            this.f11635a = c2250f;
        }

        public final void run() {
            if (System.currentTimeMillis() - this.f11635a.f11660M >= 8000) {
                if (this.f11635a.f11668e.m13479b() && this.f11635a.f11668e.m13480c()) {
                    this.f11635a.f11668e.m13477a(0);
                } else {
                    this.f11635a.m13438d();
                }
            }
        }
    }

    /* renamed from: com.tencent.map.b.f.2 */
    final class C22452 extends BroadcastReceiver {
        private /* synthetic */ C2250f f11636a;

        C22452(C2250f c2250f) {
            this.f11636a = c2250f;
        }

        public final void onReceive(Context context, Intent intent) {
            if (!intent.getBooleanExtra("noConnectivity", false) && this.f11636a.f11680q != null) {
                this.f11636a.f11680q.sendEmptyMessage(Opcodes.ACC_NATIVE);
            }
        }
    }

    /* renamed from: com.tencent.map.b.f.a */
    final class C2246a extends Thread {
        private C2241b f11637a;
        private C2237b f11638b;
        private C2253b f11639c;
        private /* synthetic */ C2250f f11640d;

        C2246a(C2250f c2250f, C2241b c2241b, C2237b c2237b, C2253b c2253b) {
            this.f11640d = c2250f;
            this.f11637a = null;
            this.f11638b = null;
            this.f11639c = null;
            if (c2241b != null) {
                this.f11637a = (C2241b) c2241b.clone();
            }
            if (c2237b != null) {
                this.f11638b = (C2237b) c2237b.clone();
            }
            if (c2253b != null) {
                this.f11639c = (C2253b) c2253b.clone();
            }
        }

        public final void run() {
            if (!C2250f.f11646t) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) this.f11640d.f11665b.getSystemService("phone");
                    this.f11640d.f11652E = telephonyManager.getDeviceId();
                    this.f11640d.f11653F = telephonyManager.getSubscriberId();
                    this.f11640d.f11654G = telephonyManager.getLine1Number();
                    Pattern compile = Pattern.compile("[0-9a-zA-Z+-]*");
                    this.f11640d.f11652E = this.f11640d.f11652E == null ? C2915a.f14760f : this.f11640d.f11652E;
                    if (compile.matcher(this.f11640d.f11652E).matches()) {
                        this.f11640d.f11652E = this.f11640d.f11652E == null ? C2915a.f14760f : this.f11640d.f11652E;
                    } else {
                        this.f11640d.f11652E = C2915a.f14760f;
                    }
                    this.f11640d.f11653F = this.f11640d.f11653F == null ? C2915a.f14760f : this.f11640d.f11653F;
                    if (compile.matcher(this.f11640d.f11653F).matches()) {
                        this.f11640d.f11653F = this.f11640d.f11653F == null ? C2915a.f14760f : this.f11640d.f11653F;
                    } else {
                        this.f11640d.f11653F = C2915a.f14760f;
                    }
                    this.f11640d.f11654G = this.f11640d.f11654G == null ? C2915a.f14760f : this.f11640d.f11654G;
                    if (compile.matcher(this.f11640d.f11654G).matches()) {
                        this.f11640d.f11654G = this.f11640d.f11654G == null ? C2915a.f14760f : this.f11640d.f11654G;
                    } else {
                        this.f11640d.f11654G = C2915a.f14760f;
                    }
                } catch (Exception e) {
                }
                C2250f.f11646t = true;
                this.f11640d.f11652E = this.f11640d.f11652E == null ? C2915a.f14760f : this.f11640d.f11652E;
                this.f11640d.f11653F = this.f11640d.f11653F == null ? C2915a.f14760f : this.f11640d.f11653F;
                this.f11640d.f11654G = this.f11640d.f11654G == null ? C2915a.f14760f : this.f11640d.f11654G;
                this.f11640d.f11656I = C2257j.m13493a(this.f11640d.f11652E == null ? "0123456789ABCDEF" : this.f11640d.f11652E);
            }
            String a = this.f11640d.f11670g == 4 ? C2256i.m13487a(this.f11639c) : "[]";
            String a2 = C2256i.m13485a(this.f11638b, this.f11640d.f11667d.m13391b());
            String a3 = C2256i.m13488a(this.f11640d.f11652E, this.f11640d.f11653F, this.f11640d.f11654G, this.f11640d.f11655H, this.f11640d.f11658K);
            String a4 = (this.f11637a == null || !this.f11637a.m13392a()) ? "{}" : C2256i.m13486a(this.f11637a);
            this.f11640d.f11680q.sendMessage(this.f11640d.f11680q.obtainMessage(16, (("{\"version\":\"1.1.8\",\"address\":" + this.f11640d.f11675l) + ",\"source\":203,\"access_token\":\"" + this.f11640d.f11656I + "\",\"app_name\":" + "\"" + this.f11640d.f11657J + "\",\"bearing\":1") + ",\"attribute\":" + a3 + ",\"location\":" + a4 + ",\"cells\":" + a2 + ",\"wifis\":" + a + "}"));
        }
    }

    /* renamed from: com.tencent.map.b.f.b */
    final class C2247b extends Thread {
        private String f11641a;
        private String f11642b;
        private String f11643c;
        private /* synthetic */ C2250f f11644d;

        public C2247b(C2250f c2250f, String str) {
            this.f11644d = c2250f;
            this.f11641a = null;
            this.f11642b = null;
            this.f11643c = null;
            this.f11641a = str;
            this.f11642b = (c2250f.f11651D == 0 ? "http://lstest.map.soso.com/loc?c=1" : "http://lbs.map.qq.com/loc?c=1") + "&mars=" + c2250f.f11676m;
        }

        private String m13412a(byte[] bArr, String str) {
            this.f11644d.f11660M = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer();
            try {
                stringBuffer.append(new String(bArr, str));
                return stringBuffer.toString();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            Message message = new Message();
            message.what = 8;
            try {
                byte[] a = C2257j.m13494a(this.f11641a.getBytes());
                this.f11644d.f11679p = true;
                C2262n a2 = C2231b.m13367a(this.f11642b, "SOSO MAP LBS SDK", a);
                this.f11644d.f11679p = false;
                this.f11643c = m13412a(C2257j.m13495b(a2.f11740a), a2.f11741b);
                if (this.f11643c != null) {
                    message.arg1 = 0;
                    message.obj = this.f11643c;
                } else {
                    message.arg1 = 1;
                }
            } catch (Exception e) {
                int i = 0;
                while (true) {
                    i++;
                    if (i > 3) {
                        break;
                    }
                    try {
                        C2247b.sleep(1000);
                        byte[] a3 = C2257j.m13494a(this.f11641a.getBytes());
                        this.f11644d.f11679p = true;
                        C2262n a4 = C2231b.m13367a(this.f11642b, "SOSO MAP LBS SDK", a3);
                        this.f11644d.f11679p = false;
                        this.f11643c = m13412a(C2257j.m13495b(a4.f11740a), a4.f11741b);
                        if (this.f11643c != null) {
                            message.arg1 = 0;
                            message.obj = this.f11643c;
                        } else {
                            message.arg1 = 1;
                        }
                    } catch (Exception e2) {
                    }
                }
                this.f11644d.f11679p = false;
                message.arg1 = 1;
            }
            C2250f.m13447j(this.f11644d);
            this.f11644d.f11680q.sendMessage(message);
        }
    }

    /* renamed from: com.tencent.map.b.f.c */
    final class C2248c extends Handler {
        private /* synthetic */ C2250f f11645a;

        public C2248c(C2250f c2250f) {
            this.f11645a = c2250f;
            super(Looper.getMainLooper());
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C2250f.m13423a(this.f11645a, (C2241b) message.obj);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    C2250f.m13422a(this.f11645a, (C2237b) message.obj);
                case Type.BYTE /*3*/:
                    C2250f.m13424a(this.f11645a, (C2253b) message.obj);
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    C2250f.m13420a(this.f11645a, message.arg1);
                case Type.INT /*5*/:
                    C2250f.m13430b(this.f11645a, message.arg1);
                case Type.FLOAT /*6*/:
                    C2250f.m13421a(this.f11645a, (Location) message.obj);
                case Type.DOUBLE /*8*/:
                    if (message.arg1 == 0) {
                        this.f11645a.m13426a((String) message.obj);
                    } else if (this.f11645a.f11684w == null || !this.f11645a.f11684w.m13392a()) {
                        this.f11645a.m13441e();
                    }
                case Segment.TOKENS_PER_SEGMENT /*16*/:
                    if (message.obj != null) {
                        C2250f.m13425a(this.f11645a, (String) message.obj);
                        this.f11645a.f11682s = null;
                    }
                case Opcodes.ACC_NATIVE /*256*/:
                    if (this.f11645a.f11649B == 1) {
                        this.f11645a.m13438d();
                    }
                default:
            }
        }
    }

    static {
        f11646t = false;
        f11647u = null;
    }

    private C2250f() {
        this.f11664a = 5000;
        this.f11665b = null;
        this.f11666c = null;
        this.f11667d = null;
        this.f11668e = null;
        this.f11669f = SmileConstants.MAX_SHARED_STRING_VALUES;
        this.f11670g = 4;
        this.f11671h = null;
        this.f11672i = null;
        this.f11673j = null;
        this.f11677n = new byte[0];
        this.f11678o = new byte[0];
        this.f11679p = false;
        this.f11680q = null;
        this.f11681r = null;
        this.f11682s = null;
        this.f11683v = -1;
        this.f11684w = null;
        this.f11685x = null;
        this.f11686y = null;
        this.f11687z = null;
        this.f11648A = null;
        this.f11649B = 0;
        this.f11650C = 0;
        this.f11651D = 1;
        this.f11652E = C2915a.f14760f;
        this.f11653F = C2915a.f14760f;
        this.f11654G = C2915a.f14760f;
        this.f11655H = C2915a.f14760f;
        this.f11656I = C2915a.f14760f;
        this.f11657J = C2915a.f14760f;
        this.f11658K = false;
        this.f11659L = false;
        this.f11660M = 0;
        this.f11661N = null;
        this.f11662O = new C22441(this);
        this.f11663P = new C22452(this);
        this.f11666c = new C2243e();
        this.f11667d = new C2239d();
        this.f11668e = new C2254g();
    }

    public static synchronized C2250f m13418a() {
        C2250f c2250f;
        synchronized (C2250f.class) {
            if (f11647u == null) {
                f11647u = new C2250f();
            }
            c2250f = f11647u;
        }
        return c2250f;
    }

    private static ArrayList<C2225c> m13419a(JSONArray jSONArray) {
        int length = jSONArray.length();
        ArrayList<C2225c> arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(new C2225c(jSONObject.getString(User.FN_NAME), jSONObject.getString("addr"), jSONObject.getString("catalog"), jSONObject.getDouble("dist"), Double.parseDouble(jSONObject.getString("latitude")), Double.parseDouble(jSONObject.getString("longitude"))));
        }
        return arrayList;
    }

    static /* synthetic */ void m13420a(C2250f c2250f, int i) {
        if (i == 0) {
            c2250f.f11684w = null;
        }
        c2250f.f11669f = i == 0 ? 1 : 2;
        if (c2250f.f11673j != null) {
            c2250f.f11673j.m13353a(c2250f.f11669f);
        }
    }

    static /* synthetic */ void m13421a(C2250f c2250f, Location location) {
        if (location == null || location.getLatitude() > 359.0d || location.getLongitude() > 359.0d) {
            if (c2250f.f11684w == null || !c2250f.f11684w.m13392a()) {
                c2250f.m13441e();
            } else {
                c2250f.m13432b(true);
            }
        }
        c2250f.f11687z = new C2226d();
        c2250f.f11687z.f11560z = 0;
        c2250f.f11687z.f11559y = 0;
        c2250f.f11687z.f11536b = C2256i.m13482a(location.getLatitude(), 6);
        c2250f.f11687z.f11537c = C2256i.m13482a(location.getLongitude(), 6);
        if (c2250f.f11684w != null && c2250f.f11684w.m13392a()) {
            c2250f.f11687z.f11539e = C2256i.m13482a((double) c2250f.f11684w.m13393b().getAccuracy(), 1);
            c2250f.f11687z.f11538d = C2256i.m13482a(c2250f.f11684w.m13393b().getAltitude(), 1);
            c2250f.f11687z.f11540f = C2256i.m13482a((double) c2250f.f11684w.m13393b().getSpeed(), 1);
            c2250f.f11687z.f11541g = C2256i.m13482a((double) c2250f.f11684w.m13393b().getBearing(), 1);
            c2250f.f11687z.f11535a = 0;
        }
        c2250f.f11687z.f11558x = true;
        if (!(c2250f.f11675l == 0 || c2250f.f11648A == null || c2250f.f11649B != 0)) {
            if ((c2250f.f11675l == 3 || c2250f.f11675l == 4) && c2250f.f11675l == c2250f.f11648A.f11560z) {
                c2250f.f11687z.f11543i = c2250f.f11648A.f11543i;
                c2250f.f11687z.f11544j = c2250f.f11648A.f11544j;
                c2250f.f11687z.f11545k = c2250f.f11648A.f11545k;
                c2250f.f11687z.f11546l = c2250f.f11648A.f11546l;
                c2250f.f11687z.f11547m = c2250f.f11648A.f11547m;
                c2250f.f11687z.f11548n = c2250f.f11648A.f11548n;
                c2250f.f11687z.f11549o = c2250f.f11648A.f11549o;
                c2250f.f11687z.f11550p = c2250f.f11648A.f11550p;
                c2250f.f11687z.f11560z = 3;
            }
            if (c2250f.f11675l == 4 && c2250f.f11675l == c2250f.f11648A.f11560z && c2250f.f11648A.f11557w != null) {
                c2250f.f11687z.f11557w = new ArrayList();
                Iterator it = c2250f.f11648A.f11557w.iterator();
                while (it.hasNext()) {
                    c2250f.f11687z.f11557w.add(new C2225c((C2225c) it.next()));
                }
                c2250f.f11687z.f11560z = 4;
            }
            if (c2250f.f11675l == 7 && c2250f.f11675l == c2250f.f11648A.f11560z) {
                c2250f.f11687z.f11560z = 7;
                c2250f.f11687z.f11542h = c2250f.f11648A.f11542h;
                c2250f.f11687z.f11543i = c2250f.f11648A.f11543i;
                if (c2250f.f11648A.f11542h == 0) {
                    c2250f.f11687z.f11544j = c2250f.f11648A.f11544j;
                    c2250f.f11687z.f11545k = c2250f.f11648A.f11545k;
                    c2250f.f11687z.f11546l = c2250f.f11648A.f11546l;
                    c2250f.f11687z.f11547m = c2250f.f11648A.f11547m;
                    c2250f.f11687z.f11548n = c2250f.f11648A.f11548n;
                    c2250f.f11687z.f11549o = c2250f.f11648A.f11549o;
                    c2250f.f11687z.f11550p = c2250f.f11648A.f11550p;
                } else {
                    c2250f.f11687z.f11551q = c2250f.f11648A.f11551q;
                    c2250f.f11687z.f11552r = c2250f.f11648A.f11552r;
                    c2250f.f11687z.f11553s = c2250f.f11648A.f11553s;
                    c2250f.f11687z.f11554t = c2250f.f11648A.f11554t;
                    c2250f.f11687z.f11555u = c2250f.f11648A.f11555u;
                    c2250f.f11687z.f11556v = c2250f.f11648A.f11556v;
                }
            }
        }
        if (c2250f.f11649B != 0 || c2250f.f11648A != null) {
            if (c2250f.f11649B != 0) {
                c2250f.f11687z.f11559y = c2250f.f11649B;
            }
            if (System.currentTimeMillis() - c2250f.f11683v >= c2250f.f11664a && c2250f.f11673j != null && c2250f.f11674k == 1) {
                c2250f.f11673j.m13354a(c2250f.f11687z);
                c2250f.f11683v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void m13422a(C2250f c2250f, C2237b c2237b) {
        c2250f.f11685x = c2237b;
        if (c2250f.f11668e != null && c2250f.f11668e.m13479b() && c2250f.f11668e.m13480c()) {
            c2250f.f11668e.m13477a(0);
            return;
        }
        if (c2250f.f11650C > 0 && !C2256i.m13489a(c2237b.f11600a, c2237b.f11601b, c2237b.f11602c, c2237b.f11603d, c2237b.f11604e)) {
            c2250f.f11650C--;
        }
        c2250f.m13438d();
    }

    static /* synthetic */ void m13423a(C2250f c2250f, C2241b c2241b) {
        if (c2241b != null) {
            c2250f.f11684w = c2241b;
            if (c2250f.f11674k != 1 || c2250f.f11684w == null || !c2250f.f11684w.m13392a()) {
                return;
            }
            if (c2250f.f11676m == 0) {
                c2250f.m13432b(false);
            } else if (c2250f.f11676m == 1 && c2250f.f11672i != null) {
                C2231b c2231b = c2250f.f11672i;
                double latitude = c2250f.f11684w.m13393b().getLatitude();
                double longitude = c2250f.f11684w.m13393b().getLongitude();
                Context context = c2250f.f11665b;
                c2231b.m13371a(latitude, longitude, (C2229a) c2250f);
            }
        }
    }

    static /* synthetic */ void m13424a(C2250f c2250f, C2253b c2253b) {
        if (c2253b != null) {
            c2250f.f11686y = c2253b;
            c2250f.m13438d();
        }
    }

    static /* synthetic */ void m13425a(C2250f c2250f, String str) {
        if (C2256i.m13492c(str)) {
            if (c2250f.f11674k != 0 || c2250f.f11673j == null) {
                String b = c2250f.f11671h == null ? null : (c2250f.f11685x == null || c2250f.f11686y == null) ? null : c2250f.f11671h.m13377b(c2250f.f11685x.f11601b, c2250f.f11685x.f11602c, c2250f.f11685x.f11603d, c2250f.f11685x.f11604e, c2250f.f11686y.m13469a());
                if (b != null) {
                    c2250f.m13426a(b);
                    return;
                }
                if (!(c2250f.f11671h == null || c2250f.f11685x == null || c2250f.f11686y == null)) {
                    c2250f.f11671h.m13375a(c2250f.f11685x.f11601b, c2250f.f11685x.f11602c, c2250f.f11685x.f11603d, c2250f.f11685x.f11604e, c2250f.f11686y.m13469a());
                }
                if (!c2250f.f11679p) {
                    if (c2250f.f11681r != null) {
                        c2250f.f11681r.interrupt();
                    }
                    c2250f.f11681r = null;
                    c2250f.f11681r = new C2247b(c2250f, str);
                    c2250f.f11681r.start();
                    return;
                }
                return;
            }
            byte[] bytes;
            try {
                bytes = str.getBytes();
            } catch (Exception e) {
                bytes = null;
            }
            c2250f.f11673j.m13355a(bytes, 0);
        } else if (c2250f.f11650C > 0) {
            c2250f.f11650C--;
        } else if (c2250f.f11674k == 0 && c2250f.f11673j != null) {
            c2250f.f11673j.m13355a(null, -1);
        } else if (c2250f.f11674k == 1 && c2250f.f11673j != null) {
            c2250f.f11687z = new C2226d();
            c2250f.f11649B = 3;
            c2250f.f11687z.f11559y = 3;
            c2250f.f11687z.f11560z = -1;
            c2250f.f11673j.m13354a(c2250f.f11687z);
        }
    }

    private void m13426a(String str) {
        int i = 0;
        try {
            double d;
            this.f11687z = new C2226d();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("location");
            this.f11687z.f11535a = 1;
            this.f11687z.f11536b = C2256i.m13482a(jSONObject2.getDouble("latitude"), 6);
            this.f11687z.f11537c = C2256i.m13482a(jSONObject2.getDouble("longitude"), 6);
            this.f11687z.f11538d = C2256i.m13482a(jSONObject2.getDouble("altitude"), 1);
            this.f11687z.f11539e = C2256i.m13482a(jSONObject2.getDouble("accuracy"), 1);
            this.f11687z.f11558x = this.f11676m == 1;
            String string = jSONObject.getString("bearing");
            int i2 = -100;
            if (string != null && string.split(MiPushClient.ACCEPT_TIME_SEPARATOR).length > 1) {
                i = Integer.parseInt(string.split(MiPushClient.ACCEPT_TIME_SEPARATOR)[1]);
            }
            if (this.f11685x != null) {
                i2 = this.f11685x.f11605f;
            }
            C2226d c2226d = this.f11687z;
            double d2 = this.f11687z.f11539e;
            if (i >= 6) {
                d = 40.0d;
            } else if (i == 5) {
                d = 60.0d;
            } else if (i == 4) {
                d = 70.0d;
            } else if (i == 3) {
                d = 90.0d;
            } else if (i == 2) {
                d = 110.0d;
            } else {
                i2 = (i2 < -72 || i != 0) ? d2 <= 100.0d ? ((int) (((d2 - WeightedLatLng.DEFAULT_INTENSITY) / 10.0d) + WeightedLatLng.DEFAULT_INTENSITY)) * 10 : (d2 <= 100.0d || d2 > 800.0d) ? ((int) ((0.8d * d2) / 10.0d)) * 10 : ((int) ((0.85d * d2) / 10.0d)) * 10 : ((int) ((0.45d * d2) / 10.0d)) * 10;
                d = (double) i2;
            }
            c2226d.f11539e = d;
            this.f11687z.f11560z = 0;
            if ((this.f11675l == 3 || this.f11675l == 4) && this.f11676m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details").getJSONObject("subnation");
                this.f11687z.m13359a(jSONObject2.getString(User.FN_NAME));
                this.f11687z.f11547m = jSONObject2.getString("town");
                this.f11687z.f11548n = jSONObject2.getString("village");
                this.f11687z.f11549o = jSONObject2.getString("street");
                this.f11687z.f11550p = jSONObject2.getString("street_no");
                this.f11687z.f11560z = 3;
                this.f11687z.f11542h = 0;
            }
            if (this.f11675l == 4 && this.f11676m == 1) {
                this.f11687z.f11557w = C2250f.m13419a(jSONObject.getJSONObject("details").getJSONArray("poilist"));
                this.f11687z.f11560z = 4;
            }
            if (this.f11675l == 7 && this.f11676m == 1) {
                jSONObject2 = jSONObject.getJSONObject("details");
                i = jSONObject2.getInt("stat");
                jSONObject2 = jSONObject2.getJSONObject("subnation");
                if (i == 0) {
                    this.f11687z.m13359a(jSONObject2.getString(User.FN_NAME));
                    this.f11687z.f11547m = jSONObject2.getString("town");
                    this.f11687z.f11548n = jSONObject2.getString("village");
                    this.f11687z.f11549o = jSONObject2.getString("street");
                    this.f11687z.f11550p = jSONObject2.getString("street_no");
                } else if (i == 1) {
                    this.f11687z.f11543i = jSONObject2.getString("nation");
                    this.f11687z.f11551q = jSONObject2.getString("admin_level_1");
                    this.f11687z.f11552r = jSONObject2.getString("admin_level_2");
                    this.f11687z.f11553s = jSONObject2.getString("admin_level_3");
                    this.f11687z.f11554t = jSONObject2.getString("locality");
                    this.f11687z.f11555u = jSONObject2.getString("sublocality");
                    this.f11687z.f11556v = jSONObject2.getString(bq.f10107a);
                }
                this.f11687z.f11542h = i;
                this.f11687z.f11560z = 7;
            }
            this.f11687z.f11559y = 0;
            this.f11648A = new C2226d(this.f11687z);
            this.f11649B = 0;
            if (this.f11671h != null) {
                this.f11671h.m13376a(str);
            }
        } catch (Exception e) {
            this.f11687z = new C2226d();
            this.f11687z.f11560z = -1;
            this.f11687z.f11559y = 2;
            this.f11649B = 2;
        }
        if (this.f11673j != null && this.f11674k == 1) {
            if (this.f11684w == null || !this.f11684w.m13392a()) {
                this.f11673j.m13354a(this.f11687z);
                this.f11683v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void m13430b(C2250f c2250f, int i) {
        int i2 = 3;
        if (i == 3) {
            i2 = 4;
        }
        c2250f.f11670g = i2;
        if (c2250f.f11673j != null) {
            c2250f.f11673j.m13353a(c2250f.f11670g);
        }
    }

    private void m13432b(boolean z) {
        if (this.f11684w != null && this.f11684w.m13392a()) {
            Location b = this.f11684w.m13393b();
            this.f11687z = new C2226d();
            this.f11687z.f11536b = C2256i.m13482a(b.getLatitude(), 6);
            this.f11687z.f11537c = C2256i.m13482a(b.getLongitude(), 6);
            this.f11687z.f11538d = C2256i.m13482a(b.getAltitude(), 1);
            this.f11687z.f11539e = C2256i.m13482a((double) b.getAccuracy(), 1);
            this.f11687z.f11540f = C2256i.m13482a((double) b.getSpeed(), 1);
            this.f11687z.f11541g = C2256i.m13482a((double) b.getBearing(), 1);
            this.f11687z.f11535a = 0;
            this.f11687z.f11558x = false;
            if (z) {
                this.f11687z.f11559y = 1;
            } else {
                this.f11687z.f11559y = 0;
            }
            this.f11687z.f11560z = 0;
            this.f11648A = new C2226d(this.f11687z);
            this.f11649B = 0;
            if (System.currentTimeMillis() - this.f11683v >= this.f11664a && this.f11673j != null && this.f11674k == 1) {
                this.f11673j.m13354a(this.f11687z);
                this.f11683v = System.currentTimeMillis();
            }
        }
    }

    private void m13438d() {
        if (this.f11682s == null) {
            this.f11682s = new C2246a(this, this.f11684w, this.f11685x, this.f11686y);
            this.f11682s.start();
        }
    }

    private void m13441e() {
        this.f11687z = new C2226d();
        this.f11649B = 1;
        this.f11687z.f11559y = 1;
        this.f11687z.f11560z = -1;
        this.f11687z.f11535a = 1;
        if (this.f11673j != null && this.f11674k == 1) {
            this.f11673j.m13354a(this.f11687z);
        }
    }

    static /* synthetic */ void m13447j(C2250f c2250f) {
    }

    public final void m13459a(double d, double d2) {
        synchronized (this.f11678o) {
            Message obtainMessage = this.f11680q.obtainMessage(6);
            Location location = new Location("Deflect");
            location.setLatitude(d);
            location.setLongitude(d2);
            obtainMessage.obj = location;
            this.f11680q.sendMessage(obtainMessage);
        }
    }

    public final void m13460a(int i) {
        synchronized (this.f11678o) {
            this.f11680q.sendMessage(this.f11680q.obtainMessage(4, i, 0));
        }
    }

    public final void m13461a(C2237b c2237b) {
        synchronized (this.f11678o) {
            this.f11680q.sendMessage(this.f11680q.obtainMessage(2, c2237b));
        }
    }

    public final void m13462a(C2241b c2241b) {
        synchronized (this.f11678o) {
            this.f11680q.sendMessage(this.f11680q.obtainMessage(1, c2241b));
        }
    }

    public final void m13463a(C2253b c2253b) {
        synchronized (this.f11678o) {
            this.f11680q.sendMessage(this.f11680q.obtainMessage(3, c2253b));
        }
    }

    public final boolean m13464a(Context context, C2224b c2224b) {
        synchronized (this.f11677n) {
            if (context == null || c2224b == null) {
                return false;
            } else if (C2256i.m13490a(this.f11657J)) {
                this.f11680q = new C2248c(this);
                this.f11661N = new Handler(Looper.getMainLooper());
                this.f11665b = context;
                this.f11673j = c2224b;
                C2260l.m13506a().m13510a(this.f11665b.getApplicationContext());
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                        this.f11658K = connectivityManager.getActiveNetworkInfo().isRoaming();
                    }
                    this.f11665b.registerReceiver(this.f11663P, new IntentFilter(NetworkStateReceiver.f9876a));
                } catch (Exception e) {
                }
                this.f11674k = this.f11673j.m13352a();
                this.f11675l = this.f11673j.m13356b();
                this.f11676m = this.f11673j.m13357c();
                this.f11683v = -1;
                if (this.f11675l == 7) {
                    this.f11675l = 0;
                }
                this.f11659L = false;
                this.f11651D = 1;
                boolean a = this.f11666c.m13411a((C2242c) this, this.f11665b);
                boolean a2 = this.f11667d.m13390a(this.f11665b, (C2238c) this);
                boolean a3 = this.f11668e.m13478a(this.f11665b, this, 1);
                this.f11671h = C2234c.m13372a();
                this.f11672i = C2231b.m13366a();
                this.f11684w = null;
                this.f11685x = null;
                this.f11686y = null;
                this.f11687z = null;
                this.f11648A = null;
                this.f11649B = 0;
                if (this.f11671h != null) {
                    this.f11671h.m13378b();
                }
                this.f11650C = 1;
                if (a && this.f11676m == 0) {
                    return true;
                } else if (a2 || a3) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public final boolean m13465a(String str, String str2) {
        boolean z;
        synchronized (this.f11677n) {
            if (C2228a.m13362a().m13363a(str, str2)) {
                this.f11657J = str;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void m13466b() {
        synchronized (this.f11677n) {
            try {
                if (this.f11673j != null) {
                    this.f11673j = null;
                    this.f11661N.removeCallbacks(this.f11662O);
                    this.f11665b.unregisterReceiver(this.f11663P);
                    this.f11666c.m13410a();
                    this.f11667d.m13389a();
                    this.f11668e.m13476a();
                }
            } catch (Exception e) {
            }
        }
    }

    public final void m13467b(int i) {
        synchronized (this.f11678o) {
            this.f11680q.sendMessage(this.f11680q.obtainMessage(5, i, 0));
        }
    }
}
