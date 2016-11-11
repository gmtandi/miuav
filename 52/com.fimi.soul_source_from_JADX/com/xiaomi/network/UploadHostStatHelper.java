package com.xiaomi.network;

import android.content.Context;
import android.util.Log;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.common.logger.thrift.C2479a;
import com.xiaomi.common.logger.thrift.mfs.C2483b;
import com.xiaomi.common.logger.thrift.mfs.C2485c;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3273h;
import org.p122a.p137b.p182b.C3254d;

public class UploadHostStatHelper {
    private static UploadHostStatHelper f13036e;
    private List<HttpRecordCallback> f13037a;
    private final Random f13038b;
    private Timer f13039c;
    private boolean f13040d;
    private Context f13041f;

    public interface HttpRecordCallback {
        List<C2483b> m14865a();

        double m14866b();
    }

    private UploadHostStatHelper(Context context) {
        this.f13037a = new ArrayList();
        this.f13038b = new Random();
        this.f13039c = new Timer("Upload Http Record Timer");
        this.f13040d = false;
        this.f13041f = null;
        this.f13041f = context.getApplicationContext();
    }

    public static synchronized UploadHostStatHelper m14867a() {
        UploadHostStatHelper uploadHostStatHelper;
        synchronized (UploadHostStatHelper.class) {
            uploadHostStatHelper = f13036e;
        }
        return uploadHostStatHelper;
    }

    private String m14868a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(m14875b(str));
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032X", new Object[]{bigInteger});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void m14870a(Context context) {
        synchronized (UploadHostStatHelper.class) {
            if (f13036e == null) {
                f13036e = new UploadHostStatHelper(context);
            }
        }
    }

    private void m14872a(String str, String str2) {
        List arrayList = new ArrayList();
        String valueOf = String.valueOf(System.nanoTime());
        String valueOf2 = String.valueOf(System.currentTimeMillis());
        arrayList.add(new BasicNameValuePair("n", valueOf));
        arrayList.add(new BasicNameValuePair("d", str2));
        arrayList.add(new BasicNameValuePair("t", valueOf2));
        arrayList.add(new BasicNameValuePair("s", m14868a(valueOf + str2 + valueOf2 + "56C6A520%$C99119A0&^229(!@2746C7")));
        Network.m14856a(this.f13041f, String.format("http://%1$s/diagnoses/v1/report", new Object[]{str}), arrayList);
    }

    private void m14873a(List<C2483b> list, double d) {
        for (C2483b c2483b : list) {
            C2478b c2485c = new C2485c();
            c2485c.m14224a("httpapi");
            c2485c.m14223a(c2483b);
            c2485c.m14222a(new C2479a());
            String str = new String(Base64Coder.m14828a(new C3273h(new C3254d()).m18103a(c2485c)));
            if (((double) this.f13038b.nextInt(C1873o.ak)) < 10000.0d * d) {
                try {
                    m14872a("f3.mi-stat.gslb.mi-idc.com", str);
                } catch (Throwable e) {
                    Log.e("uploadhoststat", null, e);
                } catch (Throwable e2) {
                    Log.e("uploadhoststat", null, e2);
                }
            }
        }
    }

    private byte[] m14875b(String str) {
        try {
            return str.getBytes(C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public void m14876a(HttpRecordCallback httpRecordCallback) {
        this.f13037a.add(httpRecordCallback);
    }

    public void m14877b() {
        if (!this.f13040d) {
            this.f13040d = true;
            this.f13039c.schedule(new C2624c(this), Util.MILLSECONDS_OF_MINUTE);
        }
    }
}
