package com.google.zxing.oned.rss.expanded.decoders;

import com.fimi.soul.module.setting.newhand.C1873o;
import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected void addWeightCode(StringBuffer stringBuffer, int i) {
        if (i < C1873o.ak) {
            stringBuffer.append("(3202)");
        } else {
            stringBuffer.append("(3203)");
        }
    }

    protected int checkWeight(int i) {
        return i < C1873o.ak ? i : i - 10000;
    }
}
