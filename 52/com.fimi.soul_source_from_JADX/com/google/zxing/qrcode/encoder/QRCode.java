package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import it.p074a.p075a.C2799f;

public final class QRCode {
    public static final int NUM_MASK_PATTERNS = 8;
    private ErrorCorrectionLevel ecLevel;
    private int maskPattern;
    private ByteMatrix matrix;
    private int matrixWidth;
    private Mode mode;
    private int numDataBytes;
    private int numECBytes;
    private int numRSBlocks;
    private int numTotalBytes;
    private int version;

    public QRCode() {
        this.mode = null;
        this.ecLevel = null;
        this.version = -1;
        this.matrixWidth = -1;
        this.maskPattern = -1;
        this.numTotalBytes = -1;
        this.numDataBytes = -1;
        this.numECBytes = -1;
        this.numRSBlocks = -1;
        this.matrix = null;
    }

    public static boolean isValidMaskPattern(int i) {
        return i >= 0 && i < NUM_MASK_PATTERNS;
    }

    public int at(int i, int i2) {
        byte b = this.matrix.get(i, i2);
        if (b == null || b == 1) {
            return b;
        }
        throw new RuntimeException("Bad value");
    }

    public ErrorCorrectionLevel getECLevel() {
        return this.ecLevel;
    }

    public int getMaskPattern() {
        return this.maskPattern;
    }

    public ByteMatrix getMatrix() {
        return this.matrix;
    }

    public int getMatrixWidth() {
        return this.matrixWidth;
    }

    public Mode getMode() {
        return this.mode;
    }

    public int getNumDataBytes() {
        return this.numDataBytes;
    }

    public int getNumECBytes() {
        return this.numECBytes;
    }

    public int getNumRSBlocks() {
        return this.numRSBlocks;
    }

    public int getNumTotalBytes() {
        return this.numTotalBytes;
    }

    public int getVersion() {
        return this.version;
    }

    public boolean isValid() {
        return (this.mode == null || this.ecLevel == null || this.version == -1 || this.matrixWidth == -1 || this.maskPattern == -1 || this.numTotalBytes == -1 || this.numDataBytes == -1 || this.numECBytes == -1 || this.numRSBlocks == -1 || !isValidMaskPattern(this.maskPattern) || this.numTotalBytes != this.numDataBytes + this.numECBytes || this.matrix == null || this.matrixWidth != this.matrix.getWidth() || this.matrix.getWidth() != this.matrix.getHeight()) ? false : true;
    }

    public void setECLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.ecLevel = errorCorrectionLevel;
    }

    public void setMaskPattern(int i) {
        this.maskPattern = i;
    }

    public void setMatrix(ByteMatrix byteMatrix) {
        this.matrix = byteMatrix;
    }

    public void setMatrixWidth(int i) {
        this.matrixWidth = i;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setNumDataBytes(int i) {
        this.numDataBytes = i;
    }

    public void setNumECBytes(int i) {
        this.numECBytes = i;
    }

    public void setNumRSBlocks(int i) {
        this.numRSBlocks = i;
    }

    public void setNumTotalBytes(int i) {
        this.numTotalBytes = i;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(C2799f.f14282t);
        stringBuffer.append("<<\n");
        stringBuffer.append(" mode: ");
        stringBuffer.append(this.mode);
        stringBuffer.append("\n ecLevel: ");
        stringBuffer.append(this.ecLevel);
        stringBuffer.append("\n version: ");
        stringBuffer.append(this.version);
        stringBuffer.append("\n matrixWidth: ");
        stringBuffer.append(this.matrixWidth);
        stringBuffer.append("\n maskPattern: ");
        stringBuffer.append(this.maskPattern);
        stringBuffer.append("\n numTotalBytes: ");
        stringBuffer.append(this.numTotalBytes);
        stringBuffer.append("\n numDataBytes: ");
        stringBuffer.append(this.numDataBytes);
        stringBuffer.append("\n numECBytes: ");
        stringBuffer.append(this.numECBytes);
        stringBuffer.append("\n numRSBlocks: ");
        stringBuffer.append(this.numRSBlocks);
        if (this.matrix == null) {
            stringBuffer.append("\n matrix: null\n");
        } else {
            stringBuffer.append("\n matrix:\n");
            stringBuffer.append(this.matrix.toString());
        }
        stringBuffer.append(">>\n");
        return stringBuffer.toString();
    }
}
