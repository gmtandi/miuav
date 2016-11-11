package com.autonavi.amap.mapcore;

import android.text.TextUtils;
import com.amap.api.mapcore.util.bt;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.p054c.p055a.p063b.p068d.C0921a;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2951i;

public abstract class BaseMapLoader {
    long createtime;
    int datasource;
    public HttpURLConnection httpURLConnection;
    volatile boolean inRequest;
    volatile boolean isFinished;
    volatile boolean mCanceled;
    int mCapaticy;
    int mCapaticyExt;
    MapCore mGLMapEngine;
    BaseMapCallImplement mMapCallback;
    long m_reqestStartLen;
    int mapLevel;
    public ArrayList<MapSourceGridData> mapTiles;
    int nextImgDataLength;
    byte[] recievedDataBuffer;
    int recievedDataSize;
    boolean recievedHeader;

    public BaseMapLoader() {
        this.mapTiles = new ArrayList();
        this.recievedDataSize = 0;
        this.nextImgDataLength = 0;
        this.recievedHeader = false;
        this.mCapaticy = 30720;
        this.mCapaticyExt = C1142e.f5202b;
        this.inRequest = false;
        this.isFinished = false;
        this.mCanceled = false;
        this.datasource = 0;
        this.httpURLConnection = null;
    }

    private synchronized void onConnectionOver() {
        processRecivedVersionOrScenicWidgetData();
        this.recievedDataBuffer = null;
        this.nextImgDataLength = 0;
        this.recievedDataSize = 0;
        int i = 0;
        while (i < this.mapTiles.size()) {
            try {
                this.mMapCallback.tileProcessCtrl.m5296a(((MapSourceGridData) this.mapTiles.get(i)).keyGridName);
                i++;
            } catch (Exception e) {
            }
        }
        this.isFinished = true;
    }

