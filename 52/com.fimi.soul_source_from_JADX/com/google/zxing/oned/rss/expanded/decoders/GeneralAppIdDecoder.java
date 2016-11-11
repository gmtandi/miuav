package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.open.GameAppOperation;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p124f.p125c.C3022o;

final class GeneralAppIdDecoder {
    private final StringBuffer buffer;
    private final CurrentParsingState current;
    private final BitArray information;

    GeneralAppIdDecoder(BitArray bitArray) {
        this.current = new CurrentParsingState();
        this.buffer = new StringBuffer();
        this.information = bitArray;
    }

    private DecodedChar decodeAlphanumeric(int i) {
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, '$');
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 6);
        if (extractNumericValueFromBitArray >= 32 && extractNumericValueFromBitArray < 58) {
            return new DecodedChar(i + 6, (char) (extractNumericValueFromBitArray + 33));
        }
        switch (extractNumericValueFromBitArray) {
            case Opcodes.ASTORE /*58*/:
                return new DecodedChar(i + 6, '*');
            case UsbId.ARDUINO_SERIAL_ADAPTER /*59*/:
                return new DecodedChar(i + 6, ',');
            case GameAppOperation.SHARE_PRIZE_SUMMARY_MAX_LENGTH /*60*/:
                return new DecodedChar(i + 6, SignatureVisitor.SUPER);
            case 61:
                return new DecodedChar(i + 6, '.');
            case 62:
                return new DecodedChar(i + 6, '/');
            default:
                throw new RuntimeException(new StringBuffer().append("Decoding invalid alphanumeric value: ").append(extractNumericValueFromBitArray).toString());
        }
    }

    private DecodedChar decodeIsoIec646(int i) {
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, '$');
        }
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((extractNumericValueFromBitArray + 48) - 5));
        }
        extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 7);
        if (extractNumericValueFromBitArray >= 64 && extractNumericValueFromBitArray < 90) {
            return new DecodedChar(i + 7, (char) (extractNumericValueFromBitArray + 1));
        }
        if (extractNumericValueFromBitArray >= 90 && extractNumericValueFromBitArray < Opcodes.INEG) {
            return new DecodedChar(i + 7, (char) (extractNumericValueFromBitArray + 7));
        }
        int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 8);
        switch (extractNumericValueFromBitArray2) {
            case SmileConstants.TOKEN_MISC_BINARY_7BIT /*232*/:
                return new DecodedChar(i + 8, '!');
            case 233:
                return new DecodedChar(i + 8, C3022o.f15057e);
            case 234:
                return new DecodedChar(i + 8, '%');
            case 235:
                return new DecodedChar(i + 8, '&');
            case SmileConstants.TOKEN_MISC_SHARED_STRING_LONG /*236*/:
                return new DecodedChar(i + 8, '\'');
            case 237:
                return new DecodedChar(i + 8, '(');
            case 238:
                return new DecodedChar(i + 8, ')');
            case 239:
                return new DecodedChar(i + 8, '*');
            case 240:
                return new DecodedChar(i + 8, SignatureVisitor.EXTENDS);
            case 241:
                return new DecodedChar(i + 8, ',');
            case 242:
                return new DecodedChar(i + 8, SignatureVisitor.SUPER);
            case 243:
                return new DecodedChar(i + 8, '.');
            case 244:
                return new DecodedChar(i + 8, '/');
            case 245:
                return new DecodedChar(i + 8, ':');
            case 246:
                return new DecodedChar(i + 8, ';');
            case 247:
                return new DecodedChar(i + 8, '<');
            case 248:
                return new DecodedChar(i + 8, SignatureVisitor.INSTANCEOF);
            case 249:
                return new DecodedChar(i + 8, '>');
            case C2799f.f14257G /*250*/:
                return new DecodedChar(i + 8, '?');
            case 251:
                return new DecodedChar(i + 8, '_');
            case SmileConstants.INT_MARKER_END_OF_STRING /*252*/:
                return new DecodedChar(i + 8, C3022o.f15055c);
            default:
                throw new RuntimeException(new StringBuffer().append("Decoding invalid ISO/IEC 646 value: ").append(extractNumericValueFromBitArray2).toString());
        }
    }

    private DecodedNumeric decodeNumeric(int i) {
        if (i + 7 > this.information.size) {
            int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 4);
            return extractNumericValueFromBitArray == 0 ? new DecodedNumeric(this.information.size, 10, 10) : new DecodedNumeric(this.information.size, extractNumericValueFromBitArray - 1, 10);
        } else {
            int extractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 7);
            return new DecodedNumeric(i + 7, (extractNumericValueFromBitArray2 - 8) / 11, (extractNumericValueFromBitArray2 - 8) % 11);
        }
    }

    static int extractNumericValueFromBitArray(BitArray bitArray, int i, int i2) {
        int i3 = 0;
        if (i2 > 32) {
            throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (bitArray.get(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    private boolean isAlphaOr646ToNumericLatch(int i) {
        if (i + 3 > this.information.size) {
            return false;
        }
        for (int i2 = i; i2 < i + 3; i2++) {
            if (this.information.get(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlphaTo646ToAlphaLatch(int i) {
        if (i + 1 > this.information.size) {
            return false;
        }
        int i2 = 0;
        while (i2 < 5 && i2 + i < this.information.size) {
            if (i2 == 2) {
                if (!this.information.get(i + 2)) {
                    return false;
                }
            } else if (this.information.get(i + i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int i) {
        if (i + 1 > this.information.size) {
            return false;
        }
        int i2 = 0;
        while (i2 < 4 && i2 + i < this.information.size) {
            if (this.information.get(i + i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean isStillAlpha(int i) {
        boolean z = true;
        if (i + 5 > this.information.size) {
            return false;
        }
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 16) {
            return true;
        }
        if (i + 6 > this.information.size) {
            return false;
        }
        extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 6);
        if (extractNumericValueFromBitArray < 16 || extractNumericValueFromBitArray >= 63) {
            z = false;
        }
        return z;
    }

    private boolean isStillIsoIec646(int i) {
        boolean z = true;
        if (i + 5 > this.information.size) {
            return false;
        }
        int extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (extractNumericValueFromBitArray >= 5 && extractNumericValueFromBitArray < 16) {
            return true;
        }
        if (i + 7 > this.information.size) {
            return false;
        }
        extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 7);
        if (extractNumericValueFromBitArray >= 64 && extractNumericValueFromBitArray < Opcodes.INEG) {
            return true;
        }
        if (i + 8 > this.information.size) {
            return false;
        }
        extractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 8);
        if (extractNumericValueFromBitArray < SmileConstants.TOKEN_MISC_BINARY_7BIT || extractNumericValueFromBitArray >= SmileConstants.TOKEN_MISC_BINARY_RAW) {
            z = false;
        }
        return z;
    }

    private boolean isStillNumeric(int i) {
        if (i + 7 > this.information.size) {
            return i + 4 <= this.information.size;
        } else {
            for (int i2 = i; i2 < i + 3; i2++) {
                if (this.information.get(i2)) {
                    return true;
                }
            }
            return this.information.get(i + 3);
        }
    }

    private BlockParsedResult parseAlphaBlock() {
        while (isStillAlpha(this.current.position)) {
            DecodedChar decodeAlphanumeric = decodeAlphanumeric(this.current.position);
            this.current.position = decodeAlphanumeric.getNewPosition();
            if (decodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeAlphanumeric.getValue());
        }
        CurrentParsingState currentParsingState;
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            currentParsingState = this.current;
            currentParsingState.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                currentParsingState.position += 5;
            } else {
                this.current.position = this.information.size;
            }
            this.current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private DecodedInformation parseBlocks() {
        BlockParsedResult parseAlphaBlock;
        boolean isFinished;
        do {
            int i = this.current.position;
            if (this.current.isAlpha()) {
                parseAlphaBlock = parseAlphaBlock();
                isFinished = parseAlphaBlock.isFinished();
            } else if (this.current.isIsoIec646()) {
                parseAlphaBlock = parseIsoIec646Block();
                isFinished = parseAlphaBlock.isFinished();
            } else {
                parseAlphaBlock = parseNumericBlock();
                isFinished = parseAlphaBlock.isFinished();
            }
            if ((i != this.current.position ? 1 : null) == null && !isFinished) {
                break;
            }
        } while (!isFinished);
        return parseAlphaBlock.getDecodedInformation();
    }

    private BlockParsedResult parseIsoIec646Block() {
        while (isStillIsoIec646(this.current.position)) {
            DecodedChar decodeIsoIec646 = decodeIsoIec646(this.current.position);
            this.current.position = decodeIsoIec646.getNewPosition();
            if (decodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeIsoIec646.getValue());
        }
        CurrentParsingState currentParsingState;
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            currentParsingState = this.current;
            currentParsingState.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                currentParsingState.position += 5;
            } else {
                this.current.position = this.information.size;
            }
            this.current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseNumericBlock() {
        while (isStillNumeric(this.current.position)) {
            DecodedNumeric decodeNumeric = decodeNumeric(this.current.position);
            this.current.position = decodeNumeric.getNewPosition();
            if (decodeNumeric.isFirstDigitFNC1()) {
                return new BlockParsedResult(decodeNumeric.isSecondDigitFNC1() ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), decodeNumeric.getSecondDigit()), true);
            }
            this.buffer.append(decodeNumeric.getFirstDigit());
            if (decodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodeNumeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.position)) {
            this.current.setAlpha();
            CurrentParsingState currentParsingState = this.current;
            currentParsingState.position += 4;
        }
        return new BlockParsedResult(false);
    }

    String decodeAllCodes(StringBuffer stringBuffer, int i) {
        String str = null;
        while (true) {
            DecodedInformation decodeGeneralPurposeField = decodeGeneralPurposeField(i, str);
            stringBuffer.append(FieldParser.parseFieldsInGeneralPurpose(decodeGeneralPurposeField.getNewString()));
            str = decodeGeneralPurposeField.isRemaining() ? String.valueOf(decodeGeneralPurposeField.getRemainingValue()) : null;
            if (i == decodeGeneralPurposeField.getNewPosition()) {
                return stringBuffer.toString();
            }
            i = decodeGeneralPurposeField.getNewPosition();
        }
    }

    DecodedInformation decodeGeneralPurposeField(int i, String str) {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.position = i;
        DecodedInformation parseBlocks = parseBlocks();
        return (parseBlocks == null || !parseBlocks.isRemaining()) ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), parseBlocks.getRemainingValue());
    }

    int extractNumericValueFromBitArray(int i, int i2) {
        return extractNumericValueFromBitArray(this.information, i, i2);
    }
}
