package com.p054c.p055a.p056a.p057a.p058a.p059a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p057a.p060b.C0872a;
import com.p054c.p055a.p072c.C0947e;
import com.p054c.p055a.p072c.C0957d;
import com.p054c.p055a.p072c.C0958f;
import com.tencent.mm.sdk.platformtools.MAlarmHandler;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.c.a.a.a.a.a.h */
public class C0865h implements C0864a {
    public static final int f4679a = 32768;
    public static final CompressFormat f4680b;
    public static final int f4681c = 100;
    private static final String f4682i = " argument must be not null";
    private static final String f4683j = " argument must be positive number";
    protected C0857a f4684d;
    protected final C0872a f4685e;
    protected int f4686f;
    protected CompressFormat f4687g;
    protected int f4688h;
    private File f4689k;

    static {
        f4680b = CompressFormat.PNG;
    }

    public C0865h(File file, C0872a c0872a, long j) {
        this(file, null, c0872a, j, 0);
    }

    public C0865h(File file, File file2, C0872a c0872a, long j, int i) {
        this.f4686f = f4679a;
        this.f4687g = f4680b;
        this.f4688h = f4681c;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (c0872a == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            long j2 = j == 0 ? MAlarmHandler.NEXT_FIRE_INTERVAL : j;
            int i2 = i == 0 ? Integer.MAX_VALUE : i;
            this.f4689k = file2;
            this.f4685e = c0872a;
            m7034a(file, file2, j2, i2);
        }
    }

    private void m7034a(File file, File file2, long j, int i) {
        try {
            this.f4684d = C0857a.m6959a(file, 1, 1, j, i);
        } catch (Throwable e) {
            C0958f.m7555a(e);
            if (file2 != null) {
                m7034a(file2, null, j, i);
            }
            if (this.f4684d == null) {
                throw e;
            }
        }
    }

    private String m7035c(String str) {
        return this.f4685e.m7069a(str);
    }

    public File m7036a() {
        return this.f4684d.m6986a();
    }

    public File m7037a(String str) {
        C0863g a;
        Throwable e;
        Throwable th;
        File file = null;
        try {
            a = this.f4684d.m6985a(m7035c(str));
            if (a != null) {
                try {
                    file = a.m7023a(0);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        C0958f.m7555a(e);
                        if (a != null) {
                            a.close();
                        }
                        return file;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            a.close();
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                a.close();
            }
        } catch (IOException e3) {
            e = e3;
            a = file;
            C0958f.m7555a(e);
            if (a != null) {
                a.close();
            }
            return file;
        } catch (Throwable e4) {
            a = file;
            th = e4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
        return file;
    }

    public void m7038a(int i) {
        this.f4686f = i;
    }

    public void m7039a(CompressFormat compressFormat) {
        this.f4687g = compressFormat;
    }

    public boolean m7040a(String str, Bitmap bitmap) {
        boolean z = false;
        C0860d b = this.f4684d.m6989b(m7035c(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.m7006c(0), this.f4686f);
            try {
                z = bitmap.compress(this.f4687g, this.f4688h, bufferedOutputStream);
                if (z) {
                    b.m7002a();
                } else {
                    b.m7005b();
                }
            } finally {
                C0957d.m7547a(bufferedOutputStream);
            }
        }
        return z;
    }

    public boolean m7041a(String str, InputStream inputStream, C0947e c0947e) {
        boolean z = false;
        C0860d b = this.f4684d.m6989b(m7035c(str));
        if (b != null) {
            Closeable bufferedOutputStream = new BufferedOutputStream(b.m7006c(0), this.f4686f);
            try {
                z = C0957d.m7551a(inputStream, bufferedOutputStream, c0947e, this.f4686f);
                C0957d.m7547a(bufferedOutputStream);
                if (z) {
                    b.m7002a();
                } else {
                    b.m7005b();
                }
            } catch (Throwable th) {
                C0957d.m7547a(bufferedOutputStream);
                b.m7005b();
            }
        }
        return z;
    }

    public void m7042b() {
        try {
            this.f4684d.close();
        } catch (Throwable e) {
            C0958f.m7555a(e);
        }
        this.f4684d = null;
    }

    public void m7043b(int i) {
        this.f4688h = i;
    }

    public boolean m7044b(String str) {
        try {
            return this.f4684d.m6991c(m7035c(str));
        } catch (Throwable e) {
            C0958f.m7555a(e);
            return false;
        }
    }

    public void m7045c() {
        try {
            this.f4684d.m6996h();
        } catch (Throwable e) {
            C0958f.m7555a(e);
        }
        try {
            m7034a(this.f4684d.m6986a(), this.f4689k, this.f4684d.m6988b(), this.f4684d.m6990c());
        } catch (Throwable e2) {
            C0958f.m7555a(e2);
        }
    }
}
