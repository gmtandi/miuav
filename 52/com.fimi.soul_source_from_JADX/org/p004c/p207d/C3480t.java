package org.p004c.p207d;

import java.util.ArrayList;
import java.util.List;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3404g;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.d.t */
class C3480t extends C3377k {
    final /* synthetic */ C3507d f15998a;
    final /* synthetic */ C3377k f15999b;
    final /* synthetic */ C3476s f16000c;

    C3480t(C3476s c3476s, C3507d c3507d, C3377k c3377k) {
        this.f16000c = c3476s;
        this.f15998a = c3507d;
        this.f15999b = c3377k;
    }

    public void m19004a() {
        List arrayList = new ArrayList();
        this.f16000c.m18973b(this.f15998a, arrayList);
        try {
            this.f15999b.m18589a();
            this.f16000c.m18971a(this.f15998a, arrayList);
        } catch (C3398b e) {
            arrayList.add(e);
            this.f16000c.m18967a(e, this.f15998a, arrayList);
        } catch (Throwable th) {
            arrayList.add(th);
            this.f16000c.m18966a(th, this.f15998a, arrayList);
        } finally {
            this.f16000c.m18975c(this.f15998a, arrayList);
        }
        C3404g.m18670a(arrayList);
    }
}
