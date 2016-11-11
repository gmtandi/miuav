package com.fimi.soul.biz.camera.entity;

import com.fimi.soul.biz.camera.C1313t;

public class X11StorageTask extends X11Task {
    public X11StorageTask(C1313t c1313t) {
        super(c1313t);
    }

    protected void doNext() {
        getContext().m8873r().m8749c(getCurrentKey());
    }
}
