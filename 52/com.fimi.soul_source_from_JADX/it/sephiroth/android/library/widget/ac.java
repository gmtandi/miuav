package it.sephiroth.android.library.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ac implements Creator<GroupMetadata> {
    ac() {
    }

    public GroupMetadata m16282a(Parcel parcel) {
        return GroupMetadata.m16168a(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong());
    }

    public GroupMetadata[] m16283a(int i) {
        return new GroupMetadata[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m16282a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m16283a(i);
    }
}
