package cn.sharesdk.wechat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;

public class WechatHandlerActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        setTheme(16973839);
        super.onCreate(bundle);
        try {
            WechatHelper.m858a().m882a(this);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
        finish();
    }

    public void onGetMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            WechatHelper.m858a().m882a(this);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
        finish();
    }

    public void onShowMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }
}
