package com.baidu.tts.loopj;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.util.Log;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class LogHandler implements LogInterface {
    boolean mLoggingEnabled;
    int mLoggingLevel;

    public LogHandler() {
        this.mLoggingEnabled = true;
        this.mLoggingLevel = 2;
    }

    @TargetApi(8)
    private void checkedWtf(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void m6898d(String str, String str2) {
        log(2, str, str2);
    }

    public void m6899d(String str, String str2, Throwable th) {
        logWithThrowable(3, str, str2, th);
    }

    public void m6900e(String str, String str2) {
        log(6, str, str2);
    }

    public void m6901e(String str, String str2, Throwable th) {
        logWithThrowable(6, str, str2, th);
    }

    public int getLoggingLevel() {
        return this.mLoggingLevel;
    }

    public void m6902i(String str, String str2) {
        log(4, str, str2);
    }

    public void m6903i(String str, String str2, Throwable th) {
        logWithThrowable(4, str, str2, th);
    }

    public boolean isLoggingEnabled() {
        return this.mLoggingEnabled;
    }

    public void log(int i, String str, String str2) {
        logWithThrowable(i, str, str2, null);
    }

    public void logWithThrowable(int i, String str, String str2, Throwable th) {
        if (isLoggingEnabled() && shouldLog(i)) {
            switch (i) {
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    Log.v(str, str2, th);
                case Type.BYTE /*3*/:
                    Log.d(str, str2, th);
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    Log.i(str, str2, th);
                case Type.INT /*5*/:
                    Log.w(str, str2, th);
                case Type.FLOAT /*6*/:
                    Log.e(str, str2, th);
                case Type.DOUBLE /*8*/:
                    if (Integer.valueOf(VERSION.SDK).intValue() > 8) {
                        checkedWtf(str, str2, th);
                    } else {
                        Log.e(str, str2, th);
                    }
                default:
            }
        }
    }

    public void setLoggingEnabled(boolean z) {
        this.mLoggingEnabled = z;
    }

    public void setLoggingLevel(int i) {
        this.mLoggingLevel = i;
    }

    public boolean shouldLog(int i) {
        return i >= this.mLoggingLevel;
    }

    public void m6904v(String str, String str2) {
        log(2, str, str2);
    }

    public void m6905v(String str, String str2, Throwable th) {
        logWithThrowable(2, str, str2, th);
    }

    public void m6906w(String str, String str2) {
        log(5, str, str2);
    }

    public void m6907w(String str, String str2, Throwable th) {
        logWithThrowable(5, str, str2, th);
    }

    public void wtf(String str, String str2) {
        log(8, str, str2);
    }

    public void wtf(String str, String str2, Throwable th) {
        logWithThrowable(8, str, str2, th);
    }
}
