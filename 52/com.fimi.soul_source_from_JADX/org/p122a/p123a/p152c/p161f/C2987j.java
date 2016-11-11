package org.p122a.p123a.p152c.p161f;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2922b;
import org.p122a.p123a.p124f.p165d.C3035a;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.c.f.j */
public class C2987j {
    private String f14925a;
    private String f14926b;
    private String f14927c;
    private String f14928d;
    private String f14929e;
    private String f14930f;
    private int f14931g;
    private String f14932h;
    private String f14933i;
    private String f14934j;
    private List<NameValuePair> f14935k;
    private String f14936l;
    private String f14937m;
    private String f14938n;

    public C2987j() {
        this.f14931g = -1;
    }

    public C2987j(String str) {
        m16934a(new URI(str));
    }

    public C2987j(URI uri) {
        m16934a(uri);
    }

    private List<NameValuePair> m16933a(String str, Charset charset) {
        return (str == null || str.length() <= 0) ? null : C2989l.m16984a(str, charset);
    }

    private void m16934a(URI uri) {
        this.f14925a = uri.getScheme();
        this.f14926b = uri.getRawSchemeSpecificPart();
        this.f14927c = uri.getRawAuthority();
        this.f14930f = uri.getHost();
        this.f14931g = uri.getPort();
        this.f14929e = uri.getRawUserInfo();
        this.f14928d = uri.getUserInfo();
        this.f14933i = uri.getRawPath();
        this.f14932h = uri.getPath();
        this.f14934j = uri.getRawQuery();
        this.f14935k = m16933a(uri.getRawQuery(), C2922b.f14781e);
        this.f14938n = uri.getRawFragment();
        this.f14937m = uri.getFragment();
    }

    private String m16935c(List<NameValuePair> list) {
        return C2989l.m16978a((Iterable) list, C2922b.f14781e);
    }

    private String m16936h(String str) {
        return C2989l.m16991b(str, C2922b.f14781e);
    }

    private String m16937i(String str) {
        return C2989l.m16994d(str, C2922b.f14781e);
    }

    private String m16938j(String str) {
        return C2989l.m16993c(str, C2922b.f14781e);
    }

    private static String m16939k(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        while (i < str.length() && str.charAt(i) == '/') {
            i++;
        }
        return i > 1 ? str.substring(i - 1) : str;
    }

