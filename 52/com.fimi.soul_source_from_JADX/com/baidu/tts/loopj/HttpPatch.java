package com.baidu.tts.loopj;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class HttpPatch extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "PATCH";

    public HttpPatch(String str) {
        setURI(URI.create(str));
    }

    public HttpPatch(URI uri) {
        setURI(uri);
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
