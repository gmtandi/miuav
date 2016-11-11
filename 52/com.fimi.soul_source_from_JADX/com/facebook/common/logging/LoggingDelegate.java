package com.facebook.common.logging;

public interface LoggingDelegate {
    void m7641d(String str, String str2);

    void m7642d(String str, String str2, Throwable th);

    void m7643e(String str, String str2);

    void m7644e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    void m7645i(String str, String str2);

    void m7646i(String str, String str2, Throwable th);

    boolean isLoggable(int i);

    void log(int i, String str, String str2);

    void setMinimumLoggingLevel(int i);

    void m7647v(String str, String str2);

    void m7648v(String str, String str2, Throwable th);

    void m7649w(String str, String str2);

    void m7650w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
