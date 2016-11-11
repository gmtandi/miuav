package it.sephiroth.android.library.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class am implements Creator<SavedState> {
    am() {
    }

    public SavedState m16301a(Parcel parcel) {
        return new SavedState(null);
    }

    public SavedState[] m16302a(int i) {
        return new SavedState[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m16301a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m16302a(i);
    }
}
