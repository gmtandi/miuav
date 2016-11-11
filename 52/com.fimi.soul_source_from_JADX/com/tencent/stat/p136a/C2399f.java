package com.tencent.stat.p136a;

import com.autonavi.amap.mapcore.ERROR_CODE;
import com.fimi.soul.service.CameraSocketService;
import com.hoho.android.usbserial.driver.UsbId;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.tencent.stat.a.f */
public enum C2399f {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER),
    ADDITION(CameraSocketService.f9884d),
    MONITOR_STAT(ERROR_CODE.CONN_ERROR),
    MTA_GAME_USER(UsbId.VENDOR_ATMEL),
    NETWORK_MONITOR(ERROR_CODE.CANCEL_ERROR);
    
    private int f12280i;

    private C2399f(int i) {
        this.f12280i = i;
    }

    public int m13950a() {
        return this.f12280i;
    }
}
