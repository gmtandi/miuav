package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.p133a.C2333f;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.open.b */
public abstract class C2293b extends Dialog {
    protected C2336a jsBridge;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient mChromeClient;

    /* renamed from: com.tencent.open.b.1 */
    class C23371 extends WebChromeClient {
        final /* synthetic */ C2293b f12021a;

        C23371(C2293b c2293b) {
            this.f12021a = c2293b;
        }

        public void onConsoleMessage(String str, int i, String str2) {
            C2333f.m13757c("WebConsole", str + " -- From 222 line " + i + " of " + str2);
            if (VERSION.SDK_INT == 7) {
                this.f12021a.onConsoleMessage(str);
            }
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (consoleMessage == null) {
                return false;
            }
            C2333f.m13757c("WebConsole", consoleMessage.message() + " -- From  111 line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            if (VERSION.SDK_INT > 7) {
                this.f12021a.onConsoleMessage(consoleMessage == null ? C2915a.f14760f : consoleMessage.message());
            }
            return true;
        }
    }

    public C2293b(Context context) {
        super(context);
        this.mChromeClient = new C23371(this);
    }

    public C2293b(Context context, int i) {
        super(context, i);
        this.mChromeClient = new C23371(this);
    }

    protected abstract void onConsoleMessage(String str);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.jsBridge = new C2336a();
    }
}
