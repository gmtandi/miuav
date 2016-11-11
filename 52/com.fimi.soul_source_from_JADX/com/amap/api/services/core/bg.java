package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.amap.api.services.core.bk.C0465b;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

abstract class bg {
    private bk f3047a;

    protected bg(Context context) {
        try {
            this.f3047a = m4595a(context, m4603a());
        } catch (Throwable th) {
            ay.m4590a(th, "LogProcessor", "LogUpDateProcessor");
            th.printStackTrace();
        }
    }

    public static bg m4594a(Context context, int i) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new bb(context);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return new bd(context);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new az(context);
            default:
                return null;
        }
    }

    private bk m4595a(Context context, String str) {
        bk bkVar = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getFilesDir().getAbsolutePath());
            stringBuilder.append(bf.f3074a);
            stringBuilder.append(str);
            File file = new File(stringBuilder.toString());
            if (file.exists() || file.mkdirs()) {
                bkVar = bk.m4695a(file, 1, 1, 20480);
            }
        } catch (Throwable e) {
            ay.m4590a(e, "LogProcessor", "initDiskLru");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.m4590a(e2, "LogProcessor", "initDiskLru");
            e2.printStackTrace();
        }
        return bkVar;
    }

    private String m4596a(List<am> list, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(m4601c(context)).append("\",\"els\":[");
        Object obj = 1;
        for (am amVar : list) {
            Object obj2;
            String c = m4602c(amVar.m4540b());
            if (c != null) {
                if (C2915a.f14760f.equals(c)) {
                    obj2 = obj;
                    obj = obj2;
                } else {
                    String str = c + "||" + amVar.m4544d();
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                    stringBuilder.append("{\"log\":\"").append(str).append("\"}");
                }
            }
            obj2 = obj;
            obj = obj2;
        }
        if (obj != null) {
            return null;
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private void m4597a(ak akVar, int i) {
        try {
            m4598a(akVar.m4532a(2, i), akVar, i);
        } catch (Throwable th) {
            ay.m4590a(th, "LogProcessor", "processDeleteFail");
            th.printStackTrace();
        }
    }

    private void m4598a(List<am> list, ak akVar, int i) {
        if (list != null && list.size() > 0) {
            for (am amVar : list) {
                if (m4599a(amVar.m4540b())) {
                    akVar.m4534a(amVar.m4540b(), i);
                } else {
                    amVar.m4538a(2);
                    akVar.m4533a(amVar, amVar.m4537a());
                }
            }
        }
    }

    private boolean m4599a(String str) {
        boolean z = false;
        if (this.f3047a != null) {
            try {
                z = this.f3047a.m4721c(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    private int m4600b(String str) {
        int i = 0;
        Log.i("yiyi.qi", str);
        try {
            byte[] a = bs.m4739a(false).m4749a(new bh(ae.m4503b(str.getBytes())));
            if (a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(a));
                    String str2 = XiaomiOAuthConstants.EXTRA_CODE_2;
                    if (jSONObject.has(str2)) {
                        i = jSONObject.getInt(str2);
                    }
                } catch (Throwable e) {
                    ay.m4590a(e, "LogProcessor", "processUpdate");
                    e.printStackTrace();
                }
            }
        } catch (Throwable e2) {
            ay.m4590a(e2, "LogProcessor", "processUpdate");
            e2.printStackTrace();
        }
        return i;
    }

    private String m4601c(Context context) {
        String str = null;
        try {
            String a = C0498y.m4878a(context);
            if (!C2915a.f14760f.equals(a)) {
                str = C0498y.m4884b(context, a.getBytes(C1142e.f5201a));
            }
        } catch (Throwable e) {
            ay.m4590a(e, "LogProcessor", "getPublicInfo");
            e.printStackTrace();
        } catch (Throwable e2) {
            ay.m4590a(e2, "LogProcessor", "getPublicInfo");
            e2.printStackTrace();
        }
        return str;
    }

    private String m4602c(String str) {
        Throwable e;
        IOException iOException;
        Throwable th;
        Object obj;
        String str2 = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream a;
        try {
            if (this.f3047a == null) {
                if (str2 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        ay.m4590a(e2, "LogProcessor", "readLog1");
                        e2.printStackTrace();
                    }
                }
                if (str2 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        ay.m4590a(e, "LogProcessor", "readLog2");
                        iOException.printStackTrace();
                        return str2;
                    }
                }
                return str2;
            }
            C0465b a2 = this.f3047a.m4715a(str);
            if (a2 == null) {
                if (str2 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        ay.m4590a(e22, "LogProcessor", "readLog1");
                        e22.printStackTrace();
                    }
                }
                if (str2 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        iOException = e4;
                        ay.m4590a((Throwable) iOException, "LogProcessor", "readLog2");
                        iOException.printStackTrace();
                        return str2;
                    }
                }
                return str2;
            }
            a = a2.m4678a(0);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                    while (true) {
                        int read = a.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str2 = byteArrayOutputStream.toString("utf-8");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e5) {
                            ay.m4590a(e5, "LogProcessor", "readLog1");
                            e5.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e6) {
                            e5 = e6;
                            ay.m4590a(e5, "LogProcessor", "readLog2");
                            iOException.printStackTrace();
                            return str2;
                        }
                    }
                } catch (IOException e7) {
                    e5 = e7;
                    try {
                        ay.m4590a(e5, "LogProcessor", "readLog");
                        e5.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e52) {
                                ay.m4590a(e52, "LogProcessor", "readLog1");
                                e52.printStackTrace();
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e8) {
                                e52 = e8;
                                ay.m4590a(e52, "LogProcessor", "readLog2");
                                iOException.printStackTrace();
                                return str2;
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e522) {
                                ay.m4590a(e522, "LogProcessor", "readLog1");
                                e522.printStackTrace();
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable e5222) {
                                ay.m4590a(e5222, "LogProcessor", "readLog2");
                                e5222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    e5222 = th3;
                    ay.m4590a(e5222, "LogProcessor", "readLog");
                    e5222.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e52222) {
                            ay.m4590a(e52222, "LogProcessor", "readLog1");
                            e52222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e9) {
                            e52222 = e9;
                            ay.m4590a(e52222, "LogProcessor", "readLog2");
                            iOException.printStackTrace();
                            return str2;
                        }
                    }
                    return str2;
                }
            } catch (IOException e10) {
                e52222 = e10;
                obj = str2;
                ay.m4590a(e52222, "LogProcessor", "readLog");
                e52222.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                return str2;
            } catch (Throwable e522222) {
                obj = str2;
                th = e522222;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
            return str2;
        } catch (IOException e11) {
            e522222 = e11;
            obj = str2;
            Object obj2 = str2;
            ay.m4590a(e522222, "LogProcessor", "readLog");
            e522222.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            return str2;
        } catch (Throwable e5222222) {
            byteArrayOutputStream = str2;
            a = str2;
            th = e5222222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    protected abstract String m4603a();

    protected abstract boolean m4604a(Context context);

    protected abstract int m4605b();

    void m4606b(Context context) {
        try {
            if (m4604a(context)) {
                synchronized (Looper.getMainLooper()) {
                    ak akVar = new ak(context);
                    m4597a(akVar, m4605b());
                    List a = akVar.m4532a(0, m4605b());
                    if (a == null || a.size() == 0) {
                        return;
                    }
                    String a2 = m4596a(a, context);
                    if (a2 == null) {
                        return;
                    }
                    if (m4600b(a2) == 1) {
                        m4598a(a, akVar, m4605b());
                    }
                }
            }
        } catch (Throwable th) {
            ay.m4590a(th, "LogProcessor", "processUpdateLog");
            th.printStackTrace();
        }
    }

    void m4607c() {
        if (this.f3047a != null && !this.f3047a.m4717a()) {
            try {
                this.f3047a.close();
            } catch (Throwable e) {
                ay.m4590a(e, "LogProcessor", "closeDiskLru");
                e.printStackTrace();
            } catch (Throwable e2) {
                ay.m4590a(e2, "LogProcessor", "closeDiskLru");
                e2.printStackTrace();
            }
        }
    }
}
