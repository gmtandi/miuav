package com.google.zxing.common;

import com.fimi.soul.media.player.IMediaPlayer;
import java.util.Hashtable;

public final class CharacterSetECI extends ECI {
    private static Hashtable NAME_TO_ECI;
    private static Hashtable VALUE_TO_ECI;
    private final String encodingName;

    private CharacterSetECI(int i, String str) {
        super(i);
        this.encodingName = str;
    }

    private static void addCharacterSet(int i, String str) {
        CharacterSetECI characterSetECI = new CharacterSetECI(i, str);
        VALUE_TO_ECI.put(new Integer(i), characterSetECI);
        NAME_TO_ECI.put(str, characterSetECI);
    }

    private static void addCharacterSet(int i, String[] strArr) {
        int i2 = 0;
        CharacterSetECI characterSetECI = new CharacterSetECI(i, strArr[0]);
        VALUE_TO_ECI.put(new Integer(i), characterSetECI);
        while (i2 < strArr.length) {
            NAME_TO_ECI.put(strArr[i2], characterSetECI);
            i2++;
        }
    }

    public static CharacterSetECI getCharacterSetECIByName(String str) {
        if (NAME_TO_ECI == null) {
            initialize();
        }
        return (CharacterSetECI) NAME_TO_ECI.get(str);
    }

    public static CharacterSetECI getCharacterSetECIByValue(int i) {
        if (VALUE_TO_ECI == null) {
            initialize();
        }
        if (i >= 0 && i < IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR) {
            return (CharacterSetECI) VALUE_TO_ECI.get(new Integer(i));
        }
        throw new IllegalArgumentException(new StringBuffer().append("Bad ECI value: ").append(i).toString());
    }

    private static void initialize() {
        VALUE_TO_ECI = new Hashtable(29);
        NAME_TO_ECI = new Hashtable(29);
        addCharacterSet(0, "Cp437");
        addCharacterSet(1, new String[]{"ISO8859_1", "ISO-8859-1"});
        addCharacterSet(2, "Cp437");
        addCharacterSet(3, new String[]{"ISO8859_1", "ISO-8859-1"});
        addCharacterSet(4, "ISO8859_2");
        addCharacterSet(5, "ISO8859_3");
        addCharacterSet(6, "ISO8859_4");
        addCharacterSet(7, "ISO8859_5");
        addCharacterSet(8, "ISO8859_6");
        addCharacterSet(9, "ISO8859_7");
        addCharacterSet(10, "ISO8859_8");
        addCharacterSet(11, "ISO8859_9");
        addCharacterSet(12, "ISO8859_10");
        addCharacterSet(13, "ISO8859_11");
        addCharacterSet(15, "ISO8859_13");
        addCharacterSet(16, "ISO8859_14");
        addCharacterSet(17, "ISO8859_15");
        addCharacterSet(18, "ISO8859_16");
        addCharacterSet(20, new String[]{StringUtils.SHIFT_JIS, "Shift_JIS"});
    }

    public String getEncodingName() {
        return this.encodingName;
    }
}
