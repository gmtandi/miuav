package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.tencent.mm.sdk.contact.RContact;
import java.util.Hashtable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

final class UPCEANExtensionSupport {
    private static final int[] CHECK_DIGIT_ENCODINGS;
    private static final int[] EXTENSION_START_PATTERN;
    private final int[] decodeMiddleCounters;
    private final StringBuffer decodeRowStringBuffer;

    static {
        EXTENSION_START_PATTERN = new int[]{1, 1, 2};
        CHECK_DIGIT_ENCODINGS = new int[]{24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    }

    UPCEANExtensionSupport() {
        this.decodeMiddleCounters = new int[4];
        this.decodeRowStringBuffer = new StringBuffer();
    }

    private static int determineCheckDigit(int i) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == CHECK_DIGIT_ENCODINGS[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        for (i = length - 2; i >= 0; i -= 2) {
            i2 += str.charAt(i) - 48;
        }
        i2 *= 3;
        for (i = length - 1; i >= 0; i -= 2) {
            i2 += str.charAt(i) - 48;
        }
        return (i2 * 3) % 10;
    }

    private static Integer parseExtension2String(String str) {
        return Integer.valueOf(str);
    }

    private static String parseExtension5String(String str) {
        String str2 = null;
        switch (str.charAt(0)) {
            case SmileConstants.TOKEN_PREFIX_KEY_SHARED_LONG /*48*/:
                str2 = "\u62e2";
                break;
            case Opcodes.SALOAD /*53*/:
                str2 = RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR;
                break;
            case Opcodes.DSTORE /*57*/:
                if ("99991".equals(str)) {
                    return "0.00";
                }
                if ("99990".equals(str)) {
                    return "Used";
                }
                break;
            default:
                str2 = C2915a.f14760f;
                break;
        }
        int parseInt = Integer.parseInt(str.substring(1));
        return new StringBuffer().append(str2).append(parseInt / 100).append('.').append(parseInt % 100).toString();
    }

    private static Hashtable parseExtensionString(String str) {
        Object obj;
        Object parseExtension2String;
        switch (str.length()) {
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                obj = ResultMetadataType.ISSUE_NUMBER;
                parseExtension2String = parseExtension2String(str);
                break;
            case Type.INT /*5*/:
                obj = ResultMetadataType.SUGGESTED_PRICE;
                parseExtension2String = parseExtension5String(str);
                break;
            default:
                return null;
        }
        if (parseExtension2String == null) {
            return null;
        }
        Hashtable hashtable = new Hashtable(1);
        hashtable.put(obj, parseExtension2String);
        return hashtable;
    }

    int decodeMiddle(BitArray bitArray, int[] iArr, StringBuffer stringBuffer) {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 5 && i < size) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, UPCEANReader.L_AND_G_PATTERNS);
            stringBuffer.append((char) ((decodeDigit % 10) + 48));
            int i4 = i;
            for (int i5 : iArr2) {
                i4 += i5;
            }
            i = decodeDigit >= 10 ? (1 << (4 - i2)) | i3 : i3;
            if (i2 != 4) {
                while (i4 < size && !bitArray.get(i4)) {
                    i4++;
                }
                while (i4 < size && bitArray.get(i4)) {
                    i4++;
                }
            }
            i2++;
            i3 = i;
            i = i4;
        }
        if (stringBuffer.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (extensionChecksum(stringBuffer.toString()) == determineCheckDigit(i3)) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    Result decodeRow(int i, BitArray bitArray, int i2) {
        int[] findGuardPattern = UPCEANReader.findGuardPattern(bitArray, i2, false, EXTENSION_START_PATTERN);
        StringBuffer stringBuffer = this.decodeRowStringBuffer;
        stringBuffer.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, findGuardPattern, stringBuffer);
        String stringBuffer2 = stringBuffer.toString();
        Hashtable parseExtensionString = parseExtensionString(stringBuffer2);
        r6 = new ResultPoint[2];
        r6[0] = new ResultPoint(((float) (findGuardPattern[1] + findGuardPattern[0])) / 2.0f, (float) i);
        r6[1] = new ResultPoint((float) decodeMiddle, (float) i);
        Result result = new Result(stringBuffer2, null, r6, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }
}
