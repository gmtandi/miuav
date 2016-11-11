package com.amap.api.mapcore.util;

import android.os.Build.VERSION;
import com.amap.api.services.core.AMapException;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

public class df {
    private static dh f2416a;
    private int f2417b;
    private int f2418c;
    private boolean f2419d;
    private SSLContext f2420e;
    private Proxy f2421f;
    private volatile boolean f2422g;
    private long f2423h;
    private long f2424i;
    private HostnameVerifier f2425j;

    df(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    df(int i, int i2, Proxy proxy, boolean z) {
        this.f2422g = false;
        this.f2423h = -1;
        this.f2424i = 0;
        this.f2425j = new dg(this);
        this.f2417b = i;
        this.f2418c = i2;
        this.f2421f = proxy;
        this.f2419d = z;
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance(C3026h.f15062a);
                instance.init(null, null, null);
                this.f2420e = instance;
            } catch (Throwable e) {
                cb.m3809a(e, "HttpUrlUtil", "HttpUrlUtil");
            } catch (Throwable e2) {
                cb.m3809a(e2, "HttpUtil", "HttpUtil");
            }
        }
    }

    private dl m4010a(HttpURLConnection httpURLConnection) {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        InputStream pushbackInputStream;
        IOException e;
        Throwable th;
        InputStream gZIPInputStream;
        InputStream inputStream2;
        PushbackInputStream pushbackInputStream2 = null;
        try {
            Map headerFields = httpURLConnection.getHeaderFields();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != C2799f.f14282t) {
                throw new bk("\u7f51\u7edc\u5f02\u5e38\u539f\u56e0\uff1a" + httpURLConnection.getResponseMessage() + " \u7f51\u7edc\u5f02\u5e38\u72b6\u6001\u7801\uff1a" + responseCode);
            }
            byte[] bArr;
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                inputStream = httpURLConnection.getInputStream();
                try {
                    pushbackInputStream = new PushbackInputStream(inputStream, 2);
                } catch (IOException e2) {
                    e = e2;
                    pushbackInputStream = null;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    pushbackInputStream = null;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e3) {
                            cb.m3809a(e3, "HttpUrlUtil", "parseResult");
                            e3.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e4) {
                            cb.m3809a(e4, "HttpUrlUtil", "parseResult");
                            e4.printStackTrace();
                        }
                    }
                    if (pushbackInputStream2 != null) {
                        try {
                            pushbackInputStream2.close();
                        } catch (Throwable e5) {
                            cb.m3809a(e5, "HttpUrlUtil", "parseResult");
                            e5.printStackTrace();
                        }
                    }
                    if (pushbackInputStream != null) {
                        try {
                            pushbackInputStream.close();
                        } catch (Throwable e52) {
                            cb.m3809a(e52, "HttpUrlUtil", "parseResult");
                            e52.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable e522) {
                            cb.m3809a(e522, "HttpUrlUtil", "parseResult");
                            e522.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                pushbackInputStream = null;
                inputStream = null;
                throw e;
            } catch (Throwable th4) {
                th = th4;
                pushbackInputStream = null;
                inputStream = null;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                bArr = new byte[2];
                pushbackInputStream.read(bArr);
                pushbackInputStream.unread(bArr);
                gZIPInputStream = (bArr[0] == 31 && bArr[1] == -117) ? new GZIPInputStream(pushbackInputStream) : pushbackInputStream;
            } catch (IOException e7) {
                e = e7;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream2;
                throw e;
            } catch (Throwable th5) {
                th = th5;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                while (true) {
                    int read = gZIPInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                if (f2416a != null) {
                    f2416a.m3824a();
                }
                dl dlVar = new dl();
                dlVar.f2434a = byteArrayOutputStream.toByteArray();
                dlVar.f2435b = headerFields;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e32) {
                        cb.m3809a(e32, "HttpUrlUtil", "parseResult");
                        e32.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e42) {
                        cb.m3809a(e42, "HttpUrlUtil", "parseResult");
                        e42.printStackTrace();
                    }
                }
                if (pushbackInputStream != null) {
                    try {
                        pushbackInputStream.close();
                    } catch (Throwable e8) {
                        cb.m3809a(e8, "HttpUrlUtil", "parseResult");
                        e8.printStackTrace();
                    }
                }
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable e5222) {
                        cb.m3809a(e5222, "HttpUrlUtil", "parseResult");
                        e5222.printStackTrace();
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e52222) {
                        cb.m3809a(e52222, "HttpUrlUtil", "parseResult");
                        e52222.printStackTrace();
                    }
                }
                return dlVar;
            } catch (IOException e9) {
                e = e9;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream2;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                inputStream2 = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream2;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (pushbackInputStream2 != null) {
                    pushbackInputStream2.close();
                }
                if (pushbackInputStream != null) {
                    pushbackInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            pushbackInputStream = null;
            inputStream = null;
            byteArrayOutputStream = null;
            throw e;
        } catch (Throwable th7) {
            th = th7;
            pushbackInputStream = null;
            inputStream = null;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (pushbackInputStream2 != null) {
                pushbackInputStream2.close();
            }
            if (pushbackInputStream != null) {
                pushbackInputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    static String m4011a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = C2915a.f14760f;
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(str2));
        }
        return stringBuilder.toString();
    }

    public static void m4012a(dh dhVar) {
        f2416a = dhVar;
    }

    private void m4013a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", UUID.randomUUID().toString().replaceAll("-", C2915a.f14760f).toLowerCase());
        } catch (Throwable th) {
            cb.m3809a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.f2417b);
        httpURLConnection.setReadTimeout(this.f2418c);
    }

    dl m4014a(String str, Map<String, String> map, Map<String, String> map2) {
        try {
            String a = m4011a((Map) map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a != null) {
                stringBuffer.append("?").append(a);
            }
            HttpURLConnection a2 = m4016a(stringBuffer.toString(), (Map) map, false);
            a2.connect();
            return m4010a(a2);
        } catch (ConnectException e) {
            throw new bk(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e2) {
            throw new bk(AMapException.ERROR_URL);
        } catch (UnknownHostException e3) {
            throw new bk(AMapException.ERROR_UNKNOW_HOST);
        } catch (SocketException e4) {
            throw new bk(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            throw new bk(AMapException.ERROR_SOCKE_TIME_OUT);
        } catch (IOException e6) {
            throw new bk(AMapException.ERROR_IO);
        } catch (Throwable th) {
            th.printStackTrace();
            bk bkVar = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    dl m4015a(String str, Map<String, String> map, byte[] bArr) {
        try {
            HttpURLConnection a = m4016a(str, (Map) map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return m4010a(a);
        } catch (ConnectException e) {
            e.printStackTrace();
            throw new bk(AMapException.ERROR_CONNECTION);
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
            throw new bk(AMapException.ERROR_URL);
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
            throw new bk(AMapException.ERROR_UNKNOW_HOST);
        } catch (SocketException e4) {
            e4.printStackTrace();
            throw new bk(AMapException.ERROR_SOCKET);
        } catch (SocketTimeoutException e5) {
            e5.printStackTrace();
            throw new bk(AMapException.ERROR_SOCKE_TIME_OUT);
        } catch (IOException e6) {
            e6.printStackTrace();
            throw new bk(AMapException.ERROR_IO);
        } catch (Throwable th) {
            cb.m3809a(th, "HttpUrlUtil", "makePostReqeust");
            bk bkVar = new bk(AMapException.ERROR_UNKNOWN);
        }
    }

    HttpURLConnection m4016a(String str, Map<String, String> map, boolean z) {
        HttpURLConnection httpURLConnection;
        bq.m3688a();
        URL url = new URL(str);
        if (this.f2421f != null) {
            URLConnection openConnection = url.openConnection(this.f2421f);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (this.f2419d) {
            httpURLConnection = (HttpsURLConnection) openConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.f2420e.getSocketFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.f2425j);
        } else {
            httpURLConnection = (HttpURLConnection) openConnection;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty(C3004e.f15024j, "close");
        }
        m4013a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod(C2955m.f14864a);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod(C2951i.f14860a);
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    void m4017a() {
        this.f2422g = true;
    }

    void m4018a(long j) {
        this.f2424i = j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m4019a(java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, com.amap.api.mapcore.util.de.C0336a r14) {
        /*
        r10 = this;
        r1 = 0;
        r0 = 1;
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = 0;
        r2 = 0;
        r4 = 0;
        if (r14 != 0) goto L_0x0038;
    L_0x0009:
        if (r1 == 0) goto L_0x000e;
    L_0x000b:
        r2.close();	 Catch:{ IOException -> 0x0014, Throwable -> 0x0020 }
    L_0x000e:
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        r4.disconnect();	 Catch:{ Throwable -> 0x002c }
    L_0x0013:
        return;
    L_0x0014:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r2, r3);
        goto L_0x000e;
    L_0x0020:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r2, r3);
        goto L_0x000e;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x0038:
        r2 = m4011a(r13);	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r4 = new java.lang.StringBuffer;	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r4.<init>();	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r4.append(r11);	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        if (r2 == 0) goto L_0x004f;
    L_0x0046:
        r5 = "?";
        r5 = r4.append(r5);	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r5.append(r2);	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
    L_0x004f:
        r2 = r4.toString();	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r4 = 0;
        r2 = r10.m4016a(r2, r12, r4);	 Catch:{ ConnectException -> 0x0305, MalformedURLException -> 0x0301, UnknownHostException -> 0x02fd, SocketException -> 0x01e2, SocketTimeoutException -> 0x0218, IOException -> 0x024e, Throwable -> 0x0284, all -> 0x02ba }
        r4 = new java.lang.StringBuilder;	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4.<init>();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r5 = "bytes=";
        r4 = r4.append(r5);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r6 = r10.f2424i;	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = r4.append(r6);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = r4.toString();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r5 = "RANGE";
        r2.setRequestProperty(r5, r4);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r2.connect();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r5 = r2.getResponseCode();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r4) goto L_0x010d;
    L_0x0083:
        r4 = r0;
    L_0x0084:
        r6 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r5 == r6) goto L_0x0110;
    L_0x0088:
        r0 = r0 & r4;
        if (r0 == 0) goto L_0x00b4;
    L_0x008b:
        r0 = new com.amap.api.mapcore.util.bk;	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r3 = new java.lang.StringBuilder;	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r3.<init>();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = "\u7f51\u7edc\u5f02\u5e38\u539f\u56e0\uff1a";
        r3 = r3.append(r4);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = r2.getResponseMessage();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r3 = r3.append(r4);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = " \u7f51\u7edc\u5f02\u5e38\u72b6\u6001\u7801\uff1a";
        r3 = r3.append(r4);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r3 = r3.append(r5);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r3 = r3.toString();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r0.<init>(r3);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r14.m3366a(r0);	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
    L_0x00b4:
        r1 = r2.getInputStream();	 Catch:{ ConnectException -> 0x0309, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
    L_0x00bc:
        r3 = java.lang.Thread.interrupted();	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        if (r3 != 0) goto L_0x013d;
    L_0x00c2:
        r3 = r10.f2422g;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        if (r3 != 0) goto L_0x013d;
    L_0x00c6:
        r3 = 0;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = r1.read(r0, r3, r4);	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        if (r3 <= 0) goto L_0x013d;
    L_0x00cf:
        r4 = r10.f2423h;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r6 = -1;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 == 0) goto L_0x00df;
    L_0x00d7:
        r4 = r10.f2424i;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r6 = r10.f2423h;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x013d;
    L_0x00df:
        if (r3 != r8) goto L_0x0113;
    L_0x00e1:
        r4 = r10.f2424i;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r14.m3367a(r0, r4);	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
    L_0x00e6:
        r4 = r10.f2424i;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r6 = (long) r3;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r4 = r4 + r6;
        r10.f2424i = r4;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        goto L_0x00bc;
    L_0x00ed:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00f1:
        r14.m3366a(r0);	 Catch:{ all -> 0x02ed }
        if (r2 == 0) goto L_0x00f9;
    L_0x00f6:
        r2.close();	 Catch:{ IOException -> 0x0196, Throwable -> 0x01a3 }
    L_0x00f9:
        if (r1 == 0) goto L_0x0013;
    L_0x00fb:
        r1.disconnect();	 Catch:{ Throwable -> 0x0100 }
        goto L_0x0013;
    L_0x0100:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x010d:
        r4 = r3;
        goto L_0x0084;
    L_0x0110:
        r0 = r3;
        goto L_0x0088;
    L_0x0113:
        r4 = new byte[r3];	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r5 = 0;
        r6 = 0;
        java.lang.System.arraycopy(r0, r5, r4, r6, r3);	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r6 = r10.f2424i;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        r14.m3367a(r4, r6);	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        goto L_0x00e6;
    L_0x0120:
        r0 = move-exception;
    L_0x0121:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x0129;
    L_0x0126:
        r1.close();	 Catch:{ IOException -> 0x01b0, Throwable -> 0x01bd }
    L_0x0129:
        if (r2 == 0) goto L_0x0013;
    L_0x012b:
        r2.disconnect();	 Catch:{ Throwable -> 0x0130 }
        goto L_0x0013;
    L_0x0130:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x013d:
        r0 = r10.f2422g;	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        if (r0 == 0) goto L_0x015d;
    L_0x0141:
        r14.m3368d();	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
    L_0x0144:
        if (r1 == 0) goto L_0x0149;
    L_0x0146:
        r1.close();	 Catch:{ IOException -> 0x017e, Throwable -> 0x018a }
    L_0x0149:
        if (r2 == 0) goto L_0x0013;
    L_0x014b:
        r2.disconnect();	 Catch:{ Throwable -> 0x0150 }
        goto L_0x0013;
    L_0x0150:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x015d:
        r14.m3369e();	 Catch:{ ConnectException -> 0x00ed, MalformedURLException -> 0x0120, UnknownHostException -> 0x0161, SocketException -> 0x02fa, SocketTimeoutException -> 0x02f7, IOException -> 0x02f4, Throwable -> 0x02f2 }
        goto L_0x0144;
    L_0x0161:
        r0 = move-exception;
    L_0x0162:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x016a;
    L_0x0167:
        r1.close();	 Catch:{ IOException -> 0x01ca, Throwable -> 0x01d6 }
    L_0x016a:
        if (r2 == 0) goto L_0x0013;
    L_0x016c:
        r2.disconnect();	 Catch:{ Throwable -> 0x0171 }
        goto L_0x0013;
    L_0x0171:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x017e:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0149;
    L_0x018a:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0149;
    L_0x0196:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r2, r3);
        goto L_0x00f9;
    L_0x01a3:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r2, r3);
        goto L_0x00f9;
    L_0x01b0:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0129;
    L_0x01bd:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0129;
    L_0x01ca:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x016a;
    L_0x01d6:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x016a;
    L_0x01e2:
        r0 = move-exception;
        r2 = r1;
    L_0x01e4:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x01ec;
    L_0x01e9:
        r1.close();	 Catch:{ IOException -> 0x0200, Throwable -> 0x020c }
    L_0x01ec:
        if (r2 == 0) goto L_0x0013;
    L_0x01ee:
        r2.disconnect();	 Catch:{ Throwable -> 0x01f3 }
        goto L_0x0013;
    L_0x01f3:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x0200:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x01ec;
    L_0x020c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x01ec;
    L_0x0218:
        r0 = move-exception;
        r2 = r1;
    L_0x021a:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x0222;
    L_0x021f:
        r1.close();	 Catch:{ IOException -> 0x0236, Throwable -> 0x0242 }
    L_0x0222:
        if (r2 == 0) goto L_0x0013;
    L_0x0224:
        r2.disconnect();	 Catch:{ Throwable -> 0x0229 }
        goto L_0x0013;
    L_0x0229:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x0236:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0222;
    L_0x0242:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0222;
    L_0x024e:
        r0 = move-exception;
        r2 = r1;
    L_0x0250:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x0258;
    L_0x0255:
        r1.close();	 Catch:{ IOException -> 0x026c, Throwable -> 0x0278 }
    L_0x0258:
        if (r2 == 0) goto L_0x0013;
    L_0x025a:
        r2.disconnect();	 Catch:{ Throwable -> 0x025f }
        goto L_0x0013;
    L_0x025f:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x026c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0258;
    L_0x0278:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x0258;
    L_0x0284:
        r0 = move-exception;
        r2 = r1;
    L_0x0286:
        r14.m3366a(r0);	 Catch:{ all -> 0x02eb }
        if (r1 == 0) goto L_0x028e;
    L_0x028b:
        r1.close();	 Catch:{ IOException -> 0x02a2, Throwable -> 0x02ae }
    L_0x028e:
        if (r2 == 0) goto L_0x0013;
    L_0x0290:
        r2.disconnect();	 Catch:{ Throwable -> 0x0295 }
        goto L_0x0013;
    L_0x0295:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r2 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r2);
        goto L_0x0013;
    L_0x02a2:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x028e;
    L_0x02ae:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r0, r1, r3);
        goto L_0x028e;
    L_0x02ba:
        r0 = move-exception;
        r2 = r1;
    L_0x02bc:
        if (r1 == 0) goto L_0x02c1;
    L_0x02be:
        r1.close();	 Catch:{ IOException -> 0x02c7, Throwable -> 0x02d3 }
    L_0x02c1:
        if (r2 == 0) goto L_0x02c6;
    L_0x02c3:
        r2.disconnect();	 Catch:{ Throwable -> 0x02df }
    L_0x02c6:
        throw r0;
    L_0x02c7:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r1, r3, r4);
        goto L_0x02c1;
    L_0x02d3:
        r1 = move-exception;
        r1.printStackTrace();
        r3 = "HttpUrlUtil";
        r4 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r1, r3, r4);
        goto L_0x02c1;
    L_0x02df:
        r1 = move-exception;
        r1.printStackTrace();
        r2 = "HttpUrlUtil";
        r3 = "makeDownloadGetRequest";
        com.amap.api.mapcore.util.cb.m3809a(r1, r2, r3);
        goto L_0x02c6;
    L_0x02eb:
        r0 = move-exception;
        goto L_0x02bc;
    L_0x02ed:
        r0 = move-exception;
        r9 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x02bc;
    L_0x02f2:
        r0 = move-exception;
        goto L_0x0286;
    L_0x02f4:
        r0 = move-exception;
        goto L_0x0250;
    L_0x02f7:
        r0 = move-exception;
        goto L_0x021a;
    L_0x02fa:
        r0 = move-exception;
        goto L_0x01e4;
    L_0x02fd:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0162;
    L_0x0301:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0121;
    L_0x0305:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00f1;
    L_0x0309:
        r0 = move-exception;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00f1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.df.a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.mapcore.util.de$a):void");
    }

    void m4020b(long j) {
        this.f2423h = j;
    }
}
