package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Build.VERSION;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.be;
import com.xiaomi.market.sdk.C2537j;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.k.h */
public class C1384h {
    private static C1384h f6218a;
    private Context f6219b;
    private C1145b f6220c;

    static {
        f6218a = null;
    }

    public C1384h(Context context) {
        this.f6220c = null;
        this.f6219b = context;
        this.f6220c = (C1145b) C1189f.m8328a(C1152e.Volley);
    }

    public static C1384h m9307a(Context context) {
        if (f6218a == null) {
            f6218a = new C1384h(context);
        }
        return f6218a;
    }

    public void m9308a(String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "commitCrashMsg"));
        arrayList.add(new BasicNameValuePair(C2537j.aw, be.m12382e(this.f6219b)));
        arrayList.add(new BasicNameValuePair(C2537j.ax, be.m12378d(this.f6219b)));
        arrayList.add(new BasicNameValuePair(C2537j.f12839W, this.f6219b.getPackageName()));
        arrayList.add(new BasicNameValuePair("crashTime", be.m12366b(System.currentTimeMillis())));
        arrayList.add(new BasicNameValuePair("deviceName", be.m12351a()));
        arrayList.add(new BasicNameValuePair("systemVersion", be.m12365b()));
        arrayList.add(new BasicNameValuePair("sdkVersion", VERSION.SDK_INT + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("channel", "Android"));
        arrayList.add(new BasicNameValuePair("crashMsg", str));
        NetUtil.m12201a(arrayList, this.f6219b);
        String format = URLEncodedUtils.format(arrayList, C1142e.f5201a);
        this.f6220c.m7905a(String.format("%s?%s", new Object[]{C1236a.f5612j, format}), new C1385i(this));
    }
}
