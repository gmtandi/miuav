package com.fimi.soul.utils;

import android.os.AsyncTask;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.login.LoginActivity;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import org.json.JSONException;
import org.json.JSONObject;

class ap extends AsyncTask<Void, Void, String> {
    Exception f10040a;
    final /* synthetic */ XiaomiOAuthFuture f10041b;
    final /* synthetic */ am f10042c;

    ap(am amVar, XiaomiOAuthFuture xiaomiOAuthFuture) {
        this.f10042c = amVar;
        this.f10041b = xiaomiOAuthFuture;
    }

    protected String m12279a(Void... voidArr) {
        try {
            return (String) this.f10041b.getResult();
        } catch (Exception e) {
            this.f10040a = e;
            return null;
        } catch (Exception e2) {
            this.f10040a = e2;
            return null;
        } catch (Exception e22) {
            this.f10040a = e22;
            return null;
        }
    }

    protected void m12280a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject(UriUtil.DATA_SCHEME);
                if (jSONObject.getString("phone") != null && jSONObject.getString("phone").length() > 0) {
                    this.f10042c.f10031c.setPhone(jSONObject.getString("phone"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12279a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12280a((String) obj);
    }

    protected void onPreExecute() {
        C1236a.m8532a("waiting for Future result...", LoginActivity.class);
    }
}
