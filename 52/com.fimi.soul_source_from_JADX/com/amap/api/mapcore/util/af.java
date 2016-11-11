package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.fimi.soul.entity.User;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class af {
    public static long m3411a() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long m3412a(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            j = file2.isDirectory() ? j + m3412a(file2) : j + file2.length();
        }
        return j;
    }

    public static OfflineMapProvince m3413a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setUrl(jSONObject.optString(SocialConstants.PARAM_URL));
        offlineMapProvince.setProvinceName(jSONObject.optString(User.FN_NAME));
        offlineMapProvince.setJianpin(jSONObject.optString("jianpin"));
        offlineMapProvince.setPinyin(jSONObject.optString("pinyin"));
        offlineMapProvince.setProvinceCode(jSONObject.optString("adcode"));
        offlineMapProvince.setVersion(jSONObject.optString(C2537j.aq));
        offlineMapProvince.setSize(Long.parseLong(jSONObject.optString("size")));
        offlineMapProvince.setCityList(m3417b(jSONObject));
        return offlineMapProvince;
    }

    public static String m3414a(Context context, String str) {
        try {
            return bj.m3617a(bh.m3592a(context).open(str));
        } catch (Throwable th) {
            ce.m3829a(th, "MapDownloadManager", "readOfflineAsset");
            th.printStackTrace();
            return null;
        }
    }

    public static void m3415a(String str) {
    }

    public static void m3416a(String str, Context context) {
        File[] listFiles = new File(bj.m3634b(context)).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.exists() && file.getName().contains(str)) {
                    m3419b(file);
                }
            }
            m3422c(bj.m3634b(context));
        }
    }

    public static ArrayList<OfflineMapCity> m3417b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("cities");
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        if (optJSONArray == null) {
            return arrayList;
        }
        if (optJSONArray.length() == 0) {
            arrayList.add(m3420c(jSONObject));
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(m3420c(optJSONObject));
            }
        }
        return arrayList;
    }

    public static List<OfflineMapProvince> m3418b(String str) {
        List<OfflineMapProvince> arrayList = new ArrayList();
        if (str == null || C2915a.f14760f.equals(str)) {
            return arrayList;
        }
        JSONObject optJSONObject = new JSONObject(str).optJSONObject("result");
        if (optJSONObject == null) {
            return arrayList;
        }
        optJSONObject = optJSONObject.optJSONObject("offlinemap_with_province_vfour");
        if (optJSONObject == null) {
            return arrayList;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("offlinemapinfo_with_province");
        if (optJSONObject2 == null) {
            return arrayList;
        }
        if (optJSONObject2.has(C2537j.aq)) {
            C0391i.f2486d = optJSONObject2.optString(C2537j.aq);
        }
        JSONArray optJSONArray = optJSONObject2.optJSONArray("provinces");
        if (optJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
            if (optJSONObject3 != null) {
                arrayList.add(m3413a(optJSONObject3));
            }
        }
        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("others");
        optJSONObject = null;
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            optJSONObject = optJSONArray2.getJSONObject(0);
        }
        if (optJSONObject == null) {
            return arrayList;
        }
        arrayList.add(m3413a(optJSONObject));
        return arrayList;
    }

    public static boolean m3419b(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!m3419b(listFiles[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static OfflineMapCity m3420c(JSONObject jSONObject) {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setAdcode(jSONObject.optString("adcode"));
        offlineMapCity.setUrl(jSONObject.optString(SocialConstants.PARAM_URL));
        offlineMapCity.setCity(jSONObject.optString(User.FN_NAME));
        offlineMapCity.setCode(jSONObject.optString("citycode"));
        offlineMapCity.setPinyin(jSONObject.optString("pinyin"));
        offlineMapCity.setJianpin(jSONObject.optString("jianpin"));
        offlineMapCity.setVersion(jSONObject.optString(C2537j.aq));
        offlineMapCity.setSize(Long.parseLong(jSONObject.optString("size")));
        return offlineMapCity;
    }

    public static String m3421c(File file) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        Throwable e;
        Object obj;
        Throwable th;
        String str = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    } catch (FileNotFoundException e2) {
                        e = e2;
                    } catch (IOException e3) {
                        e = e3;
                    }
                }
                str = stringBuffer.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e42) {
                        e42.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                obj = str;
                try {
                    ce.m3829a(e, "MapDownloadManager", "readOfflineSD filenotfound");
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e42222) {
                            e42222.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e422222) {
                            e422222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                obj = str;
                ce.m3829a(e, "MapDownloadManager", "readOfflineSD io");
                e.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4222222) {
                        e4222222.printStackTrace();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e42222222) {
                        e42222222.printStackTrace();
                    }
                }
                return str;
            } catch (Throwable e7) {
                obj = str;
                th = e7;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e8) {
            e7 = e8;
            obj = str;
            Object obj2 = str;
            ce.m3829a(e7, "MapDownloadManager", "readOfflineSD filenotfound");
            e7.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (IOException e9) {
            e7 = e9;
            bufferedReader = str;
            fileInputStream = str;
            ce.m3829a(e7, "MapDownloadManager", "readOfflineSD io");
            e7.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable e72) {
            bufferedReader = str;
            fileInputStream = str;
            th = e72;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    public static void m3422c(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.exists() && file2.isDirectory()) {
                        String[] list = file2.list();
                        if (list == null) {
                            file2.delete();
                        } else if (list.length == 0) {
                            file2.delete();
                        }
                    }
                }
            }
        }
    }
}
