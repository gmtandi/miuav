package com.fimi.soul.biz.p089a;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.UpdateDroneInforBean;
import com.fimi.soul.utils.NetUtil;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.a.a */
public class C1240a {
    private C1145b f5633a;
    private UpdateDroneInforBean f5634b;
    private Context f5635c;

    public C1240a(Context context) {
        this.f5633a = null;
        this.f5635c = context;
        this.f5633a = (C1145b) C1189f.m8328a(C1152e.Volley);
        this.f5634b = (UpdateDroneInforBean) C1189f.m8333c().m7926a(C1543c.f7243r, UpdateDroneInforBean.class);
    }

    public void m8537a() {
        if (this.f5634b != null && !C2915a.f14760f.equals(this.f5634b.getPlaneID())) {
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("commandCode", "commitDeviceVersion"));
            arrayList.add(new BasicNameValuePair("userID", this.f5634b.getUserID() + C2915a.f14760f));
            arrayList.add(new BasicNameValuePair("planeID", this.f5634b.getPlaneID()));
            if (C1325k.m8930a().m8942g()) {
                arrayList.add(new BasicNameValuePair("fcType", C1543c.bJ));
            } else {
                arrayList.add(new BasicNameValuePair("fcType", C1543c.bI));
            }
            Gson gson = new Gson();
            gson.toJson(this.f5634b.getmDroneInfoBean());
            arrayList.add(new BasicNameValuePair("jsonStr", gson.toJson(this.f5634b.getmDroneInfoBean())));
            NetUtil.m12201a(arrayList, C1189f.m8327a());
            String format = URLEncodedUtils.format(arrayList, C1142e.f5201a);
            this.f5633a.m7905a(String.format("%s?%s", new Object[]{C1236a.f5612j, format}), new C1241b(this));
        }
    }
}
