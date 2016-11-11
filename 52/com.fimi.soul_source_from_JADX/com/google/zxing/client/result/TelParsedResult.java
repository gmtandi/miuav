package com.google.zxing.client.result;

public final class TelParsedResult extends ParsedResult {
    private final String number;
    private final String telURI;
    private final String title;

    public TelParsedResult(String str, String str2, String str3) {
        super(ParsedResultType.TEL);
        this.number = str;
        this.telURI = str2;
        this.title = str3;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(20);
        ParsedResult.maybeAppend(this.number, stringBuffer);
        ParsedResult.maybeAppend(this.title, stringBuffer);
        return stringBuffer.toString();
    }

    public String getNumber() {
        return this.number;
    }

    public String getTelURI() {
        return this.telURI;
    }

    public String getTitle() {
        return this.title;
    }
}
