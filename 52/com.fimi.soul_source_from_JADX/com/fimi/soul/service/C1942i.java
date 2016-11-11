package com.fimi.soul.service;

import com.fimi.soul.utils.C1962b;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.service.i */
public class C1942i implements Runnable {
    final /* synthetic */ CameraSocketService f9982a;

    public C1942i(CameraSocketService cameraSocketService) {
        this.f9982a = cameraSocketService;
    }

    public void run() {
        C1962b.m12310a(this.f9982a.f9908L);
        byte[] bArr = new byte[28];
        byte[] bytes = be.m12385f(this.f9982a).getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bArr[i] = bytes[i];
        }
        bArr[20] = (byte) 5;
        bArr[21] = (byte) -26;
        bArr[22] = (byte) 0;
        bArr[23] = (byte) 0;
        bArr[24] = (byte) 1;
        bArr[25] = (byte) 0;
        bArr[26] = (byte) 0;
        bArr[27] = (byte) 0;
        C1962b.m12310a(bArr);
        try {
            Thread.sleep(3000);
            if (!this.f9982a.m12135g()) {
                this.f9982a.m12127a(7, this.f9982a.f9917v, "refuse connect");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
