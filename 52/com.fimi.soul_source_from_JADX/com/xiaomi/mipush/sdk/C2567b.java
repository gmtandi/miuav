package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import java.util.List;

/* renamed from: com.xiaomi.mipush.sdk.b */
final class C2567b implements Runnable {
    final /* synthetic */ Context f12875a;

    C2567b(Context context) {
        this.f12875a = context;
    }

    public void run() {
        if (!MiPushClient.shouldUseMIUIPush(this.f12875a) && 1 == C2566a.m14615a(this.f12875a).m14636m()) {
            try {
                List<PackageInfo> installedPackages = this.f12875a.getPackageManager().getInstalledPackages(4);
                if (installedPackages != null) {
                    for (PackageInfo packageInfo : installedPackages) {
                        ServiceInfo[] serviceInfoArr = packageInfo.services;
                        if (serviceInfoArr != null) {
                            for (ServiceInfo serviceInfo : serviceInfoArr) {
                                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !this.f12875a.getPackageName().equals(serviceInfo.packageName)) {
                                    try {
                                        Thread.sleep(((long) ((Math.random() * 300.0d) + 60.0d)) * 1000);
                                    } catch (InterruptedException e) {
                                    }
                                    Intent intent = new Intent();
                                    intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                                    intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                                    this.f12875a.startService(intent);
                                }
                            }
                            continue;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}
