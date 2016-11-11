package com.baidu.tts.p049k;

import android.content.Context;
import com.baidu.tts.client.model.BasicHandler;
import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.DownloadHandler;
import com.baidu.tts.client.model.LibEngineParams;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.database.C0782a;
import com.baidu.tts.p038d.C0775b;
import com.baidu.tts.p038d.C0778d;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p049k.p050a.C0821h;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.MD5;
import java.io.File;
import java.util.Map;
import java.util.Set;

/* renamed from: com.baidu.tts.k.a */
public class C0822a {
    private Context f4589a;
    private C0782a f4590b;
    private C0821h f4591c;
    private C0778d f4592d;

    public C0822a(Context context) {
        this.f4589a = context;
        m6817f();
    }

    private void m6817f() {
        this.f4590b = new C0782a(this);
        this.f4591c = new C0821h(this);
        this.f4592d = new C0778d();
        this.f4592d.m6701a(this);
        this.f4592d.m6700a();
    }

    public BasicHandler<ModelBags> m6818a(Conditions conditions) {
        return this.f4591c.m6811a(conditions);
    }

    public BasicHandler<ModelFileBags> m6819a(Set<String> set) {
        return this.f4591c.m6812a((Set) set);
    }

    public DownloadHandler m6820a(C0775b c0775b) {
        return this.f4592d.m6698a(c0775b);
    }

    public LibEngineParams m6821a() {
        return this.f4591c.m6813a();
    }

    public String m6822a(String str, String str2) {
        return this.f4590b.m6719a(str, str2);
    }

    public boolean m6823a(String str) {
        Map d = this.f4590b.m6729d(str);
        if (DataTool.isMapEmpty(d)) {
            return false;
        }
        String str2 = (String) d.get(C0794g.ABS_PATH.m6742b());
        String str3 = (String) d.get(C0794g.LENGTH.m6742b());
        String str4 = (String) d.get(C0794g.MD5.m6742b());
        File file = new File(str2);
        return file.exists() ? file.length() != Long.parseLong(str3) ? false : MD5.getInstance().getBigFileMd5(file).equalsIgnoreCase(str4) : false;
    }

    public BasicHandler<ModelBags> m6824b() {
        return this.f4591c.m6814b();
    }

    public BasicHandler<ModelBags> m6825b(Conditions conditions) {
        return this.f4591c.m6815b(conditions);
    }

    public BasicHandler<ModelFileBags> m6826b(Set<String> set) {
        return this.f4591c.m6816b((Set) set);
    }

    public boolean m6827b(String str) {
        Map e = this.f4590b.m6730e(str);
        if (DataTool.isMapEmpty(e)) {
            return false;
        }
        return m6823a((String) e.get(C0794g.TEXT_DATA_ID.m6742b())) && m6823a((String) e.get(C0794g.SPEECH_DATA_ID.m6742b()));
    }

    public void m6828c() {
        this.f4592d.m6702b();
    }

    public Context m6829d() {
        return this.f4589a;
    }

    public C0782a m6830e() {
        return this.f4590b;
    }
}
