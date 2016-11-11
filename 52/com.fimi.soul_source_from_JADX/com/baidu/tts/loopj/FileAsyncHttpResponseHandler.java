package com.baidu.tts.loopj;

import android.content.Context;
import java.io.File;
import org.apache.http.Header;

public abstract class FileAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    private static final String LOG_TAG = "FileAsyncHttpRH";
    protected final boolean append;
    protected final File file;
    protected File frontendFile;
    protected final boolean renameIfExists;

    public FileAsyncHttpResponseHandler(Context context) {
        this.file = getTemporaryFile(context);
        this.append = false;
        this.renameIfExists = false;
    }

    public FileAsyncHttpResponseHandler(File file) {
        this(file, false);
    }

    public FileAsyncHttpResponseHandler(File file, boolean z) {
        this(file, z, false);
    }

    public FileAsyncHttpResponseHandler(File file, boolean z, boolean z2) {
        Utils.asserts(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!(file.isDirectory() || file.getParentFile().isDirectory())) {
            Utils.asserts(file.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file.isDirectory() && !file.mkdirs()) {
            AsyncHttpClient.log.m6888d(LOG_TAG, "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.file = file;
        this.append = z;
        this.renameIfExists = z2;
    }

    public boolean deleteTargetFile() {
        return getTargetFile() != null && getTargetFile().delete();
    }

    protected File getOriginalFile() {
        Utils.asserts(this.file != null, "Target file is null, fatal!");
        return this.file;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected byte[] getResponseData(org.apache.http.HttpEntity r9) {
        /*
        r8 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x004b;
    L_0x0003:
        r1 = r9.getContent();
        r2 = r9.getContentLength();
        r4 = new java.io.FileOutputStream;
        r5 = r8.getTargetFile();
        r6 = r8.append;
        r4.<init>(r5, r6);
        if (r1 == 0) goto L_0x004b;
    L_0x0018:
        r5 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r5 = new byte[r5];	 Catch:{ all -> 0x0037 }
    L_0x001c:
        r6 = r1.read(r5);	 Catch:{ all -> 0x0037 }
        r7 = -1;
        if (r6 == r7) goto L_0x0042;
    L_0x0023:
        r7 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0037 }
        r7 = r7.isInterrupted();	 Catch:{ all -> 0x0037 }
        if (r7 != 0) goto L_0x0042;
    L_0x002d:
        r0 = r0 + r6;
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ all -> 0x0037 }
        r6 = (long) r0;	 Catch:{ all -> 0x0037 }
        r8.sendProgressMessage(r6, r2);	 Catch:{ all -> 0x0037 }
        goto L_0x001c;
    L_0x0037:
        r0 = move-exception;
        com.baidu.tts.loopj.AsyncHttpClient.silentCloseInputStream(r1);
        r4.flush();
        com.baidu.tts.loopj.AsyncHttpClient.silentCloseOutputStream(r4);
        throw r0;
    L_0x0042:
        com.baidu.tts.loopj.AsyncHttpClient.silentCloseInputStream(r1);
        r4.flush();
        com.baidu.tts.loopj.AsyncHttpClient.silentCloseOutputStream(r4);
    L_0x004b:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.loopj.FileAsyncHttpResponseHandler.getResponseData(org.apache.http.HttpEntity):byte[]");
    }

    public File getTargetFile() {
        if (this.frontendFile == null) {
            this.frontendFile = getOriginalFile().isDirectory() ? getTargetFileByParsingURL() : getOriginalFile();
        }
        return this.frontendFile;
    }

    protected File getTargetFileByParsingURL() {
        Utils.asserts(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
        Utils.asserts(getRequestURI() != null, "RequestURI is null, cannot proceed");
        String uri = getRequestURI().toString();
        String substring = uri.substring(uri.lastIndexOf(47) + 1, uri.length());
        File file = new File(getOriginalFile(), substring);
        if (!file.exists() || !this.renameIfExists) {
            return file;
        }
        uri = !substring.contains(".") ? substring + " (%d)" : substring.substring(0, substring.lastIndexOf(46)) + " (%d)" + substring.substring(substring.lastIndexOf(46), substring.length());
        int i = 0;
        while (true) {
            File file2 = new File(getOriginalFile(), String.format(uri, new Object[]{Integer.valueOf(i)}));
            if (!file2.exists()) {
                return file2;
            }
            i++;
        }
    }

    protected File getTemporaryFile(Context context) {
        Utils.asserts(context != null, "Tried creating temporary file without having Context");
        try {
            return File.createTempFile("temp_", "_handled", context.getCacheDir());
        } catch (Throwable e) {
            AsyncHttpClient.log.m6891e(LOG_TAG, "Cannot create temporary file", e);
            return null;
        }
    }

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, File file);

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        onFailure(i, headerArr, th, getTargetFile());
    }

    public abstract void onSuccess(int i, Header[] headerArr, File file);

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        onSuccess(i, headerArr, getTargetFile());
    }
}
