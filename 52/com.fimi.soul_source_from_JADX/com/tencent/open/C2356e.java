package com.tencent.open;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import com.tencent.open.p133a.C2333f;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.tencent.open.e */
public class C2356e extends AsyncTask<Bitmap, Void, HashMap<String, Object>> {
    private static final SimpleDateFormat f12069a;
    private C2295a f12070b;

    /* renamed from: com.tencent.open.e.a */
    public interface C2295a {
        void m13595a(String str);

        void m13596b(String str);
    }

    static {
        f12069a = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.CHINA);
    }

    public C2356e(C2295a c2295a) {
        this.f12070b = c2295a;
    }

    private int m13814a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        i3 = Math.round(((float) i4) / ((float) i));
        return round < i3 ? round : i3;
    }

    private Bitmap m13815a(Bitmap bitmap) {
        int i = 1;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray().length / SmileConstants.MAX_SHARED_STRING_VALUES > SmileConstants.MAX_SHARED_STRING_VALUES) {
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, 50, byteArrayOutputStream);
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, null, options);
        options.inJustDecodeBounds = false;
        int a = m13814a(options, 320, 320);
        if (a > 0) {
            i = a;
        }
        C2333f.m13757c("comp", "comp be=" + i);
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, options);
    }

    private String m13816a(long j) {
        return f12069a.format(new Date(j));
    }

    public static void m13817a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
            }
        }
    }

    public static boolean m13818a() {
        return Environment.getExternalStorageState().equals("mounted") || new File("/mnt/sdcard-ext").isDirectory();
    }

    private String m13819b() {
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().getAbsolutePath() : new File("/mnt/sdcard-ext").isDirectory() ? "/mnt/sdcard-ext" : ".";
    }

    private String m13820b(Bitmap bitmap) {
        OutputStream outputStream;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        String str = C2915a.f14760f;
        try {
            str = m13816a(System.currentTimeMillis()) + ".png";
            String str2 = m13819b() + File.separator + ".AppCenterWebBuffer";
            str = str2 + File.separator + str;
            File file = new File(str2);
            if (file.exists() || !file.mkdirs()) {
                file = new File(str);
            } else {
                file = new File(str);
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            OutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream2);
                fileOutputStream2.flush();
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Exception e2) {
                outputStream = fileOutputStream2;
                try {
                    str = C2915a.f14760f;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            str = C2915a.f14760f;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return str;
        }
        return str;
    }

    protected HashMap<String, Object> m13821a(Bitmap... bitmapArr) {
        HashMap<String, Object> hashMap = new HashMap();
        try {
            Bitmap bitmap = bitmapArr[0];
            if (bitmap != null) {
                Object b;
                String str = C2915a.f14760f;
                if (bitmap.getWidth() > 320 || bitmap.getHeight() > 320) {
                    Bitmap a = m13815a(bitmap);
                    b = m13820b(a);
                    a.recycle();
                } else {
                    b = m13820b(bitmap);
                }
                bitmap.recycle();
                hashMap.put("ResultType", Integer.valueOf(1));
                hashMap.put("ResultValue", b);
            }
        } catch (Exception e) {
            hashMap.put("ResultType", Integer.valueOf(0));
            hashMap.put("ResultValue", e.getMessage());
        }
        return hashMap;
    }

    protected void m13822a(HashMap<String, Object> hashMap) {
        if (((Integer) hashMap.get("ResultType")).intValue() == 1) {
            this.f12070b.m13595a((String) hashMap.get("ResultValue"));
        } else {
            this.f12070b.m13596b((String) hashMap.get("ResultValue"));
        }
        super.onPostExecute(hashMap);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m13821a((Bitmap[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m13822a((HashMap) obj);
    }
}
