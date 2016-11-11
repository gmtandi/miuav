package org.codehaus.jackson.org.objectweb.asm;

import org.codehaus.jackson.smile.SmileConstants;

final class Item {
    int f16202a;
    int f16203b;
    int f16204c;
    long f16205d;
    String f16206g;
    String f16207h;
    String f16208i;
    int f16209j;
    Item f16210k;

    Item() {
    }

    Item(int i) {
        this.f16202a = i;
    }

    Item(int i, Item item) {
        this.f16202a = i;
        this.f16203b = item.f16203b;
        this.f16204c = item.f16204c;
        this.f16205d = item.f16205d;
        this.f16206g = item.f16206g;
        this.f16207h = item.f16207h;
        this.f16208i = item.f16208i;
        this.f16209j = item.f16209j;
    }

    void m19352a(double d) {
        this.f16203b = 6;
        this.f16205d = Double.doubleToRawLongBits(d);
        this.f16209j = Integer.MAX_VALUE & (this.f16203b + ((int) d));
    }

    void m19353a(float f) {
        this.f16203b = 4;
        this.f16204c = Float.floatToRawIntBits(f);
        this.f16209j = Integer.MAX_VALUE & (this.f16203b + ((int) f));
    }

    void m19354a(int i) {
        this.f16203b = 3;
        this.f16204c = i;
        this.f16209j = Integer.MAX_VALUE & (this.f16203b + i);
    }

    void m19355a(int i, String str, String str2, String str3) {
        this.f16203b = i;
        this.f16206g = str;
        this.f16207h = str2;
        this.f16208i = str3;
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
            case Opcodes.FCONST_2 /*13*/:
                this.f16209j = (str.hashCode() + i) & Integer.MAX_VALUE;
            case Opcodes.FCONST_1 /*12*/:
                this.f16209j = ((str.hashCode() * str2.hashCode()) + i) & Integer.MAX_VALUE;
            default:
                this.f16209j = (((str.hashCode() * str2.hashCode()) * str3.hashCode()) + i) & Integer.MAX_VALUE;
        }
    }

    void m19356a(long j) {
        this.f16203b = 5;
        this.f16205d = j;
        this.f16209j = Integer.MAX_VALUE & (this.f16203b + ((int) j));
    }

    boolean m19357a(Item item) {
        switch (this.f16203b) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.LONG /*7*/:
            case Type.DOUBLE /*8*/:
            case Opcodes.FCONST_2 /*13*/:
                return item.f16206g.equals(this.f16206g);
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return item.f16204c == this.f16204c;
            case Type.INT /*5*/:
            case Type.FLOAT /*6*/:
            case Opcodes.DCONST_1 /*15*/:
                return item.f16205d == this.f16205d;
            case Opcodes.FCONST_1 /*12*/:
                return item.f16206g.equals(this.f16206g) && item.f16207h.equals(this.f16207h);
            case Opcodes.DCONST_0 /*14*/:
                return item.f16204c == this.f16204c && item.f16206g.equals(this.f16206g);
            default:
                return item.f16206g.equals(this.f16206g) && item.f16207h.equals(this.f16207h) && item.f16208i.equals(this.f16208i);
        }
    }
}
