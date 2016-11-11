package com.p016a;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.dv */
final class dv implements Serializable {
    protected byte[] f969a;
    protected byte[] f970b;
    protected byte[] f971c;
    protected short f972d;
    protected short f973e;
    protected byte f974f;
    protected byte[] f975g;
    protected byte[] f976h;
    protected short f977i;
    protected ArrayList f978j;
    private byte f979k;
    private short f980l;

    dv() {
        this.f979k = SmileConstants.HEADER_BYTE_2;
        this.f980l = (short) 0;
        this.f969a = new byte[16];
        this.f970b = new byte[16];
        this.f971c = new byte[16];
        this.f972d = (short) 0;
        this.f973e = (short) 0;
        this.f974f = (byte) 0;
        this.f975g = new byte[16];
        this.f976h = new byte[32];
        this.f977i = (short) 0;
        this.f978j = new ArrayList();
    }

    private Boolean m1583a(DataOutputStream dataOutputStream) {
        Boolean.valueOf(true);
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream2.flush();
            dataOutputStream2.write(this.f969a);
            dataOutputStream2.write(this.f970b);
            dataOutputStream2.write(this.f971c);
            dataOutputStream2.writeShort(this.f972d);
            dataOutputStream2.writeShort(this.f973e);
            dataOutputStream2.writeByte(this.f974f);
            this.f975g[15] = (byte) 0;
            dataOutputStream2.write(ed.m1649a(this.f975g, this.f975g.length));
            this.f976h[31] = (byte) 0;
            dataOutputStream2.write(ed.m1649a(this.f976h, this.f976h.length));
            dataOutputStream2.writeShort(this.f977i);
            for (short s = (short) 0; s < this.f977i; s = (short) (s + 1)) {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream2);
                dataOutputStream3.flush();
                dp dpVar = (dp) this.f978j.get(s);
                if (!(dpVar.f917c == null || dpVar.f917c.m1546a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(dpVar.f918d == null || dpVar.f918d.m1794a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(dpVar.f919e == null || dpVar.f919e.m1664a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(dpVar.f920f == null || dpVar.f920f.m1637a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                if (!(dpVar.f921g == null || dpVar.f921g.m1636a(dataOutputStream3).booleanValue())) {
                    Boolean.valueOf(false);
                }
                dpVar.f915a = Integer.valueOf(byteArrayOutputStream2.size() + 4).shortValue();
                dataOutputStream2.writeShort(dpVar.f915a);
                dataOutputStream2.writeInt(dpVar.f916b);
                dataOutputStream2.write(byteArrayOutputStream2.toByteArray());
            }
            this.f980l = Integer.valueOf(byteArrayOutputStream.size()).shortValue();
            dataOutputStream.writeByte(this.f979k);
            dataOutputStream.writeShort(this.f980l);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
            return Boolean.valueOf(true);
        } catch (IOException e) {
            return Boolean.valueOf(false);
        }
    }

    protected final byte[] m1584a() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m1583a(new DataOutputStream(byteArrayOutputStream));
        return byteArrayOutputStream.toByteArray();
    }
}
