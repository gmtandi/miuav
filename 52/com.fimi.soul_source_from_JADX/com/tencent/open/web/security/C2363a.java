package com.tencent.open.web.security;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.tencent.open.p133a.C2333f;

/* renamed from: com.tencent.open.web.security.a */
public class C2363a extends InputConnectionWrapper {
    public static String f12130a;
    public static boolean f12131b;
    public static boolean f12132c;

    static {
        f12131b = false;
        f12132c = false;
    }

    public C2363a(InputConnection inputConnection, boolean z) {
        super(inputConnection, z);
    }

    public boolean commitText(CharSequence charSequence, int i) {
        f12132c = true;
        f12130a = charSequence.toString();
        C2333f.m13754b("CaptureInputConnection", "-->commitText: " + charSequence.toString());
        return super.commitText(charSequence, i);
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            C2333f.m13757c("CaptureInputConnection", "sendKeyEvent");
            f12130a = String.valueOf((char) keyEvent.getUnicodeChar());
            f12132c = true;
            C2333f.m13757c("CaptureInputConnection", "s: " + f12130a);
        }
        C2333f.m13754b("CaptureInputConnection", "-->sendKeyEvent: " + f12130a);
        return super.sendKeyEvent(keyEvent);
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        f12132c = true;
        f12130a = charSequence.toString();
        C2333f.m13754b("CaptureInputConnection", "-->setComposingText: " + charSequence.toString());
        return super.setComposingText(charSequence, i);
    }
}
