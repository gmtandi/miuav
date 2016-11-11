package com.mob.commons.appcollector;

import android.content.Context;
import android.text.TextUtils;
import com.mi.live.openlivesdk.C2116b;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.util.ArrayList;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.mob.commons.appcollector.a */
public class C2149a {
    private static C2149a f11315b;
    private final String f11316a;
    private int f11317c;
    private int f11318d;
    private int f11319e;
    private int f11320f;
    private int f11321g;
    private long f11322h;
    private Context f11323i;
    private String f11324j;

    private C2149a(Context context, String str) {
        this.f11316a = "http://cca.mob.com:80/caconf";
        this.f11323i = context.getApplicationContext();
        this.f11324j = str;
    }

    private int m13154a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        switch (str.charAt(0)) {
            case Opcodes.ISUB /*100*/:
                return 86400;
            case Opcodes.IMUL /*104*/:
                return 3600;
            case Opcodes.LDIV /*109*/:
                return 2592000;
            case Opcodes.DNEG /*119*/:
                return 604800;
            default:
                return 0;
        }
    }

    public static synchronized C2149a m13155a(Context context, String str) {
        C2149a c2149a;
        synchronized (C2149a.class) {
            if (f11315b == null) {
                f11315b = new C2149a(context, str);
            }
            c2149a = f11315b;
        }
        return c2149a;
    }

    private synchronized void m13156f() {
        if (0 == this.f11322h || this.f11322h + MiStatInterface.MAX_UPLOAD_INTERVAL < System.currentTimeMillis()) {
            try {
                NetworkHelper networkHelper = new NetworkHelper();
                DeviceHelper instance = DeviceHelper.getInstance(this.f11323i);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new KVPair(C2116b.f11123f, this.f11324j));
                arrayList.add(new KVPair("plat", String.valueOf(instance.getPlatformCode())));
                arrayList.add(new KVPair("apppkg", instance.getPackageName()));
                arrayList.add(new KVPair("appver", instance.getAppVersionName()));
                arrayList.add(new KVPair("networktype", instance.getDetailNetworkTypeForStatic()));
                NetworkTimeOut networkTimeOut = new NetworkTimeOut();
                networkTimeOut.readTimout = 30000;
                networkTimeOut.connectionTimeout = 30000;
                String httpGet = networkHelper.httpGet("http://cca.mob.com:80/caconf", arrayList, null, networkTimeOut);
                Map fromJson = new Hashon().fromJson(httpGet);
                if (fromJson == null) {
                    throw new Throwable("response is illegal: " + httpGet);
                }
                String valueOf = String.valueOf(fromJson.get(RMsgInfo.COL_STATUS));
                if ("200".equals(valueOf)) {
                    fromJson = (Map) C2178R.forceCast(fromJson.get("switchs"));
                    if (fromJson == null) {
                        throw new Throwable("response is illegal: " + httpGet);
                    }
                    this.f11317c = ((Integer) fromJson.get("in")).intValue();
                    this.f11318d = ((Integer) fromJson.get("all")).intValue();
                    this.f11321g = m13154a((String) fromJson.get("agap"));
                    this.f11319e = ((Integer) fromJson.get("un")).intValue();
                    this.f11320f = ((Integer) fromJson.get("rt")).intValue();
                    this.f11322h = System.currentTimeMillis();
                } else {
                    throw new Throwable("response is illegal: " + valueOf.toString());
                }
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }
    }

    public boolean m13157a() {
        m13156f();
        return this.f11317c == 1;
    }

    public boolean m13158b() {
        m13156f();
        return this.f11318d == 1;
    }

    public int m13159c() {
        m13156f();
        return this.f11321g;
    }

    public boolean m13160d() {
        m13156f();
        return this.f11319e == 1;
    }

    public boolean m13161e() {
        m13156f();
        return this.f11320f == 1;
    }
}
