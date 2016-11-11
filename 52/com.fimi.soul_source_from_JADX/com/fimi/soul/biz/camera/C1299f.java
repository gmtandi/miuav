package com.fimi.soul.biz.camera;

import android.os.Message;
import android.util.Log;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1152e;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.kernel.p076b.p079c.C1133a;
import com.fimi.kernel.p076b.p079c.C1136d;
import com.fimi.kernel.p076b.p081e.C1145b;
import com.fimi.kernel.p084e.C1174m;
import com.fimi.kernel.p084e.ac;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.biz.camera.entity.X11ApWifiConfig;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11FileSystem;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd.NotificationType;
import com.fimi.soul.biz.camera.entity.X11SystemConfig;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.camera.p088b.C1267c;
import com.fimi.soul.biz.camera.p088b.C1269f;
import com.fimi.soul.biz.camera.p088b.C1275d;
import com.fimi.soul.biz.camera.p093a.C1270b;
import com.fimi.soul.biz.camera.p094c.C1277a;
import com.fimi.soul.biz.p096d.C1316b;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.ah;
import com.fimi.soul.utils.be;
import com.tencent.mm.sdk.openapi.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.f */
public abstract class C1299f extends C1273a implements C1269f {
    private static final int f5792d = 1024;
    private static final int f5793e = 1025;
    private static final int f5794f = 2048;
    private static final int f5795g = 2049;
    private static final int f5796h = 4096;
    private static int f5797i = 0;
    private static final long f5798x = 1500;
    private boolean f5799A;
    private boolean f5800B;
    private boolean f5801C;
    private C1136d f5802D;
    private long f5803E;
    private String f5804F;
    C1313t f5805a;
    C1325k f5806b;
    ExecutorService f5807c;
    private X11ApWifiConfig f5808j;
    private C1309p f5809k;
    private X11SystemConfig f5810l;
    private X11FileSystem f5811m;
    private List<C1213e<X11RespCmd>> f5812n;
    private C1213e<X11RespCmd> f5813o;
    private C1136d f5814p;
    private C1133a f5815q;
    private DataOutputStream f5816r;
    private C1275d f5817s;
    private StringBuilder f5818t;
    private C1311r f5819u;
    private C1312s f5820v;
    private C1122k f5821w;
    private long f5822y;
    private boolean f5823z;

    static {
        f5797i = -1;
    }

    public C1299f(C1267c c1267c) {
        this.f5809k = C1309p.Normal;
        this.f5818t = new StringBuilder();
        this.f5822y = System.currentTimeMillis();
        this.f5823z = false;
        this.f5799A = true;
        this.f5800B = false;
        this.f5801C = true;
        this.f5807c = Executors.newSingleThreadExecutor();
        this.f5803E = 0;
        this.f5804F = null;
        m8674a(c1267c);
        this.f5810l = new X11SystemConfig();
        this.f5808j = new X11ApWifiConfig();
        this.f5811m = new X11FileSystem();
        C1316b c1316b = new C1316b();
        this.f5806b = C1325k.m8930a();
        this.f5812n = new ArrayList();
        if (this.f5814p == null) {
            this.f5814p = new C1300g(this);
            m8843b(this.f5814p);
            m7687a(new C1301h(this));
        }
        if (this.f5815q == null) {
            this.f5815q = new C1302i(this);
            m8829a(this.f5815q);
        }
    }

    private List<X11FileInfo> m8806a(Map<String, String> map) {
        List<X11FileInfo> arrayList = new ArrayList();
        for (String str : map.keySet()) {
            X11FileInfo x11FileInfo = new X11FileInfo();
            x11FileInfo.setName(str);
            if (!str.toUpperCase().contains("THM")) {
                x11FileInfo.setLocalPath(C1969i.m12474a() + "X1/");
                String curPath = m8855k().getCurPath();
                x11FileInfo.setAbsolutePath(curPath + "/" + str);
                x11FileInfo.setRemotePath(curPath.substring(curPath.indexOf("DCIM"), curPath.length()) + "/" + str);
                String[] split = ((String) map.get(str)).split("\\|");
                x11FileInfo.setSize(Long.parseLong(split[0].replace(" bytes", C2915a.f14760f).trim()));
                x11FileInfo.setCreateDate(split[1]);
                arrayList.add(x11FileInfo);
            }
        }
        Collections.sort(arrayList, new C1304k(this));
        return arrayList;
    }

