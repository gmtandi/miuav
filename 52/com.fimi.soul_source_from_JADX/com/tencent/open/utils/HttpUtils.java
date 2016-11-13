package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.p128a.C2186a;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Util.Statistic;
import com.tencent.tauth.IRequestListener;
import com.xiaomi.mipush.sdk.MiPushClient;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.InvalidPropertiesFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

public class HttpUtils {
    private static final String f12094a;

    /* renamed from: com.tencent.open.utils.HttpUtils.1 */
    final class C23601 extends Thread {
        final /* synthetic */ QQToken f12086a;
        final /* synthetic */ Context f12087b;
        final /* synthetic */ String f12088c;
        final /* synthetic */ Bundle f12089d;
        final /* synthetic */ String f12090e;
        final /* synthetic */ IRequestListener f12091f;

        C23601(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
            this.f12086a = qQToken;
            this.f12087b = context;
            this.f12088c = str;
            this.f12089d = bundle;
            this.f12090e = str2;
            this.f12091f = iRequestListener;
        }

        public void run() {
            try {
                JSONObject request = HttpUtils.request(this.f12086a, this.f12087b, this.f12088c, this.f12089d, this.f12090e);
                if (this.f12091f != null) {
                    this.f12091f.onComplete(request);
                    C2333f.m13754b(C2333f.f12014d, "OpenApi onComplete");
                }
            } catch (Throwable e) {
                if (this.f12091f != null) {
                    this.f12091f.onMalformedURLException(e);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync MalformedURLException", e);
                }
            } catch (Throwable e2) {
                if (this.f12091f != null) {
                    this.f12091f.onConnectTimeoutException(e2);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync onConnectTimeoutException", e2);
                }
            } catch (Throwable e22) {
                if (this.f12091f != null) {
                    this.f12091f.onSocketTimeoutException(e22);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync onSocketTimeoutException", e22);
                }
            } catch (Throwable e222) {
                if (this.f12091f != null) {
                    this.f12091f.onNetworkUnavailableException(e222);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync onNetworkUnavailableException", e222);
                }
            } catch (Throwable e2222) {
                if (this.f12091f != null) {
                    this.f12091f.onHttpStatusException(e2222);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync onHttpStatusException", e2222);
                }
            } catch (Throwable e22222) {
                if (this.f12091f != null) {
                    this.f12091f.onIOException(e22222);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync IOException", e22222);
                }
            } catch (Throwable e222222) {
                if (this.f12091f != null) {
                    this.f12091f.onJSONException(e222222);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync JSONException", e222222);
                }
            } catch (Throwable e2222222) {
                if (this.f12091f != null) {
                    this.f12091f.onUnknowException(e2222222);
                    C2333f.m13755b(C2333f.f12014d, "OpenApi requestAsync onUnknowException", e2222222);
                }
            }
        }
    }

    public class CustomSSLSocketFactory extends SSLSocketFactory {
        private final SSLContext f12092a;

        public CustomSSLSocketFactory(KeyStore keyStore) {
            MyX509TrustManager myX509TrustManager;
            super(keyStore);
            this.f12092a = SSLContext.getInstance(C3026h.f15062a);
            try {
                myX509TrustManager = new MyX509TrustManager();
            } catch (Exception e) {
                myX509TrustManager = null;
            }
            this.f12092a.init(null, new TrustManager[]{myX509TrustManager}, null);
        }

        public Socket createSocket() {
            return this.f12092a.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) {
            return this.f12092a.getSocketFactory().createSocket(socket, str, i, z);
        }
    }

    public class HttpStatusException extends Exception {
        public static final String ERROR_INFO = "http status code error:";

        public HttpStatusException(String str) {
            super(str);
        }
    }

    public class MyX509TrustManager implements X509TrustManager {
        X509TrustManager f12093a;

