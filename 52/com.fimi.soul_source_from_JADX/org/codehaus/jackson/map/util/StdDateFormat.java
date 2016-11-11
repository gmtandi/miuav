package org.codehaus.jackson.map.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.p122a.p123a.p124f.p125c.C3022o;

public class StdDateFormat extends DateFormat {
    static final String[] ALL_FORMATS;
    static final SimpleDateFormat DATE_FORMAT_ISO8601;
    static final SimpleDateFormat DATE_FORMAT_ISO8601_Z;
    static final SimpleDateFormat DATE_FORMAT_PLAIN;
    static final SimpleDateFormat DATE_FORMAT_RFC1123;
    static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final StdDateFormat instance;
    transient SimpleDateFormat _formatISO8601;
    transient SimpleDateFormat _formatISO8601_z;
    transient SimpleDateFormat _formatPlain;
    transient SimpleDateFormat _formatRFC1123;

    static {
        ALL_FORMATS = new String[]{DATE_FORMAT_STR_ISO8601, DATE_FORMAT_STR_ISO8601_Z, DATE_FORMAT_STR_RFC1123, DATE_FORMAT_STR_PLAIN};
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        DATE_FORMAT_RFC1123 = new SimpleDateFormat(DATE_FORMAT_STR_RFC1123);
        DATE_FORMAT_RFC1123.setTimeZone(timeZone);
        DATE_FORMAT_ISO8601 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601);
        DATE_FORMAT_ISO8601.setTimeZone(timeZone);
        DATE_FORMAT_ISO8601_Z = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z);
        DATE_FORMAT_ISO8601_Z.setTimeZone(timeZone);
        DATE_FORMAT_PLAIN = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN);
        DATE_FORMAT_PLAIN.setTimeZone(timeZone);
        instance = new StdDateFormat();
    }

    public static DateFormat getBlueprintISO8601Format() {
        return DATE_FORMAT_ISO8601;
    }

    public static DateFormat getBlueprintRFC1123Format() {
        return DATE_FORMAT_RFC1123;
    }

    public static DateFormat getISO8601Format(TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_ISO8601.clone();
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat;
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_RFC1123.clone();
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat;
    }

    private static final boolean hasTimeZone(String str) {
        int length = str.length();
        if (length >= 6) {
            char charAt = str.charAt(length - 6);
            if (charAt == SignatureVisitor.EXTENDS || charAt == SignatureVisitor.SUPER) {
                return true;
            }
            charAt = str.charAt(length - 5);
            if (charAt == SignatureVisitor.EXTENDS || charAt == SignatureVisitor.SUPER) {
                return true;
            }
            char charAt2 = str.charAt(length - 3);
            if (charAt2 == SignatureVisitor.EXTENDS || charAt2 == SignatureVisitor.SUPER) {
                return true;
            }
        }
        return false;
    }

    public StdDateFormat clone() {
        return new StdDateFormat();
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = (SimpleDateFormat) DATE_FORMAT_ISO8601.clone();
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    protected boolean looksLikeISO8601(String str) {
        return str.length() >= 5 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == SignatureVisitor.SUPER;
    }

    public Date parse(String str) {
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = parse(trim, parsePosition);
        if (parse != null) {
            return parse;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\", \"");
            } else {
                stringBuilder.append(C3022o.f15057e);
            }
            stringBuilder.append(str2);
        }
        stringBuilder.append(C3022o.f15057e);
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[]{trim, stringBuilder.toString()}), parsePosition.getErrorIndex());
    }

    public Date parse(String str, ParsePosition parsePosition) {
        if (looksLikeISO8601(str)) {
            return parseAsISO8601(str, parsePosition);
        }
        int length = str.length();
        char charAt;
        do {
            length--;
            if (length < 0) {
                break;
            }
            charAt = str.charAt(length);
            if (charAt < '0') {
                break;
            }
        } while (charAt <= '9');
        return (length >= 0 || !NumberInput.inLongRange(str, false)) ? parseAsRFC1123(str, parsePosition) : new Date(Long.parseLong(str));
    }

    protected Date parseAsISO8601(String str, ParsePosition parsePosition) {
        SimpleDateFormat simpleDateFormat;
        int length = str.length();
        char charAt = str.charAt(length - 1);
        if (length <= 10 && Character.isDigit(charAt)) {
            simpleDateFormat = this._formatPlain;
            if (simpleDateFormat == null) {
                simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_PLAIN.clone();
                this._formatPlain = simpleDateFormat;
            }
        } else if (charAt == 'Z') {
            simpleDateFormat = this._formatISO8601_z;
            if (simpleDateFormat == null) {
                simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_ISO8601_Z.clone();
                this._formatISO8601_z = simpleDateFormat;
            }
            if (str.charAt(length - 4) == ':') {
                StringBuilder stringBuilder = new StringBuilder(str);
                stringBuilder.insert(length - 1, ".000");
                str = stringBuilder.toString();
            }
        } else if (hasTimeZone(str)) {
            charAt = str.charAt(length - 3);
            if (charAt == ':') {
                r0 = new StringBuilder(str);
                r0.delete(length - 3, length - 2);
                str = r0.toString();
            } else if (charAt == SignatureVisitor.EXTENDS || charAt == SignatureVisitor.SUPER) {
                str = str + "00";
            }
            int length2 = str.length();
            if (Character.isDigit(str.charAt(length2 - 9))) {
                StringBuilder stringBuilder2 = new StringBuilder(str);
                stringBuilder2.insert(length2 - 5, ".000");
                str = stringBuilder2.toString();
            }
            simpleDateFormat = this._formatISO8601;
            if (this._formatISO8601 == null) {
                simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_ISO8601.clone();
                this._formatISO8601 = simpleDateFormat;
            }
        } else {
            r0 = new StringBuilder(str);
            if ((length - str.lastIndexOf(84)) - 1 <= 8) {
                r0.append(".000");
            }
            r0.append('Z');
            str = r0.toString();
            simpleDateFormat = this._formatISO8601_z;
            if (simpleDateFormat == null) {
                simpleDateFormat = (SimpleDateFormat) DATE_FORMAT_ISO8601_Z.clone();
                this._formatISO8601_z = simpleDateFormat;
            }
        }
        return simpleDateFormat.parse(str, parsePosition);
    }

    protected Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = (SimpleDateFormat) DATE_FORMAT_RFC1123.clone();
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }
}
