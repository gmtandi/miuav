package com.fimi.soul.utils;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.fimi.soul.utils.s */
final class C1979s implements FilenameFilter {
    C1979s() {
    }

    public boolean accept(File file, String str) {
        return str.contains(".xml");
    }
}
