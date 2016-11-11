package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.p133a.C2333f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.p122a.p123a.C2915a;

public class AsynLoadImg {
    private static String f12079c;
    private String f12080a;
    private AsynLoadImgBack f12081b;
    private long f12082d;
    private Handler f12083e;
    private Runnable f12084f;

    /* renamed from: com.tencent.open.utils.AsynLoadImg.1 */
    class C23581 extends Handler {
        final /* synthetic */ AsynLoadImg f12077a;

        C23581(AsynLoadImg asynLoadImg, Looper looper) {
            this.f12077a = asynLoadImg;
            super(looper);
        }

        public void handleMessage(Message message) {
            C2333f.m13751a("AsynLoadImg", "handleMessage:" + message.arg1);
            if (message.arg1 == 0) {
                this.f12077a.f12081b.saved(message.arg1, (String) message.obj);
            } else {
                this.f12077a.f12081b.saved(message.arg1, null);
            }
        }
    }

    /* renamed from: com.tencent.open.utils.AsynLoadImg.2 */
    class C23592 implements Runnable {
        final /* synthetic */ AsynLoadImg f12078a;

        C23592(AsynLoadImg asynLoadImg) {
            this.f12078a = asynLoadImg;
        }

        public void run() {
            C2333f.m13751a("AsynLoadImg", "saveFileRunnable:");
            String str = "share_qq_" + Util.encrypt(this.f12078a.f12080a) + Util.PHOTO_DEFAULT_EXT;
            String str2 = AsynLoadImg.f12079c + str;
            File file = new File(str2);
            Message obtainMessage = this.f12078a.f12083e.obtainMessage();
            if (file.exists()) {
                obtainMessage.arg1 = 0;
                obtainMessage.obj = str2;
                C2333f.m13751a("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - this.f12078a.f12082d));
            } else {
                boolean saveFile;
                Bitmap bitmap = AsynLoadImg.getbitmap(this.f12078a.f12080a);
                if (bitmap != null) {
                    saveFile = this.f12078a.saveFile(bitmap, str);
                } else {
                    C2333f.m13751a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                    saveFile = false;
                }
                if (saveFile) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = str2;
                } else {
                    obtainMessage.arg1 = 1;
                }
                C2333f.m13751a("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - this.f12078a.f12082d));
            }
            this.f12078a.f12083e.sendMessage(obtainMessage);
        }
    }

    public AsynLoadImg(Activity activity) {
        this.f12084f = new C23592(this);
        this.f12083e = new C23581(this, activity.getMainLooper());
    }

    public static Bitmap getbitmap(String str) {
        C2333f.m13751a("AsynLoadImg", "getbitmap:" + str);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            C2333f.m13751a("AsynLoadImg", "image download finished." + str);
            return decodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            C2333f.m13751a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }

    public void save(String str, AsynLoadImgBack asynLoadImgBack) {
        C2333f.m13751a("AsynLoadImg", "--save---");
        if (str == null || str.equals(C2915a.f14760f)) {
            asynLoadImgBack.saved(1, null);
        } else if (Util.hasSDCard()) {
            f12079c = Environment.getExternalStorageDirectory() + "/tmp/";
            this.f12082d = System.currentTimeMillis();
            this.f12080a = str;
            this.f12081b = asynLoadImgBack;
            new Thread(this.f12084f).start();
        } else {
            asynLoadImgBack.saved(2, null);
        }
    }

    public boolean saveFile(Bitmap bitmap, String str) {
        IOException e;
        OutputStream outputStream;
        Throwable th;
        String str2 = f12079c;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            str2 = str2 + str;
            C2333f.m13751a("AsynLoadImg", "saveFile:" + str);
            OutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str2)));
            try {
                bitmap.compress(CompressFormat.JPEG, 80, bufferedOutputStream2);
                bufferedOutputStream2.flush();
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e3) {
                e2 = e3;
                outputStream = bufferedOutputStream2;
                try {
                    e2.printStackTrace();
                    C2333f.m13751a("AsynLoadImg", "saveFile bmp fail---");
                    if (bufferedOutputStream != null) {
                        return false;
                    }
                    try {
                        bufferedOutputStream.close();
                        return false;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        return false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            e2.printStackTrace();
            C2333f.m13751a("AsynLoadImg", "saveFile bmp fail---");
            if (bufferedOutputStream != null) {
                return false;
            }
            bufferedOutputStream.close();
            return false;
        }
    }
}
