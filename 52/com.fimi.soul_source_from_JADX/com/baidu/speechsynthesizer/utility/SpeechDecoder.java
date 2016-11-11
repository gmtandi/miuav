package com.baidu.speechsynthesizer.utility;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;

public class SpeechDecoder {
    static {
        LoggerProxy.m6515d("SpeechDecoder", "before load gnustl_shared");
        System.loadLibrary("gnustl_shared");
        LoggerProxy.m6515d("SpeechDecoder", "before load BDSpeechDecoder_V1");
        System.loadLibrary("BDSpeechDecoder_V1");
        LoggerProxy.m6515d("SpeechDecoder", "after load BDSpeechDecoder_V1");
    }

    public native int decode(byte[] bArr, int i, short[] sArr, int[] iArr, int i2, int i3);
}
