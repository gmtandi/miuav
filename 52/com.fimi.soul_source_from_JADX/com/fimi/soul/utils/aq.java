package com.fimi.soul.utils;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.login.LoginActivity;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import org.json.JSONException;
import org.json.JSONObject;

class aq extends AsyncTask<Void, Void, String> {
    Exception f10043a;
    final /* synthetic */ XiaomiOAuthFuture f10044b;
    final /* synthetic */ am f10045c;

    aq(am amVar, XiaomiOAuthFuture xiaomiOAuthFuture) {
        this.f10045c = amVar;
        this.f10044b = xiaomiOAuthFuture;
    }

    protected String m12281a(Void... voidArr) {
        try {
            return (String) this.f10044b.getResult();
        } catch (Exception e) {
            this.f10043a = e;
            return null;
        } catch (Exception e2) {
            this.f10043a = e2;
            return null;
        } catch (Exception e22) {
            this.f10043a = e22;
            return null;
        }
    }

    protected void m12282a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject(UriUtil.DATA_SCHEME);
                this.f10045c.f10031c.setXiaomiID(jSONObject.getString("userId"));
                this.f10045c.f10031c.setName(jSONObject.getString("miliaoNick"));
                this.f10045c.f10031c.setNickName(jSONObject.getString("miliaoNick"));
                this.f10045c.f10031c.setDevice(be.m12353a(C1189f.m8327a()));
                if (jSONObject.has("sex")) {
                    if (jSONObject.getString("sex").equals(this.f10045c.f10033e.getResources().getString(C1205R.string.man))) {
                        this.f10045c.f10031c.setSex(Constants.VIA_TO_TYPE_QQ_GROUP);
                    } else if (jSONObject.getString("sex").equals(this.f10045c.f10033e.getResources().getString(C1205R.string.woman))) {
                        this.f10045c.f10031c.setSex(Constants.VIA_RESULT_SUCCESS);
                    }
                }
                if (jSONObject.has("miliaoIcon_orig")) {
                    this.f10045c.f10031c.setUserImgUrl(jSONObject.getString("miliaoIcon_orig"));
                }
                this.f10045c.f10032d.m9280e(this.f10045c.f10031c, new ar(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (this.f10045c.f10034f != null) {
            this.f10045c.f10034f.m11536a(this.f10045c.f10033e.getResources().getString(C1205R.string.login_result));
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12281a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12282a((String) obj);
    }

    protected void onPreExecute() {
        C1236a.m8532a("waiting for Future result...", LoginActivity.class);
    }
}
