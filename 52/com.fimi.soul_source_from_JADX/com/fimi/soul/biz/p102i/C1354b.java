package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1353c;
import com.fimi.soul.entity.BroadcastComment;
import com.fimi.soul.entity.BroadcastMode;
import com.fimi.soul.entity.Page;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.i.b */
public class C1354b implements C1353c {
    public PlaneMsg m9038a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getBroadcastListPaging"));
        arrayList.add(new BasicNameValuePair("userID", C1236a.m8533b(context).getUserID()));
        arrayList.add(new BasicNameValuePair("xiaomiID", C1236a.m8533b(context).getXiaomiID()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastMode[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9039a(Context context, int i) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getNewBroadcastList"));
        arrayList.add(new BasicNameValuePair("userID", C1236a.m8533b(context).getUserID()));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("pageSize", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastMode[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9040a(Context context, BroadcastComment broadcastComment) {
        String broadcastID = broadcastComment.getBroadcastID();
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addBroadcastComment"));
        arrayList.add(new BasicNameValuePair(RMsgInfo.COL_CONTENT, broadcastComment.getContent()));
        arrayList.add(new BasicNameValuePair("broadcastID", broadcastComment.getBroadcastID()));
        arrayList.add(new BasicNameValuePair("userID", broadcastComment.getUserID()));
        arrayList.add(new BasicNameValuePair("userCommentID", broadcastComment.getUserCommentID()));
        arrayList.add(new BasicNameValuePair("userCommentName", broadcastComment.getUserCommentName()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (z) {
                return m9046b(context, broadcastID, 1);
            }
            planeMsg.setCommandCode(string);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
            return planeMsg;
        } catch (JSONException e) {
            e.printStackTrace();
            return planeMsg;
        }
    }

    public PlaneMsg m9041a(Context context, String str, int i) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getBroadcastListPaging"));
        arrayList.add(new BasicNameValuePair("userID", str));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastMode[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9042a(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPraise"));
        arrayList.add(new BasicNameValuePair("broadcastID", str));
        arrayList.add(new BasicNameValuePair("userID", str2));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((BroadcastMode) ai.m12249b(BroadcastMode.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9043a(BroadcastMode broadcastMode, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addBroadcast"));
        arrayList.add(new BasicNameValuePair(RMsgInfo.COL_CONTENT, broadcastMode.getContent()));
        arrayList.add(new BasicNameValuePair("imgUrl", broadcastMode.getImgUrl()));
        arrayList.add(new BasicNameValuePair("userID", broadcastMode.getUserID()));
        arrayList.add(new BasicNameValuePair("objectName", broadcastMode.getObjectName()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((BroadcastMode) ai.m12249b(BroadcastMode.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9044a(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getContentById"));
        arrayList.add(new BasicNameValuePair("contentid", str));
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
                planeMsg.setData((BroadcastMode) ai.m12249b(BroadcastMode.class, jSONObject2));
                b = jSONObject2;
            }
            BroadcastMode broadcastMode = (BroadcastMode) ai.m12249b(BroadcastMode.class, b);
            planeMsg.setCommandCode(string2);
            planeMsg.setData(broadcastMode);
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

    public PlaneMsg m9045b(Context context, int i) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getHotBroadCastList"));
        arrayList.add(new BasicNameValuePair("userID", C1236a.m8533b(context).getUserID()));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("pageSize", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastMode[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9046b(Context context, String str, int i) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getBroadcastCommentList"));
        arrayList.add(new BasicNameValuePair("broadcastID", str));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastComment[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9047b(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addBroadcastCommentPraise"));
        arrayList.add(new BasicNameValuePair("broadcastCommentID", str));
        arrayList.add(new BasicNameValuePair("userID", str2));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((BroadcastComment) ai.m12249b(BroadcastComment.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9048b(BroadcastMode broadcastMode, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "delBroadcast"));
        arrayList.add(new BasicNameValuePair("broadCastID", broadcastMode.getBroadcastID()));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public boolean m9049b(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "delContentById"));
        arrayList.add(new BasicNameValuePair("contentid", str));
        try {
            return new JSONObject(NetUtil.m12204b(C1236a.f5612j, arrayList, context)).getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PlaneMsg m9050c(Context context, String str, int i) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getAttentionBroadcastList"));
        arrayList.add(new BasicNameValuePair("userID", str));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("pageSize", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(BroadcastMode[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
            }
            if (!jSONObject.isNull("page")) {
                planeMsg.setPage((Page) ai.m12249b(Page.class, jSONObject.getJSONObject("page").toString()));
            }
            planeMsg.setCommandCode(string2);
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

    public PlaneMsg m9051c(BroadcastMode broadcastMode, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addInform"));
        arrayList.add(new BasicNameValuePair("userID", broadcastMode.getUserID()));
        arrayList.add(new BasicNameValuePair(RMsgInfo.COL_CONTENT, broadcastMode.getContent()));
        arrayList.add(new BasicNameValuePair("broadcastID", broadcastMode.getBroadcastID()));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9052c(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getBroadCast"));
        arrayList.add(new BasicNameValuePair("broadcastID", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((BroadcastMode) ai.m12249b(BroadcastMode.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
            }
            planeMsg.setCommandCode(string2);
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
}
