package com.fimi.soul.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/* renamed from: com.fimi.soul.utils.u */
public class C1981u {
    public static FileOutputStream m12513a() {
        File file = new File(C1969i.m12477b());
        file.mkdirs();
        File file2 = new File(file, "Parameters-" + C1980t.m12511a() + ".param");
        if (file2.exists()) {
            file2.delete();
        }
        return new FileOutputStream(file2);
    }

    public static FileOutputStream m12514a(String str) {
        File file = new File(C1969i.m12480c());
        file.mkdirs();
        File file2 = new File(file, str + "-" + C1980t.m12511a() + ".txt");
        if (file2.exists()) {
            file2.delete();
        }
        return new FileOutputStream(file2);
    }

    public static FileOutputStream m12515b() {
        File file = new File(C1969i.m12485h());
        file.mkdirs();
        File file2 = new File(file, C1980t.m12511a() + ".txt");
        if (file2.exists()) {
            file2.delete();
        }
        return new FileOutputStream(file2);
    }

    public static BufferedOutputStream m12516c() {
        File file = new File(C1969i.m12482e());
        file.mkdirs();
        File file2 = new File(file, C1980t.m12511a() + ".tlog");
        if (file2.exists()) {
            file2.delete();
        }
        return new BufferedOutputStream(new FileOutputStream(file2));
    }

    public static void m12517d() {
        File file = new File(C1969i.m12483f());
        file.mkdirs();
        new File(file, ".nomedia").createNewFile();
    }
}
