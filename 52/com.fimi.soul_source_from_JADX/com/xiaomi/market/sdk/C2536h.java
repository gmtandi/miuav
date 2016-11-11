package com.xiaomi.market.sdk;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TreeMap;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.h */
public class C2536h {
    final /* synthetic */ C2530c f12834G;
    private TreeMap f12835R;

    public C2536h(C2530c c2530c) {
        this(c2530c, true);
    }

    public C2536h(C2530c c2530c, boolean z) {
        this.f12834G = c2530c;
        this.f12835R = new TreeMap();
        if (z) {
            c2530c.f12815x = this;
        }
    }

    public C2536h m14518a(String str, boolean z) {
        if (z) {
            this.f12835R.put(str, "true");
        } else {
            this.f12835R.put(str, "false");
        }
        return this;
    }

    public C2536h m14519d(String str, String str2) {
        Object obj;
        if (str2 == null) {
            obj = C2915a.f14760f;
        }
        this.f12835R.put(str, obj);
        return this;
    }

    public String get(String str) {
        return (String) this.f12835R.get(str);
    }

    public boolean isEmpty() {
        return this.f12835R.isEmpty();
    }

    public TreeMap m14520j() {
        return this.f12835R;
    }

    public String toString() {
        if (this.f12835R.isEmpty()) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.f12835R.keySet()) {
            stringBuilder.append(str);
            stringBuilder.append("=");
            try {
                stringBuilder.append(URLEncoder.encode((String) this.f12835R.get(str), C1142e.f5201a));
            } catch (UnsupportedEncodingException e) {
            }
            stringBuilder.append("&");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }
}
