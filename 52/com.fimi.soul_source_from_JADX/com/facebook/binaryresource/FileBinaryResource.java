package com.facebook.binaryresource;

import com.facebook.common.internal.Files;
import com.facebook.common.internal.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileBinaryResource implements BinaryResource {
    private final File mFile;

    private FileBinaryResource(File file) {
        this.mFile = (File) Preconditions.checkNotNull(file);
    }

    public static FileBinaryResource createOrNull(File file) {
        return file != null ? new FileBinaryResource(file) : null;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FileBinaryResource)) {
            return false;
        }
        return this.mFile.equals(((FileBinaryResource) obj).mFile);
    }

    public File getFile() {
        return this.mFile;
    }

    public int hashCode() {
        return this.mFile.hashCode();
    }

    public InputStream openStream() {
        return new FileInputStream(this.mFile);
    }

    public byte[] read() {
        return Files.toByteArray(this.mFile);
    }

    public long size() {
        return this.mFile.length();
    }
}
