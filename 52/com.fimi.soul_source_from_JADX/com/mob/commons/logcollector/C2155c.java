package com.mob.commons.logcollector;

import android.content.Context;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.mob.tools.MobLog;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import com.xiaomi.market.sdk.C2537j;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.mob.commons.logcollector.c */
public class C2155c extends SSDKHandlerThread {
    private static C2155c f11334a;
    private static String f11335b;
    private HashMap<String, Integer> f11336c;
    private Context f11337d;
    private DeviceHelper f11338e;
    private NetworkHelper f11339f;
    private C2156d f11340g;
    private File f11341h;
    private FileLocker f11342i;

    static {
        f11335b = "http://api.exc.mob.com:80";
    }

    private C2155c(Context context) {
        super("MOB_LOGGER");
        this.f11337d = context.getApplicationContext();
        this.f11339f = new NetworkHelper();
        this.f11338e = DeviceHelper.getInstance(context);
        this.f11340g = C2156d.m13198a(context);
        this.f11336c = new HashMap();
        this.f11342i = new FileLocker();
        this.f11341h = new File(context.getFilesDir(), ".lock");
        if (!this.f11341h.exists()) {
            try {
                this.f11341h.createNewFile();
            } catch (Throwable e) {
                MobLog.getInstance().m750w(e);
            }
        }
        NLog.setContext(context);
        startThread();
    }

    public static synchronized C2155c m13183a(Context context) {
        C2155c c2155c;
        synchronized (C2155c.class) {
            if (f11334a == null) {
                f11334a = new C2155c(context);
            }
            c2155c = f11334a;
        }
        return c2155c;
    }

