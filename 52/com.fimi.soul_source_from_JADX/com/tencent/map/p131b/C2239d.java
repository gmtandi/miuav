package com.tencent.map.p131b;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.map.b.d */
public final class C2239d {
    private Context f11608a;
    private TelephonyManager f11609b;
    private C2236a f11610c;
    private C2238c f11611d;
    private C2237b f11612e;
    private boolean f11613f;
    private List<NeighboringCellInfo> f11614g;
    private byte[] f11615h;
    private byte[] f11616i;
    private boolean f11617j;

    /* renamed from: com.tencent.map.b.d.1 */
    final class C22351 extends Thread {
        private /* synthetic */ C2239d f11585a;

        C22351(C2239d c2239d) {
            this.f11585a = c2239d;
        }

        public final void run() {
            if (this.f11585a.f11609b != null) {
                Collection neighboringCellInfo = this.f11585a.f11609b.getNeighboringCellInfo();
                synchronized (this.f11585a.f11616i) {
                    if (neighboringCellInfo != null) {
                        this.f11585a.f11614g.clear();
                        this.f11585a.f11614g.addAll(neighboringCellInfo);
                    }
                }
            }
            this.f11585a.f11617j = false;
        }
    }

    /* renamed from: com.tencent.map.b.d.a */
    public final class C2236a extends PhoneStateListener {
        private int f11586a;
        private int f11587b;
        private int f11588c;
        private int f11589d;
        private int f11590e;
        private int f11591f;
        private int f11592g;
        private int f11593h;
        private Method f11594i;
        private Method f11595j;
        private Method f11596k;
        private Method f11597l;
        private Method f11598m;
        private /* synthetic */ C2239d f11599n;

        public C2236a(C2239d c2239d, int i, int i2) {
            this.f11599n = c2239d;
            this.f11586a = 0;
            this.f11587b = 0;
            this.f11588c = 0;
            this.f11589d = 0;
            this.f11590e = 0;
            this.f11591f = -1;
            this.f11592g = Integer.MAX_VALUE;
            this.f11593h = Integer.MAX_VALUE;
            this.f11594i = null;
            this.f11595j = null;
            this.f11596k = null;
            this.f11597l = null;
            this.f11598m = null;
            this.f11587b = i;
            this.f11586a = i2;
        }

        public final void onCellLocationChanged(CellLocation cellLocation) {
            this.f11591f = -1;
            this.f11590e = -1;
            this.f11589d = -1;
            this.f11588c = -1;
            if (cellLocation != null) {
                switch (this.f11586a) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        GsmCellLocation gsmCellLocation;
                        Object obj;
                        String networkOperator;
                        try {
                            gsmCellLocation = (GsmCellLocation) cellLocation;
                            try {
                                if (gsmCellLocation.getLac() <= 0 && gsmCellLocation.getCid() <= 0) {
                                    gsmCellLocation = (GsmCellLocation) this.f11599n.f11609b.getCellLocation();
                                }
                                obj = 1;
                            } catch (Exception e) {
                                obj = null;
                                networkOperator = this.f11599n.f11609b.getNetworkOperator();
                                if (networkOperator != null) {
                                    try {
                                        if (networkOperator.length() > 3) {
                                            this.f11588c = Integer.valueOf(networkOperator.substring(3)).intValue();
                                        }
                                    } catch (Exception e2) {
                                        this.f11590e = -1;
                                        this.f11589d = -1;
                                        this.f11588c = -1;
                                    }
                                }
                                this.f11589d = gsmCellLocation.getLac();
                                this.f11590e = gsmCellLocation.getCid();
                                C2239d.m13385c(this.f11599n);
                                this.f11599n.f11612e = new C2237b(this.f11599n, this.f11586a, this.f11587b, this.f11588c, this.f11589d, this.f11590e, this.f11591f, this.f11592g, this.f11593h);
                                if (this.f11599n.f11611d == null) {
                                    this.f11599n.f11611d.m13379a(this.f11599n.f11612e);
                                }
                            }
                        } catch (Exception e3) {
                            gsmCellLocation = null;
                            obj = null;
                            networkOperator = this.f11599n.f11609b.getNetworkOperator();
                            if (networkOperator != null) {
                                if (networkOperator.length() > 3) {
                                    this.f11588c = Integer.valueOf(networkOperator.substring(3)).intValue();
                                }
                            }
                            this.f11589d = gsmCellLocation.getLac();
                            this.f11590e = gsmCellLocation.getCid();
                            C2239d.m13385c(this.f11599n);
                            this.f11599n.f11612e = new C2237b(this.f11599n, this.f11586a, this.f11587b, this.f11588c, this.f11589d, this.f11590e, this.f11591f, this.f11592g, this.f11593h);
                            if (this.f11599n.f11611d == null) {
                                this.f11599n.f11611d.m13379a(this.f11599n.f11612e);
                            }
                        }
                        if (!(obj == null || gsmCellLocation == null)) {
                            networkOperator = this.f11599n.f11609b.getNetworkOperator();
                            if (networkOperator != null) {
                                if (networkOperator.length() > 3) {
                                    this.f11588c = Integer.valueOf(networkOperator.substring(3)).intValue();
                                }
                            }
                            this.f11589d = gsmCellLocation.getLac();
                            this.f11590e = gsmCellLocation.getCid();
                            C2239d.m13385c(this.f11599n);
                        }
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        if (cellLocation != null) {
                            try {
                                if (this.f11594i == null) {
                                    this.f11594i = Class.forName("android.telephony.cdma.CdmaCellLocation").getMethod("getBaseStationId", new Class[0]);
                                    this.f11595j = Class.forName("android.telephony.cdma.CdmaCellLocation").getMethod("getSystemId", new Class[0]);
                                    this.f11596k = Class.forName("android.telephony.cdma.CdmaCellLocation").getMethod("getNetworkId", new Class[0]);
                                    this.f11597l = Class.forName("android.telephony.cdma.CdmaCellLocation").getMethod("getBaseStationLatitude", new Class[0]);
                                    this.f11598m = Class.forName("android.telephony.cdma.CdmaCellLocation").getMethod("getBaseStationLongitude", new Class[0]);
                                }
                                this.f11588c = ((Integer) this.f11595j.invoke(cellLocation, new Object[0])).intValue();
                                this.f11589d = ((Integer) this.f11596k.invoke(cellLocation, new Object[0])).intValue();
                                this.f11590e = ((Integer) this.f11594i.invoke(cellLocation, new Object[0])).intValue();
                                this.f11592g = ((Integer) this.f11597l.invoke(cellLocation, new Object[0])).intValue();
                                this.f11593h = ((Integer) this.f11598m.invoke(cellLocation, new Object[0])).intValue();
                                break;
                            } catch (Exception e4) {
                                this.f11590e = -1;
                                this.f11589d = -1;
                                this.f11588c = -1;
                                this.f11592g = Integer.MAX_VALUE;
                                this.f11593h = Integer.MAX_VALUE;
                                break;
                            }
                        }
                        break;
                }
            }
            this.f11599n.f11612e = new C2237b(this.f11599n, this.f11586a, this.f11587b, this.f11588c, this.f11589d, this.f11590e, this.f11591f, this.f11592g, this.f11593h);
            if (this.f11599n.f11611d == null) {
                this.f11599n.f11611d.m13379a(this.f11599n.f11612e);
            }
        }

