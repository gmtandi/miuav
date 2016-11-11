package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.o */
public class C0397o {
    private Context f2514a;

    public C0397o(Context context) {
        this.f2514a = context;
    }

    private boolean m4175a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List<String> b = C0405x.m4223a(context).m4234b(str);
        String a = bj.m3615a(context);
        for (String str2 : b) {
            try {
                File file = new File(a + "vmap/" + str2);
                if (file.exists() && !af.m3419b(file)) {
                    af.m3415a("deleteDownload delete some thing wrong!");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        try {
            af.m3422c(a + "vmap/");
            af.m3416a(str, context);
            return true;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            return false;
        }
    }

    private boolean m4176b(C0385g c0385g) {
        if (c0385g == null) {
            return false;
        }
        boolean a = m4175a(c0385g.getAdcode(), this.f2514a);
        if (a) {
            c0385g.m4081h();
            return a;
        }
        c0385g.m4080g();
        return false;
    }

    public void m4177a(C0385g c0385g) {
        m4176b(c0385g);
    }
}
