package com.amap.api.maps.model;

public class MyTrafficStyle {
    private int f2733a;
    private int f2734b;
    private int f2735c;
    private int f2736d;

    public MyTrafficStyle() {
        this.f2733a = -16735735;
        this.f2734b = -35576;
        this.f2735c = -1441006;
        this.f2736d = -7208950;
    }

    public int getCongestedColor() {
        return this.f2735c;
    }

    public int getSeriousCongestedColor() {
        return this.f2736d;
    }

    public int getSlowColor() {
        return this.f2734b;
    }

    public int getSmoothColor() {
        return this.f2733a;
    }

    public void setCongestedColor(int i) {
        this.f2735c = i;
    }

    public void setSeriousCongestedColor(int i) {
        this.f2736d = i;
    }

    public void setSlowColor(int i) {
        this.f2734b = i;
    }

    public void setSmoothColor(int i) {
        this.f2733a = i;
    }
}
