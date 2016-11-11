package com.fimi.soul.module.update;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.setting.newhand.NewHandActivity;
import com.fimi.soul.utils.ay;

/* renamed from: com.fimi.soul.module.update.c */
class C1911c extends Handler {
    final /* synthetic */ CheckFirmwareActvity f9820a;

    C1911c(CheckFirmwareActvity checkFirmwareActvity) {
        this.f9820a = checkFirmwareActvity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        float floatValue = message.obj != null ? ((Float) message.obj).floatValue() : 0.0f;
        this.f9820a.f9659h.setCurrentCount(floatValue);
        if (!this.f9820a.f9673v.m9569P().m9995a()) {
            this.f9820a.m11919a(this.f9820a.getString(C1205R.string.disconnect_titlefaile), this.f9820a.getString(C1205R.string.disconnect_reson), false, false, false);
        } else if (!this.f9820a.f9673v.m9570Q()) {
            this.f9820a.m11919a(this.f9820a.getString(C1205R.string.unconnectdrone), this.f9820a.getString(C1205R.string.fc_tip_error), false, false, false);
        } else if (floatValue == 190.0f) {
            if (!this.f9820a.m11921b()) {
                if (C1189f.m8333c().m7937d(C1236a.f5621s)) {
                    this.f9820a.f9656e.postDelayed(this.f9820a.f9657f, 10);
                    return;
                }
                this.f9820a.f9656e.removeCallbacks(this.f9820a.f9657f);
                this.f9820a.startActivity(new Intent(this.f9820a, NewHandActivity.class));
                this.f9820a.finish();
                this.f9820a.overridePendingTransition(17432576, 17432577);
            }
        } else if (floatValue >= 200.0f) {
            this.f9820a.f9656e.removeCallbacks(this.f9820a.f9657f);
            if (this.f9820a.f9676y) {
                Editor edit = ay.m12293a(this.f9820a).edit();
                edit.putBoolean(C1236a.f5586J, true);
                edit.commit();
                this.f9820a.dpa.m8525d();
                return;
            }
            this.f9820a.startActivity(new Intent(this.f9820a, FlightActivity.class));
        } else if (this.f9820a.f9667p > 0.0f) {
            this.f9820a.f9656e.postDelayed(this.f9820a.f9657f, 10);
        }
    }
}
