package com.fimi.soul.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p089a.C1240a;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.DroneInfoBean;
import com.fimi.soul.entity.UpdateDroneInforBean;
import com.fimi.soul.entity.User;
import com.fimi.soul.utils.C1972l;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.p122a.p123a.C2915a;

public class FlyInforService extends Service {
    private DroneInfoBean f9937a;
    private C1157c f9938b;

    public FlyInforService() {
        this.f9937a = new DroneInfoBean();
    }

    private void m12140a() {
        Object updateDroneInforBean = new UpdateDroneInforBean();
        User b = C1236a.m8533b(this);
        if (!(b == null || b.getUserID() == null || C2915a.f14760f.equals(b.getUserID()))) {
            updateDroneInforBean.setUserID(b.getUserID());
        }
        C1532a c1532a = (C1532a) this.f9938b.m7939a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
        if (c1532a != null) {
            updateDroneInforBean.setPlaneID(c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g());
            this.f9937a.setFlySoftVersion(c1532a.m10057c());
        }
        c1532a = (C1532a) this.f9938b.m7939a(Constants.VIA_TO_TYPE_QQ_GROUP, C1532a.class);
        if (c1532a != null) {
            this.f9937a.setRemoteId(c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g());
            this.f9937a.setRemoteSoftVersion(c1532a.m10057c());
        }
        c1532a = (C1532a) this.f9938b.m7939a(Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, C1532a.class);
        if (c1532a != null) {
            this.f9937a.setCoulidId(c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g());
            this.f9937a.setCloudSoftVersion(c1532a.m10057c());
        }
        c1532a = (C1532a) this.f9938b.m7939a(C1972l.f10195n, C1532a.class);
        if (c1532a != null) {
            this.f9937a.setLightStreamVersion(c1532a.m10057c());
        }
        updateDroneInforBean.setmDroneInfoBean(this.f9937a);
        this.f9938b.m7943a(C1543c.f7243r, updateDroneInforBean);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f9938b = C1157c.m7938a();
        this.f9937a.setPhoneDevice(be.m12388g(getApplicationContext()).substring(0, be.m12388g(getApplicationContext()).indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR)));
        this.f9937a.setTelePhoneId(be.m12388g(getApplicationContext()).substring(be.m12388g(getApplicationContext()).indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1));
        this.f9937a.setAppSoftVersion(be.m12378d(getApplicationContext()));
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (be.m12370b(getApplicationContext())) {
            new C1240a(getApplicationContext()).m8537a();
        }
        m12140a();
        return super.onStartCommand(intent, i, i2);
    }
}
