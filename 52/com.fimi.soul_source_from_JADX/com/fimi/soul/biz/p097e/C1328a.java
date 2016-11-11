package com.fimi.soul.biz.p097e;

import android.content.Context;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.BatteryOverDischange;
import com.fimi.soul.utils.C1963c;
import com.fimi.soul.utils.be;
import com.xiaomi.market.sdk.C2537j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.e.a */
public class C1328a {
    private C1963c f5949a;
    private Context f5950b;
    private ba f5951c;

    public C1328a(Context context) {
        this.f5950b = context;
        this.f5949a = C1963c.m12455a(this.f5950b);
        this.f5951c = ba.m9259a(this.f5950b);
    }

    public void m8949a() {
        ah.m8077b(new C1329b(this));
    }

    public void m8950a(BatteryOverDischange batteryOverDischange) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userID", batteryOverDischange.getUserId());
            jSONObject.put("batteryId", batteryOverDischange.getBatteryId());
            jSONObject.put("battery_level", batteryOverDischange.getBatteryLevel());
            jSONObject.put("voltage", batteryOverDischange.getVoltage());
            jSONObject.put("battery_current", batteryOverDischange.getBatteryCurrent());
            jSONObject.put("temperature", batteryOverDischange.getTemperature());
            jSONObject.put("battery_full", batteryOverDischange.getBatteryFull());
            jSONObject.put("battery_one", batteryOverDischange.getBatteryOne());
            jSONObject.put("battery_two", batteryOverDischange.getBatteryTwo());
            jSONObject.put("battery_three", batteryOverDischange.getBatteryThree());
            jSONObject.put("battery_four", batteryOverDischange.getBatteryFour());
            jSONObject.put("battery_recyle", batteryOverDischange.getBatteryRecyle());
            jSONObject.put(C2537j.aq, batteryOverDischange.getVersion());
            jSONObject.put("appType", batteryOverDischange.getAppType());
            jSONArray.put(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (be.m12370b(this.f5950b)) {
            this.f5951c.m9277c(jSONArray.toString(), new C1332d(this, batteryOverDischange));
        } else {
            this.f5949a.m12457a(batteryOverDischange);
        }
    }
}
