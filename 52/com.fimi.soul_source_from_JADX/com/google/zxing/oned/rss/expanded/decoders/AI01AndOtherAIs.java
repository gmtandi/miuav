package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder {
    private static final int HEADER_SIZE = 4;

    AI01AndOtherAIs(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(01)");
        int length = stringBuffer.length();
        stringBuffer.append(this.generalDecoder.extractNumericValueFromBitArray(HEADER_SIZE, HEADER_SIZE));
        encodeCompressedGtinWithoutAI(stringBuffer, 8, length);
        return this.generalDecoder.decodeAllCodes(stringBuffer, 48);
    }
}
