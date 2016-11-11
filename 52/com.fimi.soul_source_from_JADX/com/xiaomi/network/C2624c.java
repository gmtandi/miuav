package com.xiaomi.network;

import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import java.util.List;
import java.util.TimerTask;
import org.p122a.p137b.C3258g;

/* renamed from: com.xiaomi.network.c */
class C2624c extends TimerTask {
    final /* synthetic */ UploadHostStatHelper f13047a;

    C2624c(UploadHostStatHelper uploadHostStatHelper) {
        this.f13047a = uploadHostStatHelper;
    }

    public void run() {
        for (HttpRecordCallback httpRecordCallback : this.f13047a.f13037a) {
            List a = httpRecordCallback.m14865a();
            double b = httpRecordCallback.m14866b();
            if (a != null) {
                try {
                    if (a.size() > 0) {
                        this.f13047a.m14873a(a, b);
                    }
                } catch (C3258g e) {
                    e.printStackTrace();
                }
            }
        }
        this.f13047a.f13040d = false;
    }
}
