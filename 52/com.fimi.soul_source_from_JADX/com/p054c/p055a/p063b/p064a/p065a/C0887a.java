package com.p054c.p055a.p063b.p064a.p065a;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.c.a.b.a.a.a */
public interface C0887a<E> extends C0886b<E>, BlockingQueue<E> {
    E m7151a();

    E m7152a(long j, TimeUnit timeUnit);

    void m7153a(E e);

    boolean m7154a(E e, long j, TimeUnit timeUnit);

    boolean add(E e);

    E m7155b();

    E m7156b(long j, TimeUnit timeUnit);

    void m7157b(E e);

    boolean m7158b(E e, long j, TimeUnit timeUnit);

    boolean m7159c(E e);

    boolean contains(Object obj);

    boolean m7160d(E e);

    void m7161e(E e);

    E element();

    void m7162f(E e);

    boolean m7163g(Object obj);

    boolean m7164h(Object obj);

    void m7165i(E e);

    Iterator<E> iterator();

    boolean offer(E e);

    boolean offer(E e, long j, TimeUnit timeUnit);

    E peek();

    E poll();

    E poll(long j, TimeUnit timeUnit);

    void put(E e);

    E remove();

    boolean remove(Object obj);

    int size();

    E take();
}
