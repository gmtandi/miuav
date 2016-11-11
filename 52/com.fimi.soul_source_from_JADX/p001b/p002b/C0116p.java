package p001b.p002b;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.p004c.p198b.C3451h;

/* renamed from: b.b.p */
public class C0116p implements C0115j {
    private String f121a;
    private Vector<C0115j> f122b;

    public C0116p() {
        this.f122b = new Vector(10);
    }

    public C0116p(Class<?> cls) {
        this.f122b = new Vector(10);
        m137c(cls);
    }

    public C0116p(Class<? extends C0136k> cls, String str) {
        this((Class) cls);
        m145b(str);
    }

    public C0116p(String str) {
        this.f122b = new Vector(10);
        m145b(str);
    }

    public C0116p(Class<?>... clsArr) {
        this.f122b = new Vector(10);
        for (Class d : clsArr) {
            m141a(m138d(d));
        }
    }

    public C0116p(Class<? extends C0136k>[] clsArr, String str) {
        this((Class[]) clsArr);
        m145b(str);
    }

    public static C0115j m130a(Class<?> cls, String str) {
        try {
            Constructor a = C0116p.m133a((Class) cls);
            try {
                Object obj;
                if (a.getParameterTypes().length == 0) {
                    Object newInstance = a.newInstance(new Object[0]);
                    if (newInstance instanceof C0136k) {
                        ((C0136k) newInstance).m284e(str);
                        obj = newInstance;
                    } else {
                        obj = newInstance;
                    }
                } else {
                    obj = a.newInstance(new Object[]{str});
                }
                return (C0115j) obj;
            } catch (Throwable e) {
                return C0116p.m131a("Cannot instantiate test case: " + str + " (" + C0116p.m132a(e) + ")");
            } catch (InvocationTargetException e2) {
                return C0116p.m131a("Exception in constructor: " + str + " (" + C0116p.m132a(e2.getTargetException()) + ")");
            } catch (Throwable e3) {
                return C0116p.m131a("Cannot access test case: " + str + " (" + C0116p.m132a(e3) + ")");
            }
        } catch (NoSuchMethodException e4) {
            return C0116p.m131a("Class " + cls.getName() + " has no public constructor TestCase(String name) or TestCase()");
        }
    }

    public static C0115j m131a(String str) {
        return new C0141q("warning", str);
    }

    private static String m132a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static Constructor<?> m133a(Class<?> cls) {
        try {
            return cls.getConstructor(new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            return cls.getConstructor(new Class[0]);
        }
    }

    private void m134a(Method method, List<String> list, Class<?> cls) {
        String name = method.getName();
        if (!list.contains(name)) {
            if (m135a(method)) {
                list.add(name);
                m141a(C0116p.m130a((Class) cls, name));
            } else if (m136b(method)) {
                m141a(C0116p.m131a("Test method isn't public: " + method.getName() + "(" + cls.getCanonicalName() + ")"));
            }
        }
    }

    private boolean m135a(Method method) {
        return m136b(method) && Modifier.isPublic(method.getModifiers());
    }

    private boolean m136b(Method method) {
        return method.getParameterTypes().length == 0 && method.getName().startsWith("test") && method.getReturnType().equals(Void.TYPE);
    }

    private void m137c(Class<?> cls) {
        this.f121a = cls.getName();
        try {
            C0116p.m133a((Class) cls);
            if (Modifier.isPublic(cls.getModifiers())) {
                List arrayList = new ArrayList();
                for (Class cls2 = cls; C0115j.class.isAssignableFrom(cls2); cls2 = cls2.getSuperclass()) {
                    for (Method a : C3451h.m18812a(cls2)) {
                        m134a(a, arrayList, cls);
                    }
                }
                if (this.f122b.size() == 0) {
                    m141a(C0116p.m131a("No tests found in " + cls.getName()));
                    return;
                }
                return;
            }
            m141a(C0116p.m131a("Class " + cls.getName() + " is not public"));
        } catch (NoSuchMethodException e) {
            m141a(C0116p.m131a("Class " + cls.getName() + " has no public constructor TestCase(String name) or TestCase()"));
        }
    }

    private C0115j m138d(Class<?> cls) {
        return C0136k.class.isAssignableFrom(cls) ? new C0116p(cls.asSubclass(C0136k.class)) : C0116p.m131a(cls.getCanonicalName() + " does not extend TestCase");
    }

    public int m139a() {
        Iterator it = this.f122b.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((C0115j) it.next()).m128a() + i;
        }
        return i;
    }

    public C0115j m140a(int i) {
        return (C0115j) this.f122b.get(i);
    }

    public void m141a(C0115j c0115j) {
        this.f122b.add(c0115j);
    }

    public void m142a(C0115j c0115j, C0139n c0139n) {
        c0115j.m129a(c0139n);
    }

    public void m143a(C0139n c0139n) {
        Iterator it = this.f122b.iterator();
        while (it.hasNext()) {
            C0115j c0115j = (C0115j) it.next();
            if (!c0139n.m312f()) {
                m142a(c0115j, c0139n);
            } else {
                return;
            }
        }
    }

    public void m144b(Class<? extends C0136k> cls) {
        m141a(new C0116p((Class) cls));
    }

    public void m145b(String str) {
        this.f121a = str;
    }

    public String m146c() {
        return this.f121a;
    }

    public int m147d() {
        return this.f122b.size();
    }

    public Enumeration<C0115j> m148e() {
        return this.f122b.elements();
    }

    public String toString() {
        return m146c() != null ? m146c() : super.toString();
    }
}
