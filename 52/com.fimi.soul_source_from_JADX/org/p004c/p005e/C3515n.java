package org.p004c.p005e;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3493a;

/* renamed from: org.c.e.n */
public class C3515n implements Serializable {
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 1;
    private final AtomicInteger f16057a;
    private final AtomicInteger f16058b;
    private final CopyOnWriteArrayList<C3493a> f16059c;
    private final AtomicLong f16060d;
    private final AtomicLong f16061e;
    private C3518q f16062f;

    static {
        serialPersistentFields = ObjectStreamClass.lookup(C3518q.class).getFields();
    }

    public C3515n() {
        this.f16057a = new AtomicInteger();
        this.f16058b = new AtomicInteger();
        this.f16059c = new CopyOnWriteArrayList();
        this.f16060d = new AtomicLong();
        this.f16061e = new AtomicLong();
    }

    private C3515n(C3518q c3518q) {
        this.f16057a = c3518q.f16064a;
        this.f16058b = c3518q.f16065b;
        this.f16059c = new CopyOnWriteArrayList(c3518q.f16066c);
        this.f16060d = new AtomicLong(c3518q.f16067d);
        this.f16061e = new AtomicLong(c3518q.f16068e);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.f16062f = C3518q.m19154a(objectInputStream);
    }

    private Object readResolve() {
        return new C3515n(this.f16062f);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        new C3518q(this).m19159a(objectOutputStream);
    }

    public int m19140a() {
        return this.f16057a.get();
    }

    public int m19141b() {
        return this.f16059c.size();
    }

    public long m19142c() {
        return this.f16060d.get();
    }

    public List<C3493a> m19143d() {
        return this.f16059c;
    }

    public int m19144e() {
        return this.f16058b.get();
    }

    public boolean m19145f() {
        return m19141b() == 0;
    }

    public C0133b m19146g() {
        return new C3517p();
    }
}
