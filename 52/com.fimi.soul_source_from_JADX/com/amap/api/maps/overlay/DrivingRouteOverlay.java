package com.amap.api.maps.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.amap.api.mapcore.C0330s;
import com.amap.api.mapcore.util.bh;
import com.amap.api.mapcore.util.bj;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DrivingRouteOverlay extends C0443b {
    private DrivePath f2857a;
    private List<LatLonPoint> f2858b;
    private List<Marker> f2859c;
    private boolean f2860d;
    private Context f2861e;
    private PolylineOptions f2862f;

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        this(context, aMap, drivePath, latLonPoint, latLonPoint2, null);
        this.f2861e = context;
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.f2859c = new ArrayList();
        this.f2860d = true;
        this.mAMap = aMap;
        this.f2857a = drivePath;
        this.startPoint = C0444a.m4420a(latLonPoint);
        this.endPoint = C0444a.m4420a(latLonPoint2);
        this.f2858b = list;
        this.f2861e = context;
    }

    private LatLonPoint m4401a(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(0);
    }

    private void m4402a() {
        this.f2862f = null;
        this.f2862f = new PolylineOptions();
        this.f2862f.color(getDriveColor()).width(getRouteWidth());
    }

    private void m4403a(LatLng latLng, LatLng latLng2) {
        this.f2862f.add(latLng, latLng2);
    }

    private void m4404a(LatLonPoint latLonPoint, LatLng latLng) {
        m4403a(C0444a.m4420a(latLonPoint), latLng);
    }

    private void m4405a(DriveStep driveStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("\u65b9\u5411:" + driveStep.getAction() + "\n\u9053\u8def:" + driveStep.getRoad()).snippet(driveStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(getDriveBitmapDescriptor()));
    }

    private LatLonPoint m4406b(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(driveStep.getPolyline().size() - 1);
    }

    private void m4407b() {
        addPolyLine(this.f2862f);
    }

    private void m4408c() {
        if (this.f2858b != null && this.f2858b.size() > 0) {
            for (int i = 0; i < this.f2858b.size(); i++) {
                LatLonPoint latLonPoint = (LatLonPoint) this.f2858b.get(i);
                if (latLonPoint != null) {
                    this.f2859c.add(this.mAMap.addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude())).visible(this.f2860d).icon(m4410d()).title("\u9014\u7ecf\u70b9")));
                }
            }
        }
    }

    private void m4409c(DriveStep driveStep) {
        this.f2862f.addAll(C0444a.m4421a(driveStep.getPolyline()));
    }

    private BitmapDescriptor m4410d() {
        Bitmap decodeStream;
        Throwable th;
        Throwable th2;
        Throwable th3;
        BitmapDescriptor fromBitmap;
        Bitmap a;
        InputStream inputStream = null;
        InputStream open;
        try {
            open = bh.m3592a(this.f2861e).open("amap_throughpoint.png");
            try {
                decodeStream = BitmapFactory.decodeStream(open);
            } catch (Throwable th22) {
                th = th22;
                Object obj = inputStream;
                th3 = th;
                try {
                    th3.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                    a.recycle();
                    return fromBitmap;
                } catch (Throwable th4) {
                    th22 = th4;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th322) {
                            th322.printStackTrace();
                        }
                    }
                    throw th22;
                }
            }
            try {
                a = bj.m3610a(decodeStream, C0330s.f2068a);
                if (open != null) {
                    try {
                        open.close();
                    } catch (Throwable th3222) {
                        th3222.printStackTrace();
                    }
                }
            } catch (Throwable th222) {
                th = th222;
                a = decodeStream;
                th3222 = th;
                th3222.printStackTrace();
                if (open != null) {
                    open.close();
                }
                fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                a.recycle();
                return fromBitmap;
            }
        } catch (Throwable th5) {
            th222 = th5;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            throw th222;
        }
        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
        a.recycle();
        return fromBitmap;
    }

    public void addToMap() {
        m4402a();
        try {
            List steps = this.f2857a.getSteps();
            int i = 0;
            while (i < steps.size()) {
                DriveStep driveStep = (DriveStep) steps.get(i);
                LatLng a = C0444a.m4420a(m4401a(driveStep));
                if (i < steps.size() - 1 && i == 0) {
                    m4403a(this.startPoint, a);
                }
                m4405a(driveStep, a);
                m4409c(driveStep);
                if (i == steps.size() - 1) {
                    m4404a(m4406b(driveStep), this.endPoint);
                }
                i++;
            }
            addStartAndEndMarker();
            m4408c();
            m4407b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        if (this.f2858b != null && this.f2858b.size() > 0) {
            for (int i = 0; i < this.f2858b.size(); i++) {
                builder.include(new LatLng(((LatLonPoint) this.f2858b.get(i)).getLatitude(), ((LatLonPoint) this.f2858b.get(i)).getLongitude()));
            }
        }
        return builder.build();
    }

    public void removeFromMap() {
        try {
            super.removeFromMap();
            if (this.f2859c != null && this.f2859c.size() > 0) {
                for (int i = 0; i < this.f2859c.size(); i++) {
                    ((Marker) this.f2859c.get(i)).remove();
                }
                this.f2859c.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public void setThroughPointIconVisibility(boolean z) {
        try {
            this.f2860d = z;
            if (this.f2859c != null && this.f2859c.size() > 0) {
                for (int i = 0; i < this.f2859c.size(); i++) {
                    ((Marker) this.f2859c.get(i)).setVisible(z);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }
}
