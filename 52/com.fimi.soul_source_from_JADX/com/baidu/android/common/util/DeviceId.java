package com.baidu.android.common.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.baidu.android.common.logging.Log;
import com.baidu.android.common.security.AESUtil;
import com.baidu.android.common.security.Base64;
import com.baidu.android.common.security.MD5Util;
import com.baidu.android.common.security.SHA1Util;
import com.fimi.kernel.C1154b;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.stat.DeviceInfo;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public final class DeviceId {
    private static final String ACTION_GLAXY_CUID = "com.baidu.intent.action.GALAXY";
    private static final String AES_KEY = "30212102dicudiab";
    private static final boolean DEBUG = false;
    private static final String DEFAULT_TM_DEVICEID = "";
    private static final String EXT_DIR = "backups/.SystemConfig";
    private static final String EXT_FILE = ".cuid";
    private static final String EXT_FILE_V2 = ".cuid2";
    private static final String KEY_DEVICE_ID = "com.baidu.deviceid";
    private static final String KEY_DEVICE_ID_V2 = "com.baidu.deviceid.v2";
    private static final String KEY_IMEI = "bd_setting_i";
    private static final String META_KEY_GALAXY_SF = "galaxy_sf";
    private static final String META_KEY_GLAXY_DATA = "galaxy_data";
    private static final String OLD_EXT_DIR = "baidu";
    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final int SDK_ANDROID_M = 23;
    private static final String SELF_CUID_FILE = "libcuid.so";
    private static final int STORAGE_SDCARD_V1 = 4;
    private static final int STORAGE_SDCARD_V2 = 8;
    private static final int STORAGE_SELF_FILE = 16;
    private static final int STORAGE_SYSTEM_SETTING_V1 = 1;
    private static final int STORAGE_SYSTEM_SETTING_V2 = 2;
    private static final String TAG = "DeviceId";
    private static CUIDInfo sCachedCuidInfo;
    private final Context mContext;
    private PublicKey mPublicKey;
    private int mSaveMask;

    /* renamed from: com.baidu.android.common.util.DeviceId.1 */
    class C06631 implements Comparator<CUIDBuddyInfo> {
        C06631() {
        }

        public int compare(CUIDBuddyInfo cUIDBuddyInfo, CUIDBuddyInfo cUIDBuddyInfo2) {
            int i = cUIDBuddyInfo2.priority - cUIDBuddyInfo.priority;
            return i == 0 ? (cUIDBuddyInfo.isSelf && cUIDBuddyInfo2.isSelf) ? 0 : cUIDBuddyInfo.isSelf ? -1 : cUIDBuddyInfo2.isSelf ? DeviceId.STORAGE_SYSTEM_SETTING_V1 : i : i;
        }
    }

    class CUIDBuddyInfo {
        public ApplicationInfo appInfo;
        public boolean isSelf;
        public int priority;
        public boolean sigMatched;

        private CUIDBuddyInfo() {
            this.priority = 0;
            this.sigMatched = DeviceId.DEBUG;
            this.isSelf = DeviceId.DEBUG;
        }
    }

    class CUIDInfo {
        private static final int VERSION = 2;
        public String deviceId;
        public String imei;
        public int version;

        private CUIDInfo() {
            this.version = VERSION;
        }

        public static CUIDInfo createFromJson(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Object string = jSONObject.getString("deviceid");
                String string2 = jSONObject.getString("imei");
                int i = jSONObject.getInt(DeviceInfo.TAG_VERSION);
                if (TextUtils.isEmpty(string) || string2 == null) {
                    return null;
                }
                CUIDInfo cUIDInfo = new CUIDInfo();
                cUIDInfo.deviceId = string;
                cUIDInfo.imei = string2;
                cUIDInfo.version = i;
                return cUIDInfo;
            } catch (Throwable e) {
                DeviceId.handleThrowable(e);
                return null;
            }
        }

        public String getFinalCUID() {
            String str = this.imei;
            if (TextUtils.isEmpty(str)) {
                str = Constants.VIA_RESULT_SUCCESS;
            }
            return this.deviceId + "|" + new StringBuffer(str).reverse().toString();
        }

        public String toPersitString() {
            try {
                return new JSONObject().put("deviceid", this.deviceId).put("imei", this.imei).put(DeviceInfo.TAG_VERSION, this.version).toString();
            } catch (Throwable e) {
                DeviceId.handleThrowable(e);
                return null;
            }
        }
    }

    private DeviceId(Context context) {
        this.mSaveMask = 0;
        this.mContext = context.getApplicationContext();
        initPublicKey();
    }

    private static String byte2hex(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String str = DEFAULT_TM_DEVICEID;
        String str2 = DEFAULT_TM_DEVICEID;
        str2 = str;
        for (int i = 0; i < bArr.length; i += STORAGE_SYSTEM_SETTING_V1) {
            String toHexString = Integer.toHexString(bArr[i] & Util.MASK_8BIT);
            str2 = toHexString.length() == STORAGE_SYSTEM_SETTING_V1 ? str2 + Constants.VIA_RESULT_SUCCESS + toHexString : str2 + toHexString;
        }
        return str2.toLowerCase();
    }

    private boolean checkSelfPermission(String str) {
        return this.mContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0 ? true : DEBUG;
    }

    private List<CUIDBuddyInfo> collectBuddyInfos(Intent intent, boolean z) {
        List<CUIDBuddyInfo> arrayList = new ArrayList();
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), SmileConstants.TOKEN_PREFIX_TINY_UNICODE).metaData;
                        if (bundle != null) {
                            Object string = bundle.getString(META_KEY_GLAXY_DATA);
                            if (!TextUtils.isEmpty(string)) {
                                byte[] decode = Base64.decode(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(decode));
                                CUIDBuddyInfo cUIDBuddyInfo = new CUIDBuddyInfo();
                                cUIDBuddyInfo.priority = jSONObject.getInt("priority");
                                cUIDBuddyInfo.appInfo = resolveInfo.activityInfo.applicationInfo;
                                if (this.mContext.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    cUIDBuddyInfo.isSelf = true;
                                }
                                if (z) {
                                    Object string2 = bundle.getString(META_KEY_GALAXY_SF);
                                    if (!TextUtils.isEmpty(string2)) {
                                        int i;
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (i = 0; i < strArr.length; i += STORAGE_SYSTEM_SETTING_V1) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (isSigsEqual(strArr, formatAndroidSigArray(packageInfo.signatures))) {
                                            byte[] decryptByPublicKey = decryptByPublicKey(Base64.decode(string2.getBytes()), this.mPublicKey);
                                            i = (decryptByPublicKey == null || !Arrays.equals(decryptByPublicKey, SHA1Util.sha1(decode))) ? 0 : STORAGE_SYSTEM_SETTING_V1;
                                            if (i != 0) {
                                                cUIDBuddyInfo.sigMatched = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(cUIDBuddyInfo);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new C06631());
        return arrayList;
    }

    private static byte[] decryptByPublicKey(byte[] bArr, PublicKey publicKey) {
        Cipher instance = Cipher.getInstance(RSA_ALGORITHM);
        instance.init(STORAGE_SYSTEM_SETTING_V2, publicKey);
        return instance.doFinal(bArr);
    }

    private static String decryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(str.getBytes())));
        } catch (Throwable e) {
            handleThrowable(e);
            return DEFAULT_TM_DEVICEID;
        }
    }

    private static String encryptCUIDInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, str.getBytes()), "utf-8");
        } catch (Throwable e) {
            handleThrowable(e);
            return DEFAULT_TM_DEVICEID;
        } catch (Throwable e2) {
            handleThrowable(e2);
            return DEFAULT_TM_DEVICEID;
        }
    }

    private String[] formatAndroidSigArray(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i += STORAGE_SYSTEM_SETTING_V1) {
            strArr[i] = byte2hex(SHA1Util.sha1(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    public static String getAndroidId(Context context) {
        String str = DEFAULT_TM_DEVICEID;
        Object string = Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? DEFAULT_TM_DEVICEID : string;
    }

    public static String getCUID(Context context) {
        return getOrCreateCUIDInfo(context).getFinalCUID();
    }

    private CUIDInfo getCUIDInfo() {
        boolean z;
        CUIDBuddyInfo cUIDBuddyInfo;
        boolean z2;
        String str;
        String str2;
        CUIDInfo createFromJson;
        CUIDInfo cuidInfoFromExternalV2;
        String str3 = null;
        int i = 0;
        List collectBuddyInfos = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID).setPackage(this.mContext.getPackageName()), true);
        int i2;
        if (collectBuddyInfos == null || collectBuddyInfos.size() == 0) {
            for (i2 = 0; i2 < 3; i2 += STORAGE_SYSTEM_SETTING_V1) {
                Log.m5957w(TAG, "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = DEBUG;
        } else {
            cUIDBuddyInfo = (CUIDBuddyInfo) collectBuddyInfos.get(0);
            z2 = cUIDBuddyInfo.sigMatched;
            if (!cUIDBuddyInfo.sigMatched) {
                for (i2 = 0; i2 < 3; i2 += STORAGE_SYSTEM_SETTING_V1) {
                    Log.m5957w(TAG, "galaxy config err, In the release version of the signature should be matched");
                }
            }
            z = z2;
        }
        File file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
        CUIDInfo createFromJson2 = file.exists() ? CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file))) : null;
        if (createFromJson2 == null) {
            this.mSaveMask |= STORAGE_SELF_FILE;
            List<CUIDBuddyInfo> collectBuddyInfos2 = collectBuddyInfos(new Intent(ACTION_GLAXY_CUID), z);
            if (collectBuddyInfos2 != null) {
                str = C1154b.f5232c;
                file = this.mContext.getFilesDir();
                if (str.equals(file.getName())) {
                    str2 = str;
                } else {
                    Log.m5950e(TAG, "fetal error:: app files dir name is unexpectedly :: " + file.getAbsolutePath());
                    str2 = file.getName();
                }
                for (CUIDBuddyInfo cUIDBuddyInfo2 : collectBuddyInfos2) {
                    if (!cUIDBuddyInfo2.isSelf) {
                        File file2 = new File(new File(cUIDBuddyInfo2.appInfo.dataDir, str2), SELF_CUID_FILE);
                        if (file2.exists()) {
                            createFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getFileContent(file2)));
                            if (createFromJson != null) {
                                break;
                            }
                        }
                        createFromJson = createFromJson2;
                        createFromJson2 = createFromJson;
                    }
                }
            }
        }
        createFromJson = createFromJson2;
        if (createFromJson == null) {
            createFromJson = CUIDInfo.createFromJson(decryptCUIDInfo(getSystemSettingValue(KEY_DEVICE_ID_V2)));
        }
        boolean checkSelfPermission = checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        if (createFromJson == null && checkSelfPermission) {
            this.mSaveMask |= STORAGE_SYSTEM_SETTING_V2;
            cuidInfoFromExternalV2 = getCuidInfoFromExternalV2();
        } else {
            cuidInfoFromExternalV2 = createFromJson;
        }
        if (cuidInfoFromExternalV2 == null) {
            this.mSaveMask |= STORAGE_SDCARD_V2;
            cuidInfoFromExternalV2 = getCUidInfoFromSystemSettingV1();
        }
        if (cuidInfoFromExternalV2 == null && checkSelfPermission) {
            this.mSaveMask |= STORAGE_SYSTEM_SETTING_V1;
            str = getIMEIFromSystem(DEFAULT_TM_DEVICEID);
            cuidInfoFromExternalV2 = getExternalV1DeviceId(str);
            i = STORAGE_SYSTEM_SETTING_V1;
        } else {
            str = null;
        }
        if (cuidInfoFromExternalV2 == null) {
            this.mSaveMask |= STORAGE_SDCARD_V1;
            if (i == 0) {
                str = getIMEIFromSystem(DEFAULT_TM_DEVICEID);
            }
            CUIDInfo cUIDInfo = new CUIDInfo();
            str2 = getAndroidId(this.mContext);
            cUIDInfo.deviceId = MD5Util.toMd5((VERSION.SDK_INT < SDK_ANDROID_M ? str + str2 + UUID.randomUUID().toString() : "com.baidu" + str2).getBytes(), true);
            cUIDInfo.imei = str;
            createFromJson = cUIDInfo;
        } else {
            createFromJson = cuidInfoFromExternalV2;
        }
        file = new File(this.mContext.getFilesDir(), SELF_CUID_FILE);
        if (!((this.mSaveMask & STORAGE_SELF_FILE) == 0 && file.exists())) {
            str2 = TextUtils.isEmpty(null) ? encryptCUIDInfo(createFromJson.toPersitString()) : null;
            writeToCuidFile(str2);
            str3 = str2;
        }
        z2 = hasWriteSettingsPermission();
        if (z2 && ((this.mSaveMask & STORAGE_SYSTEM_SETTING_V2) != 0 || TextUtils.isEmpty(getSystemSettingValue(KEY_DEVICE_ID_V2)))) {
            if (TextUtils.isEmpty(str3)) {
                str3 = encryptCUIDInfo(createFromJson.toPersitString());
            }
            tryPutSystemSettingValue(KEY_DEVICE_ID_V2, str3);
        }
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
            File file3 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if (!((this.mSaveMask & STORAGE_SDCARD_V2) == 0 && file3.exists())) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = encryptCUIDInfo(createFromJson.toPersitString());
                }
                setExternalV2DeviceId(str3);
            }
        }
        if (z2 && ((this.mSaveMask & STORAGE_SYSTEM_SETTING_V1) != 0 || TextUtils.isEmpty(getSystemSettingValue(KEY_DEVICE_ID)))) {
            tryPutSystemSettingValue(KEY_DEVICE_ID, createFromJson.deviceId);
            tryPutSystemSettingValue(KEY_IMEI, createFromJson.imei);
        }
        if (z2 && !TextUtils.isEmpty(createFromJson.imei)) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if (!((this.mSaveMask & STORAGE_SYSTEM_SETTING_V2) == 0 && file.exists())) {
                setExternalDeviceId(createFromJson.imei, createFromJson.deviceId);
            }
        }
        return createFromJson;
    }

    private CUIDInfo getCUidInfoFromSystemSettingV1() {
        Object systemSettingValue = getSystemSettingValue(KEY_DEVICE_ID);
        String systemSettingValue2 = getSystemSettingValue(KEY_IMEI);
        if (TextUtils.isEmpty(systemSettingValue2)) {
            systemSettingValue2 = getIMEIFromSystem(DEFAULT_TM_DEVICEID);
            if (!TextUtils.isEmpty(systemSettingValue2)) {
                tryPutSystemSettingValue(KEY_IMEI, systemSettingValue2);
            }
        }
        if (TextUtils.isEmpty(systemSettingValue)) {
            systemSettingValue = getSystemSettingValue(MD5Util.toMd5(("com.baidu" + systemSettingValue2 + getAndroidId(this.mContext)).getBytes(), true));
        }
        if (TextUtils.isEmpty(systemSettingValue)) {
            return null;
        }
        CUIDInfo cUIDInfo = new CUIDInfo();
        cUIDInfo.deviceId = systemSettingValue;
        cUIDInfo.imei = systemSettingValue2;
        return cUIDInfo;
    }

    private CUIDInfo getCuidInfoFromExternalV2() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            Object fileContent = getFileContent(file);
            if (!TextUtils.isEmpty(fileContent)) {
                try {
                    return CUIDInfo.createFromJson(new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(fileContent.getBytes()))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getDeviceID(Context context) {
        return getOrCreateCUIDInfo(context).deviceId;
    }

    private CUIDInfo getExternalV1DeviceId(String str) {
        Object obj = null;
        Object obj2 = VERSION.SDK_INT < SDK_ANDROID_M ? STORAGE_SYSTEM_SETTING_V1 : null;
        if (obj2 != null && TextUtils.isEmpty(str)) {
            return null;
        }
        String str2;
        CUIDInfo cUIDInfo;
        Object obj3 = DEFAULT_TM_DEVICEID;
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            obj = STORAGE_SYSTEM_SETTING_V1;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append("\r\n");
            }
            bufferedReader.close();
            String[] split = new String(AESUtil.decrypt(AES_KEY, AES_KEY, Base64.decode(stringBuilder.toString().getBytes()))).split("=");
            if (split != null && split.length == STORAGE_SYSTEM_SETTING_V2) {
                if (obj2 != null && str.equals(split[0])) {
                    obj3 = split[STORAGE_SYSTEM_SETTING_V1];
                    str2 = str;
                    if (obj == null) {
                        setExternalDeviceId(str2, obj3);
                    }
                    if (!TextUtils.isEmpty(obj3)) {
                        return null;
                    }
                    cUIDInfo = new CUIDInfo();
                    cUIDInfo.deviceId = obj3;
                    cUIDInfo.imei = str2;
                    return cUIDInfo;
                } else if (obj2 == null) {
                    if (TextUtils.isEmpty(str)) {
                        str = split[STORAGE_SYSTEM_SETTING_V1];
                    }
                    obj3 = split[STORAGE_SYSTEM_SETTING_V1];
                    str2 = str;
                    if (obj == null) {
                        try {
                            setExternalDeviceId(str2, obj3);
                        } catch (FileNotFoundException e) {
                        } catch (IOException e2) {
                        } catch (Exception e3) {
                        }
                    }
                    if (!TextUtils.isEmpty(obj3)) {
                        return null;
                    }
                    cUIDInfo = new CUIDInfo();
                    cUIDInfo.deviceId = obj3;
                    cUIDInfo.imei = str2;
                    return cUIDInfo;
                }
            }
            str2 = str;
            if (obj == null) {
                setExternalDeviceId(str2, obj3);
            }
        } catch (FileNotFoundException e4) {
            str2 = str;
        } catch (IOException e5) {
            str2 = str;
        } catch (Exception e6) {
            str2 = str;
        }
        if (!TextUtils.isEmpty(obj3)) {
            return null;
        }
        cUIDInfo = new CUIDInfo();
        cUIDInfo.deviceId = obj3;
        cUIDInfo.imei = str2;
        return cUIDInfo;
    }

    private static String getFileContent(File file) {
        Throwable e;
        Throwable th;
        String str = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[Opcodes.ACC_ANNOTATION];
                CharArrayWriter charArrayWriter = new CharArrayWriter();
                while (true) {
                    int read = fileReader.read(cArr);
                    if (read <= 0) {
                        break;
                    }
                    charArrayWriter.write(cArr, 0, read);
                }
                str = charArrayWriter.toString();
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (Throwable e2) {
                        handleThrowable(e2);
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    handleThrowable(e2);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e22) {
                            handleThrowable(e22);
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable e222) {
                            handleThrowable(e222);
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e222 = e4;
            Object obj = str;
            handleThrowable(e222);
            if (fileReader != null) {
                fileReader.close();
            }
            return str;
        } catch (Throwable e2222) {
            fileReader = str;
            th = e2222;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return str;
    }

    public static String getIMEI(Context context) {
        return getOrCreateCUIDInfo(context).imei;
    }

    private String getIMEIFromSystem(String str) {
        String deviceId;
        CharSequence imeiCheck;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
                imeiCheck = imeiCheck(deviceId);
                return TextUtils.isEmpty(imeiCheck) ? str : imeiCheck;
            }
        } catch (Throwable e) {
            Log.m5951e(TAG, "Read IMEI failed", e);
        }
        deviceId = null;
        imeiCheck = imeiCheck(deviceId);
        if (TextUtils.isEmpty(imeiCheck)) {
        }
    }

    private static CUIDInfo getOrCreateCUIDInfo(Context context) {
        if (sCachedCuidInfo == null) {
            synchronized (CUIDInfo.class) {
                if (sCachedCuidInfo == null) {
                    SystemClock.uptimeMillis();
                    sCachedCuidInfo = new DeviceId(context).getCUIDInfo();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return sCachedCuidInfo;
    }

    private String getSystemSettingValue(String str) {
        try {
            return System.getString(this.mContext.getContentResolver(), str);
        } catch (Throwable e) {
            handleThrowable(e);
            return null;
        }
    }

    private static void handleThrowable(Throwable th) {
    }

    private boolean hasReadImeiPermission() {
        return checkSelfPermission("android.permission.READ_PHONE_STATE");
    }

    private boolean hasWriteSettingsPermission() {
        return checkSelfPermission("android.permission.WRITE_SETTINGS");
    }

    private static String imeiCheck(String str) {
        return (str == null || !str.contains(":")) ? str : DEFAULT_TM_DEVICEID;
    }

    private void initPublicKey() {
        ByteArrayInputStream byteArrayInputStream;
        Throwable e;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(CuidCertStore.getCertBytes());
            try {
                this.mPublicKey = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e2) {
                        handleThrowable(e2);
                    }
                }
            } catch (Exception e3) {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable e22) {
                        handleThrowable(e22);
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                byteArrayInputStream2 = byteArrayInputStream;
                e22 = th2;
                if (byteArrayInputStream2 != null) {
                    try {
                        byteArrayInputStream2.close();
                    } catch (Throwable th3) {
                        handleThrowable(th3);
                    }
                }
                throw e22;
            }
        } catch (Exception e4) {
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        } catch (Throwable th4) {
            e22 = th4;
            if (byteArrayInputStream2 != null) {
                byteArrayInputStream2.close();
            }
            throw e22;
        }
    }

    private boolean isSigsEqual(String[] strArr, String[] strArr2) {
        int i = 0;
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return DEBUG;
        }
        HashSet hashSet = new HashSet();
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2 += STORAGE_SYSTEM_SETTING_V1) {
            hashSet.add(strArr[i2]);
        }
        HashSet hashSet2 = new HashSet();
        length = strArr2.length;
        while (i < length) {
            hashSet2.add(strArr2[i]);
            i += STORAGE_SYSTEM_SETTING_V1;
        }
        return hashSet.equals(hashSet2);
    }

    private static void setExternalDeviceId(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("=");
            stringBuilder.append(str2);
            File file = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
            File file2 = new File(file, EXT_FILE);
            try {
                if (file.exists() && !file.isDirectory()) {
                    File file3;
                    Random random = new Random();
                    File parentFile = file.getParentFile();
                    String name = file.getName();
                    do {
                        file3 = new File(parentFile, name + random.nextInt() + ".tmp");
                    } while (file3.exists());
                    file.renameTo(file3);
                    file3.delete();
                }
                file.mkdirs();
                FileWriter fileWriter = new FileWriter(file2, DEBUG);
                fileWriter.write(Base64.encode(AESUtil.encrypt(AES_KEY, AES_KEY, stringBuilder.toString().getBytes()), "utf-8"));
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
            } catch (Exception e2) {
            }
        }
    }

    private static void setExternalV2DeviceId(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), EXT_DIR);
        File file2 = new File(file, EXT_FILE_V2);
        try {
            if (file.exists() && !file.isDirectory()) {
                File file3;
                Random random = new Random();
                File parentFile = file.getParentFile();
                String name = file.getName();
                do {
                    file3 = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file3.exists());
                file.renameTo(file3);
                file3.delete();
            }
            file.mkdirs();
            FileWriter fileWriter = new FileWriter(file2, DEBUG);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
        } catch (Exception e2) {
        }
    }

    private boolean tryPutSystemSettingValue(String str, String str2) {
        try {
            return System.putString(this.mContext.getContentResolver(), str, str2);
        } catch (Throwable e) {
            handleThrowable(e);
            return DEBUG;
        }
    }

    private boolean writeToCuidFile(String str) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.mContext.openFileOutput(SELF_CUID_FILE, STORAGE_SYSTEM_SETTING_V1);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            if (fileOutputStream == null) {
                return true;
            }
            try {
                fileOutputStream.close();
                return true;
            } catch (Throwable e) {
                handleThrowable(e);
                return true;
            }
        } catch (Throwable e2) {
            handleThrowable(e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable e22) {
                    handleThrowable(e22);
                }
            }
            return DEBUG;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable e3) {
                    handleThrowable(e3);
                }
            }
        }
    }

    private static void writeToFile(File file, byte[] bArr) {
        Throwable e;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e2) {
                        handleThrowable(e2);
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    handleThrowable(e2);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e22) {
                            handleThrowable(e22);
                        }
                    }
                } catch (Throwable th) {
                    e22 = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e4) {
                            handleThrowable(e4);
                        }
                    }
                    throw e22;
                }
            } catch (SecurityException e5) {
                e22 = e5;
                handleThrowable(e22);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e222) {
                        handleThrowable(e222);
                    }
                }
            }
        } catch (IOException e6) {
            e222 = e6;
            fileOutputStream = null;
            handleThrowable(e222);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (SecurityException e7) {
            e222 = e7;
            fileOutputStream = null;
            handleThrowable(e222);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th2) {
            e222 = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e222;
        }
    }
}
