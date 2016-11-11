package cn.sharesdk.wechat.utils;

import android.text.TextUtils;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.C0205d;
import com.amap.api.services.district.DistrictSearchQuery;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: cn.sharesdk.wechat.utils.i */
class C0234i extends Thread {
    final /* synthetic */ PlatformActionListener f459a;
    final /* synthetic */ C0232g f460b;

    C0234i(C0232g c0232g, PlatformActionListener platformActionListener) {
        this.f460b = c0232g;
        this.f459a = platformActionListener;
    }

    public void run() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f460b.f454d.getDb().getToken()));
        arrayList.add(new KVPair(SocialConstants.PARAM_OPEN_ID, this.f460b.f454d.getDb().get(SocialConstants.PARAM_OPEN_ID)));
        String a = this.f460b.f453c.m417a("https://api.weixin.qq.com/sns/userinfo", arrayList, "/sns/userinfo", this.f460b.f455e);
        if (!TextUtils.isEmpty(a)) {
            C0205d.m752a().m737d("getUserInfo ==>>" + a, new Object[0]);
            HashMap fromJson = new Hashon().fromJson(a);
            if (!fromJson.containsKey("errcode") || ((Integer) fromJson.get("errcode")).intValue() == 0) {
                String valueOf;
                String valueOf2;
                int parseInt;
                try {
                    valueOf = String.valueOf(fromJson.get(SocialConstants.PARAM_OPEN_ID));
                    valueOf2 = String.valueOf(fromJson.get(RContact.COL_NICKNAME));
                    parseInt = C2178R.parseInt(String.valueOf(fromJson.get("sex")));
                } catch (Throwable th) {
                    C0205d.m752a().m738d(th);
                    return;
                }
                String valueOf3 = String.valueOf(fromJson.get(DistrictSearchQuery.KEYWORDS_PROVINCE));
                String valueOf4 = String.valueOf(fromJson.get(DistrictSearchQuery.KEYWORDS_CITY));
                String valueOf5 = String.valueOf(fromJson.get(DistrictSearchQuery.KEYWORDS_COUNTRY));
                String valueOf6 = String.valueOf(fromJson.get("headimgurl"));
                String valueOf7 = String.valueOf(fromJson.get(GameAppOperation.GAME_UNION_ID));
                this.f460b.f454d.getDb().put(RContact.COL_NICKNAME, valueOf2);
                if (parseInt == 1) {
                    this.f460b.f454d.getDb().put("gender", Constants.VIA_RESULT_SUCCESS);
                } else if (parseInt == 2) {
                    this.f460b.f454d.getDb().put("gender", Constants.VIA_TO_TYPE_QQ_GROUP);
                } else {
                    this.f460b.f454d.getDb().put("gender", Constants.VIA_SSO_LOGIN);
                }
                this.f460b.f454d.getDb().putUserId(valueOf);
                this.f460b.f454d.getDb().put("icon", valueOf6);
                this.f460b.f454d.getDb().put(DistrictSearchQuery.KEYWORDS_PROVINCE, valueOf3);
                this.f460b.f454d.getDb().put(DistrictSearchQuery.KEYWORDS_CITY, valueOf4);
                this.f460b.f454d.getDb().put(DistrictSearchQuery.KEYWORDS_COUNTRY, valueOf5);
                this.f460b.f454d.getDb().put(SocialConstants.PARAM_OPEN_ID, valueOf);
                this.f460b.f454d.getDb().put(GameAppOperation.GAME_UNION_ID, valueOf7);
                this.f459a.onComplete(this.f460b.f454d, 8, fromJson);
            } else if (this.f459a != null) {
                this.f459a.onError(this.f460b.f454d, 8, new Throwable(new Hashon().fromHashMap(fromJson)));
            }
        } else if (this.f459a != null) {
            this.f459a.onError(this.f460b.f454d, 8, new Throwable());
        }
    }
}
