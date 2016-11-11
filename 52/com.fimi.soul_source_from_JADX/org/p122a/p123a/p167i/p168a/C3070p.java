package org.p122a.p123a.p167i.p168a;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.biz.camera.C1314u;
import com.p054c.p055a.p072c.C0957d;
import java.util.Locale;
import org.apache.http.impl.auth.NTLMEngineException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: org.a.a.i.a.p */
class C3070p extends C3067m {
    protected int f15207a;
    protected byte[] f15208b;
    protected byte[] f15209c;
    protected byte[] f15210d;
    protected byte[] f15211e;
    protected byte[] f15212f;
    protected byte[] f15213g;

    C3070p(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) {
        byte[] t;
        byte[] bytes;
        this.f15207a = i;
        String d = C3063i.m17232g(str2);
        String e = C3063i.m17234h(str);
        C3064j c3064j = new C3064j(e, str3, str4, bArr, str5, bArr2);
        if ((GravityCompat.RELATIVE_LAYOUT_DIRECTION & i) != 0 && bArr2 != null && str5 != null) {
            try {
                this.f15212f = c3064j.m17254l();
                this.f15211e = c3064j.m17255m();
                t = (i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? c3064j.m17262t() : c3064j.m17260r();
            } catch (NTLMEngineException e2) {
                this.f15212f = new byte[0];
                this.f15211e = c3064j.m17247e();
                t = (i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? c3064j.m17262t() : c3064j.m17258p();
            }
        } else if ((AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END & i) != 0) {
            this.f15212f = c3064j.m17256n();
            this.f15211e = c3064j.m17257o();
            t = (i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? c3064j.m17262t() : c3064j.m17261s();
        } else {
            this.f15212f = c3064j.m17249g();
            this.f15211e = c3064j.m17247e();
            t = (i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? c3064j.m17262t() : c3064j.m17259q();
        }
        if ((i & 16) == 0) {
            this.f15213g = null;
        } else if ((1073741824 & i) != 0) {
            this.f15213g = C3063i.m17210b(c3064j.m17245c(), t);
        } else {
            this.f15213g = t;
        }
        if (d != null) {
            try {
                bytes = d.getBytes("UnicodeLittleUnmarked");
            } catch (Throwable e3) {
                throw new NTLMEngineException("Unicode not supported: " + e3.getMessage(), e3);
            }
        }
        bytes = null;
        this.f15209c = bytes;
        this.f15208b = e != null ? e.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked") : null;
        this.f15210d = str3.getBytes("UnicodeLittleUnmarked");
    }

    String m17290c() {
        int i = 0;
        int length = this.f15212f.length;
        int length2 = this.f15211e.length;
        int length3 = this.f15208b != null ? this.f15208b.length : 0;
        int length4 = this.f15209c != null ? this.f15209c.length : 0;
        int length5 = this.f15210d.length;
        if (this.f15213g != null) {
            i = this.f15213g.length;
        }
        int i2 = length2 + 72;
        int i3 = i2 + length;
        int i4 = i3 + length3;
        int i5 = i4 + length5;
        int i6 = i5 + length4;
        m17275a(i6 + i, 3);
        m17283e(length2);
        m17283e(length2);
        m17284f(72);
        m17283e(length);
        m17283e(length);
        m17284f(i2);
        m17283e(length3);
        m17283e(length3);
        m17284f(i3);
        m17283e(length5);
        m17283e(length5);
        m17284f(i4);
        m17283e(length4);
        m17283e(length4);
        m17284f(i5);
        m17283e(i);
        m17283e(i);
        m17284f(i6);
        m17284f(((((((((((((this.f15207a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) | (this.f15207a & Opcodes.ACC_INTERFACE)) | (this.f15207a & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END)) | 33554432) | (this.f15207a & C0957d.f5043a)) | (this.f15207a & 32)) | (this.f15207a & 16)) | (this.f15207a & 536870912)) | (this.f15207a & C1186y.f5353a)) | (this.f15207a & 1073741824)) | (this.f15207a & GravityCompat.RELATIVE_LAYOUT_DIRECTION)) | (this.f15207a & 1)) | (this.f15207a & 4));
        m17283e(C1314u.f5853F);
        m17284f(2600);
        m17283e(3840);
        m17276a(this.f15211e);
        m17276a(this.f15212f);
        m17276a(this.f15208b);
        m17276a(this.f15210d);
        m17276a(this.f15209c);
        if (this.f15213g != null) {
            m17276a(this.f15213g);
        }
        return super.m17281c();
    }
}
