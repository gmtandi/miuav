package com.amap.api.services.core;

import org.p122a.p123a.C2915a;

class at {
    at() {
    }

    static String m4573a(String str) {
        if (str == null) {
            return null;
        }
        String b = aa.m4467b(str.getBytes());
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    static String m4574b(String str) {
        return str.length() < 2 ? C2915a.f14760f : aa.m4463a(str.substring(1));
    }
}
