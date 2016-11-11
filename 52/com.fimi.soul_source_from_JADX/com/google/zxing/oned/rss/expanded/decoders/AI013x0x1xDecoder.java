package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int dateSize = 16;
    private static final int headerSize = 8;
    private static final int weightSize = 20;
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    private void encodeCompressedDate(StringBuffer stringBuffer, int i) {
        int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(i, dateSize);
        if (extractNumericValueFromBitArray != 38400) {
            stringBuffer.append('(');
            stringBuffer.append(this.dateCode);
            stringBuffer.append(')');
            int i2 = extractNumericValueFromBitArray % 32;
            extractNumericValueFromBitArray /= 32;
            int i3 = (extractNumericValueFromBitArray % 12) + 1;
            extractNumericValueFromBitArray /= 12;
            if (extractNumericValueFromBitArray / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(extractNumericValueFromBitArray);
            if (i3 / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i3);
            if (i2 / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i2);
        }
    }

    protected void addWeightCode(StringBuffer stringBuffer, int i) {
        int i2 = i / 100000;
        stringBuffer.append('(');
        stringBuffer.append(this.firstAIdigits);
        stringBuffer.append(i2);
        stringBuffer.append(')');
    }

    protected int checkWeight(int i) {
        return i % 100000;
    }

    public String parseInformation() {
        if (this.information.size != 84) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuffer stringBuffer = new StringBuffer();
        encodeCompressedGtin(stringBuffer, headerSize);
        encodeCompressedWeight(stringBuffer, 48, weightSize);
        encodeCompressedDate(stringBuffer, 68);
        return stringBuffer.toString();
    }
}
