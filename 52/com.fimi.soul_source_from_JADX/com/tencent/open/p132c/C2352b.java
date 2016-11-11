package com.tencent.open.p132c;

import android.content.Context;
import android.webkit.WebView;
import java.lang.reflect.Method;

/* renamed from: com.tencent.open.c.b */
public class C2352b extends WebView {
    public C2352b(Context context) {
        super(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Method method = getSettings().getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(this, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e) {
        }
    }
}
