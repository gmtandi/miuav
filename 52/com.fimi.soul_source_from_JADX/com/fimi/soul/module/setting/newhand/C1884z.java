package com.fimi.soul.module.setting.newhand;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.az;

/* renamed from: com.fimi.soul.module.setting.newhand.z */
class C1884z implements OnClickListener {
    final /* synthetic */ C1874p f9603a;

    C1884z(C1874p c1874p) {
        this.f9603a = c1874p;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        int j = C1189f.m8335e().m8039j();
        if (j >= 2) {
            this.f9603a.m11877a(4, 1);
            return;
        }
        C1189f.m8335e().m8026b(j + 1);
        new az(this.f9603a.f9591x).m12789a((int) C1205R.drawable.img_right_pulley).m12790a(this.f9603a.f9591x.getString(C1205R.string.setting_dialog_button_force_attitude)).m12791a(this.f9603a.f9591x.getString(C1205R.string.ensure), new aa(this)).m12788a().show();
    }
}
