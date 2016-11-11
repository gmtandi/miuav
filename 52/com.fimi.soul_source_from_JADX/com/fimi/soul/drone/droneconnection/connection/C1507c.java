package com.fimi.soul.drone.droneconnection.connection;

import android.os.Handler;
import android.os.HandlerThread;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.C1173l;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p114e.C1532a;
import com.fimi.soul.utils.C1967g;
import com.fimi.soul.utils.C1969i;
import com.tencent.connect.common.Constants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.c */
public class C1507c {
    private static final String f7118c = "com123456789fimi987654321soul12345678911";
    HandlerThread f7119a;
    StringBuffer f7120b;
    private Handler f7121d;
    private SimpleDateFormat f7122e;
    private SimpleDateFormat f7123f;
    private SimpleDateFormat f7124g;
    private BufferedWriter f7125h;
    private File f7126i;
    private File f7127j;
    private StringBuilder f7128k;
    private boolean f7129l;
    private boolean f7130m;
    private Runnable f7131n;

    private C1507c() {
        this.f7120b = new StringBuffer();
        this.f7128k = new StringBuilder();
        this.f7129l = false;
        this.f7131n = new C1508d(this);
        if (this.f7119a == null && this.f7121d == null) {
            this.f7119a = new HandlerThread("LogHandler");
            this.f7119a.start();
            this.f7121d = new Handler(this.f7119a.getLooper());
        }
    }

    public static C1507c m9968a() {
        return C1509e.f7133a;
    }

    private void m9969a(C1465c c1465c, String str) {
        byte[] e = c1465c.m9817e();
        this.f7120b.append(str).append("    ");
        for (int i = 0; i < e.length; i++) {
            this.f7120b.append(Character.forDigit((e[i] & 240) >> 4, 16));
            this.f7120b.append(Character.forDigit(e[i] & 15, 16));
            this.f7120b.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        this.f7128k.append(C1967g.m12472b(this.f7120b.toString(), f7118c));
        this.f7128k.append("\n");
        this.f7120b = this.f7120b.delete(0, this.f7120b.length());
        if (this.f7130m && this.f7126i != null) {
            m9970a(this.f7128k, this.f7126i);
        } else if (!this.f7130m && this.f7127j != null) {
            m9970a(this.f7128k, this.f7127j);
        }
    }

    private void m9970a(StringBuilder stringBuilder, File file) {
        try {
            this.f7125h.write(stringBuilder.toString());
            this.f7125h.flush();
            stringBuilder.delete(0, stringBuilder.length());
            this.f7129l = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m9972a(C1465c c1465c) {
        if (c1465c.f7000c != Opcodes.LMUL || this.f7129l) {
            m9969a(c1465c, m9983j());
        } else {
            this.f7129l = true;
        }
    }

    public void m9973a(String str) {
        IOException e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(C1969i.m12489l() + "log.txt");
            if (file == null || !file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
            try {
                fileOutputStream2.write(str.getBytes());
                fileOutputStream2.write("\r\n".getBytes());
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (IOException e3) {
                e2 = e3;
                fileOutputStream = fileOutputStream2;
                try {
                    e2.printStackTrace();
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                fileOutputStream.flush();
                fileOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            e22 = e5;
            e22.printStackTrace();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public void m9974a(boolean z) {
        if (this.f7130m != z) {
            this.f7130m = z;
        }
    }

    public void m9975b() {
        String format = m9981h().format(new Date());
        File file = new File(C1969i.m12474a() + "/LOGDOWN/" + m9982i() + "/AirFly");
        file.mkdirs();
        if (this.f7126i == null || !this.f7126i.exists()) {
            this.f7126i = new File(file, format + ".txt");
        }
        if (this.f7125h == null) {
            try {
                this.f7125h = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f7126i, true)));
                C1532a c1532a = (C1532a) C1189f.m8333c().m7926a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
                if (c1532a != null) {
                    this.f7125h.write(C1967g.m12472b(c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g(), f7118c) + "\n");
                }
                this.f7125h.flush();
                this.f7121d.postDelayed(this.f7131n, 200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m9976c() {
        String format = m9981h().format(new Date());
        File file = new File(C1969i.m12474a() + "/LOGDOWN/" + m9982i() + "/NoFly");
        file.mkdirs();
        if (this.f7127j == null || !this.f7127j.exists()) {
            this.f7127j = new File(file, format + ".txt");
        }
        if (this.f7125h == null) {
            try {
                this.f7125h = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f7127j, true)));
                C1532a c1532a = (C1532a) C1189f.m8333c().m7926a(Constants.VIA_RESULT_SUCCESS, C1532a.class);
                if (c1532a != null) {
                    this.f7125h.write(C1967g.m12472b(c1532a.m10059d() + C2915a.f14760f + c1532a.m10061e() + C2915a.f14760f + c1532a.m10063g(), f7118c) + "\n");
                }
                this.f7125h.flush();
                this.f7121d.postDelayed(this.f7131n, 200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m9977d() {
        if (this.f7125h != null) {
            try {
                this.f7125h.close();
                this.f7125h = null;
                this.f7126i = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean m9978e() {
        return (this.f7125h == null || this.f7126i == null) ? false : true;
    }

    public boolean m9979f() {
        return (this.f7125h == null || this.f7127j == null) ? false : true;
    }

    public void m9980g() {
        if (this.f7125h != null) {
            try {
                this.f7125h.close();
                this.f7125h = null;
                this.f7127j = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SimpleDateFormat m9981h() {
        if (this.f7122e == null) {
            this.f7122e = new SimpleDateFormat(C1236a.f5614l, Locale.CHINA);
        }
        return this.f7122e;
    }

    public String m9982i() {
        if (this.f7123f == null) {
            this.f7123f = new SimpleDateFormat(C1173l.f5330b, Locale.US);
        }
        return this.f7123f.format(new Date());
    }

    public String m9983j() {
        if (this.f7124g == null) {
            this.f7124g = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS", Locale.US);
        }
        return this.f7124g.format(new Date());
    }

    public boolean m9984k() {
        return this.f7127j != null && this.f7127j.length() > 10485760;
    }
}
