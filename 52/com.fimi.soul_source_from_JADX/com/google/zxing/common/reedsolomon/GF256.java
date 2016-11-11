package com.google.zxing.common.reedsolomon;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class GF256 {
    public static final GF256 DATA_MATRIX_FIELD;
    public static final GF256 QR_CODE_FIELD;
    private final int[] expTable;
    private final int[] logTable;
    private final GF256Poly one;
    private final GF256Poly zero;

    static {
        QR_CODE_FIELD = new GF256(285);
        DATA_MATRIX_FIELD = new GF256(301);
    }

    private GF256(int i) {
        this.expTable = new int[Opcodes.ACC_NATIVE];
        this.logTable = new int[Opcodes.ACC_NATIVE];
        int i2 = 1;
        for (int i3 = 0; i3 < Opcodes.ACC_NATIVE; i3++) {
            this.expTable[i3] = i2;
            i2 <<= 1;
            if (i2 >= Opcodes.ACC_NATIVE) {
                i2 ^= i;
            }
        }
        for (i2 = 0; i2 < Util.MASK_8BIT; i2++) {
            this.logTable[this.expTable[i2]] = i2;
        }
        this.zero = new GF256Poly(this, new int[]{0});
        this.one = new GF256Poly(this, new int[]{1});
    }

    static int addOrSubtract(int i, int i2) {
        return i ^ i2;
    }

    GF256Poly buildMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.zero;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new GF256Poly(this, iArr);
        }
    }

    int exp(int i) {
        return this.expTable[i];
    }

    GF256Poly getOne() {
        return this.one;
    }

    GF256Poly getZero() {
        return this.zero;
    }

    int inverse(int i) {
        if (i != 0) {
            return this.expTable[255 - this.logTable[i]];
        }
        throw new ArithmeticException();
    }

    int log(int i) {
        if (i != 0) {
            return this.logTable[i];
        }
        throw new IllegalArgumentException();
    }

    int multiply(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int i3 = this.logTable[i] + this.logTable[i2];
        return this.expTable[(i3 >>> 8) + (i3 & Util.MASK_8BIT)];
    }
}
