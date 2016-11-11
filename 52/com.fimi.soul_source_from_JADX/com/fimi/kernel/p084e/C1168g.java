package com.fimi.kernel.p084e;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: com.fimi.kernel.e.g */
final class C1168g implements FileFilter {
    C1168g() {
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]", file.getName());
    }
}
