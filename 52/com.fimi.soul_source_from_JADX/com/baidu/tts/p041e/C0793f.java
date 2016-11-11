package com.baidu.tts.p041e;

/* renamed from: com.baidu.tts.e.f */
public enum C0793f {
    ONLINE(0, "online engine"),
    OFFLINE(1, "offline engine"),
    MIX(2, "online and offline mix engine");
    
    private final int f4403d;
    private final String f4404e;

    private C0793f(int i, String str) {
        this.f4403d = i;
        this.f4404e = str;
    }

    public int m6739a() {
        return this.f4403d;
    }
}
