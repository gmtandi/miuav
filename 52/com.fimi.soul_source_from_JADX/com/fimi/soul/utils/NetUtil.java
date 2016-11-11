package com.fimi.soul.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.kernel.p084e.C1173l;
import com.fimi.soul.base.C1236a;
import com.tencent.connect.common.Constants;
import it.p074a.p075a.C2799f;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p124f.p125c.C3030l;
import org.p122a.p123a.p167i.p169b.C3096v;

public class NetUtil {
    public static String f10003a = null;
    public static Header f10004b = null;
    public static final String f10005c = "admin";

    static {
        System.loadLibrary("server-jni");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m12197a(java.lang.String r6, java.util.List<org.apache.http.NameValuePair> r7, android.content.Context r8) {
        /*
        r0 = m12205b(r7);
        r0 = m12203a(r0);
        if (r0 == 0) goto L_0x000c;
    L_0x000a:
        r6 = "";
    L_0x000c:
        r0 = "save";
        r1 = 3;
        r1 = r8.getSharedPreferences(r0, r1);
        m12201a(r7, r8);
        r2 = new org.apache.http.client.methods.HttpPost;
        r2.<init>(r6);
        r0 = f10003a;
        if (r0 == 0) goto L_0x00b9;
    L_0x001f:
        r0 = "Cookie";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "name=";
        r3 = r3.append(r4);
        r4 = "cookie_name";
        r5 = "";
        r4 = r1.getString(r4, r5);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.addHeader(r0, r3);
        r0 = "Cookie";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "password=";
        r3 = r3.append(r4);
        r4 = "cookie_password";
        r5 = "";
        r4 = r1.getString(r4, r5);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.addHeader(r0, r3);
        r0 = "Cookie";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "JSESSIONID=";
        r3 = r3.append(r4);
        r4 = f10003a;
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.addHeader(r0, r3);
        r0 = "Cookie";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "key=";
        r3 = r3.append(r4);
        r4 = "cookie_key";
        r5 = "";
        r4 = r1.getString(r4, r5);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.addHeader(r0, r3);
        r0 = "Cookie";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "datasourceName=";
        r3 = r3.append(r4);
        r4 = "cookie_datasourceName";
        r5 = "";
        r4 = r1.getString(r4, r5);
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.addHeader(r0, r3);
    L_0x00b9:
        if (r7 == 0) goto L_0x00cb;
    L_0x00bb:
        r0 = r7.size();	 Catch:{ UnsupportedEncodingException -> 0x017f }
        if (r0 <= 0) goto L_0x00cb;
    L_0x00c1:
        r0 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 Catch:{ UnsupportedEncodingException -> 0x017f }
        r3 = "UTF-8";
        r0.<init>(r7, r3);	 Catch:{ UnsupportedEncodingException -> 0x017f }
        r2.setEntity(r0);	 Catch:{ UnsupportedEncodingException -> 0x017f }
    L_0x00cb:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "start... url: ";
        r0 = r0.append(r3);
        r0 = r0.append(r6);
        r3 = ",JSESSIONID=";
        r0 = r0.append(r3);
        r3 = f10003a;
        r0 = r0.append(r3);
        r3 = ",my_name=";
        r0 = r0.append(r3);
        r3 = "cookie_name";
        r4 = "";
        r3 = r1.getString(r3, r4);
        r0 = r0.append(r3);
        r3 = ",my_pwd=";
        r0 = r0.append(r3);
        r3 = "cookie_password";
        r4 = "";
        r3 = r1.getString(r3, r4);
        r0 = r0.append(r3);
        r3 = ",key=";
        r0 = r0.append(r3);
        r3 = "cookie_key";
        r4 = "";
        r3 = r1.getString(r3, r4);
        r0 = r0.append(r3);
        r3 = ",datasourceName=";
        r0 = r0.append(r3);
        r3 = "cookie_datasourceName";
        r4 = "";
        r1 = r1.getString(r3, r4);
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = com.fimi.soul.utils.NetUtil.class;
        com.fimi.soul.base.C1236a.m8532a(r0, r1);
        r3 = m12200a();
        r0 = "";
        r1 = r3.execute(r2);	 Catch:{ Exception -> 0x018d }
        r2 = "Content-Encoding";
        r2 = r1.getFirstHeader(r2);	 Catch:{ Exception -> 0x018d }
        f10004b = r2;	 Catch:{ Exception -> 0x018d }
        r2 = r1.getStatusLine();	 Catch:{ Exception -> 0x018d }
        r2 = r2.getStatusCode();	 Catch:{ Exception -> 0x018d }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 != r4) goto L_0x0185;
    L_0x0155:
        r1 = r1.getEntity();	 Catch:{ Exception -> 0x018d }
        r2 = "utf-8";
        r0 = org.apache.http.util.EntityUtils.toString(r1, r2);	 Catch:{ Exception -> 0x018d }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x018d }
        r1.<init>();	 Catch:{ Exception -> 0x018d }
        r2 = "data... json: ";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x018d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x018d }
        r1 = r1.toString();	 Catch:{ Exception -> 0x018d }
        r2 = com.fimi.soul.utils.NetUtil.class;
        com.fimi.soul.base.C1236a.m8532a(r1, r2);	 Catch:{ Exception -> 0x018d }
    L_0x0177:
        r1 = r3.getConnectionManager();
        r1.shutdown();
    L_0x017e:
        return r0;
    L_0x017f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00cb;
    L_0x0185:
        r1 = " request  timeout ";
        r2 = com.fimi.soul.utils.NetUtil.class;
        com.fimi.soul.base.C1236a.m8532a(r1, r2);	 Catch:{ Exception -> 0x018d }
        goto L_0x0177;
    L_0x018d:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x0199 }
        r1 = r3.getConnectionManager();
        r1.shutdown();
        goto L_0x017e;
    L_0x0199:
        r0 = move-exception;
        r1 = r3.getConnectionManager();
        r1.shutdown();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fimi.soul.utils.NetUtil.a(java.lang.String, java.util.List, android.content.Context):java.lang.String");
    }

    public static String m12198a(String str, List<NameValuePair> list, Context context, boolean z) {
        Exception e;
        if (!z) {
            return m12197a(str, list, context);
        }
        m12201a(list, context);
        SharedPreferences a = ay.m12293a(context);
        if (list != null && list.size() > 0) {
            str = str + "?" + URLEncodedUtils.format(list, C1142e.f5201a);
        }
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(30000));
        defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(120000));
        HttpUriRequest httpGet = new HttpGet(str);
        if (f10003a != null) {
            httpGet.addHeader("Cookie", "name=" + a.getString("cookie_name", C2915a.f14760f));
            httpGet.addHeader("Cookie", "password=" + a.getString("cookie_password", C2915a.f14760f));
            httpGet.addHeader("Cookie", "JSESSIONID=" + f10003a);
            httpGet.addHeader("Cookie", "key=" + a.getString("cookie_key", C2915a.f14760f));
            httpGet.addHeader("Cookie", "datasourceName=" + a.getString("cookie_datasourceName", C2915a.f14760f));
        }
        C1236a.m8532a("start... url: " + str + ",JSESSIONID=" + f10003a + ",my_name=" + a.getString("cookie_name", C2915a.f14760f) + ",my_pwd=" + a.getString("cookie_password", C2915a.f14760f) + ",key=" + a.getString("cookie_key", C2915a.f14760f) + ",datasourceName=" + a.getString("cookie_datasourceName", C2915a.f14760f), NetUtil.class);
        String str2 = C2915a.f14760f;
        String entityUtils;
        try {
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            f10004b = execute.getFirstHeader(C3004e.f15025k);
            if (statusCode == C2799f.f14282t) {
                List cookies = defaultHttpClient.getCookieStore().getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                    if ("JSESSIONID".equals(((Cookie) cookies.get(i)).getName())) {
                        f10003a = ((Cookie) cookies.get(i)).getValue();
                        break;
                    }
                }
                entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
                try {
                    C1236a.m8532a("data... json: " + entityUtils, NetUtil.class);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        return entityUtils;
                    } finally {
                        defaultHttpClient.getConnectionManager().shutdown();
                    }
                }
            } else {
                entityUtils = str2;
            }
            defaultHttpClient.getConnectionManager().shutdown();
            return entityUtils;
        } catch (Exception e3) {
            Exception exception = e3;
            entityUtils = str2;
            e = exception;
            e.printStackTrace();
            return entityUtils;
        }
    }

    public static String m12199a(List<NameValuePair> list) {
        Map treeMap = new TreeMap();
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (NameValuePair nameValuePair : list) {
                treeMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        if (treeMap == null || treeMap.size() == 0) {
            return null;
        }
        for (String str : treeMap.keySet()) {
            if (!(treeMap.get(str) == null || treeMap.get(str) == C2915a.f14760f)) {
                stringBuffer.append(str + "=" + ((String) treeMap.get(str)) + "&");
            }
        }
        try {
            return br.m12453a(URLEncoder.encode(stringBuffer.toString() + "key=", "utf-8"), getServerTokenKey()).toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HttpClient m12200a() {
        HttpClient httpClient = null;
        try {
            httpClient = C3096v.m17455a().m17422a(new C3026h(C3030l.m17122c().m17111a().m17114a(null, new ax()).m17119c())).m17453i();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
        } catch (KeyStoreException e3) {
            e3.printStackTrace();
        }
        return httpClient;
    }

    public static void m12201a(List<NameValuePair> list, Context context) {
        list.add(new BasicNameValuePair("req", C1173l.m8145a(new Date(), C1173l.f5333e) + ((int) (Math.random() * 1000000.0d)) + Constants.VIA_REPORT_TYPE_SHARE_TO_QQ));
        list.add(new BasicNameValuePair("channel", "android"));
        list.add(new BasicNameValuePair("deviceID", be.m12388g(context)));
        list.add(new BasicNameValuePair("visitID", C1236a.m8533b(context).getUserID().equals(C2915a.f14760f) ? Constants.VIA_RESULT_SUCCESS : C1236a.m8533b(context).getUserID()));
        list.add(new BasicNameValuePair("local", be.m12377d()));
        list.add(new BasicNameValuePair("appVersion", be.m12378d(context)));
        list.add(new BasicNameValuePair("signMsg", m12199a((List) list)));
    }

    public static boolean m12202a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo.isAvailable() && networkInfo.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    private static boolean m12203a(String str) {
        boolean z = false;
        for (C1973m c1973m : C1973m.values()) {
            if (c1973m.toString().equals(str)) {
                z = true;
            }
        }
        return z;
    }

    public static String m12204b(String str, List<NameValuePair> list, Context context) {
        return m12197a(str, list, context);
    }

    public static String m12205b(List<NameValuePair> list) {
        String str = null;
        if (list != null && list.size() > 0) {
            for (NameValuePair nameValuePair : list) {
                str = nameValuePair.getName().equals("commandCode") ? nameValuePair.getValue() : str;
            }
        }
        return str;
    }

    public static native String getServerTokenKey();
}
