package com.loopj.android.http;

import com.baidu.tts.loopj.RequestParams;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;
import org.p122a.p123a.C3004e;

class SimpleMultipartEntity implements HttpEntity {
    private static final char[] MULTIPART_CHARS;
    private String boundary;
    boolean isSetFirst;
    boolean isSetLast;
    ByteArrayOutputStream out;

    static {
        MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public SimpleMultipartEntity() {
        int i = 0;
        this.boundary = null;
        this.out = new ByteArrayOutputStream();
        this.isSetLast = false;
        this.isSetFirst = false;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(MULTIPART_CHARS[random.nextInt(MULTIPART_CHARS.length)]);
            i++;
        }
        this.boundary = stringBuffer.toString();
    }

    public void addPart(String str, File file, boolean z) {
        try {
            addPart(str, file.getName(), new FileInputStream(file), z);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPart(String str, String str2) {
        writeFirstBoundaryIfNeeds();
        try {
            this.out.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n").getBytes());
            this.out.write(str2.getBytes());
            this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addPart(java.lang.String r5, java.lang.String r6, java.io.InputStream r7, java.lang.String r8, boolean r9) {
        /*
        r4 = this;
        r4.writeFirstBoundaryIfNeeds();
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r0.<init>();	 Catch:{ IOException -> 0x006e }
        r1 = "Content-Type: ";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x006e }
        r0 = r0.append(r8);	 Catch:{ IOException -> 0x006e }
        r1 = "\r\n";
        r0 = r0.append(r1);	 Catch:{ IOException -> 0x006e }
        r0 = r0.toString();	 Catch:{ IOException -> 0x006e }
        r1 = r4.out;	 Catch:{ IOException -> 0x006e }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r2.<init>();	 Catch:{ IOException -> 0x006e }
        r3 = "Content-Disposition: form-data; name=\"";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.append(r5);	 Catch:{ IOException -> 0x006e }
        r3 = "\"; filename=\"";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.append(r6);	 Catch:{ IOException -> 0x006e }
        r3 = "\"\r\n";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x006e }
        r2 = r2.toString();	 Catch:{ IOException -> 0x006e }
        r2 = r2.getBytes();	 Catch:{ IOException -> 0x006e }
        r1.write(r2);	 Catch:{ IOException -> 0x006e }
        r1 = r4.out;	 Catch:{ IOException -> 0x006e }
        r0 = r0.getBytes();	 Catch:{ IOException -> 0x006e }
        r1.write(r0);	 Catch:{ IOException -> 0x006e }
        r0 = r4.out;	 Catch:{ IOException -> 0x006e }
        r1 = "Content-Transfer-Encoding: binary\r\n\r\n";
        r1 = r1.getBytes();	 Catch:{ IOException -> 0x006e }
        r0.write(r1);	 Catch:{ IOException -> 0x006e }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ IOException -> 0x006e }
    L_0x0060:
        r1 = r7.read(r0);	 Catch:{ IOException -> 0x006e }
        r2 = -1;
        if (r1 == r2) goto L_0x0076;
    L_0x0067:
        r2 = r4.out;	 Catch:{ IOException -> 0x006e }
        r3 = 0;
        r2.write(r0, r3, r1);	 Catch:{ IOException -> 0x006e }
        goto L_0x0060;
    L_0x006e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00af }
        r7.close();	 Catch:{ IOException -> 0x00aa }
    L_0x0075:
        return;
    L_0x0076:
        if (r9 != 0) goto L_0x009c;
    L_0x0078:
        r0 = r4.out;	 Catch:{ IOException -> 0x006e }
        r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006e }
        r1.<init>();	 Catch:{ IOException -> 0x006e }
        r2 = "\r\n--";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r2 = r4.boundary;	 Catch:{ IOException -> 0x006e }
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r2 = "\r\n";
        r1 = r1.append(r2);	 Catch:{ IOException -> 0x006e }
        r1 = r1.toString();	 Catch:{ IOException -> 0x006e }
        r1 = r1.getBytes();	 Catch:{ IOException -> 0x006e }
        r0.write(r1);	 Catch:{ IOException -> 0x006e }
    L_0x009c:
        r0 = r4.out;	 Catch:{ IOException -> 0x006e }
        r0.flush();	 Catch:{ IOException -> 0x006e }
        r7.close();	 Catch:{ IOException -> 0x00a5 }
        goto L_0x0075;
    L_0x00a5:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0075;
    L_0x00aa:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0075;
    L_0x00af:
        r0 = move-exception;
        r7.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x00b3:
        throw r0;
    L_0x00b4:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00b3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.SimpleMultipartEntity.addPart(java.lang.String, java.lang.String, java.io.InputStream, java.lang.String, boolean):void");
    }

    public void addPart(String str, String str2, InputStream inputStream, boolean z) {
        addPart(str, str2, inputStream, RequestParams.APPLICATION_OCTET_STREAM, z);
    }

    public void consumeContent() {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() {
        return new ByteArrayInputStream(this.out.toByteArray());
    }

    public Header getContentEncoding() {
        return null;
    }

    public long getContentLength() {
        writeLastBoundaryIfNeeds();
        return (long) this.out.toByteArray().length;
    }

    public Header getContentType() {
        return new BasicHeader(C3004e.f15031q, "multipart/form-data; boundary=" + this.boundary);
    }

    public boolean isChunked() {
        return false;
    }

    public boolean isRepeatable() {
        return false;
    }

    public boolean isStreaming() {
        return false;
    }

    public void writeFirstBoundaryIfNeeds() {
        if (!this.isSetFirst) {
            try {
                this.out.write(("--" + this.boundary + "\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.isSetFirst = true;
    }

    public void writeLastBoundaryIfNeeds() {
        if (!this.isSetLast) {
            try {
                this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.isSetLast = true;
        }
    }

    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.out.toByteArray());
    }
}
