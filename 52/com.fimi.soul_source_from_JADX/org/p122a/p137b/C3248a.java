package org.p122a.p137b;

import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: org.a.b.a */
public class C3248a {
    private short[] f15725a;
    private int f15726b;

    public C3248a(int i) {
        this.f15726b = -1;
        this.f15725a = new short[i];
    }

    private void m17922c() {
        Object obj = new short[(this.f15725a.length * 2)];
        System.arraycopy(this.f15725a, 0, obj, 0, this.f15725a.length);
        this.f15725a = obj;
    }

    public short m17923a() {
        short[] sArr = this.f15725a;
        int i = this.f15726b;
        this.f15726b = i - 1;
        return sArr[i];
    }

    public void m17924a(short s) {
        if (this.f15725a.length == this.f15726b + 1) {
            m17922c();
        }
        short[] sArr = this.f15725a;
        int i = this.f15726b + 1;
        this.f15726b = i;
        sArr[i] = s;
    }

    public void m17925b() {
        this.f15726b = -1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.f15725a.length; i++) {
            if (i != 0) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            if (i == this.f15726b) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.f15725a[i]);
            if (i == this.f15726b) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
