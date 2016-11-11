package com.google.zxing.common.reedsolomon;

import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class ReedSolomonDecoder {
    private final GF256 field;

    public ReedSolomonDecoder(GF256 gf256) {
        this.field = gf256;
    }

    private int[] findErrorLocations(GF256Poly gF256Poly) {
        int i = 1;
        int degree = gF256Poly.getDegree();
        if (degree == 1) {
            return new int[]{gF256Poly.getCoefficient(1)};
        }
        int[] iArr = new int[degree];
        int i2 = 0;
        while (i < Opcodes.ACC_NATIVE && i2 < degree) {
            if (gF256Poly.evaluateAt(i) == 0) {
                iArr[i2] = this.field.inverse(i);
                i2++;
            }
            i++;
        }
        if (i2 == degree) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GF256Poly gF256Poly, int[] iArr, boolean z) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int inverse = this.field.inverse(iArr[i]);
            int i2 = 1;
            int i3 = 0;
            while (i3 < length) {
                int multiply;
                if (i != i3) {
                    multiply = this.field.multiply(iArr[i3], inverse);
                    multiply = this.field.multiply(i2, (multiply & 1) == 0 ? multiply | 1 : multiply & -2);
                } else {
                    multiply = i2;
                }
                i3++;
                i2 = multiply;
            }
            iArr2[i] = this.field.multiply(gF256Poly.evaluateAt(inverse), this.field.inverse(i2));
            if (z) {
                iArr2[i] = this.field.multiply(iArr2[i], inverse);
            }
        }
        return iArr2;
    }

    private GF256Poly[] runEuclideanAlgorithm(GF256Poly gF256Poly, GF256Poly gF256Poly2, int i) {
        if (gF256Poly.getDegree() >= gF256Poly2.getDegree()) {
            GF256Poly gF256Poly3 = gF256Poly2;
            gF256Poly2 = gF256Poly;
            gF256Poly = gF256Poly3;
        }
        GF256Poly one = this.field.getOne();
        GF256Poly zero = this.field.getZero();
        GF256Poly zero2 = this.field.getZero();
        GF256Poly one2 = this.field.getOne();
        while (gF256Poly.getDegree() >= i / 2) {
            if (gF256Poly.isZero()) {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
            GF256Poly zero3 = this.field.getZero();
            int inverse = this.field.inverse(gF256Poly.getCoefficient(gF256Poly.getDegree()));
            GF256Poly gF256Poly4 = zero3;
            zero3 = gF256Poly2;
            while (zero3.getDegree() >= gF256Poly.getDegree() && !zero3.isZero()) {
                int degree = zero3.getDegree() - gF256Poly.getDegree();
                int multiply = this.field.multiply(zero3.getCoefficient(zero3.getDegree()), inverse);
                gF256Poly4 = gF256Poly4.addOrSubtract(this.field.buildMonomial(degree, multiply));
                zero3 = zero3.addOrSubtract(gF256Poly.multiplyByMonomial(degree, multiply));
            }
            gF256Poly2 = gF256Poly;
            gF256Poly = zero3;
            gF256Poly3 = gF256Poly4.multiply(zero).addOrSubtract(one);
            one = zero;
            zero = gF256Poly3;
            GF256Poly gF256Poly5 = one2;
            one2 = gF256Poly4.multiply(one2).addOrSubtract(zero2);
            zero2 = gF256Poly5;
        }
        int coefficient = one2.getCoefficient(0);
        if (coefficient == 0) {
            throw new ReedSolomonException("sigmaTilde(0) was zero");
        }
        coefficient = this.field.inverse(coefficient);
        one2 = one2.multiply(coefficient);
        zero2 = gF256Poly.multiply(coefficient);
        return new GF256Poly[]{one2, zero2};
    }

    public void decode(int[] iArr, int i) {
        int i2 = 0;
        GF256Poly gF256Poly = new GF256Poly(this.field, iArr);
        int[] iArr2 = new int[i];
        boolean equals = this.field.equals(GF256.DATA_MATRIX_FIELD);
        int i3 = 0;
        int i4 = 1;
        while (i3 < i) {
            int evaluateAt = gF256Poly.evaluateAt(this.field.exp(equals ? i3 + 1 : i3));
            iArr2[(iArr2.length - 1) - i3] = evaluateAt;
            i3++;
            i4 = evaluateAt != 0 ? 0 : i4;
        }
        if (i4 == 0) {
            GF256Poly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new GF256Poly(this.field, iArr2), i);
            GF256Poly gF256Poly2 = runEuclideanAlgorithm[0];
            GF256Poly gF256Poly3 = runEuclideanAlgorithm[1];
            int[] findErrorLocations = findErrorLocations(gF256Poly2);
            int[] findErrorMagnitudes = findErrorMagnitudes(gF256Poly3, findErrorLocations, equals);
            while (i2 < findErrorLocations.length) {
                i4 = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
                if (i4 < 0) {
                    throw new ReedSolomonException("Bad error location");
                }
                iArr[i4] = GF256.addOrSubtract(iArr[i4], findErrorMagnitudes[i2]);
                i2++;
            }
        }
    }
}
