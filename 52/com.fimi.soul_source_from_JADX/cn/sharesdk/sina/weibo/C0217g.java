package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.sharesdk.framework.C0185e;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.C0155b;
import cn.sharesdk.framework.authorize.C0158e;
import cn.sharesdk.framework.authorize.C0159f;
import cn.sharesdk.framework.authorize.C0161g;
import cn.sharesdk.framework.p012a.C0151a;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

/* renamed from: cn.sharesdk.sina.weibo.g */
public class C0217g extends C0185e {
    private static C0217g f405b;
    private String f406c;
    private String f407d;
    private String f408e;
    private String f409f;
    private String[] f410g;
    private C0151a f411h;
    private Context f412i;

    private C0217g(Platform platform) {
        super(platform);
        this.f410g = new String[]{"follow_app_official_microblog"};
        this.f411h = C0151a.m412a();
        this.f412i = platform.getContext();
    }

    public static synchronized C0217g m803a(Platform platform) {
        C0217g c0217g;
        synchronized (C0217g.class) {
            if (f405b == null) {
                f405b = new C0217g(platform);
            }
            c0217g = f405b;
        }
        return c0217g;
    }

    private HashMap<String, Object> m804a(String str, float f, float f2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        arrayList.add(new KVPair(RMsgInfo.COL_STATUS, str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair("lat", String.valueOf(f2)));
        String b = this.f411h.m421b("https://api.weibo.com/2/statuses/update.json", arrayList, "/2/statuses/update.json", m626c());
        return b != null ? new Hashon().fromJson(b) : null;
    }

    private HashMap<String, Object> m805a(String str, String str2, float f, float f2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        arrayList.add(new KVPair(RMsgInfo.COL_STATUS, str));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair("lat", String.valueOf(f2)));
        arrayList.add(new KVPair(SocialConstants.PARAM_URL, str2));
        String b = this.f411h.m421b("https://api.weibo.com/2/statuses/upload_url_text.json", arrayList, "/2/statuses/upload_url_text.json", m626c());
        return b != null ? new Hashon().fromJson(b) : null;
    }

