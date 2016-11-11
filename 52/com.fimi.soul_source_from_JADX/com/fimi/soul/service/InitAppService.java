package com.fimi.soul.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.C1184w;
import com.fimi.kernel.p084e.ac;
import com.fimi.soul.biz.update.C1404a;
import com.fimi.soul.biz.update.C1420r;
import com.fimi.soul.biz.update.C1423u;
import com.fimi.soul.biz.update.ah;
import com.fimi.soul.biz.update.aj;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.fimi.soul.receiver.SpeekSignReceiver;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import java.io.File;
import java.util.List;
import org.p122a.p123a.C2915a;

public class InitAppService extends Service {
    public static final String f9939a = "sp_down_firmware";
    private volatile int f9940b;
    private C1404a f9941c;
    private C1420r f9942d;
    private C1423u f9943e;
    private ah f9944f;
    private aj<UpdateVersonBean> f9945g;
    private ah f9946h;
    private SpeekSignReceiver f9947i;
    private int f9948j;
    private AudioManager f9949k;
    private ah f9950l;

    public InitAppService() {
        this.f9940b = 0;
        this.f9941c = new C1404a();
        this.f9942d = new C1420r();
        this.f9943e = new C1423u();
    }

    private void m12141a(C1423u c1423u, UpdateVersonBean updateVersonBean) {
        m12144e();
        updateVersonBean.getSysname();
        File file = new File(C1969i.m12493p());
        if (!file.exists()) {
            file.mkdir();
        }
        String fileEncode = updateVersonBean.getFileEncode();
        String e = c1423u.m9503e(updateVersonBean);
        File file2 = new File(e);
        Object obj = null;
        if (file2.exists()) {
            String a = ac.m8017a(file2);
            if (!C1184w.m8281b(fileEncode) && fileEncode.equals(a)) {
                obj = 1;
            }
        }
        if (obj == null) {
            c1423u.m9500b(updateVersonBean, e, new C1956w(this));
        }
    }

    private synchronized void m12144e() {
        this.f9940b++;
    }

    private synchronized void m12145f() {
        this.f9940b--;
    }

    private void m12146g() {
        if (be.m12375c(getBaseContext())) {
            if (!m12154b()) {
                m12155c();
            }
        } else if (be.m12370b(getBaseContext())) {
            m12157d();
        } else {
            Log.d("Good", "\u6ca1\u6709\u7f51\u7edc");
        }
    }

    private void m12147h() {
        try {
            new Thread(new C1952s(this)).start();
        } catch (Exception e) {
            Log.d("moweiru", "e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void m12148i() {
        C1423u c1423u = new C1423u();
        c1423u.m9494a(new C1955v(this, c1423u));
    }

    public void m12149a() {
        this.f9944f = null;
        this.f9945g = null;
        this.f9946h = null;
    }

    public void m12150a(ah ahVar) {
        this.f9950l = ahVar;
    }

    public void m12151a(aj<UpdateVersonBean> ajVar) {
        this.f9945g = ajVar;
    }

    public void m12152a(List<UpdateVersonBean> list) {
        if (list == null || list.size() < 1) {
            C1189f.m8333c().m7930a(f9939a, C2915a.f14760f);
        } else {
            C1189f.m8333c().m7932a(f9939a, (List) list);
        }
    }

    public void m12153b(ah ahVar) {
        this.f9946h = ahVar;
    }

    public synchronized boolean m12154b() {
        return this.f9940b > 0;
    }

    public synchronized void m12155c() {
        if (!m12154b() && be.m12375c(getBaseContext())) {
            m12148i();
        }
    }

    public void m12156c(ah ahVar) {
        this.f9944f = ahVar;
    }

    public void m12157d() {
        this.f9943e.m9494a(new C1954u(this));
    }

    public IBinder onBind(Intent intent) {
        return new C1957x(this);
    }

    public void onCreate() {
        super.onCreate();
        this.f9949k = (AudioManager) getSystemService(FimiMediaMeta.IJKM_VAL_TYPE__AUDIO);
        this.f9948j = this.f9949k.getStreamVolume(3);
        this.f9949k.setStreamVolume(3, this.f9949k.getStreamMaxVolume(3), 4);
        this.f9947i = new SpeekSignReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkStateReceiver.f9876a);
        registerReceiver(this.f9947i, intentFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f9947i != null) {
            unregisterReceiver(this.f9947i);
        }
        if (this.f9949k != null) {
            this.f9949k.setStreamVolume(3, this.f9948j, 4);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        C1423u.m9485n();
        m12146g();
        m12147h();
        return super.onStartCommand(intent, i, i2);
    }
}
