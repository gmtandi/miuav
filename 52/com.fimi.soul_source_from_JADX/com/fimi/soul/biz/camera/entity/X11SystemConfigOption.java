package com.fimi.soul.biz.camera.entity;

public class X11SystemConfigOption {
    private String defaultOption;
    private String[] options;
    private String[] optionsText;
    private String permission;
    private String type;

    public String getDefaultOption() {
        return this.defaultOption;
    }

    public String[] getOptions() {
        return this.options;
    }

    public String[] getOptionsText() {
        return this.optionsText;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getType() {
        return this.type;
    }

    public boolean isReadonly() {
        return "readonly".equals(this.permission);
    }

    public void setDefaultOption(String str) {
        this.defaultOption = str;
    }

    public void setOptions(String[] strArr) {
        this.options = strArr;
    }

    public void setOptionsText(String[] strArr) {
        this.optionsText = strArr;
    }

    public void setPermission(String str) {
        this.permission = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
