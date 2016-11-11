package com.baidu.tts.client.model;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p038d.C0769a;
import com.baidu.tts.p038d.C0775b;
import com.baidu.tts.p038d.p040b.C0770a;
import com.baidu.tts.p038d.p040b.C0773d;
import java.util.concurrent.Future;

public class DownloadHandler {
    public static final int DOWNLOAD_SUCCESS = 0;
    private C0775b f4229a;
    private Future<C0769a> f4230b;
    private TtsError f4231c;
    private C0770a f4232d;
    private volatile boolean f4233e;

    public DownloadHandler() {
        this.f4232d = C0770a.m6614a();
        this.f4233e = false;
    }

    private OnDownloadListener m6550a() {
        return this.f4229a.m6682c();
    }

    private void m6551a(String str) {
        OnDownloadListener a = m6550a();
        if (a != null) {
            synchronized (this) {
                if (!this.f4233e) {
                    a.onStart(str);
                }
            }
        }
    }

    private void m6552a(String str, int i) {
        OnDownloadListener a = m6550a();
        if (a != null) {
            synchronized (this) {
                if (!this.f4233e) {
                    a.onFinish(str, i);
                    this.f4229a.m6679a(null);
                }
            }
        }
    }

    private void m6553a(String str, long j, long j2) {
        OnDownloadListener a = m6550a();
        if (a != null) {
            synchronized (this) {
                if (!this.f4233e) {
                    a.onProgress(str, j, j2);
                }
            }
        }
    }

    public C0775b getDownloadParams() {
        return this.f4229a;
    }

    public int getErrorCode() {
        return getErrorCode(this.f4231c);
    }

    public int getErrorCode(TtsError ttsError) {
        return ttsError != null ? ttsError.getDetailCode() : 0;
    }

    public String getErrorMessage() {
        return getErrorMessage(this.f4231c);
    }

    public String getErrorMessage(TtsError ttsError) {
        return ttsError != null ? ttsError.getDetailMessage() : null;
    }

    public String getModelId() {
        return this.f4229a.m6678a();
    }

    public TtsError getTtsError() {
        return this.f4231c;
    }

    public synchronized void reset() {
        LoggerProxy.m6515d("DownloadHandler", "reset");
        this.f4233e = false;
    }

    public void reset(C0775b c0775b) {
        setDownloadParams(c0775b);
        reset();
    }

    public void setCheckFuture(Future<C0769a> future) {
        this.f4230b = future;
    }

    public void setDownloadParams(C0775b c0775b) {
        this.f4229a = c0775b;
    }

    public void setTtsError(TtsError ttsError) {
        this.f4231c = ttsError;
    }

    public synchronized void stop() {
        LoggerProxy.m6515d("DownloadHandler", "stop");
        this.f4233e = true;
        if (this.f4230b != null) {
            this.f4230b.cancel(true);
            this.f4230b = null;
        }
        this.f4232d.m6616a(this);
        this.f4229a.m6679a(null);
    }

    public void updateFinish(C0773d c0773d, TtsError ttsError) {
        updateFinish(c0773d.m6665g(), ttsError);
    }

    public void updateFinish(String str, TtsError ttsError) {
        setTtsError(ttsError);
        m6552a(str, getErrorCode());
    }

    public void updateProgress(C0773d c0773d) {
        m6553a(c0773d.m6665g(), c0773d.m6666h(), c0773d.m6660c());
    }

    public void updateStart(C0773d c0773d) {
        m6551a(c0773d.m6665g());
    }
}
