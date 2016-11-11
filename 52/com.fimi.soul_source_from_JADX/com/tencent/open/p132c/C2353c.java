package com.tencent.open.p132c;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.web.security.C2363a;
import com.tencent.open.web.security.SecureJsInterface;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.open.c.c */
public class C2353c extends C2352b {
    public static boolean f12064a;
    private KeyEvent f12065b;
    private C2363a f12066c;

    public C2353c(Context context) {
        super(context);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        C2333f.m13754b("SecureWebView", "-->dispatchKeyEvent, is device support: " + f12064a);
        if (!f12064a) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() != 0) {
            return super.dispatchKeyEvent(keyEvent);
        }
        switch (keyEvent.getKeyCode()) {
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return super.dispatchKeyEvent(keyEvent);
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                return super.dispatchKeyEvent(keyEvent);
            case UsbId.ARDUINO_UNO_R3 /*67*/:
                C2363a.f12131b = true;
                return super.dispatchKeyEvent(keyEvent);
            default:
                if (keyEvent.getUnicodeChar() == 0) {
                    return super.dispatchKeyEvent(keyEvent);
                }
                if (SecureJsInterface.isPWDEdit) {
                    int unicodeChar = keyEvent.getUnicodeChar();
                    if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= Opcodes.LUSHR)) {
                        this.f12065b = new KeyEvent(0, 17);
                        return super.dispatchKeyEvent(this.f12065b);
                    }
                }
                return super.dispatchKeyEvent(keyEvent);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        C2333f.m13757c("SecureWebView", "-->create input connection, is edit: " + SecureJsInterface.isPWDEdit);
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C2333f.m13754b("SecureWebView", "-->onCreateInputConnection, inputConn is " + onCreateInputConnection);
        if (onCreateInputConnection != null) {
            f12064a = true;
            this.f12066c = new C2363a(super.onCreateInputConnection(editorInfo), false);
            return this.f12066c;
        }
        f12064a = false;
        return onCreateInputConnection;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        C2333f.m13754b("SecureWebView", "-->onKeyDown, is device support: " + f12064a);
        if (!f12064a) {
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        switch (keyEvent.getKeyCode()) {
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return super.onKeyDown(i, keyEvent);
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                return super.onKeyDown(i, keyEvent);
            case UsbId.ARDUINO_UNO_R3 /*67*/:
                C2363a.f12131b = true;
                return super.onKeyDown(i, keyEvent);
            default:
                if (keyEvent.getUnicodeChar() == 0) {
                    return super.onKeyDown(i, keyEvent);
                }
                if (SecureJsInterface.isPWDEdit) {
                    int unicodeChar = keyEvent.getUnicodeChar();
                    if ((unicodeChar >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= Opcodes.LUSHR)) {
                        this.f12065b = new KeyEvent(0, 17);
                        return super.onKeyDown(this.f12065b.getKeyCode(), this.f12065b);
                    }
                }
                return super.onKeyDown(i, keyEvent);
        }
    }
}
