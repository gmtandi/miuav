package com.fimi.soul.drone.p107c;

/* renamed from: com.fimi.soul.drone.c.c */
/* synthetic */ class C1471c {
    static final /* synthetic */ int[] f7016a;

    static {
        f7016a = new int[C1472d.values().length];
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_UNINIT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_IDLE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_GOT_STX.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_GOT_LENGTH.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_GOT_MSGID.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_GOT_PAYLOAD.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f7016a[C1472d.MILINK_PARSE_STATE_GOT_CRC1.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
