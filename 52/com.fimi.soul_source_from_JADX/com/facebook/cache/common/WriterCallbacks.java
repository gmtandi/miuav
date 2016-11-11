package com.facebook.cache.common;

import com.facebook.common.internal.ByteStreams;
import java.io.InputStream;
import java.io.OutputStream;

public class WriterCallbacks {

    /* renamed from: com.facebook.cache.common.WriterCallbacks.1 */
    final class C09621 implements WriterCallback {
        final /* synthetic */ InputStream val$is;

        C09621(InputStream inputStream) {
            this.val$is = inputStream;
        }

        public void write(OutputStream outputStream) {
            ByteStreams.copy(this.val$is, outputStream);
        }
    }

    /* renamed from: com.facebook.cache.common.WriterCallbacks.2 */
    final class C09632 implements WriterCallback {
        final /* synthetic */ byte[] val$data;

        C09632(byte[] bArr) {
            this.val$data = bArr;
        }

        public void write(OutputStream outputStream) {
            outputStream.write(this.val$data);
        }
    }

    public static WriterCallback from(InputStream inputStream) {
        return new C09621(inputStream);
    }

    public static WriterCallback from(byte[] bArr) {
        return new C09632(bArr);
    }
}
