package com.google.zxing.client.result;

public abstract class ParsedResult {
    private final ParsedResultType type;

    protected ParsedResult(ParsedResultType parsedResultType) {
        this.type = parsedResultType;
    }

    public static void maybeAppend(String str, StringBuffer stringBuffer) {
        if (str != null && str.length() > 0) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append('\n');
            }
            stringBuffer.append(str);
        }
    }

    public static void maybeAppend(String[] strArr, StringBuffer stringBuffer) {
        if (strArr != null) {
            int i = 0;
            while (i < strArr.length) {
                if (strArr[i] != null && strArr[i].length() > 0) {
                    if (stringBuffer.length() > 0) {
                        stringBuffer.append('\n');
                    }
                    stringBuffer.append(strArr[i]);
                }
                i++;
            }
        }
    }

    public abstract String getDisplayResult();

    public ParsedResultType getType() {
        return this.type;
    }

    public String toString() {
        return getDisplayResult();
    }
}
