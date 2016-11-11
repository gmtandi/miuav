package com.fimi.soul.utils;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.fimi.soul.utils.r */
final class C1978r implements FilenameFilter {
    C1978r() {
    }

    public boolean accept(File file, String str) {
        return str.contains(".kml") || str.contains(".kmz");
    }
}
