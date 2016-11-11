package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import java.util.Hashtable;

public interface MultipleBarcodeReader {
    Result[] decodeMultiple(BinaryBitmap binaryBitmap);

    Result[] decodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable);
}
