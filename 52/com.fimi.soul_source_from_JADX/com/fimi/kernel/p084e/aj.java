package com.fimi.kernel.p084e;

import java.util.concurrent.Callable;

/* renamed from: com.fimi.kernel.e.aj */
class aj implements Callable<Boolean> {
    private String f5300a;

    public aj(String str) {
        this.f5300a = str;
    }

    public Boolean m8080a() {
        for (int i = 0; i < 999999999; i++) {
            if (i == 999999997) {
                System.out.println(this.f5300a);
            }
            if (Thread.interrupted()) {
                return Boolean.valueOf(false);
            }
        }
        System.out.println("\u7ee7\u7eed\u6267\u884c..........");
        return Boolean.valueOf(true);
    }

    public /* synthetic */ Object call() {
        return m8080a();
    }
}
