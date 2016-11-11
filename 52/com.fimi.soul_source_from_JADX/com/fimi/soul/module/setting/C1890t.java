package com.fimi.soul.module.setting;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.io.File;

/* renamed from: com.fimi.soul.module.setting.t */
class C1890t implements OnClickListener {
    final /* synthetic */ FlyLogsActivity f9617a;

    C1890t(FlyLogsActivity flyLogsActivity) {
        this.f9617a = flyLogsActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f9617a.f9061j != null && this.f9617a.f9061j.size() > 0) {
            for (aa c : this.f9617a.f9061j) {
                File file = new File(c.m11669c());
                if (file.exists()) {
                    file.delete();
                }
            }
            this.f9617a.f9059h.m11907b(this.f9617a.f9061j);
        }
        this.f9617a.f9059h.m11904a(false);
        this.f9617a.f9063l.setVisibility(0);
        this.f9617a.f9064m.setVisibility(8);
        this.f9617a.f9062k.setVisibility(8);
        this.f9617a.f9059h.m11908b(false);
        this.f9617a.f9061j.clear();
    }
}
