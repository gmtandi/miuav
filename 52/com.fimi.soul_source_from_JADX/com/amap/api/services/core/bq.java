package com.amap.api.services.core;

import android.os.Build.VERSION;
import com.fimi.kernel.p076b.p080d.C1142e;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p124f.p125c.C3026h;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2955m;

public class bq {
    private static br f3122a;
    private static TrustManager f3123g;
    private int f3124b;
    private int f3125c;
    private boolean f3126d;
    private SSLContext f3127e;
    private Proxy f3128f;

    static {
        f3123g = new bw();
    }

    bq(int i, int i2, Proxy proxy, boolean z) {
        this.f3124b = i;
        this.f3125c = i2;
        this.f3128f = proxy;
        this.f3126d = z;
        if (z) {
            try {
                SSLContext instance = SSLContext.getInstance(C3026h.f15062a);
                instance.init(null, new TrustManager[]{f3123g}, null);
                this.f3127e = instance;
            } catch (Throwable e) {
                ay.m4590a(e, "HttpUrlUtil", "HttpUrlUtil");
                e.printStackTrace();
            } catch (Throwable e2) {
                ay.m4590a(e2, "HttpUrlUtil", "HttpUrlUtil");
                e2.printStackTrace();
            } catch (Throwable e22) {
                ay.m4590a(e22, "HttpUtil", "HttpUtil");
                e22.printStackTrace();
            }
        }
    }

