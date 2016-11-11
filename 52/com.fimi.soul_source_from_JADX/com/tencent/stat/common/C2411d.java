package com.tencent.stat.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tencent.stat.common.d */
public class C2411d {
    public static File m13986a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                C2411d.m13986a(file.getParentFile().getAbsolutePath());
            }
            file.mkdir();
        }
        return file;
    }

    public static List<String> m13987a(File file) {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine.trim());
        }
    }
}
