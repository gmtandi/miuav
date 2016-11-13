package com.autonavi.amap.mapcore;

import android.util.Log;
import java.util.Hashtable;

public class MapCoreManager {
    static MapCoreManager mapCoreManager;
    private Hashtable<Integer, MapCore> mapCoreTable;

    static {
        try {
            System.loadLibrary("gdinamapv4sdk752");
            System.loadLibrary("gdinamapv4sdk752ex");
        } catch (Exception e) {
        }
    }

    private MapCoreManager() {
        this.mapCoreTable = new Hashtable();
    }

    private void OnMapDataRequired(int i, String[] strArr, int i2) {
        Log.i("mapcore", "OnMapDataRequired instance: " + i2);
        MapCore mapCore;
        if (i2 != 0) {
            mapCore = getMapCore(i2);
            if (mapCore != null) {
                mapCore.OnMapDataRequired(i, strArr);
                return;
            }
            return;
        }
        Log.i("mapcore", "instance is 0:  tilesNames: " + strArr.length);
        for (String str : strArr) {
            Log.i("mapcore", "instance is 0:  tilesNames: " + str);
        }
        for (Integer num : this.mapCoreTable.keySet()) {
            mapCore = (MapCore) this.mapCoreTable.get(num);
            if (mapCore != null) {
                mapCore.OnMapDataRequired(i, strArr);
            }
        }
    }

    private void OnMapLabelsRequired(int[] iArr, int i, int i2) {
        Log.i("mapcore", "OnMapLabelsRequired instance: " + i2);
        if (i2 != 0) {
            MapCore mapCore = getMapCore(i2);
            if (mapCore != null) {
                mapCore.OnMapLabelsRequired(iArr, i);
            }
        }
        Log.i("mapcore", "OnMapLabelsRequired instance is :" + i2 + "  chars: " + iArr.length + "  size: " + i);
        for (int i3 : iArr) {
            Log.i("mapcore", "OnMapLabelsRequired instance is : " + i2 + " chars: " + i3);
        }
    }

    public static MapCoreManager getInstance() {
        if (mapCoreManager == null) {
            mapCoreManager = new MapCoreManager();
        }
        return mapCoreManager;
    }

    private void onIndoorBuildingActivity(byte[] bArr, int i) {
        if (i != 0) {
            MapCore mapCore = getMapCore(i);
            if (mapCore != null) {
                mapCore.onIndoorBuildingActivity(bArr);
            }
        }
    }

    private void onIndoorDataRequired(int i, String[] strArr, int[] iArr, int[] iArr2, int i2) {
        if (i2 != 0) {
            MapCore mapCore = getMapCore(i2);
            if (mapCore != null) {
                mapCore.onIndoorDataRequired(i, strArr, iArr, iArr2);
            }
        }
    }

    public MapCore getMapCore(int i) {
        return (MapCore) this.mapCoreTable.get(Integer.valueOf(i));
    }

    public int getMapCoreSize() {
        return this.mapCoreTable.size();
    }

    public void putMapCore(int i, MapCore mapCore) {
        this.mapCoreTable.put(Integer.valueOf(i), mapCore);
    }

    public void removeMapCore(int i) {
        this.mapCoreTable.remove(Integer.valueOf(i));
    }
}
