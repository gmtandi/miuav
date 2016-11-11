package com.tencent.mm.sdk.contact;

import android.content.Context;
import android.net.Uri;
import com.tencent.mm.sdk.storage.ContentProviderDB;
import java.util.HashMap;
import java.util.Map;

public class RContactDB extends ContentProviderDB<RContactDB> {
    private static final Map<String, Uri> f11788O;

    static {
        Map hashMap = new HashMap();
        f11788O = hashMap;
        hashMap.put(RContactStorage.TABLE, Uri.parse("content://com.tencent.mm.sdk.contact.provider/rcontact"));
    }

    public RContactDB(Context context) {
        super(context);
    }

    public Uri getUriFromTable(String str) {
        return (Uri) f11788O.get(str);
    }
}
