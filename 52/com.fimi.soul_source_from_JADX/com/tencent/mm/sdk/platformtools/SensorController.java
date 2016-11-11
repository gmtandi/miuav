package com.tencent.mm.sdk.platformtools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.org.objectweb.asm.Type;

public class SensorController extends BroadcastReceiver implements SensorEventListener {
    private static float aR;
    private static float aU;
    private SensorManager aS;
    private float aT;
    private SensorEventCallBack aV;
    private Sensor aW;
    private final boolean aX;
    private boolean aY;
    private boolean aZ;

    public interface SensorEventCallBack {
        void onSensorEvent(boolean z);
    }

    static {
        aR = 4.2949673E9f;
        aU = 0.5f;
    }

    public SensorController(Context context) {
        this.aY = false;
        this.aZ = false;
        this.aS = (SensorManager) context.getSystemService("sensor");
        this.aW = this.aS.getDefaultSensor(8);
        this.aX = this.aW != null;
        this.aT = aU + C2020f.f10933c;
    }

    public boolean isSensorEnable() {
        return this.aX;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            int intExtra = intent.getIntExtra(XiaomiOAuthConstants.EXTRA_STATE_2, 0);
            if (intExtra == 1) {
                this.aY = true;
            }
            if (intExtra == 0) {
                this.aY = false;
            }
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!this.aY) {
            float f = sensorEvent.values[0];
            switch (sensorEvent.sensor.getType()) {
                case Type.DOUBLE /*8*/:
                    if (f < aR) {
                        aR = f;
                        aU = 0.5f + f;
                    }
                    if (this.aT < aU || f >= aU) {
                        if (this.aT <= aU && f > aU && this.aV != null) {
                            Log.m13547v("MicroMsg.SensorController", "sensor event true");
                            this.aV.onSensorEvent(true);
                        }
                    } else if (this.aV != null) {
                        Log.m13547v("MicroMsg.SensorController", "sensor event false");
                        this.aV.onSensorEvent(false);
                    }
                    this.aT = f;
                default:
            }
        }
    }

    public void removeSensorCallBack() {
        Log.m13547v("MicroMsg.SensorController", "sensor callback removed");
        this.aS.unregisterListener(this, this.aW);
        this.aS.unregisterListener(this);
        this.aZ = false;
        this.aV = null;
    }

    public void setSensorCallBack(SensorEventCallBack sensorEventCallBack) {
        Log.m13547v("MicroMsg.SensorController", "sensor callback set");
        if (!this.aZ) {
            this.aS.registerListener(this, this.aW, 2);
            this.aZ = true;
        }
        this.aV = sensorEventCallBack;
    }
}
