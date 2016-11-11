package com.amap.api.services.core;

public class SuggestionCity {
    private String f2953a;
    private String f2954b;
    private String f2955c;
    private int f2956d;

    protected SuggestionCity() {
    }

    public SuggestionCity(String str, String str2, String str3, int i) {
        this.f2953a = str;
        this.f2954b = str2;
        this.f2955c = str3;
        this.f2956d = i;
    }

    public String getAdCode() {
        return this.f2955c;
    }

    public String getCityCode() {
        return this.f2954b;
    }

    public String getCityName() {
        return this.f2953a;
    }

    public int getSuggestionNum() {
        return this.f2956d;
    }

    public void setAdCode(String str) {
        this.f2955c = str;
    }

    public void setCityCode(String str) {
        this.f2954b = str;
    }

    public void setCityName(String str) {
        this.f2953a = str;
    }

    public void setSuggestionNum(int i) {
        this.f2956d = i;
    }
}
