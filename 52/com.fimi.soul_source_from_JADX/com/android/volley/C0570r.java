package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.android.volley.r */
public abstract class C0570r<T> implements Comparable<C0570r<T>> {
    private static final String f3546a = "UTF-8";
    private static long f3547q;
    private final ai f3548b;
    private final int f3549c;
    private final String f3550d;
    private String f3551e;
    private String f3552f;
    private final int f3553g;
    private aa f3554h;
    private Integer f3555i;
    private C0600v f3556j;
    private boolean f3557k;
    private boolean f3558l;
    private boolean f3559m;
    private ad f3560n;
    private C0555c f3561o;
    private Object f3562p;

    public C0570r(int i, String str, aa aaVar) {
        this.f3548b = ai.f3499a ? new ai() : null;
        this.f3557k = true;
        this.f3558l = false;
        this.f3559m = false;
        this.f3561o = null;
        this.f3549c = i;
        this.f3550d = str;
        this.f3552f = C0570r.m5090a(i, str);
        this.f3554h = aaVar;
        m5100a(new C0558f());
        this.f3553g = C0570r.m5093d(str);
    }

    @Deprecated
    public C0570r(String str, aa aaVar) {
        this(-1, str, aaVar);
    }

    private static String m5090a(int i, String str) {
        StringBuilder append = new StringBuilder().append("Request:").append(i).append(":").append(str).append(":").append(System.currentTimeMillis()).append(":");
        long j = f3547q;
        f3547q = 1 + j;
        return C0562j.m5084a(append.append(j).toString());
    }

    private byte[] m5091a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append(SignatureVisitor.INSTANCEOF);
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    private static int m5093d(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public void m5094A() {
        this.f3559m = true;
    }

    public boolean m5095B() {
        return this.f3559m;
    }

    public int m5096a() {
        return this.f3549c;
    }

    public int m5097a(C0570r<T> c0570r) {
        C0599u x = m5133x();
        C0599u x2 = c0570r.m5133x();
        return x == x2 ? this.f3555i.intValue() - c0570r.f3555i.intValue() : x2.ordinal() - x.ordinal();
    }

    protected ag m5098a(ag agVar) {
        return agVar;
    }

    public final C0570r<?> m5099a(int i) {
        this.f3555i = Integer.valueOf(i);
        return this;
    }

    public C0570r<?> m5100a(ad adVar) {
        this.f3560n = adVar;
        return this;
    }

    public C0570r<?> m5101a(C0555c c0555c) {
        this.f3561o = c0555c;
        return this;
    }

    public C0570r<?> m5102a(C0600v c0600v) {
        this.f3556j = c0600v;
        return this;
    }

    public C0570r<?> m5103a(Object obj) {
        this.f3562p = obj;
        return this;
    }

    public final C0570r<?> m5104a(boolean z) {
        this.f3557k = z;
        return this;
    }

    protected abstract C0604z<T> m5105a(C0566n c0566n);

    public void m5106a(String str) {
        if (ai.f3499a) {
            this.f3548b.m5065a(str, Thread.currentThread().getId());
        }
    }

    public Object m5107b() {
        return this.f3562p;
    }

    public void m5108b(ag agVar) {
        if (this.f3554h != null) {
            this.f3554h.m5047a(agVar);
        }
    }

    protected abstract void m5109b(T t);

    void m5110b(String str) {
        if (this.f3556j != null) {
            this.f3556j.m5285b(this);
            m5114e();
        }
        if (ai.f3499a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new C0571s(this, str, id));
                return;
            }
            this.f3548b.m5065a(str, id);
            this.f3548b.m5064a(toString());
        }
    }

    public aa m5111c() {
        return this.f3554h;
    }

    public void m5112c(String str) {
        this.f3551e = str;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m5097a((C0570r) obj);
    }

    public int m5113d() {
        return this.f3553g;
    }

    protected void m5114e() {
        this.f3554h = null;
    }

    public final int m5115f() {
        if (this.f3555i != null) {
            return this.f3555i.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String m5116g() {
        return this.f3551e != null ? this.f3551e : this.f3550d;
    }

    public String m5117h() {
        return this.f3550d;
    }

    public String m5118i() {
        return this.f3552f;
    }

    public String m5119j() {
        return this.f3549c + ":" + this.f3550d;
    }

    public C0555c m5120k() {
        return this.f3561o;
    }

    public void m5121l() {
        this.f3558l = true;
    }

    public boolean m5122m() {
        return this.f3558l;
    }

    public Map<String, String> m5123n() {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> m5124o() {
        return m5128s();
    }

    @Deprecated
    protected String m5125p() {
        return m5129t();
    }

    @Deprecated
    public String m5126q() {
        return m5130u();
    }

    @Deprecated
    public byte[] m5127r() {
        Map o = m5124o();
        return (o == null || o.size() <= 0) ? null : m5091a(o, m5125p());
    }

    protected Map<String, String> m5128s() {
        return null;
    }

    protected String m5129t() {
        return f3546a;
    }

    public String toString() {
        return (this.f3558l ? "[X] " : "[ ] ") + m5116g() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ("0x" + Integer.toHexString(m5113d())) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m5133x() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f3555i;
    }

    public String m5130u() {
        return "application/x-www-form-urlencoded; charset=" + m5129t();
    }

    public byte[] m5131v() {
        Map s = m5128s();
        return (s == null || s.size() <= 0) ? null : m5091a(s, m5129t());
    }

    public final boolean m5132w() {
        return this.f3557k;
    }

    public C0599u m5133x() {
        return C0599u.NORMAL;
    }

    public final int m5134y() {
        return this.f3560n.m5052a();
    }

    public ad m5135z() {
        return this.f3560n;
    }
}
