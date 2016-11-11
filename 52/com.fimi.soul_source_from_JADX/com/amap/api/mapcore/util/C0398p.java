package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.amap.api.mapcore.C0330s;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.tencent.stat.DeviceInfo;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import com.xiaomi.market.sdk.C2537j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p153a.C2924b;

/* renamed from: com.amap.api.mapcore.util.p */
public class C0398p extends aj<String, List<OfflineMapProvince>> {
    private Context f2515j;

    public C0398p(Context context, String str) {
        super(context, str);
        getClass();
        m3432a((int) FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
        getClass();
        m3435b(FDSClientConfiguration.DEFAULT_SOCKET_TIMEOUT_MS);
    }

    private void m4178c(String str) {
        OutputStream fileOutputStream;
        Throwable e;
        if (!bj.m3634b(this.f2515j).equals(C2915a.f14760f)) {
            File file = new File(bj.m3634b(this.f2515j) + "offlinemapv4.png");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e2) {
                    ce.m3829a(e2, "OfflineUpdateCityHandler", "writeSD dirCreate");
                    e2.printStackTrace();
                }
            }
            if (b_() > 1048576) {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(str.getBytes("utf-8"));
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        try {
                            ce.m3829a(e, "OfflineUpdateCityHandler", "writeSD filenotfound");
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            e = th;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw e;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        ce.m3829a(e, "OfflineUpdateCityHandler", "writeSD io");
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                    }
                } catch (FileNotFoundException e7) {
                    e = e7;
                    fileOutputStream = null;
                    ce.m3829a(e, "OfflineUpdateCityHandler", "writeSD filenotfound");
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e8) {
                    e = e8;
                    fileOutputStream = null;
                    ce.m3829a(e, "OfflineUpdateCityHandler", "writeSD io");
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th2) {
                    e = th2;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw e;
                }
            }
        }
    }

    public String m4179a() {
        return "http://restapi.amap.com/v3/config/resource";
    }

    protected List<OfflineMapProvince> m4180a(String str) {
        List<OfflineMapProvince> list = null;
        try {
            if (this.f2515j != null) {
                m4178c(str);
            }
        } catch (Throwable th) {
            ce.m3829a(th, "OfflineUpdateCityHandler", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            list = af.m3418b(str);
        } catch (Throwable th2) {
            ce.m3829a(th2, "OfflineUpdateCityHandler", "loadData parseJson");
            th2.printStackTrace();
        }
        return list;
    }

    protected List<OfflineMapProvince> m4181a(byte[] bArr) {
        List<OfflineMapProvince> arrayList = new ArrayList();
        try {
            String str = new String(bArr, "utf-8");
            bj.m3624a(str);
            if (!(str == null || C2915a.f14760f.equals(str))) {
                String optString = new JSONObject(str).optString(RMsgInfo.COL_STATUS);
                if (!(optString == null || optString.equals(C2915a.f14760f) || optString.equals(Constants.VIA_RESULT_SUCCESS))) {
                    arrayList = m4180a(str);
                }
            }
        } catch (Throwable th) {
            ce.m3829a(th, "OfflineUpdateCityHandler", "loadData jsonInit");
            th.printStackTrace();
        }
        return arrayList;
    }

    public void m4182a(Context context) {
        this.f2515j = context;
    }

    protected /* synthetic */ Object m4183b(String str) {
        return m4180a(str);
    }

    protected /* synthetic */ Object m4184b(byte[] bArr) {
        return m4181a(bArr);
    }

    public Map<String, String> m4185b() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(SharedPref.KEY, bl.m3652f(this.f2515j));
        hashMap.put("opertype", "offlinemap_with_province_vfour");
        hashMap.put("plattype", "android");
        hashMap.put("product", C0330s.f2069b);
        hashMap.put(C2537j.aq, "3.3.1");
        hashMap.put("ext", C2924b.f14791c);
        hashMap.put("output", "json");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(bl.m3652f(this.f2515j));
        stringBuffer.append("&opertype=offlinemap_with_province_vfour");
        stringBuffer.append("&plattype=android");
        stringBuffer.append("&product=").append(C0330s.f2069b);
        stringBuffer.append("&version=").append("3.3.1");
        stringBuffer.append("&ext=standard");
        stringBuffer.append("&output=json");
        String d = bx.m3784d(stringBuffer.toString());
        String a = bn.m3661a();
        hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
        hashMap.put("scode", bn.m3667a(this.f2515j, a, d));
        return hashMap;
    }

    public long b_() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }
}
