package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.m */
public class C0395m {
    public ArrayList<OfflineMapProvince> f2503a;
    private C0405x f2504b;
    private Context f2505c;
    private Handler f2506d;

    public C0395m(Context context, Handler handler) {
        this.f2503a = new ArrayList();
        this.f2505c = context;
        this.f2506d = handler;
        this.f2504b = C0405x.m4223a(context);
    }

    private void m4146a(C0385g c0385g, OfflineMapCity offlineMapCity) {
        int b = c0385g.m4076c().m3452b();
        if (c0385g.m4076c().equals(c0385g.f2465a)) {
            m4154d(c0385g.getAdcode());
        } else {
            if (c0385g.m4076c().equals(c0385g.f2470f)) {
                af.m3415a("saveJSONObjectToFile  CITY " + c0385g.getCity());
                c0385g.m4096w().m4208d();
            }
            if (m4151a(c0385g.getcompleteCode(), c0385g.m4076c().m3452b())) {
                m4148a(c0385g.m4096w());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(c0385g.getcompleteCode());
    }

    private void m4147a(C0385g c0385g, OfflineMapProvince offlineMapProvince) {
        int b = c0385g.m4076c().m3452b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            m4154d(offlineMapProvince.getProvinceCode());
            try {
                af.m3416a(offlineMapProvince.getProvinceCode(), this.f2505c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (m4153b(b) && m4152a(offlineMapProvince)) {
            C0401s c0401s;
            if (c0385g.getAdcode().equals(offlineMapProvince.getProvinceCode())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(c0385g.getcompleteCode());
                offlineMapProvince.setVersion(c0385g.getVersion());
                offlineMapProvince.setUrl(c0385g.getUrl());
                c0401s = new C0401s(offlineMapProvince, this.f2505c);
                c0401s.m4204a(c0385g.m4066a());
                c0401s.m4196c(c0385g.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                c0401s = new C0401s(offlineMapProvince, this.f2505c);
            }
            c0401s.m4208d();
            m4148a(c0401s);
            af.m3415a("saveJSONObjectToFile  province " + c0401s.m4197e());
        }
    }

    private void m4148a(C0401s c0401s) {
        if (this.f2504b != null && c0401s != null) {
            this.f2504b.m4230a(c0401s);
        }
    }

    private void m4149a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
    }

    private void m4150a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
    }

    private boolean m4151a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private boolean m4152a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (((OfflineMapCity) it.next()).getState() != 4) {
                return false;
            }
        }
        return true;
    }

    private boolean m4153b(int i) {
        return i == 4;
    }

    private void m4154d(String str) {
        if (this.f2504b != null) {
            this.f2504b.m4235c(str);
        }
    }

    public OfflineMapCity m4155a(String str) {
        if (str == null || str.equals(C2915a.f14760f)) {
            return null;
        }
        Iterator it = this.f2503a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCode().equals(str)) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    public ArrayList<OfflineMapProvince> m4156a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList();
        Iterator it = this.f2503a.iterator();
        while (it.hasNext()) {
            arrayList.add((OfflineMapProvince) it.next());
        }
        return arrayList;
    }

    public void m4157a(C0385g c0385g) {
        String adcode = c0385g.getAdcode();
        synchronized (this.f2503a) {
            Iterator it = this.f2503a.iterator();
            loop0:
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                    if (offlineMapCity.getAdcode().trim().equals(adcode.trim())) {
                        m4146a(c0385g, offlineMapCity);
                        m4147a(c0385g, offlineMapProvince);
                        break loop0;
                    }
                }
            }
        }
    }

    public void m4158a(List<OfflineMapProvince> list) {
        if (this.f2503a.size() > 0) {
            for (int i = 0; i < this.f2503a.size(); i++) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) this.f2503a.get(i);
                OfflineMapProvince offlineMapProvince2 = (OfflineMapProvince) list.get(i);
                m4150a(offlineMapProvince, offlineMapProvince2);
                ArrayList cityList = offlineMapProvince.getCityList();
                ArrayList cityList2 = offlineMapProvince2.getCityList();
                for (int i2 = 0; i2 < cityList.size(); i2++) {
                    m4149a((OfflineMapCity) cityList.get(i2), (OfflineMapCity) cityList2.get(i2));
                }
            }
            return;
        }
        for (OfflineMapProvince offlineMapProvince3 : list) {
            this.f2503a.add(offlineMapProvince3);
        }
    }

    public boolean m4159a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1;
    }

    public OfflineMapCity m4160b(String str) {
        if (str == null || str.equals(C2915a.f14760f)) {
            return null;
        }
        Iterator it = this.f2503a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    public ArrayList<OfflineMapCity> m4161b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        Iterator it = this.f2503a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                arrayList.add((OfflineMapCity) it2.next());
            }
        }
        return arrayList;
    }

    public OfflineMapProvince m4162c(String str) {
        if (str == null || str.equals(C2915a.f14760f)) {
            return null;
        }
        Iterator it = this.f2503a.iterator();
        while (it.hasNext()) {
            OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
            if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                return offlineMapProvince;
            }
        }
        return null;
    }

    public ArrayList<OfflineMapCity> m4163c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f2503a) {
            arrayList = new ArrayList();
            Iterator it = this.f2503a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (offlineMapCity.getState() == 4) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> m4164d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f2503a) {
            arrayList = new ArrayList();
            Iterator it = this.f2503a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince.getState() == 4) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapCity> m4165e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f2503a) {
            arrayList = new ArrayList();
            Iterator it = this.f2503a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (m4159a(offlineMapCity.getState())) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> m4166f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f2503a) {
            arrayList = new ArrayList();
            Iterator it = this.f2503a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (m4159a(offlineMapProvince.getState())) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public void m4167g() {
        m4168h();
        this.f2506d = null;
        this.f2504b = null;
        this.f2505c = null;
    }

    public void m4168h() {
        this.f2503a.clear();
    }
}
