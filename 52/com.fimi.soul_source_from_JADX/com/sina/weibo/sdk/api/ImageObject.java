package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import cn.sharesdk.framework.utils.C0205d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class ImageObject implements Parcelable {
    public static final Creator<ImageObject> CREATOR;
    private static final int DATA_SIZE;
    public byte[] imageData;
    public String imagePath;

    /* renamed from: com.sina.weibo.sdk.api.ImageObject.1 */
    final class C21831 implements Creator<ImageObject> {
        C21831() {
        }

        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    }

    static {
        DATA_SIZE = AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_END;
        CREATOR = new C21831();
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            new Throwable("imageData and imagePath are null").printStackTrace();
            return false;
        } else if (this.imageData != null && this.imageData.length > DATA_SIZE) {
            new Throwable("imageData is too large").printStackTrace();
            return false;
        } else if (this.imagePath == null || this.imagePath.length() <= Opcodes.ACC_INTERFACE) {
            if (this.imagePath != null) {
                try {
                    File file = new File(this.imagePath);
                    if (!file.exists() || file.length() == 0 || file.length() > 10485760) {
                        new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                        return false;
                    }
                } catch (Throwable th) {
                    new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            new Throwable("imagePath is too length").printStackTrace();
            return false;
        }
    }

    public int describeContents() {
        return 0;
    }

    public int getObjType() {
        return 2;
    }

    public final void setImageObject(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
                this.imageData = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        C0205d.m752a().m738d(th2);
                    }
                }
            } catch (Throwable th3) {
                th2 = th3;
                try {
                    C0205d.m752a().m738d(th2);
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th22) {
                            C0205d.m752a().m738d(th22);
                        }
                    }
                } catch (Throwable th4) {
                    th22 = th4;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th5) {
                            C0205d.m752a().m738d(th5);
                        }
                    }
                    throw th22;
                }
            }
        } catch (Throwable th6) {
            th22 = th6;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th22;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }
}
