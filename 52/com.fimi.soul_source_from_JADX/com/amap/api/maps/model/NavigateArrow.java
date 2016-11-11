package com.amap.api.maps.model;

import android.os.RemoteException;
import com.amap.api.mapcore.ai;
import java.util.List;

public class NavigateArrow {
    private final ai f2739a;

    public NavigateArrow(ai aiVar) {
        this.f2739a = aiVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NavigateArrow)) {
            return false;
        }
        try {
            return this.f2739a.m2572a(((NavigateArrow) obj).f2739a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f2739a.m2574c();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.f2739a.m2664m();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public int getSideColor() {
        try {
            return this.f2739a.m2663l();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getTopColor() {
        try {
            return this.f2739a.m2662i();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        try {
            return this.f2739a.m2661h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f2739a.m2575d();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f2739a.m2577f();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f2739a.m2576e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f2739a.m2573b();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f2739a.m2658a((List) list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public void setSideColor(int i) {
        try {
            this.f2739a.m2660b(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTopColor(int i) {
        try {
            this.f2739a.m2657a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f2739a.m2570a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setWidth(float f) {
        try {
            this.f2739a.m2659b(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        try {
            this.f2739a.m2568a(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
