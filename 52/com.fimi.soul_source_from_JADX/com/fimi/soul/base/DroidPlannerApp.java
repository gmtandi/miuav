package com.fimi.soul.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.p103k.at;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.droneconnection.connection.C1235n;
import com.fimi.soul.drone.droneconnection.connection.service.C1522a;
import com.fimi.soul.drone.p105a.C1430a;
import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.utils.aa;
import com.fimi.soul.utils.ay;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DroidPlannerApp extends ErrorReportApp implements C1235n {
    static DroidPlannerApp f5568e;
    private static HashMap f5569i;
    public C1433a f5570a;
    public C1430a f5571b;
    public boolean f5572c;
    public C1522a f5573d;
    private SharedPreferences f5574f;
    private List<Activity> f5575g;
    private List<Activity> f5576h;

    static {
        f5568e = null;
        f5569i = new HashMap();
    }

    public DroidPlannerApp() {
        this.f5572c = true;
        this.f5574f = null;
        this.f5575g = new LinkedList();
        this.f5576h = new ArrayList();
    }

    public static HashMap<String, String> m8514c() {
        return f5569i;
    }

    public static Context m8515g() {
        return f5568e;
    }

    private void m8516h() {
        new at(m8515g()).m9233a();
    }

    private boolean m8517i() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
        String packageName = getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public void m8518a() {
        if (this.f5576h != null && this.f5576h.size() > 0) {
            for (Activity finish : this.f5576h) {
                finish.finish();
            }
            this.f5576h.clear();
        }
    }

    public void m8519a(Activity activity) {
        this.f5576h.add(activity);
    }

    public void m8520a(C1437b c1437b) {
        this.f5571b.m9512a(c1437b);
    }

    public void m8521a(boolean z) {
        C1433a c1433a = this.f5570a;
        boolean z2 = z || this.f5570a.m9560G().m10408a() > 5;
        c1433a.m9595b(z2);
    }

    public void m8522b() {
        if (this.f5575g != null && this.f5575g.size() > 0) {
            this.f5575g.clear();
        }
    }

    public void m8523b(Activity activity) {
        this.f5575g.add(activity);
    }

    public void m8524b(boolean z) {
        this.f5570a.m9591a(z);
        if (!z) {
            this.f5570a.m9595b(false);
            this.f5570a.m9589a(C1584h.CLEANALLOBJ);
        }
    }

    public void m8525d() {
        try {
            for (Activity activity : this.f5575g) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m8526e() {
        this.f5570a.m9589a(C1584h.CONNECTSUCESS);
    }

    public void m8527f() {
        this.f5570a.m9560G().m10409a((byte) 0);
        this.f5570a.m9595b(false);
        this.f5570a.m9589a(C1584h.CLEANALLOBJ);
    }

    public void onCreate() {
        super.onCreate();
        f5568e = this;
        this.f5573d = new C1522a(this, this);
        this.f5574f = ay.m12293a((Context) this);
        this.f5570a = new C1433a(this.f5573d, this, new Handler());
        this.f5571b = new C1430a(this.f5570a, m8515g());
        if (m8517i() && this.f5574f.contains("isfirstloading")) {
            MiPushClient.registerPush(this, C1236a.f5628z, C1236a.f5620r);
        }
        Logger.setLogger(this, new C1237b(this));
        MiStatInterface.initialize(this, C1236a.f5628z, C1236a.f5620r, "Xiao Mi");
        MiStatInterface.setUploadPolicy(3, 0);
        MiStatInterface.enableExceptionCatcher(false);
        m8516h();
        C1276b.m8684a(this);
        aa.m12207a();
    }

    public void onTerminate() {
        super.onTerminate();
        aa.m12213b();
    }
}
