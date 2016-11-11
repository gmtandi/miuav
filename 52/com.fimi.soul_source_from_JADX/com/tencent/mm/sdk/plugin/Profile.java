package com.tencent.mm.sdk.plugin;

import android.net.Uri;
import com.amap.api.services.district.DistrictSearchQuery;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.mm.sdk.conversation.RConversationStorage;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import com.xiaomi.market.sdk.C2537j;
import java.lang.reflect.Field;

public class Profile extends MAutoDBItem {
    public static final Uri CONTENT_URI;
    public static String[] columns;
    private static final Field[] f11811p;
    public String field_alias;
    public String field_avatar;
    public String field_bindemail;
    public String field_bindmobile;
    public long field_bindqq;
    public String field_city;
    public String field_nickname;
    public String field_province;
    public String field_signature;
    public String field_username;
    public String field_weibo;

    static {
        CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/profile");
        columns = new String[]{RConversationStorage.PRIMARY_KEY, "bindqq", "bindmobile", "bindemail", RContact.COL_ALIAS, RContact.COL_NICKNAME, C2537j.f12842Z, DistrictSearchQuery.KEYWORDS_PROVINCE, DistrictSearchQuery.KEYWORDS_CITY, "weibo", "avatar"};
        f11811p = MAutoDBItem.getValidFields(Profile.class);
    }

    protected Field[] fields() {
        return f11811p;
    }
}
