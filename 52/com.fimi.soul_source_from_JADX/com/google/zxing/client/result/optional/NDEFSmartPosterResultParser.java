package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.tencent.open.SocialConstants;

final class NDEFSmartPosterResultParser extends AbstractNDEFResultParser {
    NDEFSmartPosterResultParser() {
    }

    public static NDEFSmartPosterParsedResult parse(Result result) {
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null) {
            return null;
        }
        NDEFRecord readRecord = NDEFRecord.readRecord(rawBytes, 0);
        if (readRecord == null || !readRecord.isMessageBegin() || !readRecord.isMessageEnd() || !readRecord.getType().equals(NDEFRecord.SMART_POSTER_WELL_KNOWN_TYPE)) {
            return null;
        }
        byte[] payload = readRecord.getPayload();
        int i = -1;
        String str = null;
        String str2 = null;
        NDEFRecord nDEFRecord = null;
        int i2 = 0;
        int i3 = 0;
        while (i3 < payload.length) {
            nDEFRecord = NDEFRecord.readRecord(payload, i3);
            if (nDEFRecord == null) {
                break;
            } else if (i2 == 0 && !nDEFRecord.isMessageBegin()) {
                return null;
            } else {
                String type = nDEFRecord.getType();
                if (NDEFRecord.TEXT_WELL_KNOWN_TYPE.equals(type)) {
                    str2 = NDEFTextResultParser.decodeTextPayload(nDEFRecord.getPayload())[1];
                } else if (NDEFRecord.URI_WELL_KNOWN_TYPE.equals(type)) {
                    str = NDEFURIResultParser.decodeURIPayload(nDEFRecord.getPayload());
                } else if (SocialConstants.PARAM_ACT.equals(type)) {
                    i = nDEFRecord.getPayload()[0];
                }
                i2++;
                i3 += nDEFRecord.getTotalRecordLength();
            }
        }
        return i2 != 0 ? (nDEFRecord == null || nDEFRecord.isMessageEnd()) ? new NDEFSmartPosterParsedResult(i, str, str2) : null : null;
    }
}
