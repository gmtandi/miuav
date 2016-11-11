package com.p016a;

import com.amap.api.services.core.AMapException;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.bk */
public class bk {
    private static bk f631a;

    public static bk m1169a() {
        if (f631a == null) {
            f631a = new bk();
        }
        return f631a;
    }

    public HttpURLConnection m1170a(bp bpVar, boolean z) {
        fm e;
        try {
            m1174c(bpVar);
            Proxy proxy = bpVar.f552c == null ? null : bpVar.f552c;
            HttpURLConnection a = (z ? new bn(bpVar.f550a, bpVar.f551b, proxy, true) : new bn(bpVar.f550a, bpVar.f551b, proxy, false)).m1181a(bpVar.m1040e(), bpVar.m1033a(), true);
            byte[] f = bpVar.m1041f();
            if (f != null && f.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(f);
                dataOutputStream.close();
            }
            a.connect();
            return a;
        } catch (fm e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new fm(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m1171a(bp bpVar) {
        fm e;
        try {
            bq b = m1172b(bpVar, true);
            return b != null ? b.f644a : null;
        } catch (fm e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new fm(AMapException.ERROR_UNKNOWN);
        }
    }

    protected bq m1172b(bp bpVar, boolean z) {
        fm e;
        try {
            m1174c(bpVar);
            return new bn(bpVar.f550a, bpVar.f551b, bpVar.f552c == null ? null : bpVar.f552c, z).m1180a(bpVar.m1040e(), bpVar.m1033a(), bpVar.m1041f());
        } catch (fm e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new fm(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m1173b(bp bpVar) {
        fm e;
        try {
            bq b = m1172b(bpVar, false);
            return b != null ? b.f644a : null;
        } catch (fm e2) {
            throw e2;
        } catch (Throwable th) {
            C0247g.m1917a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new fm(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void m1174c(bp bpVar) {
        if (bpVar == null) {
            throw new fm("requeust is null");
        } else if (bpVar.m1038c() == null || C2915a.f14760f.equals(bpVar.m1038c())) {
            throw new fm("request url is empty");
        }
    }
}
