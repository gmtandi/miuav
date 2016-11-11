package com.fimi.soul.utils;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.fimi.soul.utils.l */
public class C1972l {
    public static final String f10156A = "";
    public static final String f10157B = "critical";
    public static final String f10158C = "over load";
    public static final String f10159D = "outlier";
    public static final String f10160E = "frozen";
    public static final String f10161F = "unattached";
    public static final String f10162G = "tempe error";
    public static final String f10163H = "";
    public static final String f10164I = "critical";
    public static final String f10165J = "over load";
    public static final String f10166K = "outlier";
    public static final String f10167L = "frozen";
    public static final String f10168M = "unattached";
    public static final String f10169N = "tempe error";
    public static final String f10170O = "";
    public static final String f10171P = "critical";
    public static final String f10172Q = "over load";
    public static final String f10173R = "outlier";
    public static final String f10174S = "frozen";
    public static final String f10175T = "unattached";
    public static final String f10176U = "distraction";
    public static final String f10177V = "";
    public static final String f10178W = "critical";
    public static final String f10179X = "bad signal";
    public static final String f10180Y = "outlier";
    public static final String f10181Z = "frozen";
    public static final String f10182a = "";
    public static final String aA = "unattached";
    public static final String aB = "stall";
    public static final String aC = "overheat";
    public static final String aD = "overspeed";
    public static final String aE = "overcurrent";
    public static final String aF = "";
    public static final String aG = "critical";
    public static final String aH = "unattached";
    public static final String aI = "critical";
    public static final String aJ = "";
    public static final String aK = "";
    public static final String aL = "";
    public static final String aM = "";
    public static final String aN = "critical";
    public static final String aO = "unattached";
    public static final String aP = "critical";
    public static final String aQ = "";
    public static final String aR = "";
    public static final String aS = "";
    public static final String aT = "";
    public static final String aU = "critical";
    public static final String aV = "unattached";
    public static final String aW = "";
    public static final String aX = "";
    public static final String aY = "";
    public static final String aZ = "";
    public static final String aa = "unattached";
    public static final String ab = "distraction";
    public static final String ac = "";
    public static final String ad = "critical";
    public static final String ae = "over load";
    public static final String af = "outlier";
    public static final String ag = "frozen";
    public static final String ah = "unattached";
    public static final String ai = "distraction";
    public static final String aj = "";
    public static final String ak = "critical";
    public static final String al = "unauthorized";
    public static final String am = "Comm error";
    public static final String an = "tempe error";
    public static final String ao = "overcurrent";
    public static final String ap = "low battery level";
    public static final String aq = "low battery level";
    public static final String ar = "";
    public static final String as = "critical";
    public static final String at = "unattached";
    public static final String au = "full";
    public static final String av = "read error";
    public static final String aw = "write error";
    public static final String ax = "";
    public static final String ay = "";
    public static final String az = "critical";
    public static final int f10183b = 0;
    public static final String bA = "";
    public static final String bB = "";
    public static final String bC = "";
    public static final String bD = "";
    private static C1972l bF = null;
    public static final String ba = "";
    public static final String bb = "critical";
    public static final String bc = "unattached";
    public static final String bd = "";
    public static final String be = "";
    public static final String bf = "";
    public static final String bg = "";
    public static final String bh = "";
    public static final String bi = "critical";
    public static final String bj = "bad accel";
    public static final String bk = "euler error";
    public static final String bl = "diverge";
    public static final String bm = "Un calied";
    public static final String bn = "";
    public static final String bo = "";
    public static final String bp = "critical";
    public static final String bq = "close to";
    public static final String br = "entered";
    public static final String bs = "";
    public static final String bt = "";
    public static final String bu = "";
    public static final String bv = "";
    public static final String bw = "critical";
    public static final String bx = "Attitute limit";
    public static final String by = "Attitute limit";
    public static final String bz = "speed limit";
    public static final int f10184c = 1;
    public static final int f10185d = 2;
    public static final int f10186e = 3;
    public static final String f10187f = "1";
    public static final String f10188g = "2";
    public static final String f10189h = "3";
    public static final String f10190i = "4";
    public static final String f10191j = "5";
    public static final String f10192k = "6";
    public static final String f10193l = "7";
    public static final String f10194m = "8";
    public static final String f10195n = "9";
    public static final String f10196o = "10";
    public static final String f10197p = "11";
    public static final String f10198q = "12";
    public static final String f10199r = "13";
    public static final String f10200s = "32";
    public static final String f10201t = "33";
    public static final String f10202u = "34";
    public static final String f10203v = "over load";
    public static final String f10204w = "outlier";
    public static final String f10205x = "frozen";
    public static final String f10206y = "unattached";
    public static final String f10207z = "tempe error";
    private Map<String, String[]> bE;

