package com.amap.api.maps.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkStep;
import java.util.List;

public class WalkRouteOverlay extends C0443b {
    private WalkPath f2866a;

    public WalkRouteOverlay(Context context, AMap aMap, WalkPath walkPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.mAMap = aMap;
        this.f2866a = walkPath;
        this.startPoint = C0444a.m4420a(latLonPoint);
        this.endPoint = C0444a.m4420a(latLonPoint2);
    }

    private LatLonPoint m4413a(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(walkStep.getPolyline().size() - 1);
    }

    private void m4414a(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).color(getWalkColor()).width(getRouteWidth()));
    }

    private void m4415a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        m4414a(C0444a.m4420a(latLonPoint), C0444a.m4420a(latLonPoint2));
    }

    private void m4416a(WalkStep walkStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("\u65b9\u5411:" + walkStep.getAction() + "\n\u9053\u8def:" + walkStep.getRoad()).snippet(walkStep.getInstruction()).visible(this.nodeIconVisible).anchor(0.5f, 0.5f).icon(getWalkBitmapDescriptor()));
    }

    private void m4417a(WalkStep walkStep, WalkStep walkStep2) {
        LatLonPoint a = m4413a(walkStep);
        LatLonPoint b = m4418b(walkStep2);
        if (!a.equals(b)) {
            m4415a(a, b);
        }
    }

    private LatLonPoint m4418b(WalkStep walkStep) {
        return (LatLonPoint) walkStep.getPolyline().get(0);
    }

    private void m4419c(WalkStep walkStep) {
        addPolyLine(new PolylineOptions().addAll(C0444a.m4421a(walkStep.getPolyline())).color(getWalkColor()).width(getRouteWidth()));
    }

    public void addToMap() {
        try {
            List steps = this.f2866a.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                WalkStep walkStep = (WalkStep) steps.get(i);
                LatLng a = C0444a.m4420a((LatLonPoint) walkStep.getPolyline().get(0));
                if (i < steps.size() - 1) {
                    if (i == 0) {
                        m4414a(this.startPoint, a);
                    }
                    m4417a(walkStep, (WalkStep) steps.get(i + 1));
                } else {
                    m4414a(C0444a.m4420a(m4413a(walkStep)), this.endPoint);
                }
                m4416a(walkStep, a);
                m4419c(walkStep);
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public /* bridge */ /* synthetic */ void removeFromMap() {
        super.removeFromMap();
    }

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }
}
