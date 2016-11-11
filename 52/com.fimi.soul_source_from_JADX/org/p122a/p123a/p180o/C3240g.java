package org.p122a.p123a.p180o;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.http.util.VersionInfo;

/* renamed from: org.a.a.o.g */
public class C3240g {
    public static final String f15702a = "UNAVAILABLE";
    public static final String f15703b = "version.properties";
    public static final String f15704c = "info.module";
    public static final String f15705d = "info.release";
    public static final String f15706e = "info.timestamp";
    private final String f15707f;
    private final String f15708g;
    private final String f15709h;
    private final String f15710i;
    private final String f15711j;

    protected C3240g(String str, String str2, String str3, String str4, String str5) {
        C3234a.m17886a((Object) str, "Package identifier");
        this.f15707f = str;
        if (str2 == null) {
            str2 = f15702a;
        }
        this.f15708g = str2;
        if (str3 == null) {
            str3 = f15702a;
        }
        this.f15709h = str3;
        if (str4 == null) {
            str4 = f15702a;
        }
        this.f15710i = str4;
        if (str5 == null) {
            str5 = f15702a;
        }
        this.f15711j = str5;
    }

    public static String m17912a(String str, String str2, Class<?> cls) {
        C3240g a = C3240g.m17913a(str2, cls.getClassLoader());
        return str + "/" + (a != null ? a.m17918c() : f15702a) + " (Java 1.5 minimum; Java/" + System.getProperty("java.version") + ")";
    }

    public static C3240g m17913a(String str, ClassLoader classLoader) {
        Map properties;
        C3234a.m17886a((Object) str, "Package identifier");
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        InputStream resourceAsStream;
        try {
            resourceAsStream = classLoader.getResourceAsStream(str.replace('.', '/') + "/" + f15703b);
            if (resourceAsStream != null) {
                properties = new Properties();
                properties.load(resourceAsStream);
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                }
            } else {
                properties = null;
            }
        } catch (IOException e2) {
            properties = null;
        } catch (Throwable th) {
            resourceAsStream.close();
        }
        return properties != null ? C3240g.m17914a(str, properties, classLoader) : null;
    }

    protected static C3240g m17914a(String str, Map<?, ?> map, ClassLoader classLoader) {
        String str2;
        String str3;
        String str4;
        String str5 = null;
        C3234a.m17886a((Object) str, "Package identifier");
        if (map != null) {
            String str6 = (String) map.get(f15704c);
            String str7 = (str6 == null || str6.length() >= 1) ? str6 : null;
            str6 = (String) map.get(f15705d);
            str2 = (str6 == null || (str6.length() >= 1 && !str6.equals("${pom.version}"))) ? str6 : null;
            str6 = (String) map.get(f15706e);
            if (str6 == null || (str6.length() >= 1 && !str6.equals("${mvn.timestamp}"))) {
                str3 = str6;
                str4 = str2;
                str2 = str7;
            } else {
                str3 = null;
                str4 = str2;
                str2 = str7;
            }
        } else {
            str3 = null;
            str4 = null;
            str2 = null;
        }
        if (classLoader != null) {
            str5 = classLoader.toString();
        }
        return new C3240g(str, str2, str4, str3, str5);
    }

    public static VersionInfo[] m17915a(String[] strArr, ClassLoader classLoader) {
        C3234a.m17886a((Object) strArr, "Package identifier array");
        List arrayList = new ArrayList(strArr.length);
        for (String a : strArr) {
            C3240g a2 = C3240g.m17913a(a, classLoader);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return (VersionInfo[]) arrayList.toArray(new VersionInfo[arrayList.size()]);
    }

    public final String m17916a() {
        return this.f15707f;
    }

    public final String m17917b() {
        return this.f15708g;
    }

    public final String m17918c() {
        return this.f15709h;
    }

    public final String m17919d() {
        return this.f15710i;
    }

    public final String m17920e() {
        return this.f15711j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(((((this.f15707f.length() + 20) + this.f15708g.length()) + this.f15709h.length()) + this.f15710i.length()) + this.f15711j.length());
        stringBuilder.append("VersionInfo(").append(this.f15707f).append(':').append(this.f15708g);
        if (!f15702a.equals(this.f15709h)) {
            stringBuilder.append(':').append(this.f15709h);
        }
        if (!f15702a.equals(this.f15710i)) {
            stringBuilder.append(':').append(this.f15710i);
        }
        stringBuilder.append(')');
        if (!f15702a.equals(this.f15711j)) {
            stringBuilder.append('@').append(this.f15711j);
        }
        return stringBuilder.toString();
    }
}
