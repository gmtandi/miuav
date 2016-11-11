package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.os.Handler;
import com.amap.api.mapcore.util.C0385g;
import com.amap.api.mapcore.util.C0391i;
import com.amap.api.mapcore.util.C0391i.C0389a;
import com.amap.api.mapcore.util.C0395m;
import com.amap.api.mapcore.util.bj;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import java.util.ArrayList;
import java.util.Iterator;
import org.p122a.p123a.C2915a;

public final class OfflineMapManager {
    C0395m f2823a;
    C0391i f2824b;
    private Context f2825c;
    private AMap f2826d;
    private OfflineMapDownloadListener f2827e;
    private Handler f2828f;
    private Handler f2829g;

    /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.1 */
    class C04361 implements C0389a {
        final /* synthetic */ OfflineMapManager f2818a;

        /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.1.1 */
        class C04331 implements Runnable {
            final /* synthetic */ C0385g f2812a;
            final /* synthetic */ C04361 f2813b;

            C04331(C04361 c04361, C0385g c0385g) {
                this.f2813b = c04361;
                this.f2812a = c0385g;
            }

            public void run() {
                this.f2813b.f2818a.f2827e.onDownload(this.f2812a.m4076c().m3452b(), this.f2812a.getcompleteCode(), this.f2812a.getCity());
            }
        }

        /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.1.2 */
        class C04342 implements Runnable {
            final /* synthetic */ C0385g f2814a;
            final /* synthetic */ C04361 f2815b;

            C04342(C04361 c04361, C0385g c0385g) {
                this.f2815b = c04361;
                this.f2814a = c0385g;
            }

            public void run() {
                if (this.f2814a.m4076c().equals(this.f2814a.f2471g) || this.f2814a.m4076c().equals(this.f2814a.f2465a)) {
                    this.f2815b.f2818a.f2827e.onCheckUpdate(true, this.f2814a.getCity());
                } else {
                    this.f2815b.f2818a.f2827e.onCheckUpdate(false, this.f2814a.getCity());
                }
            }
        }

        /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.1.3 */
        class C04353 implements Runnable {
            final /* synthetic */ C0385g f2816a;
            final /* synthetic */ C04361 f2817b;

            C04353(C04361 c04361, C0385g c0385g) {
                this.f2817b = c04361;
                this.f2816a = c0385g;
            }

            public void run() {
                if (this.f2816a.m4076c().equals(this.f2816a.f2465a)) {
                    this.f2817b.f2818a.f2827e.onRemove(true, this.f2816a.getCity(), C2915a.f14760f);
                } else {
                    this.f2817b.f2818a.f2827e.onRemove(false, this.f2816a.getCity(), C2915a.f14760f);
                }
            }
        }

        C04361(OfflineMapManager offlineMapManager) {
            this.f2818a = offlineMapManager;
        }

        public void m4359a(C0385g c0385g) {
            if (!(this.f2818a.f2827e == null || c0385g == null)) {
                this.f2818a.f2828f.post(new C04331(this, c0385g));
            }
            if (this.f2818a.f2826d != null && c0385g.m4076c().m3451a(c0385g.f2470f)) {
                this.f2818a.f2826d.setLoadOfflineData(false);
                this.f2818a.f2826d.setLoadOfflineData(true);
            }
        }

        public void m4360b(C0385g c0385g) {
            if (this.f2818a.f2827e != null && c0385g != null) {
                this.f2818a.f2828f.post(new C04342(this, c0385g));
            }
        }

        public void m4361c(C0385g c0385g) {
            if (this.f2818a.f2827e != null && c0385g != null) {
                this.f2818a.f2828f.post(new C04353(this, c0385g));
            }
        }
    }

    /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.2 */
    class C04372 implements Runnable {
        final /* synthetic */ String f2819a;
        final /* synthetic */ OfflineMapManager f2820b;

        C04372(OfflineMapManager offlineMapManager, String str) {
            this.f2820b = offlineMapManager;
            this.f2819a = str;
        }

        public void run() {
            try {
                this.f2820b.f2824b.m4131d(this.f2819a);
            } catch (Throwable e) {
                ce.m3829a(e, "OfflineMapManager", "downloadByProvinceName");
            }
        }
    }

    /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager.3 */
    class C04383 implements Runnable {
        final /* synthetic */ String f2821a;
        final /* synthetic */ OfflineMapManager f2822b;

        C04383(OfflineMapManager offlineMapManager, String str) {
            this.f2822b = offlineMapManager;
            this.f2821a = str;
        }

        public void run() {
            this.f2822b.f2824b.m4128c(this.f2821a);
        }
    }

    public interface OfflineMapDownloadListener {
        void onCheckUpdate(boolean z, String str);

