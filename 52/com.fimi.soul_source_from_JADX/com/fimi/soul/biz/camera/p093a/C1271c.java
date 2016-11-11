package com.fimi.soul.biz.camera.p093a;

/* renamed from: com.fimi.soul.biz.camera.a.c */
class C1271c implements Runnable {
    final /* synthetic */ byte[] f5729a;
    final /* synthetic */ C1270b f5730b;

    C1271c(C1270b c1270b, byte[] bArr) {
        this.f5730b = c1270b;
        this.f5729a = bArr;
    }

    public void run() {
        try {
            if (!this.f5730b.f5727a.m7885b()) {
                this.f5730b.f5727a.m7886d();
            }
            this.f5730b.f5727a.m7878a(this.f5729a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
