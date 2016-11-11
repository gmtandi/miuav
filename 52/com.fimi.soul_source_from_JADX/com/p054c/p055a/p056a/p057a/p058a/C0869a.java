package com.p054c.p055a.p056a.p057a.p058a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p057a.p060b.C0872a;
import com.p054c.p055a.p063b.C0905a;
import com.p054c.p055a.p072c.C0947e;
import com.p054c.p055a.p072c.C0957d;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* renamed from: com.c.a.a.a.a.a */
public abstract class C0869a implements C0864a {
    public static final int f4700a = 32768;
    public static final CompressFormat f4701b;
    public static final int f4702c = 100;
    private static final String f4703j = " argument must be not null";
    private static final String f4704k = ".tmp";
    protected final File f4705d;
    protected final File f4706e;
    protected final C0872a f4707f;
    protected int f4708g;
    protected CompressFormat f4709h;
    protected int f4710i;

    static {
        f4701b = CompressFormat.PNG;
    }

    public C0869a(File file) {
        this(file, null);
    }

    public C0869a(File file, File file2) {
        this(file, file2, C0905a.m7219b());
    }

    public C0869a(File file, File file2, C0872a c0872a) {
        this.f4708g = f4700a;
        this.f4709h = f4701b;
        this.f4710i = f4702c;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (c0872a == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.f4705d = file;
            this.f4706e = file2;
            this.f4707f = c0872a;
        }
    }

    public File m7052a() {
        return this.f4705d;
    }

    public File m7053a(String str) {
        return m7061c(str);
    }

    public void m7054a(int i) {
        this.f4708g = i;
    }

    public void m7055a(CompressFormat compressFormat) {
        this.f4709h = compressFormat;
    }

    public boolean m7056a(String str, Bitmap bitmap) {
        File c = m7061c(str);
        File file = new File(c.getAbsolutePath() + f4704k);
        Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f4708g);
        try {
            boolean compress = bitmap.compress(this.f4709h, this.f4710i, bufferedOutputStream);
            C0957d.m7547a(bufferedOutputStream);
            if (compress && !file.renameTo(c)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            C0957d.m7547a(bufferedOutputStream);
            file.delete();
        }
    }

    public boolean m7057a(String str, InputStream inputStream, C0947e c0947e) {
        boolean a;
        Throwable th;
        File c = m7061c(str);
        File file = new File(c.getAbsolutePath() + f4704k);
        Closeable bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f4708g);
            a = C0957d.m7551a(inputStream, bufferedOutputStream, c0947e, this.f4708g);
            try {
                C0957d.m7547a(bufferedOutputStream);
                if (a && !file.renameTo(c)) {
                    a = false;
                }
                if (!a) {
                    file.delete();
                }
                return a;
            } catch (Throwable th2) {
                th = th2;
                a = false;
                if (!a) {
                    file.delete();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a = false;
            if (a && !file.renameTo(c)) {
                a = false;
            }
            if (a) {
                file.delete();
            }
            throw th;
        }
    }

    public void m7058b() {
    }

    public void m7059b(int i) {
        this.f4710i = i;
    }

    public boolean m7060b(String str) {
        return m7061c(str).delete();
    }

    protected File m7061c(String str) {
        String a = this.f4707f.m7069a(str);
        File file = this.f4705d;
        if (!(this.f4705d.exists() || this.f4705d.mkdirs() || this.f4706e == null || (!this.f4706e.exists() && !this.f4706e.mkdirs()))) {
            file = this.f4706e;
        }
        return new File(file, a);
    }

    public void m7062c() {
        File[] listFiles = this.f4705d.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }
}
