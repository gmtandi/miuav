package p147m.framework.p148a;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: m.framework.a.e */
public class C2850e extends C2846c {
    private ArrayList<C2846c> f14589a;

    public C2850e() {
        this.f14589a = new ArrayList();
    }

    protected InputStream m16425a() {
        InputStream c2851f = new C2851f();
        Iterator it = this.f14589a.iterator();
        while (it.hasNext()) {
            c2851f.m16429a(((C2846c) it.next()).m16415a());
        }
        return c2851f;
    }

    public C2850e m16426a(C2846c c2846c) {
        this.f14589a.add(c2846c);
        return this;
    }

    protected long m16427b() {
        Iterator it = this.f14589a.iterator();
        long j = 0;
        while (it.hasNext()) {
            j = ((C2846c) it.next()).m16416b() + j;
        }
        return j;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.f14589a.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((C2846c) it.next()).toString());
        }
        return stringBuilder.toString();
    }
}
