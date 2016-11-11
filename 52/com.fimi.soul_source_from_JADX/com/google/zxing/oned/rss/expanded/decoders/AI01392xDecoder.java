package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01392xDecoder extends AI01decoder {
    private static final int headerSize = 8;
    private static final int lastDigitSize = 2;

    AI01392xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        if (this.information.size < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuffer stringBuffer = new StringBuffer();
        encodeCompressedGtin(stringBuffer, headerSize);
        int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(48, lastDigitSize);
        stringBuffer.append("(392");
        stringBuffer.append(extractNumericValueFromBitArray);
        stringBuffer.append(')');
        stringBuffer.append(this.generalDecoder.decodeGeneralPurposeField(50, null).getNewString());
        return stringBuffer.toString();
    }
}
