package com.mob.commons.authorize;

import android.content.Context;
import android.util.Base64;
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
import com.tencent.mm.sdk.message.RMsgInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.authorize.a */
public final class C2151a {
    private final String m13162a(Context context, MobProduct mobProduct, HashMap<String, Object> hashMap, boolean z) {
        int parseInt;
        Throwable th;
        ObjectOutputStream objectOutputStream;
        Throwable th2;
        DeviceHelper instance = DeviceHelper.getInstance(context);
        int i = -1;
        try {
            parseInt = C2178R.parseInt(instance.getCarrier());
        } catch (Throwable th3) {
            MobLog.getInstance().m750w(th3);
            return null;
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("adsid", instance.getAdvertisingID());
        hashMap2.put("imei", instance.getIMEI());
        hashMap2.put("serialno", instance.getSerialno());
        hashMap2.put("androidid", instance.getAndroidID());
        hashMap2.put("mac", instance.getMacAddress());
        hashMap2.put("model", instance.getModel());
        hashMap2.put("factory", instance.getManufacturer());
        hashMap2.put("carrier", Integer.valueOf(parseInt));
        hashMap2.put("screensize", instance.getScreenSize());
        hashMap2.put("sysver", instance.getOSVersionName());
        hashMap2.put("plat", Integer.valueOf(1));
        Hashon hashon = new Hashon();
        String encodeToString = Base64.encodeToString(Data.AES128Encode("sdk.commonap.sdk", hashon.fromHashMap(hashMap2)), 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", encodeToString));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        HashMap fromJson = hashon.fromJson(new NetworkHelper().httpPost("http://devs.data.mob.com:80/dinfo", arrayList, null, null, networkTimeOut));
        if (fromJson == null) {
            return null;
        }
        encodeToString = (String) fromJson.get("duid");
        if (encodeToString == null || encodeToString.length() <= 0) {
            return null;
        }
        hashMap.put("duid", encodeToString);
        hashMap2.put("carrier", String.valueOf(parseInt));
        hashMap.put("deviceInfo", hashMap2);
        if (!z) {
            return encodeToString;
        }
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(C2178R.getCacheRoot(context), ".duid")));
            try {
                objectOutputStream.writeObject(hashMap);
                if (objectOutputStream == null) {
                    return encodeToString;
                }
                try {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                    return encodeToString;
                } catch (Throwable th4) {
                    return encodeToString;
                }
            } catch (Throwable th5) {
                th2 = th5;
                try {
                    MobLog.getInstance().m750w(th2);
                    if (objectOutputStream != null) {
                        return encodeToString;
                    }
                    try {
                        objectOutputStream.flush();
                        objectOutputStream.close();
                        return encodeToString;
                    } catch (Throwable th6) {
                        return encodeToString;
                    }
                } catch (Throwable th7) {
                    th3 = th7;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.flush();
                            objectOutputStream.close();
                        } catch (Throwable th8) {
                        }
                    }
                    throw th3;
                }
            }
        } catch (Throwable th9) {
            th3 = th9;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.flush();
                objectOutputStream.close();
            }
            throw th3;
        }
    }

    private final void m13163a(Context context, MobProduct mobProduct, HashMap<String, Object> hashMap) {
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        try {
            String str = (String) hashMap.get("duid");
            DeviceHelper instance = DeviceHelper.getInstance(context);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("product", mobProduct.getProductTag()));
            arrayList.add(new KVPair(C2116b.f11123f, mobProduct.getProductAppkey()));
            arrayList.add(new KVPair("duid", str));
            arrayList.add(new KVPair("apppkg", instance.getPackageName()));
            arrayList.add(new KVPair("appver", String.valueOf(instance.getAppVersion())));
            arrayList.add(new KVPair("sdkver", String.valueOf(mobProduct.getSdkver())));
            arrayList.add(new KVPair("network", instance.getDetailNetworkTypeForStatic()));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            HashMap fromJson = new Hashon().fromJson(new NetworkHelper().httpPost("http://devs.data.mob.com:80/dsign", arrayList, null, null, networkTimeOut));
            if ("true".equals(String.valueOf(fromJson.get("reup"))) && m13162a(context, mobProduct, hashMap, false) == null) {
            }
            if ("200".equals(String.valueOf(fromJson.get(RMsgInfo.COL_STATUS)))) {
                ((HashMap) ((HashMap) hashMap.get("appInfo")).get(instance.getPackageName())).put(mobProduct.getProductTag(), mobProduct.getProductAppkey());
                ObjectOutputStream objectOutputStream2;
                try {
                    objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(C2178R.getCacheRoot(context), ".duid")));
                    try {
                        objectOutputStream2.writeObject(hashMap);
                        if (objectOutputStream2 != null) {
                            try {
                                objectOutputStream2.flush();
                                objectOutputStream2.close();
                            } catch (Throwable th2) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            MobLog.getInstance().m750w(th);
                            if (objectOutputStream2 != null) {
                                try {
                                    objectOutputStream2.flush();
                                    objectOutputStream2.close();
                                } catch (Throwable th4) {
                                }
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.flush();
                                    objectOutputStream.close();
                                } catch (Throwable th6) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    if (objectOutputStream != null) {
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th8) {
            MobLog.getInstance().m750w(th8);
        }
    }

    private final boolean m13164a(Context context, HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return true;
        }
        DeviceHelper instance = DeviceHelper.getInstance(context);
        String advertisingID = instance.getAdvertisingID();
        Object obj = hashMap.get("adsid");
        if (advertisingID != null) {
            if (obj == null && advertisingID != null) {
                return true;
            }
            if (!(obj == null || obj.equals(advertisingID))) {
                return true;
            }
        }
        Object obj2 = hashMap.get("imei");
        if (obj2 == null || !obj2.equals(instance.getIMEI())) {
            return true;
        }
        obj2 = hashMap.get("serialno");
        if (obj2 == null || !obj2.equals(instance.getSerialno())) {
            return true;
        }
        obj2 = hashMap.get("mac");
        if (obj2 == null || !obj2.equals(instance.getMacAddress())) {
            return true;
        }
        obj2 = hashMap.get("model");
        if (obj2 == null || !obj2.equals(instance.getModel())) {
            return true;
        }
        obj2 = hashMap.get("factory");
        if (obj2 == null || !obj2.equals(instance.getManufacturer())) {
            return true;
        }
        obj2 = hashMap.get("androidid");
        if (obj2 == null || !obj2.equals(instance.getAndroidID())) {
            return true;
        }
        obj2 = hashMap.get("sysver");
        return obj2 == null || !obj2.equals(instance.getOSVersionName());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String m13165b(android.content.Context r7, com.mob.commons.authorize.MobProduct r8) {
        /*
        r6 = this;
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00b9 }
        r2 = com.mob.tools.utils.C2178R.getCacheRoot(r7);	 Catch:{ Throwable -> 0x00b9 }
        r3 = ".duid";
        r0.<init>(r2, r3);	 Catch:{ Throwable -> 0x00b9 }
        r2 = r0.exists();	 Catch:{ Throwable -> 0x00b9 }
        if (r2 == 0) goto L_0x00c1;
    L_0x0012:
        r2 = r0.isFile();	 Catch:{ Throwable -> 0x00b9 }
        if (r2 == 0) goto L_0x00c1;
    L_0x0018:
        r3 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x009d, all -> 0x00b1 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x009d, all -> 0x00b1 }
        r2 = new java.io.ObjectInputStream;	 Catch:{ Throwable -> 0x009d, all -> 0x00b1 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x009d, all -> 0x00b1 }
        r0 = r2.readObject();	 Catch:{ Throwable -> 0x00d9 }
        r0 = (java.util.HashMap) r0;	 Catch:{ Throwable -> 0x00d9 }
        if (r2 == 0) goto L_0x002d;
    L_0x002a:
        r2.close();	 Catch:{ Throwable -> 0x00cd }
    L_0x002d:
        if (r0 != 0) goto L_0x00e2;
    L_0x002f:
        r0 = r6.m13166c(r7, r8);
        r3 = r0;
    L_0x0034:
        if (r3 == 0) goto L_0x00e0;
    L_0x0036:
        r0 = "duid";
        r0 = r3.get(r0);
        r0 = (java.lang.String) r0;
        if (r0 == 0) goto L_0x009c;
    L_0x0040:
        r1 = "deviceInfo";
        r1 = r3.get(r1);	 Catch:{ Throwable -> 0x00c4 }
        r1 = (java.util.HashMap) r1;	 Catch:{ Throwable -> 0x00c4 }
        r1 = r6.m13164a(r7, r1);	 Catch:{ Throwable -> 0x00c4 }
        if (r1 == 0) goto L_0x00dd;
    L_0x004e:
        r1 = 1;
        r1 = r6.m13162a(r7, r8, r3, r1);	 Catch:{ Throwable -> 0x00c4 }
        if (r1 == 0) goto L_0x00dd;
    L_0x0055:
        r0 = "appInfo";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x00d2 }
        r0 = (java.util.HashMap) r0;	 Catch:{ Throwable -> 0x00d2 }
        if (r0 != 0) goto L_0x00db;
    L_0x005f:
        r0 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00d2 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d2 }
        r2 = "appInfo";
        r3.put(r2, r0);	 Catch:{ Throwable -> 0x00d2 }
        r2 = r0;
    L_0x006a:
        r0 = com.mob.tools.utils.DeviceHelper.getInstance(r7);	 Catch:{ Throwable -> 0x00d2 }
        r4 = r0.getPackageName();	 Catch:{ Throwable -> 0x00d2 }
        r0 = r2.get(r4);	 Catch:{ Throwable -> 0x00d2 }
        r0 = (java.util.HashMap) r0;	 Catch:{ Throwable -> 0x00d2 }
        if (r0 != 0) goto L_0x0082;
    L_0x007a:
        r0 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00d2 }
        r0.<init>();	 Catch:{ Throwable -> 0x00d2 }
        r2.put(r4, r0);	 Catch:{ Throwable -> 0x00d2 }
    L_0x0082:
        r2 = r8.getProductTag();	 Catch:{ Throwable -> 0x00d2 }
        r0 = r0.get(r2);	 Catch:{ Throwable -> 0x00d2 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x00d2 }
        if (r0 == 0) goto L_0x0098;
    L_0x008e:
        r2 = r8.getProductAppkey();	 Catch:{ Throwable -> 0x00d2 }
        r0 = r0.equals(r2);	 Catch:{ Throwable -> 0x00d2 }
        if (r0 != 0) goto L_0x009b;
    L_0x0098:
        r6.m13163a(r7, r8, r3);	 Catch:{ Throwable -> 0x00d2 }
    L_0x009b:
        r0 = r1;
    L_0x009c:
        return r0;
    L_0x009d:
        r0 = move-exception;
        r2 = r1;
    L_0x009f:
        r3 = com.mob.tools.MobLog.getInstance();	 Catch:{ all -> 0x00d7 }
        r3.m750w(r0);	 Catch:{ all -> 0x00d7 }
        if (r2 == 0) goto L_0x00c1;
    L_0x00a8:
        r2.close();	 Catch:{ Throwable -> 0x00ad }
        r0 = r1;
        goto L_0x002d;
    L_0x00ad:
        r0 = move-exception;
        r0 = r1;
        goto L_0x002d;
    L_0x00b1:
        r0 = move-exception;
        r2 = r1;
    L_0x00b3:
        if (r2 == 0) goto L_0x00b8;
    L_0x00b5:
        r2.close();	 Catch:{ Throwable -> 0x00d0 }
    L_0x00b8:
        throw r0;	 Catch:{ Throwable -> 0x00b9 }
    L_0x00b9:
        r0 = move-exception;
        r2 = com.mob.tools.MobLog.getInstance();
        r2.m750w(r0);
    L_0x00c1:
        r0 = r1;
        goto L_0x002d;
    L_0x00c4:
        r1 = move-exception;
    L_0x00c5:
        r2 = com.mob.tools.MobLog.getInstance();
        r2.m750w(r1);
        goto L_0x009c;
    L_0x00cd:
        r2 = move-exception;
        goto L_0x002d;
    L_0x00d0:
        r2 = move-exception;
        goto L_0x00b8;
    L_0x00d2:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x00c5;
    L_0x00d7:
        r0 = move-exception;
        goto L_0x00b3;
    L_0x00d9:
        r0 = move-exception;
        goto L_0x009f;
    L_0x00db:
        r2 = r0;
        goto L_0x006a;
    L_0x00dd:
        r1 = r0;
        goto L_0x0055;
    L_0x00e0:
        r0 = r1;
        goto L_0x009c;
    L_0x00e2:
        r3 = r0;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.authorize.a.b(android.content.Context, com.mob.commons.authorize.MobProduct):java.lang.String");
    }

    private final HashMap<String, Object> m13166c(Context context, MobProduct mobProduct) {
        int parseInt;
        Throwable th;
        ObjectOutputStream objectOutputStream;
        DeviceHelper instance = DeviceHelper.getInstance(context);
        int i = -1;
        try {
            parseInt = C2178R.parseInt(instance.getCarrier());
        } catch (Throwable th2) {
            MobLog.getInstance().m750w(th2);
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("adsid", instance.getAdvertisingID());
        hashMap.put("imei", instance.getIMEI());
        hashMap.put("serialno", instance.getSerialno());
        hashMap.put("androidid", instance.getAndroidID());
        hashMap.put("mac", instance.getMacAddress());
        hashMap.put("model", instance.getModel());
        hashMap.put("factory", instance.getManufacturer());
        hashMap.put("carrier", Integer.valueOf(parseInt));
        hashMap.put("screensize", instance.getScreenSize());
        hashMap.put("sysver", instance.getOSVersionName());
        hashMap.put("plat", Integer.valueOf(1));
        Hashon hashon = new Hashon();
        String encodeToString = Base64.encodeToString(Data.AES128Encode("sdk.commonap.sdk", hashon.fromHashMap(hashMap)), 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", encodeToString));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        HashMap fromJson = hashon.fromJson(new NetworkHelper().httpPost("http://devs.data.mob.com:80/dinfo", arrayList, null, null, networkTimeOut));
        if (fromJson == null) {
            return null;
        }
        encodeToString = (String) fromJson.get("duid");
        if (encodeToString == null || encodeToString.length() <= 0) {
            return null;
        }
        HashMap<String, Object> hashMap2 = new HashMap();
        try {
            hashMap2.put("duid", encodeToString);
            hashMap.put("carrier", String.valueOf(parseInt));
            hashMap2.put("deviceInfo", hashMap);
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(C2178R.getCacheRoot(context), ".duid")));
            try {
                objectOutputStream.writeObject(hashMap2);
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.flush();
                        objectOutputStream.close();
                    } catch (Throwable th3) {
                    }
                }
            } catch (Throwable th4) {
                th2 = th4;
                try {
                    MobLog.getInstance().m750w(th2);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.flush();
                            objectOutputStream.close();
                        } catch (Throwable th5) {
                        }
                    }
                    return hashMap2;
                } catch (Throwable th6) {
                    th2 = th6;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.flush();
                            objectOutputStream.close();
                        } catch (Throwable th7) {
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th8) {
            th2 = th8;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.flush();
                objectOutputStream.close();
            }
            throw th2;
        }
        return hashMap2;
    }

    public final synchronized String m13167a(Context context, MobProduct mobProduct) {
        String b;
        try {
            File file = new File(C2178R.getCacheRoot(context), ".globalLock");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileLocker fileLocker = new FileLocker();
            fileLocker.setLockFile(file.getAbsolutePath());
            if (fileLocker.lock(true)) {
                b = m13165b(context, mobProduct);
                fileLocker.release();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
        b = null;
        return b;
    }
}
