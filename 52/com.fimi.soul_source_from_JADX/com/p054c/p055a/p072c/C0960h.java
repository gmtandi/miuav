package com.p054c.p055a.p072c;

import java.util.Comparator;

/* renamed from: com.c.a.c.h */
final class C0960h implements Comparator<String> {
    C0960h() {
    }

    public int m7568a(String str, String str2) {
        return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m7568a((String) obj, (String) obj2);
    }
}
