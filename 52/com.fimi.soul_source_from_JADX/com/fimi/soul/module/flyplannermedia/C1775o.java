package com.fimi.soul.module.flyplannermedia;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.module.flyplannermedia.o */
public class C1775o<T> {
    private int f8708a;
    private int f8709b;
    private int f8710c;
    private List<List<T>> f8711d;
    private C1776p<T> f8712e;

    public C1775o() {
        this.f8708a = 12;
        this.f8709b = 0;
        this.f8710c = 0;
        this.f8711d = null;
    }

    public C1775o(List<T> list) {
        this.f8708a = 12;
        this.f8709b = 0;
        this.f8710c = 0;
        this.f8711d = null;
        m11452a((List) list);
    }

    public C1775o(List<T> list, int i) {
        this.f8708a = 12;
        this.f8709b = 0;
        this.f8710c = 0;
        this.f8711d = null;
        this.f8708a = i;
        m11452a((List) list);
    }

    private void m11452a(List<T> list) {
        this.f8711d = new ArrayList();
        int size = ((list.size() - 1) / this.f8708a) + 1;
        for (int i = 0; i < size; i++) {
            List arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.f8708a; i2++) {
                int i3 = (this.f8708a * i) + i2;
                if (i3 < list.size()) {
                    arrayList.add(list.get(i3));
                }
            }
            this.f8711d.add(arrayList);
        }
    }

    public int m11453a() {
        return this.f8708a;
    }

    public void m11454a(C1776p<T> c1776p) {
        this.f8712e = c1776p;
    }

    public boolean m11455b() {
        if (this.f8712e == null || this.f8710c >= this.f8708a || this.f8709b >= this.f8711d.size() || this.f8710c >= ((List) this.f8711d.get(this.f8709b)).size()) {
            return false;
        }
        Object obj = ((List) this.f8711d.get(this.f8709b)).get(this.f8710c);
        this.f8710c++;
        this.f8712e.m11458a(obj);
        return true;
    }

    public void m11456c() {
        this.f8709b = 0;
        this.f8710c = 0;
    }

    public boolean m11457d() {
        if (this.f8709b >= this.f8711d.size() || this.f8710c < ((List) this.f8711d.get(this.f8709b)).size()) {
            return false;
        }
        this.f8709b++;
        this.f8710c = 0;
        return true;
    }
}
