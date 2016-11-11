package com.amap.api.maps;

import android.os.RemoteException;
import com.amap.api.mapcore.aq;
import com.amap.api.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final aq f2641a;

    UiSettings(aq aqVar) {
        this.f2641a = aqVar;
    }

    public int getLogoPosition() {
        try {
            return this.f2641a.m2742j();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getZoomPosition() {
        try {
            return this.f2641a.m2744k();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isCompassEnabled() {
        try {
            return this.f2641a.m2731d();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isIndoorSwitchEnabled() {
        try {
            return this.f2641a.m2724a();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isMyLocationButtonEnabled() {
        try {
            return this.f2641a.m2733e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isRotateGesturesEnabled() {
        try {
            return this.f2641a.m2741i();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScaleControlsEnabled() {
        try {
            return this.f2641a.m2727b();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScrollGesturesEnabled() {
        try {
            return this.f2641a.m2735f();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isTiltGesturesEnabled() {
        try {
            return this.f2641a.m2739h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomControlsEnabled() {
        try {
            return this.f2641a.m2729c();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.f2641a.m2737g();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setAllGesturesEnabled(boolean z) {
        try {
            this.f2641a.m2743j(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setCompassEnabled(boolean z) {
        try {
            this.f2641a.m2730d(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIndoorSwitchEnabled(boolean z) {
        try {
            this.f2641a.m2723a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setLogoPosition(int i) {
        try {
            this.f2641a.m2722a(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        try {
            this.f2641a.m2732e(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setRotateGesturesEnabled(boolean z) {
        try {
            this.f2641a.m2740i(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setScaleControlsEnabled(boolean z) {
        try {
            this.f2641a.m2726b(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setScrollGesturesEnabled(boolean z) {
        try {
            this.f2641a.m2734f(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTiltGesturesEnabled(boolean z) {
        try {
            this.f2641a.m2738h(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomControlsEnabled(boolean z) {
        try {
            this.f2641a.m2728c(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomGesturesEnabled(boolean z) {
        try {
            this.f2641a.m2736g(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomPosition(int i) {
        try {
            this.f2641a.m2725b(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
