package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.baidu.android.common.logging.Log;
import com.fimi.kernel.p076b.p080d.C1142e;
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
            C0205d.m752a().m737d("checkArgs fail, all arguments is null", new Object[0]);
            return false;
        } else if (this.extInfo != null && this.extInfo.length() > Opcodes.ACC_STRICT) {
            C0205d.m752a().m737d("checkArgs fail, extInfo is invalid", new Object[0]);
            return false;
        } else if (this.filePath != null && this.filePath.length() > C1142e.f5202b) {
            C0205d.m752a().m737d("checkArgs fail, filePath is invalid", new Object[0]);
            return false;
        } else if (this.filePath != null && new File(this.filePath).length() > 10485760) {
            C0205d.m752a().m737d("checkArgs fail, fileSize is too large", new Object[0]);
            return false;
        } else if (this.fileData == null || this.fileData.length <= Log.FILE_LIMETE) {
            return true;
        } else {
            C0205d.m752a().m737d("checkArgs fail, fileData is too large", new Object[0]);
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
