package com.fimi.kernel.p084e;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/* renamed from: com.fimi.kernel.e.q */
public class C1178q<T> {
    public static Object m8207a(String str, Class cls) {
        Object obj = null;
        try {
            obj = new GsonBuilder().create().fromJson(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String m8208a(Object obj) {
        String str = null;
        try {
            str = new GsonBuilder().create().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String m8209a(List<?> list) {
        String str = null;
        try {
            str = new GsonBuilder().create().toJson((Object) list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static List<?> m8210a(String str, TypeToken typeToken) {
        try {
            return (List) new GsonBuilder().create().fromJson(str, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void m8211a(String[] strArr) {
        List<C1180s> a = C1178q.m8210a("[{id:1,name:22},{id:2,name:33}]", new C1179r());
        System.out.println(a.size());
        for (C1180s b : a) {
            System.out.println(b.m8214b());
        }
        System.out.println(((C1180s) C1178q.m8207a("{id:1,name:22}", C1180s.class)).m8214b());
    }
}
