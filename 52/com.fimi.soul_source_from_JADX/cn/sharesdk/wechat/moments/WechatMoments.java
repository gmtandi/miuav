package cn.sharesdk.wechat.moments;

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
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

public class WechatMoments extends Platform {
    public static final String NAME;
    private String f428a;
    private String f429b;
    private boolean f430c;

    public class ShareParams extends cn.sharesdk.wechat.utils.WechatHelper.ShareParams {
        public ShareParams() {
            this.scene = 1;
        }
    }

    static {
        NAME = WechatMoments.class.getSimpleName();
    }

    public WechatMoments(Context context) {
        super(context);
    }

    protected boolean checkAuthorize(int i, Object obj) {
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f428a);
        if (a.m885c()) {
            if (a.m886d()) {
                if (i == 9 || isAuthValid()) {
                    return true;
                }
                innerAuthorize(i, obj);
                return false;
            } else if (this.listener == null) {
                return false;
            } else {
                this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
                return false;
            }
        } else if (this.listener == null) {
            return false;
        } else {
            this.listener.onError(this, i, new WechatClientNotExistException());
            return false;
        }
    }

    protected void doAuthorize(String[] strArr) {
        if (!TextUtils.isEmpty(this.f428a) && !TextUtils.isEmpty(this.f429b)) {
            WechatHelper a = WechatHelper.m858a();
            a.m881a(this.context, this.f428a);
            if (a.m885c()) {
                if (a.m886d()) {
                    C0232g c0232g = new C0232g(this, 23);
                    c0232g.m919a(this.f428a, this.f429b);
                    C0236k c0236k = new C0236k(this);
                    c0236k.m934a(c0232g);
                    c0236k.m932a(new C0223a(this));
                    try {
                        a.m879a(c0236k);
                    } catch (Throwable th) {
                        if (this.listener != null) {
                            this.listener.onError(this, 1, th);
                        }
                    }
                } else if (this.listener != null) {
                    this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
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
        shareParams.set("scene", Integer.valueOf(1));
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f428a);
        C0236k c0236k = new C0236k(this);
        if (this.f430c) {
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
            if (this.listener != null && a.m884b()) {
                HashMap hashMap = new HashMap();
                hashMap.put("ShareParams", shareParams);
                if (this.listener != null) {
                    this.listener.onComplete(this, 9, hashMap);
                }
            }
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
        return 23;
    }

    public int getVersion() {
        return 1;
    }

    public boolean hasShareCallback() {
        return !this.f430c;
    }

    protected void initDevInfo(String str) {
        this.f428a = getDevinfo("AppId");
        this.f429b = getDevinfo("AppSecret");
        this.f430c = "true".equals(getDevinfo("BypassApproval"));
        if (this.f428a == null || this.f428a.length() <= 0) {
            this.f428a = getDevinfo("Wechat", "AppId");
            this.f430c = "true".equals(getDevinfo("Wechat", "BypassApproval"));
            if (this.f428a == null || this.f428a.length() <= 0) {
                this.f428a = getDevinfo("WechatFavorite", "AppId");
                if (this.f428a != null && this.f428a.length() > 0) {
                    copyDevinfo("WechatFavorite", NAME);
                    this.f428a = getDevinfo("AppId");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyDevinfo("Wechat", NAME);
            this.f428a = getDevinfo("AppId");
            this.f430c = "true".equals(getDevinfo("BypassApproval"));
            C0205d.m752a().m737d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    public boolean isClientValid() {
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f428a);
        return a.m885c() && a.m886d();
    }

    @Deprecated
    public boolean isValid() {
        WechatHelper a = WechatHelper.m858a();
        a.m881a(this.context, this.f428a);
        return a.m885c() && a.m886d() && super.isValid();
    }

    protected void setNetworkDevinfo() {
        this.f428a = getNetworkDevinfo("app_id", "AppId");
        this.f429b = getNetworkDevinfo("app_secret", "AppSecret");
        if (this.f428a == null || this.f428a.length() <= 0) {
            this.f428a = getNetworkDevinfo(22, "app_id", "AppId");
            if (this.f428a == null || this.f428a.length() <= 0) {
                this.f428a = getNetworkDevinfo(37, "app_id", "AppId");
                if (this.f428a != null && this.f428a.length() > 0) {
                    copyNetworkDevinfo(23, 23);
                    this.f428a = getNetworkDevinfo("app_id", "AppId");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                }
            } else {
                copyNetworkDevinfo(22, 23);
                this.f428a = getNetworkDevinfo("app_id", "AppId");
                C0205d.m752a().m737d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
            }
        }
        if (this.f429b == null || this.f429b.length() <= 0) {
            this.f429b = getNetworkDevinfo(22, "app_secret", "AppSecret");
            if (this.f429b == null || this.f429b.length() <= 0) {
                this.f429b = getNetworkDevinfo(37, "app_secret", "AppSecret");
                if (this.f429b != null && this.f429b.length() > 0) {
                    copyNetworkDevinfo(23, 23);
                    this.f429b = getNetworkDevinfo("app_secret", "AppSecret");
                    C0205d.m752a().m737d("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.", new Object[0]);
                    return;
                }
                return;
            }
            copyNetworkDevinfo(22, 23);
            this.f429b = getNetworkDevinfo("app_secret", "AppSecret");
            C0205d.m752a().m737d("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.", new Object[0]);
        }
    }

    protected void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    protected void userInfor(String str) {
        if (!TextUtils.isEmpty(this.f428a) && !TextUtils.isEmpty(this.f429b)) {
            C0232g c0232g = new C0232g(this, 23);
            c0232g.m919a(this.f428a, this.f429b);
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
