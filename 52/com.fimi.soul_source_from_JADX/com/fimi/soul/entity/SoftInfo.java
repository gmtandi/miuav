package com.fimi.soul.entity;

import java.io.Serializable;

public class SoftInfo implements Serializable {
    private String downloadUrl;
    private String officialWebsite;
    private String softInfoVersion;
    private String userAgreement;

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getOfficialWebsite() {
        return this.officialWebsite;
    }

    public String getSoftInfoVersion() {
        return this.softInfoVersion;
    }

    public String getUserAgreement() {
        return this.userAgreement;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setOfficialWebsite(String str) {
        this.officialWebsite = str;
    }

    public void setSoftInfoVersion(String str) {
        this.softInfoVersion = str;
    }

    public void setUserAgreement(String str) {
        this.userAgreement = str;
    }
}
