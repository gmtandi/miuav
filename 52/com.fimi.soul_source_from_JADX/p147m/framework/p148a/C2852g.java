package p147m.framework.p148a;

import com.baidu.tts.loopj.RequestParams;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import it.p074a.p075a.C2799f;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: m.framework.a.g */
public class C2852g {
    public static int f14592a;
    public static int f14593b;

    private String m16430a(ArrayList<C2849d<String>> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C2849d c2849d = (C2849d) it.next();
            String encode = URLEncoder.encode(c2849d.f14587a, "utf-8");
            String encode2 = c2849d.f14588b != null ? URLEncoder.encode((String) c2849d.f14588b, "utf-8") : C2915a.f14760f;
            if (stringBuilder.length() > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(encode).append(SignatureVisitor.INSTANCEOF).append(encode2);
        }
        return stringBuilder.toString();
    }

    private HttpClient m16431a() {
        KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
        instance.load(null, null);
        SocketFactory c2854i = new C2854i(instance);
        c2854i.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, C1142e.f5201a);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, c2854i, 443));
        return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    private HttpPost m16432a(String str, ArrayList<C2849d<String>> arrayList) {
        HttpPost httpPost = new HttpPost(str);
        if (arrayList != null) {
            C2856k c2856k = new C2856k();
            c2856k.m16444a(m16430a(arrayList));
            HttpEntity c = c2856k.m16417c();
            c.setContentType(C2989l.f14939a);
            httpPost.setEntity(c);
        }
        return httpPost;
    }

    private HttpPost m16433a(String str, ArrayList<C2849d<String>> arrayList, C2849d<String> c2849d) {
        HttpPost httpPost = new HttpPost(str);
        String uuid = UUID.randomUUID().toString();
        C2850e c2850e = new C2850e();
        C2846c c2856k = new C2856k();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C2849d c2849d2 = (C2849d) it.next();
                c2856k.m16444a("--").m16444a(uuid).m16444a("\r\n");
                c2856k.m16444a("content-disposition: form-data; name=\"").m16444a(c2849d2.f14587a).m16444a("\"\r\n\r\n");
                c2856k.m16444a((String) c2849d2.f14588b).m16444a("\r\n");
            }
        }
        httpPost.setHeader(C3004e.f15031q, "multipart/form-data; boundary=" + uuid);
        c2856k.m16444a("--").m16444a(uuid).m16444a("\r\n");
        c2856k.m16444a("Content-Disposition: form-data; name=\"").m16444a(c2849d.f14587a).m16444a("\"; filename=\"").m16444a(new File((String) c2849d.f14588b).getName()).m16444a("\"\r\n");
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor((String) c2849d.f14588b);
        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
            if (((String) c2849d.f14588b).toLowerCase().endsWith("jpg") || ((String) c2849d.f14588b).toLowerCase().endsWith("jepg")) {
                contentTypeFor = "image/jpeg";
            } else if (((String) c2849d.f14588b).toLowerCase().endsWith("png")) {
                contentTypeFor = "image/png";
            } else if (((String) c2849d.f14588b).toLowerCase().endsWith("gif")) {
                contentTypeFor = "image/gif";
            } else {
                InputStream fileInputStream = new FileInputStream((String) c2849d.f14588b);
                contentTypeFor = URLConnection.guessContentTypeFromStream(fileInputStream);
                fileInputStream.close();
                if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                    contentTypeFor = RequestParams.APPLICATION_OCTET_STREAM;
                }
            }
        }
        c2856k.m16444a("Content-Type: ").m16444a(contentTypeFor).m16444a("\r\n\r\n");
        c2850e.m16426a(c2856k);
        c2856k = new C2848b();
        c2856k.m16423a((String) c2849d.f14588b);
        c2850e.m16426a(c2856k);
        C2846c c2856k2 = new C2856k();
        c2856k2.m16444a("\r\n--").m16444a(uuid).m16444a("--\r\n");
        c2850e.m16426a(c2856k2);
        httpPost.setEntity(c2850e.m16417c());
        return httpPost;
    }

    public String m16434a(String str, ArrayList<C2849d<String>> arrayList, ArrayList<C2849d<String>> arrayList2) {
        if (arrayList != null) {
            String a = m16430a(arrayList);
            if (a.length() > 0) {
                str = new StringBuilder(String.valueOf(str)).append("?").append(a).toString();
            }
        }
        HttpUriRequest httpGet = new HttpGet(str);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C2849d c2849d = (C2849d) it.next();
                httpGet.setHeader(c2849d.f14587a, (String) c2849d.f14588b);
            }
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f14592a);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f14593b);
        httpGet.setParams(basicHttpParams);
        HttpClient a2 = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a2.execute(httpGet);
        if (execute.getStatusLine().getStatusCode() == C2799f.f14282t) {
            String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
            a2.getConnectionManager().shutdown();
            return entityUtils;
        }
        entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a2.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public String m16435a(String str, ArrayList<C2849d<String>> arrayList, C2849d<String> c2849d, ArrayList<C2849d<String>> arrayList2) {
        HttpUriRequest a;
        if (c2849d == null || c2849d.f14588b == null || !new File((String) c2849d.f14588b).exists()) {
            Object a2 = m16432a(str, (ArrayList) arrayList);
        } else {
            a = m16433a(str, (ArrayList) arrayList, (C2849d) c2849d);
        }
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C2849d c2849d2 = (C2849d) it.next();
                a.setHeader(c2849d2.f14587a, (String) c2849d2.f14588b);
            }
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f14592a);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f14593b);
        a.setParams(basicHttpParams);
        HttpClient a3 = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a3.execute(a);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (statusCode == C2799f.f14282t || statusCode == bj.f6779b) {
            String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
            a3.getConnectionManager().shutdown();
            return entityUtils;
        }
        entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a3.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public void m16436a(String str, File file) {
        HttpUriRequest httpGet = new HttpGet(str);
        HttpClient a = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a.execute(httpGet);
        if (execute.getStatusLine().getStatusCode() == C2799f.f14282t) {
            InputStream content = execute.getEntity().getContent();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            for (int read = content.read(bArr); read > 0; read = content.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            content.close();
            fileOutputStream.close();
            a.getConnectionManager().shutdown();
            return;
        }
        String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public void m16437a(String str, ArrayList<C2849d<String>> arrayList, ArrayList<C2849d<String>> arrayList2, C2853h c2853h) {
        if (arrayList != null) {
            String a = m16430a(arrayList);
            if (a.length() > 0) {
                str = new StringBuilder(String.valueOf(str)).append("?").append(a).toString();
            }
        }
        HttpUriRequest httpGet = new HttpGet(str);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C2849d c2849d = (C2849d) it.next();
                httpGet.setHeader(c2849d.f14587a, (String) c2849d.f14588b);
            }
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f14592a);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f14593b);
        httpGet.setParams(basicHttpParams);
        HttpClient a2 = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a2.execute(httpGet);
        if (execute.getStatusLine().getStatusCode() == C2799f.f14282t) {
            InputStream content = execute.getEntity().getContent();
            if (c2853h != null) {
                c2853h.m16442a(content);
            }
            content.close();
            a2.getConnectionManager().shutdown();
            return;
        }
        String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a2.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public void m16438a(String str, ArrayList<C2849d<String>> arrayList, C2846c c2846c, C2853h c2853h) {
        HttpUriRequest httpPost = new HttpPost(str);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C2849d c2849d = (C2849d) it.next();
                httpPost.setHeader(c2849d.f14587a, (String) c2849d.f14588b);
            }
        }
        httpPost.setEntity(c2846c.m16417c());
        HttpClient a = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a.execute(httpPost);
        if (execute.getStatusLine().getStatusCode() == C2799f.f14282t) {
            InputStream content = execute.getEntity().getContent();
            if (c2853h != null) {
                c2853h.m16442a(content);
            }
            content.close();
            a.getConnectionManager().shutdown();
        }
        String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public void m16439a(String str, ArrayList<C2849d<String>> arrayList, C2849d<String> c2849d, ArrayList<C2849d<String>> arrayList2, C2853h c2853h) {
        HttpUriRequest a;
        if (c2849d == null || c2849d.f14588b == null || !new File((String) c2849d.f14588b).exists()) {
            Object a2 = m16432a(str, (ArrayList) arrayList);
        } else {
            a = m16433a(str, (ArrayList) arrayList, (C2849d) c2849d);
        }
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C2849d c2849d2 = (C2849d) it.next();
                a.setHeader(c2849d2.f14587a, (String) c2849d2.f14588b);
            }
        }
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f14592a);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f14593b);
        a.setParams(basicHttpParams);
        HttpClient a3 = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a3.execute(a);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (statusCode == C2799f.f14282t || statusCode == bj.f6779b) {
            InputStream content = execute.getEntity().getContent();
            if (c2853h != null) {
                c2853h.m16442a(content);
            }
            content.close();
            a3.getConnectionManager().shutdown();
            return;
        }
        String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a3.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public void m16440a(String str, C2853h c2853h) {
        HttpUriRequest httpGet = new HttpGet(str);
        HttpClient a = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a.execute(httpGet);
        if (execute.getStatusLine().getStatusCode() == C2799f.f14282t) {
            InputStream content = execute.getEntity().getContent();
            if (c2853h != null) {
                c2853h.m16442a(content);
            }
            content.close();
            a.getConnectionManager().shutdown();
            return;
        }
        String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }

    public String m16441b(String str, ArrayList<C2849d<String>> arrayList, C2849d<String> c2849d, ArrayList<C2849d<String>> arrayList2) {
        if (arrayList != null) {
            String a = m16430a(arrayList);
            if (a.length() > 0) {
                str = new StringBuilder(String.valueOf(str)).append("?").append(a).toString();
            }
        }
        HttpUriRequest httpPut = new HttpPut(str);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                C2849d c2849d2 = (C2849d) it.next();
                httpPut.setHeader(c2849d2.f14587a, (String) c2849d2.f14588b);
            }
        }
        C2848b c2848b = new C2848b();
        c2848b.m16423a((String) c2849d.f14588b);
        HttpEntity c = c2848b.m16417c();
        c.setContentEncoding(RequestParams.APPLICATION_OCTET_STREAM);
        httpPut.setEntity(c);
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f14592a);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f14593b);
        httpPut.setParams(basicHttpParams);
        HttpClient a2 = str.startsWith("https://") ? m16431a() : new DefaultHttpClient();
        HttpResponse execute = a2.execute(httpPut);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (statusCode == C2799f.f14282t || statusCode == bj.f6779b) {
            String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
            a2.getConnectionManager().shutdown();
            return entityUtils;
        }
        entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
        a2.getConnectionManager().shutdown();
        throw new Throwable(entityUtils);
    }
}
