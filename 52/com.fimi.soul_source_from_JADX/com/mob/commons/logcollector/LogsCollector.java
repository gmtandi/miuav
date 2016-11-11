package com.mob.commons.logcollector;

import android.content.Context;
import android.content.Intent;
import com.mob.tools.MobLog;
import com.mob.tools.log.LogCollector;
import com.tencent.open.SocialConstants;

public abstract class LogsCollector implements LogCollector {
    private C2155c f376a;

    public LogsCollector(Context context) {
        this.f376a = C2155c.m13183a(context);
        this.f376a.m13196a(getSDKVersion(), getSDKTag(), getAppkey());
    }

    final int m754a(int i, String str) {
        if (this.f376a.m13194a() != null) {
            try {
                Intent intent = new Intent();
                intent.setAction("cn.sharesdk.log");
                intent.putExtra("package", this.f376a.m13194a().getPackageName());
                intent.putExtra("priority", i);
                intent.putExtra(SocialConstants.PARAM_SEND_MSG, str);
                this.f376a.m13194a().sendBroadcast(intent);
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }
        return 0;
    }

    protected abstract String getAppkey();

    protected abstract String getSDKTag();

    protected abstract int getSDKVersion();

    public final void log(String str, int i, int i2, String str2, String str3) {
        m754a(i, str3);
        if (str != null && str.equals(getSDKTag())) {
            if (i2 == 1) {
                this.f376a.m13197b(getSDKVersion(), i2, str, getAppkey(), str3);
            } else if (i2 == 2) {
                this.f376a.m13195a(getSDKVersion(), i2, str, getAppkey(), str3);
            } else if (i == 5) {
                this.f376a.m13195a(getSDKVersion(), i2, str, getAppkey(), str3);
            }
        }
    }
}
