package com.fimi.soul.module.dronemanage;

import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.Polyline;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p106b.C1434a;
import com.fimi.soul.drone.p106b.C1435b;
import com.fimi.soul.drone.p110d.C1474a;
import com.fimi.soul.drone.p110d.C1477d;
import com.fimi.soul.drone.p110d.C1486m;
import com.fimi.soul.drone.p116g.C1547g;
import com.fimi.soul.drone.p117h.at;
import com.fimi.soul.drone.p117h.bh;
import com.fimi.soul.module.setting.newhand.ae;
import com.fimi.soul.utils.MapProjection;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.dronemanage.z */
public class C1738z extends C1716x implements C1234i {
    private static C1547g f8527j = null;
    private static C1547g f8528k = null;
    private static int f8529m = 0;
    private static final int f8530n = 2;
    List<bh> f8531a;
    ArrayList<BitmapDescriptor> f8532c;
    private List<Polyline> f8533d;
    private List<Marker> f8534e;
    private AMap f8535f;
    private Context f8536g;
    private int f8537h;
    private int f8538i;
    private C1433a f8539l;
    private ae f8540o;
    private int f8541p;
    private int f8542q;
    private C1725l f8543r;
    private ad f8544s;

    public C1738z(C1433a c1433a, Context context, FlightMapFragment flightMapFragment) {
        this.f8531a = new ArrayList();
        this.f8533d = new ArrayList();
        this.f8534e = new ArrayList();
        this.f8537h = 1;
        this.f8538i = 1;
        this.f8540o = ae.NORMAL;
        this.f8541p = 1;
        this.f8542q = 20;
        this.f8532c = new ArrayList();
        this.f8535f = c1433a.m9579Z();
        this.f8536g = context;
        this.f8539l = c1433a;
        this.f8544s = flightMapFragment;
        this.f8543r = new C1725l(this.f8535f);
        c1433a.m9590a((C1234i) this);
    }

