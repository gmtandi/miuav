package com.fimi.soul.biz.camera;

import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.l */
class C1305l implements C1153f<String> {
    final /* synthetic */ Boolean f5829a;
    final /* synthetic */ X11RespCmd f5830b;
    final /* synthetic */ C1299f f5831c;

    C1305l(C1299f c1299f, Boolean bool, X11RespCmd x11RespCmd) {
        this.f5831c = c1299f;
        this.f5829a = bool;
        this.f5830b = x11RespCmd;
    }

    public void m8865a(String str) {
        if (str != null && !C2915a.f14760f.equals(str)) {
            String[] split = str.split("\n");
            List arrayList = new ArrayList();
            for (String split2 : split) {
                String split22;
                String[] split3 = split22.split("\\|");
                if (split3.length > 3) {
                    X11FileInfo x11FileInfo = new X11FileInfo();
                    String trim = split3[0].trim();
                    String trim2 = split3[1].trim();
                    String trim3 = split3[2].trim();
                    split22 = split3[3].trim();
                    x11FileInfo.setSize(Long.parseLong(trim));
                    x11FileInfo.setName(trim3);
                    trim = be.m12386f(trim2);
                    if (trim != null) {
                        x11FileInfo.setCreateDate(trim);
                    }
                    x11FileInfo.setLocalPath(C1969i.m12474a() + "X1/");
                    x11FileInfo.setAbsolutePath(C1314u.co + split22 + trim3);
                    x11FileInfo.setRemotePath(split22 + trim3);
                    arrayList.add(x11FileInfo);
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.f5831c.m8855k().setInfos(arrayList);
            }
            if (this.f5831c.f5813o != null) {
                this.f5831c.f5813o.m8440a(this.f5829a.booleanValue(), this.f5830b);
            }
        } else if (this.f5831c.f5813o != null) {
            this.f5831c.f5813o.m8440a(false, this.f5830b);
        }
    }

    public void m8867b(String str) {
        if (this.f5831c.f5813o != null) {
            this.f5831c.f5813o.m8440a(false, this.f5830b);
        }
    }
}
