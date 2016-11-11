package com.baidu.tts.client;

import android.content.Context;
import android.os.Bundle;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.p022a.p024b.C0672a;
import com.baidu.tts.p041e.C0786b;
import com.baidu.tts.p041e.C0790c;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0795h;
import com.baidu.tts.p041e.C0797j;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.StringTool;
import java.util.List;

public class SpeechSynthesizer {
    public static final String AUDIO_BITRATE_AMR_12K65;
    public static final String AUDIO_BITRATE_AMR_14K25;
    public static final String AUDIO_BITRATE_AMR_15K85;
    public static final String AUDIO_BITRATE_AMR_18K25;
    public static final String AUDIO_BITRATE_AMR_19K85;
    public static final String AUDIO_BITRATE_AMR_23K05;
    public static final String AUDIO_BITRATE_AMR_23K85;
    public static final String AUDIO_BITRATE_AMR_6K6;
    public static final String AUDIO_BITRATE_AMR_8K85;
    public static final String AUDIO_BITRATE_BV_16K;
    public static final String AUDIO_BITRATE_OPUS_16K;
    public static final String AUDIO_BITRATE_OPUS_18K;
    public static final String AUDIO_BITRATE_OPUS_20K;
    public static final String AUDIO_BITRATE_OPUS_24K;
    public static final String AUDIO_BITRATE_OPUS_32K;
    public static final String AUDIO_BITRATE_OPUS_8K;
    public static final String AUDIO_ENCODE_AMR;
    public static final String AUDIO_ENCODE_BV;
    public static final String AUDIO_ENCODE_OPUS;
    public static final int ERROR_APP_ID_IS_INVALID;
    public static final int ERROR_LIST_IS_TOO_LONG;
    public static final int ERROR_QUEUE_IS_FULL;
    public static final int ERROR_TEXT_ENCODE_IS_WRONG;
    public static final int ERROR_TEXT_IS_EMPTY;
    public static final int ERROR_TEXT_IS_TOO_LONG;
    public static final String LANGUAGE_ZH;
    public static final int MAX_LIST_SIZE = 100;
    public static final int MAX_QUEUE_SIZE = 1000;
    public static final String MIX_MODE_DEFAULT;
    public static final String MIX_MODE_HIGH_SPEED_NETWORK;
    public static final String MIX_MODE_HIGH_SPEED_SYNTHESIZE;
    public static final String MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI;
    public static final String PARAM_AUDIO_ENCODE;
    public static final String PARAM_AUDIO_RATE;
    public static final String PARAM_CUSTOM_SYNTH;
    public static final String PARAM_LANGUAGE;
    public static final String PARAM_MIX_MODE;
    public static final String PARAM_OPEN_XML;
    public static final String PARAM_PITCH;
    public static final String PARAM_PRODUCT_ID;
    public static final String PARAM_SPEAKER;
    public static final String PARAM_SPEED;
    public static final String PARAM_TEXT_ENCODE;
    public static final String PARAM_TTS_LICENCE_FILE;
    public static final String PARAM_TTS_SPEECH_MODEL_FILE;
    public static final String PARAM_TTS_TEXT_MODEL_FILE;
    public static final String PARAM_VOCODER_OPTIM_LEVEL;
    public static final String PARAM_VOLUME;
    public static final String TEXT_ENCODE_BIG5;
    public static final String TEXT_ENCODE_GBK;
    public static final String TEXT_ENCODE_UTF8;
    private static volatile SpeechSynthesizer f4215a;
    private C0672a f4216b;