        void onDownload(int i, int i2, String str);

        void onRemove(boolean z, String str, String str2);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener) {
        this.f2828f = new Handler();
        this.f2829g = new Handler();
        this.f2827e = offlineMapDownloadListener;
        m4364a(context);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener, AMap aMap) {
        this.f2828f = new Handler();
        this.f2829g = new Handler();
        this.f2827e = offlineMapDownloadListener;
        this.f2826d = aMap;
        m4364a(context);
    }

    private void m4363a() {
        if (!bj.m3640c(this.f2825c)) {
            throw new AMapException(com.amap.api.services.core.AMapException.ERROR_CONNECTION);
        }
    }

    private void m4364a(Context context) {
        this.f2825c = context.getApplicationContext();
        C0391i.f2485b = false;
        this.f2824b = C0391i.m4106a(context);
        this.f2823a = this.f2824b.f2490f;
        this.f2824b.m4120a(new C04361(this));
    }

    private void m4365a(String str, String str2) {
        this.f2824b.m4121a(str);
    }

    private void m4367b() {
        this.f2827e = null;
    }

    public void destroy() {
        this.f2824b.m4129d();
        m4367b();
        this.f2826d = null;
        this.f2828f.removeCallbacksAndMessages(null);
        this.f2828f = null;
        this.f2829g.removeCallbacksAndMessages(null);
        this.f2829g = null;
    }

    public void downloadByCityCode(String str) {
        this.f2824b.m4134e(str);
    }

    public void downloadByCityName(String str) {
        this.f2824b.m4131d(str);
    }

    public void downloadByProvinceName(String str) {
        try {
            m4363a();
            OfflineMapProvince itemByProvinceName = getItemByProvinceName(str);
            if (itemByProvinceName == null) {
                throw new AMapException(com.amap.api.services.core.AMapException.ERROR_INVALID_PARAMETER);
            }
            Iterator it = itemByProvinceName.getCityList().iterator();
            while (it.hasNext()) {
                this.f2829g.post(new C04372(this, ((OfflineMapCity) it.next()).getCity()));
            }
        } catch (Throwable th) {
            if (th instanceof AMapException) {
                AMapException aMapException = (AMapException) th;
            } else {
                ce.m3829a(th, "OfflineMapManager", "downloadByProvinceName");
            }
        }
    }

    public ArrayList<OfflineMapCity> getDownloadOfflineMapCityList() {
        return this.f2823a.m4163c();
    }

    public ArrayList<OfflineMapProvince> getDownloadOfflineMapProvinceList() {
        return this.f2823a.m4164d();
    }

    public ArrayList<OfflineMapCity> getDownloadingCityList() {
        return this.f2823a.m4165e();
    }

    public ArrayList<OfflineMapProvince> getDownloadingProvinceList() {
        return this.f2823a.m4166f();
    }

    public OfflineMapCity getItemByCityCode(String str) {
        return this.f2823a.m4155a(str);
    }

    public OfflineMapCity getItemByCityName(String str) {
        return this.f2823a.m4160b(str);
    }

    public OfflineMapProvince getItemByProvinceName(String str) {
        return this.f2823a.m4162c(str);
    }

    public ArrayList<OfflineMapCity> getOfflineMapCityList() {
        return this.f2823a.m4161b();
    }

    public ArrayList<OfflineMapProvince> getOfflineMapProvinceList() {
        return this.f2823a.m4156a();
    }

    public void pause() {
        this.f2824b.m4126c();
    }

    public void remove(String str) {
        if (this.f2824b.m4125b(str)) {
            this.f2824b.m4128c(str);
            return;
        }
        OfflineMapProvince c = this.f2823a.m4162c(str);
        if (c != null && c.getCityList() != null) {
            Iterator it = c.getCityList().iterator();
            while (it.hasNext()) {
                this.f2829g.post(new C04383(this, ((OfflineMapCity) it.next()).getCity()));
            }
        } else if (this.f2827e != null) {
            this.f2827e.onRemove(false, str, "\u6ca1\u6709\u8be5\u57ce\u5e02");
        }
    }

    public void restart() {
    }

    public void stop() {
        this.f2824b.m4123b();
    }

    public void updateOfflineCityByCode(String str) {
        OfflineMapCity itemByCityCode = getItemByCityCode(str);
        if (itemByCityCode == null || itemByCityCode.getCity() == null) {
            throw new AMapException(com.amap.api.services.core.AMapException.ERROR_INVALID_PARAMETER);
        }
        m4365a(itemByCityCode.getCity(), "cityname");
    }

    public void updateOfflineCityByName(String str) {
        m4365a(str, "cityname");
    }

    public void updateOfflineMapProvinceByName(String str) {
        m4365a(str, "cityname");
    }
}
