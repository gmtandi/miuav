package com.tencent.mm.sdk.openapi;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class WXImageObject implements IMediaObject {
    public byte[] imageData;
    public String imagePath;
    public String imageUrl;

    public WXImageObject(Bitmap bitmap) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
            this.imageData = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WXImageObject(byte[] bArr) {
        this.imageData = bArr;
    }

    public boolean checkArgs() {
        if ((this.imageData == null || this.imageData.length == 0) && ((this.imagePath == null || this.imagePath.length() == 0) && (this.imageUrl == null || this.imageUrl.length() == 0))) {
            Log.m13541e("MicroMsg.SDK.WXImageObject", "checkArgs fail, all arguments are null");
            return false;
        } else if (this.imageData != null && this.imageData.length > com.baidu.android.common.logging.Log.FILE_LIMETE) {
            Log.m13541e("MicroMsg.SDK.WXImageObject", "checkArgs fail, content is too large");
            return false;
        } else if (this.imagePath == null || this.imagePath.length() <= C1142e.f5202b) {
            if (this.imagePath != null) {
                int i;
                String str = this.imagePath;
                if (str == null || str.length() == 0) {
                    i = 0;
                } else {
                    File file = new File(str);
                    i = !file.exists() ? 0 : (int) file.length();
                }
                if (i > com.baidu.android.common.logging.Log.FILE_LIMETE) {
                    Log.m13541e("MicroMsg.SDK.WXImageObject", "checkArgs fail, image content is too large");
                    return false;
                }
            }
            if (this.imageUrl == null || this.imageUrl.length() <= C1142e.f5202b) {
                return true;
            }
            Log.m13541e("MicroMsg.SDK.WXImageObject", "checkArgs fail, url is invalid");
            return false;
        } else {
            Log.m13541e("MicroMsg.SDK.WXImageObject", "checkArgs fail, path is invalid");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putByteArray("_wximageobject_imageData", this.imageData);
        bundle.putString("_wximageobject_imagePath", this.imagePath);
        bundle.putString("_wximageobject_imageUrl", this.imageUrl);
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public int type() {
        return 2;
    }

    public void unserialize(Bundle bundle) {
        this.imageData = bundle.getByteArray("_wximageobject_imageData");
        this.imagePath = bundle.getString("_wximageobject_imagePath");
        this.imageUrl = bundle.getString("_wximageobject_imageUrl");
    }
}
