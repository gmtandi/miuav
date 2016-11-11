package org.p122a.p123a.p124f.p125c;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p124f.p164b.C3015b;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3239f;

@C2914d
/* renamed from: org.a.a.f.c.h */
public class C3026h implements C3015b {
    public static final String f15062a = "TLS";
    public static final String f15063b = "SSL";
    public static final String f15064c = "SSLv2";
    public static final X509HostnameVerifier f15065d;
    public static final X509HostnameVerifier f15066e;
    public static final X509HostnameVerifier f15067f;
    private static final String f15068g = "HttpClient";
    private final SSLSocketFactory f15069h;
    private final X509HostnameVerifier f15070i;
    private final String[] f15071j;
    private final String[] f15072k;

    static {
        f15065d = new C3019b();
        f15066e = new C3020c();
        f15067f = new C3032n();
    }

    public C3026h(SSLContext sSLContext) {
        this(sSLContext, f15066e);
    }

    public C3026h(SSLContext sSLContext, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) C3234a.m17886a((Object) sSLContext, "SSL context")).getSocketFactory(), null, null, x509HostnameVerifier);
    }

    public C3026h(SSLContext sSLContext, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) C3234a.m17886a((Object) sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, x509HostnameVerifier);
    }

    public C3026h(SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, null, null, x509HostnameVerifier);
    }

    public C3026h(SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this.f15069h = (SSLSocketFactory) C3234a.m17886a((Object) sSLSocketFactory, "SSL socket factory");
        this.f15071j = strArr;
        this.f15072k = strArr2;
        if (x509HostnameVerifier == null) {
            x509HostnameVerifier = f15066e;
        }
        this.f15070i = x509HostnameVerifier;
    }

    public static C3026h m17102a() {
        return new C3026h((SSLSocketFactory) SSLSocketFactory.getDefault(), f15066e);
    }

    private void m17103a(SSLSocket sSLSocket, String str) {
        try {
            if (Log.isLoggable(f15068g, 3)) {
                try {
                    List arrayList;
                    SSLSession session = sSLSocket.getSession();
                    Log.d(f15068g, "Secure session established");
                    Log.d(f15068g, " negotiated protocol: " + session.getProtocol());
                    Log.d(f15068g, " negotiated cipher suite: " + session.getCipherSuite());
                    X509Certificate x509Certificate = (X509Certificate) session.getPeerCertificates()[0];
                    Log.d(f15068g, " peer principal: " + x509Certificate.getSubjectX500Principal().toString());
                    Collection<List> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                    if (subjectAlternativeNames != null) {
                        List arrayList2 = new ArrayList();
                        for (List arrayList3 : subjectAlternativeNames) {
                            if (!arrayList3.isEmpty()) {
                                arrayList2.add((String) arrayList3.get(1));
                            }
                        }
                        Log.d(f15068g, " peer alternative names: " + arrayList2);
                    }
                    Log.d(f15068g, " issuer principal: " + x509Certificate.getIssuerX500Principal().toString());
                    Collection<List> issuerAlternativeNames = x509Certificate.getIssuerAlternativeNames();
                    if (issuerAlternativeNames != null) {
                        arrayList3 = new ArrayList();
                        for (List list : issuerAlternativeNames) {
                            if (!list.isEmpty()) {
                                arrayList3.add((String) list.get(1));
                            }
                        }
                        Log.d(f15068g, " issuer alternative names: " + arrayList3);
                    }
                } catch (Exception e) {
                }
            }
            this.f15070i.verify(str, sSLSocket);
        } catch (IOException e2) {
            try {
                sSLSocket.close();
            } catch (Exception e3) {
            }
            throw e2;
        }
    }

    private static String[] m17104a(String str) {
        return C3239f.m17911b(str) ? null : str.split(" *, *");
    }

    public static C3026h m17105b() {
        return new C3026h((SSLSocketFactory) SSLSocketFactory.getDefault(), C3026h.m17104a(System.getProperty("https.protocols")), C3026h.m17104a(System.getProperty("https.cipherSuites")), f15066e);
    }

    public Socket m17106a(int i, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        C3234a.m17886a((Object) httpHost, "HTTP host");
        C3234a.m17886a((Object) inetSocketAddress, "Remote address");
        Socket a = socket != null ? socket : m17108a(httpContext);
        if (inetSocketAddress2 != null) {
            a.bind(inetSocketAddress2);
        }
        if (i > 0) {
            try {
                if (a.getSoTimeout() == 0) {
                    a.setSoTimeout(i);
                }
            } catch (IOException e) {
                try {
                    a.close();
                } catch (IOException e2) {
                }
                throw e;
            }
        }
        if (Log.isLoggable(f15068g, 3)) {
            Log.d(f15068g, "Connecting socket to " + inetSocketAddress + " with timeout " + i);
        }
        a.connect(inetSocketAddress, i);
        if (!(a instanceof SSLSocket)) {
            return m17107a(a, httpHost.getHostName(), inetSocketAddress.getPort(), httpContext);
        }
        SSLSocket sSLSocket = (SSLSocket) a;
        if (Log.isLoggable(f15068g, 3)) {
            Log.d(f15068g, "Starting handshake");
        }
        sSLSocket.startHandshake();
        m17103a(sSLSocket, httpHost.getHostName());
        return a;
    }

    @TargetApi(17)
    public Socket m17107a(Socket socket, String str, int i, HttpContext httpContext) {
        SSLSocket sSLSocket = (SSLSocket) this.f15069h.createSocket(socket, str, i, true);
        if (this.f15071j != null) {
            sSLSocket.setEnabledProtocols(this.f15071j);
        } else {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            List arrayList = new ArrayList(enabledProtocols.length);
            for (String str2 : enabledProtocols) {
                if (!str2.startsWith(f15063b)) {
                    arrayList.add(str2);
                }
            }
            if (!arrayList.isEmpty()) {
                sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
        if (this.f15072k != null) {
            sSLSocket.setEnabledCipherSuites(this.f15072k);
        }
        if (Log.isLoggable(f15068g, 3)) {
            Log.d(f15068g, "Enabled protocols: " + Arrays.asList(sSLSocket.getEnabledProtocols()));
            Log.d(f15068g, "Enabled cipher suites:" + Arrays.asList(sSLSocket.getEnabledCipherSuites()));
        }
        m17109a(sSLSocket);
        if (VERSION.SDK_INT >= 17) {
            if (Log.isLoggable(f15068g, 3)) {
                Log.d(f15068g, "Enabling SNI for " + str);
            }
            try {
                sSLSocket.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(sSLSocket, new Object[]{str});
            } catch (Throwable e) {
                if (Log.isLoggable(f15068g, 3)) {
                    Log.d(f15068g, "SNI configuration failed", e);
                }
            }
        }
        if (Log.isLoggable(f15068g, 3)) {
            Log.d(f15068g, "Starting handshake");
        }
        sSLSocket.startHandshake();
        m17103a(sSLSocket, str);
        return sSLSocket;
    }

    public Socket m17108a(HttpContext httpContext) {
        return SocketFactory.getDefault().createSocket();
    }

    protected void m17109a(SSLSocket sSLSocket) {
    }

    X509HostnameVerifier m17110c() {
        return this.f15070i;
    }
}
