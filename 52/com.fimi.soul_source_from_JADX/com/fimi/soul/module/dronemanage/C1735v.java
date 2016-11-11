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
import com.fimi.soul.drone.p110d.C1485l;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.dronemanage.v */
public class C1735v extends C1716x implements C1234i {
    List<bh> f8520a;
    private AMap f8521c;
    private Context f8522d;
    private C1433a f8523e;
    private List<Marker> f8524f;

    public C1735v(AMap aMap, Context context, C1433a c1433a) {
        this.f8520a = new ArrayList();
        this.f8524f = new ArrayList();
        this.f8521c = aMap;
        this.f8522d = context;
        this.f8523e = c1433a;
        c1433a.m9590a((C1234i) this);
    }

    private void m11267j() {
        if (this.f8520a.size() > 0) {
            bh bhVar = (bh) this.f8520a.get(0);
            bhVar.m10502c(1);
            m11272a(bhVar);
        }
    }

    private void m11268k() {
        if (this.f8520a != null && this.f8520a.size() > 0) {
            for (bh c : this.f8520a) {
                c.m10502c(0);
            }
            m11273b();
        }
    }

    public void m11269a() {
        m11268k();
        if (this.f8520a.size() > 0) {
            bh bhVar = (bh) this.f8520a.get(0);
            aj a = ad.m12228a(0.1d, bhVar.m10499b().latitude, bhVar.m10499b().longitude);
            LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
            C1485l.m9877a(this.f8523e);
            return;
        }
        ak.m8085a(this.f8522d, this.f8522d.getResources().getString(C1205R.string.insterinterfly), 0);
    }

    protected void m11270a(Message message) {
    }

    public void m11271a(LatLng latLng) {
        this.f8520a.clear();
        m11275c();
        bh bhVar = new bh();
        bhVar.m10496a(latLng);
        bhVar.m10504d(3);
        if (!this.f8520a.contains(bhVar)) {
            this.f8520a.add(bhVar);
        }
        m11272a(bhVar);
    }

    public void m11272a(bh bhVar) {
        Marker addMarker = bhVar.m10505e() == 0 ? this.f8521c.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.xqd_1, C2915a.f14760f, C2915a.f14760f, this.f8522d)))) : this.f8521c.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.xqd_2, C2915a.f14760f, C2915a.f14760f, this.f8522d))));
        if (!this.f8524f.contains(addMarker)) {
            this.f8524f.add(addMarker);
        }
        addMarker.setObject(bhVar);
    }

    public void m11273b() {
        m11275c();
        for (bh a : this.f8520a) {
            m11272a(a);
        }
    }

    public void m11274b(bh bhVar) {
        this.f8520a.remove(bhVar);
        m11273b();
    }

    public void m11275c() {
        if (this.f8524f != null && this.f8524f.size() > 0) {
            for (Marker marker : this.f8524f) {
                if (marker != null) {
                    marker.destroy();
                }
            }
            this.f8524f.clear();
        }
    }

    public List<bh> m11276d() {
        return this.f8520a;
    }

    public void m11277e() {
        if (this.f8520a.size() > 0) {
            m11273b();
        }
    }

    public void m11278f() {
        this.f8523e.m9594b((C1234i) this);
    }

    public void m11279g() {
        this.f8523e.m9590a((C1234i) this);
    }

    public /* bridge */ /* synthetic */ void m11280h() {
        super.m11206h();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1736w.f8525a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11267j();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m11269a();
            default:
        }
    }
}
