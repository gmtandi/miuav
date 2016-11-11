package com.google.zxing.client.result;

import com.mining.app.zxing.p127b.C2142m;

public final class ParsedResultType {
    public static final ParsedResultType ADDRESSBOOK;
    public static final ParsedResultType ANDROID_INTENT;
    public static final ParsedResultType CALENDAR;
    public static final ParsedResultType EMAIL_ADDRESS;
    public static final ParsedResultType GEO;
    public static final ParsedResultType ISBN;
    public static final ParsedResultType MOBILETAG_RICH_WEB;
    public static final ParsedResultType NDEF_SMART_POSTER;
    public static final ParsedResultType PRODUCT;
    public static final ParsedResultType SMS;
    public static final ParsedResultType TEL;
    public static final ParsedResultType TEXT;
    public static final ParsedResultType URI;
    public static final ParsedResultType WIFI;
    private final String name;

    static {
        ADDRESSBOOK = new ParsedResultType("ADDRESSBOOK");
        EMAIL_ADDRESS = new ParsedResultType("EMAIL_ADDRESS");
        PRODUCT = new ParsedResultType("PRODUCT");
        URI = new ParsedResultType("URI");
        TEXT = new ParsedResultType("TEXT");
        ANDROID_INTENT = new ParsedResultType("ANDROID_INTENT");
        GEO = new ParsedResultType("GEO");
        TEL = new ParsedResultType("TEL");
        SMS = new ParsedResultType("SMS");
        CALENDAR = new ParsedResultType("CALENDAR");
        WIFI = new ParsedResultType("WIFI");
        NDEF_SMART_POSTER = new ParsedResultType("NDEF_SMART_POSTER");
        MOBILETAG_RICH_WEB = new ParsedResultType("MOBILETAG_RICH_WEB");
        ISBN = new ParsedResultType(C2142m.f11244b);
    }

    private ParsedResultType(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
