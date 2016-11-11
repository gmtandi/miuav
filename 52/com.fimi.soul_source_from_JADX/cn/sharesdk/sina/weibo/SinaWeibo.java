package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class SinaWeibo extends Platform {
    public static final String NAME;
    private String f386a;
    private String f387b;
    private String f388c;
    private boolean f389d;

    public class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String imageUrl;
        @Deprecated
        public float latitude;
        @Deprecated
        public float longitude;
    }

    static {
        NAME = SinaWeibo.class.getSimpleName();
    }

    public SinaWeibo(Context context) {
        super(context);
    }

    protected boolean checkAuthorize(int i, Object obj) {
        C0217g a = C0217g.m803a((Platform) this);
        if (i == 9 && this.f389d && a.m819a()) {
            return true;
        }
        if (isAuthValid()) {
            a.m817a(this.f386a, this.f387b);
            a.m821b(this.db.getToken());
            return true;
        }
        innerAuthorize(i, obj);
        return false;
    }

    protected void doAuthorize(String[] strArr) {
        C0217g a = C0217g.m803a((Platform) this);
        a.m817a(this.f386a, this.f387b);
        a.m816a(this.f388c);
        a.m818a(strArr);
        a.m815a(new C0213c(this, a), isSSODisable());
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        try {
            HashMap a = C0217g.m803a((Platform) this).m813a(str, str2, (HashMap) hashMap, (HashMap) hashMap2);
            if (a == null || a.size() <= 0) {
                if (this.listener != null) {
                    this.listener.onError(this, i, new Throwable());
                }
            } else if (!a.containsKey("error_code") || ((Integer) a.get("error_code")).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, i, a);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, i, new Throwable(new Hashon().fromHashMap(a)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, i, th);
        }
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        String string;
        C0217g a;
        String imagePath;
        String imageUrl;
        HashMap a2;
        CharSequence text = shareParams.getText();
        if (TextUtils.isEmpty(text)) {
            int stringRes = C2178R.getStringRes(getContext(), "ssdk_weibo_upload_content");
            if (stringRes > 0) {
                string = getContext().getString(stringRes);
                a = C0217g.m803a((Platform) this);
                a.m817a(this.f386a, this.f387b);
                imagePath = shareParams.getImagePath();
                imageUrl = shareParams.getImageUrl();
                if (!this.f389d && a.m819a() && a.m822b()) {
                    try {
                        a.m814a(shareParams, this.listener);
                        return;
                    } catch (Throwable th) {
                        this.listener.onError(this, 9, th);
                        return;
                    }
                }
                float latitude = shareParams.getLatitude();
                a2 = a.m812a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude);
                if (a2 != null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 9, new Throwable());
                    }
                } else if (a2.containsKey("error_code") || ((Integer) a2.get("error_code")).intValue() == 0) {
                    a2.put("ShareParams", shareParams);
                    if (this.listener != null) {
                        this.listener.onComplete(this, 9, a2);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 9, new Throwable(new Hashon().fromHashMap(a2)));
                    return;
                } else {
                    return;
                }
            }
        }
        CharSequence charSequence = text;
        a = C0217g.m803a((Platform) this);
        a.m817a(this.f386a, this.f387b);
        imagePath = shareParams.getImagePath();
        imageUrl = shareParams.getImageUrl();
        if (!this.f389d) {
        }
        try {
            float latitude2 = shareParams.getLatitude();
            a2 = a.m812a(getShortLintk(string, false), imageUrl, imagePath, shareParams.getLongitude(), latitude2);
            if (a2 != null) {
                if (a2.containsKey("error_code")) {
                }
                a2.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, a2);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 9, new Throwable());
            }
        } catch (Throwable th2) {
            this.listener.onError(this, 9, th2);
        }
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap();
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                hashMap2.put(SocialConstants.PARAM_TYPE, "FOLLOWING");
                break;
            case Type.OBJECT /*10*/:
                hashMap2.put(SocialConstants.PARAM_TYPE, "FRIENDS");
                break;
            case Opcodes.T_LONG /*11*/:
                hashMap2.put(SocialConstants.PARAM_TYPE, "FOLLOWERS");
                break;
            default:
                return null;
        }
        hashMap2.put("snsplat", Integer.valueOf(getPlatformId()));
        hashMap2.put("snsuid", this.db.getUserId());
        int parseInt = Integer.parseInt(String.valueOf(hashMap.get("current_cursor")));
        int parseInt2 = Integer.parseInt(String.valueOf(hashMap.get("total_number")));
        if (parseInt2 == 0) {
            return null;
        }
        Object obj = hashMap.get("users");
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) obj;
        if (arrayList2.size() <= 0) {
            return null;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                HashMap hashMap4 = new HashMap();
                hashMap4.put("snsuid", String.valueOf(hashMap3.get(LocaleUtil.INDONESIAN)));
                hashMap4.put(RContact.COL_NICKNAME, String.valueOf(hashMap3.get("screen_name")));
                hashMap4.put("icon", String.valueOf(hashMap3.get("avatar_hd")));
                if (String.valueOf(hashMap3.get("verified")).equals("true")) {
                    hashMap4.put("secretType", Constants.VIA_TO_TYPE_QQ_GROUP);
                } else {
                    hashMap4.put("secretType", Constants.VIA_RESULT_SUCCESS);
                }
                hashMap4.put(OAuth.SECRET, String.valueOf(hashMap3.get("verified_reason")));
                String valueOf = String.valueOf(hashMap3.get("gender"));
                if (valueOf.equals("m")) {
                    hashMap4.put("gender", Constants.VIA_RESULT_SUCCESS);
                } else if (valueOf.equals("f")) {
                    hashMap4.put("gender", Constants.VIA_TO_TYPE_QQ_GROUP);
                } else {
                    hashMap4.put("gender", Constants.VIA_SSO_LOGIN);
                }
                hashMap4.put("snsUserUrl", "http://weibo.com/" + String.valueOf(hashMap3.get("profile_url")));
                hashMap4.put("resume", String.valueOf(hashMap3.get(SocialConstants.PARAM_COMMENT)));
                hashMap4.put("followerCount", String.valueOf(hashMap3.get("followers_count")));
                hashMap4.put("favouriteCount", String.valueOf(hashMap3.get("friends_count")));
                hashMap4.put("shareCount", String.valueOf(hashMap3.get("statuses_count")));
                hashMap4.put("snsregat", String.valueOf(C2178R.dateToLong(String.valueOf(hashMap3.get("created_at")))));
                arrayList.add(hashMap4);
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        if (10 == i) {
            hashMap2.put("nextCursor", ((Integer) hashMap.get("page_count")).intValue() * (parseInt + 1) >= parseInt2 ? parseInt + "_true" : (parseInt + 1) + "_false");
        } else {
            int size = arrayList.size() + parseInt;
            hashMap2.put("nextCursor", size >= parseInt2 ? parseInt2 + "_true" : size + "_false");
        }
        hashMap2.put("list", arrayList);
        return hashMap2;
    }

    protected C0176a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0176a c0176a = new C0176a();
        c0176a.f261b = shareParams.getText();
        if (hashMap != null) {
            c0176a.f260a = String.valueOf(hashMap.get(LocaleUtil.INDONESIAN));
            c0176a.f263d.add(String.valueOf(hashMap.get("original_pic")));
            c0176a.f266g = hashMap;
        }
        return c0176a;
    }

    protected void follow(String str) {
        try {
            HashMap d = C0217g.m803a((Platform) this).m826d(str);
            if (d == null) {
                if (this.listener != null) {
                    this.listener.onError(this, 6, new Throwable());
                }
            } else if (!d.containsKey("error_code") || ((Integer) d.get("error_code")).intValue() == 0) {
                if (this.listener != null) {
                    this.listener.onComplete(this, 6, d);
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 6, new Throwable(new Hashon().fromHashMap(d)));
            }
        } catch (Throwable th) {
            this.listener.onError(this, 6, th);
        }
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            Object userId = this.db.getUserId();
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.get(RContact.COL_NICKNAME);
        }
        if (!TextUtils.isEmpty(userId)) {
            try {
                HashMap c = C0217g.m803a((Platform) this).m823c(i, i2, userId);
                if (!(c == null || c.containsKey("error_code"))) {
                    c.put("page_count", Integer.valueOf(i));
                    c.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(10, c);
                }
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            Object userId = this.db.getUserId();
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.get(RContact.COL_NICKNAME);
        }
        if (!TextUtils.isEmpty(userId)) {
            try {
                HashMap d = C0217g.m803a((Platform) this).m825d(i, i2, userId);
                if (!(d == null || d.containsKey("error_code"))) {
                    d.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(11, d);
                }
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return hashMap;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            Object userId = this.db.getUserId();
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.get(RContact.COL_NICKNAME);
        }
        if (!TextUtils.isEmpty(userId)) {
            try {
                HashMap b = C0217g.m803a((Platform) this).m820b(i, i2, userId);
                if (!(b == null || b.containsKey("error_code"))) {
                    b.put("current_cursor", Integer.valueOf(i2));
                    hashMap = filterFriendshipInfo(2, b);
                }
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return hashMap;
    }

    protected void getFriendList(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            Object userId = this.db.getUserId();
        }
        if (TextUtils.isEmpty(userId)) {
            userId = this.db.get(RContact.COL_NICKNAME);
        }
        if (!TextUtils.isEmpty(userId)) {
            try {
                HashMap b = C0217g.m803a((Platform) this).m820b(i, i2, userId);
                if (b == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 2, new Throwable());
                    }
                } else if (!b.containsKey("error_code") || ((Integer) b.get("error_code")).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 2, b);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 2, new Throwable(new Hashon().fromHashMap(b)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 2, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    public String getName() {
        return NAME;
    }

    protected int getPlatformId() {
        return 1;
    }

    public int getVersion() {
        return 1;
    }

    public boolean hasShareCallback() {
        return true;
    }

    protected void initDevInfo(String str) {
        this.f386a = getDevinfo("AppKey");
        this.f387b = getDevinfo("AppSecret");
        this.f388c = getDevinfo("RedirectUrl");
        this.f389d = "true".equals(getDevinfo("ShareByAppClient"));
    }

    public boolean isClientValid() {
        return C0217g.m803a((Platform) this).m819a();
    }

    protected void setNetworkDevinfo() {
        this.f386a = getNetworkDevinfo("app_key", "AppKey");
        this.f387b = getNetworkDevinfo("app_secret", "AppSecret");
        this.f388c = getNetworkDevinfo(AuthorizeActivityBase.KEY_REDIRECT_URI, "RedirectUrl");
    }

    protected void timeline(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(RContact.COL_NICKNAME);
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap a = C0217g.m803a((Platform) this).m811a(i, i2, str);
                if (a == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 7, new Throwable());
                    }
                } else if (!a.containsKey("error_code") || ((Integer) a.get("error_code")).intValue() == 0) {
                    if (this.listener != null) {
                        this.listener.onComplete(this, 7, a);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 7, new Throwable(new Hashon().fromHashMap(a)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 7, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }

    protected void userInfor(String str) {
        Object obj = 1;
        Object obj2 = null;
        if (TextUtils.isEmpty(str)) {
            str = this.db.getUserId();
            obj2 = 1;
        }
        if (TextUtils.isEmpty(str)) {
            str = this.db.get(RContact.COL_NICKNAME);
        } else {
            obj = obj2;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap c = C0217g.m803a((Platform) this).m824c(str);
                if (c == null) {
                    if (this.listener != null) {
                        this.listener.onError(this, 8, new Throwable());
                    }
                } else if (!c.containsKey("error_code") || ((Integer) c.get("error_code")).intValue() == 0) {
                    if (obj != null) {
                        this.db.putUserId(String.valueOf(c.get(LocaleUtil.INDONESIAN)));
                        this.db.put(RContact.COL_NICKNAME, String.valueOf(c.get("screen_name")));
                        this.db.put("icon", String.valueOf(c.get("avatar_hd")));
                        if (String.valueOf(c.get("verified")).equals("true")) {
                            this.db.put("secretType", Constants.VIA_TO_TYPE_QQ_GROUP);
                        } else {
                            this.db.put("secretType", Constants.VIA_RESULT_SUCCESS);
                        }
                        this.db.put(OAuth.SECRET, String.valueOf(c.get("verified_reason")));
                        String valueOf = String.valueOf(c.get("gender"));
                        if (valueOf.equals("m")) {
                            this.db.put("gender", Constants.VIA_RESULT_SUCCESS);
                        } else if (valueOf.equals("f")) {
                            this.db.put("gender", Constants.VIA_TO_TYPE_QQ_GROUP);
                        } else {
                            this.db.put("gender", Constants.VIA_SSO_LOGIN);
                        }
                        this.db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(c.get("profile_url")));
                        this.db.put("resume", String.valueOf(c.get(SocialConstants.PARAM_COMMENT)));
                        this.db.put("followerCount", String.valueOf(c.get("followers_count")));
                        this.db.put("favouriteCount", String.valueOf(c.get("friends_count")));
                        this.db.put("shareCount", String.valueOf(c.get("statuses_count")));
                        this.db.put("snsregat", String.valueOf(C2178R.dateToLong(String.valueOf(c.get("created_at")))));
                    }
                    if (this.listener != null) {
                        this.listener.onComplete(this, 8, c);
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 8, new Throwable(new Hashon().fromHashMap(c)));
                }
            } catch (Throwable th) {
                this.listener.onError(this, 8, th);
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
        }
    }
}
