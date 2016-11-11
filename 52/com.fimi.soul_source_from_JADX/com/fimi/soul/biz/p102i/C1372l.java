package com.fimi.soul.biz.p102i;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.services.district.DistrictSearchQuery;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1371l;
import com.fimi.soul.biz.p099f.C1333a;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.biz.p103k.ab;
import com.fimi.soul.entity.BatteryOverDischange;
import com.fimi.soul.entity.FlightTimeInfo;
import com.fimi.soul.entity.HomePage;
import com.fimi.soul.entity.NearPerson;
import com.fimi.soul.entity.Page;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.Relation;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.AuthorizeApi;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.i.l */
public class C1372l implements C1371l {
    private static String f6007a;
    private C1394s f6008b;

    static {
        f6007a = XiaomiOAuthConstants.OPEN_API_PATH_PROFILE;
    }

    public C1372l(Context context) {
        this.f6008b = C1394s.m9340a(context);
    }

    public PlaneMsg m9146a(Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getAllUsers"));
        String str = C1236a.f5612j;
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            ArrayList a = ai.m12246a(User[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString());
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

    public PlaneMsg m9147a(Relation relation, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addUserRelation"));
        arrayList.add(new BasicNameValuePair("userID", relation.getUserID()));
        arrayList.add(new BasicNameValuePair("relationID", relation.getRelationID()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((Relation) ai.m12249b(Relation.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9148a(User user, int i, String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "selectNearbyPerson"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("sex", user.getSex()));
        arrayList.add(new BasicNameValuePair("curLongitude", user.getCurLongitude()));
        arrayList.add(new BasicNameValuePair("curLatitude", user.getCurLatitude()));
        arrayList.add(new BasicNameValuePair("curPage", i + C2915a.f14760f));
        arrayList.add(new BasicNameValuePair("pageSize", "20"));
        arrayList.add(new BasicNameValuePair("distance", str));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData(ai.m12246a(NearPerson[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9149a(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", MiPushClient.COMMAND_REGISTER));
        arrayList.add(new BasicNameValuePair(User.FN_NAME, user.getName()));
        arrayList.add(new BasicNameValuePair(C2537j.f12842Z, user.getSignature()));
        arrayList.add(new BasicNameValuePair(DistrictSearchQuery.KEYWORDS_COUNTRY, user.getCountry()));
        arrayList.add(new BasicNameValuePair("nickName", user.getNickName()));
        arrayList.add(new BasicNameValuePair("userIDs", user.getUserIDs()));
        arrayList.add(new BasicNameValuePair("xiaomiID", user.getXiaomiID()));
        arrayList.add(new BasicNameValuePair("device", user.getDevice()));
        arrayList.add(new BasicNameValuePair("sex", user.getSex()));
        arrayList.add(new BasicNameValuePair("phone", user.getPhone()));
        arrayList.add(new BasicNameValuePair("userImgUrl", user.getUserImgUrl()));
        Location d = this.f6008b.m9352d();
        if (d != null && ab.m9180a(context).m9188d()) {
            arrayList.add(new BasicNameValuePair("curLongitude", this.f6008b.m9349b() + C2915a.f14760f));
            arrayList.add(new BasicNameValuePair("curLatitude", this.f6008b.m9345a() + C2915a.f14760f));
        }
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                User user2 = (User) ai.m12249b(User.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString());
                if (!(d == null || 0.0d == d.getLatitude() || 0.0d == d.getLongitude())) {
                    user2.setCurLongitude(this.f6008b.m9349b() + C2915a.f14760f);
                    user2.setCurLatitude(this.f6008b.m9345a() + C2915a.f14760f);
                }
                if (z) {
                    C1236a.m8529a(context, user2);
                }
                planeMsg.setData(user2);
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

    public PlaneMsg m9150a(String str, int i, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "selectNickName"));
        arrayList.add(new BasicNameValuePair("nickName", str));
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
                planeMsg.setData(ai.m12246a(User[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9151a(String str, long j, long j2, String str2, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPlaneAction"));
        arrayList.add(new BasicNameValuePair("planeID", str));
        arrayList.add(new BasicNameValuePair("flyTime", String.valueOf(j)));
        arrayList.add(new BasicNameValuePair("flyJourney", String.valueOf(j2)));
        arrayList.add(new BasicNameValuePair("userID", str2));
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
            planeMsg.setData(ai.m12249b(FlightTimeInfo.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9152a(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getUserById"));
        arrayList.add(new BasicNameValuePair("userid", str));
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
                planeMsg.setData((User) ai.m12249b(User.class, jSONObject2));
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

    public PlaneMsg m9153a(String str, String str2, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "login"));
        arrayList.add(new BasicNameValuePair(User.FN_NAME, str));
        arrayList.add(new BasicNameValuePair("pwd", str2));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                planeMsg.setData((User) ai.m12249b(User.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9154b(Context context) {
        PlaneMsg planeMsg = new PlaneMsg();
        String str = C2915a.f14760f;
        try {
            try {
                JSONObject jSONObject = new JSONObject(AuthorizeApi.doHttpGet(context, f6007a, Long.parseLong(C1236a.f5628z), C1333a.m8958c(context, 0), C1333a.m8956b(context, 0), C1333a.m8954a(context, 0)));
                if (jSONObject.getString("result").equals("ok")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME);
                    User user = new User();
                    user.setXiaomiID(jSONObject2.getString("userId"));
                    user.setName(jSONObject2.getString("miliaoNick"));
                    user.setNickName(jSONObject2.getString("miliaoNick"));
                    user.setDevice(be.m12353a(context));
                    if (!jSONObject2.isNull("sex") && jSONObject2.getString("sex").equals(context.getResources().getString(C1205R.string.man))) {
                        user.setSex(Constants.VIA_TO_TYPE_QQ_GROUP);
                    } else if (!jSONObject2.isNull("sex") && jSONObject2.getString("sex").equals(context.getResources().getString(C1205R.string.woman))) {
                        user.setSex(Constants.VIA_RESULT_SUCCESS);
                    }
                    return m9149a(user, context);
                }
                if (jSONObject.getString("result").equals(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    str = jSONObject.getString(SocialConstants.PARAM_COMMENT);
                    planeMsg.setErrorCode(jSONObject.getString(XiaomiOAuthConstants.EXTRA_CODE_2));
                    planeMsg.setErrorMessage(str);
                    planeMsg.setSuccess(false);
                }
                return planeMsg;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            str = context.getString(C1205R.string.login_xiaomi_account_sync_fail);
            planeMsg.setErrorCode("110");
            planeMsg.setErrorMessage(str);
            planeMsg.setSuccess(false);
            C1236a.m8532a(str, C1372l.class);
            return planeMsg;
        }
    }

    public PlaneMsg m9155b(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "editUserImage"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("userImgUrl", user.getUserImgUrl()));
        arrayList.add(new BasicNameValuePair("objectName", user.getObjectName()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                User user2 = (User) ai.m12249b(User.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString());
                if (z) {
                    C1236a.m8529a(context, user2);
                }
                planeMsg.setData(user2);
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

    public PlaneMsg m9156b(String str, int i, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getUserRelations"));
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
                planeMsg.setData(ai.m12246a(Relation[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9157b(String str, String str2, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "editPushRegID"));
        arrayList.add(new BasicNameValuePair("userID", str));
        arrayList.add(new BasicNameValuePair("regID", str2));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        C1236a.m8532a(b, C1372l.class);
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

    public boolean m9158b(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "delUserById"));
        arrayList.add(new BasicNameValuePair("userid", str));
        try {
            return new JSONObject(NetUtil.m12204b(C1236a.f5612j, arrayList, context)).getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PlaneMsg m9159c(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "editUser"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("sex", user.getSex()));
        arrayList.add(new BasicNameValuePair(C2537j.f12842Z, user.getSignature()));
        arrayList.add(new BasicNameValuePair("nickName", user.getNickName()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                User user2 = (User) ai.m12249b(User.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString());
                if (z) {
                    C1236a.m8529a(context, user2);
                }
                planeMsg.setData(user2);
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

    public PlaneMsg m9160c(String str, int i, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getRelationUserList"));
        arrayList.add(new BasicNameValuePair("relationID", str));
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
                planeMsg.setData(ai.m12246a(Relation[].class, jSONObject.getJSONArray(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9161c(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addPlaneActionList"));
        arrayList.add(new BasicNameValuePair("jsonContent", str));
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
            planeMsg.setData(ai.m12249b(FlightTimeInfo.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9162d(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "sumPlaneFlyTime"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        HomePage homePage = new HomePage();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            boolean z = jSONObject.getBoolean("success");
            if (jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                homePage.setFlyNum(Constants.VIA_RESULT_SUCCESS);
                homePage.setSumPlaneFlyTime(Constants.VIA_RESULT_SUCCESS);
            } else {
                jSONObject = jSONObject.getJSONObject(UriUtil.DATA_SCHEME);
                homePage.setFlyNum(jSONObject.getString("flyNum"));
                homePage.setSumPlaneFlyTime(jSONObject.getString("sumFlyTime"));
                String string2 = jSONObject.getString("sumFlyTime");
                if (string2 == null || string2.equals(C2915a.f14760f) || string2.equals(Constants.VIA_RESULT_SUCCESS) || string2.equals("null")) {
                    homePage.setFlyNum(Constants.VIA_RESULT_SUCCESS);
                    homePage.setSumPlaneFlyTime(Constants.VIA_RESULT_SUCCESS);
                } else {
                    homePage.setSumPlaneFlyTime(C2915a.f14760f + com.fimi.kernel.p084e.ab.m8015a((double) Float.valueOf(Float.valueOf(Float.parseFloat(string2)).floatValue() / BitmapDescriptorFactory.HUE_YELLOW).floatValue(), 2));
                }
            }
            if (z && C1236a.m8533b(context).getUserID().equals(user.getUserID())) {
                Editor edit = ay.m12293a(context).edit();
                edit.putString("sumPlaneFlyTime", homePage.getSumPlaneFlyTime());
                edit.putString("flyNum", homePage.getFlyNum());
                edit.commit();
            }
            planeMsg.setCommandCode(string);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
            planeMsg.setData(homePage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9163d(String str, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "addOverdischarge"));
        arrayList.add(new BasicNameValuePair("jsonContent", str));
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
            planeMsg.setData(ai.m12249b(BatteryOverDischange.class, jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString()));
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

    public PlaneMsg m9164e(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getIsAttention"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("relationID", user.getCountry()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            JSONObject jSONObject = new JSONObject(b);
            b = jSONObject.getString("errorMessage");
            String string = jSONObject.getString("commandCode");
            String string2 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            planeMsg.setData(jSONObject.getString(UriUtil.DATA_SCHEME));
            planeMsg.setCommandCode(string);
            planeMsg.setErrorCode(string2);
            planeMsg.setErrorMessage(b);
            planeMsg.setSuccess(z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9165f(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "clearLocation"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        C1236a.m8532a(b, C1372l.class);
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

    public PlaneMsg m9166g(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "exitAircraft"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        C1236a.m8534c(context);
        String b = NetUtil.m12204b(C1236a.f5612j, arrayList, context);
        C1236a.m8532a(b, C1372l.class);
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            User user2;
            JSONObject jSONObject = new JSONObject(b);
            String string = jSONObject.getString("errorMessage");
            String string2 = jSONObject.getString("commandCode");
            String string3 = jSONObject.getString("errorCode");
            boolean z = jSONObject.getBoolean("success");
            if (!jSONObject.isNull(UriUtil.DATA_SCHEME)) {
                String jSONObject2 = jSONObject.getJSONObject(UriUtil.DATA_SCHEME).toString();
                user2 = (User) ai.m12249b(User.class, jSONObject2);
                planeMsg.setData(user);
                b = jSONObject2;
            }
            user2 = (User) ai.m12249b(User.class, b);
            planeMsg.setCommandCode(string2);
            planeMsg.setData(user);
            planeMsg.setErrorCode(string3);
            planeMsg.setErrorMessage(string);
            planeMsg.setSuccess(z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planeMsg;
    }

    public PlaneMsg m9167h(User user, Context context) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "editPhone"));
        arrayList.add(new BasicNameValuePair("userID", user.getUserID()));
        arrayList.add(new BasicNameValuePair("phone", user.getPhone()));
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
}
