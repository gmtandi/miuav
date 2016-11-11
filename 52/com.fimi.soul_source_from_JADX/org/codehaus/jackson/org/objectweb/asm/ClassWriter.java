package org.codehaus.jackson.org.objectweb.asm;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.infra.galaxy.fds.android.util.Consts;
import it.p074a.p075a.C2799f;

public class ClassWriter implements ClassVisitor {
    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    static final byte[] f16139a;
    MethodWriter f16140A;
    MethodWriter f16141B;
    private short f16142D;
    Item[] f16143E;
    String f16144F;
    private final boolean f16145G;
    private final boolean f16146H;
    boolean f16147I;
    ClassReader f16148J;
    int f16149b;
    int f16150c;
    final ByteVector f16151d;
    Item[] f16152e;
    int f16153f;
    final Item f16154g;
    final Item f16155h;
    final Item f16156i;
    private int f16157j;
    private int f16158k;
    private int f16159l;
    private int f16160m;
    private int f16161n;
    private int[] f16162o;
    private int f16163p;
    private ByteVector f16164q;
    private int f16165r;
    private int f16166s;
    private AnnotationWriter f16167t;
    private AnnotationWriter f16168u;
    private Attribute f16169v;
    private int f16170w;
    private ByteVector f16171x;
    FieldWriter f16172y;
    FieldWriter f16173z;

    static {
        byte[] bArr = new byte[C2799f.f14251A];
        String str = "AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHHFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII";
        for (int i = 0; i < bArr.length; i += COMPUTE_MAXS) {
            bArr[i] = (byte) (str.charAt(i) - 65);
        }
        f16139a = bArr;
    }

    public ClassWriter(int i) {
        boolean z = true;
        this.f16150c = COMPUTE_MAXS;
        this.f16151d = new ByteVector();
        this.f16152e = new Item[Opcodes.ACC_NATIVE];
        this.f16153f = (int) (0.75d * ((double) this.f16152e.length));
        this.f16154g = new Item();
        this.f16155h = new Item();
        this.f16156i = new Item();
        this.f16146H = (i & COMPUTE_MAXS) != 0;
        if ((i & COMPUTE_FRAMES) == 0) {
            z = false;
        }
        this.f16145G = z;
    }

    public ClassWriter(ClassReader classReader, int i) {
        this(i);
        classReader.m19318a(this);
        this.f16148J = classReader;
    }

    private Item m19319a(Item item) {
        Item item2 = this.f16152e[item.f16209j % this.f16152e.length];
        while (item2 != null && (item2.f16203b != item.f16203b || !item.m19357a(item2))) {
            item2 = item2.f16210k;
        }
        return item2;
    }

    private void m19320a(int i, int i2, int i3) {
        this.f16151d.m19310b(i, i2).putShort(i3);
    }

    private Item m19321b(String str) {
        this.f16155h.m19355a(8, str, null, null);
        Item a = m19319a(this.f16155h);
        if (a != null) {
            return a;
        }
        this.f16151d.m19310b(8, newUTF8(str));
        int i = this.f16150c;
        this.f16150c = i + COMPUTE_MAXS;
        a = new Item(i, this.f16155h);
        m19322b(a);
        return a;
    }

    private void m19322b(Item item) {
        int length;
        if (this.f16150c > this.f16153f) {
            length = this.f16152e.length;
            int i = (length * COMPUTE_FRAMES) + COMPUTE_MAXS;
            Item[] itemArr = new Item[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                Item item2 = this.f16152e[i2];
                while (item2 != null) {
                    int length2 = item2.f16209j % itemArr.length;
                    Item item3 = item2.f16210k;
                    item2.f16210k = itemArr[length2];
                    itemArr[length2] = item2;
                    item2 = item3;
                }
            }
            this.f16152e = itemArr;
            this.f16153f = (int) (((double) i) * 0.75d);
        }
        length = item.f16209j % this.f16152e.length;
        item.f16210k = this.f16152e[length];
        this.f16152e[length] = item;
    }

