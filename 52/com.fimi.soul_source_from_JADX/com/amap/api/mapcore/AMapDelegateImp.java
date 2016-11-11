package com.amap.api.mapcore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.mapcore.C0325p.C0324a;
import com.amap.api.mapcore.ar.C0286a;
import com.amap.api.mapcore.az.C0302a;
import com.amap.api.mapcore.util.C0364c;
import com.amap.api.mapcore.util.C0364c.C0279a;
import com.amap.api.mapcore.util.C0375d;
import com.amap.api.mapcore.util.C0375d.C0288a;
import com.amap.api.mapcore.util.C0381f;
import com.amap.api.mapcore.util.au;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.bl;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnCacheRemoveListener;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnIndoorBuildingActiveListener;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapLongClickListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.AMap.OnMapTouchListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMap.OnPOIClickListener;
import com.amap.api.maps.AMap.OnPolylineClickListener;
import com.amap.api.maps.AMap.onMapPrintScreenListener;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.GLMapResManager;
import com.autonavi.amap.mapcore.GLMapResManager.MapViewMode;
import com.autonavi.amap.mapcore.GLMapResManager.MapViewModeState;
import com.autonavi.amap.mapcore.GLMapResManager.MapViewTime;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.SelectedMapPoi;
import com.autonavi.amap.mapcore.VMapDataCache;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.module.setting.newhand.ae;
import com.fimi.soul.service.CameraSocketService;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.TitleBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.io.File;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public class AMapDelegateImp implements Renderer, ab {
    private static final double aH;
    private CopyOnWriteArrayList<Integer> f1497A;
    private CopyOnWriteArrayList<Integer> f1498B;
    private MapViewTime f1499C;
    private MapViewMode f1500D;
    private MapViewModeState f1501E;
    private int f1502F;
    private MapCore f1503G;
    private Context f1504H;
    private C0293a f1505I;
    private MapProjection f1506J;
    private GestureDetector f1507K;
    private ScaleGestureDetector f1508L;
    private C0375d f1509M;
    private SurfaceHolder f1510N;
    private az f1511O;
    private br f1512P;
    private as f1513Q;
    private C0328r f1514R;
    private bj f1515S;
    private C0322n f1516T;
    private ar f1517U;
    private OnMyLocationChangeListener f1518V;
    private OnMarkerClickListener f1519W;
    private OnPolylineClickListener f1520X;
    private OnMarkerDragListener f1521Y;
    private OnMapLoadedListener f1522Z;
    float f1523a;
    private onMapPrintScreenListener aA;
    private OnMapScreenShotListener aB;
    private Handler aC;
    private C0381f aD;
    private C0325p aE;
    private Timer aF;
    private TimeChangedReceiver aG;
    private boolean aI;
    private boolean aJ;
    private boolean aK;
    private boolean aL;
    private boolean aM;
    private boolean aN;
    private boolean aO;
    private boolean aP;
    private boolean aQ;
    private Boolean aR;
    private boolean aS;
    private boolean aT;
    private boolean aU;
    private Handler aV;
    private int aW;
    private C0332t aX;
    private boolean aY;
    private boolean aZ;
    private OnCameraChangeListener aa;
    private OnMapClickListener ab;
    private OnMapTouchListener ac;
    private OnPOIClickListener ad;
    private OnMapLongClickListener ae;
    private OnInfoWindowClickListener af;
    private OnIndoorBuildingActiveListener ag;
    private InfoWindowAdapter ah;
    private InfoWindowAdapter ai;
    private View aj;
    private ah ak;
    private bh al;
    private am am;
    private aq an;
    private LocationSource ao;
    private Rect ap;
    private C0320l aq;
    private C0364c ar;
    private bb as;
    private C0323o at;
    private int au;
    private int av;
    private CancelableCallback aw;
    private int ax;
    private Drawable ay;
    private Location az;
    float f1524b;
    private volatile boolean ba;
    private volatile boolean bb;
    private Handler bc;
    private Runnable bd;
    private volatile boolean be;
    private boolean bf;
    private boolean bg;
    private boolean bh;
    private Marker bi;
    private ah bj;
    private boolean bk;
    private boolean bl;
    private boolean bm;
    private int bn;
    private boolean bo;
    private Thread bp;
    private LatLngBounds bq;
    private boolean br;
    private boolean bs;
    private int bt;
    private int bu;
    private Handler bv;
    private Runnable bw;
    private Runnable bx;
    private C0278a by;
    float f1525c;
    public aw f1526d;
    av f1527e;
    bs f1528f;
    bo f1529g;
    C0410w f1530h;
    GLMapResManager f1531i;
    ae f1532j;
    Runnable f1533k;
    final Handler f1534l;
    CustomRenderer f1535m;
    private int f1536n;
    private int f1537o;
    private int f1538p;
    private Bitmap f1539q;
    private Bitmap f1540r;
    private int f1541s;
    private int f1542t;
    private boolean f1543u;
    private boolean f1544v;
    private boolean f1545w;
    private boolean f1546x;
    private MyTrafficStyle f1547y;
    private float f1548z;

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ boolean f1397a;
        final /* synthetic */ AMapDelegateImp f1398b;

        AnonymousClass10(AMapDelegateImp aMapDelegateImp, boolean z) {
            this.f1398b = aMapDelegateImp;
            this.f1397a = z;
        }

        public void run() {
            this.f1398b.f1503G.setParameter(2601, this.f1397a ? 1 : 0, 0, 0, 0);
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.19 */
    class AnonymousClass19 implements CancelableCallback {
        final /* synthetic */ LatLngBounds f1407a;
        final /* synthetic */ int f1408b;
        final /* synthetic */ int f1409c;
        final /* synthetic */ int f1410d;
        final /* synthetic */ long f1411e;
        final /* synthetic */ CancelableCallback f1412f;
        final /* synthetic */ AMapDelegateImp f1413g;

        AnonymousClass19(AMapDelegateImp aMapDelegateImp, LatLngBounds latLngBounds, int i, int i2, int i3, long j, CancelableCallback cancelableCallback) {
            this.f1413g = aMapDelegateImp;
            this.f1407a = latLngBounds;
            this.f1408b = i;
            this.f1409c = i2;
            this.f1410d = i3;
            this.f1411e = j;
            this.f1412f = cancelableCallback;
        }

        public void onCancel() {
            if (this.f1412f != null) {
                this.f1412f.onCancel();
            }
        }

        public void onFinish() {
            try {
                this.f1413g.m2464a(C0325p.m3301a(this.f1407a, this.f1408b, this.f1409c, this.f1410d), this.f1411e, this.f1412f);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.1 */
    class C02681 extends az {
        final /* synthetic */ AMapDelegateImp f1415a;

        C02681(AMapDelegateImp aMapDelegateImp, Context context, ab abVar) {
            this.f1415a = aMapDelegateImp;
            super(context, abVar);
        }

        protected void m2078a() {
            super.m2077a();
            this.f1415a.aC.removeCallbacks(this.f1415a.bx);
            this.f1415a.aC.post(this.f1415a.bw);
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.2 */
    class C02692 implements Runnable {
        final /* synthetic */ Builder f1416a;
        final /* synthetic */ AMapDelegateImp f1417b;

        C02692(AMapDelegateImp aMapDelegateImp, Builder builder) {
            this.f1417b = aMapDelegateImp;
            this.f1416a = builder;
        }

        public void run() {
            try {
                this.f1417b.m2463a(C0325p.m3300a(this.f1416a.build(), 50));
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.3 */
    class C02713 implements Runnable {
        final /* synthetic */ MapViewTime f1419a;
        final /* synthetic */ MapViewMode f1420b;
        final /* synthetic */ MapViewModeState f1421c;
        final /* synthetic */ MapViewTime f1422d;
        final /* synthetic */ MapViewMode f1423e;
        final /* synthetic */ AMapDelegateImp f1424f;

        /* renamed from: com.amap.api.mapcore.AMapDelegateImp.3.1 */
        class C02701 implements Runnable {
            final /* synthetic */ C02713 f1418a;

            C02701(C02713 c02713) {
                this.f1418a = c02713;
            }

            public void run() {
                this.f1418a.f1424f.am();
            }
        }

        C02713(AMapDelegateImp aMapDelegateImp, MapViewTime mapViewTime, MapViewMode mapViewMode, MapViewModeState mapViewModeState, MapViewTime mapViewTime2, MapViewMode mapViewMode2) {
            this.f1424f = aMapDelegateImp;
            this.f1419a = mapViewTime;
            this.f1420b = mapViewMode;
            this.f1421c = mapViewModeState;
            this.f1422d = mapViewTime2;
            this.f1423e = mapViewMode2;
        }

        public void run() {
            String styleName = this.f1424f.f1531i.getStyleName();
            String iconName = this.f1424f.f1531i.getIconName();
            this.f1424f.f1499C = this.f1419a;
            this.f1424f.f1500D = this.f1420b;
            this.f1424f.f1501E = this.f1421c;
            String styleName2 = this.f1424f.f1531i.getStyleName();
            String iconName2 = this.f1424f.f1531i.getIconName();
            if (this.f1424f.f1500D == MapViewMode.SATELLITE || this.f1424f.f1499C == MapViewTime.NIGHT || this.f1422d == MapViewTime.NIGHT || this.f1423e == MapViewMode.SATELLITE) {
                this.f1424f.f1534l.post(new C02701(this));
            }
            this.f1424f.f1503G.setParameter(2501, 0, 0, 0, 0);
            if (!styleName.equals(styleName2)) {
                this.f1424f.f1531i.setStyleData();
            }
            if (this.f1424f.f1500D == MapViewMode.SATELLITE || this.f1423e == MapViewMode.SATELLITE) {
                this.f1424f.f1503G.setParameter(2011, this.f1424f.f1500D == MapViewMode.SATELLITE ? 1 : 0, 0, 0, 0);
            }
            if (this.f1424f.f1499C == MapViewTime.NIGHT || this.f1422d == MapViewTime.NIGHT) {
                this.f1424f.f1503G.setParameter(2401, this.f1424f.f1499C == MapViewTime.NIGHT ? 1 : 0, 0, 0, 0);
                this.f1424f.f1531i.setRoadGuideTexture(true);
                this.f1424f.f1531i.setBkTexture(true);
            }
            if (!iconName.equals(iconName2)) {
                this.f1424f.f1531i.setIconsData(true);
            }
            this.f1424f.f1531i.setTrafficTexture(true);
            if (this.f1424f.f1501E != null) {
                this.f1424f.f1503G.setParameter(2013, this.f1424f.f1500D.ordinal(), this.f1424f.f1499C.ordinal(), this.f1424f.f1501E.ordinal(), 0);
            }
            this.f1424f.f1503G.setParameter(2501, 1, 1, 0, 0);
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.4 */
    class C02724 implements Runnable {
        final /* synthetic */ boolean f1425a;
        final /* synthetic */ AMapDelegateImp f1426b;

        C02724(AMapDelegateImp aMapDelegateImp, boolean z) {
            this.f1426b = aMapDelegateImp;
            this.f1425a = z;
        }

        public void run() {
            if (this.f1426b.f1503G != null) {
                this.f1426b.f1503G.setParameter(SmileConstants.MAX_SHARED_STRING_VALUES, this.f1425a ? 1 : 0, 0, 0, 0);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.5 */
    class C02735 implements Runnable {
        final /* synthetic */ boolean f1427a;
        final /* synthetic */ AMapDelegateImp f1428b;

        C02735(AMapDelegateImp aMapDelegateImp, boolean z) {
            this.f1428b = aMapDelegateImp;
            this.f1427a = z;
        }

        public void run() {
            if (this.f1427a) {
                this.f1428b.m2506b(true);
            } else {
                this.f1428b.f1517U.m2773a(false);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.6 */
    class C02746 implements Runnable {
        final /* synthetic */ boolean f1429a;
        final /* synthetic */ AMapDelegateImp f1430b;

        C02746(AMapDelegateImp aMapDelegateImp, boolean z) {
            this.f1430b = aMapDelegateImp;
            this.f1429a = z;
        }

        public void run() {
            if (this.f1430b.f1503G != null) {
                this.f1430b.f1503G.setParameter(1021, this.f1429a ? 1 : 0, 0, 0, 0);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.7 */
    class C02757 implements Runnable {
        final /* synthetic */ AMapDelegateImp f1431a;

        C02757(AMapDelegateImp aMapDelegateImp) {
            this.f1431a = aMapDelegateImp;
        }

        public void run() {
            try {
                this.f1431a.m2462a(this.f1431a.ak);
            } catch (Throwable th) {
                ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow postDelayed");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.8 */
    class C02768 extends bh {
        final /* synthetic */ AMapDelegateImp f1451a;

        C02768(AMapDelegateImp aMapDelegateImp, MarkerOptions markerOptions, ab abVar) {
            this.f1451a = aMapDelegateImp;
            super(markerOptions, abVar);
        }

        public void m2198a() {
            this.f1451a.aC.removeCallbacks(this.f1451a.bw);
            this.f1451a.aC.post(this.f1451a.bx);
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.9 */
    class C02779 implements InfoWindowAdapter {
        final /* synthetic */ AMapDelegateImp f1452a;

        C02779(AMapDelegateImp aMapDelegateImp) {
            this.f1452a = aMapDelegateImp;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }

        public View getInfoWindow(Marker marker) {
            return null;
        }
    }

    public class TimeChangedReceiver extends BroadcastReceiver {
        final /* synthetic */ AMapDelegateImp f1453a;

        public TimeChangedReceiver(AMapDelegateImp aMapDelegateImp) {
            this.f1453a = aMapDelegateImp;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.DATE_CHANGED".equals(action)) {
            }
            if ("android.intent.action.TIME_SET".equals(action)) {
                this.f1453a.f1534l.sendEmptyMessage(22);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.a */
    abstract class C0278a implements Runnable {
        boolean f1454b;
        boolean f1455c;
        MapViewMode f1456d;
        MapViewTime f1457e;
        MapViewModeState f1458f;

        private C0278a() {
            this.f1454b = false;
            this.f1455c = false;
        }

        public void run() {
            this.f1454b = false;
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.b */
    class C0280b implements C0279a {
        Float f1459a;
        Float f1460b;
        IPoint f1461c;
        float f1462d;
        C0325p f1463e;
        final /* synthetic */ AMapDelegateImp f1464f;
        private float f1465g;
        private float f1466h;
        private float f1467i;
        private float f1468j;
        private float f1469k;

        private C0280b(AMapDelegateImp aMapDelegateImp) {
            this.f1464f = aMapDelegateImp;
            this.f1459a = null;
            this.f1460b = null;
            this.f1461c = new IPoint();
            this.f1462d = 0.0f;
            this.f1463e = C0325p.m3291a();
        }

        public void m2202a() {
            if (!this.f1464f.bg) {
                try {
                    if (!this.f1464f.an.m2737g()) {
                        return;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    this.f1464f.m2504b(C0325p.m3306c());
                } catch (Throwable e2) {
                    ce.m3829a(e2, "AMapDelegateImpGLSurfaceView", "onMultiTouchSingleTap");
                    e2.printStackTrace();
                }
            }
        }

        public void m2203a(float f, float f2, float f3, float f4, float f5) {
            this.f1465g = f2;
            this.f1467i = f3;
            this.f1466h = f4;
            this.f1468j = f5;
            this.f1469k = (this.f1468j - this.f1467i) / (this.f1466h - this.f1465g);
            this.f1459a = null;
            this.f1460b = null;
            if (this.f1464f.bs) {
                this.f1463e.f2026a = C0324a.changeGeoCenterZoomTiltBearing;
                this.f1464f.m2460a(this.f1464f.bt, this.f1464f.bu, this.f1461c);
                this.f1463e.f2040o = this.f1461c;
                this.f1463e.f2039n = this.f1464f.bs;
            } else {
                this.f1463e.f2026a = C0324a.changeTilt;
            }
            this.f1463e.f2029d = this.f1464f.f1506J.getMapZoomer();
            this.f1463e.f2032g = this.f1464f.f1506J.getMapAngle();
        }

        public boolean m2204a(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            try {
                if (!this.f1464f.an.m2739h()) {
                    return true;
                }
                if (this.f1464f.bg || this.f1464f.bl) {
                    return true;
                }
                if (this.f1460b == null) {
                    this.f1460b = Float.valueOf(f4);
                }
                if (this.f1459a == null) {
                    this.f1459a = Float.valueOf(f2);
                }
                float f5 = this.f1467i - f2;
                float f6 = this.f1468j - f4;
                float f7 = this.f1465g - f;
                float f8 = this.f1466h - f3;
                if (((double) Math.abs(this.f1469k - ((f4 - f2) / (f3 - f)))) >= 0.2d || (((f5 <= 0.0f || f6 <= 0.0f) && (f5 >= 0.0f || f6 >= 0.0f)) || ((f7 < 0.0f || f8 < 0.0f) && (f7 > 0.0f || f8 > 0.0f)))) {
                    return false;
                }
                f6 = (this.f1459a.floatValue() - f2) / 4.0f;
                this.f1464f.bf = true;
                f5 = this.f1464f.f1506J.getCameraHeaderAngle();
                if (f5 > 45.0f) {
                    f5 = 45.0f;
                }
                this.f1462d = f5 - f6;
                this.f1463e.f2031f = this.f1462d;
                this.f1464f.f1527e.m2798a(this.f1463e);
                this.f1459a = Float.valueOf(f2);
                this.f1460b = Float.valueOf(f4);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return true;
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.c */
    class C0284c implements OnDoubleTapListener {
        final /* synthetic */ AMapDelegateImp f1476a;

        /* renamed from: com.amap.api.mapcore.AMapDelegateImp.c.1 */
        class C02811 implements Runnable {
            final /* synthetic */ ah f1470a;
            final /* synthetic */ C0284c f1471b;

            C02811(C0284c c0284c, ah ahVar) {
                this.f1471b = c0284c;
                this.f1470a = ahVar;
            }

            public void run() {
                try {
                    this.f1471b.f1476a.m2462a(this.f1470a);
                } catch (Throwable th) {
                    ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "onSingleTapUp showInfoWindow");
                    th.printStackTrace();
                }
            }
        }

        /* renamed from: com.amap.api.mapcore.AMapDelegateImp.c.2 */
        class C02832 implements Runnable {
            final /* synthetic */ MotionEvent f1474a;
            final /* synthetic */ C0284c f1475b;

            /* renamed from: com.amap.api.mapcore.AMapDelegateImp.c.2.1 */
            class C02821 implements Runnable {
                final /* synthetic */ Poi f1472a;
                final /* synthetic */ C02832 f1473b;

                C02821(C02832 c02832, Poi poi) {
                    this.f1473b = c02832;
                    this.f1472a = poi;
                }

                public void run() {
                    this.f1473b.f1475b.f1476a.ad.onPOIClick(this.f1472a);
                }
            }

            C02832(C0284c c0284c, MotionEvent motionEvent) {
                this.f1475b = c0284c;
                this.f1474a = motionEvent;
            }

            public void run() {
                Poi a = this.f1475b.f1476a.m2362a((int) this.f1474a.getX(), (int) this.f1474a.getY(), 25);
                if (this.f1475b.f1476a.ad != null && a != null) {
                    this.f1475b.f1476a.f1534l.post(new C02821(this, a));
                }
            }
        }

        private C0284c(AMapDelegateImp aMapDelegateImp) {
            this.f1476a = aMapDelegateImp;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onDoubleTap(android.view.MotionEvent r6) {
            /*
            r5 = this;
            r4 = 1;
            r0 = r5.f1476a;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.an;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.m2737g();	 Catch:{ RemoteException -> 0x000e }
            if (r0 != 0) goto L_0x0012;
        L_0x000d:
            return r4;
        L_0x000e:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x0012:
            r0 = r5.f1476a;
            r0 = r0.bn;
            if (r0 > r4) goto L_0x000d;
        L_0x001a:
            r0 = r5.f1476a;
            r0.bm = r4;
            r0 = r5.f1476a;
            r0 = r0.f1506J;
            r0 = r0.getMapZoomer();
            r1 = r5.f1476a;
            r1 = r1.m2544s();
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x000d;
        L_0x0033:
            r0 = r6.getX();
            r1 = r6.getY();
            r0 = (int) r0;
            r1 = (int) r1;
            r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r3 = new android.graphics.Point;
            r3.<init>(r0, r1);
            r0 = com.amap.api.mapcore.C0325p.m3294a(r2, r3);
            r1 = r5.f1476a;	 Catch:{ RemoteException -> 0x004e }
            r1.m2504b(r0);	 Catch:{ RemoteException -> 0x004e }
            goto L_0x000d;
        L_0x004e:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onDoubleTap";
            com.amap.api.mapcore.util.ce.m3829a(r0, r1, r2);
            r0.printStackTrace();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.AMapDelegateImp.c.onDoubleTap(android.view.MotionEvent):boolean");
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            this.f1476a.bk = false;
            if (this.f1476a.bo) {
                this.f1476a.bo = false;
            } else {
                if (this.f1476a.aj != null) {
                    if (this.f1476a.f1526d.m2813a(new Rect(this.f1476a.aj.getLeft(), this.f1476a.aj.getTop(), this.f1476a.aj.getRight(), this.f1476a.aj.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                        if (this.f1476a.af != null) {
                            ah d = this.f1476a.f1526d.m2819d();
                            if (d.m2121o()) {
                                this.f1476a.af.onInfoWindowClick(new Marker(d));
                            }
                        }
                    }
                }
                try {
                    if (this.f1476a.f1526d.m2815b(motionEvent)) {
                        ah d2 = this.f1476a.f1526d.m2819d();
                        if (d2 != null && d2.m2121o()) {
                            Marker marker = new Marker(d2);
                            if (this.f1476a.f1519W != null) {
                                if (this.f1476a.f1519W.onMarkerClick(marker) || this.f1476a.f1526d.m2804a() <= 0) {
                                    this.f1476a.f1526d.m2817c(d2);
                                } else {
                                    this.f1476a.aC.postDelayed(new C02811(this, d2), 20);
                                    if (!d2.m2084F()) {
                                        LatLng g = d2.m2113g();
                                        if (g != null) {
                                            IPoint iPoint = new IPoint();
                                            this.f1476a.m2453a(g.latitude, g.longitude, iPoint);
                                            this.f1476a.m2463a(C0325p.m3302a(iPoint));
                                        }
                                    }
                                }
                            }
                            this.f1476a.f1526d.m2817c(d2);
                        }
                    } else {
                        DPoint dPoint;
                        if (this.f1476a.ab != null) {
                            dPoint = new DPoint();
                            this.f1476a.m2458a((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            this.f1476a.ab.onMapClick(new LatLng(dPoint.f3692y, dPoint.f3691x));
                        }
                        if (this.f1476a.f1520X != null) {
                            dPoint = new DPoint();
                            this.f1476a.m2458a((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            LatLng latLng = new LatLng(dPoint.f3692y, dPoint.f3691x);
                            if (latLng != null) {
                                aj a = this.f1476a.f1530h.m4247a(latLng);
                                if (a != null) {
                                    this.f1476a.f1520X.onPolylineClick(new Polyline((al) a));
                                }
                            }
                        }
                        this.f1476a.m2491a(new C02832(this, motionEvent));
                    }
                } catch (Throwable e) {
                    ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "onSingleTapUp moveCamera");
                    e.printStackTrace();
                } catch (Throwable e2) {
                    ce.m3829a(e2, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
                    e2.printStackTrace();
                }
            }
            return true;
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.d */
    class C0285d implements OnGestureListener {
        FPoint f1477a;
        IPoint f1478b;
        IPoint f1479c;
        C0325p f1480d;
        final /* synthetic */ AMapDelegateImp f1481e;

        private C0285d(AMapDelegateImp aMapDelegateImp) {
            this.f1481e = aMapDelegateImp;
            this.f1477a = new FPoint();
            this.f1478b = new IPoint();
            this.f1479c = new IPoint();
            this.f1480d = C0325p.m3302a(this.f1479c);
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.f1481e.bk = false;
            if (!this.f1481e.bm) {
                try {
                    this.f1481e.m2546u();
                } catch (Throwable e) {
                    ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "onDown");
                    e.printStackTrace();
                }
            }
            this.f1481e.bm = false;
            this.f1481e.bn = 0;
            this.f1477a.f3693x = motionEvent.getX();
            this.f1477a.f3694y = motionEvent.getY();
            this.f1481e.m2460a((int) this.f1477a.f3693x, (int) this.f1477a.f3694y, this.f1478b);
            return true;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onFling(android.view.MotionEvent r11, android.view.MotionEvent r12, float r13, float r14) {
            /*
            r10 = this;
            r4 = 0;
            r9 = 1;
            r0 = r10.f1481e;
            r0.bk = r4;
            r0 = r10.f1481e;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.an;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.m2735f();	 Catch:{ RemoteException -> 0x0014 }
            if (r0 != 0) goto L_0x001f;
        L_0x0013:
            return r9;
        L_0x0014:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onFling";
            com.amap.api.mapcore.util.ce.m3829a(r0, r1, r2);
            r0.printStackTrace();
        L_0x001f:
            r0 = r10.f1481e;
            r0 = r0.ar;
            r0 = r0.m3806a();
            if (r0 != 0) goto L_0x0013;
        L_0x002b:
            r0 = r11.getEventTime();
            r2 = r10.f1481e;
            r2 = r2.ar;
            r2 = r2.m3808b();
            r0 = r0 - r2;
            r2 = 30;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0013;
        L_0x0040:
            r0 = r10.f1481e;
            r0 = r0.m2532l();
            r1 = r10.f1481e;
            r1 = r1.m2534m();
            r6 = r0 * 2;
            r8 = r1 * 2;
            r2 = r10.f1481e;
            r0 = r0 / 2;
            r2.au = r0;
            r0 = r10.f1481e;
            r1 = r1 / 2;
            r0.av = r1;
            r0 = r10.f1481e;
            r1 = 0;
            r0.aw = r1;
            r0 = r10.f1481e;
            r0 = r0.aj;
            if (r0 == 0) goto L_0x0096;
        L_0x006c:
            r0 = r10.f1481e;
            r0 = r0.ak;
            if (r0 == 0) goto L_0x0096;
        L_0x0074:
            r0 = r10.f1481e;
            r0 = r0.ak;
            r0 = r0.m2084F();
            if (r0 != 0) goto L_0x0096;
        L_0x0080:
            r0 = r10.f1481e;
            r0.aT = r4;
            r0 = r10.f1481e;
            r0 = r0.al;
            if (r0 == 0) goto L_0x0096;
        L_0x008d:
            r0 = r10.f1481e;
            r0 = r0.al;
            r0.m2171c(r9);
        L_0x0096:
            r0 = r10.f1481e;
            r0 = r0.at;
            r1 = r10.f1481e;
            r1 = r1.au;
            r2 = r10.f1481e;
            r2 = r2.av;
            r3 = -r13;
            r3 = (int) r3;
            r3 = r3 * 3;
            r3 = r3 / 5;
            r4 = -r14;
            r4 = (int) r4;
            r4 = r4 * 3;
            r4 = r4 / 5;
            r5 = -r6;
            r7 = -r8;
            r0.m3276a(r1, r2, r3, r4, r5, r6, r7, r8);
            r0 = r10.f1481e;
            r0 = r0.f1529g;
            if (r0 == 0) goto L_0x0013;
        L_0x00bf:
            r0 = r10.f1481e;
            r0 = r0.f1529g;
            r0.m3179b(r9);
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.AMapDelegateImp.d.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.f1481e.bk = false;
            this.f1481e.bj = this.f1481e.f1526d.m2806a(motionEvent);
            if (this.f1481e.f1521Y != null && this.f1481e.bj != null && this.f1481e.bj.m2117k()) {
                this.f1481e.bi = new Marker(this.f1481e.bj);
                LatLng position = this.f1481e.bi.getPosition();
                LatLng g = this.f1481e.bj.m2113g();
                IPoint iPoint = new IPoint();
                this.f1481e.m2499b(g.latitude, g.longitude, iPoint);
                iPoint.f3715y -= 60;
                DPoint dPoint = new DPoint();
                this.f1481e.m2458a(iPoint.f3714x, iPoint.f3715y, dPoint);
                this.f1481e.bi.setPosition(new LatLng((position.latitude + dPoint.f3692y) - g.latitude, (dPoint.f3691x + position.longitude) - g.longitude));
                this.f1481e.f1526d.m2817c(this.f1481e.bj);
                this.f1481e.f1521Y.onMarkerDragStart(this.f1481e.bi);
                this.f1481e.bh = true;
            } else if (this.f1481e.ae != null) {
                DPoint dPoint2 = new DPoint();
                this.f1481e.m2458a((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint2);
                this.f1481e.ae.onMapLongClick(new LatLng(dPoint2.f3692y, dPoint2.f3691x));
                this.f1481e.bo = true;
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.f1481e.bk = true;
            if ((!this.f1481e.at.m3279a() && this.f1481e.at.m3289j() == 1) || this.f1481e.ar.m3806a() || motionEvent2.getEventTime() - this.f1481e.ar.m3808b() < 30) {
                this.f1481e.bk = false;
            } else if (motionEvent2.getPointerCount() >= 2) {
                this.f1481e.bk = false;
            } else {
                try {
                    if (!this.f1481e.an.m2735f()) {
                        this.f1481e.bk = false;
                    } else if (this.f1481e.bn > 1) {
                        this.f1481e.bk = false;
                    } else {
                        if (!(this.f1481e.aj == null || this.f1481e.ak == null || this.f1481e.ak.m2084F() || this.f1481e.al == null)) {
                            this.f1481e.al.m2171c(true);
                        }
                        IPoint iPoint = new IPoint();
                        this.f1481e.m2460a((int) motionEvent2.getX(), (int) motionEvent2.getY(), iPoint);
                        int i = this.f1478b.f3714x - iPoint.f3714x;
                        int i2 = this.f1478b.f3715y - iPoint.f3715y;
                        IPoint iPoint2 = new IPoint();
                        this.f1481e.f1506J.getGeoCenter(iPoint2);
                        this.f1479c.f3714x = i + iPoint2.f3714x;
                        this.f1479c.f3715y = i2 + iPoint2.f3715y;
                        this.f1480d.f2040o = this.f1479c;
                        this.f1481e.f1527e.m2798a(this.f1480d);
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "onScroll");
                    th.printStackTrace();
                }
            }
            return true;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.e */
    class C0287e implements C0286a {
        final /* synthetic */ AMapDelegateImp f1482a;

        private C0287e(AMapDelegateImp aMapDelegateImp) {
            this.f1482a = aMapDelegateImp;
        }

        public void m2206a(int i) {
            if (this.f1482a.aD != null) {
                this.f1482a.aD.activeFloorIndex = this.f1482a.aD.floor_indexs[i];
                this.f1482a.aD.activeFloorName = this.f1482a.aD.floor_names[i];
                try {
                    this.f1482a.m2505b(this.f1482a.aD);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.f */
    class C0289f implements C0288a {
        float f1483a;
        float f1484b;
        IPoint f1485c;
        C0325p f1486d;
        final /* synthetic */ AMapDelegateImp f1487e;

        private C0289f(AMapDelegateImp aMapDelegateImp) {
            this.f1487e = aMapDelegateImp;
            this.f1483a = 0.0f;
            this.f1484b = 0.0f;
            this.f1485c = new IPoint();
            this.f1486d = C0325p.m3291a();
        }

        public boolean m2210a(C0375d c0375d) {
            if (this.f1487e.bf) {
                return false;
            }
            float b = c0375d.m3996b();
            this.f1483a += b;
            if (!this.f1487e.bl && Math.abs(this.f1483a) <= BitmapDescriptorFactory.HUE_ORANGE && Math.abs(this.f1483a) <= 350.0f) {
                return true;
            }
            this.f1487e.bl = true;
            this.f1484b = b + this.f1487e.f1506J.getMapAngle();
            this.f1486d.f2032g = this.f1484b;
            this.f1487e.f1527e.m2798a(this.f1486d);
            this.f1483a = 0.0f;
            return true;
        }

        public boolean m2211b(C0375d c0375d) {
            try {
                if (!this.f1487e.an.m2741i()) {
                    return false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (this.f1487e.bs) {
                this.f1486d.f2039n = this.f1487e.bs;
                this.f1486d.f2026a = C0324a.changeBearingGeoCenter;
                this.f1487e.m2460a(this.f1487e.bt, this.f1487e.bu, this.f1485c);
                this.f1486d.f2040o = this.f1485c;
            } else {
                this.f1486d.f2026a = C0324a.changeBearing;
            }
            this.f1487e.bl = false;
            this.f1483a = 0.0f;
            this.f1487e.bn = 2;
            return !this.f1487e.bf && ((float) this.f1487e.m2536n()) / 8.0f < c0375d.m3992c();
        }

        public void m2212c(C0375d c0375d) {
            this.f1483a = 0.0f;
            if (this.f1487e.bl) {
                this.f1487e.bl = false;
                C0325p a = C0325p.m3291a();
                a.f2041p = true;
                this.f1487e.f1527e.m2798a(a);
            }
            this.f1487e.al();
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.g */
    class C0290g implements OnScaleGestureListener {
        C0325p f1488a;
        final /* synthetic */ AMapDelegateImp f1489b;
        private float f1490c;
        private IPoint f1491d;

        private C0290g(AMapDelegateImp aMapDelegateImp) {
            this.f1489b = aMapDelegateImp;
            this.f1490c = 0.0f;
            this.f1491d = new IPoint();
            this.f1488a = C0325p.m3291a();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!this.f1489b.bf) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (this.f1489b.bg || ((double) scaleFactor) > 1.08d || ((double) scaleFactor) < 0.92d) {
                    this.f1489b.bg = true;
                    scaleFactor = (float) (Math.log((double) scaleFactor) / AMapDelegateImp.aH);
                    this.f1488a.f2029d = bj.m3602a(scaleFactor + this.f1490c);
                    this.f1489b.f1527e.m2798a(this.f1488a);
                }
            }
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            try {
                if (!this.f1489b.an.m2737g() || this.f1489b.bn < 2) {
                    return false;
                }
            } catch (Throwable e) {
                ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "onScaleBegin");
                e.printStackTrace();
            }
            this.f1489b.bn = 2;
            if (this.f1489b.bf) {
                return false;
            }
            if (this.f1489b.bs) {
                this.f1488a.f2039n = this.f1489b.bs;
                this.f1488a.f2026a = C0324a.changeGeoCenterZoom;
                this.f1489b.m2460a(this.f1489b.bt, this.f1489b.bu, this.f1491d);
                this.f1488a.f2040o = this.f1491d;
            } else {
                this.f1488a.f2026a = C0324a.zoomTo;
            }
            this.f1489b.bg = false;
            this.f1490c = this.f1489b.f1506J.getMapZoomer();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.f1490c = 0.0f;
            if (this.f1489b.bg) {
                this.f1489b.bg = false;
                C0325p a = C0325p.m3291a();
                a.f2041p = true;
                this.f1489b.f1527e.m2798a(a);
            }
            this.f1489b.al();
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.h */
    class C0291h extends TimerTask {
        AMapDelegateImp f1492a;
        final /* synthetic */ AMapDelegateImp f1493b;

        public C0291h(AMapDelegateImp aMapDelegateImp, AMapDelegateImp aMapDelegateImp2) {
            this.f1493b = aMapDelegateImp;
            this.f1492a = aMapDelegateImp2;
        }

        public void run() {
            if (!this.f1493b.ba || this.f1493b.bb || !this.f1493b.f1530h.m4256d()) {
                this.f1493b.f1532j.requestRender();
            } else if (!this.f1493b.f1526d.m2818c()) {
                this.f1493b.f1532j.requestRender();
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.AMapDelegateImp.i */
    class C0292i implements Runnable {
        final /* synthetic */ AMapDelegateImp f1494a;
        private Context f1495b;
        private OnCacheRemoveListener f1496c;

        public C0292i(AMapDelegateImp aMapDelegateImp, Context context, OnCacheRemoveListener onCacheRemoveListener) {
            this.f1494a = aMapDelegateImp;
            this.f1495b = context;
            this.f1496c = onCacheRemoveListener;
        }

        public boolean equals(Object obj) {
            return obj instanceof C0292i;
        }

        public void run() {
            Throwable th;
            boolean z;
            Throwable th2;
            boolean z2;
            try {
                boolean z3;
                Context applicationContext = this.f1495b.getApplicationContext();
                String b = bj.m3634b(applicationContext);
                String a = bj.m3615a(applicationContext);
                boolean z4 = this.f1494a.m2373a(new File(b));
                if (z4) {
                    try {
                        if (this.f1494a.m2373a(new File(a))) {
                            z3 = true;
                            this.f1494a.f1503G.setParameter(2601, 1, 0, 0, 0);
                            if (this.f1496c != null) {
                                this.f1496c.onRemoveCacheFinish(z3);
                            }
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        z2 = z4;
                        this.f1494a.f1503G.setParameter(2601, 1, 0, 0, 0);
                        if (this.f1496c != null) {
                            this.f1496c.onRemoveCacheFinish(z2);
                        }
                        throw th2;
                    }
                }
                z3 = false;
                try {
                    this.f1494a.f1503G.setParameter(2601, 1, 0, 0, 0);
                    if (this.f1496c != null) {
                        this.f1496c.onRemoveCacheFinish(z3);
                    }
                } catch (Throwable th32) {
                    th32.printStackTrace();
                }
            } catch (Throwable th4) {
                th2 = th4;
                z2 = true;
                this.f1494a.f1503G.setParameter(2601, 1, 0, 0, 0);
                if (this.f1496c != null) {
                    this.f1496c.onRemoveCacheFinish(z2);
                }
                throw th2;
            }
        }
    }

    static {
        aH = Math.log(2.0d);
    }

    public AMapDelegateImp(ae aeVar, Context context, AttributeSet attributeSet) {
        this.f1536n = -1;
        this.f1537o = -1;
        this.f1538p = 40;
        this.f1539q = null;
        this.f1540r = null;
        this.f1541s = 221010267;
        this.f1542t = 101697799;
        this.f1523a = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f1524b = 0.0f;
        this.f1525c = 0.0f;
        this.f1543u = false;
        this.f1544v = true;
        this.f1545w = true;
        this.f1546x = false;
        this.f1547y = null;
        this.f1548z = C2020f.f10933c;
        this.f1497A = new CopyOnWriteArrayList();
        this.f1498B = new CopyOnWriteArrayList();
        this.f1527e = new av(this);
        this.f1499C = MapViewTime.DAY;
        this.f1500D = MapViewMode.NORAML;
        this.f1501E = MapViewModeState.NORMAL;
        this.f1502F = 1;
        this.f1505I = null;
        this.f1510N = null;
        this.ap = new Rect();
        this.au = 0;
        this.av = 0;
        this.aw = null;
        this.ax = 0;
        this.ay = null;
        this.aA = null;
        this.aB = null;
        this.aC = new Handler();
        this.aD = null;
        this.aE = null;
        this.aG = null;
        this.aI = true;
        this.aJ = false;
        this.aK = false;
        this.aL = false;
        this.aM = false;
        this.aN = true;
        this.aO = false;
        this.aP = false;
        this.aQ = false;
        this.aR = Boolean.valueOf(false);
        this.aS = false;
        this.aT = true;
        this.aU = false;
        this.aV = new Handler();
        this.f1530h = null;
        this.f1531i = null;
        this.f1532j = null;
        this.aW = 0;
        this.aX = new C0332t();
        this.ba = false;
        this.bb = false;
        this.bc = new Handler();
        this.bd = new C0316i(this);
        this.be = false;
        this.bf = false;
        this.bg = false;
        this.bh = false;
        this.bi = null;
        this.bj = null;
        this.bk = false;
        this.bl = false;
        this.bm = false;
        this.bn = 0;
        this.bo = false;
        this.bp = new C0310c(this);
        this.bq = null;
        this.f1534l = new C0311d(this);
        this.br = false;
        this.bs = false;
        this.bv = new C0312e(this);
        this.bw = new C0313f(this);
        this.bx = new C0314g(this);
        this.by = new C0315h(this);
        C0330s.f2070c = bl.m3649c(context);
        this.f1532j = aeVar;
        this.f1504H = context;
        this.an = new bp(this);
        this.f1503G = new MapCore(this.f1504H);
        this.f1505I = new C0293a(this);
        this.f1503G.setMapCallback(this.f1505I);
        aeVar.setRenderer(this);
        ad();
        this.f1531i = new GLMapResManager(this, context);
        this.am = new bi(this);
        this.aq = new C0320l(this);
        this.f1507K = new GestureDetector(context, new C0285d());
        this.f1507K.setOnDoubleTapListener(new C0284c());
        this.f1507K.setIsLongpressEnabled(true);
        this.f1508L = new ScaleGestureDetector(context, new C0290g());
        this.f1509M = new C0375d(context, new C0289f());
        this.ar = new C0364c(context, new C0280b());
        this.f1511O = new C02681(this, context, this);
        this.f1530h = new C0410w(this);
        this.f1512P = new br(this.f1504H, this);
        this.f1515S = new bj(this.f1504H, this);
        this.f1516T = new C0322n(this.f1504H);
        this.f1517U = new ar(this.f1504H);
        this.f1529g = new bo(this.f1504H, this);
        this.f1528f = new bs(this.f1504H, this);
        this.f1513Q = new as(this.f1504H, this.f1527e, this);
        this.f1514R = new C0328r(this.f1504H, this.f1527e, this);
        this.f1526d = new aw(this.f1504H, attributeSet, this);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f1511O.addView((View) this.f1532j, 0, layoutParams);
        this.f1511O.addView(this.f1516T, 1, layoutParams);
        this.f1511O.addView(this.f1526d, new C0302a(layoutParams));
        this.f1511O.addView(this.f1512P, layoutParams);
        this.f1511O.addView(this.f1515S, layoutParams);
        this.f1511O.addView(this.f1529g, layoutParams);
        this.f1511O.addView(this.f1517U, new LayoutParams(-2, -2));
        this.f1517U.m2771a(new C0287e());
        this.f1511O.addView(this.f1528f, new C0302a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        this.f1511O.addView(this.f1513Q, new C0302a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        try {
            if (!this.an.m2733e()) {
                this.f1513Q.setVisibility(8);
            }
        } catch (Throwable e) {
            ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "locationView gone");
            e.printStackTrace();
        }
        this.f1511O.addView(this.f1514R, new C0302a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 51));
        this.f1514R.setVisibility(8);
        this.at = new C0323o(context);
        this.as = new bb(this, context);
        this.ai = new C02779(this);
        this.ah = this.ai;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        this.aG = new TimeChangedReceiver(this);
        this.f1504H.registerReceiver(this.aG, intentFilter);
        this.f1528f.setId(AutoTestConfig.ZoomControllerViewId);
        this.f1515S.setId(AutoTestConfig.ScaleControlsViewId);
        this.f1513Q.setId(AutoTestConfig.MyLocationViewId);
        this.f1514R.setId(AutoTestConfig.CompassViewId);
    }

    public static Bitmap m2355a(int i, int i2, int i3, int i4, GL10 gl10) {
        try {
            int[] iArr = new int[(i3 * i4)];
            int[] iArr2 = new int[(i3 * i4)];
            Buffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            gl10.glReadPixels(i, i2, i3, i4, 6408, 5121, wrap);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = iArr[(i5 * i3) + i6];
                    iArr2[(((i4 - i5) - 1) * i3) + i6] = ((i7 & -16711936) | ((i7 << 16) & 16711680)) | ((i7 >> 16) & Util.MASK_8BIT);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return createBitmap;
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    private Poi m2362a(int i, int i2, int i3) {
        if (!this.aK) {
            return null;
        }
        try {
            SelectedMapPoi GetSelectedMapPoi = this.f1503G.GetSelectedMapPoi(i, i2, i3);
            if (GetSelectedMapPoi == null) {
                return null;
            }
            DPoint dPoint = new DPoint();
            MapProjection.geo2LonLat(GetSelectedMapPoi.mapx, GetSelectedMapPoi.mapy, dPoint);
            return new Poi(GetSelectedMapPoi.name, new LatLng(dPoint.f3692y, dPoint.f3691x, false), GetSelectedMapPoi.poiid);
        } catch (Throwable th) {
            return null;
        }
    }

    private void m2370a(MapProjection mapProjection, int i, int i2, DPoint dPoint) {
        if (this.aJ) {
            FPoint fPoint = new FPoint();
            mapProjection.win2Map(i, i2, fPoint);
            IPoint iPoint = new IPoint();
            mapProjection.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
            MapProjection.geo2LonLat(iPoint.f3714x, iPoint.f3715y, dPoint);
        }
    }

    private boolean m2373a(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!m2373a(listFiles[i])) {
                    return false;
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return true;
    }

    private void ad() {
        if (!this.aJ) {
            this.f1503G.newMap();
            this.f1505I.onResume(this.f1503G);
            this.f1506J = this.f1503G.getMapstate();
            this.f1506J.setGeoCenter(this.f1541s, this.f1542t);
            this.f1506J.setMapAngle(this.f1525c);
            this.f1506J.setMapZoomer(this.f1523a);
            this.f1506J.setCameraHeaderAngle(this.f1524b);
            this.f1503G.setMapstate(this.f1506J);
            this.aJ = true;
            af();
            this.f1532j.setRenderMode(0);
        }
    }

    private void ae() {
        m2491a(new Runnable() {
            final /* synthetic */ AMapDelegateImp f1403a;

            {
                this.f1403a = r1;
            }

            public void run() {
                if (this.f1403a.aJ) {
                    this.f1403a.f1499C = MapViewTime.DAY;
                    this.f1403a.f1500D = MapViewMode.NORAML;
                    this.f1403a.f1501E = MapViewModeState.NORMAL;
                    try {
                        this.f1403a.f1503G.destroy();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    VMapDataCache.getInstance().reset();
                    this.f1403a.aJ = false;
                }
            }
        });
    }

    private void af() {
        try {
            m2531k(this.f1543u);
            m2533l(this.f1544v);
            m2529j(this.f1545w);
            m2527i(this.f1546x);
            m2488a(this.f1547y);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private boolean ag() {
        if (!(this.f1506J.getMapZoomer() < 17.0f || this.aD == null || this.aD.f2450g == null)) {
            IPoint iPoint = new IPoint();
            m2503b(this.aD.f2450g.f3714x, this.aD.f2450g.f3715y, iPoint);
            if (this.ap.contains(iPoint.f3714x, iPoint.f3715y)) {
                return true;
            }
        }
        return false;
    }

    private synchronized void ah() {
        if (this.aF != null) {
            ai();
        }
        if (this.aF == null) {
            this.aF = new Timer();
        }
        this.aF.schedule(new C0291h(this, this), 0, (long) (XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER / this.f1538p));
    }

    private synchronized void ai() {
        if (this.aF != null) {
            this.aF.cancel();
            this.aF = null;
        }
    }

    private synchronized void aj() {
        try {
            if (!this.be) {
                this.f1531i.setStyleData();
                this.f1531i.setIconsData(true);
                this.f1531i.setTrafficTexture(true);
                this.f1531i.setOtherMapTexture(true);
                this.f1531i.setRoadGuideTexture(true);
                this.f1531i.setBkTexture(true);
                this.be = true;
            }
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "setInternaltexture");
            th.printStackTrace();
        }
    }

    private LatLng ak() {
        if (!this.aJ) {
            return null;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        this.f1506J.getGeoCenter(iPoint);
        MapProjection.geo2LonLat(iPoint.f3714x, iPoint.f3715y, dPoint);
        return new LatLng(dPoint.f3692y, dPoint.f3691x, false);
    }

    private void al() {
        if (this.bo) {
            this.bo = false;
        }
        if (this.bk) {
            this.bk = false;
            C0325p a = C0325p.m3291a();
            a.f2041p = true;
            this.f1527e.m2798a(a);
        }
        if (this.bf) {
            this.bf = false;
            a = C0325p.m3291a();
            a.f2041p = true;
            this.f1527e.m2798a(a);
        }
        this.bg = false;
        this.bh = false;
        if (this.f1521Y != null && this.bi != null) {
            this.f1521Y.onMarkerDragEnd(this.bi);
            this.bi = null;
        }
    }

    private void am() {
        if (this.f1500D == MapViewMode.SATELLITE || this.f1499C == MapViewTime.NIGHT) {
            this.f1512P.m3216a(true);
        } else {
            this.f1512P.m3216a(false);
        }
    }

    private void m2376b(MotionEvent motionEvent) {
        if (this.bh && this.bi != null) {
            int x = (int) motionEvent.getX();
            int y = (int) (motionEvent.getY() - BitmapDescriptorFactory.HUE_YELLOW);
            LatLng g = this.bj.m2113g();
            LatLng e = this.bj.m2110e();
            DPoint dPoint = new DPoint();
            m2458a(x, y, dPoint);
            this.bi.setPosition(new LatLng((e.latitude + dPoint.f3692y) - g.latitude, (dPoint.f3691x + e.longitude) - g.longitude));
            this.f1521Y.onMarkerDrag(this.bi);
        }
    }

    public aq m2414A() {
        return this.an;
    }

    public am m2415B() {
        return this.am;
    }

    public OnCameraChangeListener m2416C() {
        return this.aa;
    }

    public View m2417D() {
        return this.f1511O;
    }

    public void m2418E() {
        if (this.aj != null) {
            this.aj.clearFocus();
            this.f1511O.removeView(this.aj);
            bj.m3623a(this.aj.getBackground());
            bj.m3623a(this.ay);
            if (this.al != null) {
                this.al.m2171c(false);
            }
            this.aj = null;
        }
        this.ak = null;
    }

    public float m2419F() {
        return this.f1506J.getMapZoomer();
    }

    void m2420G() {
        this.f1534l.obtainMessage(18).sendToTarget();
    }

    public LatLngBounds m2421H() {
        return this.bq;
    }

    public Point m2422I() {
        return this.f1512P == null ? null : this.f1512P.m3218c();
    }

    public float m2423J() {
        try {
            LatLng latLng = m2543r().target;
            float f = this.f1523a;
            if (this.aJ) {
                f = this.f1506J.getMapZoomer();
            }
            return (float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, (double) f) * 256.0d));
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "getScalePerPixel");
            th.printStackTrace();
            return 0.0f;
        }
    }

    public int m2424K() {
        Integer valueOf = Integer.valueOf(0);
        if (this.f1497A.size() > 0) {
            valueOf = (Integer) this.f1497A.get(0);
            this.f1497A.remove(0);
            this.f1498B.add(valueOf);
        }
        return valueOf.intValue();
    }

    public List<Marker> m2425L() {
        boolean z = m2536n() > 0 && m2538o() > 0;
        au.m3485a(z, (Object) "\u5730\u56fe\u672a\u521d\u59cb\u5316\u5b8c\u6210\uff01");
        return this.f1526d.m2823f();
    }

    public void m2426M() {
        this.f1530h.m4252b();
    }

    public void m2427N() {
        this.br = true;
    }

    public boolean m2428O() {
        return this.br;
    }

    public void m2429P() {
        if (this.f1526d != null) {
            this.f1526d.m2824g();
        }
        this.br = false;
    }

    public int m2430Q() {
        return this.ax;
    }

    public boolean m2431R() {
        return this.aK;
    }

    public C0323o m2432S() {
        return this.at;
    }

    public void m2433T() {
        m2469a(null);
    }

    public void m2434U() {
        if (this.f1530h != null) {
            this.f1530h.m4254c();
        }
        if (this.f1526d != null) {
            this.f1526d.m2814b();
        }
        if (this.f1535m != null) {
            this.f1535m.OnMapReferencechanged();
        }
    }

    public Context m2435V() {
        return this.f1504H;
    }

    public float m2436W() {
        return this.f1548z;
    }

    public void m2437X() {
        this.aK = false;
        m2519g();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2516f();
    }

    public MapViewTime m2438Y() {
        return this.f1499C;
    }

    public MapViewMode m2439Z() {
        return this.f1500D;
    }

    public ac m2440a(ArcOptions arcOptions) {
        if (arcOptions == null) {
            return null;
        }
        aj c0321m = new C0321m(this);
        c0321m.m3250a(arcOptions.getStrokeColor());
        c0321m.m3251a(arcOptions.getStart());
        c0321m.m3258b(arcOptions.getPassed());
        c0321m.m3260c(arcOptions.getEnd());
        c0321m.m3253a(arcOptions.isVisible());
        c0321m.m3257b(arcOptions.getStrokeWidth());
        c0321m.m3249a(arcOptions.getZIndex());
        this.f1530h.m4249a(c0321m);
        m2518f(false);
        return c0321m;
    }

    public ad m2441a(CircleOptions circleOptions) {
        if (circleOptions == null) {
            return null;
        }
        aj c0326q = new C0326q(this);
        c0326q.m3321b(circleOptions.getFillColor());
        c0326q.m3314a(circleOptions.getCenter());
        c0326q.m3316a(circleOptions.isVisible());
        c0326q.m3320b(circleOptions.getStrokeWidth());
        c0326q.m3312a(circleOptions.getZIndex());
        c0326q.m3313a(circleOptions.getStrokeColor());
        c0326q.m3311a(circleOptions.getRadius());
        this.f1530h.m4249a(c0326q);
        m2518f(false);
        return c0326q;
    }

    public af m2442a(GroundOverlayOptions groundOverlayOptions) {
        if (groundOverlayOptions == null) {
            return null;
        }
        aj aaVar = new aa(this);
        aaVar.m2612b(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        aaVar.m2602a(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        aaVar.m2603a(groundOverlayOptions.getImage());
        aaVar.m2604a(groundOverlayOptions.getLocation());
        aaVar.m2605a(groundOverlayOptions.getBounds());
        aaVar.m2614c(groundOverlayOptions.getBearing());
        aaVar.m2616d(groundOverlayOptions.getTransparency());
        aaVar.m2607a(groundOverlayOptions.isVisible());
        aaVar.m2601a(groundOverlayOptions.getZIndex());
        this.f1530h.m4249a(aaVar);
        m2518f(false);
        return aaVar;
    }

    public ai m2443a(NavigateArrowOptions navigateArrowOptions) {
        if (navigateArrowOptions == null) {
            return null;
        }
        aj bcVar = new bc(this);
        bcVar.m2914a(navigateArrowOptions.getTopColor());
        bcVar.m2915a(navigateArrowOptions.getPoints());
        bcVar.m2917a(navigateArrowOptions.isVisible());
        bcVar.m2921b(navigateArrowOptions.getWidth());
        bcVar.m2913a(navigateArrowOptions.getZIndex());
        this.f1530h.m4249a(bcVar);
        m2518f(false);
        return bcVar;
    }

    public ak m2444a(PolygonOptions polygonOptions) {
        if (polygonOptions == null) {
            return null;
        }
        aj bfVar = new bf(this);
        bfVar.m2952a(polygonOptions.getFillColor());
        bfVar.m2953a(polygonOptions.getPoints());
        bfVar.m2955a(polygonOptions.isVisible());
        bfVar.m2960b(polygonOptions.getStrokeWidth());
        bfVar.m2951a(polygonOptions.getZIndex());
        bfVar.m2961b(polygonOptions.getStrokeColor());
        this.f1530h.m4249a(bfVar);
        m2518f(false);
        return bfVar;
    }

    public al m2445a(PolylineOptions polylineOptions) {
        if (polylineOptions == null) {
            return null;
        }
        aj bgVar = new bg(this.f1530h);
        bgVar.m2993a(polylineOptions.getColor());
        bgVar.m3005b(polylineOptions.isGeodesic());
        bgVar.m3010c(polylineOptions.isDottedLine());
        bgVar.m2996a(polylineOptions.getPoints());
        bgVar.m2999a(polylineOptions.isVisible());
        bgVar.m3003b(polylineOptions.getWidth());
        bgVar.m2992a(polylineOptions.getZIndex());
        bgVar.m3013d(polylineOptions.isUseTexture());
        if (polylineOptions.getColorValues() != null) {
            bgVar.m3014e(polylineOptions.getColorValues());
            bgVar.m3015e(polylineOptions.isUseGradient());
        }
        if (polylineOptions.getCustomTexture() != null) {
            bgVar.m2994a(polylineOptions.getCustomTexture());
        }
        if (polylineOptions.getCustomTextureList() != null) {
            bgVar.m3009c(polylineOptions.getCustomTextureList());
            bgVar.m3012d(polylineOptions.getCustomTextureIndex());
        }
        this.f1530h.m4249a(bgVar);
        m2518f(false);
        return bgVar;
    }

    public LatLngBounds m2446a(LatLng latLng, float f) {
        int n = m2536n();
        int o = m2538o();
        if (n <= 0 || o <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        MapProjection mapProjection = new MapProjection(this.f1503G);
        mapProjection.setCameraHeaderAngle(0.0f);
        mapProjection.setMapAngle(0.0f);
        mapProjection.setGeoCenter(iPoint.f3714x, iPoint.f3715y);
        mapProjection.setMapZoomer(f);
        mapProjection.recalculate();
        DPoint dPoint = new DPoint();
        m2370a(mapProjection, 0, 0, dPoint);
        LatLng latLng2 = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
        m2370a(mapProjection, n, o, dPoint);
        LatLng latLng3 = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
        mapProjection.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    public Marker m2447a(MarkerOptions markerOptions) {
        if (markerOptions == null) {
            return null;
        }
        ah baVar = new ba(markerOptions, this.f1526d);
        this.f1526d.m2808a(baVar);
        m2518f(false);
        return new Marker(baVar);
    }

    public Text m2448a(TextOptions textOptions) {
        if (textOptions == null) {
            return null;
        }
        ah blVar = new bl(textOptions, this.f1526d);
        this.f1526d.m2808a(blVar);
        m2518f(false);
        return new Text(blVar);
    }

    public TileOverlay m2449a(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        ap bnVar = new bn(tileOverlayOptions, this.f1529g);
        this.f1529g.m3175a(bnVar);
        m2518f(false);
        return new TileOverlay(bnVar);
    }

    public MapCore m2450a() {
        return this.f1503G;
    }

    public ArrayList<Marker> m2451a(ArrayList<MarkerOptions> arrayList, boolean z) {
        int i = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList();
        try {
            MarkerOptions markerOptions;
            if (arrayList.size() == 1) {
                markerOptions = (MarkerOptions) arrayList.get(0);
                if (markerOptions != null) {
                    arrayList2.add(m2447a(markerOptions));
                    if (z && markerOptions.getPosition() != null) {
                        m2463a(C0325p.m3298a(markerOptions.getPosition(), 18.0f));
                    }
                    return arrayList2;
                }
            }
            Builder builder = LatLngBounds.builder();
            int i2 = 0;
            while (i2 < arrayList.size()) {
                int i3;
                markerOptions = (MarkerOptions) arrayList.get(i2);
                if (arrayList.get(i2) != null) {
                    arrayList2.add(m2447a(markerOptions));
                    if (markerOptions.getPosition() != null) {
                        builder.include(markerOptions.getPosition());
                        i3 = i + 1;
                        i2++;
                        i = i3;
                    }
                }
                i3 = i;
                i2++;
                i = i3;
            }
            if (z && i > 0) {
                if (this.aK) {
                    this.f1534l.postDelayed(new C02692(this, builder), 50);
                } else {
                    this.aE = C0325p.m3300a(builder.build(), 50);
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    public void m2452a(double d, double d2, FPoint fPoint) {
        if (this.aJ) {
            IPoint iPoint = new IPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint);
            this.f1506J.geo2Map(iPoint.f3714x, iPoint.f3715y, fPoint);
        }
    }

    public void m2453a(double d, double d2, IPoint iPoint) {
        MapProjection.lonlat2Geo(d2, d, iPoint);
    }

    public void m2454a(float f) {
        if (this.as != null) {
            this.as.m2904a(f);
        }
    }

    public void m2455a(float f, float f2, IPoint iPoint) {
        if (this.aJ) {
            this.f1506J.map2Geo(f, f2, iPoint);
        }
    }

    public void m2456a(int i) {
        if (this.as != null) {
            this.as.m2905a(i);
        }
    }

    public void m2457a(int i, int i2) {
        if (this.f1505I != null) {
            this.bs = true;
            this.f1505I.m2565a(i, i2);
            this.bt = i;
            this.bu = i2;
        }
    }

    public void m2458a(int i, int i2, DPoint dPoint) {
        m2370a(this.f1506J, i, i2, dPoint);
    }

    public void m2459a(int i, int i2, FPoint fPoint) {
        if (this.aJ) {
            this.f1506J.win2Map(i, i2, fPoint);
        }
    }

    public void m2460a(int i, int i2, IPoint iPoint) {
        if (this.aJ) {
            FPoint fPoint = new FPoint();
            this.f1506J.win2Map(i, i2, fPoint);
            this.f1506J.map2Geo(fPoint.f3693x, fPoint.f3694y, iPoint);
        }
    }

    public void m2461a(Location location) {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            try {
                if (!this.aI || this.ao == null) {
                    this.as.m2908b();
                    this.as = null;
                    return;
                }
                if (this.as == null || this.az == null) {
                    if (this.as == null) {
                        this.as = new bb(this, this.f1504H);
                    }
                    m2463a(C0325p.m3298a(latLng, this.f1506J.getMapZoomer()));
                }
                this.as.m2906a(location);
                if (!(this.f1518V == null || (this.az != null && this.az.getBearing() == location.getBearing() && this.az.getAccuracy() == location.getAccuracy() && this.az.getLatitude() == location.getLatitude() && this.az.getLongitude() == location.getLongitude()))) {
                    this.f1518V.onMyLocationChange(location);
                }
                this.az = new Location(location);
                m2518f(false);
            } catch (Throwable e) {
                ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "showMyLocationOverlay");
                e.printStackTrace();
            }
        }
    }

    public void m2462a(ah ahVar) {
        int i = -2;
        if (ahVar != null) {
            m2418E();
            if ((ahVar.m2115i() != null || ahVar.m2116j() != null) && this.ah != null) {
                this.ak = ahVar;
                if (this.aK) {
                    int i2;
                    Marker marker = new Marker(ahVar);
                    this.aj = this.ah.getInfoWindow(marker);
                    try {
                        if (this.ay == null) {
                            this.ay = bd.m2937a(this.f1504H, "infowindow_bg.9.png");
                        }
                    } catch (Throwable th) {
                        ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow decodeDrawableFromAsset");
                        th.printStackTrace();
                    }
                    if (this.aj == null) {
                        this.aj = this.ah.getInfoContents(marker);
                    }
                    View linearLayout = new LinearLayout(this.f1504H);
                    if (this.aj != null) {
                        if (this.aj.getBackground() == null) {
                            this.aj.setBackgroundDrawable(this.ay);
                        }
                        linearLayout.addView(this.aj);
                    } else {
                        linearLayout.setBackgroundDrawable(this.ay);
                        View textView = new TextView(this.f1504H);
                        textView.setText(ahVar.m2115i());
                        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        View textView2 = new TextView(this.f1504H);
                        textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView2.setText(ahVar.m2116j());
                        linearLayout.setOrientation(1);
                        linearLayout.addView(textView);
                        linearLayout.addView(textView2);
                    }
                    this.aj = linearLayout;
                    LayoutParams layoutParams = this.aj.getLayoutParams();
                    this.aj.setDrawingCacheEnabled(true);
                    this.aj.setDrawingCacheQuality(0);
                    ahVar.m2108d();
                    int D = ahVar.m2082D() + ahVar.m2080B();
                    int E = (ahVar.m2083E() + ahVar.m2081C()) + 2;
                    if (layoutParams != null) {
                        i2 = layoutParams.width;
                        i = layoutParams.height;
                    } else {
                        i2 = -2;
                    }
                    layoutParams = new C0302a(i2, i, ahVar.m2112f(), D, E, 81);
                    Bitmap a;
                    BitmapDescriptor fromBitmap;
                    if (this.al == null) {
                        a = bj.m3612a(this.aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.al = new C02768(this, new MarkerOptions().icon(fromBitmap), this);
                        this.al.m2157a(ahVar.m2112f());
                        this.al.m2167b(D, E);
                    } else {
                        this.al.m2157a(ahVar.m2112f());
                        this.al.m2167b(D, E);
                        a = bj.m3612a(this.aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.al.m2155a(fromBitmap);
                    }
                    this.f1511O.addView(this.aj, layoutParams);
                    ahVar.m2104b(true);
                    return;
                }
                this.aC.postDelayed(new C02757(this), 100);
            }
        }
    }

    public void m2463a(C0325p c0325p) {
        if (c0325p.f2026a == C0324a.newLatLngBounds) {
            boolean z = m2536n() > 0 && m2538o() > 0;
            au.m3485a(z, (Object) "the map must have a size");
        }
        if ((this.aY || this.aZ) && this.f1527e.m2801d() > 0) {
            C0325p a = C0325p.m3291a();
            a.f2026a = C0324a.changeGeoCenterZoomTiltBearing;
            a.f2040o = new IPoint(this.f1541s, this.f1542t);
            a.f2029d = this.f1523a;
            a.f2032g = this.f1525c;
            a.f2031f = this.f1524b;
            this.f1527e.m2798a(c0325p);
            while (this.f1527e.m2801d() > 0) {
                C0325p c = this.f1527e.m2800c();
                if (c != null) {
                    if (c.f2033h != null) {
                        CameraPosition cameraPosition = c.f2033h;
                        if (cameraPosition.target != null) {
                            IPoint iPoint = new IPoint();
                            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, iPoint);
                            a.f2040o = iPoint;
                        }
                        a.f2029d = cameraPosition.zoom == 0.0f ? a.f2029d : cameraPosition.zoom;
                        a.f2032g = cameraPosition.bearing == 0.0f ? a.f2032g : cameraPosition.bearing;
                        a.f2031f = cameraPosition.tilt == 0.0f ? a.f2031f : cameraPosition.tilt;
                    } else if (c.f2026a.equals(C0324a.zoomIn)) {
                        a.f2029d += C2020f.f10933c;
                    } else if (c.f2026a.equals(C0324a.zoomOut)) {
                        a.f2029d -= C2020f.f10933c;
                    } else if (c.f2026a.equals(C0324a.zoomBy)) {
                        a.f2029d += a.f2030e;
                    } else {
                        a.f2040o = c.f2040o == null ? a.f2040o : c.f2040o;
                        a.f2029d = c.f2029d == 0.0f ? a.f2029d : c.f2029d;
                        a.f2032g = c.f2032g == 0.0f ? a.f2032g : c.f2032g;
                        a.f2031f = c.f2031f == 0.0f ? a.f2031f : c.f2031f;
                        a.f2027b = c.f2027b == 0.0f ? a.f2027b : c.f2027b;
                        a.f2028c = c.f2028c == 0.0f ? a.f2028c : c.f2028c;
                        a.f2036k = c.f2036k == 0 ? a.f2036k : c.f2036k;
                        a.f2037l = c.f2037l == 0 ? a.f2037l : c.f2037l;
                    }
                    a.f2029d = bj.m3602a(a.f2029d);
                    a.f2031f = bj.m3603a(a.f2031f, a.f2029d);
                }
            }
            c0325p = a;
        }
        m2546u();
        c0325p.f2041p = true;
        c0325p.f2039n = this.bs;
        this.f1527e.m2798a(c0325p);
    }

    public void m2464a(C0325p c0325p, long j, CancelableCallback cancelableCallback) {
        if (this.aY || this.aZ) {
            m2463a(c0325p);
            return;
        }
        if (c0325p.f2026a == C0324a.newLatLngBounds) {
            boolean z = m2536n() > 0 && m2538o() > 0;
            au.m3485a(z, (Object) "the map must have a size");
        }
        if (!this.at.m3279a()) {
            this.at.m3278a(true);
            if (this.aw != null) {
                this.aw.onCancel();
            }
        }
        this.at.m3281b(this.bs);
        this.aw = cancelableCallback;
        if (this.aP) {
            this.aQ = true;
        }
        this.aO = false;
        IPoint iPoint;
        if (c0325p.f2026a == C0324a.scrollBy) {
            if (c0325p.f2027b == 0.0f && c0325p.f2028c == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            this.at.m3281b(false);
            iPoint = new IPoint();
            this.f1506J.getGeoCenter(iPoint);
            IPoint iPoint2 = new IPoint();
            m2460a((m2536n() / 2) + ((int) c0325p.f2027b), (m2538o() / 2) + ((int) c0325p.f2028c), iPoint2);
            this.at.m3277a(new AccelerateDecelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, this.f1506J.getMapZoomer(), this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), iPoint2.f3714x - iPoint.f3714x, iPoint2.f3715y - iPoint.f3715y, 0.0f, 0.0f, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.zoomIn) {
            r6 = this.f1506J.getMapZoomer();
            r11 = bj.m3602a(C2020f.f10933c + r6) - r6;
            if (r11 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, r6, this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.zoomOut) {
            r6 = this.f1506J.getMapZoomer();
            r11 = bj.m3602a(r6 - C2020f.f10933c) - r6;
            if (r11 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, r6, this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.zoomTo) {
            r6 = this.f1506J.getMapZoomer();
            r11 = bj.m3602a(c0325p.f2029d) - r6;
            if (r11 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, r6, this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.zoomBy) {
            this.at.m3281b(false);
            float f = c0325p.f2030e;
            r6 = this.f1506J.getMapZoomer();
            r11 = bj.m3602a(r6 + f) - r6;
            if (r11 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            Point point = c0325p.f2038m;
            IPoint iPoint3 = new IPoint();
            this.f1506J.getGeoCenter(iPoint3);
            r9 = 0;
            r10 = 0;
            IPoint iPoint4 = new IPoint();
            int i;
            if (point != null) {
                m2460a(point.x, point.y, iPoint4);
                r3 = iPoint3.f3714x - iPoint4.f3714x;
                i = iPoint3.f3715y - iPoint4.f3715y;
                r9 = (int) ((((double) r3) / Math.pow(2.0d, (double) f)) - ((double) r3));
                r10 = (int) ((((double) i) / Math.pow(2.0d, (double) f)) - ((double) i));
            } else if (this.bs) {
                m2460a(this.bt, this.bu, iPoint4);
                r3 = iPoint3.f3714x - iPoint4.f3714x;
                i = iPoint3.f3715y - iPoint4.f3715y;
                r9 = (int) ((((double) r3) / Math.pow(2.0d, (double) f)) - ((double) r3));
                r10 = (int) ((((double) i) / Math.pow(2.0d, (double) f)) - ((double) i));
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint3.f3714x, iPoint3.f3715y, r6, this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), r9, r10, r11, 0.0f, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.newCameraPosition) {
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            r3 = new IPoint();
            CameraPosition cameraPosition = c0325p.f2033h;
            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, r3);
            r6 = this.f1506J.getMapZoomer();
            r9 = r3.f3714x - iPoint.f3714x;
            r10 = r3.f3715y - iPoint.f3715y;
            r11 = bj.m3602a(cameraPosition.zoom) - r6;
            r7 = this.f1506J.getMapAngle();
            r12 = (cameraPosition.bearing % 360.0f) - (r7 % 360.0f);
            if (Math.abs(r12) >= BitmapDescriptorFactory.HUE_CYAN) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            r8 = this.f1506J.getCameraHeaderAngle();
            r13 = bj.m3603a(cameraPosition.tilt, cameraPosition.zoom) - r8;
            if (r9 == 0 && r10 == 0 && r11 == 0.0f && r12 == 0.0f && r13 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.at.m3277a(new AccelerateInterpolator());
                this.at.m3275a(iPoint.f3714x, iPoint.f3715y, r6, r7, r8, r9, r10, r11, r12, r13, j);
            }
        } else if (c0325p.f2026a == C0324a.changeBearing) {
            r7 = this.f1506J.getMapAngle();
            r12 = (c0325p.f2032g % 360.0f) - (r7 % 360.0f);
            if (Math.abs(r12) >= BitmapDescriptorFactory.HUE_CYAN) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            if (r12 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, this.f1506J.getMapZoomer(), r7, this.f1506J.getCameraHeaderAngle(), 0, 0, 0.0f, r12, 0.0f, j);
        } else if (c0325p.f2026a == C0324a.changeTilt) {
            r8 = this.f1506J.getCameraHeaderAngle();
            r13 = c0325p.f2031f - r8;
            if (r13 == 0.0f) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            this.at.m3277a(new AccelerateInterpolator());
            this.at.m3275a(iPoint.f3714x, iPoint.f3715y, this.f1506J.getMapZoomer(), this.f1506J.getMapAngle(), r8, 0, 0, 0.0f, 0.0f, r13, j);
        } else if (c0325p.f2026a == C0324a.changeCenter) {
            iPoint = new IPoint();
            if (this.bs) {
                m2460a(this.bt, this.bu, iPoint);
            } else {
                this.f1506J.getGeoCenter(iPoint);
            }
            r9 = c0325p.f2040o.f3714x - iPoint.f3714x;
            r10 = c0325p.f2040o.f3715y - iPoint.f3715y;
            if (r9 == 0 && r10 == 0) {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.at.m3277a(new AccelerateDecelerateInterpolator());
                this.at.m3275a(iPoint.f3714x, iPoint.f3715y, this.f1506J.getMapZoomer(), this.f1506J.getMapAngle(), this.f1506J.getCameraHeaderAngle(), r9, r10, 0.0f, 0.0f, 0.0f, j);
            }
        } else if (c0325p.f2026a == C0324a.newLatLngBounds || c0325p.f2026a == C0324a.newLatLngBoundsWithSize) {
            int i2;
            this.at.m3281b(false);
            if (c0325p.f2026a == C0324a.newLatLngBounds) {
                r3 = m2536n();
                r9 = m2538o();
                i2 = r3;
            } else {
                r3 = c0325p.f2036k;
                r9 = c0325p.f2037l;
                i2 = r3;
            }
            float mapAngle = this.f1506J.getMapAngle() % 360.0f;
            float cameraHeaderAngle = this.f1506J.getCameraHeaderAngle();
            r12 = -mapAngle;
            if (Math.abs(r12) >= BitmapDescriptorFactory.HUE_CYAN) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            r13 = -cameraHeaderAngle;
            LatLngBounds latLngBounds = c0325p.f2034i;
            int i3 = c0325p.f2035j;
            IPoint iPoint5 = new IPoint();
            this.f1506J.getGeoCenter(iPoint5);
            float mapZoomer = this.f1506J.getMapZoomer();
            this.at.m3277a(new AccelerateInterpolator());
            iPoint = new IPoint();
            r3 = new IPoint();
            MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
            MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, r3);
            r10 = iPoint.f3714x - r3.f3714x;
            int i4 = r3.f3715y - iPoint.f3715y;
            if (r10 > 0 || i4 > 0) {
                int i5 = (iPoint.f3714x + r3.f3714x) / 2;
                int i6 = (iPoint.f3715y + r3.f3715y) / 2;
                IPoint iPoint6 = new IPoint();
                m2499b((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) / 2.0d, (latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) / 2.0d, iPoint6);
                int i7;
                if ((!this.ap.contains(iPoint6.f3714x, iPoint6.f3715y) ? 1 : null) == null) {
                    r3 = i2 - (i3 * 2);
                    i7 = r9 - (i3 * 2);
                    if (r3 <= 0) {
                        r3 = 1;
                    }
                    if (i7 <= 0) {
                        i7 = 1;
                    }
                    r11 = bj.m3602a((float) ((int) (Math.min(Math.log(((double) this.f1506J.getMapLenWithWin(r3)) / ((double) this.f1506J.getMapLenWithGeo(r10))) / Math.log(2.0d), Math.log(((double) this.f1506J.getMapLenWithWin(i7)) / ((double) this.f1506J.getMapLenWithGeo(i4))) / Math.log(2.0d)) + ((double) mapZoomer)))) - mapZoomer;
                    r9 = i5 - iPoint5.f3714x;
                    r10 = i6 - iPoint5.f3715y;
                    if (r9 == 0 && r10 == 0 && r11 == 0.0f) {
                        this.f1534l.obtainMessage(17).sendToTarget();
                        return;
                    } else {
                        this.at.m3277a(new DecelerateInterpolator());
                        this.at.m3275a(iPoint5.f3714x, iPoint5.f3715y, mapZoomer, mapAngle, cameraHeaderAngle, r9, r10, r11, r12, r13, j);
                    }
                } else {
                    this.aw = new AnonymousClass19(this, latLngBounds, i2, r9, i3, j, this.aw);
                    i4 = ((iPoint5.f3714x + i5) / 2) - iPoint5.f3714x;
                    r10 = ((iPoint5.f3715y + i6) / 2) - iPoint5.f3715y;
                    i7 = (int) bj.m3601a((double) (((float) m2532l()) / 2.0f), (double) (((float) m2534m()) / 2.0f), (double) Math.abs(i5 - iPoint5.f3714x), (double) Math.abs(i6 - iPoint5.f3715y));
                    r11 = i7 == 0 ? 0.0f : ((float) i7) - mapZoomer;
                    if (r11 >= 0.0f) {
                        r11 = 0.0f;
                    }
                    this.aO = true;
                    this.at.m3275a(iPoint5.f3714x, iPoint5.f3715y, mapZoomer, mapAngle, cameraHeaderAngle, i4, r10, r11, r12 / 2.0f, r13 / 2.0f, j / 2);
                }
            } else {
                this.f1534l.obtainMessage(17).sendToTarget();
                return;
            }
        } else {
            c0325p.f2041p = true;
            this.f1527e.m2798a(c0325p);
        }
        m2518f(false);
    }

    public void m2465a(C0325p c0325p, CancelableCallback cancelableCallback) {
        m2464a(c0325p, 250, cancelableCallback);
    }

    public void m2466a(C0381f c0381f) {
        if (!this.f1543u) {
            return;
        }
        if (c0381f == null) {
            if (!ag()) {
                if (this.ag != null) {
                    this.ag.OnIndoorBuilding(c0381f);
                }
                if (this.aD != null) {
                    this.aD.f2450g = null;
                }
                if (this.f1517U.m2777d()) {
                    this.f1534l.post(new Runnable() {
                        final /* synthetic */ AMapDelegateImp f1399a;

                        {
                            this.f1399a = r1;
                        }

                        public void run() {
                            this.f1399a.f1517U.setVisibility(8);
                        }
                    });
                }
                C0330s.f2073f = 19.0f;
                if (this.an.m2729c()) {
                    this.f1534l.sendEmptyMessage(21);
                }
            }
        } else if (this.aD == null || !this.aD.poiid.equals(c0381f.poiid) || !this.f1517U.m2777d()) {
            if (this.aD == null || !this.aD.poiid.equals(c0381f.poiid) || this.aD.f2450g == null) {
                this.aD = c0381f;
                this.aD.f2450g = new IPoint();
                this.f1506J.getGeoCenter(this.aD.f2450g);
            }
            if (this.ag != null) {
                this.ag.OnIndoorBuilding(c0381f);
            }
            C0330s.f2073f = TitleBar.BACKBTN_LEFT_MARGIN;
            if (this.an.m2729c()) {
                this.f1534l.sendEmptyMessage(21);
            }
            if (this.an.m2724a() && !this.f1517U.m2777d()) {
                this.an.m2723a(true);
                this.f1534l.post(new Runnable() {
                    final /* synthetic */ AMapDelegateImp f1400a;

                    {
                        this.f1400a = r1;
                    }

                    public void run() {
                        try {
                            this.f1400a.f1517U.m2774a(this.f1400a.aD.floor_names);
                            this.f1400a.f1517U.m2772a(this.f1400a.aD.activeFloorName);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else if (!this.an.m2724a() && this.f1517U.m2777d()) {
                this.an.m2723a(false);
            }
        }
    }

    public void m2467a(C0408v c0408v) {
        this.aX.m3340a(c0408v);
    }

    public void m2468a(InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            this.ah = this.ai;
        } else {
            this.ah = infoWindowAdapter;
        }
    }

    public void m2469a(OnCacheRemoveListener onCacheRemoveListener) {
        if (this.aV != null) {
            try {
                this.f1503G.setParameter(2601, 0, 0, 0, 0);
                Runnable c0292i = new C0292i(this, this.f1504H, onCacheRemoveListener);
                this.aV.removeCallbacks(c0292i);
                this.aV.post(c0292i);
            } catch (Throwable th) {
                ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "removecache");
                th.printStackTrace();
            }
        }
    }

    public void m2470a(OnCameraChangeListener onCameraChangeListener) {
        this.aa = onCameraChangeListener;
    }

    public void m2471a(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) {
        this.ag = onIndoorBuildingActiveListener;
    }

    public void m2472a(OnInfoWindowClickListener onInfoWindowClickListener) {
        this.af = onInfoWindowClickListener;
    }

    public void m2473a(OnMapClickListener onMapClickListener) {
        this.ab = onMapClickListener;
    }

    public void m2474a(OnMapLoadedListener onMapLoadedListener) {
        this.f1522Z = onMapLoadedListener;
    }

    public void m2475a(OnMapLongClickListener onMapLongClickListener) {
        this.ae = onMapLongClickListener;
    }

    public void m2476a(OnMapScreenShotListener onMapScreenShotListener) {
        this.aB = onMapScreenShotListener;
        this.aS = true;
        m2518f(false);
    }

    public void m2477a(OnMapTouchListener onMapTouchListener) {
        this.ac = onMapTouchListener;
    }

    public void m2478a(OnMarkerClickListener onMarkerClickListener) {
        this.f1519W = onMarkerClickListener;
    }

    public void m2479a(OnMarkerDragListener onMarkerDragListener) {
        this.f1521Y = onMarkerDragListener;
    }

    public void m2480a(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.f1518V = onMyLocationChangeListener;
    }

    public void m2481a(OnPOIClickListener onPOIClickListener) {
        this.ad = onPOIClickListener;
    }

    public void m2482a(OnPolylineClickListener onPolylineClickListener) {
        this.f1520X = onPolylineClickListener;
    }

    public void m2483a(onMapPrintScreenListener com_amap_api_maps_AMap_onMapPrintScreenListener) {
        this.aA = com_amap_api_maps_AMap_onMapPrintScreenListener;
        this.aS = true;
        m2518f(false);
    }

    public void m2484a(CustomRenderer customRenderer) {
        this.f1535m = customRenderer;
    }

    public void m2485a(LocationSource locationSource) {
        try {
            this.ao = locationSource;
            if (locationSource != null) {
                this.f1513Q.m2779a(true);
            } else {
                this.f1513Q.m2779a(false);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "setLocationSource");
            th.printStackTrace();
        }
    }

    void m2486a(CameraPosition cameraPosition) {
        Message message = new Message();
        message.what = 10;
        message.obj = cameraPosition;
        this.f1534l.sendMessage(message);
    }

    public void m2487a(MyLocationStyle myLocationStyle) {
        if (this.as != null) {
            this.as.m2907a(myLocationStyle);
        }
    }

    public void m2488a(MyTrafficStyle myTrafficStyle) {
        if (this.aJ && myTrafficStyle != null) {
            this.f1547y = myTrafficStyle;
            this.f1503G.setParameter(2201, 1, 1, 1, 1);
            this.f1503G.setParameter(2202, myTrafficStyle.getSmoothColor(), myTrafficStyle.getSlowColor(), myTrafficStyle.getCongestedColor(), myTrafficStyle.getSeriousCongestedColor());
        }
    }

    public void m2489a(MapViewMode mapViewMode, MapViewTime mapViewTime) {
        m2490a(mapViewMode, mapViewTime, MapViewModeState.NORMAL);
    }

    public void m2490a(MapViewMode mapViewMode, MapViewTime mapViewTime, MapViewModeState mapViewModeState) {
        if (this.f1499C != mapViewTime || this.f1500D != mapViewMode || this.f1501E != mapViewModeState) {
            if (this.aL) {
                MapViewTime mapViewTime2 = this.f1499C;
                MapViewMode mapViewMode2 = this.f1500D;
                MapViewModeState mapViewModeState2 = this.f1501E;
                if (this.be && this.aJ) {
                    m2491a(new C02713(this, mapViewTime, mapViewMode, mapViewModeState, mapViewTime2, mapViewMode2));
                    return;
                }
                this.by.f1456d = mapViewMode;
                this.by.f1457e = mapViewTime;
                this.by.f1454b = true;
                return;
            }
            this.f1499C = mapViewTime;
            this.f1500D = mapViewMode;
            this.f1501E = mapViewModeState;
        }
    }

    public void m2491a(Runnable runnable) {
        if (this.f1532j != null) {
            this.f1532j.queueEvent(runnable);
        }
    }

    public void m2492a(GL10 gl10) {
        int i = 0;
        if (!this.aU) {
            int[] iArr = new int[C2799f.f14263a];
            this.f1497A.clear();
            gl10.glGenTextures(C2799f.f14263a, iArr, 0);
            while (i < iArr.length) {
                this.f1497A.add(Integer.valueOf(iArr[i]));
                i++;
            }
            this.aU = true;
        }
    }

    public void m2493a(boolean z) {
        if (this.f1528f != null) {
            this.f1528f.m3229a(z);
        }
    }

    protected void m2494a(boolean z, CameraPosition cameraPosition) {
        if (this.aa != null && this.at.m3279a() && this.f1532j.isEnabled()) {
            if (cameraPosition == null) {
                try {
                    cameraPosition = m2543r();
                } catch (Throwable e) {
                    ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "cameraChangeFinish");
                    e.printStackTrace();
                }
            }
            this.aa.onCameraChangeFinish(cameraPosition);
        }
    }

    public boolean m2495a(MotionEvent motionEvent) {
        if (!this.aK) {
            return false;
        }
        m2518f(false);
        if (motionEvent.getAction() == C1314u.f5853F) {
            this.bn = motionEvent.getPointerCount();
        }
        this.f1507K.onTouchEvent(motionEvent);
        this.ar.m3807a(motionEvent);
        this.f1508L.onTouchEvent(motionEvent);
        this.f1509M.m3507a(motionEvent);
        if (motionEvent.getAction() == 2) {
            try {
                m2376b(motionEvent);
            } catch (Throwable e) {
                ce.m3829a(e, "AMapDelegateImpGLSurfaceView", "onDragMarker");
                e.printStackTrace();
            }
        }
        if (motionEvent.getAction() == 1) {
            al();
        }
        m2518f(false);
        if (this.ac != null) {
            this.bv.removeMessages(1);
            Message obtainMessage = this.bv.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = MotionEvent.obtain(motionEvent);
            obtainMessage.sendToTarget();
        }
        return true;
    }

    public boolean m2496a(String str) {
        m2518f(false);
        return this.f1530h.m4255c(str);
    }

    public MapViewModeState aa() {
        return this.f1501E;
    }

    public boolean ab() {
        return this.aM;
    }

    public float m2497b(float f) {
        return bj.m3602a(f);
    }

    public int m2498b() {
        return this.f1536n;
    }

    public void m2499b(double d, double d2, IPoint iPoint) {
        if (this.aJ) {
            MapProjection mapProjection = new MapProjection(this.f1503G);
            mapProjection.recalculate();
            IPoint iPoint2 = new IPoint();
            FPoint fPoint = new FPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint2);
            mapProjection.geo2Map(iPoint2.f3714x, iPoint2.f3715y, fPoint);
            mapProjection.map2Win(fPoint.f3693x, fPoint.f3694y, iPoint);
            mapProjection.recycle();
        }
    }

    public void m2500b(int i) {
        this.f1502F = i;
        if (this.aK) {
            if (i == 1) {
                try {
                    m2489a(MapViewMode.NORAML, MapViewTime.DAY);
                } catch (Throwable th) {
                    ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "setMaptype");
                    th.printStackTrace();
                    return;
                }
            } else if (i == 2) {
                m2489a(MapViewMode.SATELLITE, MapViewTime.DAY);
            } else if (i == 3) {
                m2490a(MapViewMode.NORAML, MapViewTime.NIGHT, MapViewModeState.NAVI_CAR);
            } else if (i == 4) {
                m2490a(MapViewMode.NORAML, MapViewTime.DAY, MapViewModeState.NAVI_CAR);
            } else {
                this.f1502F = 1;
            }
            m2518f(false);
        }
    }

    public void m2501b(int i, int i2, DPoint dPoint) {
        MapProjection.geo2LonLat(i, i2, dPoint);
    }

    public void m2502b(int i, int i2, FPoint fPoint) {
        if (this.aJ) {
            this.f1506J.geo2Map(i2, i, fPoint);
        }
    }

    public void m2503b(int i, int i2, IPoint iPoint) {
        if (this.aJ) {
            FPoint fPoint = new FPoint();
            this.f1506J.geo2Map(i, i2, fPoint);
            this.f1506J.map2Win(fPoint.f3693x, fPoint.f3694y, iPoint);
        }
    }

    public void m2504b(C0325p c0325p) {
        m2465a(c0325p, null);
    }

    public void m2505b(C0381f c0381f) {
        if (c0381f != null && c0381f.activeFloorName != null && c0381f.poiid != null) {
            this.aD = c0381f;
            m2518f(false);
            m2491a(new Runnable() {
                final /* synthetic */ AMapDelegateImp f1401a;

                {
                    this.f1401a = r1;
                }

                public void run() {
                    this.f1401a.f1503G.setIndoorBuildingToBeActive(this.f1401a.aD.activeFloorName, this.f1401a.aD.activeFloorIndex, this.f1401a.aD.poiid);
                }
            });
        }
    }

    public void m2506b(boolean z) {
        if (this.f1517U != null && z && ag()) {
            this.f1517U.m2773a(true);
        }
    }

    public float m2507c(int i) {
        return this.aJ ? this.f1506J.getMapLenWithWin(i) : 0.0f;
    }

    public MapProjection m2508c() {
        if (this.f1506J == null) {
            this.f1506J = this.f1503G.getMapstate();
        }
        return this.f1506J;
    }

    public void m2509c(boolean z) {
        if (this.f1513Q != null) {
            if (z) {
                this.f1513Q.setVisibility(0);
            } else {
                this.f1513Q.setVisibility(8);
            }
        }
    }

    public void m2510d() {
        this.aZ = false;
    }

    public void m2511d(int i) {
        if (this.f1512P != null) {
            this.f1512P.m3215a(i);
            this.f1512P.invalidate();
            if (this.f1515S.getVisibility() == 0) {
                this.f1515S.invalidate();
            }
        }
    }

    public void m2512d(boolean z) {
        if (this.f1514R != null) {
            this.f1514R.m3337a(z);
        }
    }

    public void m2513e() {
        this.aZ = true;
    }

    public void m2514e(int i) {
        if (this.f1528f != null) {
            this.f1528f.m3228a(i);
        }
    }

    public void m2515e(boolean z) {
        if (this.f1515S != null) {
            this.f1515S.m3037a(z);
        }
    }

    public void m2516f() {
        if (this.aW != 1) {
            this.aW = 1;
            this.aY = false;
            if (!this.aJ) {
                m2491a(new Runnable() {
                    final /* synthetic */ AMapDelegateImp f1402a;

                    {
                        this.f1402a = r1;
                    }

                    public void run() {
                        this.f1402a.ad();
                        this.f1402a.ah();
                        if (this.f1402a.f1505I != null) {
                            this.f1402a.f1505I.onResume(this.f1402a.f1503G);
                            this.f1402a.m2518f(false);
                        }
                        if (this.f1402a.f1529g != null) {
                            this.f1402a.f1529g.m3182d();
                        }
                        if (this.f1402a.as != null) {
                            this.f1402a.as.m2903a();
                        }
                    }
                });
            }
            if (this.f1532j instanceof C0317j) {
                ((C0317j) this.f1532j).onResume();
            } else {
                ((C0319k) this.f1532j).m3243c();
            }
        }
    }

    public void m2517f(int i) {
        if (this.f1498B.contains(Integer.valueOf(i))) {
            this.f1497A.add(Integer.valueOf(i));
            this.f1498B.remove(this.f1498B.indexOf(Integer.valueOf(i)));
        }
    }

    public synchronized void m2518f(boolean z) {
        if (!z) {
            this.bb = false;
            this.bc.removeCallbacks(this.bd);
            this.ba = false;
        } else if (!(this.ba || this.bb)) {
            this.bb = true;
            this.bc.postDelayed(this.bd, 6000);
        }
    }

    public void m2519g() {
        if (this.aW == 1) {
            this.aW = -1;
            this.aY = true;
            this.aM = false;
            if (this.f1516T != null) {
                this.f1516T.m3269a(true);
            }
            if (this.f1505I != null) {
                this.f1505I.destoryMap(this.f1503G);
            }
            ai();
            IPoint iPoint = new IPoint();
            this.f1506J.recalculate();
            this.f1506J.getGeoCenter(iPoint);
            this.f1541s = iPoint.f3714x;
            this.f1542t = iPoint.f3715y;
            this.f1523a = this.f1506J.getMapZoomer();
            this.f1525c = this.f1506J.getMapAngle();
            this.f1524b = this.f1506J.getCameraHeaderAngle();
            if (this.f1532j instanceof C0317j) {
                ((C0317j) this.f1532j).onPause();
            } else {
                ((C0319k) this.f1532j).m3242b();
            }
            ae();
        }
    }

    public void m2520g(int i) {
        this.ax = i;
    }

    public void m2521g(boolean z) {
        this.f1532j.setZOrderOnTop(z);
    }

    public void m2522h() {
        this.aR = Boolean.valueOf(true);
        try {
            ai();
            if (this.f1540r != null) {
                this.f1540r.recycle();
                this.f1540r = null;
            }
            if (this.f1539q != null) {
                this.f1539q.recycle();
                this.f1539q = null;
            }
            if (!(this.f1534l == null || this.f1533k == null)) {
                this.f1534l.removeCallbacks(this.f1533k);
                this.f1533k = null;
            }
            if (this.bc != null) {
                this.bc.removeCallbacks(this.bd);
            }
            if (this.aG != null) {
                this.f1504H.unregisterReceiver(this.aG);
            }
            if (this.f1528f != null) {
                this.f1528f.m3226a();
            }
            if (this.f1515S != null) {
                this.f1515S.m3034a();
            }
            if (this.f1512P != null) {
                this.f1512P.m3214a();
            }
            if (this.f1513Q != null) {
                this.f1513Q.m2778a();
            }
            if (this.f1514R != null) {
                this.f1514R.m3336a();
            }
            if (this.f1529g != null) {
                this.f1529g.m3178b();
                this.f1529g.m3183e();
            }
            if (this.f1530h != null) {
                this.f1530h.m4248a();
            }
            if (this.f1526d != null) {
                this.f1526d.m2821e();
            }
            if (this.f1517U != null) {
                this.f1517U.m2775b();
            }
            if (this.bp != null) {
                this.bp.interrupt();
                this.bp = null;
            }
            if (this.f1505I != null) {
                this.f1505I.OnMapDestory(this.f1503G);
                this.f1503G.setMapCallback(null);
                this.f1505I = null;
            }
            m2418E();
            bj.m3623a(this.ay);
            if (this.f1497A != null) {
                this.f1497A.clear();
            }
            if (this.f1498B != null) {
                this.f1498B.clear();
            }
            if (this.f1503G != null) {
                m2491a(new Runnable() {
                    final /* synthetic */ AMapDelegateImp f1404a;

                    {
                        this.f1404a = r1;
                    }

                    public void run() {
                        try {
                            this.f1404a.f1503G.destroy();
                            this.f1404a.f1503G = null;
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                Thread.sleep(200);
            }
            if (this.f1511O != null) {
                this.f1511O.removeAllViews();
                this.f1511O = null;
            }
            this.ao = null;
            this.ab = null;
            this.f1547y = null;
            ce.m3830b();
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "destroy");
            th.printStackTrace();
        }
    }

    public void m2523h(int i) {
        this.f1532j.setVisibility(i);
    }

    public void m2524h(boolean z) {
        String str = null;
        try {
            String c;
            m2418E();
            if (this.as != null) {
                if (z) {
                    c = this.as.m2909c();
                    str = this.as.m2910d();
                    this.f1530h.m4253b(str);
                    this.f1529g.m3178b();
                    this.f1526d.m2811a(c);
                    m2518f(false);
                }
                this.as.m2911e();
            }
            c = null;
            this.f1530h.m4253b(str);
            this.f1529g.m3178b();
            this.f1526d.m2811a(c);
            m2518f(false);
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    void m2525i() {
        this.f1534l.obtainMessage(14).sendToTarget();
    }

    public void m2526i(int i) {
        try {
            this.f1538p = Math.max(10, Math.min(i, 40));
            this.f1534l.sendEmptyMessage(22);
        } catch (Throwable th) {
        }
    }

    public void m2527i(boolean z) {
        this.f1546x = z;
        m2518f(false);
        this.f1527e.m2797a(new au(2).m2794a(z));
    }

    void m2528j() {
        this.f1534l.post(new Runnable() {
            final /* synthetic */ AMapDelegateImp f1405a;

            {
                this.f1405a = r1;
            }

            public void run() {
                this.f1405a.f1515S.m3038b();
            }
        });
    }

    public void m2529j(boolean z) {
        this.f1545w = z;
        m2518f(false);
        m2491a(new C02724(this, z));
    }

    public Rect m2530k() {
        return this.ap;
    }

    public void m2531k(boolean z) {
        this.f1543u = z;
        m2518f(false);
        if (z) {
            this.f1503G.setParameter(C1314u.f5861N, 1, 0, 0, 0);
        } else {
            this.f1503G.setParameter(C1314u.f5861N, 0, 0, 0, 0);
            C0330s.f2073f = 19.0f;
            if (this.an.m2729c()) {
                this.f1534l.sendEmptyMessage(21);
            }
        }
        if (this.an.m2724a()) {
            this.f1534l.post(new C02735(this, z));
        }
    }

    public int m2532l() {
        return this.ap.width();
    }

    public void m2533l(boolean z) {
        this.f1544v = z;
        m2518f(false);
        m2491a(new C02746(this, z));
    }

    public int m2534m() {
        return this.ap.height();
    }

    public void m2535m(boolean z) {
        try {
            if (this.ao == null) {
                this.f1513Q.m2779a(false);
            } else if (z) {
                this.ao.activate(this.aq);
                this.f1513Q.m2779a(true);
                if (this.as == null) {
                    this.as = new bb(this, this.f1504H);
                }
            } else {
                if (this.as != null) {
                    this.as.m2908b();
                    this.as = null;
                }
                this.az = null;
                this.ao.deactivate();
            }
            if (!z) {
                this.an.m2732e(z);
            }
            this.aI = z;
            m2518f(false);
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "setMyLocationEnabled");
            th.printStackTrace();
        }
    }

    public int m2536n() {
        return this.f1532j.getWidth();
    }

    public CameraPosition m2537n(boolean z) {
        if (!this.aJ) {
            return null;
        }
        LatLng latLng;
        if (z) {
            DPoint dPoint = new DPoint();
            m2458a(this.bt, this.bu, dPoint);
            latLng = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
        } else {
            latLng = ak();
        }
        return CameraPosition.builder().target(latLng).bearing(this.f1506J.getMapAngle()).tilt(this.f1506J.getCameraHeaderAngle()).zoom(this.f1506J.getMapZoomer()).build();
    }

    public int m2538o() {
        return this.f1532j.getHeight();
    }

    void m2539o(boolean z) {
        this.f1534l.obtainMessage(20, z ? 1 : 0, 0).sendToTarget();
    }

    public void onDrawFrame(GL10 gl10) {
        int i = 1;
        try {
            if (this.aJ) {
                gl10.glColor4f(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, 0.5f);
                gl10.glClear(16640);
                this.f1503G.setGL(gl10);
                this.f1503G.drawFrame(gl10);
                m2492a(gl10);
                this.f1529g.m3176a(gl10);
                this.f1530h.m4251a(gl10, false, this.ax);
                this.f1526d.m2812a(gl10);
                this.aX.m3339a(gl10);
                if (this.al != null) {
                    this.al.m2162a(gl10);
                }
                if (this.aS) {
                    if (!this.f1503G.canStopRenderMap()) {
                        i = 0;
                    }
                    Message obtainMessage = this.f1534l.obtainMessage(16, m2355a(0, 0, m2536n(), m2538o(), gl10));
                    obtainMessage.arg1 = i;
                    obtainMessage.sendToTarget();
                    this.aS = false;
                }
                if (!this.at.m3279a()) {
                    this.f1534l.sendEmptyMessage(13);
                }
                if (this.f1516T != null) {
                    i = this.f1516T.getVisibility();
                    C0322n c0322n = this.f1516T;
                    if (i != 8) {
                        if (!this.aK) {
                            this.f1534l.sendEmptyMessage(11);
                            this.aK = true;
                        }
                        this.aM = true;
                        this.f1534l.post(new Runnable() {
                            final /* synthetic */ AMapDelegateImp f1406a;

                            {
                                this.f1406a = r1;
                            }

                            public void run() {
                                if (!this.f1406a.aY) {
                                    try {
                                        this.f1406a.m2500b(this.f1406a.f1502F);
                                        if (this.f1406a.aD != null) {
                                            this.f1406a.m2505b(this.f1406a.aD);
                                        }
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    this.f1406a.f1516T.m3269a(false);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            gl10.glClearColor(0.9453125f, 0.93359f, 0.9101f, C2020f.f10933c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        int i3 = Opcodes.ISHL;
        int i4 = 50;
        int i5 = 1;
        this.ap = new Rect(0, 0, i, i2);
        try {
            this.f1503G.setGL(gl10);
            this.f1503G.surfaceChange(gl10, i, i2);
            int i6 = this.f1504H.getResources().getDisplayMetrics().densityDpi;
            float f = this.f1504H.getResources().getDisplayMetrics().density;
            int i7 = 100;
            if (i6 > Opcodes.ISHL) {
                if (i6 <= SmileConstants.TOKEN_PREFIX_SHORT_UNICODE) {
                    if (Math.max(i, i2) <= 480) {
                        i6 = 120;
                    } else {
                        i6 = 100;
                        i3 = SmileConstants.TOKEN_PREFIX_SHORT_UNICODE;
                    }
                    i4 = i3;
                    i7 = i6;
                } else if (i6 <= 240) {
                    if (Math.min(i, i2) >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                        i7 = 60;
                        i4 = C2799f.f14282t;
                        i5 = 2;
                    } else {
                        i7 = 70;
                        i4 = Opcodes.FCMPG;
                        i5 = 2;
                    }
                } else if (i6 <= 320) {
                    i5 = 3;
                    i7 = 50;
                    i4 = Opcodes.GETFIELD;
                } else if (i6 <= 480) {
                    i5 = 3;
                    i7 = 50;
                    i4 = ae.f9482j;
                } else {
                    i7 = 40;
                    i4 = 360;
                    i5 = 4;
                }
            }
            this.f1503G.setParameter(2051, i7, i4, (int) (f * 100.0f), i5);
            this.f1548z = ((float) i7) / 100.0f;
            this.f1503G.setParameter(CameraSocketService.f9884d, 0, 0, 0, 0);
            this.f1503G.setParameter(1023, 1, 0, 0, 0);
            m2518f(false);
            if (this.f1535m != null) {
                this.f1535m.onSurfaceChanged(gl10, i, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        try {
            if (!this.aJ) {
                ad();
            }
            this.be = false;
            this.f1503G.setGL(gl10);
            aj();
            this.f1503G.surfaceCreate(gl10);
            if (this.f1539q == null || this.f1539q.isRecycled()) {
                this.f1539q = bj.m3609a(this.f1504H, "lineTexture.png");
            }
            if (this.f1540r == null || this.f1540r.isRecycled()) {
                this.f1540r = bj.m3609a(this.f1504H, "lineDashTexture.png");
            }
            this.aU = false;
            this.f1536n = bj.m3606a(gl10, this.f1539q);
            this.f1537o = bj.m3607a(gl10, this.f1540r, true);
            this.f1539q = null;
            this.f1526d.m2826i();
            this.f1530h.m4257e();
            this.f1529g.m3184f();
            if (this.al != null) {
                this.al.m2146J();
            }
            ah();
            m2518f(false);
            if (!this.aL) {
                this.bp.setName("AuthThread");
                this.bp.start();
                this.aL = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.f1535m != null) {
            this.f1535m.onSurfaceCreated(gl10, eGLConfig);
        }
    }

    public int m2540p() {
        return this.f1537o;
    }

    public void m2541p(boolean z) {
        m2491a(new AnonymousClass10(this, z));
    }

    public void m2542q() {
        try {
            if (this.aT && this.aj != null && this.ak != null) {
                C0302a c0302a = (C0302a) this.aj.getLayoutParams();
                if (c0302a != null) {
                    this.ak.m2108d();
                    int D = this.ak.m2082D() + this.ak.m2080B();
                    int E = (this.ak.m2083E() + this.ak.m2081C()) + 2;
                    c0302a.f1644a = this.ak.m2112f();
                    c0302a.f1645b = D;
                    c0302a.f1646c = E;
                    if (this.al != null) {
                        this.al.m2157a(this.ak.m2112f());
                        this.al.m2167b(D, E);
                    }
                }
                this.f1511O.onLayout(false, 0, 0, 0, 0);
                m2518f(false);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    public CameraPosition m2543r() {
        return m2537n(this.bs);
    }

    public float m2544s() {
        return C0330s.f2073f;
    }

    public float m2545t() {
        return C2020f.f10931a;
    }

    public void m2546u() {
        if (!this.at.m3279a()) {
            this.at.m3278a(true);
            m2494a(true, null);
            if (this.aw != null) {
                this.aw.onCancel();
            }
            if (!(this.aj == null || this.al == null)) {
                this.aj.setVisibility(0);
            }
            this.aw = null;
        }
        m2518f(false);
    }

    public void m2547v() {
        try {
            m2524h(false);
        } catch (Throwable th) {
            ce.m3829a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    public int m2548w() {
        return this.f1502F;
    }

    public boolean m2549x() {
        return this.f1546x;
    }

    public boolean m2550y() {
        return this.aI;
    }

    public Location m2551z() {
        return this.ao != null ? this.aq.f1944a : null;
    }
}
