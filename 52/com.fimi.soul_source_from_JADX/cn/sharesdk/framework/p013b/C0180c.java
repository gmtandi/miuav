package cn.sharesdk.framework.p013b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.p013b.p014a.C0167c;
import cn.sharesdk.framework.p013b.p014a.C0168d;
import cn.sharesdk.framework.p013b.p014a.C0169e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.utils.C0205d;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.hoho.android.usbserial.driver.UsbId;
import com.mi.live.openlivesdk.C2116b;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C3004e;

/* renamed from: cn.sharesdk.framework.b.c */
public class C0180c {
    private String f280a;
    private Context f281b;
    private C0169e f282c;
    private DeviceHelper f283d;
    private NetworkHelper f284e;
    private Hashon f285f;
    private String f286g;
    private String f287h;
    private boolean f288i;
    private HashMap<String, String> f289j;

    public C0180c(Context context, String str) {
        this.f280a = str;
        this.f281b = context.getApplicationContext();
        this.f282c = C0169e.m467a(this.f281b);
        this.f283d = DeviceHelper.getInstance(this.f281b);
        this.f284e = new NetworkHelper();
        this.f285f = new Hashon();
        try {
            this.f289j = (HashMap) this.f282c.m497k("buffered_server_paths");
        } catch (Throwable th) {
            this.f289j = new HashMap();
        }
        m583j();
    }

