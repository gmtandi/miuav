package com.tencent.open.p133a;

import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.tencent.open.a.g */
public class C2334g implements Iterable<String> {
    private ConcurrentLinkedQueue<String> f12016a;
    private AtomicInteger f12017b;

    public C2334g() {
        this.f12016a = null;
        this.f12017b = null;
        this.f12016a = new ConcurrentLinkedQueue();
        this.f12017b = new AtomicInteger(0);
    }

    public int m13762a() {
        return this.f12017b.get();
    }

    public int m13763a(String str) {
        int length = str.length();
        this.f12016a.add(str);
        return this.f12017b.addAndGet(length);
    }

    public void m13764a(Writer writer, char[] cArr) {
        if (writer != null && cArr != null && cArr.length != 0) {
            int length = cArr.length;
            Iterator it = iterator();
            int i = 0;
            int i2 = length;
            while (it.hasNext()) {
                String str = (String) it.next();
                int length2 = str.length();
                int i3 = 0;
                while (length2 > 0) {
                    int i4 = i2 > length2 ? length2 : i2;
                    str.getChars(i3, i3 + i4, cArr, i);
                    i2 -= i4;
                    i += i4;
                    length2 -= i4;
                    i4 += i3;
                    if (i2 == 0) {
                        writer.write(cArr, 0, length);
                        i = 0;
                        i2 = length;
                        i3 = i4;
                    } else {
                        i3 = i4;
                    }
                }
            }
            if (i > 0) {
                writer.write(cArr, 0, i);
            }
            writer.flush();
        }
    }

    public void m13765b() {
        this.f12016a.clear();
        this.f12017b.set(0);
    }

    public Iterator<String> iterator() {
        return this.f12016a.iterator();
    }
}
