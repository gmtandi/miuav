package com.fimi.soul.drone.p116g;

import android.content.Context;
import com.fimi.soul.drone.p107c.p108a.p109a.bi;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.drone.g.e */
public class C1545e {
    public static Queue<bi> f7255a;

    static {
        f7255a = new LinkedList();
    }

    public static Queue<bi> m10119a() {
        return f7255a;
    }

    public static void m10120a(Queue<bi> queue) {
        queue.removeAll(queue);
    }

    public static byte[] m10121a(InputStream inputStream, long j, Context context) {
        if (f7255a.size() > 1) {
            C1545e.m10120a(f7255a);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[Opcodes.GETFIELD];
        int i = 1;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
                bi biVar = new bi();
                biVar.f6776d = (short) i;
                if (bArr.length == Opcodes.GETFIELD) {
                    biVar.m9708a(bArr.length);
                    biVar.m9710b(bArr.length + 2);
                    for (read = 0; read < bArr.length; read++) {
                        biVar.f6778f[read] = bArr[read];
                    }
                    f7255a.offer(biVar);
                } else {
                    biVar.m9708a(bArr.length);
                    biVar.m9710b(bArr.length + 2);
                    for (read = 0; read < bArr.length; read++) {
                        biVar.f6778f[read] = bArr[read];
                    }
                    f7255a.offer(biVar);
                }
                bArr = ((long) i) == j / 180 ? new byte[((int) (j % 180))] : new byte[Opcodes.GETFIELD];
                i++;
            } else {
                inputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