    private String m580b(int i, int i2) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.f283d.getCarrier());
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            parseInt = -1;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, "BSINFO");
        hashMap.put("plat", Integer.valueOf(this.f283d.getPlatformCode()));
        hashMap.put("device", this.f283d.getDeviceKey());
        hashMap.put("carrier", Integer.valueOf(parseInt));
        hashMap.put("simopname", this.f283d.getCarrierName());
        hashMap.put("lac", Integer.valueOf(i2));
        hashMap.put("cell", Integer.valueOf(i));
        return this.f285f.fromHashMap(hashMap);
    }

    private String m581d(HashMap<String, Object> hashMap) {
        hashMap.put(SocialConstants.PARAM_TYPE, "DEVICE");
        hashMap.put(SharedPref.KEY, this.f283d.getDeviceKey());
        hashMap.put("carrier", this.f283d.getCarrier());
        hashMap.put(C2116b.f11123f, this.f280a);
        hashMap.put("apppkg", this.f283d.getPackageName());
        hashMap.put("appver", String.valueOf(this.f283d.getAppVersion()));
        hashMap.put("sdkver", Integer.valueOf(UsbId.SILAB_CP2102 + ShareSDK.getSDKVersionCode()));
        hashMap.put("networktype", this.f283d.getDetailNetworkTypeForStatic());
        return this.f285f.fromHashMap(hashMap);
    }

    private String m582e(String str) {
        boolean b = this.f282c.m480b();
        boolean c = this.f282c.m482c();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Data.urlEncode(this.f283d.getPackageName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.f283d.getAppVersionName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(UsbId.SILAB_CP2102 + ShareSDK.getSDKVersionCode()), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(this.f283d.getPlatformCode()), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.f283d.getDetailNetworkTypeForStatic(), "utf-8")).append("|");
        if (b) {
            stringBuilder.append(Data.urlEncode(String.valueOf(this.f283d.getOSVersionInt()), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f283d.getScreenSize(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f283d.getManufacturer(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f283d.getModel(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.f283d.getCarrier(), "utf-8")).append("|");
        } else {
            stringBuilder.append("|||||");
        }
        if (c) {
            stringBuilder.append(str);
        } else {
            stringBuilder.append(str.split("\\|")[0]);
            stringBuilder.append("|||||");
        }
        C0205d.m752a().m743i("shorLinkMsg ===>>>>", stringBuilder.toString());
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.f283d.getDeviceKey(), this.f280a})), stringBuilder.toString()), 2);
    }

    private void m583j() {
        String str = this.f283d.getPackageName() + "/" + this.f283d.getAppVersionName();
        String str2 = "ShareSDK/" + ShareSDK.getSDKVersionName();
        this.f286g = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ("Android/" + this.f283d.getOSVersionInt());
        this.f287h = "http://api.share.mob.com:80";
        this.f288i = true;
    }

    private String m584k() {
        return this.f287h + "/conn";
    }

    private String m585l() {
        return (this.f289j == null || !this.f289j.containsKey("/date")) ? this.f287h + "/date" : ((String) this.f289j.get("/date")) + "/date";
    }

    private String m586m() {
        return this.f287h + "/conf5";
    }

    private String m587n() {
        return (this.f289j == null || !this.f289j.containsKey("/data2")) ? this.f287h + "/data2" : ((String) this.f289j.get("/data2")) + "/data2";
    }

    private String m588o() {
        return "http://up.sharesdk.cn/upload/image";
    }

    private String m589p() {
        return (this.f289j == null || !this.f289j.containsKey("/log4")) ? this.f287h + "/log4" : ((String) this.f289j.get("/log4")) + "/log4";
    }

    private String m590q() {
        return "http://l.mob.com/url/ShareSdkMapping.do";
    }

    private String m591r() {
        return (this.f289j == null || !this.f289j.containsKey("/snsconf")) ? this.f287h + "/snsconf" : ((String) this.f289j.get("/snsconf")) + "/snsconf";
    }

    public HashMap<String, Object> m592a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2116b.f11123f, this.f280a));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        C0205d.m752a().m743i(" isConnectToServer response == %s", this.f284e.httpPost(m584k(), arrayList, null, arrayList2, networkTimeOut));
        return this.f285f.fromJson(this.f284e.httpPost(m584k(), arrayList, null, arrayList2, networkTimeOut));
    }

    public HashMap<String, Object> m593a(String str, ArrayList<String> arrayList, int i, String str2) {
        if (!this.f288i) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(SharedPref.KEY, this.f280a));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(new KVPair("urls", ((String) arrayList.get(i2)).toString()));
        }
        arrayList2.add(new KVPair("deviceid", this.f283d.getDeviceKey()));
        arrayList2.add(new KVPair("snsplat", String.valueOf(i)));
        CharSequence e = m582e(str2);
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        arrayList2.add(new KVPair("m", e));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        networkTimeOut.connectionTimeout = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        String httpPost = this.f284e.httpPost(m590q(), arrayList2, null, arrayList3, networkTimeOut);
        C0205d.m752a().m743i("> SERVER_SHORT_LINK_URL  resp: %s", httpPost);
        if (TextUtils.isEmpty(httpPost)) {
            this.f288i = false;
            return null;
        }
        HashMap<String, Object> fromJson = this.f285f.fromJson(httpPost);
        return ((Integer) fromJson.get(RMsgInfo.COL_STATUS)).intValue() == C2799f.f14282t ? fromJson : null;
    }

    public void m594a(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", Data.Base64AES(m580b(i, i2), "sdk.sharesdk.sdk")));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        String httpPost = this.f284e.httpPost(m587n(), arrayList, null, arrayList2, networkTimeOut);
        C0205d.m752a().m743i("> uploadCellInfo  resp: %s", httpPost);
    }

    public void m595a(C0171c c0171c) {
        C0168d.m463a(this.f281b, c0171c.toString(), c0171c.f233e);
    }

    public void m596a(String str) {
        this.f287h = str;
    }

    public void m597a(ArrayList<String> arrayList) {
        C0168d.m464a(this.f281b, arrayList);
    }

    public void m598a(HashMap<String, String> hashMap) {
        this.f289j = hashMap;
        this.f282c.m473a("buffered_server_paths", this.f289j);
    }

    public boolean m599a(String str, boolean z) {
        try {
            if ("none".equals(this.f283d.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("m", str));
            arrayList.add(new KVPair("t", z ? Constants.VIA_TO_TYPE_QQ_GROUP : Constants.VIA_RESULT_SUCCESS));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            String httpPost = this.f284e.httpPost(m589p(), arrayList, null, arrayList2, networkTimeOut);
            C0205d.m752a().m743i("> Upload All Log  resp: %s", httpPost);
            return TextUtils.isEmpty(httpPost) || ((Integer) this.f285f.fromJson(httpPost).get(RMsgInfo.COL_STATUS)).intValue() == C2799f.f14282t;
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return false;
        }
    }

    public long m600b() {
        if (!this.f282c.m494i()) {
            return 0;
        }
        String str = "{}";
        try {
            str = this.f284e.httpGet(m585l(), null, null, null);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
        HashMap fromJson = this.f285f.fromJson(str);
        if (!fromJson.containsKey("timestamp")) {
            return this.f282c.m468a();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - C2178R.parseLong(String.valueOf(fromJson.get("timestamp")));
            this.f282c.m472a("service_time", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (Throwable th2) {
            C0205d.m752a().m738d(th2);
            return this.f282c.m468a();
        }
    }

    public void m601b(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", str));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = C1873o.ak;
        networkTimeOut.connectionTimeout = C1873o.ak;
        String httpPost = this.f284e.httpPost(m587n(), arrayList, null, arrayList2, networkTimeOut);
        C0205d.m752a().m743i("> uploadEXTDeviceData  resp: %s", httpPost);
    }

    public void m602b(HashMap<String, Object> hashMap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", Data.Base64AES(m581d((HashMap) hashMap), "sdk.sharesdk.sdk")));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        String httpPost = this.f284e.httpPost(m587n(), arrayList, null, arrayList2, networkTimeOut);
        C0205d.m752a().m743i("> uploadDeviceData  resp: %s", httpPost);
    }

    public HashMap<String, Object> m603c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2116b.f11123f, this.f280a));
        arrayList.add(new KVPair("device", this.f283d.getDeviceKey()));
        arrayList.add(new KVPair("plat", String.valueOf(this.f283d.getPlatformCode())));
        arrayList.add(new KVPair("apppkg", this.f283d.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.f283d.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(UsbId.SILAB_CP2102 + ShareSDK.getSDKVersionCode())));
        arrayList.add(new KVPair("networktype", this.f283d.getDetailNetworkTypeForStatic()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = C1873o.ak;
        networkTimeOut.connectionTimeout = C1873o.ak;
        C0205d.m752a().m743i(" get server config response == %s", this.f284e.httpPost(m586m(), arrayList, null, arrayList2, networkTimeOut));
        return this.f285f.fromJson(this.f284e.httpPost(m586m(), arrayList, null, arrayList2, networkTimeOut));
    }

    public HashMap<String, Object> m604c(String str) {
        KVPair kVPair = new KVPair(UriUtil.LOCAL_FILE_SCHEME, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C3004e.f15013Y, this.f286g));
        C0205d.m752a().m743i("upload file response == %s", this.f284e.httpPost(m588o(), null, kVPair, arrayList, null));
        return this.f285f.fromJson(this.f284e.httpPost(m588o(), null, kVPair, arrayList, null));
    }

    public void m605c(HashMap<String, Object> hashMap) {
        this.f282c.m474a(this.f280a, this.f285f.fromHashMap(hashMap));
    }

    public HashMap<String, Object> m606d() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("mac", this.f283d.getMacAddress());
        hashMap.put("udid", this.f283d.getDeviceId());
        hashMap.put("model", this.f283d.getModel());
        hashMap.put("factory", this.f283d.getManufacturer());
        hashMap.put("plat", Integer.valueOf(this.f283d.getPlatformCode()));
        hashMap.put("sysver", String.valueOf(this.f283d.getOSVersionInt()));
        hashMap.put("breaked", Boolean.valueOf(false));
        hashMap.put("screensize", this.f283d.getScreenSize());
        hashMap.put("androidid", this.f283d.getAndroidID());
        CharSequence advertisingID = this.f283d.getAdvertisingID();
        if (!TextUtils.isEmpty(advertisingID)) {
            hashMap.put("adsid", advertisingID);
        }
        return hashMap;
    }

    public HashMap<String, Object> m607d(String str) {
        return this.f285f.fromJson(new String(Data.AES128Decode(Data.rawMD5(this.f280a + ":" + this.f283d.getDeviceKey()), Base64.decode(str, 2)), C1142e.f5201a).trim());
    }

    public String m608e() {
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, "DEVEXT");
        hashMap.put("plat", Integer.valueOf(this.f283d.getPlatformCode()));
        hashMap.put("device", this.f283d.getDeviceKey());
        hashMap.put("phonename", this.f283d.getBluetoothName());
        hashMap.put("signmd5", this.f283d.getSignMD5());
        if (this.f283d.getDetailNetworkTypeForStatic().equals("wifi")) {
            hashMap.put("ssid", this.f283d.getSSID());
            hashMap.put("bssid", this.f283d.getBssid());
        }
        return this.f285f.fromHashMap(hashMap);
    }

    public HashMap<String, Object> m609f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2116b.f11123f, this.f280a));
        arrayList.add(new KVPair("device", this.f283d.getDeviceKey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = C1873o.ak;
        networkTimeOut.connectionTimeout = C1873o.ak;
        return this.f285f.fromJson(this.f284e.httpPost(m591r(), arrayList, null, arrayList2, networkTimeOut));
    }

    public ArrayList<C0167c> m610g() {
        ArrayList<C0167c> a = C0168d.m465a(this.f281b);
        return a == null ? new ArrayList() : a;
    }

    public HashMap<String, Object> m611h() {
        return this.f285f.fromJson(this.f282c.m486e(this.f280a));
    }

    public void m612i() {
        String authorize = DeviceAuthorizer.authorize(this.f281b, new C0181d(this));
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, "DUID");
        hashMap.put("plat", Integer.valueOf(this.f283d.getPlatformCode()));
        hashMap.put("device", this.f283d.getDeviceKey());
        hashMap.put("duid", authorize);
        hashMap.put("mac", this.f283d.getMacAddress());
        hashMap.put("udid", this.f283d.getDeviceId());
        hashMap.put("model", this.f283d.getModel());
        authorize = this.f285f.fromHashMap(hashMap);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", Data.Base64AES(authorize, "sdk.sharesdk.sdk")));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C3004e.f15013Y, this.f286g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        authorize = this.f284e.httpPost(m587n(), arrayList, null, arrayList2, networkTimeOut);
        C0205d.m752a().m743i("> uploadDuid  resp: %s", authorize);
    }
}
