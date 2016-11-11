package com.fimi.soul.drone.p116g;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* renamed from: com.fimi.soul.drone.g.f */
public class C1546f {
    public static byte[] m10122a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
