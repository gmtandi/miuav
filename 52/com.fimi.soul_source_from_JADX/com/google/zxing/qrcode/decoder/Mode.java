package com.google.zxing.qrcode.decoder;

import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public final class Mode {
    public static final Mode ALPHANUMERIC;
    public static final Mode BYTE;
    public static final Mode ECI;
    public static final Mode FNC1_FIRST_POSITION;
    public static final Mode FNC1_SECOND_POSITION;
    public static final Mode KANJI;
    public static final Mode NUMERIC;
    public static final Mode STRUCTURED_APPEND;
    public static final Mode TERMINATOR;
    private final int bits;
    private final int[] characterCountBitsForVersions;
    private final String name;

    static {
        TERMINATOR = new Mode(new int[]{0, 0, 0}, 0, "TERMINATOR");
        NUMERIC = new Mode(new int[]{10, 12, 14}, 1, "NUMERIC");
        ALPHANUMERIC = new Mode(new int[]{9, 11, 13}, 2, "ALPHANUMERIC");
        STRUCTURED_APPEND = new Mode(new int[]{0, 0, 0}, 3, "STRUCTURED_APPEND");
        BYTE = new Mode(new int[]{8, 16, 16}, 4, "BYTE");
        ECI = new Mode(null, 7, "ECI");
        KANJI = new Mode(new int[]{8, 10, 12}, 8, "KANJI");
        FNC1_FIRST_POSITION = new Mode(null, 5, "FNC1_FIRST_POSITION");
        FNC1_SECOND_POSITION = new Mode(null, 9, "FNC1_SECOND_POSITION");
    }

    private Mode(int[] iArr, int i, String str) {
        this.characterCountBitsForVersions = iArr;
        this.bits = i;
        this.name = str;
    }

    public static Mode forBits(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return TERMINATOR;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return NUMERIC;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return ALPHANUMERIC;
            case Type.BYTE /*3*/:
                return STRUCTURED_APPEND;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return BYTE;
            case Type.INT /*5*/:
                return FNC1_FIRST_POSITION;
            case Type.LONG /*7*/:
                return ECI;
            case Type.DOUBLE /*8*/:
                return KANJI;
            case Type.ARRAY /*9*/:
                return FNC1_SECOND_POSITION;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getBits() {
        return this.bits;
    }

    public int getCharacterCountBits(Version version) {
        if (this.characterCountBitsForVersions == null) {
            throw new IllegalArgumentException("Character count doesn't apply to this mode");
        }
        int versionNumber = version.getVersionNumber();
        versionNumber = versionNumber <= 9 ? 0 : versionNumber <= 26 ? 1 : 2;
        return this.characterCountBitsForVersions[versionNumber];
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