    static {
        ERROR_QUEUE_IS_FULL = C0802n.TTS_QUEUE_IS_FULL.m6752b();
        ERROR_LIST_IS_TOO_LONG = C0802n.TTS_LIST_IS_TOO_LONG.m6752b();
        ERROR_TEXT_IS_EMPTY = C0802n.TEXT_IS_EMPTY.m6752b();
        ERROR_TEXT_IS_TOO_LONG = C0802n.TEXT_IS_TOO_LONG.m6752b();
        ERROR_TEXT_ENCODE_IS_WRONG = C0802n.TEXT_ENCODE_IS_WRONG.m6752b();
        ERROR_APP_ID_IS_INVALID = C0802n.TTS_APP_ID_IS_INVALID.m6752b();
        PARAM_SPEED = C0794g.m6740a(C0794g.SPEED);
        PARAM_PITCH = C0794g.m6740a(C0794g.PITCH);
        PARAM_VOLUME = C0794g.m6740a(C0794g.VOLUME);
        PARAM_TTS_TEXT_MODEL_FILE = C0794g.m6740a(C0794g.TEXT_DAT_PATH);
        PARAM_TTS_SPEECH_MODEL_FILE = C0794g.m6740a(C0794g.SPEECH_DAT_PATH);
        PARAM_TTS_LICENCE_FILE = C0794g.m6740a(C0794g.TTS_LICENSE_FILE_PATH);
        PARAM_VOCODER_OPTIM_LEVEL = C0794g.m6740a(C0794g.TTS_VOCODER_OPTIMIZATION);
        PARAM_CUSTOM_SYNTH = C0794g.m6740a(C0794g.CUSTOM_SYNTH);
        PARAM_OPEN_XML = C0794g.m6740a(C0794g.OPEN_XML);
        PARAM_PRODUCT_ID = C0794g.m6740a(C0794g.PRODUCT_ID);
        PARAM_LANGUAGE = C0794g.m6740a(C0794g.LANGUAGE);
        PARAM_TEXT_ENCODE = C0794g.m6740a(C0794g.TEXT_ENCODE);
        PARAM_AUDIO_ENCODE = C0794g.m6740a(C0794g.AUDIO_ENCODE);
        PARAM_AUDIO_RATE = C0794g.m6740a(C0794g.BITRATE);
        PARAM_SPEAKER = C0794g.m6740a(C0794g.SPEAKER);
        PARAM_MIX_MODE = C0794g.m6740a(C0794g.MIX_MODE);
        MIX_MODE_DEFAULT = C0797j.DEFAULT.name();
        MIX_MODE_HIGH_SPEED_NETWORK = C0797j.HIGH_SPEED_NETWORK.name();
        MIX_MODE_HIGH_SPEED_SYNTHESIZE = C0797j.HIGH_SPEED_SYNTHESIZE.name();
        MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI = C0797j.HIGH_SPEED_SYNTHESIZE_WIFI.name();
        LANGUAGE_ZH = C0795h.ZH.m6744a();
        TEXT_ENCODE_GBK = C0791d.GB18030.m6738b();
        TEXT_ENCODE_BIG5 = C0791d.BIG5.m6738b();
        TEXT_ENCODE_UTF8 = C0791d.UTF8.m6738b();
        AUDIO_ENCODE_BV = C0786b.BV.m6733a();
        AUDIO_ENCODE_AMR = C0786b.AMR.m6733a();
        AUDIO_ENCODE_OPUS = C0786b.OPUS.m6733a();
        AUDIO_BITRATE_BV_16K = C0790c.BV_16K.m6735a();
        AUDIO_BITRATE_AMR_6K6 = C0790c.AMR_6K6.m6735a();
        AUDIO_BITRATE_AMR_8K85 = C0790c.AMR_8K85.m6735a();
        AUDIO_BITRATE_AMR_12K65 = C0790c.AMR_12K65.m6735a();
        AUDIO_BITRATE_AMR_14K25 = C0790c.AMR_14K25.m6735a();
        AUDIO_BITRATE_AMR_15K85 = C0790c.AMR_15K85.m6735a();
        AUDIO_BITRATE_AMR_18K25 = C0790c.AMR_18K25.m6735a();
        AUDIO_BITRATE_AMR_19K85 = C0790c.AMR_19K85.m6735a();
        AUDIO_BITRATE_AMR_23K05 = C0790c.AMR_23K05.m6735a();
        AUDIO_BITRATE_AMR_23K85 = C0790c.AMR_23K85.m6735a();
        AUDIO_BITRATE_OPUS_8K = C0790c.OPUS_8K.m6735a();
        AUDIO_BITRATE_OPUS_16K = C0790c.OPUS_16K.m6735a();
        AUDIO_BITRATE_OPUS_18K = C0790c.OPUS_18K.m6735a();
        AUDIO_BITRATE_OPUS_20K = C0790c.OPUS_20K.m6735a();
        AUDIO_BITRATE_OPUS_24K = C0790c.OPUS_24K.m6735a();
        AUDIO_BITRATE_OPUS_32K = C0790c.OPUS_32K.m6735a();
        f4215a = null;
    }

