package com.fimi.soul.utils;

import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class af {
    public static int m12238a(String str, String str2, String str3, int i, Handler handler) {
        IOException iOException;
        IOException iOException2;
        Throwable th;
        Throwable th2;
        HttpURLConnection httpURLConnection;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection2 = null;
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(str3 + "/" + str2);
        System.out.println("test.apk==" + file2.getName());
        Message obtain;
        try {
            InputStream inputStream2;
            FileOutputStream fileOutputStream2;
            try {
                long contentLength;
                long j;
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    System.out.println("conn.getContentLength()==" + httpURLConnection3.getContentLength());
                    contentLength = (long) httpURLConnection3.getContentLength();
                    inputStream2 = httpURLConnection3.getInputStream();
                    j = 0;
                } catch (IOException e) {
                    iOException = e;
                    httpURLConnection2 = httpURLConnection3;
                    iOException2 = iOException;
                    try {
                        iOException2.printStackTrace();
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (inputStream != null) {
                            return 0;
                        }
                        try {
                            inputStream.close();
                            return 0;
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            return 0;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream2 = inputStream;
                        fileOutputStream2 = fileOutputStream;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    inputStream2 = null;
                    fileOutputStream2 = null;
                    th2 = th4;
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    throw th;
                }
                try {
                    fileOutputStream2 = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[3072];
                        httpURLConnection3.connect();
                        if (httpURLConnection3.getResponseCode() >= 400) {
                            System.out.println("nono");
                            if (httpURLConnection3 != null) {
                                httpURLConnection3.disconnect();
                            }
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException iOException22) {
                                    iOException22.printStackTrace();
                                }
                            }
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException iOException222) {
                                    iOException222.printStackTrace();
                                }
                            }
                            return 0;
                        }
                        long j2 = 0;
                        while (0.0d <= 100.0d && inputStream2 != null) {
                            int read = inputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                            j += (long) read;
                            long j3 = (100 * j) / contentLength;
                            if (System.currentTimeMillis() - j2 > 1000) {
                                j2 = System.currentTimeMillis();
                                Message obtain2 = Message.obtain();
                                obtain2.what = 2;
                                obtain2.obj = Long.valueOf(j3);
                                obtain2.arg1 = i;
                                handler.sendMessage(obtain2);
                            }
                        }
                        httpURLConnection3.disconnect();
                        fileOutputStream2.close();
                        inputStream2.close();
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException iOException2222) {
                                iOException2222.printStackTrace();
                            }
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException iOException22222) {
                                iOException22222.printStackTrace();
                            }
                        }
                        obtain = Message.obtain();
                        obtain.what = 1;
                        obtain.arg1 = i;
                        handler.sendMessage(obtain);
                        return 1;
                    } catch (IOException e22222) {
                        inputStream = inputStream2;
                        fileOutputStream = fileOutputStream2;
                        iOException = e22222;
                        httpURLConnection2 = httpURLConnection3;
                        iOException22222 = iOException;
                    } catch (Throwable th42) {
                        th2 = th42;
                        httpURLConnection2 = httpURLConnection3;
                        th = th2;
                    }
                } catch (IOException e222222) {
                    inputStream = inputStream2;
                    httpURLConnection = httpURLConnection3;
                    iOException22222 = e222222;
                    httpURLConnection2 = httpURLConnection;
                    iOException22222.printStackTrace();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream != null) {
                        return 0;
                    }
                    inputStream.close();
                    return 0;
                } catch (Throwable th422) {
                    fileOutputStream2 = null;
                    httpURLConnection = httpURLConnection3;
                    th = th422;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                iOException22222 = e3;
                iOException22222.printStackTrace();
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    return 0;
                }
                inputStream.close();
                return 0;
            } catch (Throwable th5) {
                th = th5;
                inputStream2 = null;
                fileOutputStream2 = null;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                throw th;
            }
        } catch (MalformedURLException e4) {
            e4.printStackTrace();
            obtain = Message.obtain();
            obtain.what = 0;
            obtain.arg1 = i;
            handler.sendMessage(obtain);
            return 0;
        }
    }

    public static int m12239a(String str, String str2, String str3, Handler handler) {
        return m12238a(str, str2, str3, 0, handler);
    }
}
