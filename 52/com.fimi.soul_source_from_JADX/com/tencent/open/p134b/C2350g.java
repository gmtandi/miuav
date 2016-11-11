package com.tencent.open.p134b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.fimi.soul.service.CameraSocketService;
import com.mi.live.openlivesdk.C2116b;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.OpenConfig;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import it.p074a.p075a.C2799f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: com.tencent.open.b.g */
public class C2350g {
    protected static final String f12053a;
    protected static C2350g f12054b;
    protected Random f12055c;
    protected List<Serializable> f12056d;
    protected List<Serializable> f12057e;
    protected HandlerThread f12058f;
    protected Handler f12059g;

    /* renamed from: com.tencent.open.b.g.1 */
    class C23441 extends Handler {
        final /* synthetic */ C2350g f12034a;

        C23441(C2350g c2350g, Looper looper) {
            this.f12034a = c2350g;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER /*1000*/:
                    this.f12034a.m13803b();
                    break;
                case CameraSocketService.f9884d /*1001*/:
                    this.f12034a.m13806e();
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: com.tencent.open.b.g.2 */
    class C23452 implements Runnable {
        final /* synthetic */ Bundle f12035a;
        final /* synthetic */ boolean f12036b;
        final /* synthetic */ C2350g f12037c;

        C23452(C2350g c2350g, Bundle bundle, boolean z) {
            this.f12037c = c2350g;
            this.f12035a = bundle;
            this.f12036b = z;
        }

        public void run() {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("uin", Constants.DEFAULT_UIN);
                bundle.putString("imei", C2340c.m13780b(Global.getContext()));
                bundle.putString("imsi", C2340c.m13781c(Global.getContext()));
                bundle.putString("android_id", C2340c.m13782d(Global.getContext()));
                bundle.putString("mac", C2340c.m13777a());
                bundle.putString(Constants.PARAM_PLATFORM, Constants.VIA_TO_TYPE_QQ_GROUP);
                bundle.putString("os_ver", VERSION.RELEASE);
                bundle.putString("position", Util.getLocation(Global.getContext()));
                bundle.putString("network", C2338a.m13772a(Global.getContext()));
                bundle.putString("language", C2340c.m13779b());
                bundle.putString(C2537j.ai, C2340c.m13778a(Global.getContext()));
                bundle.putString("apn", C2338a.m13773b(Global.getContext()));
                bundle.putString("model_name", Build.MODEL);
                bundle.putString("timezone", TimeZone.getDefault().getID());
                bundle.putString("sdk_ver", Constants.SDK_VERSION);
                bundle.putString("qz_ver", Util.getAppVersionName(Global.getContext(), Constants.PACKAGE_QZONE));
                bundle.putString("qq_ver", Util.getVersionName(Global.getContext(), Constants.PACKAGE_QQ));
                bundle.putString("qua", Util.getQUA3(Global.getContext(), Global.getPackageName()));
                bundle.putString(C2116b.f11124g, Global.getPackageName());
                bundle.putString("app_ver", Util.getAppVersionName(Global.getContext(), Global.getPackageName()));
                if (this.f12035a != null) {
                    bundle.putAll(this.f12035a);
                }
                this.f12037c.f12057e.add(new C2339b(bundle));
                int size = this.f12037c.f12057e.size();
                int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                if (i == 0) {
                    i = C1873o.ak;
                }
                if (this.f12037c.m13801a("report_via", size) || this.f12036b) {
                    this.f12037c.m13806e();
                    this.f12037c.f12059g.removeMessages(CameraSocketService.f9884d);
                } else if (!this.f12037c.f12059g.hasMessages(CameraSocketService.f9884d)) {
                    Message obtain = Message.obtain();
                    obtain.what = CameraSocketService.f9884d;
                    this.f12037c.f12059g.sendMessageDelayed(obtain, (long) i);
                }
            } catch (Throwable e) {
                C2333f.m13755b(C2350g.f12053a, "--> reporVia, exception in sub thread.", e);
            }
        }
    }

