package com.p016a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONObject;

/* renamed from: com.a.et */
public final class et {
    private Context f1125a;
    private boolean f1126b;
    private int f1127c;
    private int f1128d;
    private int f1129e;
    private int f1130f;
    private int f1131g;
    private int f1132h;
    private int f1133i;
    private long f1134j;
    private es f1135k;

    private et(Context context) {
        this.f1125a = null;
        this.f1126b = true;
        this.f1127c = 1270;
        this.f1128d = 310;
        this.f1129e = 4;
        this.f1130f = C2799f.f14282t;
        this.f1131g = 1;
        this.f1132h = 0;
        this.f1133i = 0;
        this.f1134j = 0;
        this.f1135k = null;
        this.f1125a = context;
    }

    private static int m1750a(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 4) {
            i3 += (bArr[i2 + i] & Util.MASK_8BIT) << (i2 << 3);
            i2++;
        }
        return i3;
    }

    protected static et m1751a(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        et etVar = new et(context);
        etVar.f1132h = 0;
        etVar.f1133i = 0;
        etVar.f1134j = ((System.currentTimeMillis() + 28800000) / MiStatInterface.MAX_UPLOAD_INTERVAL) * MiStatInterface.MAX_UPLOAD_INTERVAL;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(new File(et.m1754b(context) + File.separator + "data_carrier_status"));
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[32];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (toByteArray != null && toByteArray.length >= 22) {
                    etVar.f1126b = toByteArray[0] != null;
                    etVar.f1127c = (toByteArray[1] * 10) << 10;
                    etVar.f1128d = (toByteArray[2] * 10) << 10;
                    etVar.f1129e = toByteArray[3];
                    etVar.f1130f = toByteArray[4] * 10;
                    etVar.f1131g = toByteArray[5];
                    long b = et.m1753b(toByteArray, 14);
                    if (etVar.f1134j - b < MiStatInterface.MAX_UPLOAD_INTERVAL) {
                        etVar.f1134j = b;
                        etVar.f1132h = et.m1750a(toByteArray, 6);
                        etVar.f1133i = et.m1750a(toByteArray, 10);
                    }
                }
                byteArrayOutputStream.close();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                    }
                }
                return etVar;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream2 = fileInputStream;
                th = th3;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e3) {
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return etVar;
        } catch (Throwable th4) {
            th = th4;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return etVar;
    }

    private static byte[] m1752a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) ((j >> (i << 3)) & 255));
        }
        return bArr;
    }

    private static long m1753b(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 8) {
            i3 += (bArr[i2 + 14] & Util.MASK_8BIT) << (i2 << 3);
            i2++;
        }
        return (long) i3;
    }

    private static String m1754b(Context context) {
        boolean z = false;
        File file = null;
        if (Process.myUid() != XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            file = ed.m1644a(context);
        }
        try {
            z = "mounted".equals(Environment.getExternalStorageState());
        } catch (Exception e) {
        }
        return ((z || !ed.m1653c()) && file != null) ? file.getPath() : context.getFilesDir().getPath();
    }

    private static byte[] m1755c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >> (i2 << 3));
        }
        return bArr;
    }

    private void m1756g() {
        long currentTimeMillis = System.currentTimeMillis() + 28800000;
        if (currentTimeMillis - this.f1134j > MiStatInterface.MAX_UPLOAD_INTERVAL) {
            this.f1134j = (currentTimeMillis / MiStatInterface.MAX_UPLOAD_INTERVAL) * MiStatInterface.MAX_UPLOAD_INTERVAL;
            this.f1132h = 0;
            this.f1133i = 0;
        }
    }

    protected final void m1757a(int i) {
        m1756g();
        if (i < 0) {
            i = 0;
        }
        this.f1132h = i;
    }

    protected final void m1758a(es esVar) {
        this.f1135k = esVar;
    }

    protected final boolean m1759a() {
        m1756g();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f1125a.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? this.f1126b : activeNetworkInfo.getType() == 1 ? this.f1126b && this.f1132h < this.f1127c : this.f1126b && this.f1133i < this.f1128d;
    }

    protected final boolean m1760a(String str) {
        boolean z;
        FileOutputStream fileOutputStream;
        Throwable th;
        int i = 1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("e")) {
                this.f1126b = jSONObject.getInt("e") != 0;
            }
            if (jSONObject.has("d")) {
                int i2 = jSONObject.getInt("d");
                this.f1127c = ((i2 & Opcodes.LAND) * 10) << 10;
                this.f1128d = (((i2 & 3968) >> 7) * 10) << 10;
                this.f1129e = (520192 & i2) >> 12;
                this.f1130f = ((66584576 & i2) >> 19) * 10;
                this.f1131g = (i2 & 2080374784) >> 26;
                if (this.f1131g == 31) {
                    this.f1131g = AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS;
                }
                if (this.f1135k != null) {
                    this.f1135k.m1749a();
                }
            }
            z = jSONObject.has("u") ? jSONObject.getInt("u") != 0 : false;
        } catch (Exception e) {
            z = false;
        }
        FileOutputStream fileOutputStream2;
        try {
            m1756g();
            fileOutputStream2 = new FileOutputStream(new File(et.m1754b(this.f1125a) + File.separator + "data_carrier_status"));
            try {
                byte[] c = et.m1755c(this.f1132h);
                byte[] c2 = et.m1755c(this.f1133i);
                byte[] a = et.m1752a(this.f1134j);
                byte[] bArr = new byte[22];
                if (!this.f1126b) {
                    i = 0;
                }
                bArr[0] = (byte) i;
                bArr[1] = (byte) (this.f1127c / C1142e.f5202b);
                bArr[2] = (byte) (this.f1128d / C1142e.f5202b);
                bArr[3] = (byte) this.f1129e;
                bArr[4] = (byte) (this.f1130f / 10);
                bArr[5] = (byte) this.f1131g;
                bArr[6] = c[0];
                bArr[7] = c[1];
                bArr[8] = c[2];
                bArr[9] = c[3];
                bArr[10] = c2[0];
                bArr[11] = c2[1];
                bArr[12] = c2[2];
                bArr[13] = c2[3];
                bArr[14] = a[0];
                bArr[15] = a[1];
                bArr[16] = a[2];
                bArr[17] = a[3];
                bArr[18] = a[4];
                bArr[19] = a[5];
                bArr[20] = a[6];
                bArr[21] = a[7];
                fileOutputStream2.write(bArr);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = null;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
        return z;
    }

    protected final int m1761b() {
        return this.f1129e;
    }

    protected final void m1762b(int i) {
        m1756g();
        if (i < 0) {
            i = 0;
        }
        this.f1133i = i;
    }

    protected final int m1763c() {
        return this.f1130f;
    }

    protected final int m1764d() {
        return this.f1131g;
    }

    protected final int m1765e() {
        m1756g();
        return this.f1132h;
    }

    protected final int m1766f() {
        m1756g();
        return this.f1133i;
    }
}
