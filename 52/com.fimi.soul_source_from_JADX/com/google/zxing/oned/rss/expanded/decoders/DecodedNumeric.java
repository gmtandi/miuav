package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedNumeric extends DecodedObject {
    static final int FNC1 = 10;
    private final int firstDigit;
    private final int secondDigit;

    DecodedNumeric(int i, int i2, int i3) {
        super(i);
        this.firstDigit = i2;
        this.secondDigit = i3;
        if (this.firstDigit < 0 || this.firstDigit > FNC1) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid firstDigit: ").append(i2).toString());
        } else if (this.secondDigit < 0 || this.secondDigit > FNC1) {
            throw new IllegalArgumentException(new StringBuffer().append("Invalid secondDigit: ").append(i3).toString());
        }
    }

    int getFirstDigit() {
        return this.firstDigit;
    }

    int getSecondDigit() {
        return this.secondDigit;
    }

    int getValue() {
        return (this.firstDigit * FNC1) + this.secondDigit;
    }

    boolean isAnyFNC1() {
        return this.firstDigit == FNC1 || this.secondDigit == FNC1;
    }

    boolean isFirstDigitFNC1() {
        return this.firstDigit == FNC1;
    }

    boolean isSecondDigitFNC1() {
        return this.secondDigit == FNC1;
    }
}
