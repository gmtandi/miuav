package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.fimi.kernel.C1154b;
import com.fimi.kernel.p084e.C1173l;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.base.C1236a;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.market.sdk.C2538k;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.mob.tools.utils.R */
public class C2178R {
    private static float density;
    private static int deviceWidth;
    private static Object rp;

    public static void clearCache(Context context) {
        C2178R.deleteFileAndFolder(new File(C2178R.getCachePath(context, null)));
    }

    public static String contentUriToPath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        if (new File(uri.getPath()).exists()) {
            return uri.getPath();
        }
        String str;
        try {
            Cursor query;
            Cursor query2;
            if (VERSION.SDK_INT >= 19) {
                Class cls = Class.forName("android.provider.DocumentsContract");
                Method method = cls.getMethod("isDocumentUri", new Class[]{Context.class, Uri.class});
                method.setAccessible(true);
                if (Boolean.TRUE.equals(method.invoke(null, new Object[]{context, uri}))) {
                    Method method2 = cls.getMethod("getDocumentId", new Class[]{Uri.class});
                    method2.setAccessible(true);
                    str = String.valueOf(method2.invoke(null, new Object[]{uri})).split(":")[1];
                    query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "_id=?", new String[]{str}, null);
                    query2 = query != null ? context.getContentResolver().query(uri, null, null, null, null) : query;
                    if (query2 == null) {
                        str = query2.moveToFirst() ? query2.getString(query2.getColumnIndex("_data")) : null;
                        query2.close();
                    } else {
                        str = null;
                    }
                    return str;
                }
            }
            query = null;
            if (query != null) {
            }
            if (query2 == null) {
                str = null;
            } else {
                if (query2.moveToFirst()) {
                }
                query2.close();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            str = null;
        }
        return str;
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_CUT];
        int read = fileInputStream.read(bArr);
        while (read > 0) {
            fileOutputStream.write(bArr, 0, read);
            read = fileInputStream.read(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static boolean copyFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !new File(str).exists()) {
            return false;
        }
        try {
            C2178R.copyFile(new FileInputStream(str), new FileOutputStream(str2));
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static int[] covertTimeInYears(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis <= 0) {
            return new int[]{0, 0};
        }
        long j2 = currentTimeMillis / 1000;
        if (j2 < 60) {
            return new int[]{(int) j2, 0};
        }
        j2 /= 60;
        if (j2 < 60) {
            return new int[]{(int) j2, 1};
        }
        j2 /= 60;
        if (j2 < 24) {
            return new int[]{(int) j2, 2};
        }
        j2 /= 24;
        if (j2 < 30) {
            return new int[]{(int) j2, 3};
        }
        if (j2 / 30 < 12) {
            return new int[]{(int) (j2 / 30), 4};
        }
        return new int[]{(int) ((j2 / 30) / 12), 5};
    }

    public static long dateStrToLong(String str) {
        return new SimpleDateFormat(C1173l.f5330b).parse(str, new ParsePosition(0)).getTime();
    }

    public static long dateToLong(String str) {
        try {
            Date date = new Date(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance.getTimeInMillis();
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return 0;
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length < 2 || split2[1] == null) {
                    bundle.putString(URLDecoder.decode(split2[0]), C2915a.f14760f);
                } else {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }

    public static void deleteFileAndFolder(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                file.delete();
                return;
            }
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3.isDirectory()) {
                    C2178R.deleteFileAndFolder(file3);
                } else {
                    file3.delete();
                }
            }
            file.delete();
        }
    }

    public static void deleteFilesInFolder(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        C2178R.deleteFilesInFolder(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
        }
    }

    public static int designToDevice(Context context, float f, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) (((((float) i) * density) / f) + 0.5f);
    }

    public static int designToDevice(Context context, int i, int i2) {
        if (deviceWidth == 0) {
            int[] screenSize = C2178R.getScreenSize(context);
            deviceWidth = screenSize[0] < screenSize[1] ? screenSize[0] : screenSize[1];
        }
        return (int) (((((float) i2) * ((float) deviceWidth)) / ((float) i)) + 0.5f);
    }

    private static int digit(int i, int i2) {
        if (i2 < 2 || i2 > 36) {
            return -1;
        }
        int i3 = (48 > i || i > 57) ? (97 > i || i > Opcodes.ISHR) ? (65 > i || i > 90) ? -1 : (i - 65) + 10 : (i - 97) + 10 : i - 48;
        return i3 >= i2 ? -1 : i3;
    }

    public static int dipToPx(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) * density) + 0.5f);
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (String str : bundle.keySet()) {
            Object obj2 = bundle.get(str);
            if (obj2 == null) {
                obj2 = C2915a.f14760f;
            }
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(Data.urlEncode(str) + "=" + Data.urlEncode(String.valueOf(obj2)));
        }
        return stringBuilder.toString();
    }

    public static String encodeUrl(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair kVPair = (KVPair) it.next();
            if (i > 0) {
                stringBuilder.append('&');
            }
            String str = kVPair.name;
            String str2 = (String) kVPair.value;
            if (str != null) {
                if (str2 == null) {
                    str2 = C2915a.f14760f;
                }
                stringBuilder.append(Data.urlEncode(str) + "=" + Data.urlEncode(str2));
                i++;
            }
        }
        return stringBuilder.toString();
    }

    public static <T> T forceCast(Object obj) {
        return C2178R.forceCast(obj, null);
    }

    public static <T> T forceCast(Object obj, T t) {
        return obj == null ? t : obj;
    }

    public static int getAnimRes(Context context, String str) {
        return C2178R.getResId(context, "anim", str);
    }

    public static int getBitmapRes(Context context, String str) {
        return C2178R.getResId(context, "drawable", str);
    }

    public static String getCachePath(Context context, String str) {
        String str2 = context.getFilesDir().getAbsolutePath() + "/Mob/cache/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        try {
            if (instance.getSdcardState() && instance.checkPermission("android.permission.READ_EXTERNAL_STORAGE")) {
                str2 = instance.getSdcardPath() + "/Mob/" + instance.getPackageName() + "/cache/";
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + str + "/";
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String getCacheRoot(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        if (instance.getSdcardState()) {
            str = instance.getSdcardPath() + "/Mob/";
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static int getColorRes(Context context, String str) {
        return C2178R.getResId(context, "color", str);
    }

    public static long getFileSize(File file) {
        int i = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        String[] list = file.list();
        int i2 = 0;
        while (i < list.length) {
            i2 = (int) (((long) i2) + C2178R.getFileSize(new File(file, list[i])));
            i++;
        }
        return (long) i2;
    }

    public static long getFileSize(String str) {
        return TextUtils.isEmpty(str) ? 0 : C2178R.getFileSize(new File(str));
    }

    public static int getIdRes(Context context, String str) {
        return C2178R.getResId(context, LocaleUtil.INDONESIAN, str);
    }

    public static String getImageCachePath(Context context) {
        return C2178R.getCachePath(context, C1154b.f5231b);
    }

    public static int getLayoutRes(Context context, String str) {
        return C2178R.getResId(context, "layout", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return C2178R.getResId(context, "plurals", str);
    }

    public static int getRawRes(Context context, String str) {
        return C2178R.getResId(context, "raw", str);
    }

    public static int getResId(Context context, String str, String str2) {
        int i = 0;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return i;
        }
        int intValue;
        String packageName;
        if (rp != null) {
            try {
                Method method = rp.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class});
                method.setAccessible(true);
                intValue = ((Integer) method.invoke(rp, new Object[]{context, str, str2})).intValue();
            } catch (Throwable th) {
                MobLog.getInstance().m738d(th);
            }
            if (intValue <= 0) {
                return intValue;
            }
            packageName = context.getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                return intValue;
            }
            if (intValue <= 0) {
                intValue = context.getResources().getIdentifier(str2, str, packageName);
                if (intValue <= 0) {
                    intValue = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
                }
            }
            if (intValue <= 0) {
                return intValue;
            }
            System.err.println("failed to parse " + str + " resource \"" + str2 + "\"");
            return intValue;
        }
        intValue = i;
        if (intValue <= 0) {
            return intValue;
        }
        packageName = context.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            return intValue;
        }
        if (intValue <= 0) {
            intValue = context.getResources().getIdentifier(str2, str, packageName);
            if (intValue <= 0) {
                intValue = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
            }
        }
        if (intValue <= 0) {
            return intValue;
        }
        System.err.println("failed to parse " + str + " resource \"" + str2 + "\"");
        return intValue;
    }

    public static int getScreenHeight(Context context) {
        return C2178R.getScreenSize(context)[1];
    }

    public static int[] getScreenSize(Context context) {
        WindowManager windowManager;
        try {
            windowManager = (WindowManager) context.getSystemService("window");
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            windowManager = null;
        }
        if (windowManager == null) {
            return new int[]{0, 0};
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            defaultDisplay.getMetrics(new DisplayMetrics());
            return new int[]{r1.widthPixels, r1.heightPixels};
        }
        try {
            Point point = new Point();
            Method method = defaultDisplay.getClass().getMethod("getRealSize", new Class[]{Point.class});
            method.setAccessible(true);
            method.invoke(defaultDisplay, new Object[]{point});
            return new int[]{point.x, point.y};
        } catch (Throwable th2) {
            MobLog.getInstance().m750w(th2);
            return new int[]{0, 0};
        }
    }

    public static int getScreenWidth(Context context) {
        return C2178R.getScreenSize(context)[0];
    }

    public static int getStringArrayRes(Context context, String str) {
        return C2178R.getResId(context, "array", str);
    }

    public static int getStringRes(Context context, String str) {
        return C2178R.getResId(context, "string", str);
    }

    public static int getStyleRes(Context context, String str) {
        return C2178R.getResId(context, "style", str);
    }

    public static int getTextLengthInWord(String str) {
        int i = 0;
        char[] toCharArray = str == null ? new char[0] : str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 += toCharArray[i] < '\u0100' ? 1 : 2;
            i++;
        }
        return i2;
    }

    private static Throwable invalidInt(String str) {
        throw new Throwable("Invalid int: \"" + str + "\"");
    }

    public static Date longToDate(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }

    public static String longToTime(long j, int i) {
        String str = "yyyy-MM-dd kk:mm:ss";
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "yyyy";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = C1173l.f5331c;
                break;
            case Type.INT /*5*/:
                str = C1173l.f5330b;
                break;
            case Type.OBJECT /*10*/:
                str = "yyyy-MM-dd kk";
                break;
            case Opcodes.FCONST_1 /*12*/:
                str = "yyyy-MM-dd kk:mm";
                break;
        }
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }

    public static int parseInt(String str) {
        return C2178R.parseInt(str, 10);
    }

    public static int parseInt(String str, int i) {
        int i2 = 1;
        if (i < 2 || i > 36) {
            throw new Throwable("Invalid radix: " + i);
        } else if (str == null) {
            throw C2178R.invalidInt(str);
        } else {
            int length = str.length();
            if (length == 0) {
                throw C2178R.invalidInt(str);
            }
            boolean z = str.charAt(0) == SignatureVisitor.SUPER;
            if (!z) {
                i2 = 0;
            } else if (1 == length) {
                throw C2178R.invalidInt(str);
            }
            return C2178R.parseInt(str, i2, i, z);
        }
    }

    private static int parseInt(String str, int i, int i2, boolean z) {
        int i3 = C1186y.f5353a / i2;
        int i4 = 0;
        int length = str.length();
        while (i < length) {
            int i5 = i + 1;
            int digit = C2178R.digit(str.charAt(i), i2);
            if (digit == -1) {
                throw C2178R.invalidInt(str);
            } else if (i3 > i4) {
                throw C2178R.invalidInt(str);
            } else {
                digit = (i4 * i2) - digit;
                if (digit > i4) {
                    throw C2178R.invalidInt(str);
                }
                i4 = digit;
                i = i5;
            }
        }
        if (!z) {
            i4 = -i4;
            if (i4 < 0) {
                throw C2178R.invalidInt(str);
            }
        }
        return i4;
    }

    public static long parseLong(String str) {
        return C2178R.parseLong(str, 10);
    }

    public static long parseLong(String str, int i) {
        int i2 = 1;
        if (i < 2 || i > 36) {
            throw new Throwable("Invalid radix: " + i);
        } else if (str == null) {
            throw new Throwable("Invalid long: \"" + str + "\"");
        } else {
            int length = str.length();
            if (length == 0) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
            boolean z = str.charAt(0) == SignatureVisitor.SUPER;
            if (!z) {
                i2 = 0;
            } else if (1 == length) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
            return C2178R.parseLong(str, i2, i, z);
        }
    }

    private static long parseLong(String str, int i, int i2, boolean z) {
        long j = Long.MIN_VALUE / ((long) i2);
        long j2 = 0;
        long length = (long) str.length();
        while (((long) i) < length) {
            int i3 = i + 1;
            int digit = C2178R.digit(str.charAt(i), i2);
            if (digit == -1) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            } else if (j > j2) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            } else {
                long j3 = (((long) i2) * j2) - ((long) digit);
                if (j3 > j2) {
                    throw new Throwable("Invalid long: \"" + str + "\"");
                }
                j2 = j3;
                i = i3;
            }
        }
        if (!z) {
            j2 = -j2;
            if (j2 < 0) {
                throw new Throwable("Invalid long: \"" + str + "\"");
            }
        }
        return j2;
    }

    public static Uri pathToContentUri(Context context, String str) {
        Cursor query = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{C2538k._ID}, "_data=? ", new String[]{str}, null);
        if (query != null && query.moveToFirst()) {
            return Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), C2915a.f14760f + query.getInt(query.getColumnIndex(C2538k._ID)));
        } else if (!new File(str).exists()) {
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            return context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    public static int pxToDip(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) / density) + 0.5f);
    }

    public static Object readObjectFromFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file;
        try {
            file = new File(str);
            if (!file.exists()) {
                file = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            file = null;
        }
        if (file == null) {
            return null;
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    public static boolean saveObjectToFile(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            File file;
            try {
                file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (Throwable th) {
                th.printStackTrace();
                file = null;
            }
            if (file != null) {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
                    objectOutputStream.writeObject(obj);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    return true;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
        return false;
    }

    @Deprecated
    public static void setCacheRoot(String str) {
    }

    public static void setResourceProvider(Object obj) {
        try {
            if (obj.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class}) != null) {
                rp = obj;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
    }

    public static long strToDate(String str) {
        return new SimpleDateFormat(C1236a.f5614l).parse(str, new ParsePosition(0)).getTime();
    }

    public static String toString(Object obj) {
        return obj == null ? C2915a.f14760f : obj.toString();
    }

    public static String toWordText(String str, int i) {
        char[] toCharArray = str.toCharArray();
        int i2 = i * 2;
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = i2;
        for (char c : toCharArray) {
            i3 -= c < '\u0100' ? 1 : 2;
            if (i3 < 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static Bundle urlToBundle(String str) {
        int indexOf = str.indexOf("://");
        try {
            URL url = new URL(indexOf >= 0 ? "http://" + str.substring(indexOf + 1) : "http://" + str);
            Bundle decodeUrl = C2178R.decodeUrl(url.getQuery());
            decodeUrl.putAll(C2178R.decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return new Bundle();
        }
    }
}