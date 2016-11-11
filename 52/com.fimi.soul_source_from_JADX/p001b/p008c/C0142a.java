package p001b.p008c;

import it.p074a.p075a.C2799f;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Properties;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0115j;
import p001b.p002b.C0116p;
import p001b.p002b.C0125b;
import p001b.p002b.C0138m;

/* renamed from: b.c.a */
public abstract class C0142a implements C0138m {
    public static final String f160a = "suite";
    static int f161b;
    static boolean f162c;
    private static Properties f163e;
    boolean f164d;

    static {
        f161b = C2799f.f14263a;
        f162c = true;
        f161b = C0142a.m317a("maxmessage", f161b);
    }

    public C0142a() {
        this.f164d = true;
    }

    public static int m317a(String str, int i) {
        String h = C0142a.m327h(str);
        if (h != null) {
            try {
                i = Integer.parseInt(h);
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public static String m318a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return C0142a.m328i(stringWriter.toString());
    }

    protected static Properties m319a() {
        if (f163e == null) {
            f163e = new Properties();
            f163e.put("loading", "true");
            f163e.put("filterstack", "true");
            C0142a.m326g();
        }
        return f163e;
    }

    public static void m320a(String str, String str2) {
        C0142a.m319a().put(str, str2);
    }

    protected static void m321a(Properties properties) {
        f163e = properties;
    }

    public static void m322b() {
        OutputStream fileOutputStream = new FileOutputStream(C0142a.m325f());
        try {
            C0142a.m319a().store(fileOutputStream, C2915a.f14760f);
        } finally {
            fileOutputStream.close();
        }
    }

    public static String m323e(String str) {
        return (f161b == -1 || str.length() <= f161b) ? str : str.substring(0, f161b) + "...";
    }

    protected static boolean m324e() {
        return (C0142a.m327h("filterstack").equals("true") && f162c) ? false : true;
    }

    private static File m325f() {
        return new File(System.getProperty("user.home"), "junit.properties");
    }

    private static void m326g() {
        Throwable th;
        InputStream inputStream = null;
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(C0142a.m325f());
            try {
                C0142a.m321a(new Properties(C0142a.m319a()));
                C0142a.m319a().load(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                inputStream = fileInputStream;
                th = th3;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static String m327h(String str) {
        return C0142a.m319a().getProperty(str);
    }

    public static String m328i(String str) {
        if (C0142a.m324e()) {
            return str;
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return stringWriter.toString();
                }
                if (!C0142a.m329j(readLine)) {
                    printWriter.println(readLine);
                }
            } catch (Exception e) {
                return str;
            }
        }
    }

    static boolean m329j(String str) {
        String[] strArr = new String[]{"junit.framework.TestCase", "junit.framework.TestResult", "junit.framework.TestSuite", "junit.framework.Assert.", "junit.swingui.TestRunner", "junit.awtui.TestRunner", "junit.textui.TestRunner", "java.lang.reflect.Method.invoke("};
        for (String indexOf : strArr) {
            if (str.indexOf(indexOf) > 0) {
                return true;
            }
        }
        return false;
    }

    public String m330a(long j) {
        return NumberFormat.getInstance().format(((double) j) / 1000.0d);
    }

    protected String m331a(String[] strArr) {
        String str = null;
        int i = 0;
        while (i < strArr.length) {
            int i2;
            String str2;
            int i3;
            if (strArr[i].equals("-noloading")) {
                m337a(false);
                i2 = i;
                str2 = str;
                i3 = i2;
            } else if (strArr[i].equals("-nofilterstack")) {
                f162c = false;
                i2 = i;
                str2 = str;
                i3 = i2;
            } else if (strArr[i].equals("-c")) {
                if (strArr.length > i + 1) {
                    str = m342d(strArr[i + 1]);
                } else {
                    System.out.println("Missing Test class name");
                }
                i2 = i + 1;
                str2 = str;
                i3 = i2;
            } else {
                i2 = i;
                str2 = strArr[i];
                i3 = i2;
            }
            i2 = i3 + 1;
            str = str2;
            i = i2;
        }
        return str;
    }

    public abstract void m332a(int i, C0115j c0115j, Throwable th);

    public synchronized void m333a(C0115j c0115j) {
        m339b(c0115j.toString());
    }

    public synchronized void m334a(C0115j c0115j, C0125b c0125b) {
        m332a(2, c0115j, c0125b);
    }

    public synchronized void m335a(C0115j c0115j, Throwable th) {
        m332a(1, c0115j, th);
    }

    public abstract void m336a(String str);

    public void m337a(boolean z) {
        this.f164d = z;
    }

    public synchronized void m338b(C0115j c0115j) {
        m336a(c0115j.toString());
    }

    public abstract void m339b(String str);

    public C0115j m340c(String str) {
        if (str.length() <= 0) {
            m341c();
            return null;
        }
        try {
            Class g = m345g(str);
            try {
                Method method = g.getMethod(f160a, new Class[0]);
                if (Modifier.isStatic(method.getModifiers())) {
                    try {
                        C0115j c0115j = (C0115j) method.invoke(null, new Object[0]);
                        if (c0115j == null) {
                            return c0115j;
                        }
                        m341c();
                        return c0115j;
                    } catch (InvocationTargetException e) {
                        m344f("Failed to invoke suite():" + e.getTargetException().toString());
                        return null;
                    } catch (IllegalAccessException e2) {
                        m344f("Failed to invoke suite():" + e2.toString());
                        return null;
                    }
                }
                m344f("Suite() method must be static");
                return null;
            } catch (Exception e3) {
                m341c();
                return new C0116p(g);
            }
        } catch (ClassNotFoundException e4) {
            String message = e4.getMessage();
            if (message != null) {
                str = message;
            }
            m344f("Class not found \"" + str + "\"");
            return null;
        } catch (Exception e5) {
            m344f("Error: " + e5.toString());
            return null;
        }
    }

    protected void m341c() {
    }

    public String m342d(String str) {
        return str.startsWith("Default package for") ? str.substring(str.lastIndexOf(".") + 1) : str;
    }

    protected boolean m343d() {
        return C0142a.m327h("loading").equals("true") && this.f164d;
    }

    protected abstract void m344f(String str);

    protected Class<?> m345g(String str) {
        return Class.forName(str);
    }
}
