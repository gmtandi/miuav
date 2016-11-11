package cn.sharesdk.framework.p013b;

import cn.sharesdk.framework.ShareSDK;
import com.hoho.android.usbserial.driver.UsbId;
import com.mob.commons.authorize.MobProduct;

/* renamed from: cn.sharesdk.framework.b.d */
class C0181d implements MobProduct {
    final /* synthetic */ C0180c f290a;

    C0181d(C0180c c0180c) {
        this.f290a = c0180c;
    }

    public String getProductAppkey() {
        return this.f290a.f280a;
    }

    public String getProductTag() {
        return "SHARESDK";
    }

    public int getSdkver() {
        return UsbId.SILAB_CP2102 + ShareSDK.getSDKVersionCode();
    }
}
