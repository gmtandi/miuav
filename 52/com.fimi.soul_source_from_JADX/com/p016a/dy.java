package com.p016a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.a.dy */
final class dy implements Serializable {
    protected byte f1030a;
    protected ArrayList f1031b;
    private byte f1032c;

    dy() {
        this.f1032c = (byte) 3;
        this.f1030a = (byte) 0;
        this.f1031b = new ArrayList();
    }

    protected final Boolean m1636a(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeByte(this.f1032c);
            dataOutputStream.writeByte(this.f1030a);
            for (int i = 0; i < this.f1031b.size(); i++) {
                dz dzVar = (dz) this.f1031b.get(i);
                dataOutputStream.writeByte(dzVar.f1033a);
                Object obj = new byte[dzVar.f1033a];
                System.arraycopy(dzVar.f1034b, 0, obj, 0, dzVar.f1033a < dzVar.f1034b.length ? dzVar.f1033a : dzVar.f1034b.length);
                dataOutputStream.write(obj);
                dataOutputStream.writeDouble(dzVar.f1035c);
                dataOutputStream.writeInt(dzVar.f1036d);
                dataOutputStream.writeInt(dzVar.f1037e);
                dataOutputStream.writeDouble(dzVar.f1038f);
                dataOutputStream.writeByte(dzVar.f1039g);
                dataOutputStream.writeByte(dzVar.f1040h);
                obj = new byte[dzVar.f1040h];
                System.arraycopy(dzVar.f1041i, 0, obj, 0, dzVar.f1040h < dzVar.f1041i.length ? dzVar.f1040h : dzVar.f1041i.length);
                dataOutputStream.write(obj);
                dataOutputStream.writeByte(dzVar.f1042j);
            }
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }
}
