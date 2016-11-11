package com.fimi.soul.entity;

public class CityInfo {
    private String city;
    private String cityID;
    private String id;
    private String province;
    private String town;
    private String townID;
    private String weatherID;

    public String getCity() {
        return this.city;
    }

    public String getCityID() {
        return this.cityID;
    }

    public String getId() {
        return this.id;
    }

    public String getProvince() {
        return this.province;
    }

    public String getTown() {
        return this.town;
    }

    public String getTownID() {
        return this.townID;
    }

    public String getWeatherID() {
        return this.weatherID;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCityID(String str) {
        this.cityID = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public void setTownID(String str) {
        this.townID = str;
    }

    public void setWeatherID(String str) {
        this.weatherID = str;
    }
}
