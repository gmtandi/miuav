package cn.sharesdk.framework;

import android.content.Context;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import cn.sharesdk.framework.utils.C0205d;
import com.mi.live.openlivesdk.C2116b;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public abstract class Service {
    private Context f186a;
    private String f187b;

    public abstract class ServiceEvent {
        private final int PLATFORM;
        protected Service service;

        public ServiceEvent(Service service) {
            this.PLATFORM = 1;
            this.service = service;
        }

        protected HashMap<String, Object> filterShareContent(int i, ShareParams shareParams, HashMap<String, Object> hashMap) {
            C0176a filterShareContent = ShareSDK.getPlatform(ShareSDK.platformIdToName(i)).filterShareContent(shareParams, hashMap);
            HashMap<String, Object> hashMap2 = new HashMap();
            hashMap2.put("shareID", filterShareContent.f260a);
            hashMap2.put("shareContent", new Hashon().fromJson(filterShareContent.toString()));
            C0205d.m752a().m743i("filterShareContent ==>>%s", hashMap2);
            return hashMap2;
        }

        protected HashMap<String, Object> toMap() {
            HashMap<String, Object> hashMap = new HashMap();
            DeviceHelper instance = DeviceHelper.getInstance(this.service.f186a);
            hashMap.put("deviceid", instance.getDeviceKey());
            hashMap.put(C2116b.f11123f, this.service.getAppKey());
            hashMap.put("apppkg", instance.getPackageName());
            hashMap.put("appver", Integer.valueOf(instance.getAppVersion()));
            hashMap.put("sdkver", Integer.valueOf(this.service.getServiceVersionInt()));
            hashMap.put("plat", Integer.valueOf(1));
            hashMap.put("networktype", instance.getDetailNetworkTypeForStatic());
            hashMap.put("deviceData", instance.getDeviceDataNotAES());
            return hashMap;
        }

        public final String toString() {
            return new Hashon().fromHashMap(toMap());
        }
    }

    void m398a(Context context) {
        this.f186a = context;
    }

    void m399a(String str) {
        this.f187b = str;
    }

    public String getAppKey() {
        return this.f187b;
    }

    public Context getContext() {
        return this.f186a;
    }

    public String getDeviceKey() {
        return DeviceHelper.getInstance(this.f186a).getDeviceKey();
    }

    protected abstract int getServiceVersionInt();

    public abstract String getServiceVersionName();

    public void onBind() {
    }

    public void onUnbind() {
    }
}
