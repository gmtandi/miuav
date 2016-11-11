package com.amap.api.services.poisearch;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import java.util.ArrayList;
import java.util.List;

public final class PoiResult {
    private int f3346a;
    private ArrayList<PoiItem> f3347b;
    private C0525j f3348c;

    private PoiResult(C0525j c0525j, ArrayList<PoiItem> arrayList) {
        this.f3347b = new ArrayList();
        this.f3348c = c0525j;
        this.f3346a = m4944a(c0525j.m4989i());
        this.f3347b = arrayList;
    }

    private int m4944a(int i) {
        int f = this.f3348c.m4988f();
        f = ((i + f) - 1) / f;
        return f > 30 ? 30 : f;
    }

    static PoiResult m4945a(C0525j c0525j, ArrayList<PoiItem> arrayList) {
        return new PoiResult(c0525j, arrayList);
    }

    public SearchBound getBound() {
        return this.f3348c.m4991k();
    }

    public int getPageCount() {
        return this.f3346a;
    }

    public ArrayList<PoiItem> getPois() {
        return this.f3347b;
    }

    public Query getQuery() {
        return this.f3348c.m4990j();
    }

    public List<SuggestionCity> getSearchSuggestionCitys() {
        return this.f3348c.m4993m();
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f3348c.m4992l();
    }
}