    private synchronized void m8807a(int i, byte[] bArr) {
        X11FileInfo curDownloadFile = m8855k().getCurDownloadFile();
        Log.d("Good", "len:" + i + "  path" + curDownloadFile.getLocalPath() + " name:" + curDownloadFile.getName() + "downloading:" + i);
        if (curDownloadFile != null) {
            try {
                String str = curDownloadFile.getLocalPath() + curDownloadFile.getName();
                if (i != -1) {
                    if (this.f5816r == null) {
                        this.f5803E = 0;
                        File file = new File(curDownloadFile.getLocalPath());
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        file = new File(str);
                        if (file.exists()) {
                            be.m12362a(file);
                        }
                        file.createNewFile();
                        this.f5816r = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
                    }
                    this.f5803E += (long) i;
                    this.f5816r.write(bArr, 0, i);
                    if (this.f5819u != null) {
                        this.f5819u.m8870a(curDownloadFile.getName(), this.f5803E, curDownloadFile.getSize());
                    }
                    m8835a(curDownloadFile);
                    this.f5804F = str;
                }
            } catch (Exception e) {
                Log.d("Good", "ERROR" + e);
            }
        }
    }

    private void m8808a(X11RespCmd x11RespCmd) {
        if (x11RespCmd != null) {
            boolean z;
            this.f5818t.delete(0, this.f5818t.length());
            if (x11RespCmd.getRval() >= 0) {
                z = true;
            } else {
                m8821c(x11RespCmd);
                z = false;
            }
            if (x11RespCmd.isNotification()) {
                m8818b(x11RespCmd);
            } else {
                for (C1213e a : this.f5812n) {
                    a.m8440a(z, x11RespCmd);
                }
            }
            if (x11RespCmd.getNotificationType() == NotificationType.PhotoTaken) {
                m8839a(true);
                if (C1189f.m8335e().m8020a().booleanValue()) {
                    String obj = x11RespCmd.getParam().toString();
                    if (!C2915a.f14760f.equalsIgnoreCase(obj)) {
                        this.f5821w = C1122k.m7798a(C1189f.m8327a());
                        String b = C1969i.m12478b(obj.substring(obj.lastIndexOf("/") + 1, obj.length()));
                        this.f5821w.m7808a(String.format("http://%s//%s", new Object[]{C1314u.f5874a, obj.substring(obj.indexOf("DCIM"), obj.length())}), C2915a.f14760f, 0, Boolean.valueOf(true), b);
                    }
                }
            } else if (x11RespCmd.getNotificationType() == NotificationType.CardInsert) {
                C1174m.m8185b(new File(C1969i.m12491n()));
                m8844b(true);
            } else if (x11RespCmd.getNotificationType() == NotificationType.CardRemoved) {
                C1174m.m8185b(new File(C1969i.m12491n()));
                m8844b(false);
            } else if (x11RespCmd.getNotificationType() == NotificationType.FmLsEnd) {
                x11RespCmd.setNotificationType(NotificationType.FmLsEnd);
                m8812a(Boolean.valueOf(z), x11RespCmd);
                m8839a(false);
            } else if (x11RespCmd.getMsg_id() == 4 && z) {
                if (m8855k().getInfos() != null) {
                    m8855k().getInfos().clear();
                }
            } else if (x11RespCmd.getNotificationType() == NotificationType.StartingVideoRecord) {
                if (this.f5821w == null) {
                    this.f5821w = C1122k.m7798a(C1189f.m8327a());
                }
                this.f5821w.m7812d();
            } else if (x11RespCmd.getNotificationType() == NotificationType.VideoRecordComplete) {
                if (this.f5821w == null) {
                    this.f5821w = C1122k.m7798a(C1189f.m8327a());
                }
                this.f5821w.m7813e();
            }
            if (this.f5813o != null && x11RespCmd.getNotificationType() != NotificationType.FmLsEnd) {
                this.f5813o.m8440a(z, x11RespCmd);
                return;
            }
            return;
        }
        X11RespCmd x11RespCmd2 = new X11RespCmd();
        x11RespCmd2.setMsg_id(C1314u.ah);
        Log.d("Good", "UNkown");
        if (this.f5813o != null) {
            this.f5813o.m8440a(false, x11RespCmd2);
        }
    }

    private void m8812a(Boolean bool, X11RespCmd x11RespCmd) {
        ((C1145b) C1189f.m8328a(C1152e.Volley)).m7910b(C1314u.f5880g, new C1305l(this, bool, x11RespCmd));
    }

    private void m8813a(String str) {
        if (str.contains("\"msg_id\":1282")) {
            new C1310q(this).executeOnExecutor(this.f5807c, new String[]{str});
            return;
        }
        X11RespCmd b = m8817b(str);
        if (b == null) {
            this.f5818t.append(str);
            b = m8817b(this.f5818t.toString());
        }
        m8808a(b);
    }

