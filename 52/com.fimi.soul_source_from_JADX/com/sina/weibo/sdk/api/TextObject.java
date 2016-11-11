package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import org.codehaus.jackson.smile.SmileConstants;

public class TextObject implements Parcelable {
    public static final Creator<TextObject> CREATOR;
    public String text;

    /* renamed from: com.sina.weibo.sdk.api.TextObject.1 */
    final class C21841 implements Creator<TextObject> {
        C21841() {
        }

        public TextObject createFromParcel(Parcel parcel) {
            return new TextObject(parcel);
        }

        public TextObject[] newArray(int i) {
            return new TextObject[i];
        }
    }

    static {
        CREATOR = new C21841();
    }

    public TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }

    public boolean checkArgs() {
        return !TextUtils.isEmpty(this.text) && this.text.length() <= SmileConstants.MAX_SHARED_STRING_VALUES;
    }

    public int describeContents() {
        return 0;
    }

    public int getObjType() {
        return 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }
}
