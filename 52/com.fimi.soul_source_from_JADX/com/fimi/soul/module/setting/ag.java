package com.fimi.soul.module.setting;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.entity.Setting;
import com.fimi.soul.utils.ay;

class ag implements OnClickListener {
    final /* synthetic */ MapSettingFragment f9212a;

    ag(MapSettingFragment mapSettingFragment) {
        this.f9212a = mapSettingFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Editor edit = ay.m12293a(this.f9212a.f9148l).edit();
        edit.remove("isfirstloading");
        edit.commit();
        ((Setting) this.f9212a.f9146j.get(28)).setIsOPen(Boolean.valueOf(false));
        this.f9212a.f9144h.m11688a(this.f9212a.f9146j);
        this.f9212a.f9158v.m9271b(C1236a.m8533b(this.f9212a.f9148l), new ah(this));
    }
}
