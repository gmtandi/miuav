package com.xiaomi.infra.galaxy.fds.android.model;

import com.xiaomi.infra.galaxy.fds.android.util.Consts;
import org.p122a.p123a.C3004e;

public class PutObjectResult {
    private String accessKeyId;
    private String bucketName;
    private String cdnServiceBaseUri;
    private long expires;
    private String fdsServiceBaseUri;
    private String objectName;
    private String signature;

    public String getAbsolutePresignedUri() {
        return this.fdsServiceBaseUri + getRelativePresignedUri();
    }

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getCdnPresignedUri() {
        return this.cdnServiceBaseUri + getRelativePresignedUri();
    }

    public long getExpires() {
        return this.expires;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getRelativePresignedUri() {
        return "/" + this.bucketName + "/" + this.objectName + "?" + Consts.GALAXY_ACCESS_KEY_ID + "=" + this.accessKeyId + "&" + C3004e.f15038x + "=" + this.expires + "&" + Consts.SIGNATURE + "=" + this.signature;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCdnServiceBaseUri(String str) {
        this.cdnServiceBaseUri = str;
    }

    public void setExpires(long j) {
        this.expires = j;
    }

    public void setFdsServiceBaseUri(String str) {
        this.fdsServiceBaseUri = str;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }
}
