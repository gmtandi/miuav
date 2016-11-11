package com.amap.api.mapcore;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.RemoteException;
import android.view.WindowManager;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class bk implements SensorEventListener {
    private SensorManager f1791a;
    private Sensor f1792b;
    private long f1793c;
    private final int f1794d;
    private float f1795e;
    private Context f1796f;
    private ab f1797g;
    private Marker f1798h;

    public bk(Context context, ab abVar) {
        this.f1793c = 0;
        this.f1794d = 100;
        this.f1796f = context;
        this.f1797g = abVar;
        this.f1791a = (SensorManager) context.getSystemService("sensor");
        this.f1792b = this.f1791a.getDefaultSensor(3);
    }

    public static int m3039a(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return 0;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return 90;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return Opcodes.GETFIELD;
            case Type.BYTE /*3*/:
                return -90;
            default:
                return 0;
        }
    }

    public void m3040a() {
        this.f1791a.registerListener(this, this.f1792b, 3);
    }

    public void m3041a(Marker marker) {
        this.f1798h = marker;
    }

    public void m3042b() {
        this.f1791a.unregisterListener(this, this.f1792b);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (System.currentTimeMillis() - this.f1793c >= 100 && this.f1797g.m2227S().m3279a()) {
            switch (sensorEvent.sensor.getType()) {
                case Type.BYTE /*3*/:
                    float a = (sensorEvent.values[0] + ((float) m3039a(this.f1796f))) % 360.0f;
                    if (a > BitmapDescriptorFactory.HUE_CYAN) {
                        a -= 360.0f;
                    } else if (a < -180.0f) {
                        a += 360.0f;
                    }
                    if (Math.abs(this.f1795e - a) >= C2020f.f10931a) {
                        if (Float.isNaN(a)) {
                            a = 0.0f;
                        }
                        this.f1795e = a;
                        if (this.f1798h != null) {
                            try {
                                this.f1797g.m2253a(C0325p.m3308d(this.f1795e));
                                this.f1798h.setRotateAngle(-this.f1795e);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        this.f1793c = System.currentTimeMillis();
                    }
                default:
            }
        }
    }
}
