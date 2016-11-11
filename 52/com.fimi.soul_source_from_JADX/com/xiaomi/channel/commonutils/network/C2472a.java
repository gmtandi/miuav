package com.xiaomi.channel.commonutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.SocialConstants;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p156c.C2955m;

/* renamed from: com.xiaomi.channel.commonutils.network.a */
public class C2472a {
    public static final Pattern f12441a;
    public static final Pattern f12442b;
    public static final Pattern f12443c;

    /* renamed from: com.xiaomi.channel.commonutils.network.a.a */
    public final class C2470a extends FilterInputStream {
        private boolean f12438a;

        public C2470a(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) {
            if (!this.f12438a) {
                int read = super.read(bArr, i, i2);
                if (read != -1) {
                    return read;
                }
            }
            this.f12438a = true;
            return -1;
        }
    }

    /* renamed from: com.xiaomi.channel.commonutils.network.a.b */
    public class C2471b {
        public int f12439a;
        public Map<String, String> f12440b;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", new Object[]{Integer.valueOf(this.f12439a), this.f12440b.toString()});
        }
    }

    static {
        f12441a = Pattern.compile("([^\\s;]+)(.*)");
        f12442b = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
        f12443c = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    }

    public static int m14143a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    public static String m14144a(Context context, String str, List<NameValuePair> list) {
        return C2472a.m14145a(context, str, list, null, null, null);
    }

    public static String m14145a(Context context, String str, List<NameValuePair> list, C2471b c2471b, String str2, String str3) {
        OutputStream outputStream;
        IOException e;
        Throwable th;
        BufferedReader bufferedReader;
        int i = 0;
        BufferedReader bufferedReader2 = null;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(SocialConstants.PARAM_URL);
        }
        try {
            HttpURLConnection a = C2472a.m14148a(context, C2472a.m14149a(str));
            a.setConnectTimeout(C1873o.ak);
            a.setReadTimeout(15000);
            a.setRequestMethod(C2955m.f14864a);
            if (!TextUtils.isEmpty(str2)) {
                a.setRequestProperty(C3004e.f15013Y, str2);
            }
            if (str3 != null) {
                a.setRequestProperty("Cookie", str3);
            }
            String a2 = C2472a.m14147a((List) list);
            if (a2 == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            OutputStream outputStream2;
            String headerField;
            BufferedReader bufferedReader3;
            a.setDoOutput(true);
            byte[] bytes = a2.getBytes();
            outputStream = a.getOutputStream();
            try {
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
                outputStream2 = null;
                int responseCode = a.getResponseCode();
                Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + responseCode);
                if (c2471b != null) {
                    c2471b.f12439a = responseCode;
                    if (c2471b.f12440b == null) {
                        c2471b.f12440b = new HashMap();
                    }
                    while (true) {
                        a2 = a.getHeaderFieldKey(i);
                        headerField = a.getHeaderField(i);
                        if (a2 == null && headerField == null) {
                            break;
                        }
                        c2471b.f12440b.put(a2, headerField);
                        i = (i + 1) + 1;
                    }
                }
                bufferedReader3 = new BufferedReader(new InputStreamReader(new C2470a(a.getInputStream())));
            } catch (IOException e2) {
                e = e2;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                throw new IOException(th);
            }
            try {
                StringBuffer stringBuffer = new StringBuffer();
                headerField = System.getProperty("line.separator");
                for (String readLine = bufferedReader3.readLine(); readLine != null; readLine = bufferedReader3.readLine()) {
                    stringBuffer.append(readLine);
                    stringBuffer.append(headerField);
                }
                String stringBuffer2 = stringBuffer.toString();
                bufferedReader3.close();
                BufferedReader bufferedReader4 = null;
                if (null != null) {
                    try {
                        outputStream2.close();
                    } catch (Throwable th4) {
                        Log.e("com.xiaomi.common.Network", "error while closing strean", th4);
                    }
                }
                if (null != null) {
                    bufferedReader4.close();
                }
                return stringBuffer2;
            } catch (IOException e3) {
                e = e3;
                bufferedReader = bufferedReader3;
                outputStream = null;
                bufferedReader2 = bufferedReader;
                throw e;
            } catch (Throwable th5) {
                th4 = th5;
                bufferedReader = bufferedReader3;
                outputStream = null;
                bufferedReader2 = bufferedReader;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable e4) {
                        Log.e("com.xiaomi.common.Network", "error while closing strean", e4);
                        throw th4;
                    }
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th4;
            }
        } catch (IOException e5) {
            e = e5;
            outputStream = null;
        } catch (Throwable th6) {
            th4 = th6;
            outputStream = null;
        }
    }

    public static String m14146a(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url.getProtocol()).append("://").append("10.0.0.172").append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            stringBuilder.append("?").append(url.getQuery());
        }
        return stringBuilder.toString();
    }

    public static String m14147a(List<NameValuePair> list) {
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
                Log.d("com.xiaomi.common.Network", "Failed to convert from param list to string: " + e.toString());
                Log.d("com.xiaomi.common.Network", "pair: " + nameValuePair.toString());
                return null;
            }
        }
        return (stringBuffer.length() > 0 ? stringBuffer.deleteCharAt(stringBuffer.length() - 1) : stringBuffer).toString();
    }

    public static HttpURLConnection m14148a(Context context, URL url) {
        if (C2472a.m14151c(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!C2472a.m14150b(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(C2472a.m14146a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    private static URL m14149a(String str) {
        return new URL(str);
    }

    public static boolean m14150b(Context context) {
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

    public static boolean m14151c(Context context) {
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

    public static boolean m14152d(Context context) {
        return C2472a.m14143a(context) >= 0;
    }

    public static boolean m14153e(Context context) {
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

    public static String m14154f(Context context) {
        if (C2472a.m14153e(context)) {
            return "wifi";
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
}
