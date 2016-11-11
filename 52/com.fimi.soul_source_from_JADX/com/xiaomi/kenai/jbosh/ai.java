package com.xiaomi.kenai.jbosh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public final class ai extends C2501b {
    private static final Pattern f12657a;
    private final Map<ag, String> f12658b;
    private final String f12659c;
    private final AtomicReference<String> f12660d;

    /* renamed from: com.xiaomi.kenai.jbosh.ai.a */
    public final class C2500a {
        private Map<ag, String> f12654a;
        private boolean f12655b;
        private String f12656c;

        private C2500a() {
        }

        private static C2500a m14330b(ai aiVar) {
            C2500a c2500a = new C2500a();
            c2500a.f12654a = aiVar.m14343a();
            c2500a.f12655b = true;
            c2500a.f12656c = aiVar.f12659c;
            return c2500a;
        }

        public C2500a m14331a(ag agVar, String str) {
            if (this.f12654a == null) {
                this.f12654a = new HashMap();
            } else if (this.f12655b) {
                this.f12654a = new HashMap(this.f12654a);
                this.f12655b = false;
            }
            if (str == null) {
                this.f12654a.remove(agVar);
            } else {
                this.f12654a.put(agVar, str);
            }
            return this;
        }

        public C2500a m14332a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("payload XML argument cannot be null");
            }
            this.f12656c = str;
            return this;
        }

        public C2500a m14333a(String str, String str2) {
            return m14331a(ag.m14316a("http://www.w3.org/XML/1998/namespace", str, "xmlns"), str2);
        }

        public ai m14334a() {
            if (this.f12654a == null) {
                this.f12654a = new HashMap();
            }
            if (this.f12656c == null) {
                this.f12656c = C2915a.f14760f;
            }
            return new ai(this.f12656c, null);
        }
    }

    static {
        f12657a = Pattern.compile("<body(?:[\t\n\r ][^>]*?)?(/>|>)", 64);
    }

    private ai(Map<ag, String> map, String str) {
        this.f12660d = new AtomicReference();
        this.f12658b = map;
        this.f12659c = str;
    }

    private String m14340a(String str) {
        return str.replace("'", "&apos;");
    }

    public static C2500a m14341d() {
        return new C2500a();
    }

    private String m14342g() {
        ag c = C2501b.m14335c();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(c.m14319b());
        for (Entry entry : this.f12658b.entrySet()) {
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            ag agVar = (ag) entry.getKey();
            String c2 = agVar.m14320c();
            if (c2 != null && c2.length() > 0) {
                stringBuilder.append(c2);
                stringBuilder.append(":");
            }
            stringBuilder.append(agVar.m14319b());
            stringBuilder.append("='");
            stringBuilder.append(m14340a((String) entry.getValue()));
            stringBuilder.append("'");
        }
        stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuilder.append("xmlns");
        stringBuilder.append("='");
        stringBuilder.append(c.m14317a());
        stringBuilder.append("'>");
        if (this.f12659c != null) {
            stringBuilder.append(this.f12659c);
        }
        stringBuilder.append("</body>");
        return stringBuilder.toString();
    }

    public Map<ag, String> m14343a() {
        return Collections.unmodifiableMap(this.f12658b);
    }

    public String m14344b() {
        String str = (String) this.f12660d.get();
        if (str != null) {
            return str;
        }
        str = m14342g();
        this.f12660d.set(str);
        return str;
    }

    public C2500a m14345e() {
        return C2500a.m14330b(this);
    }

    public String m14346f() {
        return this.f12659c;
    }
}