    private String m16940m() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f14925a != null) {
            stringBuilder.append(this.f14925a).append(':');
        }
        if (this.f14926b != null) {
            stringBuilder.append(this.f14926b);
        } else {
            if (this.f14927c != null) {
                stringBuilder.append("//").append(this.f14927c);
            } else if (this.f14930f != null) {
                stringBuilder.append("//");
                if (this.f14929e != null) {
                    stringBuilder.append(this.f14929e).append("@");
                } else if (this.f14928d != null) {
                    stringBuilder.append(m16936h(this.f14928d)).append("@");
                }
                if (C3035a.m17134e(this.f14930f)) {
                    stringBuilder.append("[").append(this.f14930f).append("]");
                } else {
                    stringBuilder.append(this.f14930f);
                }
                if (this.f14931g >= 0) {
                    stringBuilder.append(":").append(this.f14931g);
                }
            }
            if (this.f14933i != null) {
                stringBuilder.append(C2987j.m16939k(this.f14933i));
            } else if (this.f14932h != null) {
                stringBuilder.append(m16937i(C2987j.m16939k(this.f14932h)));
            }
            if (this.f14934j != null) {
                stringBuilder.append("?").append(this.f14934j);
            } else if (this.f14935k != null) {
                stringBuilder.append("?").append(m16935c(this.f14935k));
            } else if (this.f14936l != null) {
                stringBuilder.append("?").append(m16938j(this.f14936l));
            }
        }
        if (this.f14938n != null) {
            stringBuilder.append("#").append(this.f14938n);
        } else if (this.f14937m != null) {
            stringBuilder.append("#").append(m16938j(this.f14937m));
        }
        return stringBuilder.toString();
    }

    public URI m16941a() {
        return new URI(m16940m());
    }

    public C2987j m16942a(int i) {
        if (i < 0) {
            i = -1;
        }
        this.f14931g = i;
        this.f14926b = null;
        this.f14927c = null;
        return this;
    }

    public C2987j m16943a(String str) {
        this.f14925a = str;
        return this;
    }

    public C2987j m16944a(String str, String str2) {
        return m16948b(str + ':' + str2);
    }

    public C2987j m16945a(List<NameValuePair> list) {
        if (this.f14935k == null) {
            this.f14935k = new ArrayList();
        } else {
            this.f14935k.clear();
        }
        this.f14935k.addAll(list);
        this.f14934j = null;
        this.f14926b = null;
        this.f14936l = null;
        return this;
    }

    public C2987j m16946a(NameValuePair... nameValuePairArr) {
        if (this.f14935k == null) {
            this.f14935k = new ArrayList();
        } else {
            this.f14935k.clear();
        }
        for (Object add : nameValuePairArr) {
            this.f14935k.add(add);
        }
        this.f14934j = null;
        this.f14926b = null;
        this.f14936l = null;
        return this;
    }

    public C2987j m16947b() {
        this.f14935k = null;
        this.f14936l = null;
        this.f14934j = null;
        this.f14926b = null;
        return this;
    }

    public C2987j m16948b(String str) {
        this.f14928d = str;
        this.f14926b = null;
        this.f14927c = null;
        this.f14929e = null;
        return this;
    }

    public C2987j m16949b(String str, String str2) {
        if (this.f14935k == null) {
            this.f14935k = new ArrayList();
        }
        this.f14935k.add(new BasicNameValuePair(str, str2));
        this.f14934j = null;
        this.f14926b = null;
        this.f14936l = null;
        return this;
    }

    public C2987j m16950b(List<NameValuePair> list) {
        if (this.f14935k == null) {
            this.f14935k = new ArrayList();
        }
        this.f14935k.addAll(list);
        this.f14934j = null;
        this.f14926b = null;
        this.f14936l = null;
        return this;
    }

    public C2987j m16951c() {
        this.f14935k = null;
        this.f14934j = null;
        this.f14926b = null;
        return this;
    }

    public C2987j m16952c(String str) {
        this.f14930f = str;
        this.f14926b = null;
        this.f14927c = null;
        return this;
    }

    public C2987j m16953c(String str, String str2) {
        if (this.f14935k == null) {
            this.f14935k = new ArrayList();
        }
        if (!this.f14935k.isEmpty()) {
            Iterator it = this.f14935k.iterator();
            while (it.hasNext()) {
                if (((NameValuePair) it.next()).getName().equals(str)) {
                    it.remove();
                }
            }
        }
        this.f14935k.add(new BasicNameValuePair(str, str2));
        this.f14934j = null;
        this.f14926b = null;
        this.f14936l = null;
        return this;
    }

    public C2987j m16954d(String str) {
        this.f14932h = str;
        this.f14926b = null;
        this.f14933i = null;
        return this;
    }

    public boolean m16955d() {
        return this.f14925a != null;
    }

    @Deprecated
    public C2987j m16956e(String str) {
        this.f14935k = m16933a(str, C2922b.f14781e);
        this.f14936l = null;
        this.f14934j = null;
        this.f14926b = null;
        return this;
    }

    public boolean m16957e() {
        return this.f14932h == null;
    }

    public String m16958f() {
        return this.f14925a;
    }

    public C2987j m16959f(String str) {
        this.f14936l = str;
        this.f14934j = null;
        this.f14926b = null;
        this.f14935k = null;
        return this;
    }

    public String m16960g() {
        return this.f14928d;
    }

    public C2987j m16961g(String str) {
        this.f14937m = str;
        this.f14938n = null;
        return this;
    }

    public String m16962h() {
        return this.f14930f;
    }

    public int m16963i() {
        return this.f14931g;
    }

    public String m16964j() {
        return this.f14932h;
    }

    public List<NameValuePair> m16965k() {
        return this.f14935k != null ? new ArrayList(this.f14935k) : new ArrayList();
    }

    public String m16966l() {
        return this.f14937m;
    }

    public String toString() {
        return m16940m();
    }
}
