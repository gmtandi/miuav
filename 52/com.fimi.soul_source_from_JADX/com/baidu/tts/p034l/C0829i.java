package com.baidu.tts.p034l;

import android.text.TextUtils;
import com.baidu.tts.p033m.C0719a;
import com.baidu.tts.p041e.C0796i;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;

/* renamed from: com.baidu.tts.l.i */
public class C0829i extends C0719a<C0829i> {
    private String f4612a;
    private String f4613b;
    private String f4614c;
    private C0796i f4615d;
    private String f4616e;

    public C0829i(String str, String str2) {
        this.f4614c = Constants.VIA_RESULT_SUCCESS;
        m6873b(str);
        m6877d(str2);
    }

    public void m6869a() {
        if (!TextUtils.isEmpty(this.f4616e)) {
            this.f4612a = this.f4616e + this.f4612a;
        }
    }

    public void m6870a(C0796i c0796i) {
        this.f4615d = c0796i;
    }

    public void m6871a(String str) {
        this.f4616e = str;
    }

    public String m6872b() {
        return this.f4616e;
    }

    public void m6873b(String str) {
        this.f4612a = str;
    }

    public String m6874c() {
        return this.f4612a;
    }

    public void m6875c(String str) {
        this.f4613b = str;
    }

    public String m6876d() {
        return this.f4613b;
    }

    public void m6877d(String str) {
        if (str == null) {
            str = Constants.VIA_RESULT_SUCCESS;
        }
        this.f4614c = str;
    }

    public byte[] m6878e() {
        byte[] bArr = null;
        try {
            bArr = this.f4612a.getBytes(this.f4613b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public String m6879f() {
        return this.f4614c;
    }

    public C0796i m6880g() {
        return this.f4615d;
    }
}
