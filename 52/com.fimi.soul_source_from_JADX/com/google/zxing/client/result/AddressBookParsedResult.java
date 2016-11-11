package com.google.zxing.client.result;

public final class AddressBookParsedResult extends ParsedResult {
    private final String[] addresses;
    private final String birthday;
    private final String[] emails;
    private final String[] names;
    private final String note;
    private final String org;
    private final String[] phoneNumbers;
    private final String pronunciation;
    private final String title;
    private final String url;

    public AddressBookParsedResult(String[] strArr, String str, String[] strArr2, String[] strArr3, String str2, String[] strArr4, String str3, String str4, String str5, String str6) {
        super(ParsedResultType.ADDRESSBOOK);
        this.names = strArr;
        this.pronunciation = str;
        this.phoneNumbers = strArr2;
        this.emails = strArr3;
        this.note = str2;
        this.addresses = strArr4;
        this.org = str3;
        this.birthday = str4;
        this.title = str5;
        this.url = str6;
    }

    public String[] getAddresses() {
        return this.addresses;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(100);
        ParsedResult.maybeAppend(this.names, stringBuffer);
        ParsedResult.maybeAppend(this.pronunciation, stringBuffer);
        ParsedResult.maybeAppend(this.title, stringBuffer);
        ParsedResult.maybeAppend(this.org, stringBuffer);
        ParsedResult.maybeAppend(this.addresses, stringBuffer);
        ParsedResult.maybeAppend(this.phoneNumbers, stringBuffer);
        ParsedResult.maybeAppend(this.emails, stringBuffer);
        ParsedResult.maybeAppend(this.url, stringBuffer);
        ParsedResult.maybeAppend(this.birthday, stringBuffer);
        ParsedResult.maybeAppend(this.note, stringBuffer);
        return stringBuffer.toString();
    }

    public String[] getEmails() {
        return this.emails;
    }

    public String[] getNames() {
        return this.names;
    }

    public String getNote() {
        return this.note;
    }

    public String getOrg() {
        return this.org;
    }

    public String[] getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public String getTitle() {
        return this.title;
    }

    public String getURL() {
        return this.url;
    }
}
