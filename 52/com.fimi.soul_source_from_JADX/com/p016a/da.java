package com.p016a;

import java.util.Comparator;

/* renamed from: com.a.da */
class da implements Comparator<cy> {
    final /* synthetic */ cz f827a;

    da(cz czVar) {
        this.f827a = czVar;
    }

    public int m1427a(cy cyVar, cy cyVar2) {
        return cyVar2.m1419b() - cyVar.m1419b();
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m1427a((cy) obj, (cy) obj2);
    }
}