    private Item m19323c(Item item) {
        this.f16142D = (short) (this.f16142D + COMPUTE_MAXS);
        Item item2 = new Item(this.f16142D, this.f16154g);
        m19322b(item2);
        if (this.f16143E == null) {
            this.f16143E = new Item[16];
        }
        if (this.f16142D == this.f16143E.length) {
            Object obj = new Item[(this.f16143E.length * COMPUTE_FRAMES)];
            System.arraycopy(this.f16143E, 0, obj, 0, this.f16143E.length);
            this.f16143E = obj;
        }
        this.f16143E[this.f16142D] = item2;
        return item2;
    }

    int m19324a(int i, int i2) {
        this.f16155h.f16203b = 15;
        this.f16155h.f16205d = ((long) i) | (((long) i2) << 32);
        this.f16155h.f16209j = Integer.MAX_VALUE & ((i + 15) + i2);
        Item a = m19319a(this.f16155h);
        if (a == null) {
            String str = this.f16143E[i].f16206g;
            String str2 = this.f16143E[i2].f16206g;
            this.f16155h.f16204c = m19335c(getCommonSuperClass(str, str2));
            a = new Item(0, this.f16155h);
            m19322b(a);
        }
        return a.f16204c;
    }

    int m19325a(String str, int i) {
        this.f16154g.f16203b = 14;
        this.f16154g.f16204c = i;
        this.f16154g.f16206g = str;
        this.f16154g.f16209j = Integer.MAX_VALUE & ((str.hashCode() + 14) + i);
        Item a = m19319a(this.f16154g);
        if (a == null) {
            a = m19323c(this.f16154g);
        }
        return a.f16202a;
    }

    Item m19326a(double d) {
        this.f16154g.m19352a(d);
        Item a = m19319a(this.f16154g);
        if (a != null) {
            return a;
        }
        this.f16151d.putByte(6).putLong(this.f16154g.f16205d);
        a = new Item(this.f16150c, this.f16154g);
        m19322b(a);
        this.f16150c += COMPUTE_FRAMES;
        return a;
    }

    Item m19327a(float f) {
        this.f16154g.m19353a(f);
        Item a = m19319a(this.f16154g);
        if (a != null) {
            return a;
        }
        this.f16151d.putByte(4).putInt(this.f16154g.f16204c);
        int i = this.f16150c;
        this.f16150c = i + COMPUTE_MAXS;
        a = new Item(i, this.f16154g);
        m19322b(a);
        return a;
    }

    Item m19328a(int i) {
        this.f16154g.m19354a(i);
        Item a = m19319a(this.f16154g);
        if (a != null) {
            return a;
        }
        this.f16151d.putByte(3).putInt(i);
        int i2 = this.f16150c;
        this.f16150c = i2 + COMPUTE_MAXS;
        a = new Item(i2, this.f16154g);
        m19322b(a);
        return a;
    }

    Item m19329a(long j) {
        this.f16154g.m19356a(j);
        Item a = m19319a(this.f16154g);
        if (a != null) {
            return a;
        }
        this.f16151d.putByte(5).putLong(j);
        a = new Item(this.f16150c, this.f16154g);
        m19322b(a);
        this.f16150c += COMPUTE_FRAMES;
        return a;
    }

