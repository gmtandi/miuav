package org.codehaus.jackson.org.objectweb.asm;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.infra.galaxy.fds.android.util.Consts;

final class FieldWriter implements FieldVisitor {
    FieldWriter f16177a;
    private final ClassWriter f16178b;
    private final int f16179c;
    private final int f16180d;
    private final int f16181e;
    private int f16182f;
    private int f16183g;
    private AnnotationWriter f16184h;
    private AnnotationWriter f16185i;
    private Attribute f16186j;

    FieldWriter(ClassWriter classWriter, int i, String str, String str2, String str3, Object obj) {
        if (classWriter.f16172y == null) {
            classWriter.f16172y = this;
        } else {
            classWriter.f16173z.f16177a = this;
        }
        classWriter.f16173z = this;
        this.f16178b = classWriter;
        this.f16179c = i;
        this.f16180d = classWriter.newUTF8(str);
        this.f16181e = classWriter.newUTF8(str2);
        if (str3 != null) {
            this.f16182f = classWriter.newUTF8(str3);
        }
        if (obj != null) {
            this.f16183g = classWriter.m19330a(obj).f16202a;
        }
    }

    int m19336a() {
        int a;
        int i = 8;
        if (this.f16183g != 0) {
            this.f16178b.newUTF8("ConstantValue");
            i = 16;
        }
        if ((this.f16179c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16178b.f16149b & Util.MASK_16BIT) < 49 || (this.f16179c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            this.f16178b.newUTF8("Synthetic");
            i += 6;
        }
        if ((this.f16179c & Opcodes.ACC_DEPRECATED) != 0) {
            this.f16178b.newUTF8("Deprecated");
            i += 6;
        }
        if (this.f16182f != 0) {
            this.f16178b.newUTF8(Consts.SIGNATURE);
            i += 8;
        }
        if (this.f16184h != null) {
            this.f16178b.newUTF8("RuntimeVisibleAnnotations");
            i += this.f16184h.m19303a() + 8;
        }
        if (this.f16185i != null) {
            this.f16178b.newUTF8("RuntimeInvisibleAnnotations");
            a = i + (this.f16185i.m19303a() + 8);
        } else {
            a = i;
        }
        return this.f16186j != null ? a + this.f16186j.m19306a(this.f16178b, null, 0, -1, -1) : a;
    }

    void m19337a(ByteVector byteVector) {
        byteVector.putShort(((393216 | ((this.f16179c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) / 64)) ^ -1) & this.f16179c).putShort(this.f16180d).putShort(this.f16181e);
        int i = this.f16183g != 0 ? 1 : 0;
        if ((this.f16179c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16178b.f16149b & Util.MASK_16BIT) < 49 || (this.f16179c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            i++;
        }
        if ((this.f16179c & Opcodes.ACC_DEPRECATED) != 0) {
            i++;
        }
        if (this.f16182f != 0) {
            i++;
        }
        if (this.f16184h != null) {
            i++;
        }
        if (this.f16185i != null) {
            i++;
        }
        if (this.f16186j != null) {
            i += this.f16186j.m19305a();
        }
        byteVector.putShort(i);
        if (this.f16183g != 0) {
            byteVector.putShort(this.f16178b.newUTF8("ConstantValue"));
            byteVector.putInt(2).putShort(this.f16183g);
        }
        if ((this.f16179c & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16178b.f16149b & Util.MASK_16BIT) < 49 || (this.f16179c & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            byteVector.putShort(this.f16178b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.f16179c & Opcodes.ACC_DEPRECATED) != 0) {
            byteVector.putShort(this.f16178b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.f16182f != 0) {
            byteVector.putShort(this.f16178b.newUTF8(Consts.SIGNATURE));
            byteVector.putInt(2).putShort(this.f16182f);
        }
        if (this.f16184h != null) {
            byteVector.putShort(this.f16178b.newUTF8("RuntimeVisibleAnnotations"));
            this.f16184h.m19304a(byteVector);
        }
        if (this.f16185i != null) {
            byteVector.putShort(this.f16178b.newUTF8("RuntimeInvisibleAnnotations"));
            this.f16185i.m19304a(byteVector);
        }
        if (this.f16186j != null) {
            this.f16186j.m19307a(this.f16178b, null, 0, -1, -1, byteVector);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.f16178b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f16178b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.f16129g = this.f16184h;
            this.f16184h = annotationWriter;
        } else {
            annotationWriter.f16129g = this.f16185i;
            this.f16185i = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitAttribute(Attribute attribute) {
        attribute.f16131a = this.f16186j;
        this.f16186j = attribute;
    }

    public void visitEnd() {
    }
}
