package com.android.volley;

import it.p074a.p075a.C2799f;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.android.volley.n */
public class C0566n implements Serializable {
    private static final long serialVersionUID = -20150728102000L;
    public final int f3541a;
    public final byte[] f3542b;
    public final Map<String, String> f3543c;
    public final boolean f3544d;
    public final long f3545e;

    public C0566n(int i, byte[] bArr, Map<String, String> map, boolean z) {
        this(i, bArr, map, z, 0);
    }

    public C0566n(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this.f3541a = i;
        this.f3542b = bArr;
        this.f3543c = map;
        this.f3544d = z;
        this.f3545e = j;
    }

    public C0566n(byte[] bArr) {
        this(C2799f.f14282t, bArr, Collections.emptyMap(), false, 0);
    }

    public C0566n(byte[] bArr, Map<String, String> map) {
        this(C2799f.f14282t, bArr, map, false, 0);
    }
}
