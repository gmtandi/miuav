package com.amap.api.mapcore;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.model.CameraPosition;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public class at implements ag {
    public static volatile Context f1619a;
    public static int f1620c;
    public static int f1621d;
    public int f1622b;
    private ab f1623e;
    private int f1624f;
    private AMapOptions f1625g;

    static {
        f1620c = 0;
        f1621d = 1;
    }

    public at(int i) {
        int i2 = 0;
        this.f1622b = 0;
        this.f1624f = 0;
        if (i > 0) {
            i2 = 1;
        }
        this.f1624f = i2;
    }

    public View m2780a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f1623e == null) {
            if (f1619a == null && layoutInflater != null) {
                f1619a = layoutInflater.getContext().getApplicationContext();
            }
            if (f1619a == null) {
                throw new NullPointerException("Context \u4e3a null \u8bf7\u5728\u5730\u56fe\u8c03\u7528\u4e4b\u524d \u4f7f\u7528 MapsInitializer.initialize(Context paramContext) \u6765\u8bbe\u7f6eContext");
            }
            int i = f1619a.getResources().getDisplayMetrics().densityDpi;
            if (i <= Opcodes.ISHL) {
                C0330s.f2068a = 0.5f;
            } else if (i <= SmileConstants.TOKEN_PREFIX_SHORT_UNICODE) {
                C0330s.f2068a = 0.6f;
            } else if (i <= 240) {
                C0330s.f2068a = 0.87f;
            } else if (i <= 320) {
                C0330s.f2068a = C2020f.f10933c;
            } else if (i <= 480) {
                C0330s.f2068a = 1.5f;
            } else if (i <= 640) {
                C0330s.f2068a = 1.8f;
            } else {
                C0330s.f2068a = 0.9f;
            }
            if (this.f1624f == f1620c) {
                this.f1623e = new C0317j(f1619a).m3230a();
            } else {
                this.f1623e = new C0319k(f1619a).m3244a();
            }
            this.f1623e.m2304h(this.f1622b);
        }
        try {
            if (this.f1625g == null && bundle != null) {
                byte[] byteArray = bundle.getByteArray("MapOptions");
                if (byteArray != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    this.f1625g = AMapOptions.CREATOR.createFromParcel(obtain);
                }
            }
            m2789b(this.f1625g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f1623e.m2215D();
    }

    public ab m2781a() {
        if (this.f1623e == null) {
            if (f1619a == null) {
                throw new NullPointerException("Context \u4e3a null \u8bf7\u5728\u5730\u56fe\u8c03\u7528\u4e4b\u524d \u4f7f\u7528 MapsInitializer.initialize(Context paramContext) \u6765\u8bbe\u7f6eContext");
            }
            int i = f1619a.getResources().getDisplayMetrics().densityDpi;
            if (i <= Opcodes.ISHL) {
                C0330s.f2068a = 0.5f;
            } else if (i <= SmileConstants.TOKEN_PREFIX_SHORT_UNICODE) {
                C0330s.f2068a = 0.8f;
            } else if (i <= 240) {
                C0330s.f2068a = 0.87f;
            } else if (i <= 320) {
                C0330s.f2068a = C2020f.f10933c;
            } else if (i <= 480) {
                C0330s.f2068a = 1.5f;
            } else if (i <= 640) {
                C0330s.f2068a = 1.8f;
            } else {
                C0330s.f2068a = 0.9f;
            }
            if (this.f1624f == f1620c) {
                this.f1623e = new C0317j(f1619a).m3230a();
            } else {
                this.f1623e = new C0319k(f1619a).m3244a();
            }
        }
        return this.f1623e;
    }

    public void m2782a(int i) {
        this.f1622b = i;
        if (this.f1623e != null) {
            this.f1623e.m2304h(i);
        }
    }

    public void m2783a(Activity activity, AMapOptions aMapOptions, Bundle bundle) {
        f1619a = activity.getApplicationContext();
        this.f1625g = aMapOptions;
    }

    public void m2784a(Context context) {
        if (context != null) {
            f1619a = context.getApplicationContext();
        }
    }

    public void m2785a(Bundle bundle) {
    }

    public void m2786a(AMapOptions aMapOptions) {
        this.f1625g = aMapOptions;
    }

    public void m2787b() {
        if (this.f1623e != null) {
            this.f1623e.m2291d();
        }
    }

    public void m2788b(Bundle bundle) {
        if (this.f1623e != null) {
            if (this.f1625g == null) {
                this.f1625g = new AMapOptions();
            }
            try {
                Parcel obtain = Parcel.obtain();
                this.f1625g = this.f1625g.camera(m2781a().m2315n(false));
                this.f1625g.writeToParcel(obtain, 0);
                bundle.putByteArray("MapOptions", obtain.marshall());
            } catch (Throwable th) {
            }
        }
    }

    void m2789b(AMapOptions aMapOptions) {
        if (aMapOptions != null && this.f1623e != null) {
            CameraPosition camera = aMapOptions.getCamera();
            if (camera != null) {
                this.f1623e.m2253a(C0325p.m3299a(camera.target, camera.zoom, camera.bearing, camera.tilt));
            }
            aq A = this.f1623e.m2213A();
            A.m2740i(aMapOptions.getRotateGesturesEnabled().booleanValue());
            A.m2734f(aMapOptions.getScrollGesturesEnabled().booleanValue());
            A.m2738h(aMapOptions.getTiltGesturesEnabled().booleanValue());
            A.m2728c(aMapOptions.getZoomControlsEnabled().booleanValue());
            A.m2736g(aMapOptions.getZoomGesturesEnabled().booleanValue());
            A.m2730d(aMapOptions.getCompassEnabled().booleanValue());
            A.m2726b(aMapOptions.getScaleControlsEnabled().booleanValue());
            A.m2722a(aMapOptions.getLogoPosition());
            this.f1623e.m2282b(aMapOptions.getMapType());
            this.f1623e.m2302g(aMapOptions.getZOrderOnTop().booleanValue());
        }
    }

    public void m2790c() {
        if (this.f1623e != null) {
            this.f1623e.m2294e();
        }
    }

    public void m2791d() {
    }

    public void m2792e() {
        if (this.f1623e != null) {
            this.f1623e.m2323v();
            this.f1623e.m2303h();
            this.f1623e = null;
        }
    }

    public void m2793f() {
        Log.d("onLowMemory", "onLowMemory run");
    }
}
