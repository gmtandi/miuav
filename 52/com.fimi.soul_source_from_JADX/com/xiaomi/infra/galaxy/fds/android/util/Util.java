package com.xiaomi.infra.galaxy.fds.android.util;

import android.webkit.MimeTypeMap;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Util {
    private static final int BUFFER_SIZE = 4096;
    private static final ThreadLocal<SimpleDateFormat> DATE_FOPMAT;

    /* renamed from: com.xiaomi.infra.galaxy.fds.android.util.Util.1 */
    final class C24971 extends ThreadLocal<SimpleDateFormat> {
        C24971() {
        }

        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    }

    static {
        DATE_FOPMAT = new C24971();
    }

    public static void downloadObjectToFile(FDSObject fDSObject, File file, boolean z) {
        OutputStream bufferedOutputStream;
        Throwable e;
        File parentFile = file.getParentFile();
        if (!(z || parentFile == null || parentFile.exists())) {
            parentFile.mkdirs();
        }
        byte[] bArr = new byte[BUFFER_SIZE];
        InputStream objectContent = fDSObject.getObjectContent();
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            while (true) {
                try {
                    int read = objectContent.read(bArr, 0, BUFFER_SIZE);
                    if (read != -1) {
                        bufferedOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e2) {
                            return;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            }
            objectContent.close();
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream = null;
            try {
                throw new GalaxyFDSClientException("Unable to store object[" + fDSObject.getBucketName() + "/" + fDSObject.getObjectName() + "] content " + "to disk:" + e.getMessage(), e);
            } catch (Throwable th) {
                e = th;
                try {
                    objectContent.close();
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                } catch (IOException e5) {
                }
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            bufferedOutputStream = null;
            objectContent.close();
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw e;
        }
    }

    public static String formatDateString(Date date) {
        return ((SimpleDateFormat) DATE_FOPMAT.get()).format(date);
    }

    public static String getMimeType(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0 && lastIndexOf + 1 < name.length()) {
            name = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(lastIndexOf + 1));
            if (name != null) {
                return name;
            }
        }
        return Consts.APPLICATION_OCTET_STREAM;
    }

    public static String getStackTrace(Exception exception) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static Date parseDate(String str) {
        return ((SimpleDateFormat) DATE_FOPMAT.get()).parse(str);
    }
}
