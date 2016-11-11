package com.p016a;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.market.sdk.C2537j;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.a.ed */
public class ed {
    private Context f1055a;
    private int f1056b;
    private int f1057c;
    private int f1058d;
    private int f1059e;
    private int f1060f;
    private int f1061g;

    protected ed(Context context) {
        this.f1056b = 0;
        this.f1057c = 0;
        this.f1058d = 0;
        this.f1059e = 0;
        this.f1060f = 0;
        this.f1061g = 0;
        this.f1055a = context;
        m1660a(768);
        m1662b(768);
    }

    private static int m1642a(int i, int i2) {
        return i < i2 ? i : i2;
    }

    protected static dv m1643a(Location location, eg egVar, int i, byte b, long j, boolean z) {
        dv dvVar = new dv();
        if (i <= 0 || i > 3 || egVar == null) {
            return null;
        }
        int i2;
        int i3;
        Object obj = (i == 1 || i == 3) ? 1 : null;
        Object obj2 = (i == 2 || i == 3) ? 1 : null;
        Object bytes = egVar.m1737o().getBytes();
        System.arraycopy(bytes, 0, dvVar.f971c, 0, ed.m1642a(bytes.length, dvVar.f971c.length));
        bytes = egVar.m1725f().getBytes();
        System.arraycopy(bytes, 0, dvVar.f975g, 0, ed.m1642a(bytes.length, dvVar.f975g.length));
        bytes = egVar.m1727g().getBytes();
        System.arraycopy(bytes, 0, dvVar.f969a, 0, ed.m1642a(bytes.length, dvVar.f969a.length));
        bytes = egVar.m1728h().getBytes();
        System.arraycopy(bytes, 0, dvVar.f970b, 0, ed.m1642a(bytes.length, dvVar.f970b.length));
        dvVar.f972d = (short) egVar.m1738p();
        dvVar.f973e = (short) egVar.m1739q();
        dvVar.f974f = (byte) egVar.m1740r();
        bytes = egVar.m1741s().getBytes();
        System.arraycopy(bytes, 0, dvVar.f976h, 0, ed.m1642a(bytes.length, dvVar.f976h.length));
        long j2 = j / 1000;
        bytes = (location == null || !egVar.m1723e()) ? null : 1;
        dp dpVar;
        if (bytes != null) {
            dpVar = new dp();
            dpVar.f916b = (int) j2;
            dq dqVar = new dq();
            dqVar.f922a = (int) (location.getLongitude() * 1000000.0d);
            dqVar.f923b = (int) (location.getLatitude() * 1000000.0d);
            dqVar.f924c = (int) location.getAltitude();
            dqVar.f925d = (int) location.getAccuracy();
            dqVar.f926e = (int) location.getSpeed();
            dqVar.f927f = (short) ((int) location.getBearing());
            if (Build.MODEL.equals(C2537j.ap) || (eg.m1697b(egVar.m1746x()) && dw.f993b)) {
                dqVar.f928g = (byte) 1;
            } else {
                dqVar.f928g = (byte) 0;
            }
            dqVar.f929h = b;
            if (dqVar.f925d > 25) {
                dqVar.f929h = (byte) 5;
            }
            dqVar.f930i = System.currentTimeMillis();
            dqVar.f931j = egVar.m1736n();
            dpVar.f917c = dqVar;
            i2 = 1;
            dvVar.f978j.add(dpVar);
        } else if (!z) {
            return null;
        } else {
            dpVar = new dp();
            dpVar.f916b = (int) j2;
            dy dyVar = new dy();
            dyVar.f1030a = egVar.m1745w();
            for (i2 = 0; i2 < dyVar.f1030a; i2++) {
                dz dzVar = new dz();
                dzVar.f1033a = (byte) egVar.m1715b(i2).length();
                System.arraycopy(egVar.m1715b(i2).getBytes(), 0, dzVar.f1034b, 0, dzVar.f1033a);
                dzVar.f1035c = egVar.m1718c(i2);
                dzVar.f1036d = egVar.m1720d(i2);
                dzVar.f1037e = egVar.m1722e(i2);
                dzVar.f1038f = egVar.m1724f(i2);
                dzVar.f1039g = egVar.m1726g(i2);
                dzVar.f1040h = (byte) egVar.m1729h(i2).length();
                System.arraycopy(egVar.m1729h(i2).getBytes(), 0, dzVar.f1041i, 0, dzVar.f1040h);
                dzVar.f1042j = egVar.m1730i(i2);
                dyVar.f1031b.add(dzVar);
            }
            dpVar.f921g = dyVar;
            i2 = 1;
            dvVar.f978j.add(dpVar);
        }
        if (!(!egVar.m1719c() || egVar.m1731i() || obj == null || z)) {
            dp dpVar2 = new dp();
            dpVar2.f916b = (int) j2;
            fc fcVar = new fc();
            List a = egVar.m1711a(location.getSpeed());
            if (a != null && a.size() >= 3) {
                fcVar.f1180a = (short) ((Integer) a.get(0)).intValue();
                fcVar.f1181b = ((Integer) a.get(1)).intValue();
            }
            fcVar.f1182c = egVar.m1733k();
            List l = egVar.m1734l();
            fcVar.f1183d = (byte) l.size();
            for (i3 = 0; i3 < l.size(); i3++) {
                ef efVar = new ef();
                efVar.f1069a = (short) ((NeighboringCellInfo) l.get(i3)).getLac();
                efVar.f1070b = ((NeighboringCellInfo) l.get(i3)).getCid();
                efVar.f1071c = (byte) ((NeighboringCellInfo) l.get(i3)).getRssi();
                fcVar.f1184e.add(efVar);
            }
            dpVar2.f918d = fcVar;
            i2 = 2;
            dvVar.f978j.add(dpVar2);
        }
        i3 = i2;
        if (egVar.m1719c() && egVar.m1731i() && obj != null && !z) {
            dp dpVar3 = new dp();
            dpVar3.f916b = (int) j2;
            ee eeVar = new ee();
            List b2 = egVar.m1716b(location.getSpeed());
            if (b2 != null && b2.size() >= 6) {
                eeVar.f1062a = ((Integer) b2.get(3)).intValue();
                eeVar.f1063b = ((Integer) b2.get(4)).intValue();
                eeVar.f1064c = (short) ((Integer) b2.get(0)).intValue();
                eeVar.f1065d = (short) ((Integer) b2.get(1)).intValue();
                eeVar.f1066e = ((Integer) b2.get(2)).intValue();
                eeVar.f1067f = egVar.m1733k();
            }
            dpVar3.f919e = eeVar;
            i3++;
            dvVar.f978j.add(dpVar3);
        }
        if (!(!egVar.m1721d() || obj2 == null || z)) {
            dpVar2 = new dp();
            ea eaVar = new ea();
            List t = egVar.m1742t();
            dpVar2.f916b = (int) (((Long) t.get(0)).longValue() / 1000);
            eaVar.f1045a = (byte) (t.size() - 1);
            for (int i4 = 1; i4 < t.size(); i4++) {
                List list = (List) t.get(i4);
                if (list != null && list.size() >= 3) {
                    eb ebVar = new eb();
                    bytes = ((String) list.get(0)).getBytes();
                    System.arraycopy(bytes, 0, ebVar.f1048a, 0, ed.m1642a(bytes.length, ebVar.f1048a.length));
                    ebVar.f1049b = (short) ((Integer) list.get(1)).intValue();
                    bytes = ((String) list.get(2)).getBytes();
                    System.arraycopy(bytes, 0, ebVar.f1050c, 0, ed.m1642a(bytes.length, ebVar.f1050c.length));
                    eaVar.f1046b.add(ebVar);
                }
            }
            dpVar2.f920f = eaVar;
            i3++;
            dvVar.f978j.add(dpVar2);
        }
        dvVar.f977i = (short) i3;
        return (i3 >= 2 || z) ? dvVar : null;
    }

