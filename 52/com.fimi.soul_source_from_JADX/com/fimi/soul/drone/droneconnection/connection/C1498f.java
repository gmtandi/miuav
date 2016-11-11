package com.fimi.soul.drone.droneconnection.connection;

import android.os.Handler;
import android.util.Log;
import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.C1980t;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.f */
public abstract class C1498f {
    private static final String f7070a;
    public static final int f7071b = 0;
    public static final int f7072c = 1;
    public static final int f7073d = 2;
    private static final int f7074i = 4096;
    private static final int f7075r = 1;
    protected boolean f7076e;
    BufferedWriter f7077f;
    boolean f7078g;
    private File f7079h;
    private List<Integer> f7080j;
    private final ConcurrentHashMap<String, C1514k> f7081k;
    private final LinkedBlockingQueue<C1465c> f7082l;
    private final AtomicInteger f7083m;
    private final Runnable f7084n;
    private final Runnable f7085o;
    private Thread f7086p;
    private Handler f7087q;
    private final Runnable f7088s;

    static {
        f7070a = C1498f.class.getSimpleName();
    }

    public C1498f() {
        this.f7080j = new ArrayList();
        this.f7076e = false;
        this.f7081k = new ConcurrentHashMap();
        this.f7082l = new LinkedBlockingQueue();
        this.f7083m = new AtomicInteger(f7071b);
        this.f7084n = new C1510g(this);
        this.f7085o = new C1511h(this);
        this.f7078g = true;
        this.f7087q = new C1512i(this);
        this.f7088s = new C1513j(this);
    }

    private void m9896a() {
        for (C1514k a : this.f7081k.values()) {
            a.m9987a();
        }
    }

    private void m9897a(C1437b c1437b) {
        if (!this.f7081k.isEmpty()) {
            if (this.f7078g) {
                this.f7078g = false;
                this.f7087q.postDelayed(this.f7088s, 1500);
            }
            for (C1514k a : this.f7081k.values()) {
                a.m9988a(c1437b);
            }
        }
    }

    private void m9900b() {
        if (!this.f7081k.isEmpty()) {
            for (C1514k b : this.f7081k.values()) {
                b.m9991b();
                this.f7078g = true;
            }
        }
    }

    private void m9902b(String str) {
        if (!this.f7081k.isEmpty()) {
            for (C1514k a : this.f7081k.values()) {
                a.m9989a(str);
            }
        }
    }

    public void m9909a(C1465c c1465c) {
        byte[] d = c1465c.m9816d();
        try {
            if (c1465c.f7000c != 18) {
                if (this.f7077f == null) {
                    this.f7077f = m9917f();
                }
                if (this.f7077f != null) {
                    this.f7077f.write(C1980t.m12511a());
                    this.f7077f.write("    ");
                    StringBuffer stringBuffer = new StringBuffer(d.length * f7073d);
                    for (int i = f7071b; i < d.length; i += f7075r) {
                        stringBuffer.append(Character.forDigit((d[i] & 240) >> 4, 16));
                        stringBuffer.append(Character.forDigit(d[i] & 15, 16));
                        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    }
                    this.f7077f.write(stringBuffer.toString());
                    this.f7077f.write("\n");
                    this.f7077f.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void m9910a(String str) {
        this.f7081k.remove(str);
    }

    public void m9911a(String str, C1514k c1514k) {
        this.f7081k.put(str, c1514k);
        if (m9920i() == f7073d) {
            c1514k.m9987a();
        }
    }

    protected abstract int m9912b(byte[] bArr);

    public void m9913b(C1465c c1465c) {
        if (!this.f7082l.offer(c1465c)) {
            Log.e("sendMavPacket", "Unable to send mavlink packet. Packet queue is full!");
        }
    }

    protected abstract void m9914c(byte[] bArr);

    protected abstract void m9915d();

    protected abstract void m9916e();

    public BufferedWriter m9917f() {
        File file = new File(C1969i.m12474a() + "/updateLog/");
        file.mkdirs();
        File file2 = new File(file, C1980t.m12511a() + ".txt");
        if (file2.exists()) {
            file2.delete();
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
    }

    public void m9918g() {
        if (this.f7083m.compareAndSet(f7071b, f7075r)) {
            this.f7086p = new Thread(this.f7084n, "MiLinkConnection-Connecting Thread");
            this.f7086p.start();
        }
    }

    public void m9919h() {
        if (this.f7083m.get() != 0 && this.f7086p != null) {
            try {
                this.f7083m.set(f7071b);
                if (this.f7086p.isAlive() && !this.f7086p.isInterrupted()) {
                    this.f7086p.interrupt();
                }
                m9916e();
                m9900b();
            } catch (IOException e) {
                m9902b(e.getMessage());
            }
        }
    }

    public int m9920i() {
        return this.f7083m.get();
    }

    public abstract int m9921j();
}
