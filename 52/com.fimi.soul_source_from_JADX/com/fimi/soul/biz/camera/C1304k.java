package com.fimi.soul.biz.camera;

import com.fimi.soul.biz.camera.entity.X11FileInfo;
import java.util.Comparator;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.k */
class C1304k implements Comparator<X11FileInfo> {
    final /* synthetic */ C1299f f5828a;

    C1304k(C1299f c1299f) {
        this.f5828a = c1299f;
    }

    public int m8863a(X11FileInfo x11FileInfo, X11FileInfo x11FileInfo2) {
        return x11FileInfo.getCreateDate().replaceAll("[-\\s:]", C2915a.f14760f).compareTo(x11FileInfo2.getCreateDate().replaceAll("[-\\s:]", C2915a.f14760f));
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m8863a((X11FileInfo) obj, (X11FileInfo) obj2);
    }
}
