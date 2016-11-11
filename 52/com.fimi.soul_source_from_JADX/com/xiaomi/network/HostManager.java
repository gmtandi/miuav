package com.xiaomi.network;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.services.district.DistrictSearchQuery;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.common.logger.thrift.mfs.C2481a;
import com.xiaomi.common.logger.thrift.mfs.C2483b;
import com.xiaomi.common.logger.thrift.mfs.C2487d;
import com.xiaomi.common.logger.thrift.mfs.C2489e;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

public class HostManager {
    private static HostManagerFactory factory;
    private static boolean hostLoaded;
    private static Map<String, ArrayList<String>> mReservedHosts;
    private static String sAppName;
    private static String sAppVersion;
    private static HostManager sInstance;
    private final long MAX_REQUEST_FAILURE_CNT;
    private String currentISP;
    private long lastRemoteRequestTimestamp;
    protected Map<String, Fallbacks> mHostsMapping;
    private long remoteRequestFailureCount;
    private Context sAppContext;
    private HostFilter sHostFilter;
    private HttpGet sHttpGetter;
    private String sUserId;

    public interface HostManagerFactory {
        HostManager m14852a(Context context, HostFilter hostFilter, HttpGet httpGet, String str);
    }

    public interface HttpGet {
        String m14853a(String str);
    }

    static {
        mReservedHosts = new HashMap();
        hostLoaded = false;
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        this.mHostsMapping = new HashMap();
        this.sUserId = Constants.VIA_RESULT_SUCCESS;
        this.remoteRequestFailureCount = 0;
        this.MAX_REQUEST_FAILURE_CNT = 15;
        this.lastRemoteRequestTimestamp = 0;
        this.currentISP = "isp_prov_city_country_ip";
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
        this.sHttpGetter = httpGet;
        if (hostFilter == null) {
            this.sHostFilter = new C2623b(this);
        } else {
            this.sHostFilter = hostFilter;
        }
        this.sUserId = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        sAppName = str2;
        if (str3 == null) {
            str3 = getVersionName();
        }
        sAppVersion = str3;
    }

    public static void addReservedHost(String str, String str2) {
        ArrayList arrayList = (ArrayList) mReservedHosts.get(str);
        synchronized (mReservedHosts) {
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayList.add(str2);
                mReservedHosts.put(str, arrayList);
            } else if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
    }