    private bv m4728a(HttpURLConnection httpURLConnection) {
        InputStream pushbackInputStream;
        IOException e;
        Throwable th;
        InputStream inputStream;
        PushbackInputStream pushbackInputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream2;
        try {
            Map headerFields = httpURLConnection.getHeaderFields();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != C2799f.f14282t) {
                throw new C0495v("\u7f51\u7edc\u5f02\u5e38\u539f\u56e0\uff1a" + httpURLConnection.getResponseMessage() + " \u7f51\u7edc\u5f02\u5e38\u72b6\u6001\u7801\uff1a" + responseCode);
            }
            byte[] bArr;
            InputStream gZIPInputStream;
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                inputStream2 = httpURLConnection.getInputStream();
                try {
                    pushbackInputStream = new PushbackInputStream(inputStream2, 2);
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
                            ay.m4590a(e3, "HttpUrlUtil", "parseResult");
                            e3.printStackTrace();
                        }
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable e4) {
                            ay.m4590a(e4, "HttpUrlUtil", "parseResult");
                            e4.printStackTrace();
                        }
                    }
                    if (pushbackInputStream2 != null) {
                        try {
                            pushbackInputStream2.close();
                        } catch (Throwable e5) {
                            ay.m4590a(e5, "HttpUrlUtil", "parseResult");
                            e5.printStackTrace();
                        }
                    }
                    if (pushbackInputStream != null) {
                        try {
                            pushbackInputStream.close();
                        } catch (Throwable e52) {
                            ay.m4590a(e52, "HttpUrlUtil", "parseResult");
                            e52.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable e522) {
                            ay.m4590a(e522, "HttpUrlUtil", "parseResult");
                            e522.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                pushbackInputStream = null;
                inputStream2 = null;
                throw e;
            } catch (Throwable th4) {
                th = th4;
                pushbackInputStream = null;
                inputStream2 = null;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
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
                inputStream = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream;
                throw e;
            } catch (Throwable th5) {
                th = th5;
                inputStream = pushbackInputStream;
                pushbackInputStream = null;
                gZIPInputStream = inputStream;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
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
                if (f3122a != null) {
                    f3122a.m4583a();
                }
                bv bvVar = new bv();
                bvVar.f3135a = byteArrayOutputStream.toByteArray();
                bvVar.f3136b = headerFields;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e32) {
                        ay.m4590a(e32, "HttpUrlUtil", "parseResult");
                        e32.printStackTrace();
                    }
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Throwable e42) {
                        ay.m4590a(e42, "HttpUrlUtil", "parseResult");
                        e42.printStackTrace();
                    }
                }
                if (pushbackInputStream != null) {
                    try {
                        pushbackInputStream.close();
                    } catch (Throwable e8) {
                        ay.m4590a(e8, "HttpUrlUtil", "parseResult");
                        e8.printStackTrace();
                    }
                }
                if (gZIPInputStream != null) {
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable e5222) {
                        ay.m4590a(e5222, "HttpUrlUtil", "parseResult");
                        e5222.printStackTrace();
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable e52222) {
                        ay.m4590a(e52222, "HttpUrlUtil", "parseResult");
                        e52222.printStackTrace();
                    }
                }
                return bvVar;
            } catch (IOException e9) {
                e = e9;
                inputStream = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                inputStream = pushbackInputStream;
                pushbackInputStream = gZIPInputStream;
                gZIPInputStream = inputStream;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
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
            inputStream2 = null;
            byteArrayOutputStream = null;
            throw e;
        } catch (Throwable th7) {
            th = th7;
            pushbackInputStream = null;
            inputStream2 = null;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
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

    private String m4729a(Map<String, String> map) {
        List linkedList = new LinkedList();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        return linkedList.size() > 0 ? URLEncodedUtils.format(linkedList, C1142e.f5201a) : null;
    }

    private HttpURLConnection m4730a(URL url) {
        HttpURLConnection httpURLConnection;
        if (this.f3128f != null) {
            URLConnection openConnection = url.openConnection(this.f3128f);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (this.f3126d) {
            httpURLConnection = (HttpsURLConnection) openConnection;
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.f3127e.getSocketFactory());
        } else {
            httpURLConnection = (HttpURLConnection) openConnection;
        }
        if (VERSION.SDK != null && VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty(C3004e.f15024j, "close");
        }
        return httpURLConnection;
    }

    public static void m4731a(br brVar) {
        f3122a = brVar;
    }

    private void m4732a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, (String) map.get(str));
            }
        }
        httpURLConnection.setConnectTimeout(this.f3124b);
        httpURLConnection.setReadTimeout(this.f3125c);
    }

    bv m4733a(String str, Map<String, String> map, Map<String, String> map2) {
        try {
            String a = m4729a((Map) map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a != null) {
                stringBuffer.append("?").append(a);
            }
            HttpURLConnection a2 = m4730a(new URL(stringBuffer.toString()));
            m4732a(map, a2);
            a2.setRequestMethod(C2951i.f14860a);
            a2.setDoInput(true);
            a2.connect();
            return m4728a(a2);
        } catch (Throwable e) {
            ay.m4590a(e, "HttpUrlUtil", "getRequest");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            ay.m4590a(e2, "HttpUrlUtil", "getRequest");
            e2.printStackTrace();
            return null;
        } catch (Throwable e22) {
            ay.m4590a(e22, "HttpUrlUtil", "getRequest");
            e22.printStackTrace();
            return null;
        }
    }

    bv m4734a(String str, Map<String, String> map, Map<String, String> map2, HttpEntity httpEntity) {
        Throwable e;
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        Exception exception;
        Throwable th;
        bv bvVar = null;
        if (map2 != null) {
            try {
                String a = m4729a((Map) map2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                if (a != null) {
                    stringBuffer.append("?").append(a);
                }
                str = stringBuffer.toString();
            } catch (IllegalStateException e2) {
                e = e2;
                Object obj = bvVar;
                Object obj2 = bvVar;
                try {
                    ay.m4590a(e, "HttpUrlUtil", "postRequest2");
                    e.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e3) {
                            ay.m4590a(e3, "HttpUrlUtil", "postRequest2");
                            e3.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                            e3 = e4;
                            ay.m4590a(e3, "HttpUrlUtil", "postRequest2");
                            exception.printStackTrace();
                            return bvVar;
                        }
                    }
                    return bvVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e32) {
                            ay.m4590a(e32, "HttpUrlUtil", "postRequest2");
                            e32.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e322) {
                            ay.m4590a(e322, "HttpUrlUtil", "postRequest2");
                            e322.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e322 = e5;
                inputStream = bvVar;
                byteArrayOutputStream = bvVar;
                ay.m4590a(e322, "HttpUrlUtil", "postRequest2");
                e322.printStackTrace();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e3222) {
                        ay.m4590a(e3222, "HttpUrlUtil", "postRequest2");
                        e3222.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e6) {
                        e3222 = e6;
                        ay.m4590a(e3222, "HttpUrlUtil", "postRequest2");
                        exception.printStackTrace();
                        return bvVar;
                    }
                }
                return bvVar;
            } catch (Throwable e32222) {
                inputStream = bvVar;
                byteArrayOutputStream = bvVar;
                th = e32222;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream = httpEntity.getContent();
            try {
                byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                bvVar = m4736a(str, (Map) map, byteArrayOutputStream.toByteArray());
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e322222) {
                        ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
                        e322222.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                        exception = e7;
                        ay.m4590a((Throwable) exception, "HttpUrlUtil", "postRequest2");
                        exception.printStackTrace();
                        return bvVar;
                    }
                }
            } catch (IllegalStateException e8) {
                e322222 = e8;
                ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
                e322222.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return bvVar;
            } catch (IOException e9) {
                e322222 = e9;
                ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
                e322222.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return bvVar;
            } catch (Throwable th3) {
                e322222 = th3;
                ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
                e322222.printStackTrace();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return bvVar;
            }
        } catch (IllegalStateException e10) {
            e322222 = e10;
            obj = bvVar;
            ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
            e322222.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return bvVar;
        } catch (IOException e11) {
            e322222 = e11;
            obj = bvVar;
            ay.m4590a(e322222, "HttpUrlUtil", "postRequest2");
            e322222.printStackTrace();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return bvVar;
        } catch (Throwable e3222222) {
            obj = bvVar;
            th = e3222222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        return bvVar;
    }

    bv m4735a(String str, Map<String, String> map, Map<String, String> map2, byte[] bArr) {
        if (map2 != null) {
            try {
                String a = m4729a((Map) map2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                if (a != null) {
                    stringBuffer.append("?").append(a);
                }
                str = stringBuffer.toString();
            } catch (Throwable th) {
                ay.m4590a(th, "HttpUrlUtil", "PostReqeust3");
                th.printStackTrace();
                return null;
            }
        }
        return m4736a(str, (Map) map, bArr);
    }

    bv m4736a(String str, Map<String, String> map, byte[] bArr) {
        try {
            HttpURLConnection a = m4730a(new URL(str));
            m4732a(map, a);
            a.setRequestMethod(C2955m.f14864a);
            a.setUseCaches(false);
            a.setDoInput(true);
            a.setDoOutput(true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return m4728a(a);
        } catch (Throwable e) {
            ay.m4590a(e, "HttpUrlUtil", "postRequest");
            e.printStackTrace();
            return null;
        } catch (Throwable e2) {
            ay.m4590a(e2, "HttpUrlUtil", "postRequest");
            e2.printStackTrace();
            return null;
        } catch (Throwable e22) {
            ay.m4590a(e22, "HttpUrlUtil", "postRequest");
            e22.printStackTrace();
            return null;
        }
    }

    bv m4737b(String str, Map<String, String> map, Map<String, String> map2) {
        String a = m4729a((Map) map2);
        if (a == null) {
            return m4736a(str, (Map) map, new byte[0]);
        }
        try {
            return m4736a(str, (Map) map, a.getBytes(C1142e.f5201a));
        } catch (Throwable e) {
            ay.m4590a(e, "HttpUrlUtil", "postRequest1");
            e.printStackTrace();
            return m4736a(str, (Map) map, a.getBytes());
        }
    }
}
