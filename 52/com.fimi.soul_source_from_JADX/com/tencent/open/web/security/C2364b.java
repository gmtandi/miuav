package com.tencent.open.web.security;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.open.C2336a;
import com.tencent.open.C2336a.C2292b;
import com.tencent.open.C2336a.C2318a;
import com.tencent.open.p133a.C2333f;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.tencent.open.web.security.b */
public class C2364b extends C2336a {
    private static final String f12133b;

    static {
        f12133b = C2333f.f12014d + ".SecureJs";
    }

    public void m13852a(String str, String str2, List<String> list, C2318a c2318a) {
        C2333f.m13757c(f12133b, "-->getResult, objectName: " + str + " | methodName: " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), C1142e.f5201a));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C2292b c2292b = (C2292b) this.a.get(str);
        if (c2292b != null) {
            C2333f.m13754b(f12133b, "-->handler != null");
            c2292b.call(str2, list, c2318a);
            return;
        }
        C2333f.m13754b(f12133b, "-->handler == null");
        if (c2318a != null) {
            c2318a.m13689a();
        }
    }

    public boolean m13853a(WebView webView, String str) {
        C2333f.m13754b(f12133b, "-->canHandleUrl---url = " + str);
        if (str == null) {
            return false;
        }
        if (!Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 7) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        String str4 = (String) arrayList.get(4);
        String str5 = (String) arrayList.get(5);
        C2333f.m13757c(f12133b, "-->canHandleUrl, objectName: " + str2 + " | methodName: " + str3 + " | snStr: " + str4);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            m13852a(str2, str3, arrayList.subList(6, arrayList.size() - 1), new C2365c(webView, Long.parseLong(str4), str, str5));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
