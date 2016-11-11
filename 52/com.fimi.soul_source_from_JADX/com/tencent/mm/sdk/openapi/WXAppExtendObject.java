package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.openapi.WXMediaMessage.IMediaObject;
import com.tencent.mm.sdk.platformtools.Log;
import java.io.File;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class WXAppExtendObject implements IMediaObject {
    public String extInfo;
    public byte[] fileData;
    public String filePath;

    public WXAppExtendObject(String str, String str2) {
        this.extInfo = str;
        this.filePath = str2;
    }

    public WXAppExtendObject(String str, byte[] bArr) {
        this.extInfo = str;
        this.fileData = bArr;
    }

    public boolean checkArgs() {
        if ((this.extInfo == null || this.extInfo.length() == 0) && ((this.filePath == null || this.filePath.length() == 0) && (this.fileData == null || this.fileData.length == 0))) {
            Log.m13541e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, all arguments is null");
            return false;
        } else if (this.extInfo != null && this.extInfo.length() > Opcodes.ACC_STRICT) {
            Log.m13541e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, extInfo is invalid");
            return false;
        } else if (this.filePath == null || this.filePath.length() <= C1142e.f5202b) {
            if (this.filePath != null) {
                int i;
                String str = this.filePath;
                if (str == null || str.length() == 0) {
                    i = 0;
                } else {
                    File file = new File(str);
                    i = !file.exists() ? 0 : (int) file.length();
                }
                if (i > com.baidu.android.common.logging.Log.FILE_LIMETE) {
                    Log.m13541e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
                    return false;
                }
            }
            if (this.fileData == null || this.fileData.length <= com.baidu.android.common.logging.Log.FILE_LIMETE) {
                return true;
            }
            Log.m13541e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
            return false;
        } else {
            Log.m13541e("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, filePath is invalid");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxappextendobject_extInfo", this.extInfo);
        bundle.putByteArray("_wxappextendobject_fileData", this.fileData);
        bundle.putString("_wxappextendobject_filePath", this.filePath);
    }

    public int type() {
        return 7;
    }

    public void unserialize(Bundle bundle) {
        this.extInfo = bundle.getString("_wxappextendobject_extInfo");
        this.fileData = bundle.getByteArray("_wxappextendobject_fileData");
        this.filePath = bundle.getString("_wxappextendobject_filePath");
    }
}
