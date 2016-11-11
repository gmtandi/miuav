package com.tencent.qqconnect.dataprovider.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TextAndMediaPath implements Parcelable {
    public static final Creator<TextAndMediaPath> CREATOR;
    private String f12175a;
    private String f12176b;

    /* renamed from: com.tencent.qqconnect.dataprovider.datatype.TextAndMediaPath.1 */
    final class C23921 implements Creator<TextAndMediaPath> {
        C23921() {
        }

        public TextAndMediaPath createFromParcel(Parcel parcel) {
            return new TextAndMediaPath(null);
        }

        public TextAndMediaPath[] newArray(int i) {
            return new TextAndMediaPath[i];
        }
    }

    static {
        CREATOR = new C23921();
    }

    private TextAndMediaPath(Parcel parcel) {
        this.f12175a = parcel.readString();
        this.f12176b = parcel.readString();
    }

    public TextAndMediaPath(String str, String str2) {
        this.f12175a = str;
        this.f12176b = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getMediaPath() {
        return this.f12176b;
    }

    public String getText() {
        return this.f12175a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f12175a);
        parcel.writeString(this.f12176b);
    }
}
