package com.xiaomi.infra.galaxy.fds.android.auth;

import com.google.gson.Gson;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.StorageAccessToken;
import it.p074a.p075a.C2799f;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.C3004e;

public class OAuthCredential implements GalaxyFDSCredential {
    private static final String APP_ID = "appId";
    private static final String OAUTH_ACCESS_TOKEN = "oauthAccessToken";
    private static final String OAUTH_APPID = "oauthAppId";
    private static final String OAUTH_MAC_ALGORITHM = "oauthMacAlgorithm";
    private static final String OAUTH_MAC_KEY = "oauthMacKey";
    private static final String OAUTH_PROVIDER = "oauthProvider";
    private static final String STORAGE_ACCESS_TOKEN = "storageAccessToken";
    private final String HEADER_VALUE;
    private final String appId;
    private final StorageAccessToken storageAccessToken;

    public OAuthCredential(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.HEADER_VALUE = "OAuth";
        this.appId = str2;
        this.storageAccessToken = getStorageAccessToken(str, str2, str3, str4, str5, str6, str7);
    }

    private StorageAccessToken getStorageAccessToken(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            HttpClient defaultHttpClient = new DefaultHttpClient();
            Object httpGet = new HttpGet(str + "/?" + STORAGE_ACCESS_TOKEN + "&" + APP_ID + "=" + str2 + "&" + OAUTH_APPID + "=" + str3 + "&" + OAUTH_ACCESS_TOKEN + "=" + str4 + "&" + OAUTH_PROVIDER + "=" + str5 + "&" + OAUTH_MAC_ALGORITHM + "=" + str7 + "&" + OAUTH_MAC_KEY + "=" + str6);
            httpGet.setHeader(C3004e.f15022h, "OAuth");
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode() != C2799f.f14282t) {
                throw new GalaxyFDSClientException("Failed to get the storage access token from FDS server. URI:" + httpGet.getURI().toString() + ".Reason:" + execute.getStatusLine().toString());
            }
            InputStream content = execute.getEntity().getContent();
            StorageAccessToken storageAccessToken = (StorageAccessToken) new Gson().fromJson(new InputStreamReader(content), StorageAccessToken.class);
            content.close();
            return storageAccessToken;
        } catch (Throwable e) {
            throw new GalaxyFDSClientException("Failed to get the storage access token", e);
        }
    }

    public void addHeader(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader(C3004e.f15022h, "OAuth");
    }

    public String addParam(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (str.indexOf(63) == -1) {
            stringBuilder.append('?');
        } else {
            stringBuilder.append('&');
        }
        stringBuilder.append(APP_ID);
        stringBuilder.append(SignatureVisitor.INSTANCEOF);
        stringBuilder.append(this.appId);
        stringBuilder.append('&');
        stringBuilder.append(STORAGE_ACCESS_TOKEN);
        stringBuilder.append(SignatureVisitor.INSTANCEOF);
        stringBuilder.append(this.storageAccessToken.getToken());
        return stringBuilder.toString();
    }
}
