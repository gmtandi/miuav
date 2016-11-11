package com.xiaomi.market.sdk;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.connect.common.Constants;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.t */
class C2547t extends AsyncTask {
    private C2547t() {
    }

    private C2550w m14555a(JSONObject jSONObject) {
        if (jSONObject == null) {
            Log.e("MarketUpdateAgent", "update info json obj null");
            return null;
        }
        if (C2546s.DEBUG) {
            Log.d("MarketUpdateAgent", "updateInfo : " + jSONObject.toString());
        }
        C2550w c2550w = new C2550w();
        try {
            c2550w.bi = jSONObject.getString(C2537j.HOST);
            c2550w.bk = jSONObject.getInt(C2537j.at);
            c2550w.bj = jSONObject.getInt(C2537j.au);
            c2550w.updateLog = jSONObject.getString(C2537j.av);
            c2550w.versionCode = jSONObject.getInt(C2537j.aw);
            c2550w.versionName = jSONObject.getString(C2537j.ax);
            c2550w.bl = jSONObject.getString(C2537j.ay);
            c2550w.bm = jSONObject.getString(C2537j.az);
            c2550w.bn = jSONObject.getLong(C2537j.aA);
            if (XiaomiUpdateAgent.bg) {
                c2550w.bo = jSONObject.getString(C2537j.aB);
                c2550w.bp = jSONObject.getString(C2537j.aC);
                c2550w.bq = jSONObject.getLong(C2537j.aD);
            }
            return c2550w;
        } catch (JSONException e) {
            Log.e("MarketUpdateAgent", "get update info failed : " + e.toString());
            Log.e("MarketUpdateAgent", "original content : " + jSONObject.toString());
            return null;
        }
    }

