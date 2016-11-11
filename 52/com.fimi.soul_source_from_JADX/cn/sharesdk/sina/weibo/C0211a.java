package cn.sharesdk.sina.weibo;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.C0205d;
import com.amap.api.maps.model.WeightedLatLng;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.UIHandler;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: cn.sharesdk.sina.weibo.a */
public class C0211a extends FakeActivity implements Callback {
    private long f390a;
    private boolean f391b;
    private String f392c;
    private ShareParams f393d;
    private AuthorizeListener f394e;

    public C0211a() {
        this.f390a = 2097152;
    }

    private int m769a(Bitmap bitmap, CompressFormat compressFormat) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        int size = byteArrayOutputStream.size();
        byteArrayOutputStream.close();
        return size;
    }

    private static int m770a(Options options, int i, int i2) {
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

    private Bitmap m771a(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d = (height > i || width > i) ? height > width ? ((double) i) / ((double) height) : ((double) i) / ((double) width) : WeightedLatLng.DEFAULT_INTENSITY;
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) * d), (int) (d * ((double) height)), true);
    }

    private String m772a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] toByteArray2 = toByteArray.toByteArray();
                if (toByteArray2 != null) {
                    return Data.MD5(toByteArray2);
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private void m773a() {
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_command_type", 1);
        bundle.putString("_weibo_transaction", String.valueOf(System.currentTimeMillis()));
        if (!TextUtils.isEmpty(this.f393d.getText())) {
            bundle.putParcelable("_weibo_message_text", m783e());
            bundle.putString("_weibo_message_text_extra", C2915a.f14760f);
        }
        if (!TextUtils.isEmpty(this.f393d.getUrl())) {
            this.f390a = FimiMediaMeta.AV_CH_TOP_BACK_LEFT;
            WebpageObject f = m784f();
            if (f.checkArgs()) {
                bundle.putParcelable("_weibo_message_media", f);
                String str = C2915a.f14760f;
                if (!TextUtils.isEmpty(f.defaultText)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("extra_key_defaulttext", f.defaultText);
                    str = new Hashon().fromHashMap(hashMap);
                }
                bundle.putString("_weibo_message_media_extra", str);
            }
        } else if (!(this.f393d.getImageData() == null && TextUtils.isEmpty(this.f393d.getImagePath()))) {
            this.f390a = 2097152;
            Parcelable g = m785g();
            if (g.checkArgs()) {
                bundle.putParcelable("_weibo_message_image", g);
                bundle.putString("_weibo_message_image_extra", C2915a.f14760f);
            }
        }
        m776a(this.activity, C0221j.m834a(this.activity).m841a(), this.f392c, bundle);
    }

    private void m774a(int i, String str) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                if (this.f394e != null) {
                    this.f394e.onComplete(null);
                    break;
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (this.f394e != null) {
                    this.f394e.onCancel();
                    break;
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                if (this.f394e != null) {
                    this.f394e.onError(new Throwable(str));
                    break;
                }
                break;
        }
        finish();
    }

    private boolean m776a(Activity activity, String str, String str2, Bundle bundle) {
        Object obj = "com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY";
        if (activity == null || TextUtils.isEmpty(obj) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C0205d.m752a().m740e("launchWeiboActivity fail, invalid arguments", new Object[0]);
            return false;
        }
        String packageName = activity.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction(obj);
        intent.putExtra("_weibo_sdkVersion", "0031205000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", str2);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", m772a((Context) activity, packageName));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            C0205d.m752a().m737d("launchWeiboActivity intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
            activity.startActivityForResult(intent, 765);
            return true;
        } catch (ActivityNotFoundException e) {
            C0205d.m752a().m740e(e.getMessage(), new Object[0]);
            return false;
        }
    }

    private byte[] m777a(Context context, Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        CompressFormat bmpFormat = BitmapHelper.getBmpFormat(toByteArray);
        int dipToPx = C2178R.dipToPx(context, Opcodes.ISHL);
        if (CompressFormat.PNG == bmpFormat) {
            dipToPx = C2178R.dipToPx(context, 90);
        }
        Options options = new Options();
        if (((long) toByteArray.length) > this.f390a) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options);
            options.inSampleSize = C0211a.m770a(options, dipToPx, dipToPx);
        }
        options.inJustDecodeBounds = false;
        return m778a(context, BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, options), bmpFormat);
    }

    private byte[] m778a(Context context, Bitmap bitmap, CompressFormat compressFormat) {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            int i = Opcodes.ISHL;
            while (((long) m769a(bitmap, compressFormat)) > this.f390a) {
                int dipToPx = C2178R.dipToPx(context, i);
                i -= 5;
                bitmap = m771a(bitmap, dipToPx);
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
    }

    private void m779b() {
        UIHandler.sendEmptyMessageDelayed(1, 200, this);
    }

    private byte[] m780b(Context context, String str) {
        File file = new File(str);
        if (file.exists()) {
            CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
            int dipToPx = C2178R.dipToPx(context, Opcodes.ISHL);
            if (CompressFormat.PNG == bmpFormat) {
                dipToPx = C2178R.dipToPx(context, 90);
            }
            Options options = new Options();
            if (file.length() > this.f390a) {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                options.inSampleSize = C0211a.m770a(options, dipToPx, dipToPx);
            }
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            if (decodeFile != null) {
                return m778a(context, decodeFile, bmpFormat);
            }
            file.delete();
            throw new RuntimeException("checkArgs fail, thumbData is null");
        }
        throw new FileNotFoundException();
    }

    private boolean m781c() {
        Intent intent = new Intent("com.sina.weibo.sdk.Intent.ACTION_WEIBO_REGISTER");
        String packageName = this.activity.getPackageName();
        intent.putExtra("_weibo_sdkVersion", "0031205000");
        intent.putExtra("_weibo_appPackage", packageName);
        intent.putExtra("_weibo_appKey", this.f392c);
        intent.putExtra("_weibo_flag", 538116905);
        intent.putExtra("_weibo_sign", m772a(this.activity, packageName));
        C0205d.m752a().m737d("intent=" + intent + ", extra=" + intent.getExtras(), new Object[0]);
        this.activity.sendBroadcast(intent, "com.sina.weibo.permission.WEIBO_SDK_PERMISSION");
        return true;
    }

    private String m782d() {
        return UUID.randomUUID().toString().replace("-", C2915a.f14760f);
    }

    private TextObject m783e() {
        TextObject textObject = new TextObject();
        textObject.text = this.f393d.getText();
        return textObject;
    }

    private WebpageObject m784f() {
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.identify = m782d();
        webpageObject.title = this.f393d.getTitle();
        webpageObject.description = this.f393d.getText();
        try {
            if (this.f393d.getImageData() != null) {
                webpageObject.thumbData = m777a(this.activity, this.f393d.getImageData());
            } else if (!TextUtils.isEmpty(this.f393d.getImagePath())) {
                webpageObject.thumbData = m780b(this.activity, this.f393d.getImagePath());
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            webpageObject.setThumbImage(null);
        }
        webpageObject.actionUrl = this.f393d.getUrl();
        webpageObject.defaultText = this.f393d.getText();
        return webpageObject;
    }

    private ImageObject m785g() {
        ImageObject imageObject = new ImageObject();
        try {
            if (this.f393d.getImageData() != null) {
                imageObject.imageData = m777a(this.activity, this.f393d.getImageData());
                return imageObject;
            } else if (TextUtils.isEmpty(this.f393d.getImagePath())) {
                return imageObject;
            } else {
                imageObject.imageData = m780b(this.activity, this.f393d.getImagePath());
                return imageObject;
            }
        } catch (Throwable th) {
            C0205d.m752a().m738d(th);
            return null;
        }
    }

    public void m786a(ShareParams shareParams) {
        this.f393d = shareParams;
    }

    public void m787a(AuthorizeListener authorizeListener) {
        this.f394e = authorizeListener;
    }

    public void m788a(String str) {
        this.f392c = str;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            if (!(this.f391b || this.f394e == null)) {
                this.f394e.onCancel();
            }
            finish();
        }
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        C0205d.m752a().m737d("sina activity requestCode = %s, resultCode = %s", Integer.valueOf(i), Integer.valueOf(i));
        m779b();
    }

    public void onCreate() {
        if (m781c()) {
            UIHandler.sendEmptyMessageDelayed(1, 1000, new C0212b(this));
        }
    }

    public void onNewIntent(Intent intent) {
        this.f391b = true;
        Bundle extras = intent.getExtras();
        C0205d.m752a().m743i("onNewIntent ==>>", extras.toString());
        String stringExtra = intent.getStringExtra("_weibo_appPackage");
        CharSequence stringExtra2 = intent.getStringExtra("_weibo_transaction");
        if (TextUtils.isEmpty(stringExtra)) {
            C0205d.m752a().m740e("handleWeiboResponse faild appPackage is null", new Object[0]);
            return;
        }
        C0205d.m752a().m737d("handleWeiboResponse getCallingPackage : " + this.activity.getCallingPackage(), new Object[0]);
        if (TextUtils.isEmpty(stringExtra2)) {
            C0205d.m752a().m740e("handleWeiboResponse faild intent _weibo_transaction is null", new Object[0]);
        } else if (C0221j.m835a(this.activity, stringExtra) || stringExtra.equals(this.activity.getPackageName())) {
            m774a(extras.getInt("_weibo_resp_errcode"), extras.getString("_weibo_resp_errstr"));
        } else {
            C0205d.m752a().m740e("handleWeiboResponse faild appPackage validateSign faild", new Object[0]);
        }
    }
}
