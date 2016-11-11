package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AnyAIDecoder extends AbstractExpandedDecoder {
    private static final int HEADER_SIZE = 5;

    AnyAIDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        return this.generalDecoder.decodeAllCodes(new StringBuffer(), HEADER_SIZE);
    }
}
