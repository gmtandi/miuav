package com.p016a;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.a.ct */
public class ct {
    private ByteArrayInputStream f792a;
    private long f793b;
    private boolean f794c;
    private RandomAccessFile f795d;
    private boolean f796e;
    private final byte[] f797f;
    private cu f798g;
    private String f799h;

    public ct(File file, cu cuVar) {
        this.f794c = false;
        this.f795d = null;
        this.f796e = false;
        this.f797f = new byte[8];
        this.f799h = null;
        if (cuVar != null) {
            if (cuVar.f800a) {
                byte[] a = dn.m1516a(file);
                this.f792a = new ByteArrayInputStream(a);
                this.f793b = (long) a.length;
                this.f794c = false;
                this.f799h = file.getAbsolutePath();
            } else {
                this.f795d = new RandomAccessFile(file, "r");
                this.f794c = true;
            }
            this.f798g = cuVar;
        }
    }

    private void m1385h() {
        if (this.f796e) {
            throw new IOException("file closed");
        }
    }

    public void m1386a(long j) {
        if (j < 0) {
            throw new IOException("offset < 0: " + j);
        }
        m1385h();
        if (this.f794c) {
            this.f795d.seek(j);
            return;
        }
        this.f792a.reset();
        this.f792a.skip(j);
    }

    public boolean m1387a() {
        return this.f798g == null ? false : this.f798g.f800a;
    }

    public void m1388b() {
        synchronized (this) {
            if (this.f794c) {
                if (this.f795d != null) {
                    this.f795d.close();
                    this.f795d = null;
                }
            } else if (this.f792a != null) {
                this.f792a.close();
                this.f792a = null;
            }
            this.f796e = true;
        }
    }

    public final long m1389c() {
        m1385h();
        if (this.f794c) {
            return this.f795d.readLong();
        }
        this.f792a.read(this.f797f);
        return dn.m1520b(this.f797f);
    }

    public final int m1390d() {
        m1385h();
        if (this.f794c) {
            return this.f795d.readUnsignedShort();
        }
        this.f792a.read(this.f797f, 0, 2);
        return dn.m1527c(this.f797f);
    }

    public final int m1391e() {
        m1385h();
        if (this.f794c) {
            return this.f795d.readInt();
        }
        this.f792a.read(this.f797f, 0, 4);
        return dn.m1531d(this.f797f);
    }

    public final int m1392f() {
        m1385h();
        return this.f794c ? this.f795d.readUnsignedByte() : this.f792a.read();
    }

    protected void finalize() {
        m1388b();
        super.finalize();
    }

    public long m1393g() {
        if (!this.f796e) {
            return this.f794c ? this.f795d.length() : this.f793b;
        } else {
            throw new IOException("file closed");
        }
    }
}
