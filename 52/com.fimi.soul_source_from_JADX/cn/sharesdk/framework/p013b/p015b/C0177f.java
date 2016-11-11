package cn.sharesdk.framework.p013b.p015b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.utils.C0205d;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.framework.b.b.f */
public class C0177f extends C0171c {
    private static int f267p;
    private static long f268q;
    public int f269a;
    public String f270b;
    public String f271c;
    public C0176a f272d;
    public String f273n;
    public String[] f274o;

    /* renamed from: cn.sharesdk.framework.b.b.f.a */
    public class C0176a {
        public String f260a;
        public String f261b;
        public ArrayList<String> f262c;
        public ArrayList<String> f263d;
        public ArrayList<String> f264e;
        public ArrayList<Bitmap> f265f;
        public HashMap<String, Object> f266g;

        public C0176a() {
            this.f260a = C2915a.f14760f;
            this.f262c = new ArrayList();
            this.f263d = new ArrayList();
            this.f264e = new ArrayList();
            this.f265f = new ArrayList();
        }

        public String toString() {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.f261b)) {
                this.f261b = this.f261b.trim().replaceAll("\r", C2915a.f14760f);
                this.f261b = this.f261b.trim().replaceAll("\n", C2915a.f14760f);
                this.f261b = this.f261b.trim().replaceAll("\r\n", C2915a.f14760f);
            }
            hashMap.put("text", this.f261b);
            hashMap.put(SocialConstants.PARAM_URL, this.f262c);
            if (this.f263d != null && this.f263d.size() > 0) {
                hashMap.put("imgs", this.f263d);
            }
            if (this.f266g != null) {
                hashMap.put("attch", new Hashon().fromHashMap(this.f266g));
            }
            return new Hashon().fromHashMap(hashMap);
        }
    }

    public C0177f() {
        this.f272d = new C0176a();
    }

    protected String m563a() {
        return "[SHR]";
    }

    protected void m564a(long j) {
        f268q = j;
    }

    protected int m565b() {
        return FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
    }

    protected int m566c() {
        return 30;
    }

    protected long m567d() {
        return (long) f267p;
    }

    protected long m568e() {
        return f268q;
    }

    protected void m569f() {
        f267p++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f269a);
        stringBuilder.append('|').append(this.f270b);
        stringBuilder.append('|').append(TextUtils.isEmpty(this.f271c) ? C2915a.f14760f : this.f271c);
        String str = C2915a.f14760f;
        if (this.f274o != null && this.f274o.length > 0) {
            str = "[\"" + TextUtils.join("\",\"", this.f274o) + "\"]";
        }
        stringBuilder.append('|').append(str);
        stringBuilder.append('|');
        if (this.f272d != null) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f272d.toString()), 0);
                if (str.contains("\n")) {
                    str = str.replace("\n", C2915a.f14760f);
                }
                stringBuilder.append(str);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m)) {
            stringBuilder.append(this.m);
        }
        stringBuilder.append('|');
        if (!TextUtils.isEmpty(this.f273n)) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.f273n), 0);
                if (!TextUtils.isEmpty(str) && str.contains("\n")) {
                    str = str.replace("\n", C2915a.f14760f);
                }
                stringBuilder.append(str);
            } catch (Throwable th2) {
                C0205d.m752a().m750w(th2);
            }
        }
        return stringBuilder.toString();
    }
}
