package com.fimi.soul.media.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.os.Handler;
import android.support.v4.util.LruCache;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class an {
    private static an f7797b;
    public LruCache<String, Bitmap> f7798a;
    private ExecutorService f7799c;
    private Context f7800d;
    private ArrayList<Bitmap> f7801e;

    static {
        f7797b = null;
    }

    private an(Context context) {
        this.f7799c = Executors.newFixedThreadPool(4);
        this.f7801e = new ArrayList();
        this.f7800d = context;
        this.f7798a = new ao(this, ((int) Runtime.getRuntime().maxMemory()) / 8);
    }

    private int m10759a(Options options, int i, int i2) {
        if (i == 0 || i == 0) {
            return 1;
        }
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 <= i && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i));
        i3 = Math.round(((float) i4) / ((float) i));
        return round >= i3 ? i3 : round;
    }

    private Bitmap m10761a(String str, int i, int i2) {
        Options options = new Options();
        options.inSampleSize = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inPreferredConfig = Config.RGB_565;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = m10759a(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static an m10762a(Context context) {
        if (f7797b == null) {
            f7797b = new an(context);
        }
        return f7797b;
    }

    private void m10764a(String str, Bitmap bitmap) {
        if (m10765b(str) == null && bitmap != null) {
            this.f7801e.add(bitmap);
            this.f7798a.put(str, bitmap);
        }
    }

    private Bitmap m10765b(String str) {
        return (Bitmap) this.f7798a.get(str);
    }

    public Bitmap m10766a(String str, Point point, ar arVar) {
        Bitmap b = m10765b(str);
        Handler apVar = new ap(this, arVar, str);
        if (b == null) {
            this.f7799c.execute(new aq(this, str, apVar));
        }
        return b;
    }

    public Bitmap m10767a(String str, ar arVar) {
        return m10766a(str, null, arVar);
    }

    public void m10768a() {
        if (this.f7798a != null && this.f7798a.size() > 0) {
            this.f7798a.evictAll();
            for (int i = 0; i < this.f7801e.size(); i++) {
                Bitmap bitmap = (Bitmap) this.f7801e.get(i);
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
            }
            this.f7801e.clear();
        }
    }

    public synchronized void m10769a(String str) {
        if (str != null) {
            if (this.f7798a != null) {
                Bitmap bitmap = (Bitmap) this.f7798a.remove(str);
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
}
