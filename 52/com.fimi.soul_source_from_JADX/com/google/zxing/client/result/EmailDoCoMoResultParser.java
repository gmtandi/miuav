package com.google.zxing.client.result;

import com.google.zxing.Result;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;

final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final char[] ATEXT_SYMBOLS;

    static {
        ATEXT_SYMBOLS = new char[]{'@', '.', '!', '#', '$', '%', '&', '\'', '*', SignatureVisitor.EXTENDS, SignatureVisitor.SUPER, '/', SignatureVisitor.INSTANCEOF, '?', '^', '_', '`', '{', '|', '}', '~'};
    }

    EmailDoCoMoResultParser() {
    }

    private static boolean isAtextSymbol(char c) {
        for (char c2 : ATEXT_SYMBOLS) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    static boolean isBasicallyValidEmailAddress(String str) {
        if (str == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && ((charAt < '0' || charAt > '9') && !isAtextSymbol(charAt)))) {
                return false;
            }
            if (charAt == '@') {
                if (z) {
                    return false;
                }
                z = true;
            }
        }
        return z;
    }

    public static EmailAddressParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("MATMSG:")) {
            return null;
        }
        String[] matchDoCoMoPrefixedField = AbstractDoCoMoResultParser.matchDoCoMoPrefixedField("TO:", text, true);
        if (matchDoCoMoPrefixedField == null) {
            return null;
        }
        String str = matchDoCoMoPrefixedField[0];
        return isBasicallyValidEmailAddress(str) ? new EmailAddressParsedResult(str, AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("SUB:", text, false), AbstractDoCoMoResultParser.matchSingleDoCoMoPrefixedField("BODY:", text, false), new StringBuffer().append("mailto:").append(str).toString()) : null;
    }
}
