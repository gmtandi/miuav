package com.fimi.soul.service;

import com.fimi.soul.utils.C1962b;

/* renamed from: com.fimi.soul.service.j */
public class C1943j implements Runnable {
    final /* synthetic */ CameraSocketService f9983a;

    public C1943j(CameraSocketService cameraSocketService) {
        this.f9983a = cameraSocketService;
    }

    public void run() {
        while (this.f9983a.f9917v) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!this.f9983a.f9916u) {
                this.f9983a.f9917v = false;
                C1962b.m12308a(this.f9983a.f9898B, this.f9983a.f9909M);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (!this.f9983a.f9917v) {
                    this.f9983a.m12127a(7, true, "HEARTBEATBROADCAST disConnect....");
                    this.f9983a.m12133e();
                }
            }
        }
    }
}
