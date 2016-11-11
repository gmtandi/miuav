package com.fimi.soul.module.login;

import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;

/* renamed from: com.fimi.soul.module.login.c */
class C1791c implements C1330i {
    final /* synthetic */ C1790b f8847a;

    C1791c(C1790b c1790b) {
        this.f8847a = c1790b;
    }

    public void m11531a(PlaneMsg planeMsg) {
        this.f8847a.f8846a.m11472a(!planeMsg.isSuccess());
    }
}
