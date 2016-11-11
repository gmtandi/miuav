package com.fimi.soul.p085a.p086a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* renamed from: com.fimi.soul.a.a.a */
public class C1206a implements SensorEventListener {
    boolean f5443a;
    private Context f5444b;
    private SensorManager f5445c;
    private Sensor f5446d;
    private Sensor f5447e;
    private float f5448f;
    private C1207b f5449g;

    public C1206a(Context context) {
        this.f5443a = true;
        this.f5444b = context;
    }

    public void m8426a() {
        this.f5445c = (SensorManager) this.f5444b.getSystemService("sensor");
        if (this.f5445c != null) {
            this.f5447e = this.f5445c.getDefaultSensor(3);
            this.f5446d = this.f5445c.getDefaultSensor(4);
        }
        if (this.f5447e != null || this.f5446d != null) {
            this.f5445c.registerListener(this, this.f5447e, 2);
            SensorManager sensorManager = this.f5445c;
            Sensor sensor = this.f5446d;
            SensorManager sensorManager2 = this.f5445c;
            sensorManager.registerListener(this, sensor, 2);
        }
    }

    public void m8427a(C1207b c1207b) {
        this.f5449g = c1207b;
    }

    public void m8428b() {
        if (this.f5445c != null) {
            this.f5445c.unregisterListener(this);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 4 && ((double) Math.abs(sensorEvent.values[2])) > 0.1d) {
            this.f5443a = true;
        }
        if (sensorEvent.sensor.getType() == 3 && this.f5443a) {
            this.f5443a = false;
            float f = sensorEvent.values[0];
            if (((double) Math.abs(f - this.f5448f)) > 1.5d) {
                this.f5449g.m8429a(f);
                this.f5448f = f;
            }
        }
    }
}
