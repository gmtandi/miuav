package org.p122a.p123a.p167i.p176f;

import android.util.Log;
import it.p074a.p075a.C2799f;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.BasicRouteDirector;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRouteDirector;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.C3036d;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p151b.C2919d;
import org.p122a.p123a.p152c.C2940b;
import org.p122a.p123a.p152c.p153a.C2925c;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p152c.p160e.C2972e;
import org.p122a.p123a.p154h.C3048c;
import org.p122a.p123a.p159n.C3227f;
import org.p122a.p123a.p159n.C3231j;
import org.p122a.p123a.p167i.p168a.C3060f;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3237d;

@C2912b
/* renamed from: org.a.a.i.f.e */
public class C3171e implements C3167b {
    private static final String f15535a = "HttpClient";
    private final HttpRequestExecutor f15536b;
    private final C3036d f15537c;
    private final ConnectionReuseStrategy f15538d;
    private final ConnectionKeepAliveStrategy f15539e;
    private final HttpProcessor f15540f;
    private final C2940b f15541g;
    private final C2940b f15542h;
    private final C3060f f15543i;
    private final UserTokenHandler f15544j;
    private final HttpRouteDirector f15545k;

    public C3171e(HttpRequestExecutor httpRequestExecutor, C3036d c3036d, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, C2940b c2940b, C2940b c2940b2, UserTokenHandler userTokenHandler) {
        C3234a.m17886a((Object) httpRequestExecutor, "HTTP request executor");
        C3234a.m17886a((Object) c3036d, "Client connection manager");
        C3234a.m17886a((Object) connectionReuseStrategy, "Connection reuse strategy");
        C3234a.m17886a((Object) connectionKeepAliveStrategy, "Connection keep alive strategy");
        C3234a.m17886a((Object) c2940b, "Target authentication strategy");
        C3234a.m17886a((Object) c2940b2, "Proxy authentication strategy");
        C3234a.m17886a((Object) userTokenHandler, "User token handler");
        this.f15543i = new C3060f();
        this.f15540f = new C3227f(new C3231j(), new C2972e());
        this.f15545k = new BasicRouteDirector();
        this.f15536b = httpRequestExecutor;
        this.f15537c = c3036d;
        this.f15538d = connectionReuseStrategy;
        this.f15539e = connectionKeepAliveStrategy;
        this.f15541g = c2940b;
        this.f15542h = c2940b2;
        this.f15544j = userTokenHandler;
    }

    private boolean m17707a(C2919d c2919d, C2919d c2919d2, HttpRoute httpRoute, HttpResponse httpResponse, C2968a c2968a) {
        if (c2968a.m16906n().m16750j()) {
            HttpHost t = c2968a.m16882t();
            if (t == null) {
                t = httpRoute.getTargetHost();
            }
            HttpHost httpHost = t.getPort() < 0 ? new HttpHost(t.getHostName(), httpRoute.getTargetHost().getPort(), t.getSchemeName()) : t;
            boolean a = this.f15543i.m17194a(httpHost, httpResponse, this.f15541g, c2919d, c2968a);
            HttpHost proxyHost = httpRoute.getProxyHost();
            if (proxyHost == null) {
                proxyHost = httpRoute.getTargetHost();
            }
            boolean a2 = this.f15543i.m17194a(proxyHost, httpResponse, this.f15542h, c2919d2, c2968a);
            if (a) {
                return this.f15543i.m17195b(httpHost, httpResponse, this.f15541g, c2919d, c2968a);
            } else if (a2) {
                return this.f15543i.m17195b(proxyHost, httpResponse, this.f15542h, c2919d2, c2968a);
            }
        }
        return false;
    }

    private boolean m17708a(HttpRoute httpRoute, int i, C2968a c2968a) {
        throw new HttpException("Proxy chains are not supported.");
    }

