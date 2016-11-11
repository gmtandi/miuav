package com.fimi.soul.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class ai {
    private static ObjectMapper f10023a;

    static {
        f10023a = new ObjectMapper();
        f10023a.getDeserializationConfig().set(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        f10023a.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
        f10023a.getSerializationConfig().setSerializationInclusion(Inclusion.NON_DEFAULT);
        f10023a.configure(Feature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        f10023a.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        f10023a.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        f10023a.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, false);
        f10023a.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public static <T> T m12245a(Class<T> cls, File file) {
        return f10023a.readValue(file, (Class) cls);
    }

    public static <T> ArrayList<T> m12246a(Class<? extends T[]> cls, String str) {
        Object[] objArr = (Object[]) f10023a.readValue(str, (Class) cls);
        ArrayList<T> arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static List<LinkedHashMap<String, Object>> m12247a(String str) {
        return (List) f10023a.readValue(str, List.class);
    }

    public static void m12248a(DeserializationProblemHandler deserializationProblemHandler) {
        f10023a.getDeserializationConfig().addHandler(deserializationProblemHandler);
    }

    public static <T> T m12249b(Class<T> cls, String str) {
        return f10023a.readValue(str, (Class) cls);
    }
}
