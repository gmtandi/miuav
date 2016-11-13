package org.codehaus.jackson.sym;

import org.p122a.p123a.C2915a;

public final class Name1 extends Name {
    static final Name1 sEmptyName;
    final int mQuad;

    static {
        sEmptyName = new Name1(C2915a.f14760f, 0, 0);
    }

    Name1(String str, int i, int i2) {
        super(str, i);
        this.mQuad = i2;
    }

    static final Name1 getEmptyName() {
        return sEmptyName;
    }

    public boolean equals(int i) {
        return i == this.mQuad;
    }

    public boolean equals(int i, int i2) {
        return i == this.mQuad && i2 == 0;
    }

    public boolean equals(int[] iArr, int i) {
        return i == 1 && iArr[0] == this.mQuad;
    }
}
