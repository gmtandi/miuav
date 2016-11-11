package com.google.zxing.common.reedsolomon;

final class GF256Poly {
    private final int[] coefficients;
    private final GF256 field;

    GF256Poly(GF256 gf256, int[] iArr) {
        int i = 1;
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = gf256;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.coefficients = gf256.getZero().coefficients;
            return;
        }
        this.coefficients = new int[(length - i)];
        System.arraycopy(iArr, i, this.coefficients, 0, this.coefficients.length);
    }

    GF256Poly addOrSubtract(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (isZero()) {
            return gF256Poly;
        } else {
            if (gF256Poly.isZero()) {
                return this;
            }
            Object obj = this.coefficients;
            Object obj2 = gF256Poly.coefficients;
            if (obj.length <= obj2.length) {
                Object obj3 = obj2;
                obj2 = obj;
                obj = obj3;
            }
            Object obj4 = new int[obj.length];
            int length = obj.length - r1.length;
            System.arraycopy(obj, 0, obj4, 0, length);
            for (int i = length; i < obj.length; i++) {
                obj4[i] = GF256.addOrSubtract(r1[i - length], obj[i]);
            }
            return new GF256Poly(this.field, obj4);
        }
    }

    GF256Poly[] divide(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (gF256Poly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            GF256Poly zero = this.field.getZero();
            int inverse = this.field.inverse(gF256Poly.getCoefficient(gF256Poly.getDegree()));
            GF256Poly gF256Poly2 = zero;
            zero = this;
            while (zero.getDegree() >= gF256Poly.getDegree() && !zero.isZero()) {
                int degree = zero.getDegree() - gF256Poly.getDegree();
                int multiply = this.field.multiply(zero.getCoefficient(zero.getDegree()), inverse);
                GF256Poly multiplyByMonomial = gF256Poly.multiplyByMonomial(degree, multiply);
                gF256Poly2 = gF256Poly2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                zero = zero.addOrSubtract(multiplyByMonomial);
            }
            return new GF256Poly[]{gF256Poly2, zero};
        }
    }

    int evaluateAt(int i) {
        int i2 = 0;
        if (i == 0) {
            return getCoefficient(0);
        }
        int length = this.coefficients.length;
        int i3;
        if (i == 1) {
            i3 = 0;
            while (i2 < length) {
                int addOrSubtract = GF256.addOrSubtract(i3, this.coefficients[i2]);
                i2++;
                i3 = addOrSubtract;
            }
            return i3;
        }
        i3 = this.coefficients[0];
        i2 = 1;
        while (i2 < length) {
            addOrSubtract = GF256.addOrSubtract(this.field.multiply(i, i3), this.coefficients[i2]);
            i2++;
            i3 = addOrSubtract;
        }
        return i3;
    }

    int getCoefficient(int i) {
        return this.coefficients[(this.coefficients.length - 1) - i];
    }

    int[] getCoefficients() {
        return this.coefficients;
    }

    int getDegree() {
        return this.coefficients.length - 1;
    }

    boolean isZero() {
        return this.coefficients[0] == 0;
    }

    GF256Poly multiply(int i) {
        if (i == 0) {
            return this.field.getZero();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.multiply(this.coefficients[i2], i);
        }
        return new GF256Poly(this.field, iArr);
    }

    GF256Poly multiply(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (isZero() || gF256Poly.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = gF256Poly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr3[i + i3] = GF256.addOrSubtract(iArr3[i + i3], this.field.multiply(i2, iArr2[i3]));
                }
            }
            return new GF256Poly(this.field, iArr3);
        }
    }

    GF256Poly multiplyByMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            int[] iArr = new int[(length + i)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.field.multiply(this.coefficients[i3], i2);
            }
            return new GF256Poly(this.field, iArr);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    stringBuffer.append(" - ");
                    coefficient = -coefficient;
                } else if (stringBuffer.length() > 0) {
                    stringBuffer.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    coefficient = this.field.log(coefficient);
                    if (coefficient == 0) {
                        stringBuffer.append('1');
                    } else if (coefficient == 1) {
                        stringBuffer.append('a');
                    } else {
                        stringBuffer.append("a^");
                        stringBuffer.append(coefficient);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        stringBuffer.append('x');
                    } else {
                        stringBuffer.append("x^");
                        stringBuffer.append(degree);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
