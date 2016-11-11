package com.fimi.soul.entity;

import java.io.Serializable;
import java.util.List;

public class AddressInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String long_name;
    private String short_name;
    private List<String> types;

    public String getLong_name() {
        return this.long_name;
    }

    public String getShort_name() {
        return this.short_name;
    }

    public List<String> getTypes() {
        return this.types;
    }

    public void setLong_name(String str) {
        this.long_name = str;
    }

    public void setShort_name(String str) {
        this.short_name = str;
    }

    public void setTypes(List<String> list) {
        this.types = list;
    }
}
