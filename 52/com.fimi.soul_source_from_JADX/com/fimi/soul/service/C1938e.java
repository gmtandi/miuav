package com.fimi.soul.service;

import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.C1962b;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/* renamed from: com.fimi.soul.service.e */
public class C1938e implements Runnable {
    final /* synthetic */ CameraSocketService f9975a;

    public C1938e(CameraSocketService cameraSocketService) {
        this.f9975a = cameraSocketService;
    }

    public void run() {
        while (true) {
            C1236a.m8532a("AcceptRunnable", CameraSocketService.class);
            Socket accept = this.f9975a.f9899C.accept();
            if (this.f9975a.f9897A != null) {
                this.f9975a.f9897A.close();
                this.f9975a.f9897A = null;
            }
            if (this.f9975a.f9898B != null) {
                this.f9975a.f9898B.close();
                this.f9975a.f9898B = null;
            }
            if (this.f9975a.f9920z != null) {
                this.f9975a.f9920z.close();
                this.f9975a.f9920z = null;
            }
            this.f9975a.f9918w = true;
            Thread thread = new Thread(new C1941h(this.f9975a, accept));
            thread.start();
            CameraSocketService.f9896y.add(thread);
            this.f9975a.f9919x = accept.getInetAddress().getHostAddress();
            C1236a.m8532a("initClientSocket=Connected=ServerIp=" + this.f9975a.f9919x, CameraSocketService.class);
            while (this.f9975a.f9920z == null && this.f9975a.f9919x != null) {
                try {
                    Thread.sleep(500);
                    this.f9975a.f9920z = new Socket(this.f9975a.f9919x, C1962b.f10059b);
                    C1236a.m8532a("create Server Socket=" + this.f9975a.f9920z, CameraSocketService.class);
                    if (this.f9975a.f9920z != null) {
                        this.f9975a.f9897A = new DataInputStream(this.f9975a.f9920z.getInputStream());
                        this.f9975a.f9898B = new DataOutputStream(this.f9975a.f9920z.getOutputStream());
                        this.f9975a.f9917v = true;
                        Thread thread2 = new Thread(new C1943j(this.f9975a));
                        thread2.start();
                        CameraSocketService.f9896y.add(thread2);
                        this.f9975a.m12127a(0, this.f9975a.f9917v, this.f9975a.f9919x);
                        break;
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
            try {
                if (this.f9975a.f9920z != null) {
                    this.f9975a.m12106a(this.f9975a.f9920z);
                }
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        }
    }
}
