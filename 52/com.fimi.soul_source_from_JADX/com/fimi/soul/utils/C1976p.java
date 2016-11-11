package com.fimi.soul.utils;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.fimi.soul.utils.p */
final class C1976p implements FilenameFilter {
    C1976p() {
    }

    public boolean accept(File file, String str) {
        return str.contains(".txt");
    }
}