    Item m19330a(Object obj) {
        if (obj instanceof Integer) {
            return m19328a(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return m19328a(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return m19328a(((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return m19328a(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return m19328a(((Boolean) obj).booleanValue() ? COMPUTE_MAXS : 0);
        } else if (obj instanceof Float) {
            return m19327a(((Float) obj).floatValue());
        } else {
            if (obj instanceof Long) {
                return m19329a(((Long) obj).longValue());
            }
            if (obj instanceof Double) {
                return m19326a(((Double) obj).doubleValue());
            }
            if (obj instanceof String) {
                return m19321b((String) obj);
            }
            if (obj instanceof Type) {
                Type type = (Type) obj;
                return m19331a(type.getSort() == 10 ? type.getInternalName() : type.getDescriptor());
            }
            throw new IllegalArgumentException(new StringBuffer().append("value ").append(obj).toString());
        }
    }

    Item m19331a(String str) {
        this.f16155h.m19355a(7, str, null, null);
        Item a = m19319a(this.f16155h);
        if (a != null) {
            return a;
        }
        this.f16151d.m19310b(7, newUTF8(str));
        int i = this.f16150c;
        this.f16150c = i + COMPUTE_MAXS;
        a = new Item(i, this.f16155h);
        m19322b(a);
        return a;
    }

    Item m19332a(String str, String str2) {
        this.f16155h.m19355a(12, str, str2, null);
        Item a = m19319a(this.f16155h);
        if (a != null) {
            return a;
        }
        m19320a(12, newUTF8(str), newUTF8(str2));
        int i = this.f16150c;
        this.f16150c = i + COMPUTE_MAXS;
        a = new Item(i, this.f16155h);
        m19322b(a);
        return a;
    }

    Item m19333a(String str, String str2, String str3) {
        this.f16156i.m19355a(9, str, str2, str3);
        Item a = m19319a(this.f16156i);
        if (a != null) {
            return a;
        }
        m19320a(9, newClass(str), newNameType(str2, str3));
        int i = this.f16150c;
        this.f16150c = i + COMPUTE_MAXS;
        a = new Item(i, this.f16156i);
        m19322b(a);
        return a;
    }

    Item m19334a(String str, String str2, String str3, boolean z) {
        int i = z ? 11 : 10;
        this.f16156i.m19355a(i, str, str2, str3);
        Item a = m19319a(this.f16156i);
        if (a != null) {
            return a;
        }
        m19320a(i, newClass(str), newNameType(str2, str3));
        int i2 = this.f16150c;
        this.f16150c = i2 + COMPUTE_MAXS;
        Item item = new Item(i2, this.f16156i);
        m19322b(item);
        return item;
    }

    int m19335c(String str) {
        this.f16154g.m19355a(13, str, null, null);
        Item a = m19319a(this.f16154g);
        if (a == null) {
            a = m19323c(this.f16154g);
        }
        return a.f16202a;
    }

    protected String getCommonSuperClass(String str, String str2) {
        try {
            Class cls = Class.forName(str.replace('/', '.'));
            Class cls2 = Class.forName(str2.replace('/', '.'));
            if (cls.isAssignableFrom(cls2)) {
                return str;
            }
            if (cls2.isAssignableFrom(cls)) {
                return str2;
            }
            if (cls.isInterface() || cls2.isInterface()) {
                return "java/lang/Object";
            }
            do {
                cls = cls.getSuperclass();
            } while (!cls.isAssignableFrom(cls2));
            return cls.getName().replace('.', '/');
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public int newClass(String str) {
        return m19331a(str).f16202a;
    }

    public int newConst(Object obj) {
        return m19330a(obj).f16202a;
    }

    public int newField(String str, String str2, String str3) {
        return m19333a(str, str2, str3).f16202a;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return m19334a(str, str2, str3, z).f16202a;
    }

    public int newNameType(String str, String str2) {
        return m19332a(str, str2).f16202a;
    }

    public int newUTF8(String str) {
        this.f16154g.m19355a(COMPUTE_MAXS, str, null, null);
        Item a = m19319a(this.f16154g);
        if (a == null) {
            this.f16151d.putByte(COMPUTE_MAXS).putUTF8(str);
            int i = this.f16150c;
            this.f16150c = i + COMPUTE_MAXS;
            a = new Item(i, this.f16154g);
            m19322b(a);
        }
        return a.f16202a;
    }

    public byte[] toByteArray() {
        int i;
        int i2 = (this.f16161n * COMPUTE_FRAMES) + 24;
        FieldWriter fieldWriter = this.f16172y;
        int i3 = 0;
        while (fieldWriter != null) {
            int i4 = i3 + COMPUTE_MAXS;
            i2 += fieldWriter.m19336a();
            fieldWriter = fieldWriter.f16177a;
            i3 = i4;
        }
        MethodWriter methodWriter = this.f16140A;
        int i5 = 0;
        while (methodWriter != null) {
            i4 = i5 + COMPUTE_MAXS;
            i2 += methodWriter.m19382a();
            methodWriter = methodWriter.f16241a;
            i5 = i4;
        }
        if (this.f16159l != 0) {
            i = COMPUTE_MAXS;
            i2 += 8;
            newUTF8(Consts.SIGNATURE);
        } else {
            i = 0;
        }
        if (this.f16163p != 0) {
            i += COMPUTE_MAXS;
            i2 += 8;
            newUTF8("SourceFile");
        }
        if (this.f16164q != null) {
            i += COMPUTE_MAXS;
            i2 += this.f16164q.f16134b + 4;
            newUTF8("SourceDebugExtension");
        }
        if (this.f16165r != 0) {
            i += COMPUTE_MAXS;
            i2 += 10;
            newUTF8("EnclosingMethod");
        }
        if ((this.f16157j & Opcodes.ACC_DEPRECATED) != 0) {
            i += COMPUTE_MAXS;
            i2 += 6;
            newUTF8("Deprecated");
        }
        if ((this.f16157j & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16149b & Util.MASK_16BIT) < 49 || (this.f16157j & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            i += COMPUTE_MAXS;
            i2 += 6;
            newUTF8("Synthetic");
        }
        if (this.f16171x != null) {
            i += COMPUTE_MAXS;
            i2 += this.f16171x.f16134b + 8;
            newUTF8("InnerClasses");
        }
        if (this.f16167t != null) {
            i += COMPUTE_MAXS;
            i2 += this.f16167t.m19303a() + 8;
            newUTF8("RuntimeVisibleAnnotations");
        }
        if (this.f16168u != null) {
            i += COMPUTE_MAXS;
            i2 += this.f16168u.m19303a() + 8;
            newUTF8("RuntimeInvisibleAnnotations");
        }
        int i6 = i2;
        if (this.f16169v != null) {
            i6 += this.f16169v.m19306a(this, null, 0, -1, -1);
            i += this.f16169v.m19305a();
        }
        ByteVector byteVector = new ByteVector(this.f16151d.f16134b + i6);
        byteVector.putInt(-889275714).putInt(this.f16149b);
        byteVector.putShort(this.f16150c).putByteArray(this.f16151d.f16133a, 0, this.f16151d.f16134b);
        byteVector.putShort(((393216 | ((this.f16157j & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) / 64)) ^ -1) & this.f16157j).putShort(this.f16158k).putShort(this.f16160m);
        byteVector.putShort(this.f16161n);
        for (i2 = 0; i2 < this.f16161n; i2 += COMPUTE_MAXS) {
            byteVector.putShort(this.f16162o[i2]);
        }
        byteVector.putShort(i3);
        for (FieldWriter fieldWriter2 = this.f16172y; fieldWriter2 != null; fieldWriter2 = fieldWriter2.f16177a) {
            fieldWriter2.m19337a(byteVector);
        }
        byteVector.putShort(i5);
        for (MethodWriter methodWriter2 = this.f16140A; methodWriter2 != null; methodWriter2 = methodWriter2.f16241a) {
            methodWriter2.m19383a(byteVector);
        }
        byteVector.putShort(i);
        if (this.f16159l != 0) {
            byteVector.putShort(newUTF8(Consts.SIGNATURE)).putInt(COMPUTE_FRAMES).putShort(this.f16159l);
        }
        if (this.f16163p != 0) {
            byteVector.putShort(newUTF8("SourceFile")).putInt(COMPUTE_FRAMES).putShort(this.f16163p);
        }
        if (this.f16164q != null) {
            i = this.f16164q.f16134b - 2;
            byteVector.putShort(newUTF8("SourceDebugExtension")).putInt(i);
            byteVector.putByteArray(this.f16164q.f16133a, COMPUTE_FRAMES, i);
        }
        if (this.f16165r != 0) {
            byteVector.putShort(newUTF8("EnclosingMethod")).putInt(4);
            byteVector.putShort(this.f16165r).putShort(this.f16166s);
        }
        if ((this.f16157j & Opcodes.ACC_DEPRECATED) != 0) {
            byteVector.putShort(newUTF8("Deprecated")).putInt(0);
        }
        if ((this.f16157j & Opcodes.ACC_SYNTHETIC) != 0 && ((this.f16149b & Util.MASK_16BIT) < 49 || (this.f16157j & AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START) != 0)) {
            byteVector.putShort(newUTF8("Synthetic")).putInt(0);
        }
        if (this.f16171x != null) {
            byteVector.putShort(newUTF8("InnerClasses"));
            byteVector.putInt(this.f16171x.f16134b + COMPUTE_FRAMES).putShort(this.f16170w);
            byteVector.putByteArray(this.f16171x.f16133a, 0, this.f16171x.f16134b);
        }
        if (this.f16167t != null) {
            byteVector.putShort(newUTF8("RuntimeVisibleAnnotations"));
            this.f16167t.m19304a(byteVector);
        }
        if (this.f16168u != null) {
            byteVector.putShort(newUTF8("RuntimeInvisibleAnnotations"));
            this.f16168u.m19304a(byteVector);
        }
        if (this.f16169v != null) {
            this.f16169v.m19307a(this, null, 0, -1, -1, byteVector);
        }
        if (!this.f16147I) {
            return byteVector.f16133a;
        }
        Object classWriter = new ClassWriter(COMPUTE_FRAMES);
        new ClassReader(byteVector.f16133a).accept(classWriter, 4);
        return classWriter.toByteArray();
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        int i3 = 0;
        this.f16149b = i;
        this.f16157j = i2;
        this.f16158k = newClass(str);
        this.f16144F = str;
        if (str2 != null) {
            this.f16159l = newUTF8(str2);
        }
        this.f16160m = str3 == null ? 0 : newClass(str3);
        if (strArr != null && strArr.length > 0) {
            this.f16161n = strArr.length;
            this.f16162o = new int[this.f16161n];
            while (i3 < this.f16161n) {
                this.f16162o[i3] = newClass(strArr[i3]);
                i3 += COMPUTE_MAXS;
            }
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, COMPUTE_FRAMES);
        if (z) {
            annotationWriter.f16129g = this.f16167t;
            this.f16167t = annotationWriter;
        } else {
            annotationWriter.f16129g = this.f16168u;
            this.f16168u = annotationWriter;
        }
        return annotationWriter;
    }

    public void visitAttribute(Attribute attribute) {
        attribute.f16131a = this.f16169v;
        this.f16169v = attribute;
    }

    public void visitEnd() {
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        return new FieldWriter(this, i, str, str2, str3, obj);
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        int i2 = 0;
        if (this.f16171x == null) {
            this.f16171x = new ByteVector();
        }
        this.f16170w += COMPUTE_MAXS;
        this.f16171x.putShort(str == null ? 0 : newClass(str));
        this.f16171x.putShort(str2 == null ? 0 : newClass(str2));
        ByteVector byteVector = this.f16171x;
        if (str3 != null) {
            i2 = newUTF8(str3);
        }
        byteVector.putShort(i2);
        this.f16171x.putShort(i);
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return new MethodWriter(this, i, str, str2, str3, strArr, this.f16146H, this.f16145G);
    }

    public void visitOuterClass(String str, String str2, String str3) {
        this.f16165r = newClass(str);
        if (str2 != null && str3 != null) {
            this.f16166s = newNameType(str2, str3);
        }
    }

    public void visitSource(String str, String str2) {
        if (str != null) {
            this.f16163p = newUTF8(str);
        }
        if (str2 != null) {
            this.f16164q = new ByteVector().putUTF8(str2);
        }
    }
}
