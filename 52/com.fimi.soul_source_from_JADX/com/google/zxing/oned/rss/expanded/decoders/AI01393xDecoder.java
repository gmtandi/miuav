package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01393xDecoder extends AI01decoder {
    private static final int firstThreeDigitsSize = 10;
    private static final int headerSize = 8;
    private static final int lastDigitSize = 2;

    AI01393xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        if (this.information.size < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuffer stringBuffer = new StringBuffer();
        encodeCompressedGtin(stringBuffer, headerSize);
        int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(48, lastDigitSize);
        stringBuffer.append("(393");
        stringBuffer.append(extractNumericValueFromBitArray);
        stringBuffer.append(')');
        extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(50, firstThreeDigitsSize);
        if (extractNumericValueFromBitArray / 100 == 0) {
            stringBuffer.append('0');
        }
        if (extractNumericValueFromBitArray / firstThreeDigitsSize == 0) {
            stringBuffer.append('0');
        }
        stringBuffer.append(extractNumericValueFromBitArray);
        stringBuffer.append(this.generalDecoder.decodeGeneralPurposeField(60, null).getNewString());
        return stringBuffer.toString();
    }
}
