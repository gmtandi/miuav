package com.p016a;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.zip.CRC32;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.di */
public class di {
    public String f846A;
    public String f847B;
    public String f848C;
    public String f849D;
    public String f850E;
    public String f851F;
    public byte[] f852G;
    public String f853a;
    public short f854b;
    public String f855c;
    public String f856d;
    public String f857e;
    public String f858f;
    public String f859g;
    public String f860h;
    public String f861i;
    public String f862j;
    public String f863k;
    public String f864l;
    public String f865m;
    public String f866n;
    public String f867o;
    public String f868p;
    public String f869q;
    public String f870r;
    public String f871s;
    public String f872t;
    public String f873u;
    public String f874v;
    public String f875w;
    public String f876x;
    public String f877y;
    public String f878z;

    public di() {
        this.f853a = Constants.VIA_TO_TYPE_QQ_GROUP;
        this.f854b = (short) 0;
        this.f855c = null;
        this.f856d = null;
        this.f857e = null;
        this.f858f = null;
        this.f859g = null;
        this.f860h = null;
        this.f861i = null;
        this.f862j = null;
        this.f863k = null;
        this.f864l = null;
        this.f865m = null;
        this.f866n = null;
        this.f867o = null;
        this.f868p = null;
        this.f869q = null;
        this.f870r = null;
        this.f871s = null;
        this.f872t = null;
        this.f873u = null;
        this.f874v = null;
        this.f875w = null;
        this.f876x = null;
        this.f877y = null;
        this.f878z = null;
        this.f846A = null;
        this.f847B = null;
        this.f848C = null;
        this.f849D = null;
        this.f850E = null;
        this.f851F = null;
        this.f852G = null;
    }

    private String m1455a(String str, int i) {
        String[] split = this.f847B.split("\\*")[i].split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        return str.equals("lac") ? split[0] : str.equals("cellid") ? split[1] : str.equals("signal") ? split[2] : null;
    }

    public static void m1456a(byte[] bArr, int i) {
    }

