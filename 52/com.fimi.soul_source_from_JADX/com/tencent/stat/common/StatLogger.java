package com.tencent.stat.common;

import android.util.Log;

public final class StatLogger {
    private String f12304a;
    private boolean f12305b;
    private int f12306c;

    public StatLogger() {
        this.f12304a = "default";
        this.f12305b = true;
        this.f12306c = 2;
    }

    public StatLogger(String str) {
        this.f12304a = "default";
        this.f12305b = true;
        this.f12306c = 2;
        this.f12304a = str;
    }

    private String m13975a() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(getClass().getName())) {
                return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
            }
        }
        return null;
    }

    public void m13976d(Object obj) {
        if (isDebugEnable()) {
            debug(obj);
        }
    }

    public void debug(Object obj) {
        if (this.f12306c <= 3) {
            String a = m13975a();
            Log.d(this.f12304a, a == null ? obj.toString() : a + " - " + obj);
        }
    }

    public void m13977e(Exception exception) {
        if (isDebugEnable()) {
            error(exception);
        }
    }

    public void m13978e(Object obj) {
        if (isDebugEnable()) {
            error(obj);
        }
    }

    public void error(Exception exception) {
        if (this.f12306c <= 6) {
            StringBuffer stringBuffer = new StringBuffer();
            String a = m13975a();
            StackTraceElement[] stackTrace = exception.getStackTrace();
            if (a != null) {
                stringBuffer.append(a + " - " + exception + "\r\n");
            } else {
                stringBuffer.append(exception + "\r\n");
            }
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    if (stackTraceElement != null) {
                        stringBuffer.append("[ " + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " ]\r\n");
                    }
                }
            }
            Log.e(this.f12304a, stringBuffer.toString());
        }
    }

    public void error(Object obj) {
        if (this.f12306c <= 6) {
            String a = m13975a();
            Log.e(this.f12304a, a == null ? obj.toString() : a + " - " + obj);
        }
    }

    public int getLogLevel() {
        return this.f12306c;
    }

    public void m13979i(Object obj) {
        if (isDebugEnable()) {
            info(obj);
        }
    }

    public void info(Object obj) {
        if (this.f12306c <= 4) {
            String a = m13975a();
            Log.i(this.f12304a, a == null ? obj.toString() : a + " - " + obj);
        }
    }

    public boolean isDebugEnable() {
        return this.f12305b;
    }

    public void setDebugEnable(boolean z) {
        this.f12305b = z;
    }

    public void setLogLevel(int i) {
        this.f12306c = i;
    }

    public void setTag(String str) {
        this.f12304a = str;
    }

    public void m13980v(Object obj) {
        if (isDebugEnable()) {
            verbose(obj);
        }
    }

    public void verbose(Object obj) {
        if (this.f12306c <= 2) {
            String a = m13975a();
            Log.v(this.f12304a, a == null ? obj.toString() : a + " - " + obj);
        }
    }

    public void m13981w(Object obj) {
        if (isDebugEnable()) {
            warn(obj);
        }
    }

    public void warn(Object obj) {
        if (this.f12306c <= 5) {
            String a = m13975a();
            Log.w(this.f12304a, a == null ? obj.toString() : a + " - " + obj);
        }
    }
}
