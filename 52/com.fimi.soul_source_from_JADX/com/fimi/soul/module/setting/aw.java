package com.fimi.soul.module.setting;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.fimi.soul.C1205R;
import com.tencent.connect.common.Constants;

class aw implements OnCheckedChangeListener {
    final /* synthetic */ UserFeedBackActivity f9284a;

    aw(UserFeedBackActivity userFeedBackActivity) {
        this.f9284a = userFeedBackActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            switch (compoundButton.getId()) {
                case C1205R.id.fimi_plane:
                    this.f9284a.f9182j.setChecked(false);
                    this.f9284a.f9183k.setChecked(false);
                    this.f9284a.f9184l.setChecked(false);
                    compoundButton.setTag(Constants.VIA_TO_TYPE_QQ_GROUP);
                    break;
                case C1205R.id.fimi_control:
                    this.f9284a.f9181i.setChecked(false);
                    this.f9284a.f9183k.setChecked(false);
                    this.f9284a.f9184l.setChecked(false);
                    compoundButton.setTag(Constants.VIA_SSO_LOGIN);
                    break;
                case C1205R.id.fimi_camera:
                    this.f9284a.f9181i.setChecked(false);
                    this.f9284a.f9182j.setChecked(false);
                    this.f9284a.f9184l.setChecked(false);
                    compoundButton.setTag(Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP);
                    break;
                case C1205R.id.fimi_app:
                    this.f9284a.f9181i.setChecked(false);
                    this.f9284a.f9182j.setChecked(false);
                    this.f9284a.f9183k.setChecked(false);
                    compoundButton.setTag(Constants.VIA_TO_TYPE_QZONE);
                    break;
            }
            this.f9284a.f9171C = compoundButton.getTag().toString();
        }
    }
}
