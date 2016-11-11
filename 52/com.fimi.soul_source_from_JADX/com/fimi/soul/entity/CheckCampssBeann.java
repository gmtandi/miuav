package com.fimi.soul.entity;

import com.amap.api.maps.model.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckCampssBeann implements Serializable {
    private List<LatLng> listLatlng;

    public CheckCampssBeann() {
        this.listLatlng = new ArrayList();
    }

    public List<LatLng> getListLatlng() {
        return this.listLatlng;
    }

    public void setListLatlng(List<LatLng> list) {
        this.listLatlng = list;
    }
}
