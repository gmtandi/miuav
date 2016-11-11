package com.p054c.p055a.p063b;

import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p068d.C0923d;
import java.io.InputStream;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.b.m */
class C0942m implements C0920c {
    private final C0920c f4967a;

    public C0942m(C0920c c0920c) {
        this.f4967a = c0920c;
    }

    public InputStream m7493a(String str, Object obj) {
        switch (C0940k.f4935a[C0923d.m7267a(str).ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                throw new IllegalStateException();
            default:
                return this.f4967a.m7253a(str, obj);
        }
    }
}
