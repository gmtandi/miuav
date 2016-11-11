package com.fimi.soul.biz.camera;

import com.fimi.soul.utils.be;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.h */
class C1301h implements Runnable {
    final /* synthetic */ C1299f f5825a;

    C1301h(C1299f c1299f) {
        this.f5825a = c1299f;
    }

    public void run() {
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                Thread.sleep(1500);
                if (this.f5825a.f5800B) {
                    if (this.f5825a.f5806b.m8944i()) {
                        this.f5825a.f5823z = this.f5825a.f5806b.m8943h();
                    } else {
                        this.f5825a.f5823z = be.m12380d(C1314u.f5874a);
                        this.f5825a.f5806b.m8934a(this.f5825a.f5823z);
                    }
                    if (this.f5825a.f5823z) {
                        i2++;
                        i = 0;
                    } else {
                        i++;
                        i2 = 0;
                    }
                    if (i > 1) {
                        if (this.f5825a.m8848d()) {
                            this.f5825a.m8827a(-1);
                        }
                        this.f5825a.m7685a().sendEmptyMessageDelayed(2049, 100);
                    } else if (!this.f5825a.m8848d() && r2 > 1) {
                        if (System.currentTimeMillis() - this.f5825a.f5822y > 7500) {
                            this.f5825a.f5822y = System.currentTimeMillis();
                            this.f5825a.m8860p();
                        }
                        this.f5825a.m7685a().sendEmptyMessageDelayed(Opcodes.ACC_STRICT, 500);
                    } else if (this.f5825a.m8848d()) {
                        if (C2915a.f14760f.equalsIgnoreCase(this.f5825a.m8853i().getDvVersion())) {
                            this.f5825a.f5805a = (C1313t) C1276b.m8680a().m8699d();
                            this.f5825a.f5805a.m8873r().m8758k();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
