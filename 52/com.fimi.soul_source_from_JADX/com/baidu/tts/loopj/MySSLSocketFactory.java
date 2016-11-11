package com.baidu.tts.loopj;

import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpVersion;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.p122a.p123a.p124f.p125c.C3026h;

public class MySSLSocketFactory extends SSLSocketFactory {
    SSLContext sslContext;

    /* renamed from: com.baidu.tts.loopj.MySSLSocketFactory.1 */
    class C08481 implements X509TrustManager {
        C08481() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public MySSLSocketFactory(KeyStore keyStore) {
        super(keyStore);
        this.sslContext = SSLContext.getInstance(C3026h.f15062a);
        C08481 c08481 = new C08481();
        this.sslContext.init(null, new TrustManager[]{c08481}, null);
    }

    public static SSLSocketFactory getFixedSocketFactory() {
        try {
            SSLSocketFactory mySSLSocketFactory = new MySSLSocketFactory(getKeystore());
            mySSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return mySSLSocketFactory;
        } catch (Throwable th) {
            th.printStackTrace();
            return SSLSocketFactory.getSocketFactory();
        }
    }

    public static KeyStore getKeystore() {
        KeyStore instance;
        Throwable th;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return instance;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            instance = null;
            th = th4;
            th.printStackTrace();
            return instance;
        }
        return instance;
    }

    public static KeyStore getKeystoreOfCA(InputStream inputStream) {
        Certificate generateCertificate;
        CertificateException e;
        Object obj;
        KeyStore instance;
        Exception exception;
        KeyStore keyStore;
        Exception exception2;
        Throwable th;
        InputStream inputStream2 = null;
        InputStream bufferedInputStream;
        try {
            CertificateFactory instance2 = CertificateFactory.getInstance("X.509");
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                generateCertificate = instance2.generateCertificate(bufferedInputStream);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (CertificateException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            obj = inputStream2;
                        }
                    }
                    obj = inputStream2;
                    instance = KeyStore.getInstance(KeyStore.getDefaultType());
                    try {
                        instance.load(null, null);
                        instance.setCertificateEntry("ca", generateCertificate);
                        return instance;
                    } catch (Exception e5) {
                        exception = e5;
                        keyStore = instance;
                        exception2 = exception;
                        exception2.printStackTrace();
                        return keyStore;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream2 = bufferedInputStream;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (CertificateException e7) {
            e = e7;
            bufferedInputStream = inputStream2;
            e.printStackTrace();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            obj = inputStream2;
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            instance.setCertificateEntry("ca", generateCertificate);
            return instance;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream2 != null) {
                inputStream2.close();
            }
            throw th;
        }
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            instance.setCertificateEntry("ca", generateCertificate);
            return instance;
        } catch (Exception e52) {
            exception = e52;
            keyStore = inputStream2;
            exception2 = exception;
            exception2.printStackTrace();
            return keyStore;
        }
    }

    public static DefaultHttpClient getNewHttpClient(KeyStore keyStore) {
        try {
            SocketFactory mySSLSocketFactory = new MySSLSocketFactory(keyStore);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, mySSLSocketFactory, 443));
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, C1142e.f5201a);
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    public Socket createSocket() {
        return this.sslContext.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
    }

    public void fixHttpsURLConnection() {
        HttpsURLConnection.setDefaultSSLSocketFactory(this.sslContext.getSocketFactory());
    }
}
