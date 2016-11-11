package com.fimi.soul.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ay;
import com.loopj.android.http.AsyncHttpClient;
import com.tencent.mm.sdk.contact.RContact;
import com.xiaomi.market.sdk.C2537j;

/* renamed from: com.fimi.soul.base.a */
public class C1236a {
    public static final String f5577A = "1465961173";
    public static final String f5578B = "f423350548790693b784577448fd7d15";
    public static final String f5579C = "miserver_preferen_";
    public static final String f5580D = "temp_update.apk";
    public static final int f5581E = 0;
    public static final int f5582F = 1;
    public static final String f5583G = "check_firmware_model";
    public static final int f5584H = 0;
    public static final int f5585I = 1;
    public static final String f5586J = "connect_success";
    public static final int f5587K = 0;
    public static final String f5588L = "is_setting_enter";
    public static final int f5589M = 1;
    public static final int f5590N = 2;
    public static final String f5591O = "wifiDistanceFile";
    public static final String f5592P = "ispopDialog";
    public static final String f5593Q = "isdeleteTF";
    public static final String f5594R = "isdirectcalicompass";
    public static final String f5595S = "sava_flight_all_time";
    public static final String f5596T = "save_total_flight_time";
    public static final String f5597U = "sava_flight_cur_time";
    public static final String f5598V = "sava_flight_cur_count";
    public static final String f5599W = "sava_flight_cur_range";
    public static final String f5600X = "sava_flight_all_range";
    public static final int f5601Y = 4;
    public static final String f5602Z = "sp_upgrade_xml";
    public static final boolean f5603a = false;
    private static User aa = null;
    public static final int f5604b = 1;
    public static final int f5605c = 2;
    public static final String f5606d = "MiPlaneApp";
    public static final boolean f5607e = true;
    public static final boolean f5608f = false;
    public static final String f5609g = "https://drone.fimi.com";
    public static final String f5610h = "https://drone.fimi.com/statement.html";
    public static final String f5611i = "https://drone.fimi.com/privacy.html";
    public static final String f5612j = "https://drone.fimi.com/android/fimi.service";
    public static final String f5613k = "";
    public static final String f5614l = "yyyy-MM-dd HH:mm:ss";
    public static final String f5615m = "MM-dd HH:mm";
    public static final String f5616n = "https://files.fds.api.xiaomi.com";
    public static final String f5617o = "dronedata";
    public static final String f5618p = "HmacSHA1";
    public static final String f5619q = "2882303761517328945";
    public static final String f5620r = "5101732874945";
    public static final String f5621s = "sp_new_hand";
    public static final String f5622t = "wx63bc994fd4e454c2";
    public static final String f5623u = "18665971360";
    public static final String f5624v = "fimi12345";
    public static final String f5625w = "b2d5437b351877d9";
    public static final String f5626x = "5c4873e37f2de34e4900cff10349ce6c";
    public static final String f5627y = "XiaoMi";
    public static final String f5628z = "2882303761517328945";

    static {
        aa = new User();
    }

    public static void m8528a(Context context) {
        SharedPreferences a = ay.m12293a(context);
        aa.setName(a.getString(User.FN_NAME, "\u5c0f\u98de"));
        aa.setNickName(a.getString(RContact.COL_NICKNAME, "\u98de\u98de"));
        aa.setSex(a.getString("sex", "\u7537"));
        aa.setSignature(a.getString(C2537j.f12842Z, f5613k));
        aa.setXiaomiID(a.getString("xiaomiId", f5613k));
        aa.setUserIDs(a.getString("userIDs", f5613k));
        aa.setUserID(a.getString("userID", f5613k));
        aa.setUserImgUrl(a.getString("userImgUrl", f5613k));
        aa.setCurLatitude(a.getString("curLatitude", f5613k));
        aa.setCurLongitude(a.getString("curLongitude", f5613k));
        aa.setPhone(a.getString("phone", f5613k));
    }

    public static void m8529a(Context context, User user) {
        C1236a.m8530a(user);
        Editor edit = ay.m12293a(context).edit();
        edit.putString(User.FN_NAME, aa.getName());
        edit.putString(RContact.COL_NICKNAME, aa.getNickName());
        edit.putString("sex", aa.getSex());
        edit.putString("device", aa.getDevice());
        edit.putString(C2537j.f12842Z, aa.getSignature());
        edit.putString("userIDs", aa.getUserIDs());
        edit.putString("phone", aa.getPhone());
        edit.putString("xiaomiId", aa.getXiaomiID());
        edit.putString("userID", aa.getUserID());
        edit.putString("isfirstloading", f5613k);
        edit.putString("userImgUrl", aa.getUserImgUrl());
        edit.putString("objectName", aa.getObjectName());
        edit.putString("curLongitude", aa.getCurLongitude());
        edit.putString("curLatitude", aa.getCurLatitude());
        edit.commit();
    }

    public static void m8530a(User user) {
        aa.setCountry(user.getCountry());
        aa.setCurLatitude(user.getCurLatitude());
        aa.setCurLongitude(user.getCurLongitude());
        aa.setDevice(user.getDevice());
        aa.setPhone(user.getPhone());
        aa.setName(user.getName());
        aa.setNickName(user.getNickName());
        aa.setSex(user.getSex());
        aa.setSignature(user.getSignature());
        aa.setUserIDs(user.getUserIDs());
        aa.setUserImgUrl(user.getUserImgUrl());
        aa.setXiaomiID(user.getXiaomiID());
        aa.setUserID(user.getUserID());
        aa.setCurLatitude(user.getCurLatitude());
        aa.setCurLongitude(user.getCurLongitude());
    }

    public static void m8531a(AsyncHttpClient asyncHttpClient, SharedPreferences sharedPreferences) {
        asyncHttpClient.addHeader("Cookie", "name=" + sharedPreferences.getString("cookie_name", f5613k));
        asyncHttpClient.addHeader("Cookie", "password=" + sharedPreferences.getString("cookie_password", f5613k));
        asyncHttpClient.addHeader("Cookie", "JSESSIONID=" + NetUtil.f10003a);
        asyncHttpClient.addHeader("Cookie", "key=" + sharedPreferences.getString("cookie_key", f5613k));
        asyncHttpClient.addHeader("Cookie", "datasourceName=" + sharedPreferences.getString("cookie_datasourceName", f5613k));
    }

    public static void m8532a(String str, Class cls) {
    }

    public static User m8533b(Context context) {
        if (TextUtils.isEmpty(aa.getUserID())) {
            C1236a.m8528a(context);
        }
        return aa;
    }

    public static void m8534c(Context context) {
        Editor edit = ay.m12293a(context).edit();
        edit.remove("userID");
        edit.commit();
        aa.setUserID(f5613k);
        aa.setName(f5613k);
        aa.setNickName(f5613k);
        aa.setSignature(f5613k);
        aa.setCountry(f5613k);
    }

    public static final boolean m8535d(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return (locationManager.isProviderEnabled(GeocodeSearch.GPS) || locationManager.isProviderEnabled("network")) ? f5607e : f5608f;
    }
}
