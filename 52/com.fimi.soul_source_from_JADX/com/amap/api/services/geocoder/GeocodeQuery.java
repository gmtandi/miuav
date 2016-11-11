package com.amap.api.services.geocoder;

public class GeocodeQuery {
    private String f3224a;
    private String f3225b;

    public GeocodeQuery(String str, String str2) {
        this.f3224a = str;
        this.f3225b = str2;
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
        GeocodeQuery geocodeQuery = (GeocodeQuery) obj;
        if (this.f3225b == null) {
            if (geocodeQuery.f3225b != null) {
                return false;
            }
        } else if (!this.f3225b.equals(geocodeQuery.f3225b)) {
            return false;
        }
        return this.f3224a == null ? geocodeQuery.f3224a == null : this.f3224a.equals(geocodeQuery.f3224a);
    }

    public String getCity() {
        return this.f3225b;
    }

    public String getLocationName() {
        return this.f3224a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3225b == null ? 0 : this.f3225b.hashCode()) + 31) * 31;
        if (this.f3224a != null) {
            i = this.f3224a.hashCode();
        }
        return hashCode + i;
    }

    public void setCity(String str) {
        this.f3225b = str;
    }

    public void setLocationName(String str) {
        this.f3224a = str;
    }
}
