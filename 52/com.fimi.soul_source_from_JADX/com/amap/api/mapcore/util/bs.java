package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class bs {
    public static String m3725a(String str) {
        Throwable th;
        Throwable th2;
        String str2 = null;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[Opcodes.ACC_STRICT];
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    fileInputStream2 = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    str2 = bx.m3785d(instance.digest());
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable th4) {
                            cb.m3809a(th4, "MD5", "getMd5FromFile");
                        }
                    }
                } else if (str2 != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th42) {
                        cb.m3809a(th42, "MD5", "getMd5FromFile");
                    }
                }
            } else if (str2 != null) {
                try {
                    fileInputStream.close();
                } catch (Throwable th422) {
                    cb.m3809a(th422, "MD5", "getMd5FromFile");
                }
            }
        } catch (Throwable th4222) {
            fileInputStream2 = str2;
            th2 = th4222;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th2;
        }
        return str2;
    }

    public static String m3726a(byte[] bArr) {
        return bx.m3785d(m3729b(bArr));
    }

    public static byte[] m3727a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            cb.m3809a(e, "MD5", "getMd5Bytes");
        } catch (Throwable e2) {
            cb.m3809a(e2, "MD5", "getMd5Bytes1");
        }
        return bArr2;
    }

    public static String m3728b(String str) {
        return str == null ? null : bx.m3785d(m3731d(str));
    }

    private static byte[] m3729b(byte[] bArr) {
        return m3727a(bArr, "MD5");
    }

    public static String m3730c(String str) {
        return bx.m3786e(m3732e(str));
    }

    public static byte[] m3731d(String str) {
        try {
            return m3733f(str);
        } catch (Throwable e) {
            cb.m3809a(e, "MD5", "getMd5Bytes");
            return new byte[0];
        } catch (Throwable e2) {
            cb.m3809a(e2, "MD5", "getMd5Bytes");
            return new byte[0];
        } catch (Throwable e22) {
            cb.m3809a(e22, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    private static byte[] m3732e(String str) {
        try {
            return m3733f(str);
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

    private static byte[] m3733f(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bx.m3778a(str));
        return instance.digest();
    }
}
