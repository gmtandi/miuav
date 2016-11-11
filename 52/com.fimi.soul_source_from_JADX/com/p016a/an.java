package com.p016a;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.an */
class an {
    an() {
    }

    static String m1045a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "dex";
    }

    static String m1046a(Context context, C0261v c0261v, gd gdVar) {
        List c = c0261v.m2052c(au.m1089b(gdVar.m1938a(), "copy"), new au());
        if (c == null || c.size() == 0) {
            return null;
        }
        an.m1053a(c);
        int i = 0;
        while (i < c.size()) {
            av avVar = (av) c.get(i);
            if (an.m1054a(context, c0261v, avVar.m1096a(), gdVar)) {
                try {
                    an.m1050a(context, c0261v, gdVar, new aw(an.m1057b(gdVar.m1938a(), gdVar.m1940b()), avVar.m1098b(), gdVar.m1938a(), gdVar.m1940b(), avVar.m1101e()).m1110a("usedex").m1109a(), an.m1047a(context, avVar.m1096a()));
                    return avVar.m1101e();
                } catch (Throwable th) {
                    C0247g.m1917a(th, "DexFileManager", "loadAvailableDynamicSDKFile");
                }
            } else {
                an.m1051a(context, c0261v, avVar.m1096a());
                i++;
            }
        }
        return null;
    }

    static String m1047a(Context context, String str) {
        return an.m1045a(context) + File.separator + str;
    }

    static String m1048a(Context context, String str, String str2) {
        return an.m1047a(context, an.m1057b(str, str2));
    }

    static String m1049a(String str) {
        return str + ".dex";
    }

    static void m1050a(Context context, C0261v c0261v, gd gdVar, av avVar, String str) {
        InputStream fileInputStream;
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            String a = gdVar.m1938a();
            an.m1058b(context, c0261v, avVar.m1096a());
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileOutputStream = new FileOutputStream(new File(an.m1048a(context, a, gdVar.m1940b())), true);
                try {
                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    ap.m1062a(c0261v, avVar, au.m1088b(avVar.m1096a()));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    fileOutputStream2 = fileInputStream;
                    try {
                        throw e2;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileOutputStream2;
                        fileOutputStream2 = fileOutputStream;
                    }
                } catch (IOException e5) {
                    e32 = e5;
                    fileOutputStream2 = fileOutputStream;
                    throw e32;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream2 = fileOutputStream;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                fileOutputStream = null;
                Object obj = fileInputStream;
                throw e2;
            } catch (IOException e8) {
                e32 = e8;
                throw e32;
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        } catch (FileNotFoundException e9) {
            e2 = e9;
            fileOutputStream = null;
            throw e2;
        } catch (IOException e10) {
            e32 = e10;
            fileInputStream = null;
            throw e32;
        } catch (Throwable th5) {
            th = th5;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    static void m1051a(Context context, C0261v c0261v, String str) {
        an.m1060c(context, c0261v, str);
    }

    public static void m1052a(C0261v c0261v, Context context, gd gdVar) {
        C0239w auVar = new au();
        String a = gdVar.m1938a();
        String b = an.m1057b(a, gdVar.m1940b());
        av a2 = ap.m1061a(c0261v, b);
        if (a2 != null) {
            an.m1058b(context, c0261v, b);
            List c = c0261v.m2052c(au.m1086a(a, a2.m1101e()), auVar);
            if (c != null && c.size() > 0) {
                av avVar = (av) c.get(0);
                avVar.m1097a("errorstatus");
                ap.m1062a(c0261v, avVar, au.m1088b(avVar.m1096a()));
                File file = new File(an.m1047a(context, avVar.m1096a()));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    static void m1053a(List<av> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                av avVar = (av) list.get(i);
                av avVar2 = (av) list.get(i2);
                if (ay.m1111a(avVar2.m1101e(), avVar.m1101e()) > 0) {
                    list.set(i, avVar2);
                    list.set(i2, avVar);
                }
            }
        }
    }

    static boolean m1054a(Context context, C0261v c0261v, String str, gd gdVar) {
        return an.m1055a(c0261v, str, an.m1047a(context, str), gdVar);
    }

    static boolean m1055a(C0261v c0261v, String str, String str2, gd gdVar) {
        av a = ap.m1061a(c0261v, str);
        return a != null && gdVar.m1940b().equals(a.m1100d()) && an.m1056a(str2, a.m1098b());
    }

    static boolean m1056a(String str, String str2) {
        String a = fz.m1908a(str);
        return a != null && a.equalsIgnoreCase(str2);
    }

    static String m1057b(String str, String str2) {
        return fz.m1911b(str + str2) + ".jar";
    }

    static void m1058b(Context context, C0261v c0261v, String str) {
        an.m1060c(context, c0261v, str);
        an.m1060c(context, c0261v, an.m1049a(str));
    }

    static void m1059b(Context context, String str, String str2) {
        new ao(context, str, str2).start();
    }

    private static void m1060c(Context context, C0261v c0261v, String str) {
        File file = new File(an.m1047a(context, str));
        if (file.exists()) {
            file.delete();
        }
        c0261v.m2048a(au.m1088b(str), new au());
    }
}
