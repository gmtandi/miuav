package com.baidu.tts.client;

import com.baidu.tts.p041e.C0800m;

public enum TtsMode {
    ONLINE(C0800m.ONLINE),
    MIX(C0800m.MIX);
    
    private final C0800m f4218a;

    private TtsMode(C0800m c0800m) {
        this.f4218a = c0800m;
    }

    public String getDescription() {
        return this.f4218a.m6750b();
    }

    public int getMode() {
        return this.f4218a.m6749a();
    }

    public C0800m getTtsEnum() {
        return this.f4218a;
    }
}
