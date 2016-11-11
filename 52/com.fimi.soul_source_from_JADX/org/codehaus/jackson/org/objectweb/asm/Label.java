package org.codehaus.jackson.org.objectweb.asm;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.smile.SmileConstants;

public class Label {
    int f16211a;
    int f16212b;
    int f16213c;
    private int f16214d;
    private int[] f16215e;
    int f16216f;
    int f16217g;
    Frame f16218h;
    Label f16219i;
    public Object info;
    Edge f16220j;
    Label f16221k;

    private void m19358a(int i, int i2) {
        if (this.f16215e == null) {
            this.f16215e = new int[6];
        }
        if (this.f16214d >= this.f16215e.length) {
            Object obj = new int[(this.f16215e.length + 6)];
            System.arraycopy(this.f16215e, 0, obj, 0, this.f16215e.length);
            this.f16215e = obj;
        }
        int[] iArr = this.f16215e;
        int i3 = this.f16214d;
        this.f16214d = i3 + 1;
        iArr[i3] = i;
        iArr = this.f16215e;
        i3 = this.f16214d;
        this.f16214d = i3 + 1;
        iArr[i3] = i2;
    }

    Label m19359a() {
        return this.f16218h == null ? this : this.f16218h.f16188b;
    }

    void m19360a(long j, int i) {
        if ((this.f16211a & SmileConstants.MAX_SHARED_STRING_VALUES) == 0) {
            this.f16211a |= SmileConstants.MAX_SHARED_STRING_VALUES;
            this.f16215e = new int[(((i - 1) / 32) + 1)];
        }
        int[] iArr = this.f16215e;
        int i2 = (int) (j >>> 32);
        iArr[i2] = iArr[i2] | ((int) j);
    }

    void m19361a(MethodWriter methodWriter, ByteVector byteVector, int i, boolean z) {
        if ((this.f16211a & 2) == 0) {
            if (z) {
                m19358a(-1 - i, byteVector.f16134b);
                byteVector.putInt(-1);
                return;
            }
            m19358a(i, byteVector.f16134b);
            byteVector.putShort(-1);
        } else if (z) {
            byteVector.putInt(this.f16213c - i);
        } else {
            byteVector.putShort(this.f16213c - i);
        }
    }

    boolean m19362a(long j) {
        return ((this.f16211a & SmileConstants.MAX_SHARED_STRING_VALUES) == 0 || (this.f16215e[(int) (j >>> 32)] & ((int) j)) == 0) ? false : true;
    }

    boolean m19363a(Label label) {
        if ((this.f16211a & SmileConstants.MAX_SHARED_STRING_VALUES) == 0 || (label.f16211a & SmileConstants.MAX_SHARED_STRING_VALUES) == 0) {
            return false;
        }
        for (int i = 0; i < this.f16215e.length; i++) {
            if ((this.f16215e[i] & label.f16215e[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    boolean m19364a(MethodWriter methodWriter, int i, byte[] bArr) {
        int i2 = 0;
        this.f16211a |= 2;
        this.f16213c = i;
        boolean z = false;
        while (i2 < this.f16214d) {
            int i3 = i2 + 1;
            int i4 = this.f16215e[i2];
            i2 = i3 + 1;
            i3 = this.f16215e[i3];
            int i5;
            if (i4 >= 0) {
                i4 = i - i4;
                if (i4 < -32768 || i4 > 32767) {
                    int i6 = bArr[i3 - 1] & Util.MASK_8BIT;
                    if (i6 <= Opcodes.JSR) {
                        bArr[i3 - 1] = (byte) (i6 + 49);
                    } else {
                        bArr[i3 - 1] = (byte) (i6 + 20);
                    }
                    z = true;
                }
                i5 = i3 + 1;
                bArr[i3] = (byte) (i4 >>> 8);
                bArr[i5] = (byte) i4;
            } else {
                i4 = (i4 + i) + 1;
                i5 = i3 + 1;
                bArr[i3] = (byte) (i4 >>> 24);
                i3 = i5 + 1;
                bArr[i5] = (byte) (i4 >>> 16);
                i5 = i3 + 1;
                bArr[i3] = (byte) (i4 >>> 8);
                bArr[i5] = (byte) i4;
            }
        }
        return z;
    }

    void m19365b(Label label, long j, int i) {
        while (this != null) {
            Label label2 = this.f16221k;
            this.f16221k = null;
            if (label != null) {
                if ((this.f16211a & Opcodes.ACC_STRICT) != 0) {
                    this = label2;
                } else {
                    this.f16211a |= Opcodes.ACC_STRICT;
                    if (!((this.f16211a & Opcodes.ACC_NATIVE) == 0 || m19363a(label))) {
                        Edge edge = new Edge();
                        edge.f16174a = this.f16216f;
                        edge.f16175b = label.f16220j.f16175b;
                        edge.f16176c = this.f16220j;
                        this.f16220j = edge;
                    }
                }
            } else if (m19362a(j)) {
                this = label2;
            } else {
                m19360a(j, i);
            }
            Label label3 = label2;
            Edge edge2 = this.f16220j;
            while (edge2 != null) {
                if (((this.f16211a & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 0 || edge2 != this.f16220j.f16176c) && edge2.f16175b.f16221k == null) {
                    edge2.f16175b.f16221k = label3;
                    label3 = edge2.f16175b;
                }
                edge2 = edge2.f16176c;
            }
            this = label3;
        }
    }

    public int getOffset() {
        if ((this.f16211a & 2) != 0) {
            return this.f16213c;
        }
        throw new IllegalStateException("Label offset position has not been resolved yet");
    }

    public String toString() {
        return new StringBuffer().append("L").append(System.identityHashCode(this)).toString();
    }
}
