package com.fimi.soul.module.setting;

import java.util.Comparator;

/* renamed from: com.fimi.soul.module.setting.u */
class C1891u implements Comparator<aa> {
    final /* synthetic */ FlyLogsActivity f9618a;

    C1891u(FlyLogsActivity flyLogsActivity) {
        this.f9618a = flyLogsActivity;
    }

    public int m11897a(aa aaVar, aa aaVar2) {
        return aaVar2.m11673f() > aaVar.m11673f() ? 1 : aaVar.m11673f() == aaVar2.m11673f() ? 0 : -1;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m11897a((aa) obj, (aa) obj2);
    }
}