    private void m11284d(int i) {
        if (this.f8542q == 6 && i >= 1 && i <= this.f8534e.size() && this.f8534e.size() >= 1) {
            ((Marker) this.f8534e.get(i - 1)).setIcons(m11285e(i));
            if (i >= f8530n) {
                for (int i2 = 0; i2 < i - 1; i2++) {
                    ((Marker) this.f8534e.get(i2)).setIcon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_hd_gray, (i2 + 1) + C2915a.f14760f, C2915a.f14760f, this.f8536g)));
                }
            }
        }
    }

    private ArrayList<BitmapDescriptor> m11285e(int i) {
        this.f8532c.clear();
        this.f8532c.add(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_wp_map2, i + C2915a.f14760f, C2915a.f14760f, this.f8536g)));
        this.f8532c.add(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_wp_map, i + C2915a.f14760f, C2915a.f14760f, this.f8536g)));
        return this.f8532c;
    }

    static /* synthetic */ int m11286l() {
        int i = f8529m;
        f8529m = i + 1;
        return i;
    }

    private void m11288n() {
        if (this.f8531a != null && this.f8531a.size() > 0) {
            for (bh c : this.f8531a) {
                c.m10502c(0);
            }
            m11303c();
        }
    }

    private void m11289o() {
        for (Marker draggable : this.f8534e) {
            draggable.setDraggable(true);
        }
    }

    private void m11290p() {
        this.f8541p++;
        at l = this.f8539l.m9609l();
        m11296a(new LatLng(l.m10398c(), l.m10397b()), 1);
        if (this.f8541p > l.m10403h() && f8528k != null) {
            f8528k.m10134e();
            f8528k.m10135f();
            this.f8541p = 1;
            m11303c();
        }
    }

    private void m11291q() {
        f8529m = 0;
        if (f8527j != null) {
            f8527j.m10134e();
        }
        int a = this.f8539l.m9608k().m10386a();
        if (this.f8538i == a && this.f8531a.size() >= 1 && this.f8534e.size() >= 1) {
            ((bh) this.f8531a.get(a - 1)).m10502c(1);
            ((Marker) this.f8534e.get(a - 1)).setIcon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_wp_map2, a + C2915a.f14760f, C2915a.f14760f, this.f8536g)));
            ((Marker) this.f8534e.get(a - 1)).setDraggable(false);
        }
        if (a == this.f8531a.size()) {
            this.f8540o = ae.SENDCOM;
            C1474a.m9855a(this.f8539l);
            ak.m11201a(this.f8539l, C1474a.f7028b.m9637a());
            ak.m11200a();
            return;
        }
        this.f8538i++;
        m11294a(this.f8538i);
    }

    private void m11292r() {
        f8528k = new C1547g(ae.f9482j, new ab(this));
        f8528k.m10133d();
    }

    public void m11293a() {
        f8527j = new C1547g(ae.f9482j, new aa(this));
    }

    public void m11294a(int i) {
        if (this.f8531a.size() >= 1 && i <= this.f8531a.size()) {
            bh bhVar = (bh) this.f8531a.get(i - 1);
            aj a = ad.m12228a(0.1d, bhVar.m10499b().latitude, bhVar.m10499b().longitude);
            LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
            C1486m.f7051b.f6686f = (float) latLng.latitude;
            C1486m.f7051b.f6685e = (float) latLng.longitude;
            C1486m.f7051b.f6687g = (short) bhVar.m10501c();
            C1486m.f7051b.f6684d = (short) i;
            C1486m.m9878a(this.f8539l);
            if (!f8527j.m10131b()) {
                f8527j.m10132c();
            }
        }
    }

    protected void m11295a(Message message) {
        switch (message.what) {
            case f8530n /*2*/:
                f8529m = 0;
                f8527j.m10134e();
                f8527j.m10135f();
                ak.m8085a(this.f8536g, this.f8536g.getResources().getString(C1205R.string.updataerror), 0);
            default:
        }
    }

    public void m11296a(LatLng latLng, int i) {
        if (this.f8531a.size() >= 20) {
            ak.m8085a(this.f8536g, this.f8536g.getResources().getString(C1205R.string.maxwp), 3000);
            return;
        }
        bh bhVar = new bh();
        bhVar.m10496a(latLng);
        bhVar.m10504d(1);
        if (i == 1) {
            bhVar.m10502c(1);
        } else {
            bhVar.m10502c(0);
        }
        if (!this.f8531a.contains(bhVar)) {
            this.f8531a.add(bhVar);
        }
        this.f8544s.m11170a(this.f8531a);
        m11297a(bhVar);
    }

    public void m11297a(bh bhVar) {
        Polyline addPolyline = this.f8535f.addPolyline(this.b.add(bhVar.m10499b()));
        if (this.f8531a.size() > 1) {
            this.f8543r.m11251a(((bh) this.f8531a.get(this.f8531a.size() - 2)).m10499b(), ((bh) this.f8531a.get(this.f8531a.size() - 1)).m10499b());
        }
        Marker addMarker = bhVar.m10505e() == 0 ? this.f8535f.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_wp_map, this.f8537h + C2915a.f14760f, C2915a.f14760f, this.f8536g)))) : this.f8535f.addMarker(C1434a.m9624a(bhVar.m10499b()).icon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_wp_map2, this.f8537h + C2915a.f14760f, C2915a.f14760f, this.f8536g))));
        if (!this.f8533d.contains(addPolyline)) {
            this.f8533d.add(addPolyline);
        }
        if (!this.f8534e.contains(addMarker)) {
            this.f8534e.add(addMarker);
        }
        bhVar.m10500b(this.f8537h);
        addMarker.setObject(bhVar);
        this.f8537h++;
    }

    public void m11298a(List<bh> list) {
        Collection arrayList = new ArrayList();
        if (list.size() == 0) {
            this.f8531a.clear();
        }
        for (bh bhVar : this.f8531a) {
            if (!list.contains(bhVar)) {
                arrayList.add(bhVar);
            }
        }
        this.f8531a.removeAll(arrayList);
        m11303c();
    }

    public List<bh> m11299b(List<Point> list) {
        int i = 0;
        int size;
        List arrayList;
        List a;
        if (list.size() >= 20) {
            size = this.f8531a != null ? 20 - this.f8531a.size() : 20;
            arrayList = new ArrayList();
            while (i < size) {
                arrayList.add(list.get(i));
                i++;
            }
            a = MapProjection.m12196a(arrayList, this.f8535f);
        } else {
            size = this.f8531a != null ? list.size() > 20 - this.f8531a.size() ? 20 - this.f8531a.size() : list.size() : 0;
            arrayList = new ArrayList();
            while (i < size) {
                arrayList.add(list.get(i));
                i++;
            }
            a = MapProjection.m12196a(arrayList, this.f8535f);
        }
        if (r0.size() > 0) {
            for (LatLng latLng : r0) {
                bh bhVar = new bh();
                bhVar.m10504d(1);
                bhVar.m10496a(latLng);
                this.f8531a.add(bhVar);
                if (this.f8531a.size() > 20) {
                    break;
                }
            }
        }
        m11303c();
        return this.f8531a;
    }

    public void m11300b() {
        this.f8538i = 1;
        m11288n();
        if (f8527j == null) {
            m11293a();
        }
        m11294a(this.f8538i);
        this.f8540o = ae.SENDING;
    }

    public void m11301b(int i) {
        C1477d.f7033a.f6662d = (short) i;
        C1477d.m9858a(this.f8539l);
    }

    public void m11302b(bh bhVar) {
        this.f8531a.remove(bhVar);
        m11303c();
    }

    public void m11303c() {
        m11305d();
        for (bh a : this.f8531a) {
            m11297a(a);
        }
    }

    public void m11304c(int i) {
        this.f8542q = i;
        this.f8539l.m9590a((C1234i) this);
        if (this.f8531a.size() >= 1 || this.f8542q != 6) {
            m11303c();
        } else {
            m11292r();
        }
    }

    public void m11305d() {
        this.f8537h = 1;
        if (this.f8534e != null && this.f8534e.size() > 0) {
            for (Marker marker : this.f8534e) {
                if (marker != null) {
                    marker.destroy();
                }
            }
            this.f8534e.clear();
        }
        m11306e();
    }

    public void m11306e() {
        this.f8543r.m11250a();
        if (this.f8533d != null && this.f8533d.size() > 0) {
            for (Polyline polyline : this.f8533d) {
                if (polyline != null) {
                    polyline.remove();
                }
            }
            this.f8533d.clear();
        }
        m11309h();
    }

    public void m11307f() {
        for (bh b : this.f8531a) {
            Polyline addPolyline = this.f8535f.addPolyline(this.b.add(b.m10499b()));
            if (!this.f8533d.contains(addPolyline)) {
                this.f8533d.add(addPolyline);
            }
        }
    }

    public List<bh> m11308g() {
        return this.f8531a;
    }

    public /* bridge */ /* synthetic */ void m11309h() {
        super.m11206h();
    }

    public void m11310j() {
        this.f8539l.m9594b((C1234i) this);
    }

    public void m11311k() {
        this.f8539l.m9590a((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (ac.f8392a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m11291q();
            case f8530n /*2*/:
                m11284d(c1433a.m9618u().m10304e());
            case Type.BYTE /*3*/:
                this.f8542q = c1433a.ah().m10170e();
                if (this.f8534e.size() > 0 && ((Marker) this.f8534e.get(this.f8534e.size() - 1)).getIcons().size() != 1 && this.f8542q != 6) {
                    ((Marker) this.f8534e.get(this.f8534e.size() - 1)).setIcon(BitmapDescriptorFactory.fromBitmap(C1435b.m9626a((int) C1205R.drawable.ic_hd_gray, this.f8534e.size() + C2915a.f14760f, C2915a.f14760f, this.f8536g)));
                    m11289o();
                }
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                m11290p();
            case Type.FLOAT /*6*/:
                m11289o();
            default:
        }
    }
}
