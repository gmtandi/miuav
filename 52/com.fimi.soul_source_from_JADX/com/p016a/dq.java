package com.p016a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: com.a.dq */
final class dq implements Serializable {
    protected int f922a;
    protected int f923b;
    protected int f924c;
    protected int f925d;
    protected int f926e;
    protected short f927f;
    protected byte f928g;
    protected byte f929h;
    protected long f930i;
    protected long f931j;
    private byte f932k;

    dq() {
        this.f932k = (byte) 1;
        this.f922a = 0;
        this.f923b = 0;
        this.f924c = 0;
        this.f925d = 0;
        this.f926e = 0;
        this.f927f = (short) 0;
        this.f928g = (byte) 0;
        this.f929h = (byte) 0;
        this.f930i = 0;
        this.f931j = 0;
    }

    protected final Boolean m1546a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        if (dataOutputStream != null) {
            try {
                dataOutputStream.writeByte(this.f932k);
                dataOutputStream.writeInt(this.f922a);
                dataOutputStream.writeInt(this.f923b);
                dataOutputStream.writeInt(this.f924c);
                dataOutputStream.writeInt(this.f925d);
                dataOutputStream.writeInt(this.f926e);
                dataOutputStream.writeShort(this.f927f);
                dataOutputStream.writeByte(this.f928g);
                dataOutputStream.writeByte(this.f929h);
                dataOutputStream.writeLong(this.f930i);
                dataOutputStream.writeLong(this.f931j);
                valueOf = Boolean.valueOf(true);
            } catch (IOException e) {
            }
        }
        return valueOf;
    }
}
