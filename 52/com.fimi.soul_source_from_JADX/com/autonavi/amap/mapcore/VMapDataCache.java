package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.HashMap;

public class VMapDataCache {
    private static final int MAXSIZE = 400;
    private static VMapDataCache instance;
    HashMap<String, C0613e> vCancelMapDataHs;
    ArrayList<String> vCancelMapDataList;
    HashMap<String, C0613e> vMapDataHs;
    ArrayList<String> vMapDataList;

    public VMapDataCache() {
        this.vMapDataHs = new HashMap();
        this.vMapDataList = new ArrayList();
        this.vCancelMapDataHs = new HashMap();
        this.vCancelMapDataList = new ArrayList();
    }

    public static VMapDataCache getInstance() {
        if (instance == null) {
            instance = new VMapDataCache();
        }
        return instance;
    }

    static String getKey(String str, int i) {
        return str + "-" + i;
    }

    public synchronized C0613e getCancelRecoder(String str, int i) {
        C0613e c0613e;
        c0613e = (C0613e) this.vCancelMapDataHs.get(getKey(str, i));
        if (c0613e != null && (System.currentTimeMillis() / 1000) - ((long) c0613e.f3726b) > 10) {
            c0613e = null;
        }
        return c0613e;
    }

    public synchronized C0613e getRecoder(String str, int i) {
        C0613e c0613e;
        c0613e = (C0613e) this.vMapDataHs.get(getKey(str, i));
        if (c0613e != null) {
            c0613e.f3728d++;
        }
        return c0613e;
    }

    public int getSize() {
        return this.vMapDataHs.size();
    }

    public synchronized C0613e putCancelRecoder(byte[] bArr, String str, int i) {
        C0613e c0613e = null;
        synchronized (this) {
            if (getRecoder(str, i) == null) {
                C0613e c0613e2 = new C0613e(str, i);
                if (c0613e2.f3725a != null) {
                    if (this.vCancelMapDataHs.size() > MAXSIZE) {
                        this.vCancelMapDataHs.remove(this.vMapDataList.get(0));
                        this.vCancelMapDataList.remove(0);
                    }
                    this.vCancelMapDataHs.put(getKey(str, i), c0613e2);
                    this.vCancelMapDataList.add(getKey(str, i));
                    c0613e = c0613e2;
                }
            }
        }
        return c0613e;
    }

    public synchronized C0613e putRecoder(byte[] bArr, String str, int i) {
        C0613e c0613e;
        c0613e = new C0613e(str, i);
        if (c0613e.f3725a == null) {
            c0613e = null;
        } else {
            if (this.vMapDataHs.size() > MAXSIZE) {
                this.vMapDataHs.remove(this.vMapDataList.get(0));
                this.vMapDataList.remove(0);
            }
            this.vMapDataHs.put(getKey(str, i), c0613e);
            this.vMapDataList.add(getKey(str, i));
        }
        return c0613e;
    }

    public synchronized void reset() {
        this.vMapDataHs.clear();
        this.vMapDataList.clear();
        this.vCancelMapDataHs.clear();
        this.vCancelMapDataList.clear();
    }
}
