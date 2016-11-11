package com.xiaomi.mistatistic.sdk;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import com.xiaomi.mistatistic.sdk.controller.C2588a;
import com.xiaomi.mistatistic.sdk.controller.C2593g;
import com.xiaomi.mistatistic.sdk.controller.C2601o;
import com.xiaomi.mistatistic.sdk.controller.C2603q;
import com.xiaomi.mistatistic.sdk.controller.C2605s;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.a */
public class C2579a implements UncaughtExceptionHandler {
    private static boolean f12921a;
    private static int f12922b;
    private final UncaughtExceptionHandler f12923c;

    static {
        f12921a = false;
        f12922b = 1;
    }

    public C2579a() {
        this.f12923c = null;
    }

    public C2579a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f12923c = uncaughtExceptionHandler;
    }

    public static void m14689a(int i) {
        f12922b = i;
    }

    public static void m14690a(Throwable th) {
        if (!f12921a) {
            return;
        }
        if (th == null) {
            throw new IllegalArgumentException("the throwable is null.");
        } else if (th.getStackTrace() != null && th.getStackTrace().length != 0) {
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String obj = stringWriter.toString();
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("app_id", C2588a.m14710b()));
            arrayList.add(new BasicNameValuePair("app_key", C2588a.m14711c()));
            arrayList.add(new BasicNameValuePair("device_uuid", C2593g.m14736a(C2588a.m14708a())));
            arrayList.add(new BasicNameValuePair("device_os", "Android " + VERSION.SDK_INT));
            arrayList.add(new BasicNameValuePair("device_model", Build.MODEL));
            arrayList.add(new BasicNameValuePair("app_version", C2588a.m14713e()));
            arrayList.add(new BasicNameValuePair("app_channel", C2588a.m14712d()));
            arrayList.add(new BasicNameValuePair("app_start_time", String.valueOf(System.currentTimeMillis())));
            arrayList.add(new BasicNameValuePair("app_crash_time", String.valueOf(System.currentTimeMillis())));
            arrayList.add(new BasicNameValuePair("crash_exception_type", th.getClass().getName() + ":" + th.getMessage()));
            arrayList.add(new BasicNameValuePair("crash_exception_desc", th instanceof OutOfMemoryError ? "OutOfMemoryError" : obj));
            arrayList.add(new BasicNameValuePair("crash_callstack", obj));
            try {
                new C2601o().m14769a("upload the exception " + C2603q.m14771a(C2588a.m14708a(), BuildSetting.isTest() ? "http://10.99.168.145:8097/micrash" : "https://data.mistat.xiaomi.com/micrash", arrayList));
            } catch (Throwable e) {
                new C2601o().m14770a("Error to upload the exception", e);
            }
        }
    }

    public static void m14691a(boolean z) {
        UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUncaughtExceptionHandler instanceof C2579a)) {
            if (z) {
                defaultUncaughtExceptionHandler = null;
            }
            Thread.setDefaultUncaughtExceptionHandler(new C2579a(defaultUncaughtExceptionHandler));
            f12921a = true;
        }
    }

    public static ArrayList m14692b() {
        ObjectInputStream objectInputStream;
        ArrayList arrayList;
        Object obj;
        Throwable e;
        ArrayList arrayList2 = new ArrayList();
        ObjectInputStream objectInputStream2 = null;
        try {
            ArrayList arrayList3;
            File filesDir = C2588a.m14708a().getFilesDir();
            if (filesDir != null) {
                File file = new File(filesDir, ".exception");
                if (file.isFile()) {
                    objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    try {
                        arrayList3 = (ArrayList) objectInputStream.readObject();
                        objectInputStream2 = objectInputStream;
                        if (objectInputStream2 == null) {
                            try {
                                objectInputStream2.close();
                                arrayList = arrayList3;
                                obj = null;
                            } catch (IOException e2) {
                                arrayList = arrayList3;
                                obj = null;
                            }
                        } else {
                            arrayList = arrayList3;
                            obj = null;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            new C2601o().m14770a(C2915a.f14760f, e);
                            obj = 1;
                            if (objectInputStream == null) {
                                try {
                                    objectInputStream.close();
                                    arrayList = arrayList2;
                                } catch (IOException e4) {
                                    arrayList = arrayList2;
                                }
                            } else {
                                arrayList = arrayList2;
                            }
                            if (obj != null) {
                                C2579a.m14694c();
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            e = th;
                            objectInputStream2 = objectInputStream;
                            if (objectInputStream2 != null) {
                                try {
                                    objectInputStream2.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw e;
                        }
                    }
                    if (obj != null) {
                        C2579a.m14694c();
                    }
                    return arrayList;
                }
            }
            arrayList3 = arrayList2;
            if (objectInputStream2 == null) {
                arrayList = arrayList3;
                obj = null;
            } else {
                objectInputStream2.close();
                arrayList = arrayList3;
                obj = null;
            }
        } catch (Exception e6) {
            e = e6;
            objectInputStream = null;
            new C2601o().m14770a(C2915a.f14760f, e);
            obj = 1;
            if (objectInputStream == null) {
                arrayList = arrayList2;
            } else {
                objectInputStream.close();
                arrayList = arrayList2;
            }
            if (obj != null) {
                C2579a.m14694c();
            }
            return arrayList;
        } catch (Throwable th2) {
            e = th2;
            if (objectInputStream2 != null) {
                objectInputStream2.close();
            }
            throw e;
        }
        if (obj != null) {
            C2579a.m14694c();
        }
        return arrayList;
    }

    public static void m14693b(Throwable th) {
        ObjectOutputStream objectOutputStream;
        Throwable e;
        ArrayList b = C2579a.m14692b();
        b.add(th);
        if (b.size() > 5) {
            b.remove(0);
        }
        try {
            objectOutputStream = new ObjectOutputStream(C2588a.m14708a().openFileOutput(".exception", 0));
            try {
                objectOutputStream.writeObject(b);
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    new C2601o().m14770a(C2915a.f14760f, e);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw e;
                }
            }
        } catch (IOException e6) {
            e = e6;
            objectOutputStream = null;
            new C2601o().m14770a(C2915a.f14760f, e);
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Throwable th3) {
            e = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw e;
        }
    }

    public static void m14694c() {
        new File(C2588a.m14708a().getFilesDir(), ".exception").delete();
    }

    public static int m14695d() {
        return f12922b;
    }

    public boolean m14696a() {
        if (System.currentTimeMillis() - C2605s.m14780a(C2588a.m14708a(), "crash_time", 0) > MiStatInterface.MIN_UPLOAD_INTERVAL) {
            C2605s.m14783b(C2588a.m14708a(), "crash_count", 1);
            C2605s.m14784b(C2588a.m14708a(), "crash_time", System.currentTimeMillis());
        } else {
            int a = C2605s.m14779a(C2588a.m14708a(), "crash_count", 0);
            if (a == 0) {
                C2605s.m14784b(C2588a.m14708a(), "crash_time", System.currentTimeMillis());
            }
            a++;
            C2605s.m14783b(C2588a.m14708a(), "crash_count", a);
            if (a > 10) {
                return true;
            }
        }
        return false;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (VERSION.SDK_INT >= 9) {
            StrictMode.setThreadPolicy(new Builder().build());
        }
        if (!MiStatInterface.shouldExceptionUploadImmediately()) {
            C2579a.m14693b(th);
        } else if (m14696a()) {
            new C2601o().m14769a("crazy crash...");
        } else {
            C2579a.m14690a(th);
        }
        if (this.f12923c != null) {
            this.f12923c.uncaughtException(thread, th);
        }
    }
}
