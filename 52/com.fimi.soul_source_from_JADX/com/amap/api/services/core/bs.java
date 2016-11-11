package com.amap.api.services.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.apache.http.HttpEntity;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class bs {
    private static bs f3132a;
    private av f3133b;
    private Handler f3134c;

    /* renamed from: com.amap.api.services.core.bs.1 */
    class C04681 extends ax {
        final /* synthetic */ bt f3129b;
        final /* synthetic */ bu f3130c;
        final /* synthetic */ bs f3131d;

        public void m4738a() {
            try {
                this.f3131d.m4745a(this.f3131d.m4747b(this.f3129b, false), this.f3130c);
            } catch (C0495v e) {
                this.f3131d.m4746a(e, this.f3130c);
            }
        }
    }

    /* renamed from: com.amap.api.services.core.bs.a */
    class C0469a extends Handler {
        private C0469a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                        ((bx) message.obj).f3138b.m4751a();
                        return;
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        bx bxVar = (bx) message.obj;
                        bxVar.f3138b.m4752a(bxVar.f3137a);
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

    private bs(boolean z, int i) {
        if (z) {
            try {
                this.f3133b = av.m4575a(i);
            } catch (Throwable th) {
                ay.m4590a(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.f3134c = new C0469a(null);
        } else {
            this.f3134c = new C0469a();
        }
    }

    public static bs m4739a(boolean z) {
        return m4740a(z, 5);
    }

    private static synchronized bs m4740a(boolean z, int i) {
        bs bsVar;
        synchronized (bs.class) {
            try {
                if (f3132a == null) {
                    f3132a = new bs(z, i);
                } else if (z) {
                    if (f3132a.f3133b == null) {
                        f3132a.f3133b = av.m4575a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            bsVar = f3132a;
        }
        return bsVar;
    }

    private bv m4742a(bt btVar, boolean z) {
        C0495v e;
        HttpEntity e2 = btVar.m4448e();
        byte[] e_ = btVar.e_();
        try {
            m4748c(btVar);
            bq bqVar = new bq(btVar.f2957e, btVar.f2958f, btVar.f2959g == null ? null : new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(btVar.f2959g.getHostName(), btVar.f2959g.getPort())), z);
            return (e2 == null && e_ == null) ? bqVar.m4737b(btVar.m4445b(), btVar.d_(), btVar.c_()) : e_ != null ? bqVar.m4735a(btVar.m4445b(), btVar.d_(), btVar.c_(), e_) : bqVar.m4734a(btVar.m4445b(), btVar.d_(), btVar.c_(), e2);
        } catch (C0495v e3) {
            throw e3;
        } catch (Throwable th) {
            th.printStackTrace();
            e3 = new C0495v(AMapException.ERROR_UNKNOWN);
        }
    }

    private void m4745a(bv bvVar, bu buVar) {
        buVar.m4753a(bvVar.f3136b, bvVar.f3135a);
        bx bxVar = new bx();
        bxVar.f3138b = buVar;
        Message obtain = Message.obtain();
        obtain.obj = bxVar;
        obtain.what = 0;
        this.f3134c.sendMessage(obtain);
    }

    private void m4746a(C0495v c0495v, bu buVar) {
        bx bxVar = new bx();
        bxVar.f3137a = c0495v;
        bxVar.f3138b = buVar;
        Message obtain = Message.obtain();
        obtain.obj = bxVar;
        obtain.what = 1;
        this.f3134c.sendMessage(obtain);
    }

    private bv m4747b(bt btVar, boolean z) {
        C0495v e;
        try {
            m4748c(btVar);
            return new bq(btVar.f2957e, btVar.f2958f, btVar.f2959g == null ? null : new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(btVar.f2959g.getHostName(), btVar.f2959g.getPort())), z).m4733a(btVar.m4445b(), btVar.d_(), btVar.c_());
        } catch (C0495v e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new C0495v(AMapException.ERROR_UNKNOWN);
        }
    }

    private void m4748c(bt btVar) {
        if (btVar == null) {
            throw new C0495v("requeust is null");
        } else if (btVar.m4445b() == null || C2915a.f14760f.equals(btVar.m4445b())) {
            throw new C0495v("request url is empty");
        }
    }

    public byte[] m4749a(bt btVar) {
        C0495v e;
        try {
            bv a = m4742a(btVar, false);
            return a != null ? a.f3135a : null;
        } catch (C0495v e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C0495v(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] m4750b(bt btVar) {
        C0495v e;
        try {
            bv a = m4742a(btVar, true);
            return a != null ? a.f3135a : null;
        } catch (C0495v e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new C0495v(AMapException.ERROR_UNKNOWN);
        }
    }
}
