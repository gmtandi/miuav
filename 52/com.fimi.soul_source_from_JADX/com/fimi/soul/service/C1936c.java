package com.fimi.soul.service;

/* renamed from: com.fimi.soul.service.c */
class C1936c implements Runnable {
    final /* synthetic */ int f9970a;
    final /* synthetic */ boolean f9971b;
    final /* synthetic */ String f9972c;
    final /* synthetic */ CameraSocketService f9973d;

    C1936c(CameraSocketService cameraSocketService, int i, boolean z, String str) {
        this.f9973d = cameraSocketService;
        this.f9970a = i;
        this.f9971b = z;
        this.f9972c = str;
    }

    public void run() {
        if (this.f9973d.f9902F != null) {
            this.f9973d.f9902F.m12180a(this.f9970a, this.f9971b, this.f9972c);
        }
    }
}
