package com.baidu.tts.p038d.p040b;

import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.database.C0782a;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.StringTool;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.baidu.tts.d.b.c */
public class C0772c {
    private String f4291a;
    private Map<String, String> f4292b;

    public C0772c(String str) {
        this.f4291a = str;
        this.f4292b = new HashMap();
    }

    public String m6644a() {
        return DataTool.getMapValue(this.f4292b, C0794g.ABS_PATH.m6742b());
    }

    public void m6645a(ModelFileBags modelFileBags, C0782a c0782a) {
        c0782a.m6724a(modelFileBags);
        m6646a(c0782a);
    }

    public boolean m6646a(C0782a c0782a) {
        this.f4292b = c0782a.m6729d(this.f4291a);
        if (DataTool.isMapEmpty(this.f4292b)) {
            return false;
        }
        String str = (String) this.f4292b.get(C0794g.ABS_PATH.m6742b());
        if (StringTool.isEmpty(str)) {
            c0782a.m6726b(this.f4291a);
            return false;
        }
        C0774e.m6668a().m6674c(str).m6637c(this.f4291a);
        return true;
    }

    public String m6647b() {
        return DataTool.getMapValue(this.f4292b, C0794g.LENGTH.m6742b());
    }

    public String m6648c() {
        return DataTool.getMapValue(this.f4292b, C0794g.MD5.m6742b());
    }
}
