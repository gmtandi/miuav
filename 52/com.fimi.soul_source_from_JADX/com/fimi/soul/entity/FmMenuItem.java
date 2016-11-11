package com.fimi.soul.entity;

public class FmMenuItem {
    private int iconId;
    private int id;
    private String mark;
    private String text;

    public FmMenuItem(int i, String str) {
        this.id = i;
        this.text = str;
    }

    public FmMenuItem(String str) {
        this.text = str;
    }

    public int getIconId() {
        return this.iconId;
    }

    public int getId() {
        return this.id;
    }

    public String getMark() {
        return this.mark;
    }

    public String getText() {
        return this.text;
    }

    public void setIconId(int i) {
        this.iconId = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setMark(String str) {
        this.mark = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
