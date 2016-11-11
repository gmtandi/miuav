package com.fimi.soul.module.flyplannermedia;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.soul.entity.WifiDistanceFile;
import java.io.File;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.module.flyplannermedia.h */
class C1768h implements OnClickListener {
    final /* synthetic */ List f8700a;
    final /* synthetic */ C1766f f8701b;

    C1768h(C1766f c1766f, List list) {
        this.f8701b = c1766f;
        this.f8700a = list;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C1189f.m8331b().m8336a();
        for (WifiDistanceFile wifiDistanceFile : this.f8701b.f8698a.m11386b().m8493c()) {
            if (this.f8700a.contains(wifiDistanceFile)) {
                File file = new File(wifiDistanceFile.getPath().replace("file://", C2915a.f14760f));
                if (file.exists()) {
                    file.delete();
                }
                this.f8700a.remove(wifiDistanceFile);
            }
            C1113b a = this.f8701b.f8698a.f8659d.m7803a(this.f8701b.f8698a.getActivity(), wifiDistanceFile.getRemoteUrl());
            if (a != null) {
                this.f8701b.f8698a.f8659d.m7809a(a);
            }
        }
        this.f8701b.f8698a.m11386b().m8488a(this.f8700a);
        this.f8701b.f8698a.m11386b().m8498g();
        this.f8701b.f8698a.m11386b().notifyDataSetChanged();
        this.f8701b.f8698a.m11399h(false);
        C1189f.m8331b().m8348c();
        if (this.f8700a.size() == 0) {
            this.f8701b.f8698a.m11391d(true);
        }
    }
}
