package org.p122a.p123a.p178k;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.message.BufferedHeader;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.k.d */
public class C3212d implements LineParser {
    @Deprecated
    public static final C3212d f15665a;
    public static final C3212d f15666b;
    protected final ProtocolVersion f15667c;

    static {
        f15665a = new C3212d();
        f15666b = new C3212d();
    }

    public C3212d() {
        this(null);
    }

    public C3212d(ProtocolVersion protocolVersion) {
        if (protocolVersion == null) {
            protocolVersion = HttpVersion.HTTP_1_1;
        }
        this.f15667c = protocolVersion;
    }

    public static ProtocolVersion m17817a(String str, LineParser lineParser) {
        C3234a.m17886a((Object) str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (lineParser == null) {
            lineParser = f15666b;
        }
        return lineParser.parseProtocolVersion(charArrayBuffer, parserCursor);
    }

    public static RequestLine m17818b(String str, LineParser lineParser) {
        C3234a.m17886a((Object) str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (lineParser == null) {
            lineParser = f15666b;
        }
        return lineParser.parseRequestLine(charArrayBuffer, parserCursor);
    }

    public static StatusLine m17819c(String str, LineParser lineParser) {
        C3234a.m17886a((Object) str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (lineParser == null) {
            lineParser = f15666b;
        }
        return lineParser.parseStatusLine(charArrayBuffer, parserCursor);
    }

    public static Header m17820d(String str, LineParser lineParser) {
        C3234a.m17886a((Object) str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        if (lineParser == null) {
            lineParser = f15666b;
        }
        return lineParser.parseHeader(charArrayBuffer);
    }

    protected ProtocolVersion m17821a(int i, int i2) {
        return this.f15667c.forVersion(i, i2);
    }

    protected RequestLine m17822a(String str, String str2, ProtocolVersion protocolVersion) {
        return new BasicRequestLine(str, str2, protocolVersion);
    }

    protected StatusLine m17823a(ProtocolVersion protocolVersion, int i, String str) {
        return new BasicStatusLine(protocolVersion, i, str);
    }

    protected void m17824a(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos < upperBound && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
            pos++;
        }
        parserCursor.updatePos(pos);
    }

    public boolean hasProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        boolean z = true;
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        C3234a.m17886a((Object) parserCursor, "Parser cursor");
        int pos = parserCursor.getPos();
        String protocol = this.f15667c.getProtocol();
        int length = protocol.length();
        if (charArrayBuffer.length() < length + 4) {
            return false;
        }
        if (pos < 0) {
            pos = (charArrayBuffer.length() - 4) - length;
        } else if (pos == 0) {
            while (pos < charArrayBuffer.length() && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
                pos++;
            }
        }
        if ((pos + length) + 4 > charArrayBuffer.length()) {
            return false;
        }
        int i = 0;
        boolean z2 = true;
        while (z2 && i < length) {
            z2 = charArrayBuffer.charAt(pos + i) == protocol.charAt(i);
            i++;
        }
        if (!z2) {
            z = z2;
        } else if (charArrayBuffer.charAt(pos + length) != '/') {
            z = false;
        }
        return z;
    }

    public Header parseHeader(CharArrayBuffer charArrayBuffer) {
        return new BufferedHeader(charArrayBuffer);
    }

    public ProtocolVersion parseProtocolVersion(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Object obj = 1;
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        C3234a.m17886a((Object) parserCursor, "Parser cursor");
        String protocol = this.f15667c.getProtocol();
        int length = protocol.length();
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        m17824a(charArrayBuffer, parserCursor);
        int pos2 = parserCursor.getPos();
        if ((pos2 + length) + 4 > upperBound) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring(pos, upperBound));
        }
        int i = 0;
        Object obj2 = 1;
        while (obj2 != null && i < length) {
            obj2 = charArrayBuffer.charAt(pos2 + i) == protocol.charAt(i) ? 1 : null;
            i++;
        }
        if (obj2 == null) {
            obj = obj2;
        } else if (charArrayBuffer.charAt(pos2 + length) != '/') {
            obj = null;
        }
        if (obj == null) {
            throw new ParseException("Not a valid protocol version: " + charArrayBuffer.substring(pos, upperBound));
        }
        int i2 = (length + 1) + pos2;
        int indexOf = charArrayBuffer.indexOf(46, i2, upperBound);
        if (indexOf == -1) {
            throw new ParseException("Invalid protocol version number: " + charArrayBuffer.substring(pos, upperBound));
        }
        try {
            int parseInt = Integer.parseInt(charArrayBuffer.substringTrimmed(i2, indexOf));
            indexOf++;
            i2 = charArrayBuffer.indexOf(32, indexOf, upperBound);
            if (i2 == -1) {
                i2 = upperBound;
            }
            try {
                indexOf = Integer.parseInt(charArrayBuffer.substringTrimmed(indexOf, i2));
                parserCursor.updatePos(i2);
                return m17821a(parseInt, indexOf);
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid protocol minor version number: " + charArrayBuffer.substring(pos, upperBound));
            }
        } catch (NumberFormatException e2) {
            throw new ParseException("Invalid protocol major version number: " + charArrayBuffer.substring(pos, upperBound));
        }
    }