    static {
        bF = null;
    }

    private C1972l() {
        this.bE = new HashMap();
        m12500c();
    }

    public static C1972l m12499a() {
        if (bF == null) {
            bF = new C1972l();
        }
        return bF;
    }

    private void m12500c() {
        if (this.bE == null || this.bE.size() == 0) {
            this.bE.put(f10187f, new String[]{"over load;2", "outlier;2", "frozen;2", "unattached;0", "tempe error;2", ";", "critical;0"});
            this.bE.put(f10188g, new String[]{"over load;2", "outlier;2", "frozen;2", "unattached;0", "tempe error;2", ";", "critical;0"});
            this.bE.put(f10189h, new String[]{"over load;2", "outlier;2", "frozen;2", "unattached;0", "tempe error;2", ";", "critical;0"});
            this.bE.put(f10190i, new String[]{"over load;2", "outlier;2", "frozen;2", "unattached;0", "distraction;2", ";", "critical;0"});
            this.bE.put(f10191j, new String[]{"bad signal;2", "outlier;2", "frozen;2", "unattached;0", "distraction;2", ";", "critical;0"});
            this.bE.put(f10192k, new String[]{"over load;2", "outlier;2", "frozen;2", "unattached;0", "distraction;2", "distraction;2", ";", "critical;0"});
            this.bE.put(f10193l, new String[]{"unauthorized;3", "Comm error;3", "tempe error;3", "overcurrent;3", "low battery level;1", "low battery level;0", "critical;0"});
            this.bE.put(f10194m, new String[]{"unattached;3", "full;3", "read error;3", "write error;3", ";", ";", "critical;3"});
            this.bE.put(f10195n, new String[]{"unattached;0", "stall;0", "overheat;0", "overspeed;2", "overcurrent;2", ";", "critical;0"});
            this.bE.put(f10196o, new String[]{"unattached;2", "critical;2", ";", ";", ";", ";", ";", "critical;2"});
            this.bE.put(f10198q, new String[]{"unattached;3", ";", ";", ";", ";", ";", "critical;3"});
            this.bE.put(f10199r, new String[]{"unattached;3", ";", ";", ";", ";", ";", "critical;3"});
            this.bE.put(f10200s, new String[]{"bad accel;3", "euler error;3", "diverge;2", "Un calied;3", ";3", ";3", "critical;3"});
            this.bE.put(f10201t, new String[]{"close to;2", "entered;3", ";3", ";3", ";3", ";3", "critical;3"});
            this.bE.put(f10202u, new String[]{"Attitute limit;2", "Attitute limit;2", "speed limit;2", ";", ";", ";", ";"});
        }
    }

    public String m12501a(int i, int i2, Context context) {
        String str = bv;
        if (i2 != 0) {
            String[] a = m12503a(String.valueOf(i2));
            if (a != null && a.length > 0) {
                for (int i3 = f10183b; i3 < 7; i3 += f10184c) {
                    if (((f10184c << i3) & i) > 0) {
                        str = str + a[i3] + "#";
                    }
                }
            }
        }
        return str;
    }

    public boolean m12502a(int i, int i2) {
        return ((f10184c << (i2 + -1)) & i) > 0;
    }

    public String[] m12503a(String str) {
        return (str == null || str.equals(bv) || !this.bE.containsKey(str)) ? null : (String[]) this.bE.get(str);
    }

    public Map<String, String[]> m12504b() {
        return this.bE;
    }
}
