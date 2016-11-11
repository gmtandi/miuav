package com.xiaomi.push.log;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.misc.C2467b;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.push.log.b */
public class C2626b implements LoggerInterface {
    private static final SimpleDateFormat f13050a;
    private static C2467b f13051b;
    private static String f13052c;
    private static List<Pair<String, Throwable>> f13053f;
    private String f13054d;
    private Context f13055e;

    static {
        f13050a = new SimpleDateFormat("MM-dd HH:mm:ss aaa");
        f13051b = new C2467b(true);
        f13052c = "/MiPushLog";
        f13053f = Collections.synchronizedList(new ArrayList());
    }

    public C2626b(Context context) {
        this.f13055e = context;
        if (context.getApplicationContext() != null) {
            this.f13055e = context.getApplicationContext();
        }
        this.f13054d = this.f13055e.getPackageName();
    }

    private void m14888b() {
        String str;
        Throwable e;
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        String str2;
        BufferedWriter bufferedWriter = null;
        RandomAccessFile randomAccessFile2 = null;
        FileLock fileLock2 = null;
        BufferedWriter bufferedWriter2 = null;
        RandomAccessFile randomAccessFile3;
        FileLock lock;
        BufferedWriter bufferedWriter3;
        try {
            File file = new File(this.f13055e.getExternalFilesDir(null) + f13052c);
            if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                File file2 = new File(file, "log.lock");
                if (!file2.exists() || file2.isDirectory()) {
                    file2.createNewFile();
                }
                randomAccessFile3 = new RandomAccessFile(file2, "rw");
                try {
                    lock = randomAccessFile3.getChannel().lock();
                    try {
                        bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                        while (!f13053f.isEmpty()) {
                            try {
                                Pair pair = (Pair) f13053f.remove(0);
                                str = (String) pair.first;
                                if (pair.second != null) {
                                    str = (str + "\n") + Log.getStackTraceString((Throwable) pair.second);
                                }
                                bufferedWriter2.write(str + "\n");
                            } catch (Exception e2) {
                                e = e2;
                                bufferedWriter3 = bufferedWriter2;
                                fileLock = lock;
                                randomAccessFile = randomAccessFile3;
                            } catch (Throwable th) {
                                e = th;
                                bufferedWriter = bufferedWriter2;
                            }
                        }
                        bufferedWriter2.flush();
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                            bufferedWriter3 = bufferedWriter;
                        } else {
                            bufferedWriter3 = bufferedWriter2;
                        }
                        try {
                            file2 = new File(file, "log1.txt");
                            if (file2.length() >= 1048576) {
                                File file3 = new File(file, "log0.txt");
                                if (file3.exists() && file3.isFile()) {
                                    file3.delete();
                                }
                                file2.renameTo(file3);
                            }
                            if (bufferedWriter3 != null) {
                                try {
                                    bufferedWriter3.close();
                                } catch (Throwable e3) {
                                    Log.e(this.f13054d, C2915a.f14760f, e3);
                                }
                            }
                            if (lock != null && lock.isValid()) {
                                try {
                                    lock.release();
                                } catch (Throwable e32) {
                                    Log.e(this.f13054d, C2915a.f14760f, e32);
                                }
                            }
                            if (randomAccessFile3 != null) {
                                try {
                                    randomAccessFile3.close();
                                    return;
                                } catch (IOException e4) {
                                    e32 = e4;
                                    str = this.f13054d;
                                    str2 = C2915a.f14760f;
                                    Log.e(str, str2, e32);
                                    return;
                                }
                            }
                            return;
                        } catch (Exception e5) {
                            e32 = e5;
                            fileLock = lock;
                            randomAccessFile = randomAccessFile3;
                            try {
                                Log.e(this.f13054d, C2915a.f14760f, e32);
                                if (bufferedWriter3 != null) {
                                    try {
                                        bufferedWriter3.close();
                                    } catch (Throwable e322) {
                                        Log.e(this.f13054d, C2915a.f14760f, e322);
                                    }
                                }
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (Throwable e3222) {
                                        Log.e(this.f13054d, C2915a.f14760f, e3222);
                                    }
                                }
                                if (randomAccessFile == null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e6) {
                                        e3222 = e6;
                                        str = this.f13054d;
                                        str2 = C2915a.f14760f;
                                        Log.e(str, str2, e3222);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                e3222 = th2;
                                lock = fileLock;
                                randomAccessFile3 = randomAccessFile;
                                bufferedWriter = bufferedWriter3;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (Throwable e7) {
                                        Log.e(this.f13054d, C2915a.f14760f, e7);
                                    }
                                }
                                try {
                                    lock.release();
                                } catch (Throwable e72) {
                                    Log.e(this.f13054d, C2915a.f14760f, e72);
                                }
                                if (randomAccessFile3 != null) {
                                    try {
                                        randomAccessFile3.close();
                                    } catch (Throwable e722) {
                                        Log.e(this.f13054d, C2915a.f14760f, e722);
                                    }
                                }
                                throw e3222;
                            }
                        } catch (Throwable th3) {
                            e3222 = th3;
                            bufferedWriter = bufferedWriter3;
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                            if (lock != null && lock.isValid()) {
                                lock.release();
                            }
                            if (randomAccessFile3 != null) {
                                randomAccessFile3.close();
                            }
                            throw e3222;
                        }
                    } catch (Exception e8) {
                        e3222 = e8;
                        bufferedWriter3 = bufferedWriter;
                        randomAccessFile = randomAccessFile3;
                        fileLock = lock;
                        Log.e(this.f13054d, C2915a.f14760f, e3222);
                        if (bufferedWriter3 != null) {
                            bufferedWriter3.close();
                        }
                        fileLock.release();
                        if (randomAccessFile == null) {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th4) {
                        e3222 = th4;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        lock.release();
                        if (randomAccessFile3 != null) {
                            randomAccessFile3.close();
                        }
                        throw e3222;
                    }
                } catch (Exception e9) {
                    e3222 = e9;
                    bufferedWriter3 = bufferedWriter;
                    randomAccessFile = randomAccessFile3;
                    Log.e(this.f13054d, C2915a.f14760f, e3222);
                    if (bufferedWriter3 != null) {
                        bufferedWriter3.close();
                    }
                    fileLock.release();
                    if (randomAccessFile == null) {
                        randomAccessFile.close();
                    }
                } catch (Throwable th5) {
                    e3222 = th5;
                    Object obj = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    lock.release();
                    if (randomAccessFile3 != null) {
                        randomAccessFile3.close();
                    }
                    throw e3222;
                }
            }
            Log.w(this.f13054d, "Create mipushlog directory fail.");
            if (bufferedWriter != null) {
                try {
                    bufferedWriter2.close();
                } catch (Throwable e10) {
                    Log.e(this.f13054d, C2915a.f14760f, e10);
                }
            }
            if (bufferedWriter != null && bufferedWriter.isValid()) {
                try {
                    fileLock2.release();
                } catch (Throwable e7222) {
                    Log.e(this.f13054d, C2915a.f14760f, e7222);
                }
            }
            if (bufferedWriter != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e11) {
                    e3222 = e11;
                    str = this.f13054d;
                    str2 = C2915a.f14760f;
                }
            }
        } catch (Exception e12) {
            e3222 = e12;
            bufferedWriter3 = bufferedWriter;
            Object obj2 = bufferedWriter;
            Log.e(this.f13054d, C2915a.f14760f, e3222);
            if (bufferedWriter3 != null) {
                bufferedWriter3.close();
            }
            fileLock.release();
            if (randomAccessFile == null) {
                randomAccessFile.close();
            }
        } catch (Throwable th6) {
            e3222 = th6;
            lock = bufferedWriter;
            randomAccessFile3 = bufferedWriter;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            lock.release();
            if (randomAccessFile3 != null) {
                randomAccessFile3.close();
            }
            throw e3222;
        }
    }

    public final void log(String str) {
        log(str, null);
    }

    public final void log(String str, Throwable th) {
        f13053f.add(new Pair(String.format("%1$s %2$s %3$s ", new Object[]{f13050a.format(new Date()), this.f13054d, str}), th));
        f13051b.m14141a(new C2627c(this));
    }

    public final void setTag(String str) {
        this.f13054d = str;
    }
}
