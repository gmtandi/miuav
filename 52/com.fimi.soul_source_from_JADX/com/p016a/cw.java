package com.p016a;

import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;

/* renamed from: com.a.cw */
public class cw {
    final /* synthetic */ cv f806a;
    private AmapLoc f807b;
    private String f808c;

    protected cw(cv cvVar) {
        this.f806a = cvVar;
        this.f807b = null;
        this.f808c = null;
    }

    public AmapLoc m1406a() {
        return this.f807b;
    }

    public void m1407a(AmapLoc amapLoc) {
        this.f807b = amapLoc;
    }

    public void m1408a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f808c = null;
        } else {
            this.f808c = str.replace("##", "#");
        }
    }

    public String m1409b() {
        return this.f808c;
    }
}
