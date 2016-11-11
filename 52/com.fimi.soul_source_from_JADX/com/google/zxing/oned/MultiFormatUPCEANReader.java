package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final Vector readers;

    public MultiFormatUPCEANReader(Hashtable hashtable) {
        Vector vector = hashtable == null ? null : (Vector) hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        this.readers = new Vector();
        if (vector != null) {
            if (vector.contains(BarcodeFormat.EAN_13)) {
                this.readers.addElement(new EAN13Reader());
            } else if (vector.contains(BarcodeFormat.UPC_A)) {
                this.readers.addElement(new UPCAReader());
            }
            if (vector.contains(BarcodeFormat.EAN_8)) {
                this.readers.addElement(new EAN8Reader());
            }
            if (vector.contains(BarcodeFormat.UPC_E)) {
                this.readers.addElement(new UPCEReader());
            }
        }
        if (this.readers.isEmpty()) {
            this.readers.addElement(new EAN13Reader());
            this.readers.addElement(new EAN8Reader());
            this.readers.addElement(new UPCEReader());
        }
    }

    public Result decodeRow(int i, BitArray bitArray, Hashtable hashtable) {
        int[] findStartGuardPattern = UPCEANReader.findStartGuardPattern(bitArray);
        int size = this.readers.size();
        int i2 = 0;
        while (i2 < size) {
            try {
                Result decodeRow = ((UPCEANReader) this.readers.elementAt(i2)).decodeRow(i, bitArray, findStartGuardPattern, hashtable);
                int i3 = (BarcodeFormat.EAN_13.equals(decodeRow.getBarcodeFormat()) && decodeRow.getText().charAt(0) == '0') ? 1 : 0;
                Vector vector = hashtable == null ? null : (Vector) hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
                int i4 = (vector == null || vector.contains(BarcodeFormat.UPC_A)) ? 1 : 0;
                return (i3 == 0 || i4 == 0) ? decodeRow : new Result(decodeRow.getText().substring(1), null, decodeRow.getResultPoints(), BarcodeFormat.UPC_A);
            } catch (ReaderException e) {
                i2++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        int size = this.readers.size();
        for (int i = 0; i < size; i++) {
            ((Reader) this.readers.elementAt(i)).reset();
        }
    }
}
