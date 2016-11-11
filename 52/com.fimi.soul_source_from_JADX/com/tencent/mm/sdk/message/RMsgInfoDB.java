package com.tencent.mm.sdk.message;

import android.content.Context;
import android.net.Uri;
import com.tencent.mm.sdk.storage.ContentProviderDB;
import java.util.HashMap;
import java.util.Map;

public class RMsgInfoDB extends ContentProviderDB<RMsgInfoDB> {
    private static final Map<String, Uri> f11793O;
    public static final String TABLE = "message";

    static {
        Map hashMap = new HashMap();
        f11793O = hashMap;
        hashMap.put(TABLE, Uri.parse("content://com.tencent.mm.sdk.msginfo.provider/message"));
    }

    public RMsgInfoDB(Context context) {
        super(context);
    }

    public Uri getUriFromTable(String str) {
        return (Uri) f11793O.get(str);
    }
}
