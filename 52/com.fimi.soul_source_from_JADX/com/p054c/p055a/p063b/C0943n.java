package com.p054c.p055a.p063b;

import com.p054c.p055a.p063b.p064a.C0898d;
import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p068d.C0923d;
import java.io.InputStream;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.c.a.b.n */
class C0943n implements C0920c {
    private final C0920c f4968a;

    public C0943n(C0920c c0920c) {
        this.f4968a = c0920c;
    }

    public InputStream m7494a(String str, Object obj) {
        InputStream a = this.f4968a.m7253a(str, obj);
        switch (C0940k.f4935a[C0923d.m7267a(str).ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new C0898d(a);
            default:
                return a;
        }
    }
}
