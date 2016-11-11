package com.baidu.tts.p044g.p045a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p041e.C0802n;

/* renamed from: com.baidu.tts.g.a.b */
public class C0806b extends C0805a {
    public C0806b(C0802n c0802n) {
        super(c0802n);
    }

    public int m6756a(TtsError ttsError) {
        return this.a.m6752b();
    }

    public String m6757b(TtsError ttsError) {
        int code = ttsError.getCode();
        String message = ttsError.getMessage();
        Throwable throwable = ttsError.getThrowable();
        int b = this.a.m6752b();
        String str = "(" + b + ")" + this.a.m6753c();
        if (message != null) {
            str = str + "[(" + code + ")" + message + "]";
        } else if (code != 0) {
            str = str + "[(" + code + ")]";
        }
        if (throwable == null) {
            return str;
        }
        return str + "[(cause)" + throwable.toString() + "]";
    }
}
