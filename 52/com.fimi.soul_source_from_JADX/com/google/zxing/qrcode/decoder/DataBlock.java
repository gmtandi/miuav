package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version.ECB;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        int i;
        int dataCodewords;
        int i2;
        ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
        ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
        int i3 = 0;
        for (ECB count : eCBlocks) {
            i3 += count.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i3];
        i3 = 0;
        for (ECB ecb : eCBlocks) {
            i = 0;
            while (i < ecb.getCount()) {
                dataCodewords = ecb.getDataCodewords();
                i2 = i3 + 1;
                dataBlockArr[i3] = new DataBlock(dataCodewords, new byte[(eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords)]);
                i++;
                i3 = i2;
            }
        }
        i = dataBlockArr[0].codewords.length;
        int length = dataBlockArr.length - 1;
        while (length >= 0 && dataBlockArr[length].codewords.length != i) {
            length--;
        }
        int i4 = length + 1;
        i -= eCBlocksForLevel.getECCodewordsPerBlock();
        int i5 = 0;
        length = 0;
        while (i5 < i) {
            i2 = length;
            length = 0;
            while (length < i3) {
                int i6 = i2 + 1;
                dataBlockArr[length].codewords[i5] = bArr[i2];
                length++;
                i2 = i6;
            }
            i5++;
            length = i2;
        }
        i2 = i4;
        while (i2 < i3) {
            i6 = length + 1;
            dataBlockArr[i2].codewords[i] = bArr[length];
            i2++;
            length = i6;
        }
        dataCodewords = dataBlockArr[0].codewords.length;
        while (i < dataCodewords) {
            i2 = 0;
            i6 = length;
            while (i2 < i3) {
                i5 = i6 + 1;
                dataBlockArr[i2].codewords[i2 < i4 ? i : i + 1] = bArr[i6];
                i2++;
                i6 = i5;
            }
            i++;
            length = i6;
        }
        return dataBlockArr;
    }

    byte[] getCodewords() {
        return this.codewords;
    }

    int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
