package it.p074a.p075a;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p084e.C1173l;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mipush.sdk.MiPushClient;
import it.p074a.p075a.p140a.C2778d;
import it.p074a.p075a.p141b.C2786a;
import it.p074a.p075a.p142c.C2789a;
import it.p074a.p075a.p142c.C2790b;
import it.p074a.p075a.p142c.C2791c;
import it.p074a.p075a.p142c.C2792d;
import it.p074a.p075a.p142c.C2793e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.SSLSocketFactory;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: it.a.a.b */
public class C2787b {
    public static final int f14186a = 0;
    public static final int f14187b = 1;
    public static final int f14188c = 2;
    public static final int f14189d = 0;
    public static final int f14190e = 1;
    public static final int f14191f = 2;
    public static final int f14192g = 0;
    public static final int f14193h = 1;
    public static final int f14194i = 2;
    private static final int f14195j = 65536;
    private static final DateFormat f14196k;
    private static final Pattern f14197l;
    private static final Pattern f14198m;
    private boolean f14199A;
    private int f14200B;
    private int f14201C;
    private long f14202D;
    private C2798e f14203E;
    private long f14204F;
    private boolean f14205G;
    private String f14206H;
    private boolean f14207I;
    private boolean f14208J;
    private boolean f14209K;
    private boolean f14210L;
    private boolean f14211M;
    private boolean f14212N;
    private boolean f14213O;
    private InputStream f14214P;
    private OutputStream f14215Q;
    private boolean f14216R;
    private boolean f14217S;
    private Object f14218T;
    private Object f14219U;
    private C2800g f14220V;
    private C2777i f14221n;
    private SSLSocketFactory f14222o;
    private ArrayList f14223p;
    private ArrayList f14224q;
    private C2784u f14225r;
    private C2788s f14226s;
    private String f14227t;
    private int f14228u;
    private int f14229v;
    private String f14230w;
    private String f14231x;
    private boolean f14232y;
    private boolean f14233z;

    static {
        f14196k = new SimpleDateFormat(C1173l.f5333e);
        f14197l = Pattern.compile("\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}");
        f14198m = Pattern.compile("\"/.*\"");
    }

    public C2787b() {
        this.f14221n = new C2778d();
        this.f14222o = (SSLSocketFactory) SSLSocketFactory.getDefault();
        this.f14223p = new ArrayList();
        this.f14224q = new ArrayList();
        this.f14225r = C2786a.m15863a();
        this.f14226s = null;
        this.f14227t = null;
        this.f14228u = f14192g;
        this.f14229v = f14192g;
        this.f14232y = false;
        this.f14233z = false;
        this.f14199A = true;
        this.f14200B = f14192g;
        this.f14201C = f14192g;
        this.f14202D = 0;
        this.f14205G = false;
        this.f14206H = null;
        this.f14207I = false;
        this.f14208J = false;
        this.f14209K = false;
        this.f14210L = false;
        this.f14211M = false;
        this.f14212N = false;
        this.f14213O = false;
        this.f14214P = null;
        this.f14215Q = null;
        this.f14216R = false;
        this.f14217S = false;
        this.f14218T = new Object();
        this.f14219U = new Object();
        this.f14220V = null;
        m15894a(new C2793e());
        m15894a(new C2789a());
        m15894a(new C2790b());
        m15894a(new C2792d());
        m15894a(new C2791c());
    }

    private void m15864E() {
        synchronized (this.f14218T) {
            this.f14208J = false;
            this.f14205G = false;
            this.f14209K = false;
            this.f14210L = false;
            this.f14212N = false;
            this.f14220V.m15978a("FEAT");
            C2808t c = this.f14220V.m15983c();
            if (c.m15997a() == C2799f.f14284v) {
                String[] c2 = c.m15999c();
                for (int i = f14193h; i < c2.length - 1; i += f14193h) {
                    String toUpperCase = c2[i].trim().toUpperCase();
                    if ("REST STREAM".equalsIgnoreCase(toUpperCase)) {
                        this.f14205G = true;
                    } else if ("UTF8".equalsIgnoreCase(toUpperCase)) {
                        this.f14208J = true;
                        this.f14220V.m15981b(C1142e.f5201a);
                    } else if ("MLSD".equalsIgnoreCase(toUpperCase)) {
                        this.f14209K = true;
                    } else if ("MODE Z".equalsIgnoreCase(toUpperCase) || toUpperCase.startsWith("MODE Z ")) {
                        this.f14210L = true;
                    }
                }
            }
            if (this.f14208J) {
                this.f14220V.m15978a("OPTS UTF8 ON");
                this.f14220V.m15983c();
            }
            if (this.f14229v == f14193h || this.f14229v == f14194i) {
                this.f14220V.m15978a("PBSZ 0");
                this.f14220V.m15983c();
                this.f14220V.m15978a("PROT P");
                if (this.f14220V.m15983c().m15998b()) {
                    this.f14212N = true;
                }
            }
        }
    }

