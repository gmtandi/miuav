package com.fimi.kernel.p084e;

import java.io.File;
import java.io.IOException;

/* renamed from: com.fimi.kernel.e.ac */
public class ac {
    public static String m8017a(File file) {
        try {
            return C1183v.m8262a(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m8018a(String str) {
        return ac.m8017a(new File(str));
    }
}
