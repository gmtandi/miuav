package com.baidu.tts.aop.ttslistener;

import com.baidu.tts.p034l.C0828h;

public interface TtsListener {
    void onError(C0828h c0828h);

    void onPlayFinished(C0828h c0828h);

    void onPlayProgressUpdate(C0828h c0828h);

    void onPlayStart(C0828h c0828h);

    void onSynthesizeDataArrived(C0828h c0828h);

    void onSynthesizeFinished(C0828h c0828h);

    void onSynthesizeStart(C0828h c0828h);
}
