package it.p074a.p075a.p140a;

import com.fimi.kernel.p076b.p080d.C1142e;
import it.p074a.p075a.C2777i;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/* renamed from: it.a.a.a.f */
public class C2780f extends C2777i {
    private String f14172d;
    private int f14173e;
    private String f14174f;
    private String f14175g;

    public C2780f(String str, int i) {
        this(str, i, null, null);
    }

    public C2780f(String str, int i, String str2, String str3) {
        this.f14172d = str;
        this.f14173e = i;
        this.f14174f = str2;
        this.f14175g = str3;
    }

    private Socket m15847a(String str, int i, boolean z) {
        Socket b;
        InputStream inputStream;
        OutputStream outputStream = null;
        byte[] bytes = "\r\n".getBytes(C1142e.f5201a);
        String stringBuffer = new StringBuffer().append("CONNECT ").append(str).append(":").append(i).append(" HTTP/1.1").toString();
        String stringBuffer2 = new StringBuffer().append("Host: ").append(str).append(":").append(i).toString();
        if (z) {
            try {
                b = m15836b(this.f14172d, this.f14173e);
            } catch (IOException e) {
                e = e;
                inputStream = null;
                b = null;
                try {
                    IOException e2;
                    throw e2;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                Throwable th3;
                th3 = th2;
                inputStream = null;
                b = null;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th5) {
                    }
                }
                if (b != null) {
                    try {
                        b.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th3;
            }
        }
        b = m15832a(this.f14172d, this.f14173e);
        try {
            inputStream = b.getInputStream();
            try {
                outputStream = b.getOutputStream();
                outputStream.write(stringBuffer.getBytes(C1142e.f5201a));
                outputStream.write(bytes);
                outputStream.write(stringBuffer2.getBytes(C1142e.f5201a));
                outputStream.write(bytes);
                if (!(this.f14174f == null || this.f14175g == null)) {
                    outputStream.write(new StringBuffer().append("Proxy-Authorization: Basic ").append(C2774a.m15816a(new StringBuffer().append(this.f14174f).append(":").append(this.f14175g).toString())).toString().getBytes(C1142e.f5201a));
                    outputStream.write(bytes);
                }
                outputStream.write(bytes);
                outputStream.flush();
                ArrayList arrayList = new ArrayList();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine = bufferedReader.readLine();
                while (readLine != null && readLine.length() > 0) {
                    arrayList.add(readLine);
                    readLine = bufferedReader.readLine();
                }
                int size = arrayList.size();
                if (size < 1) {
                    throw new IOException("HTTPTunnelConnector: invalid proxy response");
                }
                readLine = (String) arrayList.get(0);
                if (!readLine.startsWith("HTTP/") || readLine.length() < 12) {
                    throw new IOException("HTTPTunnelConnector: invalid proxy response");
                }
                if ("200".equals(readLine.substring(9, 12))) {
                    return b;
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("HTTPTunnelConnector: connection failed\r\n");
                stringBuffer3.append("Response received from the proxy:\r\n");
                for (int i2 = 0; i2 < size; i2++) {
                    stringBuffer3.append((String) arrayList.get(i2));
                    stringBuffer3.append("\r\n");
                }
                throw new IOException(stringBuffer3.toString());
            } catch (IOException e3) {
                e2 = e3;
                throw e2;
            }
        } catch (IOException e4) {
            e2 = e4;
            inputStream = null;
            throw e2;
        } catch (Throwable th7) {
            th3 = th7;
            inputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (b != null) {
                b.close();
            }
            throw th3;
        }
    }

    public Socket m15848c(String str, int i) {
        return m15847a(str, i, false);
    }

    public Socket m15849d(String str, int i) {
        return m15847a(str, i, true);
    }
}
