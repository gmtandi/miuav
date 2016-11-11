package com.fimi.soul.utils;

import android.content.Context;
import com.fimi.kernel.p076b.p080d.C1142e;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class ae {
    private String f10017a;
    private HttpPost f10018b;
    private HttpResponse f10019c;
    private HttpParams f10020d;
    private HttpClient f10021e;

    public ae(String str, Context context) {
        this.f10017a = str;
        this.f10020d = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(this.f10020d, 15000);
        HttpConnectionParams.setSoTimeout(this.f10020d, 15000);
        this.f10021e = new DefaultHttpClient(this.f10020d);
    }

    public InputStream m12234a(List<NameValuePair> list) {
        InputStream inputStream = null;
        try {
            this.f10018b = new HttpPost(this.f10017a);
            if (list != null) {
                this.f10018b.setEntity(new UrlEncodedFormEntity(list, C1142e.f5201a));
            }
            this.f10019c = this.f10021e.execute(this.f10018b);
            if (C2799f.f14282t == this.f10019c.getStatusLine().getStatusCode()) {
                inputStream = this.f10019c.getEntity().getContent();
            }
        } catch (IOException e) {
        }
        return inputStream;
    }

    public String m12235b(List<NameValuePair> list) {
        try {
            this.f10018b = new HttpPost(this.f10017a);
            if (list != null) {
                this.f10018b.setEntity(new UrlEncodedFormEntity(list, C1142e.f5201a));
            }
            this.f10019c = this.f10021e.execute(this.f10018b);
            if (C2799f.f14282t != this.f10019c.getStatusLine().getStatusCode()) {
                return null;
            }
            String entityUtils = EntityUtils.toString(this.f10019c.getEntity());
            System.out.println("\u0221\ufffd\u00f7\ufffd\ufffd\ufffd\u05b5" + entityUtils);
            return entityUtils;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public int m12236c(List<NameValuePair> list) {
        int i = -1;
        try {
            this.f10018b = new HttpPost(this.f10017a);
            if (list != null) {
                this.f10018b.setEntity(new UrlEncodedFormEntity(list, C1142e.f5201a));
            }
            this.f10019c = this.f10021e.execute(this.f10018b);
            if (C2799f.f14282t == this.f10019c.getStatusLine().getStatusCode()) {
                i = Integer.parseInt(EntityUtils.toString(this.f10019c.getEntity()));
            }
        } catch (IOException e) {
        }
        return i;
    }

    public int m12237d(List<NameValuePair> list) {
        try {
            this.f10018b = new HttpPost(this.f10017a);
            if (list != null) {
                this.f10018b.setEntity(new UrlEncodedFormEntity(list, C1142e.f5201a));
            }
            this.f10019c = this.f10021e.execute(this.f10018b);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
