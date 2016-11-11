package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import org.codehaus.jackson.smile.SmileConstants;

public class di extends dd {
    private static di f2431a;
    private dn f2432b;
    private Handler f2433c;

    /* renamed from: com.amap.api.mapcore.util.di.1 */
    class C03771 extends dp {
        final /* synthetic */ dj f2428a;
        final /* synthetic */ dk f2429b;
        final /* synthetic */ di f2430c;

        public void m4023a() {
            try {
                this.f2430c.m4029a(this.f2430c.m4030b(this.f2428a, false), this.f2429b);
            } catch (bk e) {
                this.f2430c.m4026a(e, this.f2429b);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.util.di.a */
    class C0378a extends Handler {
        private C0378a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        ((dm) message.obj).f2437b.m4033a();
                        return;
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        dm dmVar = (dm) message.obj;
                        dmVar.f2437b.m4034a(dmVar.f2436a);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            th.printStackTrace();
        }
    }

    private di(boolean z, int i) {
        if (z) {
            try {
                this.f2432b = dn.m4036a(i);
            } catch (Throwable th) {
                ce.m3829a(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f2433c = new C0378a(null);
        } else {
            this.f2433c = new C0378a();
        }
    }

    public static di m4024a(boolean z) {
        return m4025a(z, 5);
    }

    private static synchronized di m4025a(boolean z, int i) {
        di diVar;
        synchronized (di.class) {
            try {
                if (f2431a == null) {
                    f2431a = new di(z, i);
                } else if (z) {
                    if (f2431a.f2432b == null) {
                        f2431a.f2432b = dn.m4036a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            diVar = f2431a;
        }
        return diVar;
    }

    private void m4026a(bk bkVar, dk dkVar) {
        dm dmVar = new dm();
        dmVar.f2436a = bkVar;
        dmVar.f2437b = dkVar;
        Message obtain = Message.obtain();
        obtain.obj = dmVar;
        obtain.what = 1;
        this.f2433c.sendMessage(obtain);
    }

    private void m4029a(dl dlVar, dk dkVar) {
        dkVar.m4035a(dlVar.f2435b, dlVar.f2434a);
        dm dmVar = new dm();
        dmVar.f2437b = dkVar;
        Message obtain = Message.obtain();
        obtain.obj = dmVar;
        obtain.what = 0;
        this.f2433c.sendMessage(obtain);
    }

    public dl m4030b(dj djVar, boolean z) {
        bk e;
        try {
            m4007c(djVar);
            return new df(djVar.f2114g, djVar.f2115h, djVar.f2116i == null ? null : djVar.f2116i, z).m4014a(djVar.m3431a(), djVar.m3436c(), djVar.m3434b());
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m4031b(dj djVar) {
        bk e;
        try {
            dl a = m4004a(djVar, false);
            return a != null ? a.f2434a : null;
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            ce.m3827a().m3835b(th, "NetManager", "makeSyncPostRequest");
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m4032d(dj djVar) {
        bk e;
        try {
            dl b = m4030b(djVar, false);
            return b != null ? b.f2434a : null;
        } catch (bk e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new bk(AMapException.ERROR_UNKNOWN);
        }
    }
}
