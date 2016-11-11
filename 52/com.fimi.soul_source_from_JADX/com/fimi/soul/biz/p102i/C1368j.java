package com.fimi.soul.biz.p102i;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1367n;
import com.fimi.soul.entity.AppVersion;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.entity.UpgradeResultInfo;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.i.j */
public class C1368j implements C1367n {
    public PlaneMsg m9106a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getDeviceNewVersion"));
        String a = NetUtil.m12198a(C1236a.f5612j, arrayList, context, false);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(a);
            a = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            ArrayList a2 = ai.m12246a(UpdateVersonBean[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString());
            planeMsg.setCommandCode(string);
            planeMsg.setData(a2);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(a);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9107a(Context context, UpgradeResultInfo upgradeResultInfo) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "commitDeviceVersion"));
        arrayList.add(new BasicNameValuePair("userID", upgradeResultInfo.getUserID()));
        arrayList.add(new BasicNameValuePair("jsonStr", upgradeResultInfo.getJsonStr()));
        String a = NetUtil.m12198a(C1236a.f5612j, arrayList, context, false);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(a);
            a = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("errorMessage");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(jSONObject.getString("commandCode"));
            planeMsg.setErrorCode(string);
            planeMsg.setErrorMessage(a);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9108a(Context context, String str) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getVersionContent"));
        arrayList.add(new BasicNameValuePair("appversion", str));
        String a = NetUtil.m12198a(C1236a.f5612j, arrayList, context, false);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(a);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("errorMessage");
            boolean z = jSONObject.getBoolean("success");
            String string3 = jSONObject.getString("commandCode");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((AppVersion) ai.m12249b(AppVersion.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string3);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9109a(String str, Context context) {
        return null;
    }

    public PlaneMsg m9110b(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getDeviceNewVersion"));
        User b = C1236a.m8533b(C1189f.m8327a());
        if (!(b == null || TextUtils.isEmpty(b.getUserID()))) {
            arrayList.add(new BasicNameValuePair("userID", b.getUserID()));
        }
        String a = NetUtil.m12198a(C1236a.f5612j, arrayList, context, false);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(a);
            a = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            String string2 = jSONObject.getString("commandCode");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(UpdateVersonBean[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string);
            planeMsg.setErrorMessage(a);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public boolean m9111b(String str, Context context) {
        return false;
    }
}
