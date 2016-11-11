package com.baidu.tts.loopj;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class HttpGet extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "GET";

    public HttpGet(String str) {
        setURI(URI.create(str));
    }

    public HttpGet(URI uri) {
        setURI(uri);
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
