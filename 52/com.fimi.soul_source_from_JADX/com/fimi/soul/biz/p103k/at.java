package com.fimi.soul.biz.p103k;

import android.content.Context;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.entity.FdsMsg;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.k.at */
public class at {
    private static at f6088c;
    C1390n f6089a;
    private Context f6090b;
    private LinkedList<File> f6091d;
    private C1532a f6092e;

    public at(Context context) {
        this.f6091d = new LinkedList();
        this.f6090b = context;
    }

    public static at m9227a(Context context) {
        if (f6088c == null) {
            f6088c = new at(context);
        }
        return f6088c;
    }

    private void m9231a(FdsMsg fdsMsg, File file, C1532a c1532a) {
        List arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("commandCode", "uploadFlyLog"));
        arrayList.add(new BasicNameValuePair("userID", C1236a.m8533b(this.f6090b).getUserID() != null ? C1236a.m8533b(this.f6090b).getUserID() : "000"));
        arrayList.add(new BasicNameValuePair("planeSsid", file.getName()));
        arrayList.add(new BasicNameValuePair("binVersion", c1532a != null ? c1532a.m10057c() + C2915a.f14760f : "0000"));
        arrayList.add(new BasicNameValuePair("fileFdsUrl", fdsMsg.getUrl()));
        arrayList.add(new BasicNameValuePair("currentTime", be.m12366b(System.currentTimeMillis())));
        String name = file.getParentFile().getName();
        if (name.equals("NoFly")) {
            arrayList.add(new BasicNameValuePair("isInSky", Constants.VIA_TO_TYPE_QQ_GROUP));
        } else if (name.equals("AirFly")) {
            arrayList.add(new BasicNameValuePair("isInSky", Constants.VIA_RESULT_SUCCESS));
        }
        ah.m8077b(new av(this, arrayList));
    }

    public void m9233a() {
        int i = 0;
        try {
            File file;
            File file2 = new File(C1969i.m12474a() + "/fw_upgrade/FMLink_Up_Log/");
            file2.mkdirs();
            if (file2.exists()) {
                for (File file3 : file2.listFiles()) {
                    if (file3 != null) {
                        file = new File(file2, file3.getName());
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                    }
                }
                file2.delete();
            }
            file2 = new File(C1969i.m12474a() + "/fw_upgrade/FMLink_Down_Log/");
            file2.mkdirs();
            if (file2.exists()) {
                for (File file32 : file2.listFiles()) {
                    if (file32 != null) {
                        file = new File(file2, file32.getName());
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                    }
                }
                file2.delete();
            }
            file2 = new File(C1969i.m12474a() + "/updateLog/");
            file2.mkdirs();
            if (file2.exists()) {
                for (File file322 : file2.listFiles()) {
                    if (file322 != null) {
                        file = new File(file2, file322.getName());
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                    }
                }
                file2.delete();
            }
            File file4 = new File(be.m12397o(this.f6090b));
            if (file4.exists()) {
                file4.delete();
            }
            file4 = new File(C1969i.m12474a() + "/" + "SAVEDRONEINFO/");
            file4.mkdirs();
            if (file4.exists()) {
                File[] listFiles = file4.listFiles();
                int length = listFiles.length;
                while (i < length) {
                    File file5 = listFiles[i];
                    if (file5 != null && file5.exists()) {
                        file5.delete();
                    }
                    i++;
                }
                file4.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m9234a(C1390n c1390n) {
        this.f6089a = c1390n;
    }

    public void m9235a(File file) {
        C1388l.m9322a(this.f6090b).m9327a(file, new au(this, file));
    }

    public void m9236b() {
        this.f6091d.clear();
        this.f6092e = (C1532a) C1189f.m8333c().m7926a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
        if (this.f6092e != null) {
            this.f6092e.m10059d() + C2915a.f14760f + this.f6092e.m10061e() + C2915a.f14760f + this.f6092e.m10063g();
        }
        File file = new File(C1969i.m12474a() + "/" + "SAVEDRONEINFO/");
        file.mkdirs();
        if (file.exists() && be.m12375c(this.f6090b)) {
            for (File file2 : file.listFiles()) {
                if (file2 != null) {
                    File file3 = new File(file, file2.getName());
                    if (file3 != null && file3.exists()) {
                        file3.delete();
                    }
                }
            }
            file.delete();
        }
    }

    public void m9237c() {
        if (this.f6092e != null && this.f6091d.size() > 0) {
            m9235a((File) this.f6091d.poll());
        }
    }
}
