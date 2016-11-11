package com.fimi.kernel.p084e;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.e.a */
public class C1162a {
    private static int f5260a;
    private static String f5261b;

    static {
        f5260a = SmileConstants.TOKEN_PREFIX_TINY_UNICODE;
        f5261b = "AES";
    }

    public static boolean m7968a(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        FileInputStream fileInputStream;
        IOException e2;
        Throwable th;
        Exception e3;
        FileOutputStream fileOutputStream2 = null;
        FileInputStream fileInputStream2;
        try {
            byte[] a;
            File file = new File(str2);
            fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[((int) file.length())];
                fileInputStream2.read(bArr);
                a = C1162a.m7969a(str, bArr);
                fileOutputStream = new FileOutputStream(str3);
            } catch (FileNotFoundException e4) {
                e = e4;
                fileOutputStream = null;
                fileInputStream = fileInputStream2;
                try {
                    e.printStackTrace();
                    try {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        fileInputStream2.close();
                        fileOutputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e22 = e6;
                try {
                    e22.printStackTrace();
                    try {
                        fileInputStream2.close();
                        fileOutputStream2.close();
                        return false;
                    } catch (IOException e222) {
                        e222.printStackTrace();
                        return false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream2.close();
                    fileOutputStream2.close();
                    throw th;
                }
            } catch (Exception e7) {
                e3 = e7;
                e3.printStackTrace();
                try {
                    fileInputStream2.close();
                    fileOutputStream2.close();
                    return false;
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                    return false;
                }
            }
            try {
                fileOutputStream.write(a);
                if (fileInputStream2 == null || fileOutputStream == null) {
                    return true;
                }
                try {
                    fileInputStream2.close();
                    fileOutputStream.close();
                    return true;
                } catch (IOException e52) {
                    e52.printStackTrace();
                    return true;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                fileInputStream = fileInputStream2;
                e.printStackTrace();
                if (!(fileInputStream == null || fileOutputStream == null)) {
                    fileInputStream.close();
                    fileOutputStream.close();
                }
                return false;
            } catch (IOException e9) {
                e2222 = e9;
                fileOutputStream2 = fileOutputStream;
                e2222.printStackTrace();
                if (!(fileInputStream2 == null || fileOutputStream2 == null)) {
                    fileInputStream2.close();
                    fileOutputStream2.close();
                }
                return false;
            } catch (Exception e10) {
                e3 = e10;
                fileOutputStream2 = fileOutputStream;
                e3.printStackTrace();
                if (!(fileInputStream2 == null || fileOutputStream2 == null)) {
                    fileInputStream2.close();
                    fileOutputStream2.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream2 = fileOutputStream;
                if (!(fileInputStream2 == null || fileOutputStream2 == null)) {
                    fileInputStream2.close();
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e11) {
            e = e11;
            fileOutputStream = null;
            e.printStackTrace();
            fileInputStream.close();
            fileOutputStream.close();
            return false;
        } catch (IOException e12) {
            e2222 = e12;
            fileInputStream2 = null;
            e2222.printStackTrace();
            fileInputStream2.close();
            fileOutputStream2.close();
            return false;
        } catch (Exception e13) {
            e3 = e13;
            fileInputStream2 = null;
            e3.printStackTrace();
            fileInputStream2.close();
            fileOutputStream2.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream2 = null;
            fileInputStream2.close();
            fileOutputStream2.close();
            throw th;
        }
    }

    public static byte[] m7969a(String str, byte[] bArr) {
        return C1162a.m7971a(C1162a.m7970a(str.getBytes()), bArr);
    }

    private static byte[] m7970a(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance(f5261b);
        SecureRandom instance2 = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        instance2.setSeed(bArr);
        instance.init(f5260a, instance2);
        return instance.generateKey().getEncoded();
    }

    private static byte[] m7971a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, f5261b);
        Cipher instance = Cipher.getInstance(f5261b);
        instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }

    public static boolean m7972b(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        FileInputStream fileInputStream2;
        IOException e2;
        Throwable th;
        Exception e3;
        FileOutputStream fileOutputStream2 = null;
        try {
            byte[] b;
            File file = new File(str2);
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[((int) file.length())];
                fileInputStream.read(bArr);
                b = C1162a.m7973b(str, bArr);
                fileOutputStream = new FileOutputStream(str3);
            } catch (FileNotFoundException e4) {
                e = e4;
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                try {
                    e.printStackTrace();
                    try {
                        fileInputStream2.close();
                        fileOutputStream.close();
                        return false;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        fileInputStream.close();
                        fileOutputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e22 = e6;
                try {
                    e22.printStackTrace();
                    try {
                        fileInputStream.close();
                        fileOutputStream2.close();
                        return false;
                    } catch (IOException e222) {
                        e222.printStackTrace();
                        return false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream.close();
                    fileOutputStream2.close();
                    throw th;
                }
            } catch (Exception e7) {
                e3 = e7;
                e3.printStackTrace();
                try {
                    fileInputStream.close();
                    fileOutputStream2.close();
                    return false;
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                    return false;
                }
            }
            try {
                fileOutputStream.write(b);
                if (fileInputStream == null || fileOutputStream == null) {
                    return true;
                }
                try {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                } catch (IOException e52) {
                    e52.printStackTrace();
                    return true;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                if (!(fileInputStream2 == null || fileOutputStream == null)) {
                    fileInputStream2.close();
                    fileOutputStream.close();
                }
                return false;
            } catch (IOException e9) {
                e2222 = e9;
                fileOutputStream2 = fileOutputStream;
                e2222.printStackTrace();
                if (!(fileInputStream == null || fileOutputStream2 == null)) {
                    fileInputStream.close();
                    fileOutputStream2.close();
                }
                return false;
            } catch (Exception e10) {
                e3 = e10;
                fileOutputStream2 = fileOutputStream;
                e3.printStackTrace();
                if (!(fileInputStream == null || fileOutputStream2 == null)) {
                    fileInputStream.close();
                    fileOutputStream2.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream2 = fileOutputStream;
                if (!(fileInputStream == null || fileOutputStream2 == null)) {
                    fileInputStream.close();
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e11) {
            e = e11;
            fileOutputStream = null;
            e.printStackTrace();
            fileInputStream2.close();
            fileOutputStream.close();
            return false;
        } catch (IOException e12) {
            e2222 = e12;
            fileInputStream = null;
            e2222.printStackTrace();
            fileInputStream.close();
            fileOutputStream2.close();
            return false;
        } catch (Exception e13) {
            e3 = e13;
            fileInputStream = null;
            e3.printStackTrace();
            fileInputStream.close();
            fileOutputStream2.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            fileInputStream.close();
            fileOutputStream2.close();
            throw th;
        }
    }

    public static byte[] m7973b(String str, byte[] bArr) {
        return C1162a.m7974b(C1162a.m7970a(str.getBytes()), bArr);
    }

    private static byte[] m7974b(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, f5261b);
        Cipher instance = Cipher.getInstance(f5261b);
        instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
        return instance.doFinal(bArr2);
    }
}
