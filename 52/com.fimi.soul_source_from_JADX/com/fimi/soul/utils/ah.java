package com.fimi.soul.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class ah {
    public static Gson f10022a;

    static {
        f10022a = new Gson();
    }

    public static <T> T m12242a(String str, Class<T> cls) {
        return f10022a.fromJson(str, (Class) cls);
    }

    public static <T> T m12243a(String str, Type type) {
        return f10022a.fromJson(str, type);
    }

    public static String m12244a(Object obj) {
        return f10022a.toJson(obj);
    }
}
