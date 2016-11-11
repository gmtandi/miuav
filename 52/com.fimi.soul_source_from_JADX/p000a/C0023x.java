package p000a;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a.x */
final class C0023x implements C0001q<Object, Void> {
    final /* synthetic */ Object f104a;
    final /* synthetic */ ArrayList f105b;
    final /* synthetic */ AtomicBoolean f106c;
    final /* synthetic */ AtomicInteger f107d;
    final /* synthetic */ ae f108e;

    C0023x(Object obj, ArrayList arrayList, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, ae aeVar) {
        this.f104a = obj;
        this.f105b = arrayList;
        this.f106c = atomicBoolean;
        this.f107d = atomicInteger;
        this.f108e = aeVar;
    }

    public Void m115a(C0018s<Object> c0018s) {
        if (c0018s.m107d()) {
            synchronized (this.f104a) {
                this.f105b.add(c0018s.m109f());
            }
        }
        if (c0018s.m104c()) {
            this.f106c.set(true);
        }
        if (this.f107d.decrementAndGet() == 0) {
            if (this.f105b.size() != 0) {
                if (this.f105b.size() == 1) {
                    this.f108e.m8b((Exception) this.f105b.get(0));
                } else {
                    this.f108e.m8b(new C0000a(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(this.f105b.size())}), (Throwable[]) this.f105b.toArray(new Throwable[this.f105b.size()])));
                }
            } else if (this.f106c.get()) {
                this.f108e.m11c();
            } else {
                this.f108e.m9b(null);
            }
        }
        return null;
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m115a(c0018s);
    }
}
