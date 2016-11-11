package com.amap.api.mapcore.util;

import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

@cl(a = "update_item_download_info")
/* renamed from: com.amap.api.mapcore.util.t */
class C0402t {
    @cm(a = "mAdcode", b = 6)
    private String f2534a;
    @cm(a = "fileLength", b = 5)
    private long f2535b;
    @cm(a = "splitter", b = 2)
    private int f2536c;
    @cm(a = "startPos", b = 5)
    private long f2537d;
    @cm(a = "endPos", b = 5)
    private long f2538e;

    public C0402t() {
        this.f2534a = C2915a.f14760f;
        this.f2535b = 0;
        this.f2536c = 0;
        this.f2537d = 0;
        this.f2538e = 0;
    }

    public C0402t(String str, long j, int i, long j2, long j3) {
        this.f2534a = C2915a.f14760f;
        this.f2535b = 0;
        this.f2536c = 0;
        this.f2537d = 0;
        this.f2538e = 0;
        this.f2534a = str;
        this.f2535b = j;
        this.f2536c = i;
        this.f2537d = j2;
        this.f2538e = j3;
    }

    public static String m4209a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public long m4210a() {
        return this.f2535b;
    }

    public long m4211a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return m4212b();
            default:
                return 0;
        }
    }

    public long m4212b() {
        return this.f2537d;
    }

    public long m4213b(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return m4214c();
            default:
                return 0;
        }
    }

    public long m4214c() {
        return this.f2538e;
    }
}
