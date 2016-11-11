package com.p016a;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.a.dg */
class dg extends DefaultHandler {
    public AmapLoc f843a;
    final /* synthetic */ df f844b;
    private String f845c;

    private dg(df dfVar) {
        this.f844b = dfVar;
        this.f843a = new AmapLoc();
        this.f845c = C2915a.f14760f;
    }

    public void characters(char[] cArr, int i, int i2) {
        this.f845c = String.valueOf(cArr, i, i2);
    }

    public void endElement(String str, String str2, String str3) {
        if (str2.equals("retype")) {
            this.f843a.m5336g(this.f845c);
        } else if (str2.equals("rdesc")) {
            this.f843a.m5338h(this.f845c);
        } else if (str2.equals("adcode")) {
            this.f843a.m5344k(this.f845c);
        } else if (str2.equals("citycode")) {
            this.f843a.m5340i(this.f845c);
        } else if (str2.equals("radius")) {
            try {
                this.f843a.m5312a(Float.parseFloat(this.f845c));
            } catch (Throwable th) {
                ev.m1777a(th, "parser", "endElement3");
                this.f843a.m5312a(3891.0f);
            }
        } else if (str2.equals("cenx")) {
            try {
                this.f843a.m5311a(Double.parseDouble(this.f845c));
            } catch (Throwable th2) {
                ev.m1777a(th2, "parser", "endElement2");
                this.f843a.m5311a(0.0d);
            }
        } else if (str2.equals("ceny")) {
            try {
                this.f843a.m5319b(Double.parseDouble(this.f845c));
            } catch (Throwable th22) {
                ev.m1777a(th22, "parser", "endElement1");
                this.f843a.m5319b(0.0d);
            }
        } else if (str2.equals(SocialConstants.PARAM_APP_DESC)) {
            this.f843a.m5342j(this.f845c);
        } else if (str2.equals(DistrictSearchQuery.KEYWORDS_COUNTRY)) {
            this.f843a.m5346l(this.f845c);
        } else if (str2.equals(DistrictSearchQuery.KEYWORDS_PROVINCE)) {
            this.f843a.m5348m(this.f845c);
        } else if (str2.equals(DistrictSearchQuery.KEYWORDS_CITY)) {
            this.f843a.m5350n(this.f845c);
        } else if (str2.equals(DistrictSearchQuery.KEYWORDS_DISTRICT)) {
            this.f843a.m5352o(this.f845c);
        } else if (str2.equals("road")) {
            this.f843a.m5354p(this.f845c);
        } else if (str2.equals("street")) {
            this.f843a.m5356q(this.f845c);
        } else if (str2.equals("number")) {
            this.f843a.m5358r(this.f845c);
        } else if (str2.equals("poiname")) {
            this.f843a.m5360s(this.f845c);
        } else if (str2.equals("BIZ")) {
            if (this.f843a.m5308E() == null) {
                this.f843a.m5316a(new JSONObject());
            }
            try {
                this.f843a.m5308E().put("BIZ", this.f845c);
            } catch (Throwable th222) {
                ev.m1777a(th222, "parser", "endElement");
            }
        } else if (str2.equals("cens")) {
            this.f843a.m5364u(this.f845c);
        } else if (str2.equals("pid")) {
            this.f843a.m5366v(this.f845c);
        } else if (str2.equals("flr")) {
            this.f843a.m5368w(this.f845c);
        } else if (str2.equals("coord")) {
            if (TextUtils.isEmpty(ev.f1148g)) {
                ev.f1148g = this.f845c;
            }
            this.f843a.m5370x(this.f845c);
        } else if (str2.equals("mcell")) {
            this.f843a.m5372y(this.f845c);
        } else if (!str2.equals("gkeyloc") && !str2.equals("gkeygeo")) {
            if (str2.equals("apiTime")) {
                this.f843a.m5314a(Long.parseLong(this.f845c));
            } else if (str2.equals("aoiname")) {
                this.f843a.m5362t(this.f845c);
            }
        }
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        this.f845c = C2915a.f14760f;
    }
}
