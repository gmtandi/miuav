package com.fimi.soul.media.gallery;

import android.content.SharedPreferences.Editor;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.view.dialog.C1198a;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.ay;

/* renamed from: com.fimi.soul.media.gallery.i */
class C1595i implements C1198a {
    final /* synthetic */ DroneImagePagerActivity f7816a;

    C1595i(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7816a = droneImagePagerActivity;
    }

    public void m10773a(int i) {
    }

    public void m10774a(int i, Boolean bool) {
        Editor edit = ay.m12293a(C1189f.m8327a()).edit();
        if (i == -2) {
            if (bool.booleanValue()) {
                edit.putBoolean(C1236a.f5592P, false);
                edit.putBoolean(C1236a.f5593Q, false);
                edit.commit();
            } else {
                edit.putBoolean(C1236a.f5592P, true);
                edit.putBoolean(C1236a.f5593Q, false);
                edit.commit();
            }
        } else if (i == -1) {
            if (bool.booleanValue()) {
                edit.putBoolean(C1236a.f5592P, false);
                edit.putBoolean(C1236a.f5593Q, true);
                edit.commit();
            } else {
                edit.putBoolean(C1236a.f5592P, true);
                edit.putBoolean(C1236a.f5593Q, true);
                edit.commit();
            }
        }
        this.f7816a.m10663c();
    }
}
