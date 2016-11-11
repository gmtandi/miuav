package org.p122a.p123a.p167i.p176f;

import java.io.InterruptedIOException;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.i.f.i */
public class C3175i extends InterruptedIOException {
    private static final long serialVersionUID = 4973849966012490112L;

    public C3175i(String str) {
        super(str);
    }

    public C3175i(String str, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
    }
}
