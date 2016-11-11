package com.baidu.tts.loopj;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p152c.p160e.C2968a;
import org.p122a.p123a.p159n.C2967c;

public class PreemptiveAuthorizationHttpRequestInterceptor implements HttpRequestInterceptor {
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        AuthState authState = (AuthState) httpContext.getAttribute(C2968a.f14895i);
        CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.getAttribute(C2968a.f14893g);
        HttpHost httpHost = (HttpHost) httpContext.getAttribute(C2967c.f14884q);
        if (authState.getAuthScheme() == null) {
            Credentials credentials = credentialsProvider.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()));
            if (credentials != null) {
                authState.setAuthScheme(new BasicScheme());
                authState.setCredentials(credentials);
            }
        }
    }
}
