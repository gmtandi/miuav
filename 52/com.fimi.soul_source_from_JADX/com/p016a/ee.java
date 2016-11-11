package com.p016a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: com.a.ee */
final class ee implements Serializable {
    protected int f1062a;
    protected int f1063b;
    protected short f1064c;
    protected short f1065d;
    protected int f1066e;
    protected byte f1067f;
    private byte f1068g;

    ee() {
        this.f1068g = (byte) 4;
        this.f1062a = 0;
        this.f1063b = 0;
        this.f1064c = (short) 0;
        this.f1065d = (short) 0;
        this.f1066e = 0;
        this.f1067f = (byte) 0;
    }

    protected final Boolean m1664a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.f1068g);
            dataOutputStream.writeInt(this.f1062a);
            dataOutputStream.writeInt(this.f1063b);
            dataOutputStream.writeShort(this.f1064c);
            dataOutputStream.writeShort(this.f1065d);
            dataOutputStream.writeInt(this.f1066e);
            dataOutputStream.writeByte(this.f1067f);
            valueOf = Boolean.valueOf(true);
        } catch (IOException e) {
        }
        return valueOf;
    }
}
