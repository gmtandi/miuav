package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

public final class ApkExternalInfoTool {
    public static final String CHANNELID = "channelNo";
    private static final ZipLong f12075a;
    private static final ZipShort f12076b;

    class ApkExternalInfo {
        Properties f12073a;
        byte[] f12074b;

        private ApkExternalInfo() {
            this.f12073a = new Properties();
        }

        void m13823a(byte[] bArr) {
            if (bArr != null) {
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                int length = ApkExternalInfoTool.f12076b.getBytes().length;
                byte[] bArr2 = new byte[length];
                wrap.get(bArr2);
                if (!ApkExternalInfoTool.f12076b.equals(new ZipShort(bArr2))) {
                    throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
                } else if (bArr.length - length > 2) {
                    bArr2 = new byte[2];
                    wrap.get(bArr2);
                    int value = new ZipShort(bArr2).getValue();
                    if ((bArr.length - length) - 2 >= value) {
                        byte[] bArr3 = new byte[value];
                        wrap.get(bArr3);
                        this.f12073a.load(new ByteArrayInputStream(bArr3));
                        length = ((bArr.length - length) - value) - 2;
                        if (length > 0) {
                            this.f12074b = new byte[length];
                            wrap.get(this.f12074b);
                        }
                    }
                }
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.f12073a + ", otherData=" + Arrays.toString(this.f12074b) + "]";
        }
    }

    static {
        f12075a = new ZipLong(101010256);
        f12076b = new ZipShort(38651);
    }

    private static byte[] m13825a(RandomAccessFile randomAccessFile) {
        int i = 1;
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bytes = f12075a.getBytes();
        byte read = randomAccessFile.read();
        while (read != (byte) -1) {
            if (read == bytes[0] && randomAccessFile.read() == bytes[1] && randomAccessFile.read() == bytes[2] && randomAccessFile.read() == bytes[3]) {
                break;
            }
            length--;
            randomAccessFile.seek(length);
            read = randomAccessFile.read();
        }
        i = 0;
        if (i == 0) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek((16 + length) + 4);
        byte[] bArr = new byte[2];
        randomAccessFile.readFully(bArr);
        i = new ZipShort(bArr).getValue();
        if (i == 0) {
            return null;
        }
        bArr = new byte[i];
        randomAccessFile.read(bArr);
        return bArr;
    }

    public static String read(File file, String str) {
        Throwable th;
        String str2 = null;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                byte[] a = m13825a(randomAccessFile);
                if (a != null) {
                    ApkExternalInfo apkExternalInfo = new ApkExternalInfo();
                    apkExternalInfo.m13823a(a);
                    str2 = apkExternalInfo.f12073a.getProperty(str);
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } else if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            randomAccessFile = null;
            th = th4;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static String readChannelId(File file) {
        return read(file, CHANNELID);
    }
}
