package com.fimi.soul.service;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.utils.C1969i;
import java.io.File;

/* renamed from: com.fimi.soul.service.y */
class C1958y extends Handler {
    final /* synthetic */ UpdateApkService f10001a;

    C1958y(UpdateApkService updateApkService) {
        this.f10001a = updateApkService;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1) {
            Toast.makeText(this.f10001a, C1205R.string.down_success, 0).show();
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.fromFile(new File(C1969i.m12486i(), C1236a.f5580D)), "application/vnd.android.package-archive");
            this.f10001a.startActivity(intent);
        } else if (message.what == 2) {
            this.f10001a.f9956d.contentView.setProgressBar(C1205R.id.download_bar, 100, Integer.parseInt(message.obj.toString()), false);
            this.f10001a.f9956d.contentView.setTextViewText(C1205R.id.text_download_process, message.obj.toString() + "%");
            this.f10001a.m12161a();
            return;
        } else {
            Toast.makeText(this.f10001a, C1205R.string.down_fail, 0).show();
        }
        this.f10001a.f9955c.cancel(8888);
        UpdateApkService.f9952h = false;
        this.f10001a.stopSelf();
    }
}