    private X11RespCmd m8817b(String str) {
        try {
            return (X11RespCmd) ah.m12243a(str, new C1306m(this).getType());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return (X11RespCmd) ah.m12243a(str, new C1307n(this).getType());
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    return (X11RespCmd) ah.m12243a(str, new C1308o(this).getType());
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return null;
                }
            }
        }
    }

    private void m8818b(X11RespCmd x11RespCmd) {
        Log.d("Good", "Notification:" + x11RespCmd.getType());
        if (x11RespCmd.getMsg_id() == 7) {
            if (C1314u.aj.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.DisconnectHDMI);
            }
            if (C1314u.ak.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.DisconnectShutDown);
            }
            if (C1314u.al.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.StartingVideoRecord);
            }
            if (C1314u.am.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.VideoRecordComplete);
            }
            if (C1314u.an.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.PhotoTaken);
                m8826q();
            }
            if (C1314u.ao.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.ContinueCaputureStarted);
                this.f5809k = C1309p.ContinueCapturing;
            }
            if (C1314u.ap.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.ContinueCaptureStoped);
                m8826q();
            }
            if (C1314u.aq.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.ContinueCaputureStarted);
                m8826q();
            }
            if (C1314u.ar.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.ContiuneBurstCompleted);
                m8826q();
            }
            if (C1314u.at.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.LowBatteryWarning);
            }
            if (C1314u.as.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.LowStorageWarning);
            }
            if (C1314u.au.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.TimeLapseVideoStatus);
            }
            if (C1314u.av.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.TimeLapsePhotoStatus);
            }
            if (C1314u.aw.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CameraConnectToPc);
            }
            if (C1314u.ax.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.LogUpdated);
            }
            if (C1314u.ay.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.PowerModeChange);
            }
            if (C1314u.az.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.VFStarted);
            }
            if (C1314u.aA.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.VFStoped);
            }
            if (C1314u.aB.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.AutoFileDelete);
            }
            if (C1314u.aC.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.StorageRunOut);
            }
            if (C1314u.aD.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.StorageIOError);
            }
            if (C1314u.aE.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.LowSpeedCard);
            }
            if (C1314u.aF.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.MuxerIndexLimit);
            }
            if (C1314u.aG.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.MuxerFileSizeLimit);
            }
            if (C1314u.aH.equals(x11RespCmd.getType())) {
                C1189f.m8335e().m8030c(true);
                C1189f.m8335e().m8033d(true);
                x11RespCmd.setNotificationType(NotificationType.CardRemoved);
            }
            if (C1314u.aI.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CardInsert);
            }
            if (C1314u.aK.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.PhotoFinish);
            }
            if (C1314u.aJ.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CannotIssuePIV);
            }
            if (C1314u.aV.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.PutFileComplete);
            }
            if (C1314u.aZ.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.FmLsEnd);
            }
            if (C1314u.aL.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_FILE_SYS_ERROR);
            }
            if (C1314u.aM.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_PARAM_ERR);
            }
            if (C1314u.aN.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_NO_PROPOSE);
            }
            if (C1314u.aO.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_NO_PROPOSE_AND_PARAM_ERR);
            }
            if (C1314u.aP.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_WRITE_LOW);
            }
            if (C1314u.aQ.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_WRITE_LOW_AND_PARAM_ERR);
            }
            if (C1314u.aR.equals(x11RespCmd.getType())) {
                x11RespCmd.setNotificationType(NotificationType.CARD_SETROOT_ERROR);
            }
        }
    }

    private void m8821c(X11RespCmd x11RespCmd) {
        switch (x11RespCmd.getRval()) {
            case C1314u.bl /*-17*/:
                m8853i().setFreeKBSpace(0);
            case ErrCode.ERR_AUTH_DENIED /*-4*/:
            case AppbarJsBridge.Code_Java_Exception /*-3*/:
                f5797i = -1;
            case Opcodes.F_NEW /*-1*/:
                if (x11RespCmd.getMsg_id() == 5) {
                    m8853i().setFreeKBSpace(0);
                }
            default:
        }
    }

    private void m8826q() {
        this.f5809k = C1309p.Normal;
    }

    public void m8827a(int i) {
        f5797i = i;
    }

    protected void m8828a(Message message) {
        if (message.what == f5792d) {
            X11FileInfo x11FileInfo = (X11FileInfo) message.obj;
            if (this.f5817s != null) {
                this.f5817s.m8679a(x11FileInfo.getLocalPath(), x11FileInfo.getName());
            }
        } else if (message.what == f5793e) {
            r0 = new X11RespCmd();
            r0.setMsg_id(C1314u.ai);
            Log.d("Good", "\u4e0b\u8f7d\u5931\u8d25");
            if (this.f5813o != null) {
                this.f5813o.m8440a(false, r0);
            }
        } else if (message.what == f5794f) {
            r0 = new X11RespCmd();
            r0.setMsg_id(C1314u.af);
            Log.d("Good", "\u76f8\u673a\u8fde\u63a5\u8d85\u65f6");
            if (this.f5801C && this.f5811m.getCurDirFileList() != null) {
                this.f5811m.getCurDirFileList().clear();
            }
            if (this.f5813o != null) {
                this.f5813o.m8440a(false, r0);
            }
        } else if (message.what == f5796h) {
            ak.m8085a(C1189f.m8334d(), "session=" + f5797i + " isConCamera=" + this.f5823z, 3000);
        } else if (message.what == f5795g) {
            r0 = new X11RespCmd();
            r0.setMsg_id(C1314u.ag);
            if (this.f5813o != null) {
                this.f5813o.m8440a(false, r0);
            }
        }
    }

    public void m8829a(C1133a c1133a) {
        ((C1269f) m8675b()).m8657a(c1133a);
    }

    @Deprecated
    public void m8830a(C1136d c1136d) {
        ((C1269f) m8675b()).m8658a(c1136d);
    }

    public void m8831a(C1275d c1275d) {
        this.f5817s = c1275d;
    }

    public void m8832a(C1213e<X11RespCmd> c1213e) {
        this.f5813o = c1213e;
    }

    protected void m8833a(C1277a c1277a) {
        if (c1277a != null) {
            this.f5812n.add(c1277a);
        }
    }

    public void m8834a(X11ApWifiConfig x11ApWifiConfig) {
        this.f5808j = x11ApWifiConfig;
    }

    public void m8835a(X11FileInfo x11FileInfo) {
        if (x11FileInfo.getSize() != 0 && this.f5803E >= x11FileInfo.getSize() && this.f5816r != null) {
            Log.d("Good", "\u4e0b\u8f7d\u5b8c\u6bd5:" + this.f5803E);
            try {
                this.f5816r.close();
                this.f5816r = null;
                String md5 = x11FileInfo.getMd5();
                File file = new File(x11FileInfo.getLocalPath() + x11FileInfo.getName());
                String a = (file != null && file.isFile() && file.exists()) ? ac.m8017a(file) : C2915a.f14760f;
                Message message = new Message();
                if (md5 == null || a.equals(md5)) {
                    message.what = f5792d;
                    message.obj = x11FileInfo;
                    m8855k().setCurDownloadFile(null);
                } else {
                    file.delete();
                    message.what = f5793e;
                }
                m7685a().sendMessageDelayed(message, 200);
                this.f5803E = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void m8836a(C1309p c1309p) {
        this.f5809k = c1309p;
    }

    public void m8837a(C1311r c1311r) {
        this.f5819u = c1311r;
    }

    public void m8838a(C1312s c1312s) {
        this.f5820v = c1312s;
    }

    public void m8839a(boolean z) {
        this.f5801C = z;
    }

    public void m8840a(byte[] bArr) {
        m8675b().m8655a(bArr);
    }

    public void m8841a(byte[] bArr, int i, int i2) {
        ((C1269f) m8675b()).m8659a(bArr, i, i2);
    }

    public void m8842b(C1133a c1133a) {
        ((C1269f) m8675b()).m8660b(c1133a);
    }

    @Deprecated
    public void m8843b(C1136d c1136d) {
        ((C1269f) m8675b()).m8661b(c1136d);
    }

    public void m8844b(boolean z) {
        this.f5799A = z;
    }

    public void m8845c(C1136d c1136d) {
        ((C1269f) m8675b()).m8658a(this.f5802D);
        this.f5802D = c1136d;
        ((C1269f) m8675b()).m8661b(this.f5802D);
    }

    public void m8846c(boolean z) {
        this.f5800B = z;
    }

    public boolean m8847c() {
        return this.f5801C;
    }

    public boolean m8848d() {
        return f5797i > 0;
    }

    public int m8849e() {
        return f5797i;
    }

    public C1309p m8850f() {
        return this.f5809k;
    }

    public boolean m8851g() {
        return m8850f() != C1309p.Normal;
    }

    public boolean m8852h() {
        return this.f5799A;
    }

    public X11SystemConfig m8853i() {
        return this.f5810l;
    }

    public X11ApWifiConfig m8854j() {
        return this.f5808j;
    }

    public X11FileSystem m8855k() {
        return this.f5811m;
    }

    public C1312s m8856l() {
        return this.f5820v;
    }

    public void m8857m() {
        if (m8675b() != null && (m8675b() instanceof C1270b)) {
            ((C1270b) m8675b()).m8668b();
            Log.v("Good", "disConnect");
        }
    }

    public boolean m8858n() {
        return (m8675b() != null && (m8675b() instanceof C1270b)) ? ((C1270b) m8675b()).m8671c() : false;
    }

    public void m8859o() {
        com.fimi.kernel.p084e.ah.m8075a(new C1303j(this));
    }

    public void m8860p() {
        if (m8675b() != null && (m8675b() instanceof C1270b)) {
            ((C1270b) m8675b()).m8672d();
            Log.v("Good", "disConnect");
        }
    }
}
