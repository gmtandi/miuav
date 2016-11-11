package com.google.zxing.datamatrix.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version) {
        int i;
        int i2;
        int dataCodewords;
        int i3;
        int i4;
        ECBlocks eCBlocks = version.getECBlocks();
        ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        int i5 = 0;
        for (ECB count : eCBlocks2) {
            i5 += count.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i5];
        i5 = 0;
        for (ECB ecb : eCBlocks2) {
            i2 = 0;
            while (i2 < ecb.getCount()) {
                dataCodewords = ecb.getDataCodewords();
                i3 = i5 + 1;
                dataBlockArr[i5] = new DataBlock(dataCodewords, new byte[(eCBlocks.getECCodewords() + dataCodewords)]);
                i2++;
                i5 = i3;
            }
        }
        i2 = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        int i6 = i2 - 1;
        int i7 = 0;
        for (i4 = 0; i4 < i6; i4++) {
            i = 0;
            while (i < i5) {
                i3 = i7 + 1;
                dataBlockArr[i].codewords[i4] = bArr[i7];
                i++;
                i7 = i3;
            }
        }
        i6 = version.getVersionNumber() == 24 ? 1 : 0;
        i = i6 != 0 ? 8 : i5;
        i3 = 0;
        while (i3 < i) {
            i4 = i7 + 1;
            dataBlockArr[i3].codewords[i2 - 1] = bArr[i7];
            i3++;
            i7 = i4;
        }
        dataCodewords = dataBlockArr[0].codewords.length;
        i = i7;
        while (i2 < dataCodewords) {
            i3 = 0;
            i7 = i;
            while (i3 < i5) {
                i = (i6 == 0 || i3 <= 7) ? i2 : i2 - 1;
                i4 = i7 + 1;
                dataBlockArr[i3].codewords[i] = bArr[i7];
                i3++;
                i7 = i4;
            }
            i2++;
            i = i7;
        }
        if (i == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    byte[] getCodewords() {
        return this.codewords;
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