    private boolean m17709b(C2919d c2919d, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, C2968a c2968a) {
        C2925c n = c2968a.m16906n();
        int n2 = n.m16754n();
        HttpHost targetHost = httpRoute.getTargetHost();
        HttpHost proxyHost = httpRoute.getProxyHost();
        HttpResponse httpResponse = null;
        HttpRequest basicHttpRequest = new BasicHttpRequest("CONNECT", targetHost.toHostString(), httpRequest.getProtocolVersion());
        this.f15536b.preProcess(basicHttpRequest, this.f15540f, c2968a);
        while (httpResponse == null) {
            if (!httpClientConnection.isOpen()) {
                this.f15537c.m17139a(httpClientConnection, httpRoute, n2 > 0 ? n2 : 0, (HttpContext) c2968a);
            }
            basicHttpRequest.removeHeaders(C3004e.f15002N);
            this.f15543i.m17193a(basicHttpRequest, c2919d, c2968a);
            httpResponse = this.f15536b.execute(basicHttpRequest, httpClientConnection, c2968a);
            if (httpResponse.getStatusLine().getStatusCode() < C2799f.f14282t) {
                throw new HttpException("Unexpected response to CONNECT request: " + httpResponse.getStatusLine());
            } else if (n.m16750j() && this.f15543i.m17194a(proxyHost, httpResponse, this.f15542h, c2919d, c2968a) && this.f15543i.m17195b(proxyHost, httpResponse, this.f15542h, c2919d, c2968a)) {
                if (this.f15538d.keepAlive(httpResponse, c2968a)) {
                    if (Log.isLoggable(f15535a, 3)) {
                        Log.d(f15535a, "Connection kept alive");
                    }
                    C3237d.m17904b(httpResponse.getEntity());
                } else {
                    httpClientConnection.close();
                }
                httpResponse = null;
            }
        }
        if (httpResponse.getStatusLine().getStatusCode() <= 299) {
            return false;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            httpResponse.setEntity(new C3048c(entity));
        }
        httpClientConnection.close();
        throw new C3180n("CONNECT refused by proxy: " + httpResponse.getStatusLine(), httpResponse);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.p122a.p123a.p152c.p156c.C2946d m17710a(org.apache.http.conn.routing.HttpRoute r19, org.p122a.p123a.p152c.p156c.C2957p r20, org.p122a.p123a.p152c.p160e.C2968a r21, org.p122a.p123a.p152c.p156c.C2941h r22) {
        /*
        r18 = this;
        r2 = "HTTP route";
        r0 = r19;
        org.p122a.p123a.p180o.C3234a.m17886a(r0, r2);
        r2 = "HTTP request";
        r0 = r20;
        org.p122a.p123a.p180o.C3234a.m17886a(r0, r2);
        r2 = "HTTP context";
        r0 = r21;
        org.p122a.p123a.p180o.C3234a.m17886a(r0, r2);
        r2 = r21.m16903k();
        if (r2 != 0) goto L_0x037c;
    L_0x001b:
        r2 = new org.a.a.b.d;
        r2.<init>();
        r3 = "http.auth.target-scope";
        r0 = r21;
        r0.setAttribute(r3, r2);
        r11 = r2;
    L_0x0028:
        r3 = r21.m16904l();
        if (r3 != 0) goto L_0x003a;
    L_0x002e:
        r3 = new org.a.a.b.d;
        r3.<init>();
        r2 = "http.auth.proxy-scope";
        r0 = r21;
        r0.setAttribute(r2, r3);
    L_0x003a:
        r0 = r20;
        r2 = r0 instanceof org.apache.http.HttpEntityEnclosingRequest;
        if (r2 == 0) goto L_0x0047;
    L_0x0040:
        r2 = r20;
        r2 = (org.apache.http.HttpEntityEnclosingRequest) r2;
        org.p122a.p123a.p167i.p176f.C3176j.m17717a(r2);
    L_0x0047:
        r12 = r21.m16905m();
        r0 = r18;
        r2 = r0.f15537c;
        r0 = r19;
        r2 = r2.m17135a(r0, r12);
        if (r22 == 0) goto L_0x006d;
    L_0x0057:
        r4 = r22.isAborted();
        if (r4 == 0) goto L_0x0068;
    L_0x005d:
        r2.m16821a();
        r2 = new org.a.a.i.f.i;
        r3 = "Request aborted";
        r2.<init>(r3);
        throw r2;
    L_0x0068:
        r0 = r22;
        r0.m16817a(r2);
    L_0x006d:
        r14 = r21.m16906n();
        r4 = r14.m16753m();	 Catch:{ InterruptedException -> 0x00ec, ExecutionException -> 0x00fc }
        if (r4 <= 0) goto L_0x00e9;
    L_0x0077:
        r4 = (long) r4;	 Catch:{ InterruptedException -> 0x00ec, ExecutionException -> 0x00fc }
    L_0x0078:
        r6 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00ec, ExecutionException -> 0x00fc }
        r4 = r2.m17066a(r4, r6);	 Catch:{ InterruptedException -> 0x00ec, ExecutionException -> 0x00fc }
        r2 = "http.connection";
        r0 = r21;
        r0.setAttribute(r2, r4);
        r2 = r14.m16744d();
        if (r2 == 0) goto L_0x00ba;
    L_0x008b:
        r2 = r4.isOpen();
        if (r2 == 0) goto L_0x00ba;
    L_0x0091:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);
        if (r2 == 0) goto L_0x00a1;
    L_0x009a:
        r2 = "HttpClient";
        r5 = "Stale connection check";
        android.util.Log.d(r2, r5);
    L_0x00a1:
        r2 = r4.isStale();
        if (r2 == 0) goto L_0x00ba;
    L_0x00a7:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);
        if (r2 == 0) goto L_0x00b7;
    L_0x00b0:
        r2 = "HttpClient";
        r5 = "Stale connection detected";
        android.util.Log.d(r2, r5);
    L_0x00b7:
        r4.close();
    L_0x00ba:
        r15 = new org.a.a.i.f.c;
        r0 = r18;
        r2 = r0.f15537c;
        r15.<init>(r2, r4);
        if (r22 == 0) goto L_0x00ca;
    L_0x00c5:
        r0 = r22;
        r0.m16817a(r15);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x00ca:
        r2 = 1;
        r13 = r2;
    L_0x00cc:
        r2 = 1;
        if (r13 <= r2) goto L_0x010b;
    L_0x00cf:
        r2 = org.p122a.p123a.p167i.p176f.C3176j.m17719a(r20);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x010b;
    L_0x00d5:
        r2 = new org.apache.http.client.NonRepeatableRequestException;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r3 = "Cannot retry request with a non-repeatable request entity.";
        r2.<init>(r3);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        throw r2;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x00dd:
        r2 = move-exception;
        r3 = new java.io.InterruptedIOException;
        r4 = "Connection has been shut down";
        r3.<init>(r4);
        r3.initCause(r2);
        throw r3;
    L_0x00e9:
        r4 = 0;
        goto L_0x0078;
    L_0x00ec:
        r2 = move-exception;
        r3 = java.lang.Thread.currentThread();
        r3.interrupt();
        r3 = new org.a.a.i.f.i;
        r4 = "Request aborted";
        r3.<init>(r4, r2);
        throw r3;
    L_0x00fc:
        r2 = move-exception;
        r3 = r2.getCause();
        if (r3 != 0) goto L_0x0379;
    L_0x0103:
        r3 = new org.a.a.i.f.i;
        r4 = "Request execution failed";
        r3.<init>(r4, r2);
        throw r3;
    L_0x010b:
        if (r22 == 0) goto L_0x0120;
    L_0x010d:
        r2 = r22.isAborted();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x0120;
    L_0x0113:
        r2 = new org.a.a.i.f.i;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r3 = "Request aborted";
        r2.<init>(r3);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        throw r2;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x011b:
        r2 = move-exception;
        r15.abortConnection();
        throw r2;
    L_0x0120:
        r2 = r4.isOpen();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x0154;
    L_0x0126:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x0149;
    L_0x012f:
        r2 = "HttpClient";
        r5 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = "Opening connection ";
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0149:
        r2 = r18;
        r5 = r19;
        r6 = r20;
        r7 = r21;
        r2.m17711a(r3, r4, r5, r6, r7);	 Catch:{ n -> 0x0172 }
    L_0x0154:
        r2 = r14.m16755o();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 < 0) goto L_0x015d;
    L_0x015a:
        r4.setSocketTimeout(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x015d:
        if (r22 == 0) goto L_0x01b7;
    L_0x015f:
        r2 = r22.isAborted();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x01b7;
    L_0x0165:
        r2 = new org.a.a.i.f.i;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r3 = "Request aborted";
        r2.<init>(r3);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        throw r2;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x016d:
        r2 = move-exception;
        r15.abortConnection();
        throw r2;
    L_0x0172:
        r2 = move-exception;
        r3 = "HttpClient";
        r4 = 3;
        r3 = android.util.Log.isLoggable(r3, r4);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r3 == 0) goto L_0x0185;
    L_0x017c:
        r3 = "HttpClient";
        r4 = r2.getMessage();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r3, r4);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0185:
        r9 = r2.m17727a();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0189:
        if (r12 != 0) goto L_0x0376;
    L_0x018b:
        r0 = r18;
        r2 = r0.f15544j;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r21;
        r2 = r2.getUserToken(r0);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r3 = "http.user-token";
        r0 = r21;
        r0.setAttribute(r3, r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x019c:
        if (r2 == 0) goto L_0x01a1;
    L_0x019e:
        r15.m17701a(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x01a1:
        r2 = r9.getEntity();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x01ad;
    L_0x01a7:
        r2 = r2.isStreaming();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x036f;
    L_0x01ad:
        r15.releaseConnection();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = new org.a.a.i.f.d;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r3 = 0;
        r2.<init>(r9, r3);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x01b6:
        return r2;
    L_0x01b7:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x01dc;
    L_0x01c0:
        r2 = "HttpClient";
        r5 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = "Executing request ";
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = r20.getRequestLine();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x01dc:
        r2 = "Authorization";
        r0 = r20;
        r2 = r0.containsHeader(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x0216;
    L_0x01e6:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x020b;
    L_0x01ef:
        r2 = "HttpClient";
        r5 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = "Target auth state: ";
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = r11.m16730b();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x020b:
        r0 = r18;
        r2 = r0.f15543i;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r20;
        r1 = r21;
        r2.m17193a(r0, r11, r1);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0216:
        r2 = "Proxy-Authorization";
        r0 = r20;
        r2 = r0.containsHeader(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x0256;
    L_0x0220:
        r2 = r19.isTunnelled();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x0256;
    L_0x0226:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x024b;
    L_0x022f:
        r2 = "HttpClient";
        r5 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = "Proxy auth state: ";
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r6 = r3.m16730b();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r5.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x024b:
        r0 = r18;
        r2 = r0.f15543i;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r20;
        r1 = r21;
        r2.m17193a(r0, r3, r1);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0256:
        r0 = r18;
        r2 = r0.f15536b;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r20;
        r1 = r21;
        r9 = r2.execute(r0, r4, r1);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r18;
        r2 = r0.f15538d;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r21;
        r2 = r2.keepAlive(r9, r0);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x030b;
    L_0x026e:
        r0 = r18;
        r2 = r0.f15539e;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r0 = r21;
        r6 = r2.getKeepAliveDuration(r9, r0);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x02be;
    L_0x0281:
        r16 = 0;
        r2 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1));
        if (r2 <= 0) goto L_0x0308;
    L_0x0287:
        r2 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = "for ";
        r2 = r2.append(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.append(r6);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = " ";
        r2 = r2.append(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.append(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x02a6:
        r5 = "HttpClient";
        r8 = new java.lang.StringBuilder;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r8.<init>();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r10 = "Connection can be kept alive ";
        r8 = r8.append(r10);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r8.append(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.toString();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        android.util.Log.d(r5, r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x02be:
        r2 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r15.m17700a(r6, r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r15.m17704c();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x02c6:
        r5 = r18;
        r6 = r11;
        r7 = r3;
        r8 = r19;
        r10 = r21;
        r2 = r5.m17707a(r6, r7, r8, r9, r10);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x0189;
    L_0x02d4:
        r2 = r9.getEntity();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = r15.m17703b();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r5 == 0) goto L_0x0314;
    L_0x02de:
        org.p122a.p123a.p180o.C3237d.m17904b(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x02e1:
        r2 = r20.m16831a();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = "Authorization";
        r5 = r2.containsHeader(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r5 != 0) goto L_0x02f4;
    L_0x02ed:
        r5 = "Authorization";
        r0 = r20;
        r0.removeHeaders(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x02f4:
        r5 = "Proxy-Authorization";
        r2 = r2.containsHeader(r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != 0) goto L_0x0303;
    L_0x02fc:
        r2 = "Proxy-Authorization";
        r0 = r20;
        r0.removeHeaders(r2);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0303:
        r2 = r13 + 1;
        r13 = r2;
        goto L_0x00cc;
    L_0x0308:
        r2 = "indefinitely";
        goto L_0x02a6;
    L_0x030b:
        r15.m17705d();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        goto L_0x02c6;
    L_0x030f:
        r2 = move-exception;
        r15.abortConnection();
        throw r2;
    L_0x0314:
        r4.close();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r3.m16730b();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = org.p122a.p123a.p151b.C2917b.SUCCESS;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != r5) goto L_0x0342;
    L_0x031f:
        r2 = r3.m16731c();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x0342;
    L_0x0325:
        r2 = r3.m16731c();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.isConnectionBased();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x0342;
    L_0x032f:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x033f;
    L_0x0338:
        r2 = "HttpClient";
        r5 = "Resetting proxy auth state";
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x033f:
        r3.m16723a();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x0342:
        r2 = r11.m16730b();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r5 = org.p122a.p123a.p151b.C2917b.SUCCESS;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 != r5) goto L_0x02e1;
    L_0x034a:
        r2 = r11.m16731c();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x02e1;
    L_0x0350:
        r2 = r11.m16731c();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2 = r2.isConnectionBased();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x02e1;
    L_0x035a:
        r2 = "HttpClient";
        r5 = 3;
        r2 = android.util.Log.isLoggable(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        if (r2 == 0) goto L_0x036a;
    L_0x0363:
        r2 = "HttpClient";
        r5 = "Resetting target auth state";
        android.util.Log.d(r2, r5);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
    L_0x036a:
        r11.m16723a();	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        goto L_0x02e1;
    L_0x036f:
        r2 = new org.a.a.i.f.d;	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        r2.<init>(r9, r15);	 Catch:{ f -> 0x00dd, HttpException -> 0x011b, IOException -> 0x016d, RuntimeException -> 0x030f }
        goto L_0x01b6;
    L_0x0376:
        r2 = r12;
        goto L_0x019c;
    L_0x0379:
        r2 = r3;
        goto L_0x0103;
    L_0x037c:
        r11 = r2;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.f.e.a(org.apache.http.conn.routing.HttpRoute, org.a.a.c.c.p, org.a.a.c.e.a, org.a.a.c.c.h):org.a.a.c.c.d");
    }

    void m17711a(C2919d c2919d, HttpClientConnection httpClientConnection, HttpRoute httpRoute, HttpRequest httpRequest, C2968a c2968a) {
        int n = c2968a.m16906n().m16754n();
        RouteTracker routeTracker = new RouteTracker(httpRoute);
        int nextStep;
        do {
            Object toRoute = routeTracker.toRoute();
            nextStep = this.f15545k.nextStep(httpRoute, toRoute);
            switch (nextStep) {
                case Opcodes.F_NEW /*-1*/:
                    throw new HttpException("Unable to establish route: planned = " + httpRoute + "; current = " + toRoute);
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f15537c.m17142b(httpClientConnection, httpRoute, c2968a);
                    continue;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f15537c.m17139a(httpClientConnection, httpRoute, n > 0 ? n : 0, (HttpContext) c2968a);
                    routeTracker.connectTarget(httpRoute.isSecure());
                    continue;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f15537c.m17139a(httpClientConnection, httpRoute, n > 0 ? n : 0, (HttpContext) c2968a);
                    routeTracker.connectProxy(httpRoute.getProxyHost(), false);
                    continue;
                case Type.BYTE /*3*/:
                    boolean b = m17709b(c2919d, httpClientConnection, httpRoute, httpRequest, c2968a);
                    if (Log.isLoggable(f15535a, 3)) {
                        Log.d(f15535a, "Tunnel to target created.");
                    }
                    routeTracker.tunnelTarget(b);
                    continue;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    int hopCount = toRoute.getHopCount() - 1;
                    boolean a = m17708a(httpRoute, hopCount, c2968a);
                    if (Log.isLoggable(f15535a, 3)) {
                        Log.d(f15535a, "Tunnel to proxy created.");
                    }
                    routeTracker.tunnelProxy(httpRoute.getHopTarget(hopCount), a);
                    continue;
                case Type.INT /*5*/:
                    this.f15537c.m17140a(httpClientConnection, httpRoute, c2968a);
                    routeTracker.layerProtocol(httpRoute.isSecure());
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + nextStep + " from RouteDirector.");
            }
        } while (nextStep > 0);
    }
}
