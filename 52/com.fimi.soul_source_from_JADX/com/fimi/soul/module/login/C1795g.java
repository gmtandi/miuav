package com.fimi.soul.module.login;

import android.os.AsyncTask;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.module.login.g */
class C1795g extends AsyncTask<Void, Void, Boolean> {
    final /* synthetic */ LoginActivity f8851a;

    C1795g(LoginActivity loginActivity) {
        this.f8851a = loginActivity;
    }

    protected Boolean m11532a(Void... voidArr) {
        int i = 0;
        if (!this.f8851a.drone.m9570Q()) {
            this.f8851a.f8795b = false;
            for (int i2 = 0; i2 < 8 && !this.f8851a.f8795b; i2++) {
                this.f8851a.f8795b = be.m12380d(C1543c.f7237l);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            if (this.f8851a.f8795b) {
                while (i < 5) {
                    if (this.f8851a.f8795b) {
                        this.f8851a.m11497h();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e2) {
                    }
                    if (this.f8851a.drone.m9569P().m9995a()) {
                        break;
                    }
                    this.f8851a.drone.m9569P().m9999d();
                    i++;
                }
                if (!this.f8851a.drone.m9570Q()) {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e3) {
                    }
                }
            }
        }
        return Boolean.valueOf(this.f8851a.drone.m9569P().m9995a());
    }

    protected void m11533a(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            this.f8851a.f8790K.removeMessages(0);
            this.f8851a.f8790K.removeCallbacks(this.f8851a.f8792M);
            this.f8851a.f8790K.sendEmptyMessageDelayed(1, 100);
            return;
        }
        this.f8851a.f8790K.sendEmptyMessage(0);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11532a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11533a((Boolean) obj);
    }
}