    private byte[] m1457b(String str) {
        String[] split = str.split(":");
        if (split == null || split.length != 6) {
            String[] strArr = new String[6];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = Constants.VIA_RESULT_SUCCESS;
            }
            split = strArr;
        }
        byte[] bArr = new byte[6];
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].length() > 2) {
                split[i2] = split[i2].substring(0, 2);
            }
            bArr[i2] = (byte) Integer.parseInt(split[i2], 16);
        }
        return bArr;
    }

    public String m1458a(String str) {
        if (!this.f846A.contains(str + ">")) {
            return Constants.VIA_RESULT_SUCCESS;
        }
        int indexOf = this.f846A.indexOf(str + ">");
        return this.f846A.substring((indexOf + str.length()) + 1, this.f846A.indexOf("</" + str));
    }

    public byte[] m1459a() {
        Object b;
        Object e;
        m1460b();
        int i = 3072;
        if (this.f852G != null) {
            i = 3072 + (this.f852G.length + 1);
        }
        Object obj = new byte[i];
        obj[0] = Byte.parseByte(this.f853a);
        Object b2 = dn.m1524b(this.f854b);
        System.arraycopy(b2, 0, obj, 1, b2.length);
        int length = b2.length + 1;
        try {
            b2 = this.f855c.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th) {
            ev.m1777a(th, "Req", "buildV4Dot2");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f856d.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th2) {
            ev.m1777a(th2, "Req", "buildV4Dot21");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f867o.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th22) {
            ev.m1777a(th22, "Req", "buildV4Dot22");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f857e.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th222) {
            ev.m1777a(th222, "Req", "buildV4Dot23");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f858f.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th2222) {
            ev.m1777a(th2222, "Req", "buildV4Dot24");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f859g.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th22222) {
            ev.m1777a(th22222, "Req", "buildV4Dot25");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f873u.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th222222) {
            ev.m1777a(th222222, "Req", "buildV4Dot26");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f860h.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th2222222) {
            ev.m1777a(th2222222, "Req", "buildV4Dot27");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f868p.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th22222222) {
            ev.m1777a(th22222222, "Req", "buildV4Dot28");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f869q.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            i = b2.length + length;
        } catch (Throwable th222222222) {
            ev.m1777a(th222222222, "Req", "buildV4Dot29");
            obj[length] = null;
            i = length + 1;
        }
        if (TextUtils.isEmpty(this.f872t)) {
            obj[i] = null;
            length = i + 1;
        } else {
            b = m1457b(this.f872t);
            obj[i] = (byte) b.length;
            i++;
            System.arraycopy(b, 0, obj, i, b.length);
            length = b.length + i;
        }
        try {
            b2 = this.f874v.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th2222222222) {
            ev.m1777a(th2222222222, "Req", "buildV4Dot211");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f875w.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            length += b2.length;
        } catch (Throwable th22222222222) {
            ev.m1777a(th22222222222, "Req", "buildV4Dot212");
            obj[length] = null;
            length++;
        }
        try {
            b2 = this.f876x.getBytes("GBK");
            obj[length] = (byte) b2.length;
            length++;
            System.arraycopy(b2, 0, obj, length, b2.length);
            i = b2.length + length;
        } catch (Throwable th222222222222) {
            ev.m1777a(th222222222222, "Req", "buildV4Dot213");
            obj[length] = null;
            i = length + 1;
        }
        obj[i] = Byte.parseByte(this.f877y);
        i++;
        obj[i] = Byte.parseByte(this.f862j);
        i++;
        if (this.f862j.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
            obj[i] = Byte.parseByte(this.f863k);
            i++;
        }
        if (this.f862j.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f862j.equals(Constants.VIA_SSO_LOGIN)) {
            b = dn.m1530c(Integer.parseInt(this.f864l));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
        }
        if (this.f862j.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f862j.equals(Constants.VIA_SSO_LOGIN)) {
            b = dn.m1530c(Integer.parseInt(this.f865m));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
        }
        if (this.f862j.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f862j.equals(Constants.VIA_SSO_LOGIN)) {
            b = dn.m1536e(this.f866n);
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
        }
        obj[i] = Byte.parseByte(this.f878z);
        i++;
        if (this.f878z.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
            b = dn.m1536e(m1458a("mcc"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1536e(m1458a("mnc"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1536e(m1458a("lac"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1538f(m1458a("cellid"));
            System.arraycopy(b, 0, obj, i, b.length);
            length = b.length + i;
            i = Integer.parseInt(m1458a("signal"));
            if (i > Opcodes.LAND) {
                i = 0;
            }
            obj[length] = (byte) i;
            i = length + 1;
            if (this.f847B.length() == 0) {
                obj[i] = null;
                i++;
            } else {
                int length2 = this.f847B.split("\\*").length;
                obj[i] = (byte) length2;
                i++;
                length = 0;
                while (length < length2) {
                    e = dn.m1536e(m1455a("lac", length));
                    System.arraycopy(e, 0, obj, i, e.length);
                    i += e.length;
                    e = dn.m1538f(m1455a("cellid", length));
                    System.arraycopy(e, 0, obj, i, e.length);
                    int length3 = e.length + i;
                    i = Integer.parseInt(m1455a("signal", length));
                    if (i > Opcodes.LAND) {
                        i = 0;
                    }
                    obj[length3] = (byte) i;
                    length++;
                    i = length3 + 1;
                }
            }
        } else if (this.f878z.equals(Constants.VIA_SSO_LOGIN)) {
            b = dn.m1536e(m1458a("mcc"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1536e(m1458a("sid"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1536e(m1458a("nid"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1536e(m1458a("bid"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1538f(m1458a("lon"));
            System.arraycopy(b, 0, obj, i, b.length);
            i += b.length;
            b = dn.m1538f(m1458a("lat"));
            System.arraycopy(b, 0, obj, i, b.length);
            length = b.length + i;
            i = Integer.parseInt(m1458a("signal"));
            if (i > Opcodes.LAND) {
                i = 0;
            }
            obj[length] = (byte) i;
            i = length + 1;
            obj[i] = null;
            i++;
        }
        if (this.f848C.length() == 0) {
            obj[i] = null;
            i++;
        } else {
            String[] split;
            obj[i] = 1;
            length = i + 1;
            try {
                split = this.f848C.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                b2 = m1457b(split[0]);
                System.arraycopy(b2, 0, obj, length, b2.length);
                length += b2.length;
                b2 = split[2].getBytes("GBK");
                obj[length] = (byte) b2.length;
                length++;
                System.arraycopy(b2, 0, obj, length, b2.length);
                length += b2.length;
            } catch (Throwable th2222222222222) {
                ev.m1777a(th2222222222222, "Req", "buildV4Dot216");
                b2 = m1457b("00:00:00:00:00:00");
                System.arraycopy(b2, 0, obj, length, b2.length);
                i = b2.length + length;
                obj[i] = null;
                i++;
                obj[i] = Byte.parseByte(Constants.VIA_RESULT_SUCCESS);
                i++;
            }
            i = Integer.parseInt(split[1]);
            if (i > Opcodes.LAND) {
                i = 0;
            }
            obj[length] = Byte.parseByte(String.valueOf(i));
            i = length + 1;
        }
        String[] split2 = this.f849D.split("\\*");
        if (TextUtils.isEmpty(this.f849D) || split2.length == 0) {
            obj[i] = null;
            length = i + 1;
        } else {
            obj[i] = (byte) split2.length;
            i++;
            length3 = 0;
            while (length3 < split2.length) {
                String[] split3 = split2[length3].split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                b = m1457b(split3[0]);
                System.arraycopy(b, 0, obj, i, b.length);
                length = b.length + i;
                try {
                    b2 = split3[2].getBytes("GBK");
                    obj[length] = (byte) b2.length;
                    length++;
                    System.arraycopy(b2, 0, obj, length, b2.length);
                    i = b2.length + length;
                } catch (Throwable th22222222222222) {
                    ev.m1777a(th22222222222222, "Req", "buildV4Dot217");
                    obj[length] = null;
                    i = length + 1;
                }
                length = Integer.parseInt(split3[1]);
                if (length > Opcodes.LAND) {
                    length = 0;
                }
                obj[i] = Byte.parseByte(String.valueOf(length));
                length3++;
                i++;
            }
            b = dn.m1524b(Integer.parseInt(this.f850E));
            System.arraycopy(b, 0, obj, i, b.length);
            length = b.length + i;
        }
        try {
            b2 = this.f851F.getBytes("GBK");
            if (b2.length > Opcodes.LAND) {
                b2 = null;
            }
            if (b2 == null) {
                obj[length] = (byte) 0;
                i = length + 1;
            } else {
                obj[length] = (byte) b2.length;
                length++;
                System.arraycopy(b2, 0, obj, length, b2.length);
                i = b2.length + length;
            }
        } catch (Throwable th222222222222222) {
            ev.m1777a(th222222222222222, "Req", "buildV4Dot218");
            obj[length] = null;
            i = length + 1;
        }
        length = this.f852G != null ? this.f852G.length : 0;
        e = dn.m1524b(length);
        System.arraycopy(e, 0, obj, i, e.length);
        i += e.length;
        if (length > 0) {
            System.arraycopy(this.f852G, 0, obj, i, this.f852G.length);
            i += this.f852G.length;
        }
        b = new byte[i];
        System.arraycopy(obj, 0, b, 0, i);
        CRC32 crc32 = new CRC32();
        crc32.update(b);
        e = dn.m1515a(crc32.getValue());
        byte[] bArr = new byte[(e.length + i)];
        System.arraycopy(b, 0, bArr, 0, i);
        System.arraycopy(e, 0, bArr, i, e.length);
        i += e.length;
        di.m1456a(bArr, 0);
        return bArr;
    }

    public void m1460b() {
        if (TextUtils.isEmpty(this.f853a)) {
            this.f853a = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f855c)) {
            this.f855c = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f856d)) {
            this.f856d = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f857e)) {
            this.f857e = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f858f)) {
            this.f858f = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f859g)) {
            this.f859g = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f860h)) {
            this.f860h = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f861i)) {
            this.f861i = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f862j)) {
            this.f862j = Constants.VIA_RESULT_SUCCESS;
        } else if (!(this.f862j.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f862j.equals(Constants.VIA_SSO_LOGIN))) {
            this.f862j = Constants.VIA_RESULT_SUCCESS;
        }
        if (TextUtils.isEmpty(this.f863k)) {
            this.f863k = Constants.VIA_RESULT_SUCCESS;
        } else if (!(this.f863k.equals(Constants.VIA_RESULT_SUCCESS) || this.f863k.equals(Constants.VIA_TO_TYPE_QQ_GROUP))) {
            this.f863k = Constants.VIA_RESULT_SUCCESS;
        }
        if (TextUtils.isEmpty(this.f864l)) {
            this.f864l = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f865m)) {
            this.f865m = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f866n)) {
            this.f866n = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f867o)) {
            this.f867o = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f868p)) {
            this.f868p = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f869q)) {
            this.f869q = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f870r)) {
            this.f870r = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f871s)) {
            this.f871s = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f872t)) {
            this.f872t = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f873u)) {
            this.f873u = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f874v)) {
            this.f874v = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f875w)) {
            this.f875w = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f876x)) {
            this.f876x = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f877y)) {
            this.f877y = Constants.VIA_RESULT_SUCCESS;
        } else if (!(this.f877y.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f877y.equals(Constants.VIA_SSO_LOGIN))) {
            this.f877y = Constants.VIA_RESULT_SUCCESS;
        }
        if (TextUtils.isEmpty(this.f878z)) {
            this.f878z = Constants.VIA_RESULT_SUCCESS;
        } else if (!(this.f878z.equals(Constants.VIA_TO_TYPE_QQ_GROUP) || this.f878z.equals(Constants.VIA_SSO_LOGIN))) {
            this.f878z = Constants.VIA_RESULT_SUCCESS;
        }
        if (TextUtils.isEmpty(this.f846A)) {
            this.f846A = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f847B)) {
            this.f847B = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f848C)) {
            this.f848C = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f849D)) {
            this.f849D = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f850E)) {
            this.f850E = C2915a.f14760f;
        }
        if (TextUtils.isEmpty(this.f851F)) {
            this.f851F = C2915a.f14760f;
        }
        if (this.f852G == null) {
            this.f852G = new byte[0];
        }
    }
}
