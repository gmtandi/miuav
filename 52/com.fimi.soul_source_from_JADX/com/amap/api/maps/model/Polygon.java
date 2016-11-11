package com.amap.api.maps.model;

import android.os.RemoteException;
import com.amap.api.mapcore.ak;
import java.util.List;

public final class Polygon {
    private ak f2752a;

    public Polygon(ak akVar) {
        this.f2752a = akVar;
    }

    public boolean contains(LatLng latLng) {
        try {
            return this.f2752a.m2667a(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj instanceof Polygon) {
            try {
                z = this.f2752a.m2572a(((Polygon) obj).f2752a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public int getFillColor() {
        try {
            return this.f2752a.m2671i();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f2752a.m2574c();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.f2752a.m2672l();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getStrokeColor() {
        try {
            return this.f2752a.m2673m();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getStrokeWidth() {
        try {
            return this.f2752a.m2670h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f2752a.m2575d();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f2752a.m2577f();
        } catch (RemoteException e) {
            return super.hashCode();
        }
    }

    public boolean isVisible() {
        try {
            return this.f2752a.m2576e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f2752a.m2573b();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFillColor(int i) {
        try {
            this.f2752a.m2665a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f2752a.m2666a((List) list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeColor(int i) {
        try {
            this.f2752a.m2669b(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStrokeWidth(float f) {
        try {
            this.f2752a.m2668b(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f2752a.m2570a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        try {
            this.f2752a.m2568a(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
