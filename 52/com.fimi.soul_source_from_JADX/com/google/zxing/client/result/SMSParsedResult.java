package com.google.zxing.client.result;

public final class SMSParsedResult extends ParsedResult {
    private final String body;
    private final String[] numbers;
    private final String subject;
    private final String[] vias;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.numbers = new String[]{str};
        this.vias = new String[]{str2};
        this.subject = str3;
        this.body = str4;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.numbers = strArr;
        this.vias = strArr2;
        this.subject = str;
        this.body = str2;
    }

    public String getBody() {
        return this.body;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(100);
        ParsedResult.maybeAppend(this.numbers, stringBuffer);
        ParsedResult.maybeAppend(this.subject, stringBuffer);
        ParsedResult.maybeAppend(this.body, stringBuffer);
        return stringBuffer.toString();
    }

    public String[] getNumbers() {
        return this.numbers;
    }

    public String getSMSURI() {
        Object obj = 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("sms:");
        Object obj2 = 1;
        for (int i = 0; i < this.numbers.length; i++) {
            if (obj2 != null) {
                obj2 = null;
            } else {
                stringBuffer.append(',');
            }
            stringBuffer.append(this.numbers[i]);
            if (this.vias[i] != null) {
                stringBuffer.append(";via=");
                stringBuffer.append(this.vias[i]);
            }
        }
        Object obj3 = this.body != null ? 1 : null;
        if (this.subject == null) {
            obj = null;
        }
        if (!(obj3 == null && obj == null)) {
            stringBuffer.append('?');
            if (obj3 != null) {
                stringBuffer.append("body=");
                stringBuffer.append(this.body);
            }
            if (obj != null) {
                if (obj3 != null) {
                    stringBuffer.append('&');
                }
                stringBuffer.append("subject=");
                stringBuffer.append(this.subject);
            }
        }
        return stringBuffer.toString();
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getVias() {
        return this.vias;
    }
}
