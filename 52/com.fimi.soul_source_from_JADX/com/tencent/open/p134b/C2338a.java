package com.tencent.open.p134b;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.tencent.open.p133a.C2333f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.open.b.a */
public class C2338a {
    protected static final String f12022a;
    protected static final Uri f12023b;

    static {
        f12022a = C2338a.class.getName();
        f12023b = Uri.parse("content://telephony/carriers/preferapn");
    }

    public static String m13772a(Context context) {
        int d = C2338a.m13775d(context);
        if (d == 2) {
            return "wifi";
        }
        if (d == 1) {
            return "cmwap";
        }
        if (d == 4) {
            return "cmnet";
        }
        if (d == 16) {
            return "uniwap";
        }
        if (d == 8) {
            return "uninet";
        }
        if (d == 64) {
            return "wap";
        }
        if (d == 32) {
            return "net";
        }
        if (d == Opcodes.ACC_INTERFACE) {
            return "ctwap";
        }
        if (d == Opcodes.ACC_NATIVE) {
            return "ctnet";
        }
        if (d == Opcodes.ACC_STRICT) {
            return "3gnet";
        }
        if (d == SmileConstants.MAX_SHARED_STRING_VALUES) {
            return "3gwap";
        }
        String b = C2338a.m13773b(context);
        return (b == null || b.length() == 0) ? "none" : b;
    }

    public static String m13773b(Context context) {
        try {
            Cursor query = context.getContentResolver().query(f12023b, null, null, null, null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("apn"));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (SecurityException e) {
            C2333f.m13759e(f12022a, "getApn has exception: " + e.getMessage());
            return C2915a.f14760f;
        }
    }

    public static String m13774c(Context context) {
        try {
            Cursor query = context.getContentResolver().query(f12023b, null, null, null, null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("proxy"));
            if (query == null) {
                return string;
            }
            query.close();
            return string;
        } catch (SecurityException e) {
            C2333f.m13759e(f12022a, "getApnProxy has exception: " + e.getMessage());
            return C2915a.f14760f;
        }
    }

    public static int m13775d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                return 2;
            }
            String toLowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            if (toLowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("epc.tmobile.com")) {
                return 4;
            }
            if (toLowerCase.startsWith("uniwap")) {
                return 16;
            }
            if (toLowerCase.startsWith("uninet")) {
                return 8;
            }
            if (toLowerCase.startsWith("wap")) {
                return 64;
            }
            if (toLowerCase.startsWith("net")) {
                return 32;
            }
            if (toLowerCase.startsWith("ctwap")) {
                return Opcodes.ACC_INTERFACE;
            }
            if (toLowerCase.startsWith("ctnet")) {
                return Opcodes.ACC_NATIVE;
            }
            if (toLowerCase.startsWith("3gwap")) {
                return SmileConstants.MAX_SHARED_STRING_VALUES;
            }
            if (toLowerCase.startsWith("3gnet")) {
                return Opcodes.ACC_STRICT;
            }
            if (toLowerCase.startsWith("#777")) {
                toLowerCase = C2338a.m13774c(context);
                return (toLowerCase == null || toLowerCase.length() <= 0) ? Opcodes.ACC_NATIVE : Opcodes.ACC_INTERFACE;
            }
            return SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
        } catch (Exception e) {
            C2333f.m13759e(f12022a, "getMProxyType has exception: " + e.getMessage());
        }
    }

    public static String m13776e(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "MOBILE";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : "MOBILE";
    }
}
