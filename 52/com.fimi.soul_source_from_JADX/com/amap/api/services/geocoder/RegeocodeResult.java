package com.amap.api.services.geocoder;

public class RegeocodeResult {
    private RegeocodeQuery f3252a;
    private RegeocodeAddress f3253b;

    public RegeocodeResult(RegeocodeQuery regeocodeQuery, RegeocodeAddress regeocodeAddress) {
        this.f3252a = regeocodeQuery;
        this.f3253b = regeocodeAddress;
    }

    public RegeocodeAddress getRegeocodeAddress() {
        return this.f3253b;
    }

    public RegeocodeQuery getRegeocodeQuery() {
        return this.f3252a;
    }

    public void setRegeocodeAddress(RegeocodeAddress regeocodeAddress) {
        this.f3253b = regeocodeAddress;
    }

    public void setRegeocodeQuery(RegeocodeQuery regeocodeQuery) {
        this.f3252a = regeocodeQuery;
    }
}
