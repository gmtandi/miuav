package com.fimi.soul.entity;

public class SumFlyDataByMonth {
    private int count;
    private Double flyjourneysum;
    private Double flytimesum;
    private boolean isAccessNet;
    private String monthDate;

    public int getCount() {
        return this.count;
    }

    public Double getFlyjourneysum() {
        return this.flyjourneysum;
    }

    public Double getFlytimesum() {
        return this.flytimesum;
    }

    public String getMonthDate() {
        return this.monthDate;
    }

    public boolean isAccessNet() {
        return this.isAccessNet;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setFlyjourneysum(Double d) {
        this.flyjourneysum = d;
    }

    public void setFlytimesum(Double d) {
        this.flytimesum = d;
    }

    public void setIsAccessNet(boolean z) {
        this.isAccessNet = z;
    }

    public void setMonthDate(String str) {
        this.monthDate = str;
    }
}
