package com.tencent.open.p134b;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: com.tencent.open.b.b */
public class C2339b implements Serializable {
    public final HashMap<String, String> f12024a;

    public C2339b(Bundle bundle) {
        this.f12024a = new HashMap();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                this.f12024a.put(str, bundle.getString(str));
            }
        }
    }
}
