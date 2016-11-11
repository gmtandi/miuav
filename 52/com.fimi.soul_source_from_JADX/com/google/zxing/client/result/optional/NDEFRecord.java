package com.google.zxing.client.result.optional;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.smile.SmileConstants;

final class NDEFRecord {
    public static final String ACTION_WELL_KNOWN_TYPE = "act";
    public static final String SMART_POSTER_WELL_KNOWN_TYPE = "Sp";
    private static final int SUPPORTED_HEADER = 17;
    private static final int SUPPORTED_HEADER_MASK = 63;
    public static final String TEXT_WELL_KNOWN_TYPE = "T";
    public static final String URI_WELL_KNOWN_TYPE = "U";
    private final int header;
    private final byte[] payload;
    private final int totalRecordLength;
    private final String type;

    private NDEFRecord(int i, String str, byte[] bArr, int i2) {
        this.header = i;
        this.type = str;
        this.payload = bArr;
        this.totalRecordLength = i2;
    }

    static NDEFRecord readRecord(byte[] bArr, int i) {
        int i2 = bArr[i] & Util.MASK_8BIT;
        if (((i2 ^ SUPPORTED_HEADER) & SUPPORTED_HEADER_MASK) != 0) {
            return null;
        }
        int i3 = bArr[i + 1] & Util.MASK_8BIT;
        int i4 = bArr[i + 2] & Util.MASK_8BIT;
        String bytesToString = AbstractNDEFResultParser.bytesToString(bArr, i + 3, i3, "US-ASCII");
        Object obj = new byte[i4];
        System.arraycopy(bArr, (i + 3) + i3, obj, 0, i4);
        return new NDEFRecord(i2, bytesToString, obj, (i3 + 3) + i4);
    }

    byte[] getPayload() {
        return this.payload;
    }

    int getTotalRecordLength() {
        return this.totalRecordLength;
    }

    String getType() {
        return this.type;
    }

    boolean isMessageBegin() {
        return (this.header & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != 0;
    }

    boolean isMessageEnd() {
        return (this.header & 64) != 0;
    }
}
