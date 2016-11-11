package com.p016a;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.a.ec */
public class ec {
    protected File f1051a;
    protected int[] f1052b;
    private ArrayList f1053c;
    private boolean f1054d;

    protected ec(File file, ArrayList arrayList, int[] iArr) {
        this.f1054d = false;
        this.f1051a = file;
        this.f1053c = arrayList;
        this.f1052b = iArr;
    }

    public void m1638a(boolean z) {
        this.f1054d = z;
    }

    public byte[] m1639a() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Iterator it = this.f1053c.iterator();
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            try {
                dataOutputStream.writeInt(bArr.length);
                dataOutputStream.write(bArr);
            } catch (IOException e) {
            }
        }
        try {
            byteArrayOutputStream.close();
            dataOutputStream.close();
        } catch (IOException e2) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected final boolean m1640b() {
        return this.f1054d;
    }

    protected final int m1641c() {
        if (this.f1053c == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.f1053c.size(); i2++) {
            i += this.f1053c.get(i2) != null ? ((byte[]) this.f1053c.get(i2)).length : 0;
        }
        return i;
    }
}
