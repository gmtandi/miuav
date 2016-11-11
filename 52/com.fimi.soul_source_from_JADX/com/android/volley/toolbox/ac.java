package com.android.volley.toolbox;

import com.android.volley.C0566n;
import com.android.volley.C0570r;
import com.android.volley.C0604z;
import com.android.volley.aa;
import com.android.volley.ab;
import com.android.volley.ah;
import java.io.UnsupportedEncodingException;

public abstract class ac<T> extends C0570r<T> {
    protected static final String f3584a = "utf-8";
    private static final String f3585b;
    private ab<T> f3586c;
    private final String f3587d;

    static {
        f3585b = String.format("application/json; charset=%s", new Object[]{f3584a});
    }

    public ac(int i, String str, String str2, ab<T> abVar, aa aaVar) {
        super(i, str, aaVar);
        this.f3586c = abVar;
        this.f3587d = str2;
    }

    public ac(String str, String str2, ab<T> abVar, aa aaVar) {
        this(-1, str, str2, abVar, aaVar);
    }

    protected abstract C0604z<T> m5146a(C0566n c0566n);

    protected void m5147b(T t) {
        if (this.f3586c != null) {
            this.f3586c.m5048a(t);
        }
    }

    protected void m5148e() {
        super.m5114e();
        this.f3586c = null;
    }

    public String m5149q() {
        return m5151u();
    }

    public byte[] m5150r() {
        return m5152v();
    }

    public String m5151u() {
        return f3585b;
    }

    public byte[] m5152v() {
        byte[] bArr = null;
        try {
            if (this.f3587d != null) {
                bArr = this.f3587d.getBytes(f3584a);
            }
        } catch (UnsupportedEncodingException e) {
            ah.m5061d("Unsupported Encoding while trying to get the bytes of %s using %s", this.f3587d, f3584a);
        }
        return bArr;
    }
}
