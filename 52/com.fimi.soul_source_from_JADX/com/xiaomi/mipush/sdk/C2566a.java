package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.openauth.BuildConfig;
import com.xiaomi.push.service.C2651g;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.xiaomi.mipush.sdk.a */
public class C2566a {
    private static C2566a f12872a;
    private Context f12873b;
    private C2565a f12874c;

    /* renamed from: com.xiaomi.mipush.sdk.a.a */
    class C2565a {
        public String f12861a;
        public String f12862b;
        public String f12863c;
        public String f12864d;
        public String f12865e;
        public String f12866f;
        public String f12867g;
        public boolean f12868h;
        public boolean f12869i;
        public int f12870j;
        final /* synthetic */ C2566a f12871k;

        private C2565a(C2566a c2566a) {
            this.f12871k = c2566a;
            this.f12868h = true;
            this.f12869i = false;
            this.f12870j = 1;
        }

        private String m14605d() {
            return C2566a.m14616a(this.f12871k.f12873b, this.f12871k.f12873b.getPackageName());
        }

        public void m14606a(int i) {
            this.f12870j = i;
        }

        public void m14607a(String str, String str2) {
            this.f12863c = str;
            this.f12864d = str2;
            this.f12866f = C2651g.m15028e(this.f12871k.f12873b);
            this.f12865e = m14605d();
            this.f12868h = true;
            Editor edit = this.f12871k.m14633j().edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f12866f);
            edit.putString("vName", m14605d());
            edit.putBoolean("valid", true);
            edit.commit();
        }

        public void m14608a(String str, String str2, String str3) {
            this.f12861a = str;
            this.f12862b = str2;
            this.f12867g = str3;
            Editor edit = this.f12871k.m14633j().edit();
            edit.putString("appId", this.f12861a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        public void m14609a(boolean z) {
            this.f12869i = z;
        }

        public boolean m14610a() {
            return m14612b(this.f12861a, this.f12862b);
        }

        public void m14611b() {
            this.f12871k.m14633j().edit().clear().commit();
            this.f12861a = null;
            this.f12862b = null;
            this.f12863c = null;
            this.f12864d = null;
            this.f12866f = null;
            this.f12865e = null;
            this.f12868h = false;
            this.f12869i = false;
            this.f12870j = 1;
        }

        public boolean m14612b(String str, String str2) {
            return TextUtils.equals(this.f12861a, str) && TextUtils.equals(this.f12862b, str2) && !TextUtils.isEmpty(this.f12863c) && !TextUtils.isEmpty(this.f12864d) && TextUtils.equals(this.f12866f, C2651g.m15028e(this.f12871k.f12873b));
        }

        public void m14613c() {
            this.f12868h = false;
            this.f12871k.m14633j().edit().putBoolean("valid", this.f12868h).commit();
        }
    }

    private C2566a(Context context) {
        this.f12873b = context;
        m14617o();
    }

    public static C2566a m14615a(Context context) {
        if (f12872a == null) {
            f12872a = new C2566a(context);
        }
        return f12872a;
    }

    public static String m14616a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, Opcodes.ACC_ENUM);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : BuildConfig.VERSION_NAME;
    }

    private void m14617o() {
        this.f12874c = new C2565a();
        SharedPreferences j = m14633j();
        this.f12874c.f12861a = j.getString("appId", null);
        this.f12874c.f12862b = j.getString("appToken", null);
        this.f12874c.f12863c = j.getString("regId", null);
        this.f12874c.f12864d = j.getString("regSec", null);
        this.f12874c.f12866f = j.getString("devId", null);
        if (!TextUtils.isEmpty(this.f12874c.f12866f) && this.f12874c.f12866f.startsWith("a-")) {
            this.f12874c.f12866f = C2651g.m15028e(this.f12873b);
            j.edit().putString("devId", this.f12874c.f12866f).commit();
        }
        this.f12874c.f12865e = j.getString("vName", null);
        this.f12874c.f12868h = j.getBoolean("valid", true);
        this.f12874c.f12869i = j.getBoolean("paused", false);
        this.f12874c.f12870j = j.getInt("envType", 1);
        this.f12874c.f12867g = j.getString("regResource", null);
    }

    public void m14618a(int i) {
        this.f12874c.m14606a(i);
        m14633j().edit().putInt("envType", i).commit();
    }

    public void m14619a(String str) {
        Editor edit = m14633j().edit();
        edit.putString("vName", str);
        edit.commit();
        this.f12874c.f12865e = str;
    }

    public void m14620a(String str, String str2, String str3) {
        this.f12874c.m14608a(str, str2, str3);
    }

    public void m14621a(boolean z) {
        this.f12874c.m14609a(z);
        m14633j().edit().putBoolean("paused", z).commit();
    }

    public boolean m14622a() {
        return !TextUtils.equals(C2566a.m14616a(this.f12873b, this.f12873b.getPackageName()), this.f12874c.f12865e);
    }

    public boolean m14623a(String str, String str2) {
        return this.f12874c.m14612b(str, str2);
    }

    public void m14624b(String str, String str2) {
        this.f12874c.m14607a(str, str2);
    }

    public boolean m14625b() {
        if (this.f12874c.m14610a()) {
            return true;
        }
        C2463b.m14123a("Don't send message before initialization succeeded!");
        return false;
    }

    public String m14626c() {
        return this.f12874c.f12861a;
    }

    public String m14627d() {
        return this.f12874c.f12862b;
    }

    public String m14628e() {
        return this.f12874c.f12863c;
    }

    public String m14629f() {
        return this.f12874c.f12864d;
    }

    public String m14630g() {
        return this.f12874c.f12867g;
    }

    public void m14631h() {
        this.f12874c.m14611b();
    }

    public boolean m14632i() {
        return this.f12874c.m14610a();
    }

    public SharedPreferences m14633j() {
        return this.f12873b.getSharedPreferences("mipush", 0);
    }

    public void m14634k() {
        this.f12874c.m14613c();
    }

    public boolean m14635l() {
        return this.f12874c.f12869i;
    }

    public int m14636m() {
        return this.f12874c.f12870j;
    }

    public boolean m14637n() {
        return !this.f12874c.f12868h;
    }
}
