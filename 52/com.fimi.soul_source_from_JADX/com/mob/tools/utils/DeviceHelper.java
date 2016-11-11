package com.mob.tools.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.support.v4.widget.AutoScrollHelper;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.fimi.soul.entity.User;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.mob.tools.MobLog;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.openauth.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.json.JSONArray;
import org.p122a.p123a.C2915a;

public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    private Context context;

    class GSConnection implements ServiceConnection {
        boolean got;
        private final BlockingQueue<IBinder> iBinders;

        private GSConnection() {
            this.got = false;
            this.iBinders = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.iBinders.put(iBinder);
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder takeBinder() {
            if (this.got) {
                throw new IllegalStateException();
            }
            this.got = true;
            return (IBinder) this.iBinders.poll(1500, TimeUnit.MILLISECONDS);
        }
    }

    private DeviceHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    private String getCurrentNetworkHardwareAddress() {
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
            Enumeration inetAddresses = networkInterface.getInetAddresses();
            if (inetAddresses != null) {
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                            }
                            if (stringBuilder.length() > 0) {
                                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                            }
                            return stringBuilder.toString();
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private String getHardwareAddressFromShell(String str) {
        BufferedReader bufferedReader;
        CharSequence readLine;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/" + str + "/address").getInputStream()));
            try {
                readLine = bufferedReader.readLine();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    MobLog.getInstance().m738d(th);
                    if (bufferedReader == null) {
                        try {
                            bufferedReader.close();
                            readLine = null;
                        } catch (Throwable th4) {
                            readLine = null;
                        }
                    } else {
                        readLine = null;
                    }
                    return TextUtils.isEmpty(readLine) ? readLine : null;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(readLine)) {
        }
    }

    public static DeviceHelper getInstance(Context context) {
        if (deviceHelper == null && context != null) {
            deviceHelper = new DeviceHelper(context);
        }
        return deviceHelper;
    }

    private String getLocalDeviceKey() {
        if (!getSdcardState()) {
            return null;
        }
        File file;
        File file2 = new File(getSdcardPath(), "ShareSDK");
        if (file2.exists()) {
            file = new File(file2, ".dk");
            if (file.exists() && file.renameTo(new File(C2178R.getCacheRoot(this.context), ".dk"))) {
                file.delete();
            }
        }
        file = new File(C2178R.getCacheRoot(this.context), ".dk");
        if (!file.exists()) {
            return null;
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Object readObject = objectInputStream.readObject();
        String valueOf = (readObject == null || !(readObject instanceof char[])) ? null : String.valueOf((char[]) readObject);
        objectInputStream.close();
        return valueOf;
    }

    private Object getSystemService(String str) {
        try {
            return this.context.getSystemService(str);
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return null;
        }
    }

    private boolean is4GMobileNetwork() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        return telephonyManager.getNetworkType() == 13;
    }

    private boolean isFastMobileNetwork() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
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

    private boolean isSystemApp(PackageInfo packageInfo) {
        return ((packageInfo.applicationInfo.flags & 1) == 1) || ((packageInfo.applicationInfo.flags & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 1);
    }

    private String[] listNetworkHardwareAddress() {
        int i = 0;
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        List<NetworkInterface> list = Collections.list(networkInterfaces);
        HashMap hashMap = new HashMap();
        for (NetworkInterface networkInterface : list) {
            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            if (hardwareAddress != null) {
                StringBuilder stringBuilder = new StringBuilder();
                int length = hardwareAddress.length;
                for (int i2 = 0; i2 < length; i2++) {
                    stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i2])}));
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                hashMap.put(networkInterface.getName(), stringBuilder.toString());
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collection arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        Collection arrayList4 = new ArrayList();
        Collection arrayList5 = new ArrayList();
        Collection arrayList6 = new ArrayList();
        Collection arrayList7 = new ArrayList();
        Collection arrayList8 = new ArrayList();
        while (arrayList.size() > 0) {
            String str = (String) arrayList.remove(0);
            if (str.startsWith("wlan")) {
                arrayList2.add(str);
            } else if (str.startsWith("eth")) {
                arrayList3.add(str);
            } else if (str.startsWith("rev_rmnet")) {
                arrayList4.add(str);
            } else if (str.startsWith("dummy")) {
                arrayList5.add(str);
            } else if (str.startsWith("usbnet")) {
                arrayList6.add(str);
            } else if (str.startsWith("rmnet_usb")) {
                arrayList7.add(str);
            } else {
                arrayList8.add(str);
            }
        }
        Collections.sort(arrayList2);
        Collections.sort(arrayList3);
        Collections.sort(arrayList4);
        Collections.sort(arrayList5);
        Collections.sort(arrayList6);
        Collections.sort(arrayList7);
        Collections.sort(arrayList8);
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        arrayList.addAll(arrayList5);
        arrayList.addAll(arrayList6);
        arrayList.addAll(arrayList7);
        arrayList.addAll(arrayList8);
        String[] strArr = new String[arrayList.size()];
        while (i < strArr.length) {
            strArr[i] = (String) hashMap.get(arrayList.get(i));
            i++;
        }
        return strArr;
    }

    private void saveLocalDeviceKey(String str) {
        if (getSdcardState()) {
            File file = new File(C2178R.getCacheRoot(this.context), ".dk");
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(str.toCharArray());
            objectOutputStream.flush();
            objectOutputStream.close();
        }
    }

    public String Base64AES(String str, String str2) {
        String encodeToString;
        Throwable th;
        try {
            encodeToString = Base64.encodeToString(Data.AES128Encode(str2, str), 0);
            try {
                if (encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", C2915a.f14760f);
                }
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().m750w(th);
                return encodeToString;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            encodeToString = null;
            th = th4;
            MobLog.getInstance().m750w(th);
            return encodeToString;
        }
        return encodeToString;
    }

    public boolean checkPermission(String str) {
        int intValue;
        if (VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.importClass("android.content.Context");
                Integer num = (Integer) ReflectHelper.invokeInstanceMethod(this.context, "checkSelfPermission", str);
                intValue = num == null ? -1 : num.intValue();
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
                intValue = -1;
            }
        } else {
            this.context.checkPermission(str, Process.myPid(), Process.myUid());
            intValue = this.context.getPackageManager().checkPermission(str, getPackageName());
        }
        return intValue == 0;
    }

    public String getAdvertisingID() {
        String readString;
        try {
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            ServiceConnection gSConnection = new GSConnection();
            this.context.bindService(intent, gSConnection, 1);
            IBinder takeBinder = gSConnection.takeBinder();
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            takeBinder.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            readString = obtain2.readString();
            obtain2.recycle();
            obtain.recycle();
            MobLog.getInstance().m743i("getAdvertisingID === " + readString, new Object[0]);
            this.context.unbindService(gSConnection);
            return readString;
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
            return null;
        }
    }

    public String getAndroidID() {
        String string = Secure.getString(this.context.getContentResolver(), "android_id");
        MobLog.getInstance().m743i("getAndroidID === " + string, new Object[0]);
        return string;
    }

    public String getAppName() {
        String str = this.context.getApplicationInfo().name;
        if (str != null) {
            return str;
        }
        int i = this.context.getApplicationInfo().labelRes;
        return i > 0 ? this.context.getString(i) : String.valueOf(this.context.getApplicationInfo().nonLocalizedLabel);
    }

    public int getAppVersion() {
        int i = 0;
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
            return i;
        }
    }

    public String getAppVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
            return BuildConfig.VERSION_NAME;
        }
    }

    public String getBluetoothName() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && checkPermission("android.permission.BLUETOOTH")) {
                return defaultAdapter.getName();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
        return null;
    }

    public String getBssid() {
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String bssid = connectionInfo.getBSSID();
        if (bssid == null) {
            bssid = null;
        }
        return bssid;
    }

    public String getCarrier() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return "-1";
        }
        Object simOperator = telephonyManager.getSimOperator();
        return TextUtils.isEmpty(simOperator) ? "-1" : simOperator;
    }

    public String getCarrierName() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        try {
            if (!checkPermission("android.permission.READ_PHONE_STATE")) {
                return null;
            }
            CharSequence simOperatorName = telephonyManager.getSimOperatorName();
            if (TextUtils.isEmpty(simOperatorName)) {
                simOperatorName = null;
            }
            return simOperatorName;
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return null;
        }
    }

    public int getCellId() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
                if (telephonyManager != null) {
                    return ((GsmCellLocation) telephonyManager.getCellLocation()).getCid();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
        return -1;
    }

    public int getCellLac() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
                if (telephonyManager != null) {
                    return ((GsmCellLocation) telephonyManager.getCellLocation()).getLac();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
        return -1;
    }

    public String getCharAndNumr(int i) {
        long currentTimeMillis = System.currentTimeMillis() ^ SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                stringBuffer.insert(i2 + 1, (char) (random.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    public String getDetailNetworkTypeForStatic() {
        String toLowerCase = getNetworkType().toLowerCase();
        return (TextUtils.isEmpty(toLowerCase) || "none".equals(toLowerCase)) ? "none" : toLowerCase.startsWith("wifi") ? "wifi" : toLowerCase.startsWith("4g") ? "4g" : toLowerCase.startsWith("3g") ? "3g" : toLowerCase.startsWith("2g") ? "2g" : toLowerCase.startsWith("bluetooth") ? "bluetooth" : toLowerCase;
    }

    public String getDeviceData() {
        return Base64AES(getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 16));
    }

    public String getDeviceDataNotAES() {
        return getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
    }

    public String getDeviceId() {
        String imei = getIMEI();
        return (!TextUtils.isEmpty(imei) || VERSION.SDK_INT < 9) ? imei : getSerialno();
    }

    public String getDeviceKey() {
        String localDeviceKey;
        String str = null;
        try {
            localDeviceKey = getLocalDeviceKey();
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            localDeviceKey = str;
        }
        if (TextUtils.isEmpty(localDeviceKey) || localDeviceKey.length() < 40) {
            try {
                localDeviceKey = getMacAddress();
                str = Data.byteToHex(Data.SHA1(localDeviceKey + ":" + getDeviceId() + ":" + getModel()));
            } catch (Throwable th2) {
                MobLog.getInstance().m738d(th2);
            }
            localDeviceKey = (TextUtils.isEmpty(str) || str.length() < 40) ? getCharAndNumr(40) : str;
            if (localDeviceKey != null) {
                try {
                    saveLocalDeviceKey(localDeviceKey);
                } catch (Throwable th3) {
                    MobLog.getInstance().m750w(th3);
                }
            }
        }
        return localDeviceKey;
    }

    public String getDeviceType() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
        if (uiModeManager != null) {
            switch (uiModeManager.getCurrentModeType()) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return "NO_UI";
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return "DESK";
                case Type.BYTE /*3*/:
                    return "CAR";
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    return "TELEVISION";
                case Type.INT /*5*/:
                    return "APPLIANCE";
                case Type.FLOAT /*6*/:
                    return "WATCH";
            }
        }
        return "UNDEFINED";
    }

    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CharSequence deviceId;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                return TextUtils.isEmpty(deviceId) ? deviceId : null;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
        deviceId = null;
        if (TextUtils.isEmpty(deviceId)) {
        }
    }

    public String getIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CharSequence subscriberId;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                subscriberId = telephonyManager.getSubscriberId();
                return TextUtils.isEmpty(subscriberId) ? subscriberId : null;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
        subscriberId = null;
        if (TextUtils.isEmpty(subscriberId)) {
        }
    }

    public String getIPAddress() {
        try {
            if (checkPermission("android.permission.INTERNET")) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
        return "0.0.0.0";
    }

    public ArrayList<HashMap<String, String>> getInstalledApp(boolean z) {
        try {
            PackageManager packageManager = this.context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            ArrayList<HashMap<String, String>> arrayList = new ArrayList();
            for (PackageInfo packageInfo : installedPackages) {
                if (z || !isSystemApp(packageInfo)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("pkg", packageInfo.packageName);
                    hashMap.put(User.FN_NAME, packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    hashMap.put(C2537j.aq, packageInfo.versionName);
                    arrayList.add(hashMap);
                }
            }
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return new ArrayList();
        }
    }

    public String getLine1Number() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        return telephonyManager == null ? "-1" : telephonyManager.getLine1Number();
    }

    public float[] getLocation(int i, int i2) {
        try {
            if (checkPermission("android.permission.ACCESS_FINE_LOCATION") && ((LocationManager) getSystemService("location")) != null) {
                return new LocationHelper().getLocation(this.context, i, i2);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
        return null;
    }

    public String getMCC() {
        if (((TelephonyManager) getSystemService("phone")) == null) {
            return null;
        }
        String imsi = getIMSI();
        return (imsi == null || imsi.length() < 3) ? null : imsi.substring(0, 3);
    }

    public String getMNC() {
        if (((TelephonyManager) getSystemService("phone")) == null) {
            return null;
        }
        String imsi = getIMSI();
        return (imsi == null || imsi.length() < 5) ? null : imsi.substring(3, 5);
    }

    public String getMacAddress() {
        String hardwareAddressFromShell;
        if (VERSION.SDK_INT >= 23) {
            try {
                hardwareAddressFromShell = getHardwareAddressFromShell("wlan0");
            } catch (Throwable th) {
                MobLog.getInstance().m738d(th);
                hardwareAddressFromShell = null;
            }
            if (hardwareAddressFromShell == null) {
                try {
                    hardwareAddressFromShell = getCurrentNetworkHardwareAddress();
                } catch (Throwable th2) {
                    MobLog.getInstance().m738d(th2);
                    hardwareAddressFromShell = null;
                }
            }
            if (hardwareAddressFromShell == null) {
                try {
                    String[] listNetworkHardwareAddress = listNetworkHardwareAddress();
                    if (listNetworkHardwareAddress.length > 0) {
                        hardwareAddressFromShell = listNetworkHardwareAddress[0];
                    }
                } catch (Throwable th22) {
                    MobLog.getInstance().m738d(th22);
                    hardwareAddressFromShell = null;
                }
            }
            if (hardwareAddressFromShell != null) {
                return hardwareAddressFromShell;
            }
        }
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        hardwareAddressFromShell = connectionInfo.getMacAddress();
        if (hardwareAddressFromShell == null) {
            hardwareAddressFromShell = null;
        }
        return hardwareAddressFromShell;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getMime() {
        return getIMEI();
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        return telephonyManager == null ? null : telephonyManager.getNetworkOperator();
    }

    public String getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        if (connectivityManager == null) {
            return "none";
        }
        try {
            if (!checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
                return "none";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return "none";
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    return is4GMobileNetwork() ? "4G" : isFastMobileNetwork() ? "3G" : "2G";
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return "wifi";
                case Type.FLOAT /*6*/:
                    return "wimax";
                case Type.LONG /*7*/:
                    return "bluetooth";
                case Type.DOUBLE /*8*/:
                    return "dummy";
                case Type.ARRAY /*9*/:
                    return "ethernet";
                default:
                    return String.valueOf(type);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return "none";
        }
    }

    public String getNetworkTypeForStatic() {
        String toLowerCase = getNetworkType().toLowerCase();
        return (TextUtils.isEmpty(toLowerCase) || "none".equals(toLowerCase)) ? "none" : (toLowerCase.startsWith("4g") || toLowerCase.startsWith("3g") || toLowerCase.startsWith("2g")) ? "cell" : toLowerCase.startsWith("wifi") ? "wifi" : "other";
    }

    public String getOSCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getOSLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getOSVersion() {
        return String.valueOf(getOSVersionInt());
    }

    public int getOSVersionInt() {
        return VERSION.SDK_INT;
    }

    public String getOSVersionName() {
        return VERSION.RELEASE;
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    public int getPlatformCode() {
        return 1;
    }

    public JSONArray getRunningApp() {
        JSONArray jSONArray = new JSONArray();
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
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

    public String getRunningAppStr() {
        JSONArray runningApp = getRunningApp();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < runningApp.length(); i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(String.valueOf(runningApp.get(i)));
        }
        return stringBuilder.toString();
    }

    public String getSSID() {
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        String replace = connectionInfo.getSSID().replace("\"", C2915a.f14760f);
        if (replace == null) {
            replace = null;
        }
        return replace;
    }

    public String getScreenSize() {
        int[] screenSize = C2178R.getScreenSize(this.context);
        return this.context.getResources().getConfiguration().orientation == 1 ? screenSize[0] + "x" + screenSize[1] : screenSize[1] + "x" + screenSize[0];
    }

    public String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public boolean getSdcardState() {
        try {
            return checkPermission("android.permission.WRITE_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState());
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return false;
        }
    }

    public String getSerialno() {
        if (VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN});
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
            return null;
        }
    }

    public String getSignMD5() {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
        } catch (Throwable e) {
            MobLog.getInstance().m750w(e);
            return null;
        }
    }

    public String getSimSerialNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        return telephonyManager == null ? "-1" : telephonyManager.getSimSerialNumber();
    }

    public String getTopTaskPackageName() {
        boolean checkPermission;
        try {
            checkPermission = checkPermission("android.permission.GET_TASKS");
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            checkPermission = false;
        }
        if (checkPermission) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                return activityManager == null ? null : VERSION.SDK_INT <= 20 ? ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName() : ((RunningAppProcessInfo) activityManager.getRunningAppProcesses().get(0)).processName.split(":")[0];
            } catch (Throwable th2) {
                MobLog.getInstance().m750w(th2);
            }
        }
        return null;
    }

    public void hideSoftInput(View view) {
        Object systemService = getSystemService("input_method");
        if (systemService != null) {
            ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean isMainProcess(int i) {
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager.getRunningAppProcesses() == null) {
            return i <= 0;
        } else {
            Object obj;
            if (i <= 0) {
                i = Process.myPid();
            }
            for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == i) {
                    obj = runningAppProcessInfo.processName;
                    break;
                }
            }
            obj = null;
            return getPackageName().equals(obj);
        }
    }

    public boolean isMainProcess(Context context, int i) {
        return isMainProcess(i);
    }

    public boolean isRooted() {
        return false;
    }

    public HashMap<String, String> ping(String str, int i, int i2) {
        int indexOf;
        float f;
        float floatValue;
        float f2 = 0.0f;
        ArrayList arrayList = new ArrayList();
        int i3 = i2 + 8;
        Process exec = Runtime.getRuntime().exec("ping -c " + i + " -s " + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            if (readLine.startsWith(i3 + " bytes from")) {
                if (readLine.endsWith(LocaleUtil.MALAY)) {
                    readLine = readLine.substring(0, readLine.length() - 2).trim();
                } else if (readLine.endsWith("s")) {
                    readLine = readLine.substring(0, readLine.length() - 1).trim() + "000";
                }
                indexOf = readLine.indexOf("time=");
                if (indexOf > 0) {
                    try {
                        arrayList.add(Float.valueOf(Float.parseFloat(readLine.substring(indexOf + 5).trim())));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
            readLine = bufferedReader.readLine();
        }
        exec.waitFor();
        indexOf = arrayList.size();
        int size = i - arrayList.size();
        if (indexOf > 0) {
            float f3 = AutoScrollHelper.NO_MAX;
            int i4 = 0;
            float f4 = 0.0f;
            f = 0.0f;
            while (i4 < indexOf) {
                floatValue = ((Float) arrayList.get(i4)).floatValue();
                if (floatValue < f3) {
                    f3 = floatValue;
                }
                i4++;
                f4 += floatValue;
                f = floatValue > f ? floatValue : f;
            }
            f2 = f4 / ((float) indexOf);
            floatValue = f3;
        } else {
            f = 0.0f;
            floatValue = 0.0f;
        }
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("address", str);
        hashMap.put("transmitted", String.valueOf(i));
        hashMap.put("received", String.valueOf(indexOf));
        hashMap.put("loss", String.valueOf(size));
        hashMap.put("min", String.valueOf(floatValue));
        hashMap.put("max", String.valueOf(f));
        hashMap.put("avg", String.valueOf(f2));
        return hashMap;
    }

    public void showSoftInput(View view) {
        Object systemService = getSystemService("input_method");
        if (systemService != null) {
            ((InputMethodManager) systemService).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
        }
    }
}
