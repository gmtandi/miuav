package com.fimi.soul.drone.p115f;

import com.fimi.soul.drone.p107c.p108a.p109a.bi;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/* renamed from: com.fimi.soul.drone.f.b */
public class C1539b {
    public static final int f7198a = 176;
    public static Queue<bi> f7199b;

    static {
        f7199b = new LinkedList();
    }

    public static Queue<bi> m10106a() {
        return f7199b;
    }

    public static void m10107a(Queue<bi> queue) {
        queue.removeAll(queue);
    }

    public static byte[] m10108a(int i, InputStream inputStream, long j) {
        int i2 = 1;
        if (f7199b.size() > 1) {
            C1539b.m10107a(f7199b);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[f7198a];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
                bi biVar = new bi();
                biVar.f6777e = (byte) i;
                biVar.f6776d = (short) i2;
                if (bArr.length == f7198a) {
                    biVar.m9708a(bArr.length);
                    biVar.m9710b(bArr.length + 3);
                    for (read = 0; read < bArr.length; read++) {
                        biVar.f6778f[read] = bArr[read];
                    }
                    f7199b.offer(biVar);
                } else {
                    biVar.m9708a(bArr.length);
                    biVar.m9710b(bArr.length + 3);
                    for (read = 0; read < bArr.length; read++) {
                        biVar.f6778f[read] = bArr[read];
                    }
                    f7199b.offer(biVar);
                }
                bArr = new byte[f7198a];
                i2++;
            } else {
                inputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
