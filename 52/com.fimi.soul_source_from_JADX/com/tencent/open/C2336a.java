package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.open.p133a.C2333f;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.open.a */
public class C2336a {
    private static final String f12019b;
    protected HashMap<String, C2292b> f12020a;

    /* renamed from: com.tencent.open.a.b */
    public class C2292b {
        public void call(String str, List<String> list, C2318a c2318a) {
            Method method = null;
            for (Method method2 : getClass().getDeclaredMethods()) {
                if (method2.getName().equals(str) && method2.getParameterTypes().length == list.size()) {
                    method = method2;
                    break;
                }
            }
            if (method != null) {
                try {
                    Object invoke;
                    switch (list.size()) {
                        case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                            invoke = method.invoke(this, new Object[0]);
                            break;
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            invoke = method.invoke(this, new Object[]{list.get(0)});
                            break;
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1)});
                            break;
                        case Type.BYTE /*3*/:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2)});
                            break;
                        case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3)});
                            break;
                        case Type.INT /*5*/:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)});
                            break;
                        default:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)});
                            break;
                    }
                    Class returnType = method.getReturnType();
                    C2333f.m13754b(C2336a.f12019b, "-->call, result: " + invoke + " | ReturnType: " + returnType.getName());
                    if ("void".equals(returnType.getName()) || returnType == Void.class) {
                        if (c2318a != null) {
                            c2318a.m13690a(null);
                        }
                    } else if (c2318a != null && customCallback()) {
                        c2318a.m13691a(invoke != null ? invoke.toString() : null);
                    }
                } catch (Throwable e) {
                    C2333f.m13755b(C2336a.f12019b, "-->handler call mehtod ex. targetMethod: " + method, e);
                    if (c2318a != null) {
                        c2318a.m13689a();
                    }
                }
            } else if (c2318a != null) {
                c2318a.m13689a();
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    /* renamed from: com.tencent.open.a.a */
    public class C2318a {
        protected WeakReference<WebView> f11956a;
        protected long f11957b;
        protected String f11958c;

        public C2318a(WebView webView, long j, String str) {
            this.f11956a = new WeakReference(webView);
            this.f11957b = j;
            this.f11958c = str;
        }

        public void m13689a() {
            WebView webView = (WebView) this.f11956a.get();
            if (webView != null) {
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.f11957b + ",{'r':1,'result':'no such method'})");
            }
        }

        public void m13690a(Object obj) {
            WebView webView = (WebView) this.f11956a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj instanceof String) {
                    str = "'" + ((String) obj).replace("\\", "\\\\").replace("'", "\\'") + "'";
                } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    str = obj.toString();
                }
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.f11957b + ",{'r':0,'result':" + str + "});");
            }
        }

        public void m13691a(String str) {
            WebView webView = (WebView) this.f11956a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    static {
        f12019b = C2333f.f12014d + ".JsBridge";
    }

    public C2336a() {
        this.f12020a = new HashMap();
    }

    public void m13769a(C2292b c2292b, String str) {
        this.f12020a.put(str, c2292b);
    }

    public void m13770a(String str, String str2, List<String> list, C2318a c2318a) {
        C2333f.m13754b(f12019b, "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), C1142e.f5201a));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C2292b c2292b = (C2292b) this.f12020a.get(str);
        if (c2292b != null) {
            C2333f.m13754b(f12019b, "call----");
            c2292b.call(str2, list, c2318a);
            return;
        }
        C2333f.m13754b(f12019b, "not call----objName NOT FIND");
        if (c2318a != null) {
            c2318a.m13689a();
        }
    }

    public boolean m13771a(WebView webView, String str) {
        C2333f.m13754b(f12019b, "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List subList = arrayList.subList(4, arrayList.size() - 1);
        C2318a c2318a = new C2318a(webView, 4, str);
        webView.getUrl();
        m13770a(str2, str3, subList, c2318a);
        return true;
    }
}
