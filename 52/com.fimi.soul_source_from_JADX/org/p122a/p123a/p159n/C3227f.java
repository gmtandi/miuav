package org.p122a.p123a.p159n;

import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestInterceptorList;
import org.apache.http.protocol.HttpResponseInterceptorList;
import org.p122a.p123a.p150a.C2914d;

@C2914d
/* renamed from: org.a.a.n.f */
public final class C3227f implements HttpProcessor {
    private final HttpRequestInterceptor[] f15697a;
    private final HttpResponseInterceptor[] f15698b;

    public C3227f(List<HttpRequestInterceptor> list, List<HttpResponseInterceptor> list2) {
        if (list != null) {
            this.f15697a = (HttpRequestInterceptor[]) list.toArray(new HttpRequestInterceptor[list.size()]);
        } else {
            this.f15697a = new HttpRequestInterceptor[0];
        }
        if (list2 != null) {
            this.f15698b = (HttpResponseInterceptor[]) list2.toArray(new HttpResponseInterceptor[list2.size()]);
        } else {
            this.f15698b = new HttpResponseInterceptor[0];
        }
    }

    @Deprecated
    public C3227f(HttpRequestInterceptorList httpRequestInterceptorList, HttpResponseInterceptorList httpResponseInterceptorList) {
        int i;
        int i2 = 0;
        if (httpRequestInterceptorList != null) {
            int requestInterceptorCount = httpRequestInterceptorList.getRequestInterceptorCount();
            this.f15697a = new HttpRequestInterceptor[requestInterceptorCount];
            for (i = 0; i < requestInterceptorCount; i++) {
                this.f15697a[i] = httpRequestInterceptorList.getRequestInterceptor(i);
            }
        } else {
            this.f15697a = new HttpRequestInterceptor[0];
        }
        if (httpResponseInterceptorList != null) {
            i = httpResponseInterceptorList.getResponseInterceptorCount();
            this.f15698b = new HttpResponseInterceptor[i];
            while (i2 < i) {
                this.f15698b[i2] = httpResponseInterceptorList.getResponseInterceptor(i2);
                i2++;
            }
            return;
        }
        this.f15698b = new HttpResponseInterceptor[0];
    }

    public C3227f(HttpRequestInterceptor... httpRequestInterceptorArr) {
        this(httpRequestInterceptorArr, null);
    }

    public C3227f(HttpRequestInterceptor[] httpRequestInterceptorArr, HttpResponseInterceptor[] httpResponseInterceptorArr) {
        if (httpRequestInterceptorArr != null) {
            int length = httpRequestInterceptorArr.length;
            this.f15697a = new HttpRequestInterceptor[length];
            System.arraycopy(httpRequestInterceptorArr, 0, this.f15697a, 0, length);
        } else {
            this.f15697a = new HttpRequestInterceptor[0];
        }
        if (httpResponseInterceptorArr != null) {
            length = httpResponseInterceptorArr.length;
            this.f15698b = new HttpResponseInterceptor[length];
            System.arraycopy(httpResponseInterceptorArr, 0, this.f15698b, 0, length);
            return;
        }
        this.f15698b = new HttpResponseInterceptor[0];
    }

    public C3227f(HttpResponseInterceptor... httpResponseInterceptorArr) {
        this(null, httpResponseInterceptorArr);
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        for (HttpRequestInterceptor process : this.f15697a) {
            process.process(httpRequest, httpContext);
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        for (HttpResponseInterceptor process : this.f15698b) {
            process.process(httpResponse, httpContext);
        }
    }
}
