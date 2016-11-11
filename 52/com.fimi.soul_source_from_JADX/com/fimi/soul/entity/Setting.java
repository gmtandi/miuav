package com.fimi.soul.entity;

import java.util.Observable;

public class Setting extends Observable {
    private String content;
    private boolean displayTv;
    private int id;
    private boolean isCheckedButton;
    private Boolean isOPen;
    private int newHandModel;

    public Setting() {
        this.isOPen = Boolean.valueOf(false);
        this.displayTv = false;
    }

    public String getContent() {
        return this.content;
    }

    public int getId() {
        return this.id;
    }

    public Boolean getIsOPen() {
        return this.isOPen;
    }

    public int getNewHandModel() {
        return this.newHandModel;
    }

    public boolean isCheckedButton() {
        return this.isCheckedButton;
    }

    public boolean isDisplayTv() {
        return this.displayTv;
    }

    public void setCheckedButton(boolean z) {
        this.isCheckedButton = z;
    }

    public void setContent(String str) {
        this.content = str;
        setChanged();
        notifyObservers();
    }

    public void setDisplayTv(boolean z) {
        this.displayTv = z;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setIsOPen(Boolean bool) {
        if (this.isOPen != null) {
            if ((!bool.booleanValue()) == this.isOPen.booleanValue()) {
                setChanged();
                notifyObservers();
                this.isOPen = bool;
            }
        }
    }

    public void setNewHandModel(int i) {
        this.newHandModel = i;
    }
}
