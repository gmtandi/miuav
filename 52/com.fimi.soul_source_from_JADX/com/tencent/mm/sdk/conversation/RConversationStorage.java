package com.tencent.mm.sdk.conversation;

import android.content.Context;
import com.tencent.mm.sdk.storage.ISQLiteDatabase;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import com.tencent.mm.sdk.storage.MAutoStorage;

public class RConversationStorage extends MAutoStorage<RConversation> {
    public static final String AUTHORITY = "com.tencent.mm.sdk.conversation.provider";
    public static final String PRIMARY_KEY = "username";
    public static final String TABLE = "rconversation";

    private RConversationStorage(ISQLiteDatabase iSQLiteDatabase) {
        super(iSQLiteDatabase);
    }

    public static RConversationStorage create(Context context) {
        return new RConversationStorage(new RConversationDB(context));
    }

    public RConversation get(String str) {
        MAutoDBItem rConversation = new RConversation();
        rConversation.field_username = str;
        return !super.get(rConversation, PRIMARY_KEY) ? null : rConversation;
    }

    public String[] getColumns() {
        return RConversation.COLUMNS;
    }

    public String getPrimaryKey() {
        return PRIMARY_KEY;
    }

    public String getTableName() {
        return TABLE;
    }
}
