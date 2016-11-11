package com.fimi.soul.biz.p100g;

import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1584h;
import java.util.Observable;
import java.util.Observer;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.g.e */
class C1338e implements Observer {
    final /* synthetic */ C1336c f5975a;

    C1338e(C1336c c1336c) {
        this.f5975a = c1336c;
    }

    public void update(Observable observable, Object obj) {
        this.f5975a.m8979a();
        switch (this.f5975a.f5961e) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f5975a.f5957a.getCurrentFlightModel() == 7 && this.f5975a.f5961e != 2) {
                    this.f5975a.f5958b.m8985a(C1205R.string.comtohome);
                    this.f5975a.f5967k.m8992a(this.f5975a.f5959c.f6507c.getString(C1205R.string.comtohome));
                    this.f5975a.f5959c.m9589a(C1584h.NOTIDRONEFLOOR);
                } else if (this.f5975a.f5957a.getCurrentFlightModel() == 3) {
                    this.f5975a.f5958b.m8985a(C1205R.string.comalding);
                    this.f5975a.f5967k.m8992a(this.f5975a.f5959c.f6507c.getString(C1205R.string.comalding));
                    this.f5975a.f5959c.m9589a(C1584h.NOTIDRONEFLOOR);
                } else {
                    this.f5975a.f5958b.m8985a(C1205R.string.comtakeoff);
                    this.f5975a.f5967k.m8992a(this.f5975a.f5959c.f6507c.getString(C1205R.string.comtakeoff));
                }
            default:
        }
    }
}
