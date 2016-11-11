package org.p184b.p186b;

import java.lang.reflect.Array;
import java.util.Iterator;

/* renamed from: org.b.b.a */
public class C3302a implements Iterator<Object> {
    private final Object f15809a;
    private int f15810b;

    public C3302a(Object obj) {
        this.f15810b = 0;
        if (obj.getClass().isArray()) {
            this.f15809a = obj;
            return;
        }
        throw new IllegalArgumentException("not an array");
    }

    public boolean hasNext() {
        return this.f15810b < Array.getLength(this.f15809a);
    }

    public Object next() {
        Object obj = this.f15809a;
        int i = this.f15810b;
        this.f15810b = i + 1;
        return Array.get(obj, i);
    }

    public void remove() {
        throw new UnsupportedOperationException("cannot remove items from an array");
    }
}
