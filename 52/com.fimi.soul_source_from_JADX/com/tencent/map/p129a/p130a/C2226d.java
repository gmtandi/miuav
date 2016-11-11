package com.tencent.map.p129a.p130a;

import com.tencent.map.p131b.C2255h;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.Iterator;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.tencent.map.a.a.d */
public class C2226d {
    private long f11534A;
    public int f11535a;
    public double f11536b;
    public double f11537c;
    public double f11538d;
    public double f11539e;
    public double f11540f;
    public double f11541g;
    public int f11542h;
    public String f11543i;
    public String f11544j;
    public String f11545k;
    public String f11546l;
    public String f11547m;
    public String f11548n;
    public String f11549o;
    public String f11550p;
    public String f11551q;
    public String f11552r;
    public String f11553s;
    public String f11554t;
    public String f11555u;
    public String f11556v;
    public ArrayList<C2225c> f11557w;
    public boolean f11558x;
    public int f11559y;
    public int f11560z;

    public C2226d() {
        this.f11535a = 1;
        this.f11536b = 0.0d;
        this.f11537c = 0.0d;
        this.f11538d = -1.0d;
        this.f11539e = 0.0d;
        this.f11540f = 0.0d;
        this.f11541g = 0.0d;
        this.f11542h = 0;
        this.f11543i = null;
        this.f11544j = null;
        this.f11545k = null;
        this.f11546l = null;
        this.f11547m = null;
        this.f11548n = null;
        this.f11549o = null;
        this.f11550p = null;
        this.f11551q = null;
        this.f11552r = null;
        this.f11553s = null;
        this.f11554t = null;
        this.f11555u = null;
        this.f11556v = null;
        this.f11557w = null;
        this.f11558x = false;
        this.f11559y = 0;
        this.f11560z = -1;
        this.f11534A = -1;
        this.f11539e = 0.0d;
        this.f11538d = 0.0d;
        this.f11537c = 0.0d;
        this.f11536b = 0.0d;
        this.f11550p = null;
        this.f11549o = null;
        this.f11548n = null;
        this.f11547m = null;
        this.f11558x = false;
        this.f11534A = System.currentTimeMillis();
        this.f11559y = 0;
        this.f11560z = -1;
        this.f11557w = null;
    }

    public C2226d(C2226d c2226d) {
        this.f11535a = 1;
        this.f11536b = 0.0d;
        this.f11537c = 0.0d;
        this.f11538d = -1.0d;
        this.f11539e = 0.0d;
        this.f11540f = 0.0d;
        this.f11541g = 0.0d;
        this.f11542h = 0;
        this.f11543i = null;
        this.f11544j = null;
        this.f11545k = null;
        this.f11546l = null;
        this.f11547m = null;
        this.f11548n = null;
        this.f11549o = null;
        this.f11550p = null;
        this.f11551q = null;
        this.f11552r = null;
        this.f11553s = null;
        this.f11554t = null;
        this.f11555u = null;
        this.f11556v = null;
        this.f11557w = null;
        this.f11558x = false;
        this.f11559y = 0;
        this.f11560z = -1;
        this.f11534A = -1;
        this.f11535a = c2226d.f11535a;
        this.f11536b = c2226d.f11536b;
        this.f11537c = c2226d.f11537c;
        this.f11538d = c2226d.f11538d;
        this.f11539e = c2226d.f11539e;
        this.f11558x = c2226d.f11558x;
        this.f11543i = c2226d.f11543i;
        this.f11542h = 0;
        this.f11544j = c2226d.f11544j;
        this.f11545k = c2226d.f11545k;
        this.f11546l = c2226d.f11546l;
        this.f11547m = c2226d.f11547m;
        this.f11548n = c2226d.f11548n;
        this.f11549o = c2226d.f11549o;
        this.f11550p = c2226d.f11550p;
        this.f11551q = c2226d.f11551q;
        this.f11552r = c2226d.f11552r;
        this.f11553s = c2226d.f11553s;
        this.f11554t = c2226d.f11554t;
        this.f11555u = c2226d.f11555u;
        this.f11556v = c2226d.f11556v;
        this.f11534A = c2226d.m13358a();
        this.f11559y = c2226d.f11559y;
        this.f11560z = c2226d.f11560z;
        this.f11557w = null;
        if (c2226d.f11557w != null) {
            this.f11557w = new ArrayList();
            Iterator it = c2226d.f11557w.iterator();
            while (it.hasNext()) {
                this.f11557w.add((C2225c) it.next());
            }
        }
    }

    public long m13358a() {
        return this.f11534A;
    }

    public void m13359a(String str) {
        String str2 = "Unknown";
        this.f11546l = str2;
        this.f11545k = str2;
        this.f11544j = str2;
        this.f11543i = str2;
        if (str != null) {
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            if (split != null) {
                int length = split.length;
                if (length > 0) {
                    this.f11543i = split[0];
                }
                if (length > 1) {
                    this.f11544j = split[1];
                }
                if (length == 3) {
                    this.f11545k = split[1];
                } else if (length > 3) {
                    this.f11545k = split[2];
                }
                if (length == 3) {
                    this.f11546l = split[2];
                } else if (length > 3) {
                    this.f11546l = split[3];
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f11560z).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11559y).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11558x ? "Mars" : "WGS84").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11535a == 0 ? "GPS" : "Network").append("\n");
        stringBuilder.append(this.f11536b).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11537c).append("\n");
        stringBuilder.append(this.f11538d).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11539e).append("\n");
        stringBuilder.append(this.f11540f).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11541g).append("\n");
        if (this.f11560z == 3 || this.f11560z == 4) {
            stringBuilder.append(this.f11543i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11544j).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11545k).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11546l).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11547m).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11548n).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11549o).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11550p).append("\n");
        }
        if (this.f11560z == 4 && this.f11557w != null) {
            stringBuilder.append(this.f11557w.size()).append("\n");
            Iterator it = this.f11557w.iterator();
            while (it.hasNext()) {
                C2225c c2225c = (C2225c) it.next();
                stringBuilder.append(c2225c.f11528a).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c2225c.f11529b).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c2225c.f11530c).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c2225c.f11531d).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c2225c.f11532e).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(c2225c.f11533f).append("\n");
            }
        }
        if (this.f11560z == 7) {
            if (this.f11542h == 0) {
                stringBuilder.append(this.f11543i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11544j).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11545k).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11546l).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11547m).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11548n).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11549o).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11550p).append("\n");
            } else if (this.f11542h == 1) {
                stringBuilder.append(this.f11543i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11551q).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11552r).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11553s).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11554t).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11555u).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(this.f11556v).append("\n");
            }
        }
        C2255h.m13481a(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
