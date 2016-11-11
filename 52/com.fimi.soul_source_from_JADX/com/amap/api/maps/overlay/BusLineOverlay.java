package com.amap.api.maps.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import com.amap.api.mapcore.C0330s;
import com.amap.api.mapcore.util.bh;
import com.amap.api.mapcore.util.bj;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.p122a.p123a.C2915a;

public class BusLineOverlay {
    private BusLineItem f2840a;
    private AMap f2841b;
    private ArrayList<Marker> f2842c;
    private Polyline f2843d;
    private List<BusStationItem> f2844e;
    private BitmapDescriptor f2845f;
    private BitmapDescriptor f2846g;
    private BitmapDescriptor f2847h;
    private Context f2848i;

    public BusLineOverlay(Context context, AMap aMap, BusLineItem busLineItem) {
        this.f2842c = new ArrayList();
        this.f2848i = context;
        this.f2840a = busLineItem;
        this.f2841b = aMap;
        this.f2844e = this.f2840a.getBusStations();
    }

    private BitmapDescriptor m4377a(String str) {
        InputStream open;
        Bitmap a;
        IOException e;
        IOException iOException;
        BitmapDescriptor fromBitmap;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object obj;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            open = bh.m3592a(this.f2848i).open(str);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                try {
                    a = bj.m3610a(decodeStream, C0330s.f2068a);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    iOException = e3;
                    a = decodeStream;
                    e2 = iOException;
                    try {
                        e2.printStackTrace();
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        return fromBitmap;
                    } catch (Throwable th4) {
                        th = th4;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    a = decodeStream;
                    th3 = th2;
                    th3.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                    a.recycle();
                    return fromBitmap;
                }
            } catch (IOException e32) {
                iOException = e32;
                obj = inputStream2;
                e2222 = iOException;
                e2222.printStackTrace();
                if (open != null) {
                    open.close();
                }
                fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                a.recycle();
                return fromBitmap;
            } catch (Throwable th52) {
                th2 = th52;
                obj = inputStream2;
                th3 = th2;
                th3.printStackTrace();
                if (open != null) {
                    open.close();
                }
                fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                a.recycle();
                return fromBitmap;
            }
        } catch (IOException e322) {
            open = inputStream2;
            inputStream = inputStream2;
            e2222 = e322;
            a = inputStream;
            e2222.printStackTrace();
            if (open != null) {
                open.close();
            }
            fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
            a.recycle();
            return fromBitmap;
        } catch (Throwable th6) {
            th52 = th6;
            open = inputStream2;
            if (open != null) {
                open.close();
            }
            throw th52;
        }
        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
        a.recycle();
        return fromBitmap;
    }

    private LatLngBounds m4378a(List<LatLonPoint> list) {
        Builder builder = LatLngBounds.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.include(new LatLng(((LatLonPoint) list.get(i)).getLatitude(), ((LatLonPoint) list.get(i)).getLongitude()));
        }
        return builder.build();
    }

    private MarkerOptions m4379a(int i) {
        MarkerOptions snippet = new MarkerOptions().position(new LatLng(((BusStationItem) this.f2844e.get(i)).getLatLonPoint().getLatitude(), ((BusStationItem) this.f2844e.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i));
        if (i == 0) {
            snippet.icon(getStartBitmapDescriptor());
        } else if (i == this.f2844e.size() - 1) {
            snippet.icon(getEndBitmapDescriptor());
        } else {
            snippet.anchor(0.5f, 0.5f);
            snippet.icon(getBusBitmapDescriptor());
        }
        return snippet;
    }

    private void m4380a() {
        if (this.f2845f != null) {
            this.f2845f.recycle();
            this.f2845f = null;
        }
        if (this.f2846g != null) {
            this.f2846g.recycle();
            this.f2846g = null;
        }
        if (this.f2847h != null) {
            this.f2847h.recycle();
            this.f2847h = null;
        }
    }

    public void addToMap() {
        int i = 1;
        try {
            this.f2843d = this.f2841b.addPolyline(new PolylineOptions().addAll(C0444a.m4421a(this.f2840a.getDirectionsCoordinates())).color(getBusColor()).width(getBuslineWidth()));
            if (this.f2844e.size() >= 1) {
                while (i < this.f2844e.size() - 1) {
                    this.f2842c.add(this.f2841b.addMarker(m4379a(i)));
                    i++;
                }
                this.f2842c.add(this.f2841b.addMarker(m4379a(0)));
                this.f2842c.add(this.f2841b.addMarker(m4379a(this.f2844e.size() - 1)));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected BitmapDescriptor getBusBitmapDescriptor() {
        this.f2847h = m4377a("amap_bus.png");
        return this.f2847h;
    }

    protected int getBusColor() {
        return Color.parseColor("#537edc");
    }

    public int getBusStationIndex(Marker marker) {
        for (int i = 0; i < this.f2842c.size(); i++) {
            if (((Marker) this.f2842c.get(i)).equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    public BusStationItem getBusStationItem(int i) {
        return (i < 0 || i >= this.f2844e.size()) ? null : (BusStationItem) this.f2844e.get(i);
    }

    protected float getBuslineWidth() {
        return 18.0f;
    }

    protected BitmapDescriptor getEndBitmapDescriptor() {
        this.f2846g = m4377a("amap_end.png");
        return this.f2846g;
    }

    protected String getSnippet(int i) {
        return C2915a.f14760f;
    }

    protected BitmapDescriptor getStartBitmapDescriptor() {
        this.f2845f = m4377a("amap_start.png");
        return this.f2845f;
    }

    protected String getTitle(int i) {
        return ((BusStationItem) this.f2844e.get(i)).getBusStationName();
    }

    public void removeFromMap() {
        if (this.f2843d != null) {
            this.f2843d.remove();
        }
        try {
            Iterator it = this.f2842c.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).remove();
            }
            m4380a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void zoomToSpan() {
        if (this.f2841b != null) {
            try {
                List directionsCoordinates = this.f2840a.getDirectionsCoordinates();
                if (directionsCoordinates != null && directionsCoordinates.size() > 0) {
                    this.f2841b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4378a(directionsCoordinates), 5));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
