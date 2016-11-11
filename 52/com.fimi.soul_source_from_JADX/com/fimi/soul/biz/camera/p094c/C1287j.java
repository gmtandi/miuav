package com.fimi.soul.biz.camera.p094c;

import android.util.Log;
import com.fimi.kernel.p084e.C1173l;
import com.fimi.kernel.p084e.ac;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.BaseX11Cmd;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11FileSystem;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.update.ah;
import com.fimi.soul.biz.update.ak;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.utils.C1969i;
import com.hoho.android.usbserial.driver.UsbId;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.c.j */
public class C1287j extends C1277a {
    private static final String f5768c;
    private ah f5769a;
    private boolean f5770b;

    static {
        f5768c = C1969i.m12491n();
    }

    public C1287j(C1299f c1299f) {
        super(c1299f);
    }

    private void m8762a(X11FileSystem x11FileSystem, X11RespCmd x11RespCmd) {
        X11FileInfo curDownloadFile = x11FileSystem.getCurDownloadFile();
        if (curDownloadFile != null) {
            curDownloadFile.setMd5(x11RespCmd.getMd5sum());
            curDownloadFile.setSize(x11RespCmd.getSize());
            m8702a().m8835a(curDownloadFile);
        }
    }

    private void m8763h() {
        X11FileInfo curUploadFile = m8702a().m8855k().getCurUploadFile();
        Log.d("Good", "\u53d1\u9001\u6587\u4ef6" + curUploadFile);
        if (curUploadFile != null) {
            File file = new File(curUploadFile.getLocalPath());
            long length = file.length();
            this.f5770b = false;
            Log.d("Good", "\u6587\u4ef6\u5927\u5c0f" + length);
            if (file.exists()) {
                long j = 0;
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[2560];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            Log.d("Good", "\u53d1\u9001\u5b8c\u6bd5,\u603b\u957f\u5ea6\u4e3a:" + j);
                            m8702a().m8855k().setCurDownloadFile(null);
                            fileInputStream.close();
                            return;
                        } else if (!this.f5770b) {
                            m8709a(bArr, 0, read);
                            j += (long) read;
                            Log.d("Good", "\u5df2\u53d1\u9001" + j);
                            if (this.f5769a != null) {
                                this.f5769a.m9419a(j >= length, j, length, 0);
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.d("Good", e.toString());
                    e.printStackTrace();
                }
            }
        }
    }

    public void m8764a(X11FileInfo x11FileInfo) {
        m8702a().m8855k().setCurDownloadFile(x11FileInfo);
        m8704a((int) C1314u.f5867T, x11FileInfo.getName());
    }

    public void m8765a(ah ahVar) {
        this.f5769a = ahVar;
    }

    public void m8766a(String str) {
        m8704a((int) C1314u.f5865R, str);
    }

    public void m8767a(String str, int i) {
        BaseX11Cmd b = m8710b(C1314u.f5869V, str, null);
        b.setSent_size((long) i);
        m8706a(b);
    }

    public void m8768a(String str, C1290m c1290m) {
        X11FileInfo x11FileInfo = new X11FileInfo();
        m8769a(str, null, C1173l.m8145a(new Date(), C1173l.f5333e), c1290m);
    }

    public void m8769a(String str, String str2, String str3, C1290m c1290m) {
        String str4 = null;
        switch (C1289l.f5772a[c1290m.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str4 = "thumb";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str4 = "fullview";
                break;
            case Type.BYTE /*3*/:
                str4 = "idr";
                break;
        }
        X11FileInfo x11FileInfo = new X11FileInfo();
        x11FileInfo.setName(str);
        x11FileInfo.setCreateDate(str3);
        x11FileInfo.setAbsolutePath(str2);
        m8705a((int) C1314u.f5860M, str2, str4);
        x11FileInfo.setName(C1276b.m8681a(x11FileInfo));
        x11FileInfo.setLocalPath(f5768c);
        x11FileInfo.setRemotePath(m8702a().m8855k().getCurPath());
        m8702a().m8855k().setCurDownloadFile(x11FileInfo);
    }

    public void m8770a(boolean z) {
        this.f5770b = z;
    }

    public void m8771a(boolean z, X11RespCmd x11RespCmd) {
        super.m8707a(z, x11RespCmd);
        if (z) {
            X11FileSystem k = m8702a().m8855k();
            switch (x11RespCmd.getMsg_id()) {
                case C1314u.f5860M /*1025*/:
                    m8762a(k, x11RespCmd);
                case C1314u.f5865R /*1283*/:
                case C1314u.f5866S /*1284*/:
                    k.setCurPath(x11RespCmd.getPwd());
                case C1314u.f5867T /*1285*/:
                    m8762a(k, x11RespCmd);
                case C1314u.f5868U /*1286*/:
                    ak.m9434c("CMD_PUT_FILE:1286");
                    Log.i("msg=", "\u8fdb\u5165");
                    com.fimi.kernel.p084e.ah.m8075a(new C1288k(this));
                default:
            }
        }
    }

    public void m8773b() {
        m8703a((int) C1314u.f5866S);
    }

    public void m8774b(String str) {
        m8704a((int) C1314u.f5864Q, String.format("%s -D -S", new Object[]{str}));
    }

    public void m8775b(String str, int i) {
        m8705a((int) UsbId.VENDOR_FTDI, str, i + C2915a.f14760f);
    }

    public void m8776c() {
        m8766a("..");
    }

    public void m8777c(String str) {
        m8704a((int) C1314u.f5863P, str);
    }

    public void m8778d() {
        m8766a(C1314u.cn);
    }

    public void m8779d(String str) {
        m8704a(8, str);
    }

    public void m8780e() {
        m8703a((int) C1465c.f6998a);
    }

    public void m8781e(String str) {
        X11FileInfo x11FileInfo = new X11FileInfo();
        x11FileInfo.setRemotePath(m8702a().m8855k().getCurPath());
        if (x11FileInfo.getLocalPath() == null) {
            x11FileInfo.setLocalPath(f5768c);
        }
        x11FileInfo.setName(str);
        m8764a(x11FileInfo);
    }

    public void m8782f() {
        String curPath = m8702a().m8855k().getCurPath();
        if (curPath == null) {
            curPath = C2915a.f14760f;
        }
        m8774b(curPath);
    }

    public void m8783f(String str) {
        m8704a((int) C1314u.f5869V, str);
    }

    public void m8784g(String str) {
        m8704a((int) C1314u.f5861N, str);
    }

    public boolean m8785g() {
        return this.f5770b;
    }

    public void m8786h(String str) {
        BaseX11Cmd b = m8710b(C1314u.f5868U, C1314u.cp + C2915a.f14760f + str.substring(str.lastIndexOf("/") + 1), null);
        File file = new File(str);
        String a = ac.m8017a(file);
        long length = file.length();
        b.setMd5sum(a);
        b.setSize(length);
        X11FileInfo x11FileInfo = new X11FileInfo();
        x11FileInfo.setLocalPath(str);
        x11FileInfo.setName(file.getName());
        m8702a().m8855k().setCurUploadFile(x11FileInfo);
        m8706a(b);
    }
}
