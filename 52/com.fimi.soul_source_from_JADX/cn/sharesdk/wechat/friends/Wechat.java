package cn.sharesdk.wechat.friends;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.C0232g;
import cn.sharesdk.wechat.utils.C0236k;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatHelper;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

public class Wechat extends Platform {
    public static final String NAME;
    private String f424a;
    private String f425b;
    private boolean f426c;

    public class ShareParams extends cn.sharesdk.wechat.utils.WechatHelper.ShareParams {
        public ShareParams() {
            this.scene = 0;
        }
    }

    static {
        NAME = Wechat.class.getSimpleName();
    }

    public Wechat(Context context) {
        super(context);
    }

    protected boolean checkAuthorize(int i, Object obj) {
        if (isClientValid()) {
            if (i == 9 || isAuthValid()) {
                return true;
            }
            innerAuthorize(i, obj);
            return false;
        } else if (this.listener == null) {
            return false;
        } else {
            this.listener.onError(this, i, new WechatClientNotExistException());
            return false;
        }
    }

    protected void doAuthorize(String[] strArr) {
        if (!TextUtils.isEmpty(this.f424a) && !TextUtils.isEmpty(this.f425b)) {
            WechatHelper a = WechatHelper.m858a();
            a.m881a(this.context, this.f424a);
            if (a.m885c()) {
                C0232g c0232g = new C0232g(this, 22);
                c0232g.m919a(this.f424a, this.f425b);
                C0236k c0236k = new C0236k(this);
                c0236k.m934a(c0232g);
                c0236k.m932a(new C0222a(this));
                try {
                    a.m879a(c0236k);
                } catch (Throwable th) {
                    if (this.listener != null) {
                        this.listener.onError(this, 1, th);
                    }
                }
            } else if (this.listener != null) {
                this.listener.onError(this, 1, new WechatClientNotExistException());
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }

    protected void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    protected void doShare(cn.sharesdk.framework.Platform.ShareParams shareParams) {
        shareParams.set("scene", Integer.valueOf(0));
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f424a);
        C0236k c0236k = new C0236k(this);
        if (this.f426c) {
            try {
                a.m880a(c0236k, shareParams, this.listener);
                return;
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(this, 9, th);
                    return;
                }
                return;
            }
        }
        c0236k.m931a(shareParams, this.listener);
        try {
            a.m883b(c0236k);
        } catch (Throwable th2) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th2);
            }
        }
    }

    protected HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    protected C0176a filterShareContent(cn.sharesdk.framework.Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        C0176a c0176a = new C0176a();
        String text = shareParams.getText();
        c0176a.f261b = text;
        CharSequence imageUrl = shareParams.getImageUrl();
        String imagePath = shareParams.getImagePath();
        Bitmap imageData = shareParams.getImageData();
        if (!TextUtils.isEmpty(imageUrl)) {
            c0176a.f263d.add(imageUrl);
        } else if (imagePath != null) {
            c0176a.f264e.add(imagePath);
        } else if (imageData != null) {
            c0176a.f265f.add(imageData);
        }
        String url = shareParams.getUrl();
        if (url != null) {
            c0176a.f262c.add(url);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(SocialConstants.PARAM_TITLE, shareParams.getTitle());
        hashMap2.put(SocialConstants.PARAM_URL, url);
        hashMap2.put("extInfo", null);
        hashMap2.put(RMsgInfo.COL_CONTENT, text);
        hashMap2.put("image", c0176a.f263d);
        hashMap2.put("musicFileUrl", url);
        c0176a.f266g = hashMap2;
        return c0176a;
    }

    protected void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    protected HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    protected HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    protected void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    public String getName() {
        return NAME;
    }

    public int getPlatformId() {
        return 22;
    }

    public int getVersion() {
        return 1;
    }

    public boolean hasShareCallback() {
        return !this.f426c;
    }

    protected void initDevInfo(String str) {
        this.f424a = getDevinfo("AppId");
        this.f425b = getDevinfo("AppSecret");
        this.f426c = "true".equals(getDevinfo("BypassApproval"));
        if (this.f424a == null || this.f424a.length() <= 0) {
            this.f424a = getDevinfo("WechatMoments", "AppId");
            this.f426c = "true".equals(getDevinfo("WechatMoments", "BypassApproval"));
            if (this.f424a == null || this.f424a.length() <= 0) {
                this.f424a = getDevinfo("WechatFavorite", "AppId");
                if (this.f424a != null && this.f424a.length() > 0) {
                    copyDevinfo("WechatFavorite", NAME);
                    this.f424a = getDevinfo("AppId");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyDevinfo("WechatMoments", NAME);
            this.f424a = getDevinfo("AppId");
            this.f426c = "true".equals(getDevinfo("BypassApproval"));
            C0205d.m752a().m737d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    public boolean isClientValid() {
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f424a);
        return a.m885c();
    }

    @Deprecated
    public boolean isValid() {
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f424a);
        return a.m885c() && super.isValid();
    }

    protected void setNetworkDevinfo() {
        this.f424a = getNetworkDevinfo("app_id", "AppId");
        this.f425b = getNetworkDevinfo("app_secret", "AppSecret");
        if (this.f424a == null || this.f424a.length() <= 0) {
            this.f424a = getNetworkDevinfo(23, "app_id", "AppId");
            if (this.f424a == null || this.f424a.length() <= 0) {
                this.f424a = getNetworkDevinfo(37, "app_id", "AppId");
                if (this.f424a != null && this.f424a.length() > 0) {
                    copyNetworkDevinfo(37, 22);
                    this.f424a = getNetworkDevinfo("app_id", "AppId");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            } else {
                copyNetworkDevinfo(23, 22);
                this.f424a = getNetworkDevinfo("app_id", "AppId");
                C0205d.m752a().m737d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
        if (this.f425b == null || this.f425b.length() <= 0) {
            this.f425b = getNetworkDevinfo(23, "app_secret", "AppSecret");
            if (this.f425b == null || this.f425b.length() <= 0) {
                this.f425b = getNetworkDevinfo(37, "app_secret", "AppSecret");
                if (this.f425b != null && this.f425b.length() > 0) {
                    copyNetworkDevinfo(37, 22);
                    this.f425b = getNetworkDevinfo("app_secret", "AppSecret");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyNetworkDevinfo(23, 22);
            this.f425b = getNetworkDevinfo("app_secret", "AppSecret");
            C0205d.m752a().m737d("Try to use the dev info of WechatMoments, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    protected void userInfor(String str) {
        if (!TextUtils.isEmpty(this.f424a) && !TextUtils.isEmpty(this.f425b)) {
            C0232g c0232g = new C0232g(this, 22);
            c0232g.m919a(this.f424a, this.f425b);
            try {
                c0232g.m917a(this.listener);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
                if (this.listener != null) {
                    this.listener.onError(this, 8, th);
                }
            }
        } else if (this.listener != null) {
            this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
        }
    }
}
