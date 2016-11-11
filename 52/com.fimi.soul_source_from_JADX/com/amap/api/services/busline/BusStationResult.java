package com.amap.api.services.busline;

import com.amap.api.services.core.C0456b;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusStationResult {
    private int f2912a;
    private ArrayList<BusStationItem> f2913b;
    private BusStationQuery f2914c;
    private List<String> f2915d;
    private List<SuggestionCity> f2916e;

    private BusStationResult(C0456b c0456b, ArrayList<?> arrayList) {
        this.f2913b = new ArrayList();
        this.f2915d = new ArrayList();
        this.f2916e = new ArrayList();
        this.f2914c = (BusStationQuery) c0456b.m4614c();
        this.f2912a = m4432a(c0456b.m4615d());
        this.f2916e = c0456b.m4616f();
        this.f2915d = c0456b.b_();
        this.f2913b = arrayList;
    }

    private int m4432a(int i) {
        int pageSize = this.f2914c.getPageSize();
        pageSize = ((i + pageSize) - 1) / pageSize;
        return pageSize > 30 ? 30 : pageSize;
    }

    static BusStationResult m4433a(C0456b c0456b, ArrayList<?> arrayList) {
        return new BusStationResult(c0456b, arrayList);
    }

    public List<BusStationItem> getBusStations() {
        return this.f2913b;
    }

    public int getPageCount() {
        return this.f2912a;
    }

    public BusStationQuery getQuery() {
        return this.f2914c;
    }

    public List<SuggestionCity> getSearchSuggestionCities() {
        return this.f2916e;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f2915d;
    }
}
