package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

public class RegeocodeQuery {
    private LatLonPoint f3249a;
    private float f3250b;
    private String f3251c;

    public RegeocodeQuery(LatLonPoint latLonPoint, float f, String str) {
        this.f3251c = GeocodeSearch.AMAP;
        this.f3249a = latLonPoint;
        this.f3250b = f;
        setLatLonType(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RegeocodeQuery regeocodeQuery = (RegeocodeQuery) obj;
        if (this.f3251c == null) {
            if (regeocodeQuery.f3251c != null) {
                return false;
            }
        } else if (!this.f3251c.equals(regeocodeQuery.f3251c)) {
            return false;
        }
        if (this.f3249a == null) {
            if (regeocodeQuery.f3249a != null) {
                return false;
            }
        } else if (!this.f3249a.equals(regeocodeQuery.f3249a)) {
            return false;
        }
        return Float.floatToIntBits(this.f3250b) == Float.floatToIntBits(regeocodeQuery.f3250b);
    }

    public String getLatLonType() {
        return this.f3251c;
    }

    public LatLonPoint getPoint() {
        return this.f3249a;
    }

    public float getRadius() {
        return this.f3250b;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3251c == null ? 0 : this.f3251c.hashCode()) + 31) * 31;
        if (this.f3249a != null) {
            i = this.f3249a.hashCode();
        }
        return ((hashCode + i) * 31) + Float.floatToIntBits(this.f3250b);
    }

    public void setLatLonType(String str) {
        if (str == null) {
            return;
        }
        if (str.equals(GeocodeSearch.AMAP) || str.equals(GeocodeSearch.GPS)) {
            this.f3251c = str;
        }
    }

    public void setPoint(LatLonPoint latLonPoint) {
        this.f3249a = latLonPoint;
    }

    public void setRadius(float f) {
        this.f3250b = f;
    }
}