    private SpeechSynthesizer() {
        this.f4216b = new C0672a();
    }

    public static SpeechSynthesizer getInstance() {
        if (f4215a == null) {
            synchronized (SpeechSynthesizer.class) {
                if (f4215a == null) {
                    f4215a = new SpeechSynthesizer();
                }
            }
        }
        return f4215a;
    }

    public AuthInfo auth(TtsMode ttsMode) {
        return this.f4216b.m5995b(ttsMode);
    }

    public int batchSpeak(List<SpeechSynthesizeBag> list) {
        return DataTool.isListEmpty(list) ? C0802n.TTS_PARAMETER_INVALID.m6752b() : this.f4216b.m5987a((List) list);
    }

    public int freeCustomResource() {
        return this.f4216b.m6000f();
    }

    public synchronized int initTts(TtsMode ttsMode) {
        TtsError a;
        a = this.f4216b.m5988a(ttsMode);
        return a == null ? ERROR_TEXT_IS_TOO_LONG : a.getDetailCode();
    }

    public String libVersion() {
        return this.f4216b.m5989a();
    }

    public int loadCustomResource(String str) {
        return this.f4216b.m5984a(str);
    }

    public int loadEnglishModel(String str, String str2) {
        return this.f4216b.m5997c(str, str2);
    }

    public int loadModel(String str, String str2) {
        return this.f4216b.m5993b(str, str2);
    }

    public synchronized int pause() {
        return this.f4216b.m5992b();
    }

    public synchronized int release() {
        this.f4216b.m5999e();
        f4215a = null;
        return ERROR_TEXT_IS_TOO_LONG;
    }

    public synchronized int resume() {
        return this.f4216b.m5996c();
    }

    public int setApiKey(String str, String str2) {
        setParam(C0794g.API_KEY.name(), str);
        setParam(C0794g.SECRET_KEY.name(), str2);
        return ERROR_TEXT_IS_TOO_LONG;
    }

    public int setAppId(String str) {
        if (!StringTool.isAllNumber(str)) {
            return ERROR_APP_ID_IS_INVALID;
        }
        setParam(C0794g.APP_CODE.name(), str);
        return ERROR_TEXT_IS_TOO_LONG;
    }

    public int setAudioStreamType(int i) {
        return this.f4216b.m5983a(i);
    }

    public void setContext(Context context) {
        this.f4216b.m5990a(context);
    }

    public int setParam(String str, String str2) {
        return this.f4216b.m5985a(str, str2);
    }

    public void setSpeechSynthesizerListener(SpeechSynthesizerListener speechSynthesizerListener) {
        this.f4216b.m5991a(speechSynthesizerListener);
    }

    public int setStereoVolume(float f, float f2) {
        return this.f4216b.m5982a(f, f2);
    }

    public int speak(SpeechSynthesizeBag speechSynthesizeBag) {
        try {
            return speak(speechSynthesizeBag.getText(), speechSynthesizeBag.getUtteranceId());
        } catch (Exception e) {
            return C0802n.TTS_PARAMETER_INVALID.m6752b();
        }
    }

    public int speak(String str) {
        return speak(str, null);
    }

    public int speak(String str, String str2) {
        return speak(str, str2, null);
    }

    public int speak(String str, String str2, Bundle bundle) {
        return this.f4216b.m5986a(str, str2, bundle);
    }

    public synchronized int stop() {
        return this.f4216b.m5998d();
    }

    public int synthesize(SpeechSynthesizeBag speechSynthesizeBag) {
        try {
            return synthesize(speechSynthesizeBag.getText(), speechSynthesizeBag.getUtteranceId());
        } catch (Exception e) {
            return C0802n.TTS_PARAMETER_INVALID.m6752b();
        }
    }

    public int synthesize(String str) {
        return synthesize(str, null);
    }

    public int synthesize(String str, String str2) {
        return synthesize(str, str2, null);
    }

    public int synthesize(String str, String str2, Bundle bundle) {
        return this.f4216b.m5994b(str, str2, bundle);
    }
}
