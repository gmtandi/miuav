package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.mapcore.util.C0407z.C0382a;
import com.amap.api.mapcore.util.ah.C0341a;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.File;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.amap.api.mapcore.util.g */
public class C0385g extends OfflineMapCity implements ag, C0384q {
    public static final Creator<C0385g> f2464l;
    public al f2465a;
    public al f2466b;
    public al f2467c;
    public al f2468d;
    public al f2469e;
    public al f2470f;
    public al f2471g;
    public al f2472h;
    al f2473i;
    Context f2474j;
    boolean f2475k;
    private String f2476m;
    private String f2477n;
    private long f2478o;

    /* renamed from: com.amap.api.mapcore.util.g.1 */
    class C03831 implements C0382a {
        final /* synthetic */ String f2451a;
        final /* synthetic */ File f2452b;
        final /* synthetic */ C0385g f2453c;

        C03831(C0385g c0385g, String str, File file) {
            this.f2453c = c0385g;
            this.f2451a = str;
            this.f2452b = file;
        }

        public void m4056a(String str, String str2) {
        }

        public void m4057a(String str, String str2, float f) {
            int i = (int) (60.0d + (((double) f) * 0.39d));
            if (i - this.f2453c.getcompleteCode() > 0 && System.currentTimeMillis() - this.f2453c.f2478o > 1000) {
                this.f2453c.setCompleteCode(i);
                this.f2453c.f2478o = System.currentTimeMillis();
            }
        }

        public void m4058a(String str, String str2, int i) {
            this.f2453c.f2473i.m3458g();
        }

        public void m4059b(String str, String str2) {
            try {
                new File(this.f2451a).delete();
                af.m3419b(this.f2452b);
                this.f2453c.setCompleteCode(100);
                this.f2453c.f2473i.m3460i();
            } catch (Exception e) {
                this.f2453c.f2473i.m3458g();
            }
        }
    }

    static {
        f2464l = new C0386h();
    }

    public C0385g(Context context, int i) {
        this.f2465a = new an(6, this);
        this.f2466b = new at(2, this);
        this.f2467c = new ap(0, this);
        this.f2468d = new ar(3, this);
        this.f2469e = new as(1, this);
        this.f2470f = new am(4, this);
        this.f2471g = new aq(7, this);
        this.f2472h = new ao(-1, this);
        this.f2476m = null;
        this.f2477n = C2915a.f14760f;
        this.f2475k = false;
        this.f2478o = 0;
        this.f2474j = context;
        m4067a(i);
    }

    public C0385g(Context context, OfflineMapCity offlineMapCity) {
        this(context, offlineMapCity.getState());
        setCity(offlineMapCity.getCity());
        setUrl(offlineMapCity.getUrl());
        setState(offlineMapCity.getState());
        setCompleteCode(offlineMapCity.getcompleteCode());
        setAdcode(offlineMapCity.getAdcode());
        setVersion(offlineMapCity.getVersion());
        setSize(offlineMapCity.getSize());
        setCode(offlineMapCity.getCode());
        setJianpin(offlineMapCity.getJianpin());
        setPinyin(offlineMapCity.getPinyin());
        m4092s();
    }

    public C0385g(Parcel parcel) {
        super(parcel);
        this.f2465a = new an(6, this);
        this.f2466b = new at(2, this);
        this.f2467c = new ap(0, this);
        this.f2468d = new ar(3, this);
        this.f2469e = new as(1, this);
        this.f2470f = new am(4, this);
        this.f2471g = new aq(7, this);
        this.f2472h = new ao(-1, this);
        this.f2476m = null;
        this.f2477n = C2915a.f14760f;
        this.f2475k = false;
        this.f2478o = 0;
        this.f2477n = parcel.readString();
    }

    private void m4063a(File file, File file2, String str) {
        new C0407z().m4242a(file, file2, -1, af.m3412a(file), new C03831(this, str, file));
    }

    public String m4064A() {
        return m4093t();
    }

    public String m4065B() {
        return m4094u();
    }

    public String m4066a() {
        return this.f2477n;
    }

    public void m4067a(int i) {
        switch (i) {
            case Opcodes.F_NEW /*-1*/:
                this.f2473i = this.f2472h;
                break;
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                this.f2473i = this.f2467c;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f2473i = this.f2469e;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f2473i = this.f2466b;
                break;
            case Type.BYTE /*3*/:
                this.f2473i = this.f2468d;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f2473i = this.f2470f;
                break;
            case Type.FLOAT /*6*/:
                this.f2473i = this.f2465a;
                break;
            case Type.LONG /*7*/:
                this.f2473i = this.f2471g;
                break;
            default:
                if (i < 0) {
                    this.f2473i = this.f2472h;
                    break;
                }
                break;
        }
        setState(i);
    }

