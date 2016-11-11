package com.p016a;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import java.util.List;

/* renamed from: com.a.eu */
public final class eu {
    private static int f1136c;
    private static int f1137d;
    private static float f1138f;
    protected ez f1139a;
    protected ew f1140b;
    private eg f1141e;

    static {
        f1136c = 10;
        f1137d = 100;
        f1138f = 0.5f;
    }

    protected eu(eg egVar) {
        this.f1139a = new ez(this);
        this.f1140b = new ew(this);
        this.f1141e = egVar;
    }

    protected static void m1767a() {
    }

    protected static void m1768a(int i) {
        f1136c = i;
    }

    protected static void m1769b(int i) {
        f1137d = i;
    }

    protected final boolean m1770a(Location location) {
        boolean z = false;
        if (this.f1141e != null) {
            List j = this.f1141e.m1732j();
            if (!(j == null || location == null)) {
                "cell.list.size: " + j.size();
                ex exVar = null;
                if (j.size() >= 2) {
                    ex exVar2 = new ex((CellLocation) j.get(1));
                    if (this.f1140b.f1163b == null) {
                        exVar = exVar2;
                        z = true;
                    } else {
                        boolean z2 = location.distanceTo(this.f1140b.f1163b) > ((float) f1137d);
                        if (!z2) {
                            int i;
                            exVar = this.f1140b.f1162a;
                            if (exVar2.f1168e == exVar.f1168e && exVar2.f1167d == exVar.f1167d && exVar2.f1166c == exVar.f1166c && exVar2.f1165b == exVar.f1165b && exVar2.f1164a == exVar.f1164a) {
                                i = 1;
                            } else {
                                z2 = false;
                            }
                            z2 = i == 0;
                        }
                        "collect cell?: " + z2;
                        z = z2;
                        exVar = exVar2;
                    }
                }
                if (z) {
                    this.f1140b.f1162a = exVar;
                }
            }
        }
        return z;
    }

    protected final boolean m1771b(Location location) {
        int i = 0;
        if (this.f1141e == null) {
            return false;
        }
        boolean z;
        List list;
        List a = this.f1141e.m1712a(false);
        if (a.size() >= 2) {
            List list2 = (List) a.get(1);
            if (this.f1139a.f1171b == null) {
                z = true;
            } else if (list2 == null || list2.size() <= 0) {
                list = list2;
                z = false;
            } else {
                z = location.distanceTo(this.f1139a.f1171b) > ((float) f1136c);
                if (z) {
                    list = list2;
                } else {
                    int i2;
                    List list3 = this.f1139a.f1170a;
                    float f = f1138f;
                    if (list2 == null || list3 == null) {
                        i2 = 0;
                    } else if (list2 == null || list3 == null) {
                        i2 = 0;
                    } else {
                        int size = list2.size();
                        int size2 = list3.size();
                        float f2 = (float) (size + size2);
                        if (size == 0 && size2 == 0) {
                            i2 = 1;
                        } else if (size == 0 || size2 == 0) {
                            i2 = 0;
                        } else {
                            int i3 = 0;
                            int i4 = 0;
                            while (i3 < size) {
                                String str = ((ScanResult) list2.get(i3)).BSSID;
                                if (str != null) {
                                    for (int i5 = 0; i5 < size2; i5++) {
                                        if (str.equals(((ey) list3.get(i5)).f1169a)) {
                                            i2 = i4 + 1;
                                            break;
                                        }
                                    }
                                }
                                i2 = i4;
                                i3++;
                                i4 = i2;
                            }
                            i2 = ((float) (i4 << 1)) >= f2 * f ? 1 : 0;
                        }
                    }
                    z = i2 == 0;
                }
            }
            list = list2;
        } else {
            list = null;
            z = false;
        }
        if (z) {
            this.f1139a.f1170a.clear();
            int size3 = list.size();
            while (i < size3) {
                this.f1139a.f1170a.add(new ey(((ScanResult) list.get(i)).BSSID));
                i++;
            }
        }
        return z;
    }
}
