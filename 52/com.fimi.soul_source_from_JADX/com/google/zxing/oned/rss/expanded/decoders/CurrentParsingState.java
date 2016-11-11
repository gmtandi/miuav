package com.google.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState {
    private static final int ALPHA = 2;
    private static final int ISO_IEC_646 = 4;
    private static final int NUMERIC = 1;
    private int encoding;
    int position;

    CurrentParsingState() {
        this.position = 0;
        this.encoding = NUMERIC;
    }

    boolean isAlpha() {
        return this.encoding == ALPHA;
    }

    boolean isIsoIec646() {
        return this.encoding == ISO_IEC_646;
    }

    boolean isNumeric() {
        return this.encoding == NUMERIC;
    }

    void setAlpha() {
        this.encoding = ALPHA;
    }

    void setIsoIec646() {
        this.encoding = ISO_IEC_646;
    }

    void setNumeric() {
        this.encoding = NUMERIC;
    }
}
