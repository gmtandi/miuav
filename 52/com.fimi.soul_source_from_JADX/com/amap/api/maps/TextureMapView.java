package com.amap.api.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore.ab;
import com.amap.api.mapcore.ag;
import com.amap.api.mapcore.at;
import com.amap.api.maps.model.RuntimeRemoteException;

public class TextureMapView extends FrameLayout {
    private ag f2636a;
    private AMap f2637b;
    private int f2638c;

    public TextureMapView(Context context) {
        super(context);
        this.f2638c = 0;
        getMapFragmentDelegate().m2648a(context);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2638c = 0;
        this.f2638c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().m2648a(context);
        getMapFragmentDelegate().m2646a(this.f2638c);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2638c = 0;
        this.f2638c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().m2648a(context);
        getMapFragmentDelegate().m2646a(this.f2638c);
    }

    public TextureMapView(Context context, AMapOptions aMapOptions) {
        super(context);
        this.f2638c = 0;
        getMapFragmentDelegate().m2648a(context);
        getMapFragmentDelegate().m2650a(aMapOptions);
    }

    public AMap getMap() {
        try {
            ab a = getMapFragmentDelegate().m2645a();
            if (a == null) {
                return null;
            }
            if (this.f2637b == null) {
                this.f2637b = new AMap(a);
            }
            return this.f2637b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    protected ag getMapFragmentDelegate() {
        if (this.f2636a == null) {
            this.f2636a = new at(at.f1621d);
        }
        return this.f2636a;
    }

    public final void onCreate(Bundle bundle) {
        try {
            addView(getMapFragmentDelegate().m2644a(null, null, bundle), new LayoutParams(-1, -1));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onDestroy() {
        try {
            getMapFragmentDelegate().m2655e();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onLowMemory() {
        try {
            getMapFragmentDelegate().m2656f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onPause() {
        try {
            getMapFragmentDelegate().m2653c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onResume() {
        try {
            getMapFragmentDelegate().m2651b();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().m2652b(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        getMapFragmentDelegate().m2646a(i);
    }
}
