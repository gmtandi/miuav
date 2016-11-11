package com.google.zxing.client.result;

public final class EmailAddressParsedResult extends ParsedResult {
    private final String body;
    private final String emailAddress;
    private final String mailtoURI;
    private final String subject;

    EmailAddressParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.EMAIL_ADDRESS);
        this.emailAddress = str;
        this.subject = str2;
        this.body = str3;
        this.mailtoURI = str4;
    }

    public String getBody() {
        return this.body;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(30);
        ParsedResult.maybeAppend(this.emailAddress, stringBuffer);
        ParsedResult.maybeAppend(this.subject, stringBuffer);
        ParsedResult.maybeAppend(this.body, stringBuffer);
        return stringBuffer.toString();
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getMailtoURI() {
        return this.mailtoURI;
    }

    public String getSubject() {
        return this.subject;
    }
}
