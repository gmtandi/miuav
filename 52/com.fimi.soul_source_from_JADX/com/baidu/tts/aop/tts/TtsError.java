package com.baidu.tts.aop.tts;

import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0806b;

public class TtsError {
    private Throwable f4046a;
    private int f4047b;
    private String f4048c;
    private C0806b f4049d;

    public int getCode() {
        return this.f4047b;
    }

    public int getDetailCode() {
        return this.f4049d != null ? this.f4049d.m6756a(this) : this.f4047b;
    }

    public String getDetailMessage() {
        return this.f4049d != null ? this.f4049d.m6757b(this) : this.f4048c != null ? this.f4048c : "TtsErrorFlyweight is null";
    }

    public C0802n getErrorEnum() {
        return this.f4049d == null ? null : this.f4049d.m6755a();
    }

    public String getMessage() {
        return this.f4048c;
    }

    public Throwable getThrowable() {
        return this.f4046a;
    }

    public C0806b getTtsErrorFlyweight() {
        return this.f4049d;
    }

    public void setCode(int i) {
        this.f4047b = i;
    }

    public void setMessage(String str) {
        this.f4048c = str;
    }

    public void setThrowable(Throwable th) {
        this.f4046a = th;
    }

    public void setTtsErrorFlyweight(C0806b c0806b) {
        this.f4049d = c0806b;
    }
}
