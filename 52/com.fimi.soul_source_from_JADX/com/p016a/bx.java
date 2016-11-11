package com.p016a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.mi.live.openlivesdk.C2116b;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.open.SocialConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.json.JSONObject;

/* renamed from: com.a.bx */
public class bx {
    ca f704a;
    private String f705b;
    private Context f706c;
    private boolean f707d;
    private fj f708e;
    private ServiceConnection f709f;
    private ServiceConnection f710g;
    private Intent f711h;
    private String f712i;
    private String f713j;
    private String f714k;
    private boolean f715l;
    private boolean f716m;
    private String f717n;
    private String f718o;
    private String f719p;
    private String f720q;

    bx(Context context) {
        this.f705b = null;
        this.f706c = null;
        this.f707d = true;
        this.f708e = null;
        this.f709f = null;
        this.f710g = null;
        this.f711h = new Intent();
        this.f712i = "com.autonavi.minimap";
        this.f713j = "com.amap.api.service.AMapService";
        this.f714k = "com.autonavi.minimap.LBSConnectionService";
        this.f715l = false;
        this.f716m = false;
        this.f704a = null;
        this.f717n = "invaid type";
        this.f718o = "empty appkey";
        this.f719p = "refused";
        this.f720q = "failed";
        this.f706c = context;
        try {
            this.f705b = fy.m1904b(cs.m1378a(fn.m1842f(context).getBytes(C1142e.f5201a), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            ev.m1777a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    private AmapLoc m1270a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        byte[] b;
        String str;
        JSONObject jSONObject;
        AmapLoc amapLoc;
        String string;
        if (bundle.containsKey(SharedPref.KEY)) {
            try {
                b = cs.m1382b(fy.m1905b(bundle.getString(SharedPref.KEY)), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n");
            } catch (Throwable th) {
                ev.m1777a(th, "ConnectionServiceManager", "parseData part");
            }
            if (bundle.containsKey("result")) {
                return null;
            }
            try {
                str = new String(cs.m1379a(b, fy.m1905b(bundle.getString("result"))), "utf-8");
                if (str != null) {
                    return null;
                }
                jSONObject = new JSONObject(str);
                if (jSONObject.has(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
                    amapLoc = new AmapLoc(jSONObject);
                    amapLoc.m5328c("lbs");
                    amapLoc.m5313a(7);
                    if ("WGS84".equals(amapLoc.m5345l()) && ev.m1778a(amapLoc.m5339i(), amapLoc.m5337h())) {
                        DPoint a = fi.m1819a(this.f706c, amapLoc.m5337h(), amapLoc.m5339i());
                        amapLoc.m5319b(a.getLatitude());
                        amapLoc.m5311a(a.getLongitude());
                    }
                    return amapLoc;
                }
                string = jSONObject.getString(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
                if (this.f717n.equals(string)) {
                    this.f707d = false;
                }
                if (this.f718o.equals(string)) {
                    this.f707d = false;
                }
                if (this.f719p.equals(string)) {
                    this.f707d = false;
                }
                return this.f720q.equals(string) ? null : null;
            } catch (Throwable th2) {
                ev.m1777a(th2, bx.class.getName(), "parseData");
                return null;
            }
        }
        b = null;
        if (bundle.containsKey("result")) {
            return null;
        }
        str = new String(cs.m1379a(b, fy.m1905b(bundle.getString("result"))), "utf-8");
        if (str != null) {
            return null;
        }
        jSONObject = new JSONObject(str);
        if (jSONObject.has(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2)) {
            amapLoc = new AmapLoc(jSONObject);
            amapLoc.m5328c("lbs");
            amapLoc.m5313a(7);
            DPoint a2 = fi.m1819a(this.f706c, amapLoc.m5337h(), amapLoc.m5339i());
            amapLoc.m5319b(a2.getLatitude());
            amapLoc.m5311a(a2.getLongitude());
            return amapLoc;
        }
        string = jSONObject.getString(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
        if (this.f717n.equals(string)) {
            this.f707d = false;
        }
        if (this.f718o.equals(string)) {
            this.f707d = false;
        }
        if (this.f719p.equals(string)) {
            this.f707d = false;
        }
        if (this.f720q.equals(string)) {
        }
    }

    public void m1271a() {
        m1274c();
        this.f706c = null;
        this.f708e = null;
        this.f709f = null;
        this.f710g = null;
        if (this.f704a != null) {
            this.f704a.m1267a(-1);
        }
        this.f707d = true;
        this.f715l = false;
        this.f716m = false;
    }

    public void m1272a(ca caVar) {
        try {
            this.f704a = caVar;
            if (this.f709f == null) {
                this.f709f = new by(this, caVar);
            }
            if (this.f710g == null) {
                this.f710g = new bz(this);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "ConnectionServiceManager", "init");
        }
    }

    boolean m1273b() {
        try {
            if (dj.m1483p()) {
                this.f711h.putExtra(C2116b.f11123f, this.f705b);
                this.f711h.setComponent(new ComponentName(this.f712i, this.f713j));
                this.f715l = this.f706c.bindService(this.f711h, this.f709f, 1);
            }
            if (dj.m1484q()) {
                Intent intent = new Intent();
                intent.putExtra(C2116b.f11123f, this.f705b);
                intent.setComponent(new ComponentName(this.f712i, this.f714k));
                this.f716m = this.f706c.bindService(intent, this.f710g, 1);
            }
            return this.f715l && this.f716m;
        } catch (Throwable th) {
            ev.m1777a(th, "ConnectionServiceManager", "bindService");
            return false;
        }
    }

    void m1274c() {
        try {
            if (this.f715l) {
                this.f706c.unbindService(this.f709f);
            }
            if (this.f716m) {
                this.f706c.unbindService(this.f710g);
            }
        } catch (Throwable e) {
            ev.m1777a(e, "ConnectionServiceManager", "unbindService");
        } catch (Throwable e2) {
            ev.m1777a(e2, "ConnectionServiceManager", "unbindService");
        }
        this.f708e = null;
    }

    AmapLoc m1275d() {
        AmapLoc amapLoc = null;
        try {
            if (this.f707d && this.f715l) {
                Bundle bundle = new Bundle();
                bundle.putString(SocialConstants.PARAM_TYPE, "corse");
                bundle.putString(C2116b.f11123f, this.f705b);
                this.f708e.m1829a(bundle);
                if (bundle.size() >= 1) {
                    amapLoc = m1270a(bundle);
                }
            }
        } catch (Throwable e) {
            ev.m1777a(e, "ConnectionServiceManager", "sendCommand");
        } catch (Throwable e2) {
            ev.m1777a(e2, "ConnectionServiceManager", "sendCommand");
        }
        return amapLoc;
    }
}
