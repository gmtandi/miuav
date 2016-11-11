package com.p016a;

import android.text.TextUtils;
import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.a.fz */
public class fz {
    public static String m1908a(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        String str2 = null;
        FileInputStream fileInputStream2 = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[Opcodes.ACC_STRICT];
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    str2 = gf.m1965c(instance.digest());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                            C0247g.m1917a(th4, "MD5", "getMd5FromFile");
                        }
                    }
                } else if (str2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th42) {
                        C0247g.m1917a(th42, "MD5", "getMd5FromFile");
                    }
                }
            } else if (str2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable th422) {
                    C0247g.m1917a(th422, "MD5", "getMd5FromFile");
                }
            }
        } catch (Throwable th4222) {
            fileInputStream = str2;
            th2 = th4222;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th2;
        }
        return str2;
    }

    public static String m1909a(byte[] bArr) {
        return gf.m1965c(fz.m1912b(bArr));
    }

    public static byte[] m1910a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            C0247g.m1917a(e, "MD5", "getMd5Bytes");
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "MD5", "getMd5Bytes1");
        }
        return bArr2;
    }

    public static String m1911b(String str) {
        return str == null ? null : gf.m1965c(fz.m1914d(str));
    }

    private static byte[] m1912b(byte[] bArr) {
        return fz.m1910a(bArr, "MD5");
    }

    public static String m1913c(String str) {
        return gf.m1966d(fz.m1915e(str));
    }

    public static byte[] m1914d(String str) {
        try {
            return fz.m1916f(str);
        } catch (Throwable e) {
            C0247g.m1917a(e, "MD5", "getMd5Bytes");
            return new byte[0];
        } catch (Throwable e2) {
            C0247g.m1917a(e2, "MD5", "getMd5Bytes");
            return new byte[0];
        } catch (Throwable e22) {
            C0247g.m1917a(e22, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    private static byte[] m1915e(String str) {
        try {
            return fz.m1916f(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return new byte[0];
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] m1916f(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes(C1142e.f5201a));
        return instance.digest();
    }
}