    /* renamed from: com.tencent.open.b.g.3 */
    class C23463 implements Runnable {
        final /* synthetic */ long f12038a;
        final /* synthetic */ String f12039b;
        final /* synthetic */ String f12040c;
        final /* synthetic */ int f12041d;
        final /* synthetic */ long f12042e;
        final /* synthetic */ long f12043f;
        final /* synthetic */ boolean f12044g;
        final /* synthetic */ C2350g f12045h;

        C23463(C2350g c2350g, long j, String str, String str2, int i, long j2, long j3, boolean z) {
            this.f12045h = c2350g;
            this.f12038a = j;
            this.f12039b = str;
            this.f12040c = str2;
            this.f12041d = i;
            this.f12042e = j2;
            this.f12043f = j3;
            this.f12044g = z;
        }

        public void run() {
            int i = 1;
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.f12038a;
                Bundle bundle = new Bundle();
                String a = C2338a.m13772a(Global.getContext());
                bundle.putString("apn", a);
                bundle.putString(SocialConstants.PARAM_APP_ID, "1000067");
                bundle.putString("commandid", this.f12039b);
                bundle.putString("detail", this.f12040c);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("network=").append(a).append('&');
                stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                stringBuilder.append("wifi=").append(C2338a.m13776e(Global.getContext()));
                bundle.putString("deviceInfo", stringBuilder.toString());
                int a2 = 100 / this.f12045h.m13796a(this.f12041d);
                if (a2 > 0) {
                    i = a2 > 100 ? 100 : a2;
                }
                bundle.putString("frequency", i + C2915a.f14760f);
                bundle.putString("reqSize", this.f12042e + C2915a.f14760f);
                bundle.putString("resultCode", this.f12041d + C2915a.f14760f);
                bundle.putString("rspSize", this.f12043f + C2915a.f14760f);
                bundle.putString("timeCost", elapsedRealtime + C2915a.f14760f);
                bundle.putString("uin", Constants.DEFAULT_UIN);
                this.f12045h.f12056d.add(new C2339b(bundle));
                int size = this.f12045h.f12056d.size();
                i = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportTimeInterval");
                if (i == 0) {
                    i = C1873o.ak;
                }
                if (this.f12045h.m13801a("report_cgi", size) || this.f12044g) {
                    this.f12045h.m13803b();
                    this.f12045h.f12059g.removeMessages(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
                } else if (!this.f12045h.f12059g.hasMessages(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER)) {
                    Message obtain = Message.obtain();
                    obtain.what = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
                    this.f12045h.f12059g.sendMessageDelayed(obtain, (long) i);
                }
            } catch (Throwable e) {
                C2333f.m13755b(C2350g.f12053a, "--> reportCGI, exception in sub thread.", e);
            }
        }
    }

    /* renamed from: com.tencent.open.b.g.4 */
    class C23474 implements Runnable {
        final /* synthetic */ C2350g f12046a;

        C23474(C2350g c2350g) {
            this.f12046a = c2350g;
        }

        public void run() {
            Object obj = null;
            Bundle c = this.f12046a.m13804c();
            if (c != null) {
                int i = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_HttpRetryCount");
                int i2 = i == 0 ? 3 : i;
                C2333f.m13754b(C2350g.f12053a, "-->doReportCgi, retryCount: " + i2);
                i = 0;
                do {
                    i++;
                    try {
                        HttpClient httpClient = HttpUtils.getHttpClient(Global.getContext(), null, ServerSetting.DEFAULT_URL_REPORT);
                        HttpUriRequest httpPost = new HttpPost(ServerSetting.DEFAULT_URL_REPORT);
                        httpPost.addHeader(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
                        httpPost.setHeader(C3004e.f15031q, C2989l.f14939a);
                        httpPost.setEntity(new ByteArrayEntity(HttpUtils.encodeUrl(c).getBytes()));
                        int statusCode = httpClient.execute(httpPost).getStatusLine().getStatusCode();
                        C2333f.m13754b(C2350g.f12053a, "-->doReportCgi, statusCode: " + statusCode);
                        if (statusCode == C2799f.f14282t) {
                            C2343f.m13791a().m13794b("report_cgi");
                            obj = 1;
                        }
                    } catch (Throwable e) {
                        try {
                            C2333f.m13752a(C2350g.f12053a, "-->doReportCgi, doupload exception", e);
                            continue;
                        } catch (Throwable e2) {
                            C2333f.m13752a(C2350g.f12053a, "-->doReportCgi, doupload exception out.", e2);
                            return;
                        }
                    } catch (Throwable e3) {
                        C2333f.m13752a(C2350g.f12053a, "-->doReportCgi, doupload exception", e3);
                        continue;
                    } catch (Throwable e22) {
                        C2333f.m13752a(C2350g.f12053a, "-->doReportCgi, doupload exception", e22);
                    }
                    if (obj == null) {
                        C2343f.m13791a().m13793a("report_cgi", this.f12046a.f12056d);
                    }
                    this.f12046a.f12056d.clear();
                } while (i < i2);
                if (obj == null) {
                    C2343f.m13791a().m13793a("report_cgi", this.f12046a.f12056d);
                }
                this.f12046a.f12056d.clear();
            }
        }
    }

