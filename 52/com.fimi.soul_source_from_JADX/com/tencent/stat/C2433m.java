package com.tencent.stat;

/* renamed from: com.tencent.stat.m */
class C2433m implements C2407c {
    final /* synthetic */ C2431k f12382a;

    C2433m(C2431k c2431k) {
        this.f12382a = c2431k;
    }

    public void m14080a() {
        if (C2434n.m14089b().m14100a() >= StatConfig.getMaxBatchReportCount()) {
            C2434n.m14089b().m14101a(StatConfig.getMaxBatchReportCount());
        }
    }

    public void m14081b() {
    }
}
