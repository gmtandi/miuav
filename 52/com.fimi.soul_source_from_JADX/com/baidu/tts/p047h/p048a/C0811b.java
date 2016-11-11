package com.baidu.tts.p047h.p048a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.util.Iterator;

/* renamed from: com.baidu.tts.h.a.b */
public class C0811b implements Iterator<C0810a> {
    private int f4568a;
    private int f4569b;
    private int f4570c;
    private int f4571d;
    private int f4572e;
    private int f4573f;

    private int m6790d() {
        return (this.f4568a + this.f4569b) - 1;
    }

    private int m6791e() {
        return (this.f4571d + this.f4570c) - 1;
    }

    public void m6792a() {
        this.f4568a = 0;
        this.f4569b = 0;
        this.f4571d = 0;
        this.f4572e = 0;
        this.f4573f = 0;
    }

    public void m6793a(int i) {
        this.f4570c = i;
    }

    public void m6794b() {
    }

    public void m6795b(int i) {
        this.f4569b += i;
        this.f4573f = 0;
    }

    public C0810a m6796c() {
        C0810a c0810a = new C0810a();
        int e = m6791e();
        if (e <= m6790d()) {
            int i = (e - this.f4572e) + 1;
            c0810a.m6784a(this.f4573f);
            c0810a.m6787b(i);
            this.f4572e = e + 1;
            this.f4571d = this.f4572e;
            this.f4573f += i;
            float f = ((float) this.f4571d) / ((float) this.f4569b);
            LoggerProxy.m6515d("UtteranceSubpackager", "mCurrentProgressStartIndex=" + this.f4571d + "--mCurrentAllUtteranceLenght=" + this.f4569b + "--percent=" + f);
            c0810a.m6783a(f);
            c0810a.m6785a(true);
        } else {
            e = this.f4569b - this.f4572e;
            c0810a.m6784a(this.f4573f);
            c0810a.m6787b(e);
            this.f4572e += e;
            this.f4573f = e + this.f4573f;
        }
        return c0810a;
    }

    public boolean hasNext() {
        return this.f4572e < m6790d();
    }

    public /* synthetic */ Object next() {
        return m6796c();
    }

    public void remove() {
    }
}
