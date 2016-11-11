package it.p074a.p075a.p141b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* renamed from: it.a.a.b.a */
public class C2786a extends C2785b {
    private static final Object f14184a;
    private static C2786a f14185b;

    static {
        f14184a = new Object();
        f14185b = null;
    }

    private C2786a() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("textualexts")));
            while (true) {
                try {
                    break;
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        break;
                    }
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    while (true) {
                        if (stringTokenizer.hasMoreTokens()) {
                            m15860b(stringTokenizer.nextToken());
                        }
                    }
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 != null) {
                        break;
                    }
                    StringTokenizer stringTokenizer2 = new StringTokenizer(readLine2);
                    while (true) {
                        if (stringTokenizer2.hasMoreTokens()) {
                            m15860b(stringTokenizer2.nextToken());
                        }
                    }
                } catch (Exception e) {
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader2 = bufferedReader;
                    th = th3;
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (Exception e2) {
            bufferedReader = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable th4) {
                }
            }
        } catch (Throwable th5) {
            th = th5;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Throwable th6) {
                }
            }
            throw th;
        }
    }

    public static C2786a m15863a() {
        synchronized (f14184a) {
            if (f14185b == null) {
                f14185b = new C2786a();
            }
        }
        return f14185b;
    }
}
