package com.amap.api.maps.model;

import android.os.RemoteException;
import com.amap.api.mapcore.ah;
import com.autonavi.amap.mapcore.IPoint;
import java.util.ArrayList;

public final class Marker {
    private ah f2710a;

    public Marker(ah ahVar) {
        this.f2710a = ahVar;
    }

    public void destroy() {
        try {
            if (this.f2710a != null) {
                this.f2710a.m2122p();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        try {
            return !(obj instanceof Marker) ? false : this.f2710a.m2101a(((Marker) obj).f2710a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public IPoint getGeoPoint() {
        return this.f2710a.m2087I();
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        try {
            return this.f2710a.m2129w();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f2710a.m2114h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Object getObject() {
        return this.f2710a.m2125s();
    }

    public int getPeriod() {
        try {
            return this.f2710a.m2128v();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getPosition() {
        try {
            return this.f2710a.m2110e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getRotateAngle() {
        return this.f2710a.m2127u();
    }

    public String getSnippet() {
        try {
            return this.f2710a.m2116j();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getTitle() {
        try {
            return this.f2710a.m2115i();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        return this.f2710a.m2085G();
    }

    public int hashCode() {
        return this.f2710a.m2123q();
    }

    public void hideInfoWindow() {
        try {
            this.f2710a.m2119m();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isDraggable() {
        return this.f2710a.m2117k();
    }

    public boolean isFlat() {
        return this.f2710a.m2079A();
    }

    public boolean isInfoWindowShown() {
        return this.f2710a.m2120n();
    }

    public boolean isPerspective() {
        try {
            return this.f2710a.m2126t();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.f2710a.m2121o();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f2710a.m2105b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setAnchor(float f, float f2) {
        try {
            this.f2710a.m2090a(f, f2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDraggable(boolean z) {
        try {
            this.f2710a.m2100a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFlat(boolean z) {
        try {
            this.f2710a.m2111e(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeoPoint(IPoint iPoint) {
        this.f2710a.m2095a(iPoint);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                this.f2710a.m2093a(bitmapDescriptor);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        try {
            this.f2710a.m2098a((ArrayList) arrayList);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setObject(Object obj) {
        this.f2710a.m2096a(obj);
    }

    public void setPeriod(int i) {
        try {
            this.f2710a.m2091a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPerspective(boolean z) {
        try {
            this.f2710a.m2109d(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.f2710a.m2094a(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPositionByPixels(int i, int i2) {
        this.f2710a.m2092a(i, i2);
    }

    public void setRotateAngle(float f) {
        try {
            this.f2710a.m2089a(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setSnippet(String str) {
        try {
            this.f2710a.m2103b(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTitle(String str) {
        try {
            this.f2710a.m2097a(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setToTop() {
        try {
            this.f2710a.m2132z();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f2710a.m2106c(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        this.f2710a.m2102b(f);
    }

    public void showInfoWindow() {
        try {
            this.f2710a.m2118l();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
