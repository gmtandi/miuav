package com.fimi.soul.biz.update;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.service.UpdateApkService;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import com.xiaomi.market.sdk.UpdateResponse;
import java.io.File;
import org.codehaus.jackson.util.BufferRecycler;

/* renamed from: com.fimi.soul.biz.update.m */
class C1416m implements OnClickListener {
    final /* synthetic */ UpdateResponse f6367a;
    final /* synthetic */ C1412i f6368b;

    C1416m(C1412i c1412i, UpdateResponse updateResponse) {
        this.f6368b = c1412i;
        this.f6367a = updateResponse;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ak.m8084a(this.f6368b.f6358a, this.f6368b.f6358a.getString(C1205R.string.downloading_apk));
        Intent intent;
        if (new File(C1969i.m12486i(), C1236a.f5580D).exists() && String.valueOf(this.f6367a.versionCode).compareTo(be.m12355a(this.f6368b.f6358a, C1969i.m12486i() + C1236a.f5580D)) == 0) {
            intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.fromFile(new File(C1969i.m12486i(), C1236a.f5580D)), "application/vnd.android.package-archive");
            this.f6368b.f6358a.startActivity(intent);
        } else if (be.m12373c() != null) {
            intent = new Intent(this.f6368b.f6358a, UpdateApkService.class);
            intent.putExtra("down_url", this.f6367a.path);
            intent.setFlags(536870912);
            this.f6368b.f6358a.startService(intent);
        } else {
            ak.m8083a(this.f6368b.f6358a, (int) C1205R.string.system_sd_tip, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
        }
    }
}
