package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.SuggestBean;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.k.ao */
public class ao extends C1377q {
    public ArrayList<Bitmap> f6073g;
    private Context f6074h;

    public ao(Context context) {
        super(context);
        this.f6073g = new ArrayList();
        this.f6074h = context;
    }

    public PlaneMsg m9214a(SuggestBean suggestBean, C1330i c1330i) {
        this.f.put(Integer.valueOf(1), c1330i);
        C1388l a = C1388l.m9322a(this.f6074h);
        Log.i("bitmapList size is ", this.f6073g.size() + C2915a.f14760f);
        if (this.f6073g == null || this.f6073g.size() <= 0 || Constants.VIA_RESULT_SUCCESS.equals(suggestBean.getUserID())) {
            ah.m8077b(new C1393r(this, 1, suggestBean, c1330i));
        } else {
            int size = this.f6073g.size();
            Iterator it = this.f6073g.iterator();
            while (it.hasNext()) {
                Bitmap bitmap = (Bitmap) it.next();
                a.m9324a(bitmap, new ap(this, suggestBean, size, c1330i));
                if (bitmap != null && bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
        }
        return this.e;
    }

    public void m9215a(Bitmap bitmap) {
        this.f6073g.add(bitmap);
    }

    public ArrayList<Bitmap> m9216b() {
        return this.f6073g;
    }
}
