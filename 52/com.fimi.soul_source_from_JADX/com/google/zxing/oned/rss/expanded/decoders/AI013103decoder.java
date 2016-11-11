package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI013103decoder extends AI013x0xDecoder {
    AI013103decoder(BitArray bitArray) {
        super(bitArray);
    }

    protected void addWeightCode(StringBuffer stringBuffer, int i) {
        stringBuffer.append("(3103)");
    }

    protected int checkWeight(int i) {
        return i;
    }
}
