package com.fimi.kernel.p084e;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.e.ak */
public class ak {
    public static final int f5301a = 0;
    public static int f5302b;
    public static int f5303c;
    private static Context f5304d;
    private static Toast f5305e;
    private static Handler f5306f;
    private static Runnable f5307g;
    private static Handler f5308h;

    static {
        f5304d = null;
        f5302b = 1;
        f5303c = 0;
        f5306f = null;
        f5307g = new al();
        f5308h = new am();
    }

    public static void m8082a(Context context, int i) {
        f5304d = context;
        Toast.makeText(context, C2915a.f14760f + context.getResources().getText(i), 0).show();
    }

    public static void m8083a(Context context, int i, int i2) {
        ak.m8085a(context, context.getResources().getString(i), i2);
    }

    public static void m8084a(Context context, String str) {
        f5304d = context;
        if (!C1184w.m8281b(str)) {
            Toast.makeText(context, str, 0).show();
        }
    }

    public static void m8085a(Context context, String str, int i) {
        if (context != null) {
            f5306f = new Handler(context.getMainLooper());
            f5306f.removeCallbacks(f5307g);
            if (f5305e != null) {
                f5305e.setText(str);
            } else if (i == 0) {
                f5305e = Toast.makeText(context, str, 0);
            } else {
                f5305e = Toast.makeText(context, str, 1);
            }
            if (1 == i) {
                f5306f.postDelayed(f5307g, 3500);
            } else if (i == 0) {
                f5306f.postDelayed(f5307g, 2000);
            } else {
                f5306f.postDelayed(f5307g, (long) i);
            }
            f5305e.show();
        }
    }

    public static void m8087b(Context context, int i) {
        f5304d = context;
        Message obtainMessage = f5308h.obtainMessage(0);
        Bundle bundle = new Bundle();
        if (context != null && bundle != null) {
            bundle.putString("TEXT", context.getResources().getString(i));
            obtainMessage.setData(bundle);
            f5308h.sendMessage(obtainMessage);
        }
    }

    public static void m8088b(Context context, String str) {
        f5304d = context;
        Message obtainMessage = f5308h.obtainMessage(0);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", str);
        obtainMessage.setData(bundle);
        f5308h.sendMessage(obtainMessage);
    }
}
