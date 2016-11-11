package org.p004c.p207d;

import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.d.v */
class C3482v extends C3377k {
    final /* synthetic */ C3524d f16001a;
    final /* synthetic */ C3377k f16002b;
    final /* synthetic */ C3481u f16003c;

    C3482v(C3481u c3481u, C3524d c3524d, C3377k c3377k) {
        this.f16003c = c3481u;
        this.f16001a = c3524d;
        this.f16002b = c3377k;
    }

    public void m19010a() {
        this.f16003c.m19008b(this.f16001a);
        try {
            this.f16002b.m18589a();
            this.f16003c.m19007a(this.f16001a);
            this.f16003c.m19009c(this.f16001a);
        } catch (C3398b e) {
            throw e;
        } catch (Throwable th) {
            this.f16003c.m19009c(this.f16001a);
        }
    }
}
