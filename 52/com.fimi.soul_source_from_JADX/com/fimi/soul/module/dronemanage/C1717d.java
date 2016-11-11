package com.fimi.soul.module.dronemanage;

import android.content.Context;
import android.os.Message;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p106b.C1434a;
import com.fimi.soul.drone.p106b.C1435b;
import com.fimi.soul.drone.p110d.C1481h;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.dronemanage.d */
public class C1717d extends C1716x implements C1234i {
    private static final int f8428g = 1;
    List<bh> f8429a;
    private AMap f8430c;
    private Context f8431d;
    private C1433a f8432e;
    private List<Marker> f8433f;

    public C1717d(AMap aMap, Context context, C1433a c1433a) {
        this.f8429a = new ArrayList();
        this.f8433f = new ArrayList();
        this.f8430c = aMap;
        this.f8431d = context;
        this.f8432e = c1433a;
        c1433a.m9590a((C1234i) this);
    }

    private void m11208j() {
        if (this.f8429a.size() > 0) {
            bh bhVar = (bh) this.f8429a.get(0);
            bhVar.m10502c(f8428g);
            m11213a(bhVar);
        }
    }

    private void m11209k() {
        if (this.f8429a != null && this.f8429a.size() > 0) {
            for (bh c : this.f8429a) {
                c.m10502c(0);
            }
            m11214b();
        }
    }

    public void m11210a() {
        m11209k();
        if (this.f8429a.size() > 0) {
            bh bhVar = (bh) this.f8429a.get(0);
            aj a = ad.m12228a(0.1d, bhVar.m10499b().latitude, bhVar.m10499b().longitude);
            LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
            C1481h.f7041a.f6874f = (float) latLng.latitude;
            C1481h.f7041a.f6873e = (float) latLng.longitude;
            C1481h.f7041a.f6875g = (short) bhVar.m10501c();
            C1481h.m9872a(this.f8432e);
            ak.m11201a(this.f8432e, C1481h.f7041a.m9778a());
            ak.m11200a();
            return;
        }
        ak.m8085a(this.f8431d, this.f8431d.getResources().getString(C1205R.string.insterassingfly), 0);
    }

    protected void m11211a(Message message) {
    }

    public void m11212a(LatLng latLng) {
        this.f8429a.clear();
        m11216c();
        bh bhVar = new bh();
        bhVar.m10496a(latLng);
        bhVar.m10504d(2);
        if (!this.f8429a.contains(bhVar)) {
            this.f8429a.add(bhVar);
        }
        m11213a(bhVar);
    }

    public void m11213a(bh bhVar) {
        Marker addMarker = bhVar.m10505e() == 0 ? this.f8430c.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.hd_1, C2915a.f14760f, C2915a.f14760f, this.f8431d)))) : this.f8430c.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.hd_2, C2915a.f14760f, C2915a.f14760f, this.f8431d))));
        if (!this.f8433f.contains(addMarker)) {
            this.f8433f.add(addMarker);
        }
        addMarker.setObject(bhVar);
    }

    public void m11214b() {
        m11216c();
        for (bh a : this.f8429a) {
            m11213a(a);
        }
    }

    public void m11215b(bh bhVar) {
        this.f8429a.remove(bhVar);
        m11214b();
    }

    public void m11216c() {
        if (this.f8433f != null && this.f8433f.size() > 0) {
            for (Marker marker : this.f8433f) {
                if (marker != null) {
                    marker.destroy();
                }
            }
            this.f8433f.clear();
        }
    }

    public List<bh> m11217d() {
        return this.f8429a;
    }

    public void m11218e() {
        if (this.f8429a.size() > 0) {
            m11214b();
        }
    }

    public void m11219f() {
        this.f8432e.m9594b((C1234i) this);
    }

    public void m11220g() {
        this.f8432e.m9590a((C1234i) this);
    }

    public /* bridge */ /* synthetic */ void m11221h() {
        super.m11206h();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1718e.f8434a[c1584h.ordinal()]) {
            case f8428g /*1*/:
                ak.m11202b();
                m11208j();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m11210a();
            default:
        }
    }
}
