package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.URIParsedResult;
import com.tencent.mm.sdk.platformtools.Util;

final class NDEFURIResultParser extends AbstractNDEFResultParser {
    private static final String[] URI_PREFIXES;

    static {
        URI_PREFIXES = new String[]{null, "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"};
    }

    NDEFURIResultParser() {
    }

    static String decodeURIPayload(byte[] bArr) {
        int i = bArr[0] & Util.MASK_8BIT;
        String str = null;
        if (i < URI_PREFIXES.length) {
            str = URI_PREFIXES[i];
        }
        String bytesToString = AbstractNDEFResultParser.bytesToString(bArr, 1, bArr.length - 1, "UTF8");
        return str == null ? bytesToString : new StringBuffer().append(str).append(bytesToString).toString();
    }

    public static URIParsedResult parse(Result result) {
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null) {
            return null;
        }
        NDEFRecord readRecord = NDEFRecord.readRecord(rawBytes, 0);
        return (readRecord != null && readRecord.isMessageBegin() && readRecord.isMessageEnd() && readRecord.getType().equals(NDEFRecord.URI_WELL_KNOWN_TYPE)) ? new URIParsedResult(decodeURIPayload(readRecord.getPayload()), null) : null;
    }
}
