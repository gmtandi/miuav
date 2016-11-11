package com.xiaomi.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.SocialConstants;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p156c.C2955m;

public class Network {
    public static final Pattern f13033a;
    public static final Pattern f13034b;
    public static final Pattern f13035c;

    public final class DoneHandlerInputStream extends FilterInputStream {
        private boolean f13030a;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) {
            if (!this.f13030a) {
                int read = super.read(bArr, i, i2);
                if (read != -1) {
                    return read;
                }
            }
            this.f13030a = true;
            return -1;
        }
    }

    public class HttpHeaderInfo {
        public int f13031a;
        public Map<String, String> f13032b;
    }

    public interface PostDownloadHandler {
    }

    static {
        f13033a = Pattern.compile("([^\\s;]+)(.*)");
        f13034b = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
        f13035c = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    }

    public static InputStream m14854a(Context context, URL url, String str, String str2) {
        return m14855a(context, url, str, str2, null, null);
    }

    public static InputStream m14855a(Context context, URL url, String str, String str2, Map<String, String> map, HttpHeaderInfo httpHeaderInfo) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (url == null) {
            throw new IllegalArgumentException(SocialConstants.PARAM_URL);
        } else {
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection b = m14863b(context, url);
                b.setConnectTimeout(C1873o.ak);
                b.setReadTimeout(15000);
                if (!TextUtils.isEmpty(str)) {
                    b.setRequestProperty(C3004e.f15013Y, str);
                }
                if (str2 != null) {
                    b.setRequestProperty("Cookie", str2);
                }
                if (map != null) {
                    for (String str3 : map.keySet()) {
                        b.setRequestProperty(str3, (String) map.get(str3));
                    }
                }
                if (httpHeaderInfo != null) {
                    if (url.getProtocol().equals(UriUtil.HTTP_SCHEME) || url.getProtocol().equals(UriUtil.HTTPS_SCHEME)) {
                        httpHeaderInfo.f13031a = b.getResponseCode();
                        if (httpHeaderInfo.f13032b == null) {
                            httpHeaderInfo.f13032b = new HashMap();
                        }
                        int i = 0;
                        while (true) {
                            Object headerFieldKey = b.getHeaderFieldKey(i);
                            CharSequence headerField = b.getHeaderField(i);
                            if (headerFieldKey == null && headerField == null) {
                                break;
                            }
                            if (!(TextUtils.isEmpty(headerFieldKey) || TextUtils.isEmpty(headerField))) {
                                httpHeaderInfo.f13032b.put(headerFieldKey.toLowerCase(), headerField);
                            }
                            i++;
                        }
                    }
                }
                return new DoneHandlerInputStream(b.getInputStream());
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                IOException iOException = new IOException(th);
            }
        }
    }

    public static String m14856a(Context context, String str, List<NameValuePair> list) {
        return m14857a(context, str, (List) list, null, null, null);
    }

    public static String m14857a(Context context, String str, List<NameValuePair> list, Map<String, String> map, String str2, String str3) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(SocialConstants.PARAM_URL);
        }
        try {
            HttpURLConnection b = m14863b(context, new URL(str));
            b.setConnectTimeout(C1873o.ak);
            b.setReadTimeout(15000);
            b.setRequestMethod(C2955m.f14864a);
            if (!TextUtils.isEmpty(str2)) {
                b.setRequestProperty(C3004e.f15013Y, str2);
            }
            if (str3 != null) {
                b.setRequestProperty("Cookie", str3);
            }
            String a = m14861a((List) list);
            if (a == null) {
                throw new IllegalArgumentException("nameValuePairs");
            }
            b.setDoOutput(true);
            byte[] bytes = a.getBytes();
            b.getOutputStream().write(bytes, 0, bytes.length);
            b.getOutputStream().flush();
            b.getOutputStream().close();
            Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + b.getResponseCode());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(b.getInputStream())));
            StringBuffer stringBuffer = new StringBuffer();
            String property = System.getProperty("line.separator");
            for (a = bufferedReader.readLine(); a != null; a = bufferedReader.readLine()) {
                stringBuffer.append(a);
                stringBuffer.append(property);
            }
            a = stringBuffer.toString();
            bufferedReader.close();
            if (map != null) {
                while (true) {
                    String headerFieldKey = b.getHeaderFieldKey(i);
                    String headerField = b.getHeaderField(i);
                    if (headerFieldKey == null && headerField == null) {
                        break;
                    }
                    map.put(headerFieldKey, headerField);
                    i = (i + 1) + 1;
                }
            }
            return a;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            IOException iOException = new IOException(th);
        }
    }

    public static String m14858a(Context context, URL url) {
        return m14859a(context, url, null, C1142e.f5201a, null);
    }

    public static String m14859a(Context context, URL url, String str, String str2, String str3) {
        InputStream inputStream = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(SmileConstants.MAX_SHARED_STRING_VALUES);
            inputStream = m14854a(context, url, str, str3);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2), SmileConstants.MAX_SHARED_STRING_VALUES);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append("\r\n");
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + e.toString());
                }
            }
            return stringBuilder.toString();
        } catch (IOException e2) {
            throw e2;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + e3.toString());
                }
            }
        }
    }

    public static String m14860a(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url.getProtocol()).append("://").append("10.0.0.172").append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            stringBuilder.append("?").append(url.getQuery());
        }
        return stringBuilder.toString();
    }

    public static String m14861a(List<NameValuePair> list) {
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

    public static boolean m14862a(Context context) {
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

    public static HttpURLConnection m14863b(Context context, URL url) {
        if (m14864b(context)) {
            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!m14862a(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(m14860a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", url.getHost());
        return httpURLConnection;
    }

    public static boolean m14864b(Context context) {
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
