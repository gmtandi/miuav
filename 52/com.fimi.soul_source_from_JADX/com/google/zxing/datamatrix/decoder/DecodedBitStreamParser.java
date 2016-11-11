package com.google.zxing.datamatrix.decoder;

import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p124f.p125c.C3022o;

final class DecodedBitStreamParser {
    private static final int ANSIX12_ENCODE = 4;
    private static final int ASCII_ENCODE = 1;
    private static final int BASE256_ENCODE = 6;
    private static final char[] C40_BASIC_SET_CHARS;
    private static final int C40_ENCODE = 2;
    private static final char[] C40_SHIFT2_SET_CHARS;
    private static final int EDIFACT_ENCODE = 5;
    private static final int PAD_ENCODE = 0;
    private static final char[] TEXT_BASIC_SET_CHARS;
    private static final int TEXT_ENCODE = 3;
    private static final char[] TEXT_SHIFT3_SET_CHARS;

    static {
        C40_BASIC_SET_CHARS = new char[]{'*', '*', '*', C3022o.f15055c, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        C40_SHIFT2_SET_CHARS = new char[]{'!', C3022o.f15057e, '#', '$', '%', '&', '\'', '(', ')', '*', SignatureVisitor.EXTENDS, ',', SignatureVisitor.SUPER, '.', '/', ':', ';', '<', SignatureVisitor.INSTANCEOF, '>', '?', '@', '[', C3022o.f15058f, ']', '^', '_'};
        TEXT_BASIC_SET_CHARS = new char[]{'*', '*', '*', C3022o.f15055c, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        TEXT_SHIFT3_SET_CHARS = new char[]{'\'', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', '\u007f'};
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr) {
        String stringBuffer;
        BitSource bitSource = new BitSource(bArr);
        StringBuffer stringBuffer2 = new StringBuffer(100);
        StringBuffer stringBuffer3 = new StringBuffer(PAD_ENCODE);
        Vector vector = new Vector(ASCII_ENCODE);
        int i = ASCII_ENCODE;
        do {
            if (i == ASCII_ENCODE) {
                i = decodeAsciiSegment(bitSource, stringBuffer2, stringBuffer3);
            } else {
                switch (i) {
                    case C40_ENCODE /*2*/:
                        decodeC40Segment(bitSource, stringBuffer2);
                        break;
                    case TEXT_ENCODE /*3*/:
                        decodeTextSegment(bitSource, stringBuffer2);
                        break;
                    case ANSIX12_ENCODE /*4*/:
                        decodeAnsiX12Segment(bitSource, stringBuffer2);
                        break;
                    case EDIFACT_ENCODE /*5*/:
                        decodeEdifactSegment(bitSource, stringBuffer2);
                        break;
                    case BASE256_ENCODE /*6*/:
                        decodeBase256Segment(bitSource, stringBuffer2, vector);
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
                i = ASCII_ENCODE;
            }
            if (i != 0) {
            }
            if (stringBuffer3.length() > 0) {
                stringBuffer2.append(stringBuffer3.toString());
            }
            stringBuffer = stringBuffer2.toString();
            if (vector.isEmpty()) {
                vector = null;
            }
            return new DecoderResult(bArr, stringBuffer, vector, null);
        } while (bitSource.available() > 0);
        if (stringBuffer3.length() > 0) {
            stringBuffer2.append(stringBuffer3.toString());
        }
        stringBuffer = stringBuffer2.toString();
        if (vector.isEmpty()) {
            vector = null;
        }
        return new DecoderResult(bArr, stringBuffer, vector, null);
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuffer stringBuffer) {
        int[] iArr = new int[TEXT_ENCODE];
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != C1465c.f6998a) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                for (readBits = PAD_ENCODE; readBits < TEXT_ENCODE; readBits += ASCII_ENCODE) {
                    int i = iArr[readBits];
                    if (i == 0) {
                        stringBuffer.append(C3022o.f15053a);
                    } else if (i == ASCII_ENCODE) {
                        stringBuffer.append('*');
                    } else if (i == C40_ENCODE) {
                        stringBuffer.append('>');
                    } else if (i == TEXT_ENCODE) {
                        stringBuffer.append(C3022o.f15055c);
                    } else if (i < 14) {
                        stringBuffer.append((char) (i + 44));
                    } else if (i < 40) {
                        stringBuffer.append((char) (i + 51));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (bitSource.available() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static int decodeAsciiSegment(BitSource bitSource, StringBuffer stringBuffer, StringBuffer stringBuffer2) {
        int i = PAD_ENCODE;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                stringBuffer.append((char) ((i != 0 ? readBits + SmileConstants.TOKEN_PREFIX_TINY_UNICODE : readBits) - 1));
                return ASCII_ENCODE;
            } else if (readBits == Opcodes.LOR) {
                return PAD_ENCODE;
            } else {
                if (readBits <= 229) {
                    readBits -= 130;
                    if (readBits < 10) {
                        stringBuffer.append('0');
                    }
                    stringBuffer.append(readBits);
                } else if (readBits == C2799f.f14256F) {
                    return C40_ENCODE;
                } else {
                    if (readBits == 231) {
                        return BASE256_ENCODE;
                    }
                    if (!(readBits == SmileConstants.TOKEN_MISC_BINARY_7BIT || readBits == 233 || readBits == 234)) {
                        if (readBits == 235) {
                            i = ASCII_ENCODE;
                        } else if (readBits == SmileConstants.TOKEN_MISC_SHARED_STRING_LONG) {
                            stringBuffer.append("[)>\u001e05\u001d");
                            stringBuffer2.insert(PAD_ENCODE, "\u001e\u0004");
                        } else if (readBits == 237) {
                            stringBuffer.append("[)>\u001e06\u001d");
                            stringBuffer2.insert(PAD_ENCODE, "\u001e\u0004");
                        } else if (readBits == 238) {
                            return ANSIX12_ENCODE;
                        } else {
                            if (readBits == 239) {
                                return TEXT_ENCODE;
                            }
                            if (readBits == 240) {
                                return EDIFACT_ENCODE;
                            }
                            if (readBits != 241 && readBits >= 242) {
                                throw FormatException.getFormatInstance();
                            }
                        }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return ASCII_ENCODE;
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuffer stringBuffer, Vector vector) {
        int readBits = bitSource.readBits(8);
        if (readBits == 0) {
            readBits = bitSource.available() / 8;
        } else if (readBits >= C2799f.f14257G) {
            readBits = ((readBits - 249) * C2799f.f14257G) + bitSource.readBits(8);
        }
        Object obj = new byte[readBits];
        for (int i = PAD_ENCODE; i < readBits; i += ASCII_ENCODE) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            obj[i] = unrandomize255State(bitSource.readBits(8), i);
        }
        vector.addElement(obj);
        try {
            stringBuffer.append(new String(obj, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(new StringBuffer().append("Platform does not support required encoding: ").append(e).toString());
        }
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuffer stringBuffer) {
        int[] iArr = new int[TEXT_ENCODE];
        void v = PAD_ENCODE;
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != C1465c.f6998a) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                int i = PAD_ENCODE;
                readBits = PAD_ENCODE;
                while (i < TEXT_ENCODE) {
                    int i2;
                    Object obj;
                    int i3;
                    int i4 = iArr[i];
                    switch (readBits) {
                        case PAD_ENCODE /*0*/:
                            if (i4 >= TEXT_ENCODE) {
                                if (v == null) {
                                    stringBuffer.append(C40_BASIC_SET_CHARS[i4]);
                                    i2 = readBits;
                                    obj = v;
                                    i3 = i2;
                                    break;
                                }
                                stringBuffer.append((char) (C40_BASIC_SET_CHARS[i4] + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                i3 = readBits;
                                obj = PAD_ENCODE;
                                break;
                            }
                            obj = v;
                            i3 = i4 + ASCII_ENCODE;
                            break;
                        case ASCII_ENCODE /*1*/:
                            if (v != null) {
                                stringBuffer.append((char) (i4 + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                v = PAD_ENCODE;
                            } else {
                                stringBuffer.append(i4);
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        case C40_ENCODE /*2*/:
                            if (i4 < 27) {
                                if (v != null) {
                                    stringBuffer.append((char) (C40_SHIFT2_SET_CHARS[i4] + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                    v = PAD_ENCODE;
                                } else {
                                    stringBuffer.append(C40_SHIFT2_SET_CHARS[i4]);
                                }
                            } else if (i4 == 27) {
                                throw FormatException.getFormatInstance();
                            } else if (i4 == 30) {
                                v = ASCII_ENCODE;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        case TEXT_ENCODE /*3*/:
                            if (v != null) {
                                stringBuffer.append((char) (i4 + SmileConstants.TOKEN_PREFIX_MISC_OTHER));
                                v = PAD_ENCODE;
                            } else {
                                stringBuffer.append((char) (i4 + 96));
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                    i += ASCII_ENCODE;
                    i2 = i3;
                    v = obj;
                    readBits = i2;
                }
                if (bitSource.available() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuffer stringBuffer) {
        void v = PAD_ENCODE;
        while (bitSource.available() > 16) {
            int i = PAD_ENCODE;
            while (i < ANSIX12_ENCODE) {
                int readBits = bitSource.readBits(BASE256_ENCODE);
                Object obj = readBits == 11111 ? ASCII_ENCODE : v;
                if (obj == null) {
                    stringBuffer.append((readBits & 32) == 0 ? readBits | 64 : readBits);
                }
                i += ASCII_ENCODE;
                v = obj;
            }
            if (v != null) {
                return;
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuffer stringBuffer) {
        int[] iArr = new int[TEXT_ENCODE];
        void v = PAD_ENCODE;
        while (bitSource.available() != 8) {
            int readBits = bitSource.readBits(8);
            if (readBits != C1465c.f6998a) {
                parseTwoBytes(readBits, bitSource.readBits(8), iArr);
                int i = PAD_ENCODE;
                readBits = PAD_ENCODE;
                while (i < TEXT_ENCODE) {
                    int i2;
                    Object obj;
                    int i3;
                    int i4 = iArr[i];
                    switch (readBits) {
                        case PAD_ENCODE /*0*/:
                            if (i4 >= TEXT_ENCODE) {
                                if (v == null) {
                                    stringBuffer.append(TEXT_BASIC_SET_CHARS[i4]);
                                    i2 = readBits;
                                    obj = v;
                                    i3 = i2;
                                    break;
                                }
                                stringBuffer.append((char) (TEXT_BASIC_SET_CHARS[i4] + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                i3 = readBits;
                                obj = PAD_ENCODE;
                                break;
                            }
                            obj = v;
                            i3 = i4 + ASCII_ENCODE;
                            break;
                        case ASCII_ENCODE /*1*/:
                            if (v != null) {
                                stringBuffer.append((char) (i4 + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                v = PAD_ENCODE;
                            } else {
                                stringBuffer.append(i4);
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        case C40_ENCODE /*2*/:
                            if (i4 < 27) {
                                if (v != null) {
                                    stringBuffer.append((char) (C40_SHIFT2_SET_CHARS[i4] + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                    v = PAD_ENCODE;
                                } else {
                                    stringBuffer.append(C40_SHIFT2_SET_CHARS[i4]);
                                }
                            } else if (i4 == 27) {
                                throw FormatException.getFormatInstance();
                            } else if (i4 == 30) {
                                v = ASCII_ENCODE;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        case TEXT_ENCODE /*3*/:
                            if (v != null) {
                                stringBuffer.append((char) (TEXT_SHIFT3_SET_CHARS[i4] + SmileConstants.TOKEN_PREFIX_TINY_UNICODE));
                                v = PAD_ENCODE;
                            } else {
                                stringBuffer.append(TEXT_SHIFT3_SET_CHARS[i4]);
                            }
                            obj = v;
                            i3 = PAD_ENCODE;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                    i += ASCII_ENCODE;
                    i2 = i3;
                    v = obj;
                    readBits = i2;
                }
                if (bitSource.available() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[PAD_ENCODE] = i4;
        i3 -= i4 * 1600;
        i4 = i3 / 40;
        iArr[ASCII_ENCODE] = i4;
        iArr[C40_ENCODE] = i3 - (i4 * 40);
    }

    private static byte unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * Opcodes.FCMPL) % Util.MASK_8BIT) + ASCII_ENCODE);
        if (i3 < 0) {
            i3 += Opcodes.ACC_NATIVE;
        }
        return (byte) i3;
    }
}
