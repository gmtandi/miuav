package com.google.zxing.common;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Vector;

public final class DecoderResult {
    private final Vector byteSegments;
    private final ErrorCorrectionLevel ecLevel;
    private final byte[] rawBytes;
    private final String text;

    public DecoderResult(byte[] bArr, String str, Vector vector, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr == null && str == null) {
            throw new IllegalArgumentException();
        }
        this.rawBytes = bArr;
        this.text = str;
        this.byteSegments = vector;
        this.ecLevel = errorCorrectionLevel;
    }

    public Vector getByteSegments() {
        return this.byteSegments;
    }

    public ErrorCorrectionLevel getECLevel() {
        return this.ecLevel;
    }

    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    public String getText() {
        return this.text;
    }
}
