package com.xiaomi.mistatistic.sdk.controller;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.j */
public class C2596j {
    private static C2596j f12966a;
    private HttpEventFilter f12967b;
    private boolean f12968c;
    private List f12969d;
    private Handler f12970e;

    static {
        f12966a = new C2596j();
    }

    private C2596j() {
        this.f12968c = false;
        this.f12969d = new LinkedList();
        this.f12970e = new C2597k(this, Looper.getMainLooper());
    }

    public static C2596j m14753a() {
        return f12966a;
    }

    private void m14754a(JSONObject jSONObject) {
        if (jSONObject.has(UriUtil.DATA_SCHEME)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME);
            int optInt = jSONObject2.optInt(FimiMediaMeta.IJKM_KEY_SAMPLE_RATE, C1873o.ak);
            int optInt2 = jSONObject2.optInt("delay", 300000);
            long optLong = jSONObject2.optLong("ban_time", 0);
            C2605s.m14783b(C2588a.m14708a(), "rt_upload_rate", optInt);
            C2605s.m14784b(C2588a.m14708a(), "rt_upload_delay", (long) optInt2);
            C2605s.m14784b(C2588a.m14708a(), "rt_ban_time", System.currentTimeMillis() + optLong);
        }
    }

    private String m14757e() {
        return BuildSetting.isTest() ? "http://10.99.168.145:8097/realtime_network" : "https://data.mistat.xiaomi.com/realtime_network";
    }

    private boolean m14758f() {
        Map hashMap = new HashMap();
        synchronized (this.f12969d) {
            for (HttpEvent httpEvent : this.f12969d) {
                CharSequence url = httpEvent.getUrl();
                if (!TextUtils.isEmpty(url)) {
                    if (hashMap.containsKey(url)) {
                        ((List) hashMap.get(url)).add(httpEvent);
                    } else {
                        hashMap.put(url, new ArrayList());
                        ((List) hashMap.get(url)).add(httpEvent);
                    }
                }
            }
            if (hashMap.isEmpty()) {
                return false;
            }
            JSONArray jSONArray = new JSONArray();
            for (String str : hashMap.keySet()) {
                JSONArray jSONArray2 = new JSONArray();
                for (HttpEvent toJSON : (List) hashMap.get(str)) {
                    jSONArray2.put(toJSON.toJSON());
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(SocialConstants.PARAM_URL, str);
                jSONObject.put(UriUtil.DATA_SCHEME, jSONArray2);
                jSONArray.put(jSONObject);
            }
            boolean a = m14761a(jSONArray.toString());
            return a;
        }
    }

    public void m14759a(HttpEventFilter httpEventFilter) {
        this.f12967b = httpEventFilter;
    }

    public void m14760a(HttpEvent httpEvent) {
        if (!httpEvent.getUrl().equals(m14757e()) || this.f12968c) {
            if (!(this.f12967b == null || httpEvent.getUrl().equals(m14757e()))) {
                httpEvent = this.f12967b.onEvent(httpEvent);
            }
            if (httpEvent != null && !TextUtils.isEmpty(httpEvent.getUrl())) {
                synchronized (this.f12969d) {
                    this.f12969d.add(httpEvent);
                    if (this.f12969d.size() > 100) {
                        this.f12969d.remove(0);
                    }
                }
                if (!this.f12970e.hasMessages(1023) && !httpEvent.getUrl().equals(m14757e())) {
                    this.f12970e.sendEmptyMessageDelayed(1023, m14764d());
                }
            }
        }
    }

    public boolean m14761a(String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("app_id", C2588a.m14710b()));
        arrayList.add(new BasicNameValuePair("app_package", C2588a.m14708a().getPackageName()));
        arrayList.add(new BasicNameValuePair("device_uuid", C2593g.m14736a(C2588a.m14708a())));
        arrayList.add(new BasicNameValuePair("device_os", "android" + VERSION.SDK_INT));
        arrayList.add(new BasicNameValuePair("device_model", Build.MODEL));
        arrayList.add(new BasicNameValuePair("app_version", C2588a.m14713e()));
        arrayList.add(new BasicNameValuePair("app_channel", C2588a.m14712d()));
        arrayList.add(new BasicNameValuePair("time", String.valueOf(System.currentTimeMillis())));
        arrayList.add(new BasicNameValuePair("net_value", str));
        Object a = C2603q.m14771a(C2588a.m14708a(), m14757e(), arrayList);
        new C2601o().m14769a("http data complete, result=" + a);
        if (!TextUtils.isEmpty(a)) {
            JSONObject jSONObject = new JSONObject(a);
            if ("ok".equals(jSONObject.getString(RMsgInfo.COL_STATUS))) {
                m14754a(jSONObject);
                return true;
            }
        }
        return false;
    }

    public List m14762b() {
        List linkedList;
        synchronized (this.f12969d) {
            linkedList = new LinkedList(this.f12969d);
        }
        return linkedList;
    }

    public boolean m14763c() {
        return System.currentTimeMillis() > C2605s.m14780a(C2588a.m14708a(), "rt_ban_time", 0) && Math.random() * 10000.0d <= ((double) C2605s.m14779a(C2588a.m14708a(), "rt_upload_rate", (int) C1873o.ak));
    }

    public long m14764d() {
        return C2605s.m14780a(C2588a.m14708a(), "rt_upload_delay", (long) MiStatInterface.MIN_UPLOAD_INTERVAL);
    }
}
