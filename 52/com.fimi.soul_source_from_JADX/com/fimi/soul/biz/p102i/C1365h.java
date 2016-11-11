package com.fimi.soul.biz.p102i;

import android.content.Context;
import android.text.TextUtils;
import com.fimi.soul.biz.p098j.C1364j;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.be;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.fimi.soul.biz.i.h */
public class C1365h implements C1364j {
    private Context f6005a;
    private String f6006b;

    public C1365h(Context context) {
        this.f6005a = context;
    }

    public PlaneMsg m9094a(String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("ssid", str + ",pwd=" + str2));
        Object a = NetUtil.m12198a(this.f6006b + "action/setWifi", arrayList, this.f6005a, true);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (!TextUtils.isEmpty(a) && a.contains("ok")) {
                planeMsg.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public String m9095a() {
        return this.f6006b;
    }

    public void m9096a(String str) {
        this.f6006b = str;
    }

    public PlaneMsg m9097b() {
        String a = NetUtil.m12198a(this.f6006b + "dir.log", new ArrayList(), this.f6005a, true);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (!TextUtils.isEmpty(a)) {
                planeMsg.setData(be.m12372c(a));
                planeMsg.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9098c() {
        CharSequence a = NetUtil.m12198a(this.f6006b + "action/getDir", new ArrayList(), this.f6005a, true);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (!TextUtils.isEmpty(a)) {
                planeMsg.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9099d() {
        Object a = NetUtil.m12198a(this.f6006b + "action/reboot", new ArrayList(), this.f6005a, true);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (!TextUtils.isEmpty(a) && a.contains("ok")) {
                planeMsg.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }
}
