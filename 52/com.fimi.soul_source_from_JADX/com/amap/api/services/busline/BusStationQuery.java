package com.amap.api.services.busline;

import com.amap.api.services.core.C0471d;

public class BusStationQuery {
    private String f2908a;
    private String f2909b;
    private int f2910c;
    private int f2911d;

    public BusStationQuery(String str, String str2) {
        this.f2910c = 10;
        this.f2911d = 0;
        this.f2908a = str;
        this.f2909b = str2;
        if (!m4431a()) {
            throw new IllegalArgumentException("Empty query");
        }
    }

    private boolean m4431a() {
        return !C0471d.m4763a(this.f2908a);
    }

    protected BusStationQuery clone() {
        BusStationQuery busStationQuery = new BusStationQuery(this.f2908a, this.f2909b);
        busStationQuery.setPageNumber(this.f2911d);
        busStationQuery.setPageSize(this.f2910c);
        return busStationQuery;
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
        BusStationQuery busStationQuery = (BusStationQuery) obj;
        if (this.f2909b == null) {
            if (busStationQuery.f2909b != null) {
                return false;
            }
        } else if (!this.f2909b.equals(busStationQuery.f2909b)) {
            return false;
        }
        return this.f2911d != busStationQuery.f2911d ? false : this.f2910c != busStationQuery.f2910c ? false : this.f2908a == null ? busStationQuery.f2908a == null : this.f2908a.equals(busStationQuery.f2908a);
    }

    public String getCity() {
        return this.f2909b;
    }

    public int getPageNumber() {
        return this.f2911d;
    }

    public int getPageSize() {
        return this.f2910c;
    }

    public String getQueryString() {
        return this.f2908a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f2909b == null ? 0 : this.f2909b.hashCode()) + 31) * 31) + this.f2911d) * 31) + this.f2910c) * 31;
        if (this.f2908a != null) {
            i = this.f2908a.hashCode();
        }
        return hashCode + i;
    }

    public void setCity(String str) {
        this.f2909b = str;
    }

    public void setPageNumber(int i) {
        this.f2911d = i;
    }

    public void setPageSize(int i) {
        int i2 = 20;
        if (i <= 20) {
            i2 = i;
        }
        if (i2 <= 0) {
            i2 = 10;
        }
        this.f2910c = i2;
    }

    public void setQueryString(String str) {
        this.f2908a = str;
    }

    protected boolean weakEquals(BusStationQuery busStationQuery) {
        if (this == busStationQuery) {
            return true;
        }
        if (busStationQuery == null) {
            return false;
        }
        if (this.f2909b == null) {
            if (busStationQuery.f2909b != null) {
                return false;
            }
        } else if (!this.f2909b.equals(busStationQuery.f2909b)) {
            return false;
        }
        return this.f2910c != busStationQuery.f2910c ? false : this.f2908a == null ? busStationQuery.f2908a == null : this.f2908a.equals(busStationQuery.f2908a);
    }
}
