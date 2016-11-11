package com.google.zxing.common;

import com.fimi.soul.media.player.IMediaPlayer;

public abstract class ECI {
    private final int value;

    ECI(int i) {
        this.value = i;
    }

    public static ECI getECIByValue(int i) {
        if (i >= 0 && i <= 999999) {
            return i < IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR ? CharacterSetECI.getCharacterSetECIByValue(i) : null;
        } else {
            throw new IllegalArgumentException(new StringBuffer().append("Bad ECI value: ").append(i).toString());
        }
    }

    public int getValue() {
        return this.value;
    }
}
