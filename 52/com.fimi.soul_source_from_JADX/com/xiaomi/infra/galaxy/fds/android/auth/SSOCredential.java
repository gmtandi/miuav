package com.xiaomi.infra.galaxy.fds.android.auth;

import com.xiaomi.infra.galaxy.fds.android.util.Args;
import org.apache.http.client.methods.HttpRequestBase;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.C3004e;

public class SSOCredential implements GalaxyFDSCredential {
    private final String APP_ID;
    private final String HEADER_VALUE;
    private final String SERVICE_TOKEN_PARAM;
    private final String appId;
    private final String serviceToken;

    @Deprecated
    public SSOCredential(String str) {
        this.HEADER_VALUE = "SSO";
        this.SERVICE_TOKEN_PARAM = "serviceToken";
        this.APP_ID = "appId";
        Args.notNull(str, "Service token");
        Args.notEmpty(str, "Service token");
        this.serviceToken = str;
        this.appId = null;
    }

    public SSOCredential(String str, String str2) {
        this.HEADER_VALUE = "SSO";
        this.SERVICE_TOKEN_PARAM = "serviceToken";
        this.APP_ID = "appId";
        Args.notNull(str, "Service token");
        Args.notEmpty(str, "Service token");
        Args.notNull(str2, "App id");
        Args.notEmpty(str2, "App id");
        this.serviceToken = str;
        this.appId = str2;
    }

    public void addHeader(HttpRequestBase httpRequestBase) {
        httpRequestBase.addHeader(C3004e.f15022h, "SSO");
    }

    public String addParam(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.indexOf(63) == -1) {
            stringBuilder.append('?');
        } else {
            stringBuilder.append('&');
        }
        stringBuilder.append("serviceToken");
        stringBuilder.append(SignatureVisitor.INSTANCEOF);
        stringBuilder.append(this.serviceToken);
        if (this.appId != null) {
            stringBuilder.append('&');
            stringBuilder.append("appId");
            stringBuilder.append(SignatureVisitor.INSTANCEOF);
            stringBuilder.append(this.appId);
        }
        return stringBuilder.toString();
    }
}
