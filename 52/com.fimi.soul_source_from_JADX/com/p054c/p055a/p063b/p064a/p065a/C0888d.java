package com.p054c.p055a.p063b.p064a.p065a;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.p122a.p123a.p124f.p125c.C3022o;

/* renamed from: com.c.a.b.a.a.d */
public class C0888d<E> extends AbstractQueue<E> implements C0887a<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    transient C0894i<E> f4735a;
    transient C0894i<E> f4736b;
    final ReentrantLock f4737c;
    private transient int f4738d;
    private final int f4739e;
    private final Condition f4740f;
    private final Condition f4741g;

    public C0888d() {
        this(Integer.MAX_VALUE);
    }

    public C0888d(int i) {
        this.f4737c = new ReentrantLock();
        this.f4740f = this.f4737c.newCondition();
        this.f4741g = this.f4737c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.f4739e = i;
    }

    public C0888d(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!m7167c(new C0894i(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean m7166b(C0894i<E> c0894i) {
        if (this.f4738d >= this.f4739e) {
            return false;
        }
        C0894i c0894i2 = this.f4735a;
        c0894i.f4750c = c0894i2;
        this.f4735a = c0894i;
        if (this.f4736b == null) {
            this.f4736b = c0894i;
        } else {
            c0894i2.f4749b = c0894i;
        }
        this.f4738d++;
        this.f4740f.signal();
        return true;
    }

    private boolean m7167c(C0894i<E> c0894i) {
        if (this.f4738d >= this.f4739e) {
            return false;
        }
        C0894i c0894i2 = this.f4736b;
        c0894i.f4749b = c0894i2;
        this.f4736b = c0894i;
        if (this.f4735a == null) {
            this.f4735a = c0894i;
        } else {
            c0894i2.f4750c = c0894i;
        }
        this.f4738d++;
        this.f4740f.signal();
        return true;
    }

    private E m7168m() {
        C0894i c0894i = this.f4735a;
        if (c0894i == null) {
            return null;
        }
        C0894i c0894i2 = c0894i.f4750c;
        E e = c0894i.f4748a;
        c0894i.f4748a = null;
        c0894i.f4750c = c0894i;
        this.f4735a = c0894i2;
        if (c0894i2 == null) {
            this.f4736b = null;
        } else {
            c0894i2.f4749b = null;
        }
        this.f4738d--;
        this.f4741g.signal();
        return e;
    }

    private E m7169n() {
        C0894i c0894i = this.f4736b;
        if (c0894i == null) {
            return null;
        }
        C0894i c0894i2 = c0894i.f4749b;
        E e = c0894i.f4748a;
        c0894i.f4748a = null;
        c0894i.f4749b = c0894i;
        this.f4736b = c0894i2;
        if (c0894i2 == null) {
            this.f4735a = null;
        } else {
            c0894i2.f4750c = null;
        }
        this.f4738d--;
        this.f4741g.signal();
        return e;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f4738d = 0;
        this.f4735a = null;
        this.f4736b = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (C0894i c0894i = this.f4735a; c0894i != null; c0894i = c0894i.f4750c) {
                objectOutputStream.writeObject(c0894i.f4748a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    public E m7170a() {
        E m;
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        while (true) {
            try {
                m = m7168m();
                if (m != null) {
                    break;
                }
                this.f4740f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return m;
    }

    public E m7171a(long j, TimeUnit timeUnit) {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E m = m7168m();
                if (m != null) {
                    reentrantLock.unlock();
                    return m;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.f4740f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    void m7172a(C0894i<E> c0894i) {
        C0894i c0894i2 = c0894i.f4749b;
        C0894i c0894i3 = c0894i.f4750c;
        if (c0894i2 == null) {
            m7168m();
        } else if (c0894i3 == null) {
            m7169n();
        } else {
            c0894i2.f4750c = c0894i3;
            c0894i3.f4749b = c0894i2;
            c0894i.f4748a = null;
            this.f4738d--;
            this.f4741g.signal();
        }
    }

    public void m7173a(E e) {
        if (!m7180c((Object) e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean m7174a(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lockInterruptibly();
        while (!m7166b(c0894i)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.f4741g.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public boolean add(E e) {
        m7177b((Object) e);
        return true;
    }

    public E m7175b() {
        E n;
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        while (true) {
            try {
                n = m7169n();
                if (n != null) {
                    break;
                }
                this.f4740f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return n;
    }

    public E m7176b(long j, TimeUnit timeUnit) {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E n = m7169n();
                if (n != null) {
                    reentrantLock.unlock();
                    return n;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.f4740f.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public void m7177b(E e) {
        if (!m7182d(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean m7178b(E e, long j, TimeUnit timeUnit) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lockInterruptibly();
        while (!m7167c(c0894i)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.f4741g.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E m7179c() {
        E e = m7183e();
        if (e != null) {
            return e;
        }
        throw new NoSuchElementException();
    }

    public boolean m7180c(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            boolean b = m7166b(c0894i);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            C0894i c0894i = this.f4735a;
            while (c0894i != null) {
                c0894i.f4748a = null;
                C0894i c0894i2 = c0894i.f4750c;
                c0894i.f4749b = null;
                c0894i.f4750c = null;
                c0894i = c0894i2;
            }
            this.f4736b = null;
            this.f4735a = null;
            this.f4738d = 0;
            this.f4741g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            for (C0894i c0894i = this.f4735a; c0894i != null; c0894i = c0894i.f4750c) {
                if (obj.equals(c0894i.f4748a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E m7181d() {
        E f = m7185f();
        if (f != null) {
            return f;
        }
        throw new NoSuchElementException();
    }

    public boolean m7182d(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            boolean c = m7167c(c0894i);
            return c;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.f4737c;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.f4738d);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.f4735a.f4748a);
                    m7168m();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E m7183e() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            E m = m7168m();
            return m;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void m7184e(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        while (!m7166b(c0894i)) {
            try {
                this.f4741g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E element() {
        return m7187g();
    }

    public E m7185f() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            E n = m7169n();
            return n;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void m7186f(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        C0894i c0894i = new C0894i(e);
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        while (!m7167c(c0894i)) {
            try {
                this.f4741g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E m7187g() {
        E i = m7191i();
        if (i != null) {
            return i;
        }
        throw new NoSuchElementException();
    }

    public boolean m7188g(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            for (C0894i c0894i = this.f4735a; c0894i != null; c0894i = c0894i.f4750c) {
                if (obj.equals(c0894i.f4748a)) {
                    m7172a(c0894i);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E m7189h() {
        E j = m7193j();
        if (j != null) {
            return j;
        }
        throw new NoSuchElementException();
    }

    public boolean m7190h(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            for (C0894i c0894i = this.f4736b; c0894i != null; c0894i = c0894i.f4749b) {
                if (obj.equals(c0894i.f4748a)) {
                    m7172a(c0894i);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E m7191i() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            E e = this.f4735a == null ? null : this.f4735a.f4748a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public void m7192i(E e) {
        m7173a((Object) e);
    }

    public Iterator<E> iterator() {
        return new C0893h();
    }

    public E m7193j() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            E e = this.f4736b == null ? null : this.f4736b.f4748a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E m7194k() {
        return m7179c();
    }

    public Iterator<E> m7195l() {
        return new C0892g();
    }

    public boolean offer(E e) {
        return m7182d(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return m7178b(e, j, timeUnit);
    }

    public E peek() {
        return m7191i();
    }

    public E poll() {
        return m7183e();
    }

    public E poll(long j, TimeUnit timeUnit) {
        return m7171a(j, timeUnit);
    }

    public void put(E e) {
        m7186f(e);
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            int i = this.f4739e - this.f4738d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E remove() {
        return m7179c();
    }

    public boolean remove(Object obj) {
        return m7188g(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            int i = this.f4738d;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E take() {
        return m7170a();
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.f4738d];
            int i = 0;
            C0894i c0894i = this.f4735a;
            while (c0894i != null) {
                int i2 = i + 1;
                objArr[i] = c0894i.f4748a;
                c0894i = c0894i.f4750c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.f4738d) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f4738d);
            }
            int i = 0;
            C0894i c0894i = this.f4735a;
            while (c0894i != null) {
                int i2 = i + 1;
                tArr[i] = c0894i.f4748a;
                c0894i = c0894i.f4750c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.f4737c;
        reentrantLock.lock();
        try {
            String str;
            C0894i c0894i = this.f4735a;
            if (c0894i == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                C0894i c0894i2 = c0894i;
                while (true) {
                    Object obj = c0894i2.f4748a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    c0894i = c0894i2.f4750c;
                    if (c0894i == null) {
                        break;
                    }
                    stringBuilder.append(',').append(C3022o.f15055c);
                    c0894i2 = c0894i;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }
}
