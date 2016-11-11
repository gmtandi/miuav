package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1362h;
import com.fimi.soul.entity.Plane;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import com.xiaomi.market.sdk.C2537j;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.i.g */
public class C1363g implements C1362h {
    public PlaneMsg m9086a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getAllPlane"));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            ArrayList a = ai.m12246a(Plane[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString());
            planeMsg.setCommandCode(string);
            planeMsg.setData(a);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e2) {
            e2.printStackTrace();
        } catch (JsonMappingException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9087a(Plane plane, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPlane"));
        arrayList.add(new BasicNameValuePair(C2537j.aq, plane.getVersion()));
        arrayList.add(new BasicNameValuePair("flyControlID", plane.getFlyControlID()));
        arrayList.add(new BasicNameValuePair("rcIC", plane.getRcIC()));
        arrayList.add(new BasicNameValuePair("userId", plane.getUser().getUserIDs() + C2915a.f14760f));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            User user = (User) ai.m12249b(User.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString());
            planeMsg.setCommandCode(string2);
            planeMsg.setData(user);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e2) {
            e2.printStackTrace();
        } catch (JsonMappingException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9088a(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getPlaneById"));
        arrayList.add(new BasicNameValuePair("planeid", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                String jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString();
                planeMsg.setData((Plane) ai.m12249b(Plane.class, jSONObject2));
                b = jSONObject2;
            }
            User user = (User) ai.m12249b(User.class, b);
            planeMsg.setCommandCode(string2);
            planeMsg.setData(user);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonParseException e2) {
            e2.printStackTrace();
        } catch (JsonMappingException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return planeMsg;
    }

    public boolean m9089b(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "delPlaneById"));
        arrayList.add(new BasicNameValuePair("planeid", str));
        try {
            return new JSONObject(NetUtil.m12204b(C1236a.f5612j, arrayList, context)).getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
