package com.mob.commons.appcollector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Base64;
import com.fimi.kernel.C1154b;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.entity.User;
import com.mi.live.openlivesdk.C2116b;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.LocalDB;
import com.tencent.open.SocialConstants;
import com.xiaomi.market.sdk.C2537j;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.openauth.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class RuntimeCollector extends Thread {
    private static final String f11297b;
    private static RuntimeCollector f11298c;
    private static boolean f11299d;
    private static FileLocker f11300e;
    private final String f11301a;
    private ArrayList<HashMap<String, Object>> f11302f;
    private HashMap<String, HashMap<String, Object>> f11303g;
    private HashMap<String, HashMap<String, Object>> f11304h;
    private HashMap<String, Integer> f11305i;
    private PackageManager f11306j;
    private NetworkHelper f11307k;
    private DeviceHelper f11308l;
    private C2148a f11309m;
    private Context f11310n;
    private String f11311o;
    private long f11312p;
    private long f11313q;
    private int f11314r;

    /* renamed from: com.mob.commons.appcollector.RuntimeCollector.a */
    class C2148a {
        private LocalDB f11296a;

        private C2148a(Context context) {
            this.f11296a = new LocalDB();
            m13132b(context);
        }

        private String m13131a(Context context) {
            return C2178R.getCacheRoot(context) + ".db_rtcache";
        }

        private void m13132b(Context context) {
            if (this.f11296a == null) {
                this.f11296a = new LocalDB();
            }
            File file = new File(m13131a(context));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e) {
                    MobLog.getInstance().m750w(e);
                }
            }
            this.f11296a.open(m13131a(context));
        }

        public int m13133a(long j) {
            return ((int) ((System.currentTimeMillis() - j) / 1000)) + this.f11296a.getInt("sdk_time");
        }

        public ArrayList<HashMap<String, Object>> m13134a() {
            Object object = this.f11296a.getObject(C1154b.f5233d);
            return object == null ? new ArrayList() : (ArrayList) C2178R.forceCast(object);
        }

        public void m13135a(int i) {
            this.f11296a.putInt("sdk_time", Integer.valueOf(i));
        }

        public void m13136a(ArrayList<HashMap<String, Object>> arrayList) {
            this.f11296a.putObject(C1154b.f5233d, arrayList);
        }

        public void m13137a(HashMap<String, Integer> hashMap) {
            this.f11296a.putObject(C1314u.bx, hashMap);
        }

        public void m13138b() {
            this.f11296a.putLong("time", Long.valueOf(System.currentTimeMillis()));
        }

        public void m13139b(int i) {
            this.f11296a.putInt("app_all", Integer.valueOf(i));
        }

        public int m13140c(int i) {
            return this.f11296a.getInt("app_all") + i;
        }

        public long m13141c() {
            return this.f11296a.getLong("time");
        }

        public HashMap<String, Integer> m13142d() {
            Object object = this.f11296a.getObject(C1314u.bx);
            if (object == null) {
                object = new HashMap();
            }
            return (HashMap) C2178R.forceCast(object);
        }
    }

    static {
        f11297b = VERSION.SDK_INT >= 16 ? "^u\\d+_a\\d+" : "^app_\\d+";
        f11300e = new FileLocker();
    }

    private RuntimeCollector(Context context, String str) {
        this.f11301a = "http://cca.mob.com:80/ca";
        setName("mob.rtc");
        this.f11310n = context.getApplicationContext();
        this.f11311o = str;
    }

    private final String m13143a(String str) {
        try {
            return this.f11306j.getPackageInfo(str, 0).versionName;
        } catch (Throwable th) {
            return BuildConfig.VERSION_NAME;
        }
    }

    private void m13144a() {
        BufferedReader bufferedReader;
        Throwable e;
        int i = 0;
        this.f11304h.clear();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("top -d 0 -n 1").getInputStream()));
            while (i < 8) {
                try {
                    bufferedReader.readLine();
                    i++;
                } catch (IOException e2) {
                    e = e2;
                }
            }
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                readLine = readLine.replaceAll(" +", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (split != null && split.length >= 2) {
                    readLine = split[split.length - 1];
                    if (split[split.length - 2].matches(f11297b) && readLine.compareTo("top") != 0) {
                        String b = m13149b(readLine);
                        if (b == null) {
                            readLine = bufferedReader.readLine();
                        } else if (m13153c(readLine)) {
                            readLine = bufferedReader.readLine();
                        } else {
                            m13145a(readLine, split[7]);
                            HashMap hashMap = new HashMap();
                            hashMap.put("pkg", readLine);
                            hashMap.put(User.FN_NAME, b);
                            hashMap.put(C2537j.aq, m13143a(readLine));
                            hashMap.put("runtimes", Integer.valueOf(0));
                            hashMap.put("start_timestamp", Long.valueOf(0));
                            this.f11304h.put(readLine, hashMap);
                        }
                    }
                    readLine = bufferedReader.readLine();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable th) {
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedReader = null;
            try {
                MobLog.getInstance().m750w(e);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                e = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                    }
                }
                throw e;
            }
        } catch (Throwable th5) {
            e = th5;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw e;
        }
    }

    private void m13145a(String str, String str2) {
        int i = 0;
        if ("fg".equals(str2)) {
            if (this.f11305i.get(str + "_fg") != null) {
                i = ((Integer) this.f11305i.get(str + "_fg")).intValue();
            }
            this.f11305i.put(str + "_fg", Integer.valueOf(i + 1));
        } else if ("bg".equals(str2)) {
            if (this.f11305i.get(str + "_bg") != null) {
                i = ((Integer) this.f11305i.get(str + "_bg")).intValue();
            }
            this.f11305i.put(str + "_bg", Integer.valueOf(i + 1));
        } else {
            if (this.f11305i.get(str + "_em") != null) {
                i = ((Integer) this.f11305i.get(str + "_em")).intValue();
            }
            this.f11305i.put(str + "_em", Integer.valueOf(i + 1));
        }
    }

    private void m13146a(HashMap<String, Object> hashMap) {
        Object obj = 1;
        Iterator it = this.f11302f.iterator();
        while (it.hasNext()) {
            HashMap hashMap2 = (HashMap) it.next();
            if (hashMap2.get("pkg").equals(hashMap.get("pkg"))) {
                obj = null;
                HashMap hashMap3 = hashMap2;
                break;
            }
        }
        if (0 == ((Long) C2178R.forceCast(hashMap3.get("start_timestamp"), Long.valueOf(0))).longValue()) {
            hashMap3.put("start_timestamp", Long.valueOf(System.currentTimeMillis()));
            if (obj != null) {
                this.f11302f.add(hashMap3);
            }
        }
    }

    private void m13147a(boolean z) {
        Iterator it = this.f11302f.iterator();
        while (it.hasNext()) {
            m13150b((HashMap) it.next());
        }
        this.f11309m.m13136a(this.f11302f);
        this.f11309m.m13135a(this.f11309m.m13133a(this.f11313q));
        this.f11313q = System.currentTimeMillis();
        this.f11309m.m13137a(this.f11305i);
        this.f11309m.m13139b(this.f11314r);
        if (!z) {
            for (Entry value : this.f11303g.entrySet()) {
                m13146a((HashMap) value.getValue());
            }
        }
    }

    private boolean m13148a(ArrayList<HashMap<String, Object>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_TYPE, "APP_RUNTIMES");
        hashMap.put("plat", Integer.valueOf(this.f11308l.getPlatformCode()));
        hashMap.put("device", this.f11308l.getDeviceKey());
        hashMap.put("imei", this.f11308l.getIMEI());
        hashMap.put("serialno", this.f11308l.getSerialno());
        hashMap.put("mac", this.f11308l.getMacAddress());
        hashMap.put("model", this.f11308l.getModel());
        hashMap.put("top_count", Integer.valueOf(this.f11309m.m13140c(0)));
        hashMap.put("recordat", Long.valueOf(this.f11309m.m13141c()));
        hashMap.put("sdk_runtime_len", Integer.valueOf(this.f11309m.m13133a(this.f11313q)));
        hashMap.put("list", arrayList);
        String fromHashMap = new Hashon().fromHashMap(hashMap);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(C2116b.f11123f, this.f11311o));
        arrayList2.add(new KVPair("m", Base64.encodeToString(Data.AES128Encode((this.f11311o + "0000000000000000").substring(0, 16), fromHashMap), 2)));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        try {
            fromHashMap = this.f11307k.httpPost("http://cca.mob.com:80/ca", arrayList2, null, null, networkTimeOut);
            MobLog.getInstance().m743i("> uploadApps list resp: %s", fromHashMap);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m743i("> uploadApps list resp: %s", null);
        }
    }

    private String m13149b(String str) {
        try {
            return this.f11306j.getApplicationLabel(this.f11306j.getApplicationInfo(str, SmileConstants.TOKEN_PREFIX_TINY_UNICODE)).toString();
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private void m13150b(HashMap<String, Object> hashMap) {
        HashMap hashMap2;
        Object obj;
        Iterator it = this.f11302f.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3.get("pkg").equals(hashMap.get("pkg"))) {
                hashMap2 = hashMap3;
                obj = 1;
                break;
            }
        }
        hashMap2 = null;
        obj = null;
        if (obj != null) {
            long longValue = (!hashMap2.containsKey("start_timestamp") || hashMap2.get("start_timestamp") == null) ? 0 : ((Long) C2178R.forceCast(hashMap2.get("start_timestamp"))).longValue();
            if (0 != longValue) {
                int intValue = ((Integer) C2178R.forceCast(hashMap2.get("runtimes"))).intValue();
                hashMap2.put("start_timestamp", Long.valueOf(0));
                hashMap2.put("runtimes", Integer.valueOf((int) (((System.currentTimeMillis() - longValue) / 1000) + ((long) intValue))));
            }
        }
    }

    private boolean m13151b() {
        Iterator it = this.f11302f.iterator();
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            long longValue = (!hashMap.containsKey("start_timestamp") || hashMap.get("start_timestamp") == null) ? 0 : ((Long) C2178R.forceCast(hashMap.get("start_timestamp"))).longValue();
            hashMap.remove("start_timestamp");
            if (0 != longValue) {
                hashMap.put("runtimes", Integer.valueOf((int) (((System.currentTimeMillis() - longValue) / 1000) + ((long) ((Integer) C2178R.forceCast(hashMap.get("runtimes"))).intValue()))));
            }
            String str = (String) C2178R.forceCast(hashMap.get("pkg"));
            int intValue = this.f11305i.get(new StringBuilder().append(str).append("_bg").toString()) != null ? ((Integer) this.f11305i.get(str + "_bg")).intValue() : 0;
            int intValue2 = this.f11305i.get(new StringBuilder().append(str).append("_fg").toString()) != null ? ((Integer) this.f11305i.get(str + "_fg")).intValue() : 0;
            int intValue3 = this.f11305i.get(new StringBuilder().append(str).append("_em").toString()) != null ? ((Integer) this.f11305i.get(str + "_em")).intValue() : 0;
            hashMap.put("bg", Integer.valueOf(intValue));
            hashMap.put("fg", Integer.valueOf(intValue2));
            hashMap.put("empty", Integer.valueOf(intValue3));
        }
        this.f11309m.m13136a(this.f11302f);
        this.f11309m.m13137a(this.f11305i);
        this.f11309m.m13135a(this.f11309m.m13133a(this.f11313q));
        this.f11309m.m13139b(this.f11314r);
        this.f11313q = System.currentTimeMillis();
        try {
            if (!m13148a(this.f11302f)) {
                return false;
            }
            this.f11309m.m13138b();
            this.f11309m.m13136a(null);
            this.f11309m.m13137a(null);
            this.f11309m.m13135a(0);
            this.f11309m.m13139b(0);
            this.f11302f.clear();
            this.f11303g.clear();
            this.f11305i.clear();
            this.f11312p = this.f11309m.m13141c();
            this.f11314r = 0;
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return false;
        }
    }

    private void m13152c(HashMap<String, HashMap<String, Object>> hashMap) {
        this.f11314r++;
        m13144a();
        HashMap hashMap2 = (HashMap) C2178R.forceCast(this.f11304h.clone());
        for (Entry key : hashMap.entrySet()) {
            hashMap2.remove(key.getKey());
        }
        for (Entry value : hashMap2.entrySet()) {
            m13146a((HashMap) value.getValue());
        }
        hashMap2 = (HashMap) C2178R.forceCast(hashMap.clone());
        for (Entry key2 : this.f11304h.entrySet()) {
            hashMap2.remove(key2.getKey());
        }
        for (Entry value2 : hashMap2.entrySet()) {
            m13150b((HashMap) value2.getValue());
        }
    }

    private boolean m13153c(String str) {
        int i;
        try {
            i = this.f11306j.getApplicationInfo(str, SmileConstants.TOKEN_PREFIX_TINY_UNICODE).flags;
        } catch (Throwable e) {
            MobLog.getInstance().m750w(e);
            i = 0;
        }
        return ((i & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) == 0 && (i & 1) == 0) ? false : true;
    }

    public static synchronized void startCollector(Context context, String str) {
        synchronized (RuntimeCollector.class) {
            String str2 = C2178R.getCacheRoot(context.getApplicationContext()) + ".rc_lock";
            File file = new File(str2);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (Throwable e) {
                    MobLog.getInstance().m750w(e);
                }
            }
            f11300e.setLockFile(str2);
            if (f11300e.lock(true) && f11298c == null) {
                f11298c = new RuntimeCollector(context, str);
                f11298c.start();
                f11299d = false;
            }
            f11300e.release();
        }
    }

    public static synchronized void stopCollector() {
        synchronized (RuntimeCollector.class) {
            if (f11298c != null) {
                f11299d = true;
            }
        }
    }

    public void run() {
        if (C2149a.m13155a(this.f11310n, this.f11311o).m13161e()) {
            this.f11308l = DeviceHelper.getInstance(this.f11310n);
            this.f11309m = new C2148a(null);
            this.f11307k = new NetworkHelper();
            this.f11306j = this.f11310n.getPackageManager();
            this.f11312p = this.f11309m.m13141c();
            if (0 == this.f11312p) {
                this.f11309m.m13138b();
                this.f11312p = System.currentTimeMillis();
            }
            this.f11313q = System.currentTimeMillis();
            this.f11302f = this.f11309m.m13134a();
            this.f11303g = new HashMap();
            this.f11304h = new HashMap();
            this.f11314r = this.f11309m.m13140c(0);
            this.f11305i = this.f11309m.m13142d();
            int i = 0;
            while (true) {
                if (Math.abs(System.currentTimeMillis() - this.f11312p) > MiStatInterface.MAX_UPLOAD_INTERVAL) {
                    m13151b();
                }
                m13152c(this.f11303g);
                this.f11303g = (HashMap) C2178R.forceCast(this.f11304h.clone());
                int i2 = i + 1;
                if (i2 % 2 == 0) {
                    m13147a(false);
                    i2 = 0;
                }
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                }
                if (f11299d) {
                    m13147a(true);
                    return;
                }
                i = i2;
            }
        }
    }
}
