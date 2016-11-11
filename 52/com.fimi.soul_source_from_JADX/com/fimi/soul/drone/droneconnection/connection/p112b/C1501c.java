package com.fimi.soul.drone.droneconnection.connection.p112b;

import com.fimi.soul.drone.droneconnection.connection.C1498f;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.b.c */
public abstract class C1501c extends C1498f {
    private static final int f7093a = 20000;
    private Socket f7094h;
    private BufferedOutputStream f7095i;
    private BufferedInputStream f7096j;
    private String f7097k;
    private int f7098l;
    private String f7099m;

    public C1501c() {
        this.f7099m = null;
    }

    private void m9936c() {
        this.f7098l = m9937a();
        this.f7097k = m9939b();
        InetAddress byName = InetAddress.getByName(this.f7097k);
        this.f7099m = byName.toString();
        this.f7094h = new Socket();
        this.f7094h.setReuseAddress(true);
        this.f7094h.setSoLinger(true, 0);
        this.f7094h.connect(new InetSocketAddress(byName, this.f7098l), f7093a);
        this.f7094h.setTrafficClass(20);
        if (this.f7094h.isConnected()) {
            this.f7095i = new BufferedOutputStream(this.f7094h.getOutputStream());
            this.f7096j = new BufferedInputStream(this.f7094h.getInputStream());
        }
    }

    protected abstract int m9937a();

    public final int m9938b(byte[] bArr) {
        return this.f7096j.read(bArr);
    }

    protected abstract String m9939b();

    public final void m9940c(byte[] bArr) {
        if (this.f7095i != null) {
            this.f7095i.write(bArr);
            this.f7095i.flush();
        }
    }

    public final void m9941d() {
        m9936c();
    }

    public final void m9942e() {
        if (this.f7094h != null) {
            this.f7094h.close();
        }
    }

    public final int m9943j() {
        return 3;
    }
}
