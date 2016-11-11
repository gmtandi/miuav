package com.fimi.soul.p087b;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.aa;
import com.fimi.soul.media.player.FermiPlayerUtils;
import com.fimi.soul.utils.C1985y;
import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.b.s */
class C1230s extends AsyncTask<String, Void, Bitmap> {
    String f5544a;
    final /* synthetic */ C1224m f5545b;
    private String f5546c;
    private String f5547d;

    C1230s(C1224m c1224m) {
        this.f5545b = c1224m;
        this.f5544a = null;
    }

    protected Bitmap m8504a(String... strArr) {
        this.f5547d = strArr[0];
        this.f5546c = strArr[1];
        Bitmap bitmap = null;
        if (C2915a.f14760f.equalsIgnoreCase(this.f5547d) || new File(this.f5546c).exists()) {
            if (C1985y.m12536c(this.f5546c, this.f5545b.f5519h)) {
                bitmap = ThumbnailUtils.createVideoThumbnail(this.f5546c, 1);
                try {
                    this.f5544a = FermiPlayerUtils.getVideoDurationString(this.f5545b.f5519h, this.f5546c, "mm:ss");
                    if (this.f5545b.m8492b()) {
                        this.f5545b.f5532u.put("getdur_online" + this.f5546c, this.f5544a);
                    } else {
                        this.f5545b.f5532u.put("getdur_local" + this.f5546c, this.f5544a);
                    }
                } catch (Exception e) {
                }
            } else {
                try {
                    bitmap = aa.m7982a(this.f5545b.f5519h, this.f5546c, (int) SmileConstants.TOKEN_PREFIX_SHORT_UNICODE);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (FermiPlayerUtils.createRemoteVideoThumbnail(this.f5547d, this.f5546c)) {
            bitmap = BitmapFactory.decodeFile(this.f5546c);
        }
        if (bitmap != null) {
            this.f5545b.m8487a(this.f5546c, bitmap);
        }
        return bitmap;
    }

    @TargetApi(16)
    protected void m8505a(Bitmap bitmap) {
        Object obj;
        super.onPostExecute(bitmap);
        if (this.f5545b.m8492b()) {
            obj = "getdur_online" + this.f5546c;
        } else {
            String str = "getdur_local" + this.f5546c;
        }
        ImageView imageView = (ImageView) this.f5545b.f5530s.findViewWithTag(this.f5546c);
        TextView textView = (TextView) this.f5545b.f5530s.findViewWithTag(obj);
        if (textView != null && this.f5544a != null) {
            textView.setVisibility(0);
            textView.setText((String) this.f5545b.f5532u.get(obj));
        } else if (!(this.f5545b.m8492b() || textView == null)) {
            textView.setVisibility(8);
        }
        Bitmap a = this.f5545b.m8477a(this.f5546c);
        if (!(a == null || imageView == null)) {
            this.f5545b.m8467a(imageView, this.f5546c, a);
        }
        this.f5545b.f5529r.remove(this);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8504a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8505a((Bitmap) obj);
    }
}