        MyX509TrustManager() {
            KeyStore instance;
            Throwable th;
            FileInputStream fileInputStream;
            try {
                instance = KeyStore.getInstance("JKS");
            } catch (Exception e) {
                instance = null;
            }
            TrustManager[] trustManagerArr = new TrustManager[0];
            if (instance != null) {
                try {
                    InputStream fileInputStream2 = new FileInputStream("trustedCerts");
                    try {
                        instance.load(fileInputStream2, "passphrase".toCharArray());
                        TrustManagerFactory instance2 = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
                        instance2.init(instance);
                        TrustManager[] trustManagers = instance2.getTrustManagers();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        InputStream inputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    fileInputStream = null;
                    th = th4;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init((KeyStore) null);
            trustManagers = instance3.getTrustManagers();
            for (int i = 0; i < trustManagers.length; i++) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    this.f12093a = (X509TrustManager) trustManagers[i];
                    return;
                }
            }
            throw new Exception("Couldn't initialize");
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            try {
                this.f12093a.checkClientTrusted(x509CertificateArr, str);
            } catch (CertificateException e) {
            }
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            try {
                this.f12093a.checkServerTrusted(x509CertificateArr, str);
            } catch (CertificateException e) {
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.f12093a.getAcceptedIssuers();
        }
    }

    public class NetworkProxy {
        public final String host;
        public final int port;

        private NetworkProxy(String str, int i) {
            this.host = str;
            this.port = i;
        }
    }

    public class NetworkUnavailableException extends Exception {
        public static final String ERROR_INFO = "network unavailable";

        public NetworkUnavailableException(String str) {
            super(str);
        }
    }

    static {
        f12094a = HttpUtils.class.getName();
    }

    private HttpUtils() {
    }

    private static int m13831a(Context context) {
        int i = -1;
        if (VERSION.SDK_INT >= 11) {
            Object property = System.getProperty("http.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return i;
            }
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
                return i;
            }
        } else if (context == null) {
            return Proxy.getDefaultPort();
        } else {
            i = Proxy.getPort(context);
            return i < 0 ? Proxy.getDefaultPort() : i;
        }
    }

    private static String m13832a(HttpResponse httpResponse) {
        String str = C2915a.f14760f;
        InputStream content = httpResponse.getEntity().getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Header firstHeader = httpResponse.getFirstHeader(C3004e.f15025k);
        InputStream gZIPInputStream = (firstHeader == null || firstHeader.getValue().toLowerCase().indexOf(AsyncHttpClient.ENCODING_GZIP) <= -1) ? content : new GZIPInputStream(content);
        byte[] bArr = new byte[Opcodes.ACC_INTERFACE];
        while (true) {
            int read = gZIPInputStream.read(bArr);
            if (read == -1) {
                return new String(byteArrayOutputStream.toByteArray(), C1142e.f5201a);
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private static void m13833a(Context context, QQToken qQToken, String str) {
        if (str.indexOf("add_share") > -1 || str.indexOf("upload_pic") > -1 || str.indexOf("add_topic") > -1 || str.indexOf("set_user_face") > -1 || str.indexOf("add_t") > -1 || str.indexOf("add_pic_t") > -1 || str.indexOf("add_pic_url") > -1 || str.indexOf("add_video") > -1) {
            C2186a.m13215a(context, qQToken, "requireApi", str);
        }
    }

    private static String m13834b(Context context) {
        if (VERSION.SDK_INT >= 11) {
            return System.getProperty("http.proxyHost");
        }
        if (context == null) {
            return Proxy.getDefaultHost();
        }
        Object host = Proxy.getHost(context);
        return TextUtils.isEmpty(host) ? Proxy.getDefaultHost() : host;
    }

    public static String encodePostBody(Bundle bundle, String str) {
        if (bundle == null) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = bundle.size();
        int i = -1;
        for (String str2 : bundle.keySet()) {
            int i2 = i + 1;
            Object obj = bundle.get(str2);
            if (obj instanceof String) {
                stringBuilder.append("Content-Disposition: form-data; name=\"" + str2 + "\"" + "\r\n" + "\r\n" + ((String) obj));
                if (i2 < size - 1) {
                    stringBuilder.append("\r\n--" + str + "\r\n");
                }
                i = i2;
            } else {
                i = i2;
            }
        }
        return stringBuilder.toString();
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            if ((obj2 instanceof String) || (obj2 instanceof String[])) {
                Object obj3;
                if (obj2 instanceof String[]) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=");
                    String[] stringArray = bundle.getStringArray(str);
                    if (stringArray != null) {
                        for (int i = 0; i < stringArray.length; i++) {
                            if (i == 0) {
                                stringBuilder.append(URLEncoder.encode(stringArray[i]));
                            } else {
                                stringBuilder.append(URLEncoder.encode(MiPushClient.ACCEPT_TIME_SEPARATOR + stringArray[i]));
                            }
                        }
                        obj3 = obj;
                    }
                } else {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(bundle.getString(str)));
                    obj3 = obj;
                }
                obj = obj3;
            }
        }
        return stringBuilder.toString();
    }

