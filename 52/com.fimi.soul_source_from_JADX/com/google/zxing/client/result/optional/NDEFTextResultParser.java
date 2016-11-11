package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.TextParsedResult;
import org.codehaus.jackson.smile.SmileConstants;

final class NDEFTextResultParser extends AbstractNDEFResultParser {
    NDEFTextResultParser() {
    }

    static String[] decodeTextPayload(byte[] bArr) {
        byte b = bArr[0];
        int i = (b & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0 ? 1 : 0;
        int i2 = b & 31;
        String bytesToString = AbstractNDEFResultParser.bytesToString(bArr, 1, i2, "US-ASCII");
        String bytesToString2 = AbstractNDEFResultParser.bytesToString(bArr, i2 + 1, (bArr.length - i2) - 1, i != 0 ? "UTF-16" : "UTF8");
        return new String[]{bytesToString, bytesToString2};
    }

    public static TextParsedResult parse(Result result) {
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null) {
            return null;
        }
        NDEFRecord readRecord = NDEFRecord.readRecord(rawBytes, 0);
        if (readRecord == null || !readRecord.isMessageBegin() || !readRecord.isMessageEnd() || !readRecord.getType().equals(NDEFRecord.TEXT_WELL_KNOWN_TYPE)) {
            return null;
        }
        String[] decodeTextPayload = decodeTextPayload(readRecord.getPayload());
        return new TextParsedResult(decodeTextPayload[0], decodeTextPayload[1]);
    }
}
