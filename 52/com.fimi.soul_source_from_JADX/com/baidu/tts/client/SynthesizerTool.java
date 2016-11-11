package com.baidu.tts.client;

import com.baidu.tts.jni.EmbeddedSynthesizerEngine;
import com.baidu.tts.tools.ResourceTools;
import com.baidu.tts.tools.StringTool;

public class SynthesizerTool {
    public static boolean verifyModelFile(String str) {
        if (StringTool.isEmpty(str)) {
            return false;
        }
        try {
            return EmbeddedSynthesizerEngine.bdTTSVerifyDataFile(ResourceTools.stringToByteArrayAddNull(str)) >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
