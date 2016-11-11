package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1360g;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.NetUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.i.f */
public class C1361f implements C1360g {
    public PlaneMsg m9081a(Context context) {
        return null;
    }

    public PlaneMsg m9082a(User user, String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "applyX2"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("planeID", C1901a.m12002a().m12003a(0).m12059i()));
        arrayList.add(new BasicNameValuePair("sign", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            int i = jSONObject.getInt(UriUtil.DATA_SCHEME);
            planeMsg.setCommandCode(string);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
            planeMsg.setData(Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9083a(String str, Context context) {
        return null;
    }

    public boolean m9084b(String str, Context context) {
        return false;
    }
}
