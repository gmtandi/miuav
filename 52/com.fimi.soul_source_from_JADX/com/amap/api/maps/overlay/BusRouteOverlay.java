package com.amap.api.maps.overlay;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.WalkStep;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.List;

public class BusRouteOverlay extends C0443b {
    private BusPath f2855a;
    private LatLng f2856b;

    public BusRouteOverlay(Context context, AMap aMap, BusPath busPath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        super(context);
        this.f2855a = busPath;
        this.startPoint = C0444a.m4420a(latLonPoint);
        this.endPoint = C0444a.m4420a(latLonPoint2);
        this.mAMap = aMap;
    }

    private void m4383a(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).width(getRouteWidth()).color(getWalkColor()));
    }

    private void m4384a(LatLng latLng, String str, String str2) {
        addStationMarker(new MarkerOptions().position(latLng).title(str).snippet(str2).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getWalkBitmapDescriptor()));
    }

    private void m4385a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        m4383a(C0444a.m4420a(latLonPoint), C0444a.m4420a(latLonPoint2));
    }

    private void m4386a(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        for (int i = 0; i < steps.size(); i++) {
            WalkStep walkStep = (WalkStep) steps.get(i);
            if (i == 0) {
                m4384a(C0444a.m4420a((LatLonPoint) walkStep.getPolyline().get(0)), walkStep.getRoad(), m4396c(steps));
            }
            List a = C0444a.m4421a(walkStep.getPolyline());
            this.f2856b = (LatLng) a.get(a.size() - 1);
            m4393b(a);
            if (i < steps.size() - 1) {
                LatLng latLng = (LatLng) a.get(a.size() - 1);
                LatLng a2 = C0444a.m4420a((LatLonPoint) ((WalkStep) steps.get(i + 1)).getPolyline().get(0));
                if (!latLng.equals(a2)) {
                    m4383a(latLng, a2);
                }
            }
        }
    }

    private void m4387a(BusStep busStep, BusStep busStep2) {
        LatLng a = C0444a.m4420a(m4399e(busStep));
        LatLng a2 = C0444a.m4420a(m4400f(busStep2));
        if (a2.latitude - a.latitude > 1.0E-4d || a2.longitude - a.longitude > 1.0E-4d) {
            drawLineArrow(a, a2);
        }
    }

    private void m4388a(RouteBusLineItem routeBusLineItem) {
        m4389a(routeBusLineItem.getPolyline());
    }

    private void m4389a(List<LatLonPoint> list) {
        if (list.size() >= 1) {
            addPolyLine(new PolylineOptions().width(getRouteWidth()).color(getBusColor()).addAll(C0444a.m4421a((List) list)));
        }
    }

    private void m4390b(BusStep busStep) {
        LatLonPoint d = m4398d(busStep);
        LatLonPoint f = m4400f(busStep);
        if (!d.equals(f)) {
            m4385a(d, f);
        }
    }

    private void m4391b(BusStep busStep, BusStep busStep2) {
        LatLng a = C0444a.m4420a(m4399e(busStep));
        LatLng a2 = C0444a.m4420a(m4400f(busStep2));
        if (!a.equals(a2)) {
            drawLineArrow(a, a2);
        }
    }

    private void m4392b(RouteBusLineItem routeBusLineItem) {
        LatLng a = C0444a.m4420a(routeBusLineItem.getDepartureBusStation().getLatLonPoint());
        String busLineName = routeBusLineItem.getBusLineName();
        addStationMarker(new MarkerOptions().position(a).title(busLineName).snippet(m4395c(routeBusLineItem)).anchor(0.5f, 0.5f).visible(this.nodeIconVisible).icon(getBusBitmapDescriptor()));
    }

    private void m4393b(List<LatLng> list) {
        addPolyLine(new PolylineOptions().addAll(list).color(getWalkColor()).width(getRouteWidth()));
    }

    private LatLonPoint m4394c(BusStep busStep) {
        return (LatLonPoint) ((WalkStep) busStep.getWalk().getSteps().get(0)).getPolyline().get(0);
    }

    private String m4395c(RouteBusLineItem routeBusLineItem) {
        return "(" + routeBusLineItem.getDepartureBusStation().getBusStationName() + "-->" + routeBusLineItem.getArrivalBusStation().getBusStationName() + ") \u7ecf\u8fc7" + (routeBusLineItem.getPassStationNum() + 1) + "\u7ad9";
    }

    private String m4396c(List<WalkStep> list) {
        float f = 0.0f;
        for (WalkStep distance : list) {
            f = distance.getDistance() + f;
        }
        return "\u6b65\u884c" + f + "\u7c73";
    }

    private void m4397c(BusStep busStep, BusStep busStep2) {
        LatLonPoint e = m4399e(busStep);
        LatLonPoint c = m4394c(busStep2);
        if (!e.equals(c)) {
            m4385a(e, c);
        }
    }

    private LatLonPoint m4398d(BusStep busStep) {
        List steps = busStep.getWalk().getSteps();
        steps = ((WalkStep) steps.get(steps.size() - 1)).getPolyline();
        return (LatLonPoint) steps.get(steps.size() - 1);
    }

    private LatLonPoint m4399e(BusStep busStep) {
        List polyline = busStep.getBusLine().getPolyline();
        return (LatLonPoint) polyline.get(polyline.size() - 1);
    }

    private LatLonPoint m4400f(BusStep busStep) {
        return (LatLonPoint) busStep.getBusLine().getPolyline().get(0);
    }

    public void addToMap() {
        try {
            List steps = this.f2855a.getSteps();
            for (int i = 0; i < steps.size(); i++) {
                BusStep busStep = (BusStep) steps.get(i);
                if (i < steps.size() - 1) {
                    BusStep busStep2 = (BusStep) steps.get(i + 1);
                    if (!(busStep.getWalk() == null || busStep.getBusLine() == null)) {
                        m4390b(busStep);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() == null)) {
                        m4397c(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4391b(busStep, busStep2);
                    }
                    if (!(busStep.getBusLine() == null || busStep2.getWalk() != null || busStep2.getBusLine() == null)) {
                        m4387a(busStep, busStep2);
                    }
                }
                if (busStep.getWalk() != null && busStep.getWalk().getSteps().size() > 0) {
                    m4386a(busStep);
                } else if (busStep.getBusLine() == null) {
                    m4383a(this.f2856b, this.endPoint);
                }
                if (busStep.getBusLine() != null) {
                    RouteBusLineItem busLine = busStep.getBusLine();
                    m4388a(busLine);
                    m4392b(busLine);
                }
            }
            addStartAndEndMarker();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void drawLineArrow(LatLng latLng, LatLng latLng2) {
        addPolyLine(new PolylineOptions().add(latLng, latLng2).width(C2020f.f10931a).color(getBusColor()).width(getRouteWidth()));
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
