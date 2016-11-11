package com.fimi.soul.drone.p107c.p108a;

import com.facebook.imageutils.JfifUtil;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.drone.c.a.a */
public class C1464a {
    private static final int f6995b = 0;
    private final int[] f6996a;
    private int f6997c;

    public C1464a() {
        this.f6996a = new int[]{50, Opcodes.IUSHR, Opcodes.L2F, 0, 237, JfifUtil.MARKER_EOI, Opcodes.IMUL, Opcodes.DNEG, 0, 0, 0, 89, 0, 0, 0, 0, 0, 0, 0, 0, C2799f.f14287y, Opcodes.IF_ICMPEQ, C2799f.f14251A, Opcodes.JSR, 24, 23, Opcodes.TABLESWITCH, Opcodes.D2F, 67, Opcodes.DREM, 39, 246, Opcodes.INVOKEINTERFACE, Opcodes.IMUL, 237, 244, 222, C2799f.f14285w, 9, C1465c.f6998a, C2799f.f14256F, 28, 28, Opcodes.IINC, C2799f.f14252B, SmileConstants.TOKEN_MISC_BINARY_7BIT, 11, Opcodes.IFEQ, 41, 39, C2799f.f14287y, 223, Opcodes.F2D, 33, 15, 3, 100, 24, 239, 238, 30, 240, Opcodes.INVOKESPECIAL, Opcodes.IXOR, Opcodes.IXOR, 0, Opcodes.LCMP, 21, 0, 243, Opcodes.IUSHR, 0, 0, 0, 20, 0, Opcodes.DCMPG, Opcodes.D2L, 0, 0, Opcodes.LAND, Opcodes.FMUL, 0, 0, 0, 0, 0, 0, 0, 231, Opcodes.INVOKESPECIAL, 63, 54, 0, 0, 0, 0, 0, 0, 0, Opcodes.DRETURN, Opcodes.FSUB, Opcodes.IFLE, JfifUtil.MARKER_RST0, 56, 93, 0, 0, 0, 0, 235, 93, Opcodes.IUSHR, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 42, 241, 15, Opcodes.I2F, 219, JfifUtil.MARKER_RST0, Opcodes.NEWARRAY, 84, 22, 19, 21, Opcodes.I2F, 0, 78, 68, Opcodes.ANEWARRAY, Opcodes.LAND, Opcodes.DDIV, 21, 21, Opcodes.D2F, 1, 234, 73, Opcodes.PUTFIELD, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, C1458u.f6934b, 49, Opcodes.TABLESWITCH, 44, 83, 46, 0};
        m9807a();
    }

    public void m9807a() {
        this.f6997c = 0;
    }

    public void m9808a(int i) {
        this.f6997c = ((i & Util.MASK_8BIT) + this.f6997c) % Util.MASK_16BIT;
    }

    public int m9809b() {
        return (this.f6997c >> 8) & Util.MASK_8BIT;
    }

    public void m9810b(int i) {
        int i2 = (i & Util.MASK_8BIT) ^ (this.f6997c & Util.MASK_8BIT);
        i2 ^= (i2 << 4) & Util.MASK_8BIT;
        this.f6997c = ((i2 >> 4) & 15) ^ ((((this.f6997c >> 8) & Util.MASK_8BIT) ^ (i2 << 8)) ^ (i2 << 3));
    }

    public int m9811c() {
        return this.f6997c & Util.MASK_8BIT;
    }

    public void m9812c(int i) {
        m9810b(this.f6996a[i]);
    }
}
