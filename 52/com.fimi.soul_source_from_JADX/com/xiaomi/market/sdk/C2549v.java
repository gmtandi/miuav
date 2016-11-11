package com.xiaomi.market.sdk;

import android.os.AsyncTask;

/* renamed from: com.xiaomi.market.sdk.v */
class C2549v extends AsyncTask {
    private C2549v() {
    }

    protected void m14561a(Boolean bool) {
        if (!bool.booleanValue()) {
            C2547t.m14557x();
        }
    }

    protected Boolean m14562b(Void... voidArr) {
        return Boolean.valueOf(C2540m.m14532i(XiaomiUpdateAgent.mContext).m14535a(XiaomiUpdateAgent.aL));
    }

    protected /* synthetic */ Object doInBackground(Object... objArr) {
        return m14562b((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m14561a((Boolean) obj);
    }
}
