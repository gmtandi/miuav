package com.fimi.soul.service;

import java.net.DatagramPacket;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.service.k */
public class C1944k implements Runnable {
    final /* synthetic */ CameraSocketService f9984a;

    public C1944k(CameraSocketService cameraSocketService) {
        this.f9984a = cameraSocketService;
    }

    public void run() {
        byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        DatagramPacket datagramPacket = new DatagramPacket(bArr, SmileConstants.MAX_SHARED_STRING_VALUES);
        while (true) {
            try {
                this.f9984a.f9903G.acquire();
                this.f9984a.f9912p.receive(datagramPacket);
                this.f9984a.m12127a(-1, true, new String(bArr, 0, datagramPacket.getLength()) + datagramPacket.getAddress().getHostAddress());
                this.f9984a.f9903G.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
