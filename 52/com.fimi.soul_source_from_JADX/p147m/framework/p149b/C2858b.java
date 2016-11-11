package p147m.framework.p149b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import com.fimi.soul.entity.User;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.common.Constants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.openauth.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONArray;
import org.p122a.p123a.C2915a;

/* renamed from: m.framework.b.b */
public class C2858b {
    private static C2858b f14599b;
    private Context f14600a;

    public C2858b(Context context) {
        this.f14600a = context.getApplicationContext();
    }

    public static C2858b m16458a(Context context) {
        if (f14599b == null) {
            f14599b = new C2858b(context);
        }
        return f14599b;
    }

    private boolean m16459a(PackageInfo packageInfo) {
        return ((packageInfo.applicationInfo.flags & 1) == 1) || ((packageInfo.applicationInfo.flags & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 1);
    }

    private boolean m16460w() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f14600a.getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        switch (telephonyManager.getNetworkType()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return false;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return false;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return false;
            case Type.BYTE /*3*/:
                return true;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return false;
            case Type.INT /*5*/:
                return true;
            case Type.FLOAT /*6*/:
                return true;
            case Type.LONG /*7*/:
                return false;
            case Type.DOUBLE /*8*/:
                return true;
            case Type.ARRAY /*9*/:
                return true;
            case Type.OBJECT /*10*/:
                return true;
            case Opcodes.T_LONG /*11*/:
                return false;
            case Opcodes.FCONST_1 /*12*/:
                return true;
            case Opcodes.FCONST_2 /*13*/:
                return true;
            case Opcodes.DCONST_0 /*14*/:
                return true;
            case Opcodes.DCONST_1 /*15*/:
                return true;
            default:
                return false;
        }
    }

    public String m16461a(String str, String str2) {
        String encodeToString;
        Throwable th;
        try {
            encodeToString = Base64.encodeToString(C2857a.m16451a(str2, str), 0);
            try {
                if (encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", C2915a.f14760f);
                }
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return encodeToString;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            encodeToString = null;
            th = th4;
            th.printStackTrace();
            return encodeToString;
        }
        return encodeToString;
    }

    public ArrayList<HashMap<String, String>> m16462a(boolean z) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        try {
            PackageManager packageManager = this.f14600a.getPackageManager();
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
                if (z || !m16459a(packageInfo)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("pkg", packageInfo.packageName);
                    hashMap.put(User.FN_NAME, packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    hashMap.put(C2537j.aq, packageInfo.versionName);
                    arrayList.add(hashMap);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    public boolean m16463a() {
        return false;
    }

    public String m16464b() {
        WifiManager wifiManager = (WifiManager) this.f14600a.getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (macAddress == null) {
            macAddress = null;
        }
        return macAddress;
    }

    public String m16465c() {
        return Build.MODEL;
    }

    public String m16466d() {
        return Build.MANUFACTURER;
    }

    public String m16467e() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f14600a.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        String deviceId = telephonyManager.getDeviceId();
        String str = C2915a.f14760f;
        if (deviceId != null) {
            str = new String(deviceId).replace(Constants.VIA_RESULT_SUCCESS, C2915a.f14760f);
        }
        if ((deviceId == null || r2.length() <= 0) && VERSION.SDK_INT >= 9) {
            try {
                Class cls = Class.forName("android.os.SystemProperties");
                deviceId = (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN});
            } catch (Throwable th) {
                th.printStackTrace();
                deviceId = null;
            }
        }
        return deviceId;
    }

    public String m16468f() {
        return m16461a(m16465c() + "|" + m16469g() + "|" + m16466d() + "|" + m16471i() + "|" + m16470h(), m16476n().substring(0, 16));
    }

    public String m16469g() {
        return String.valueOf(VERSION.SDK_INT);
    }

    public String m16470h() {
        DisplayMetrics displayMetrics = this.f14600a.getResources().getDisplayMetrics();
        return this.f14600a.getResources().getConfiguration().orientation == 1 ? displayMetrics.widthPixels + "x" + displayMetrics.heightPixels : displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
    }

    public String m16471i() {
        Object simOperator = ((TelephonyManager) this.f14600a.getSystemService("phone")).getSimOperator();
        return TextUtils.isEmpty(simOperator) ? "-1" : simOperator;
    }

    public String m16472j() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f14600a.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return null;
        }
        int type = activeNetworkInfo.getType();
        if (1 == type) {
            return "wifi";
        }
        if (type != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        String str = C2915a.f14760f;
        if (defaultHost != null && defaultHost.length() > 0) {
            str = " wap";
        }
        return new StringBuilder(String.valueOf(m16460w() ? "3G" : "2G")).append(str).toString();
    }

    public int m16473k() {
        return 1;
    }

    public JSONArray m16474l() {
        JSONArray jSONArray = new JSONArray();
        ActivityManager activityManager = (ActivityManager) this.f14600a.getSystemService("activity");
        if (activityManager == null) {
            return jSONArray;
        }
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return jSONArray;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            jSONArray.put(runningAppProcessInfo.processName);
        }
        return jSONArray;
    }

    public String m16475m() {
        JSONArray l = m16474l();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < l.length(); i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(String.valueOf(l.get(i)));
        }
        return stringBuilder.toString();
    }

    public String m16476n() {
        try {
            String b = m16464b();
            String e = m16467e();
            return C2857a.m16449a(C2857a.m16450a(new StringBuilder(String.valueOf(b)).append(":").append(e).append(":").append(m16465c()).toString()));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String m16477o() {
        return this.f14600a.getPackageName();
    }

    public String m16478p() {
        String str = this.f14600a.getApplicationInfo().name;
        if (str != null) {
            return str;
        }
        int i = this.f14600a.getApplicationInfo().labelRes;
        return i > 0 ? this.f14600a.getString(i) : str;
    }

    public int m16479q() {
        int i = 0;
        try {
            return this.f14600a.getPackageManager().getPackageInfo(this.f14600a.getPackageName(), 0).versionCode;
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    public String m16480r() {
        try {
            return this.f14600a.getPackageManager().getPackageInfo(this.f14600a.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            th.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }

    public String m16481s() {
        return ((TelephonyManager) this.f14600a.getSystemService("phone")).getNetworkOperator();
    }

    public String m16482t() {
        try {
            return ((RunningTaskInfo) ((ActivityManager) this.f14600a.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean m16483u() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public String m16484v() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
