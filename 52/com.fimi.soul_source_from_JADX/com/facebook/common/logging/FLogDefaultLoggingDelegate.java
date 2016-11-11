package com.facebook.common.logging;

import android.util.Log;
import com.fimi.soul.media.player.FimiMediaMeta;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.p122a.p123a.C2915a;

public class FLogDefaultLoggingDelegate implements LoggingDelegate {
    public static final FLogDefaultLoggingDelegate sInstance;
    private String mApplicationTag;
    private int mMinimumLoggingLevel;

    static {
        sInstance = new FLogDefaultLoggingDelegate();
    }

    private FLogDefaultLoggingDelegate() {
        this.mApplicationTag = FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        this.mMinimumLoggingLevel = 5;
    }

    public static FLogDefaultLoggingDelegate getInstance() {
        return sInstance;
    }

    private static String getMsg(String str, Throwable th) {
        return str + '\n' + getStackTraceString(th);
    }

    private static String getStackTraceString(Throwable th) {
        if (th == null) {
            return C2915a.f14760f;
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private String prefixTag(String str) {
        return this.mApplicationTag != null ? this.mApplicationTag + ":" + str : str;
    }

    private void println(int i, String str, String str2) {
        Log.println(i, prefixTag(str), str2);
    }

    private void println(int i, String str, String str2, Throwable th) {
        Log.println(i, prefixTag(str), getMsg(str2, th));
    }

    public void m7651d(String str, String str2) {
        println(3, str, str2);
    }

    public void m7652d(String str, String str2, Throwable th) {
        println(3, str, str2, th);
    }

    public void m7653e(String str, String str2) {
        println(6, str, str2);
    }

    public void m7654e(String str, String str2, Throwable th) {
        println(6, str, str2, th);
    }

    public int getMinimumLoggingLevel() {
        return this.mMinimumLoggingLevel;
    }

    public void m7655i(String str, String str2) {
        println(4, str, str2);
    }

    public void m7656i(String str, String str2, Throwable th) {
        println(4, str, str2, th);
    }

    public boolean isLoggable(int i) {
        return this.mMinimumLoggingLevel <= i;
    }

    public void log(int i, String str, String str2) {
        println(i, str, str2);
    }

    public void setApplicationTag(String str) {
        this.mApplicationTag = str;
    }

    public void setMinimumLoggingLevel(int i) {
        this.mMinimumLoggingLevel = i;
    }

    public void m7657v(String str, String str2) {
        println(2, str, str2);
    }

    public void m7658v(String str, String str2, Throwable th) {
        println(2, str, str2, th);
    }

    public void m7659w(String str, String str2) {
        println(5, str, str2);
    }

    public void m7660w(String str, String str2, Throwable th) {
        println(5, str, str2, th);
    }

    public void wtf(String str, String str2) {
        println(6, str, str2);
    }

    public void wtf(String str, String str2, Throwable th) {
        println(6, str, str2, th);
    }
}
