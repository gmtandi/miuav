package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.C0330s;
import com.amap.api.maps.AMapException;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

public abstract class aj<T, V> extends dj {
    protected T f2118a;
    protected int f2119b;
    protected String f2120c;
    protected Context f2121d;
    protected final int f2122e;
    protected final int f2123f;
    private int f2124j;

    public aj(Context context, T t) {
        this.f2119b = 1;
        this.f2120c = C2915a.f14760f;
        this.f2124j = 1;
        this.f2122e = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        this.f2123f = FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS;
        m3443a(context, t);
    }

    private V m3442a(byte[] bArr) {
        return m3446b(bArr);
    }

    private void m3443a(Context context, T t) {
        this.f2121d = context;
        this.f2118a = t;
    }

    private V m3444h() {
        int i = 0;
        V v = null;
        while (i < this.f2119b) {
            try {
                di a = di.m4024a(false);
                m3433a(bt.m3735a(this.f2121d));
                v = m3442a(a.m4032d(this));
                i = this.f2119b;
            } catch (Throwable e) {
                ce.m3829a(e, "ProtocalHandler", "getDataMayThrow AMapException");
                e.printStackTrace();
                i++;
                if (i >= this.f2119b) {
                    throw new AMapException(e.getErrorMessage());
                }
            } catch (Throwable e2) {
                ce.m3829a(e2, "ProtocalHandler", "getDataMayThrow AMapCoreException");
                e2.printStackTrace();
                i++;
                if (i < this.f2119b) {
                    try {
                        Thread.sleep((long) (this.f2124j * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER));
                    } catch (InterruptedException e3) {
                        ce.m3829a(e2, "ProtocalHandler", "getDataMayThrow InterruptedException");
                        e2.printStackTrace();
                        throw new AMapException(e2.getMessage());
                    }
                }
                m3449e();
                throw new AMapException(e2.m3644a());
            }
        }
        return v;
    }

    protected abstract V m3445b(String str);

    protected V m3446b(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Throwable e) {
            ce.m3829a(e, "ProtocalHandler", "loadData Exception");
            e.printStackTrace();
            str = null;
        }
        if (str == null || str.equals(C2915a.f14760f)) {
            return null;
        }
        bj.m3624a(str);
        return m3445b(str);
    }

    public Map<String, String> m3447c() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(C3004e.f15031q, C2989l.f14939a);
        hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
        hashMap.put(C3004e.f15013Y, C0330s.f2071d);
        hashMap.put("X-INFO", bn.m3665a(this.f2121d, bj.m3642e(), null, false));
        return hashMap;
    }

    public V m3448d() {
        return this.f2118a != null ? m3444h() : null;
    }

    protected V m3449e() {
        return null;
    }
}
