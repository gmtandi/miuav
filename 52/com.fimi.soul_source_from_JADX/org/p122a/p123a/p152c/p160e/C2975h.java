package org.p122a.p123a.p152c.p160e;

import com.baidu.tts.loopj.AsyncHttpClient;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p152c.p155b.C2930b;
import org.p122a.p123a.p152c.p155b.C2935g;

@C2912b
/* renamed from: org.a.a.c.e.h */
public class C2975h implements HttpResponseInterceptor {
    public static final String f14905a = "http.client.response.uncompressed";

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        int i = 1;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && entity.getContentLength() != 0) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                HeaderElement[] elements = contentEncoding.getElements();
                if (0 < elements.length) {
                    HeaderElement headerElement = elements[0];
                    String toLowerCase = headerElement.getName().toLowerCase(Locale.ENGLISH);
                    if (AsyncHttpClient.ENCODING_GZIP.equals(toLowerCase) || "x-gzip".equals(toLowerCase)) {
                        httpResponse.setEntity(new C2935g(httpResponse.getEntity()));
                    } else if ("deflate".equals(toLowerCase)) {
                        httpResponse.setEntity(new C2930b(httpResponse.getEntity()));
                    } else if (!"identity".equals(toLowerCase)) {
                        throw new HttpException("Unsupported Content-Coding: " + headerElement.getName());
                    } else {
                        return;
                    }
                }
                i = 0;
                if (i != 0) {
                    httpResponse.removeHeaders(C3004e.f15027m);
                    httpResponse.removeHeaders(C3004e.f15025k);
                    httpResponse.removeHeaders(C3004e.f15029o);
                }
            }
        }
    }
}
