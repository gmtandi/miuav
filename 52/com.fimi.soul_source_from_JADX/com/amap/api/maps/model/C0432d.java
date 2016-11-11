package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.model.d */
class C0432d implements Creator<Tile> {
    C0432d() {
    }

    public Tile m4357a(Parcel parcel) {
        return new Tile(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
    }

    public Tile[] m4358a(int i) {
        return new Tile[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4357a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4358a(i);
    }
}
