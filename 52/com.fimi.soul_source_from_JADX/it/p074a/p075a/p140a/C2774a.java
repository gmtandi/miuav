package it.p074a.p075a.p140a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: it.a.a.a.a */
class C2774a {
    static String f14149a;
    static char f14150b;

    static {
        f14149a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        f14150b = SignatureVisitor.INSTANCEOF;
    }

    C2774a() {
    }

    public static String m15816a(String str) {
        try {
            return new String(C2774a.m15822a(str.getBytes()), "ASCII");
        } catch (Throwable e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    public static String m15817a(String str, String str2) {
        try {
            try {
                return new String(C2774a.m15822a(str.getBytes(str2)), "ASCII");
            } catch (Throwable e) {
                throw new RuntimeException("ASCII is not supported!", e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException(new StringBuffer().append("Unsupported charset: ").append(str2).toString(), e2);
        }
    }

    public static void m15818a(File file, File file2) {
        OutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    C2774a.m15820a(fileInputStream, fileOutputStream);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void m15819a(File file, File file2, int i) {
        OutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    C2774a.m15821a(fileInputStream, fileOutputStream, i);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void m15820a(InputStream inputStream, OutputStream outputStream) {
        C2774a.m15821a(inputStream, outputStream, 0);
    }

    public static void m15821a(InputStream inputStream, OutputStream outputStream, int i) {
        OutputStream c2776c = new C2776c(outputStream, i);
        C2774a.m15829c(inputStream, c2776c);
        c2776c.m15831a();
    }

    public static byte[] m15822a(byte[] bArr) {
        return C2774a.m15823a(bArr, 0);
    }

    public static byte[] m15823a(byte[] bArr, int i) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            C2774a.m15821a(byteArrayInputStream, byteArrayOutputStream, i);
            try {
                byteArrayInputStream.close();
            } catch (Throwable th) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } catch (Throwable th3) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th4) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static String m15824b(String str) {
        try {
            return new String(C2774a.m15828b(str.getBytes("ASCII")));
        } catch (Throwable e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    public static String m15825b(String str, String str2) {
        try {
            try {
                return new String(C2774a.m15828b(str.getBytes("ASCII")), str2);
            } catch (Throwable e) {
                throw new RuntimeException(new StringBuffer().append("Unsupported charset: ").append(str2).toString(), e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException("ASCII is not supported!", e2);
        }
    }

    public static void m15826b(File file, File file2) {
        OutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream = null;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    C2774a.m15827b(fileInputStream, fileOutputStream);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = fileInputStream;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                inputStream = fileInputStream;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static void m15827b(InputStream inputStream, OutputStream outputStream) {
        C2774a.m15829c(new C2775b(inputStream), outputStream);
    }

    public static byte[] m15828b(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            C2774a.m15827b(byteArrayInputStream, byteArrayOutputStream);
            try {
                byteArrayInputStream.close();
            } catch (Throwable th) {
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } catch (Throwable th3) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th4) {
            }
            byteArrayOutputStream.close();
        }
    }

    private static void m15829c(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