    private HashMap<String, Object> m808b(String str, String str2, float f, float f2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        arrayList.add(new KVPair(RMsgInfo.COL_STATUS, str2));
        arrayList.add(new KVPair("long", String.valueOf(f)));
        arrayList.add(new KVPair("lat", String.valueOf(f2)));
        String a = this.f411h.m414a("https://api.weibo.com/2/statuses/upload.json", arrayList, new KVPair("pic", str), "/2/statuses/upload.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public String m810a(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(Constants.PARAM_CLIENT_ID, this.f406c));
        arrayList.add(new KVPair("client_secret", this.f407d));
        arrayList.add(new KVPair(AuthorizeActivityBase.KEY_REDIRECT_URI, this.f408e));
        arrayList.add(new KVPair("grant_type", "authorization_code"));
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_CODE_2, str));
        String b = this.f411h.m421b("https://api.weibo.com/oauth2/access_token", arrayList, "/oauth2/access_token", m626c());
        ShareSDK.logApiEvent("/oauth2/access_token", m626c());
        return b;
    }

    public HashMap<String, Object> m811a(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.f411h.m417a("https://api.weibo.com/2/statuses/user_timeline.json", arrayList, "/2/statuses/user_timeline.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public HashMap<String, Object> m812a(String str, String str2, String str3, float f, float f2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return !TextUtils.isEmpty(str3) ? m808b(str3, str, f, f2) : !TextUtils.isEmpty(str2) ? m805a(str, str2, f, f2) : m804a(str, f, f2);
        } else {
            throw new Throwable("weibo content can not be null!");
        }
    }

    public HashMap<String, Object> m813a(String str, String str2, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (str2 == null) {
            return null;
        }
        KVPair kVPair;
        String httpGet;
        ArrayList arrayList = new ArrayList();
        if (hashMap != null && hashMap.size() > 0) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new KVPair((String) entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        if (this.f409f != null) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        }
        if (hashMap2 == null || hashMap2.size() <= 0) {
            kVPair = null;
        } else {
            HashMap<String, Object> hashMap3 = null;
            for (Entry entry2 : hashMap2.entrySet()) {
                Object kVPair2 = new KVPair((String) entry2.getKey(), entry2.getValue());
            }
            kVPair = hashMap3;
        }
        try {
            if (C2951i.f14860a.equals(str2.toUpperCase())) {
                httpGet = new NetworkHelper().httpGet(str, arrayList, null, null);
            } else {
                if (C2955m.f14864a.equals(str2.toUpperCase())) {
                    httpGet = new NetworkHelper().httpPost(str, arrayList, kVPair, null, null);
                }
                httpGet = null;
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
        return (httpGet == null || httpGet.length() <= 0) ? null : new Hashon().fromJson(httpGet);
    }

    public void m814a(ShareParams shareParams, PlatformActionListener platformActionListener) {
        if (shareParams.getImageData() == null && TextUtils.isEmpty(shareParams.getImagePath()) && !TextUtils.isEmpty(shareParams.getImageUrl())) {
            try {
                File file = new File(BitmapHelper.downloadBitmap(this.f412i, shareParams.getImageUrl()));
                if (file.exists()) {
                    shareParams.setImagePath(file.getAbsolutePath());
                }
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        Object text = shareParams.getText();
        if (!TextUtils.isEmpty(text)) {
            shareParams.setText(getPlatform().getShortLintk(text, false));
        }
        AuthorizeListener c0219i = new C0219i(this, platformActionListener, shareParams);
        C0211a c0211a = new C0211a();
        c0211a.m788a(this.f406c);
        c0211a.m786a(shareParams);
        c0211a.m787a(c0219i);
        c0211a.show(this.f412i, null);
    }

    public void m815a(AuthorizeListener authorizeListener, boolean z) {
        if (z) {
            m625b(authorizeListener);
        } else {
            m624a(new C0218h(this, authorizeListener));
        }
    }

    public void m816a(String str) {
        this.f408e = str;
    }

    public void m817a(String str, String str2) {
        this.f406c = str;
        this.f407d = str2;
    }

    public void m818a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.f410g = strArr;
        }
    }

    public boolean m819a() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("com.sina.weibo");
        intent.setType("image/*");
        return this.a.getContext().getPackageManager().resolveActivity(intent, 0) != null;
    }

    public HashMap<String, Object> m820b(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        if (this.f409f != null) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        }
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.f411h.m417a("https://api.weibo.com/2/friendships/friends.json", arrayList, "/2/friendships/friends.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public void m821b(String str) {
        this.f409f = str;
    }

    public boolean m822b() {
        return C0221j.m834a(this.f412i).m842b() >= 10350;
    }

    public HashMap<String, Object> m823c(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        if (this.f409f != null) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        }
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("page", String.valueOf(i2)));
        String a = this.f411h.m417a("https://api.weibo.com/2/friendships/friends/bilateral.json", arrayList, "/2/friendships/friends/bilateral.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public HashMap<String, Object> m824c(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        if (this.f409f != null) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        }
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String a = this.f411h.m417a("https://api.weibo.com/2/users/show.json", arrayList, "/2/users/show.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public HashMap<String, Object> m825d(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        if (this.f409f != null) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        }
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        arrayList.add(new KVPair("count", String.valueOf(i)));
        arrayList.add(new KVPair("cursor", String.valueOf(i2)));
        String a = this.f411h.m417a("https://api.weibo.com/2/friendships/followers.json", arrayList, "/2/friendships/followers.json", m626c());
        return a != null ? new Hashon().fromJson(a) : null;
    }

    public HashMap<String, Object> m826d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(C2537j.au, this.f406c));
        arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, this.f409f));
        Object obj = 1;
        try {
            C2178R.parseLong(str);
        } catch (Throwable th) {
            obj = null;
        }
        if (obj != null) {
            arrayList.add(new KVPair("uid", str));
        } else {
            arrayList.add(new KVPair("screen_name", str));
        }
        String b = this.f411h.m421b("https://api.weibo.com/2/friendships/create.json", arrayList, "/2/friendships/create.json", m626c());
        return b != null ? new Hashon().fromJson(b) : null;
    }

    public String getAuthorizeUrl() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(Constants.PARAM_CLIENT_ID, this.f406c));
        arrayList.add(new KVPair("response_type", XiaomiOAuthConstants.EXTRA_CODE_2));
        arrayList.add(new KVPair(AuthorizeActivityBase.KEY_REDIRECT_URI, this.f408e));
        if (this.f410g != null && this.f410g.length > 0) {
            arrayList.add(new KVPair(XiaomiOAuthConstants.EXTRA_SCOPE_2, TextUtils.join(MiPushClient.ACCEPT_TIME_SEPARATOR, this.f410g)));
        }
        arrayList.add(new KVPair("display", "mobile"));
        String str = "https://open.weibo.cn/oauth2/authorize?" + C2178R.encodeUrl(arrayList);
        ShareSDK.logApiEvent("/oauth2/authorize", m626c());
        return str;
    }

    public C0155b getAuthorizeWebviewClient(C0161g c0161g) {
        return new C0214d(c0161g);
    }

    public String getRedirectUri() {
        return TextUtils.isEmpty(this.f408e) ? "https://api.weibo.com/oauth2/default.html" : this.f408e;
    }

    public C0159f getSSOProcessor(C0158e c0158e) {
        C0159f c0216f = new C0216f(c0158e);
        c0216f.m450a(32973);
        c0216f.m801a(this.f406c, this.f408e, this.f410g);
        return c0216f;
    }
}
