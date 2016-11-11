package com.google.zxing.multi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {
    private static final Result[] EMPTY_RESULT_ARRAY;

    static {
        EMPTY_RESULT_ARRAY = new Result[0];
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        int i = 0;
        Vector vector = new Vector();
        DetectorResult[] detectMulti = new MultiDetector(binaryBitmap.getBlackMatrix()).detectMulti(hashtable);
        for (int i2 = 0; i2 < detectMulti.length; i2++) {
            try {
                DecoderResult decode = getDecoder().decode(detectMulti[i2].getBits());
                Result result = new Result(decode.getText(), decode.getRawBytes(), detectMulti[i2].getPoints(), BarcodeFormat.QR_CODE);
                if (decode.getByteSegments() != null) {
                    result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decode.getByteSegments());
                }
                if (decode.getECLevel() != null) {
                    result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decode.getECLevel().toString());
                }
                vector.addElement(result);
            } catch (ReaderException e) {
            }
        }
        if (vector.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        Result[] resultArr = new Result[vector.size()];
        while (i < vector.size()) {
            resultArr[i] = (Result) vector.elementAt(i);
            i++;
        }
        return resultArr;
    }
}
