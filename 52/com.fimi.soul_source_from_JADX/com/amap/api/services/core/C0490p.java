package com.amap.api.services.core;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch.OnBusLineSearchListener;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch.OnBusStationSearchListener;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch.OnDistrictSearchListener;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.poisearch.PoiItemDetail;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.amap.api.services.core.p */
public class C0490p extends Handler {
    private static C0490p f3166a;

    /* renamed from: com.amap.api.services.core.p.a */
    public class C0484a {
        public BusLineResult f3154a;
        public OnBusLineSearchListener f3155b;
    }

    /* renamed from: com.amap.api.services.core.p.b */
    public class C0485b {
        public BusStationResult f3156a;
        public OnBusStationSearchListener f3157b;
    }

    /* renamed from: com.amap.api.services.core.p.c */
    public class C0486c {
        public GeocodeResult f3158a;
        public OnGeocodeSearchListener f3159b;
    }

    /* renamed from: com.amap.api.services.core.p.d */
    public class C0487d {
        public PoiItemDetail f3160a;
        public OnPoiSearchListener f3161b;
    }

    /* renamed from: com.amap.api.services.core.p.e */
    public class C0488e {
        public PoiResult f3162a;
        public OnPoiSearchListener f3163b;
    }

    /* renamed from: com.amap.api.services.core.p.f */
    public class C0489f {
        public RegeocodeResult f3164a;
        public OnGeocodeSearchListener f3165b;
    }

    C0490p() {
    }

    C0490p(Looper looper) {
        super(looper);
    }

    public static synchronized C0490p m4850a() {
        C0490p c0490p;
        synchronized (C0490p.class) {
            if (f3166a == null) {
                if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
                    f3166a = new C0490p(Looper.getMainLooper());
                } else {
                    f3166a = new C0490p();
                }
            }
            c0490p = f3166a;
        }
        return c0490p;
    }

    private void m4851a(Message message) {
        C0485b c0485b = (C0485b) message.obj;
        if (c0485b != null) {
            OnBusStationSearchListener onBusStationSearchListener = c0485b.f3157b;
            if (onBusStationSearchListener != null) {
                onBusStationSearchListener.onBusStationSearched(message.what == 0 ? c0485b.f3156a : null, message.what);
            }
        }
    }

    private void m4852b(Message message) {
        OnPoiSearchListener onPoiSearchListener;
        Bundle data;
        if (message.what == 60) {
            C0488e c0488e = (C0488e) message.obj;
            if (c0488e != null) {
                onPoiSearchListener = c0488e.f3163b;
                if (onPoiSearchListener != null) {
                    data = message.getData();
                    if (data != null) {
                        onPoiSearchListener.onPoiSearched(c0488e.f3162a, data.getInt("errorCode"));
                    }
                }
            }
        } else if (message.what == 61) {
            C0487d c0487d = (C0487d) message.obj;
            if (c0487d != null) {
                onPoiSearchListener = c0487d.f3161b;
                data = message.getData();
                if (data != null) {
                    onPoiSearchListener.onPoiItemDetailSearched(c0487d.f3160a, data.getInt("errorCode"));
                }
            }
        }
    }

    private void m4853c(Message message) {
        InputtipsListener inputtipsListener = (InputtipsListener) message.obj;
        if (inputtipsListener != null) {
            List list = null;
            if (message.what == 0) {
                list = message.getData().getParcelableArrayList("result");
            }
            inputtipsListener.onGetInputtips(list, message.what);
        }
    }

    private void m4854d(Message message) {
        OnGeocodeSearchListener onGeocodeSearchListener;
        if (message.what == 21) {
            C0489f c0489f = (C0489f) message.obj;
            if (c0489f != null) {
                onGeocodeSearchListener = c0489f.f3165b;
                if (onGeocodeSearchListener != null) {
                    onGeocodeSearchListener.onRegeocodeSearched(c0489f.f3164a, message.arg2);
                }
            }
        } else if (message.what == 20) {
            C0486c c0486c = (C0486c) message.obj;
            if (c0486c != null) {
                onGeocodeSearchListener = c0486c.f3159b;
                if (onGeocodeSearchListener != null) {
                    onGeocodeSearchListener.onGeocodeSearched(c0486c.f3158a, message.arg2);
                }
            }
        }
    }

    private void m4855e(Message message) {
        OnDistrictSearchListener onDistrictSearchListener = (OnDistrictSearchListener) message.obj;
        if (onDistrictSearchListener != null) {
            onDistrictSearchListener.onDistrictSearched((DistrictResult) message.getData().getParcelable("result"));
        }
    }

    private void m4856f(Message message) {
        C0484a c0484a = (C0484a) message.obj;
        if (c0484a != null) {
            OnBusLineSearchListener onBusLineSearchListener = c0484a.f3155b;
            if (onBusLineSearchListener != null) {
                onBusLineSearchListener.onBusLineSearched(message.what == 0 ? c0484a.f3154a : null, message.what);
            }
        }
    }

    private void m4857g(Message message) {
        OnRouteSearchListener onRouteSearchListener = (OnRouteSearchListener) message.obj;
        if (onRouteSearchListener != null) {
            Bundle data;
            if (message.what == 10) {
                data = message.getData();
                if (data != null) {
                    onRouteSearchListener.onBusRouteSearched((BusRouteResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
                }
            } else if (message.what == 11) {
                data = message.getData();
                if (data != null) {
                    onRouteSearchListener.onDriveRouteSearched((DriveRouteResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
                }
            } else if (message.what == 12) {
                data = message.getData();
                if (data != null) {
                    onRouteSearchListener.onWalkRouteSearched((WalkRouteResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
                }
            }
        }
    }

    public void handleMessage(Message message) {
        try {
            switch (message.arg1) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m4857g(message);
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    m4854d(message);
                    return;
                case Type.BYTE /*3*/:
                    m4856f(message);
                    return;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    m4855e(message);
                    return;
                case Type.INT /*5*/:
                    m4853c(message);
                    return;
                case Type.FLOAT /*6*/:
                    m4852b(message);
                    return;
                case Type.LONG /*7*/:
                    m4851a(message);
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            C0471d.m4762a(th, "MessageHandler", "handleMessage");
        }
        C0471d.m4762a(th, "MessageHandler", "handleMessage");
    }
}