    private C2794j m15865F() {
        C2808t c;
        if (this.f14210L && this.f14207I) {
            if (!this.f14211M) {
                this.f14220V.m15978a("MODE Z");
                c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    this.f14211M = true;
                }
            }
        } else if (this.f14211M) {
            this.f14220V.m15978a("MODE S");
            c = this.f14220V.m15983c();
            m15874O();
            if (c.m15998b()) {
                this.f14211M = false;
            }
        }
        return this.f14199A ? m15867H() : m15866G();
    }

    private C2794j m15866G() {
        C2794j c2796c = new C2796c(this);
        int c = c2796c.m15971c();
        int i = c >>> 8;
        c &= Util.MASK_8BIT;
        int[] J = m15869J();
        this.f14220V.m15978a(new StringBuffer().append("PORT ").append(J[f14192g]).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(J[f14193h]).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(J[f14194i]).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(J[3]).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(i).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c).toString());
        C2808t c2 = this.f14220V.m15983c();
        m15874O();
        if (c2.m15998b()) {
            return c2796c;
        }
        c2796c.m15970b();
        try {
            c2796c.m15969a().close();
        } catch (Throwable th) {
        }
        throw new C2803n(c2);
    }

    private C2794j m15867H() {
        this.f14220V.m15978a("PASV");
        C2808t c = this.f14220V.m15983c();
        m15874O();
        if (c.m15998b()) {
            int i;
            String substring;
            String[] c2 = c.m15999c();
            for (i = f14192g; i < c2.length; i += f14193h) {
                Matcher matcher = f14197l.matcher(c2[i]);
                if (matcher.find()) {
                    substring = c2[i].substring(matcher.start(), matcher.end());
                    break;
                }
            }
            substring = null;
            if (substring == null) {
                throw new C2805p();
            }
            StringTokenizer stringTokenizer = new StringTokenizer(substring, MiPushClient.ACCEPT_TIME_SEPARATOR);
            i = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt4 = Integer.parseInt(stringTokenizer.nextToken());
            return new C2797d(this, new StringBuffer().append(i).append(".").append(parseInt).append(".").append(parseInt2).append(".").append(parseInt3).toString(), Integer.parseInt(stringTokenizer.nextToken()) | (parseInt4 << 8));
        }
        throw new C2803n(c);
    }

    private String m15868I() {
        return this.f14206H != null ? this.f14206H : this.f14208J ? C1142e.f5201a : System.getProperty("file.encoding");
    }

    private int[] m15869J() {
        int[] K = m15870K();
        return K == null ? m15871L() : K;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] m15870K() {
        /*
        r10 = this;
        r9 = 4;
        r1 = 0;
        r0 = 0;
        r2 = "ftp4j.activeDataTransfer.hostAddress";
        r5 = java.lang.System.getProperty(r2);
        if (r5 == 0) goto L_0x0067;
    L_0x000b:
        r6 = new java.util.StringTokenizer;
        r2 = ".";
        r6.<init>(r5, r2);
        r2 = r6.countTokens();
        if (r2 != r9) goto L_0x0035;
    L_0x0018:
        r3 = 1;
        r2 = new int[r9];
        r4 = r1;
    L_0x001c:
        if (r4 >= r9) goto L_0x0070;
    L_0x001e:
        r7 = r6.nextToken();
        r7 = java.lang.Integer.parseInt(r7);	 Catch:{ NumberFormatException -> 0x0068 }
        r2[r4] = r7;	 Catch:{ NumberFormatException -> 0x0068 }
    L_0x0028:
        r7 = r2[r4];
        if (r7 < 0) goto L_0x0032;
    L_0x002c:
        r7 = r2[r4];
        r8 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r7 <= r8) goto L_0x006d;
    L_0x0032:
        if (r1 == 0) goto L_0x0035;
    L_0x0034:
        r0 = r2;
    L_0x0035:
        if (r1 != 0) goto L_0x0067;
    L_0x0037:
        r1 = java.lang.System.err;
        r2 = new java.lang.StringBuffer;
        r2.<init>();
        r3 = "WARNING: invalid value \"";
        r2 = r2.append(r3);
        r2 = r2.append(r5);
        r3 = "\" for the ";
        r2 = r2.append(r3);
        r3 = "ftp4j.activeDataTransfer.hostAddress";
        r2 = r2.append(r3);
        r3 = " system property. The value should ";
        r2 = r2.append(r3);
        r3 = "be in the x.x.x.x form.";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.println(r2);
    L_0x0067:
        return r0;
    L_0x0068:
        r7 = move-exception;
        r7 = -1;
        r2[r4] = r7;
        goto L_0x0028;
    L_0x006d:
        r4 = r4 + 1;
        goto L_0x001c;
    L_0x0070:
        r1 = r3;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.b.K():int[]");
    }

    private int[] m15871L() {
        byte[] address = InetAddress.getLocalHost().getAddress();
        int i = address[f14192g] & Util.MASK_8BIT;
        int i2 = address[f14193h] & Util.MASK_8BIT;
        int i3 = address[f14194i] & Util.MASK_8BIT;
        int i4 = address[3] & Util.MASK_8BIT;
        return new int[]{i, i2, i3, i4};
    }

    private void m15872M() {
        if (this.f14202D > 0) {
            this.f14203E = new C2798e(this, null);
            this.f14203E.start();
        }
    }

    private void m15873N() {
        if (this.f14203E != null) {
            this.f14203E.interrupt();
            this.f14203E = null;
        }
    }

    private void m15874O() {
        if (this.f14203E != null) {
            this.f14204F = System.currentTimeMillis() + this.f14202D;
        }
    }

    static long m15875a(C2787b c2787b, long j) {
        c2787b.f14204F = j;
        return j;
    }

    static Socket m15876a(C2787b c2787b, Socket socket, String str, int i) {
        return c2787b.m15877a(socket, str, i);
    }

    private Socket m15877a(Socket socket, String str, int i) {
        return this.f14222o.createSocket(socket, str, i, true);
    }

    static boolean m15878a(C2787b c2787b) {
        return c2787b.f14212N;
    }

    static C2777i m15879b(C2787b c2787b) {
        return c2787b.f14221n;
    }

    static String m15880c(C2787b c2787b) {
        return c2787b.f14227t;
    }

    static Object m15881d(C2787b c2787b) {
        return c2787b.f14218T;
    }

    static long m15882e(C2787b c2787b) {
        return c2787b.f14204F;
    }

    static long m15883f(C2787b c2787b) {
        return c2787b.f14202D;
    }

    private int m15884m(String str) {
        int lastIndexOf = str.lastIndexOf(46) + f14193h;
        int length = str.length();
        if (lastIndexOf <= 0 || lastIndexOf >= length - 1) {
            return f14194i;
        }
        return this.f14225r.m15858a(str.substring(lastIndexOf, length).toLowerCase()) ? f14193h : f14194i;
    }

    public String[] m15885A() {
        String[] c;
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("HELP");
                C2808t c2 = this.f14220V.m15983c();
                m15874O();
                if (c2.m15998b()) {
                    c = c2.m15999c();
                } else {
                    throw new C2803n(c2);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return c;
    }

    public String[] m15886B() {
        String[] c;
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("STAT");
                C2808t c2 = this.f14220V.m15983c();
                m15874O();
                if (c2.m15998b()) {
                    c = c2.m15999c();
                } else {
                    throw new C2803n(c2);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return c;
    }

    public C2804o[] m15887C() {
        return m15944l(null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] m15888D() {
        /*
        r11 = this;
        r10 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        r9 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r8 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r1 = 0;
        r3 = 0;
        r4 = r11.f14218T;
        monitor-enter(r4);
        r0 = r11.f14232y;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x001a;
    L_0x000f:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0017 }
        r1 = "Client not connected";
        r0.<init>(r1);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r11.f14233z;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x0026;
    L_0x001e:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0017 }
        r1 = "Client not authenticated";
        r0.<init>(r1);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x0026:
        r0 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r2 = "TYPE A";
        r0.m15978a(r2);	 Catch:{ all -> 0x0017 }
        r0 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r0 = r0.m15983c();	 Catch:{ all -> 0x0017 }
        r11.m15874O();	 Catch:{ all -> 0x0017 }
        r2 = r0.m15998b();	 Catch:{ all -> 0x0017 }
        if (r2 != 0) goto L_0x0042;
    L_0x003c:
        r1 = new it.a.a.n;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0042:
        r5 = new java.util.ArrayList;	 Catch:{ all -> 0x0017 }
        r5.<init>();	 Catch:{ all -> 0x0017 }
        r0 = r11.m15865F();	 Catch:{ all -> 0x0017 }
        r2 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r6 = "NLST";
        r2.m15978a(r6);	 Catch:{ all -> 0x0017 }
        r6 = r0.m15967a();	 Catch:{ all -> 0x00d8 }
        r0.m15968b();	 Catch:{ all -> 0x00dd }
        r2 = r11.f14219U;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r0 = 1;
        r11.f14213O = r0;	 Catch:{ all -> 0x00e0 }
        r0 = 0;
        r11.f14216R = r0;	 Catch:{ all -> 0x00e0 }
        r0 = 0;
        r11.f14217S = r0;	 Catch:{ all -> 0x00e0 }
        monitor-exit(r2);	 Catch:{ all -> 0x00e0 }
        r0 = r6.getInputStream();	 Catch:{ IOException -> 0x018a }
        r11.f14214P = r0;	 Catch:{ IOException -> 0x018a }
        r0 = r11.f14211M;	 Catch:{ IOException -> 0x018a }
        if (r0 == 0) goto L_0x0079;
    L_0x0070:
        r0 = new java.util.zip.InflaterInputStream;	 Catch:{ IOException -> 0x018a }
        r2 = r11.f14214P;	 Catch:{ IOException -> 0x018a }
        r0.<init>(r2);	 Catch:{ IOException -> 0x018a }
        r11.f14214P = r0;	 Catch:{ IOException -> 0x018a }
    L_0x0079:
        r2 = new it.a.a.v;	 Catch:{ IOException -> 0x018a }
        r0 = r11.f14214P;	 Catch:{ IOException -> 0x018a }
        r7 = r11.m15868I();	 Catch:{ IOException -> 0x018a }
        r2.<init>(r0, r7);	 Catch:{ IOException -> 0x018a }
    L_0x0084:
        r0 = r2.m16000a();	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        if (r0 == 0) goto L_0x0137;
    L_0x008a:
        r1 = r0.length();	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        if (r1 <= 0) goto L_0x0084;
    L_0x0090:
        r5.add(r0);	 Catch:{ IOException -> 0x0094, all -> 0x0186 }
        goto L_0x0084;
    L_0x0094:
        r0 = move-exception;
        r1 = r2;
    L_0x0096:
        r2 = r11.f14219U;	 Catch:{ all -> 0x00a6 }
        monitor-enter(r2);	 Catch:{ all -> 0x00a6 }
        r5 = r11.f14216R;	 Catch:{ all -> 0x00a3 }
        if (r5 == 0) goto L_0x00e3;
    L_0x009d:
        r0 = new it.a.a.a;	 Catch:{ all -> 0x00a3 }
        r0.<init>();	 Catch:{ all -> 0x00a3 }
        throw r0;	 Catch:{ all -> 0x00a3 }
    L_0x00a3:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00a3 }
        throw r0;	 Catch:{ all -> 0x00a6 }
    L_0x00a6:
        r0 = move-exception;
    L_0x00a7:
        if (r1 == 0) goto L_0x00ac;
    L_0x00a9:
        r1.close();	 Catch:{ Throwable -> 0x012d }
    L_0x00ac:
        r6.close();	 Catch:{ Throwable -> 0x0130 }
    L_0x00af:
        r1 = 0;
        r11.f14214P = r1;	 Catch:{ all -> 0x00dd }
        r2 = r11.f14219U;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r1 = r11.f14216R;	 Catch:{ all -> 0x0133 }
        r3 = 0;
        r11.f14213O = r3;	 Catch:{ all -> 0x0184 }
        r3 = 0;
        r11.f14216R = r3;	 Catch:{ all -> 0x0184 }
        monitor-exit(r2);	 Catch:{ all -> 0x0184 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x00bf:
        r0 = move-exception;
    L_0x00c0:
        r2 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r2 = r2.m15983c();	 Catch:{ all -> 0x0017 }
        r3 = r2.m15997a();	 Catch:{ all -> 0x0017 }
        if (r3 == r9) goto L_0x0119;
    L_0x00cc:
        r3 = r2.m15997a();	 Catch:{ all -> 0x0017 }
        if (r3 == r8) goto L_0x0119;
    L_0x00d2:
        r0 = new it.a.a.n;	 Catch:{ all -> 0x0017 }
        r0.<init>(r2);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x00d8:
        r1 = move-exception;
        r0.m15968b();	 Catch:{ all -> 0x00dd }
        throw r1;	 Catch:{ all -> 0x00dd }
    L_0x00dd:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00c0;
    L_0x00e0:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00e0 }
        throw r0;	 Catch:{ all -> 0x00dd }
    L_0x00e3:
        r5 = new it.a.a.k;	 Catch:{ all -> 0x00a3 }
        r7 = "I/O error in data transfer";
        r5.<init>(r7, r0);	 Catch:{ all -> 0x00a3 }
        throw r5;	 Catch:{ all -> 0x00a3 }
    L_0x00eb:
        r1 = r11.f14217S;	 Catch:{ all -> 0x0017 }
        if (r1 == 0) goto L_0x00f7;
    L_0x00ef:
        r1 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r1.m15983c();	 Catch:{ all -> 0x0017 }
        r1 = 0;
        r11.f14217S = r1;	 Catch:{ all -> 0x0017 }
    L_0x00f7:
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x00f8:
        r0 = r11.f14217S;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0104;
    L_0x00fc:
        r0 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r0.m15983c();	 Catch:{ all -> 0x0017 }
        r0 = 0;
        r11.f14217S = r0;	 Catch:{ all -> 0x0017 }
    L_0x0104:
        r1 = r5.size();	 Catch:{ all -> 0x0017 }
        r2 = new java.lang.String[r1];	 Catch:{ all -> 0x0017 }
    L_0x010a:
        if (r3 >= r1) goto L_0x0117;
    L_0x010c:
        r0 = r5.get(r3);	 Catch:{ all -> 0x0017 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0017 }
        r2[r3] = r0;	 Catch:{ all -> 0x0017 }
        r3 = r3 + 1;
        goto L_0x010a;
    L_0x0117:
        monitor-exit(r4);	 Catch:{ all -> 0x0017 }
        return r2;
    L_0x0119:
        r2 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r2 = r2.m15983c();	 Catch:{ all -> 0x0017 }
        if (r1 != 0) goto L_0x00eb;
    L_0x0121:
        r1 = r2.m15997a();	 Catch:{ all -> 0x0017 }
        if (r1 == r10) goto L_0x00eb;
    L_0x0127:
        r0 = new it.a.a.n;	 Catch:{ all -> 0x0017 }
        r0.<init>(r2);	 Catch:{ all -> 0x0017 }
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x012d:
        r1 = move-exception;
        goto L_0x00ac;
    L_0x0130:
        r1 = move-exception;
        goto L_0x00af;
    L_0x0133:
        r0 = move-exception;
        r1 = r3;
    L_0x0135:
        monitor-exit(r2);	 Catch:{ all -> 0x0184 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x0137:
        if (r2 == 0) goto L_0x013c;
    L_0x0139:
        r2.close();	 Catch:{ Throwable -> 0x0166 }
    L_0x013c:
        r6.close();	 Catch:{ Throwable -> 0x0168 }
    L_0x013f:
        r0 = 0;
        r11.f14214P = r0;	 Catch:{ all -> 0x00dd }
        r2 = r11.f14219U;	 Catch:{ all -> 0x00dd }
        monitor-enter(r2);	 Catch:{ all -> 0x00dd }
        r1 = r11.f14216R;	 Catch:{ all -> 0x016a }
        r0 = 0;
        r11.f14213O = r0;	 Catch:{ all -> 0x0182 }
        r0 = 0;
        r11.f14216R = r0;	 Catch:{ all -> 0x0182 }
        monitor-exit(r2);	 Catch:{ all -> 0x0182 }
        r0 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r0 = r0.m15983c();	 Catch:{ all -> 0x0017 }
        r2 = r0.m15997a();	 Catch:{ all -> 0x0017 }
        if (r2 == r9) goto L_0x016e;
    L_0x015a:
        r2 = r0.m15997a();	 Catch:{ all -> 0x0017 }
        if (r2 == r8) goto L_0x016e;
    L_0x0160:
        r1 = new it.a.a.n;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0166:
        r0 = move-exception;
        goto L_0x013c;
    L_0x0168:
        r0 = move-exception;
        goto L_0x013f;
    L_0x016a:
        r0 = move-exception;
        r1 = r3;
    L_0x016c:
        monitor-exit(r2);	 Catch:{ all -> 0x0182 }
        throw r0;	 Catch:{ all -> 0x00bf }
    L_0x016e:
        r0 = r11.f14220V;	 Catch:{ all -> 0x0017 }
        r0 = r0.m15983c();	 Catch:{ all -> 0x0017 }
        if (r1 != 0) goto L_0x00f8;
    L_0x0176:
        r1 = r0.m15997a();	 Catch:{ all -> 0x0017 }
        if (r1 == r10) goto L_0x00f8;
    L_0x017c:
        r1 = new it.a.a.n;	 Catch:{ all -> 0x0017 }
        r1.<init>(r0);	 Catch:{ all -> 0x0017 }
        throw r1;	 Catch:{ all -> 0x0017 }
    L_0x0182:
        r0 = move-exception;
        goto L_0x016c;
    L_0x0184:
        r0 = move-exception;
        goto L_0x0135;
    L_0x0186:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00a7;
    L_0x018a:
        r0 = move-exception;
        goto L_0x0096;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.b.D():java.lang.String[]");
    }

    public C2777i m15889a() {
        C2777i c2777i;
        synchronized (this.f14218T) {
            c2777i = this.f14221n;
        }
        return c2777i;
    }

    public void m15890a(int i) {
        if (i == 0 || i == f14193h || i == f14194i) {
            synchronized (this.f14218T) {
                if (this.f14232y) {
                    throw new IllegalStateException("The security level of the connection can't be changed while the client is connected");
                }
                this.f14229v = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid security");
    }

    public void m15891a(long j) {
        synchronized (this.f14218T) {
            if (this.f14232y && this.f14233z) {
                m15873N();
            }
            long j2 = this.f14202D;
            this.f14202D = j;
            if (!(j2 == 0 || j == 0 || this.f14204F <= 0)) {
                this.f14204F -= j2 - j;
            }
            if (this.f14232y && this.f14233z) {
                m15872M();
            }
        }
    }

    public void m15892a(C2801h c2801h) {
        synchronized (this.f14218T) {
            this.f14223p.add(c2801h);
            if (this.f14220V != null) {
                this.f14220V.m15977a(c2801h);
            }
        }
    }

    public void m15893a(C2777i c2777i) {
        synchronized (this.f14218T) {
            this.f14221n = c2777i;
        }
    }

    public void m15894a(C2788s c2788s) {
        synchronized (this.f14218T) {
            this.f14224q.add(c2788s);
        }
    }

    public void m15895a(C2784u c2784u) {
        synchronized (this.f14218T) {
            this.f14225r = c2784u;
        }
    }

    public void m15896a(File file) {
        m15898a(file, 0, null);
    }

    public void m15897a(File file, long j) {
        m15898a(file, j, null);
    }

    public void m15898a(File file, long j, C1096l c1096l) {
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    m15905a(file.getName(), fileInputStream, j, j, c1096l);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    }
                    return;
                } catch (IllegalStateException e) {
                    throw e;
                } catch (IOException e2) {
                    throw e2;
                } catch (C2805p e3) {
                    throw e3;
                } catch (C2803n e4) {
                    throw e4;
                } catch (C2802k e5) {
                    throw e5;
                } catch (C2783a e6) {
                    throw e6;
                } catch (Throwable th2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                }
            } catch (Throwable e7) {
                throw new C2802k(e7);
            }
        }
        throw new FileNotFoundException(file.getAbsolutePath());
    }

    public void m15899a(File file, C1096l c1096l) {
        m15898a(file, 0, c1096l);
    }

    public void m15900a(String str) {
        synchronized (this.f14218T) {
            this.f14206H = str;
            if (this.f14232y) {
                try {
                    this.f14220V.m15981b(m15868I());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void m15901a(String str, File file) {
        m15903a(str, file, 0, null);
    }

    public void m15902a(String str, File file, long j) {
        m15903a(str, file, j, null);
    }

    public void m15903a(String str, File file, long j, C1096l c1096l) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(file, j > 0);
            try {
                m15907a(str, fileOutputStream, j, c1096l);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th) {
                    }
                }
            } catch (IllegalStateException e) {
                throw e;
            } catch (IOException e2) {
                throw e2;
            } catch (C2805p e3) {
                throw e3;
            } catch (C2803n e4) {
                throw e4;
            } catch (C2802k e5) {
                throw e5;
            } catch (C2783a e6) {
                throw e6;
            } catch (Throwable th2) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th3) {
                    }
                }
            }
        } catch (Throwable e7) {
            throw new C2802k(e7);
        }
    }

    public void m15904a(String str, File file, C1096l c1096l) {
        m15903a(str, file, 0, c1096l);
    }

    public void m15905a(String str, InputStream inputStream, long j, long j2, C1096l c1096l) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                int i = this.f14200B;
                if (i == 0) {
                    i = m15884m(str);
                }
                if (i == f14193h) {
                    this.f14220V.m15978a("TYPE A");
                } else if (i == f14194i) {
                    this.f14220V.m15978a("TYPE I");
                }
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    C2794j F = m15865F();
                    if (this.f14205G || j > 0) {
                        try {
                            this.f14220V.m15978a(new StringBuffer().append("REST ").append(j).toString());
                            c = this.f14220V.m15983c();
                            m15874O();
                            if (c.m15997a() != C2799f.f14281s && (!(c.m15997a() == C2799f.f14264b || c.m15997a() == C2799f.f14265c) || j > 0)) {
                                throw new C2803n(c);
                            }
                        } catch (Throwable th) {
                            F.m15968b();
                        }
                    }
                    boolean z = false;
                    this.f14220V.m15978a(new StringBuffer().append("STOR ").append(str).toString());
                    try {
                        Socket a = F.m15967a();
                        F.m15968b();
                        synchronized (this.f14219U) {
                            this.f14213O = true;
                            this.f14216R = false;
                            this.f14217S = false;
                        }
                        try {
                            inputStream.skip(j2);
                            this.f14215Q = a.getOutputStream();
                            if (this.f14211M) {
                                this.f14215Q = new DeflaterOutputStream(this.f14215Q);
                            }
                            if (c1096l != null) {
                                c1096l.m7678d();
                            }
                            if (i != f14193h) {
                                if (i == f14194i) {
                                    byte[] bArr = new byte[f14195j];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        this.f14215Q.write(bArr, f14192g, read);
                                        this.f14215Q.flush();
                                        if (c1096l != null) {
                                            c1096l.m7675a(read);
                                        }
                                    }
                                }
                            } else {
                                Reader inputStreamReader = new InputStreamReader(inputStream);
                                Writer outputStreamWriter = new OutputStreamWriter(this.f14215Q, m15868I());
                                char[] cArr = new char[f14195j];
                                while (true) {
                                    int read2 = inputStreamReader.read(cArr);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, f14192g, read2);
                                    outputStreamWriter.flush();
                                    if (c1096l != null) {
                                        c1096l.m7675a(read2);
                                    }
                                }
                            }
                            if (this.f14215Q != null) {
                                try {
                                    this.f14215Q.close();
                                } catch (Throwable th2) {
                                }
                            }
                            try {
                                a.close();
                            } catch (Throwable th3) {
                            }
                            this.f14215Q = null;
                            synchronized (this.f14219U) {
                                z = this.f14216R;
                                this.f14213O = false;
                                this.f14216R = false;
                            }
                            C2808t c2 = this.f14220V.m15983c();
                            m15874O();
                            if (c2.m15997a() == Opcodes.FCMPG || c2.m15997a() == Opcodes.LUSHR) {
                                c2 = this.f14220V.m15983c();
                                if (z || c2.m15997a() == C2799f.f14254D) {
                                    if (this.f14217S) {
                                        this.f14220V.m15983c();
                                        this.f14217S = false;
                                    }
                                    if (c1096l != null) {
                                        c1096l.m7676b();
                                    }
                                } else {
                                    throw new C2803n(c2);
                                }
                            }
                            throw new C2803n(c2);
                        } catch (Throwable e) {
                            synchronized (this.f14219U) {
                            }
                            if (this.f14216R) {
                                if (c1096l != null) {
                                    c1096l.m7674a();
                                }
                                throw new C2783a();
                            }
                            if (c1096l != null) {
                                c1096l.m7677c();
                            }
                            throw new C2802k("I/O error in data transfer", e);
                        } catch (Throwable th4) {
                            if (this.f14215Q != null) {
                                this.f14215Q.close();
                            }
                            a.close();
                            this.f14215Q = null;
                            synchronized (this.f14219U) {
                            }
                            z = this.f14216R;
                            this.f14213O = false;
                            this.f14216R = false;
                        }
                    } catch (Throwable th5) {
                    }
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15906a(String str, InputStream inputStream, long j, C1096l c1096l) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                int i = this.f14200B;
                if (i == 0) {
                    i = m15884m(str);
                }
                if (i == f14193h) {
                    this.f14220V.m15978a("TYPE A");
                } else if (i == f14194i) {
                    this.f14220V.m15978a("TYPE I");
                }
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    boolean z = false;
                    C2794j F = m15865F();
                    this.f14220V.m15978a(new StringBuffer().append("APPE ").append(str).toString());
                    try {
                        Socket a = F.m15967a();
                        F.m15968b();
                        synchronized (this.f14219U) {
                            this.f14213O = true;
                            this.f14216R = false;
                            this.f14217S = false;
                        }
                        try {
                            inputStream.skip(j);
                            this.f14215Q = a.getOutputStream();
                            if (this.f14211M) {
                                this.f14215Q = new DeflaterOutputStream(this.f14215Q);
                            }
                            if (c1096l != null) {
                                c1096l.m7678d();
                            }
                            if (i != f14193h) {
                                if (i == f14194i) {
                                    byte[] bArr = new byte[f14195j];
                                    while (true) {
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        this.f14215Q.write(bArr, f14192g, read);
                                        this.f14215Q.flush();
                                        if (c1096l != null) {
                                            c1096l.m7675a(read);
                                        }
                                    }
                                }
                            } else {
                                Reader inputStreamReader = new InputStreamReader(inputStream);
                                Writer outputStreamWriter = new OutputStreamWriter(this.f14215Q, m15868I());
                                char[] cArr = new char[f14195j];
                                while (true) {
                                    int read2 = inputStreamReader.read(cArr);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, f14192g, read2);
                                    outputStreamWriter.flush();
                                    if (c1096l != null) {
                                        c1096l.m7675a(read2);
                                    }
                                }
                            }
                            if (this.f14215Q != null) {
                                try {
                                    this.f14215Q.close();
                                } catch (Throwable th) {
                                }
                            }
                            try {
                                a.close();
                            } catch (Throwable th2) {
                            }
                            this.f14215Q = null;
                            synchronized (this.f14219U) {
                                z = this.f14216R;
                                this.f14213O = false;
                                this.f14216R = false;
                            }
                            C2808t c2 = this.f14220V.m15983c();
                            m15874O();
                            if (c2.m15997a() == Opcodes.FCMPG || c2.m15997a() == Opcodes.LUSHR) {
                                c2 = this.f14220V.m15983c();
                                if (z || c2.m15997a() == C2799f.f14254D) {
                                    if (this.f14217S) {
                                        this.f14220V.m15983c();
                                        this.f14217S = false;
                                    }
                                    if (c1096l != null) {
                                        c1096l.m7676b();
                                    }
                                } else {
                                    throw new C2803n(c2);
                                }
                            }
                            throw new C2803n(c2);
                        } catch (Throwable e) {
                            synchronized (this.f14219U) {
                            }
                            if (this.f14216R) {
                                if (c1096l != null) {
                                    c1096l.m7674a();
                                }
                                throw new C2783a();
                            }
                            if (c1096l != null) {
                                c1096l.m7677c();
                            }
                            throw new C2802k("I/O error in data transfer", e);
                        } catch (Throwable th3) {
                            if (this.f14215Q != null) {
                                this.f14215Q.close();
                            }
                            a.close();
                            this.f14215Q = null;
                            synchronized (this.f14219U) {
                            }
                            z = this.f14216R;
                            this.f14213O = false;
                            this.f14216R = false;
                        }
                    } catch (Throwable th4) {
                    }
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15907a(String str, OutputStream outputStream, long j, C1096l c1096l) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                int i = this.f14200B;
                if (i == 0) {
                    i = m15884m(str);
                }
                if (i == f14193h) {
                    this.f14220V.m15978a("TYPE A");
                } else if (i == f14194i) {
                    this.f14220V.m15978a("TYPE I");
                }
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    C2794j F = m15865F();
                    if (this.f14205G || j > 0) {
                        try {
                            this.f14220V.m15978a(new StringBuffer().append("REST ").append(j).toString());
                            c = this.f14220V.m15983c();
                            m15874O();
                            if (c.m15997a() != C2799f.f14281s && (!(c.m15997a() == C2799f.f14264b || c.m15997a() == C2799f.f14265c) || j > 0)) {
                                throw new C2803n(c);
                            }
                        } catch (Throwable th) {
                            F.m15968b();
                        }
                    }
                    boolean z = false;
                    this.f14220V.m15978a(new StringBuffer().append("RETR ").append(str).toString());
                    try {
                        Socket a = F.m15967a();
                        F.m15968b();
                        synchronized (this.f14219U) {
                            this.f14213O = true;
                            this.f14216R = false;
                            this.f14217S = false;
                        }
                        try {
                            this.f14214P = a.getInputStream();
                            if (this.f14211M) {
                                this.f14214P = new InflaterInputStream(this.f14214P);
                            }
                            if (c1096l != null) {
                                c1096l.m7678d();
                            }
                            if (i != f14193h) {
                                if (i == f14194i) {
                                    byte[] bArr = new byte[f14195j];
                                    while (true) {
                                        int read = this.f14214P.read(bArr, f14192g, bArr.length);
                                        if (read == -1) {
                                            break;
                                        }
                                        outputStream.write(bArr, f14192g, read);
                                        if (c1096l != null) {
                                            c1096l.m7675a(read);
                                        }
                                    }
                                }
                            } else {
                                Reader inputStreamReader = new InputStreamReader(this.f14214P, m15868I());
                                Writer outputStreamWriter = new OutputStreamWriter(outputStream);
                                char[] cArr = new char[f14195j];
                                while (true) {
                                    int read2 = inputStreamReader.read(cArr, f14192g, cArr.length);
                                    if (read2 == -1) {
                                        break;
                                    }
                                    outputStreamWriter.write(cArr, f14192g, read2);
                                    outputStreamWriter.flush();
                                    if (c1096l != null) {
                                        c1096l.m7675a(read2);
                                    }
                                }
                            }
                            if (this.f14214P != null) {
                                try {
                                    this.f14214P.close();
                                } catch (Throwable th2) {
                                }
                            }
                            try {
                                a.close();
                            } catch (Throwable th3) {
                            }
                            this.f14214P = null;
                            synchronized (this.f14219U) {
                                z = this.f14216R;
                                this.f14213O = false;
                                this.f14216R = false;
                            }
                            C2808t c2 = this.f14220V.m15983c();
                            m15874O();
                            if (c2.m15997a() == Opcodes.FCMPG || c2.m15997a() == Opcodes.LUSHR) {
                                c2 = this.f14220V.m15983c();
                                if (z || c2.m15997a() == C2799f.f14254D) {
                                    if (this.f14217S) {
                                        this.f14220V.m15983c();
                                        this.f14217S = false;
                                    }
                                    if (c1096l != null) {
                                        c1096l.m7676b();
                                    }
                                } else {
                                    throw new C2803n(c2);
                                }
                            }
                            throw new C2803n(c2);
                        } catch (Throwable e) {
                            synchronized (this.f14219U) {
                            }
                            if (this.f14216R) {
                                if (c1096l != null) {
                                    c1096l.m7674a();
                                }
                                throw new C2783a();
                            }
                            if (c1096l != null) {
                                c1096l.m7677c();
                            }
                            throw new C2802k("I/O error in data transfer", e);
                        } catch (Throwable th4) {
                            if (this.f14214P != null) {
                                this.f14214P.close();
                            }
                            a.close();
                            this.f14214P = null;
                            synchronized (this.f14219U) {
                            }
                            z = this.f14216R;
                            this.f14213O = false;
                            this.f14216R = false;
                        }
                    } catch (Throwable th5) {
                    }
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15908a(String str, String str2) {
        m15909a(str, str2, null);
    }

    public void m15909a(String str, String str2, String str3) {
        Object obj = null;
        synchronized (this.f14218T) {
            if (this.f14232y) {
                C2808t c;
                Object obj2;
                if (this.f14229v == f14194i) {
                    this.f14220V.m15978a("AUTH TLS");
                    if (this.f14220V.m15983c().m15998b()) {
                        this.f14220V.m15979a(this.f14222o);
                    } else {
                        this.f14220V.m15978a("AUTH SSL");
                        c = this.f14220V.m15983c();
                        if (c.m15998b()) {
                            this.f14220V.m15979a(this.f14222o);
                        } else {
                            throw new C2803n(c.m15997a(), "SECURITY_FTPES cannot be applied: the server refused both AUTH TLS and AUTH SSL commands");
                        }
                    }
                }
                this.f14233z = false;
                this.f14220V.m15978a(new StringBuffer().append("USER ").append(str).toString());
                c = this.f14220V.m15983c();
                switch (c.m15997a()) {
                    case C2799f.f14256F /*230*/:
                        obj2 = f14192g;
                        break;
                    case C2799f.f14279q /*331*/:
                        obj2 = f14193h;
                        break;
                    default:
                        throw new C2803n(c);
                }
                if (obj2 != null) {
                    if (str2 == null) {
                        throw new C2803n((int) C2799f.f14279q);
                    }
                    this.f14220V.m15978a(new StringBuffer().append("PASS ").append(str2).toString());
                    c = this.f14220V.m15983c();
                    switch (c.m15997a()) {
                        case C2799f.f14256F /*230*/:
                            break;
                        case C2799f.f14280r /*332*/:
                            obj = f14193h;
                            break;
                        default:
                            throw new C2803n(c);
                    }
                }
                if (obj != null) {
                    if (str3 == null) {
                        throw new C2803n((int) C2799f.f14280r);
                    }
                    this.f14220V.m15978a(new StringBuffer().append("ACCT ").append(str3).toString());
                    C2808t c2 = this.f14220V.m15983c();
                    switch (c2.m15997a()) {
                        case C2799f.f14256F /*230*/:
                            break;
                        default:
                            throw new C2803n(c2);
                    }
                }
                this.f14233z = true;
                this.f14230w = str;
                this.f14231x = str2;
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        m15864E();
        m15872M();
    }

    public void m15910a(SSLSocketFactory sSLSocketFactory) {
        synchronized (this.f14218T) {
            this.f14222o = sSLSocketFactory;
        }
    }

    public void m15911a(boolean z) {
        synchronized (this.f14218T) {
            this.f14199A = z;
        }
    }

    public String[] m15912a(String str, int i) {
        String[] c;
        Socket socket = null;
        synchronized (this.f14218T) {
            if (this.f14232y) {
                throw new IllegalStateException(new StringBuffer().append("Client already connected to ").append(str).append(" on port ").append(i).toString());
            }
            try {
                socket = this.f14221n.m15839c(str, i);
                if (this.f14229v == f14193h) {
                    socket = m15877a(socket, str, i);
                }
                this.f14220V = new C2800g(socket, m15868I());
                Iterator it = this.f14223p.iterator();
                while (it.hasNext()) {
                    this.f14220V.m15977a((C2801h) it.next());
                }
                C2808t c2 = this.f14220V.m15983c();
                if (c2.m15998b()) {
                    this.f14232y = true;
                    this.f14233z = false;
                    this.f14226s = null;
                    this.f14227t = str;
                    this.f14228u = i;
                    this.f14230w = null;
                    this.f14231x = null;
                    this.f14208J = false;
                    this.f14205G = false;
                    this.f14209K = false;
                    this.f14210L = false;
                    this.f14212N = false;
                    c = c2.m15999c();
                    if (!(this.f14232y || socket == null)) {
                        try {
                            socket.close();
                        } catch (Throwable th) {
                        }
                    }
                } else {
                    throw new C2803n(c2);
                }
            } catch (IOException e) {
                throw e;
            } catch (Throwable th2) {
                if (!(this.f14232y || socket == null)) {
                    try {
                        socket.close();
                    } catch (Throwable th3) {
                    }
                }
            }
        }
        return c;
    }

    public SSLSocketFactory m15913b() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (this.f14218T) {
            sSLSocketFactory = this.f14222o;
        }
        return sSLSocketFactory;
    }

    public void m15914b(int i) {
        if (i == 0 || i == f14194i || i == f14193h) {
            synchronized (this.f14218T) {
                this.f14200B = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid type");
    }

    public void m15915b(C2801h c2801h) {
        synchronized (this.f14218T) {
            this.f14223p.remove(c2801h);
            if (this.f14220V != null) {
                this.f14220V.m15980b(c2801h);
            }
        }
    }

    public void m15916b(C2788s c2788s) {
        synchronized (this.f14218T) {
            this.f14224q.remove(c2788s);
        }
    }

    public void m15917b(File file) {
        m15918b(file, null);
    }

    public void m15918b(File file, C1096l c1096l) {
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    m15906a(file.getName(), fileInputStream, 0, c1096l);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            return;
                        } catch (Throwable th) {
                            return;
                        }
                    }
                    return;
                } catch (IllegalStateException e) {
                    throw e;
                } catch (IOException e2) {
                    throw e2;
                } catch (C2805p e3) {
                    throw e3;
                } catch (C2803n e4) {
                    throw e4;
                } catch (C2802k e5) {
                    throw e5;
                } catch (C2783a e6) {
                    throw e6;
                } catch (Throwable th2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                }
            } catch (Throwable e7) {
                throw new C2802k(e7);
            }
        }
        throw new FileNotFoundException(file.getAbsolutePath());
    }

    public void m15919b(String str, String str2) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("RNFR ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15997a() != C2799f.f14281s) {
                    throw new C2803n(c);
                }
                this.f14220V.m15978a(new StringBuffer().append("RNTO ").append(str2).toString());
                c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15920b(boolean z) {
        this.f14207I = z;
    }

    public String[] m15921b(String str) {
        return m15912a(str, this.f14229v == f14193h ? 990 : 21);
    }

    public int m15922c() {
        return this.f14229v;
    }

    public C2808t m15923c(String str) {
        C2808t c;
        synchronized (this.f14218T) {
            if (this.f14232y) {
                this.f14220V.m15978a(str);
                m15874O();
                c = this.f14220V.m15983c();
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        return c;
    }

    public void m15924c(int i) {
        if (this.f14200B == 0 || this.f14200B == f14193h || this.f14200B == f14194i) {
            synchronized (this.f14218T) {
                this.f14201C = i;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid MLSD policy");
    }

    public void m15925c(boolean z) {
        synchronized (this.f14218T) {
            if (this.f14232y) {
                if (this.f14233z) {
                    m15873N();
                }
                if (z) {
                    this.f14220V.m15978a("QUIT");
                    C2808t c = this.f14220V.m15983c();
                    if (!c.m15998b()) {
                        throw new C2803n(c);
                    }
                }
                this.f14220V.m15976a();
                this.f14220V = null;
                this.f14232y = false;
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
    }

    public int m15926d() {
        int i;
        synchronized (this.f14218T) {
            i = this.f14200B;
        }
        return i;
    }

    public C2808t m15927d(String str) {
        C2808t c;
        synchronized (this.f14218T) {
            if (this.f14232y) {
                this.f14220V.m15978a(new StringBuffer().append("SITE ").append(str).toString());
                m15874O();
                c = this.f14220V.m15983c();
            } else {
                throw new IllegalStateException("Client not connected");
            }
        }
        return c;
    }

    public void m15928d(boolean z) {
        synchronized (this.f14219U) {
            if (this.f14213O && !this.f14216R) {
                if (z) {
                    this.f14220V.m15978a("ABOR");
                    m15874O();
                    this.f14217S = true;
                }
                if (this.f14214P != null) {
                    try {
                        this.f14214P.close();
                    } catch (Throwable th) {
                    }
                }
                if (this.f14215Q != null) {
                    try {
                        this.f14215Q.close();
                    } catch (Throwable th2) {
                    }
                }
                this.f14216R = true;
            }
        }
    }

    public int m15929e() {
        int i;
        synchronized (this.f14218T) {
            i = this.f14201C;
        }
        return i;
    }

    public void m15930e(String str) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("ACCT ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public String m15931f() {
        String str;
        synchronized (this.f14218T) {
            str = this.f14206H;
        }
        return str;
    }

    public void m15932f(String str) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("CWD ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public Date m15933g(String str) {
        Date parse;
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("MDTM ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    String[] c2 = c.m15999c();
                    if (c2.length != f14193h) {
                        throw new C2805p();
                    }
                    try {
                        parse = f14196k.parse(c2[f14192g]);
                    } catch (ParseException e) {
                        throw new C2805p();
                    }
                }
                throw new C2803n(c);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return parse;
    }

    public boolean m15934g() {
        boolean z;
        synchronized (this.f14218T) {
            z = this.f14205G;
        }
        return z;
    }

    public long m15935h(String str) {
        long parseLong;
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("TYPE I");
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    this.f14220V.m15978a(new StringBuffer().append("SIZE ").append(str).toString());
                    c = this.f14220V.m15983c();
                    m15874O();
                    if (c.m15998b()) {
                        String[] c2 = c.m15999c();
                        if (c2.length != f14193h) {
                            throw new C2805p();
                        }
                        try {
                            parseLong = Long.parseLong(c2[f14192g]);
                        } catch (Throwable th) {
                            C2805p c2805p = new C2805p();
                        }
                    } else {
                        throw new C2803n(c);
                    }
                }
                throw new C2803n(c);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return parseLong;
    }

    public boolean m15936h() {
        return this.f14210L;
    }

    public void m15937i(String str) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("DELE ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public boolean m15938i() {
        return this.f14207I;
    }

    public C2784u m15939j() {
        C2784u c2784u;
        synchronized (this.f14218T) {
            c2784u = this.f14225r;
        }
        return c2784u;
    }

    public void m15940j(String str) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("RMD ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15941k(String str) {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a(new StringBuffer().append("MKD ").append(str).toString());
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public boolean m15942k() {
        boolean z;
        synchronized (this.f14218T) {
            z = this.f14233z;
        }
        return z;
    }

    public boolean m15943l() {
        boolean z;
        synchronized (this.f14218T) {
            z = this.f14232y;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public it.p074a.p075a.C2804o[] m15944l(java.lang.String r12) {
        /*
        r11 = this;
        r10 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r9 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r0 = 1;
        r1 = 0;
        r3 = 0;
        r5 = r11.f14218T;
        monitor-enter(r5);
        r2 = r11.f14232y;	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0019;
    L_0x000e:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0016 }
        r1 = "Client not connected";
        r0.<init>(r1);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0016 }
        throw r0;
    L_0x0019:
        r2 = r11.f14233z;	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0025;
    L_0x001d:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0016 }
        r1 = "Client not authenticated";
        r0.<init>(r1);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0025:
        r2 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r4 = "TYPE A";
        r2.m15978a(r4);	 Catch:{ all -> 0x0016 }
        r2 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r2 = r2.m15983c();	 Catch:{ all -> 0x0016 }
        r11.m15874O();	 Catch:{ all -> 0x0016 }
        r4 = r2.m15998b();	 Catch:{ all -> 0x0016 }
        if (r4 != 0) goto L_0x0041;
    L_0x003b:
        r0 = new it.a.a.n;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0041:
        r2 = r11.m15865F();	 Catch:{ all -> 0x0016 }
        r4 = r11.f14201C;	 Catch:{ all -> 0x0016 }
        if (r4 != 0) goto L_0x0102;
    L_0x0049:
        r0 = r11.f14209K;	 Catch:{ all -> 0x0016 }
        r4 = r0;
    L_0x004c:
        if (r4 == 0) goto L_0x010c;
    L_0x004e:
        r0 = "MLSD";
    L_0x0050:
        if (r12 == 0) goto L_0x006f;
    L_0x0052:
        r6 = r12.length();	 Catch:{ all -> 0x0016 }
        if (r6 <= 0) goto L_0x006f;
    L_0x0058:
        r6 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0016 }
        r6.<init>();	 Catch:{ all -> 0x0016 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0016 }
        r6 = " ";
        r0 = r0.append(r6);	 Catch:{ all -> 0x0016 }
        r0 = r0.append(r12);	 Catch:{ all -> 0x0016 }
        r0 = r0.toString();	 Catch:{ all -> 0x0016 }
    L_0x006f:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0016 }
        r6.<init>();	 Catch:{ all -> 0x0016 }
        r7 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r7.m15978a(r0);	 Catch:{ all -> 0x0016 }
        r7 = r2.m15967a();	 Catch:{ all -> 0x0110 }
        r2.m15968b();	 Catch:{ all -> 0x0115 }
        r2 = r11.f14219U;	 Catch:{ all -> 0x0115 }
        monitor-enter(r2);	 Catch:{ all -> 0x0115 }
        r0 = 1;
        r11.f14213O = r0;	 Catch:{ all -> 0x0118 }
        r0 = 0;
        r11.f14216R = r0;	 Catch:{ all -> 0x0118 }
        r0 = 0;
        r11.f14217S = r0;	 Catch:{ all -> 0x0118 }
        monitor-exit(r2);	 Catch:{ all -> 0x0118 }
        r0 = r7.getInputStream();	 Catch:{ IOException -> 0x0212 }
        r11.f14214P = r0;	 Catch:{ IOException -> 0x0212 }
        r0 = r11.f14211M;	 Catch:{ IOException -> 0x0212 }
        if (r0 == 0) goto L_0x00a0;
    L_0x0097:
        r0 = new java.util.zip.InflaterInputStream;	 Catch:{ IOException -> 0x0212 }
        r2 = r11.f14214P;	 Catch:{ IOException -> 0x0212 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0212 }
        r11.f14214P = r0;	 Catch:{ IOException -> 0x0212 }
    L_0x00a0:
        r2 = new it.a.a.v;	 Catch:{ IOException -> 0x0212 }
        r8 = r11.f14214P;	 Catch:{ IOException -> 0x0212 }
        if (r4 == 0) goto L_0x011b;
    L_0x00a6:
        r0 = "UTF-8";
    L_0x00a8:
        r2.<init>(r8, r0);	 Catch:{ IOException -> 0x0212 }
    L_0x00ab:
        r0 = r2.m16000a();	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        if (r0 == 0) goto L_0x01b7;
    L_0x00b1:
        r8 = r0.length();	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        if (r8 <= 0) goto L_0x00ab;
    L_0x00b7:
        r6.add(r0);	 Catch:{ IOException -> 0x00bb, all -> 0x020e }
        goto L_0x00ab;
    L_0x00bb:
        r0 = move-exception;
        r1 = r2;
    L_0x00bd:
        r2 = r11.f14219U;	 Catch:{ all -> 0x00cd }
        monitor-enter(r2);	 Catch:{ all -> 0x00cd }
        r4 = r11.f14216R;	 Catch:{ all -> 0x00ca }
        if (r4 == 0) goto L_0x0120;
    L_0x00c4:
        r0 = new it.a.a.a;	 Catch:{ all -> 0x00ca }
        r0.<init>();	 Catch:{ all -> 0x00ca }
        throw r0;	 Catch:{ all -> 0x00ca }
    L_0x00ca:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00ca }
        throw r0;	 Catch:{ all -> 0x00cd }
    L_0x00cd:
        r0 = move-exception;
    L_0x00ce:
        if (r1 == 0) goto L_0x00d3;
    L_0x00d0:
        r1.close();	 Catch:{ Throwable -> 0x01ad }
    L_0x00d3:
        r7.close();	 Catch:{ Throwable -> 0x01b0 }
    L_0x00d6:
        r1 = 0;
        r11.f14214P = r1;	 Catch:{ all -> 0x0115 }
        r2 = r11.f14219U;	 Catch:{ all -> 0x0115 }
        monitor-enter(r2);	 Catch:{ all -> 0x0115 }
        r1 = r11.f14216R;	 Catch:{ all -> 0x01b3 }
        r3 = 0;
        r11.f14213O = r3;	 Catch:{ all -> 0x020c }
        r3 = 0;
        r11.f14216R = r3;	 Catch:{ all -> 0x020c }
        monitor-exit(r2);	 Catch:{ all -> 0x020c }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x00e6:
        r0 = move-exception;
    L_0x00e7:
        r2 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r2 = r2.m15983c();	 Catch:{ all -> 0x0016 }
        r11.m15874O();	 Catch:{ all -> 0x0016 }
        r3 = r2.m15997a();	 Catch:{ all -> 0x0016 }
        if (r3 == r10) goto L_0x0197;
    L_0x00f6:
        r3 = r2.m15997a();	 Catch:{ all -> 0x0016 }
        if (r3 == r9) goto L_0x0197;
    L_0x00fc:
        r0 = new it.a.a.n;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0102:
        r4 = r11.f14201C;	 Catch:{ all -> 0x0016 }
        if (r4 != r0) goto L_0x0109;
    L_0x0106:
        r4 = r0;
        goto L_0x004c;
    L_0x0109:
        r4 = r3;
        goto L_0x004c;
    L_0x010c:
        r0 = "LIST";
        goto L_0x0050;
    L_0x0110:
        r0 = move-exception;
        r2.m15968b();	 Catch:{ all -> 0x0115 }
        throw r0;	 Catch:{ all -> 0x0115 }
    L_0x0115:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00e7;
    L_0x0118:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0118 }
        throw r0;	 Catch:{ all -> 0x0115 }
    L_0x011b:
        r0 = r11.m15868I();	 Catch:{ IOException -> 0x0212 }
        goto L_0x00a8;
    L_0x0120:
        r4 = new it.a.a.k;	 Catch:{ all -> 0x00ca }
        r6 = "I/O error in data transfer";
        r4.<init>(r6, r0);	 Catch:{ all -> 0x00ca }
        throw r4;	 Catch:{ all -> 0x00ca }
    L_0x0128:
        r1 = r11.f14217S;	 Catch:{ all -> 0x0016 }
        if (r1 == 0) goto L_0x0134;
    L_0x012c:
        r1 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r1.m15983c();	 Catch:{ all -> 0x0016 }
        r1 = 0;
        r11.f14217S = r1;	 Catch:{ all -> 0x0016 }
    L_0x0134:
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0135:
        r0 = r11.f14217S;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0141;
    L_0x0139:
        r0 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r0.m15983c();	 Catch:{ all -> 0x0016 }
        r0 = 0;
        r11.f14217S = r0;	 Catch:{ all -> 0x0016 }
    L_0x0141:
        r2 = r6.size();	 Catch:{ all -> 0x0016 }
        r7 = new java.lang.String[r2];	 Catch:{ all -> 0x0016 }
    L_0x0147:
        if (r3 >= r2) goto L_0x0154;
    L_0x0149:
        r0 = r6.get(r3);	 Catch:{ all -> 0x0016 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0016 }
        r7[r3] = r0;	 Catch:{ all -> 0x0016 }
        r3 = r3 + 1;
        goto L_0x0147;
    L_0x0154:
        if (r4 == 0) goto L_0x0167;
    L_0x0156:
        r0 = new it.a.a.c.c;	 Catch:{ all -> 0x0016 }
        r0.<init>();	 Catch:{ all -> 0x0016 }
        r1 = r0.m15964a(r7);	 Catch:{ all -> 0x0016 }
    L_0x015f:
        if (r1 != 0) goto L_0x0195;
    L_0x0161:
        r0 = new it.a.a.r;	 Catch:{ all -> 0x0016 }
        r0.<init>();	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x0167:
        r0 = r11.f14226s;	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0171;
    L_0x016b:
        r0 = r11.f14226s;	 Catch:{ r -> 0x0190 }
        r1 = r0.m15959a(r7);	 Catch:{ r -> 0x0190 }
    L_0x0171:
        if (r1 != 0) goto L_0x015f;
    L_0x0173:
        r0 = r11.f14224q;	 Catch:{ all -> 0x0016 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0016 }
    L_0x0179:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x015f;
    L_0x017f:
        r0 = r2.next();	 Catch:{ all -> 0x0016 }
        r0 = (it.p074a.p075a.C2788s) r0;	 Catch:{ all -> 0x0016 }
        r1 = r0.m15959a(r7);	 Catch:{ r -> 0x018c }
        r11.f14226s = r0;	 Catch:{ r -> 0x018c }
        goto L_0x015f;
    L_0x018c:
        r0 = move-exception;
        r0 = r1;
        r1 = r0;
        goto L_0x0179;
    L_0x0190:
        r0 = move-exception;
        r0 = 0;
        r11.f14226s = r0;	 Catch:{ all -> 0x0016 }
        goto L_0x0171;
    L_0x0195:
        monitor-exit(r5);	 Catch:{ all -> 0x0016 }
        return r1;
    L_0x0197:
        r2 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r2 = r2.m15983c();	 Catch:{ all -> 0x0016 }
        if (r1 != 0) goto L_0x0128;
    L_0x019f:
        r1 = r2.m15997a();	 Catch:{ all -> 0x0016 }
        r3 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        if (r1 == r3) goto L_0x0128;
    L_0x01a7:
        r0 = new it.a.a.n;	 Catch:{ all -> 0x0016 }
        r0.<init>(r2);	 Catch:{ all -> 0x0016 }
        throw r0;	 Catch:{ all -> 0x0016 }
    L_0x01ad:
        r1 = move-exception;
        goto L_0x00d3;
    L_0x01b0:
        r1 = move-exception;
        goto L_0x00d6;
    L_0x01b3:
        r0 = move-exception;
        r1 = r3;
    L_0x01b5:
        monitor-exit(r2);	 Catch:{ all -> 0x020c }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x01b7:
        if (r2 == 0) goto L_0x01bc;
    L_0x01b9:
        r2.close();	 Catch:{ Throwable -> 0x01e9 }
    L_0x01bc:
        r7.close();	 Catch:{ Throwable -> 0x01eb }
    L_0x01bf:
        r0 = 0;
        r11.f14214P = r0;	 Catch:{ all -> 0x0115 }
        r7 = r11.f14219U;	 Catch:{ all -> 0x0115 }
        monitor-enter(r7);	 Catch:{ all -> 0x0115 }
        r2 = r11.f14216R;	 Catch:{ all -> 0x01ed }
        r0 = 0;
        r11.f14213O = r0;	 Catch:{ all -> 0x0209 }
        r0 = 0;
        r11.f14216R = r0;	 Catch:{ all -> 0x0209 }
        monitor-exit(r7);	 Catch:{ all -> 0x0209 }
        r0 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r0 = r0.m15983c();	 Catch:{ all -> 0x0016 }
        r11.m15874O();	 Catch:{ all -> 0x0016 }
        r7 = r0.m15997a();	 Catch:{ all -> 0x0016 }
        if (r7 == r10) goto L_0x01f1;
    L_0x01dd:
        r7 = r0.m15997a();	 Catch:{ all -> 0x0016 }
        if (r7 == r9) goto L_0x01f1;
    L_0x01e3:
        r1 = new it.a.a.n;	 Catch:{ all -> 0x0016 }
        r1.<init>(r0);	 Catch:{ all -> 0x0016 }
        throw r1;	 Catch:{ all -> 0x0016 }
    L_0x01e9:
        r0 = move-exception;
        goto L_0x01bc;
    L_0x01eb:
        r0 = move-exception;
        goto L_0x01bf;
    L_0x01ed:
        r0 = move-exception;
        r1 = r3;
    L_0x01ef:
        monitor-exit(r7);	 Catch:{ all -> 0x0207 }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x01f1:
        r0 = r11.f14220V;	 Catch:{ all -> 0x0016 }
        r0 = r0.m15983c();	 Catch:{ all -> 0x0016 }
        if (r2 != 0) goto L_0x0135;
    L_0x01f9:
        r2 = r0.m15997a();	 Catch:{ all -> 0x0016 }
        r7 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        if (r2 == r7) goto L_0x0135;
    L_0x0201:
        r1 = new it.a.a.n;	 Catch:{ all -> 0x0016 }
        r1.<init>(r0);	 Catch:{ all -> 0x0016 }
        throw r1;	 Catch:{ all -> 0x0016 }
    L_0x0207:
        r0 = move-exception;
        goto L_0x01ef;
    L_0x0209:
        r0 = move-exception;
        r1 = r2;
        goto L_0x01ef;
    L_0x020c:
        r0 = move-exception;
        goto L_0x01b5;
    L_0x020e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ce;
    L_0x0212:
        r0 = move-exception;
        goto L_0x00bd;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.b.l(java.lang.String):it.a.a.o[]");
    }

    public boolean m15945m() {
        boolean z;
        synchronized (this.f14218T) {
            z = this.f14199A;
        }
        return z;
    }

    public String m15946n() {
        String str;
        synchronized (this.f14218T) {
            str = this.f14227t;
        }
        return str;
    }

    public int m15947o() {
        int i;
        synchronized (this.f14218T) {
            i = this.f14228u;
        }
        return i;
    }

    public String m15948p() {
        String str;
        synchronized (this.f14218T) {
            str = this.f14231x;
        }
        return str;
    }

    public String m15949q() {
        String str;
        synchronized (this.f14218T) {
            str = this.f14230w;
        }
        return str;
    }

    public long m15950r() {
        long j;
        synchronized (this.f14218T) {
            j = this.f14202D;
        }
        return j;
    }

    public C2801h[] m15951s() {
        C2801h[] c2801hArr;
        synchronized (this.f14218T) {
            int size = this.f14223p.size();
            c2801hArr = new C2801h[size];
            for (int i = f14192g; i < size; i += f14193h) {
                c2801hArr[i] = (C2801h) this.f14223p.get(i);
            }
        }
        return c2801hArr;
    }

    public C2788s[] m15952t() {
        C2788s[] c2788sArr;
        synchronized (this.f14218T) {
            int size = this.f14224q.size();
            c2788sArr = new C2788s[size];
            for (int i = f14192g; i < size; i += f14193h) {
                c2788sArr[i] = (C2788s) this.f14224q.get(i);
            }
        }
        return c2788sArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
        r6 = this;
        r0 = 0;
        r2 = r6.f14218T;
        monitor-enter(r2);
        r3 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0083 }
        r3.<init>();	 Catch:{ all -> 0x0083 }
        r1 = r6.getClass();	 Catch:{ all -> 0x0083 }
        r1 = r1.getName();	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = " [connected=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14232y;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14232y;	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x0036;
    L_0x0022:
        r1 = ", host=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14227t;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", port=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14228u;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
    L_0x0036:
        r1 = ", connector=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14221n;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", security=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14229v;	 Catch:{ all -> 0x0083 }
        switch(r1) {
            case 0: goto L_0x007d;
            case 1: goto L_0x0086;
            case 2: goto L_0x008c;
            default: goto L_0x004a;
        };	 Catch:{ all -> 0x0083 }
    L_0x004a:
        r1 = ", authenticated=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14233z;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14233z;	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x00c7;
    L_0x0058:
        r1 = ", username=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14230w;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", password=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r4 = new java.lang.StringBuffer;	 Catch:{ all -> 0x0083 }
        r4.<init>();	 Catch:{ all -> 0x0083 }
        r1 = r0;
    L_0x006d:
        r5 = r6.f14231x;	 Catch:{ all -> 0x0083 }
        r5 = r5.length();	 Catch:{ all -> 0x0083 }
        if (r1 >= r5) goto L_0x0092;
    L_0x0075:
        r5 = 42;
        r4.append(r5);	 Catch:{ all -> 0x0083 }
        r1 = r1 + 1;
        goto L_0x006d;
    L_0x007d:
        r1 = "SECURITY_FTP";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x004a;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0083 }
        throw r0;
    L_0x0086:
        r1 = "SECURITY_FTPS";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x004a;
    L_0x008c:
        r1 = "SECURITY_FTPES";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x004a;
    L_0x0092:
        r3.append(r4);	 Catch:{ all -> 0x0083 }
        r1 = ", restSupported=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14205G;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", utf8supported=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14208J;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", mlsdSupported=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14209K;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", mode=modezSupported";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14210L;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", mode=modezEnabled";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14211M;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
    L_0x00c7:
        r1 = ", transfer mode=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14199A;	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x0108;
    L_0x00d0:
        r1 = "passive";
    L_0x00d2:
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = ", transfer type=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14200B;	 Catch:{ all -> 0x0083 }
        switch(r1) {
            case 0: goto L_0x010b;
            case 1: goto L_0x0117;
            case 2: goto L_0x0111;
            default: goto L_0x00df;
        };	 Catch:{ all -> 0x0083 }
    L_0x00df:
        r1 = ", textualExtensionRecognizer=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r6.f14225r;	 Catch:{ all -> 0x0083 }
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r4 = r6.m15952t();	 Catch:{ all -> 0x0083 }
        r1 = r4.length;	 Catch:{ all -> 0x0083 }
        if (r1 <= 0) goto L_0x011d;
    L_0x00f0:
        r1 = ", listParsers=";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        r1 = r0;
    L_0x00f6:
        r5 = r4.length;	 Catch:{ all -> 0x0083 }
        if (r1 >= r5) goto L_0x011d;
    L_0x00f9:
        if (r1 <= 0) goto L_0x0100;
    L_0x00fb:
        r5 = ", ";
        r3.append(r5);	 Catch:{ all -> 0x0083 }
    L_0x0100:
        r5 = r4[r1];	 Catch:{ all -> 0x0083 }
        r3.append(r5);	 Catch:{ all -> 0x0083 }
        r1 = r1 + 1;
        goto L_0x00f6;
    L_0x0108:
        r1 = "active";
        goto L_0x00d2;
    L_0x010b:
        r1 = "TYPE_AUTO";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x00df;
    L_0x0111:
        r1 = "TYPE_BINARY";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x00df;
    L_0x0117:
        r1 = "TYPE_TEXTUAL";
        r3.append(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x00df;
    L_0x011d:
        r1 = r6.m15951s();	 Catch:{ all -> 0x0083 }
        r4 = r1.length;	 Catch:{ all -> 0x0083 }
        if (r4 <= 0) goto L_0x013b;
    L_0x0124:
        r4 = ", communicationListeners=";
        r3.append(r4);	 Catch:{ all -> 0x0083 }
    L_0x0129:
        r4 = r1.length;	 Catch:{ all -> 0x0083 }
        if (r0 >= r4) goto L_0x013b;
    L_0x012c:
        if (r0 <= 0) goto L_0x0133;
    L_0x012e:
        r4 = ", ";
        r3.append(r4);	 Catch:{ all -> 0x0083 }
    L_0x0133:
        r4 = r1[r0];	 Catch:{ all -> 0x0083 }
        r3.append(r4);	 Catch:{ all -> 0x0083 }
        r0 = r0 + 1;
        goto L_0x0129;
    L_0x013b:
        r0 = ", autoNoopTimeout=";
        r3.append(r0);	 Catch:{ all -> 0x0083 }
        r0 = r6.f14202D;	 Catch:{ all -> 0x0083 }
        r3.append(r0);	 Catch:{ all -> 0x0083 }
        r0 = "]";
        r3.append(r0);	 Catch:{ all -> 0x0083 }
        r0 = r3.toString();	 Catch:{ all -> 0x0083 }
        monitor-exit(r2);	 Catch:{ all -> 0x0083 }
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.a.a.b.toString():java.lang.String");
    }

    public void m15953u() {
        this.f14221n.m15837b();
    }

    public void m15954v() {
        if (this.f14220V != null) {
            this.f14220V.m15976a();
            this.f14220V = null;
        }
        this.f14232y = false;
        m15873N();
    }

    public void m15955w() {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("REIN");
                C2808t c = this.f14220V.m15983c();
                if (c.m15998b()) {
                    m15873N();
                    this.f14233z = false;
                    this.f14230w = null;
                    this.f14231x = null;
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public void m15956x() {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                try {
                    this.f14220V.m15978a("NOOP");
                    C2808t c = this.f14220V.m15983c();
                    if (c.m15998b()) {
                    } else {
                        throw new C2803n(c);
                    }
                } finally {
                    m15874O();
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }

    public String m15957y() {
        String substring;
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("PWD");
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                    String[] c2 = c.m15999c();
                    if (c2.length != f14193h) {
                        throw new C2805p();
                    }
                    Matcher matcher = f14198m.matcher(c2[f14192g]);
                    if (matcher.find()) {
                        substring = c2[f14192g].substring(matcher.start() + f14193h, matcher.end() - 1);
                    } else {
                        throw new C2805p();
                    }
                }
                throw new C2803n(c);
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
        return substring;
    }

    public void m15958z() {
        synchronized (this.f14218T) {
            if (!this.f14232y) {
                throw new IllegalStateException("Client not connected");
            } else if (this.f14233z) {
                this.f14220V.m15978a("CDUP");
                C2808t c = this.f14220V.m15983c();
                m15874O();
                if (c.m15998b()) {
                } else {
                    throw new C2803n(c);
                }
            } else {
                throw new IllegalStateException("Client not authenticated");
            }
        }
    }
}
