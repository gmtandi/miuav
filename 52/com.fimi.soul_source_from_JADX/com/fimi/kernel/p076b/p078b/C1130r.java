package com.fimi.kernel.p076b.p078b;

import android.os.Message;
import com.fimi.kernel.p076b.C1153f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.fimi.kernel.b.b.r */
class C1130r implements Runnable {
    final /* synthetic */ C1153f f5182a;
    final /* synthetic */ String f5183b;
    final /* synthetic */ C1129q f5184c;

    C1130r(C1129q c1129q, C1153f c1153f, String str) {
        this.f5184c = c1129q;
        this.f5182a = c1153f;
        this.f5183b = str;
    }

    public void run() {
        int a;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        Exception exception;
        Throwable th;
        HttpURLConnection httpURLConnection = null;
        do {
            a = this.f5184c.m7830b();
        } while (this.f5184c.f5181b.containsKey(Integer.valueOf(a)));
        this.f5184c.f5181b.put(Integer.valueOf(a), this.f5182a);
        Message message = new Message();
        message.what = a;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f5183b).openConnection();
            try {
                inputStreamReader = new InputStreamReader(httpURLConnection2.getInputStream());
            } catch (Exception e) {
                Exception exception2 = e;
                inputStreamReader2 = null;
                httpURLConnection = httpURLConnection2;
                exception = exception2;
                try {
                    message.arg1 = 0;
                    message.obj = exception.toString();
                    exception.printStackTrace();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStreamReader2 != null) {
                        try {
                            inputStreamReader2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    this.f5184c.m7685a().sendMessage(message);
                } catch (Throwable th2) {
                    th = th2;
                    inputStreamReader = inputStreamReader2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                inputStreamReader = null;
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                message.obj = stringBuffer.toString();
                message.arg1 = 1;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                httpURLConnection = httpURLConnection2;
                exception = e4;
                inputStreamReader2 = inputStreamReader;
                message.arg1 = 0;
                message.obj = exception.toString();
                exception.printStackTrace();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                this.f5184c.m7685a().sendMessage(message);
            } catch (Throwable th32) {
                httpURLConnection = httpURLConnection2;
                th = th32;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            inputStreamReader2 = null;
            message.arg1 = 0;
            message.obj = exception.toString();
            exception.printStackTrace();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            this.f5184c.m7685a().sendMessage(message);
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        this.f5184c.m7685a().sendMessage(message);
    }
}