    private String m14556w() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(C2537j.ah, C2528a.f12788b + "*" + C2528a.f12789c);
            jSONObject.put(C2537j.ai, C2528a.f12790d);
            jSONObject.put(C2537j.aj, C2528a.f12791e);
            jSONObject.put(C2537j.ak, C2528a.f12792f);
            jSONObject.put(C2537j.al, C2528a.f12793g);
            jSONObject.put(C2537j.am, C2528a.f12794h);
            jSONObject.put(C2537j.an, C2528a.f12795i);
            jSONObject.put(C2537j.ao, C2528a.f12796j);
            jSONObject.put(C2537j.ap, C2528a.f12797k);
            jSONObject.put(C2537j.aq, C2528a.f12798l);
            jSONObject.put(C2915a.f14757c, C2528a.RELEASE);
            jSONObject.put(C2537j.as, C2529b.m14487a(C2528a.f12799m));
            return jSONObject.toString();
        } catch (JSONException e) {
            return C2915a.f14760f;
        }
    }

    private static void m14557x() {
        if ((XiaomiUpdateAgent.mContext instanceof Activity) && ((Activity) XiaomiUpdateAgent.mContext).isFinishing()) {
            Log.e("MarketUpdateAgent", "activity not running!");
            return;
        }
        CharSequence string;
        Builder title = new Builder(XiaomiUpdateAgent.mContext).setTitle(XiaomiUpdateAgent.mContext.getString(C2546s.m14548b(XiaomiUpdateAgent.mContext.getPackageName(), "string", "xiaomi_update_dialog_title"), new Object[]{XiaomiUpdateAgent.aL.aT}));
        if (TextUtils.isEmpty(XiaomiUpdateAgent.aK.bo)) {
            string = XiaomiUpdateAgent.mContext.getString(C2546s.m14548b(XiaomiUpdateAgent.mContext.getPackageName(), "string", "xiaomi_update_dialog_message"), new Object[]{XiaomiUpdateAgent.aK.versionName, C2546s.m14547a(XiaomiUpdateAgent.aK.bn, XiaomiUpdateAgent.mContext)});
        } else {
            string = XiaomiUpdateAgent.mContext.getString(C2546s.m14548b(XiaomiUpdateAgent.mContext.getPackageName(), "string", "xiaomi_update_dialog_message_diff"), new Object[]{XiaomiUpdateAgent.aK.versionName, C2546s.m14547a(XiaomiUpdateAgent.aK.bq, XiaomiUpdateAgent.mContext)});
        }
        title.setMessage(string).setNegativeButton(17039360, null).setPositiveButton(17039370, new C2548u()).show();
    }

    protected Integer m14559a(Void... voidArr) {
        if (!C2546s.m14552m(XiaomiUpdateAgent.mContext)) {
            return Integer.valueOf(3);
        }
        if (!C2546s.m14553n(XiaomiUpdateAgent.mContext) && XiaomiUpdateAgent.bf) {
            return Integer.valueOf(2);
        }
        C2528a.m14474a(XiaomiUpdateAgent.mContext);
        XiaomiUpdateAgent.aL = XiaomiUpdateAgent.m14466p(XiaomiUpdateAgent.mContext);
        if (XiaomiUpdateAgent.aL == null) {
            return Integer.valueOf(5);
        }
        C2530c c2530c = new C2530c(C2537j.f12838V);
        c2530c.getClass();
        C2536h c2536h = new C2536h(c2530c);
        c2536h.m14519d(C2537j.ag, m14556w());
        c2536h.m14519d(C2537j.f12839W, XiaomiUpdateAgent.aL.packageName);
        c2536h.m14519d(C2537j.aw, new StringBuilder(String.valueOf(XiaomiUpdateAgent.aL.versionCode)).toString());
        c2536h.m14519d(C2537j.az, XiaomiUpdateAgent.aL.aV);
        c2536h.m14519d(C2537j.f12842Z, XiaomiUpdateAgent.aL.aU);
        c2536h.m14519d(C2537j.aa, C2528a.f12800n);
        c2536h.m14519d(C2537j.ap, String.valueOf(C2528a.f12797k));
        c2536h.m14519d(C2537j.ac, C2528a.f12798l);
        c2536h.m14519d(C2537j.ad, C2528a.LANGUAGE);
        c2536h.m14519d(C2537j.ae, C2528a.COUNTRY);
        c2536h.m14519d(C2537j.af, Constants.VIA_RESULT_SUCCESS);
        if (C2535g.OK == c2530c.m14516h()) {
            XiaomiUpdateAgent.aK = m14555a(c2530c.m14512e());
            if (XiaomiUpdateAgent.aK != null) {
                Log.i("MarketUpdateAgent", XiaomiUpdateAgent.aK.toString());
                return Integer.valueOf(XiaomiUpdateAgent.aK.bk == 0 ? 0 : 1);
            }
        }
        return Integer.valueOf(4);
    }

    protected void m14560a(Integer num) {
        if (XiaomiUpdateAgent.be) {
            switch (num.intValue()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    new C2549v().execute(new Void[0]);
                    return;
                default:
                    return;
            }
        }
        UpdateResponse updateResponse = new UpdateResponse();
        if (num.intValue() == 0) {
            updateResponse.updateLog = XiaomiUpdateAgent.aK.updateLog;
            updateResponse.versionCode = XiaomiUpdateAgent.aK.versionCode;
            updateResponse.versionName = XiaomiUpdateAgent.aK.versionName;
            updateResponse.path = C2530c.m14501c(XiaomiUpdateAgent.aK.bi, XiaomiUpdateAgent.aK.bl);
        }
        if (XiaomiUpdateAgent.bh != null) {
            XiaomiUpdateAgent.bh.onUpdateReturned(num.intValue(), updateResponse);
        }
    }

    protected /* synthetic */ Object doInBackground(Object... objArr) {
        return m14559a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m14560a((Integer) obj);
    }

    protected void onPreExecute() {
        Log.d("MarketUpdateAgent", "start to check update");
        if (!XiaomiUpdateAgent.bg) {
            XiaomiUpdateAgent.bg = Patcher.m14461o();
        }
    }
}
