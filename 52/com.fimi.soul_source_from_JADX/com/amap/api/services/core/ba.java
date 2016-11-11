package com.amap.api.services.core;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.p122a.p123a.C2915a;

class ba extends bi {
    private String[] f3055a;
    private int f3056b;
    private boolean f3057c;
    private int f3058d;
    private C0458a f3059e;

    /* renamed from: com.amap.api.services.core.ba.a */
    class C0458a implements bn {
        final /* synthetic */ ba f3052a;
        private ak f3053b;

        private C0458a(ba baVar, ak akVar) {
            this.f3052a = baVar;
            this.f3053b = akVar;
        }

        public void m4618a(String str) {
            try {
                this.f3053b.m4536b(str, this.f3052a.m4641a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    ba() {
        this.f3055a = new String[10];
        this.f3056b = 0;
        this.f3057c = false;
        this.f3058d = 0;
    }

    private void m4639b(String str) {
        try {
            if (this.f3056b > 9) {
                this.f3056b = 0;
            }
            this.f3055a[this.f3056b] = str;
            this.f3056b++;
        } catch (Throwable th) {
            ay.m4590a(th, "ANRWriter", "addData");
            th.printStackTrace();
        }
    }

    private String m4640c() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = this.f3056b;
            while (i < 10 && i <= 9) {
                stringBuilder.append(this.f3055a[i]);
                i++;
            }
            for (i = 0; i < this.f3056b; i++) {
                stringBuilder.append(this.f3055a[i]);
            }
        } catch (Throwable th) {
            ay.m4590a(th, "ANRWriter", "getLogInfo");
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    protected int m4641a() {
        return 2;
    }

    protected bn m4642a(ak akVar) {
        try {
            if (this.f3059e == null) {
                this.f3059e = new C0458a(akVar, null);
            }
        } catch (Throwable th) {
            ay.m4590a(th, "ANRWriter", "getListener");
            th.printStackTrace();
        }
        return this.f3059e;
    }

    protected String m4643a(String str) {
        return ab.m4473b(str);
    }

    protected String m4644a(List<ad> list) {
        InputStream fileInputStream;
        bo boVar;
        InputStream inputStream;
        Throwable e;
        IOException iOException;
        InputStream inputStream2 = null;
        bo boVar2 = null;
        try {
            File file = new File("/data/anr/traces.txt");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    boVar2 = new bo(fileInputStream, bp.f3120a);
                    Object obj = null;
                    while (true) {
                        try {
                            String str;
                            Object obj2;
                            String a = boVar2.m4725a();
                            if (a.contains("pid")) {
                                while (!a.contains("\"main\"")) {
                                    a = boVar2.m4725a();
                                }
                                str = a;
                                obj2 = 1;
                            } else {
                                str = a;
                                obj2 = obj;
                            }
                            obj = str.equals(C2915a.f14760f) ? null : obj2;
                            if (obj != null) {
                                m4639b(str);
                                if (this.f3058d == 5) {
                                    break;
                                } else if (this.f3057c) {
                                    this.f3058d++;
                                } else {
                                    for (ad adVar : list) {
                                        this.f3057c = m4637a(adVar.m4499f(), str);
                                        if (this.f3057c) {
                                            m4636a(adVar);
                                        }
                                    }
                                }
                            }
                        } catch (EOFException e2) {
                        } catch (FileNotFoundException e3) {
                            boVar = boVar2;
                            inputStream = fileInputStream;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (boVar2 != null) {
                        try {
                            boVar2.close();
                        } catch (Throwable e5) {
                            ay.m4590a(e5, "ANRWriter", "initLog1");
                            e5.printStackTrace();
                        } catch (Throwable e52) {
                            ay.m4590a(e52, "ANRWriter", "initLog2");
                            e52.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e52 = e6;
                            ay.m4590a(e52, "ANRWriter", "initLog3");
                            iOException.printStackTrace();
                            return this.f3057c ? null : m4640c();
                        } catch (Throwable th) {
                            e52 = th;
                            ay.m4590a(e52, "ANRWriter", "initLog4");
                            e52.printStackTrace();
                            if (this.f3057c) {
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    boVar = null;
                    inputStream = fileInputStream;
                    if (boVar != null) {
                        try {
                            boVar.close();
                        } catch (Throwable e522) {
                            ay.m4590a(e522, "ANRWriter", "initLog1");
                            e522.printStackTrace();
                        } catch (Throwable e5222) {
                            ay.m4590a(e5222, "ANRWriter", "initLog2");
                            e5222.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            iOException = e8;
                            ay.m4590a((Throwable) iOException, "ANRWriter", "initLog3");
                            iOException.printStackTrace();
                            if (this.f3057c) {
                            }
                        } catch (Throwable th2) {
                            e5222 = th2;
                            ay.m4590a(e5222, "ANRWriter", "initLog4");
                            e5222.printStackTrace();
                            if (this.f3057c) {
                            }
                        }
                    }
                    if (this.f3057c) {
                    }
                } catch (IOException e9) {
                    e5222 = e9;
                    boVar2 = null;
                    try {
                        ay.m4590a(e5222, "ANRWriter", "initLog");
                        e5222.printStackTrace();
                        if (boVar2 != null) {
                            try {
                                boVar2.close();
                            } catch (Throwable e52222) {
                                ay.m4590a(e52222, "ANRWriter", "initLog1");
                                e52222.printStackTrace();
                            } catch (Throwable e522222) {
                                ay.m4590a(e522222, "ANRWriter", "initLog2");
                                e522222.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e522222 = e10;
                                ay.m4590a(e522222, "ANRWriter", "initLog3");
                                iOException.printStackTrace();
                                if (this.f3057c) {
                                }
                            } catch (Throwable th3) {
                                e522222 = th3;
                                ay.m4590a(e522222, "ANRWriter", "initLog4");
                                e522222.printStackTrace();
                                if (this.f3057c) {
                                }
                            }
                        }
                        if (this.f3057c) {
                        }
                    } catch (Throwable th4) {
                        e522222 = th4;
                        if (boVar2 != null) {
                            try {
                                boVar2.close();
                            } catch (Throwable e11) {
                                ay.m4590a(e11, "ANRWriter", "initLog1");
                                e11.printStackTrace();
                            } catch (Throwable e112) {
                                ay.m4590a(e112, "ANRWriter", "initLog2");
                                e112.printStackTrace();
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e1122) {
                                ay.m4590a(e1122, "ANRWriter", "initLog3");
                                e1122.printStackTrace();
                            } catch (Throwable e11222) {
                                ay.m4590a(e11222, "ANRWriter", "initLog4");
                                e11222.printStackTrace();
                            }
                        }
                        throw e522222;
                    }
                } catch (Throwable th5) {
                    e522222 = th5;
                    boVar2 = null;
                    if (boVar2 != null) {
                        boVar2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw e522222;
                }
                if (this.f3057c) {
                }
            }
            if (null != null) {
                try {
                    boVar2.close();
                } catch (Throwable e12) {
                    ay.m4590a(e12, "ANRWriter", "initLog1");
                    e12.printStackTrace();
                } catch (Throwable e122) {
                    ay.m4590a(e122, "ANRWriter", "initLog2");
                    e122.printStackTrace();
                }
            }
            if (null != null) {
                try {
                    inputStream2.close();
                } catch (Throwable e5222222) {
                    ay.m4590a(e5222222, "ANRWriter", "initLog3");
                    e5222222.printStackTrace();
                } catch (Throwable e52222222) {
                    ay.m4590a(e52222222, "ANRWriter", "initLog4");
                    e52222222.printStackTrace();
                }
            }
            return null;
        } catch (FileNotFoundException e13) {
            boVar = null;
            inputStream = null;
            if (boVar != null) {
                boVar.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (this.f3057c) {
            }
        } catch (IOException e14) {
            e52222222 = e14;
            boVar2 = null;
            fileInputStream = null;
            ay.m4590a(e52222222, "ANRWriter", "initLog");
            e52222222.printStackTrace();
            if (boVar2 != null) {
                boVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (this.f3057c) {
            }
        } catch (Throwable th6) {
            e52222222 = th6;
            boVar2 = null;
            fileInputStream = null;
            if (boVar2 != null) {
                boVar2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e52222222;
        }
    }

    protected String m4645b() {
        return bf.f3077d;
    }
}
