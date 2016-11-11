package com.xiaomi.market.sdk;

import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.w */
class C2550w {
    String bi;
    int bj;
    int bk;
    String bl;
    String bm;
    long bn;
    String bo;
    String bp;
    long bq;
    String updateLog;
    int versionCode;
    String versionName;

    C2550w() {
        this.bo = C2915a.f14760f;
        this.bp = C2915a.f14760f;
    }

    public String toString() {
        return "UpdateInfo:\nhost = " + this.bi + "\nfitness = " + this.bk + "\nupdateLog = " + this.updateLog + "\nversionCode = " + this.versionCode + "\nversionName = " + this.versionName + "\napkUrl = " + this.bl + "\napkHash = " + this.bm + "\napkSize = " + this.bn + "\ndiffUrl = " + this.bo + "\ndiffHash = " + this.bp + "\ndiffSize = " + this.bq;
    }
}
