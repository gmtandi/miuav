package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.facebook.imagepipeline.memory.DefaultFlexByteArrayPoolParams;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.contact.RContact;
import com.tencent.open.SocialConstants;
import com.tencent.open.p134b.C2341d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p152c.p156c.C2955m;

public class ImageActivity extends Activity {
    RelativeLayout f11440a;
    private QQToken f11441b;
    private String f11442c;
    private Handler f11443d;
    private C2210c f11444e;
    private Button f11445f;
    private Button f11446g;
    private C2207b f11447h;
    private TextView f11448i;
    private ProgressBar f11449j;
    private int f11450k;
    private boolean f11451l;
    private long f11452m;
    private int f11453n;
    private final int f11454o;
    private final int f11455p;
    private Rect f11456q;
    private String f11457r;
    private Bitmap f11458s;
    private final OnClickListener f11459t;
    private final OnClickListener f11460u;
    private final IUiListener f11461v;
    private final IUiListener f11462w;

    /* renamed from: com.tencent.connect.avatar.ImageActivity.1 */
    class C21971 implements OnGlobalLayoutListener {
        final /* synthetic */ ImageActivity f11427a;

        C21971(ImageActivity imageActivity) {
            this.f11427a = imageActivity;
        }

        public void onGlobalLayout() {
            this.f11427a.f11440a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            this.f11427a.f11456q = this.f11427a.f11447h.m13316a();
            this.f11427a.f11444e.m13324a(this.f11427a.f11456q);
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.2 */
    class C21992 implements OnClickListener {
        final /* synthetic */ ImageActivity f11429a;

        /* renamed from: com.tencent.connect.avatar.ImageActivity.2.1 */
        class C21981 implements Runnable {
            final /* synthetic */ C21992 f11428a;

            C21981(C21992 c21992) {
                this.f11428a = c21992;
            }

            public void run() {
                this.f11428a.f11429a.m13293c();
            }
        }

        C21992(ImageActivity imageActivity) {
            this.f11429a = imageActivity;
        }

        public void onClick(View view) {
            this.f11429a.f11449j.setVisibility(0);
            this.f11429a.f11446g.setEnabled(false);
            this.f11429a.f11446g.setTextColor(Color.rgb(21, 21, 21));
            this.f11429a.f11445f.setEnabled(false);
            this.f11429a.f11445f.setTextColor(Color.rgb(36, 94, Opcodes.I2F));
            new Thread(new C21981(this)).start();
            if (this.f11429a.f11451l) {
                this.f11429a.m13310a("10657", 0);
                return;
            }
            this.f11429a.m13310a("10655", System.currentTimeMillis() - this.f11429a.f11452m);
            if (this.f11429a.f11444e.f11469b) {
                this.f11429a.m13310a("10654", 0);
            }
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.3 */
    class C22003 implements OnClickListener {
        final /* synthetic */ ImageActivity f11430a;

        C22003(ImageActivity imageActivity) {
            this.f11430a = imageActivity;
        }

        public void onClick(View view) {
            this.f11430a.m13310a("10656", System.currentTimeMillis() - this.f11430a.f11452m);
            this.f11430a.setResult(0);
            this.f11430a.m13297d();
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.4 */
    class C22014 implements Runnable {
        final /* synthetic */ String f11431a;
        final /* synthetic */ int f11432b;
        final /* synthetic */ ImageActivity f11433c;

        C22014(ImageActivity imageActivity, String str, int i) {
            this.f11433c = imageActivity;
            this.f11431a = str;
            this.f11432b = i;
        }

        public void run() {
            this.f11433c.m13291b(this.f11431a, this.f11432b);
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.5 */
    class C22025 implements IUiListener {
        final /* synthetic */ ImageActivity f11434a;

        C22025(ImageActivity imageActivity) {
            this.f11434a = imageActivity;
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            int i;
            this.f11434a.f11446g.setEnabled(true);
            this.f11434a.f11446g.setTextColor(-1);
            this.f11434a.f11445f.setEnabled(true);
            this.f11434a.f11445f.setTextColor(-1);
            this.f11434a.f11449j.setVisibility(8);
            JSONObject jSONObject = (JSONObject) obj;
            try {
                i = jSONObject.getInt("ret");
            } catch (JSONException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i == 0) {
                this.f11434a.m13284a("\u8bbe\u7f6e\u6210\u529f", 0);
                this.f11434a.m13310a("10658", 0);
                C2341d.m13784a().m13786a(this.f11434a.f11441b.getOpenId(), this.f11434a.f11441b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP, Constants.VIA_RESULT_SUCCESS);
                Context context = this.f11434a;
                if (!(this.f11434a.f11442c == null || C2915a.f14760f.equals(this.f11434a.f11442c))) {
                    Intent intent = new Intent();
                    intent.setClassName(context, this.f11434a.f11442c);
                    if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                        context.startActivity(intent);
                    }
                }
                this.f11434a.m13280a(0, jSONObject.toString(), null, null);
                this.f11434a.m13297d();
                return;
            }
            this.f11434a.m13284a("\u8bbe\u7f6e\u51fa\u9519\u4e86\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u518d\u5c1d\u8bd5\u4e0b\u5462\uff1a\uff09", 1);
            C2341d.m13784a().m13786a(this.f11434a.f11441b.getOpenId(), this.f11434a.f11441b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_ACT_TYPE_NINETEEN, Constants.VIA_TO_TYPE_QQ_GROUP);
        }

        public void onError(UiError uiError) {
            this.f11434a.f11446g.setEnabled(true);
            this.f11434a.f11446g.setTextColor(-1);
            this.f11434a.f11445f.setEnabled(true);
            this.f11434a.f11445f.setTextColor(-1);
            this.f11434a.f11445f.setText("\u91cd\u8bd5");
            this.f11434a.f11449j.setVisibility(8);
            this.f11434a.f11451l = true;
            this.f11434a.m13284a(uiError.errorMessage, 1);
            this.f11434a.m13310a("10660", 0);
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.6 */
    class C22046 implements IUiListener {
        final /* synthetic */ ImageActivity f11437a;

        /* renamed from: com.tencent.connect.avatar.ImageActivity.6.1 */
        class C22031 implements Runnable {
            final /* synthetic */ String f11435a;
            final /* synthetic */ C22046 f11436b;

            C22031(C22046 c22046, String str) {
                this.f11436b = c22046;
                this.f11435a = str;
            }

            public void run() {
                this.f11436b.f11437a.m13294c(this.f11435a);
            }
        }

        C22046(ImageActivity imageActivity) {
            this.f11437a = imageActivity;
        }

        private void m13272a(int i) {
            if (this.f11437a.f11450k < 2) {
                this.f11437a.m13299e();
            }
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            int i = -1;
            try {
                i = jSONObject.getInt("ret");
                if (i == 0) {
                    this.f11437a.f11443d.post(new C22031(this, jSONObject.getString(RContact.COL_NICKNAME)));
                    this.f11437a.m13310a("10659", 0);
                } else {
                    this.f11437a.m13310a("10661", 0);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (i != 0) {
                m13272a(i);
            }
        }

        public void onError(UiError uiError) {
            m13272a(0);
        }
    }

    class QQAvatarImp extends BaseApi {
        final /* synthetic */ ImageActivity f11438a;

        public QQAvatarImp(ImageActivity imageActivity, QQToken qQToken) {
            this.f11438a = imageActivity;
            super(qQToken);
        }

        public void setAvator(Bitmap bitmap, IUiListener iUiListener) {
            Bundle composeCGIParams = composeCGIParams();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 40, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            bitmap.recycle();
            IRequestListener tempRequestListener = new TempRequestListener(iUiListener);
            composeCGIParams.putByteArray(SocialConstants.PARAM_AVATAR_URI, toByteArray);
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/set_user_face", composeCGIParams, C2955m.f14864a, tempRequestListener);
            C2341d.m13784a().m13786a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_ACT_TYPE_NINETEEN, Constants.VIA_RESULT_SUCCESS);
        }
    }

    /* renamed from: com.tencent.connect.avatar.ImageActivity.a */
    class C2205a extends View {
        final /* synthetic */ ImageActivity f11439a;

        public C2205a(ImageActivity imageActivity, Context context) {
            this.f11439a = imageActivity;
            super(context);
        }

        public void m13273a(Button button) {
            Drawable stateListDrawable = new StateListDrawable();
            Drawable a = this.f11439a.m13287b("com.tencent.plus.blue_normal.png");
            Drawable a2 = this.f11439a.m13287b("com.tencent.plus.blue_down.png");
            Drawable a3 = this.f11439a.m13287b("com.tencent.plus.blue_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable(stateListDrawable);
        }

        public void m13274b(Button button) {
            Drawable stateListDrawable = new StateListDrawable();
            Drawable a = this.f11439a.m13287b("com.tencent.plus.gray_normal.png");
            Drawable a2 = this.f11439a.m13287b("com.tencent.plus.gray_down.png");
            Drawable a3 = this.f11439a.m13287b("com.tencent.plus.gray_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable(stateListDrawable);
        }
    }

    public ImageActivity() {
        this.f11450k = 0;
        this.f11451l = false;
        this.f11452m = 0;
        this.f11453n = 0;
        this.f11454o = 640;
        this.f11455p = 640;
        this.f11456q = new Rect();
        this.f11459t = new C21992(this);
        this.f11460u = new C22003(this);
        this.f11461v = new C22025(this);
        this.f11462w = new C22046(this);
    }

    private Bitmap m13275a(String str) {
        int i = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Uri parse = Uri.parse(str);
        InputStream openInputStream = getContentResolver().openInputStream(parse);
        if (openInputStream == null) {
            return null;
        }
        BitmapFactory.decodeStream(openInputStream, null, options);
        openInputStream.close();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        while (i2 * i3 > DefaultFlexByteArrayPoolParams.DEFAULT_MAX_BYTE_ARRAY_SIZE) {
            i2 /= 2;
            i3 /= 2;
            i *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(parse), null, options);
    }

    private View m13278a() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        this.f11440a = new RelativeLayout(this);
        this.f11440a.setLayoutParams(layoutParams);
        this.f11440a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams3);
        this.f11440a.addView(relativeLayout);
        this.f11444e = new C2210c(this);
        this.f11444e.setLayoutParams(layoutParams2);
        this.f11444e.setScaleType(ScaleType.MATRIX);
        relativeLayout.addView(this.f11444e);
        this.f11447h = new C2207b(this);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(layoutParams2);
        layoutParams4.addRule(14, -1);
        layoutParams4.addRule(15, -1);
        this.f11447h.setLayoutParams(layoutParams4);
        relativeLayout.addView(this.f11447h);
        relativeLayout = new LinearLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, C2206a.m13314a(this, 80.0f));
        layoutParams2.addRule(14, -1);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setOrientation(0);
        relativeLayout.setGravity(17);
        this.f11440a.addView(relativeLayout);
        View imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(C2206a.m13314a(this, 24.0f), C2206a.m13314a(this, 24.0f)));
        imageView.setImageDrawable(m13287b("com.tencent.plus.logo.png"));
        relativeLayout.addView(imageView);
        this.f11448i = new TextView(this);
        layoutParams2 = new LinearLayout.LayoutParams(layoutParams3);
        layoutParams2.leftMargin = C2206a.m13314a(this, 7.0f);
        this.f11448i.setLayoutParams(layoutParams2);
        this.f11448i.setEllipsize(TruncateAt.END);
        this.f11448i.setSingleLine();
        this.f11448i.setTextColor(-1);
        this.f11448i.setTextSize(24.0f);
        this.f11448i.setVisibility(8);
        relativeLayout.addView(this.f11448i);
        relativeLayout = new RelativeLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, C2206a.m13314a(this, BitmapDescriptorFactory.HUE_YELLOW));
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(9, -1);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setBackgroundDrawable(m13287b("com.tencent.plus.bar.png"));
        int a = C2206a.m13314a(this, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.setPadding(a, a, a, 0);
        this.f11440a.addView(relativeLayout);
        C2205a c2205a = new C2205a(this, this);
        int a2 = C2206a.m13314a(this, 14.0f);
        int a3 = C2206a.m13314a(this, 7.0f);
        this.f11446g = new Button(this);
        this.f11446g.setLayoutParams(new RelativeLayout.LayoutParams(C2206a.m13314a(this, 78.0f), C2206a.m13314a(this, 45.0f)));
        this.f11446g.setText("\u53d6\u6d88");
        this.f11446g.setTextColor(-1);
        this.f11446g.setTextSize(18.0f);
        this.f11446g.setPadding(a2, a3, a2, a3);
        c2205a.m13274b(this.f11446g);
        relativeLayout.addView(this.f11446g);
        this.f11445f = new Button(this);
        LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(C2206a.m13314a(this, 78.0f), C2206a.m13314a(this, 45.0f));
        layoutParams5.addRule(11, -1);
        this.f11445f.setLayoutParams(layoutParams5);
        this.f11445f.setTextColor(-1);
        this.f11445f.setTextSize(18.0f);
        this.f11445f.setPadding(a2, a3, a2, a3);
        this.f11445f.setText("\u9009\u53d6");
        c2205a.m13273a(this.f11445f);
        relativeLayout.addView(this.f11445f);
        imageView = new TextView(this);
        layoutParams4 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams4.addRule(13, -1);
        imageView.setLayoutParams(layoutParams4);
        imageView.setText("\u79fb\u52a8\u548c\u7f29\u653e");
        imageView.setPadding(0, C2206a.m13314a(this, C2020f.f10931a), 0, 0);
        imageView.setTextSize(18.0f);
        imageView.setTextColor(-1);
        relativeLayout.addView(imageView);
        this.f11449j = new ProgressBar(this);
        layoutParams = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams.addRule(14, -1);
        layoutParams.addRule(15, -1);
        this.f11449j.setLayoutParams(layoutParams);
        this.f11449j.setVisibility(8);
        this.f11440a.addView(this.f11449j);
        return this.f11440a;
    }

    private void m13280a(int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_ERROR_CODE, i);
        intent.putExtra(Constants.KEY_ERROR_MSG, str2);
        intent.putExtra(Constants.KEY_ERROR_DETAIL, str3);
        intent.putExtra(Constants.KEY_RESPONSE, str);
        setResult(-1, intent);
    }

    private void m13281a(Bitmap bitmap) {
        new QQAvatarImp(this, this.f11441b).setAvator(bitmap, this.f11461v);
    }

    private void m13284a(String str, int i) {
        this.f11443d.post(new C22014(this, str, i));
    }

    private Drawable m13287b(String str) {
        Drawable createFromStream;
        IOException e;
        try {
            InputStream open = getAssets().open(str);
            createFromStream = Drawable.createFromStream(open, str);
            try {
                open.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return createFromStream;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            createFromStream = null;
            e = iOException;
            e.printStackTrace();
            return createFromStream;
        }
        return createFromStream;
    }

    private void m13288b() {
        try {
            this.f11458s = m13275a(this.f11457r);
            if (this.f11458s == null) {
                throw new IOException("cannot read picture: '" + this.f11457r + "'!");
            }
            this.f11444e.setImageBitmap(this.f11458s);
            this.f11445f.setOnClickListener(this.f11459t);
            this.f11446g.setOnClickListener(this.f11460u);
            this.f11440a.getViewTreeObserver().addOnGlobalLayoutListener(new C21971(this));
        } catch (IOException e) {
            e.printStackTrace();
            String str = Constants.MSG_IMAGE_ERROR;
            m13291b(str, 1);
            m13280a(-5, null, str, e.getMessage());
            m13297d();
        }
    }

    private void m13291b(String str, int i) {
        Toast makeText = Toast.makeText(this, str, 1);
        LinearLayout linearLayout = (LinearLayout) makeText.getView();
        ((TextView) linearLayout.getChildAt(0)).setPadding(8, 0, 0, 0);
        View imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(C2206a.m13314a(this, 16.0f), C2206a.m13314a(this, 16.0f)));
        if (i == 0) {
            imageView.setImageDrawable(m13287b("com.tencent.plus.ic_success.png"));
        } else {
            imageView.setImageDrawable(m13287b("com.tencent.plus.ic_error.png"));
        }
        linearLayout.addView(imageView, 0);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        makeText.setView(linearLayout);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private void m13293c() {
        float width = (float) this.f11456q.width();
        Matrix imageMatrix = this.f11444e.getImageMatrix();
        float[] fArr = new float[9];
        imageMatrix.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fArr[0];
        width = 640.0f / width;
        int i = (int) ((((float) this.f11456q.left) - f) / f3);
        int i2 = (int) ((((float) this.f11456q.top) - f2) / f3);
        Matrix matrix = new Matrix();
        matrix.set(imageMatrix);
        matrix.postScale(width, width);
        int i3 = (int) (650.0f / f3);
        Bitmap createBitmap = Bitmap.createBitmap(this.f11458s, i, i2, Math.min(this.f11458s.getWidth() - i, i3), Math.min(this.f11458s.getHeight() - i2, i3), matrix, true);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, 640, 640);
        createBitmap.recycle();
        m13281a(createBitmap2);
    }

    private void m13294c(String str) {
        CharSequence d = m13296d(str);
        if (!C2915a.f14760f.equals(d)) {
            this.f11448i.setText(d);
            this.f11448i.setVisibility(0);
        }
    }

    private String m13296d(String str) {
        return str.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", "&");
    }

    private void m13297d() {
        finish();
        if (this.f11453n != 0) {
            overridePendingTransition(0, this.f11453n);
        }
    }

    private void m13299e() {
        this.f11450k++;
        new UserInfo(this, this.f11441b).getUserInfo(this.f11462w);
    }

    public void m13310a(String str, long j) {
        Util.reportBernoulli(this, str, j, this.f11441b.getAppId());
    }

    public void onBackPressed() {
        setResult(0);
        m13297d();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setRequestedOrientation(1);
        setContentView(m13278a());
        this.f11443d = new Handler();
        Bundle bundleExtra = getIntent().getBundleExtra(Constants.KEY_PARAMS);
        this.f11457r = bundleExtra.getString(SocialConstants.PARAM_AVATAR_URI);
        this.f11442c = bundleExtra.getString("return_activity");
        String string = bundleExtra.getString(SocialConstants.PARAM_APP_ID);
        String string2 = bundleExtra.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2);
        long j = bundleExtra.getLong(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2);
        String string3 = bundleExtra.getString(SocialConstants.PARAM_OPEN_ID);
        this.f11453n = bundleExtra.getInt("exitAnim");
        this.f11441b = new QQToken(string);
        this.f11441b.setAccessToken(string2, ((j - System.currentTimeMillis()) / 1000) + C2915a.f14760f);
        this.f11441b.setOpenId(string3);
        m13288b();
        m13299e();
        this.f11452m = System.currentTimeMillis();
        m13310a("10653", 0);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f11444e.setImageBitmap(null);
        if (this.f11458s != null && !this.f11458s.isRecycled()) {
            this.f11458s.recycle();
        }
    }
}
