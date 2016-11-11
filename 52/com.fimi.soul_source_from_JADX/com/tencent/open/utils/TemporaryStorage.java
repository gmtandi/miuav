package com.tencent.open.utils;

import java.util.HashMap;

public class TemporaryStorage {
    private static HashMap<String, Object> f12109a;

    static {
        f12109a = new HashMap();
    }

    public static Object get(String str) {
        return f12109a.remove(str);
    }

    public static void remove(String str) {
        f12109a.remove(str);
    }

    public static Object set(String str, Object obj) {
        return f12109a.put(str, obj);
    }
}
