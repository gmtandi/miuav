package com.fimi.soul.entity;

import java.io.Serializable;

public class UpgradeResultInfo implements Serializable {
    private String jsonStr;
    private String userID;

    public String getJsonStr() {
        return this.jsonStr;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setJsonStr(String str) {
        this.jsonStr = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }
}
