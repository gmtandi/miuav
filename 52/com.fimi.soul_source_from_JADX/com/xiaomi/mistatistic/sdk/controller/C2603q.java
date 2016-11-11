package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.SocialConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import org.apache.http.NameValuePair;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2955m;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.q */
public abstract class C2603q {
    public static String m14771a(Context context, String str, List list) {
        OutputStream outputStream;
        IOException e;
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(SocialConstants.PARAM_URL);
        }
        try {
            HttpURLConnection a = C2603q.m14774a(context, new URL(str));
            a.setConnectTimeout(C1873o.ak);
            a.setReadTimeout(15000);
            a.setRequestMethod(C2955m.f14864a);
            String a2 = C2603q.m14773a(list);
            if (a2 == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            a.setDoOutput(true);
            byte[] bytes = a2.getBytes();
            outputStream = a.getOutputStream();
            try {
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
                OutputStream outputStream2 = null;
                a.getResponseCode();
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new C2604r(a.getInputStream())));
                try {
                    String readLine;
                    StringBuffer stringBuffer = new StringBuffer();
                    String property = System.getProperty("line.separator");
                    for (readLine = bufferedReader3.readLine(); readLine != null; readLine = bufferedReader3.readLine()) {
                        stringBuffer.append(readLine);
                        stringBuffer.append(property);
                    }
                    readLine = stringBuffer.toString();
                    bufferedReader3.close();
                    bufferedReader3 = null;
                    if (null != null) {
                        try {
                            outputStream2.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (null != null) {
                        bufferedReader3.close();
                    }
                    return readLine;
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = bufferedReader3;
                    outputStream = null;
                    bufferedReader2 = bufferedReader;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader3;
                    outputStream = null;
                    bufferedReader2 = bufferedReader;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            throw th;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                throw e;
            } catch (Throwable th4) {
                th = th4;
                throw new IOException(th.getMessage());
            }
        } catch (IOException e6) {
            e = e6;
            outputStream = null;
            throw e;
        } catch (Throwable th5) {
            th = th5;
            outputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static String m14772a(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url.getProtocol()).append("://").append("10.0.0.172").append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            stringBuilder.append("?").append(url.getQuery());
        }
        return stringBuilder.toString();
    }

    public static String m14773a(List list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (NameValuePair nameValuePair : list) {
            try {
                if (nameValuePair.getValue() != null) {
                    stringBuffer.append(URLEncoder.encode(nameValuePair.getName(), C1142e.f5201a));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(nameValuePair.getValue(), C1142e.f5201a));
                    stringBuffer.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return (stringBuffer.length() > 0 ? stringBuffer.deleteCharAt(stringBuffer.length() - 1) : stringBuffer).toString();
    }

    public static HttpURLConnection m14774a(Context context, URL url) {
        if (!UriUtil.HTTP_SCHEME.equals(url.getProtocol())) {
            return (HttpURLConnection) url.openConnection();
        }
        if (C2603q.m14778d(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!C2603q.m14777c(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(C2603q.m14772a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    public static boolean m14775a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                return 1 == activeNetworkInfo.getType();
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static String m14776b(Context context) {
        if (C2603q.m14775a(context)) {
            return "WIFI";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return C2915a.f14760f;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? C2915a.f14760f : (activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName() + "-" + activeNetworkInfo.getExtraInfo()).toLowerCase();
            } catch (Exception e) {
                return C2915a.f14760f;
            }
        } catch (Exception e2) {
            return C2915a.f14760f;
        }
    }

    public static boolean m14777c(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap")) ? false : extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3);
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean m14778d(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return false;
                }
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3) ? false : extraInfo.contains("ctwap");
            } catch (Exception e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }
}
