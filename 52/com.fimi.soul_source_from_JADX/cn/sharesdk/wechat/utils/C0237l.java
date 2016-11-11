package cn.sharesdk.wechat.utils;

import android.os.Handler.Callback;
import android.os.Message;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

/* renamed from: cn.sharesdk.wechat.utils.l */
class C0237l implements Callback {
    int f468a;
    final /* synthetic */ DeviceHelper f469b;
    final /* synthetic */ String f470c;
    final /* synthetic */ PlatformActionListener f471d;
    final /* synthetic */ Platform f472e;
    final /* synthetic */ HashMap f473f;
    final /* synthetic */ WechatHelper f474g;

    C0237l(WechatHelper wechatHelper, DeviceHelper deviceHelper, String str, PlatformActionListener platformActionListener, Platform platform, HashMap hashMap) {
        this.f474g = wechatHelper;
        this.f469b = deviceHelper;
        this.f470c = str;
        this.f471d = platformActionListener;
        this.f472e = platform;
        this.f473f = hashMap;
        this.f468a = 0;
    }

    public boolean handleMessage(Message message) {
        if (this.f470c.equals(this.f469b.getTopTaskPackageName())) {
            if (this.f468a < 5) {
                this.f468a++;
                UIHandler.sendEmptyMessageDelayed(0, 500, this);
            }
        } else if (this.f471d != null) {
            this.f471d.onComplete(this.f472e, 9, this.f473f);
        }
        return true;
    }
}
