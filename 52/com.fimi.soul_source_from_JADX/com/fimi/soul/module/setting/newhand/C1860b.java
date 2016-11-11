package com.fimi.soul.module.setting.newhand;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import com.fimi.soul.module.setting.C1886p;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.setting.newhand.b */
class C1860b extends Handler {
    final /* synthetic */ GpsSettingActivity f9500a;

    C1860b(GpsSettingActivity gpsSettingActivity) {
        this.f9500a = gpsSettingActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                C1886p.m11892a(this.f9500a.f9351u).m11895a();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f9500a.f9348r.m11825a();
            case Type.BYTE /*3*/:
                this.f9500a.f9348r.m11832b();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (!this.f9500a.f9330K) {
                    Toast.makeText(this.f9500a.f9347q, C1205R.string.gps_setting_get_return_height_fail, 0).show();
                    this.f9500a.f9342l.setText(C1873o.an);
                }
                if (!this.f9500a.f9332M) {
                    Toast.makeText(this.f9500a.f9347q, C1205R.string.gps_setting_flight_distance_fail, 0).show();
                    if (this.f9500a.f9346p.getToggleOn()) {
                        this.f9500a.f9346p.m8371a(false, true);
                        this.f9500a.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
                    }
                }
                if (!this.f9500a.f9331L) {
                    Toast.makeText(this.f9500a.f9347q, C1205R.string.get_setting_flight_speed_fail, 0).show();
                    this.f9500a.f9341k.setText(C1873o.an);
                }
            default:
        }
    }
}
