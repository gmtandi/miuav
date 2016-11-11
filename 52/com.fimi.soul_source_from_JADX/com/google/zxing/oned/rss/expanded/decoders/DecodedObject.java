package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject {
    protected final int newPosition;

    DecodedObject(int i) {
        this.newPosition = i;
    }

    int getNewPosition() {
        return this.newPosition;
    }
}
