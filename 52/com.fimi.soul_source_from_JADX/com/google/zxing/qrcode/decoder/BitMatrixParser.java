package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    BitMatrixParser(BitMatrix bitMatrix) {
        int height = bitMatrix.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix;
    }

    private int copyBit(int i, int i2, int i3) {
        return this.bitMatrix.get(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    byte[] readCodewords() {
        FormatInformation readFormatInformation = readFormatInformation();
        Version readVersion = readVersion();
        DataMask forReference = DataMask.forReference(readFormatInformation.getDataMask());
        int height = this.bitMatrix.getHeight();
        forReference.unmaskBitMatrix(this.bitMatrix, height);
        BitMatrix buildFunctionPattern = readVersion.buildFunctionPattern();
        byte[] bArr = new byte[readVersion.getTotalCodewords()];
        int i = height - 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i > 0) {
            if (i == 6) {
                i--;
            }
            int i6 = 0;
            while (i6 < height) {
                int i7 = i5 != 0 ? (height - 1) - i6 : i6;
                for (int i8 = 0; i8 < 2; i8++) {
                    if (!buildFunctionPattern.get(i - i8, i7)) {
                        i2++;
                        i3 <<= 1;
                        if (this.bitMatrix.get(i - i8, i7)) {
                            i3 |= 1;
                        }
                        if (i2 == 8) {
                            i2 = i4 + 1;
                            bArr[i4] = (byte) i3;
                            i3 = 0;
                            i4 = i2;
                            i2 = 0;
                        }
                    }
                }
                i6++;
            }
            i -= 2;
            i5 ^= 1;
        }
        if (i4 == readVersion.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    FormatInformation readFormatInformation() {
        int i = 0;
        if (this.parsedFormatInfo != null) {
            return this.parsedFormatInfo;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < 6; i2++) {
            i3 = copyBit(i2, 8, i3);
        }
        i3 = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, i3)));
        for (i2 = 5; i2 >= 0; i2--) {
            i3 = copyBit(8, i2, i3);
        }
        int height = this.bitMatrix.getHeight();
        int i4 = height - 8;
        for (i2 = height - 1; i2 >= i4; i2--) {
            i = copyBit(i2, 8, i);
        }
        for (i2 = height - 7; i2 < height; i2++) {
            i = copyBit(8, i2, i);
        }
        this.parsedFormatInfo = FormatInformation.decodeFormatInformation(i3, i);
        if (this.parsedFormatInfo != null) {
            return this.parsedFormatInfo;
        }
        throw FormatException.getFormatInstance();
    }

    Version readVersion() {
        int i = 0;
        if (this.parsedVersion != null) {
            return this.parsedVersion;
        }
        int height = this.bitMatrix.getHeight();
        int i2 = (height - 17) >> 2;
        if (i2 <= 6) {
            return Version.getVersionForNumber(i2);
        }
        int i3 = height - 11;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (i2 = height - 9; i2 >= i3; i2--) {
                i4 = copyBit(i2, i5, i4);
            }
        }
        this.parsedVersion = Version.decodeVersionInformation(i4);
        if (this.parsedVersion != null && this.parsedVersion.getDimensionForVersion() == height) {
            return this.parsedVersion;
        }
        for (i2 = 5; i2 >= 0; i2--) {
            for (int i6 = height - 9; i6 >= i3; i6--) {
                i = copyBit(i2, i6, i);
            }
        }
        this.parsedVersion = Version.decodeVersionInformation(i);
        if (this.parsedVersion != null && this.parsedVersion.getDimensionForVersion() == height) {
            return this.parsedVersion;
        }
        throw FormatException.getFormatInstance();
    }
}
