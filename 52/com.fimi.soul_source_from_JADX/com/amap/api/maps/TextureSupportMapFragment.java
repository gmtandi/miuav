package com.amap.api.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore.ab;
import com.amap.api.mapcore.ag;
import com.amap.api.mapcore.at;
import com.amap.api.maps.model.RuntimeRemoteException;

public class TextureSupportMapFragment extends Fragment {
    private AMap f2639a;
    private ag f2640b;

    public static TextureSupportMapFragment newInstance() {
        return newInstance(new AMapOptions());
    }

    public static TextureSupportMapFragment newInstance(AMapOptions aMapOptions) {
        TextureSupportMapFragment textureSupportMapFragment = new TextureSupportMapFragment();
        Bundle bundle = new Bundle();
        try {
            Parcel obtain = Parcel.obtain();
            aMapOptions.writeToParcel(obtain, 0);
            bundle.putByteArray("MapOptions", obtain.marshall());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        textureSupportMapFragment.setArguments(bundle);
        return textureSupportMapFragment;
    }

    public AMap getMap() {
        ag mapFragmentDelegate = getMapFragmentDelegate();
        if (mapFragmentDelegate == null) {
            return null;
        }
        try {
            ab a = mapFragmentDelegate.m2645a();
            if (a == null) {
                return null;
            }
            if (this.f2639a == null) {
                this.f2639a = new AMap(a);
            }
            return this.f2639a;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    protected ag getMapFragmentDelegate() {
        if (this.f2640b == null) {
            this.f2640b = new at(at.f1621d);
        }
        this.f2640b.m2648a(getActivity());
        return this.f2640b;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = getArguments();
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return getMapFragmentDelegate().m2644a(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        try {
            getMapFragmentDelegate().m2655e();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        try {
            getMapFragmentDelegate().m2654d();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        try {
            getMapFragmentDelegate().m2647a(activity, new AMapOptions(), bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        try {
            getMapFragmentDelegate().m2656f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onPause() {
        super.onPause();
        try {
            getMapFragmentDelegate().m2653c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        try {
            getMapFragmentDelegate().m2651b();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().m2652b(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            getMapFragmentDelegate().m2646a(0);
        } else {
            getMapFragmentDelegate().m2646a(8);
        }
    }
}
