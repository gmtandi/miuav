package com.google.zxing;

import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatReader implements Reader {
    private Hashtable hints;
    private Vector readers;

    private Result decodeInternal(BinaryBitmap binaryBitmap) {
        int i = 0;
        while (i < this.readers.size()) {
            try {
                return ((Reader) this.readers.elementAt(i)).decode(binaryBitmap, this.hints);
            } catch (ReaderException e) {
                i++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) {
        setHints(null);
        return decodeInternal(binaryBitmap);
    }

    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) {
        setHints(hashtable);
        return decodeInternal(binaryBitmap);
    }

    public Result decodeWithState(BinaryBitmap binaryBitmap) {
        if (this.readers == null) {
            setHints(null);
        }
        return decodeInternal(binaryBitmap);
    }

    public void reset() {
        int size = this.readers.size();
        for (int i = 0; i < size; i++) {
            ((Reader) this.readers.elementAt(i)).reset();
        }
    }

    public void setHints(Hashtable hashtable) {
        Object obj = null;
        this.hints = hashtable;
        Object obj2 = (hashtable == null || !hashtable.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
        Vector vector = hashtable == null ? null : (Vector) hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        this.readers = new Vector();
        if (vector != null) {
            if (vector.contains(BarcodeFormat.UPC_A) || vector.contains(BarcodeFormat.UPC_E) || vector.contains(BarcodeFormat.EAN_13) || vector.contains(BarcodeFormat.EAN_8) || vector.contains(BarcodeFormat.CODE_39) || vector.contains(BarcodeFormat.CODE_93) || vector.contains(BarcodeFormat.CODE_128) || vector.contains(BarcodeFormat.ITF) || vector.contains(BarcodeFormat.RSS14) || vector.contains(BarcodeFormat.RSS_EXPANDED)) {
                obj = 1;
            }
            if (obj != null && obj2 == null) {
                this.readers.addElement(new MultiFormatOneDReader(hashtable));
            }
            if (vector.contains(BarcodeFormat.QR_CODE)) {
                this.readers.addElement(new QRCodeReader());
            }
            if (vector.contains(BarcodeFormat.DATA_MATRIX)) {
                this.readers.addElement(new DataMatrixReader());
            }
            if (vector.contains(BarcodeFormat.PDF417)) {
                this.readers.addElement(new PDF417Reader());
            }
            if (!(obj == null || obj2 == null)) {
                this.readers.addElement(new MultiFormatOneDReader(hashtable));
            }
        }
        if (this.readers.isEmpty()) {
            if (obj2 == null) {
                this.readers.addElement(new MultiFormatOneDReader(hashtable));
            }
            this.readers.addElement(new QRCodeReader());
            this.readers.addElement(new DataMatrixReader());
            if (obj2 != null) {
                this.readers.addElement(new MultiFormatOneDReader(hashtable));
            }
        }
    }
}
