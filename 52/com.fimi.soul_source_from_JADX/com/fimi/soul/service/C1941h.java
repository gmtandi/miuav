package com.fimi.soul.service;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.service.h */
public class C1941h implements Runnable {
    final /* synthetic */ CameraSocketService f9980a;
    private DataInputStream f9981b;

    public C1941h(CameraSocketService cameraSocketService, Socket socket) {
        this.f9980a = cameraSocketService;
        this.f9981b = null;
        try {
            this.f9981b = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
        while (true) {
            try {
                int read = this.f9981b.read(bArr);
                if (read != -1) {
                    String str = new String(bArr, 0, read, C1142e.f5201a);
                    if (str != null) {
                        this.f9980a.m12127a(3, true, str);
                    }
                } else {
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
