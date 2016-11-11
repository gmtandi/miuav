package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.InputStream;
import org.codehaus.jackson.smile.SmileConstants;

public final class GifFormatChecker {
    private static final byte[] FRAME_HEADER_END_1;
    private static final byte[] FRAME_HEADER_END_2;
    private static final int FRAME_HEADER_SIZE = 10;
    private static final byte[] FRAME_HEADER_START;

    static {
        FRAME_HEADER_START = new byte[]{(byte) 0, SmileConstants.TOKEN_LITERAL_NULL, (byte) -7, (byte) 4};
        FRAME_HEADER_END_1 = new byte[]{(byte) 0, (byte) 44};
        FRAME_HEADER_END_2 = new byte[]{(byte) 0, SmileConstants.TOKEN_LITERAL_NULL};
    }

    private GifFormatChecker() {
    }

    @VisibleForTesting
    static boolean circularBufferMatchesBytePattern(byte[] bArr, int i, byte[] bArr2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(bArr2);
        Preconditions.checkArgument(i >= 0);
        if (bArr2.length > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[(i2 + i) % bArr.length] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnimated(InputStream inputStream) {
        byte[] bArr = new byte[FRAME_HEADER_SIZE];
        try {
            inputStream.read(bArr, 0, FRAME_HEADER_SIZE);
            int i = 0;
            int i2 = 0;
            while (inputStream.read(bArr, i2, 1) > 0) {
                if (circularBufferMatchesBytePattern(bArr, i2 + 1, FRAME_HEADER_START) && (circularBufferMatchesBytePattern(bArr, i2 + 9, FRAME_HEADER_END_1) || circularBufferMatchesBytePattern(bArr, i2 + 9, FRAME_HEADER_END_2))) {
                    i++;
                    if (i > 1) {
                        return true;
                    }
                }
                i2 = (i2 + 1) % bArr.length;
            }
            return false;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
