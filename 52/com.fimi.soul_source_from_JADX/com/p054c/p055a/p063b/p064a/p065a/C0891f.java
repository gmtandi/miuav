package com.p054c.p055a.p063b.p064a.p065a;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.c.a.b.a.a.f */
abstract class C0891f implements Iterator<E> {
    C0894i<E> f4742a;
    E f4743b;
    final /* synthetic */ C0888d f4744c;
    private C0894i<E> f4745d;

    C0891f(C0888d c0888d) {
        this.f4744c = c0888d;
        ReentrantLock reentrantLock = c0888d.f4737c;
        reentrantLock.lock();
        try {
            this.f4742a = m7197a();
            this.f4743b = this.f4742a == null ? null : this.f4742a.f4748a;
        } finally {
            reentrantLock.unlock();
        }
    }

    private C0894i<E> m7196b(C0894i<E> c0894i) {
        while (true) {
            C0894i<E> a = m7198a(c0894i);
            if (a == null) {
                return null;
            }
            if (a.f4748a != null) {
                return a;
            }
            if (a == c0894i) {
                return m7197a();
            }
            c0894i = a;
        }
    }

    abstract C0894i<E> m7197a();

    abstract C0894i<E> m7198a(C0894i<E> c0894i);

    void m7199b() {
        ReentrantLock reentrantLock = this.f4744c.f4737c;
        reentrantLock.lock();
        try {
            this.f4742a = m7196b(this.f4742a);
            this.f4743b = this.f4742a == null ? null : this.f4742a.f4748a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean hasNext() {
        return this.f4742a != null;
    }

    public E next() {
        if (this.f4742a == null) {
            throw new NoSuchElementException();
        }
        this.f4745d = this.f4742a;
        E e = this.f4743b;
        m7199b();
        return e;
    }

    public void remove() {
        C0894i c0894i = this.f4745d;
        if (c0894i == null) {
            throw new IllegalStateException();
        }
        this.f4745d = null;
        ReentrantLock reentrantLock = this.f4744c.f4737c;
        reentrantLock.lock();
        try {
            if (c0894i.f4748a != null) {
                this.f4744c.m7172a(c0894i);
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }
}
