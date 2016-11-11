package com.facebook.binaryresource;

import java.io.InputStream;

public interface BinaryResource {
    InputStream openStream();

    byte[] read();

    long size();
}