    protected static File m1644a(Context context) {
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/files/"));
    }

    public static Object m1645a(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return declaredMethod.invoke(obj, objArr);
    }

    private static ArrayList m1646a(File[] fileArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < fileArr.length) {
            if (fileArr[i].isFile() && fileArr[i].getName().length() == 10 && TextUtils.isDigitsOnly(fileArr[i].getName())) {
                arrayList.add(fileArr[i]);
            }
            i++;
        }
        return arrayList;
    }

    protected static byte[] m1647a(BitSet bitSet) {
        byte[] bArr = new byte[(bitSet.size() / 8)];
        for (int i = 0; i < bitSet.size(); i++) {
            int i2 = i / 8;
            bArr[i2] = (byte) (((bitSet.get(i) ? 1 : 0) << (7 - (i % 8))) | bArr[i2]);
        }
        return bArr;
    }

    protected static byte[] m1648a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr2;
        } catch (Exception e) {
            return bArr2;
        }
    }

    protected static byte[] m1649a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int indexOf = new String(bArr).indexOf(0);
        if (indexOf <= 0) {
            i = 1;
        } else if (indexOf + 1 <= i) {
            i = indexOf + 1;
        }
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, i);
        obj[i - 1] = null;
        return obj;
    }

    public static int m1650b(Object obj, String str, Object... objArr) {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
            if (clsArr[i] == Integer.class) {
                clsArr[i] = Integer.TYPE;
            }
        }
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, objArr)).intValue();
    }

    protected static BitSet m1651b(byte[] bArr) {
        BitSet bitSet = new BitSet(bArr.length << 3);
        int i = 0;
        for (byte b : bArr) {
            int i2 = 7;
            while (i2 >= 0) {
                int i3 = i + 1;
                bitSet.set(i, ((b & (1 << i2)) >> i2) == 1);
                i2--;
                i = i3;
            }
        }
        return bitSet;
    }

    private File m1652c(long j) {
        boolean z = false;
        if (Process.myUid() == XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            return null;
        }
        File file;
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = z;
        }
        if (ed.m1653c() && !r0) {
            file = null;
        } else if (ed.m1654d() <= ((long) (this.f1058d / 2))) {
            return null;
        } else {
            File file2 = new File(ed.m1644a(this.f1055a).getPath() + File.separator + "carrierdata");
            if (!(file2.exists() && file2.isDirectory())) {
                file2.mkdirs();
            }
            file = new File(file2.getPath() + File.separator + j);
            try {
                z = file.createNewFile();
            } catch (IOException e2) {
            }
        }
        return !z ? null : file;
    }

    protected static boolean m1653c() {
        if (VERSION.SDK_INT >= 9) {
            try {
                return ((Boolean) Environment.class.getMethod("isExternalStorageRemovable", null).invoke(null, null)).booleanValue();
            } catch (Exception e) {
            }
        }
        return true;
    }

    protected static long m1654d() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    private File m1655e() {
        if (Process.myUid() == XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            return null;
        }
        File file;
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (!ed.m1653c() || r0) {
            File file2 = new File(ed.m1644a(this.f1055a).getPath() + File.separator + "carrierdata");
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    ArrayList a = ed.m1646a(listFiles);
                    if (a.size() == 1) {
                        if (((File) a.get(0)).length() < ((long) this.f1060f)) {
                            file = (File) a.get(0);
                            return file;
                        }
                    } else if (a.size() >= 2) {
                        file = (File) a.get(0);
                        File file3 = (File) a.get(1);
                        if (file.getName().compareTo(file3.getName()) <= 0) {
                            file = file3;
                        }
                        return file;
                    }
                }
            }
        }
        file = null;
        return file;
    }

    private int m1656f() {
        if (Process.myUid() == XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            return 0;
        }
        boolean equals;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (ed.m1653c() && !r0) {
            return 0;
        }
        File file = new File(ed.m1644a(this.f1055a).getPath() + File.separator + "carrierdata");
        if (!file.exists() || !file.isDirectory()) {
            return 0;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return 0;
        }
        ArrayList a = ed.m1646a(listFiles);
        return a.size() == 1 ? ((File) a.get(0)).length() <= 0 ? 10 : 1 : a.size() >= 2 ? 2 : 0;
    }

    private File m1657g() {
        boolean equals;
        if (Process.myUid() == XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            return null;
        }
        File file;
        try {
            equals = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
            equals = false;
        }
        if (!ed.m1653c() || r0) {
            File a = ed.m1644a(this.f1055a);
            if (a != null) {
                File file2 = new File(a.getPath() + File.separator + "carrierdata");
                if (file2.exists() && file2.isDirectory()) {
                    File[] listFiles = file2.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        ArrayList a2 = ed.m1646a(listFiles);
                        if (a2.size() >= 2) {
                            a = (File) a2.get(0);
                            file = (File) a2.get(1);
                            if (a.getName().compareTo(file.getName()) <= 0) {
                                file = a;
                            }
                            return file;
                        }
                    }
                }
            }
        }
        file = null;
        return file;
    }

    protected int m1658a() {
        return this.f1056b;
    }

    protected synchronized File m1659a(long j) {
        File e;
        e = m1655e();
        if (e == null) {
            e = m1652c(j);
        }
        return e;
    }

    protected void m1660a(int i) {
        this.f1056b = i;
        this.f1058d = (((this.f1056b << 3) * AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS) + this.f1056b) + 4;
        if (this.f1056b == Opcodes.ACC_NATIVE || this.f1056b == 768) {
            this.f1060f = this.f1058d / 100;
        } else if (this.f1056b == 8736) {
            this.f1060f = this.f1058d - 5000;
        }
    }

    protected File m1661b() {
        return m1657g();
    }

    protected void m1662b(int i) {
        this.f1057c = i;
        this.f1059e = (((this.f1057c << 3) * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) + this.f1057c) + 4;
        this.f1061g = this.f1059e;
    }

    protected synchronized boolean m1663b(long j) {
        boolean z = false;
        synchronized (this) {
            int f = m1656f();
            if (f != 0) {
                if (f == 1) {
                    if (m1652c(j) != null) {
                        z = true;
                    }
                } else if (f == 2) {
                    z = true;
                }
            }
        }
        return z;
    }
}
