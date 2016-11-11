package com.fimi.kernel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import com.fimi.kernel.p076b.C1100b;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p082c.C1156a;
import com.fimi.kernel.p084e.ah;
import com.fimi.kernel.view.C1192b;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseActivity extends FragmentActivity {
    private Handler activityInnerHandler;
    private Map<C1152e, C1100b> networkManagerMap;

    protected Handler getHandler() {
        return this.activityInnerHandler;
    }

    protected C1156a getKeyValueStoreManager() {
        return C1189f.m8333c();
    }

    public C1100b getNetworkManager(C1152e c1152e) {
        if (this.networkManagerMap == null) {
            this.networkManagerMap = new HashMap();
        }
        if (this.networkManagerMap.containsKey(c1152e)) {
            return (C1100b) this.networkManagerMap.get(c1152e);
        }
        C1100b c1100b = (C1100b) C1189f.m8328a(c1152e);
        this.networkManagerMap.put(c1152e, c1100b);
        return c1100b;
    }

    protected C1192b getViewManager() {
        return C1189f.m8331b();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C1189f.m8329a((Activity) this);
        this.activityInnerHandler = new C1095a(this);
    }

    protected void onDestroy() {
        C1189f.m8332b(this);
        super.onDestroy();
    }

    public void onHandleMessage(Message message) {
    }

    protected void onResume() {
        C1189f.m8329a((Activity) this);
        super.onResume();
    }

    protected void onStart() {
        C1189f.m8329a((Activity) this);
        super.onStart();
    }

    public void runInBackground(Runnable runnable) {
        ah.m8075a(runnable);
    }
}
