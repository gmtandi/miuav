package com.fimi.soul.biz.p101h;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.utils.be;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.biz.h.a */
public class C1346a {
    final List<LatLng> f5990a;
    private AsyncTask f5991b;
    private SQLiteDatabase f5992c;
    private AMap f5993d;
    private List<Circle> f5994e;
    private CircleOptions f5995f;
    private ExecutorService f5996g;

    public C1346a(Context context, AMap aMap) {
        this.f5990a = new ArrayList();
        this.f5993d = aMap;
        this.f5994e = C1348c.m9015a().m9016b();
        m9008a(context);
        this.f5996g = ah.m8074a();
        m9011c();
    }

    private void m9008a(Context context) {
        try {
            File file = new File(be.m12397o(context));
            if (file.exists()) {
                this.f5992c = SQLiteDatabase.openDatabase(file.getPath(), null, 0);
            }
        } catch (Exception e) {
        }
    }

    private void m9011c() {
        this.f5995f = new CircleOptions().radius(8000.0d).strokeColor(Color.argb(Opcodes.LAND, Util.MASK_8BIT, 54, 0)).strokeWidth(2.0f).fillColor(Color.argb(51, Util.MASK_8BIT, 54, 0)).zIndex(100.0f);
    }

    public void m9013a() {
        this.f5996g.execute(new C1347b(this));
    }

    public void m9014b() {
        if (this.f5992c != null && this.f5992c.isOpen()) {
            this.f5992c.close();
        }
    }
}
