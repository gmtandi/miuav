package com.baidu.tts.p038d;

import com.baidu.tts.client.model.OnDownloadListener;
import com.baidu.tts.tools.StringTool;

/* renamed from: com.baidu.tts.d.b */
public class C0775b {
    private String f4303a;
    private OnDownloadListener f4304b;

    public String m6678a() {
        return this.f4303a;
    }

    public void m6679a(OnDownloadListener onDownloadListener) {
        this.f4304b = onDownloadListener;
    }

    public void m6680a(String str) {
        this.f4303a = str;
    }

    public boolean m6681b() {
        return !StringTool.isEmpty(this.f4303a);
    }

    public OnDownloadListener m6682c() {
        return this.f4304b;
    }
}
