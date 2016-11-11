package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask {
    private static final DataMask[] DATA_MASKS;

    /* renamed from: com.google.zxing.qrcode.decoder.DataMask.1 */
    class C21041 {
    }

    class DataMask000 extends DataMask {
        private DataMask000() {
            super(null);
        }

        DataMask000(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    }

    class DataMask001 extends DataMask {
        private DataMask001() {
            super(null);
        }

        DataMask001(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return (i & 1) == 0;
        }
    }

    class DataMask010 extends DataMask {
        private DataMask010() {
            super(null);
        }

        DataMask010(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return i2 % 3 == 0;
        }
    }

    class DataMask011 extends DataMask {
        private DataMask011() {
            super(null);
        }

        DataMask011(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    }

    class DataMask100 extends DataMask {
        private DataMask100() {
            super(null);
        }

        DataMask100(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return (((i >>> 1) + (i2 / 3)) & 1) == 0;
        }
    }

    class DataMask101 extends DataMask {
        private DataMask101() {
            super(null);
        }

        DataMask101(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            int i3 = i * i2;
            return (i3 % 3) + (i3 & 1) == 0;
        }
    }

    class DataMask110 extends DataMask {
        private DataMask110() {
            super(null);
        }

        DataMask110(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            int i3 = i * i2;
            return (((i3 % 3) + (i3 & 1)) & 1) == 0;
        }
    }

    class DataMask111 extends DataMask {
        private DataMask111() {
            super(null);
        }

        DataMask111(C21041 c21041) {
            this();
        }

        boolean isMasked(int i, int i2) {
            return ((((i + i2) & 1) + ((i * i2) % 3)) & 1) == 0;
        }
    }

    static {
        DATA_MASKS = new DataMask[]{new DataMask000(null), new DataMask001(null), new DataMask010(null), new DataMask011(null), new DataMask100(null), new DataMask101(null), new DataMask110(null), new DataMask111(null)};
    }

    private DataMask() {
    }

    DataMask(C21041 c21041) {
        this();
    }

    static DataMask forReference(int i) {
        if (i >= 0 && i <= 7) {
            return DATA_MASKS[i];
        }
        throw new IllegalArgumentException();
    }

    abstract boolean isMasked(int i, int i2);

    final void unmaskBitMatrix(BitMatrix bitMatrix, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (isMasked(i2, i3)) {
                    bitMatrix.flip(i3, i2);
                }
            }
        }
    }
}
