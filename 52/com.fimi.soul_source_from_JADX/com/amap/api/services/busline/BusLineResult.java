package com.amap.api.services.busline;

import com.amap.api.services.core.C0456b;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusLineResult {
    private int f2889a;
    private ArrayList<BusLineItem> f2890b;
    private BusLineQuery f2891c;
    private List<String> f2892d;
    private List<SuggestionCity> f2893e;

    private BusLineResult(C0456b c0456b, ArrayList<?> arrayList) {
        this.f2890b = new ArrayList();
        this.f2892d = new ArrayList();
        this.f2893e = new ArrayList();
        this.f2891c = (BusLineQuery) c0456b.m4614c();
        this.f2889a = m4423a(c0456b.m4615d());
        this.f2893e = c0456b.m4616f();
        this.f2892d = c0456b.b_();
        this.f2890b = arrayList;
    }

    private int m4423a(int i) {
        int pageSize = this.f2891c.getPageSize();
        pageSize = ((i + pageSize) - 1) / pageSize;
        return pageSize > 30 ? 30 : pageSize;
    }

    static BusLineResult m4424a(C0456b c0456b, ArrayList<?> arrayList) {
        return new BusLineResult(c0456b, arrayList);
    }

    public List<BusLineItem> getBusLines() {
        return this.f2890b;
    }

    public int getPageCount() {
        return this.f2889a;
    }

    public BusLineQuery getQuery() {
        return this.f2891c;
    }

    public List<SuggestionCity> getSearchSuggestionCities() {
        return this.f2893e;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f2892d;
    }
}
