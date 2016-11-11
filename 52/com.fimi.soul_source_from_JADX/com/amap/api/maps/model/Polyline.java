package com.amap.api.maps.model;

import android.os.RemoteException;
import com.amap.api.mapcore.al;
import java.util.List;

public class Polyline {
    private final al f2760a;

    public Polyline(al alVar) {
        this.f2760a = alVar;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            return this.f2760a.m2572a(((Polyline) obj).f2760a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getColor() {
        try {
            return this.f2760a.m2683i();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f2760a.m2574c();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        return this.f2760a.m2674a(latLng);
    }

    public List<LatLng> getPoints() {
        try {
            return this.f2760a.m2684l();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        try {
            return this.f2760a.m2682h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.f2760a.m2575d();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.f2760a.m2577f();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isDottedLine() {
        return this.f2760a.m2686n();
    }

    public boolean isGeodesic() {
        return this.f2760a.m2685m();
    }

    public boolean isVisible() {
        try {
            return this.f2760a.m2576e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f2760a.m2573b();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setColor(int i) {
        try {
            this.f2760a.m2675a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDottedLine(boolean z) {
        this.f2760a.m2681c(z);
    }

    public void setGeodesic(boolean z) {
        try {
            if (this.f2760a.m2685m() != z) {
                List points = getPoints();
                this.f2760a.m2678b(z);
                setPoints(points);
            }
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            this.f2760a.m2676a((List) list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTransparency(float f) {
        this.f2760a.m2680c(f);
    }

    public void setVisible(boolean z) {
        try {
            this.f2760a.m2570a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setWidth(float f) {
        try {
            this.f2760a.m2677b(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        try {
            this.f2760a.m2568a(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
