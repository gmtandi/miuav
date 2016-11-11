package com.tencent.mm.sdk.platformtools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NetStatusUtil {
    public static final int CMNET = 6;
    public static final int CMWAP = 5;
    public static final int CTNET = 8;
    public static final int CTWAP = 7;
    public static final int LTE = 10;
    public static final int MOBILE = 9;
    public static final int NET_3G = 4;
    public static final int NON_NETWORK = -1;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    public static final int TBACKGROUND_DATA_LIMITED = 2;
    public static final int TBACKGROUND_NOT_LIMITED = 0;
    public static final int TBACKGROUND_PROCESS_LIMITED = 1;
    public static final int TBACKGROUND_WIFI_LIMITED = 3;
    public static final int UNINET = 1;
    public static final int UNIWAP = 2;
    public static final int WAP_3G = 3;
    public static final int WIFI = 0;

    private static Intent m13552a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            List installedPackages = packageManager.getInstalledPackages(TBACKGROUND_NOT_LIMITED);
            if (installedPackages != null && installedPackages.size() > 0) {
                Log.m13541e("MicroMsg.NetStatusUtil", "package  size" + installedPackages.size());
                for (int i = TBACKGROUND_NOT_LIMITED; i < installedPackages.size(); i += UNINET) {
                    try {
                        Log.m13541e("MicroMsg.NetStatusUtil", "package " + ((PackageInfo) installedPackages.get(i)).packageName);
                        Intent intent = new Intent();
                        intent.setPackage(((PackageInfo) installedPackages.get(i)).packageName);
                        List queryIntentActivities = packageManager.queryIntentActivities(intent, TBACKGROUND_NOT_LIMITED);
                        int size = queryIntentActivities != null ? queryIntentActivities.size() : TBACKGROUND_NOT_LIMITED;
                        if (size > 0) {
                            try {
                                Log.m13541e("MicroMsg.NetStatusUtil", "activityName count " + size);
                                for (int i2 = TBACKGROUND_NOT_LIMITED; i2 < size; i2 += UNINET) {
                                    ActivityInfo activityInfo = ((ResolveInfo) queryIntentActivities.get(i2)).activityInfo;
                                    if (activityInfo.name.contains(str)) {
                                        Intent intent2 = new Intent("/");
                                        intent2.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
                                        intent2.setAction("android.intent.action.VIEW");
                                        context.startActivity(intent2);
                                        return intent2;
                                    }
                                }
                                continue;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            continue;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        return null;
    }

    public static boolean checkFromXml(int i) {
        try {
            runRootCommand();
            NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File("/data/system/netpolicy.xml"))).getDocumentElement().getElementsByTagName("uid-policy");
            for (int i2 = TBACKGROUND_NOT_LIMITED; i2 < elementsByTagName.getLength(); i2 += UNINET) {
                Element element = (Element) elementsByTagName.item(i2);
                String attribute = element.getAttribute("uid");
                String attribute2 = element.getAttribute("policy");
                Log.m13541e("MicroMsg.NetStatusUtil", "uid is " + attribute + "  policy is " + attribute2);
                if (attribute.equals(Integer.toString(i))) {
                    if (Integer.parseInt(attribute2) == UNINET) {
                        return true;
                    }
                    if (Integer.parseInt(attribute2) == 0) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void dumpNetStatus(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            Log.m13541e("MicroMsg.NetStatusUtil", "isAvailable " + activeNetworkInfo.isAvailable());
            Log.m13541e("MicroMsg.NetStatusUtil", "isConnected " + activeNetworkInfo.isConnected());
            Log.m13541e("MicroMsg.NetStatusUtil", "isRoaming " + activeNetworkInfo.isRoaming());
            Log.m13541e("MicroMsg.NetStatusUtil", "isFailover " + activeNetworkInfo.isFailover());
            Log.m13541e("MicroMsg.NetStatusUtil", "getSubtypeName " + activeNetworkInfo.getSubtypeName());
            Log.m13541e("MicroMsg.NetStatusUtil", "getExtraInfo " + activeNetworkInfo.getExtraInfo());
            Log.m13541e("MicroMsg.NetStatusUtil", "activeNetInfo " + activeNetworkInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getBackgroundLimitType(Context context) {
        if (VERSION.SDK_INT >= 14) {
            try {
                Class cls = Class.forName("android.app.ActivityManagerNative");
                Object invoke = cls.getMethod("getDefault", new Class[TBACKGROUND_NOT_LIMITED]).invoke(cls, new Object[TBACKGROUND_NOT_LIMITED]);
                if (((Integer) invoke.getClass().getMethod("getProcessLimit", new Class[TBACKGROUND_NOT_LIMITED]).invoke(invoke, new Object[TBACKGROUND_NOT_LIMITED])).intValue() == 0) {
                    return UNINET;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            int wifiSleeepPolicy = getWifiSleeepPolicy(context);
            if (wifiSleeepPolicy == UNIWAP) {
                return TBACKGROUND_NOT_LIMITED;
            }
            if (wifiSleeepPolicy == UNINET || wifiSleeepPolicy == 0) {
                return WAP_3G;
            }
            return TBACKGROUND_NOT_LIMITED;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static int getNetType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return NON_NETWORK;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NON_NETWORK;
        }
        if (activeNetworkInfo.getType() == UNINET) {
            return TBACKGROUND_NOT_LIMITED;
        }
        Log.m13539d("MicroMsg.NetStatusUtil", "activeNetInfo.getExtraInfo()  " + activeNetworkInfo.getExtraInfo());
        Log.m13539d("MicroMsg.NetStatusUtil", "activeNetInfo.getType()  " + activeNetworkInfo.getType());
        if (activeNetworkInfo.getExtraInfo() != null) {
            if (activeNetworkInfo.getExtraInfo().equals("uninet")) {
                return UNINET;
            }
            if (activeNetworkInfo.getExtraInfo().equals("uniwap")) {
                return UNIWAP;
            }
            if (activeNetworkInfo.getExtraInfo().equals("3gwap")) {
                return WAP_3G;
            }
            if (activeNetworkInfo.getExtraInfo().equals("3gnet")) {
                return NET_3G;
            }
            if (activeNetworkInfo.getExtraInfo().equals("cmwap")) {
                return CMWAP;
            }
            if (activeNetworkInfo.getExtraInfo().equals("cmnet")) {
                return CMNET;
            }
            if (activeNetworkInfo.getExtraInfo().equals("ctwap")) {
                return CTWAP;
            }
            if (activeNetworkInfo.getExtraInfo().equals("ctnet")) {
                return CTNET;
            }
            if (activeNetworkInfo.getExtraInfo().equals("LTE")) {
                return LTE;
            }
        }
        return MOBILE;
    }

    public static String getNetTypeString(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "NON_NETWORK";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "NON_NETWORK";
        }
        if (activeNetworkInfo.getType() == UNINET) {
            return "WIFI";
        }
        Log.m13539d("MicroMsg.NetStatusUtil", "activeNetInfo.getExtraInfo()  " + activeNetworkInfo.getExtraInfo());
        Log.m13539d("MicroMsg.NetStatusUtil", "activeNetInfo.getType()  " + activeNetworkInfo.getType());
        return activeNetworkInfo.getExtraInfo() != null ? activeNetworkInfo.getExtraInfo() : "MOBILE";
    }

    public static int getWifiSleeepPolicy(Context context) {
        return System.getInt(context.getContentResolver(), "wifi_sleep_policy", UNIWAP);
    }

    public static boolean is2G(int i) {
        return i == UNINET || i == UNIWAP || i == CMWAP || i == CMNET || i == CTWAP || i == CTNET;
    }

    public static boolean is2G(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == UNINET) {
                return false;
            }
            if (activeNetworkInfo.getSubtype() == UNIWAP || activeNetworkInfo.getSubtype() == UNINET) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is3G(int i) {
        return i == WAP_3G || i == NET_3G;
    }

    public static boolean is3G(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == UNINET) {
                return false;
            }
            if (activeNetworkInfo.getSubtype() >= CMWAP && activeNetworkInfo.getSubtype() < 13) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean is4G(int i) {
        return i == LTE;
    }

    public static boolean is4G(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() == UNINET) {
                return false;
            }
            if (activeNetworkInfo.getSubtype() >= 13) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected(Context context) {
        boolean z = false;
        try {
            z = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
        }
        return z;
    }

    public static boolean isImmediatelyDestroyActivities(Context context) {
        return System.getInt(context.getContentResolver(), "always_finish_activities", TBACKGROUND_NOT_LIMITED) != 0;
    }

    public static boolean isLimited(int i) {
        return i == UNIWAP || i == UNINET || i == WAP_3G;
    }

    public static boolean isMobile(int i) {
        return is3G(i) || is2G(i) || is4G(i);
    }

    public static boolean isMobile(Context context) {
        int netType = getNetType(context);
        return is3G(netType) || is2G(netType) || is4G(netType);
    }

    public static boolean isRestrictBacground(Context context) {
        int i = context.getApplicationInfo().uid;
        try {
            Class cls = Class.forName("android.net.NetworkPolicyManager");
            Class[] clsArr = new Class[UNINET];
            clsArr[TBACKGROUND_NOT_LIMITED] = Context.class;
            Method method = cls.getMethod("getSystemService", clsArr);
            Object[] objArr = new Object[UNINET];
            objArr[TBACKGROUND_NOT_LIMITED] = context;
            Object invoke = method.invoke(cls, objArr);
            Field declaredField = cls.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(invoke);
            Class[] clsArr2 = new Class[UNINET];
            clsArr2[TBACKGROUND_NOT_LIMITED] = Integer.TYPE;
            method = obj.getClass().getMethod("getUidPolicy", clsArr2);
            objArr = new Object[UNINET];
            objArr[TBACKGROUND_NOT_LIMITED] = Integer.valueOf(i);
            int intValue = ((Integer) method.invoke(obj, objArr)).intValue();
            Log.m13541e("MicroMsg.NetStatusUtil", "policy is " + intValue);
            if (intValue == UNINET) {
                return true;
            }
            if (intValue == 0) {
                return false;
            }
            return checkFromXml(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWap(int i) {
        return i == UNIWAP || i == CMWAP || i == CTWAP || i == WAP_3G;
    }

    public static boolean isWap(Context context) {
        return isWap(getNetType(context));
    }

    public static boolean isWifi(int i) {
        return i == 0;
    }

    public static boolean isWifi(Context context) {
        return isWifi(getNetType(context));
    }

    public static boolean runRootCommand() {
        DataOutputStream dataOutputStream;
        Exception e;
        Throwable th;
        Process process = null;
        try {
            Process exec = Runtime.getRuntime().exec("su");
            try {
                dataOutputStream = new DataOutputStream(exec.getOutputStream());
            } catch (Exception e2) {
                e = e2;
                dataOutputStream = null;
                process = exec;
                try {
                    Log.m13539d("MicroMsg.NetStatusUtil", "the device is not rooted\uff0c error message\uff1a " + e.getMessage());
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return false;
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            throw th;
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream = null;
                process = exec;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
            try {
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
                exec.waitFor();
                try {
                    dataOutputStream.close();
                    if (exec != null) {
                        exec.destroy();
                    }
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
                return true;
            } catch (Exception e5) {
                e32 = e5;
                process = exec;
                Log.m13539d("MicroMsg.NetStatusUtil", "the device is not rooted\uff0c error message\uff1a " + e32.getMessage());
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (process != null) {
                    process.destroy();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                process = exec;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (Exception e6) {
            e32 = e6;
            dataOutputStream = null;
            Log.m13539d("MicroMsg.NetStatusUtil", "the device is not rooted\uff0c error message\uff1a " + e32.getMessage());
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (process != null) {
                process.destroy();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    public static void startSettingItent(Context context, int i) {
        Intent intent;
        switch (i) {
            case UNINET /*1*/:
                try {
                    intent = new Intent("/");
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings"));
                    intent.setAction("android.intent.action.VIEW");
                    context.startActivity(intent);
                } catch (Exception e) {
                    m13552a(context, "DevelopmentSettings");
                }
            case UNIWAP /*2*/:
                try {
                    intent = new Intent("/");
                    intent.setComponent(new ComponentName("com.android.providers.subscribedfeeds", "com.android.settings.ManageAccountsSettings"));
                    intent.setAction("android.intent.action.VIEW");
                    context.startActivity(intent);
                } catch (Exception e2) {
                    try {
                        intent = new Intent("/");
                        intent.setComponent(new ComponentName("com.htc.settings.accountsync", "com.htc.settings.accountsync.ManageAccountsSettings"));
                        intent.setAction("android.intent.action.VIEW");
                        context.startActivity(intent);
                    } catch (Exception e3) {
                        m13552a(context, "ManageAccountsSettings");
                    }
                }
            case WAP_3G /*3*/:
                try {
                    intent = new Intent();
                    intent.setAction("android.settings.WIFI_IP_SETTINGS");
                    context.startActivity(intent);
                } catch (Exception e4) {
                    m13552a(context, "AdvancedSettings");
                }
            default:
        }
    }
}
