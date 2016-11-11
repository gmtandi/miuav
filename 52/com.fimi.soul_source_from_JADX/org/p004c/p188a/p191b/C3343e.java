package org.p004c.p188a.p191b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C0133b;

/* renamed from: org.c.a.b.e */
public class C3343e implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<String, Long> f15846a;
    private final Map<String, Long> f15847b;
    private final File f15848c;

    private C3343e(File file) {
        this.f15846a = new HashMap();
        this.f15847b = new HashMap();
        this.f15848c = file;
    }

    public static C3343e m18440a(File file) {
        if (file.exists()) {
            try {
                return C3343e.m18442b(file);
            } catch (C3338a e) {
                e.printStackTrace();
                file.delete();
            }
        }
        return new C3343e(file);
    }

    private static C3343e m18442b(File file) {
        ObjectInputStream objectInputStream;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                objectInputStream = new ObjectInputStream(fileInputStream);
                C3343e c3343e = (C3343e) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                return c3343e;
            } catch (Throwable th) {
                fileInputStream.close();
            }
        } catch (Throwable e) {
            throw new C3338a(e);
        }
    }

    private void m18443c() {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.f15848c));
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

    Long m18444a(C3507d c3507d) {
        return (Long) this.f15847b.get(c3507d.toString());
    }

    public C0133b m18445a() {
        return new C3345g();
    }

    void m18446a(C3507d c3507d, long j) {
        this.f15847b.put(c3507d.toString(), Long.valueOf(j));
    }

    public Comparator<C3507d> m18447b() {
        return new C3346h();
    }

    void m18448b(C3507d c3507d, long j) {
        this.f15846a.put(c3507d.toString(), Long.valueOf(j));
    }

    boolean m18449b(C3507d c3507d) {
        return !this.f15846a.containsKey(c3507d.toString());
    }

    Long m18450c(C3507d c3507d) {
        return (Long) this.f15846a.get(c3507d.toString());
    }
}
