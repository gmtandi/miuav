package com.baidu.tts.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0802n;
import java.io.UnsupportedEncodingException;
import org.p122a.p123a.C2915a;

public class ResourceTools {
    public static final int TEXT_LENGTH_LIMIT = 1024;

    public static C0829i format(String str, String str2, C0829i c0829i) {
        try {
            c0829i.m6873b(new String(c0829i.m6874c().getBytes(str), str2));
            c0829i.m6875c(str2);
            return c0829i;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppFilesDirPath(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/";
    }

    public static String getAppFilesPath(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir + "/files/";
        } catch (Exception e) {
            return context.getFilesDir().getAbsolutePath() + "/";
        }
    }

    public static String getByteMapAbsName(Context context, String str) {
        return getModelFileAbsName(context, str + ".bm");
    }

    public static String getDefaultResourcePath(Context context, String str) {
        return getSdcardFilesDirPath(context) + str;
    }

    public static String getModelFileAbsName(Context context, String str) {
        return FileTools.jointPathAndName(getAppFilesPath(context) + "modelDir/", str);
    }

    @SuppressLint({"SdCardPath"})
    public static String getSdcardFilesDirPath(Context context) {
        return "/sdcard/tts/";
    }

    public static C0802n isTextValid(String str) {
        if (TextUtils.isEmpty(str)) {
            return C0802n.TEXT_IS_EMPTY;
        }
        try {
            return str.getBytes(C0791d.GBK.m6737a()).length > TEXT_LENGTH_LIMIT ? C0802n.TEXT_IS_TOO_LONG : null;
        } catch (UnsupportedEncodingException e) {
            return C0802n.TEXT_ENCODE_IS_WRONG;
        }
    }

    public static byte[] stringToByteArrayAddNull(String str) {
        if (str == null) {
            str = C2915a.f14760f;
        }
        Object bytes = str.getBytes();
        Object obj = new byte[]{null};
        Object obj2 = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, obj2, 0, bytes.length);
        System.arraycopy(obj, 0, obj2, bytes.length, obj.length);
        return obj2;
    }
}
