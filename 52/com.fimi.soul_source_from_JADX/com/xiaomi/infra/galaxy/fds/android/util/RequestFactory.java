package com.xiaomi.infra.galaxy.fds.android.util;

import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.model.HttpMethod;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;

public class RequestFactory {

    /* renamed from: com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.1 */
    /* synthetic */ class C24961 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod;

        static {
            $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod = new int[HttpMethod.values().length];
            try {
                $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[HttpMethod.POST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[HttpMethod.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[HttpMethod.HEAD.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static HttpUriRequest createRequest(String str, GalaxyFDSCredential galaxyFDSCredential, HttpMethod httpMethod, Map<String, String> map) {
        HttpUriRequest httpUriRequest;
        String addParam = galaxyFDSCredential.addParam(str);
        Object httpGet;
        switch (C24961.$SwitchMap$com$xiaomi$infra$galaxy$fds$android$model$HttpMethod[httpMethod.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                httpGet = new HttpGet(addParam);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                httpGet = new HttpPut(addParam);
                break;
            case Type.BYTE /*3*/:
                httpGet = new HttpPost(addParam);
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                httpGet = new HttpDelete(addParam);
                break;
            case Type.INT /*5*/:
                httpGet = new HttpHead(addParam);
                break;
            default:
                httpUriRequest = null;
                break;
        }
        if (httpUriRequest != null) {
            if (map != null) {
                map.remove(C3004e.f15027m);
                for (Entry entry : map.entrySet()) {
                    httpUriRequest.addHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
            httpUriRequest.addHeader(C3004e.f15032r, Util.formatDateString(new Date()));
            galaxyFDSCredential.addHeader(httpUriRequest);
        }
        return httpUriRequest;
    }
}
