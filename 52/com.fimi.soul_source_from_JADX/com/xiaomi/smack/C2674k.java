package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.misc.C2464a;
import java.util.Map;

/* renamed from: com.xiaomi.smack.k */
public class C2674k implements Cloneable {
    private String f13234a;
    private String f13235b;
    private int f13236c;
    private boolean f13237d;
    private boolean f13238e;
    private String f13239f;
    private C2688m f13240g;

    public C2674k(Map<String, Integer> map, int i, String str, C2688m c2688m) {
        this.f13237d = C2678j.f13249c;
        this.f13238e = true;
        m15133a(map, i, str, c2688m);
    }

    private void m15133a(Map<String, Integer> map, int i, String str, C2688m c2688m) {
        this.f13235b = C2674k.m15134d();
        this.f13236c = i;
        this.f13234a = str;
        this.f13240g = c2688m;
    }

    public static final String m15134d() {
        return C2464a.f12424e ? "10.237.12.2" : C2464a.m14130a() ? "sandbox.xmpush.xiaomi.com" : C2464a.m14131b() ? "10.237.12.17" : C2464a.f12421b ? "58.68.235.106" : "app.chat.xiaomi.net";
    }

    public void m15135a(String str) {
        this.f13234a = str;
    }

    public void m15136a(boolean z) {
        this.f13237d = z;
    }

    public void m15137b(String str) {
        this.f13239f = str;
    }

    public void m15138c(String str) {
        this.f13235b = str;
    }

    public String m15139e() {
        return this.f13234a;
    }

    public String m15140f() {
        return this.f13239f;
    }

    public int m15141g() {
        return this.f13236c;
    }

    public String m15142h() {
        return this.f13235b;
    }

    public boolean m15143i() {
        return this.f13237d;
    }
}
