package com.p016a;

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

/* renamed from: com.a.k */
public class C0252k extends C0251r {
    private static boolean f1290a;
    private String[] f1291b;
    private int f1292c;
    private boolean f1293d;
    private int f1294e;

    static {
        f1290a = true;
    }

    protected C0252k(int i) {
        super(i);
        this.f1291b = new String[10];
        this.f1292c = 0;
        this.f1293d = false;
        this.f1294e = 0;
    }

    private void m2019b(String str) {
        try {
            if (this.f1292c > 9) {
                this.f1292c = 0;
            }
            this.f1291b[this.f1292c] = str;
            this.f1292c++;
        } catch (Throwable th) {
            C0247g.m1917a(th, "ANRWriter", "addData");
        }
    }

    private String m2020d() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.f1292c;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.f1291b[i]);
                i++;
            }
            for (i = 0; i < this.f1292c; i++) {
                stringBuilder.append(this.f1291b[i]);
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "ANRWriter", "getLogInfo");
        }
        return stringBuilder.toString();
    }

    protected String m2021a(List<gd> list) {
        InputStream fileInputStream;
        bh bhVar;
        InputStream inputStream;
        Throwable e;
        String str;
        String str2;
        InputStream inputStream2 = null;
        bh bhVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    bhVar2 = new bh(fileInputStream, bj.f629a);
                    Object obj = null;
                    while (true) {
                        try {
                            String str3;
                            Object obj2;
                            String a = bhVar2.m1166a();
                            if (a.contains("pid")) {
                                while (!a.contains("\"main\"")) {
                                    a = bhVar2.m1166a();
                                }
                                str3 = a;
                                obj2 = 1;
                            } else {
                                str3 = a;
                                obj2 = obj;
                            }
                            obj = str3.equals(C2915a.f14760f) ? null : obj2;
                            if (obj != null) {
                                m2019b(str3);
                                if (this.f1294e == 5) {
                                    break;
                                } else if (this.f1293d) {
                                    this.f1294e++;
                                } else {
                                    for (gd gdVar : list) {
                                        this.f1293d = m2013a(gdVar.m1944f(), str3);
                                        if (this.f1293d) {
                                            m2010a(gdVar);
                                        }
                                    }
                                }
                            }
                        } catch (EOFException e2) {
                        } catch (FileNotFoundException e3) {
                            bhVar = bhVar2;
                            inputStream = fileInputStream;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (bhVar2 != null) {
                        try {
                            bhVar2.close();
                        } catch (Throwable e5) {
                            C0247g.m1917a(e5, "ANRWriter", "initLog1");
                        } catch (Throwable e52) {
                            C0247g.m1917a(e52, "ANRWriter", "initLog2");
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e52 = e6;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            C0247g.m1917a(e52, str, str2);
                            return this.f1293d ? null : m2020d();
                        } catch (Throwable th) {
                            e52 = th;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            C0247g.m1917a(e52, str, str2);
                            if (this.f1293d) {
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    bhVar = null;
                    inputStream = fileInputStream;
                    if (bhVar != null) {
                        try {
                            bhVar.close();
                        } catch (Throwable e522) {
                            C0247g.m1917a(e522, "ANRWriter", "initLog1");
                        } catch (Throwable e5222) {
                            C0247g.m1917a(e5222, "ANRWriter", "initLog2");
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e5222 = e8;
                            str = "ANRWriter";
                            str2 = "initLog3";
                            C0247g.m1917a(e5222, str, str2);
                            if (this.f1293d) {
                            }
                        } catch (Throwable th2) {
                            e5222 = th2;
                            str = "ANRWriter";
                            str2 = "initLog4";
                            C0247g.m1917a(e5222, str, str2);
                            if (this.f1293d) {
                            }
                        }
                    }
                    if (this.f1293d) {
                    }
                } catch (IOException e9) {
                    e5222 = e9;
                    bhVar2 = null;
                    try {
                        C0247g.m1917a(e5222, "ANRWriter", "initLog");
                        if (bhVar2 != null) {
                            try {
                                bhVar2.close();
                            } catch (Throwable e52222) {
                                C0247g.m1917a(e52222, "ANRWriter", "initLog1");
                            } catch (Throwable e522222) {
                                C0247g.m1917a(e522222, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e522222 = e10;
                                str = "ANRWriter";
                                str2 = "initLog3";
                                C0247g.m1917a(e522222, str, str2);
                                if (this.f1293d) {
                                }
                            } catch (Throwable th3) {
                                e522222 = th3;
                                str = "ANRWriter";
                                str2 = "initLog4";
                                C0247g.m1917a(e522222, str, str2);
                                if (this.f1293d) {
                                }
                            }
                        }
                        if (this.f1293d) {
                        }
                    } catch (Throwable th4) {
                        e522222 = th4;
                        if (bhVar2 != null) {
                            try {
                                bhVar2.close();
                            } catch (Throwable e11) {
                                C0247g.m1917a(e11, "ANRWriter", "initLog1");
                            } catch (Throwable e112) {
                                C0247g.m1917a(e112, "ANRWriter", "initLog2");
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e1122) {
                                C0247g.m1917a(e1122, "ANRWriter", "initLog3");
                            } catch (Throwable e11222) {
                                C0247g.m1917a(e11222, "ANRWriter", "initLog4");
                            }
                        }
                        throw e522222;
                    }
                } catch (Throwable th5) {
                    e522222 = th5;
                    bhVar2 = null;
                    if (bhVar2 != null) {
                        bhVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e522222;
                }
                if (this.f1293d) {
                }
            }
            if (null != null) {
                try {
                    bhVar2.close();
                } catch (Throwable e12) {
                    C0247g.m1917a(e12, "ANRWriter", "initLog1");
                } catch (Throwable e122) {
                    C0247g.m1917a(e122, "ANRWriter", "initLog2");
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable e5222222) {
                    C0247g.m1917a(e5222222, "ANRWriter", "initLog3");
                } catch (Throwable e52222222) {
                    C0247g.m1917a(e52222222, "ANRWriter", "initLog4");
                }
            }
            return null;
        } catch (FileNotFoundException e13) {
            bhVar = null;
            inputStream = null;
            if (bhVar != null) {
                bhVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.f1293d) {
            }
        } catch (IOException e14) {
            e52222222 = e14;
            bhVar2 = null;
            fileInputStream = null;
            C0247g.m1917a(e52222222, "ANRWriter", "initLog");
            if (bhVar2 != null) {
                bhVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (this.f1293d) {
            }
        } catch (Throwable th6) {
            e52222222 = th6;
            bhVar2 = null;
            fileInputStream = null;
            if (bhVar2 != null) {
                bhVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e52222222;
        }
    }

    protected boolean m2022a(Context context) {
        if (fw.m1888m(context) != 1 || !f1290a) {
            return false;
        }
        f1290a = false;
        synchronized (Looper.getMainLooper()) {
            ae aeVar = new ae(context);
            ag a = aeVar.m997a();
            if (a == null) {
                return true;
            } else if (a.m1010c()) {
                a.m1009c(false);
                aeVar.m998a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
