package com.fimi.soul.biz.camera.p094c;

import com.fimi.kernel.p084e.C1184w;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.C1299f;
import com.fimi.soul.biz.camera.C1309p;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11DeviceInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.entity.X11SystemConfig;
import com.fimi.soul.biz.camera.entity.X11SystemConfigOption;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.module.setting.am;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.module.update.p121a.C1905e;
import com.google.gson.internal.LinkedTreeMap;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.camera.c.d */
public class C1281d extends C1277a {
    private String f5743a;
    private C1285h f5744b;
    private C1283f f5745c;
    private String f5746d;
    private String f5747e;

    public C1281d(C1299f c1299f) {
        super(c1299f);
        this.f5744b = C1285h.Max;
        this.f5745c = C1283f.Total;
    }

    private void m8735a(Map<String, String> map, X11SystemConfig x11SystemConfig) {
        x11SystemConfig.setCameraTimeLock((String) map.get(C1314u.bv));
        x11SystemConfig.setVideoStandard((String) map.get(C1314u.bw));
        x11SystemConfig.setAppStatus((String) map.get(C1314u.bx));
        x11SystemConfig.setStreamOutType((String) map.get(C1314u.by));
        x11SystemConfig.setSaveLowResolution(m8736e((String) map.get(C1314u.bz)));
        x11SystemConfig.setVideoResolution((String) map.get(C1314u.bA));
        x11SystemConfig.setVideoQuality((String) map.get(C1314u.bC));
        x11SystemConfig.setTimelapseVideo(m8736e((String) map.get(C1314u.bD)));
        x11SystemConfig.setCaptureMode((String) map.get(C1314u.bE));
        x11SystemConfig.setPhotoSize((String) map.get(C1314u.bF));
        x11SystemConfig.setPhotoStamp((String) map.get(C1314u.bG));
        x11SystemConfig.setPhotoQuality((String) map.get(C1314u.bH));
        x11SystemConfig.setTimelapsePhoto(m8736e((String) map.get(C1314u.bI)));
        x11SystemConfig.setVideoStamp((String) map.get(C1314u.bB));
        x11SystemConfig.setVideoSrt((String) map.get(C1314u.bJ));
        x11SystemConfig.setVideoLoopBack((String) map.get(C1314u.bK));
        x11SystemConfig.setVideoMode((String) map.get(C1314u.bL));
        x11SystemConfig.setSDState((String) map.get(C1314u.bM));
    }

    private boolean m8736e(String str) {
        return "on".equals(str);
    }