    private String m13184a(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, SmileConstants.MAX_SHARED_STRING_VALUES);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(toByteArray, 2);
            }
        }
    }

    private void m13185a(int i, String str, String str2, String[] strArr) {
        try {
            if (this.f11340g.m13204b()) {
                if ("none".equals(this.f11338e.getDetailNetworkTypeForStatic())) {
                    throw new IllegalStateException("network is disconnected!");
                }
                ArrayList a = C2158f.m13214a(this.f11337d, strArr);
                for (int i2 = 0; i2 < a.size(); i2++) {
                    C2157e c2157e = (C2157e) a.get(i2);
                    HashMap c = m13192c(i, str, str2);
                    c.put("errmsg", c2157e.f11345a);
                    if (m13187a(m13184a(new Hashon().fromHashMap(c)), true)) {
                        C2158f.m13212a(this.f11337d, c2157e.f11346b);
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m744i(th);
        }
    }

    private void m13186a(Message message) {
        this.handler.sendMessageDelayed(message, 1000);
    }

    private boolean m13187a(String str, boolean z) {
        try {
            if ("none".equals(this.f11338e.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("m", str));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = C1873o.ak;
            networkTimeOut.connectionTimeout = C1873o.ak;
            this.f11339f.httpPost(m13191c(), arrayList, null, null, networkTimeOut);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m744i(th);
            return false;
        }
    }

    private String m13188b() {
        return f11335b + "/errconf";
    }

    private void m13189b(int i, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair(SharedPref.KEY, str2));
        arrayList.add(new KVPair(C2537j.ap, str));
        arrayList.add(new KVPair("apppkg", this.f11338e.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.f11338e.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(i)));
        arrayList.add(new KVPair("plat", String.valueOf(this.f11338e.getPlatformCode())));
        try {
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = C1873o.ak;
            networkTimeOut.connectionTimeout = C1873o.ak;
            MobLog.getInstance().m743i("get logs server config response == %s", this.f11339f.httpPost(m13188b(), arrayList, null, null, networkTimeOut));
            HashMap fromJson = new Hashon().fromJson(r0);
            if ("-200".equals(String.valueOf(fromJson.get(RMsgInfo.COL_STATUS)))) {
                MobLog.getInstance().m743i("error log server config response fail !!", new Object[0]);
                return;
            }
            Object obj = fromJson.get("result");
            if (obj != null && (obj instanceof HashMap)) {
                HashMap hashMap;
                fromJson = (HashMap) obj;
                if (fromJson.containsKey("timestamp")) {
                    this.f11340g.m13200a(System.currentTimeMillis() - C2178R.parseLong(String.valueOf(fromJson.get("timestamp"))));
                }
                if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(String.valueOf(fromJson.get("enable")))) {
                    this.f11340g.m13202a(true);
                } else {
                    this.f11340g.m13202a(false);
                }
                Object obj2 = fromJson.get("upconf");
                if (obj2 != null && (obj2 instanceof HashMap)) {
                    hashMap = (HashMap) obj2;
                    String valueOf = String.valueOf(hashMap.get("crash"));
                    String valueOf2 = String.valueOf(hashMap.get("sdkerr"));
                    String valueOf3 = String.valueOf(hashMap.get("apperr"));
                    this.f11340g.m13203b(Constants.VIA_TO_TYPE_QQ_GROUP.equals(valueOf));
                    this.f11340g.m13205c(Constants.VIA_TO_TYPE_QQ_GROUP.equals(valueOf2));
                    this.f11340g.m13207d(Constants.VIA_TO_TYPE_QQ_GROUP.equals(valueOf3));
                }
                if (fromJson.containsKey("requesthost") && fromJson.containsKey("requestport")) {
                    obj2 = String.valueOf(fromJson.get("requesthost"));
                    Object valueOf4 = String.valueOf(fromJson.get("requestport"));
                    if (!(TextUtils.isEmpty(obj2) || TextUtils.isEmpty(valueOf4))) {
                        f11335b = "http://" + obj2 + ":" + valueOf4;
                    }
                }
                obj = fromJson.get("filter");
                if (obj != null && (obj instanceof ArrayList)) {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (arrayList2.size() > 0) {
                        hashMap = new HashMap();
                        hashMap.put("fakelist", arrayList2);
                        this.f11340g.m13201a(new Hashon().fromHashMap(hashMap));
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m738d(th);
        }
    }

    private void m13190b(Message message) {
        try {
            int i = message.arg1;
            Object[] objArr = (Object[]) message.obj;
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            m13189b(i, str, str2);
            m13185a(i, str, str2, null);
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
    }

    private String m13191c() {
        return f11335b + "/errlog";
    }

    private HashMap<String, Object> m13192c(int i, String str, String str2) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put(SharedPref.KEY, str2);
        hashMap.put("plat", Integer.valueOf(this.f11338e.getPlatformCode()));
        hashMap.put(C2537j.ap, str);
        hashMap.put("sdkver", Integer.valueOf(i));
        hashMap.put("appname", this.f11338e.getAppName());
        hashMap.put("apppkg", this.f11338e.getPackageName());
        hashMap.put("appver", String.valueOf(this.f11338e.getAppVersion()));
        hashMap.put("deviceid", this.f11338e.getDeviceKey());
        hashMap.put("model", this.f11338e.getModel());
        hashMap.put("mac", this.f11338e.getMacAddress());
        hashMap.put("udid", this.f11338e.getDeviceId());
        hashMap.put("sysver", String.valueOf(this.f11338e.getOSVersionInt()));
        hashMap.put("networktype", this.f11338e.getDetailNetworkTypeForStatic());
        return hashMap;
    }

    private void m13193c(Message message) {
        String MD5;
        try {
            int i = message.arg1;
            Object[] objArr = (Object[]) message.obj;
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            String str3 = (String) objArr[2];
            int i2 = message.arg2 == 0 ? 2 : message.arg2 == 2 ? 1 : 1;
            String f = this.f11340g.m13210f();
            if (!TextUtils.isEmpty(f)) {
                ArrayList arrayList = (ArrayList) new Hashon().fromJson(f).get("fakelist");
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        f = (String) it.next();
                        if (!TextUtils.isEmpty(f) && str3.contains(f)) {
                            return;
                        }
                    }
                }
            }
            MD5 = Data.MD5(str3);
            this.f11342i.setLockFile(this.f11341h.getAbsolutePath());
            if (this.f11342i.lock(false)) {
                C2158f.m13211a(this.f11337d, System.currentTimeMillis() - this.f11340g.m13199a(), str3, i2, MD5);
            }
            this.f11342i.release();
            this.f11336c.remove(MD5);
            if (3 == i2 && this.f11340g.m13209e()) {
                m13185a(i, str, str2, new String[]{String.valueOf(3)});
            } else if (1 == i2 && this.f11340g.m13206c()) {
                m13185a(i, str, str2, new String[]{String.valueOf(1)});
                Process.killProcess(Process.myPid());
                System.exit(0);
            } else if (2 == i2 && this.f11340g.m13208d()) {
                m13185a(i, str, str2, new String[]{String.valueOf(2)});
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
    }

    public Context m13194a() {
        return this.f11337d;
    }

    public void m13195a(int i, int i2, String str, String str2, String str3) {
        Message message = new Message();
        message.what = Opcodes.LSUB;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = new Object[]{str, str2, str3};
        this.handler.sendMessage(message);
    }

    public void m13196a(int i, String str, String str2) {
        Message message = new Message();
        message.what = 100;
        message.arg1 = i;
        message.obj = new Object[]{str, str2};
        this.handler.sendMessage(message);
    }

    public void m13197b(int i, int i2, String str, String str2, String str3) {
        m13195a(i, i2, str, str2, str3);
        try {
            this.handler.wait();
        } catch (Throwable th) {
        }
    }

    protected void onMessage(Message message) {
        switch (message.what) {
            case Opcodes.ISUB /*100*/:
                m13190b(message);
            case Opcodes.LSUB /*101*/:
                m13193c(message);
            default:
        }
    }
}
