package com.autonavi.amap.mapcore;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class NormalMapLoader extends BaseMapLoader {
    public NormalMapLoader(MapCore mapCore, BaseMapCallImplement baseMapCallImplement, int i) {
        this.datasource = i;
        this.mGLMapEngine = mapCore;
        this.mMapCallback = baseMapCallImplement;
        this.createtime = System.currentTimeMillis();
    }

    protected String getGridParma() {
        return getGridParmaV4();
    }

    public String getGridParmaV4() {
        String str = ";";
        int i = 0;
        String str2 = null;
        while (i < this.mapTiles.size()) {
            String str3;
            String gridName = ((MapSourceGridData) this.mapTiles.get(i)).getGridName();
            if (gridName == null || gridName.length() == 0 || containllegal(gridName)) {
                str3 = str2;
            } else if (isAssic(gridName)) {
                if (this.datasource != 4 || ((MapSourceGridData) this.mapTiles.get(i)).obj == null) {
                    str3 = gridName;
                } else {
                    try {
                        str3 = gridName + "-" + URLEncoder.encode((String) ((MapSourceGridData) this.mapTiles.get(i)).obj, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        str3 = str2;
                    }
                }
                str3 = str2 == null ? str3 + str : str2 + str3 + str;
            } else {
                str3 = str2;
            }
            i++;
            str2 = str3;
        }
        if (str2 == null) {
            return null;
        }
        if (str2.length() > 0) {
            str3 = str2;
            while (str3 != null && (str3.endsWith(str) || str3.endsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR))) {
                str3 = str2.substring(0, str3.length() - 1);
            }
            str2 = str3;
        }
        return str2.length() > 0 ? this.datasource == 0 ? "mapdataver=5&type=20&mesh=" + str2 : this.datasource == 1 ? "mapdataver=5&type=11&mesh=" + str2 : this.datasource == 7 ? "mapdataver=5&type=1&mesh=" + str2 : this.datasource == 8 ? "mapdataver=5&type=13&mesh=" + str2 : this.datasource == 9 ? "mapdataver=5&type=40&mesh=" + str2 : this.datasource == 2 ? "t=BMPBM&mapdataver=5&mesh=" + str2 : this.datasource == 3 ? "mapdataver=5&mesh=" + str2 : this.datasource == 4 ? "mapdataver=5&v=6.0.0&bver=2&mesh=" + str2 : this.datasource == 6 ? "t=VMMV3&mapdataver=5&type=mod&cp=0&mid=" + str2 : null : null;
    }

    protected String getMapAddress() {
        return this.mMapCallback.getMapSvrAddress();
    }

    protected String getMapSvrPath() {
        switch (this.datasource) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
            case Type.ARRAY /*9*/:
                return "/ws/mps/vmap?";
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.FLOAT /*6*/:
                return "/amapsrv/MPS?";
            case Type.BYTE /*3*/:
                return "/ws/mps/smap?";
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return "/ws/mps/rtt?";
            default:
                return null;
        }
    }

    protected boolean isNeedProcessReturn() {
        return this.datasource == 9;
    }

    public boolean isRequestValid() {
        return this.mMapCallback.isGridsInScreen(this.mapTiles, this.datasource);
    }

    protected boolean processReceivedDataHeader(int i) {
        if (this.recievedDataSize <= 7) {
            return false;
        }
        if (Convert.getInt(this.recievedDataBuffer, 0) != 0) {
            doCancel();
            return false;
        }
        Convert.moveArray(this.recievedDataBuffer, 8, this.recievedDataBuffer, 0, i - 8);
        this.recievedDataSize -= 8;
        this.nextImgDataLength = 0;
        this.recievedHeader = true;
        if (this.datasource == 0 || this.datasource == 1 || this.datasource == 8 || this.datasource == 7) {
            processReceivedDataV4();
        } else {
            super.processRecivedData();
        }
        return true;
    }

    protected void processRecivedDataByType() {
        if (this.datasource == 0 || this.datasource == 1 || this.datasource == 8 || this.datasource == 7) {
            processReceivedDataV4();
        } else {
            super.processRecivedData();
        }
    }

    void processRecivedModels(byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        byte b = bArr[i];
        if (b >= null) {
            String str = new String(bArr, i3, b);
            if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
                int i4 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
                this.mGLMapEngine.putMapData(bArr, i, i2 - i, this.datasource, 0);
                if (i4 != 0) {
                    doCancel();
                }
            }
        }
    }

    void processRecivedTileData(byte[] bArr, int i, int i2) {
        if (i == 0) {
            super.processRecivedTileData(bArr, i, i2);
        } else if (this.datasource == 2 || this.datasource == 3) {
            processRecivedTileDataBmp(bArr, i, i2);
        } else if (this.datasource == 4) {
            processRecivedTileDataVTmc(bArr, i, i2);
        } else if (this.datasource == 6) {
            processRecivedModels(bArr, i, i2);
        } else {
            super.processRecivedTileData(bArr, i, i2);
        }
    }

    void processRecivedTileDataBmp(byte[] bArr, int i, int i2) {
        int i3 = i + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        String str = (b <= null || (i4 + b) - 1 >= i2) ? C2915a.f14760f : new String(bArr, i4, b);
        i3 = i4 + b;
        if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
            int i5 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
            if (this.mGLMapEngine.putMapData(bArr, i, i2 - i, this.datasource, 0)) {
                VMapDataCache.getInstance().putRecoder(null, str, this.datasource);
            }
            if (i5 != 0) {
                doCancel();
            }
        }
    }

    void processRecivedTileDataVTmc(byte[] bArr, int i, int i2) {
        int i3 = i + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        if (i4 + b <= bArr.length && i4 <= bArr.length - 1 && b >= null) {
            String str = new String(bArr, i4, b);
            i3 = b + i4;
            i3 = (bArr[i3] + (i3 + 1)) + 4;
            if (this.mGLMapEngine.isMapEngineValid()) {
                VTMCDataCache instance = VTMCDataCache.getInstance();
                if (i2 > i) {
                    Object obj = new byte[(i2 - i)];
                    System.arraycopy(bArr, i, obj, 0, i2 - i);
                    C0614f putData = instance.putData(obj);
                    int i5 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
                    if (putData != null) {
                        this.mGLMapEngine.putMapData(putData.f3729a, 0, putData.f3729a.length, this.datasource, putData.f3731c);
                    }
                    if (i5 != 0) {
                        doCancel();
                    }
                }
            }
        }
    }

    protected void processRecivedVersionOrScenicWidgetData() {
        if (this.datasource == 9) {
            processRecivedVersionData(this.recievedDataBuffer, 0, this.recievedDataSize);
        }
    }
}
