package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;

/* renamed from: cn.sharesdk.sina.weibo.j */
public class C0221j {
    private static final Uri f420a;
    private static C0221j f421b;
    private static C0220a f422c;
    private Context f423d;

    /* renamed from: cn.sharesdk.sina.weibo.j.a */
    public class C0220a {
        private String f418a;
        private int f419b;

        private void m827a(int i) {
            this.f419b = i;
        }

        private void m830a(String str) {
            this.f418a = str;
        }

        public String m831a() {
            return this.f418a;
        }

        public int m832b() {
            return this.f419b;
        }

        public boolean m833c() {
            return !TextUtils.isEmpty(this.f418a) && this.f419b > 0;
        }

        public String toString() {
            return "WeiboInfo: PackageName = " + this.f418a + ", supportApi = " + this.f419b;
        }
    }

    static {
        f420a = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
        f422c = null;
    }

    private C0221j(Context context) {
        this.f423d = context.getApplicationContext();
    }

    public static synchronized C0221j m834a(Context context) {
        C0221j c0221j;
        synchronized (C0221j.class) {
            if (f421b == null) {
                f421b = new C0221j(context);
            }
            c0221j = f421b;
        }
        return c0221j;
    }

    public static boolean m835a(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        try {
            return C0221j.m836a(context.getPackageManager().getPackageInfo(str, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    private static boolean m836a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature toByteArray : signatureArr) {
            if (str.equals(Data.MD5(toByteArray.toByteArray()))) {
                C0205d.m752a().m737d("check pass", new Object[0]);
                return true;
            }
        }
        return false;
    }

    private C0220a m837b(Context context) {
        Object obj = 1;
        C0220a c = m838c(context);
        C0220a d = m839d(context);
        Object obj2 = c != null ? 1 : null;
        if (d == null) {
            obj = null;
        }
        return (obj2 == null || obj == null) ? obj2 == null ? obj != null ? d : null : c : c.m832b() >= d.m832b() ? c : d;
    }

    private C0220a m838c(Context context) {
        Cursor query;
        Exception e;
        Throwable th;
        try {
            query = context.getContentResolver().query(f420a, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex("package");
                if (query.moveToFirst()) {
                    int i = -1;
                    try {
                        columnIndex = Integer.parseInt(query.getString(columnIndex));
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                        columnIndex = i;
                    }
                    String string = query.getString(columnIndex2);
                    if (!TextUtils.isEmpty(string) && C0221j.m835a(context, string)) {
                        C0220a c0220a = new C0220a();
                        c0220a.m830a(string);
                        c0220a.m827a(columnIndex);
                        if (query == null) {
                            return c0220a;
                        }
                        query.close();
                        return c0220a;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    C0205d.m752a().m740e(e.getMessage(), new Object[0]);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            return null;
        } catch (Exception e4) {
            e = e4;
            query = null;
            C0205d.m752a().m740e(e.getMessage(), new Object[0]);
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private C0220a m839d(Context context) {
        C0220a c0220a = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                C0220a a;
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.applicationInfo.packageName))) {
                    a = m840a(resolveInfo.serviceInfo.applicationInfo.packageName);
                    if (a != null) {
                        if (c0220a != null) {
                            if (c0220a.m832b() < a.m832b()) {
                            }
                        }
                        c0220a = a;
                    }
                }
                a = c0220a;
                c0220a = a;
            }
        }
        return c0220a;
    }

    public C0220a m840a(String str) {
        NameNotFoundException e;
        Throwable th;
        Exception e2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InputStream open;
        try {
            byte[] bArr = new byte[63];
            open = this.f423d.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    int read = open.read(bArr, 0, Opcodes.ACC_SYNTHETIC);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && C0221j.m835a(this.f423d, str)) {
                    int optInt = new JSONObject(stringBuilder.toString()).optInt("support_api", -1);
                    C0220a c0220a = new C0220a();
                    c0220a.m830a(str);
                    c0220a.m827a(optInt);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            C0205d.m752a().m740e(e3.getMessage(), new Object[0]);
                        }
                    }
                    return c0220a;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e4) {
                        C0205d.m752a().m740e(e4.getMessage(), new Object[0]);
                        return null;
                    }
                }
            } catch (NameNotFoundException e5) {
                e = e5;
                try {
                    C0205d.m752a().m740e(e.getMessage(), new Object[0]);
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e42) {
                        C0205d.m752a().m740e(e42.getMessage(), new Object[0]);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e422) {
                            C0205d.m752a().m740e(e422.getMessage(), new Object[0]);
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e2 = e6;
                C0205d.m752a().m740e(e2.getMessage(), new Object[0]);
                if (open != null) {
                    return null;
                }
                try {
                    open.close();
                    return null;
                } catch (IOException e4222) {
                    C0205d.m752a().m740e(e4222.getMessage(), new Object[0]);
                    return null;
                }
            }
        } catch (NameNotFoundException e7) {
            e = e7;
            open = null;
            C0205d.m752a().m740e(e.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Exception e8) {
            e2 = e8;
            open = null;
            C0205d.m752a().m740e(e2.getMessage(), new Object[0]);
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public synchronized String m841a() {
        if (f422c == null) {
            f422c = m837b(this.f423d);
        }
        return f422c.m831a();
    }

    public synchronized int m842b() {
        int b;
        if (f422c == null) {
            f422c = m837b(this.f423d);
        }
        b = (f422c == null || !f422c.m833c()) ? -1 : f422c.m832b();
        return b;
    }
}
