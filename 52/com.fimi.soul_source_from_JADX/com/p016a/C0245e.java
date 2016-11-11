package com.p016a;

import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.autonavi.aps.amapapi.model.AmapLoc;

/* renamed from: com.a.e */
class C0245e extends Thread {
    boolean f1043a;
    final /* synthetic */ C0238a f1044b;

    public C0245e(C0238a c0238a, String str) {
        this.f1044b = c0238a;
        super(str);
        this.f1043a = false;
    }

    public void run() {
        this.f1043a = true;
        while (this.f1043a && !Thread.interrupted()) {
            if (this.f1044b.f476A) {
                if (!this.f1044b.f505z) {
                    this.f1044b.f483c.m1811a(this.f1044b.f481a);
                    this.f1044b.f505z = true;
                }
            } else if (this.f1044b.f505z) {
                this.f1044b.f483c.m1809a();
                this.f1044b.f505z = false;
            }
            if (AMapLocationMode.Device_Sensors.equals(this.f1044b.f481a.getLocationMode())) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Throwable th) {
                    ev.m1777a(th, "AMapLocationManager", "run part6");
                }
            } else if (!AMapLocationMode.Hight_Accuracy.equals(this.f1044b.f481a.getLocationMode()) || (this.f1044b.m950d() && (!this.f1044b.f481a.isGpsFirst() || !this.f1044b.f481a.isOnceLocation() || this.f1044b.m952e() || this.f1044b.f494n))) {
                Message obtain;
                Bundle bundle;
                while (this.f1044b.f488h == null) {
                    C0238a c0238a = this.f1044b;
                    c0238a.f492l++;
                    if (this.f1044b.f492l > 100) {
                        obtain = Message.obtain();
                        bundle = new Bundle();
                        Parcelable amapLoc = new AmapLoc();
                        amapLoc.m5321b(10);
                        amapLoc.m5322b("\u8bf7\u68c0\u67e5\u914d\u7f6e\u6587\u4ef6\u662f\u5426\u914d\u7f6e\u670d\u52a1");
                        bundle.putParcelable("location", amapLoc);
                        obtain.setData(bundle);
                        obtain.what = 1;
                        if (this.f1044b.f482b != null) {
                            this.f1044b.f482b.sendMessage(obtain);
                        }
                    } else {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                this.f1044b.f496p = 0;
                this.f1044b.f501v = true;
                obtain = Message.obtain();
                obtain.what = 1;
                bundle = new Bundle();
                bundle.putBoolean("isfirst", this.f1044b.f504y);
                bundle.putBoolean("wifiactivescan", this.f1044b.f481a.isWifiActiveScan());
                bundle.putBoolean("isNeedAddress", this.f1044b.f481a.isNeedAddress());
                bundle.putBoolean("isKillProcess", this.f1044b.f481a.isKillProcess());
                bundle.putBoolean("isOffset", this.f1044b.f481a.isOffset());
                bundle.putLong("httptimeout", this.f1044b.f481a.getHttpTimeOut());
                obtain.setData(bundle);
                obtain.replyTo = this.f1044b.f489i;
                try {
                    if (this.f1044b.f488h != null) {
                        this.f1044b.f488h.send(obtain);
                    }
                } catch (Throwable th2) {
                    ev.m1777a(th2, "AMapLocationManager", "run part4");
                }
                this.f1044b.f504y = false;
                try {
                    Thread.sleep(this.f1044b.f481a.getInterval());
                } catch (InterruptedException e3) {
                    Thread.currentThread().interrupt();
                }
            } else {
                try {
                    if (this.f1044b.f481a.isOnceLocation() && this.f1044b.f496p == 0) {
                        this.f1044b.f496p = dn.m1519b();
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException e4) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.f1044b.f501v = false;
    }
}
