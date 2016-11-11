package com.fimi.soul.module.flyplannermedia;

import com.fimi.soul.entity.WifiDistanceFile;
import java.util.Comparator;

/* renamed from: com.fimi.soul.module.flyplannermedia.y */
public class C1785y implements Comparator<WifiDistanceFile> {
    public int m11463a(WifiDistanceFile wifiDistanceFile, WifiDistanceFile wifiDistanceFile2) {
        return -wifiDistanceFile.getName().compareTo(wifiDistanceFile2.getName());
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m11463a((WifiDistanceFile) obj, (WifiDistanceFile) obj2);
    }
}
