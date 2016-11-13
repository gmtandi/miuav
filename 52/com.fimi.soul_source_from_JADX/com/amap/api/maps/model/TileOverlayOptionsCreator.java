package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class TileOverlayOptionsCreator implements Creator<TileOverlayOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public TileOverlayOptions createFromParcel(Parcel parcel) {
        boolean z = true;
        int readInt = parcel.readInt();
        TileProvider tileProvider = (TileProvider) parcel.readValue(TileProvider.class.getClassLoader());
        boolean z2 = parcel.readByte() != null;
        float readFloat = parcel.readFloat();
        int readInt2 = parcel.readInt();
        int readInt3 = parcel.readInt();
        String readString = parcel.readString();
        boolean z3 = parcel.readByte() != null;
        if (parcel.readByte() == null) {
            z = false;
        }
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions(readInt, null, z2, readFloat);
        if (tileProvider != null) {
            tileOverlayOptions.tileProvider(tileProvider);
        }
        tileOverlayOptions.memCacheSize(readInt2);
        tileOverlayOptions.diskCacheSize(readInt3);
        tileOverlayOptions.diskCacheDir(readString);
        tileOverlayOptions.memoryCacheEnabled(z3);
        tileOverlayOptions.diskCacheEnabled(z);
        return tileOverlayOptions;
    }

    public TileOverlayOptions[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
