package com.p054c.p055a.p063b.p064a;

import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.c.a.b.a.f */
public class C0900f {
    private static final int f4768a = 9;
    private static final String f4769b = "x";
    private final int f4770c;
    private final int f4771d;

    public C0900f(int i, int i2) {
        this.f4770c = i;
        this.f4771d = i2;
    }

    public C0900f(int i, int i2, int i3) {
        if (i3 % Opcodes.GETFIELD == 0) {
            this.f4770c = i;
            this.f4771d = i2;
            return;
        }
        this.f4770c = i2;
        this.f4771d = i;
    }

    public int m7206a() {
        return this.f4770c;
    }

    public C0900f m7207a(float f) {
        return new C0900f((int) (((float) this.f4770c) * f), (int) (((float) this.f4771d) * f));
    }

    public C0900f m7208a(int i) {
        return new C0900f(this.f4770c / i, this.f4771d / i);
    }

    public int m7209b() {
        return this.f4771d;
    }

    public String toString() {
        return new StringBuilder(f4768a).append(this.f4770c).append(f4769b).append(this.f4771d).toString();
    }
}
