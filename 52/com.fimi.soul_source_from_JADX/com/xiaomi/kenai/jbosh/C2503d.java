package com.xiaomi.kenai.jbosh;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.baidu.tts.loopj.RequestParams;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.channel.commonutils.string.C2474b;
import java.net.URLEncoder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;

/* renamed from: com.xiaomi.kenai.jbosh.d */
final class C2503d implements al {
    private final Lock f12693a;
    private final HttpContext f12694b;
    private final HttpClient f12695c;
    private HttpPost f12696d;
    private long f12697e;
    private boolean f12698f;
    private aa f12699g;
    private C2501b f12700h;
    private int f12701i;

    C2503d(HttpClient httpClient, C2523v c2523v, ah ahVar, C2501b c2501b, Context context) {
        this.f12693a = new ReentrantLock();
        this.f12695c = httpClient;
        this.f12694b = new BasicHttpContext();
        this.f12698f = false;
        try {
            String b = c2501b.m14338b();
            this.f12697e = Long.parseLong(c2501b.m14336a(C2517r.f12727q));
            String valueOf = String.valueOf((int) (Math.random() * 1000.0d));
            String encode = URLEncoder.encode(C2474b.m14160a("xm-http-bind&" + b));
            if (C2472a.m14150b(context)) {
                String host = c2523v.m14445a().getHost();
                Builder buildUpon = Uri.parse(C2472a.m14146a(c2523v.m14445a().toURL())).buildUpon();
                buildUpon.appendQueryParameter("t", valueOf);
                this.f12696d = new HttpPost(buildUpon.build().toString());
                this.f12696d.addHeader("X-Online-Host", host);
            } else {
                Builder buildUpon2 = Uri.parse(c2523v.m14445a().toString()).buildUpon();
                buildUpon2.appendQueryParameter("t", valueOf);
                this.f12696d = new HttpPost(buildUpon2.build().toString());
            }
            this.f12696d.addHeader("X-Content-Sig", encode);
            this.f12696d.addHeader(C3004e.f15024j, "Keep-Alive");
            HttpEntity byteArrayEntity = new ByteArrayEntity(aj.m14347a(b.getBytes(C1142e.f5201a)));
            byteArrayEntity.setContentType(RequestParams.APPLICATION_OCTET_STREAM);
            this.f12696d.setEntity(byteArrayEntity);
        } catch (Throwable e) {
            this.f12699g = new aa("Could not generate request", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m14375e() {
        /*
        r9 = this;
        r1 = 0;
        r8 = 3;
        monitor-enter(r9);
        r0 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e6 }
        r2.<init>();	 Catch:{ all -> 0x00e6 }
        r3 = "SMACK-BOSH: requesting, rid=";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00e6 }
        r4 = r9.f12697e;	 Catch:{ all -> 0x00e6 }
        r2 = r2.append(r4);	 Catch:{ all -> 0x00e6 }
        r3 = " url=";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00e6 }
        r3 = r9.f12696d;	 Catch:{ all -> 0x00e6 }
        r3 = r3.getURI();	 Catch:{ all -> 0x00e6 }
        r3 = r3.toString();	 Catch:{ all -> 0x00e6 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x00e6 }
        r2 = r2.toString();	 Catch:{ all -> 0x00e6 }
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r2);	 Catch:{ all -> 0x00e6 }
        r3 = r0;
        r0 = r1;
    L_0x0032:
        if (r3 >= r8) goto L_0x00dc;
    L_0x0034:
        r2 = r9.f12695c;	 Catch:{ Exception -> 0x010e }
        r4 = r9.f12696d;	 Catch:{ Exception -> 0x010e }
        r5 = r9.f12694b;	 Catch:{ Exception -> 0x010e }
        r2 = r2.execute(r4, r5);	 Catch:{ Exception -> 0x010e }
        r4 = r2.getEntity();	 Catch:{ Exception -> 0x010e }
        r4 = org.apache.http.util.EntityUtils.toByteArray(r4);	 Catch:{ Exception -> 0x010e }
        r5 = r2.getStatusLine();	 Catch:{ Exception -> 0x010e }
        r5 = r5.getStatusCode();	 Catch:{ Exception -> 0x010e }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010e }
        r6.<init>();	 Catch:{ Exception -> 0x010e }
        r7 = "SMACK-BOSH: get server response, code=";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x010e }
        r6 = r6.append(r5);	 Catch:{ Exception -> 0x010e }
        r6 = r6.toString();	 Catch:{ Exception -> 0x010e }
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r6);	 Catch:{ Exception -> 0x010e }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 != r6) goto L_0x010a;
    L_0x0068:
        if (r4 == 0) goto L_0x010a;
    L_0x006a:
        r6 = "X-Content-Sig";
        r6 = r2.containsHeader(r6);	 Catch:{ Exception -> 0x010e }
        if (r6 == 0) goto L_0x010a;
    L_0x0072:
        r4 = com.xiaomi.kenai.jbosh.aj.m14348b(r4);	 Catch:{ Exception -> 0x010e }
        r6 = "X-Content-Sig";
        r2 = r2.getLastHeader(r6);	 Catch:{ Exception -> 0x010e }
        r2 = r2.getValue();	 Catch:{ Exception -> 0x010e }
        r2 = java.net.URLDecoder.decode(r2);	 Catch:{ Exception -> 0x010e }
        r6 = new java.lang.String;	 Catch:{ Exception -> 0x010e }
        r7 = "UTF-8";
        r6.<init>(r4, r7);	 Catch:{ Exception -> 0x010e }
        r4 = com.xiaomi.kenai.jbosh.ap.m14363a(r6);	 Catch:{ Exception -> 0x010e }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010e }
        r6.<init>();	 Catch:{ Exception -> 0x010e }
        r7 = "xm-http-bind&";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x010e }
        r7 = r4.m14338b();	 Catch:{ Exception -> 0x010e }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x010e }
        r6 = r6.toString();	 Catch:{ Exception -> 0x010e }
        r6 = com.xiaomi.channel.commonutils.string.C2474b.m14160a(r6);	 Catch:{ Exception -> 0x010e }
        r7 = r6.equals(r2);	 Catch:{ Exception -> 0x010e }
        if (r7 != 0) goto L_0x00e9;
    L_0x00b0:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010e }
        r4.<init>();	 Catch:{ Exception -> 0x010e }
        r5 = "SMACK-BOSH: the server signature doesn't match, drop the response. received ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x010e }
        r2 = r4.append(r2);	 Catch:{ Exception -> 0x010e }
        r4 = ", expected ";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x010e }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x010e }
        r2 = r2.toString();	 Catch:{ Exception -> 0x010e }
        com.xiaomi.channel.commonutils.logger.C2463b.m14127c(r2);	 Catch:{ Exception -> 0x010e }
        r2 = new com.xiaomi.kenai.jbosh.aa;	 Catch:{ Exception -> 0x010e }
        r4 = "signature mismatch";
        r2.<init>(r4);	 Catch:{ Exception -> 0x010e }
        r9.f12699g = r2;	 Catch:{ Exception -> 0x010e }
        r9.m14376a();	 Catch:{ Exception -> 0x010e }
    L_0x00dc:
        if (r3 != r8) goto L_0x0145;
    L_0x00de:
        r9.m14376a();	 Catch:{ all -> 0x00e6 }
        r9.f12699g = r0;	 Catch:{ all -> 0x00e6 }
        r0 = r9.f12699g;	 Catch:{ all -> 0x00e6 }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x00e6:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x00e9:
        r9.f12700h = r4;	 Catch:{ Exception -> 0x010e }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010e }
        r0.<init>();	 Catch:{ Exception -> 0x010e }
        r2 = "SMACK-BOSH: server response, rid=";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x010e }
        r6 = r9.f12697e;	 Catch:{ Exception -> 0x010e }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x010e }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010e }
        com.xiaomi.channel.commonutils.logger.C2463b.m14126b(r0);	 Catch:{ Exception -> 0x010e }
        r9.f12701i = r5;	 Catch:{ Exception -> 0x010e }
        r0 = 1;
        r9.f12698f = r0;	 Catch:{ Exception -> 0x010e }
        r0 = r1;
        goto L_0x00dc;
    L_0x010a:
        r9.m14376a();	 Catch:{ Exception -> 0x010e }
        goto L_0x00dc;
    L_0x010e:
        r0 = move-exception;
        r2 = r0 instanceof java.net.SocketException;	 Catch:{ all -> 0x00e6 }
        if (r2 == 0) goto L_0x0136;
    L_0x0113:
        r2 = new com.xiaomi.kenai.jbosh.aa;	 Catch:{ all -> 0x00e6 }
        r4 = "Could not obtain response";
        r2.<init>(r4, r0);	 Catch:{ all -> 0x00e6 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e6 }
        r4.<init>();	 Catch:{ all -> 0x00e6 }
        r5 = "SMACK-BOSH: request error, retry=";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00e6 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x00e6 }
        r4 = r4.toString();	 Catch:{ all -> 0x00e6 }
        com.xiaomi.channel.commonutils.logger.C2463b.m14124a(r4, r0);	 Catch:{ all -> 0x00e6 }
        r0 = r3 + 1;
        r3 = r0;
        r0 = r2;
        goto L_0x0032;
    L_0x0136:
        r9.m14376a();	 Catch:{ all -> 0x00e6 }
        r1 = new com.xiaomi.kenai.jbosh.aa;	 Catch:{ all -> 0x00e6 }
        r2 = "Could not obtain response";
        r1.<init>(r2, r0);	 Catch:{ all -> 0x00e6 }
        r9.f12699g = r1;	 Catch:{ all -> 0x00e6 }
        r0 = r9.f12699g;	 Catch:{ all -> 0x00e6 }
        throw r0;	 Catch:{ all -> 0x00e6 }
    L_0x0145:
        monitor-exit(r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.kenai.jbosh.d.e():void");
    }

    public void m14376a() {
        if (this.f12696d != null) {
            this.f12696d.abort();
            this.f12699g = new aa("HTTP request aborted");
        }
    }

    public C2501b m14377b() {
        if (this.f12699g != null) {
            throw this.f12699g;
        }
        this.f12693a.lock();
        try {
            if (!this.f12698f) {
                m14375e();
            }
            this.f12693a.unlock();
            return this.f12700h;
        } catch (Throwable th) {
            this.f12693a.unlock();
        }
    }

    public int m14378c() {
        if (this.f12699g != null) {
            throw this.f12699g;
        }
        this.f12693a.lock();
        try {
            if (!this.f12698f) {
                m14375e();
            }
            this.f12693a.unlock();
            return this.f12701i;
        } catch (Throwable th) {
            this.f12693a.unlock();
        }
    }

    public long m14379d() {
        return this.f12697e;
    }
}