    public void m4068a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f2478o > 500) {
            if (((int) j) > getcompleteCode()) {
                setCompleteCode((int) j);
                m4077d();
            }
            this.f2478o = currentTimeMillis;
        }
    }

    public void m4069a(long j, long j2) {
        long j3 = (100 * j2) / j;
        if (((int) j3) != getcompleteCode()) {
            setCompleteCode((int) j3);
            m4077d();
        }
    }

    public void m4070a(C0341a c0341a) {
        if (this.f2473i.equals(this.f2467c) || this.f2473i.equals(this.f2466b)) {
            this.f2473i.m3458g();
        }
    }

    public void m4071a(al alVar) {
        this.f2473i = alVar;
        setState(alVar.m3452b());
    }

    public void m4072a(C0401s c0401s) {
        m4067a(c0401s.l);
        setCity(c0401s.m4197e());
        setSize(c0401s.m4201i());
        setVersion(c0401s.m4198f());
        setCompleteCode(c0401s.m4202j());
        setAdcode(c0401s.m4199g());
        setUrl(c0401s.m4200h());
        String c = c0401s.m4207c();
        if (c != null && c.length() > 0) {
            m4073a(c);
        }
    }

    public void m4073a(String str) {
        this.f2477n = str;
    }

    public String m4074b() {
        return getUrl();
    }

    public void m4075b(String str) {
        Object t;
        Object u;
        if (this.f2473i.equals(this.f2469e)) {
            this.f2477n = str;
            t = m4093t();
            u = m4094u();
        } else {
            this.f2477n = str;
            t = m4093t();
            u = m4094u();
        }
        if (TextUtils.isEmpty(t) || TextUtils.isEmpty(u)) {
            m4090q();
            return;
        }
        File file = new File(u + "/");
        File file2 = new File(bj.m3615a(this.f2474j) + "vmap/");
        File file3 = new File(bj.m3615a(this.f2474j));
        if (!file3.exists()) {
            file3.mkdir();
        }
        if (!file2.exists()) {
            file2.mkdir();
        }
        m4063a(file, file2, t);
    }

    public al m4076c() {
        return this.f2473i;
    }

    public void m4077d() {
        C0391i a = C0391i.m4106a(this.f2474j);
        if (a != null) {
            a.m4127c(this);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void m4078e() {
        C0391i a = C0391i.m4106a(this.f2474j);
        if (a != null) {
            a.m4133e(this);
            m4077d();
        }
    }

    public void m4079f() {
        af.m3415a("CityOperation current State==>" + m4076c().m3452b());
        if (this.f2473i.equals(this.f2468d)) {
            this.f2473i.m3456e();
        } else if (this.f2473i.equals(this.f2467c)) {
            this.f2473i.m3457f();
        } else if (this.f2473i.equals(this.f2471g) || this.f2473i.equals(this.f2472h)) {
            m4083j();
            this.f2475k = true;
        } else {
            m4076c().m3454c();
        }
    }

    public void m4080g() {
        this.f2473i.m3458g();
    }

    public void m4081h() {
        this.f2473i.m3450a();
        if (this.f2475k) {
            this.f2473i.m3454c();
        }
        this.f2475k = false;
    }

    public void m4082i() {
        if (this.f2473i.equals(this.f2470f)) {
            this.f2473i.m3459h();
        } else {
            this.f2473i.m3459h();
        }
    }

    public void m4083j() {
        C0391i a = C0391i.m4106a(this.f2474j);
        if (a != null) {
            a.m4119a(this);
        }
    }

    public void m4084k() {
        C0391i a = C0391i.m4106a(this.f2474j);
        if (a != null) {
            a.m4124b(this);
        }
    }

    public void m4085l() {
        C0391i a = C0391i.m4106a(this.f2474j);
        if (a != null) {
            a.m4130d(this);
        }
    }

    public void m4086m() {
        this.f2478o = 0;
        if (!this.f2473i.equals(this.f2466b)) {
            Log.e(XiaomiOAuthConstants.EXTRA_STATE_2, "state must be waiting when download onStart");
        }
        this.f2473i.m3455d();
    }

    public void m4087n() {
        if (!this.f2473i.equals(this.f2467c)) {
            Log.e(XiaomiOAuthConstants.EXTRA_STATE_2, "state must be Loading when download onFinish");
        }
        this.f2473i.m3460i();
    }

    public void m4088o() {
        m4078e();
    }

    public void m4089p() {
        this.f2478o = 0;
        setCompleteCode(0);
        if (this.f2473i.equals(this.f2469e)) {
            this.f2473i.m3455d();
        } else {
            this.f2473i.m3455d();
        }
    }

    public void m4090q() {
        if (this.f2473i.equals(this.f2469e)) {
            this.f2473i.m3458g();
        } else {
            this.f2473i.m3458g();
        }
    }

    public void m4091r() {
        m4078e();
    }

    protected void m4092s() {
        this.f2476m = C0391i.f2484a + getAdcode() + ".zip" + ".tmp";
    }

    public String m4093t() {
        return TextUtils.isEmpty(this.f2476m) ? null : this.f2476m.substring(0, this.f2476m.lastIndexOf("."));
    }

    public String m4094u() {
        if (TextUtils.isEmpty(this.f2476m)) {
            return null;
        }
        String t = m4093t();
        return t.substring(0, t.lastIndexOf(46));
    }

    public boolean m4095v() {
        return ((double) af.m3411a()) < (((double) getSize()) * 2.5d) - ((double) (((long) getcompleteCode()) * getSize())) ? false : false;
    }

    public C0401s m4096w() {
        setState(this.f2473i.m3452b());
        C0401s c0401s = new C0401s((OfflineMapCity) this, this.f2474j);
        c0401s.m4204a(m4066a());
        af.m3415a("vMapFileNames: " + m4066a());
        return c0401s;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f2477n);
    }

    public boolean m4097x() {
        return m4095v();
    }

    public String m4098y() {
        StringBuffer stringBuffer = new StringBuffer(getAdcode());
        stringBuffer.append(".zip");
        return stringBuffer.toString();
    }

    public String m4099z() {
        return getAdcode();
    }
}
