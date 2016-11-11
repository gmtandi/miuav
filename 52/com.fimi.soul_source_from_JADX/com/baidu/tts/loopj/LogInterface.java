package com.baidu.tts.loopj;

public interface LogInterface {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static final int WTF = 8;

    void m6888d(String str, String str2);

    void m6889d(String str, String str2, Throwable th);

    void m6890e(String str, String str2);

    void m6891e(String str, String str2, Throwable th);

    int getLoggingLevel();

    void m6892i(String str, String str2);

    void m6893i(String str, String str2, Throwable th);

    boolean isLoggingEnabled();

    void setLoggingEnabled(boolean z);

    void setLoggingLevel(int i);

    boolean shouldLog(int i);

    void m6894v(String str, String str2);

    void m6895v(String str, String str2, Throwable th);

    void m6896w(String str, String str2);

    void m6897w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}
