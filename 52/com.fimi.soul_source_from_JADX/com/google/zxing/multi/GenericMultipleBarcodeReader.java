package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.Hashtable;
import java.util.Vector;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable, Vector vector, int i, int i2) {
        try {
            Object obj;
            Result decode = this.delegate.decode(binaryBitmap, hashtable);
            for (int i3 = 0; i3 < vector.size(); i3++) {
                if (((Result) vector.elementAt(i3)).getText().equals(decode.getText())) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                vector.addElement(translateResultPoints(decode, i, i2));
                ResultPoint[] resultPoints = decode.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    int width = binaryBitmap.getWidth();
                    int height = binaryBitmap.getHeight();
                    int i4 = 0;
                    float f = 0.0f;
                    float f2 = 0.0f;
                    float f3 = (float) height;
                    float f4 = (float) width;
                    while (i4 < resultPoints.length) {
                        ResultPoint resultPoint = resultPoints[i4];
                        float x = resultPoint.getX();
                        float y = resultPoint.getY();
                        float f5 = x < f4 ? x : f4;
                        float f6 = y < f3 ? y : f3;
                        if (x <= f2) {
                            x = f2;
                        }
                        i4++;
                        f = y > f ? y : f;
                        f2 = x;
                        f3 = f6;
                        f4 = f5;
                    }
                    if (f4 > 100.0f) {
                        doDecodeMultiple(binaryBitmap.crop(0, 0, (int) f4, height), hashtable, vector, i, i2);
                    }
                    if (f3 > 100.0f) {
                        doDecodeMultiple(binaryBitmap.crop(0, 0, width, (int) f3), hashtable, vector, i, i2);
                    }
                    if (f2 < ((float) (width - 100))) {
                        doDecodeMultiple(binaryBitmap.crop((int) f2, 0, width - ((int) f2), height), hashtable, vector, i + ((int) f2), i2);
                    }
                    if (f < ((float) (height - 100))) {
                        doDecodeMultiple(binaryBitmap.crop(0, (int) f, width, height - ((int) f)), hashtable, vector, i, i2 + ((int) f));
                    }
                }
            }
        } catch (ReaderException e) {
        }
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
        }
        return new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        int i = 0;
        Vector vector = new Vector();
        doDecodeMultiple(binaryBitmap, hashtable, vector, 0, 0);
        if (vector.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        int size = vector.size();
        Result[] resultArr = new Result[size];
        while (i < size) {
            resultArr[i] = (Result) vector.elementAt(i);
            i++;
        }
        return resultArr;
    }
}
