package com.fimi.soul.media.gallery;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.kernel.p076b.p078b.C1115d;
import com.fimi.soul.utils.C1969i;
import java.io.File;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.media.gallery.m */
class C1599m implements OnClickListener {
    final /* synthetic */ DroneImagePagerActivity f7820a;

    C1599m(DroneImagePagerActivity droneImagePagerActivity) {
        this.f7820a = droneImagePagerActivity;
    }

    public void onClick(View view) {
        String b = C1969i.m12478b(this.f7820a.m10671g());
        if (this.f7820a.f7696A != null && this.f7820a.f7696A.m7785g() != C1115d.Completed) {
            File file = new File(b.replace("file://", C2915a.f14760f));
            if (file.exists()) {
                file.delete();
            }
            this.f7820a.f7710l.setScrollble(true);
            this.f7820a.f7724z.m7809a(this.f7820a.f7696A);
            this.f7820a.m10694c(false);
            this.f7820a.m10690a(false);
            this.f7820a.m10693b(false);
            this.f7820a.m10661b();
        }
    }
}