    public void m8737a(C1283f c1283f) {
        String str;
        this.f5745c = c1283f;
        switch (C1282e.f5751d[c1283f.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "total";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = C1314u.cd;
                break;
            case Type.BYTE /*3*/:
                str = FimiMediaMeta.IJKM_VAL_TYPE__VIDEO;
                break;
            default:
                str = null;
                break;
        }
        m8705a(6, null, str);
    }

    public void m8738a(C1284g c1284g) {
        String str = null;
        switch (C1282e.f5748a[c1284g.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "cam_stb";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = "cam_off";
                break;
        }
        m8704a(12, str);
    }

    public void m8739a(C1285h c1285h) {
        String str;
        this.f5744b = c1285h;
        switch (C1282e.f5750c[c1285h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "max";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = "current";
                break;
            case Type.BYTE /*3*/:
                str = RMsgInfo.COL_STATUS;
                break;
            default:
                str = null;
                break;
        }
        m8705a(15, null, str);
    }

    public void m8740a(C1286i c1286i, int i) {
        String str = null;
        switch (C1282e.f5749b[c1286i.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                str = "jump";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                str = "fast";
                break;
            case Type.BYTE /*3*/:
                str = "normal";
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                str = "slow";
                break;
        }
        m8705a(14, i + C2915a.f14760f, str);
    }

    public void m8741a(String str) {
        m8704a(9, str);
    }

    public void m8742a(String str, String str2) {
        this.f5746d = str;
        this.f5747e = str2;
        m8705a(2, str2, str);
    }

    public void m8743a(boolean z, X11RespCmd x11RespCmd) {
        super.m8707a(z, x11RespCmd);
        if (z) {
            X11SystemConfig i = m8702a().m8853i();
            int parseInt;
            switch (x11RespCmd.getMsg_id()) {
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (C1314u.bE.equals(this.f5746d)) {
                        i.setCaptureMode(this.f5747e);
                    }
                case Type.BYTE /*3*/:
                    if (x11RespCmd.getParam() != null) {
                        try {
                            List<LinkedTreeMap> list = (List) x11RespCmd.getParam();
                            Map hashMap = new HashMap();
                            for (LinkedTreeMap linkedTreeMap : list) {
                                for (String str : linkedTreeMap.keySet()) {
                                    hashMap.put(str, linkedTreeMap.get(str));
                                }
                            }
                            m8735a(hashMap, i);
                            if (C1314u.cc.contains(i.getAppStatus())) {
                                m8702a().m8836a(C1309p.Recoding);
                            }
                            if (C1314u.cd.contains(i.getAppStatus())) {
                                m8702a().m8836a(C1309p.ContinueCapturing);
                            }
                            if (C1314u.cb.contains(i.getAppStatus())) {
                                m8702a().m8836a(C1309p.IDLE);
                            }
                            if (C1314u.ce.contains(i.getAppStatus())) {
                                m8702a().m8836a(C1309p.Normal);
                            }
                        } catch (Exception e) {
                        }
                    }
                case Type.INT /*5*/:
                    try {
                        long parseLong = Long.parseLong(x11RespCmd.getParam().toString());
                        if (C1314u.cq.equals(this.f5743a)) {
                            i.setFreeKBSpace(parseLong);
                        }
                        if (C1314u.cr.equals(this.f5743a)) {
                            i.setTotalKBSpace(parseLong);
                            m8749c(C1314u.cq);
                        }
                    } catch (Exception e2) {
                    }
                case Type.FLOAT /*6*/:
                    parseInt = Integer.parseInt(x11RespCmd.getParam().toString());
                    switch (C1282e.f5751d[this.f5745c.ordinal()]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            i.setTotalFileNumbers(parseInt);
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            i.setPhotoFileNumbers(parseInt);
                        case Type.BYTE /*3*/:
                            i.setVideoFileNumbers(parseInt);
                        default:
                    }
                case Type.ARRAY /*9*/:
                    if (x11RespCmd.getParam() != null) {
                        String obj = x11RespCmd.getParam().toString();
                        X11SystemConfigOption x11SystemConfigOption = new X11SystemConfigOption();
                        x11SystemConfigOption.setType(obj);
                        x11SystemConfigOption.setPermission(x11RespCmd.getPermission());
                        x11SystemConfigOption.setOptions(x11RespCmd.getOptions());
                        i.getSystemConfigOptions().put(obj, x11SystemConfigOption);
                    }
                case Opcodes.T_LONG /*11*/:
                    X11DeviceInfo x11DeviceInfo = i.getX11DeviceInfo();
                    x11DeviceInfo.setBrand(x11RespCmd.getBrand());
                    x11DeviceInfo.setApiVersion(x11RespCmd.getApi_ver());
                    x11DeviceInfo.setModel(x11RespCmd.getModel());
                    x11DeviceInfo.setFirmwareVersion(x11RespCmd.getFw_ver());
                    x11DeviceInfo.setAppType(x11RespCmd.getApp_type());
                    x11DeviceInfo.setLogo(x11RespCmd.getLogo());
                    x11DeviceInfo.setChip(x11RespCmd.getChip());
                    x11DeviceInfo.setHttp(x11RespCmd.getHttp());
                    m8758k();
                case Opcodes.FCONST_2 /*13*/:
                    i.setBatteryLevel(Integer.parseInt(x11RespCmd.getParam().toString()));
                    i.setPowerSourceType(x11RespCmd.getType());
                case Opcodes.DCONST_1 /*15*/:
                    parseInt = Integer.parseInt(x11RespCmd.getParam().toString());
                    switch (C1282e.f5750c[this.f5744b.ordinal()]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            i.setMaxZoomInfo(parseInt);
                        case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                            i.setCurrentZoomInfo(parseInt);
                        default:
                    }
                case Opcodes.LDC /*18*/:
                    C1901a.m12002a().m12004a(4, C1184w.m8293n(x11RespCmd.getParam().toString()));
                    C1901a.m12002a().m12005a(new C1905e(C2915a.f14760f, 4, C1184w.m8293n(x11RespCmd.getParam().toString())));
                    i.setDvVersion(C1184w.m8293n(x11RespCmd.getParam().toString()) + C2915a.f14760f);
                    m8752e();
                case am.f9249v /*19*/:
                    String[] split = x11RespCmd.getParam().toString().split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    int parseInt2 = Integer.parseInt(split[0]);
                    parseInt = Integer.parseInt(split[1]);
                    i.setRemainVideoRecordMinutes(parseInt2);
                    i.setRemainPhotoTakeNumbers(parseInt);
                case C1314u.f5851D /*259*/:
                    m8760m();
                default:
            }
        }
    }

    public void m8745b() {
        m8703a(3);
    }

    public void m8746b(int i) {
        m8704a(16, i + C2915a.f14760f);
    }

    public void m8747b(String str) {
        m8704a(4, str);
    }

    public void m8748c() {
        m8704a(4, "D:");
    }

    public void m8749c(String str) {
        this.f5743a = str;
        m8705a(5, null, str);
    }

    public void m8750d() {
        m8703a(11);
    }

    public void m8751d(String str) {
        m8705a((int) C1314u.f5853F, str, "TCP");
    }

    public void m8752e() {
        m8704a((int) C1314u.f5851D, "none_force");
    }

    public void m8753f() {
        m8703a((int) C1314u.f5852E);
    }

    public void m8754g() {
        m8705a(2, "live", "dv_mode");
    }

    public void m8755h() {
        m8705a(2, C1314u.cc, "dv_mode");
    }

    public void m8756i() {
    }

    public void m8757j() {
        m8703a(17);
    }

    public void m8758k() {
        m8703a(18);
    }

    public void m8759l() {
        m8703a(19);
    }

    public void m8760m() {
        m8742a(C1314u.bv, new SimpleDateFormat(C1236a.f5614l).format(new Date()));
    }
}
