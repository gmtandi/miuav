package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TextOnly implements Parcelable {
    public static final Creator<TextOnly> CREATOR;
    private String f12177a;

    /* renamed from: com.tencent.qqconnect.dataprovider.datatype.TextOnly.1 */
    final class C23931 implements Creator<TextOnly> {
        C23931() {
        }

        public TextOnly createFromParcel(Parcel parcel) {
            return new TextOnly(null);
        }

        public TextOnly[] newArray(int i) {
            return new TextOnly[i];
        }
    }

    static {
        CREATOR = new C23931();
    }

    private TextOnly(Parcel parcel) {
        this.f12177a = parcel.readString();
    }

    public TextOnly(String str) {
        this.f12177a = str;
    }

    public int describeContents() {
        return 0;
    }

    public String getText() {
        return this.f12177a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f12177a);
    }
}