    public RequestLine parseRequestLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        C3234a.m17886a((Object) parserCursor, "Parser cursor");
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        try {
            m17824a(charArrayBuffer, parserCursor);
            int pos2 = parserCursor.getPos();
            int indexOf = charArrayBuffer.indexOf(32, pos2, upperBound);
            if (indexOf < 0) {
                throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
            }
            String substringTrimmed = charArrayBuffer.substringTrimmed(pos2, indexOf);
            parserCursor.updatePos(indexOf);
            m17824a(charArrayBuffer, parserCursor);
            indexOf = parserCursor.getPos();
            int indexOf2 = charArrayBuffer.indexOf(32, indexOf, upperBound);
            if (indexOf2 < 0) {
                throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
            }
            String substringTrimmed2 = charArrayBuffer.substringTrimmed(indexOf, indexOf2);
            parserCursor.updatePos(indexOf2);
            ProtocolVersion parseProtocolVersion = parseProtocolVersion(charArrayBuffer, parserCursor);
            m17824a(charArrayBuffer, parserCursor);
            if (parserCursor.atEnd()) {
                return m17822a(substringTrimmed, substringTrimmed2, parseProtocolVersion);
            }
            throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid request line: " + charArrayBuffer.substring(pos, upperBound));
        }
    }

    public StatusLine parseStatusLine(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        C3234a.m17886a((Object) charArrayBuffer, "Char array buffer");
        C3234a.m17886a((Object) parserCursor, "Parser cursor");
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        try {
            ProtocolVersion parseProtocolVersion = parseProtocolVersion(charArrayBuffer, parserCursor);
            m17824a(charArrayBuffer, parserCursor);
            int pos2 = parserCursor.getPos();
            int indexOf = charArrayBuffer.indexOf(32, pos2, upperBound);
            int i = indexOf < 0 ? upperBound : indexOf;
            String substringTrimmed = charArrayBuffer.substringTrimmed(pos2, i);
            indexOf = 0;
            while (indexOf < substringTrimmed.length()) {
                if (Character.isDigit(substringTrimmed.charAt(indexOf))) {
                    indexOf++;
                } else {
                    throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.substring(pos, upperBound));
                }
            }
            return m17823a(parseProtocolVersion, Integer.parseInt(substringTrimmed), i < upperBound ? charArrayBuffer.substringTrimmed(i, upperBound) : C2915a.f14760f);
        } catch (NumberFormatException e) {
            throw new ParseException("Status line contains invalid status code: " + charArrayBuffer.substring(pos, upperBound));
        } catch (IndexOutOfBoundsException e2) {
            throw new ParseException("Invalid status line: " + charArrayBuffer.substring(pos, upperBound));
        }
    }
}
