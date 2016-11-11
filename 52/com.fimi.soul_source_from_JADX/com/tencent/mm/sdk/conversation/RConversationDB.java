package com.tencent.mm.sdk.conversation;

import android.content.Context;
import android.net.Uri;
import com.tencent.mm.sdk.storage.ContentProviderDB;
import java.util.HashMap;
import java.util.Map;

public class RConversationDB extends ContentProviderDB<RConversationDB> {
    private static final Map<String, Uri> f11791O;

    static {
        Map hashMap = new HashMap();
        f11791O = hashMap;
        hashMap.put(RConversationStorage.TABLE, Uri.parse("content://com.tencent.mm.sdk.conversation.provider/rconversation"));
    }

    public RConversationDB(Context context) {
        super(context);
    }

    public Uri getUriFromTable(String str) {
        return (Uri) f11791O.get(str);
    }
}
