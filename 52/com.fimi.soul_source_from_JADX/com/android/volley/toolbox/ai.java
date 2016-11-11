package com.android.volley.toolbox;

import com.android.volley.C0566n;
import com.android.volley.C0570r;
import com.android.volley.C0604z;
import com.android.volley.aa;
import com.android.volley.ab;
import java.io.UnsupportedEncodingException;

public class ai extends C0570r<String> {
    private ab<String> f3598a;

    public ai(int i, String str, ab<String> abVar, aa aaVar) {
        super(i, str, aaVar);
        this.f3598a = abVar;
    }

    public ai(String str, ab<String> abVar, aa aaVar) {
        this(0, str, abVar, aaVar);
    }

    protected C0604z<String> m5170a(C0566n c0566n) {
        Object str;
        try {
            str = new String(c0566n.f3542b, C0587m.m5227a(c0566n.f3543c));
        } catch (UnsupportedEncodingException e) {
            str = new String(c0566n.f3542b);
        }
        return C0604z.m5293a(str, C0587m.m5226a(c0566n));
    }

    protected /* synthetic */ void m5171b(Object obj) {
        m5172d((String) obj);
    }

    protected void m5172d(String str) {
        if (this.f3598a != null) {
            this.f3598a.m5048a(str);
        }
    }

    protected void m5173e() {
        super.m5114e();
        this.f3598a = null;
    }
}