    private void fromJSON(String str) {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Fallbacks fromJSON = new Fallbacks().fromJSON(jSONArray.getJSONObject(i));
                this.mHostsMapping.put(fromJSON.getHost(), fromJSON);
            }
        }
    }

    public static synchronized HostManager getInstance() {
        HostManager hostManager;
        synchronized (HostManager.class) {
            if (sInstance == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            hostManager = sInstance;
        }
        return hostManager;
    }

    private Fallback getLocalFallback(String str) {
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(str);
        }
        if (fallbacks != null) {
            Fallback fallback = fallbacks.getFallback();
            if (fallback != null) {
                return fallback;
            }
        }
        return null;
    }

    private String getRemoteFallbackJSON(ArrayList<String> arrayList, String str) {
        ArrayList arrayList2 = new ArrayList();
        List<NameValuePair> arrayList3 = new ArrayList();
        arrayList3.add(new BasicNameValuePair(SocialConstants.PARAM_TYPE, str));
        arrayList3.add(new BasicNameValuePair("uuid", this.sUserId));
        arrayList3.add(new BasicNameValuePair("list", join((Collection) arrayList, MiPushClient.ACCEPT_TIME_SEPARATOR)));
        Fallback localFallback = getLocalFallback("resolver.gslb.mi-idc.com");
        String format = String.format("http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", new Object[]{"resolver.gslb.mi-idc.com"});
        if (localFallback == null) {
            arrayList2.add(format);
        } else {
            arrayList2 = localFallback.m14832a(format);
        }
        Iterator it = arrayList2.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Builder buildUpon = Uri.parse((String) it.next()).buildUpon();
        for (NameValuePair nameValuePair : arrayList3) {
            buildUpon.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
        }
        return this.sHttpGetter == null ? Network.m14858a(this.sAppContext, new URL(buildUpon.toString())) : this.sHttpGetter.m14853a(buildUpon.toString());
    }

    private String getVersionName() {
        try {
            PackageInfo packageInfo = this.sAppContext.getPackageManager().getPackageInfo(this.sAppContext.getPackageName(), Opcodes.ACC_ENUM);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return Constants.VIA_RESULT_SUCCESS;
    }

    public static synchronized void init(Context context, HostFilter hostFilter, HttpGet httpGet, String str, String str2, String str3) {
        synchronized (HostManager.class) {
            if (sInstance == null) {
                if (factory == null) {
                    sInstance = new HostManager(context, hostFilter, httpGet, str, str2, str3);
                } else {
                    sInstance = factory.m14852a(context, hostFilter, httpGet, str);
                }
                if (sInstance != null) {
                    if (UploadHostStatHelper.m14867a() == null) {
                        UploadHostStatHelper.m14870a(context);
                    }
                    UploadHostStatHelper.m14867a().m14876a(new C2622a());
                }
            }
        }
    }

    public static <T> String join(Collection<T> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next());
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return C2915a.f14760f;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(str);
            stringBuilder.append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    private String processNetwork(String str) {
        return TextUtils.isEmpty(str) ? FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN : str.startsWith("WIFI") ? "WIFI" : str;
    }

    private Fallback requestRemoteFallback(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return (Fallback) requestRemoteFallbacks(arrayList).get(0);
    }

    private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> arrayList) {
        String str;
        int i;
        purge();
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            for (String str2 : this.mHostsMapping.keySet()) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        synchronized (mReservedHosts) {
            for (String str22 : mReservedHosts.keySet()) {
                if (!arrayList.contains(str22)) {
                    arrayList.add(str22);
                }
            }
        }
        if (!arrayList.contains("resolver.gslb.mi-idc.com")) {
            arrayList.add("resolver.gslb.mi-idc.com");
        }
        ArrayList<Fallback> arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str22 = isWIFIConnected() ? "wifi" : "wap";
            Object remoteFallbackJSON = getRemoteFallbackJSON(arrayList, str22);
            if (!TextUtils.isEmpty(remoteFallbackJSON)) {
                JSONObject jSONObject = new JSONObject(remoteFallbackJSON);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString(DistrictSearchQuery.KEYWORDS_PROVINCE);
                    String string2 = jSONObject.getString(DistrictSearchQuery.KEYWORDS_CITY);
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString(DistrictSearchQuery.KEYWORDS_COUNTRY);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str22);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        str22 = (String) arrayList.get(i2);
                        JSONArray jSONArray = jSONObject2.getJSONArray(str22);
                        Fallback fallback = new Fallback(str22);
                        for (i = 0; i < jSONArray.length(); i++) {
                            Object string6 = jSONArray.getString(i);
                            if (!TextUtils.isEmpty(string6)) {
                                fallback.m14835a(new WeightedHost(string6, jSONArray.length() - i));
                            }
                        }
                        arrayList2.set(i2, fallback);
                        fallback.f13022g = string5;
                        fallback.f13018c = string;
                        fallback.f13020e = string3;
                        fallback.f13021f = string4;
                        fallback.f13019d = string2;
                        if (jSONObject.has("stat-percent")) {
                            fallback.m14833a(jSONObject.getDouble("stat-percent"));
                        }
                        if (jSONObject.has("stat-domain")) {
                            fallback.m14846c(jSONObject.getString("stat-domain"));
                        }
                        if (jSONObject.has("ttl")) {
                            fallback.m14834a(((long) jSONObject.getInt("ttl")) * 1000);
                        }
                        setCurrentISP(fallback.m14847d());
                    }
                }
            }
        } catch (JSONException e) {
        } catch (IOException e2) {
        } catch (Exception e3) {
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Fallback fallback2 = (Fallback) arrayList2.get(i3);
            if (fallback2 != null) {
                updateFallbacks((String) arrayList.get(i3), fallback2);
            }
        }
        persist();
        return arrayList2;
    }

    private JSONArray toJSON() {
        JSONArray jSONArray;
        synchronized (this.mHostsMapping) {
            jSONArray = new JSONArray();
            for (Fallbacks toJSON : this.mHostsMapping.values()) {
                jSONArray.put(toJSON.toJSON());
            }
        }
        return jSONArray;
    }

    protected boolean checkHostMapping() {
        IOException e;
        Throwable th;
        synchronized (this.mHostsMapping) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            BufferedReader bufferedReader = null;
            BufferedReader bufferedReader2;
            try {
                File file = new File(this.sAppContext.getFilesDir(), getProcessName());
                if (file.isFile()) {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                        }
                        Object stringBuilder2 = stringBuilder.toString();
                        if (!TextUtils.isEmpty(stringBuilder2)) {
                            fromJSON(stringBuilder2);
                            Log.v("HostManager", "loading the new hosts succeed");
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                }
                            }
                            return true;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            e.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e4) {
                                }
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                        try {
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e6) {
                                }
                            }
                            return false;
                        } catch (Throwable th4) {
                            th = th4;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    }
                }
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e7) {
                    }
                }
            } catch (IOException e8) {
                e = e8;
                bufferedReader2 = null;
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return false;
            } catch (Throwable th5) {
                th = th5;
                th.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return false;
            }
            return false;
        }
    }

    public ArrayList<C2483b> generateHostStats() {
        ArrayList<C2483b> arrayList;
        synchronized (this.mHostsMapping) {
            Map hashMap = new HashMap();
            for (String str : this.mHostsMapping.keySet()) {
                Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(str);
                if (fallbacks != null) {
                    Iterator it = fallbacks.getFallbacks().iterator();
                    while (it.hasNext()) {
                        C2483b c2483b;
                        Fallback fallback = (Fallback) it.next();
                        C2483b c2483b2 = (C2483b) hashMap.get(fallback.m14847d());
                        if (c2483b2 == null) {
                            c2483b2 = new C2483b();
                            c2483b2.m14196a("httpapi");
                            c2483b2.m14209e(fallback.f13021f);
                            c2483b2.m14207d(processNetwork(fallback.f13016a));
                            c2483b2.m14202b(this.sUserId);
                            c2483b2.m14205c(sAppVersion);
                            c2483b2.m14211f(sAppName);
                            c2483b2.m14214g(this.sAppContext.getPackageName());
                            c2483b2.m14215h(getVersionName());
                            C2489e c2489e = new C2489e();
                            c2489e.m14266c(fallback.f13019d);
                            c2489e.m14258a(fallback.f13022g);
                            c2489e.m14263b(fallback.f13018c);
                            c2489e.m14268d(fallback.f13020e);
                            c2483b2.m14195a(c2489e);
                            hashMap.put(fallback.m14847d(), c2483b2);
                            c2483b = c2483b2;
                        } else {
                            c2483b = c2483b2;
                        }
                        C2481a c2481a = new C2481a();
                        c2481a.m14185a(fallback.f13017b);
                        List arrayList2 = new ArrayList();
                        Iterator it2 = fallback.m14848e().iterator();
                        while (it2.hasNext()) {
                            WeightedHost weightedHost = (WeightedHost) it2.next();
                            ArrayList a = weightedHost.m14880a();
                            if (!a.isEmpty()) {
                                C2487d c2487d = new C2487d();
                                c2487d.m14237a(weightedHost.f13042a);
                                int i = 0;
                                int i2 = 0;
                                long j = 0;
                                int i3 = 0;
                                Map hashMap2 = new HashMap();
                                Iterator it3 = a.iterator();
                                while (it3.hasNext()) {
                                    int d;
                                    AccessHistory accessHistory = (AccessHistory) it3.next();
                                    if (accessHistory.m14821a() >= 0) {
                                        j += accessHistory.m14823b();
                                        d = (int) (accessHistory.m14825d() + ((long) i3));
                                        i3 = i2;
                                        i2 = i + 1;
                                    } else {
                                        CharSequence e = accessHistory.m14826e();
                                        if (!TextUtils.isEmpty(e)) {
                                            hashMap2.put(e, Integer.valueOf(hashMap2.containsKey(e) ? ((Integer) hashMap2.get(e)).intValue() + 1 : 1));
                                        }
                                        d = i2 + 1;
                                        i2 = i;
                                        int i4 = d;
                                        d = i3;
                                        i3 = i4;
                                    }
                                    i = i2;
                                    i2 = i3;
                                    i3 = d;
                                }
                                c2487d.m14238a(hashMap2);
                                c2487d.m14244b(i);
                                c2487d.m14235a(i2);
                                c2487d.m14236a(j);
                                c2487d.m14248c(i3);
                                arrayList2.add(c2487d);
                            }
                        }
                        if (!arrayList2.isEmpty()) {
                            c2481a.m14186a(arrayList2);
                            c2483b.m14197a(c2481a);
                        }
                    }
                    continue;
                }
            }
            arrayList = new ArrayList();
            for (C2483b c2483b3 : hashMap.values()) {
                if (c2483b3.m14213g() > 0) {
                    arrayList.add(c2483b3);
                }
            }
        }
        return arrayList;
    }

    public String getActiveNetworkLabel() {
        if (this.sAppContext == null) {
            return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.sAppContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) this.sAppContext.getSystemService("wifi");
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return FimiMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        } catch (Exception e) {
        }
    }

    public Fallback getFallbacksByHost(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.sHostFilter.m14851a(str)) {
            return null;
        } else {
            Fallback localFallback = getLocalFallback(str);
            if (localFallback != null) {
                return localFallback;
            }
            if (System.currentTimeMillis() - this.lastRemoteRequestTimestamp > (this.remoteRequestFailureCount * 60) * 1000) {
                this.lastRemoteRequestTimestamp = System.currentTimeMillis();
                localFallback = requestRemoteFallback(str);
                if (localFallback != null) {
                    this.remoteRequestFailureCount = 0;
                    return localFallback;
                } else if (this.remoteRequestFailureCount < 15) {
                    this.remoteRequestFailureCount++;
                }
            }
            ArrayList arrayList = (ArrayList) mReservedHosts.get(str);
            synchronized (mReservedHosts) {
                if (arrayList != null) {
                    Fallback fallback = new Fallback(str);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        fallback.m14843b((String) it.next());
                    }
                    return fallback;
                }
                return null;
            }
        }
    }

    protected String getProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.sAppContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    public boolean isWIFIConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.sAppContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return 1 == activeNetworkInfo.getType();
        } catch (Exception e) {
            return false;
        }
    }

    public void persist() {
        purge();
        synchronized (this.mHostsMapping) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.sAppContext.openFileOutput(getProcessName(), 0)));
                Object jSONArray = toJSON().toString();
                if (!TextUtils.isEmpty(jSONArray)) {
                    bufferedWriter.write(jSONArray);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void purge() {
        synchronized (this.mHostsMapping) {
            for (Fallbacks purge : this.mHostsMapping.values()) {
                purge.purge();
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.mHostsMapping.keySet()) {
                    if (((Fallbacks) this.mHostsMapping.get(str)).getFallbacks().isEmpty()) {
                        this.mHostsMapping.remove(str);
                        obj = null;
                        break;
                    }
                }
                obj = 1;
            }
        }
    }

    public void refreshFallbacks() {
        ArrayList arrayList;
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            arrayList = new ArrayList(this.mHostsMapping.keySet());
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(arrayList.get(size));
                if (!(fallbacks == null || fallbacks.getFallback() == null)) {
                    arrayList.remove(size);
                }
            }
        }
        ArrayList requestRemoteFallbacks = requestRemoteFallbacks(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            if (requestRemoteFallbacks.get(i) != null) {
                updateFallbacks((String) arrayList.get(i), (Fallback) requestRemoteFallbacks.get(i));
            }
        }
    }

    public void setCurrentISP(String str) {
        this.currentISP = str;
    }

    public void updateFallbacks(String str, Fallback fallback) {
        if (TextUtils.isEmpty(str) || fallback == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + fallback);
        } else if (this.sHostFilter.m14851a(str)) {
            synchronized (this.mHostsMapping) {
                checkHostMapping();
                if (this.mHostsMapping.containsKey(str)) {
                    ((Fallbacks) this.mHostsMapping.get(str)).addFallback(fallback);
                } else {
                    Fallbacks fallbacks = new Fallbacks(str);
                    fallbacks.addFallback(fallback);
                    this.mHostsMapping.put(str, fallbacks);
                }
            }
        }
    }
}
