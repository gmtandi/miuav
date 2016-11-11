package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.f */
public class C0186f {
    private Platform f315a;
    private Context f316b;
    private PlatformDb f317c;
    private C0152a f318d;
    private int f319e;
    private int f320f;
    private boolean f321g;
    private boolean f322h;
    private boolean f323i;

    public C0186f(Platform platform, Context context) {
        this.f322h = true;
        this.f315a = platform;
        this.f316b = context;
        String name = platform.getName();
        this.f317c = new PlatformDb(context, name, platform.getVersion());
        m639a(name);
        this.f318d = new C0152a();
    }

    private boolean m629j() {
        boolean z = false;
        if (!ShareSDK.m406a()) {
            return true;
        }
        String a;
        if (ShareSDK.m409b()) {
            a = m632a(this.f315a.getPlatformId(), "covert_url", null);
            if (a != null) {
                a.trim();
            }
            if (!"false".equals(a)) {
                z = true;
            }
            this.f322h = z;
            this.f315a.setNetworkDevinfo();
            return true;
        }
        try {
            HashMap hashMap = new HashMap();
            if (!ShareSDK.m407a(hashMap)) {
                return false;
            }
            if (ShareSDK.m410b(hashMap)) {
                a = m632a(this.f315a.getPlatformId(), "covert_url", null);
                if (a != null) {
                    a.trim();
                }
                this.f322h = !"false".equals(a);
                this.f315a.setNetworkDevinfo();
                return true;
            }
            C0205d.m752a().m743i("Failed to parse network dev-info: " + new Hashon().fromHashMap(hashMap), new Object[0]);
            return false;
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return false;
        }
    }

    private String m630k() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if ("TencentWeibo".equals(this.f315a.getName())) {
                C0205d.m752a().m743i("user id %s ==>>", m654g().getUserName());
                stringBuilder.append(Data.urlEncode(m654g().getUserName(), "utf-8"));
            } else {
                stringBuilder.append(Data.urlEncode(m654g().getUserId(), "utf-8"));
            }
            stringBuilder.append("|").append(Data.urlEncode(m654g().get("secretType"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m654g().get("gender"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m654g().get("birthday"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m654g().get("educationJSONArrayStr"), "utf-8"));
            stringBuilder.append("|").append(Data.urlEncode(m654g().get("workJSONArrayStr"), "utf-8"));
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
        return stringBuilder.toString();
    }

    public int m631a() {
        return this.f319e;
    }

    public String m632a(int i, String str, String str2) {
        Object a = ShareSDK.m400a(i, str);
        return (TextUtils.isEmpty(a) || "null".equals(a)) ? this.f315a.getDevinfo(this.f315a.getName(), str2) : a;
    }

    public String m633a(Bitmap bitmap) {
        return ShareSDK.m401a(bitmap);
    }

    public String m634a(String str, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.f322h) {
            C0205d.m752a().m743i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else if (TextUtils.isEmpty(str)) {
            C0205d.m752a().m743i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        } else {
            str = ShareSDK.m403a(str, z, this.f315a.getPlatformId(), m630k());
            C0205d.m752a().m743i("getShortLintk use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            return str;
        }
    }

    public void m635a(int i, int i2, String str) {
        m648c(2, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    public void m636a(int i, Object obj) {
        this.f318d.m434a(this.f315a, i, obj);
    }

    public void m637a(ShareParams shareParams) {
        if (shareParams != null) {
            m648c(9, shareParams);
        } else if (this.f318d != null) {
            this.f318d.onError(this.f315a, 9, new NullPointerException());
        }
    }

    public void m638a(PlatformActionListener platformActionListener) {
        this.f318d.m436a(platformActionListener);
    }

    public void m639a(String str) {
        try {
            this.f319e = C2178R.parseInt(String.valueOf(ShareSDK.m408b(str, "Id")).trim());
        } catch (Throwable th) {
            if (!(this.f315a instanceof CustomPlatform)) {
                C0205d.m752a().m737d(this.f315a.getName() + " failed to parse Id, this will cause method getId() always returens 0", new Object[0]);
            }
        }
        try {
            this.f320f = C2178R.parseInt(String.valueOf(ShareSDK.m408b(str, "SortId")).trim());
        } catch (Throwable th2) {
            if (!(this.f315a instanceof CustomPlatform)) {
                C0205d.m752a().m737d(this.f315a.getName() + " failed to parse SortId, this won't cause any problem, don't worry", new Object[0]);
            }
        }
        String b = ShareSDK.m408b(str, "Enable");
        if (b == null) {
            this.f323i = true;
            if (!(this.f315a instanceof CustomPlatform)) {
                C0205d.m752a().m737d(this.f315a.getName() + " failed to parse Enable, this will cause platform always be enable", new Object[0]);
            }
        } else {
            this.f323i = "true".equals(b.trim());
        }
        this.f315a.initDevInfo(str);
    }

    public void m640a(String str, int i, int i2) {
        m648c(7, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), str});
    }

    public void m641a(String str, String str2, short s, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        m648c(655360 | s, new Object[]{str, str2, hashMap, hashMap2});
    }

    public void m642a(boolean z) {
        this.f321g = z;
    }

    public void m643a(String[] strArr) {
        new C0188h(this, strArr).start();
    }

    public int m644b() {
        return this.f320f;
    }

    protected void m645b(int i, Object obj) {
        Object[] objArr;
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f318d != null) {
                    this.f318d.onComplete(this.f315a, 1, null);
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                objArr = (Object[]) obj;
                this.f315a.getFriendList(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
            case Type.FLOAT /*6*/:
                this.f315a.follow((String) obj);
            case Type.LONG /*7*/:
                objArr = (Object[]) obj;
                this.f315a.timeline(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
            case Type.DOUBLE /*8*/:
                this.f315a.userInfor(obj == null ? null : (String) obj);
            case Type.ARRAY /*9*/:
                ShareParams shareParams = (ShareParams) obj;
                HashMap toMap = shareParams.toMap();
                for (Field field : shareParams.getClass().getFields()) {
                    if (toMap.get(field.getName()) == null) {
                        Object obj2;
                        field.setAccessible(true);
                        try {
                            obj2 = field.get(shareParams);
                        } catch (Throwable th) {
                            C0205d.m752a().m750w(th);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            toMap.put(field.getName(), obj2);
                        }
                    }
                }
                if (this.f318d instanceof C0152a) {
                    this.f318d.m435a(this.f315a, shareParams);
                }
                this.f315a.doShare(shareParams);
            default:
                objArr = (Object[]) obj;
                this.f315a.doCustomerProtocol(String.valueOf(objArr[0]), String.valueOf(objArr[1]), i, (HashMap) objArr[2], (HashMap) objArr[3]);
        }
    }

    public void m646b(String str) {
        m648c(6, str);
    }

    public PlatformActionListener m647c() {
        return this.f318d.m433a();
    }

    protected void m648c(int i, Object obj) {
        new C0187g(this, i, obj).start();
    }

    public void m649c(String str) {
        m648c(8, str);
    }

    public String m650d(String str) {
        return ShareSDK.m402a(str);
    }

    public boolean m651d() {
        return this.f317c.isValid();
    }

    public boolean m652e() {
        return this.f321g;
    }

    public boolean m653f() {
        return this.f323i;
    }

    public PlatformDb m654g() {
        return this.f317c;
    }

    public void m655h() {
        this.f317c.removeAccount();
    }

    protected PlatformActionListener m656i() {
        return this.f318d;
    }
}
