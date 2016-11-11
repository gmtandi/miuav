package com.p016a;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.market.sdk.C2537j;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.au */
public class au implements C0239w<av> {
    private av f574a;

    public static String m1085a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        return C0261v.m2042a(hashMap);
    }

    public static String m1086a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("dynamicversion", str2);
        return C0261v.m2042a(hashMap);
    }

    public static String m1087a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        hashMap.put("sdkname", str2);
        hashMap.put("dynamicversion", str4);
        hashMap.put(C2537j.aq, str3);
        return C0261v.m2042a(hashMap);
    }

    public static String m1088b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        return C0261v.m2042a(hashMap);
    }

    public static String m1089b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put(RMsgInfo.COL_STATUS, str2);
        return C0261v.m2042a(hashMap);
    }

    public ContentValues m1090a() {
        if (this.f574a == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("filename", this.f574a.m1096a());
        contentValues.put("md5", this.f574a.m1098b());
        contentValues.put("sdkname", this.f574a.m1099c());
        contentValues.put(C2537j.aq, this.f574a.m1100d());
        contentValues.put("dynamicversion", this.f574a.m1101e());
        contentValues.put(RMsgInfo.COL_STATUS, this.f574a.m1102f());
        return contentValues;
    }

    public /* synthetic */ Object m1091a(Cursor cursor) {
        return m1094b(cursor);
    }

    public void m1092a(av avVar) {
        this.f574a = avVar;
    }

    public /* synthetic */ void m1093a(Object obj) {
        m1092a((av) obj);
    }

    public av m1094b(Cursor cursor) {
        String string = cursor.getString(1);
        String string2 = cursor.getString(2);
        String string3 = cursor.getString(3);
        String string4 = cursor.getString(4);
        String string5 = cursor.getString(5);
        return new aw(string2, string3, string, string4, string5).m1110a(cursor.getString(6)).m1109a();
    }

    public String m1095b() {
        return UriUtil.LOCAL_FILE_SCHEME;
    }
}
