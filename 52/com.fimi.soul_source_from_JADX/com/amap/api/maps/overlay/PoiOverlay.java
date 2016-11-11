package com.amap.api.maps.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiOverlay {
    private List<PoiItem> f2863a;
    private AMap f2864b;
    private ArrayList<Marker> f2865c;

    public PoiOverlay(AMap aMap, List<PoiItem> list) {
        this.f2865c = new ArrayList();
        this.f2864b = aMap;
        this.f2863a = list;
    }

    private LatLngBounds m4411a() {
        Builder builder = LatLngBounds.builder();
        for (int i = 0; i < this.f2863a.size(); i++) {
            builder.include(new LatLng(((PoiItem) this.f2863a.get(i)).getLatLonPoint().getLatitude(), ((PoiItem) this.f2863a.get(i)).getLatLonPoint().getLongitude()));
        }
        return builder.build();
    }

    private MarkerOptions m4412a(int i) {
        return new MarkerOptions().position(new LatLng(((PoiItem) this.f2863a.get(i)).getLatLonPoint().getLatitude(), ((PoiItem) this.f2863a.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i)).icon(getBitmapDescriptor(i));
    }

    public void addToMap() {
        int i = 0;
        while (i < this.f2863a.size()) {
            try {
                Marker addMarker = this.f2864b.addMarker(m4412a(i));
                addMarker.setObject(Integer.valueOf(i));
                this.f2865c.add(addMarker);
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
    }

    protected BitmapDescriptor getBitmapDescriptor(int i) {
        return null;
    }

    public int getPoiIndex(Marker marker) {
        for (int i = 0; i < this.f2865c.size(); i++) {
            if (((Marker) this.f2865c.get(i)).equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    public PoiItem getPoiItem(int i) {
        return (i < 0 || i >= this.f2863a.size()) ? null : (PoiItem) this.f2863a.get(i);
    }

    protected String getSnippet(int i) {
        return ((PoiItem) this.f2863a.get(i)).getSnippet();
    }

    protected String getTitle(int i) {
        return ((PoiItem) this.f2863a.get(i)).getTitle();
    }

    public void removeFromMap() {
        Iterator it = this.f2865c.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
    }

    public void zoomToSpan() {
        try {
            if (this.f2863a != null && this.f2863a.size() > 0 && this.f2864b != null) {
                if (this.f2863a.size() == 1) {
                    this.f2864b.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(((PoiItem) this.f2863a.get(0)).getLatLonPoint().getLatitude(), ((PoiItem) this.f2863a.get(0)).getLatLonPoint().getLongitude()), 18.0f));
                    return;
                }
                this.f2864b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4411a(), 5));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
