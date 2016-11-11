package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.x */
public class C0405x {
    private static volatile C0405x f2542a;
    private static ck f2543b;
    private Context f2544c;

    private C0405x(Context context) {
        this.f2544c = context;
        f2543b = m4226b(this.f2544c);
    }

    public static C0405x m4223a(Context context) {
        if (f2542a == null) {
            synchronized (C0405x.class) {
                if (f2542a == null) {
                    f2542a = new C0405x(context);
                }
            }
        }
        return f2542a;
    }

    private List<String> m4224a(List<C0403u> list) {
        List<String> arrayList = new ArrayList();
        if (list.size() > 0) {
            for (C0403u a : list) {
                arrayList.add(a.m4217a());
            }
        }
        return arrayList;
    }

    private void m4225a(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            String a = C0403u.m4215a(str);
            if (f2543b.m3902b(a, C0403u.class).size() > 0) {
                f2543b.m3898a(a, C0403u.class);
            }
            String[] split = str2.split(";");
            List arrayList = new ArrayList();
            for (String c0403u : split) {
                arrayList.add(new C0403u(str, c0403u));
            }
            f2543b.m3901a(arrayList);
        }
    }

    private ck m4226b(Context context) {
        try {
            return new ck(context, C0404w.m4218a());
        } catch (Throwable th) {
            ce.m3829a(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean m4227b() {
        if (f2543b == null) {
            f2543b = m4226b(this.f2544c);
        }
        return f2543b != null;
    }

    public ArrayList<C0401s> m4228a() {
        ArrayList<C0401s> arrayList = new ArrayList();
        if (!m4227b()) {
            return arrayList;
        }
        for (C0401s add : f2543b.m3902b(C2915a.f14760f, C0401s.class)) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public synchronized List<String> m4229a(String str) {
        List arrayList;
        arrayList = new ArrayList();
        if (m4227b()) {
            arrayList.addAll(m4224a(f2543b.m3902b(C0403u.m4215a(str), C0403u.class)));
        }
        return arrayList;
    }

    public synchronized void m4230a(C0401s c0401s) {
        if (m4227b()) {
            f2543b.m3896a((Object) c0401s, C0400v.m4193d(c0401s.m4199g()));
            m4225a(c0401s.m4199g(), c0401s.m4207c());
        }
    }

    public void m4231a(String str, int i, long j, long j2, long j3) {
        if (m4227b()) {
            m4232a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    public synchronized void m4232a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (m4227b()) {
            f2543b.m3896a(new C0402t(str, j, i, jArr[0], jArr2[0]), C0402t.m4209a(str));
        }
    }

    public synchronized long[] m4233a(String str, int i) {
        long[] jArr;
        if (m4227b()) {
            long a;
            long b;
            List b2 = f2543b.m3902b(C0402t.m4209a(str), C0402t.class);
            if (b2.size() > 0) {
                a = ((C0402t) b2.get(0)).m4211a(i);
                b = ((C0402t) b2.get(0)).m4213b(i);
            } else {
                b = 0;
                a = 0;
            }
            jArr = new long[]{a, b};
        } else {
            jArr = new long[]{0, 0};
        }
        return jArr;
    }

    public synchronized List<String> m4234b(String str) {
        List arrayList;
        arrayList = new ArrayList();
        if (m4227b()) {
            arrayList.addAll(m4224a(f2543b.m3902b(C0403u.m4216b(str), C0403u.class)));
        }
        return arrayList;
    }

    public synchronized void m4235c(String str) {
        if (m4227b()) {
            f2543b.m3898a(C0400v.m4193d(str), C0400v.class);
            f2543b.m3898a(C0403u.m4215a(str), C0403u.class);
            f2543b.m3898a(C0402t.m4209a(str), C0402t.class);
        }
    }

    public synchronized int m4236d(String str) {
        int i = 0;
        synchronized (this) {
            if (m4227b()) {
                List b = f2543b.m3902b(C0402t.m4209a(str), C0402t.class);
                long j = 0;
                if (b.size() > 0) {
                    j = ((C0402t) b.get(0)).m4210a();
                }
                i = (int) j;
            }
        }
        return i;
    }

    public synchronized String m4237e(String str) {
        String str2;
        str2 = null;
        if (m4227b()) {
            List b = f2543b.m3902b(C0400v.m4193d(str), C0400v.class);
            if (b.size() > 0) {
                str2 = ((C0400v) b.get(0)).m4198f();
            }
        }
        return str2;
    }

    public synchronized boolean m4238f(String str) {
        boolean z = false;
        synchronized (this) {
            if (m4227b()) {
                if (f2543b.m3902b(C0402t.m4209a(str), C0402t.class).size() > 0) {
                    z = true;
                }
            }
        }
        return z;
    }
}