    public static int getErrorCodeFromException(IOException iOException) {
        return iOException instanceof CharConversionException ? -20 : iOException instanceof MalformedInputException ? -21 : iOException instanceof UnmappableCharacterException ? -22 : iOException instanceof HttpResponseException ? -23 : iOException instanceof ClosedChannelException ? -24 : iOException instanceof ConnectionClosedException ? -25 : iOException instanceof EOFException ? -26 : iOException instanceof FileLockInterruptionException ? -27 : iOException instanceof FileNotFoundException ? -28 : iOException instanceof HttpRetryException ? -29 : iOException instanceof ConnectTimeoutException ? -7 : iOException instanceof SocketTimeoutException ? -8 : iOException instanceof InvalidPropertiesFormatException ? -30 : iOException instanceof MalformedChunkCodingException ? -31 : iOException instanceof MalformedURLException ? -3 : iOException instanceof NoHttpResponseException ? -32 : iOException instanceof InvalidClassException ? -33 : iOException instanceof InvalidObjectException ? -34 : iOException instanceof NotActiveException ? -35 : iOException instanceof NotSerializableException ? -36 : iOException instanceof OptionalDataException ? -37 : iOException instanceof StreamCorruptedException ? -38 : iOException instanceof WriteAbortedException ? -39 : iOException instanceof ProtocolException ? -40 : iOException instanceof SSLHandshakeException ? -41 : iOException instanceof SSLKeyException ? -42 : iOException instanceof SSLPeerUnverifiedException ? -43 : iOException instanceof SSLProtocolException ? -44 : iOException instanceof BindException ? -45 : iOException instanceof ConnectException ? -46 : iOException instanceof NoRouteToHostException ? -47 : iOException instanceof PortUnreachableException ? -48 : iOException instanceof SyncFailedException ? -49 : iOException instanceof UTFDataFormatException ? -50 : iOException instanceof UnknownHostException ? -51 : iOException instanceof UnknownServiceException ? -52 : iOException instanceof UnsupportedEncodingException ? -53 : iOException instanceof ZipException ? -54 : -2;
    }

