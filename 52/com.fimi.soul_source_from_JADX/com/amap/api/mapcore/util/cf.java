package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.p122a.p123a.C2915a;

public class cf extends ci {
    private static boolean f2333a;
    private String[] f2334b;
    private int f2335c;
    private boolean f2336d;
    private int f2337e;

    static {
        f2333a = true;
    }

    protected cf(int i) {
        super(i);
        this.f2334b = new String[10];
        this.f2335c = 0;
        this.f2336d = false;
        this.f2337e = 0;
    }

    private void m3867b(String str) {
        try {
            if (this.f2335c > 9) {
                this.f2335c = 0;
            }
            this.f2334b[this.f2335c] = str;
            this.f2335c++;
        } catch (Throwable th) {
            cb.m3809a(th, "ANRWriter", "addData");
        }
    }

    private String m3868d() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.f2335c;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.f2334b[i]);
                i++;
            }
            for (i = 0; i < this.f2335c; i++) {
                stringBuilder.append(this.f2334b[i]);
            }
        } catch (Throwable th) {
            cb.m3809a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    protected String m3869a(List<bv> list) {
        InputStream fileInputStream;
        db dbVar;
        InputStream inputStream;
        Throwable e;
        String str;
        String str2;
        InputStream inputStream2 = null;
        db dbVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    dbVar2 = new db(fileInputStream, dc.f2411a);
                    Object obj = null;
                    while (true) {
                        try {
                            String str3;
                            Object obj2;
                            String a = dbVar2.m4000a();
                            if (a.contains("pid")) {
                                while (!a.contains("\"main\"")) {
                                    a = dbVar2.m4000a();
                                }
                                str3 = a;
                                obj2 = 1;
                            } else {
                                str3 = a;
                                obj2 = obj;
                            }
                            obj = str3.equals(C2915a.f14760f) ? null : obj2;
                            if (obj != null) {
                                m3867b(str3);
                                if (this.f2337e == 5) {
                                    break;
                                } else if (this.f2336d) {
                                    this.f2337e++;
                                } else {
                                    for (bv bvVar : list) {
                                        this.f2336d = m3861a(bvVar.m3768e(), str3);
                                        if (this.f2336d) {
                                            m3858a(bvVar);
                                        }
                                    }
                                }
                            }
                        } catch (EOFException e2) {
                        } catch (FileNotFoundException e3) {
                            dbVar = dbVar2;
                            inputStream = fileInputStream;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (dbVar2 != null) {
                        try {
                            dbVar2.close();
                        } catch (Throwable e5) {
                            cb.m3809a(e5, "ANRWriter", "initLog1");
                        } catch (Throwable e52) {
                            cb.m3809a(e52, "ANRWriter", "initLog2");
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e52 = e6;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            cb.m3809a(e52, str, str2);
                            return this.f2336d ? null : m3868d();
                        } catch (Throwable th) {
                            e52 = th;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            cb.m3809a(e52, str, str2);
                            if (this.f2336d) {
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    dbVar = null;
                    inputStream = fileInputStream;
                    if (dbVar != null) {
                        try {
                            dbVar.close();
                        } catch (Throwable e522) {
                            cb.m3809a(e522, "ANRWriter", "initLog1");
                        } catch (Throwable e5222) {
                            cb.m3809a(e5222, "ANRWriter", "initLog2");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e5222 = e8;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            cb.m3809a(e5222, str, str2);
                            if (this.f2336d) {
                            }
                        } catch (Throwable th2) {
                            e5222 = th2;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            cb.m3809a(e5222, str, str2);
                            if (this.f2336d) {
                            }
                        }
                    }
                    if (this.f2336d) {
                    }
                } catch (IOException e9) {
                    e5222 = e9;
                    dbVar2 = null;
                    try {
                        cb.m3809a(e5222, "ANRWriter", "initLog");
                        if (dbVar2 != null) {
                            try {
                                dbVar2.close();
                            } catch (Throwable e52222) {
                                cb.m3809a(e52222, "ANRWriter", "initLog1");
                            } catch (Throwable e522222) {
                                cb.m3809a(e522222, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e522222 = e10;
                                str = "ANRWriter";
                                str2 = "initLog3";
                                cb.m3809a(e522222, str, str2);
                                if (this.f2336d) {
                                }
                            } catch (Throwable th3) {
                                e522222 = th3;
                                str = "ANRWriter";
                                str2 = "initLog4";
                                cb.m3809a(e522222, str, str2);
                                if (this.f2336d) {
                                }
                            }
                        }
                        if (this.f2336d) {
                        }
                    } catch (Throwable th4) {
                        e522222 = th4;
                        if (dbVar2 != null) {
                            try {
                                dbVar2.close();
                            } catch (Throwable e11) {
                                cb.m3809a(e11, "ANRWriter", "initLog1");
                            } catch (Throwable e112) {
                                cb.m3809a(e112, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e1122) {
                                cb.m3809a(e1122, "ANRWriter", "initLog3");
                            } catch (Throwable e11222) {
                                cb.m3809a(e11222, "ANRWriter", "initLog4");
                            }
                        }
                        throw e522222;
                    }
                } catch (Throwable th5) {
                    e522222 = th5;
                    dbVar2 = null;
                    if (dbVar2 != null) {
                        dbVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e522222;
                }
                if (this.f2336d) {
                }
            }
            if (null != null) {
                try {
                    dbVar2.close();
                } catch (Throwable e12) {
                    cb.m3809a(e12, "ANRWriter", "initLog1");
                } catch (Throwable e122) {
                    cb.m3809a(e122, "ANRWriter", "initLog2");
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable e5222222) {
                    cb.m3809a(e5222222, "ANRWriter", "initLog3");
                } catch (Throwable e52222222) {
                    cb.m3809a(e52222222, "ANRWriter", "initLog4");
                }
            }
            return null;
        } catch (FileNotFoundException e13) {
            dbVar = null;
            inputStream = null;
            if (dbVar != null) {
                dbVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.f2336d) {
            }
        } catch (IOException e14) {
            e52222222 = e14;
            dbVar2 = null;
            fileInputStream = null;
            cb.m3809a(e52222222, "ANRWriter", "initLog");
            if (dbVar2 != null) {
                dbVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (this.f2336d) {
            }
        } catch (Throwable th6) {
            e52222222 = th6;
            dbVar2 = null;
            fileInputStream = null;
            if (dbVar2 != null) {
                dbVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e52222222;
        }
    }

    protected boolean m3870a(Context context) {
        if (bq.m3703m(context) != 1 || !f2333a) {
            return false;
        }
        f2333a = false;
        synchronized (Looper.getMainLooper()) {
            cv cvVar = new cv(context);
            cw a = cvVar.m3930a();
            if (a == null) {
                return true;
            } else if (a.m3937c()) {
                a.m3936c(false);
                cvVar.m3931a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
