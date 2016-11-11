package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseMapCallImplement implements IBaseMapCallback, IMapCallback {
    private ArrayList<MapSourceGridData> bldReqMapGrids;
    ConnectionManager connectionManager;
    private ArrayList<MapSourceGridData> curBldMapGrids;
    private ArrayList<MapSourceGridData> curIndoorMapGirds;
    private ArrayList<MapSourceGridData> curPoiMapGrids;
    private ArrayList<MapSourceGridData> curRegionMapGrids;
    private ArrayList<MapSourceGridData> curRoadMapGrids;
    private ArrayList<MapSourceGridData> curScreenGirds;
    private ArrayList<MapSourceGridData> curStiMapGirds;
    private ArrayList<MapSourceGridData> curVectmcMapGirds;
    private ArrayList<MapSourceGridData> indoorMapGrids;
    Object mapGridFillLock;
    private ArrayList<MapSourceGridData> poiReqMapGrids;
    private ArrayList<MapSourceGridData> regionReqMapGrids;
    private ArrayList<MapSourceGridData> roadReqMapGrids;
    private ArrayList<MapSourceGridData> stiReqMapGirds;
    TextTextureGenerator textTextureGenerator;
    C0612d tileProcessCtrl;
    private ArrayList<MapSourceGridData> vectmcReqMapGirds;
    private ArrayList<MapSourceGridData> versionMapGrids;

    public BaseMapCallImplement() {
        this.roadReqMapGrids = new ArrayList();
        this.bldReqMapGrids = new ArrayList();
        this.regionReqMapGrids = new ArrayList();
        this.poiReqMapGrids = new ArrayList();
        this.versionMapGrids = new ArrayList();
        this.indoorMapGrids = new ArrayList();
        this.vectmcReqMapGirds = new ArrayList();
        this.stiReqMapGirds = new ArrayList();
        this.curRoadMapGrids = new ArrayList();
        this.curBldMapGrids = new ArrayList();
        this.curRegionMapGrids = new ArrayList();
        this.curPoiMapGrids = new ArrayList();
        this.curVectmcMapGirds = new ArrayList();
        this.curStiMapGirds = new ArrayList();
        this.curScreenGirds = new ArrayList();
        this.curIndoorMapGirds = new ArrayList();
        this.connectionManager = null;
        this.tileProcessCtrl = null;
        this.textTextureGenerator = null;
        this.mapGridFillLock = new Object();
    }

    private boolean isGridInList(String str, ArrayList<MapSourceGridData> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MapSourceGridData) arrayList.get(i)).getGridName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isIndoorGridInList(String str, ArrayList<MapSourceGridData> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MapSourceGridData) arrayList.get(i)).getKeyGridName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public byte[] OnMapCharsWidthsRequired(MapCore mapCore, int[] iArr, int i, int i2) {
        if (this.textTextureGenerator == null) {
            this.textTextureGenerator = new TextTextureGenerator();
        }
        return this.textTextureGenerator.getCharsWidths(iArr);
    }

    public void OnMapDataRequired(MapCore mapCore, int i, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            ArrayList reqGridList = getReqGridList(i);
            if (reqGridList != null) {
                reqGridList.clear();
                for (String mapSourceGridData : strArr) {
                    reqGridList.add(new MapSourceGridData(mapSourceGridData, i));
                }
                if (i != 5) {
                    proccessRequiredData(mapCore, reqGridList, i);
                }
            }
        }
    }

    public void OnMapDestory(MapCore mapCore) {
        try {
            destoryMap(mapCore);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void OnMapLabelsRequired(MapCore mapCore, int[] iArr, int i) {
        if (iArr != null && i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = iArr[i2];
                this.textTextureGenerator = new TextTextureGenerator();
                byte[] textPixelBuffer = this.textTextureGenerator.getTextPixelBuffer(i3);
                if (textPixelBuffer != null) {
                    mapCore.putCharbitmap(i3, textPixelBuffer);
                }
            }
        }
    }

    public void OnMapProcessEvent(MapCore mapCore) {
    }

    public void OnMapSurfaceCreate(MapCore mapCore) {
    }

    public void OnMapSurfaceRenderer(GL10 gl10, MapCore mapCore, int i) {
        if (i == 11) {
            synchronized (this.mapGridFillLock) {
                mapCore.fillCurGridListWithDataType(this.curPoiMapGrids, 8);
                mapCore.fillCurGridListWithDataType(this.curRoadMapGrids, 0);
                mapCore.fillCurGridListWithDataType(this.curRegionMapGrids, 7);
                mapCore.fillCurGridListWithDataType(this.curBldMapGrids, 1);
                mapCore.fillCurGridListWithDataType(this.curVectmcMapGirds, 4);
                mapCore.fillCurGridListWithDataType(this.curStiMapGirds, 3);
                mapCore.fillCurGridListWithDataType(this.curIndoorMapGirds, 10);
            }
        }
    }

    public synchronized void destoryMap(MapCore mapCore) {
        if (this.connectionManager != null) {
            this.connectionManager.threadFlag = false;
            if (this.connectionManager.isAlive()) {
                try {
                    this.connectionManager.interrupt();
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                } catch (Throwable th) {
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                }
            }
        }
        if (this.tileProcessCtrl != null) {
            this.tileProcessCtrl.m5295a();
        }
    }

    public ArrayList<MapSourceGridData> getCurGridList(int i) {
        return i == 0 ? this.curRoadMapGrids : i == 1 ? this.curBldMapGrids : i == 7 ? this.curRegionMapGrids : i == 8 ? this.curPoiMapGrids : i == 4 ? this.curVectmcMapGirds : i == 5 ? this.curScreenGirds : i == 10 ? this.curIndoorMapGirds : i == 3 ? this.curStiMapGirds : null;
    }

    public ArrayList<MapSourceGridData> getReqGridList(int i) {
        return i == 0 ? this.roadReqMapGrids : i == 1 ? this.bldReqMapGrids : i == 7 ? this.regionReqMapGrids : i == 8 ? this.poiReqMapGrids : i == 4 ? this.vectmcReqMapGirds : i == 5 ? this.curScreenGirds : i == 3 ? this.stiReqMapGirds : i == 9 ? this.versionMapGrids : i == 10 ? this.indoorMapGrids : null;
    }

    public boolean isGridInScreen(int i, String str) {
        try {
            if (!isMapEngineValid()) {
                return false;
            }
            synchronized (this.mapGridFillLock) {
                if (isGridInList(str, getCurGridList(i))) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isGridsInScreen(ArrayList<MapSourceGridData> arrayList, int i) {
        try {
            if (arrayList.size() == 0) {
                return false;
            }
            if (!isMapEngineValid()) {
                return false;
            }
            synchronized (this.mapGridFillLock) {
                ArrayList curGridList = getCurGridList(i);
                if (curGridList == null) {
                    return true;
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (isGridInList(((MapSourceGridData) arrayList.get(i2)).getGridName(), curGridList)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean isIndoorGridInScreen(int i, String str, short s) {
        try {
            if (!isMapEngineValid()) {
                return false;
            }
            synchronized (this.mapGridFillLock) {
                if (isIndoorGridInList(i + "-" + str + "-" + s, getCurGridList(i))) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isIndoorGridsInScreen(ArrayList<MapSourceGridData> arrayList, int i) {
        try {
            if (arrayList.size() == 0) {
                return false;
            }
            if (!isMapEngineValid()) {
                return false;
            }
            synchronized (this.mapGridFillLock) {
                ArrayList curGridList = getCurGridList(i);
                if (curGridList == null) {
                    return true;
                }
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (isIndoorGridInList(((MapSourceGridData) arrayList.get(i2)).getKeyGridName(), curGridList)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public synchronized void newMap(MapCore mapCore) {
        this.connectionManager = new ConnectionManager(mapCore);
        this.tileProcessCtrl = new C0612d();
        this.connectionManager.start();
    }

    public synchronized void onPause() {
        try {
            if (this.connectionManager != null) {
                this.connectionManager.threadFlag = false;
                if (this.connectionManager.isAlive()) {
                    this.connectionManager.interrupt();
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void onResume(MapCore mapCore) {
        try {
            this.connectionManager = new ConnectionManager(mapCore);
            this.tileProcessCtrl = new C0612d();
            this.connectionManager.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void proccessRequiredData(MapCore mapCore, ArrayList<MapSourceGridData> arrayList, int i) {
        int i2 = 0;
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            MapSourceGridData mapSourceGridData = (MapSourceGridData) arrayList.get(i3);
            if (this.tileProcessCtrl == null || !this.tileProcessCtrl.m5298b(mapSourceGridData.getKeyGridName())) {
                if (i == 4) {
                    VTMCDataCache instance = VTMCDataCache.getInstance();
                    C0614f data = instance.getData(mapSourceGridData.getGridName(), true);
                    C0614f data2 = instance.getData(mapSourceGridData.getGridName(), false);
                    if (data != null) {
                        mapSourceGridData.obj = data.f3732d;
                    }
                    if (data2 == null || data2.f3729a == null || data2.f3729a.length <= 0) {
                        arrayList2.add(mapSourceGridData);
                    } else {
                        mapCore.putMapData(data2.f3729a, 0, data2.f3729a.length, i, data2.f3731c);
                    }
                } else {
                    try {
                        String str = mapSourceGridData.gridName;
                        if (i == 10) {
                            str = mapSourceGridData.gridName + "-" + mapSourceGridData.mIndoorIndex;
                        }
                        C0613e recoder = VMapDataCache.getInstance().getRecoder(str, i);
                        if (recoder == null || recoder.f3725a == null || recoder.f3725a.length() > 0) {
                            arrayList2.add(mapSourceGridData);
                        } else {
                            arrayList2.add(mapSourceGridData);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (arrayList2.size() > 0) {
            BaseMapLoader indoorMapLoader = i == 10 ? new IndoorMapLoader(mapCore, this, i) : i == 11 ? null : new NormalMapLoader(mapCore, this, i);
            while (i2 < arrayList2.size()) {
                mapSourceGridData = (MapSourceGridData) arrayList2.get(i2);
                this.tileProcessCtrl.m5299c(mapSourceGridData.getKeyGridName());
                indoorMapLoader.addReuqestTiles(mapSourceGridData);
                i2++;
            }
            if (this.connectionManager != null) {
                this.connectionManager.insertConntionTask(indoorMapLoader);
            }
        }
    }
}