    public static HttpClient getHttpClient(Context context, String str, String str2) {
        int i;
        int i2 = 0;
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        if (VERSION.SDK_INT < 16) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                SocketFactory customSSLSocketFactory = new CustomSSLSocketFactory(instance);
                customSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, customSSLSocketFactory, 443));
            } catch (Exception e) {
                schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, SSLSocketFactory.getSocketFactory(), 443));
            }
        } else {
            schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, SSLSocketFactory.getSocketFactory(), 443));
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        OpenConfig instance2 = context != null ? OpenConfig.getInstance(context, str) : null;
        if (instance2 != null) {
            i = instance2.getInt("Common_HttpConnectionTimeout");
            i2 = instance2.getInt("Common_SocketConnectionTimeout");
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 15000;
        }
        if (i2 == 0) {
            i2 = 30000;
        }
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, i);
        HttpConnectionParams.setSoTimeout(basicHttpParams, i2);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, C1142e.f5201a);
        HttpProtocolParams.setUserAgent(basicHttpParams, "AndroidSDK_" + VERSION.SDK + "_" + Build.DEVICE + "_" + VERSION.RELEASE);
        HttpClient defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        NetworkProxy proxy = getProxy(context);
        if (proxy != null) {
            defaultHttpClient.getParams().setParameter("http.route.default-proxy", new HttpHost(proxy.host, proxy.port));
        }
        return defaultHttpClient;
    }

    public static NetworkProxy getProxy(Context context) {
        if (context == null) {
            return null;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 0) {
            Object b = m13834b(context);
            int a = m13831a(context);
            if (!TextUtils.isEmpty(b) && a >= 0) {
                return new NetworkProxy(a, null);
            }
        }
        return null;
    }

    public static Statistic openUrl2(Context context, String str, String str2, Bundle bundle) {
        HttpUriRequest httpUriRequest;
        int i;
        int size;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                    throw new NetworkUnavailableException(NetworkUnavailableException.ERROR_INFO);
                }
            }
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        String str3 = C2915a.f14760f;
        str3 = bundle2.getString("appid_for_getting_config");
        bundle2.remove("appid_for_getting_config");
        HttpClient httpClient = getHttpClient(context, str3, str);
        int length;
        if (str2.equals(C2951i.f14860a)) {
            String encodeUrl = encodeUrl(bundle2);
            length = 0 + encodeUrl.length();
            C2333f.m13754b(f12094a, "-->openUrl2 before url =" + str);
            str3 = str.indexOf("?") == -1 ? str + "?" : str + "&";
            C2333f.m13754b(f12094a, "-->openUrl2 encodedParam =" + encodeUrl + " -- url = " + str3);
            HttpUriRequest httpGet = new HttpGet(str3 + encodeUrl);
            httpGet.addHeader(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
            int i2 = length;
            httpUriRequest = httpGet;
            i = i2;
        } else if (str2.equals(C2955m.f14864a)) {
            Object obj;
            HttpPost httpPost = new HttpPost(str);
            httpPost.addHeader(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
            Bundle bundle3 = new Bundle();
            for (String str32 : bundle2.keySet()) {
                obj = bundle2.get(str32);
                if (obj instanceof byte[]) {
                    bundle3.putByteArray(str32, (byte[]) obj);
                }
            }
            if (!bundle2.containsKey("method")) {
                bundle2.putString("method", str2);
            }
            httpPost.setHeader(C3004e.f15031q, "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            httpPost.setHeader(C3004e.f15024j, "Keep-Alive");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(Util.getBytesUTF8("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
            byteArrayOutputStream.write(Util.getBytesUTF8(encodePostBody(bundle2, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
            if (!bundle3.isEmpty()) {
                size = bundle3.size();
                byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                length = -1;
                for (String str322 : bundle3.keySet()) {
                    length++;
                    byteArrayOutputStream.write(Util.getBytesUTF8("Content-Disposition: form-data; name=\"" + str322 + "\"; filename=\"" + str322 + "\"" + "\r\n"));
                    byteArrayOutputStream.write(Util.getBytesUTF8("Content-Type: content/unknown\r\n\r\n"));
                    byte[] byteArray = bundle3.getByteArray(str322);
                    if (byteArray != null) {
                        byteArrayOutputStream.write(byteArray);
                    }
                    if (length < size - 1) {
                        byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
                    }
                }
            }
            byteArrayOutputStream.write(Util.getBytesUTF8("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            i = toByteArray.length + 0;
            byteArrayOutputStream.close();
            httpPost.setEntity(new ByteArrayEntity(toByteArray));
            obj = httpPost;
        } else {
            httpUriRequest = null;
            i = 0;
        }
        HttpResponse execute = httpClient.execute(httpUriRequest);
        size = execute.getStatusLine().getStatusCode();
        if (size == C2799f.f14282t) {
            return new Statistic(m13832a(execute), i);
        }
        throw new HttpStatusException(HttpStatusException.ERROR_INFO + size);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject request(com.tencent.connect.auth.QQToken r20, android.content.Context r21, java.lang.String r22, android.os.Bundle r23, java.lang.String r24) {
        /*
        r4 = com.tencent.open.p133a.C2333f.f12014d;
        r5 = "OpenApi request";
        com.tencent.open.p133a.C2333f.m13751a(r4, r5);
        r4 = r22.toLowerCase();
        r5 = "http";
        r4 = r4.startsWith(r5);
        if (r4 != 0) goto L_0x01c1;
    L_0x0013:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = com.tencent.open.utils.ServerSetting.getInstance();
        r6 = "https://openmobile.qq.com/";
        r0 = r21;
        r5 = r5.getEnvUrl(r0, r6);
        r4 = r4.append(r5);
        r0 = r22;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = com.tencent.open.utils.ServerSetting.getInstance();
        r7 = "https://openmobile.qq.com/";
        r0 = r21;
        r6 = r6.getEnvUrl(r0, r7);
        r5 = r5.append(r6);
        r0 = r22;
        r5 = r5.append(r0);
        r5 = r5.toString();
    L_0x0051:
        r0 = r21;
        r1 = r20;
        r2 = r22;
        m13833a(r0, r1, r2);
        r10 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r7 = 0;
        r6 = r20.getAppId();
        r0 = r21;
        r6 = com.tencent.open.utils.OpenConfig.getInstance(r0, r6);
        r11 = "Common_HttpRetryCount";
        r6 = r6.getInt(r11);
        r11 = "OpenConfig_test";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "config 1:Common_HttpRetryCount            config_value:";
        r12 = r12.append(r13);
        r12 = r12.append(r6);
        r13 = "   appid:";
        r12 = r12.append(r13);
        r13 = r20.getAppId();
        r12 = r12.append(r13);
        r13 = "     url:";
        r12 = r12.append(r13);
        r12 = r12.append(r5);
        r12 = r12.toString();
        com.tencent.open.p133a.C2333f.m13754b(r11, r12);
        if (r6 != 0) goto L_0x00ff;
    L_0x00a2:
        r6 = 3;
        r13 = r6;
    L_0x00a4:
        r6 = "OpenConfig_test";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "config 1:Common_HttpRetryCount            result_value:";
        r11 = r11.append(r12);
        r11 = r11.append(r13);
        r12 = "   appid:";
        r11 = r11.append(r12);
        r12 = r20.getAppId();
        r11 = r11.append(r12);
        r12 = "     url:";
        r11 = r11.append(r12);
        r11 = r11.append(r5);
        r11 = r11.toString();
        com.tencent.open.p133a.C2333f.m13754b(r6, r11);
        r18 = r7;
        r6 = r8;
        r8 = r18;
        r9 = r10;
    L_0x00da:
        r14 = r8 + 1;
        r0 = r21;
        r1 = r24;
        r2 = r23;
        r10 = openUrl2(r0, r4, r1, r2);	 Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
        r8 = r10.response;	 Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
        r15 = com.tencent.open.utils.Util.parseJson(r8);	 Catch:{ ConnectTimeoutException -> 0x01b7, SocketTimeoutException -> 0x01b1, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
        r8 = "ret";
        r12 = r15.getInt(r8);	 Catch:{ JSONException -> 0x0101, ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a }
    L_0x00f2:
        r8 = r10.reqSize;	 Catch:{ ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
        r10 = r10.rspSize;	 Catch:{ ConnectTimeoutException -> 0x0104, SocketTimeoutException -> 0x012a, HttpStatusException -> 0x014b, NetworkUnavailableException -> 0x0173, MalformedURLException -> 0x0178, IOException -> 0x018a, JSONException -> 0x019f }
        r13 = r15;
    L_0x00f7:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        return r13;
    L_0x00ff:
        r13 = r6;
        goto L_0x00a4;
    L_0x0101:
        r8 = move-exception;
        r12 = -4;
        goto L_0x00f2;
    L_0x0104:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x0108:
        r15.printStackTrace();
        r12 = -7;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x0122;
    L_0x0112:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
    L_0x011c:
        if (r14 < r13) goto L_0x01bd;
    L_0x011e:
        r13 = r8;
        r8 = r16;
        goto L_0x00f7;
    L_0x0122:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x012a:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x012e:
        r15.printStackTrace();
        r12 = -8;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x0143;
    L_0x0138:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
        goto L_0x011c;
    L_0x0143:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x014b:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r4 = r13.getMessage();
        r8 = "http status code error:";
        r9 = "";
        r4 = r4.replace(r8, r9);	 Catch:{ Exception -> 0x016c }
        r12 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x016c }
    L_0x0160:
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x016c:
        r4 = move-exception;
        r4.printStackTrace();
        r12 = -9;
        goto L_0x0160;
    L_0x0173:
        r4 = move-exception;
        r4.printStackTrace();
        throw r4;
    L_0x0178:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -3;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x018a:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = getErrorCodeFromException(r13);
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x019f:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -4;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x01b1:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x012e;
    L_0x01b7:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x0108;
    L_0x01bd:
        r9 = r8;
        r8 = r14;
        goto L_0x00da;
    L_0x01c1:
        r5 = r22;
        r4 = r22;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.request(com.tencent.connect.auth.QQToken, android.content.Context, java.lang.String, android.os.Bundle, java.lang.String):org.json.JSONObject");
    }

    public static void requestAsync(QQToken qQToken, Context context, String str, Bundle bundle, String str2, IRequestListener iRequestListener) {
        C2333f.m13751a(C2333f.f12014d, "OpenApi requestAsync");
        new C23601(qQToken, context, str, bundle, str2, iRequestListener).start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject upload(com.tencent.connect.auth.QQToken r20, android.content.Context r21, java.lang.String r22, android.os.Bundle r23) {
        /*
        r4 = r22.toLowerCase();
        r5 = "http";
        r4 = r4.startsWith(r5);
        if (r4 != 0) goto L_0x01b8;
    L_0x000c:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = com.tencent.open.utils.ServerSetting.getInstance();
        r6 = "https://openmobile.qq.com/";
        r0 = r21;
        r5 = r5.getEnvUrl(r0, r6);
        r4 = r4.append(r5);
        r0 = r22;
        r4 = r4.append(r0);
        r4 = r4.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = com.tencent.open.utils.ServerSetting.getInstance();
        r7 = "https://openmobile.qq.com/";
        r0 = r21;
        r6 = r6.getEnvUrl(r0, r7);
        r5 = r5.append(r6);
        r0 = r22;
        r5 = r5.append(r0);
        r5 = r5.toString();
    L_0x004a:
        r0 = r21;
        r1 = r20;
        r2 = r22;
        m13833a(r0, r1, r2);
        r10 = 0;
        r8 = android.os.SystemClock.elapsedRealtime();
        r7 = 0;
        r6 = r20.getAppId();
        r0 = r21;
        r6 = com.tencent.open.utils.OpenConfig.getInstance(r0, r6);
        r11 = "Common_HttpRetryCount";
        r6 = r6.getInt(r11);
        r11 = "OpenConfig_test";
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "config 1:Common_HttpRetryCount            config_value:";
        r12 = r12.append(r13);
        r12 = r12.append(r6);
        r13 = "   appid:";
        r12 = r12.append(r13);
        r13 = r20.getAppId();
        r12 = r12.append(r13);
        r13 = "     url:";
        r12 = r12.append(r13);
        r12 = r12.append(r5);
        r12 = r12.toString();
        com.tencent.open.p133a.C2333f.m13754b(r11, r12);
        if (r6 != 0) goto L_0x00f6;
    L_0x009b:
        r6 = 3;
        r13 = r6;
    L_0x009d:
        r6 = "OpenConfig_test";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "config 1:Common_HttpRetryCount            result_value:";
        r11 = r11.append(r12);
        r11 = r11.append(r13);
        r12 = "   appid:";
        r11 = r11.append(r12);
        r12 = r20.getAppId();
        r11 = r11.append(r12);
        r12 = "     url:";
        r11 = r11.append(r12);
        r11 = r11.append(r5);
        r11 = r11.toString();
        com.tencent.open.p133a.C2333f.m13754b(r6, r11);
        r18 = r7;
        r6 = r8;
        r8 = r18;
        r9 = r10;
    L_0x00d3:
        r14 = r8 + 1;
        r0 = r21;
        r1 = r23;
        r10 = com.tencent.open.utils.Util.upload(r0, r4, r1);	 Catch:{ ConnectTimeoutException -> 0x01ae, SocketTimeoutException -> 0x01a8, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181, JSONException -> 0x0196 }
        r8 = r10.response;	 Catch:{ ConnectTimeoutException -> 0x01ae, SocketTimeoutException -> 0x01a8, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181, JSONException -> 0x0196 }
        r15 = com.tencent.open.utils.Util.parseJson(r8);	 Catch:{ ConnectTimeoutException -> 0x01ae, SocketTimeoutException -> 0x01a8, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181, JSONException -> 0x0196 }
        r8 = "ret";
        r12 = r15.getInt(r8);	 Catch:{ JSONException -> 0x00f8, ConnectTimeoutException -> 0x00fb, SocketTimeoutException -> 0x0121, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181 }
    L_0x00e9:
        r8 = r10.reqSize;	 Catch:{ ConnectTimeoutException -> 0x00fb, SocketTimeoutException -> 0x0121, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181, JSONException -> 0x0196 }
        r10 = r10.rspSize;	 Catch:{ ConnectTimeoutException -> 0x00fb, SocketTimeoutException -> 0x0121, HttpStatusException -> 0x0142, NetworkUnavailableException -> 0x016a, MalformedURLException -> 0x016f, IOException -> 0x0181, JSONException -> 0x0196 }
        r13 = r15;
    L_0x00ee:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        return r13;
    L_0x00f6:
        r13 = r6;
        goto L_0x009d;
    L_0x00f8:
        r8 = move-exception;
        r12 = -4;
        goto L_0x00e9;
    L_0x00fb:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x00ff:
        r15.printStackTrace();
        r12 = -7;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x0119;
    L_0x0109:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
    L_0x0113:
        if (r14 < r13) goto L_0x01b4;
    L_0x0115:
        r13 = r8;
        r8 = r16;
        goto L_0x00ee;
    L_0x0119:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x0121:
        r8 = move-exception;
        r16 = r15;
        r15 = r8;
    L_0x0125:
        r15.printStackTrace();
        r12 = -8;
        r8 = 0;
        r10 = 0;
        if (r14 >= r13) goto L_0x013a;
    L_0x012f:
        r6 = android.os.SystemClock.elapsedRealtime();
        r18 = r8;
        r8 = r16;
        r16 = r18;
        goto L_0x0113;
    L_0x013a:
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r15;
    L_0x0142:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r4 = r13.getMessage();
        r8 = "http status code error:";
        r9 = "";
        r4 = r4.replace(r8, r9);	 Catch:{ Exception -> 0x0163 }
        r12 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x0163 }
    L_0x0157:
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x0163:
        r4 = move-exception;
        r4.printStackTrace();
        r12 = -9;
        goto L_0x0157;
    L_0x016a:
        r4 = move-exception;
        r4.printStackTrace();
        throw r4;
    L_0x016f:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -3;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x0181:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = getErrorCodeFromException(r13);
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x0196:
        r4 = move-exception;
        r13 = r4;
        r13.printStackTrace();
        r12 = -4;
        r8 = 0;
        r10 = 0;
        r4 = com.tencent.open.p134b.C2350g.m13795a();
        r4.m13798a(r5, r6, r8, r10, r12);
        throw r13;
    L_0x01a8:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x0125;
    L_0x01ae:
        r8 = move-exception;
        r15 = r8;
        r16 = r9;
        goto L_0x00ff;
    L_0x01b4:
        r9 = r8;
        r8 = r14;
        goto L_0x00d3;
    L_0x01b8:
        r5 = r22;
        r4 = r22;
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.HttpUtils.upload(com.tencent.connect.auth.QQToken, android.content.Context, java.lang.String, android.os.Bundle):org.json.JSONObject");
    }
}