    /* renamed from: com.tencent.open.b.g.5 */
    class C23485 implements Runnable {
        final /* synthetic */ C2350g f12047a;

        C23485(C2350g c2350g) {
            this.f12047a = c2350g;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r18 = this;
            r0 = r18;
            r2 = r0.f12047a;	 Catch:{ Exception -> 0x00a3 }
            r14 = r2.m13805d();	 Catch:{ Exception -> 0x00a3 }
            if (r14 != 0) goto L_0x000b;
        L_0x000a:
            return;
        L_0x000b:
            r2 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00a3 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3 }
            r3.<init>();	 Catch:{ Exception -> 0x00a3 }
            r4 = "-->doReportVia, params: ";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
            r4 = r14.toString();	 Catch:{ Exception -> 0x00a3 }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00a3 }
            com.tencent.open.p133a.C2333f.m13754b(r2, r3);	 Catch:{ Exception -> 0x00a3 }
            r11 = com.tencent.open.p134b.C2342e.m13789a();	 Catch:{ Exception -> 0x00a3 }
            r10 = 0;
            r3 = 0;
            r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
            r6 = 0;
            r4 = 0;
            r2 = 0;
        L_0x0036:
            r10 = r10 + 1;
            r12 = com.tencent.open.utils.Global.getContext();	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r13 = "http://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report";
            r15 = "POST";
            r15 = com.tencent.open.utils.HttpUtils.openUrl2(r12, r13, r15, r14);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r12 = r15.response;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r12 = com.tencent.open.utils.Util.parseJson(r12);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r13 = "ret";
            r12 = r12.getInt(r13);	 Catch:{ JSONException -> 0x00ad, ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
        L_0x0050:
            if (r12 == 0) goto L_0x005a;
        L_0x0052:
            r12 = r15.response;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r12 = android.text.TextUtils.isEmpty(r12);	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            if (r12 != 0) goto L_0x005c;
        L_0x005a:
            r3 = 1;
            r10 = r11;
        L_0x005c:
            r12 = r15.reqSize;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x00e5, IOException -> 0x0104, Exception -> 0x010f }
            r4 = r15.rspSize;	 Catch:{ ConnectTimeoutException -> 0x00b0, SocketTimeoutException -> 0x00c0, JSONException -> 0x00cb, NetworkUnavailableException -> 0x00d2, HttpStatusException -> 0x012b, IOException -> 0x0104, Exception -> 0x010f }
            r6 = r12;
        L_0x0061:
            if (r10 < r11) goto L_0x0036;
        L_0x0063:
            r10 = r2;
            r13 = r3;
            r16 = r8;
            r8 = r4;
            r4 = r16;
        L_0x006a:
            r0 = r18;
            r2 = r0.f12047a;	 Catch:{ Exception -> 0x00a3 }
            r3 = "mapp_apptrace_sdk";
            r11 = 0;
            r12 = 0;
            r2.m13799a(r3, r4, r6, r8, r10, r11, r12);	 Catch:{ Exception -> 0x00a3 }
            if (r13 == 0) goto L_0x0118;
        L_0x0077:
            r2 = com.tencent.open.p134b.C2343f.m13791a();	 Catch:{ Exception -> 0x00a3 }
            r3 = "report_via";
            r2.m13794b(r3);	 Catch:{ Exception -> 0x00a3 }
        L_0x0080:
            r0 = r18;
            r2 = r0.f12047a;	 Catch:{ Exception -> 0x00a3 }
            r2 = r2.f12057e;	 Catch:{ Exception -> 0x00a3 }
            r2.clear();	 Catch:{ Exception -> 0x00a3 }
            r2 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00a3 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3 }
            r3.<init>();	 Catch:{ Exception -> 0x00a3 }
            r4 = "-->doReportVia, uploadSuccess: ";
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3 }
            r3 = r3.append(r13);	 Catch:{ Exception -> 0x00a3 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00a3 }
            com.tencent.open.p133a.C2333f.m13754b(r2, r3);	 Catch:{ Exception -> 0x00a3 }
            goto L_0x000a;
        L_0x00a3:
            r2 = move-exception;
            r3 = com.tencent.open.p134b.C2350g.f12053a;
            r4 = "-->doReportVia, exception in serial executor.";
            com.tencent.open.p133a.C2333f.m13755b(r3, r4, r2);
            goto L_0x000a;
        L_0x00ad:
            r12 = move-exception;
            r12 = -4;
            goto L_0x0050;
        L_0x00b0:
            r2 = move-exception;
            r2 = r10;
            r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
            r12 = 0;
            r6 = 0;
            r4 = -7;
            r10 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r12;
            goto L_0x0061;
        L_0x00c0:
            r2 = move-exception;
            r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x00a3 }
            r6 = 0;
            r4 = 0;
            r2 = -8;
            goto L_0x0061;
        L_0x00cb:
            r2 = move-exception;
            r6 = 0;
            r4 = 0;
            r2 = -4;
            goto L_0x0061;
        L_0x00d2:
            r2 = move-exception;
            r0 = r18;
            r2 = r0.f12047a;	 Catch:{ Exception -> 0x00a3 }
            r2 = r2.f12057e;	 Catch:{ Exception -> 0x00a3 }
            r2.clear();	 Catch:{ Exception -> 0x00a3 }
            r2 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00a3 }
            r3 = "doReportVia, NetworkUnavailableException.";
            com.tencent.open.p133a.C2333f.m13754b(r2, r3);	 Catch:{ Exception -> 0x00a3 }
            goto L_0x000a;
        L_0x00e5:
            r10 = move-exception;
            r16 = r10;
            r10 = r3;
            r3 = r16;
        L_0x00eb:
            r3 = r3.getMessage();	 Catch:{ Exception -> 0x0129 }
            r11 = "http status code error:";
            r12 = "";
            r3 = r3.replace(r11, r12);	 Catch:{ Exception -> 0x0129 }
            r2 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x0129 }
        L_0x00fb:
            r13 = r10;
            r10 = r2;
            r16 = r8;
            r8 = r4;
            r4 = r16;
            goto L_0x006a;
        L_0x0104:
            r2 = move-exception;
            r6 = 0;
            r4 = 0;
            r2 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r2);	 Catch:{ Exception -> 0x00a3 }
            goto L_0x0061;
        L_0x010f:
            r2 = move-exception;
            r6 = 0;
            r4 = 0;
            r2 = -6;
            r10 = r11;
            goto L_0x0061;
        L_0x0118:
            r2 = com.tencent.open.p134b.C2343f.m13791a();	 Catch:{ Exception -> 0x00a3 }
            r3 = "report_via";
            r0 = r18;
            r4 = r0.f12047a;	 Catch:{ Exception -> 0x00a3 }
            r4 = r4.f12057e;	 Catch:{ Exception -> 0x00a3 }
            r2.m13793a(r3, r4);	 Catch:{ Exception -> 0x00a3 }
            goto L_0x0080;
        L_0x0129:
            r3 = move-exception;
            goto L_0x00fb;
        L_0x012b:
            r6 = move-exception;
            r10 = r3;
            r3 = r6;
            r6 = r12;
            goto L_0x00eb;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.5.run():void");
        }
    }

    /* renamed from: com.tencent.open.b.g.6 */
    class C23496 implements Runnable {
        final /* synthetic */ Bundle f12048a;
        final /* synthetic */ String f12049b;
        final /* synthetic */ boolean f12050c;
        final /* synthetic */ String f12051d;
        final /* synthetic */ C2350g f12052e;

        C23496(C2350g c2350g, Bundle bundle, String str, boolean z, String str2) {
            this.f12052e = c2350g;
            this.f12048a = bundle;
            this.f12049b = str;
            this.f12050c = z;
            this.f12051d = str2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r10 = this;
            r2 = 1;
            r0 = 0;
            r1 = r10.f12048a;	 Catch:{ Exception -> 0x00b5 }
            if (r1 != 0) goto L_0x000e;
        L_0x0006:
            r0 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r1 = "-->httpRequest, params is null!";
            com.tencent.open.p133a.C2333f.m13759e(r0, r1);	 Catch:{ Exception -> 0x00b5 }
        L_0x000d:
            return;
        L_0x000e:
            r1 = com.tencent.open.p134b.C2342e.m13789a();	 Catch:{ Exception -> 0x00b5 }
            if (r1 != 0) goto L_0x00bf;
        L_0x0014:
            r1 = 3;
            r4 = r1;
        L_0x0016:
            r1 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b5 }
            r3.<init>();	 Catch:{ Exception -> 0x00b5 }
            r5 = "-->httpRequest, retryCount: ";
            r3 = r3.append(r5);	 Catch:{ Exception -> 0x00b5 }
            r3 = r3.append(r4);	 Catch:{ Exception -> 0x00b5 }
            r3 = r3.toString();	 Catch:{ Exception -> 0x00b5 }
            com.tencent.open.p133a.C2333f.m13754b(r1, r3);	 Catch:{ Exception -> 0x00b5 }
            r1 = com.tencent.open.utils.Global.getContext();	 Catch:{ Exception -> 0x00b5 }
            r3 = 0;
            r5 = r10.f12049b;	 Catch:{ Exception -> 0x00b5 }
            r5 = com.tencent.open.utils.HttpUtils.getHttpClient(r1, r3, r5);	 Catch:{ Exception -> 0x00b5 }
            r1 = r10.f12048a;	 Catch:{ Exception -> 0x00b5 }
            r1 = com.tencent.open.utils.HttpUtils.encodeUrl(r1);	 Catch:{ Exception -> 0x00b5 }
            r3 = r10.f12050c;	 Catch:{ Exception -> 0x00b5 }
            if (r3 == 0) goto L_0x0126;
        L_0x0043:
            r1 = java.net.URLEncoder.encode(r1);	 Catch:{ Exception -> 0x00b5 }
            r3 = r1;
        L_0x0048:
            r1 = r10.f12051d;	 Catch:{ Exception -> 0x00b5 }
            r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00b5 }
            r6 = "GET";
            r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00b5 }
            if (r1 == 0) goto L_0x00c2;
        L_0x0056:
            r6 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00b5 }
            r1 = r10.f12049b;	 Catch:{ Exception -> 0x00b5 }
            r6.<init>(r1);	 Catch:{ Exception -> 0x00b5 }
            r6.append(r3);	 Catch:{ Exception -> 0x00b5 }
            r1 = new org.apache.http.client.methods.HttpGet;	 Catch:{ Exception -> 0x00b5 }
            r3 = r6.toString();	 Catch:{ Exception -> 0x00b5 }
            r1.<init>(r3);	 Catch:{ Exception -> 0x00b5 }
            r3 = r1;
        L_0x006a:
            r1 = "Accept-Encoding";
            r6 = "gzip";
            r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00b5 }
            r1 = "Content-Type";
            r6 = "application/x-www-form-urlencoded";
            r3.addHeader(r1, r6);	 Catch:{ Exception -> 0x00b5 }
            r1 = r0;
        L_0x0079:
            r1 = r1 + 1;
            r6 = r5.execute(r3);	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r6 = r6.getStatusLine();	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r6 = r6.getStatusCode();	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r7 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r8 = new java.lang.StringBuilder;	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r8.<init>();	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r9 = "-->httpRequest, statusCode: ";
            r8 = r8.append(r9);	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r8 = r8.append(r6);	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r8 = r8.toString();	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            com.tencent.open.p133a.C2333f.m13754b(r7, r8);	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r7 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r6 == r7) goto L_0x00ee;
        L_0x00a3:
            r6 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
            r7 = "-->ReportCenter httpRequest : HttpStatuscode != 200";
            com.tencent.open.p133a.C2333f.m13754b(r6, r7);	 Catch:{ ConnectTimeoutException -> 0x00f7, SocketTimeoutException -> 0x0102, Exception -> 0x010b }
        L_0x00aa:
            if (r0 != r2) goto L_0x0114;
        L_0x00ac:
            r0 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r1 = "-->ReportCenter httpRequest Thread request success";
            com.tencent.open.p133a.C2333f.m13754b(r0, r1);	 Catch:{ Exception -> 0x00b5 }
            goto L_0x000d;
        L_0x00b5:
            r0 = move-exception;
            r0 = com.tencent.open.p134b.C2350g.f12053a;
            r1 = "-->httpRequest, exception in serial executor.";
            com.tencent.open.p133a.C2333f.m13754b(r0, r1);
            goto L_0x000d;
        L_0x00bf:
            r4 = r1;
            goto L_0x0016;
        L_0x00c2:
            r1 = r10.f12051d;	 Catch:{ Exception -> 0x00b5 }
            r1 = r1.toUpperCase();	 Catch:{ Exception -> 0x00b5 }
            r6 = "POST";
            r1 = r1.equals(r6);	 Catch:{ Exception -> 0x00b5 }
            if (r1 == 0) goto L_0x00e5;
        L_0x00d0:
            r1 = new org.apache.http.client.methods.HttpPost;	 Catch:{ Exception -> 0x00b5 }
            r6 = r10.f12049b;	 Catch:{ Exception -> 0x00b5 }
            r1.<init>(r6);	 Catch:{ Exception -> 0x00b5 }
            r3 = r3.getBytes();	 Catch:{ Exception -> 0x00b5 }
            r6 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ Exception -> 0x00b5 }
            r6.<init>(r3);	 Catch:{ Exception -> 0x00b5 }
            r1.setEntity(r6);	 Catch:{ Exception -> 0x00b5 }
            r3 = r1;
            goto L_0x006a;
        L_0x00e5:
            r0 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r1 = "-->httpRequest unkonw request method return.";
            com.tencent.open.p133a.C2333f.m13759e(r0, r1);	 Catch:{ Exception -> 0x00b5 }
            goto L_0x000d;
        L_0x00ee:
            r0 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ ConnectTimeoutException -> 0x0123, SocketTimeoutException -> 0x0120, Exception -> 0x011d }
            r6 = "-->ReportCenter httpRequest Thread success";
            com.tencent.open.p133a.C2333f.m13754b(r0, r6);	 Catch:{ ConnectTimeoutException -> 0x0123, SocketTimeoutException -> 0x0120, Exception -> 0x011d }
            r0 = r2;
            goto L_0x00aa;
        L_0x00f7:
            r6 = move-exception;
        L_0x00f8:
            r6 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r7 = "-->ReportCenter httpRequest ConnectTimeoutException";
            com.tencent.open.p133a.C2333f.m13754b(r6, r7);	 Catch:{ Exception -> 0x00b5 }
        L_0x00ff:
            if (r1 < r4) goto L_0x0079;
        L_0x0101:
            goto L_0x00aa;
        L_0x0102:
            r6 = move-exception;
        L_0x0103:
            r6 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r7 = "-->ReportCenter httpRequest SocketTimeoutException";
            com.tencent.open.p133a.C2333f.m13754b(r6, r7);	 Catch:{ Exception -> 0x00b5 }
            goto L_0x00ff;
        L_0x010b:
            r1 = move-exception;
        L_0x010c:
            r1 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r3 = "-->ReportCenter httpRequest Exception";
            com.tencent.open.p133a.C2333f.m13754b(r1, r3);	 Catch:{ Exception -> 0x00b5 }
            goto L_0x00aa;
        L_0x0114:
            r0 = com.tencent.open.p134b.C2350g.f12053a;	 Catch:{ Exception -> 0x00b5 }
            r1 = "-->ReportCenter httpRequest Thread request failed";
            com.tencent.open.p133a.C2333f.m13754b(r0, r1);	 Catch:{ Exception -> 0x00b5 }
            goto L_0x000d;
        L_0x011d:
            r0 = move-exception;
            r0 = r2;
            goto L_0x010c;
        L_0x0120:
            r0 = move-exception;
            r0 = r2;
            goto L_0x0103;
        L_0x0123:
            r0 = move-exception;
            r0 = r2;
            goto L_0x00f8;
        L_0x0126:
            r3 = r1;
            goto L_0x0048;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.6.run():void");
        }
    }

    static {
        f12053a = C2333f.f12014d + ".ReportManager";
    }

    private C2350g() {
        this.f12058f = null;
        this.f12055c = new Random();
        this.f12057e = Collections.synchronizedList(new ArrayList());
        this.f12056d = Collections.synchronizedList(new ArrayList());
        if (this.f12058f == null) {
            this.f12058f = new HandlerThread("opensdk.report.handlerthread", 10);
            this.f12058f.start();
        }
        if (this.f12058f.isAlive() && this.f12058f.getLooper() != null) {
            this.f12059g = new C23441(this, this.f12058f.getLooper());
        }
    }

    public static synchronized C2350g m13795a() {
        C2350g c2350g;
        synchronized (C2350g.class) {
            if (f12054b == null) {
                f12054b = new C2350g();
            }
            c2350g = f12054b;
        }
        return c2350g;
    }

    protected int m13796a(int i) {
        int i2;
        if (i == 0) {
            i2 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencySuccess");
            return i2 == 0 ? 10 : i2;
        } else {
            i2 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportFrequencyFailed");
            return i2 == 0 ? 100 : i2;
        }
    }

    public void m13797a(Bundle bundle, String str, boolean z) {
        if (bundle != null) {
            C2333f.m13754b(f12053a, "-->reportVia, bundle: " + bundle.toString());
            if (m13802a("report_via", str) || z) {
                this.f12059g.post(new C23452(this, bundle, z));
            }
        }
    }

    public void m13798a(String str, long j, long j2, long j3, int i) {
        m13799a(str, j, j2, j3, i, C2915a.f14760f, false);
    }

    public void m13799a(String str, long j, long j2, long j3, int i, String str2, boolean z) {
        C2333f.m13754b(f12053a, "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
        if (m13802a("report_cgi", C2915a.f14760f + i) || z) {
            this.f12059g.post(new C23463(this, j, str, str2, i, j2, j3, z));
        }
    }

    public void m13800a(String str, String str2, Bundle bundle, boolean z) {
        ThreadManager.executeOnSubThread(new C23496(this, bundle, str, z, str2));
    }

    protected boolean m13801a(String str, int i) {
        int i2 = 5;
        int i3;
        if (str.equals("report_cgi")) {
            i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Common_CGIReportMaxcount");
            if (i3 != 0) {
                i2 = i3;
            }
        } else if (str.equals("report_via")) {
            i3 = OpenConfig.getInstance(Global.getContext(), null).getInt("Agent_ReportBatchCount");
            if (i3 != 0) {
                i2 = i3;
            }
        } else {
            i2 = 0;
        }
        C2333f.m13754b(f12053a, "-->availableCount, report: " + str + " | dataSize: " + i + " | maxcount: " + i2);
        return i >= i2;
    }

    protected boolean m13802a(String str, String str2) {
        boolean z = true;
        boolean z2 = false;
        C2333f.m13754b(f12053a, "-->availableFrequency, report: " + str + " | ext: " + str2);
        if (!TextUtils.isEmpty(str)) {
            int i;
            int a;
            if (str.equals("report_cgi")) {
                try {
                    a = m13796a(Integer.parseInt(str2));
                    if (this.f12055c.nextInt(100) >= a) {
                        z = false;
                    }
                    z2 = z;
                    i = a;
                } catch (Exception e) {
                }
            } else if (str.equals("report_via")) {
                a = C2342e.m13790a(str2);
                if (this.f12055c.nextInt(100) < a) {
                    z2 = true;
                    i = a;
                } else {
                    i = a;
                }
            } else {
                i = 100;
            }
            C2333f.m13754b(f12053a, "-->availableFrequency, result: " + z2 + " | frequency: " + i);
        }
        return z2;
    }

    protected void m13803b() {
        ThreadManager.executeOnNetWorkThread(new C23474(this));
    }

    protected Bundle m13804c() {
        if (this.f12056d.size() == 0) {
            return null;
        }
        C2339b c2339b = (C2339b) this.f12056d.get(0);
        if (c2339b == null) {
            C2333f.m13754b(f12053a, "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = (String) c2339b.f12024a.get(SocialConstants.PARAM_APP_ID);
        Collection a = C2343f.m13791a().m13792a("report_cgi");
        if (a != null) {
            this.f12056d.addAll(a);
        }
        C2333f.m13754b(f12053a, "-->prepareCgiData, mCgiList size: " + this.f12056d.size());
        if (this.f12056d.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString(SocialConstants.PARAM_APP_ID, str);
            bundle.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle.putString("device", Build.DEVICE);
            bundle.putString("qua", Constants.SDK_QUA);
            bundle.putString(SharedPref.KEY, "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.f12056d.size(); i++) {
                c2339b = (C2339b) this.f12056d.get(i);
                bundle.putString(i + "_1", (String) c2339b.f12024a.get("apn"));
                bundle.putString(i + "_2", (String) c2339b.f12024a.get("frequency"));
                bundle.putString(i + "_3", (String) c2339b.f12024a.get("commandid"));
                bundle.putString(i + "_4", (String) c2339b.f12024a.get("resultCode"));
                bundle.putString(i + "_5", (String) c2339b.f12024a.get("timeCost"));
                bundle.putString(i + "_6", (String) c2339b.f12024a.get("reqSize"));
                bundle.putString(i + "_7", (String) c2339b.f12024a.get("rspSize"));
                bundle.putString(i + "_8", (String) c2339b.f12024a.get("detail"));
                bundle.putString(i + "_9", (String) c2339b.f12024a.get("uin"));
                bundle.putString(i + "_10", C2340c.m13783e(Global.getContext()) + "&" + ((String) c2339b.f12024a.get("deviceInfo")));
            }
            C2333f.m13754b(f12053a, "-->prepareCgiData, end. params: " + bundle.toString());
            return bundle;
        } catch (Throwable e) {
            C2333f.m13755b(f12053a, "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    protected Bundle m13805d() {
        Collection a = C2343f.m13791a().m13792a("report_via");
        if (a != null) {
            this.f12057e.addAll(a);
        }
        C2333f.m13754b(f12053a, "-->prepareViaData, mViaList size: " + this.f12057e.size());
        if (this.f12057e.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.f12057e) {
            JSONObject jSONObject = new JSONObject();
            C2339b c2339b = (C2339b) serializable;
            for (String str : c2339b.f12024a.keySet()) {
                try {
                    Object obj = (String) c2339b.f12024a.get(str);
                    if (obj == null) {
                        obj = C2915a.f14760f;
                    }
                    jSONObject.put(str, obj);
                } catch (Throwable e) {
                    C2333f.m13752a(f12053a, "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        C2333f.m13754b(f12053a, "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(UriUtil.DATA_SCHEME, jSONArray);
            bundle.putString(UriUtil.DATA_SCHEME, jSONObject2.toString());
            return bundle;
        } catch (Throwable e2) {
            C2333f.m13752a(f12053a, "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    protected void m13806e() {
        ThreadManager.executeOnNetWorkThread(new C23485(this));
    }
}
