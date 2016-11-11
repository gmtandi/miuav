package com.amap.api.services.core;

import android.content.Context;
import android.database.Cursor;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpHost;

public class ac {
    private static String m4478a() {
        String defaultHost;
        try {
            defaultHost = Proxy.getDefaultHost();
        } catch (Throwable th) {
            th.printStackTrace();
            ay.m4590a(th, "ProxyUtil", "getDefHost");
            defaultHost = null;
        }
        return defaultHost == null ? "null" : defaultHost;
    }

    public static HttpHost m4479a(Context context) {
        try {
            return VERSION.SDK_INT >= 11 ? m4480a(context, new URI("http://restapi.amap.com")) : m4483b(context);
        } catch (Throwable e) {
            ay.m4590a(e, "ProxyUtil", "getProxy");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            ay.m4590a(e2, "ProxyUtil", "getProxy");
            e2.printStackTrace();
            return null;
        }
    }

    private static HttpHost m4480a(Context context, URI uri) {
        if (C0500z.m4896g(context) == 0) {
            try {
                java.net.Proxy proxy;
                String hostName;
                int i = -1;
                List select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    proxy = null;
                } else {
                    proxy = (java.net.Proxy) select.get(0);
                    if (proxy == null || proxy.type() == Type.DIRECT) {
                        proxy = null;
                    }
                }
                if (proxy != null) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                    if (inetSocketAddress != null) {
                        hostName = inetSocketAddress.getHostName();
                        i = inetSocketAddress.getPort();
                        if (m4481a(hostName, i)) {
                            return new HttpHost(hostName, i, UriUtil.HTTP_SCHEME);
                        }
                    }
                }
                hostName = null;
                if (m4481a(hostName, i)) {
                    return new HttpHost(hostName, i, UriUtil.HTTP_SCHEME);
                }
            } catch (Throwable e) {
                ay.m4590a(e, "ProxyUtil", "getProxySelectorCfg");
                e.printStackTrace();
            }
        }
        return null;
    }

    private static boolean m4481a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static int m4482b() {
        int i = -1;
        try {
            i = Proxy.getDefaultPort();
        } catch (Throwable th) {
            ay.m4590a(th, "ProxyUtil", "getDefPort");
            th.printStackTrace();
        }
        return i;
    }

    private static HttpHost m4483b(Context context) {
        Cursor query;
        String string;
        String a;
        Object obj;
        Throwable th;
        int i;
        Throwable th2;
        String toLowerCase;
        Cursor cursor;
        Throwable th3;
        ay b;
        Object obj2 = null;
        if (C0500z.m4896g(context) == 0) {
            int b2;
            String str;
            try {
                query = context.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            string = query.getString(query.getColumnIndex("apn"));
                            if (string != null) {
                                string = string.toLowerCase(Locale.US);
                            }
                            int i2;
                            if (string != null && string.contains("ctwap")) {
                                a = m4478a();
                                b2 = m4482b();
                                try {
                                    if (TextUtils.isEmpty(a) || a.equals("null")) {
                                        obj = null;
                                        a = null;
                                    } else {
                                        obj = 1;
                                    }
                                    if (obj == null) {
                                        try {
                                            str = "10.0.0.200";
                                        } catch (Throwable e) {
                                            th = e;
                                            i = b2;
                                            th2 = th;
                                            ay.m4590a(th2, "ProxyUtil", "getHostProxy");
                                            string = C0500z.m4898i(context);
                                            if (string == null) {
                                                b2 = i;
                                                str = a;
                                            } else {
                                                toLowerCase = string.toLowerCase(Locale.US);
                                                str = m4478a();
                                                b2 = m4482b();
                                                if (toLowerCase.indexOf("ctwap") != -1) {
                                                    obj2 = 1;
                                                    a = str;
                                                    if (obj2 == null) {
                                                        a = "10.0.0.200";
                                                    }
                                                    if (b2 == -1) {
                                                        b2 = 80;
                                                        str = a;
                                                    }
                                                } else if (toLowerCase.indexOf("wap") != -1) {
                                                    if (!TextUtils.isEmpty(str)) {
                                                    }
                                                    obj = null;
                                                    string = a;
                                                    if (obj == null) {
                                                        string = "10.0.0.200";
                                                    }
                                                    str = string;
                                                    b2 = 80;
                                                }
                                                str = a;
                                            }
                                            if (query != null) {
                                                query.close();
                                            }
                                            if (m4481a(str, b2)) {
                                                return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                            }
                                            return null;
                                        } catch (Throwable e2) {
                                            th = e2;
                                            str = a;
                                            cursor = query;
                                            th3 = th;
                                            ay.m4590a(th3, "ProxyUtil", "getHostProxy1");
                                            th3.printStackTrace();
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                            if (m4481a(str, b2)) {
                                                return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                            }
                                            return null;
                                        }
                                    }
                                    str = a;
                                    if (b2 == -1) {
                                        b2 = 80;
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                } catch (Throwable e22) {
                                    a = null;
                                    i2 = b2;
                                    th2 = e22;
                                    i = i2;
                                    ay.m4590a(th2, "ProxyUtil", "getHostProxy");
                                    string = C0500z.m4898i(context);
                                    if (string == null) {
                                        toLowerCase = string.toLowerCase(Locale.US);
                                        str = m4478a();
                                        b2 = m4482b();
                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                            if (!(TextUtils.isEmpty(str) || str.equals("null"))) {
                                                obj2 = 1;
                                                a = str;
                                            }
                                            if (obj2 == null) {
                                                a = "10.0.0.200";
                                            }
                                            if (b2 == -1) {
                                                b2 = 80;
                                                str = a;
                                            }
                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                            if (TextUtils.isEmpty(str) || str.equals("null")) {
                                                obj = null;
                                                string = a;
                                            } else {
                                                string = str;
                                                obj = 1;
                                            }
                                            if (obj == null) {
                                                string = "10.0.0.200";
                                            }
                                            str = string;
                                            b2 = 80;
                                        }
                                        str = a;
                                    } else {
                                        b2 = i;
                                        str = a;
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                    if (m4481a(str, b2)) {
                                        return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                    }
                                    return null;
                                } catch (Throwable e222) {
                                    cursor = query;
                                    th3 = e222;
                                    str = null;
                                    ay.m4590a(th3, "ProxyUtil", "getHostProxy1");
                                    th3.printStackTrace();
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (m4481a(str, b2)) {
                                        return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                    }
                                    return null;
                                }
                                if (m4481a(str, b2)) {
                                    return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                }
                            } else if (string != null) {
                                if (string.contains("wap")) {
                                    a = m4478a();
                                    i = m4482b();
                                    try {
                                        Object obj3;
                                        if (TextUtils.isEmpty(a) || a.equals("null")) {
                                            obj3 = null;
                                            a = null;
                                        } else {
                                            obj3 = 1;
                                        }
                                        if (obj3 == null) {
                                            try {
                                                string = "10.0.0.172";
                                            } catch (SecurityException e3) {
                                                th2 = e3;
                                                try {
                                                    ay.m4590a(th2, "ProxyUtil", "getHostProxy");
                                                    string = C0500z.m4898i(context);
                                                    if (string == null) {
                                                        b2 = i;
                                                        str = a;
                                                    } else {
                                                        toLowerCase = string.toLowerCase(Locale.US);
                                                        str = m4478a();
                                                        b2 = m4482b();
                                                        if (toLowerCase.indexOf("ctwap") != -1) {
                                                            obj2 = 1;
                                                            a = str;
                                                            if (obj2 == null) {
                                                                a = "10.0.0.200";
                                                            }
                                                            if (b2 == -1) {
                                                                b2 = 80;
                                                                str = a;
                                                            }
                                                        } else if (toLowerCase.indexOf("wap") != -1) {
                                                            if (TextUtils.isEmpty(str)) {
                                                            }
                                                            obj = null;
                                                            string = a;
                                                            if (obj == null) {
                                                                string = "10.0.0.200";
                                                            }
                                                            str = string;
                                                            b2 = 80;
                                                        }
                                                        str = a;
                                                    }
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable th32) {
                                                            b = ay.m4591b();
                                                            if (b != null) {
                                                                b.m4593b(th32, "ProxyUtil", "getHostProxy2");
                                                            }
                                                            th32.printStackTrace();
                                                        }
                                                    }
                                                    if (m4481a(str, b2)) {
                                                        return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                                    }
                                                    return null;
                                                } catch (Throwable th4) {
                                                    th2 = th4;
                                                    if (query != null) {
                                                        try {
                                                            query.close();
                                                        } catch (Throwable e2222) {
                                                            ay b3 = ay.m4591b();
                                                            if (b3 != null) {
                                                                b3.m4593b(e2222, "ProxyUtil", "getHostProxy2");
                                                            }
                                                            e2222.printStackTrace();
                                                        }
                                                    }
                                                    throw th2;
                                                }
                                            } catch (Throwable th22) {
                                                th = th22;
                                                b2 = i;
                                                str = a;
                                                cursor = query;
                                                th32 = th;
                                                try {
                                                    ay.m4590a(th32, "ProxyUtil", "getHostProxy1");
                                                    th32.printStackTrace();
                                                    if (cursor != null) {
                                                        try {
                                                            cursor.close();
                                                        } catch (Throwable th322) {
                                                            b = ay.m4591b();
                                                            if (b != null) {
                                                                b.m4593b(th322, "ProxyUtil", "getHostProxy2");
                                                            }
                                                            th322.printStackTrace();
                                                        }
                                                    }
                                                    if (m4481a(str, b2)) {
                                                        return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                                    }
                                                    return null;
                                                } catch (Throwable th5) {
                                                    th22 = th5;
                                                    query = cursor;
                                                    if (query != null) {
                                                        query.close();
                                                    }
                                                    throw th22;
                                                }
                                            }
                                        }
                                        string = a;
                                        if (i == -1) {
                                            str = string;
                                            b2 = 80;
                                        } else {
                                            i2 = i;
                                            str = string;
                                            b2 = i2;
                                        }
                                        if (query != null) {
                                            try {
                                                query.close();
                                            } catch (Throwable th3222) {
                                                b = ay.m4591b();
                                                if (b != null) {
                                                    b.m4593b(th3222, "ProxyUtil", "getHostProxy2");
                                                }
                                                th3222.printStackTrace();
                                            }
                                        }
                                    } catch (SecurityException e4) {
                                        th22 = e4;
                                        a = null;
                                        ay.m4590a(th22, "ProxyUtil", "getHostProxy");
                                        string = C0500z.m4898i(context);
                                        if (string == null) {
                                            toLowerCase = string.toLowerCase(Locale.US);
                                            str = m4478a();
                                            b2 = m4482b();
                                            if (toLowerCase.indexOf("ctwap") != -1) {
                                                obj2 = 1;
                                                a = str;
                                                if (obj2 == null) {
                                                    a = "10.0.0.200";
                                                }
                                                if (b2 == -1) {
                                                    b2 = 80;
                                                    str = a;
                                                }
                                            } else if (toLowerCase.indexOf("wap") != -1) {
                                                if (TextUtils.isEmpty(str)) {
                                                }
                                                obj = null;
                                                string = a;
                                                if (obj == null) {
                                                    string = "10.0.0.200";
                                                }
                                                str = string;
                                                b2 = 80;
                                            }
                                            str = a;
                                        } else {
                                            b2 = i;
                                            str = a;
                                        }
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (m4481a(str, b2)) {
                                            return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                        }
                                        return null;
                                    } catch (Throwable th222) {
                                        cursor = query;
                                        th3222 = th222;
                                        b2 = i;
                                        str = null;
                                        ay.m4590a(th3222, "ProxyUtil", "getHostProxy1");
                                        th3222.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (m4481a(str, b2)) {
                                            return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                        }
                                        return null;
                                    }
                                    if (m4481a(str, b2)) {
                                        return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                                    }
                                }
                            }
                        }
                    } catch (SecurityException e5) {
                        th222 = e5;
                        i = -1;
                        a = null;
                        ay.m4590a(th222, "ProxyUtil", "getHostProxy");
                        string = C0500z.m4898i(context);
                        if (string == null) {
                            b2 = i;
                            str = a;
                        } else {
                            toLowerCase = string.toLowerCase(Locale.US);
                            str = m4478a();
                            b2 = m4482b();
                            if (toLowerCase.indexOf("ctwap") != -1) {
                                obj2 = 1;
                                a = str;
                                if (obj2 == null) {
                                    a = "10.0.0.200";
                                }
                                if (b2 == -1) {
                                    b2 = 80;
                                    str = a;
                                }
                            } else if (toLowerCase.indexOf("wap") != -1) {
                                if (TextUtils.isEmpty(str)) {
                                }
                                obj = null;
                                string = a;
                                if (obj == null) {
                                    string = "10.0.0.200";
                                }
                                str = string;
                                b2 = 80;
                            }
                            str = a;
                        }
                        if (query != null) {
                            query.close();
                        }
                        if (m4481a(str, b2)) {
                            return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                        }
                        return null;
                    } catch (Throwable th2222) {
                        cursor = query;
                        str = null;
                        th3222 = th2222;
                        b2 = -1;
                        ay.m4590a(th3222, "ProxyUtil", "getHostProxy1");
                        th3222.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (m4481a(str, b2)) {
                            return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                        }
                        return null;
                    }
                }
                b2 = -1;
                str = null;
                if (query != null) {
                    query.close();
                }
            } catch (SecurityException e6) {
                th2222 = e6;
                query = null;
                i = -1;
                a = null;
                ay.m4590a(th2222, "ProxyUtil", "getHostProxy");
                string = C0500z.m4898i(context);
                if (string == null) {
                    toLowerCase = string.toLowerCase(Locale.US);
                    str = m4478a();
                    b2 = m4482b();
                    if (toLowerCase.indexOf("ctwap") != -1) {
                        obj2 = 1;
                        a = str;
                        if (obj2 == null) {
                            a = "10.0.0.200";
                        }
                        if (b2 == -1) {
                            b2 = 80;
                            str = a;
                        }
                    } else if (toLowerCase.indexOf("wap") != -1) {
                        if (TextUtils.isEmpty(str)) {
                        }
                        obj = null;
                        string = a;
                        if (obj == null) {
                            string = "10.0.0.200";
                        }
                        str = string;
                        b2 = 80;
                    }
                    str = a;
                } else {
                    b2 = i;
                    str = a;
                }
                if (query != null) {
                    query.close();
                }
                if (m4481a(str, b2)) {
                    return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                }
                return null;
            } catch (Throwable th22222) {
                th3222 = th22222;
                cursor = null;
                str = null;
                b2 = -1;
                ay.m4590a(th3222, "ProxyUtil", "getHostProxy1");
                th3222.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                if (m4481a(str, b2)) {
                    return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
                }
                return null;
            } catch (Throwable th6) {
                th22222 = th6;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th22222;
            }
            if (m4481a(str, b2)) {
                return new HttpHost(str, b2, UriUtil.HTTP_SCHEME);
            }
        }
        return null;
    }
}
