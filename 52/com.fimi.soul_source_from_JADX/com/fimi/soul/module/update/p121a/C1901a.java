package com.fimi.soul.module.update.p121a;

import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.kernel.p084e.ac;
import com.fimi.soul.biz.update.ae;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.service.InitAppService;
import com.fimi.soul.utils.C1969i;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.update.a.a */
public class C1901a {
    private static C1901a f9785c;
    C1903c f9786a;
    private Map<Integer, C1905e> f9787b;

    static {
        f9785c = new C1901a();
    }

    public C1901a() {
        this.f9787b = new HashMap();
        this.f9786a = new C1906f();
    }

    public static synchronized C1901a m12002a() {
        C1901a c1901a;
        synchronized (C1901a.class) {
            if (f9785c == null) {
                f9785c = new C1901a();
            }
            c1901a = f9785c;
        }
        return c1901a;
    }

    public C1905e m12003a(int i) {
        return this.f9787b.containsKey(Integer.valueOf(i)) ? (C1905e) this.f9787b.get(Integer.valueOf(i)) : new C1905e();
    }

    public void m12004a(int i, int i2) {
        this.f9786a.m12032a(i, i2);
    }

    public void m12005a(C1905e c1905e) {
        if (c1905e != null) {
            this.f9787b.put(Integer.valueOf(c1905e.m12053d()), c1905e);
        }
    }

    public Map<Integer, C1905e> m12006b() {
        return this.f9787b;
    }

    public void m12007b(int i) {
        this.f9786a.m12033a((C1905e) this.f9787b.get(Integer.valueOf(i)));
        this.f9786a.m12037d(i);
    }

    public void m12008c() {
        C1189f.m8333c().m7930a(C1921n.f9846k, new C1902b());
        this.f9787b.clear();
    }

    public List<FirmwareInfo> m12009d() {
        List<FirmwareInfo> arrayList = new ArrayList();
        List<UpdateVersonBean> b = C1157c.m7938a().m7948b(InitAppService.f9939a, UpdateVersonBean.class);
        if (b != null && b.size() > 0) {
            for (UpdateVersonBean updateVersonBean : b) {
                int intValue = Integer.valueOf(updateVersonBean.getNewVersion()).intValue();
                if (updateVersonBean.getFileEncode().equals(ac.m8017a(updateVersonBean.getSysid() == 11 ? new File(C1969i.m12493p() + ak.m9427a(updateVersonBean.getFileEncode())) : new File(this.f9786a.m12036c(updateVersonBean.getSysid()))))) {
                    C1905e c1905e = (C1905e) f9785c.m12006b().get(Integer.valueOf(updateVersonBean.getSysid()));
                    if (c1905e != null) {
                        int b2 = c1905e.m12046b();
                        if ((intValue > b2 && b2 > 0) || (Constants.VIA_TO_TYPE_QQ_GROUP.equals(updateVersonBean.getForceSign()) && intValue != b2)) {
                            int b3 = ak.m9430b(updateVersonBean.getSysid());
                            int intValue2 = ((Integer) ak.m9428a().get(Integer.valueOf(updateVersonBean.getSysid()))).intValue();
                            FirmwareInfo firmwareInfo = new FirmwareInfo(updateVersonBean.getSysid(), updateVersonBean.getSysid(), updateVersonBean.getSysname(), C2915a.f14760f, C2915a.f14760f, updateVersonBean.getNewVersion(), 1, b3, updateVersonBean.getSysname());
                            firmwareInfo.setFileEncode(updateVersonBean.getFileEncode());
                            firmwareInfo.setUpdcontents(updateVersonBean.getUpdcontents());
                            firmwareInfo.setSource(1);
                            firmwareInfo.setUpdateTime(intValue2);
                            arrayList.add(firmwareInfo);
                        }
                    }
                }
            }
        }
        Collections.sort(arrayList, new ae());
        return arrayList;
    }

    public List<UpdateVersonBean> m12010e() {
        List<UpdateVersonBean> b = C1189f.m8333c().m7935b(InitAppService.f9939a, UpdateVersonBean.class);
        List<UpdateVersonBean> arrayList = new ArrayList();
        C1902b c1902b = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        if (c1902b == null || c1902b.m12028i() == null || c1902b.m12028i().size() == 0) {
            return arrayList;
        }
        if (b != null && b.size() > 0) {
            for (UpdateVersonBean updateVersonBean : b) {
                if (!updateVersonBean.getFileEncode().equals(ac.m8017a(updateVersonBean.getSysid() == 11 ? new File(C1969i.m12493p() + ak.m9427a(updateVersonBean.getFileEncode())) : new File(this.f9786a.m12036c(updateVersonBean.getSysid()))))) {
                    int intValue = Integer.valueOf(updateVersonBean.getNewVersion()).intValue();
                    int intValue2 = c1902b.m12028i().get(Integer.valueOf(updateVersonBean.getSysid())) == null ? 0 : ((Integer) c1902b.m12028i().get(Integer.valueOf(updateVersonBean.getSysid()))).intValue();
                    if ((intValue > intValue2 && intValue2 > 0) || (Constants.VIA_TO_TYPE_QQ_GROUP.equals(updateVersonBean.getForceSign()) && intValue != intValue2)) {
                        arrayList.add(updateVersonBean);
                    }
                }
            }
        }
        return arrayList;
    }

    public C1903c m12011f() {
        return this.f9786a;
    }
}
