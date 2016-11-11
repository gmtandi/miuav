package cn.sharesdk.framework.p013b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.p013b.p014a.C0167c;
import cn.sharesdk.framework.p013b.p014a.C0169e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.p013b.p015b.C0173b;
import cn.sharesdk.framework.p013b.p015b.C0177f;
import cn.sharesdk.framework.p013b.p015b.C0177f.C0176a;
import cn.sharesdk.framework.utils.C0205d;
import com.facebook.common.util.UriUtil;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.b.a */
public class C0170a {
    private static C0170a f229a;
    private C0180c f230b;
    private C0169e f231c;
    private boolean f232d;

    private C0170a(Context context, String str) {
        this.f230b = new C0180c(context, str);
        this.f231c = C0169e.m467a(context);
    }

    public static C0170a m499a(Context context, String str) {
        if (f229a == null) {
            f229a = new C0170a(context, str);
        }
        return f229a;
    }

    private String m500a(Bitmap bitmap, C0179b c0179b) {
        File createTempFile = File.createTempFile("bm_tmp", ".png");
        OutputStream fileOutputStream = new FileOutputStream(createTempFile);
        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m501a(createTempFile.getAbsolutePath(), c0179b);
    }

    private String m501a(String str, C0179b c0179b) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        int ceil;
        CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
        float f = 200.0f;
        if (c0179b == C0179b.BEFORE_SHARE) {
            f = 600.0f;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && ((float) i2) > f) {
            ceil = (int) Math.ceil((double) (((float) options.outHeight) / f));
        } else if (i >= i2 || ((float) i) <= f) {
            return m506c(str);
        } else {
            ceil = (int) Math.ceil((double) (((float) options.outWidth) / f));
        }
        if (ceil <= 0) {
            ceil = 1;
        }
        options = new Options();
        options.inSampleSize = ceil;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        decodeFile.getHeight();
        decodeFile.getWidth();
        File createTempFile = File.createTempFile("bm_tmp2", "." + bmpFormat.name().toLowerCase());
        OutputStream fileOutputStream = new FileOutputStream(createTempFile);
        decodeFile.compress(bmpFormat, 80, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m506c(createTempFile.getAbsolutePath());
    }

