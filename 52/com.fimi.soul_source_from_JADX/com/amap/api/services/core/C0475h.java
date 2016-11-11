package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.help.Tip;
import java.util.ArrayList;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.h */
public class C0475h extends C0449r<C0476i, ArrayList<Tip>> {
    public C0475h(Context context, C0476i c0476i) {
        super(context, c0476i);
    }

    protected ArrayList<Tip> m4777a(String str) {
        ArrayList<Tip> arrayList = null;
        try {
            arrayList = C0477j.m4827o(new JSONObject(str));
        } catch (Throwable e) {
            C0471d.m4762a(e, "InputtipsHandler", "paseJSON");
        }
        return arrayList;
    }

    protected String a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&keywords=").append(m4455c(((C0476i) this.a).f3140a));
        String str = ((C0476i) this.a).f3141b;
        if (!C0477j.m4816h(str)) {
            stringBuffer.append("&city=").append(m4455c(str));
        }
        stringBuffer.append("&key=").append(C0496w.m4874f(this.d));
        stringBuffer.append("&language=").append(C0470c.m4756b());
        return stringBuffer.toString();
    }

    protected /* synthetic */ Object m4778b(String str) {
        return m4777a(str);
    }

    public String m4779b() {
        return C0470c.m4755a() + "/assistant/inputtips?";
    }
}
