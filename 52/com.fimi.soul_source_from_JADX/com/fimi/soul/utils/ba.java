package com.fimi.soul.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ba {
    public static Map<String, String> m12314a(String str) {
        Scanner scanner = new Scanner(str);
        Map<String, String> hashMap = new HashMap();
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("=");
            if (split != null && split.length == 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        return hashMap;
    }
}
