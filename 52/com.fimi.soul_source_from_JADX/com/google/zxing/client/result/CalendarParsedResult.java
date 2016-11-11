package com.google.zxing.client.result;

public final class CalendarParsedResult extends ParsedResult {
    private final String attendee;
    private final String description;
    private final String end;
    private final String location;
    private final String start;
    private final String summary;

    public CalendarParsedResult(String str, String str2, String str3, String str4, String str5, String str6) {
        super(ParsedResultType.CALENDAR);
        if (str2 == null) {
            throw new IllegalArgumentException();
        }
        validateDate(str2);
        if (str3 == null) {
            str3 = str2;
        } else {
            validateDate(str3);
        }
        this.summary = str;
        this.start = str2;
        this.end = str3;
        this.location = str4;
        this.attendee = str5;
        this.description = str6;
    }

    private static void validateDate(String str) {
        if (str != null) {
            int length = str.length();
            if (length == 8 || length == 15 || length == 16) {
                int i = 0;
                while (i < 8) {
                    if (Character.isDigit(str.charAt(i))) {
                        i++;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                if (length <= 8) {
                    return;
                }
                if (str.charAt(8) != 'T') {
                    throw new IllegalArgumentException();
                }
                i = 9;
                while (i < 15) {
                    if (Character.isDigit(str.charAt(i))) {
                        i++;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
                if (length == 16 && str.charAt(15) != 'Z') {
                    throw new IllegalArgumentException();
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    public String getAttendee() {
        return this.attendee;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(100);
        ParsedResult.maybeAppend(this.summary, stringBuffer);
        ParsedResult.maybeAppend(this.start, stringBuffer);
        ParsedResult.maybeAppend(this.end, stringBuffer);
        ParsedResult.maybeAppend(this.location, stringBuffer);
        ParsedResult.maybeAppend(this.attendee, stringBuffer);
        ParsedResult.maybeAppend(this.description, stringBuffer);
        return stringBuffer.toString();
    }

    public String getEnd() {
        return this.end;
    }

    public String getLocation() {
        return this.location;
    }

    public String getStart() {
        return this.start;
    }

    public String getSummary() {
        return this.summary;
    }
}
