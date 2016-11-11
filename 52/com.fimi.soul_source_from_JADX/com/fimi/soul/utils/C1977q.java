package com.fimi.soul.utils;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.fimi.soul.utils.q */
final class C1977q implements FilenameFilter {
    C1977q() {
    }

    public boolean accept(File file, String str) {
        return str.contains(".param");
    }
}
