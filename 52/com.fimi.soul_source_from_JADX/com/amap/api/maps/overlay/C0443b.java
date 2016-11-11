package com.amap.api.maps.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.amap.api.maps.overlay.b */
class C0443b {
    private Context f2849a;
    protected List<Polyline> allPolyLines;
    private Bitmap f2850b;
    private Bitmap f2851c;
    private Bitmap f2852d;
    private Bitmap f2853e;
    protected Marker endMarker;
    protected LatLng endPoint;
    private Bitmap f2854f;
    protected AMap mAMap;
    protected boolean nodeIconVisible;
    protected Marker startMarker;
    protected LatLng startPoint;
    protected List<Marker> stationMarkers;

    public C0443b(Context context) {
        this.stationMarkers = new ArrayList();
        this.allPolyLines = new ArrayList();
        this.nodeIconVisible = true;
        this.f2849a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amap.api.maps.model.BitmapDescriptor m4381a(android.graphics.Bitmap r4, java.lang.String r5) {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.f2849a;	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r0 = com.amap.api.mapcore.util.bh.m3592a(r0);	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r1 = r0.open(r5);	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r4 = android.graphics.BitmapFactory.decodeStream(r1);	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        r0 = com.amap.api.mapcore.C0330s.f2068a;	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        r0 = com.amap.api.mapcore.util.bj.m3610a(r4, r0);	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        if (r1 == 0) goto L_0x001a;
    L_0x0017:
        r1.close();	 Catch:{ IOException -> 0x0022 }
    L_0x001a:
        r1 = com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(r0);
        r0.recycle();
        return r1;
    L_0x0022:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x0027:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r4;
    L_0x002b:
        r1.printStackTrace();	 Catch:{ all -> 0x0045 }
        if (r2 == 0) goto L_0x001a;
    L_0x0030:
        r2.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x001a;
    L_0x0034:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x0039:
        r0 = move-exception;
    L_0x003a:
        if (r1 == 0) goto L_0x003f;
    L_0x003c:
        r1.close();	 Catch:{ IOException -> 0x0040 }
    L_0x003f:
        throw r0;
    L_0x0040:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003f;
    L_0x0045:
        r0 = move-exception;
        r1 = r2;
        goto L_0x003a;
    L_0x0048:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.overlay.b.a(android.graphics.Bitmap, java.lang.String):com.amap.api.maps.model.BitmapDescriptor");
    }

    private void m4382a() {
        if (this.f2850b != null) {
            this.f2850b.recycle();
            this.f2850b = null;
        }
        if (this.f2851c != null) {
            this.f2851c.recycle();
            this.f2851c = null;
        }
        if (this.f2852d != null) {
            this.f2852d.recycle();
            this.f2852d = null;
        }
        if (this.f2853e != null) {
            this.f2853e.recycle();
            this.f2853e = null;
        }
        if (this.f2854f != null) {
            this.f2854f.recycle();
            this.f2854f = null;
        }
    }

    protected void addPolyLine(PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            Polyline addPolyline = this.mAMap.addPolyline(polylineOptions);
            if (addPolyline != null) {
                this.allPolyLines.add(addPolyline);
            }
        }
    }

    protected void addStartAndEndMarker() {
        this.startMarker = this.mAMap.addMarker(new MarkerOptions().position(this.startPoint).icon(getStartBitmapDescriptor()).title("\u8d77\u70b9"));
        this.endMarker = this.mAMap.addMarker(new MarkerOptions().position(this.endPoint).icon(getEndBitmapDescriptor()).title("\u7ec8\u70b9"));
    }

    protected void addStationMarker(MarkerOptions markerOptions) {
        if (markerOptions != null) {
            Marker addMarker = this.mAMap.addMarker(markerOptions);
            if (addMarker != null) {
                this.stationMarkers.add(addMarker);
            }
        }
    }

    protected BitmapDescriptor getBusBitmapDescriptor() {
        return m4381a(this.f2852d, "amap_bus.png");
    }

    protected int getBusColor() {
        return Color.parseColor("#537edc");
    }

    protected BitmapDescriptor getDriveBitmapDescriptor() {
        return m4381a(this.f2854f, "amap_car.png");
    }

    protected int getDriveColor() {
        return Color.parseColor("#537edc");
    }

    protected BitmapDescriptor getEndBitmapDescriptor() {
        return m4381a(this.f2851c, "amap_end.png");
    }

    protected LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        return builder.build();
    }

    protected float getRouteWidth() {
        return 18.0f;
    }

    protected BitmapDescriptor getStartBitmapDescriptor() {
        return m4381a(this.f2850b, "amap_start.png");
    }

    protected BitmapDescriptor getWalkBitmapDescriptor() {
        return m4381a(this.f2853e, "amap_man.png");
    }

    protected int getWalkColor() {
        return Color.parseColor("#6db74d");
    }

    public void removeFromMap() {
        if (this.startMarker != null) {
            this.startMarker.remove();
        }
        if (this.endMarker != null) {
            this.endMarker.remove();
        }
        for (Marker remove : this.stationMarkers) {
            remove.remove();
        }
        for (Polyline remove2 : this.allPolyLines) {
            remove2.remove();
        }
        m4382a();
    }

    public void setNodeIconVisibility(boolean z) {
        try {
            this.nodeIconVisible = z;
            if (this.stationMarkers != null && this.stationMarkers.size() > 0) {
                for (int i = 0; i < this.stationMarkers.size(); i++) {
                    ((Marker) this.stationMarkers.get(i)).setVisible(z);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void zoomToSpan() {
        if (this.startPoint != null && this.mAMap != null) {
            try {
                this.mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(), 50));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
