package com.fimi.soul.drone.p116g;

import com.fimi.soul.drone.droneconnection.connection.C1519q;

/* renamed from: com.fimi.soul.drone.g.c */
public class C1543c {
    public static final int f7200A = 6;
    public static final int f7201B = 7;
    public static final int f7202C = 8;
    public static final int f7203D = 9;
    public static final int f7204E = 10;
    public static final int f7205F = 11;
    public static final int f7206G = 12;
    public static final int f7207H = 13;
    public static final int f7208I = 14;
    public static final int f7209J = 15;
    public static final int f7210K = 16;
    public static final int f7211L = 17;
    public static final int f7212M = 18;
    public static final int f7213N = 19;
    public static final int f7214O = 20;
    public static final int f7215P = 4;
    public static final int f7216Q = 8;
    public static final int f7217R = 13;
    public static final int f7218S = 80;
    public static final int f7219T = 30;
    public static final int f7220U = 5;
    public static final int f7221V = 60;
    public static final int f7222W = 30;
    public static final String f7223X = "dronename";
    public static final String f7224Y = "homename";
    public static final String f7225Z = "checkcampass";
    public static final String f7226a = "pref_bluetooth_device_address";
    public static final int aA = 10;
    public static final int aB = 11;
    public static final int aC = 12;
    public static final int aD = 13;
    public static final int aE = 14;
    public static final int aF = 15;
    public static final int aG = 16;
    public static final int aH = 17;
    public static final int aI = 18;
    public static final int aJ = 19;
    public static final int aK = 20;
    public static final int aL = 21;
    public static final int aM = 22;
    public static final int aN = 23;
    public static final int aO = 24;
    public static final int aP = 25;
    public static final int aQ = 26;
    public static final int aR = 27;
    public static final int aS = 28;
    public static final int aT = 29;
    public static final int aU = 4;
    public static final int aV = 5;
    public static final int aW = 33;
    public static final int aX = 34;
    public static final int aY = 35;
    public static final int aZ = 30;
    public static final int aa = 0;
    public static final int ab = 1;
    public static final int ac = 2;
    public static final int ad = 3;
    public static final int ae = 4;
    public static final int af = 5;
    public static final int ag = 6;
    public static final int ah = 7;
    public static final int ai = 8;
    public static final int aj = 9;
    public static final int ak = 10;
    public static final int al = 11;
    public static final int am = 12;
    public static final int an = 13;
    public static final int ao = 14;
    public static final int ap = 15;
    public static final int aq = 0;
    public static final int ar = 1;
    public static final int as = 2;
    public static final int at = 3;
    public static final int au = 4;
    public static final int av = 5;
    public static final int aw = 6;
    public static final int ax = 7;
    public static final int ay = 8;
    public static final int az = 9;
    public static final String f7227b = "pref_connection_type";
    public static final int bA = 2;
    public static final String bB = "CacheDroneContrlType";
    public static final String bC = "REMEBERACTIONTIP";
    public static final String bD = "REMEBERACTIONTIPFlYTO";
    public static final String bE = "REMEBERACTIONTIPPOI";
    public static final String bF = "REMEBERACTIONTAKEPHOTO";
    public static final String bG = "DRONELATITUDE";
    public static final String bH = "DRONELONGITUDE";
    public static final String bI = "F11B-A";
    public static final String bJ = "F11B-C";
    private static final String bK;
    public static final int ba = 31;
    public static final int bb = 32;
    public static final int bc = 36;
    public static final int bd = 0;
    public static final int be = 1;
    public static final int bf = 2;
    public static final int bg = 3;
    public static final int bh = 4;
    public static final int bi = 5;
    public static final int bj = 1;
    public static final int bk = 2;
    public static final int bl = 1;
    public static final int bm = 2;
    public static final int bn = 3;
    public static final String bo = "action_model_type";
    public static final int bp = 0;
    public static final int bq = 1;
    public static final int br = 2;
    public static final int bs = 3;
    public static final int bt = 4;
    public static final int bu = 5;
    public static final int bv = 6;
    public static final int bw = 85;
    public static final int bx = 170;
    public static final int by = 51;
    public static final int bz = 1;
    public static final String f7228c;
    public static final String f7229d;
    public static final String f7230e = "pref_ui";
    public static final String f7231f = "pref_ui_language_english";
    public static final boolean f7232g = false;
    public static final String f7233h = "pref_drone_settings";
    public static final String f7234i = "pref_connection_port";
    public static final String f7235j = "pref_connection_serviceip";
    public static final String f7236k = "1234";
    public static final String f7237l = "192.168.42.100";
    public static final long f7238m = 80;
    public static final int f7239n = 300;
    public static final int f7240o = 20;
    public static final int f7241p = 100;
    public static final int f7242q = 10;
    public static final String f7243r = "flyinfo";
    public static final String f7244s = "Amptype";
    public static C1544d f7245t = null;
    public static final int f7246u = 0;
    public static final int f7247v = 1;
    public static final int f7248w = 2;
    public static final int f7249x = 3;
    public static final int f7250y = 4;
    public static final int f7251z = 5;

    static {
        bK = C1543c.class.getPackage().getName();
        f7228c = C1519q.USB.name();
        f7229d = C1519q.TCP.name();
        f7245t = C1544d.REMOTESEND;
    }

    private C1543c() {
    }

    public static C1544d m10117a() {
        return f7245t;
    }

    public static void m10118a(C1544d c1544d) {
        f7245t = c1544d;
    }
}
