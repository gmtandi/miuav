package com.autonavi.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.storage.StorageManager;
import com.amap.api.maps.MapsInitializer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class FileUtil {
    private static final String TAG = "FileUtil";

    public static void copy(Context context, String str, File file) {
        file.delete();
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        open.read(bArr);
        open.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public static void createNoMediaFileIfNotExist(String str) {
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File deleteFile : listFiles) {
                    deleteFile(deleteFile);
                }
            } else {
                return;
            }
        }
        file.delete();
    }

    public static String getExternalStroragePath(Context context) {
        int i = VERSION.SDK_INT;
        if (i >= 12) {
            try {
                String str;
                StorageManager storageManager = (StorageManager) context.getSystemService("storage");
                Method method = StorageManager.class.getMethod("getVolumeList", new Class[0]);
                Method method2 = StorageManager.class.getMethod("getVolumeState", new Class[]{String.class});
                Object[] objArr = (Object[]) method.invoke(storageManager, new Object[0]);
                Boolean.valueOf(false);
                String str2 = C2915a.f14760f;
                String str3 = C2915a.f14760f;
                str2 = C2915a.f14760f;
                String str4 = C2915a.f14760f;
                int length = objArr.length;
                int i2 = 0;
                while (i2 < length) {
                    Object obj = objArr[i2];
                    Method method3 = obj.getClass().getMethod("getPath", new Class[0]);
                    Method method4 = obj.getClass().getMethod("isRemovable", new Class[0]);
                    str2 = (String) method3.invoke(obj, new Object[0]);
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = method3.invoke(obj, new Object[0]);
                    String str5 = (String) method2.invoke(storageManager, objArr2);
                    Boolean bool = (Boolean) method4.invoke(obj, new Object[0]);
                    if (str2.toLowerCase().contains("private")) {
                        str5 = str4;
                        str2 = str3;
                    } else if (!bool.booleanValue()) {
                        continue;
                    } else if (str2 == null || str5 == null || !str5.equals("mounted")) {
                        str5 = str4;
                        str2 = str3;
                    } else {
                        if (i <= 18) {
                            str = str2;
                        } else {
                            try {
                                File[] externalFilesDirs = context.getExternalFilesDirs(null);
                                if (externalFilesDirs == null) {
                                    str2 = null;
                                } else if (externalFilesDirs.length > 1) {
                                    str2 = externalFilesDirs[1].getAbsolutePath();
                                }
                                str = str2;
                            } catch (Exception e) {
                                str = str2;
                            }
                        }
                        if (i > 18) {
                            return (str == null || str3 == null || str4 == null || !str4.equals("mounted")) ? str : str3;
                        } else {
                            if (!(str3 == null || str4 == null)) {
                                if (str4.equals("mounted")) {
                                    str = str3;
                                }
                            }
                            return str;
                        }
                    }
                    i2++;
                    str4 = str5;
                    str3 = str2;
                }
                str = null;
                if (i > 18) {
                    if (str4.equals("mounted")) {
                        str = str3;
                    }
                    return str;
                }
                if (str == null) {
                }
            } catch (Exception e2) {
            }
        }
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().toString() : null;
    }

    public static String getMapBaseStorage(Context context) {
        File file;
        String str = "map_base_path";
        if (VERSION.SDK_INT > 18) {
            str = "map_base_path_v44";
        }
        String str2 = C2915a.f14760f;
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_path", 0);
        str2 = (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.trim().length() <= 0) ? sharedPreferences.getString(str, C2915a.f14760f) : MapsInitializer.sdcardDir;
        if (str2 != null && str2.length() > 2) {
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                if (file.canWrite()) {
                    return str2;
                }
                str2 = context.getCacheDir().toString();
                if (str2 != null && str2.length() > 2 && new File(str2).isDirectory()) {
                    return str2;
                }
            }
        }
        str2 = getExternalStroragePath(context);
        if (str2 != null && str2.length() > 2) {
            str2 = str2 + File.separator + MapTilsCacheAndResManager.AUTONAVI_PATH;
            file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            if (file.isDirectory()) {
                Editor edit = sharedPreferences.edit();
                edit.putString(str, str2);
                edit.commit();
                createNoMediaFileIfNotExist(str2);
                return str2;
            }
        }
        str = context.getCacheDir().toString();
        if (str == null || str.length() <= 2) {
            return str;
        }
        str = str + File.separator + MapTilsCacheAndResManager.AUTONAVI_PATH;
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2.isDirectory() ? str : str;
    }

    public static byte[] readFileContents(String str) {
        byte[] bArr = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr2 = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                byteArrayOutputStream.close();
                fileInputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public static void writeDatasToFile(String str, byte[] bArr) {
        WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            } finally {
                writeLock.unlock();
            }
        }
        writeLock.unlock();
    }
}
