package com.baidu.tts.p044g.p046b;

import android.content.Context;
import com.baidu.android.common.util.CommonParam;
import com.baidu.tts.tools.FileTools;
import com.baidu.tts.tools.ResourceTools;
import java.lang.ref.WeakReference;

/* renamed from: com.baidu.tts.g.b.a */
public class C0808a {
    private WeakReference<Context> f4557a;
    private String f4558b;
    private String f4559c;

    public C0808a(WeakReference<Context> weakReference) {
        this.f4557a = weakReference;
    }

    private Context m6766c() {
        return this.f4557a == null ? null : (Context) this.f4557a.get();
    }

    public String m6767a() {
        if (this.f4558b == null) {
            this.f4558b = CommonParam.getCUID(m6766c());
        }
        return this.f4558b;
    }

    public String m6768b() {
        if (this.f4559c == null) {
            this.f4559c = FileTools.jointPathAndName(ResourceTools.getAppFilesDirPath(m6766c()), "baidu_tts_license");
        }
        return this.f4559c;
    }
}
