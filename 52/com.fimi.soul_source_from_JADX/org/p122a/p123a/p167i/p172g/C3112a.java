package org.p122a.p123a.p167i.p172g;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.ProtocolException;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p162e.C2998d;
import org.p122a.p123a.p178k.C3212d;
import org.p122a.p123a.p179l.C3213a;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.g.a */
public abstract class C3112a<T extends HttpMessage> implements HttpMessageParser {
    private static final int f15403b = 0;
    private static final int f15404c = 1;
    protected final LineParser f15405a;
    private final SessionInputBuffer f15406d;
    private final C2998d f15407e;
    private final List<CharArrayBuffer> f15408f;
    private int f15409g;
    private T f15410h;

    public C3112a(SessionInputBuffer sessionInputBuffer, LineParser lineParser, C2998d c2998d) {
        this.f15406d = (SessionInputBuffer) C3234a.m17886a((Object) sessionInputBuffer, "Session input buffer");
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        this.f15405a = lineParser;
        if (c2998d == null) {
            c2998d = C2998d.f14971a;
        }
        this.f15407e = c2998d;
        this.f15408f = new ArrayList();
        this.f15409g = f15403b;
    }

    @Deprecated
    public C3112a(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpParams httpParams) {
        C3234a.m17886a((Object) sessionInputBuffer, "Session input buffer");
        C3234a.m17886a((Object) httpParams, "HTTP parameters");
        this.f15406d = sessionInputBuffer;
        this.f15407e = C3213a.m17826b(httpParams);
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        this.f15405a = lineParser;
        this.f15408f = new ArrayList();
        this.f15409g = f15403b;
    }

    public static Header[] m17565a(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser) {
        List arrayList = new ArrayList();
        if (lineParser == null) {
            lineParser = C3212d.f15666b;
        }
        return C3112a.m17566a(sessionInputBuffer, i, i2, lineParser, arrayList);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.http.Header[] m17566a(org.apache.http.io.SessionInputBuffer r9, int r10, int r11, org.apache.http.message.LineParser r12, java.util.List<org.apache.http.util.CharArrayBuffer> r13) {
        /*
        r8 = 9;
        r4 = 0;
        r7 = 32;
        r2 = 0;
        r0 = "Session input buffer";
        org.p122a.p123a.p180o.C3234a.m17886a(r9, r0);
        r0 = "Line parser";
        org.p122a.p123a.p180o.C3234a.m17886a(r12, r0);
        r0 = "Header line list";
        org.p122a.p123a.p180o.C3234a.m17886a(r13, r0);
        r3 = r4;
        r0 = r4;
    L_0x0017:
        if (r0 != 0) goto L_0x0049;
    L_0x0019:
        r0 = new org.apache.http.util.CharArrayBuffer;
        r1 = 64;
        r0.<init>(r1);
    L_0x0020:
        r1 = r9.readLine(r0);
        r5 = -1;
        if (r1 == r5) goto L_0x002e;
    L_0x0027:
        r1 = r0.length();
        r5 = 1;
        if (r1 >= r5) goto L_0x004d;
    L_0x002e:
        r0 = r13.size();
        r1 = new org.apache.http.Header[r0];
    L_0x0034:
        r0 = r13.size();
        if (r2 >= r0) goto L_0x00b6;
    L_0x003a:
        r0 = r13.get(r2);
        r0 = (org.apache.http.util.CharArrayBuffer) r0;
        r0 = r12.parseHeader(r0);	 Catch:{ ParseException -> 0x00ab }
        r1[r2] = r0;	 Catch:{ ParseException -> 0x00ab }
        r2 = r2 + 1;
        goto L_0x0034;
    L_0x0049:
        r0.clear();
        goto L_0x0020;
    L_0x004d:
        r1 = r0.charAt(r2);
        if (r1 == r7) goto L_0x0059;
    L_0x0053:
        r1 = r0.charAt(r2);
        if (r1 != r8) goto L_0x00a2;
    L_0x0059:
        if (r3 == 0) goto L_0x00a2;
    L_0x005b:
        r1 = r2;
    L_0x005c:
        r5 = r0.length();
        if (r1 >= r5) goto L_0x006a;
    L_0x0062:
        r5 = r0.charAt(r1);
        if (r5 == r7) goto L_0x0082;
    L_0x0068:
        if (r5 == r8) goto L_0x0082;
    L_0x006a:
        if (r11 <= 0) goto L_0x0085;
    L_0x006c:
        r5 = r3.length();
        r5 = r5 + 1;
        r6 = r0.length();
        r5 = r5 + r6;
        r5 = r5 - r1;
        if (r5 <= r11) goto L_0x0085;
    L_0x007a:
        r0 = new org.a.a.f;
        r1 = "Maximum line length limit exceeded";
        r0.<init>(r1);
        throw r0;
    L_0x0082:
        r1 = r1 + 1;
        goto L_0x005c;
    L_0x0085:
        r3.append(r7);
        r5 = r0.length();
        r5 = r5 - r1;
        r3.append(r0, r1, r5);
        r1 = r0;
        r0 = r3;
    L_0x0092:
        if (r10 <= 0) goto L_0x00a7;
    L_0x0094:
        r3 = r13.size();
        if (r3 < r10) goto L_0x00a7;
    L_0x009a:
        r0 = new org.a.a.f;
        r1 = "Maximum header count exceeded";
        r0.<init>(r1);
        throw r0;
    L_0x00a2:
        r13.add(r0);
        r1 = r4;
        goto L_0x0092;
    L_0x00a7:
        r3 = r0;
        r0 = r1;
        goto L_0x0017;
    L_0x00ab:
        r0 = move-exception;
        r1 = new org.apache.http.ProtocolException;
        r0 = r0.getMessage();
        r1.<init>(r0);
        throw r1;
    L_0x00b6:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.a.i.g.a.a(org.apache.http.io.SessionInputBuffer, int, int, org.apache.http.message.LineParser, java.util.List):org.apache.http.Header[]");
    }

    protected abstract T m17567b(SessionInputBuffer sessionInputBuffer);

    public T parse() {
        switch (this.f15409g) {
            case f15403b /*0*/:
                try {
                    this.f15410h = m17567b(this.f15406d);
                    this.f15409g = f15404c;
                    break;
                } catch (Throwable e) {
                    throw new ProtocolException(e.getMessage(), e);
                }
            case f15404c /*1*/:
                break;
            default:
                throw new IllegalStateException("Inconsistent parser state");
        }
        this.f15410h.setHeaders(C3112a.m17566a(this.f15406d, this.f15407e.m17028b(), this.f15407e.m17027a(), this.f15405a, this.f15408f));
        T t = this.f15410h;
        this.f15410h = null;
        this.f15408f.clear();
        this.f15409g = f15403b;
        return t;
    }
}
