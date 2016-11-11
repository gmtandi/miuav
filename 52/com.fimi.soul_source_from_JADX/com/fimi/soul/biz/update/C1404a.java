package com.fimi.soul.biz.update;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p077a.C1102c;
import com.fimi.kernel.p076b.p077a.C1110j;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p096d.C1316b;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.NetUtil;
import com.p054c.p055a.p072c.C0957d;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.fimi.soul.biz.update.a */
public class C1404a extends C1403p {
    private static final String f6318f = "KEY_APUPDATEBIZ_LASTEST_VERSION";
    private static final int f6319g = 1;
    private static final String f6320h = "ar934x_US";
    private static final String f6321k = "192.168.42.100";
    private static final int f6322p = 3;
    private C1102c f6323i;
    private C1145b f6324j;
    private String f6325l;
    private long f6326m;
    private long f6327n;
    private int f6328o;
    private boolean f6329q;
    private C1316b f6330r;
    private ah f6331s;

    public C1404a() {
        this.f6323i = null;
        this.f6324j = null;
        this.f6329q = false;
        this.f6330r = null;
        this.f6324j = (C1145b) C1189f.m8328a(C1152e.Volley);
        this.f6330r = new C1316b();
    }

    static /* synthetic */ long m9399c(C1404a c1404a, long j) {
        long j2 = c1404a.f6327n + j;
        c1404a.f6327n = j2;
        return j2;
    }

    public static String m9400c() {
        return C1969i.m12493p();
    }

    public static String m9402d() {
        return String.format("%s%s", new Object[]{C1969i.m12493p(), C1921n.f9855t});
    }

    protected void m9407a(Message message) {
        super.m9382a(message);
        if (message.what == f6319g) {
            if (message.arg1 == f6319g) {
                this.f6331s.m9419a(false, (long) message.arg2, 100, 0);
            } else {
                this.f6331s.m9419a(true, 0, 0, 0);
            }
        }
    }

    public void m9408a(ah ahVar) {
        if (m9390h() == null) {
            ahVar.m9419a(true, 0, 0, 0);
            return;
        }
        m9390h().getUrl();
        this.f6325l = C1404a.m9402d();
        m9384a(m9390h(), this.f6325l, ahVar);
    }

    public void m9409a(ah ahVar, Context context, FirmwareInfo firmwareInfo) {
        Object[] objArr = new Object[f6322p];
        objArr[0] = C1969i.m12474a();
        objArr[f6319g] = "firmware";
        objArr[2] = ak.m9427a(firmwareInfo.getFileEncode());
        this.f6325l = String.format("%s/%s/%s", objArr);
        File file = new File(String.format("%s/%s", new Object[]{C1969i.m12474a(), "firmware"}));
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(this.f6325l);
        if (new File(this.f6325l).isFile()) {
            if (this.f6323i != null) {
                this.f6323i.m7718h();
                this.f6323i = null;
            }
            this.f6323i = (C1102c) C1189f.m8328a(C1152e.Ftp);
            C1110j c = this.f6323i.m7713c();
            c.m7723a(f6321k);
            c.m7722a(21);
            this.f6323i.m7700a(new C1410g(this, ahVar));
            return;
        }
        ahVar.m9419a(true, 0, 0, 0);
    }

    public void m9410a(aj<UpdateVersonBean> ajVar) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getDeviceVersion"));
        arrayList.add(new BasicNameValuePair("sysID", Constants.VIA_SHARE_TYPE_TEXT));
        arrayList.add(new BasicNameValuePair("modelName", f6320h));
        NetUtil.m12201a(arrayList, C1189f.m8327a());
        String format = URLEncodedUtils.format(arrayList, C1142e.f5201a);
        format = String.format("%s?%s", new Object[]{C1236a.f5612j, format});
        Log.d("Good", "\u8bf7\u6c42url:" + format);
        this.f6324j.m7905a(format, new C1405b(this, ajVar));
    }

    public void m9411a(InputStream inputStream, String str) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(str, true);
            byte[] bArr = new byte[C0957d.f5044b];
            while (true) {
                int read = inputStream.read(bArr, 0, C0957d.f5044b);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m9412b(ah ahVar) {
        if (this.f6323i != null) {
            this.f6323i.m7718h();
        }
        this.f6331s = ahVar;
        m7687a(new C1408e(this));
    }

    public void m9413b(aj<UpdateVersonBean> ajVar) {
        this.f6330r.m8893b(new C1407d(this, ajVar));
    }

    public boolean m9414b() {
        if (!(m9390h() == null || m9389g() == null)) {
            try {
                if (Integer.parseInt(m9390h().getNewVersion()) > Integer.parseInt(m9389g().getNewVersion())) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public void m9415e() {
        if (this.f6323i != null) {
            this.f6323i.m7718h();
        }
    }
}
