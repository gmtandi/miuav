package com.amap.api.maps.model;

import android.graphics.Typeface;
import android.os.RemoteException;
import com.amap.api.mapcore.ao;

public final class Text {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    private ao f2781a;

    public Text(ao aoVar) {
        this.f2781a = aoVar;
    }

    public void destroy() {
        try {
            if (this.f2781a != null) {
                this.f2781a.m2122p();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        try {
            return !(obj instanceof Text) ? false : this.f2781a.m2101a(((Text) obj).f2781a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getAlignX() {
        try {
            return this.f2781a.m2699O();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getAlignY() {
        try {
            return this.f2781a.m2700P();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getBackgroundColor() {
        try {
            return this.f2781a.m2695K();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFontColor() {
        try {
            return this.f2781a.m2696L();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFontSize() {
        try {
            return this.f2781a.m2697M();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.f2781a.m2114h();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Object getObject() {
        return this.f2781a.m2125s();
    }

    public LatLng getPosition() {
        try {
            return this.f2781a.m2110e();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getRotate() {
        return this.f2781a.m2127u();
    }

    public String getText() {
        try {
            return this.f2781a.m2701a();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Typeface getTypeface() {
        try {
            return this.f2781a.m2698N();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        return this.f2781a.m2085G();
    }

    public int hashCode() {
        return this.f2781a.m2123q();
    }

    public boolean isVisible() {
        try {
            return this.f2781a.m2121o();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.f2781a.m2105b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setAlign(int i, int i2) {
        try {
            this.f2781a.m2704b(i, i2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setBackgroundColor(int i) {
        try {
            this.f2781a.m2703b(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFontColor(int i) {
        try {
            this.f2781a.m2705c(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFontSize(int i) {
        try {
            this.f2781a.m2707d(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setObject(Object obj) {
        this.f2781a.m2096a(obj);
    }

    public void setPosition(LatLng latLng) {
        try {
            this.f2781a.m2094a(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setRotate(float f) {
        try {
            this.f2781a.m2089a(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setText(String str) {
        try {
            this.f2781a.m2706c(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTypeface(Typeface typeface) {
        try {
            this.f2781a.m2702a(typeface);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.f2781a.m2106c(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        this.f2781a.m2102b(f);
    }
}
