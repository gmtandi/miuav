package com.baidu.tts.p041e;

/* renamed from: com.baidu.tts.e.o */
public enum C0803o {
    TTS_SERVER("http://tts.baidu.com/text2audio"),
    MODEL_SERVER("http://tts.baidu.com/bos/story.php?");
    
    private final String f4551c;

    private C0803o(String str) {
        this.f4551c = str;
    }

    public String m6754a() {
        return this.f4551c;
    }
}
