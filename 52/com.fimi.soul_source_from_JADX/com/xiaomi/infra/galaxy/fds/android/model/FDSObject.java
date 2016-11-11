package com.xiaomi.infra.galaxy.fds.android.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class FDSObject implements Closeable {
    private final String bucketName;
    private ObjectMetadata metadata;
    private InputStream objectContent;
    private final String objectName;

    public FDSObject(String str, String str2) {
        this.bucketName = str;
        this.objectName = str2;
    }

    public void close() {
        if (this.objectContent != null) {
            try {
                this.objectContent.close();
            } catch (IOException e) {
            }
        }
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public InputStream getObjectContent() {
        return this.objectContent;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.metadata;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectContent(InputStream inputStream) {
        this.objectContent = inputStream;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }
}
