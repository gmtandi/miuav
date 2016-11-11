package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.l */
public class C0394l extends Thread {
    private Context f2501a;
    private C0405x f2502b;

    public C0394l(Context context) {
        this.f2501a = context;
        this.f2502b = C0405x.m4223a(context);
    }

    private C0401s m4142a(File file) {
        String a = bj.m3616a(file);
        C0401s c0401s = new C0401s();
        c0401s.m4206b(a);
        return c0401s;
    }

    private ArrayList<C0401s> m4143a(Context context) {
        ArrayList<C0401s> arrayList = new ArrayList();
        File file = new File(bj.m3634b(context));
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().endsWith(".zip.tmp.dt")) {
                        C0401s a = m4142a(file2);
                        if (!(a == null || a.m4197e() == null)) {
                            arrayList.add(a);
                            this.f2502b.m4230a(a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private void m4144a() {
        ArrayList a;
        Object obj;
        ArrayList arrayList = new ArrayList();
        ArrayList a2 = this.f2502b.m4228a();
        if (a2.size() < 1) {
            a = m4143a(this.f2501a);
            obj = 1;
        } else {
            a = a2;
            obj = null;
        }
        Iterator it = a.iterator();
        while (it.hasNext()) {
            C0401s c0401s = (C0401s) it.next();
            if (!(c0401s == null || c0401s.m4197e() == null || c0401s.m4199g().length() < 1)) {
                if (Thread.interrupted()) {
                    break;
                }
                if (obj != null) {
                    arrayList.add(c0401s);
                }
                if ((c0401s.l == 4 || c0401s.l == 7) && !m4145a(c0401s.m4199g())) {
                    c0401s.m4205b();
                    try {
                        af.m3416a(c0401s.m4199g(), this.f2501a);
                    } catch (Exception e) {
                    }
                    arrayList.add(c0401s);
                }
            }
        }
        C0391i a3 = C0391i.m4106a(this.f2501a);
        if (a3 != null) {
            a3.m4122a(arrayList);
        }
    }

    private boolean m4145a(String str) {
        List<String> a = this.f2502b.m4229a(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bj.m3615a(this.f2501a));
        stringBuilder.append("vmap/");
        int length = stringBuilder.length();
        for (String replace : a) {
            stringBuilder.replace(length, stringBuilder.length(), replace);
            if (!new File(stringBuilder.toString()).exists()) {
                return false;
            }
        }
        return true;
    }

    public void destroy() {
        this.f2501a = null;
        this.f2502b = null;
    }

    public void run() {
        m4144a();
    }
}
