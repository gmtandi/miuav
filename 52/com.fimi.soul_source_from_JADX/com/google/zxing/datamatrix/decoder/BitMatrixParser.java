package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

final class BitMatrixParser {
    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;

    BitMatrixParser(BitMatrix bitMatrix) {
        int height = bitMatrix.getHeight();
        if (height < 10 || height > Opcodes.D2F || (height & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.version = readVersion(bitMatrix);
        this.mappingBitMatrix = extractDataRegion(bitMatrix);
        this.readMappingMatrix = new BitMatrix(this.mappingBitMatrix.getHeight());
    }

    BitMatrix extractDataRegion(BitMatrix bitMatrix) {
        int symbolSizeRows = this.version.getSymbolSizeRows();
        int symbolSizeColumns = this.version.getSymbolSizeColumns();
        if (bitMatrix.getHeight() != symbolSizeRows) {
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        }
        int dataRegionSizeRows = this.version.getDataRegionSizeRows();
        int dataRegionSizeColumns = this.version.getDataRegionSizeColumns();
        int i = symbolSizeRows / dataRegionSizeRows;
        int i2 = symbolSizeColumns / dataRegionSizeColumns;
        BitMatrix bitMatrix2 = new BitMatrix(i * dataRegionSizeRows);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * dataRegionSizeRows;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = i5 * dataRegionSizeColumns;
                for (symbolSizeColumns = 0; symbolSizeColumns < dataRegionSizeRows; symbolSizeColumns++) {
                    int i7 = (((dataRegionSizeRows + 2) * i3) + 1) + symbolSizeColumns;
                    int i8 = i4 + symbolSizeColumns;
                    for (symbolSizeRows = 0; symbolSizeRows < dataRegionSizeColumns; symbolSizeRows++) {
                        if (bitMatrix.get((((dataRegionSizeColumns + 2) * i5) + 1) + symbolSizeRows, i7)) {
                            bitMatrix2.set(i6 + symbolSizeRows, i8);
                        }
                    }
                }
            }
        }
        return bitMatrix2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    byte[] readCodewords() {
        /*
        r14 = this;
        r9 = 4;
        r1 = 1;
        r0 = 0;
        r2 = r14.version;
        r2 = r2.getTotalCodewords();
        r10 = new byte[r2];
        r2 = r14.mappingBitMatrix;
        r11 = r2.getHeight();
        r2 = r0;
        r3 = r0;
        r4 = r0;
        r7 = r0;
        r5 = r9;
        r8 = r0;
    L_0x0017:
        if (r5 != r11) goto L_0x003f;
    L_0x0019:
        if (r7 != 0) goto L_0x003f;
    L_0x001b:
        if (r4 != 0) goto L_0x003f;
    L_0x001d:
        r6 = r8 + 1;
        r4 = r14.readCorner1(r11, r11);
        r4 = (byte) r4;
        r10[r8] = r4;
        r5 = r5 + -2;
        r4 = r7 + 2;
        r7 = r6;
        r6 = r5;
        r5 = r4;
        r4 = r1;
    L_0x002e:
        if (r6 < r11) goto L_0x00e4;
    L_0x0030:
        if (r5 < r11) goto L_0x00e4;
    L_0x0032:
        r0 = r14.version;
        r0 = r0.getTotalCodewords();
        if (r7 == r0) goto L_0x00e3;
    L_0x003a:
        r0 = com.google.zxing.FormatException.getFormatInstance();
        throw r0;
    L_0x003f:
        r6 = r11 + -2;
        if (r5 != r6) goto L_0x005d;
    L_0x0043:
        if (r7 != 0) goto L_0x005d;
    L_0x0045:
        r6 = r11 & 3;
        if (r6 == 0) goto L_0x005d;
    L_0x0049:
        if (r3 != 0) goto L_0x005d;
    L_0x004b:
        r6 = r8 + 1;
        r3 = r14.readCorner2(r11, r11);
        r3 = (byte) r3;
        r10[r8] = r3;
        r5 = r5 + -2;
        r3 = r7 + 2;
        r7 = r6;
        r6 = r5;
        r5 = r3;
        r3 = r1;
        goto L_0x002e;
    L_0x005d:
        r6 = r11 + 4;
        if (r5 != r6) goto L_0x007c;
    L_0x0061:
        r6 = 2;
        if (r7 != r6) goto L_0x007c;
    L_0x0064:
        r6 = r11 & 7;
        if (r6 != 0) goto L_0x007c;
    L_0x0068:
        if (r2 != 0) goto L_0x007c;
    L_0x006a:
        r6 = r8 + 1;
        r2 = r14.readCorner3(r11, r11);
        r2 = (byte) r2;
        r10[r8] = r2;
        r5 = r5 + -2;
        r2 = r7 + 2;
        r7 = r6;
        r6 = r5;
        r5 = r2;
        r2 = r1;
        goto L_0x002e;
    L_0x007c:
        r6 = r11 + -2;
        if (r5 != r6) goto L_0x00f0;
    L_0x0080:
        if (r7 != 0) goto L_0x00f0;
    L_0x0082:
        r6 = r11 & 7;
        if (r6 != r9) goto L_0x00f0;
    L_0x0086:
        if (r0 != 0) goto L_0x00f0;
    L_0x0088:
        r6 = r8 + 1;
        r0 = r14.readCorner4(r11, r11);
        r0 = (byte) r0;
        r10[r8] = r0;
        r5 = r5 + -2;
        r0 = r7 + 2;
        r7 = r6;
        r6 = r5;
        r5 = r0;
        r0 = r1;
        goto L_0x002e;
    L_0x009a:
        r6 = r5;
        r5 = r7;
    L_0x009c:
        if (r8 >= r11) goto L_0x00ee;
    L_0x009e:
        if (r6 < 0) goto L_0x00ee;
    L_0x00a0:
        r7 = r14.readMappingMatrix;
        r7 = r7.get(r6, r8);
        if (r7 != 0) goto L_0x00ee;
    L_0x00a8:
        r7 = r5 + 1;
        r12 = r14.readUtah(r8, r6, r11, r11);
        r12 = (byte) r12;
        r10[r5] = r12;
    L_0x00b1:
        r8 = r8 + -2;
        r5 = r6 + 2;
        if (r8 < 0) goto L_0x00b9;
    L_0x00b7:
        if (r5 < r11) goto L_0x009a;
    L_0x00b9:
        r6 = r8 + 1;
        r5 = r5 + 3;
        r8 = r6;
        r6 = r5;
        r5 = r7;
    L_0x00c0:
        if (r8 < 0) goto L_0x00ec;
    L_0x00c2:
        if (r6 >= r11) goto L_0x00ec;
    L_0x00c4:
        r7 = r14.readMappingMatrix;
        r7 = r7.get(r6, r8);
        if (r7 != 0) goto L_0x00ec;
    L_0x00cc:
        r7 = r5 + 1;
        r12 = r14.readUtah(r8, r6, r11, r11);
        r12 = (byte) r12;
        r10[r5] = r12;
    L_0x00d5:
        r8 = r8 + 2;
        r5 = r6 + -2;
        if (r8 >= r11) goto L_0x00dd;
    L_0x00db:
        if (r5 >= 0) goto L_0x00e9;
    L_0x00dd:
        r6 = r8 + 3;
        r5 = r5 + 1;
        goto L_0x002e;
    L_0x00e3:
        return r10;
    L_0x00e4:
        r8 = r7;
        r7 = r5;
        r5 = r6;
        goto L_0x0017;
    L_0x00e9:
        r6 = r5;
        r5 = r7;
        goto L_0x00c0;
    L_0x00ec:
        r7 = r5;
        goto L_0x00d5;
    L_0x00ee:
        r7 = r5;
        goto L_0x00b1;
    L_0x00f0:
        r6 = r7;
        r13 = r5;
        r5 = r8;
        r8 = r13;
        goto L_0x009c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.BitMatrixParser.readCodewords():byte[]");
    }

    int readCorner1(int i, int i2) {
        int i3 = (readModule(i + -1, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 1, 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(i - 1, 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(2, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(3, i2 + -1, i, i2) ? i3 | 1 : i3;
    }

    int readCorner2(int i, int i2) {
        int i3 = (readModule(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(i - 1, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 4, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 3, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(1, i2 + -1, i, i2) ? i3 | 1 : i3;
    }

    int readCorner3(int i, int i2) {
        int i3 = (readModule(i + -1, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 1, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 3, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i2 - 3, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(1, i2 + -1, i, i2) ? i3 | 1 : i3;
    }

    int readCorner4(int i, int i2) {
        int i3 = (readModule(i + -3, 0, i, i2) ? 1 : 0) << 1;
        if (readModule(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(i - 1, 0, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 2, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(0, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(1, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        if (readModule(2, i2 - 1, i, i2)) {
            i3 |= 1;
        }
        i3 <<= 1;
        return readModule(3, i2 + -1, i, i2) ? i3 | 1 : i3;
    }

    boolean readModule(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        if (i < 0) {
            i5 = i + i3;
            i6 = (4 - ((i3 + 4) & 7)) + i2;
        } else {
            i6 = i2;
            i5 = i;
        }
        if (i6 < 0) {
            i6 += i4;
            i5 += 4 - ((i4 + 4) & 7);
        }
        this.readMappingMatrix.set(i6, i5);
        return this.mappingBitMatrix.get(i6, i5);
    }

    int readUtah(int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (readModule(i - 2, i2 - 2, i3, i4)) {
            i5 = 1;
        }
        i5 <<= 1;
        if (readModule(i - 2, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i - 1, i2 - 2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i - 1, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i - 1, i2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i, i2 - 2, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        if (readModule(i, i2 - 1, i3, i4)) {
            i5 |= 1;
        }
        i5 <<= 1;
        return readModule(i, i2, i3, i4) ? i5 | 1 : i5;
    }

    Version readVersion(BitMatrix bitMatrix) {
        if (this.version != null) {
            return this.version;
        }
        int height = bitMatrix.getHeight();
        return Version.getVersionForDimensions(height, height);
    }
}
