package com.fimi.soul.media.gallery;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import java.io.File;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.media.gallery.g */
class C1593g implements OnClickListener {
    final /* synthetic */ C1591e f7814a;

    C1593g(C1591e c1591e) {
        this.f7814a = c1591e;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        File file = new File(this.f7814a.f7812a.m10672h().replace("file://", C2915a.f14760f));
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        Intent intent = new Intent();
        intent.putExtra("del_file", this.f7814a.f7812a.m10672h());
        this.f7814a.f7812a.setResult(1, intent);
        this.f7814a.f7812a.finish();
    }
}
