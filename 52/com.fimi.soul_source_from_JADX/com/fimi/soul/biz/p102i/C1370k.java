package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1369k;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.SumFlyDataByMonth;
import com.fimi.soul.entity.UpdateDroneItem;
import com.fimi.soul.entity.UpdatePlannerBackdata;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.i.k */
public class C1370k implements C1369k {
    public PlaneMsg m9118a(Context context) {
        return null;
    }

    public PlaneMsg m9119a(UpdateDroneItem updateDroneItem, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPlaneAction"));
        arrayList.add(new BasicNameValuePair("flyJourney", updateDroneItem.getDistance() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("flyTimeSum", updateDroneItem.getSportTime() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("flyFileUrl", updateDroneItem.getUploadurl()));
        arrayList.add(new BasicNameValuePair("planeID", updateDroneItem.getPlaneID() == null ? "123" : updateDroneItem.getPlaneID()));
        arrayList.add(new BasicNameValuePair("userID", C1236a.m8533b(context).getUserID()));
        arrayList.add(new BasicNameValuePair("startDate", updateDroneItem.getRecord_time()));
        arrayList.add(new BasicNameValuePair("endDate", updateDroneItem.getEndddata()));
        arrayList.add(new BasicNameValuePair("year", updateDroneItem.getYear() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("month", updateDroneItem.getMonth() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("maxHight", updateDroneItem.getMaxhight() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("latitude", updateDroneItem.getLatitude() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("latitude", updateDroneItem.getLongitude() + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("fcType ", updateDroneItem.getFcType()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            UpdatePlannerBackdata updatePlannerBackdata = (UpdatePlannerBackdata) ai.m12249b(UpdatePlannerBackdata.class, jSONObject.get(UriUtil.DATA_SCHEME).toString());
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
            planeMsg.setData(updatePlannerBackdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9120a(String str, Context context) {
        return null;
    }

    public PlaneMsg m9121a(String str, String str2, int i, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getPlaneActionListByMonth"));
        arrayList.add(new BasicNameValuePair("userID", str));
        arrayList.add(new BasicNameValuePair("monthDate", str2));
        arrayList.add(new BasicNameValuePair("curPage", Constants.VIA_TO_TYPE_QQ_GROUP));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            ArrayList a = ai.m12246a(UpdatePlannerBackdata[].class, jSONObject.get(UriUtil.DATA_SCHEME).toString());
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
            planeMsg.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9122b(UpdateDroneItem updateDroneItem, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "delPlaneActoin"));
        arrayList.add(new BasicNameValuePair("planeActionID", updateDroneItem.getFlag() + C2915a.f14760f));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public boolean m9123b(String str, Context context) {
        return false;
    }

    public PlaneMsg m9124c(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPlaneActionList"));
        arrayList.add(new BasicNameValuePair("jsonContent", str));
        String a = NetUtil.m12197a(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(a);
            ArrayList a2 = ai.m12246a(UpdatePlannerBackdata[].class, jSONObject.get(UriUtil.DATA_SCHEME).toString());
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
            planeMsg.setData(a2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9125d(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getPlaneActionListByUserID"));
        arrayList.add(new BasicNameValuePair("userID", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            ArrayList a = ai.m12246a(UpdatePlannerBackdata[].class, jSONObject.get(UriUtil.DATA_SCHEME).toString());
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
            planeMsg.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9126e(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "sumFlyDataByMonth"));
        arrayList.add(new BasicNameValuePair("userID", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            ArrayList a = ai.m12246a(SumFlyDataByMonth[].class, jSONObject.get(UriUtil.DATA_SCHEME).toString());
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setCommandCode(string2);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
            planeMsg.setData(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }
}
