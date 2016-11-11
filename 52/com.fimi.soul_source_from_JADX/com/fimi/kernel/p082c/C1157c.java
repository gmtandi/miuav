package com.fimi.kernel.p082c;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.fimi.kernel.C1189f;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/* renamed from: com.fimi.kernel.c.c */
public class C1157c implements C1156a {
    public static final ObjectMapper f5237a;
    private static C1157c f5238b = null;
    private static final String f5239e = "BeautifulKernel_SPStoreManager";
    private Gson f5240c;
    private SharedPreferences f5241d;

    static {
        f5238b = null;
        f5237a = new ObjectMapper();
    }

    private C1157c() {
        this.f5241d = C1189f.m8327a().getSharedPreferences(f5239e, 0);
        this.f5240c = new Gson();
    }

    public static synchronized C1157c m7938a() {
        C1157c c1157c;
        synchronized (C1157c.class) {
            if (f5238b == null) {
                f5238b = new C1157c();
            }
            c1157c = f5238b;
        }
        return c1157c;
    }

    public <T> T m7939a(String str, Class<?> cls) {
        T t = null;
        try {
            String string = this.f5241d.getString(str, null);
            if (string != null) {
                t = this.f5240c.fromJson(string, (Class) cls);
            }
        } catch (Exception e) {
        }
        return t;
    }

    public String m7940a(String str) {
        return this.f5241d.getString(str, null);
    }

    public void m7941a(String str, int i) {
        Editor edit = this.f5241d.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void m7942a(String str, long j) {
        Editor edit = this.f5241d.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void m7943a(String str, Object obj) {
        Editor edit = this.f5241d.edit();
        edit.putString(str, this.f5240c.toJson(obj, obj.getClass()));
        edit.commit();
    }

    public void m7944a(String str, String str2) {
        Editor edit = this.f5241d.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public <T> void m7945a(String str, List<T> list) {
        Editor edit = this.f5241d.edit();
        edit.putString(str, this.f5240c.toJson((Object) list));
        edit.commit();
    }

    public void m7946a(String str, boolean z) {
        Editor edit = this.f5241d.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public int m7947b(String str) {
        return this.f5241d.getInt(str, 0);
    }

    public List m7948b(String str, Class<?> cls) {
        JavaType constructCollectionType = f5237a.getTypeFactory().constructCollectionType(ArrayList.class, (Class) cls);
        try {
            String string = this.f5241d.getString(str, null);
            if (string != null) {
                return (List) f5237a.readValue(string, constructCollectionType);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public long m7949c(String str) {
        return this.f5241d.getLong(str, 0);
    }

    public boolean m7950d(String str) {
        return this.f5241d.getBoolean(str, false);
    }
}
