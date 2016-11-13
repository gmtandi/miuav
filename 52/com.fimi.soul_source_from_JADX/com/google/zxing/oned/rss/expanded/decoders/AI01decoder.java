package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01decoder extends AbstractExpandedDecoder {
    protected static final int gtinSize = 40;

    AI01decoder(BitArray bitArray) {
        super(bitArray);
    }

    private static void appendCheckDigit(StringBuffer stringBuffer, int i) {
        int charAt;
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            charAt = stringBuffer.charAt(i3 + i) - 48;
            if ((i3 & 1) == 0) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        charAt = 10 - (i2 % 10);
        if (charAt == 10) {
            charAt = 0;
        }
        stringBuffer.append(charAt);
    }

    protected void encodeCompressedGtin(StringBuffer stringBuffer, int i) {
        stringBuffer.append("(01)");
        int length = stringBuffer.length();
        stringBuffer.append('9');
        encodeCompressedGtinWithoutAI(stringBuffer, i, length);
    }

    protected void encodeCompressedGtinWithoutAI(StringBuffer stringBuffer, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray((i3 * 10) + i, 10);
            if (extractNumericValueFromBitArray / 100 == 0) {
                stringBuffer.append('0');
            }
            if (extractNumericValueFromBitArray / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(extractNumericValueFromBitArray);
        }
        appendCheckDigit(stringBuffer, i2);
    }
}
