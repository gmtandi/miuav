package org.p004c.p188a.p193c;

import org.p184b.C3276b;
import org.p184b.C3300k;

/* renamed from: org.c.a.c.e */
class C3353e extends C3276b<Object> {
    final /* synthetic */ String f15857a;

    C3353e(String str) {
        this.f15857a = str;
    }

    public void m18472a(C3300k c3300k) {
        c3300k.m18222a("has single failure containing " + this.f15857a);
    }

    public boolean m18473a(Object obj) {
        return obj.toString().contains(this.f15857a) && C3351c.m18466a(1).m18107a(obj);
    }
}
