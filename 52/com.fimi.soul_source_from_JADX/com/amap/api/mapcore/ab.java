package com.amap.api.mapcore;

import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore.util.C0381f;
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
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import java.util.ArrayList;
import java.util.List;

public interface ab {
    aq m2213A();

    am m2214B();

    View m2215D();

    void m2216E();

    float m2217F();

    LatLngBounds m2218H();

    Point m2219I();

    float m2220J();

    int m2221K();

    List<Marker> m2222L();

    void m2223M();

    void m2224N();

    int m2225Q();

    boolean m2226R();

    C0323o m2227S();

    void m2228T();

    float m2229W();

    void m2230X();

    ac m2231a(ArcOptions arcOptions);

    ad m2232a(CircleOptions circleOptions);

    af m2233a(GroundOverlayOptions groundOverlayOptions);

    ai m2234a(NavigateArrowOptions navigateArrowOptions);

    ak m2235a(PolygonOptions polygonOptions);

    al m2236a(PolylineOptions polylineOptions);

    LatLngBounds m2237a(LatLng latLng, float f);

    Marker m2238a(MarkerOptions markerOptions);

    Text m2239a(TextOptions textOptions);

    TileOverlay m2240a(TileOverlayOptions tileOverlayOptions);

    ArrayList<Marker> m2241a(ArrayList<MarkerOptions> arrayList, boolean z);

    void m2242a(double d, double d2, FPoint fPoint);

    void m2243a(double d, double d2, IPoint iPoint);

    void m2244a(float f);

    void m2245a(float f, float f2, IPoint iPoint);

    void m2246a(int i);

    void m2247a(int i, int i2);

    void m2248a(int i, int i2, DPoint dPoint);

    void m2249a(int i, int i2, FPoint fPoint);

    void m2250a(int i, int i2, IPoint iPoint);

    void m2251a(Location location);

    void m2252a(ah ahVar);

    void m2253a(C0325p c0325p);

    void m2254a(C0325p c0325p, long j, CancelableCallback cancelableCallback);

    void m2255a(C0325p c0325p, CancelableCallback cancelableCallback);

    void m2256a(C0408v c0408v);

    void m2257a(InfoWindowAdapter infoWindowAdapter);

    void m2258a(OnCacheRemoveListener onCacheRemoveListener);

    void m2259a(OnCameraChangeListener onCameraChangeListener);

    void m2260a(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener);

    void m2261a(OnInfoWindowClickListener onInfoWindowClickListener);

    void m2262a(OnMapClickListener onMapClickListener);

    void m2263a(OnMapLoadedListener onMapLoadedListener);

    void m2264a(OnMapLongClickListener onMapLongClickListener);

    void m2265a(OnMapScreenShotListener onMapScreenShotListener);

    void m2266a(OnMapTouchListener onMapTouchListener);

    void m2267a(OnMarkerClickListener onMarkerClickListener);

    void m2268a(OnMarkerDragListener onMarkerDragListener);

    void m2269a(OnMyLocationChangeListener onMyLocationChangeListener);

    void m2270a(OnPOIClickListener onPOIClickListener);

    void m2271a(OnPolylineClickListener onPolylineClickListener);

    void m2272a(onMapPrintScreenListener com_amap_api_maps_AMap_onMapPrintScreenListener);

    void m2273a(CustomRenderer customRenderer);

    void m2274a(LocationSource locationSource);

    void m2275a(MyLocationStyle myLocationStyle);

    void m2276a(MyTrafficStyle myTrafficStyle);

    void m2277a(boolean z);

    boolean m2278a(MotionEvent motionEvent);

    boolean m2279a(String str);

    int m2280b();

    void m2281b(double d, double d2, IPoint iPoint);

    void m2282b(int i);

    void m2283b(int i, int i2, DPoint dPoint);

    void m2284b(int i, int i2, FPoint fPoint);

    void m2285b(C0325p c0325p);

    void m2286b(C0381f c0381f);

    void m2287b(boolean z);

    float m2288c(int i);

    MapProjection m2289c();

    void m2290c(boolean z);

    void m2291d();

    void m2292d(int i);

    void m2293d(boolean z);

    void m2294e();

    void m2295e(int i);

    void m2296e(boolean z);

    void m2297f();

    void m2298f(int i);

    void m2299f(boolean z);

    void m2300g();

    void m2301g(int i);

    void m2302g(boolean z);

    void m2303h();

    void m2304h(int i);

    void m2305h(boolean z);

    void m2306i(int i);

    void m2307i(boolean z);

    void m2308j(boolean z);

    Rect m2309k();

    void m2310k(boolean z);

    int m2311l();

    void m2312l(boolean z);

    int m2313m();

    void m2314m(boolean z);

    CameraPosition m2315n(boolean z);

    int m2316p();

    void m2317p(boolean z);

    void m2318q();

    CameraPosition m2319r();

    float m2320s();

    float m2321t();

    void m2322u();

    void m2323v();

    int m2324w();

    boolean m2325x();

    boolean m2326y();

    Location m2327z();
}
