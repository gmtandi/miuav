package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ShareModel implements Parcelable {
    public static final Creator<ShareModel> CREATOR;
    public String f12168a;
    public String f12169b;
    public String f12170c;
    public String f12171d;

    /* renamed from: com.tencent.open.yyb.ShareModel.1 */
    final class C23871 implements Creator<ShareModel> {
        C23871() {
        }

        public ShareModel m13876a(Parcel parcel) {
            ShareModel shareModel = new ShareModel();
            shareModel.f12168a = parcel.readString();
            shareModel.f12169b = parcel.readString();
            shareModel.f12170c = parcel.readString();
            shareModel.f12171d = parcel.readString();
            return shareModel;
        }

        public ShareModel[] m13877a(int i) {
            return null;
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m13876a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m13877a(i);
        }
    }

    static {
        CREATOR = new C23871();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f12168a);
        parcel.writeString(this.f12169b);
        parcel.writeString(this.f12170c);
        parcel.writeString(this.f12171d);
    }
}