    private String m502a(String str, String str2, int i, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(str2);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null && group.length() > 0) {
                arrayList.add(group);
            }
        }
        if (arrayList.size() == 0) {
            return str;
        }
        HashMap a = this.f230b.m593a(str, arrayList, i, str3);
        if (a == null || a.size() <= 0 || !a.containsKey(UriUtil.DATA_SCHEME)) {
            return str;
        }
        arrayList = (ArrayList) a.get(UriUtil.DATA_SCHEME);
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a = (HashMap) it.next();
            hashMap.put(String.valueOf(a.get(C2537j.au)), String.valueOf(a.get("surl")));
        }
        Matcher matcher2 = compile.matcher(str);
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        while (matcher2.find()) {
            stringBuilder.append(str.substring(i2, matcher2.start()));
            stringBuilder.append((String) hashMap.get(matcher2.group()));
            i2 = matcher2.end();
        }
        stringBuilder.append(str.substring(i2, str.length()));
        C0205d.m752a().m743i("> SERVER_SHORT_LINK_URL content after replace link ===  %s", stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void m503a(C0173b c0173b) {
        boolean c = this.f231c.m482c();
        Object obj = c0173b.f250c;
        if (!c || TextUtils.isEmpty(obj)) {
            c0173b.f251d = null;
            c0173b.f250c = null;
            return;
        }
        c0173b.f250c = Data.Base64AES(obj, c0173b.f.substring(0, 16));
    }

    private void m504a(C0177f c0177f) {
        int i = 0;
        int e = this.f231c.m485e();
        boolean c = this.f231c.m482c();
        C0176a c0176a = c0177f.f272d;
        if (e == 1 || (e == 0 && this.f232d)) {
            CharSequence a;
            int size = (c0176a == null || c0176a.f264e == null) ? 0 : c0176a.f264e.size();
            for (int i2 = 0; i2 < size; i2++) {
                a = m501a((String) c0176a.f264e.get(i2), C0179b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a)) {
                    c0176a.f263d.add(a);
                }
            }
            size = (c0176a == null || c0176a.f265f == null) ? 0 : c0176a.f265f.size();
            while (i < size) {
                a = m500a((Bitmap) c0176a.f265f.get(i), C0179b.FINISH_SHARE);
                if (!TextUtils.isEmpty(a)) {
                    c0176a.f263d.add(a);
                }
                i++;
            }
        } else {
            c0177f.f272d = null;
        }
        if (!c) {
            c0177f.f273n = null;
        }
    }

    private boolean m505a(String str, boolean z) {
        return this.f230b.m599a(str, z);
    }

    private String m506c(String str) {
        HashMap c = this.f230b.m604c(str);
        return (c != null && c.size() > 0 && c.containsKey(RMsgInfo.COL_STATUS) && C2178R.parseInt(String.valueOf(c.get(RMsgInfo.COL_STATUS))) == C2799f.f14282t && c.containsKey(SocialConstants.PARAM_URL)) ? (String) c.get(SocialConstants.PARAM_URL) : null;
    }

    private String m507d(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, SmileConstants.MAX_SHARED_STRING_VALUES);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(toByteArray, 2);
            }
        }
    }

    public String m508a(Bitmap bitmap) {
        String str = null;
        if (this.f231c.m494i()) {
            try {
                str = m500a(bitmap, C0179b.BEFORE_SHARE);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return str;
    }

    public String m509a(String str) {
        String str2 = null;
        if (this.f231c.m494i()) {
            try {
                str2 = m501a(str, C0179b.BEFORE_SHARE);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return str2;
    }

    public String m510a(String str, int i, boolean z, String str2) {
        try {
            if (!this.f231c.m494i() || !this.f231c.m484d()) {
                return str;
            }
            String a;
            if (z) {
                a = m502a(str, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", i, str2);
                if (!(a == null || a.equals(str))) {
                    return a;
                }
            }
            a = m502a(str, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", i, str2);
            return (a == null || a.equals(str)) ? str : a;
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return str;
        }
    }

    public void m511a() {
        try {
            long longValue = this.f231c.m496j().longValue();
            long currentTimeMillis = System.currentTimeMillis();
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(longValue);
            int i = instance.get(5);
            instance.setTimeInMillis(currentTimeMillis);
            int i2 = instance.get(5);
            if (currentTimeMillis - longValue >= MiStatInterface.MAX_UPLOAD_INTERVAL || i != i2) {
                HashMap a = this.f230b.m592a();
                this.f231c.m475a(a.containsKey(UriUtil.LOCAL_RESOURCE_SCHEME) ? "true".equals(String.valueOf(a.get(UriUtil.LOCAL_RESOURCE_SCHEME))) : true);
                if (a != null && a.size() > 0) {
                    this.f231c.m476b(System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m512a(C0171c c0171c) {
        try {
            if (this.f231c.m494i()) {
                if (c0171c instanceof C0173b) {
                    m503a((C0173b) c0171c);
                } else if (c0171c instanceof C0177f) {
                    m504a((C0177f) c0171c);
                }
                if (!this.f231c.m480b()) {
                    c0171c.f241m = null;
                }
                long a = this.f231c.m468a();
                if (a == 0) {
                    a = this.f230b.m600b();
                }
                c0171c.f233e = System.currentTimeMillis() - a;
                this.f230b.m595a(c0171c);
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m513a(HashMap<String, Object> hashMap) {
        try {
            this.f230b.m605c((HashMap) hashMap);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m514a(boolean z) {
        this.f232d = z;
    }

    public boolean m515a(int i, int i2) {
        if (!this.f231c.m494i()) {
            return false;
        }
        try {
            this.f230b.m594a(i, i2);
            return true;
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return false;
        }
    }

    public HashMap<String, Object> m516b(String str) {
        try {
            return this.f230b.m607d(str);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return new HashMap();
        }
    }

    public void m517b() {
        try {
            if (this.f231c.m494i()) {
                this.f231c.m469a(System.currentTimeMillis());
                HashMap c = this.f230b.m603c();
                if (c.containsKey(RMsgInfo.COL_STATUS) && C2178R.parseInt(String.valueOf(c.get(RMsgInfo.COL_STATUS))) == -200) {
                    C0205d.m752a().m737d((String) c.get(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2), new Object[0]);
                    return;
                }
                HashMap hashMap;
                if (c.containsKey("timestamp")) {
                    this.f231c.m472a("service_time", Long.valueOf(System.currentTimeMillis() - C2178R.parseLong(String.valueOf(c.get("timestamp")))));
                }
                if (c.containsKey("switchs")) {
                    hashMap = (HashMap) c.get("switchs");
                    if (hashMap != null) {
                        String valueOf = String.valueOf(hashMap.get("device"));
                        String valueOf2 = String.valueOf(hashMap.get("share"));
                        String valueOf3 = String.valueOf(hashMap.get("auth"));
                        String valueOf4 = String.valueOf(hashMap.get("backflow"));
                        this.f231c.m477b(valueOf);
                        this.f231c.m483d(valueOf2);
                        this.f231c.m481c(valueOf3);
                        this.f231c.m470a(valueOf4);
                    }
                }
                if (c.containsKey("serpaths")) {
                    hashMap = (HashMap) c.get("serpaths");
                    if (hashMap != null) {
                        Object valueOf5 = String.valueOf(hashMap.get("defhost"));
                        Object valueOf6 = String.valueOf(hashMap.get("defport"));
                        if (!(TextUtils.isEmpty(valueOf5) || TextUtils.isEmpty(valueOf6))) {
                            this.f230b.m596a("http://" + valueOf5 + ":" + valueOf6);
                        }
                        HashMap hashMap2 = new HashMap();
                        if (hashMap.containsKey("assigns")) {
                            hashMap = (HashMap) hashMap.get("assigns");
                            if (hashMap == null || hashMap.size() == 0) {
                                this.f230b.m598a(null);
                                return;
                            }
                            for (String str : hashMap.keySet()) {
                                HashMap hashMap3 = (HashMap) hashMap.get(str);
                                Object valueOf7 = String.valueOf(hashMap3.get(C2537j.HOST));
                                valueOf6 = String.valueOf(hashMap3.get("port"));
                                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(valueOf7) || TextUtils.isEmpty(valueOf6))) {
                                    hashMap2.put(str, "http://" + valueOf7 + ":" + valueOf6);
                                }
                            }
                            this.f230b.m598a(hashMap2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m518c() {
        try {
            if (this.f231c.m494i()) {
                String f = this.f231c.m487f();
                HashMap d = this.f230b.m606d();
                String valueOf = String.valueOf(d);
                if (!valueOf.equals(f)) {
                    this.f231c.m488f(valueOf);
                    this.f230b.m602b(d);
                }
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m519d() {
        try {
            if (this.f231c.m494i()) {
                String g = this.f231c.m489g();
                String Base64AES = Data.Base64AES(this.f230b.m608e(), "sdk.sharesdk.sdk");
                if (!Base64AES.equals(g)) {
                    this.f231c.m490g(Base64AES);
                    this.f230b.m601b(Base64AES);
                }
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public void m520e() {
        try {
            if (this.f231c.m494i()) {
                ArrayList g = this.f230b.m610g();
                for (int i = 0; i < g.size(); i++) {
                    C0167c c0167c = (C0167c) g.get(i);
                    if (c0167c.f222b.size() == 1 ? m505a(c0167c.f221a, false) : m505a(m507d(c0167c.f221a), true)) {
                        this.f230b.m597a(c0167c.f222b);
                    }
                }
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
        }
    }

    public HashMap<String, Object> m521f() {
        try {
            return this.f230b.m611h();
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return new HashMap();
        }
    }

    public HashMap<String, Object> m522g() {
        if (!this.f231c.m494i()) {
            return new HashMap();
        }
        try {
            return this.f230b.m609f();
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return new HashMap();
        }
    }

    public void m523h() {
        if (this.f231c.m494i() && !this.f231c.m498k()) {
            try {
                this.f230b.m612i();
                this.f231c.m479b(true);
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
    }
}
