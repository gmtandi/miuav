package com.google.zxing;

import java.util.Hashtable;

public interface Reader {
    Result decode(BinaryBitmap binaryBitmap);

    Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable);

    void reset();
}
