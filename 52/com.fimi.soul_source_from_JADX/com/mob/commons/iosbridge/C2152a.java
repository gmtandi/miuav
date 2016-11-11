package com.mob.commons.iosbridge;

/* renamed from: com.mob.commons.iosbridge.a */
class C2152a extends Thread {
    final /* synthetic */ UDPServer f11330a;

    C2152a(UDPServer uDPServer, String str) {
        this.f11330a = uDPServer;
        super(str);
    }

    public void run() {
        this.f11330a.m13177d();
    }
}
