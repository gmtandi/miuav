package com.fimi.soul.utils;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.fimi.soul.utils.o */
public class C1975o {
    public static String[] m12506a() {
        return C1975o.m12507a(C1969i.m12480c(), new C1976p());
    }

    public static String[] m12507a(String str, FilenameFilter filenameFilter) {
        File file = new File(str);
        try {
            file.mkdirs();
            if (file.exists()) {
                return file.list(filenameFilter);
            }
        } catch (SecurityException e) {
        }
        return new String[0];
    }

    public static String[] m12508b() {
        return C1975o.m12507a(C1969i.m12477b(), new C1977q());
    }

    public static String[] m12509c() {
        return C1975o.m12507a(C1969i.m12481d(), new C1978r());
    }

    public static String[] m12510d() {
        return C1975o.m12507a(C1969i.m12484g(), new C1979s());
    }
}
