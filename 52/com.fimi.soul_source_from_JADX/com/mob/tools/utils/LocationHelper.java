package com.mob.tools.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.ArrayList;

public class LocationHelper implements LocationListener, Callback {
    private int GPSTimeoutSec;
    private boolean gpsRequesting;
    private Handler handler;
    private LocationManager lm;
    private boolean networkRequesting;
    int networkTimeoutSec;
    private ArrayList<Float> res;

    public LocationHelper() {
        HandlerThread handlerThread = new HandlerThread("LocationUpdater");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), this);
        this.res = new ArrayList();
    }

    private void onGPSTimeout() {
        this.lm.removeUpdates(this);
        this.gpsRequesting = false;
        if (this.lm.isProviderEnabled("network")) {
            this.networkRequesting = true;
            this.lm.requestLocationUpdates("network", 1000, 0.0f, this);
            if (this.networkTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
                return;
            }
            return;
        }
        synchronized (this) {
            notifyAll();
        }
        this.handler.getLooper().quit();
    }

    private void onRequest() {
        if (this.lm.isProviderEnabled(GeocodeSearch.GPS)) {
            this.gpsRequesting = true;
            this.lm.requestLocationUpdates(GeocodeSearch.GPS, 1000, 0.0f, this);
            if (this.GPSTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.GPSTimeoutSec * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
            }
        } else if (this.lm.isProviderEnabled("network")) {
            this.networkRequesting = true;
            this.lm.requestLocationUpdates("network", 1000, 0.0f, this);
            if (this.networkTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
            }
        } else {
            synchronized (this) {
                notifyAll();
            }
            this.handler.getLooper().quit();
        }
    }

    public float[] getLocation(Context context) {
        return getLocation(context, 0);
    }

    public float[] getLocation(Context context, int i) {
        return getLocation(context, i, 0);
    }

    public float[] getLocation(Context context, int i, int i2) {
        this.GPSTimeoutSec = i;
        this.networkTimeoutSec = i2;
        this.lm = (LocationManager) context.getSystemService("location");
        if (this.lm == null) {
            return null;
        }
        synchronized (this) {
            this.handler.sendEmptyMessageDelayed(0, 50);
            wait();
        }
        if (this.res.size() <= 0) {
            return null;
        }
        return new float[]{((Float) this.res.get(0)).floatValue(), ((Float) this.res.get(1)).floatValue()};
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            onRequest();
        } else if (this.gpsRequesting) {
            onGPSTimeout();
        } else if (this.networkRequesting) {
            this.lm.removeUpdates(this);
            synchronized (this) {
                notifyAll();
            }
            this.handler.getLooper().quit();
        }
        return false;
    }

    public void onLocationChanged(Location location) {
        synchronized (this) {
            this.lm.removeUpdates(this);
            if (location != null) {
                this.res.add(Float.valueOf((float) location.getLatitude()));
                this.res.add(Float.valueOf((float) location.getLongitude()));
            }
            notifyAll();
        }
        this.handler.getLooper().quit();
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
