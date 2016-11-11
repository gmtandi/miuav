package p000a;

import android.net.Uri;
import com.fimi.soul.module.setting.newhand.ae;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import org.p122a.p123a.C3004e;

/* renamed from: a.ak */
class ak implements Callable<Void> {
    final /* synthetic */ Uri f36a;
    final /* synthetic */ C0016p f37b;
    final /* synthetic */ C0016p f38c;
    final /* synthetic */ af f39d;

    ak(af afVar, Uri uri, C0016p c0016p, C0016p c0016p2) {
        this.f39d = afVar;
        this.f36a = uri;
        this.f37b = c0016p;
        this.f38c = c0016p2;
    }

    public Void m27a() {
        URL url = new URL(this.f36a.toString());
        URLConnection uRLConnection = null;
        while (url != null) {
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) openConnection).setInstanceFollowRedirects(true);
            }
            openConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
            openConnection.connect();
            if (openConnection instanceof HttpURLConnection) {
                URL url2;
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                if (httpURLConnection.getResponseCode() < ae.f9482j || httpURLConnection.getResponseCode() >= 400) {
                    Object obj = null;
                } else {
                    URL url3 = new URL(httpURLConnection.getHeaderField(C3004e.f14996H));
                    httpURLConnection.disconnect();
                    url2 = url3;
                }
                URLConnection uRLConnection2 = openConnection;
                url = url2;
                uRLConnection = uRLConnection2;
            } else {
                uRLConnection = openConnection;
                url = null;
            }
        }
        try {
            this.f37b.m70a(af.m20b(uRLConnection));
            this.f38c.m70a(uRLConnection.getContentType());
            return null;
        } finally {
            if (uRLConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) uRLConnection).disconnect();
            }
        }
    }

    public /* synthetic */ Object call() {
        return m27a();
    }
}
