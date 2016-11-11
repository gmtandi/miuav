package com.p016a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.model.AmapLoc;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.f */
public class C0246f extends Handler {
    C0238a f1172a;

    public C0246f(C0238a c0238a) {
        this.f1172a = null;
        this.f1172a = c0238a;
    }

    public C0246f(C0238a c0238a, Looper looper) {
        super(looper);
        this.f1172a = null;
        this.f1172a = c0238a;
    }

    public void handleMessage(Message message) {
        Throwable th;
        long time;
        C0243d a;
        StringBuffer stringBuffer;
        Iterator it;
        Iterator it2;
        Throwable th2;
        super.handleMessage(message);
        AMapLocation aMapLocation = null;
        AMapLocation aMapLocation2;
        Message obtain;
        C0238a c0238a;
        switch (message.what) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                try {
                    Bundle data = message.getData();
                    if (data != null) {
                        data.setClassLoader(AmapLoc.class.getClassLoader());
                        AMapLocation a2 = ev.m1773a((AmapLoc) data.getParcelable("location"));
                        if (a2 == null) {
                            try {
                                aMapLocation = new AMapLocation(C2915a.f14760f);
                                aMapLocation.setErrorCode(8);
                                aMapLocation2 = aMapLocation;
                            } catch (Throwable th3) {
                                th = th3;
                                aMapLocation = a2;
                                ev.m1777a(th, "AMapLocationManager", "handleMessage LBS_LOCATIONSUCCESS");
                                if (this.f1172a.f485e != null) {
                                    this.f1172a.f485e.m1805a(aMapLocation);
                                }
                                if (aMapLocation != null) {
                                    try {
                                        if (!this.f1172a.f502w) {
                                            if (aMapLocation.getErrorCode() == 0) {
                                                time = aMapLocation.getTime();
                                                aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                                                aMapLocation.setTime(time);
                                                if (this.f1172a.f479D != 0) {
                                                    a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                                                    this.f1172a.f478C.add(a);
                                                    break;
                                                }
                                                a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                                                if (this.f1172a.f478C.size() <= 1) {
                                                    this.f1172a.f478C.add(a);
                                                } else if (this.f1172a.f478C.size() != 1) {
                                                    this.f1172a.f478C.add(a);
                                                } else {
                                                    this.f1172a.f478C.set(0, a);
                                                }
                                                if (this.f1172a.f478C.size() >= 10) {
                                                    stringBuffer = new StringBuffer();
                                                    it = this.f1172a.f478C.iterator();
                                                    while (it.hasNext()) {
                                                        stringBuffer.append(((C0243d) it.next()).toString());
                                                        stringBuffer.append("#");
                                                    }
                                                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                                    ev.m1780b(stringBuffer.toString());
                                                    this.f1172a.f478C.clear();
                                                }
                                            }
                                            this.f1172a.f498r = dn.m1519b();
                                            this.f1172a.f497q = aMapLocation;
                                            if (this.f1172a.f487g != null) {
                                                this.f1172a.f487g.m1814a(aMapLocation);
                                            }
                                            it2 = this.f1172a.f484d.iterator();
                                            while (it2.hasNext()) {
                                                ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                                            }
                                            break;
                                        }
                                    } catch (Throwable th4) {
                                        ev.m1777a(th4, "AMapLocationManager", "handleMessage part7");
                                    }
                                }
                                if (this.f1172a.f481a.isOnceLocation()) {
                                    this.f1172a.stopLocation();
                                }
                            }
                        }
                        aMapLocation2 = a2;
                        try {
                            aMapLocation2.setProvider("lbs");
                        } catch (Throwable th5) {
                            th2 = th5;
                            aMapLocation = aMapLocation2;
                            th4 = th2;
                            ev.m1777a(th4, "AMapLocationManager", "handleMessage LBS_LOCATIONSUCCESS");
                            if (this.f1172a.f485e != null) {
                                this.f1172a.f485e.m1805a(aMapLocation);
                            }
                            if (aMapLocation != null) {
                                if (this.f1172a.f502w) {
                                    if (aMapLocation.getErrorCode() == 0) {
                                        time = aMapLocation.getTime();
                                        aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                                        aMapLocation.setTime(time);
                                        if (this.f1172a.f479D != 0) {
                                            a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                                            this.f1172a.f478C.add(a);
                                        } else {
                                            a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                                            if (this.f1172a.f478C.size() <= 1) {
                                                this.f1172a.f478C.add(a);
                                            } else if (this.f1172a.f478C.size() != 1) {
                                                this.f1172a.f478C.add(a);
                                            } else {
                                                this.f1172a.f478C.set(0, a);
                                            }
                                        }
                                        if (this.f1172a.f478C.size() >= 10) {
                                            stringBuffer = new StringBuffer();
                                            it = this.f1172a.f478C.iterator();
                                            while (it.hasNext()) {
                                                stringBuffer.append(((C0243d) it.next()).toString());
                                                stringBuffer.append("#");
                                            }
                                            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                            ev.m1780b(stringBuffer.toString());
                                            this.f1172a.f478C.clear();
                                        }
                                    }
                                    this.f1172a.f498r = dn.m1519b();
                                    this.f1172a.f497q = aMapLocation;
                                    if (this.f1172a.f487g != null) {
                                        this.f1172a.f487g.m1814a(aMapLocation);
                                    }
                                    it2 = this.f1172a.f484d.iterator();
                                    while (it2.hasNext()) {
                                        ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                                    }
                                }
                            }
                            if (this.f1172a.f481a.isOnceLocation()) {
                                this.f1172a.stopLocation();
                            }
                        }
                    }
                    aMapLocation2 = null;
                    aMapLocation = aMapLocation2;
                } catch (Throwable th6) {
                    th4 = th6;
                    ev.m1777a(th4, "AMapLocationManager", "handleMessage LBS_LOCATIONSUCCESS");
                    if (this.f1172a.f485e != null) {
                        this.f1172a.f485e.m1805a(aMapLocation);
                    }
                    if (aMapLocation != null) {
                        if (this.f1172a.f502w) {
                            if (aMapLocation.getErrorCode() == 0) {
                                time = aMapLocation.getTime();
                                aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                                aMapLocation.setTime(time);
                                if (this.f1172a.f479D != 0) {
                                    a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                                    if (this.f1172a.f478C.size() <= 1) {
                                        this.f1172a.f478C.add(a);
                                    } else if (this.f1172a.f478C.size() != 1) {
                                        this.f1172a.f478C.set(0, a);
                                    } else {
                                        this.f1172a.f478C.add(a);
                                    }
                                } else {
                                    a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                                    this.f1172a.f478C.add(a);
                                }
                                if (this.f1172a.f478C.size() >= 10) {
                                    stringBuffer = new StringBuffer();
                                    it = this.f1172a.f478C.iterator();
                                    while (it.hasNext()) {
                                        stringBuffer.append(((C0243d) it.next()).toString());
                                        stringBuffer.append("#");
                                    }
                                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                    ev.m1780b(stringBuffer.toString());
                                    this.f1172a.f478C.clear();
                                }
                            }
                            this.f1172a.f498r = dn.m1519b();
                            this.f1172a.f497q = aMapLocation;
                            if (this.f1172a.f487g != null) {
                                this.f1172a.f487g.m1814a(aMapLocation);
                            }
                            it2 = this.f1172a.f484d.iterator();
                            while (it2.hasNext()) {
                                ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                            }
                        }
                    }
                    if (this.f1172a.f481a.isOnceLocation()) {
                        this.f1172a.stopLocation();
                    }
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                try {
                    aMapLocation2 = (AMapLocation) message.obj;
                    try {
                        if (aMapLocation2.getErrorCode() == 0) {
                            this.f1172a.f495o = dn.m1519b();
                            this.f1172a.f494n = true;
                        }
                        if (!(C0238a.f475t || this.f1172a.f488h == null)) {
                            Message obtain2 = Message.obtain();
                            obtain2.what = 0;
                            obtain2.replyTo = this.f1172a.f489i;
                            this.f1172a.f488h.send(obtain2);
                            C0238a.f475t = true;
                        }
                        aMapLocation = aMapLocation2;
                    } catch (Throwable th52) {
                        th2 = th52;
                        aMapLocation = aMapLocation2;
                        th4 = th2;
                        ev.m1777a(th4, "AMapLocationManager", "handleMessage GPS_LOCATIONSUCCESS");
                        if (this.f1172a.f485e != null) {
                            this.f1172a.f485e.m1805a(aMapLocation);
                        }
                        if (aMapLocation != null) {
                            if (this.f1172a.f502w) {
                                if (aMapLocation.getErrorCode() == 0) {
                                    time = aMapLocation.getTime();
                                    aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                                    aMapLocation.setTime(time);
                                    if (this.f1172a.f479D != 0) {
                                        a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                                        this.f1172a.f478C.add(a);
                                        break;
                                    }
                                    a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                                    if (this.f1172a.f478C.size() <= 1) {
                                        this.f1172a.f478C.add(a);
                                    } else if (this.f1172a.f478C.size() != 1) {
                                        this.f1172a.f478C.add(a);
                                    } else {
                                        this.f1172a.f478C.set(0, a);
                                    }
                                    if (this.f1172a.f478C.size() >= 10) {
                                        stringBuffer = new StringBuffer();
                                        it = this.f1172a.f478C.iterator();
                                        while (it.hasNext()) {
                                            stringBuffer.append(((C0243d) it.next()).toString());
                                            stringBuffer.append("#");
                                        }
                                        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                        ev.m1780b(stringBuffer.toString());
                                        this.f1172a.f478C.clear();
                                    }
                                }
                                this.f1172a.f498r = dn.m1519b();
                                this.f1172a.f497q = aMapLocation;
                                if (this.f1172a.f487g != null) {
                                    this.f1172a.f487g.m1814a(aMapLocation);
                                }
                                it2 = this.f1172a.f484d.iterator();
                                while (it2.hasNext()) {
                                    ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                                }
                                break;
                            }
                        }
                        if (this.f1172a.f481a.isOnceLocation()) {
                            this.f1172a.stopLocation();
                        }
                    }
                } catch (Throwable th7) {
                    th4 = th7;
                    ev.m1777a(th4, "AMapLocationManager", "handleMessage GPS_LOCATIONSUCCESS");
                    if (this.f1172a.f485e != null) {
                        this.f1172a.f485e.m1805a(aMapLocation);
                    }
                    if (aMapLocation != null) {
                        if (this.f1172a.f502w) {
                            if (aMapLocation.getErrorCode() == 0) {
                                time = aMapLocation.getTime();
                                aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                                aMapLocation.setTime(time);
                                if (this.f1172a.f479D != 0) {
                                    a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                                    if (this.f1172a.f478C.size() <= 1) {
                                        this.f1172a.f478C.add(a);
                                    } else if (this.f1172a.f478C.size() != 1) {
                                        this.f1172a.f478C.set(0, a);
                                    } else {
                                        this.f1172a.f478C.add(a);
                                    }
                                } else {
                                    a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                                    this.f1172a.f478C.add(a);
                                }
                                if (this.f1172a.f478C.size() >= 10) {
                                    stringBuffer = new StringBuffer();
                                    it = this.f1172a.f478C.iterator();
                                    while (it.hasNext()) {
                                        stringBuffer.append(((C0243d) it.next()).toString());
                                        stringBuffer.append("#");
                                    }
                                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                                    ev.m1780b(stringBuffer.toString());
                                    this.f1172a.f478C.clear();
                                }
                            }
                            this.f1172a.f498r = dn.m1519b();
                            this.f1172a.f497q = aMapLocation;
                            if (this.f1172a.f487g != null) {
                                this.f1172a.f487g.m1814a(aMapLocation);
                            }
                            it2 = this.f1172a.f484d.iterator();
                            while (it2.hasNext()) {
                                ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                            }
                        }
                    }
                    if (this.f1172a.f481a.isOnceLocation()) {
                        this.f1172a.stopLocation();
                    }
                }
            case Type.INT /*5*/:
                this.f1172a.f495o = dn.m1519b();
                this.f1172a.f494n = true;
                return;
            case Opcodes.ISUB /*100*/:
                try {
                    this.f1172a.m954f();
                    return;
                } catch (Throwable th42) {
                    ev.m1777a(th42, "AMapLocationManager", "handleMessage FASTSKY");
                    return;
                }
            case Opcodes.LSUB /*101*/:
                try {
                    obtain = Message.obtain();
                    obtain.what = 2;
                    if (this.f1172a.f488h != null) {
                        this.f1172a.f493m = 0;
                        this.f1172a.f488h.send(obtain);
                        return;
                    }
                    c0238a = this.f1172a;
                    c0238a.f493m++;
                    if (this.f1172a.f493m < 10) {
                        this.f1172a.f482b.sendEmptyMessageDelayed(Opcodes.LSUB, 50);
                        return;
                    }
                    return;
                } catch (Throwable th422) {
                    ev.m1777a(th422, "AMapLocationManager", "handleMessage START_SOCKET");
                    return;
                }
            case Opcodes.FSUB /*102*/:
                try {
                    obtain = Message.obtain();
                    obtain.what = 3;
                    if (this.f1172a.f488h != null) {
                        this.f1172a.f493m = 0;
                        this.f1172a.f488h.send(obtain);
                        return;
                    }
                    c0238a = this.f1172a;
                    c0238a.f493m++;
                    if (this.f1172a.f493m < 10) {
                        this.f1172a.f482b.sendEmptyMessageDelayed(Opcodes.FSUB, 50);
                        return;
                    }
                    return;
                } catch (Throwable th4222) {
                    ev.m1777a(th4222, "AMapLocationManager", "handleMessage STOP_SOCKET");
                    return;
                }
            default:
                return;
        }
        try {
            if (this.f1172a.f485e != null) {
                this.f1172a.f485e.m1805a(aMapLocation);
            }
        } catch (Throwable th42222) {
            ev.m1777a(th42222, "AMapLocationManager", "handleMessage part6");
        }
        if (aMapLocation != null) {
            if (this.f1172a.f502w) {
                if (aMapLocation.getErrorCode() == 0) {
                    time = aMapLocation.getTime();
                    aMapLocation = this.f1172a.m940a(this.f1172a.f497q, aMapLocation);
                    aMapLocation.setTime(time);
                    if (this.f1172a.f479D != 0) {
                        a = this.f1172a.m938a(aMapLocation, this.f1172a.f479D);
                        if (this.f1172a.f478C.size() <= 1) {
                            this.f1172a.f478C.add(a);
                        } else if (this.f1172a.f478C.size() != 1) {
                            this.f1172a.f478C.set(0, a);
                        } else {
                            this.f1172a.f478C.add(a);
                        }
                    } else {
                        a = this.f1172a.m938a(this.f1172a.f480E, this.f1172a.f479D);
                        if (!this.f1172a.f478C.contains(a) && this.f1172a.f478C.size() <= 9) {
                            this.f1172a.f478C.add(a);
                        }
                    }
                    if (this.f1172a.f478C.size() >= 10) {
                        stringBuffer = new StringBuffer();
                        it = this.f1172a.f478C.iterator();
                        while (it.hasNext()) {
                            stringBuffer.append(((C0243d) it.next()).toString());
                            stringBuffer.append("#");
                        }
                        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                        ev.m1780b(stringBuffer.toString());
                        this.f1172a.f478C.clear();
                    }
                }
                this.f1172a.f498r = dn.m1519b();
                this.f1172a.f497q = aMapLocation;
                if (GeocodeSearch.GPS.equals(aMapLocation.getProvider()) || this.f1172a.m950d()) {
                    if (this.f1172a.f487g != null) {
                        this.f1172a.f487g.m1814a(aMapLocation);
                    }
                    it2 = this.f1172a.f484d.iterator();
                    while (it2.hasNext()) {
                        ((AMapLocationListener) it2.next()).onLocationChanged(aMapLocation);
                    }
                }
            }
        }
        try {
            if (this.f1172a.f481a.isOnceLocation()) {
                this.f1172a.stopLocation();
            }
        } catch (Throwable th422222) {
            ev.m1777a(th422222, "AMapLocationManager", "handleMessage part8");
        }
    }
}
