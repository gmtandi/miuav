package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import java.util.HashMap;

public abstract class Platform {
    public static final int ACTION_AUTHORIZING = 1;
    protected static final int ACTION_CUSTOMER = 655360;
    public static final int ACTION_FOLLOWING_USER = 6;
    protected static final int ACTION_GETTING_BILATERAL_LIST = 10;
    protected static final int ACTION_GETTING_FOLLOWER_LIST = 11;
    public static final int ACTION_GETTING_FRIEND_LIST = 2;
    public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
    public static final int ACTION_SHARE = 9;
    public static final int ACTION_TIMELINE = 7;
    public static final int ACTION_USER_INFOR = 8;
    public static final int CUSTOMER_ACTION_MASK = 65535;
    public static final int SHARE_APPS = 7;
    public static final int SHARE_EMOJI = 9;
    public static final int SHARE_FILE = 8;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_MUSIC = 5;
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_VIDEO = 6;
    public static final int SHARE_WEBPAGE = 4;
    private C0186f f179a;
    protected final Context context;
    protected final PlatformDb db;
    protected PlatformActionListener listener;

    public class ShareParams extends C0150d {
        @Deprecated
        public String imagePath;
        @Deprecated
        public String text;

        public ShareParams(String str) {
            super(str);
        }

        public ShareParams(HashMap<String, Object> hashMap) {
            super((HashMap) hashMap);
        }
    }

    public Platform(Context context) {
        this.context = context;
        this.f179a = new C0186f(this, context);
        this.db = this.f179a.m654g();
        this.listener = this.f179a.m656i();
    }

    public void SSOSetting(boolean z) {
        this.f179a.m642a(z);
    }

    void m395a() {
        this.f179a.m642a(false);
        this.f179a.m639a(getName());
    }

    protected void afterRegister(int i, Object obj) {
        this.f179a.m645b(i, obj);
    }

    public void authorize() {
        authorize(null);
    }

    public void authorize(String[] strArr) {
        this.f179a.m643a(strArr);
    }

    boolean m396b() {
        return this.f179a.m653f();
    }

    protected abstract boolean checkAuthorize(int i, Object obj);

    protected void copyDevinfo(String str, String str2) {
        ShareSDK.m405a(str, str2);
    }

    protected void copyNetworkDevinfo(int i, int i2) {
        ShareSDK.m404a(i, i2);
    }

    public void customerProtocol(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        this.f179a.m641a(str, str2, s, hashMap, hashMap2);
    }

    protected abstract void doAuthorize(String[] strArr);

    protected abstract void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2);

    protected abstract void doShare(ShareParams shareParams);

    protected abstract HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap);

    protected abstract C0176a filterShareContent(ShareParams shareParams, HashMap<String, Object> hashMap);

    protected abstract void follow(String str);

    public void followFriend(String str) {
        this.f179a.m646b(str);
    }

    protected abstract HashMap<String, Object> getBilaterals(int i, int i2, String str);

    public Context getContext() {
        return this.context;
    }

    public PlatformDb getDb() {
        return this.db;
    }

    public String getDevinfo(String str) {
        return getDevinfo(getName(), str);
    }

    public String getDevinfo(String str, String str2) {
        return ShareSDK.m408b(str, str2);
    }

    protected abstract HashMap<String, Object> getFollowers(int i, int i2, String str);

    protected abstract HashMap<String, Object> getFollowings(int i, int i2, String str);

    protected abstract void getFriendList(int i, int i2, String str);

    public int getId() {
        return this.f179a.m631a();
    }

    public abstract String getName();

    protected String getNetworkDevinfo(int i, String str, String str2) {
        return this.f179a.m632a(i, str, str2);
    }

    protected String getNetworkDevinfo(String str, String str2) {
        return getNetworkDevinfo(getPlatformId(), str, str2);
    }

    public PlatformActionListener getPlatformActionListener() {
        return this.f179a.m647c();
    }

    protected abstract int getPlatformId();

    public String getShortLintk(String str, boolean z) {
        return this.f179a.m634a(str, z);
    }

    public int getSortId() {
        return this.f179a.m644b();
    }

    public void getTimeLine(String str, int i, int i2) {
        this.f179a.m640a(str, i, i2);
    }

    public abstract int getVersion();

    public abstract boolean hasShareCallback();

    protected abstract void initDevInfo(String str);

    protected void innerAuthorize(int i, Object obj) {
        this.f179a.m636a(i, obj);
    }

    public boolean isAuthValid() {
        return this.f179a.m651d();
    }

    public boolean isClientValid() {
        return false;
    }

    public boolean isSSODisable() {
        return this.f179a.m652e();
    }

    @Deprecated
    public boolean isValid() {
        return this.f179a.m651d();
    }

    public void listFriend(int i, int i2, String str) {
        this.f179a.m635a(i, i2, str);
    }

    @Deprecated
    public void removeAccount() {
        this.f179a.m655h();
    }

    public void removeAccount(boolean z) {
        this.f179a.m655h();
        ShareSDK.removeCookieOnAuthorize(z);
    }

    protected abstract void setNetworkDevinfo();

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.f179a.m638a(platformActionListener);
    }

    public void share(ShareParams shareParams) {
        this.f179a.m637a(shareParams);
    }

    public void showUser(String str) {
        this.f179a.m649c(str);
    }

    protected abstract void timeline(int i, int i2, String str);

    protected String uploadImageToFileServer(Bitmap bitmap) {
        return this.f179a.m633a(bitmap);
    }

    protected String uploadImageToFileServer(String str) {
        return this.f179a.m650d(str);
    }

    protected abstract void userInfor(String str);
}
