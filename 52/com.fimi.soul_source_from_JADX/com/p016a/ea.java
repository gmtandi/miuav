package com.p016a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.a.ea */
final class ea implements Serializable {
    protected byte f1045a;
    protected ArrayList f1046b;
    private byte f1047c;

    ea() {
        this.f1047c = (byte) 8;
        this.f1045a = (byte) 0;
        this.f1046b = new ArrayList();
    }

    protected final Boolean m1637a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f1047c);
            dataOutputStream.writeByte(this.f1045a);
            for (byte b = (byte) 0; b < this.f1045a; b++) {
                eb ebVar = (eb) this.f1046b.get(b);
                dataOutputStream.write(ebVar.f1048a);
                dataOutputStream.writeShort(ebVar.f1049b);
                dataOutputStream.write(ed.m1649a(ebVar.f1050c, ebVar.f1050c.length));
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
