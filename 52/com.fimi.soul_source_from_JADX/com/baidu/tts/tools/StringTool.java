package com.baidu.tts.tools;

import java.util.Iterator;
import java.util.List;

public class StringTool {
    public static String addDivider(List<String> list, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = list.iterator();
        if (it.hasNext()) {
            stringBuilder.append((String) it.next());
        }
        while (it.hasNext()) {
            String str2 = (String) it.next();
            stringBuilder.append(str);
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }

    public static String addDivider(int[] iArr, String str) {
        if (iArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < iArr.length; i++) {
            stringBuilder.append(str);
            stringBuilder.append(iArr[i]);
        }
        return stringBuilder.toString();
    }

    public static boolean isAllNumber(String str) {
        return str != null ? str.matches("^[0-9]{1,20}$") : false;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isEqual(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }
}
