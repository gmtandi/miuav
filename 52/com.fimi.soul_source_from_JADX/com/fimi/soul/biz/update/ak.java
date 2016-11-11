package com.fimi.soul.biz.update;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p084e.C1173l;
import com.fimi.kernel.p084e.C1184w;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import com.fimi.soul.drone.p107c.p108a.p109a.bn;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.entity.UpgradeDetInfo;
import com.fimi.soul.entity.UpgradeResultInfo;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.module.update.p121a.C1905e;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.C1980t;
import com.google.gson.Gson;
import it.p074a.p075a.C2799f;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class ak {
    public static final String f6338a = "/fw_upgrade/Camera_Log/";
    public static final String f6339b = "/fw_upgrade/X6_Log/";
    public static final String f6340c = "10010";
    public static final long f6341d = 524288;
    public static final String f6342e = "sp_update_camara_version";
    public static final String f6343f = "sp_update_x6_version";
    private static HashMap<Integer, Integer> f6344g;

    static {
        f6344g = null;
    }

    public static long m9422a(List<UpdateVersonBean> list) {
        long j = 0;
        for (UpdateVersonBean size : list) {
            j = Long.valueOf(size.getSize()).longValue() + j;
        }
        return j;
    }

    public static UpgradeResultInfo m9423a(Context context) {
        UpgradeResultInfo upgradeResultInfo = new UpgradeResultInfo();
        User b = C1236a.m8533b(context);
        if (b != null) {
            upgradeResultInfo.setUserID(b.getUserID());
        }
        Gson gson = new Gson();
        m9434c(gson.toJson(m9433c()));
        upgradeResultInfo.setJsonStr(gson.toJson(m9433c()));
        return upgradeResultInfo;
    }

    public static File m9424a(al alVar) {
        File file = null;
        if (alVar == al.Camera) {
            file = new File(C1969i.m12474a() + f6338a);
        }
        if (alVar == al.Ap) {
            file = new File(C1969i.m12474a() + f6339b);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, C1173l.m8145a(new Date(), C1173l.f5330b) + ".txt");
        try {
            if (file2.exists() && file2.length() > f6341d) {
                file2.delete();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file2;
    }

    public static String m9425a(int i) {
        return String.valueOf(i <= 60 ? 1 : (int) Math.ceil(((double) i) / 60.0d));
    }

    private static String m9426a(C1532a c1532a) {
        return C2915a.f14760f + c1532a.m10059d() + c1532a.m10061e() + c1532a.m10063g() + c1532a.m10062f();
    }

    public static String m9427a(String str) {
        return C1921n.f9855t + str + ".bin";
    }

    public static HashMap<Integer, Integer> m9428a() {
        if (f6344g == null || f6344g.isEmpty()) {
            f6344g = new HashMap();
            f6344g.put(Integer.valueOf(0), Integer.valueOf(210));
            f6344g.put(Integer.valueOf(1), Integer.valueOf(20));
            f6344g.put(Integer.valueOf(9), Integer.valueOf(Opcodes.ISHL));
            f6344g.put(Integer.valueOf(10), Integer.valueOf(40));
            f6344g.put(Integer.valueOf(3), Integer.valueOf(Opcodes.GETFIELD));
            f6344g.put(Integer.valueOf(6), Integer.valueOf(50));
            f6344g.put(Integer.valueOf(4), Integer.valueOf(Opcodes.FCMPG));
            f6344g.put(Integer.valueOf(11), Integer.valueOf(60));
        }
        return f6344g;
    }

    public static void m9429a(File file, String str) {
        RandomAccessFile randomAccessFile;
        IOException e;
        Throwable th;
        try {
            randomAccessFile = new RandomAccessFile(file.getAbsoluteFile(), "rw");
            try {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeBytes("\n" + C1980t.m12511a() + "    " + new String(str.getBytes("ISO-8859-1"), C1142e.f5201a));
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            randomAccessFile = null;
            e22.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static int m9430b(int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return 7;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return 8;
            case Type.BYTE /*3*/:
                return 3;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return 1;
            case Type.FLOAT /*6*/:
                return 4;
            case Type.ARRAY /*9*/:
                return 5;
            case Type.OBJECT /*10*/:
                return 6;
            case Opcodes.T_LONG /*11*/:
                return 9;
            default:
                return -1;
        }
    }

    public static String m9431b() {
        List e = C1901a.m12002a().m12010e();
        return (e == null || e.size() <= 0) ? null : String.valueOf(ab.m8015a((((double) m9422a(e)) / 1024.0d) / 1024.0d, 2));
    }

    public static void m9432b(String str) {
        m9429a(m9424a(al.Ap), str);
    }

    public static UpgradeDetInfo m9433c() {
        UpgradeDetInfo upgradeDetInfo = new UpgradeDetInfo();
        C1901a a = C1901a.m12002a();
        if (a != null) {
            C1905e a2 = a.m12003a(0);
            upgradeDetInfo.setFcHwVersion(a2.m12050c());
            upgradeDetInfo.setFcID(a2.m12059i());
            upgradeDetInfo.setFcSwVersion(a2.m12046b() + C2915a.f14760f);
            a2 = a.m12003a(1);
            upgradeDetInfo.setRcHwVersion(a2.m12050c());
            upgradeDetInfo.setRcSwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setRcID(a2.m12059i());
            a2 = a.m12003a(10);
            upgradeDetInfo.setNoFlyHwVersion(a2.m12050c());
            upgradeDetInfo.setNoFlySwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setNoFlyID(a2.m12059i());
            a2 = a.m12003a(3);
            upgradeDetInfo.setGcHwVersion(a2.m12050c());
            upgradeDetInfo.setGcSwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setGcID(a2.m12059i());
            a2 = a.m12003a(9);
            upgradeDetInfo.setX2HwVersion(a2.m12050c());
            upgradeDetInfo.setX2SwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setX2ID(a2.m12059i());
            a2 = a.m12003a(6);
            upgradeDetInfo.setServoHwVersion(a2.m12050c());
            upgradeDetInfo.setServoSwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setServoID(a2.m12059i());
            a2 = a.m12003a(4);
            upgradeDetInfo.setX1HwVersion(a2.m12050c());
            upgradeDetInfo.setX1SwVersion(a2.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setX1ID(a2.m12059i());
            C1905e a3 = a.m12003a(4);
            upgradeDetInfo.setX6HwVersion(a3.m12050c());
            upgradeDetInfo.setX6SwVersion(a3.m12046b() + C2915a.f14760f);
            upgradeDetInfo.setX6ID(a3.m12059i());
        }
        return upgradeDetInfo;
    }

    public static void m9434c(String str) {
        m9429a(m9424a(al.Camera), str);
    }

    public static boolean m9435c(int i) {
        return i == Opcodes.IFNONNULL || i == bj.f6779b || i == C2799f.f14283u || i == bn.f6797b || i == C1458u.f6934b || i == Opcodes.FMUL || i == Opcodes.INSTANCEOF;
    }

    public static void m9436d() {
        m9437d(null);
        m9439e(null);
    }

    public static void m9437d(String str) {
        C1189f.m8333c().m7931a(f6343f, str);
    }

    public static String m9438e() {
        String a = C1189f.m8333c().m7927a(UpdateVersonBean.SP_CAMERA_VERSION);
        return a != null ? String.valueOf(C1184w.m8293n(a)) : C2915a.f14760f;
    }

    public static void m9439e(String str) {
        C1189f.m8333c().m7931a(f6342e, str);
    }

    public static String m9440f() {
        String d = C1325k.m8930a().m8939d();
        int i = -1;
        if (d != null) {
            i = Integer.valueOf(C1184w.m8293n(d)).intValue();
        }
        return String.valueOf(i);
    }
}
