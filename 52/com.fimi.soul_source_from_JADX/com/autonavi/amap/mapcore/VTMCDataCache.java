package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.Hashtable;

public class VTMCDataCache {
    public static final int MAXSIZE = 500;
    public static final int MAX_EXPIREDTIME = 300;
    private static VTMCDataCache instance;
    static Hashtable<String, C0614f> vtmcHs;
    static ArrayList<String> vtmcList;
    public int mNewestTimeStamp;

    static {
        vtmcHs = new Hashtable();
        vtmcList = new ArrayList();
    }

    public VTMCDataCache() {
        this.mNewestTimeStamp = 0;
    }

    private void deleteData(String str) {
        vtmcHs.remove(str);
        for (int i = 0; i < vtmcList.size(); i++) {
            if (((String) vtmcList.get(i)).equals(str)) {
                vtmcList.remove(i);
                return;
            }
        }
    }

    public static VTMCDataCache getInstance() {
        if (instance == null) {
            instance = new VTMCDataCache();
        }
        return instance;
    }

    public synchronized C0614f getData(String str, boolean z) {
        C0614f c0614f;
        c0614f = (C0614f) vtmcHs.get(str);
        if (!z) {
            if (c0614f == null) {
                c0614f = null;
            } else if (((int) (System.currentTimeMillis() / 1000)) - c0614f.f3731c > MAX_EXPIREDTIME) {
                c0614f = null;
            } else if (this.mNewestTimeStamp > c0614f.f3733e) {
                c0614f = null;
            }
        }
        return c0614f;
    }

    public int getSize() {
        return vtmcList.size();
    }

    public synchronized C0614f putData(byte[] bArr) {
        C0614f c0614f;
        C0614f c0614f2 = new C0614f(bArr);
        if (this.mNewestTimeStamp < c0614f2.f3733e) {
            this.mNewestTimeStamp = c0614f2.f3733e;
        }
        c0614f = (C0614f) vtmcHs.get(c0614f2.f3730b);
        if (c0614f != null) {
            if (c0614f.f3732d.equals(c0614f2.f3732d)) {
                c0614f.m5300a(this.mNewestTimeStamp);
            } else {
                deleteData(c0614f2.f3730b);
            }
        }
        if (vtmcList.size() > MAXSIZE) {
            vtmcHs.remove(vtmcList.get(0));
            vtmcList.remove(0);
        }
        vtmcHs.put(c0614f2.f3730b, c0614f2);
        vtmcList.add(c0614f2.f3730b);
        c0614f = c0614f2;
        return c0614f;
    }

    public void reset() {
        vtmcList.clear();
        vtmcHs.clear();
    }
}
