package com.autonavi.amap.mapcore;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager.RetStyleIconsFile;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.C1154b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapTilsCacheAndResManagerImpl extends MapTilsCacheAndResManager {
    private static final int CREATE_DIR_COUNT = 5;
    private static final long Style_Update_Internal_Time = 43200000;
    private static volatile MapTilsCacheAndResManagerImpl instance;
    private String mCachePath;
    private Context mContext;
    private String mMapBaseDataPath;
    private String mMapExtResPath;
    private String mMapOfflinePath;
    private String mMapOnlineTilesPath;

    static {
        instance = null;
    }

    private MapTilsCacheAndResManagerImpl(Context context) {
        this.mContext = context;
        init();
    }

    private void addUdateRecorder(String str) {
        Editor edit = this.mContext.getSharedPreferences("styles_icons_update_recorder", 0).edit();
        edit.putLong(str, System.currentTimeMillis());
        edit.commit();
    }

    static void copyAssertToTmp(Context context, String str, File file) {
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    byte[] decodeAssetResData = ResUtil.decodeAssetResData(context, str);
                    if (decodeAssetResData != null) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(decodeAssetResData);
                        fileOutputStream.close();
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    private String getFilePreName(String str) {
        String[] split = str.split("_");
        return split[0] + "_" + split[1] + "_" + split[2];
    }

    private byte[] getStyleIconsData(String str, int i, RetStyleIconsFile retStyleIconsFile) {
        CharSequence filePreName = getFilePreName(str);
        RetStyleIconsFile retStyleIconsFile2 = new RetStyleIconsFile();
        setRetFile(retStyleIconsFile2, str);
        File[] listFiles = new File(this.mMapExtResPath).listFiles();
        if (listFiles != null) {
            try {
                for (File file : listFiles) {
                    if (file.getName().contains(filePreName)) {
                        setRetFile(retStyleIconsFile, file.getName());
                        if (retStyleIconsFile2.serverVersion < retStyleIconsFile.serverVersion) {
                            byte[] readFileContents = FileUtil.readFileContents(file.getAbsolutePath());
                            if (readFileContents != null && readFileContents.length > 0) {
                                if (i != 1) {
                                    return readFileContents;
                                }
                                if (readFileContents.length == Convert.getInt(readFileContents, 0) + 4) {
                                    return readFileContents;
                                }
                                FileUtil.deleteFile(file);
                            }
                        } else {
                            FileUtil.deleteFile(file);
                        }
                    }
                }
            } catch (OutOfMemoryError e) {
            }
        }
        setRetFile(retStyleIconsFile, str);
        return ResUtil.decodeAssetResData(this.mContext, "map_assets/" + str);
    }

    private void initRelease() {
        int i = 0;
        File file = new File(FileUtil.getMapBaseStorage(this.mContext));
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, UriUtil.DATA_SCHEME);
        if (!file2.exists()) {
            file2.mkdir();
        }
        this.mMapBaseDataPath = file2.toString() + "/";
        file = new File(file2, MapTilsCacheAndResManager.MAP_DATA_OFFLINE_PATH_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        this.mMapOfflinePath = file.toString() + "/";
        File file3 = new File(file2, C1154b.f5233d);
        int i2 = 0;
        while (!file3.exists()) {
            int i3 = i2 + 1;
            if (i2 >= CREATE_DIR_COUNT) {
                break;
            }
            file3.mkdir();
            i2 = i3;
        }
        this.mCachePath = file3.toString() + "/";
        File file4 = new File(file3, MapTilsCacheAndResManager.MAP_TILES_PATH_NAME);
        i2 = 0;
        while (!file4.exists()) {
            i3 = i2 + 1;
            if (i2 >= CREATE_DIR_COUNT) {
                break;
            }
            file4.mkdir();
            i2 = i3;
        }
        this.mMapOnlineTilesPath = file4.toString() + "/";
        file2 = new File(file3, MapTilsCacheAndResManager.MAP_RES_EXT_PATH_NAME);
        while (!file2.exists()) {
            i2 = i + 1;
            if (i >= CREATE_DIR_COUNT) {
                break;
            }
            file2.mkdir();
            i = i2;
        }
        this.mMapExtResPath = file2.toString() + "/";
    }

    public static MapTilsCacheAndResManagerImpl instance(Context context) {
        if (instance == null) {
            instance = new MapTilsCacheAndResManagerImpl(context);
        }
        return instance;
    }

    private void setRetFile(RetStyleIconsFile retStyleIconsFile, String str) {
        if (retStyleIconsFile != null) {
            retStyleIconsFile.fullName = str;
            String[] split = str.split("_|\\.");
            retStyleIconsFile.name = split[0] + "_" + split[1];
            retStyleIconsFile.clientVersion = Integer.parseInt(split[2]);
            retStyleIconsFile.serverVersion = Integer.parseInt(split[3]);
        }
    }

    public synchronized boolean canUpate(String str) {
        boolean z = false;
        synchronized (this) {
            long j = this.mContext.getSharedPreferences("styles_icons_update_recorder", 0).getLong(str, -1);
            if (j <= 0 || System.currentTimeMillis() - j >= Style_Update_Internal_Time) {
                z = true;
            }
            if (z) {
                addUdateRecorder(str);
            }
        }
        return z;
    }

    public void checkDir() {
        init();
    }

    public void clearOnlineMapTilsCache() {
        File file = new File(this.mMapOnlineTilesPath);
        if (file.exists()) {
            FileUtil.deleteFile(file);
            init();
        }
    }

    public String getBaseMapPath() {
        return this.mMapBaseDataPath;
    }

    public byte[] getIconsData(String str, RetStyleIconsFile retStyleIconsFile) {
        try {
            return getStyleIconsData(str, 2, retStyleIconsFile);
        } catch (Throwable th) {
            return null;
        }
    }

    public String getMapCachePath() {
        return this.mCachePath;
    }

    public String getMapExtResPath() {
        return this.mMapExtResPath;
    }

    public String getMapOfflineDataPath() {
        return this.mMapOfflinePath;
    }

    public String getMapOnlineDataPath() {
        return this.mMapOnlineTilesPath;
    }

    public byte[] getOtherResData(String str) {
        return ResUtil.decodeAssetResData(this.mContext, "map_assets/" + str);
    }

    public byte[] getStyleData(String str, RetStyleIconsFile retStyleIconsFile) {
        try {
            return getStyleIconsData(str, 1, retStyleIconsFile);
        } catch (Throwable th) {
            return null;
        }
    }

    public void init() {
        try {
            initRelease();
        } catch (Throwable th) {
        }
    }

    void initDebug() {
    }

    public synchronized void reset() {
        instance = null;
    }

    public void saveFile(String str, int i, int i2, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            File[] listFiles = new File(this.mMapExtResPath).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.getName().contains(str)) {
                        file.delete();
                        break;
                    }
                }
            }
            FileUtil.writeDatasToFile(this.mMapExtResPath + (str + "_" + i + "_" + i2 + ".data"), bArr);
        }
    }
}
