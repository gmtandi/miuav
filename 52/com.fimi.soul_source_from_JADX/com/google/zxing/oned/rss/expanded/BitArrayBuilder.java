package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import java.util.Vector;

final class BitArrayBuilder {
    private BitArrayBuilder() {
    }

    static BitArray buildBitArray(Vector vector) {
        int size = (vector.size() << 1) - 1;
        BitArray bitArray = new BitArray((((ExpandedPair) vector.lastElement()).getRightChar() == null ? size - 1 : size) * 12);
        int value = ((ExpandedPair) vector.elementAt(0)).getRightChar().getValue();
        size = 11;
        int i = 0;
        while (size >= 0) {
            if (((1 << size) & value) != 0) {
                bitArray.set(i);
            }
            size--;
            i++;
        }
        int i2 = i;
        for (size = 1; size < vector.size(); size++) {
            ExpandedPair expandedPair = (ExpandedPair) vector.elementAt(size);
            int value2 = expandedPair.getLeftChar().getValue();
            value = 11;
            while (value >= 0) {
                if (((1 << value) & value2) != 0) {
                    bitArray.set(i2);
                }
                value--;
                i2++;
            }
            if (expandedPair.getRightChar() != null) {
                value = expandedPair.getRightChar().getValue();
                for (i = 11; i >= 0; i--) {
                    if (((1 << i) & value) != 0) {
                        bitArray.set(i2);
                    }
                    i2++;
                }
            }
        }
        return bitArray;
    }
}
