package com.amap.api.mapcore;

import android.content.Context;
import android.view.View;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class bo extends View {
    CopyOnWriteArrayList<ap> f1877a;
    C0307a f1878b;
    CopyOnWriteArrayList<Integer> f1879c;
    bn f1880d;
    private ab f1881e;

    /* renamed from: com.amap.api.mapcore.bo.1 */
    class C03061 extends UrlTileProvider {
        final /* synthetic */ bo f1876a;

        C03061(bo boVar, int i, int i2) {
            this.f1876a = boVar;
            super(i, i2);
        }

        public URL getTileUrl(int i, int i2, int i3) {
            try {
                return new URL(String.format("http://grid.amap.com/grid/%d/%d/%d?dpiType=webrd&lang=zh_cn&pack=%s&version=3.3.1", new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), C0330s.f2070c}));
            } catch (Throwable th) {
                return null;
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.bo.a */
    class C0307a implements Serializable, Comparator<Object> {
        C0307a() {
        }

        public int compare(Object obj, Object obj2) {
            ap apVar = (ap) obj;
            ap apVar2 = (ap) obj2;
            if (!(apVar == null || apVar2 == null)) {
                try {
                    if (apVar.m2717d() > apVar2.m2717d()) {
                        return 1;
                    }
                    if (apVar.m2717d() < apVar2.m2717d()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ce.m3829a(th, "TileOverlayView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public bo(Context context) {
        super(context);
        this.f1877a = new CopyOnWriteArrayList();
        this.f1878b = new C0307a();
        this.f1879c = new CopyOnWriteArrayList();
        this.f1880d = null;
    }

    public bo(Context context, ab abVar) {
        super(context);
        this.f1877a = new CopyOnWriteArrayList();
        this.f1878b = new C0307a();
        this.f1879c = new CopyOnWriteArrayList();
        this.f1880d = null;
        this.f1881e = abVar;
        this.f1880d = new bn(new TileOverlayOptions().tileProvider(new C03061(this, Opcodes.ACC_NATIVE, Opcodes.ACC_NATIVE)), this, true);
    }

    ab m3174a() {
        return this.f1881e;
    }

    public void m3175a(ap apVar) {
        m3180b(apVar);
        this.f1877a.add(apVar);
        m3181c();
    }

    public void m3176a(GL10 gl10) {
        try {
            Iterator it = this.f1879c.iterator();
            while (it.hasNext()) {
                bj.m3625a(gl10, ((Integer) it.next()).intValue());
            }
            this.f1879c.clear();
            this.f1880d.m3160a(gl10);
            it = this.f1877a.iterator();
            while (it.hasNext()) {
                ap apVar = (ap) it.next();
                if (apVar.m2718e()) {
                    apVar.m2710a(gl10);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void m3177a(boolean z) {
        this.f1880d.m3164b(z);
        Iterator it = this.f1877a.iterator();
        while (it.hasNext()) {
            ap apVar = (ap) it.next();
            if (apVar != null && apVar.m2718e()) {
                apVar.m2714b(z);
            }
        }
    }

    public void m3178b() {
        Iterator it = this.f1877a.iterator();
        while (it.hasNext()) {
            ap apVar = (ap) it.next();
            if (apVar != null) {
                apVar.m2708a();
            }
        }
        this.f1877a.clear();
    }

    public void m3179b(boolean z) {
        this.f1880d.m3166c(z);
        Iterator it = this.f1877a.iterator();
        while (it.hasNext()) {
            ap apVar = (ap) it.next();
            if (apVar != null) {
                apVar.m2716c(z);
            }
        }
    }

    public boolean m3180b(ap apVar) {
        return this.f1877a.remove(apVar);
    }

    void m3181c() {
        Object[] toArray = this.f1877a.toArray();
        Arrays.sort(toArray, this.f1878b);
        this.f1877a.clear();
        for (Object obj : toArray) {
            this.f1877a.add((ap) obj);
        }
    }

    public void m3182d() {
        this.f1880d.m3170g();
        Iterator it = this.f1877a.iterator();
        while (it.hasNext()) {
            ap apVar = (ap) it.next();
            if (apVar != null) {
                apVar.m2720g();
            }
        }
    }

    public void m3183e() {
        this.f1880d.m3158a();
        this.f1880d = null;
    }

    public void m3184f() {
        this.f1880d.m3171h();
        Iterator it = this.f1877a.iterator();
        while (it.hasNext()) {
            ap apVar = (ap) it.next();
            if (apVar != null) {
                apVar.m2721h();
            }
        }
    }
}
