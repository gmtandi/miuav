package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.fimi.kernel.C1154b;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.AsynLoadImgBack;
import com.tencent.open.utils.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.tencent.connect.share.a */
public class C2222a {

    /* renamed from: com.tencent.connect.share.a.1 */
    final class C22181 extends Handler {
        final /* synthetic */ AsynLoadImgBack f11516a;

        C22181(Looper looper, AsynLoadImgBack asynLoadImgBack) {
            this.f11516a = asynLoadImgBack;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case Opcodes.LSUB /*101*/:
                    this.f11516a.saved(0, (String) message.obj);
                case Opcodes.FSUB /*102*/:
                    this.f11516a.saved(message.arg1, null);
                default:
                    super.handleMessage(message);
            }
        }
    }

    /* renamed from: com.tencent.connect.share.a.2 */
    final class C22192 implements Runnable {
        final /* synthetic */ String f11517a;
        final /* synthetic */ Handler f11518b;

        C22192(String str, Handler handler) {
            this.f11517a = str;
            this.f11518b = handler;
        }

        public void run() {
            Bitmap a = C2222a.m13341a(this.f11517a, (int) Opcodes.F2L);
            if (a != null) {
                String a2;
                String str = Environment.getExternalStorageDirectory() + "/tmp/";
                String str2 = "share2qq_temp" + Util.encrypt(this.f11517a) + com.tencent.mm.sdk.platformtools.Util.PHOTO_DEFAULT_EXT;
                if (C2222a.m13347b(this.f11517a, (int) Opcodes.F2L, (int) Opcodes.F2L)) {
                    C2333f.m13754b("AsynScaleCompressImage", "out of bound,compress!");
                    a2 = C2222a.m13342a(a, str, str2);
                } else {
                    C2333f.m13754b("AsynScaleCompressImage", "not out of bound,not compress!");
                    a2 = this.f11517a;
                }
                C2333f.m13754b("AsynScaleCompressImage", "-->destFilePath: " + a2);
                if (a2 != null) {
                    Message obtainMessage = this.f11518b.obtainMessage(Opcodes.LSUB);
                    obtainMessage.obj = a2;
                    this.f11518b.sendMessage(obtainMessage);
                    return;
                }
            }
            Message obtainMessage2 = this.f11518b.obtainMessage(Opcodes.FSUB);
            obtainMessage2.arg1 = 3;
            this.f11518b.sendMessage(obtainMessage2);
        }
    }

    /* renamed from: com.tencent.connect.share.a.3 */
    final class C22203 extends Handler {
        final /* synthetic */ AsynLoadImgBack f11519a;

        C22203(Looper looper, AsynLoadImgBack asynLoadImgBack) {
            this.f11519a = asynLoadImgBack;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case Opcodes.LSUB /*101*/:
                    this.f11519a.batchSaved(0, message.getData().getStringArrayList(C1154b.f5231b));
                default:
                    super.handleMessage(message);
            }
        }
    }

    /* renamed from: com.tencent.connect.share.a.4 */
    final class C22214 implements Runnable {
        final /* synthetic */ ArrayList f11520a;
        final /* synthetic */ Handler f11521b;

        C22214(ArrayList arrayList, Handler handler) {
            this.f11520a = arrayList;
            this.f11521b = handler;
        }

        public void run() {
            for (int i = 0; i < this.f11520a.size(); i++) {
                Object obj = (String) this.f11520a.get(i);
                if (!Util.isValidUrl(obj) && Util.fileExists(obj)) {
                    Bitmap a = C2222a.m13341a((String) obj, (int) C1873o.ak);
                    if (a != null) {
                        String str = Environment.getExternalStorageDirectory() + "/tmp/";
                        String str2 = "share2qzone_temp" + Util.encrypt(obj) + com.tencent.mm.sdk.platformtools.Util.PHOTO_DEFAULT_EXT;
                        if (C2222a.m13347b((String) obj, 640, (int) C1873o.ak)) {
                            C2333f.m13754b("AsynScaleCompressImage", "out of bound, compress!");
                            obj = C2222a.m13342a(a, str, str2);
                        } else {
                            C2333f.m13754b("AsynScaleCompressImage", "not out of bound,not compress!");
                        }
                        if (obj != null) {
                            this.f11520a.set(i, obj);
                        }
                    }
                }
            }
            Message obtainMessage = this.f11521b.obtainMessage(Opcodes.LSUB);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(C1154b.f5231b, this.f11520a);
            obtainMessage.setData(bundle);
            this.f11521b.sendMessage(obtainMessage);
        }
    }

    public static final int m13339a(Options options, int i, int i2) {
        int b = C2222a.m13346b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    private static Bitmap m13340a(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            width = height;
        }
        float f = ((float) i) / ((float) width);
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static final Bitmap m13341a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        options.inPreferredConfig = Config.RGB_565;
        if (i2 > i) {
            options.inSampleSize = C2222a.m13339a(options, -1, i * i);
        }
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 <= i4) {
            i3 = i4;
        }
        return i3 > i ? C2222a.m13340a(decodeFile, i) : decodeFile;
    }

    protected static final String m13342a(Bitmap bitmap, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String stringBuffer = new StringBuffer(str).append(str2).toString();
        File file2 = new File(stringBuffer);
        if (file2.exists()) {
            file2.delete();
        }
        if (bitmap != null) {
            try {
                OutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                bitmap.recycle();
                return stringBuffer;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static final void m13343a(Context context, String str, AsynLoadImgBack asynLoadImgBack) {
        C2333f.m13754b("AsynScaleCompressImage", "scaleCompressImage");
        if (TextUtils.isEmpty(str)) {
            asynLoadImgBack.saved(1, null);
        } else if (Util.hasSDCard()) {
            new Thread(new C22192(str, new C22181(context.getMainLooper(), asynLoadImgBack))).start();
        } else {
            asynLoadImgBack.saved(2, null);
        }
    }

    public static final void m13344a(Context context, ArrayList<String> arrayList, AsynLoadImgBack asynLoadImgBack) {
        C2333f.m13754b("AsynScaleCompressImage", "batchScaleCompressImage");
        if (arrayList == null) {
            asynLoadImgBack.saved(1, null);
        } else {
            new Thread(new C22214(arrayList, new C22203(context.getMainLooper(), asynLoadImgBack))).start();
        }
    }

    private static int m13346b(Options options, int i, int i2) {
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        int min = i == -1 ? SmileConstants.TOKEN_PREFIX_TINY_UNICODE : (int) Math.min(Math.floor(d / ((double) i)), Math.floor(d2 / ((double) i)));
        return min < ceil ? ceil : (i2 == -1 && i == -1) ? 1 : i != -1 ? min : ceil;
    }

    private static final boolean m13347b(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return false;
        }
        int i5 = i3 > i4 ? i3 : i4;
        if (i3 >= i4) {
            i3 = i4;
        }
        C2333f.m13754b("AsynScaleCompressImage", "longSide=" + i5 + "shortSide=" + i3);
        options.inPreferredConfig = Config.RGB_565;
        return i5 > i2 || i3 > i;
    }
}
