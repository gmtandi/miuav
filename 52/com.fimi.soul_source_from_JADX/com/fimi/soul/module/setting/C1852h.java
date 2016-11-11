package com.fimi.soul.module.setting;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.p096d.C1316b;
import com.fimi.soul.entity.APConfig;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.setting.h */
public class C1852h extends C1848d {
    private EditText f9299a;
    private EditText f9300b;
    private Button f9301c;
    private TextView f9302d;
    private TextView f9303e;
    private TextView f9304f;
    private C1316b f9305g;
    private APConfig f9306h;
    private String f9307i;

    public C1852h(Context context) {
        super(context);
        this.f9305g = new C1316b();
    }

    private void m11728g() {
        this.f9303e = m11708c(m11711f().getString(C1205R.string.version_info), C2915a.f14760f);
        this.f9302d = m11708c(m11711f().getString(C1205R.string.ip_addr), C2915a.f14760f);
        this.f9304f = m11708c(m11711f().getString(C1205R.string.country_code), C2915a.f14760f);
        this.f9299a = m11706b(m11711f().getString(C1205R.string.SSID), C2915a.f14760f);
        this.f9300b = m11706b(m11711f().getString(C1205R.string.relay_password), C2915a.f14760f);
        this.f9301c = m11697a("\u51fa\u5382\u8bbe\u7f6e", "\u6062\u590d", new C1853i(this));
        C1849e a = m11699a(m11711f().getString(C1205R.string.save), m11711f().getString(C1205R.string.reboot));
        a.m11712a().setOnClickListener(new C1855k(this));
        a.m11714b().setOnClickListener(new C1856l(this));
        this.f9305g.m8893b(new C1858n(this));
    }

    private void m11729h() {
        if (this.f9306h != null) {
            this.f9306h.setIpAddr(this.f9302d.getText().toString());
            this.f9306h.setApPwd(this.f9300b.getText().toString());
            this.f9306h.setSsid(this.f9299a.getText().toString());
            C1189f.m8331b().m8336a();
            this.f9305g.m8888a(this.f9306h, new C1885o(this));
        }
    }

    public void m11730a(boolean z, X11RespCmd x11RespCmd) {
    }

    public View m11732c() {
        m11728g();
        return m11704b();
    }
}
