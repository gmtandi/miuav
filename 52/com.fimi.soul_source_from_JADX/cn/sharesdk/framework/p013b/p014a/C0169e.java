package cn.sharesdk.framework.p013b.p014a;

import android.content.Context;
import android.text.TextUtils;
import com.mob.tools.utils.SharePrefrenceHelper;

/* renamed from: cn.sharesdk.framework.b.a.e */
public class C0169e {
    private static C0169e f226c;
    private Context f227a;
    private SharePrefrenceHelper f228b;

    private C0169e(Context context) {
        this.f227a = context.getApplicationContext();
        this.f228b = new SharePrefrenceHelper(this.f227a);
        this.f228b.open("share_sdk", 1);
    }

    public static C0169e m467a(Context context) {
        if (f226c == null) {
            f226c = new C0169e(context.getApplicationContext());
        }
        return f226c;
    }

    public long m468a() {
        return this.f228b.getLong("service_time");
    }

    public void m469a(long j) {
        this.f228b.putLong("device_time", Long.valueOf(j));
    }

    public void m470a(String str) {
        this.f228b.putString("trans_short_link", str);
    }

    public void m471a(String str, int i) {
        this.f228b.putInt(str, Integer.valueOf(i));
    }

    public void m472a(String str, Long l) {
        this.f228b.putLong(str, l);
    }

    public void m473a(String str, Object obj) {
        this.f228b.put(str, obj);
    }

    public void m474a(String str, String str2) {
        this.f228b.putString("buffered_snsconf_" + str, str2);
    }

    public void m475a(boolean z) {
        this.f228b.putBoolean("connect_server", Boolean.valueOf(z));
    }

    public void m476b(long j) {
        this.f228b.putLong("connect_server_time", Long.valueOf(j));
    }

    public void m477b(String str) {
        this.f228b.putString("upload_device_info", str);
    }

    public void m478b(String str, String str2) {
        this.f228b.putString(str, str2);
    }

    public void m479b(boolean z) {
        this.f228b.putBoolean("upload_device_duid", Boolean.valueOf(z));
    }

    public boolean m480b() {
        Object string = this.f228b.getString("upload_device_info");
        return TextUtils.isEmpty(string) ? true : Boolean.parseBoolean(string);
    }

    public void m481c(String str) {
        this.f228b.putString("upload_user_info", str);
    }

    public boolean m482c() {
        Object string = this.f228b.getString("upload_user_info");
        return TextUtils.isEmpty(string) ? true : Boolean.parseBoolean(string);
    }

    public void m483d(String str) {
        this.f228b.putString("upload_share_content", str);
    }

    public boolean m484d() {
        Object string = this.f228b.getString("trans_short_link");
        return TextUtils.isEmpty(string) ? false : Boolean.parseBoolean(string);
    }

    public int m485e() {
        String string = this.f228b.getString("upload_share_content");
        return "true".equals(string) ? 1 : "false".equals(string) ? -1 : 0;
    }

    public String m486e(String str) {
        return this.f228b.getString("buffered_snsconf_" + str);
    }

    public String m487f() {
        return this.f228b.getString("device_data");
    }

    public void m488f(String str) {
        this.f228b.putString("device_data", str);
    }

    public String m489g() {
        return this.f228b.getString("device_ext_data");
    }

    public void m490g(String str) {
        this.f228b.putString("device_ext_data", str);
    }

    public Long m491h() {
        return Long.valueOf(this.f228b.getLong("device_time"));
    }

    public String m492h(String str) {
        return this.f228b.getString(str);
    }

    public long m493i(String str) {
        return this.f228b.getLong(str);
    }

    public boolean m494i() {
        return this.f228b.getBoolean("connect_server");
    }

    public int m495j(String str) {
        return this.f228b.getInt(str);
    }

    public Long m496j() {
        return Long.valueOf(this.f228b.getLong("connect_server_time"));
    }

    public Object m497k(String str) {
        return this.f228b.get(str);
    }

    public boolean m498k() {
        return this.f228b.getBoolean("upload_device_duid");
    }
}
