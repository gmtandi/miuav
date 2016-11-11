package com.p016a;

import android.content.Context;
import android.os.Looper;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.co */
public class co {
    Looper f770a;
    cq f771b;
    private Context f772c;
    private int f773d;
    private ArrayList<cn> f774e;
    private int f775f;
    private TelephonyManager f776g;
    private Object f777h;
    private long f778i;
    private JSONObject f779j;
    private PhoneStateListener f780k;
    private CellLocation f781l;
    private boolean f782m;
    private Object f783n;

    public co(Context context, JSONObject jSONObject) {
        this.f773d = 9;
        this.f774e = new ArrayList();
        this.f775f = -113;
        this.f778i = 0;
        this.f770a = null;
        this.f771b = null;
        this.f782m = false;
        this.f783n = new Object();
        if (context == null) {
            m1347p();
            this.f776g = (TelephonyManager) dn.m1503a(context, "phone");
            this.f779j = jSONObject;
            this.f772c = context;
        } else {
            m1347p();
            this.f776g = (TelephonyManager) dn.m1503a(context, "phone");
            this.f779j = jSONObject;
            this.f772c = context;
        }
        try {
            this.f773d = dn.m1501a(this.f776g.getCellLocation(), context);
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "CgiManager");
            this.f773d = 9;
        }
        if (this.f771b == null) {
            this.f771b = new cq(this);
            this.f771b.setName("listener");
            this.f771b.start();
        }
    }

    private CellLocation m1326a(List<?> list) {
        int i;
        Throwable th;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CellLocation cdmaCellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation = null;
        int i2 = 0;
        CellLocation cellLocation2 = null;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Object obj = list.get(i3);
            if (obj != null) {
                try {
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    i = loadClass.isInstance(obj) ? 1 : loadClass2.isInstance(obj) ? 2 : loadClass3.isInstance(obj) ? 3 : loadClass4.isInstance(obj) ? 4 : 0;
                    if (i > 0) {
                        Object obj2 = null;
                        if (i == 1) {
                            try {
                                obj2 = loadClass.cast(obj);
                            } catch (Throwable th2) {
                                th = th2;
                                i2 = i;
                                ev.m1777a(th, "CgiManager", "getCgi");
                            }
                        } else if (i == 2) {
                            obj2 = loadClass2.cast(obj);
                        } else if (i == 3) {
                            obj2 = loadClass3.cast(obj);
                        } else if (i == 4) {
                            obj2 = loadClass4.cast(obj);
                        }
                        obj = dl.m1486a(obj2, "getCellIdentity", new Object[0]);
                        if (obj == null) {
                            i2 = i;
                        } else if (i == 4) {
                            cdmaCellLocation = new CdmaCellLocation();
                            try {
                                cdmaCellLocation.setCellLocationData(dl.m1489b(obj, "getBasestationId", new Object[0]), dl.m1489b(obj, "getLatitude", new Object[0]), dl.m1489b(obj, "getLongitude", new Object[0]), dl.m1489b(obj, "getSystemId", new Object[0]), dl.m1489b(obj, "getNetworkId", new Object[0]));
                                cellLocation2 = cellLocation;
                                break;
                            } catch (Throwable th3) {
                                th = th3;
                                cellLocation2 = cdmaCellLocation;
                                i2 = i;
                            }
                        } else if (i == 3) {
                            r3 = dl.m1489b(obj, "getTac", new Object[0]);
                            r2 = dl.m1489b(obj, "getCi", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Throwable th4) {
                                th = th4;
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        } else {
                            r3 = dl.m1489b(obj, "getLac", new Object[0]);
                            r2 = dl.m1489b(obj, "getCid", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Throwable th5) {
                                th = th5;
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        }
                    } else {
                        i2 = i;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    ev.m1777a(th, "CgiManager", "getCgi");
                }
            }
        }
        i = i2;
        cdmaCellLocation = cellLocation2;
        cellLocation2 = cellLocation;
        return i != 4 ? cellLocation2 : cdmaCellLocation;
    }

    private void m1329a(int i) {
        if (i == -113) {
            this.f775f = -113;
            return;
        }
        this.f775f = i;
        switch (this.f773d) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (!this.f774e.isEmpty()) {
                    try {
                        ((cn) this.f774e.get(0)).f768j = this.f775f;
                    } catch (Throwable th) {
                        ev.m1777a(th, "CgiManager", "hdlCgiSigStrenChange");
                    }
                }
            default:
        }
    }

    private cn m1331b(NeighboringCellInfo neighboringCellInfo) {
        if (dn.m1526c() < 5) {
            return null;
        }
        try {
            cn cnVar = new cn(1);
            String[] a = dn.m1518a(this.f776g);
            cnVar.f759a = a[0];
            cnVar.f760b = a[1];
            cnVar.f761c = dl.m1489b(neighboringCellInfo, "getLac", new Object[0]);
            cnVar.f762d = neighboringCellInfo.getCid();
            cnVar.f768j = dn.m1498a(neighboringCellInfo.getRssi());
            return cnVar;
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private void m1332b(CellLocation cellLocation) {
        if (cellLocation != null && this.f776g != null) {
            this.f774e.clear();
            if (m1352a(cellLocation)) {
                this.f773d = 1;
                this.f774e.add(m1337d(cellLocation));
                List<NeighboringCellInfo> neighboringCellInfo = this.f776g.getNeighboringCellInfo();
                if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        if (m1353a(neighboringCellInfo2)) {
                            cn b = m1331b(neighboringCellInfo2);
                            if (!(b == null || this.f774e.contains(b))) {
                                this.f774e.add(b);
                            }
                        }
                    }
                }
            }
        }
    }

    private void m1334c(CellLocation cellLocation) {
        Object obj = null;
        if (cellLocation != null) {
            this.f774e.clear();
            if (dn.m1526c() >= 5) {
                try {
                    if (this.f777h != null) {
                        Object obj2;
                        Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        CellLocation cellLocation2 = (GsmCellLocation) declaredField.get(cellLocation);
                        if (cellLocation2 != null && m1352a(cellLocation2)) {
                            m1332b(cellLocation2);
                            obj2 = 1;
                            if (obj2 != null) {
                                return;
                            }
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    ev.m1777a(th, "CgiManager", "hdlCdmaLocChange");
                    return;
                }
                if (m1352a(cellLocation)) {
                    this.f773d = 2;
                    String[] a = dn.m1518a(this.f776g);
                    cn cnVar = new cn(2);
                    cnVar.f759a = a[0];
                    cnVar.f760b = a[1];
                    cnVar.f765g = dl.m1489b(cellLocation, "getSystemId", new Object[0]);
                    cnVar.f766h = dl.m1489b(cellLocation, "getNetworkId", new Object[0]);
                    cnVar.f767i = dl.m1489b(cellLocation, "getBaseStationId", new Object[0]);
                    cnVar.f768j = this.f775f;
                    cnVar.f763e = dl.m1489b(cellLocation, "getBaseStationLatitude", new Object[0]);
                    cnVar.f764f = dl.m1489b(cellLocation, "getBaseStationLongitude", new Object[0]);
                    if (cnVar.f763e < 0 || cnVar.f764f < 0) {
                        cnVar.f763e = 0;
                        cnVar.f764f = 0;
                    } else if (cnVar.f763e == Integer.MAX_VALUE) {
                        cnVar.f763e = 0;
                        cnVar.f764f = 0;
                    } else if (cnVar.f764f == Integer.MAX_VALUE) {
                        cnVar.f763e = 0;
                        cnVar.f764f = 0;
                    } else if (cnVar.f763e != cnVar.f764f || cnVar.f763e <= 0) {
                        int i = 1;
                    } else {
                        cnVar.f763e = 0;
                        cnVar.f764f = 0;
                    }
                    if (obj == null) {
                    }
                    if (!this.f774e.contains(cnVar)) {
                        this.f774e.add(cnVar);
                    }
                }
            }
        }
    }

    private cn m1337d(CellLocation cellLocation) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        cn cnVar = new cn(1);
        String[] a = dn.m1518a(this.f776g);
        cnVar.f759a = a[0];
        cnVar.f760b = a[1];
        cnVar.f761c = gsmCellLocation.getLac();
        cnVar.f762d = gsmCellLocation.getCid();
        cnVar.f768j = this.f775f;
        return cnVar;
    }

    public static int m1342k() {
        int i = 0;
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            i = 1;
        } catch (Throwable th) {
        }
        if (i != 0) {
            return i;
        }
        try {
            Class.forName("android.telephony.TelephonyManager2");
            return 2;
        } catch (Throwable th2) {
            return i;
        }
    }

    private synchronized void m1343l() {
        if (!(dn.m1509a(this.f772c) || this.f776g == null)) {
            CellLocation m = m1344m();
            if (!m1352a(m)) {
                m = m1345n();
            }
            if (m1352a(m)) {
                this.f781l = m;
            }
        }
        if (m1352a(this.f781l)) {
            switch (dn.m1501a(this.f781l, this.f772c)) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    m1332b(this.f781l);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    m1334c(this.f781l);
                    break;
                default:
                    break;
            }
        }
    }

    private CellLocation m1344m() {
        CellLocation cellLocation = null;
        TelephonyManager telephonyManager = this.f776g;
        if (telephonyManager == null) {
            return cellLocation;
        }
        CellLocation cellLocation2;
        try {
            cellLocation2 = telephonyManager.getCellLocation();
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "getSim1Cgi4");
            cellLocation2 = cellLocation;
        }
        if (m1352a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            cellLocation2 = m1326a((List) dl.m1486a(telephonyManager, "getAllCellInfo", new Object[0]));
        } catch (NoSuchMethodException e) {
        } catch (Throwable th2) {
            ev.m1777a(th2, "CgiManager", "getSim1Cgi2");
        }
        if (m1352a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            Object a = dl.m1486a(telephonyManager, "getCellLocationExt", Integer.valueOf(1));
            cellLocation2 = a != null ? (CellLocation) a : cellLocation2;
        } catch (NoSuchMethodException e2) {
        } catch (Throwable th22) {
            ev.m1777a(th22, "CgiManager", "getSim1Cgi1");
        }
        if (m1352a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            a = dl.m1486a(telephonyManager, "getCellLocationGemini", Integer.valueOf(1));
            cellLocation2 = a != null ? (CellLocation) a : cellLocation2;
        } catch (NoSuchMethodException e3) {
        } catch (Throwable th222) {
            ev.m1777a(th222, "CgiManager", "getSim1Cgi");
        }
        return m1352a(cellLocation2) ? cellLocation2 : cellLocation2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.telephony.CellLocation m1345n() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f777h;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r2 = r7.m1346o();	 Catch:{ Throwable -> 0x0070 }
        r3 = r2.isInstance(r0);	 Catch:{ Throwable -> 0x0070 }
        if (r3 == 0) goto L_0x009d;
    L_0x0010:
        r3 = r2.cast(r0);	 Catch:{ Throwable -> 0x0070 }
        r4 = "getCellLocation";
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x005a, Throwable -> 0x005d }
        r2 = com.p016a.dl.m1486a(r3, r4, r0);	 Catch:{ NoSuchMethodException -> 0x005a, Throwable -> 0x005d }
    L_0x001d:
        if (r2 != 0) goto L_0x002e;
    L_0x001f:
        r0 = 1;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x009b, Throwable -> 0x0067 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x009b, Throwable -> 0x0067 }
        r0[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x009b, Throwable -> 0x0067 }
        r2 = com.p016a.dl.m1486a(r3, r4, r0);	 Catch:{ NoSuchMethodException -> 0x009b, Throwable -> 0x0067 }
    L_0x002e:
        if (r2 != 0) goto L_0x0085;
    L_0x0030:
        r0 = "getCellLocationGemini";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r4[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r0 = com.p016a.dl.m1486a(r3, r0, r4);	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
    L_0x0041:
        if (r0 != 0) goto L_0x0054;
    L_0x0043:
        r0 = "getAllCellInfo";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x0091 }
        r0 = com.p016a.dl.m1486a(r3, r0, r2);	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x0091 }
        r0 = (java.util.List) r0;	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x0091 }
    L_0x004e:
        r0 = r7.m1326a(r0);	 Catch:{ Throwable -> 0x0070 }
        if (r0 == 0) goto L_0x0054;
    L_0x0054:
        if (r0 == 0) goto L_0x0078;
    L_0x0056:
        r0 = (android.telephony.CellLocation) r0;	 Catch:{ Throwable -> 0x0070 }
    L_0x0058:
        r1 = r0;
        goto L_0x0005;
    L_0x005a:
        r0 = move-exception;
        r2 = r1;
        goto L_0x001d;
    L_0x005d:
        r0 = move-exception;
        r2 = "CgiManager";
        r5 = "getSim2Cgi15";
        com.p016a.ev.m1777a(r0, r2, r5);	 Catch:{ Throwable -> 0x0070 }
        r2 = r1;
        goto L_0x001d;
    L_0x0067:
        r0 = move-exception;
        r4 = "CgiManager";
        r5 = "getSim2Cgi14";
        com.p016a.ev.m1777a(r0, r4, r5);	 Catch:{ Throwable -> 0x0070 }
        goto L_0x002e;
    L_0x0070:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "getSim2Cgi";
        com.p016a.ev.m1777a(r0, r2, r3);
    L_0x0078:
        r0 = r1;
        goto L_0x0058;
    L_0x007a:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0041;
    L_0x007d:
        r0 = move-exception;
        r4 = "CgiManager";
        r5 = "getSim2Cgi13";
        com.p016a.ev.m1777a(r0, r4, r5);	 Catch:{ Throwable -> 0x0070 }
    L_0x0085:
        r0 = r2;
        goto L_0x0041;
    L_0x0087:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "getSim2Cgi12";
        com.p016a.ev.m1777a(r0, r2, r3);	 Catch:{ Throwable -> 0x0070 }
        r0 = r1;
        goto L_0x004e;
    L_0x0091:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "getSim2Cgi1";
        com.p016a.ev.m1777a(r0, r2, r3);	 Catch:{ Throwable -> 0x0070 }
        r0 = r1;
        goto L_0x004e;
    L_0x009b:
        r0 = move-exception;
        goto L_0x002e;
    L_0x009d:
        r0 = r1;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.co.n():android.telephony.CellLocation");
    }

    private Class<?> m1346o() {
        String str;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (co.m1342k()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                str = "android.telephony.TelephonyManager";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "getSim2TmClass");
        }
        return cls;
    }

    private void m1347p() {
        Object obj = 1;
        JSONObject jSONObject = this.f779j;
        if (jSONObject != null) {
            try {
                if (jSONObject.has("cellupdate") && jSONObject.getString("cellupdate").equals(Constants.VIA_RESULT_SUCCESS)) {
                    obj = null;
                }
            } catch (Throwable th) {
                ev.m1777a(th, "CgiManager", "updateCgi1");
            }
        }
        if (obj != null) {
            try {
                CellLocation.requestLocationUpdate();
            } catch (Throwable th2) {
                ev.m1777a(th2, "CgiManager", "updateCgi");
            }
            this.f778i = dn.m1519b();
        }
    }

    private void m1348q() {
        this.f780k = new cp(this);
        String str = "android.telephony.PhoneStateListener";
        String str2 = C2915a.f14760f;
        int i = 0;
        if (dn.m1526c() < 7) {
            try {
                i = dl.m1490b(str, "LISTEN_SIGNAL_STRENGTH");
            } catch (Throwable th) {
                ev.m1777a(th, "CgiManager", "initPhoneStateListener3");
            }
        } else {
            try {
                i = dl.m1490b(str, "LISTEN_SIGNAL_STRENGTHS");
            } catch (Throwable th2) {
                ev.m1777a(th2, "CgiManager", "initPhoneStateListener2");
            }
        }
        if (i == 0) {
            this.f776g.listen(this.f780k, 16);
        } else {
            try {
                this.f776g.listen(this.f780k, i | 16);
            } catch (Throwable th3) {
                ev.m1777a(th3, "CgiManager", "initPhoneStateListener1");
            }
        }
        try {
            switch (co.m1342k()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f777h = dn.m1503a(this.f772c, "phone2");
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f777h = dn.m1503a(this.f772c, "phone_msim");
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f777h = dn.m1503a(this.f772c, "phone2");
                    return;
                default:
                    return;
            }
        } catch (Throwable th32) {
            ev.m1777a(th32, "CgiManager", "initPhoneStateListener");
        }
        ev.m1777a(th32, "CgiManager", "initPhoneStateListener");
    }

    private void m1349r() {
        this.f781l = null;
        this.f773d = 9;
        this.f774e.clear();
    }

    public ArrayList<cn> m1350a() {
        return this.f774e;
    }

    public void m1351a(JSONObject jSONObject) {
        this.f779j = jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m1352a(android.telephony.CellLocation r6) {
        /*
        r5 = this;
        r4 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r3 = -1;
        r0 = 0;
        if (r6 != 0) goto L_0x0008;
    L_0x0007:
        return r0;
    L_0x0008:
        r1 = 1;
        r2 = r5.f772c;
        r2 = com.p016a.dn.m1501a(r6, r2);
        switch(r2) {
            case 1: goto L_0x001a;
            case 2: goto L_0x004a;
            default: goto L_0x0012;
        };
    L_0x0012:
        r0 = r1;
    L_0x0013:
        if (r0 != 0) goto L_0x0007;
    L_0x0015:
        r1 = 9;
        r5.f773d = r1;
        goto L_0x0007;
    L_0x001a:
        r6 = (android.telephony.gsm.GsmCellLocation) r6;
        r2 = r6.getLac();
        if (r2 == r3) goto L_0x0013;
    L_0x0022:
        r2 = r6.getLac();
        if (r2 == 0) goto L_0x0013;
    L_0x0028:
        r2 = r6.getLac();
        if (r2 > r4) goto L_0x0013;
    L_0x002e:
        r2 = r6.getCid();
        if (r2 == r3) goto L_0x0013;
    L_0x0034:
        r2 = r6.getCid();
        if (r2 == 0) goto L_0x0013;
    L_0x003a:
        r2 = r6.getCid();
        if (r2 == r4) goto L_0x0013;
    L_0x0040:
        r2 = r6.getCid();
        r3 = 268435455; // 0xfffffff float:2.5243547E-29 double:1.326247364E-315;
        if (r2 < r3) goto L_0x0012;
    L_0x0049:
        goto L_0x0013;
    L_0x004a:
        r2 = "getSystemId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x006c }
        r2 = com.p016a.dl.m1489b(r6, r2, r3);	 Catch:{ Throwable -> 0x006c }
        if (r2 <= 0) goto L_0x0013;
    L_0x0055:
        r2 = "getNetworkId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x006c }
        r2 = com.p016a.dl.m1489b(r6, r2, r3);	 Catch:{ Throwable -> 0x006c }
        if (r2 < 0) goto L_0x0013;
    L_0x0060:
        r2 = "getBaseStationId";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x006c }
        r2 = com.p016a.dl.m1489b(r6, r2, r3);	 Catch:{ Throwable -> 0x006c }
        if (r2 >= 0) goto L_0x0012;
    L_0x006b:
        goto L_0x0013;
    L_0x006c:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "cgiUseful";
        com.p016a.ev.m1777a(r0, r2, r3);
        r0 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.co.a(android.telephony.CellLocation):boolean");
    }

    public boolean m1353a(NeighboringCellInfo neighboringCellInfo) {
        return (neighboringCellInfo == null || neighboringCellInfo.getLac() == -1 || neighboringCellInfo.getLac() == 0 || neighboringCellInfo.getLac() > Util.MASK_16BIT || neighboringCellInfo.getCid() == -1 || neighboringCellInfo.getCid() == 0 || neighboringCellInfo.getCid() == Util.MASK_16BIT || neighboringCellInfo.getCid() >= 268435455) ? false : true;
    }

    public boolean m1354a(boolean z) {
        return (z || this.f778i == 0 || dn.m1519b() - this.f778i < 30000) ? false : true;
    }

    public cn m1355b() {
        ArrayList arrayList = this.f774e;
        return arrayList.size() >= 1 ? (cn) arrayList.get(0) : null;
    }

    public int m1356c() {
        return this.f773d;
    }

    public CellLocation m1357d() {
        CellLocation cellLocation = null;
        if (this.f776g != null) {
            try {
                cellLocation = this.f776g.getCellLocation();
                if (m1352a(cellLocation)) {
                    this.f781l = cellLocation;
                }
            } catch (Throwable th) {
                ev.m1777a(th, "CgiManager", "getCellLocation");
            }
        }
        return cellLocation;
    }

    public TelephonyManager m1358e() {
        return this.f776g;
    }

    public void m1359f() {
        m1343l();
    }

    public void m1360g() {
        m1349r();
    }

    public void m1361h() {
        m1347p();
    }

    public void m1362i() {
        if (!(this.f776g == null || this.f780k == null)) {
            try {
                this.f776g.listen(this.f780k, 0);
            } catch (Throwable th) {
                ev.m1777a(th, "CgiManager", "destroy");
            }
        }
        this.f780k = null;
        synchronized (this.f783n) {
            this.f782m = true;
            if (this.f770a != null) {
                this.f770a.quit();
                this.f770a = null;
            }
        }
        this.f771b = null;
        this.f774e.clear();
        this.f775f = -113;
        this.f776g = null;
        this.f777h = null;
    }

    public void m1363j() {
        switch (this.f773d) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f774e.isEmpty()) {
                    this.f773d = 9;
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f774e.isEmpty()) {
                    this.f773d = 9;
                }
            default:
        }
    }
}
