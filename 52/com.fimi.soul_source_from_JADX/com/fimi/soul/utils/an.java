package com.fimi.soul.utils;

import android.os.AsyncTask;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p099f.C1333a;
import com.fimi.soul.module.login.LoginActivity;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;

class an extends AsyncTask<Void, Void, V> {
    Exception f10036a;
    final /* synthetic */ XiaomiOAuthFuture f10037b;
    final /* synthetic */ am f10038c;

    an(am amVar, XiaomiOAuthFuture xiaomiOAuthFuture) {
        this.f10038c = amVar;
        this.f10037b = xiaomiOAuthFuture;
    }

    protected V m12277a(Void... voidArr) {
        V v = null;
        try {
            v = this.f10037b.getResult();
        } catch (Exception e) {
            this.f10036a = e;
        } catch (Exception e2) {
            this.f10036a = e2;
        } catch (Exception e22) {
            this.f10036a = e22;
        }
        return v;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12277a((Void[]) objArr);
    }

    protected void onPostExecute(V v) {
        if (v != null) {
            if (v instanceof XiaomiOAuthResults) {
                this.f10038c.f10030b = (XiaomiOAuthResults) v;
                if (this.f10038c.f10030b.getAccessToken() != null) {
                    C1333a.m8959c(C1189f.m8327a(), 0, this.f10038c.f10030b.getAccessToken());
                    C1333a.m8957b(C1189f.m8327a(), 0, this.f10038c.f10030b.getMacKey());
                    C1333a.m8955a(C1189f.m8327a(), 0, this.f10038c.f10030b.getMacAlgorithm());
                    this.f10038c.m12268a();
                } else if (this.f10038c.f10034f != null) {
                    this.f10038c.f10034f.m11536a(this.f10038c.f10033e.getResources().getString(C1205R.string.login_result));
                }
            }
        } else if (this.f10036a != null) {
            if (this.f10038c.f10034f != null) {
                this.f10038c.f10034f.m11537b();
            }
        } else if (this.f10038c.f10034f != null) {
            this.f10038c.f10034f.m11536a(this.f10038c.f10033e.getResources().getString(C1205R.string.login_result));
        }
    }

    protected void onPreExecute() {
        C1236a.m8532a("waiting for Future result...", LoginActivity.class);
    }
}
