package p001b.p002b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3495d;

/* renamed from: b.b.f */
public class C0132f extends HashMap<C3507d, C0115j> {
    private static final C0132f f145a;
    private static final long serialVersionUID = 1;

    static {
        f145a = new C0132f();
    }

    public static C0132f m223a() {
        return f145a;
    }

    public C0115j m224a(C3507d c3507d) {
        if (c3507d.m19095c()) {
            return m226b(c3507d);
        }
        if (!containsKey(c3507d)) {
            put(c3507d, m226b(c3507d));
        }
        return (C0115j) get(c3507d);
    }

    public C3495d m225a(C0139n c0139n, C0131e c0131e) {
        C3495d c3495d = new C3495d();
        c3495d.m19055a(new C0134g(this, c0139n));
        return c3495d;
    }

    C0115j m226b(C3507d c3507d) {
        if (c3507d.m19096d()) {
            return new C0135h(c3507d);
        }
        C0116p c0116p = new C0116p(c3507d.m19091a());
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            c0116p.m141a(m224a((C3507d) it.next()));
        }
        return c0116p;
    }

    public List<C0115j> m227c(C3507d c3507d) {
        if (c3507d.m19096d()) {
            return Arrays.asList(new C0115j[]{m224a(c3507d)});
        }
        List<C0115j> arrayList = new ArrayList();
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            arrayList.add(m224a((C3507d) it.next()));
        }
        return arrayList;
    }
}
