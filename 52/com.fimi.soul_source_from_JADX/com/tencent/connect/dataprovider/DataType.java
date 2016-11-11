package com.tencent.connect.dataprovider;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class DataType {
    public static final int CONTENT_AND_IMAGE_PATH = 1;
    public static final int CONTENT_AND_VIDEO_PATH = 2;
    public static final int CONTENT_ONLY = 4;

    public class TextAndMediaPath implements Parcelable {
        public static final Creator<TextAndMediaPath> CREATOR;
        private String f11492a;
        private String f11493b;

        /* renamed from: com.tencent.connect.dataprovider.DataType.TextAndMediaPath.1 */
        final class C22131 implements Creator<TextAndMediaPath> {
            C22131() {
            }

            public TextAndMediaPath createFromParcel(Parcel parcel) {
                return new TextAndMediaPath(null);
            }

            public TextAndMediaPath[] newArray(int i) {
                return new TextAndMediaPath[i];
            }
        }

        static {
            CREATOR = new C22131();
        }

        private TextAndMediaPath(Parcel parcel) {
            this.f11492a = parcel.readString();
            this.f11493b = parcel.readString();
        }

        public TextAndMediaPath(String str, String str2) {
            this.f11492a = str;
            this.f11493b = str2;
        }

        public int describeContents() {
            return 0;
        }

        public String getMediaPath() {
            return this.f11493b;
        }

        public String getText() {
            return this.f11492a;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f11492a);
            parcel.writeString(this.f11493b);
        }
    }

    public class TextOnly implements Parcelable {
        public static final Creator<TextOnly> CREATOR;
        private String f11494a;

        /* renamed from: com.tencent.connect.dataprovider.DataType.TextOnly.1 */
        final class C22141 implements Creator<TextOnly> {
            C22141() {
            }

            public TextOnly createFromParcel(Parcel parcel) {
                return new TextOnly(null);
            }

            public TextOnly[] newArray(int i) {
                return new TextOnly[i];
            }
        }

        static {
            CREATOR = new C22141();
        }

        private TextOnly(Parcel parcel) {
            this.f11494a = parcel.readString();
        }

        public TextOnly(String str) {
            this.f11494a = str;
        }

        public int describeContents() {
            return 0;
        }

        public String getText() {
            return this.f11494a;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f11494a);
        }
    }
}
