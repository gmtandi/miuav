package com.facebook.common.util;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {

    /* renamed from: com.facebook.common.util.StreamUtil.1 */
    final class C09741 extends ByteArrayOutputStream {
        C09741(int i) {
            super(i);
        }

        public byte[] toByteArray() {
            return this.count == this.buf.length ? this.buf : super.toByteArray();
        }
    }

    public static byte[] getBytesFromStream(InputStream inputStream) {
        return getBytesFromStream(inputStream, inputStream.available());
    }

    public static byte[] getBytesFromStream(InputStream inputStream, int i) {
        OutputStream c09741 = new C09741(i);
        ByteStreams.copy(inputStream, c09741);
        return c09741.toByteArray();
    }

    public static long skip(InputStream inputStream, long j) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkArgument(j >= 0);
        long j2 = j;
        while (j2 > 0) {
            long skip = inputStream.skip(j2);
            if (skip > 0) {
                j2 -= skip;
            } else if (inputStream.read() == -1) {
                return j - j2;
            } else {
                j2--;
            }
        }
        return j;
    }
}
