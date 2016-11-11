package com.tencent.mm.sdk.contact;

import android.content.Context;
import com.tencent.mm.sdk.conversation.RConversationStorage;
import com.tencent.mm.sdk.storage.ISQLiteDatabase;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import com.tencent.mm.sdk.storage.MAutoStorage;

public class RContactStorage extends MAutoStorage<RContact> {
    public static final String AUTHORITY = "com.tencent.mm.sdk.contact.provider";
    public static final String PRIMARY_KEY = "";
    public static final String TABLE = "rcontact";

    private RContactStorage(ISQLiteDatabase iSQLiteDatabase) {
        super(iSQLiteDatabase);
    }

    public static RContactStorage create(Context context) {
        return new RContactStorage(new RContactDB(context));
    }

    public RContact get(String str) {
        MAutoDBItem rContact = new RContact();
        rContact.field_username = str;
        return !super.get(rContact, RConversationStorage.PRIMARY_KEY) ? null : rContact;
    }

    public String[] getColumns() {
        return RContact.COLUMNS;
    }

    public String getPrimaryKey() {
        return PRIMARY_KEY;
    }

    public String getTableName() {
        return TABLE;
    }
}
