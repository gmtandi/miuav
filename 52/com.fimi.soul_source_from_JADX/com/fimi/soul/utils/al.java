package com.fimi.soul.utils;

import android.annotation.SuppressLint;
import java.util.LinkedList;

public class al {
    private LinkedList f10027a;
    private int f10028b;

    public al() {
        this.f10028b = 10;
        this.f10027a = new LinkedList();
    }

    @SuppressLint({"NewApi"})
    public Object m12256a() {
        return this.f10027a.peekFirst();
    }

    public void m12257a(int i) {
        this.f10028b = i;
    }

    public void m12258a(Object obj) {
        if (this.f10028b <= 0) {
            this.f10027a.removeFirst();
        }
        this.f10027a.addLast(obj);
        this.f10028b--;
    }

    public void m12259a(LinkedList linkedList) {
        this.f10027a = linkedList;
    }

    public void m12260b() {
        this.f10027a.removeFirst();
    }

    public int m12261c() {
        return this.f10027a.size();
    }

    public LinkedList m12262d() {
        return this.f10027a;
    }

    public int m12263e() {
        return this.f10028b;
    }
}
