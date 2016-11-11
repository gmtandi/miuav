package com.fimi.soul.biz.p103k;

import android.os.Bundle;
import android.os.Message;
import com.fimi.soul.entity.FdsMsg;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import java.io.File;
import java.io.InputStream;

/* renamed from: com.fimi.soul.biz.k.m */
class C1389m extends ProgressListener implements Runnable {
    ObjectMetadata f6243a;
    int f6244b;
    final /* synthetic */ C1388l f6245c;
    private InputStream f6246d;
    private File f6247e;

    public C1389m(C1388l c1388l, int i, InputStream inputStream, File file, ObjectMetadata objectMetadata) {
        this.f6245c = c1388l;
        this.f6244b = i;
        this.f6246d = inputStream;
        this.f6247e = file;
        this.f6243a = objectMetadata;
    }

    public void onProgress(long j, long j2) {
        FdsMsg fdsMsg = new FdsMsg();
        fdsMsg.setTotal(j2);
        fdsMsg.setTransferred(j);
        if (this.f6247e != null) {
            fdsMsg.setFilePath(this.f6247e.getAbsolutePath());
        }
        Message obtainMessage = this.f6245c.f6240h.obtainMessage();
        obtainMessage.what = this.f6244b;
        obtainMessage.obj = fdsMsg;
        Bundle bundle = new Bundle();
        bundle.putString("file_path", this.f6247e.getAbsolutePath());
        obtainMessage.setData(bundle);
        this.f6245c.f6240h.sendMessage(obtainMessage);
    }

    public void run() {
        Message obtainMessage = this.f6245c.f6240h.obtainMessage();
        Object obj = null;
        if (this.f6244b == 0) {
            obj = this.f6245c.f6239g.m9065a(this.f6247e);
        } else if (this.f6244b == 1) {
            obj = this.f6245c.f6239g.m9067a(this.f6246d, this.f6243a);
        } else if (this.f6244b == 2) {
            obj = this.f6245c.f6239g.m9064a();
        } else if (this.f6244b == 3) {
            obj = this.f6245c.f6239g.m9068a(this.f6246d, this.f6243a, this);
        } else if (this.f6244b == 4) {
            obj = this.f6245c.f6239g.m9066a(this.f6247e, (ProgressListener) this);
        }
        obtainMessage.what = this.f6244b;
        obtainMessage.obj = obj;
        this.f6245c.f6240h.sendMessage(obtainMessage);
    }
}
