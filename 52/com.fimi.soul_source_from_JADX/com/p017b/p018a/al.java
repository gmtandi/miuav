package com.p017b.p018a;

import android.util.Log;
import com.p017b.p019b.C0617d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.b.a.al */
public class al implements Cloneable {
    private static final ar f3772i;
    private static final ar f3773j;
    private static Class[] f3774k;
    private static Class[] f3775l;
    private static Class[] f3776m;
    private static final HashMap<Class, HashMap<String, Method>> f3777n;
    private static final HashMap<Class, HashMap<String, Method>> f3778o;
    String f3779a;
    protected C0617d f3780b;
    Method f3781c;
    Class f3782d;
    C0632u f3783e;
    final ReentrantReadWriteLock f3784f;
    final Object[] f3785g;
    private Method f3786h;
    private ar f3787p;
    private Object f3788q;

    static {
        f3772i = new C0634o();
        f3773j = new C0631m();
        f3774k = new Class[]{Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
        f3775l = new Class[]{Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
        f3776m = new Class[]{Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
        f3777n = new HashMap();
        f3778o = new HashMap();
    }

    private al(C0617d c0617d) {
        this.f3781c = null;
        this.f3786h = null;
        this.f3783e = null;
        this.f3784f = new ReentrantReadWriteLock();
        this.f3785g = new Object[1];
        this.f3780b = c0617d;
        if (c0617d != null) {
            this.f3779a = c0617d.m5398b();
        }
    }

    private al(String str) {
        this.f3781c = null;
        this.f3786h = null;
        this.f3783e = null;
        this.f3784f = new ReentrantReadWriteLock();
        this.f3785g = new Object[1];
        this.f3779a = str;
    }

    public static <V> al m5450a(C0617d c0617d, ar<V> arVar, V... vArr) {
        al alVar = new al(c0617d);
        alVar.m5473a((Object[]) vArr);
        alVar.m5465a((ar) arVar);
        return alVar;
    }

    public static al m5451a(C0617d<?, Float> c0617d, float... fArr) {
        return new an((C0617d) c0617d, fArr);
    }

    public static al m5452a(C0617d<?, Integer> c0617d, int... iArr) {
        return new ao((C0617d) c0617d, iArr);
    }

    public static al m5453a(C0617d c0617d, C0636q... c0636qArr) {
        C0632u a = C0632u.m5627a(c0636qArr);
        if (a instanceof C0635p) {
            return new ao(c0617d, (C0635p) a);
        }
        if (a instanceof C0633n) {
            return new an(c0617d, (C0633n) a);
        }
        al alVar = new al(c0617d);
        alVar.f3783e = a;
        alVar.f3782d = c0636qArr[0].m5655e();
        return alVar;
    }

    public static al m5454a(String str, ar arVar, Object... objArr) {
        al alVar = new al(str);
        alVar.m5473a(objArr);
        alVar.m5465a(arVar);
        return alVar;
    }

    public static al m5455a(String str, float... fArr) {
        return new an(str, fArr);
    }

    public static al m5456a(String str, int... iArr) {
        return new ao(str, iArr);
    }

    public static al m5457a(String str, C0636q... c0636qArr) {
        C0632u a = C0632u.m5627a(c0636qArr);
        if (a instanceof C0635p) {
            return new ao(str, (C0635p) a);
        }
        if (a instanceof C0633n) {
            return new an(str, (C0633n) a);
        }
        al alVar = new al(str);
        alVar.f3783e = a;
        alVar.f3782d = c0636qArr[0].m5655e();
        return alVar;
    }

    static String m5458a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        char toUpperCase = Character.toUpperCase(str2.charAt(0));
        return str + toUpperCase + str2.substring(1);
    }

    private Method m5459a(Class cls, String str, Class cls2) {
        Method method = null;
        String a = al.m5458a(str, this.f3779a);
        Class[] clsArr = null;
        if (cls2 == null) {
            try {
                return cls.getMethod(a, clsArr);
            } catch (NoSuchMethodException e) {
                Method declaredMethod;
                try {
                    declaredMethod = cls.getDeclaredMethod(a, clsArr);
                    try {
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    } catch (NoSuchMethodException e2) {
                        Log.e("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.f3779a + ": " + e);
                        return declaredMethod;
                    }
                } catch (NoSuchMethodException e3) {
                    declaredMethod = null;
                    Log.e("PropertyValuesHolder", "Couldn't find no-arg method for property " + this.f3779a + ": " + e);
                    return declaredMethod;
                }
            }
        }
        Class[] clsArr2 = new Class[1];
        clsArr = this.f3782d.equals(Float.class) ? f3774k : this.f3782d.equals(Integer.class) ? f3775l : this.f3782d.equals(Double.class) ? f3776m : new Class[]{this.f3782d};
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            Class cls3 = clsArr[i];
            clsArr2[0] = cls3;
            try {
                method = cls.getMethod(a, clsArr2);
                this.f3782d = cls3;
                return method;
            } catch (NoSuchMethodException e4) {
                try {
                    method = cls.getDeclaredMethod(a, clsArr2);
                    method.setAccessible(true);
                    this.f3782d = cls3;
                    return method;
                } catch (NoSuchMethodException e5) {
                    i++;
                }
            }
        }
        Log.e("PropertyValuesHolder", "Couldn't find setter/getter for property " + this.f3779a + " with value type " + this.f3782d);
        return method;
    }

    private Method m5460a(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        Method method = null;
        try {
            this.f3784f.writeLock().lock();
            HashMap hashMap2 = (HashMap) hashMap.get(cls);
            if (hashMap2 != null) {
                method = (Method) hashMap2.get(this.f3779a);
            }
            if (method == null) {
                method = m5459a(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.f3779a, method);
            }
            Method method2 = method;
            this.f3784f.writeLock().unlock();
            return method2;
        } catch (Throwable th) {
            this.f3784f.writeLock().unlock();
        }
    }

    private void m5461a(Object obj, C0636q c0636q) {
        if (this.f3780b != null) {
            c0636q.m5649a(this.f3780b.m5395a(obj));
        }
        try {
            if (this.f3786h == null) {
                m5462b(obj.getClass());
            }
            c0636q.m5649a(this.f3786h.invoke(obj, new Object[0]));
        } catch (InvocationTargetException e) {
            Log.e("PropertyValuesHolder", e.toString());
        } catch (IllegalAccessException e2) {
            Log.e("PropertyValuesHolder", e2.toString());
        }
    }

    private void m5462b(Class cls) {
        this.f3786h = m5460a(cls, f3778o, "get", null);
    }

    public al m5463a() {
        try {
            al alVar = (al) super.clone();
            alVar.f3779a = this.f3779a;
            alVar.f3780b = this.f3780b;
            alVar.f3783e = this.f3783e.m5631b();
            alVar.f3787p = this.f3787p;
            return alVar;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    void m5464a(float f) {
        this.f3788q = this.f3783e.m5629a(f);
    }

    public void m5465a(ar arVar) {
        this.f3787p = arVar;
        this.f3783e.m5630a(arVar);
    }

    public void m5466a(C0617d c0617d) {
        this.f3780b = c0617d;
    }

    void m5467a(Class cls) {
        this.f3781c = m5460a(cls, f3777n, "set", this.f3782d);
    }

    void m5468a(Object obj) {
        C0636q c0636q;
        if (this.f3780b != null) {
            try {
                this.f3780b.m5395a(obj);
                Iterator it = this.f3783e.f3885e.iterator();
                while (it.hasNext()) {
                    c0636q = (C0636q) it.next();
                    if (!c0636q.m5650a()) {
                        c0636q.m5649a(this.f3780b.m5395a(obj));
                    }
                }
                return;
            } catch (ClassCastException e) {
                Log.e("PropertyValuesHolder", "No such property (" + this.f3780b.m5398b() + ") on target object " + obj + ". Trying reflection instead");
                this.f3780b = null;
            }
        }
        Class cls = obj.getClass();
        if (this.f3781c == null) {
            m5467a(cls);
        }
        Iterator it2 = this.f3783e.f3885e.iterator();
        while (it2.hasNext()) {
            c0636q = (C0636q) it2.next();
            if (!c0636q.m5650a()) {
                if (this.f3786h == null) {
                    m5462b(cls);
                }
                try {
                    c0636q.m5649a(this.f3786h.invoke(obj, new Object[0]));
                } catch (InvocationTargetException e2) {
                    Log.e("PropertyValuesHolder", e2.toString());
                } catch (IllegalAccessException e3) {
                    Log.e("PropertyValuesHolder", e3.toString());
                }
            }
        }
    }

    public void m5469a(String str) {
        this.f3779a = str;
    }

    public void m5470a(float... fArr) {
        this.f3782d = Float.TYPE;
        this.f3783e = C0632u.m5625a(fArr);
    }

    public void m5471a(int... iArr) {
        this.f3782d = Integer.TYPE;
        this.f3783e = C0632u.m5626a(iArr);
    }

    public void m5472a(C0636q... c0636qArr) {
        int i = 0;
        int length = c0636qArr.length;
        C0636q[] c0636qArr2 = new C0636q[Math.max(length, 2)];
        this.f3782d = c0636qArr[0].m5655e();
        while (i < length) {
            c0636qArr2[i] = c0636qArr[i];
            i++;
        }
        this.f3783e = new C0632u(c0636qArr2);
    }

    public void m5473a(Object... objArr) {
        this.f3782d = objArr[0].getClass();
        this.f3783e = C0632u.m5628a(objArr);
    }

    void m5474b() {
        if (this.f3787p == null) {
            ar arVar = this.f3782d == Integer.class ? f3772i : this.f3782d == Float.class ? f3773j : null;
            this.f3787p = arVar;
        }
        if (this.f3787p != null) {
            this.f3783e.m5630a(this.f3787p);
        }
    }

    void m5475b(Object obj) {
        m5461a(obj, (C0636q) this.f3783e.f3885e.get(0));
    }

    public String m5476c() {
        return this.f3779a;
    }

    void m5477c(Object obj) {
        m5461a(obj, (C0636q) this.f3783e.f3885e.get(this.f3783e.f3885e.size() - 1));
    }

    public /* synthetic */ Object clone() {
        return m5463a();
    }

    Object m5478d() {
        return this.f3788q;
    }

    void m5479d(Object obj) {
        if (this.f3780b != null) {
            this.f3780b.m5396a(obj, m5478d());
        }
        if (this.f3781c != null) {
            try {
                this.f3785g[0] = m5478d();
                this.f3781c.invoke(obj, this.f3785g);
            } catch (InvocationTargetException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (IllegalAccessException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }
    }

    public String toString() {
        return this.f3779a + ": " + this.f3783e.toString();
    }
}
