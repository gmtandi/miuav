package com.baidu.tts.client;

import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.tools.ResourceTools;

public class SpeechSynthesizeBag {
    private String f4213a;
    private String f4214b;

    public String getText() {
        return this.f4213a;
    }

    public String getUtteranceId() {
        return this.f4214b;
    }

    public int setText(String str) {
        C0802n isTextValid = ResourceTools.isTextValid(str);
        if (isTextValid != null) {
            return isTextValid.m6752b();
        }
        this.f4213a = str;
        return 0;
    }

    public void setUtteranceId(String str) {
        this.f4214b = str;
    }
}
