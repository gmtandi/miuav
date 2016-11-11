package com.xiaomi.infra.galaxy.fds.android.util;

import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import java.io.FilterInputStream;
import java.io.InputStream;

public class ObjectInputStream extends FilterInputStream {
    private long lastNotifyTime;
    private final ProgressListener listener;
    private final ObjectMetadata metadata;
    private long totalBytesRead;

    public ObjectInputStream(InputStream inputStream, ObjectMetadata objectMetadata, ProgressListener progressListener) {
        super(inputStream);
        this.metadata = objectMetadata;
        this.listener = progressListener;
    }

    private void notifyListener(boolean z) {
        if (this.listener != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!z || currentTimeMillis - this.lastNotifyTime >= this.listener.progressInterval()) {
                this.lastNotifyTime = currentTimeMillis;
                this.listener.onProgress(this.totalBytesRead, this.metadata.getContentLength());
            }
        }
    }

    public void close() {
        super.close();
        notifyListener(false);
    }

    public int read() {
        int read = super.read();
        if (read != -1) {
            this.totalBytesRead++;
            notifyListener(true);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.totalBytesRead += (long) read;
            notifyListener(true);
        }
        return read;
    }

    public synchronized void reset() {
        super.reset();
        this.totalBytesRead = 0;
        notifyListener(true);
    }
}
