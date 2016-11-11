package com.android.volley.toolbox;

import java.util.Comparator;

/* renamed from: com.android.volley.toolbox.e */
final class C0578e implements Comparator<byte[]> {
    C0578e() {
    }

    public int m5188a(byte[] bArr, byte[] bArr2) {
        return bArr.length - bArr2.length;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m5188a((byte[]) obj, (byte[]) obj2);
    }
}
