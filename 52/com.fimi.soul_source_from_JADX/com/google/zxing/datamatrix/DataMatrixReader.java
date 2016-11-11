package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import java.util.Hashtable;

public final class DataMatrixReader implements Reader {
    private static final ResultPoint[] NO_POINTS;
    private final Decoder decoder;

    static {
        NO_POINTS = new ResultPoint[0];
    }

    public DataMatrixReader() {
        this.decoder = new Decoder();
    }

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        int min = Math.min(height, width);
        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        if (topLeftOnBit == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = topLeftOnBit[0];
        int i2 = topLeftOnBit[1];
        int i3 = i;
        while (i3 < min && i2 < min && bitMatrix.get(i3, i2)) {
            i3++;
        }
        if (i3 == min) {
            throw NotFoundException.getNotFoundInstance();
        }
        min = i3 - topLeftOnBit[0];
        i = width - 1;
        while (i >= 0 && !bitMatrix.get(i, i2)) {
            i--;
        }
        if (i < 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        i++;
        if ((i - i3) % min != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i4 = ((i - i3) / min) + 2;
        i = i2 + min;
        i2 = i3 - (min >> 1);
        int i5 = i - (min >> 1);
        if (((i4 - 1) * min) + i2 >= width || ((i4 - 1) * min) + i5 >= height) {
            throw NotFoundException.getNotFoundInstance();
        }
        BitMatrix bitMatrix2 = new BitMatrix(i4);
        for (i3 = 0; i3 < i4; i3++) {
            width = i5 + (i3 * min);
            for (i = 0; i < i4; i++) {
                if (bitMatrix.get((i * min) + i2, width)) {
                    bitMatrix2.set(i, i3);
                }
            }
        }
        return bitMatrix2;
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        return decode(binaryBitmap, null);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        DecoderResult decode;
        ResultPoint[] points;
        if (hashtable == null || !hashtable.containsKey(DecodeHintType.PURE_BARCODE)) {
            DetectorResult detect = new Detector(binaryBitmap.getBlackMatrix()).detect();
            decode = this.decoder.decode(detect.getBits());
            points = detect.getPoints();
        } else {
            decode = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()));
            points = NO_POINTS;
        }
        Result result = new Result(decode.getText(), decode.getRawBytes(), points, BarcodeFormat.DATA_MATRIX);
        if (decode.getByteSegments() != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decode.getByteSegments());
        }
        if (decode.getECLevel() != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decode.getECLevel().toString());
        }
        return result;
    }

    public void reset() {
    }
}
