package com.amap.api.services.busline;

import com.amap.api.services.core.C0471d;

public class BusLineQuery {
    private String f2884a;
    private String f2885b;
    private int f2886c;
    private int f2887d;
    private SearchType f2888e;

    public enum SearchType {
        BY_LINE_ID,
        BY_LINE_NAME
    }

    public BusLineQuery(String str, SearchType searchType, String str2) {
        this.f2886c = 10;
        this.f2887d = 0;
        this.f2884a = str;
        this.f2888e = searchType;
        this.f2885b = str2;
        if (!m4422a()) {
            throw new IllegalArgumentException("Empty query");
        }
    }

    private boolean m4422a() {
        return !C0471d.m4763a(this.f2884a);
    }

    protected BusLineQuery clone() {
        BusLineQuery busLineQuery = new BusLineQuery(this.f2884a, this.f2888e, this.f2885b);
        busLineQuery.setPageNumber(this.f2887d);
        busLineQuery.setPageSize(this.f2886c);
        return busLineQuery;
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
        BusLineQuery busLineQuery = (BusLineQuery) obj;
        if (this.f2888e != busLineQuery.f2888e) {
            return false;
        }
        if (this.f2885b == null) {
            if (busLineQuery.f2885b != null) {
                return false;
            }
        } else if (!this.f2885b.equals(busLineQuery.f2885b)) {
            return false;
        }
        return this.f2887d != busLineQuery.f2887d ? false : this.f2886c != busLineQuery.f2886c ? false : this.f2884a == null ? busLineQuery.f2884a == null : this.f2884a.equals(busLineQuery.f2884a);
    }

    public SearchType getCategory() {
        return this.f2888e;
    }

    public String getCity() {
        return this.f2885b;
    }

    public int getPageNumber() {
        return this.f2887d;
    }

    public int getPageSize() {
        return this.f2886c;
    }

    public String getQueryString() {
        return this.f2884a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f2885b == null ? 0 : this.f2885b.hashCode()) + (((this.f2888e == null ? 0 : this.f2888e.hashCode()) + 31) * 31)) * 31) + this.f2887d) * 31) + this.f2886c) * 31;
        if (this.f2884a != null) {
            i = this.f2884a.hashCode();
        }
        return hashCode + i;
    }

    public void setCategory(SearchType searchType) {
        this.f2888e = searchType;
    }

    public void setCity(String str) {
        this.f2885b = str;
    }

    public void setPageNumber(int i) {
        this.f2887d = i;
    }

    public void setPageSize(int i) {
        this.f2886c = i;
    }

    public void setQueryString(String str) {
        this.f2884a = str;
    }

    protected boolean weakEquals(BusLineQuery busLineQuery) {
        return busLineQuery.getQueryString().equals(this.f2884a) && busLineQuery.getCity().equals(this.f2885b) && busLineQuery.getPageSize() == this.f2886c && busLineQuery.getCategory().compareTo(this.f2888e) == 0;
    }
}
