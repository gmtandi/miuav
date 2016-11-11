package com.baidu.tts.p044g.p046b;

import android.content.Context;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.p025i.C0673b;
import com.baidu.tts.p041e.C0794g;
import com.tencent.connect.common.Constants;
import java.lang.ref.WeakReference;
import java.util.Hashtable;

/* renamed from: com.baidu.tts.g.b.b */
public class C0809b implements C0673b {
    private static volatile C0809b f4560a;
    private Hashtable<WeakReference<Context>, C0808a> f4561b;
    private WeakReference<Context> f4562c;
    private Hashtable<String, Object> f4563d;

    static {
        f4560a = null;
    }

    private C0809b() {
        this.f4561b = new Hashtable();
        this.f4563d = new Hashtable();
        this.f4563d.put(C0794g.CTP.m6741a(), Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        this.f4563d.put(C0794g.VERSION.m6741a(), "v2.2.7");
    }

    public static C0809b m6769a() {
        if (f4560a == null) {
            synchronized (C0809b.class) {
                if (f4560a == null) {
                    f4560a = new C0809b();
                }
            }
        }
        return f4560a;
    }

    public C0808a m6770a(WeakReference<Context> weakReference) {
        if (weakReference == null) {
            return null;
        }
        C0808a c0808a = (C0808a) this.f4561b.get(weakReference);
        if (c0808a != null) {
            return c0808a;
        }
        c0808a = new C0808a(weakReference);
        this.f4561b.put(weakReference, c0808a);
        return c0808a;
    }

    public String m6771a(String str) {
        try {
            return (String) this.f4563d.get(str);
        } catch (Exception e) {
            return null;
        }
    }

    public void m6772a(Context context) {
        this.f4562c = new WeakReference(context);
    }

    public TtsError m6773b() {
        return null;
    }

    public void m6774c() {
    }

    public void m6775d() {
    }

    public void m6776e() {
    }

    public void m6777f() {
        if (this.f4561b != null) {
            this.f4561b.clear();
        }
        this.f4562c = null;
    }

    public C0808a m6778g() {
        return m6770a(this.f4562c);
    }

    public Context m6779h() {
        return (Context) this.f4562c.get();
    }

    public String m6780i() {
        String str = null;
        try {
            C0808a g = m6778g();
            if (g != null) {
                str = g.m6767a();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public String m6781j() {
        return m6771a(C0794g.VERSION.m6741a());
    }
}
