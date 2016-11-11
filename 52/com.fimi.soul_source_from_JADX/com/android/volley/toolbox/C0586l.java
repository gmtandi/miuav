package com.android.volley.toolbox;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/* renamed from: com.android.volley.toolbox.l */
public final class C0586l extends HttpEntityEnclosingRequestBase {
    public static final String f3630a = "PATCH";

    public C0586l(String str) {
        setURI(URI.create(str));
    }

    public C0586l(URI uri) {
        setURI(uri);
    }

    public String getMethod() {
        return f3630a;
    }
}
