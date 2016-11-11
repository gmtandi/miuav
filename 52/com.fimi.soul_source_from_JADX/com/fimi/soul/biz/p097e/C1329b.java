package com.fimi.soul.biz.p097e;

import com.fimi.soul.entity.BatteryOverDischange;
import com.fimi.soul.utils.be;
import com.xiaomi.market.sdk.C2537j;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.e.b */
class C1329b implements Runnable {
    final /* synthetic */ C1328a f5952a;

    C1329b(C1328a c1328a) {
        this.f5952a = c1328a;
    }

    public void run() {
        if (be.m12370b(this.f5952a.f5950b)) {
            List<BatteryOverDischange> a = this.f5952a.f5949a.m12458a();
            if (a.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                try {
                    for (BatteryOverDischange batteryOverDischange : a) {
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.f5952a.f5951c.m9277c(jSONArray.toString(), new C1331c(this));
            }
        }
    }
}