    private void onConnectionRecieveData(byte[] bArr, int i) {
        if (this.mCapaticy < this.recievedDataSize + i) {
            try {
                this.mCapaticy += this.mCapaticyExt;
                Object obj = new byte[this.mCapaticy];
                System.arraycopy(this.recievedDataBuffer, 0, obj, 0, this.recievedDataSize);
                this.recievedDataBuffer = obj;
            } catch (OutOfMemoryError e) {
                doCancel();
                return;
            }
        }
        try {
            System.arraycopy(bArr, 0, this.recievedDataBuffer, this.recievedDataSize, i);
            this.recievedDataSize += i;
            if (!isNeedProcessReturn()) {
                if (this.recievedHeader || processReceivedDataHeader(i)) {
                    processRecivedDataByType();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
            doCancel();
        } catch (Exception e3) {
            doCancel();
        }
    }

    public void OnException(int i) {
        privteTestTime(C2915a.f14760f, " network error:" + i);
        this.isFinished = true;
        if (this.datasource == 6 || this.datasource == 4 || this.datasource == 1 || this.mCanceled) {
            this.isFinished = true;
        } else {
            this.isFinished = true;
        }
    }

    public void addReuqestTiles(MapSourceGridData mapSourceGridData) {
        this.mapTiles.add(mapSourceGridData);
    }

    protected boolean containllegal(String str) {
        return str.contains("<") || str.contains("[");
    }

    public synchronized void doCancel() {
        if (!(this.mCanceled || this.isFinished)) {
            this.mCanceled = true;
            try {
                if (this.httpURLConnection != null && this.inRequest) {
                    this.httpURLConnection.disconnect();
                }
            } catch (Throwable th) {
            } finally {
                onConnectionOver();
            }
        }
    }

    public void doRequest() {
        InputStream inputStream;
        Throwable th;
        if (!this.mCanceled && !this.isFinished) {
            if (isRequestValid()) {
                String mapAddress = getMapAddress();
                String mapSvrPath = getMapSvrPath();
                if (mapSvrPath != null && mapSvrPath.length() != 0 && mapAddress != null) {
                    InputStream inputStream2 = null;
                    Object gridParma = getGridParma();
                    if (!TextUtils.isEmpty(gridParma)) {
                        this.inRequest = true;
                        try {
                            Proxy a = bt.m3735a(this.mMapCallback.getContext());
                            mapAddress = getURL(mapAddress, mapSvrPath, gridParma);
                            if (a != null) {
                                this.httpURLConnection = (HttpURLConnection) new URL(mapAddress).openConnection(a);
                            } else {
                                this.httpURLConnection = (HttpURLConnection) new URL(mapAddress).openConnection();
                            }
                            this.httpURLConnection.setConnectTimeout(C0921a.f4833b);
                            this.httpURLConnection.setRequestMethod(C2951i.f14860a);
                            if (this.httpURLConnection != null) {
                                this.httpURLConnection.connect();
                                if (this.httpURLConnection.getResponseCode() == C2799f.f14282t) {
                                    inputStream = this.httpURLConnection.getInputStream();
                                    try {
                                        onConnectionOpened();
                                        byte[] bArr = new byte[Opcodes.ACC_INTERFACE];
                                        boolean z = true;
                                        while (true) {
                                            int read = inputStream.read(bArr);
                                            if (read <= -1) {
                                                break;
                                            }
                                            if (z) {
                                                privteTestTime("recievedFirstByte:", C2915a.f14760f);
                                                z = false;
                                            }
                                            if (this.mCanceled) {
                                                break;
                                            }
                                            onConnectionRecieveData(bArr, read);
                                        }
                                    } catch (IllegalArgumentException e) {
                                        inputStream2 = inputStream;
                                    } catch (SecurityException e2) {
                                        inputStream2 = inputStream;
                                    } catch (OutOfMemoryError e3) {
                                        inputStream2 = inputStream;
                                    } catch (IllegalStateException e4) {
                                        inputStream2 = inputStream;
                                    } catch (IOException e5) {
                                        inputStream2 = inputStream;
                                    } catch (NullPointerException e6) {
                                        inputStream2 = inputStream;
                                    } catch (Throwable th2) {
                                        Throwable th3 = th2;
                                        inputStream2 = inputStream;
                                        th = th3;
                                    }
                                } else {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    inputStream = null;
                                }
                            } else {
                                OnException(ERROR_CODE.CONN_ERROR);
                                inputStream = null;
                            }
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e7) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        } catch (IllegalArgumentException e8) {
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e9) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        } catch (SecurityException e10) {
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e11) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        } catch (OutOfMemoryError e12) {
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e13) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        } catch (IllegalStateException e14) {
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e15) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        } catch (IOException e16) {
                            try {
                                OnException(ERROR_CODE.CONN_ERROR);
                                onConnectionOver();
                                if (inputStream2 != null && !this.mCanceled) {
                                    try {
                                        inputStream2.close();
                                        return;
                                    } catch (IOException e17) {
                                        OnException(ERROR_CODE.CONN_ERROR);
                                        return;
                                    }
                                }
                                return;
                            } catch (Throwable th4) {
                                th = th4;
                                onConnectionOver();
                                if (!(inputStream2 == null || this.mCanceled)) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e18) {
                                        OnException(ERROR_CODE.CONN_ERROR);
                                    }
                                }
                                throw th;
                            }
                        } catch (NullPointerException e19) {
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e20) {
                                    OnException(ERROR_CODE.CONN_ERROR);
                                    return;
                                }
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            doCancel();
        }
    }

    protected abstract String getGridParma();

    protected abstract String getMapAddress();

    protected abstract String getMapSvrPath();

    protected String getURL(String str, String str2, String str3) {
        String str4 = C2915a.f14760f;
        str4 = C2915a.f14760f;
        return str + str2 + str3;
    }

    public synchronized boolean hasFinished() {
        boolean z;
        z = this.mCanceled || this.isFinished;
        return z;
    }

    protected void initTestTime() {
        this.m_reqestStartLen = System.currentTimeMillis();
    }

    protected boolean isAssic(String str) {
        if (str == null) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] >= '\u0100' || toCharArray[i] <= '\u0000') {
                return false;
            }
            i++;
        }
        return true;
    }

    protected abstract boolean isNeedProcessReturn();

    public abstract boolean isRequestValid();

    public void onConnectionError(BaseMapLoader baseMapLoader, int i, String str) {
    }

    protected void onConnectionOpened() {
        this.recievedDataBuffer = new byte[this.mCapaticy];
        this.nextImgDataLength = 0;
        this.recievedDataSize = 0;
        this.recievedHeader = false;
    }

    protected void privteTestTime(String str, String str2) {
    }

    protected abstract boolean processReceivedDataHeader(int i);

    protected void processReceivedDataV4() {
        if (this.nextImgDataLength == 0) {
            if (this.recievedDataSize >= 8) {
                this.nextImgDataLength = Convert.getInt(this.recievedDataBuffer, 0) + 8;
                processReceivedDataV4();
            }
        } else if (this.recievedDataSize >= this.nextImgDataLength) {
            processReceivedTileDataV4(this.recievedDataBuffer, 8, this.nextImgDataLength);
            Convert.moveArray(this.recievedDataBuffer, this.nextImgDataLength, this.recievedDataBuffer, 0, this.recievedDataSize - this.nextImgDataLength);
            this.recievedDataSize -= this.nextImgDataLength;
            this.nextImgDataLength = 0;
            processReceivedDataV4();
        }
    }

    protected void processReceivedTileDataV4(byte[] bArr, int i, int i2) {
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

    protected void processRecivedData() {
        if (this.nextImgDataLength == 0) {
            if (this.recievedDataSize >= 8) {
                this.nextImgDataLength = Convert.getInt(this.recievedDataBuffer, 0) + 8;
                processRecivedData();
            }
        } else if (this.recievedDataSize >= this.nextImgDataLength) {
            int i = Convert.getInt(this.recievedDataBuffer, 0);
            int i2 = Convert.getInt(this.recievedDataBuffer, 4);
            if (i2 == 0) {
                processRecivedTileData(this.recievedDataBuffer, 8, i + 8);
            } else {
                try {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(this.recievedDataBuffer, 8, i));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[SmileConstants.TOKEN_PREFIX_TINY_UNICODE];
                    while (true) {
                        int read = gZIPInputStream.read(bArr);
                        if (read <= -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    processRecivedTileData(byteArrayOutputStream.toByteArray(), 0, i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.nextImgDataLength > 0) {
                Convert.moveArray(this.recievedDataBuffer, this.nextImgDataLength, this.recievedDataBuffer, 0, this.recievedDataSize - this.nextImgDataLength);
            }
            this.recievedDataSize -= this.nextImgDataLength;
            this.nextImgDataLength = 0;
            processRecivedData();
        }
    }

    protected abstract void processRecivedDataByType();

    void processRecivedTileData(byte[] bArr, int i, int i2) {
        int i3 = ((i + 2) + 2) + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        String str = C2915a.f14760f;
        if (b > null && (i4 + b) - 1 < i2) {
            str = new String(bArr, i4, b);
        }
        i4 += b;
        if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
            int i5 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
            VMapDataCache.getInstance().putRecoder(null, str, this.datasource);
            this.mGLMapEngine.putMapData(bArr, i, i2 - i, this.datasource, 0);
            if (i5 != 0) {
                doCancel();
            }
        }
    }

    void processRecivedVersionData(byte[] bArr, int i, int i2) {
        if (i2 > 0 && i2 <= bArr.length && Convert.getInt(bArr, 0) == 0 && Convert.getInt(bArr, 4) == 0) {
            int i3;
            int i4 = Convert.getInt(bArr, 8);
            int i5 = 1;
            ArrayList arrayList = new ArrayList();
            int i6 = 12;
            for (i3 = 0; i3 < i4; i3++) {
                String str = C2915a.f14760f;
                if (i6 >= i2) {
                    i5 = 0;
                    break;
                }
                int i7 = i6 + 1;
                byte b = bArr[i6];
                if (b <= null || i7 + b >= i2) {
                    i5 = 0;
                    break;
                }
                arrayList.add(new String(bArr, i7, b));
                i6 = (b + i7) + 4;
            }
            if (i5 != 0) {
                for (i3 = 0; i3 < arrayList.size(); i3++) {
                    VMapDataCache.getInstance().putRecoder(null, (String) arrayList.get(i3), this.datasource);
                }
                this.mGLMapEngine.putMapData(bArr, 0, i2, this.datasource, 0);
            }
        }
    }

    protected abstract void processRecivedVersionOrScenicWidgetData();
}
