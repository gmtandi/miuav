package com.amap.api.services.geocoder;

import java.util.ArrayList;
import java.util.List;

public class GeocodeResult {
    private GeocodeQuery f3226a;
    private List<GeocodeAddress> f3227b;

    public GeocodeResult(GeocodeQuery geocodeQuery, List<GeocodeAddress> list) {
        this.f3227b = new ArrayList();
        this.f3226a = geocodeQuery;
        this.f3227b = list;
    }

    public List<GeocodeAddress> getGeocodeAddressList() {
        return this.f3227b;
    }

    public GeocodeQuery getGeocodeQuery() {
        return this.f3226a;
    }

    public void setGeocodeAddressList(List<GeocodeAddress> list) {
        this.f3227b = list;
    }

    public void setGeocodeQuery(GeocodeQuery geocodeQuery) {
        this.f3226a = geocodeQuery;
    }
}
