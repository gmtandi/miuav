package org.p122a.p123a.p167i.p168a;

import org.apache.http.impl.auth.NTLMEngineException;

/* renamed from: org.a.a.i.a.o */
class C3069o extends C3067m {
    protected byte[] f15203a;
    protected String f15204b;
    protected byte[] f15205c;
    protected int f15206d;

    C3069o(String str) {
        super(str, 2);
        this.f15203a = new byte[8];
        m17277a(this.f15203a, 24);
        this.f15206d = m17280c(20);
        if ((this.f15206d & 1) == 0) {
            throw new NTLMEngineException("NTLM type 2 message has flags that make no sense: " + Integer.toString(this.f15206d));
        }
        byte[] d;
        this.f15204b = null;
        if (m17278b() >= 20) {
            d = m17282d(12);
            if (d.length != 0) {
                try {
                    this.f15204b = new String(d, "UnicodeLittleUnmarked");
                } catch (Throwable e) {
                    throw new NTLMEngineException(e.getMessage(), e);
                }
            }
        }
        this.f15205c = null;
        if (m17278b() >= 48) {
            d = m17282d(40);
            if (d.length != 0) {
                this.f15205c = d;
            }
        }
    }

    byte[] m17286d() {
        return this.f15203a;
    }

    String m17287e() {
        return this.f15204b;
    }

    byte[] m17288f() {
        return this.f15205c;
    }

    int m17289g() {
        return this.f15206d;
    }
}
