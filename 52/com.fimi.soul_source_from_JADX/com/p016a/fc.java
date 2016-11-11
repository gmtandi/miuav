package com.p016a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.a.fc */
final class fc implements Serializable {
    protected short f1180a;
    protected int f1181b;
    protected byte f1182c;
    protected byte f1183d;
    protected ArrayList f1184e;
    private byte f1185f;

    fc() {
        this.f1185f = (byte) 2;
        this.f1180a = (short) 0;
        this.f1181b = 0;
        this.f1182c = (byte) 0;
        this.f1183d = (byte) 0;
        this.f1184e = new ArrayList();
    }

    protected final Boolean m1794a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f1185f);
            dataOutputStream.writeShort(this.f1180a);
            dataOutputStream.writeInt(this.f1181b);
            dataOutputStream.writeByte(this.f1182c);
            dataOutputStream.writeByte(this.f1183d);
            for (byte b = (byte) 0; b < this.f1183d; b++) {
                dataOutputStream.writeShort(((ef) this.f1184e.get(b)).f1069a);
                dataOutputStream.writeInt(((ef) this.f1184e.get(b)).f1070b);
                dataOutputStream.writeByte(((ef) this.f1184e.get(b)).f1071c);
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
