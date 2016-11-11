package com.fimi.soul.drone.droneconnection.connection;

import com.fimi.soul.drone.p107c.C1470b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.g */
class C1510g implements Runnable {
    final /* synthetic */ C1498f f7134a;

    C1510g(C1498f c1498f) {
        this.f7134a = c1498f;
    }

    private void m9986a(C1470b c1470b, int i, byte[] bArr) {
        if (i >= 1) {
            for (int i2 = 0; i2 < i; i2++) {
                C1465c a = c1470b.m9852a(bArr[i2] & Util.MASK_8BIT);
                if (a != null) {
                    this.f7134a.f7080j.add(Integer.valueOf(a.f7000c));
                    C1507c a2 = C1507c.m9968a();
                    a2.m9974a(this.f7134a.f7076e);
                    if (this.f7134a.f7076e) {
                        if (a2.m9979f()) {
                            a2.m9980g();
                        }
                        if (a2.m9978e()) {
                            a2.m9972a(a);
                        } else {
                            a2.m9975b();
                        }
                    } else {
                        if (!a2.m9979f()) {
                            a2.m9976c();
                        } else if (a2.m9984k()) {
                            a2.m9980g();
                            return;
                        } else {
                            a2.m9972a(a);
                        }
                        if (a2.m9978e()) {
                            a2.m9977d();
                        }
                    }
                    this.f7134a.m9897a(a.m9818f());
                }
            }
        }
    }

    public void run() {
        IOException e;
        Throwable th;
        Thread thread;
        try {
            this.f7134a.m9915d();
            this.f7134a.f7083m.set(2);
            this.f7134a.m9896a();
            thread = new Thread(this.f7134a.f7085o, "MiLinkConnection-Sending Thread");
            try {
                thread.start();
                C1470b c1470b = new C1470b();
                c1470b.f7014c.m9847b();
                byte[] bArr = new byte[Opcodes.ACC_SYNTHETIC];
                while (this.f7134a.f7083m.get() == 2) {
                    m9986a(c1470b, this.f7134a.m9912b(bArr), bArr);
                }
                if (thread != null && thread.isAlive()) {
                    thread.interrupt();
                }
                this.f7134a.m9919h();
            } catch (IOException e2) {
                e = e2;
                try {
                    if (this.f7134a.f7083m.get() != 0) {
                        this.f7134a.m9902b(e.getMessage());
                    }
                    if (thread != null && thread.isAlive()) {
                        thread.interrupt();
                    }
                    this.f7134a.m9919h();
                } catch (Throwable th2) {
                    th = th2;
                    if (thread != null && thread.isAlive()) {
                        thread.interrupt();
                    }
                    this.f7134a.m9919h();
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            thread = null;
            if (this.f7134a.f7083m.get() != 0) {
                this.f7134a.m9902b(e.getMessage());
            }
            thread.interrupt();
            this.f7134a.m9919h();
        } catch (Throwable th3) {
            th = th3;
            thread = null;
            thread.interrupt();
            this.f7134a.m9919h();
            throw th;
        }
    }
}
