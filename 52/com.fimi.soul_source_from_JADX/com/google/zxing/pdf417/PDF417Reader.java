package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;

public final class PDF417Reader implements Reader {
    private static final ResultPoint[] NO_POINTS;
    private final Decoder decoder;

    static {
        NO_POINTS = new ResultPoint[0];
    }

    public PDF417Reader() {
        this.decoder = new Decoder();
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        DecoderResult decode;
        ResultPoint[] points;
        if (hashtable == null || !hashtable.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap).detect();
            decode = this.decoder.decode(detect.getBits());
            points = detect.getPoints();
        } else {
            decode = this.decoder.decode(QRCodeReader.extractPureBits(binaryBitmap.getBlackMatrix()));
            points = NO_POINTS;
        }
        return new Result(decode.getText(), decode.getRawBytes(), points, BarcodeFormat.PDF417);
    }

    public void reset() {
    }
}
