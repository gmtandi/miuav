package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.mapcore.bn.C0304a;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;

public class bb extends bc {
    private TileProvider f2193e;

    public bb(Context context, int i, int i2) {
        super(context, i, i2);
        this.f2193e = null;
        m3571a(context);
    }

    private void m3571a(Context context) {
        m3572b(context);
    }

    private void m3572b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
        }
    }

    private Bitmap m3573c(C0304a c0304a) {
        Bitmap bitmap = null;
        try {
            Tile tile = this.f2193e.getTile(c0304a.f1831a, c0304a.f1832b, c0304a.f1833c);
            if (!(tile == null || tile == TileProvider.NO_TILE)) {
                bitmap = BitmapFactory.decodeByteArray(tile.data, 0, tile.data.length);
            }
        } catch (Throwable th) {
        }
        return bitmap;
    }

    protected Bitmap m3574a(Object obj) {
        return m3573c((C0304a) obj);
    }

    public void m3575a(TileProvider tileProvider) {
        this.f2193e = tileProvider;
    }
}
