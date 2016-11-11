package org.p004c.p188a.p189a;

import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;
import org.p004c.p005e.C3328f;
import org.p004c.p005e.C3509g;
import org.p004c.p005e.C3510h;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p198b.C3402c;

/* renamed from: org.c.a.a.f */
abstract class C3329f implements C3328f {
    C3329f() {
    }

    private List<Class<?>> m18396a(String str) {
        List<Class<?>> arrayList = new ArrayList();
        for (String a : str.split(MiPushClient.ACCEPT_TIME_SEPARATOR)) {
            arrayList.add(C3402c.m18663a(a));
        }
        return arrayList;
    }

    protected abstract C3323a m18397a(List<Class<?>> list);

    public C3323a m18398a(C3510h c3510h) {
        try {
            return m18397a(m18396a(c3510h.m19109a()));
        } catch (Exception e) {
            throw new C3509g(e);
        }
    }
}
