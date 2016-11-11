package com.facebook.common.logging;

public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LoggingDelegate sHandler;

    static {
        sHandler = FLogDefaultLoggingDelegate.getInstance();
    }

    public static void m7577d(Class<?> cls, String str) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7641d(getTag(cls), str);
        }
    }

    public static void m7578d(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7641d(getTag(cls), formatString(str, obj));
        }
    }

    public static void m7579d(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7641d(tag, formatString(str, objArr));
        }
    }

    public static void m7580d(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            loggingDelegate.m7641d(tag, formatString(str, objArr));
        }
    }

    public static void m7581d(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7641d(tag, formatString(str, objArr));
        }
    }

    public static void m7582d(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7642d(getTag(cls), str, th);
        }
    }

    public static void m7583d(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7641d(getTag(cls), formatString(str, objArr));
        }
    }

    public static void m7584d(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7642d(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void m7585d(String str, String str2) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7641d(str, str2);
        }
    }

    public static void m7586d(String str, String str2, Object obj) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7641d(str, formatString(str2, obj));
        }
    }

    public static void m7587d(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7641d(str, formatString(str2, objArr));
        }
    }

    public static void m7588d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            loggingDelegate.m7641d(str, formatString(str2, objArr));
        }
    }

    public static void m7589d(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(DEBUG)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7641d(str, formatString(str2, objArr));
        }
    }

    public static void m7590d(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(DEBUG)) {
            sHandler.m7642d(str, str2, th);
        }
    }

    public static void m7591d(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(DEBUG)) {
            m7585d(str, formatString(str2, objArr));
        }
    }

    public static void m7592d(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(DEBUG)) {
            m7590d(str, formatString(str2, objArr), th);
        }
    }

    public static void m7593e(Class<?> cls, String str) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(getTag(cls), str);
        }
    }

    public static void m7594e(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7644e(getTag(cls), str, th);
        }
    }

    public static void m7595e(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(getTag(cls), formatString(str, objArr));
        }
    }

    public static void m7596e(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7644e(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void m7597e(String str, String str2) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(str, str2);
        }
    }

    public static void m7598e(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7644e(str, str2, th);
        }
    }

    public static void m7599e(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(str, formatString(str2, objArr));
        }
    }

    public static void m7600e(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7644e(str, formatString(str2, objArr), th);
        }
    }

    private static String formatString(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.getMinimumLoggingLevel();
    }

    private static String getTag(Class<?> cls) {
        return cls.getSimpleName();
    }

    public static void m7601i(Class<?> cls, String str) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(getTag(cls), str);
        }
    }

    public static void m7602i(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(getTag(cls), formatString(str, obj));
        }
    }

    public static void m7603i(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7645i(tag, formatString(str, objArr));
        }
    }

    public static void m7604i(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            loggingDelegate.m7645i(tag, formatString(str, objArr));
        }
    }

    public static void m7605i(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7645i(tag, formatString(str, objArr));
        }
    }

    public static void m7606i(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7646i(getTag(cls), str, th);
        }
    }

    public static void m7607i(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(getTag(cls), formatString(str, objArr));
        }
    }

    public static void m7608i(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (isLoggable(INFO)) {
            sHandler.m7646i(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void m7609i(String str, String str2) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(str, str2);
        }
    }

    public static void m7610i(String str, String str2, Object obj) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(str, formatString(str2, obj));
        }
    }

    public static void m7611i(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7645i(str, formatString(str2, objArr));
        }
    }

    public static void m7612i(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            loggingDelegate.m7645i(str, formatString(str2, objArr));
        }
    }

    public static void m7613i(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(INFO)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7645i(str, formatString(str2, objArr));
        }
    }

    public static void m7614i(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7646i(str, str2, th);
        }
    }

    public static void m7615i(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7645i(str, formatString(str2, objArr));
        }
    }

    public static void m7616i(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(INFO)) {
            sHandler.m7646i(str, formatString(str2, objArr), th);
        }
    }

    public static boolean isLoggable(int i) {
        return sHandler.isLoggable(i);
    }

    public static void setLoggingDelegate(LoggingDelegate loggingDelegate) {
        if (loggingDelegate == null) {
            throw new IllegalArgumentException();
        }
        sHandler = loggingDelegate;
    }

    public static void setMinimumLoggingLevel(int i) {
        sHandler.setMinimumLoggingLevel(i);
    }

    public static void m7617v(Class<?> cls, String str) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(getTag(cls), str);
        }
    }

    public static void m7618v(Class<?> cls, String str, Object obj) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(getTag(cls), formatString(str, obj));
        }
    }

    public static void m7619v(Class<?> cls, String str, Object obj, Object obj2) {
        if (sHandler.isLoggable(VERBOSE)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7647v(tag, formatString(str, objArr));
        }
    }

    public static void m7620v(Class<?> cls, String str, Object obj, Object obj2, Object obj3) {
        if (isLoggable(VERBOSE)) {
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            m7617v((Class) cls, formatString(str, objArr));
        }
    }

    public static void m7621v(Class<?> cls, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(VERBOSE)) {
            LoggingDelegate loggingDelegate = sHandler;
            String tag = getTag(cls);
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7647v(tag, formatString(str, objArr));
        }
    }

    public static void m7622v(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7648v(getTag(cls), str, th);
        }
    }

    public static void m7623v(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(getTag(cls), formatString(str, objArr));
        }
    }

    public static void m7624v(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7648v(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void m7625v(String str, String str2) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(str, str2);
        }
    }

    public static void m7626v(String str, String str2, Object obj) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(str, formatString(str2, obj));
        }
    }

    public static void m7627v(String str, String str2, Object obj, Object obj2) {
        if (sHandler.isLoggable(VERBOSE)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[VERBOSE];
            objArr[0] = obj;
            objArr[1] = obj2;
            loggingDelegate.m7647v(str, formatString(str2, objArr));
        }
    }

    public static void m7628v(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (sHandler.isLoggable(VERBOSE)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[DEBUG];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            loggingDelegate.m7647v(str, formatString(str2, objArr));
        }
    }

    public static void m7629v(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sHandler.isLoggable(VERBOSE)) {
            LoggingDelegate loggingDelegate = sHandler;
            Object[] objArr = new Object[INFO];
            objArr[0] = obj;
            objArr[1] = obj2;
            objArr[VERBOSE] = obj3;
            objArr[DEBUG] = obj4;
            loggingDelegate.m7647v(str, formatString(str2, objArr));
        }
    }

    public static void m7630v(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7648v(str, str2, th);
        }
    }

    public static void m7631v(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7647v(str, formatString(str2, objArr));
        }
    }

    public static void m7632v(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(VERBOSE)) {
            sHandler.m7648v(str, formatString(str2, objArr), th);
        }
    }

    public static void m7633w(Class<?> cls, String str) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7649w(getTag(cls), str);
        }
    }

    public static void m7634w(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7650w(getTag(cls), str, th);
        }
    }

    public static void m7635w(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7649w(getTag(cls), formatString(str, objArr));
        }
    }

    public static void m7636w(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (isLoggable(WARN)) {
            m7634w((Class) cls, formatString(str, objArr), th);
        }
    }

    public static void m7637w(String str, String str2) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7649w(str, str2);
        }
    }

    public static void m7638w(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7650w(str, str2, th);
        }
    }

    public static void m7639w(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7649w(str, formatString(str2, objArr));
        }
    }

    public static void m7640w(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(WARN)) {
            sHandler.m7650w(str, formatString(str2, objArr), th);
        }
    }

    public static void wtf(Class<?> cls, String str) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(getTag(cls), str);
        }
    }

    public static void wtf(Class<?> cls, String str, Throwable th) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(getTag(cls), str, th);
        }
    }

    public static void wtf(Class<?> cls, String str, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(getTag(cls), formatString(str, objArr));
        }
    }

    public static void wtf(Class<?> cls, Throwable th, String str, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(getTag(cls), formatString(str, objArr), th);
        }
    }

    public static void wtf(String str, String str2) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.m7643e(str, str2);
        }
    }

    public static void wtf(String str, String str2, Throwable th) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(str, str2, th);
        }
    }

    public static void wtf(String str, String str2, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(str, formatString(str2, objArr));
        }
    }

    public static void wtf(String str, Throwable th, String str2, Object... objArr) {
        if (sHandler.isLoggable(ERROR)) {
            sHandler.wtf(str, formatString(str2, objArr), th);
        }
    }
}
