package com.baidu.tts.aop.tts;

import android.content.Context;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.p025i.C0673b;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0800m;

public interface ITts extends C0673b {
    AuthInfo auth(C0800m c0800m);

    int freeCustomResource(C0825e c0825e);

    C0800m getMode();

    TtsListener getTtsListener();

    C0831j getTtsParams();

    int loadCustomResource(C0825e c0825e);

    int loadEnglishModel(C0826f c0826f);

    int loadModel(C0827g c0827g);

    int setAudioStreamType(int i);

    void setContext(Context context);

    void setMode(C0800m c0800m);

    int setParam(C0794g c0794g, String str);

    int setStereoVolume(float f, float f2);

    void setTtsListener(TtsListener ttsListener);

    void speak(C0829i c0829i);

    void synthesize(C0829i c0829i);
}
