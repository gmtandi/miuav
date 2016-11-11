package org.p122a.p123a.p124f.p125c;

import android.util.Log;
import com.xiaomi.market.sdk.C2537j;
import java.net.InetAddress;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.NameValuePair;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.p122a.p123a.p124f.p165d.C3035a;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3239f;

@C2912b
/* renamed from: org.a.a.f.c.a */
public abstract class C3018a implements X509HostnameVerifier {
    private static final String[] f15047a;
    private static final String f15048b = "HttpClient";

    static {
        f15047a = new String[]{"ac", C2537j.ae, "com", "ed", "edu", "go", "gouv", "gov", C2537j.ag, "lg", "ne", "net", "or", "org"};
        Arrays.sort(f15047a);
    }

    @Deprecated
    public static boolean m17074a(String str) {
        String[] split = str.split("\\.");
        return (split.length == 3 && split[2].length() == 2 && Arrays.binarySearch(f15047a, split[1]) >= 0) ? false : true;
    }

    public static String[] m17075a(X509Certificate x509Certificate) {
        try {
            return C3018a.m17078c(x509Certificate.getSubjectX500Principal().toString());
        } catch (SSLException e) {
            return null;
        }
    }

    private static String[] m17076a(X509Certificate x509Certificate, String str) {
        int i = C3018a.m17080e(str) ? 7 : 2;
        LinkedList linkedList = new LinkedList();
        Collection subjectAlternativeNames;
        try {
            subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            subjectAlternativeNames = null;
        }
        if (r0 != null) {
            for (List list : r0) {
                if (((Integer) list.get(0)).intValue() == i) {
                    linkedList.add((String) list.get(1));
                }
            }
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    public static String[] m17077b(X509Certificate x509Certificate) {
        return C3018a.m17076a(x509Certificate, null);
    }

    static String[] m17078c(String str) {
        if (str == null) {
            return null;
        }
        List arrayList = new ArrayList();
        List a = C3021d.f15049a.m17086a(str);
        for (int i = 0; i < a.size(); i++) {
            NameValuePair nameValuePair = (NameValuePair) a.get(i);
            Object name = nameValuePair.getName();
            String value = nameValuePair.getValue();
            if (C3239f.m17911b(name)) {
                throw new SSLException(str + " is not a valid X500 distinguished name");
            }
            if (name.equalsIgnoreCase("cn")) {
                arrayList.add(value);
            }
        }
        return arrayList.isEmpty() ? null : (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static int m17079d(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '.') {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private static boolean m17080e(String str) {
        return str != null && (C3035a.m17130a(str) || C3035a.m17134e(str));
    }

    private String m17081f(String str) {
        if (str != null && C3035a.m17134e(str)) {
            try {
                str = InetAddress.getByName(str).getHostAddress();
            } catch (Throwable e) {
                Log.e(f15048b, "Unexpected error converting " + str, e);
            }
        }
        return str;
    }

    public final void m17082a(String str, String[] strArr, String[] strArr2, boolean z) {
        int i;
        LinkedList linkedList = new LinkedList();
        if (!(strArr == null || strArr.length <= 0 || strArr[0] == null)) {
            linkedList.add(strArr[0]);
        }
        if (strArr2 != null) {
            for (Object obj : strArr2) {
                if (obj != null) {
                    linkedList.add(obj);
                }
            }
        }
        if (linkedList.isEmpty()) {
            throw new SSLException("Certificate for <" + str + "> doesn't contain CN or DNS subjectAlt");
        }
        StringBuilder stringBuilder = new StringBuilder();
        String f = m17081f(str.trim().toLowerCase(Locale.ENGLISH));
        Iterator it = linkedList.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            String toLowerCase = ((String) it.next()).toLowerCase(Locale.ENGLISH);
            stringBuilder.append(" <");
            stringBuilder.append(toLowerCase);
            stringBuilder.append('>');
            if (it.hasNext()) {
                stringBuilder.append(" OR");
            }
            String[] split = toLowerCase.split("\\.");
            i = (split.length < 3 || !split[0].endsWith("*") || !m17083b(toLowerCase) || C3018a.m17080e(str)) ? 0 : 1;
            if (i != 0) {
                String str2 = split[0];
                if (str2.length() > 1) {
                    String substring = str2.substring(0, str2.length() - 1);
                    z2 = f.startsWith(substring) && f.substring(substring.length()).endsWith(toLowerCase.substring(str2.length()));
                } else {
                    z2 = f.endsWith(toLowerCase.substring(1));
                }
                if (z2 && z) {
                    if (C3018a.m17079d(f) == C3018a.m17079d(toLowerCase)) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                }
            } else {
                z2 = f.equals(m17081f(toLowerCase));
                continue;
            }
            if (z2) {
                break;
            }
        }
        if (!z2) {
            throw new SSLException("hostname in certificate didn't match: <" + str + "> !=" + stringBuilder);
        }
    }

    boolean m17083b(String str) {
        String[] split = str.split("\\.");
        return (split.length == 3 && split[2].length() == 2 && Arrays.binarySearch(f15047a, split[1]) >= 0) ? false : true;
    }

    public final void verify(String str, X509Certificate x509Certificate) {
        verify(str, C3018a.m17075a(x509Certificate), C3018a.m17076a(x509Certificate, str));
    }

    public final void verify(String str, SSLSocket sSLSocket) {
        if (str == null) {
            throw new NullPointerException("host to verify is null");
        }
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            sSLSocket.getInputStream().available();
            session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.startHandshake();
                session = sSLSocket.getSession();
            }
        }
        verify(str, (X509Certificate) session.getPeerCertificates()[0]);
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            verify(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            return false;
        }
    }
}
