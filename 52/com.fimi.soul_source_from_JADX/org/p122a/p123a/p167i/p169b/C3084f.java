package org.p122a.p123a.p167i.p169b;

import com.fimi.soul.module.setting.newhand.ae;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3237d;

@C2912b
/* renamed from: org.a.a.i.b.f */
public class C3084f implements ResponseHandler<String> {
    public String m17376a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        HttpEntity entity = httpResponse.getEntity();
        if (statusLine.getStatusCode() < ae.f9482j) {
            return entity == null ? null : C3237d.m17908f(entity);
        } else {
            C3237d.m17904b(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
    }

    public /* synthetic */ Object handleResponse(HttpResponse httpResponse) {
        return m17376a(httpResponse);
    }
}
