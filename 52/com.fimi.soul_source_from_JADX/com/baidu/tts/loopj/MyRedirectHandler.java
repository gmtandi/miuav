package com.baidu.tts.loopj;

import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.p159n.C2967c;

class MyRedirectHandler extends DefaultRedirectHandler {
    private static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
    private final boolean enableRedirects;

    public MyRedirectHandler(boolean z) {
        this.enableRedirects = z;
    }

    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        Header firstHeader = httpResponse.getFirstHeader("location");
        if (firstHeader == null) {
            throw new ProtocolException("Received redirect response " + httpResponse.getStatusLine() + " but no location header");
        }
        String replaceAll = firstHeader.getValue().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
        try {
            URI uri;
            URI uri2 = new URI(replaceAll);
            HttpParams params = httpResponse.getParams();
            if (uri2.isAbsolute()) {
                uri = uri2;
            } else if (params.isParameterTrue("http.protocol.reject-relative-redirect")) {
                throw new ProtocolException("Relative redirect location '" + uri2 + "' not allowed");
            } else {
                HttpHost httpHost = (HttpHost) httpContext.getAttribute(C2967c.f14884q);
                if (httpHost == null) {
                    throw new IllegalStateException("Target host not available in the HTTP context");
                }
                try {
                    uri = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest) httpContext.getAttribute(C2967c.f14882o)).getRequestLine().getUri()), httpHost, true), uri2);
                } catch (Throwable e) {
                    throw new ProtocolException(e.getMessage(), e);
                }
            }
            if (params.isParameterFalse("http.protocol.allow-circular-redirects")) {
                RedirectLocations redirectLocations = (RedirectLocations) httpContext.getAttribute(REDIRECT_LOCATIONS);
                if (redirectLocations == null) {
                    redirectLocations = new RedirectLocations();
                    httpContext.setAttribute(REDIRECT_LOCATIONS, redirectLocations);
                }
                if (uri.getFragment() != null) {
                    try {
                        uri2 = URIUtils.rewriteURI(uri, new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()), true);
                    } catch (Throwable e2) {
                        throw new ProtocolException(e2.getMessage(), e2);
                    }
                }
                uri2 = uri;
                if (redirectLocations.contains(uri2)) {
                    throw new CircularRedirectException("Circular redirect to '" + uri2 + "'");
                }
                redirectLocations.add(uri2);
            }
            return uri;
        } catch (Throwable e22) {
            throw new ProtocolException("Invalid redirect URI: " + replaceAll, e22);
        }
    }

    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        if (!this.enableRedirects) {
            return false;
        }
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        switch (httpResponse.getStatusLine().getStatusCode()) {
            case 301:
            case 302:
            case 303:
            case 307:
                return true;
            default:
                return false;
        }
    }
}
