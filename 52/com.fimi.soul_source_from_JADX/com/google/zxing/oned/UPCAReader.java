package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.FormatException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class UPCAReader extends UPCEANReader {
    private final UPCEANReader ean13Reader;

    public UPCAReader() {
        this.ean13Reader = new EAN13Reader();
    }

    private static Result maybeReturnResult(Result result) {
        String text = result.getText();
        if (text.charAt(0) == '0') {
            return new Result(text.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
        }
        throw FormatException.getFormatInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap));
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        return maybeReturnResult(this.ean13Reader.decode(binaryBitmap, hashtable));
    }

    protected int decodeMiddle(BitArray bitArray, int[] iArr, StringBuffer stringBuffer) {
        return this.ean13Reader.decodeMiddle(bitArray, iArr, stringBuffer);
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, hashtable));
    }

    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Hashtable hashtable) {
        return maybeReturnResult(this.ean13Reader.decodeRow(i, bitArray, iArr, hashtable));
    }

    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_A;
    }
}
