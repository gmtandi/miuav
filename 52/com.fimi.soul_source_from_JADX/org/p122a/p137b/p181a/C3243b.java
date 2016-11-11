package org.p122a.p137b.p181a;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p137b.C2478b;

/* renamed from: org.a.b.a.b */
public class C3243b implements Serializable {
    private static Map<Class<? extends C2478b>, Map<?, C3243b>> f15716d;
    public final String f15717a;
    public final byte f15718b;
    public final C3241c f15719c;

    static {
        f15716d = new HashMap();
    }

    public C3243b(String str, byte b, C3241c c3241c) {
        this.f15717a = str;
        this.f15718b = b;
        this.f15719c = c3241c;
    }

    public static void m17921a(Class<? extends C2478b> cls, Map<?, C3243b> map) {
        f15716d.put(cls, map);
    }
}