        public final void onSignalStrengthChanged(int i) {
            if (this.f11586a == 1) {
                C2239d.m13385c(this.f11599n);
            }
            if (Math.abs(i - ((this.f11591f + Opcodes.LREM) / 2)) <= 3) {
                return;
            }
            if (this.f11591f == -1) {
                this.f11591f = (i << 1) - 113;
                return;
            }
            this.f11591f = (i << 1) - 113;
            this.f11599n.f11612e = new C2237b(this.f11599n, this.f11586a, this.f11587b, this.f11588c, this.f11589d, this.f11590e, this.f11591f, this.f11592g, this.f11593h);
            if (this.f11599n.f11611d != null) {
                this.f11599n.f11611d.m13379a(this.f11599n.f11612e);
            }
        }
    }

    /* renamed from: com.tencent.map.b.d.b */
    public final class C2237b implements Cloneable {
        public int f11600a;
        public int f11601b;
        public int f11602c;
        public int f11603d;
        public int f11604e;
        public int f11605f;
        public int f11606g;
        public int f11607h;

        public C2237b(C2239d c2239d, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f11600a = 0;
            this.f11601b = 0;
            this.f11602c = 0;
            this.f11603d = 0;
            this.f11604e = 0;
            this.f11605f = 0;
            this.f11606g = Integer.MAX_VALUE;
            this.f11607h = Integer.MAX_VALUE;
            this.f11600a = i;
            this.f11601b = i2;
            this.f11602c = i3;
            this.f11603d = i4;
            this.f11604e = i5;
            this.f11605f = i6;
            this.f11606g = i7;
            this.f11607h = i8;
        }

        public final Object clone() {
            try {
                return (C2237b) super.clone();
            } catch (Exception e) {
                return null;
            }
        }
    }

    /* renamed from: com.tencent.map.b.d.c */
    public interface C2238c {
        void m13379a(C2237b c2237b);
    }

    public C2239d() {
        this.f11608a = null;
        this.f11609b = null;
        this.f11610c = null;
        this.f11611d = null;
        this.f11612e = null;
        this.f11613f = false;
        this.f11614g = new LinkedList();
        this.f11615h = new byte[0];
        this.f11616i = new byte[0];
        this.f11617j = false;
    }

    private int m13380a(int i) {
        int intValue;
        String networkOperator = this.f11609b.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() >= 3) {
            try {
                intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
            } catch (Exception e) {
            }
            return (i == 2 || intValue != -1) ? intValue : 0;
        }
        intValue = -1;
        if (i == 2) {
        }
    }

    static /* synthetic */ void m13385c(C2239d c2239d) {
        if (!c2239d.f11617j) {
            c2239d.f11617j = true;
            new C22351(c2239d).start();
        }
    }

    public final void m13389a() {
        synchronized (this.f11615h) {
            if (this.f11613f) {
                if (!(this.f11609b == null || this.f11610c == null)) {
                    try {
                        this.f11609b.listen(this.f11610c, 0);
                    } catch (Exception e) {
                        this.f11613f = false;
                    }
                }
                this.f11613f = false;
                return;
            }
        }
    }

    public final boolean m13390a(Context context, C2238c c2238c) {
        synchronized (this.f11615h) {
            if (this.f11613f) {
                return true;
            } else if (context == null || c2238c == null) {
                return false;
            } else {
                this.f11608a = context;
                this.f11611d = c2238c;
                try {
                    this.f11609b = (TelephonyManager) this.f11608a.getSystemService("phone");
                    if (this.f11609b == null) {
                        return false;
                    }
                    int phoneType = this.f11609b.getPhoneType();
                    this.f11610c = new C2236a(this, m13380a(phoneType), phoneType);
                    if (this.f11610c == null) {
                        return false;
                    }
                    this.f11609b.listen(this.f11610c, 18);
                    this.f11613f = true;
                    return this.f11613f;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    public final List<NeighboringCellInfo> m13391b() {
        List<NeighboringCellInfo> list = null;
        synchronized (this.f11616i) {
            if (this.f11614g != null) {
                list = new LinkedList();
                list.addAll(this.f11614g);
            }
        }
        return list;
    }
}
