package com.fimi.soul.biz.update;

import android.content.Context;
import android.util.Log;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.kernel.p084e.C1184w;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.NetUtil;
import com.p054c.p055a.p072c.C0957d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.fimi.soul.biz.update.r */
public class C1420r extends C1403p {
    public static final String f6372f = "firmware.zip";
    private static final String f6373g = "x1";
    private C1145b f6374h;

    public C1420r() {
        this.f6374h = null;
        this.f6374h = (C1145b) C1189f.m8328a(C1152e.Volley);
    }

    public static String m9467a(String str) {
        if (str != null) {
            int indexOf = str.indexOf(86);
            int indexOf2 = str.indexOf(66);
            int indexOf3 = str.indexOf("SP");
            if (indexOf3 > 0 && indexOf < indexOf3) {
                return str.substring(indexOf + 1, indexOf3);
            }
            if (indexOf < indexOf2) {
                return str.substring(indexOf + 1, indexOf2);
            }
        }
        return null;
    }

    public static String m9468b() {
        return String.format("%s%s", new Object[]{C1969i.m12493p(), f6372f});
    }

    public static String m9469c() {
        return "fimware.zip";
    }

    public static String m9470d() {
        return C1184w.m8280b(C1420r.m9469c(), ".zip") + ".bin";
    }

    public void m9471a(Context context) {
        String format = String.format("%s/%s/%s", new Object[]{C1969i.m12474a(), "firmware", f6372f});
        File file = new File(String.format("%s/%s", new Object[]{C1969i.m12474a(), "firmware"}));
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(format);
        if (file.exists()) {
            file.delete();
        }
        try {
            m9474a(context.getAssets().open(f6372f), String.format("%s/%s/%s", new Object[]{C1969i.m12474a(), "firmware", f6372f}));
        } catch (Exception e) {
            ak.m8084a(context, "\u5199\u6587\u4ef6\u51fa\u9519\uff1a" + e.getMessage());
        }
    }

    public void m9472a(ah ahVar) {
        if (m9390h() == null) {
            ahVar.m9419a(true, 0, 0, 0);
        } else {
            m9384a(m9390h(), C1420r.m9468b(), ahVar);
        }
    }

    public void m9473a(aj<UpdateVersonBean> ajVar) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "getDeviceVersion"));
        arrayList.add(new BasicNameValuePair("sysID", "04"));
        arrayList.add(new BasicNameValuePair("modelName", f6373g));
        NetUtil.m12201a(arrayList, C1189f.m8327a());
        String format = URLEncodedUtils.format(arrayList, C1142e.f5201a);
        format = String.format("%s?%s", new Object[]{C1236a.f5612j, format});
        Log.d("Good", "\u8bf7\u6c42url:" + format);
        this.f6374h.m7905a(format, new C1421s(this, ajVar));
    }

    public void m9474a(InputStream inputStream, String str) {
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
}
