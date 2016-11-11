package com.fimi.kernel.p084e;

import java.io.File;
import java.util.Comparator;

/* renamed from: com.fimi.kernel.e.n */
public class C1175n implements Comparator<File> {
    public int m8197a(File file, File file2) {
        return file.lastModified() > file2.lastModified() ? 1 : file.lastModified() == file2.lastModified() ? 0 : -1;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m8197a((File) obj, (File) obj2);
    }
}
