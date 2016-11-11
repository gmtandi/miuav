package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public abstract class AbstractExpandedDecoder {
    protected final GeneralAppIdDecoder generalDecoder;
    protected final BitArray information;

    AbstractExpandedDecoder(BitArray bitArray) {
        this.information = bitArray;
        this.generalDecoder = new GeneralAppIdDecoder(bitArray);
    }

    public static AbstractExpandedDecoder createDecoder(BitArray bitArray) {
        if (bitArray.get(1)) {
            return new AI01AndOtherAIs(bitArray);
        }
        if (!bitArray.get(2)) {
            return new AnyAIDecoder(bitArray);
        }
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 4)) {
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return new AI013103decoder(bitArray);
            case Type.INT /*5*/:
                return new AI01320xDecoder(bitArray);
            default:
                switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 5)) {
                    case Opcodes.FCONST_1 /*12*/:
                        return new AI01392xDecoder(bitArray);
                    case Opcodes.FCONST_2 /*13*/:
                        return new AI01393xDecoder(bitArray);
                    default:
                        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 7)) {
                            case SmileConstants.MAX_SHORT_NAME_UNICODE_BYTES /*56*/:
                                return new AI013x0x1xDecoder(bitArray, "310", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
                            case Opcodes.DSTORE /*57*/:
                                return new AI013x0x1xDecoder(bitArray, "320", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE);
                            case Opcodes.ASTORE /*58*/:
                                return new AI013x0x1xDecoder(bitArray, "310", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
                            case UsbId.ARDUINO_SERIAL_ADAPTER /*59*/:
                                return new AI013x0x1xDecoder(bitArray, "320", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
                            case GameAppOperation.SHARE_PRIZE_SUMMARY_MAX_LENGTH /*60*/:
                                return new AI013x0x1xDecoder(bitArray, "310", Constants.VIA_REPORT_TYPE_WPA_STATE);
                            case 61:
                                return new AI013x0x1xDecoder(bitArray, "320", Constants.VIA_REPORT_TYPE_WPA_STATE);
                            case 62:
                                return new AI013x0x1xDecoder(bitArray, "310", "17");
                            case UsbId.ARDUINO_MEGA_ADK /*63*/:
                                return new AI013x0x1xDecoder(bitArray, "320", "17");
                            default:
                                throw new IllegalStateException(new StringBuffer().append("unknown decoder: ").append(bitArray).toString());
                        }
                }
        }
    }

    public abstract String parseInformation();
}
