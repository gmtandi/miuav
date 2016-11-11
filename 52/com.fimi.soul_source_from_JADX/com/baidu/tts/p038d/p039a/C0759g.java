package com.baidu.tts.p038d.p039a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.loopj.RangeFileAsyncHttpResponseHandler;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import java.io.File;
import org.apache.http.Header;

/* renamed from: com.baidu.tts.d.a.g */
public class C0759g extends RangeFileAsyncHttpResponseHandler {
    private C0763c f4262a;

    public C0759g(File file, C0763c c0763c) {
        super(file);
        this.f4262a = c0763c;
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, File file) {
        String str = null;
        if (th != null) {
            Throwable cause = th.getCause();
            str = cause == null ? th.getMessage() : cause.getMessage();
        }
        LoggerProxy.m6515d("ModelFileResponseHandler", "onFailure statuscode=" + i + "--msg=" + str);
        this.f4262a.m6589a(C0807c.m6758a().m6761a(C0802n.MODEL_REQUEST_ERROR, i, "download failure", th));
    }

    public void onProgress(long j, long j2) {
        this.f4262a.m6588a(j, j2);
    }

    public void onSuccess(int i, Header[] headerArr, File file) {
        LoggerProxy.m6515d("ModelFileResponseHandler", "onSuccess");
        this.f4262a.m6594e();
    }
}
