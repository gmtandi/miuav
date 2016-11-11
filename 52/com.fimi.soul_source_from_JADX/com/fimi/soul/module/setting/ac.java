package com.fimi.soul.module.setting;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;

class ac implements OnClickListener {
    final /* synthetic */ MapSettingFragment f9208a;

    ac(MapSettingFragment mapSettingFragment) {
        this.f9208a = mapSettingFragment;
    }

    public void onClick(View view) {
        if (this.f9208a.f9137a) {
            this.f9208a.f9137a = false;
            ((C1313t) C1276b.m8680a().m8699d()).m8873r().m8752e();
            this.f9208a.m11636e();
            return;
        }
        this.f9208a.f9149m.m11326c();
    }
}
