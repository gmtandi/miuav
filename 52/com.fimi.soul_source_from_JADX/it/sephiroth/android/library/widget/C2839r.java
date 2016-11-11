package it.sephiroth.android.library.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: it.sephiroth.android.library.widget.r */
final class C2839r implements Creator<SavedState> {
    C2839r() {
    }

    public SavedState m16408a(Parcel parcel) {
        return new SavedState(null);
    }

    public SavedState[] m16409a(int i) {
        return new SavedState[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m16408a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m16409a(i);
    }
}
