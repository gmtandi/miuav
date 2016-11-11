package com.google.zxing.client.result.optional;

import com.google.zxing.client.result.ResultParser;
import java.io.UnsupportedEncodingException;

abstract class AbstractNDEFResultParser extends ResultParser {
    AbstractNDEFResultParser() {
    }

    static String bytesToString(byte[] bArr, int i, int i2, String str) {
        try {
            return new String(bArr, i, i2, str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(new StringBuffer().append("Platform does not support required encoding: ").append(e).toString());
        }
    }
}
