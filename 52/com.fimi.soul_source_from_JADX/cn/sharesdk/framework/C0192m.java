package cn.sharesdk.framework;

import java.util.Comparator;

/* renamed from: cn.sharesdk.framework.m */
class C0192m implements Comparator<Platform> {
    final /* synthetic */ C0191l f336a;

    C0192m(C0191l c0191l) {
        this.f336a = c0191l;
    }

    public int m675a(Platform platform, Platform platform2) {
        return platform.getSortId() != platform2.getSortId() ? platform.getSortId() - platform2.getSortId() : platform.getPlatformId() - platform2.getPlatformId();
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m675a((Platform) obj, (Platform) obj2);
    }
}
