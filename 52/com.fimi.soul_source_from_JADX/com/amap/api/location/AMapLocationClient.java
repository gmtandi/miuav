package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.fence.Fence;
import com.p016a.C0238a;
import com.p016a.ak;
import com.p016a.ev;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class AMapLocationClient implements LocationManagerBase {
    C0266a f1370a;
    Context f1371b;
    LocationManagerBase f1372c;
    AMapLocationClientOption f1373d;
    AMapLocationListener f1374e;
    AMapLocationClient f1375f;

    /* renamed from: com.amap.api.location.AMapLocationClient.a */
    class C0266a extends Handler {
        AMapLocationClient f1369a;

        public C0266a(AMapLocationClient aMapLocationClient) {
            this.f1369a = null;
            this.f1369a = aMapLocationClient;
        }

        public C0266a(AMapLocationClient aMapLocationClient, Looper looper) {
            super(looper);
            this.f1369a = null;
            this.f1369a = aMapLocationClient;
        }

        public void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                switch (message.arg1) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        this.f1369a.f1373d = (AMapLocationClientOption) message.obj;
                        this.f1369a.f1372c.setLocationOption(this.f1369a.f1373d);
                        return;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        try {
                            this.f1369a.f1374e = (AMapLocationListener) message.obj;
                            this.f1369a.f1372c.setLocationListener(this.f1369a.f1374e);
                            return;
                        } catch (Throwable th) {
                            ev.m1777a(th, "AMapLocationClient", "handleMessage SET_LISTENER");
                            return;
                        }
                    case Type.BYTE /*3*/:
                        try {
                            this.f1369a.f1372c.startLocation();
                            return;
                        } catch (Throwable th2) {
                            ev.m1777a(th2, "AMapLocationClient", "handleMessage START_LOCATION");
                            return;
                        }
                    case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                        try {
                            this.f1369a.f1372c.stopLocation();
                            return;
                        } catch (Throwable th22) {
                            ev.m1777a(th22, "AMapLocationClient", "handleMessage STOP_LOCATION");
                            return;
                        }
                    case Type.INT /*5*/:
                        try {
                            this.f1369a.f1374e = (AMapLocationListener) message.obj;
                            this.f1369a.f1372c.unRegisterLocationListener(this.f1369a.f1374e);
                            return;
                        } catch (Throwable th222) {
                            ev.m1777a(th222, "AMapLocationClient", "handleMessage REMOVE_LISTENER");
                            return;
                        }
                    case Type.FLOAT /*6*/:
                        try {
                            Fence fence = (Fence) message.obj;
                            this.f1369a.f1372c.addGeoFenceAlert(fence.f1340b, fence.f1342d, fence.f1341c, fence.f1343e, fence.f1344f, fence.f1339a);
                            return;
                        } catch (Throwable th2222) {
                            ev.m1777a(th2222, "AMapLocationClient", "handleMessage ADD_GEOFENCE");
                            return;
                        }
                    case Type.LONG /*7*/:
                        try {
                            this.f1369a.f1372c.removeGeoFenceAlert((PendingIntent) message.obj);
                            return;
                        } catch (Throwable th22222) {
                            ev.m1777a(th22222, "AMapLocationClient", "handleMessage REMOVE_GEOFENCE");
                            return;
                        }
                    case Type.DOUBLE /*8*/:
                        try {
                            this.f1369a.f1372c.startAssistantLocation();
                            return;
                        } catch (Throwable th222222) {
                            ev.m1777a(th222222, "AMapLocationClient", "handleMessage START_SOCKET");
                            return;
                        }
                    case Type.ARRAY /*9*/:
                        try {
                            this.f1369a.f1372c.stopAssistantLocation();
                            return;
                        } catch (Throwable th2222222) {
                            ev.m1777a(th2222222, "AMapLocationClient", "handleMessage STOP_SOCKET");
                            return;
                        }
                    case Type.OBJECT /*10*/:
                        try {
                            Fence fence2 = (Fence) message.obj;
                            this.f1369a.f1372c.removeGeoFenceAlert(fence2.f1339a, fence2.f1340b);
                            return;
                        } catch (Throwable th22222222) {
                            ev.m1777a(th22222222, "AMapLocationClient", "handleMessage REMOVE_GEOFENCE_ONE");
                            return;
                        }
                    case Opcodes.T_LONG /*11*/:
                        try {
                            this.f1369a.f1372c.onDestroy();
                            this.f1369a.f1372c = null;
                            this.f1369a = null;
                            return;
                        } catch (Throwable th222222222) {
                            ev.m1777a(th222222222, "AMapLocationClient", "handleMessage DESTROY");
                            return;
                        }
                    default:
                        return;
                }
            } catch (Throwable th2222222222) {
                ev.m1777a(th2222222222, "AMapLocationClient", "handleMessage end");
            }
            ev.m1777a(th2222222222, "AMapLocationClient", "handleMessage end");
        }
    }

    public AMapLocationClient(Context context) {
        if (context == null) {
            try {
                throw new IllegalArgumentException("Context\u53c2\u6570\u4e0d\u80fd\u4e3anull");
            } catch (Throwable th) {
                ev.m1777a(th, "AMapLocationClient", "AMapLocationClient 1");
                return;
            }
        }
        this.f1371b = context.getApplicationContext();
        this.f1375f = new AMapLocationClient(this.f1371b, null, true);
        if (Looper.myLooper() == null) {
            this.f1370a = new C0266a(this.f1375f, this.f1371b.getMainLooper());
        } else {
            this.f1370a = new C0266a(this.f1375f);
        }
    }

    public AMapLocationClient(Context context, Intent intent) {
        if (context == null) {
            try {
                throw new IllegalArgumentException("Context\u53c2\u6570\u4e0d\u80fd\u4e3anull");
            } catch (Throwable th) {
                ev.m1777a(th, "AMapLocationClient", "AMapLocationClient 2");
                return;
            }
        }
        this.f1371b = context.getApplicationContext();
        this.f1375f = new AMapLocationClient(this.f1371b, intent, true);
        if (Looper.myLooper() == null) {
            this.f1370a = new C0266a(this.f1375f, this.f1371b.getMainLooper());
        } else {
            this.f1370a = new C0266a(this.f1375f);
        }
    }

    private AMapLocationClient(Context context, Intent intent, boolean z) {
        try {
            this.f1371b = context;
            Context context2 = context;
            this.f1372c = (LocationManagerBase) ak.m1032a(context2, ev.m1772a("2.4.1"), "com.amap.api.location.LocationManagerWrapper", C0238a.class, new Class[]{Context.class, Intent.class}, new Object[]{context, intent});
        } catch (Throwable th) {
            this.f1372c = new C0238a(context, intent);
            ev.m1777a(th, "AMapLocationClient", "AMapLocationClient 3");
        }
    }

    public static void setApiKey(String str) {
        try {
            ev.f1142a = str;
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "setApiKey");
        }
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            Message obtain = Message.obtain();
            Fence fence = new Fence();
            fence.f1340b = str;
            fence.f1342d = d;
            fence.f1341c = d2;
            fence.f1343e = f;
            fence.f1339a = pendingIntent;
            fence.f1344f = j;
            obtain.obj = fence;
            obtain.arg1 = 6;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "addGeoFenceAlert");
        }
    }

    public AMapLocation getLastKnownLocation() {
        try {
            if (!(this.f1375f == null || this.f1375f.f1372c == null)) {
                return this.f1375f.f1372c.getLastKnownLocation();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "getLastKnownLocation");
        }
        return null;
    }

    public String getVersion() {
        try {
            if (this.f1375f != null) {
                return this.f1375f.f1372c.getVersion();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "getVersion");
        }
        return null;
    }

    public boolean isStarted() {
        try {
            if (this.f1375f != null) {
                return this.f1375f.f1372c.isStarted();
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "isStarted");
        }
        return false;
    }

    public void onDestroy() {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 11;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "onDestroy");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            Message obtain = Message.obtain();
            obtain.obj = pendingIntent;
            obtain.arg1 = 7;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "removeGeoFenceAlert 2");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            Message obtain = Message.obtain();
            Fence fence = new Fence();
            fence.f1340b = str;
            fence.f1339a = pendingIntent;
            obtain.obj = fence;
            obtain.arg1 = 10;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "removeGeoFenceAlert 1");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            try {
                throw new IllegalArgumentException("listener\u53c2\u6570\u4e0d\u80fd\u4e3anull");
            } catch (Throwable th) {
                ev.m1777a(th, "AMapLocationClient", "setLocationListener");
                return;
            }
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 2;
        obtain.obj = aMapLocationListener;
        this.f1370a.sendMessage(obtain);
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            try {
                throw new IllegalArgumentException("LocationManagerOption\u53c2\u6570\u4e0d\u80fd\u4e3anull");
            } catch (Throwable th) {
                ev.m1777a(th, "AMapLocationClient", "setLocationOption");
                return;
            }
        }
        Message obtain = Message.obtain();
        obtain.arg1 = 1;
        obtain.obj = aMapLocationClientOption;
        this.f1370a.sendMessage(obtain);
    }

    public void startAssistantLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 8;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "startAssistantLocation");
        }
    }

    public void startLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 3;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 9;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 4;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            Message obtain = Message.obtain();
            obtain.arg1 = 5;
            obtain.obj = aMapLocationListener;
            this.f1370a.sendMessage(obtain);
        } catch (Throwable th) {
            ev.m1777a(th, "AMapLocationClient", "unRegisterLocationListener");
        }
    }
}
