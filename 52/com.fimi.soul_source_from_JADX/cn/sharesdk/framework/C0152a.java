package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.p013b.C0182e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.p013b.p015b.C0177f;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import cn.sharesdk.framework.utils.C0205d;
import com.fimi.soul.entity.User;
import com.mob.tools.utils.Data;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import java.util.HashMap;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.a */
public class C0152a implements PlatformActionListener {
    private PlatformActionListener f191a;
    private HashMap<Platform, ShareParams> f192b;

    C0152a() {
        this.f192b = new HashMap();
    }

    private String m425a(Platform platform) {
        try {
            return m426a(platform.getDb(), new String[]{RContact.COL_NICKNAME, "icon", "gender", "snsUserUrl", "resume", "secretType", OAuth.SECRET, "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return null;
        }
    }

    private String m426a(PlatformDb platformDb, String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            if (i > 0) {
                stringBuilder2.append('|');
                stringBuilder.append('|');
            }
            i++;
            String str2 = platformDb.get(str2);
            if (!TextUtils.isEmpty(str2)) {
                stringBuilder.append(str2);
                stringBuilder2.append(Data.urlEncode(str2, "utf-8"));
            }
        }
        C0205d.m752a().m743i("======UserData: " + stringBuilder.toString(), new Object[0]);
        return stringBuilder2.toString();
    }

    private void m428a(int i, Platform platform) {
    }

    private void m429a(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.f191a = new C0183b(this, this.f191a, i, hashMap);
        platform.showUser(null);
    }

    private String m431b(Platform platform) {
        PlatformDb db = platform.getDb();
        if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(db.getUserGender())) {
            Platform platform2 = ShareSDK.getPlatform("Wechat");
            if (platform2 != null) {
                db = platform2.getDb();
            }
        }
        try {
            return m426a(db, new String[]{"gender", "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr"});
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return null;
        }
    }

    private void m432b(Platform platform, int i, HashMap<String, Object> hashMap) {
        ShareParams shareParams = hashMap != null ? (ShareParams) hashMap.remove("ShareParams") : (ShareParams) this.f192b.remove(platform);
        try {
            HashMap hashMap2 = (HashMap) hashMap.clone();
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            HashMap<String, Object> hashMap3 = hashMap;
        }
        if (shareParams != null) {
            C0171c c0177f = new C0177f();
            c0177f.f274o = shareParams.getCustomFlag();
            String userId = platform.getDb().getUserId();
            if (("WechatMoments".equals(platform.getName()) || "WechatFavorite".equals(platform.getName())) && TextUtils.isEmpty(userId)) {
                Platform platform2 = ShareSDK.getPlatform("Wechat");
                if (platform2 != null) {
                    userId = platform2.getDb().getUserId();
                }
            } else if ("TencentWeibo".equals(platform.getName())) {
                userId = platform.getDb().get(User.FN_NAME);
            }
            c0177f.f270b = userId;
            c0177f.f269a = platform.getPlatformId();
            C0176a filterShareContent = platform.filterShareContent(shareParams, hashMap2);
            if (filterShareContent != null) {
                c0177f.f271c = filterShareContent.f260a;
                c0177f.f272d = filterShareContent;
            }
            c0177f.f273n = m431b(platform);
            C0182e.m613a(platform.getContext(), c0177f.g).m622a(c0177f);
        }
        if (this.f191a != null) {
            try {
                this.f191a.onComplete(platform, i, hashMap);
            } catch (Throwable th2) {
                C0205d.m752a().m738d(th2);
            }
        }
        m428a(9, platform);
    }

    PlatformActionListener m433a() {
        return this.f191a;
    }

    void m434a(Platform platform, int i, Object obj) {
        this.f191a = new C0184c(this, this.f191a, i, obj);
        platform.doAuthorize(null);
    }

    public void m435a(Platform platform, ShareParams shareParams) {
        this.f192b.put(platform, shareParams);
    }

    void m436a(PlatformActionListener platformActionListener) {
        this.f191a = platformActionListener;
    }

    public void onCancel(Platform platform, int i) {
        if (this.f191a != null) {
            this.f191a.onCancel(platform, i);
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (!(platform instanceof CustomPlatform)) {
            switch (i) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m429a(platform, i, (HashMap) hashMap);
                case Type.ARRAY /*9*/:
                    m432b(platform, i, hashMap);
                default:
                    if (this.f191a != null) {
                        this.f191a.onComplete(platform, i, hashMap);
                    }
            }
        } else if (this.f191a != null) {
            this.f191a.onComplete(platform, i, hashMap);
        }
    }

    public void onError(Platform platform, int i, Throwable th) {
        if (this.f191a != null) {
            this.f191a.onError(platform, i, th);
        }
    }
}
