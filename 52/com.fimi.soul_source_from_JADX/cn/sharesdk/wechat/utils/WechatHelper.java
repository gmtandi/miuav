package cn.sharesdk.wechat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.C0205d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.DeviceHelper;
import com.p054c.p055a.p072c.C0957d;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class WechatHelper {
    private static boolean f432a;
    private static WechatHelper f433b;
    private C0235j f434c;
    private C0236k f435d;

    public class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String extInfo;
        @Deprecated
        public String filePath;
        @Deprecated
        public Bitmap imageData;
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String musicUrl;
        @Deprecated
        protected int scene;
        @Deprecated
        public int shareType;
        @Deprecated
        public String title;
        @Deprecated
        public String url;
    }

    static {
        f432a = false;
    }

    private WechatHelper() {
        this.f434c = new C0235j();
    }

    private int m855a(Bitmap bitmap, CompressFormat compressFormat) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        int size = byteArrayOutputStream.size();
        byteArrayOutputStream.close();
        return size;
    }

    private static int m856a(Options options, int i, int i2) {
        int i3 = 1;
        int i4 = options.outWidth / 2;
        int i5 = options.outHeight / 2;
        while (true) {
            if (i4 / i3 <= i && i5 / i3 <= i2) {
                return i3 + 2;
            }
            i3 *= 2;
        }
    }

    private Bitmap m857a(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d = (height > i || width > i) ? height > width ? ((double) i) / ((double) height) : ((double) i) / ((double) width) : WeightedLatLng.DEFAULT_INTENSITY;
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) * d), (int) (d * ((double) height)), true);
    }

    public static WechatHelper m858a() {
        if (f433b == null) {
            f433b = new WechatHelper();
        }
        return f433b;
    }

    private void m859a(Context context, String str, String str2, Bitmap bitmap, int i, C0236k c0236k) {
        Object wXImageObject = new WXImageObject();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXImageObject.imageData = byteArrayOutputStream.toByteArray();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m868a(context, wXImageObject.imageData);
        m865a(wXMediaMessage, SocialConstants.PARAM_IMG_URL, i, c0236k);
    }

    private void m860a(Context context, String str, String str2, String str3, int i, C0236k c0236k) {
        Object wXImageObject = new WXImageObject();
        if (str3.startsWith("/data/")) {
            wXImageObject.imageData = m869a(str3);
        } else {
            wXImageObject.imagePath = str3;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m876b(context, str3);
        m865a(wXMediaMessage, SocialConstants.PARAM_IMG_URL, i, c0236k);
    }

    private void m861a(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0236k c0236k) {
        IMediaObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXMediaMessage.thumbData = m868a(context, byteArrayOutputStream.toByteArray());
        m865a(wXMediaMessage, FimiMediaMeta.IJKM_VAL_TYPE__VIDEO, i, c0236k);
    }

    private void m862a(Context context, String str, String str2, String str3, String str4, int i, C0236k c0236k) {
        IMediaObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = m876b(context, str4);
        m865a(wXMediaMessage, FimiMediaMeta.IJKM_VAL_TYPE__VIDEO, i, c0236k);
    }

    private void m863a(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, C0236k c0236k) {
        IMediaObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXMediaMessage.thumbData = m868a(context, byteArrayOutputStream.toByteArray());
        m865a(wXMediaMessage, "music", i, c0236k);
    }

    private void m864a(Context context, String str, String str2, String str3, String str4, String str5, int i, C0236k c0236k) {
        IMediaObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = m876b(context, str5);
        m865a(wXMediaMessage, "music", i, c0236k);
    }

    private void m865a(WXMediaMessage wXMediaMessage, String str, int i, C0236k c0236k) {
        Class cls;
        String str2 = DeviceHelper.getInstance(c0236k.m935b().getContext()).getPackageName() + ".wxapi.WXEntryActivity";
        try {
            cls = Class.forName(str2);
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            cls = null;
        }
        if (!(cls == null || WechatHandlerActivity.class.isAssignableFrom(cls))) {
            String str3 = str2 + " does not extend from " + WechatHandlerActivity.class.getName();
            if (f432a) {
                throw new Throwable(str3);
            }
            new Throwable(str3).printStackTrace();
        }
        C0225m c0229d = new C0229d();
        c0229d.c = str + System.currentTimeMillis();
        c0229d.f448a = wXMediaMessage;
        c0229d.f449b = i;
        this.f435d = c0236k;
        this.f434c.m924a(c0229d);
    }

    private void m866a(String str, String str2, int i, C0236k c0236k) {
        IMediaObject wXTextObject = new WXTextObject();
        wXTextObject.text = str2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str2;
        m865a(wXMediaMessage, "text", i, c0236k);
    }

    private byte[] m867a(Context context, Bitmap bitmap, CompressFormat compressFormat) {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            int i = Opcodes.ISHL;
            while (i > 40 && m855a(bitmap, compressFormat) > C0957d.f5043a) {
                int dipToPx = C2178R.dipToPx(context, i);
                i -= 5;
                bitmap = m857a(bitmap, dipToPx);
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
    }

    private byte[] m868a(Context context, byte[] bArr) {
        CompressFormat bmpFormat = BitmapHelper.getBmpFormat(bArr);
        int dipToPx = C2178R.dipToPx(context, Opcodes.ISHL);
        if (CompressFormat.PNG == bmpFormat) {
            dipToPx = C2178R.dipToPx(context, 90);
        }
        Options options = new Options();
        if (bArr.length > C0957d.f5043a) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            options.inSampleSize = m856a(options, dipToPx, dipToPx);
        }
        options.inJustDecodeBounds = false;
        return m867a(context, BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options), bmpFormat);
    }

    private byte[] m869a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            fileInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return null;
        }
    }

    private void m870b(Context context, String str, String str2, Bitmap bitmap, int i, C0236k c0236k) {
        Object wXEmojiObject = new WXEmojiObject();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        wXEmojiObject.emojiData = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m868a(context, wXEmojiObject.emojiData);
        m865a(wXMediaMessage, "emoji", i, c0236k);
    }

    private void m871b(Context context, String str, String str2, String str3, int i, C0236k c0236k) {
        IMediaObject wXEmojiObject = new WXEmojiObject();
        wXEmojiObject.emojiPath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m876b(context, str3);
        m865a(wXMediaMessage, "emoji", i, c0236k);
    }

    private void m872b(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0236k c0236k) {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (!(bitmap == null || bitmap.isRecycled())) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            wXMediaMessage.thumbData = m868a(context, byteArrayOutputStream.toByteArray());
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > C0957d.f5043a) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + C0957d.f5043a);
            }
        }
        m865a(wXMediaMessage, "webpage", i, c0236k);
    }

    private void m873b(Context context, String str, String str2, String str3, String str4, int i, C0236k c0236k) {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (str4 != null && new File(str4).exists()) {
            wXMediaMessage.thumbData = m876b(context, str4);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > C0957d.f5043a) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + C0957d.f5043a);
            }
        }
        m865a(wXMediaMessage, "webpage", i, c0236k);
    }

    private void m874b(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, C0236k c0236k) {
        IMediaObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXMediaMessage.thumbData = m868a(context, byteArrayOutputStream.toByteArray());
        m865a(wXMediaMessage, "appdata", i, c0236k);
    }

    private void m875b(Context context, String str, String str2, String str3, String str4, String str5, int i, C0236k c0236k) {
        IMediaObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = m876b(context, str5);
        m865a(wXMediaMessage, "appdata", i, c0236k);
    }

    private byte[] m876b(Context context, String str) {
        File file = new File(str);
        if (file.exists()) {
            CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
            int dipToPx = C2178R.dipToPx(context, Opcodes.ISHL);
            if (CompressFormat.PNG == bmpFormat) {
                dipToPx = C2178R.dipToPx(context, 90);
            }
            Options options = new Options();
            if (file.length() > FimiMediaMeta.AV_CH_TOP_BACK_LEFT) {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                options.inSampleSize = m856a(options, dipToPx, dipToPx);
            }
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                return m867a(context, decodeFile, bmpFormat);
            }
            file.delete();
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        throw new FileNotFoundException();
    }

    private void m877c(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0236k c0236k) {
        IMediaObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXMediaMessage.thumbData = m868a(context, byteArrayOutputStream.toByteArray());
        m865a(wXMediaMessage, "filedata", i, c0236k);
    }

    private void m878c(Context context, String str, String str2, String str3, String str4, int i, C0236k c0236k) {
        IMediaObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = m876b(context, str4);
        m865a(wXMediaMessage, "filedata", i, c0236k);
    }

    public void m879a(C0236k c0236k) {
        this.f435d = c0236k;
        C0225m c0226a = new C0226a();
        c0226a.f440a = "snsapi_userinfo";
        c0226a.f441b = "sharesdk_wechat_auth";
        this.f434c.m924a(c0226a);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m880a(cn.sharesdk.wechat.utils.C0236k r12, cn.sharesdk.framework.Platform.ShareParams r13, cn.sharesdk.framework.PlatformActionListener r14) {
        /*
        r11 = this;
        r10 = 0;
        r5 = r12.m935b();
        r0 = r13.getImagePath();
        r1 = r13.getImageUrl();
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x001e;
    L_0x0013:
        r2 = new java.io.File;
        r2.<init>(r0);
        r2 = r2.exists();
        if (r2 != 0) goto L_0x0066;
    L_0x001e:
        r2 = r13.getImageData();
        if (r2 == 0) goto L_0x013d;
    L_0x0024:
        r3 = r2.isRecycled();
        if (r3 != 0) goto L_0x013d;
    L_0x002a:
        r0 = r5.getContext();
        r1 = "images";
        r0 = com.mob.tools.utils.C2178R.getCachePath(r0, r1);
        r1 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = java.lang.System.currentTimeMillis();
        r3 = r3.append(r6);
        r4 = ".png";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1.<init>(r0, r3);
        r0 = new java.io.FileOutputStream;
        r0.<init>(r1);
        r3 = android.graphics.Bitmap.CompressFormat.PNG;
        r4 = 100;
        r2.compress(r3, r4, r0);
        r0.flush();
        r0.close();
        r0 = r1.getAbsolutePath();
    L_0x0066:
        r3 = new android.content.Intent;
        r1 = "android.intent.action.SEND";
        r3.<init>(r1);
        r1 = r13.getText();
        r1 = r5.getShortLintk(r1, r10);
        r2 = "android.intent.extra.TEXT";
        r3.putExtra(r2, r1);
        r2 = "Kdescription";
        r3.putExtra(r2, r1);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x014d;
    L_0x0085:
        r2 = new java.io.File;
        r2.<init>(r0);
        r1 = r2.exists();
        if (r1 == 0) goto L_0x00eb;
    L_0x0090:
        r1 = "/data/";
        r1 = r0.startsWith(r1);
        if (r1 == 0) goto L_0x0163;
    L_0x0098:
        r1 = r5.getContext();
        r4 = "images";
        r4 = com.mob.tools.utils.C2178R.getCachePath(r1, r4);
        r1 = new java.io.File;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r8 = java.lang.System.currentTimeMillis();
        r6 = r6.append(r8);
        r7 = r2.getName();
        r6 = r6.append(r7);
        r6 = r6.toString();
        r1.<init>(r4, r6);
        r4 = r1.getAbsolutePath();
        r1.createNewFile();
        r4 = com.mob.tools.utils.C2178R.copyFile(r0, r4);
        if (r4 == 0) goto L_0x0163;
    L_0x00cd:
        r2 = "android.intent.extra.STREAM";
        r1 = android.net.Uri.fromFile(r1);
        r3.putExtra(r2, r1);
        r1 = java.net.URLConnection.getFileNameMap();
        r0 = r1.getContentTypeFor(r0);
        if (r0 == 0) goto L_0x00e6;
    L_0x00e0:
        r1 = r0.length();
        if (r1 > 0) goto L_0x00e8;
    L_0x00e6:
        r0 = "image/*";
    L_0x00e8:
        r3.setType(r0);
    L_0x00eb:
        r0 = "scene";
        r1 = java.lang.Integer.class;
        r0 = r13.get(r0, r1);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = 1;
        if (r0 != r1) goto L_0x0153;
    L_0x00fc:
        r0 = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
    L_0x00fe:
        r1 = "com.tencent.mm";
        r3.setClassName(r1, r0);
        r0 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r3.addFlags(r0);
        r0 = r5.getContext();
        r0.startActivity(r3);
        r0 = r5.getContext();
        r2 = com.mob.tools.utils.DeviceHelper.getInstance(r0);
        r0 = r5.getContext();
        r3 = r0.getPackageName();
        r6 = new java.util.HashMap;
        r6.<init>();
        r0 = "ShareParams";
        r6.put(r0, r13);
        r0 = r2.getTopTaskPackageName();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0156;
    L_0x0133:
        if (r14 == 0) goto L_0x013c;
    L_0x0135:
        if (r14 == 0) goto L_0x013c;
    L_0x0137:
        r0 = 9;
        r14.onComplete(r5, r0, r6);
    L_0x013c:
        return;
    L_0x013d:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0066;
    L_0x0143:
        r0 = r5.getContext();
        r0 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r0, r1);
        goto L_0x0066;
    L_0x014d:
        r0 = "text/plain";
        r3.setType(r0);
        goto L_0x00eb;
    L_0x0153:
        r0 = "com.tencent.mm.ui.tools.ShareImgUI";
        goto L_0x00fe;
    L_0x0156:
        r8 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r0 = new cn.sharesdk.wechat.utils.l;
        r1 = r11;
        r4 = r14;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        com.mob.tools.utils.UIHandler.sendEmptyMessageDelayed(r10, r8, r0);
        goto L_0x013c;
    L_0x0163:
        r1 = r2;
        goto L_0x00cd;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.wechat.utils.WechatHelper.a(cn.sharesdk.wechat.utils.k, cn.sharesdk.framework.Platform$ShareParams, cn.sharesdk.framework.PlatformActionListener):void");
    }

    public boolean m881a(Context context, String str) {
        return this.f434c.m926a(context, str);
    }

    public boolean m882a(WechatHandlerActivity wechatHandlerActivity) {
        return this.f434c.m927a(wechatHandlerActivity, this.f435d);
    }

    public void m883b(C0236k c0236k) {
        Platform b = c0236k.m935b();
        cn.sharesdk.framework.Platform.ShareParams a = c0236k.m930a();
        PlatformActionListener c = c0236k.m936c();
        int shareType = a.getShareType();
        String title = a.getTitle();
        String text = a.getText();
        int scence = a.getScence();
        String imagePath = a.getImagePath();
        String imageUrl = a.getImageUrl();
        Bitmap imageData = a.getImageData();
        String musicUrl = a.getMusicUrl();
        String url = a.getUrl();
        String filePath = a.getFilePath();
        String extInfo = a.getExtInfo();
        Context context = b.getContext();
        switch (shareType) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m866a(title, text, scence, c0236k);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (imagePath != null && imagePath.length() > 0) {
                    m860a(context, title, text, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m859a(context, title, text, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m860a(context, title, text, C2915a.f14760f, scence, c0236k);
                } else {
                    m860a(context, title, text, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                filePath = b.getShortLintk(url, false);
                if (imagePath != null && imagePath.length() > 0) {
                    m873b(context, title, text, filePath, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m872b(context, title, text, filePath, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m873b(context, title, text, filePath, C2915a.f14760f, scence, c0236k);
                } else {
                    m873b(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case Type.INT /*5*/:
                String shortLintk = b.getShortLintk(musicUrl + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + url, false);
                filePath = shortLintk.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0];
                extInfo = shortLintk.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[1];
                if (imagePath != null && imagePath.length() > 0) {
                    m864a(context, title, text, filePath, extInfo, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m863a(context, title, text, filePath, extInfo, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m864a(context, title, text, filePath, extInfo, C2915a.f14760f, scence, c0236k);
                } else {
                    m864a(context, title, text, filePath, extInfo, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case Type.FLOAT /*6*/:
                filePath = b.getShortLintk(url, false);
                if (imagePath != null && imagePath.length() > 0) {
                    m862a(context, title, text, filePath, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m861a(context, title, text, filePath, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m862a(context, title, text, filePath, C2915a.f14760f, scence, c0236k);
                } else {
                    m862a(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case Type.LONG /*7*/:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_APP");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_APP");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m875b(context, title, text, filePath, extInfo, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m874b(context, title, text, filePath, extInfo, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m875b(context, title, text, filePath, extInfo, C2915a.f14760f, scence, c0236k);
                } else {
                    m875b(context, title, text, filePath, extInfo, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case Type.DOUBLE /*8*/:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_FILE");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m878c(context, title, text, filePath, imagePath, scence, c0236k);
                } else if (imageData != null && !imageData.isRecycled()) {
                    m877c(context, title, text, filePath, imageData, scence, c0236k);
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m878c(context, title, text, filePath, C2915a.f14760f, scence, c0236k);
                } else {
                    m878c(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                }
            case Type.ARRAY /*9*/:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_EMOJI");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SHARE_EMOJI");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m871b(context, title, text, imagePath, scence, c0236k);
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m871b(context, title, text, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0236k);
                } else if (imageData == null || imageData.isRecycled()) {
                    m871b(context, title, text, C2915a.f14760f, scence, c0236k);
                } else {
                    m870b(context, title, text, imageData, scence, c0236k);
                }
            default:
                if (c != null) {
                    c.onError(b, 9, new IllegalArgumentException("shareType = " + shareType));
                }
        }
    }

    public boolean m884b() {
        return this.f434c.m925a();
    }

    public boolean m885c() {
        return this.f434c.m928b();
    }

    public boolean m886d() {
        return this.f434c.m929c();
    }
}
