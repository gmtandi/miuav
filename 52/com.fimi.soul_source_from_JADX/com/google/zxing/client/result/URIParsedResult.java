package com.google.zxing.client.result;

public final class URIParsedResult extends ParsedResult {
    private final String title;
    private final String uri;

    public URIParsedResult(String str, String str2) {
        super(ParsedResultType.URI);
        this.uri = massageURI(str);
        this.title = str2;
    }

    private boolean containsUser() {
        int indexOf = this.uri.indexOf(58) + 1;
        int length = this.uri.length();
        int i = indexOf;
        while (i < length && this.uri.charAt(i) == '/') {
            i++;
        }
        indexOf = this.uri.indexOf(47, i);
        if (indexOf >= 0) {
            length = indexOf;
        }
        indexOf = this.uri.indexOf(64, i);
        return indexOf >= i && indexOf < length;
    }

    private static boolean isColonFollowedByPortNumber(String str, int i) {
        int indexOf = str.indexOf(47, i + 1);
        int length = indexOf < 0 ? str.length() : indexOf;
        if (length <= i + 1) {
            return false;
        }
        indexOf = i + 1;
        while (indexOf < length) {
            if (str.charAt(indexOf) < '0' || str.charAt(indexOf) > '9') {
                return false;
            }
            indexOf++;
        }
        return true;
    }

    private static String massageURI(String str) {
        int indexOf = str.indexOf(58);
        return indexOf < 0 ? new StringBuffer().append("http://").append(str).toString() : isColonFollowedByPortNumber(str, indexOf) ? new StringBuffer().append("http://").append(str).toString() : new StringBuffer().append(str.substring(0, indexOf).toLowerCase()).append(str.substring(indexOf)).toString();
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(30);
        ParsedResult.maybeAppend(this.title, stringBuffer);
        ParsedResult.maybeAppend(this.uri, stringBuffer);
        return stringBuffer.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public String getURI() {
        return this.uri;
    }

    public boolean isPossiblyMaliciousURI() {
        return containsUser();
    }
}
