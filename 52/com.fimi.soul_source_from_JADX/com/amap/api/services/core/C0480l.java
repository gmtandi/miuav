package com.amap.api.services.core;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Type;

/* renamed from: com.amap.api.services.core.l */
public class C0480l {
    public static ad f3144a;
    private static C0480l f3145b;
    private static Context f3146c;
    private C0479a f3147d;
    private HandlerThread f3148e;

    /* renamed from: com.amap.api.services.core.l.a */
    class C0479a extends Handler {
        String f3142a;
        final /* synthetic */ C0480l f3143b;

        public C0479a(C0480l c0480l, Looper looper) {
            this.f3143b = c0480l;
            super(looper);
            this.f3142a = "handleMessage";
        }

        public void handleMessage(Message message) {
            if (message != null) {
                switch (message.what) {
                    case Type.BYTE /*3*/:
                        try {
                            C0483o c0483o = (C0483o) message.obj;
                            if (c0483o == null) {
                                c0483o = new C0483o(false, false);
                            }
                            ay.m4586a(C0480l.f3146c, C0470c.m4754a(c0483o.m4849a()));
                            C0480l.f3144a = C0470c.m4754a(c0483o.m4849a());
                        } catch (Throwable th) {
                            C0471d.m4762a(th, "ManifestConfig", this.f3142a);
                        }
                    default:
                }
            }
        }
    }

    private C0480l(Context context) {
        this.f3148e = new C0481m(this, "manifestThread");
        f3146c = context;
        f3144a = C0470c.m4754a(false);
        try {
            this.f3148e.start();
            this.f3147d = new C0479a(this, Looper.getMainLooper());
        } catch (Throwable th) {
            C0471d.m4762a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    public static C0480l m4841a(Context context) {
        if (f3145b == null) {
            f3145b = new C0480l(context);
        }
        return f3145b;
    }
}
