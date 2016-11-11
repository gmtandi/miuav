package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.p128a.C2186a;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.message.RMsgInfoDB;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.p133a.C2333f;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p122a.p123a.p152c.p156c.C2951i;

public class TaskGuide extends BaseApi {
    private static int f11916L;
    static long f11917b;
    private static Drawable f11918k;
    private static Drawable f11919l;
    private static Drawable f11920m;
    private static int f11921n;
    private static int f11922o;
    private static int f11923p;
    private static int f11924q;
    private static int f11925r;
    private static int f11926s;
    private static int f11927t;
    private static int f11928u;
    private static int f11929v;
    private static int f11930w;
    private static int f11931x;
    private static int f11932y;
    private static int f11933z;
    private int f11934A;
    private int f11935B;
    private float f11936C;
    private Interpolator f11937D;
    private boolean f11938E;
    private Context f11939F;
    private boolean f11940G;
    private boolean f11941H;
    private long f11942I;
    private int f11943J;
    private int f11944K;
    private Runnable f11945M;
    private Runnable f11946N;
    boolean f11947a;
    IUiListener f11948c;
    private LayoutParams f11949d;
    private ViewGroup f11950e;
    private WindowManager f11951f;
    private Handler f11952g;
    private C2313h f11953h;
    private C2317k f11954i;
    private C2317k f11955j;

    /* renamed from: com.tencent.open.TaskGuide.1 */
    class C23011 implements Runnable {
        final /* synthetic */ int f11876a;
        final /* synthetic */ TaskGuide f11877b;

        C23011(TaskGuide taskGuide, int i) {
            this.f11877b = taskGuide;
            this.f11876a = i;
        }

        public void run() {
            if (!this.f11877b.f11938E) {
                return;
            }
            if (this.f11876a == 0) {
                ((C2314i) this.f11877b.f11950e.findViewById(1)).m13630a(this.f11877b.f11954i);
            } else if (this.f11876a == 1) {
                ((C2314i) this.f11877b.f11950e.findViewById(2)).m13630a(this.f11877b.f11955j);
            } else if (this.f11876a == 2) {
                ((C2314i) this.f11877b.f11950e.findViewById(1)).m13630a(this.f11877b.f11954i);
                if (this.f11877b.f11950e.getChildCount() > 1) {
                    ((C2314i) this.f11877b.f11950e.findViewById(2)).m13630a(this.f11877b.f11955j);
                }
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.2 */
    class C23022 implements Runnable {
        final /* synthetic */ TaskGuide f11878a;

        C23022(TaskGuide taskGuide) {
            this.f11878a = taskGuide;
        }

        public void run() {
            this.f11878a.f11950e = this.f11878a.m13650b(this.f11878a.f11939F);
            this.f11878a.f11949d = this.f11878a.m13638a(this.f11878a.f11939F);
            this.f11878a.m13661d();
            WindowManager windowManager = (WindowManager) this.f11878a.f11939F.getSystemService("window");
            if (!((Activity) this.f11878a.f11939F).isFinishing()) {
                if (!this.f11878a.f11938E) {
                    windowManager.addView(this.f11878a.f11950e, this.f11878a.f11949d);
                }
                this.f11878a.f11938E = true;
                this.f11878a.m13653b(2);
                this.f11878a.m13678k();
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.3 */
    class C23033 implements Runnable {
        final /* synthetic */ String f11879a;
        final /* synthetic */ TaskGuide f11880b;

        C23033(TaskGuide taskGuide, String str) {
            this.f11880b = taskGuide;
            this.f11879a = str;
        }

        public void run() {
            Toast.makeText(this.f11880b.f11939F, "\u5931\u8d25\uff1a" + this.f11879a, 1).show();
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.4 */
    /* synthetic */ class C23044 {
        static final /* synthetic */ int[] f11881a;

        static {
            f11881a = new int[C2317k.m13632a().length];
            try {
                f11881a[C2317k.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f11881a[C2317k.NORAML.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f11881a[C2317k.WAITTING_BACK_REWARD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f11881a[C2317k.REWARD_SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.a */
    abstract class C2305a implements IRequestListener {
        final /* synthetic */ TaskGuide f11882a;

        private C2305a(TaskGuide taskGuide) {
            this.f11882a = taskGuide;
        }

        protected abstract void m13625a(Exception exception);

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            m13625a(connectTimeoutException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            m13625a(httpStatusException);
        }

        public void onIOException(IOException iOException) {
            m13625a(iOException);
        }

        public void onJSONException(JSONException jSONException) {
            m13625a(jSONException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            m13625a(malformedURLException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            m13625a(networkUnavailableException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            m13625a(socketTimeoutException);
        }

        public void onUnknowException(Exception exception) {
            m13625a(exception);
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.b */
    class C2306b implements Runnable {
        final /* synthetic */ TaskGuide f11883a;

        private C2306b(TaskGuide taskGuide) {
            this.f11883a = taskGuide;
        }

        public void run() {
            this.f11883a.m13679l();
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.c */
    class C2307c implements Runnable {
        boolean f11884a;
        float f11885b;
        final /* synthetic */ TaskGuide f11886c;

        public C2307c(TaskGuide taskGuide, boolean z) {
            this.f11886c = taskGuide;
            this.f11884a = false;
            this.f11885b = 0.0f;
            this.f11884a = z;
        }

        public void run() {
            Object obj = 1;
            SystemClock.currentThreadTimeMillis();
            this.f11885b = (float) (((double) this.f11885b) + 0.1d);
            float f = this.f11885b;
            if (f > C2020f.f10933c) {
                f = C2020f.f10933c;
            }
            Object obj2 = f >= C2020f.f10933c ? 1 : null;
            int interpolation = (int) (this.f11886c.f11937D.getInterpolation(f) * ((float) this.f11886c.f11943J));
            if (this.f11884a) {
                this.f11886c.f11949d.y = this.f11886c.f11944K + interpolation;
            } else {
                this.f11886c.f11949d.y = this.f11886c.f11944K - interpolation;
            }
            C2333f.m13754b("TAG", "mWinParams.y = " + this.f11886c.f11949d.y + "deltaDistence = " + interpolation);
            if (this.f11886c.f11938E) {
                this.f11886c.f11951f.updateViewLayout(this.f11886c.f11950e, this.f11886c.f11949d);
                obj = obj2;
            }
            if (obj != null) {
                this.f11886c.m13673i();
            } else {
                this.f11886c.f11952g.postDelayed(this.f11886c.f11945M, 5);
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.d */
    class C2309d extends C2305a {
        int f11889b;
        final /* synthetic */ TaskGuide f11890c;

        /* renamed from: com.tencent.open.TaskGuide.d.1 */
        class C23081 implements Runnable {
            final /* synthetic */ Exception f11887a;
            final /* synthetic */ C2309d f11888b;

            C23081(C2309d c2309d, Exception exception) {
                this.f11888b = c2309d;
                this.f11887a = exception;
            }

            public void run() {
                C2317k c2317k = C2317k.INIT;
                if ((this.f11888b.f11889b == 0 ? this.f11888b.f11890c.f11954i : this.f11888b.f11890c.f11955j) == C2317k.WAITTING_BACK_REWARD) {
                    this.f11888b.f11890c.m13642a(this.f11888b.f11889b, C2317k.NORAML);
                    this.f11888b.f11890c.m13645a("\u9886\u53d6\u5931\u8d25 :" + this.f11887a.getClass().getName());
                }
                this.f11888b.f11890c.m13653b(this.f11888b.f11889b);
                this.f11888b.f11890c.m13662d((int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            }
        }

        public C2309d(TaskGuide taskGuide, int i) {
            this.f11890c = taskGuide;
            super(null);
            this.f11889b = -1;
            this.f11889b = i;
        }

        protected void m13626a(Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            this.f11890c.f11948c.onError(new UiError(Opcodes.LSUB, "error ", "\u91d1\u5238\u9886\u53d6\u65f6\u51fa\u73b0\u5f02\u5e38"));
            if (this.f11890c.f11952g != null) {
                this.f11890c.f11952g.post(new C23081(this, exception));
            }
        }

        public void onComplete(JSONObject jSONObject) {
            String str = null;
            try {
                int i = jSONObject.getInt(XiaomiOAuthConstants.EXTRA_CODE_2);
                str = jSONObject.getString(RMsgInfoDB.TABLE);
                JSONObject jSONObject2;
                if (i == 0) {
                    this.f11890c.m13642a(this.f11889b, C2317k.REWARD_SUCCESS);
                    jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("result", "\u91d1\u5238\u9886\u53d6\u6210\u529f");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.f11890c.f11948c.onComplete(jSONObject2);
                    this.f11890c.m13653b(this.f11889b);
                    this.f11890c.m13662d((int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
                this.f11890c.m13642a(this.f11889b, C2317k.NORAML);
                this.f11890c.m13645a(str);
                jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("result", "\u91d1\u5238\u9886\u53d6\u5931\u8d25");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.f11890c.f11948c.onComplete(jSONObject2);
                this.f11890c.m13653b(this.f11889b);
                this.f11890c.m13662d((int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
            } catch (JSONException e22) {
                this.f11890c.m13642a(this.f11889b, C2317k.NORAML);
                this.f11890c.m13645a(str);
                e22.printStackTrace();
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.e */
    class C2310e extends RelativeLayout {
        int f11891a;
        final /* synthetic */ TaskGuide f11892b;

        public C2310e(TaskGuide taskGuide, Context context) {
            this.f11892b = taskGuide;
            super(context);
            this.f11891a = 0;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            int y = (int) motionEvent.getY();
            C2333f.m13754b("XXXX", "onInterceptTouchEvent-- action = " + motionEvent.getAction() + "currentY = " + y);
            this.f11892b.m13662d(3000);
            switch (motionEvent.getAction()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f11891a = y;
                    return false;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (this.f11891a - y > ViewConfiguration.getTouchSlop() * 2) {
                        this.f11892b.m13679l();
                        return true;
                    }
                    break;
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            super.onTouchEvent(motionEvent);
            int y = (int) motionEvent.getY();
            C2333f.m13754b("XXXX", " onTouchEvent-----startY = " + this.f11891a + "currentY = " + y);
            switch (motionEvent.getAction()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f11891a = y;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (this.f11891a - y > ViewConfiguration.getTouchSlop() * 2) {
                        this.f11892b.m13679l();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.f */
    class C2311f implements OnClickListener {
        int f11893a;
        final /* synthetic */ TaskGuide f11894b;

        public C2311f(TaskGuide taskGuide, int i) {
            this.f11894b = taskGuide;
            this.f11893a = i;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            if (this.f11894b.m13655c(this.f11893a) == C2317k.NORAML) {
                this.f11894b.m13664e(this.f11893a);
                this.f11894b.m13653b(this.f11893a);
            }
            this.f11894b.m13672h();
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.g */
    class C2312g {
        int f11895a;
        String f11896b;
        String f11897c;
        long f11898d;
        int f11899e;

        public C2312g(int i, String str, String str2, long j, int i2) {
            this.f11895a = i;
            this.f11896b = str;
            this.f11897c = str2;
            this.f11898d = j;
            this.f11899e = i2;
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.h */
    class C2313h {
        String f11900a;
        String f11901b;
        C2312g[] f11902c;

        private C2313h() {
        }

        static C2313h m13627a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            C2313h c2313h = new C2313h();
            JSONObject jSONObject2 = jSONObject.getJSONObject("task_info");
            c2313h.f11900a = jSONObject2.getString("task_id");
            c2313h.f11901b = jSONObject2.getString("task_desc");
            JSONArray jSONArray = jSONObject2.getJSONArray("step_info");
            int length = jSONArray.length();
            if (length > 0) {
                c2313h.f11902c = new C2312g[length];
            }
            for (int i = 0; i < length; i++) {
                jSONObject2 = jSONArray.getJSONObject(i);
                c2313h.f11902c[i] = new C2312g(jSONObject2.getInt("step_no"), jSONObject2.getString("step_desc"), jSONObject2.getString("step_gift"), jSONObject2.getLong("end_time"), jSONObject2.getInt(RMsgInfo.COL_STATUS));
            }
            return c2313h;
        }

        public boolean m13628a() {
            return (TextUtils.isEmpty(this.f11900a) || this.f11902c == null || this.f11902c.length <= 0) ? false : true;
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.i */
    class C2314i extends LinearLayout {
        final /* synthetic */ TaskGuide f11903a;
        private TextView f11904b;
        private Button f11905c;
        private C2312g f11906d;

        public C2314i(TaskGuide taskGuide, Context context, C2312g c2312g) {
            this.f11903a = taskGuide;
            super(context);
            this.f11906d = c2312g;
            setOrientation(0);
            m13629a();
        }

        private void m13629a() {
            this.f11904b = new TextView(this.f11903a.f11939F);
            this.f11904b.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
            this.f11904b.setTextSize(15.0f);
            this.f11904b.setShadowLayer(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, Color.rgb(242, C2799f.f14284v, Opcodes.IFNONNULL));
            this.f11904b.setGravity(3);
            this.f11904b.setEllipsize(TruncateAt.END);
            this.f11904b.setIncludeFontPadding(false);
            this.f11904b.setSingleLine(true);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = C2020f.f10933c;
            layoutParams.leftMargin = this.f11903a.m13634a(4);
            addView(this.f11904b, layoutParams);
            this.f11905c = new Button(this.f11903a.f11939F);
            this.f11905c.setPadding(0, 0, 0, 0);
            this.f11905c.setTextSize(16.0f);
            this.f11905c.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
            this.f11905c.setShadowLayer(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, Color.rgb(242, C2799f.f14284v, Opcodes.IFNONNULL));
            this.f11905c.setIncludeFontPadding(false);
            this.f11905c.setOnClickListener(new C2311f(this.f11903a, this.f11906d.f11895a));
            layoutParams = new LinearLayout.LayoutParams(this.f11903a.m13634a(TaskGuide.f11923p), this.f11903a.m13634a(TaskGuide.f11924q));
            layoutParams.leftMargin = this.f11903a.m13634a(2);
            layoutParams.rightMargin = this.f11903a.m13634a(8);
            addView(this.f11905c, layoutParams);
        }

        public void m13630a(C2317k c2317k) {
            if (!TextUtils.isEmpty(this.f11906d.f11896b)) {
                this.f11904b.setText(this.f11906d.f11896b);
            }
            switch (C23044.f11881a[c2317k.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f11905c.setEnabled(false);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (this.f11906d.f11899e == 1) {
                        this.f11905c.setText(this.f11906d.f11897c);
                        this.f11905c.setBackgroundDrawable(null);
                        this.f11905c.setTextColor(Color.rgb(Util.MASK_8BIT, 246, 0));
                        this.f11905c.setEnabled(false);
                    } else if (this.f11906d.f11899e == 2) {
                        this.f11905c.setText("\u9886\u53d6\u5956\u52b1");
                        this.f11905c.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
                        this.f11905c.setBackgroundDrawable(this.f11903a.m13668f());
                        this.f11905c.setEnabled(true);
                    }
                case Type.BYTE /*3*/:
                    this.f11905c.setText("\u9886\u53d6\u4e2d...");
                    this.f11905c.setEnabled(false);
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    this.f11905c.setText("\u5df2\u9886\u53d6");
                    this.f11905c.setBackgroundDrawable(this.f11903a.m13669g());
                    this.f11905c.setEnabled(false);
                default:
            }
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.j */
    class C2316j extends C2305a {
        final /* synthetic */ TaskGuide f11908b;

        /* renamed from: com.tencent.open.TaskGuide.j.1 */
        class C23151 implements Runnable {
            final /* synthetic */ C2316j f11907a;

            C23151(C2316j c2316j) {
                this.f11907a = c2316j;
            }

            public void run() {
                this.f11907a.f11908b.m13642a(2, C2317k.INIT);
            }
        }

        private C2316j(TaskGuide taskGuide) {
            this.f11908b = taskGuide;
            super(null);
        }

        protected void m13631a(Exception exception) {
            if (exception != null) {
                exception.printStackTrace();
            }
            if (exception == null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("result", "\u6682\u65e0\u4efb\u52a1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.f11908b.f11948c.onComplete(jSONObject);
            } else {
                this.f11908b.f11948c.onError(new UiError(100, "error ", "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25"));
            }
            this.f11908b.f11952g.post(new C23151(this));
        }

        public void onComplete(JSONObject jSONObject) {
            try {
                this.f11908b.f11953h = C2313h.m13627a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.f11908b.f11953h == null || !this.f11908b.f11953h.m13628a()) {
                m13631a(null);
                return;
            }
            this.f11908b.showWindow();
            this.f11908b.m13642a(2, C2317k.NORAML);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", "\u83b7\u53d6\u6210\u529f");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.f11908b.f11948c.onComplete(jSONObject2);
        }
    }

    /* renamed from: com.tencent.open.TaskGuide.k */
    enum C2317k {
        INIT,
        WAITTING_BACK_TASKINFO,
        WAITTING_BACK_REWARD,
        NORAML,
        REWARD_SUCCESS,
        REWARD_FAIL;

        public static C2317k[] m13632a() {
            return (C2317k[]) f11915g.clone();
        }
    }

    static {
        f11921n = 75;
        f11922o = 284;
        f11923p = 75;
        f11924q = 30;
        f11925r = 29;
        f11926s = 5;
        f11927t = 74;
        f11928u = 0;
        f11929v = 6;
        f11930w = Opcodes.IFEQ;
        f11931x = 30;
        f11932y = 6;
        f11933z = 3;
        f11917b = 5000;
        f11916L = 3000;
    }

    public TaskGuide(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        this.f11949d = null;
        this.f11950e = null;
        this.f11952g = new Handler(Looper.getMainLooper());
        this.f11954i = C2317k.INIT;
        this.f11955j = C2317k.INIT;
        this.f11934A = 0;
        this.f11935B = 0;
        this.f11936C = 0.0f;
        this.f11937D = new AccelerateInterpolator();
        this.f11938E = false;
        this.f11947a = false;
        this.f11940G = false;
        this.f11941H = false;
        this.f11945M = null;
        this.f11946N = null;
        this.f11939F = context;
        this.f11951f = (WindowManager) context.getSystemService("window");
        m13657c();
    }

    public TaskGuide(Context context, QQToken qQToken) {
        super(qQToken);
        this.f11949d = null;
        this.f11950e = null;
        this.f11952g = new Handler(Looper.getMainLooper());
        this.f11954i = C2317k.INIT;
        this.f11955j = C2317k.INIT;
        this.f11934A = 0;
        this.f11935B = 0;
        this.f11936C = 0.0f;
        this.f11937D = new AccelerateInterpolator();
        this.f11938E = false;
        this.f11947a = false;
        this.f11940G = false;
        this.f11941H = false;
        this.f11945M = null;
        this.f11946N = null;
        this.f11939F = context;
        this.f11951f = (WindowManager) context.getSystemService("window");
        m13657c();
    }

    private int m13634a(int i) {
        return (int) (((float) i) * this.f11936C);
    }

    private Drawable m13635a(String str, Context context) {
        Drawable createFromStream;
        IOException e;
        try {
            InputStream open = context.getApplicationContext().getAssets().open(str);
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                if (decodeStream == null) {
                    return null;
                }
                byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                return new NinePatchDrawable(decodeStream, ninePatchChunk, new Rect(), null);
            }
            createFromStream = Drawable.createFromStream(open, str);
            try {
                open.close();
                return createFromStream;
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
    }

    private LayoutParams m13638a(Context context) {
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 49;
        this.f11951f.getDefaultDisplay().getWidth();
        this.f11951f.getDefaultDisplay().getHeight();
        layoutParams.width = m13634a(f11922o);
        layoutParams.height = m13634a(f11921n);
        layoutParams.windowAnimations = 16973826;
        layoutParams.format = 1;
        layoutParams.flags |= 520;
        layoutParams.type = 2;
        this.f11949d = layoutParams;
        return layoutParams;
    }

    private void m13642a(int i, C2317k c2317k) {
        if (i == 0) {
            this.f11954i = c2317k;
        } else if (i == 1) {
            this.f11955j = c2317k;
        } else {
            this.f11954i = c2317k;
            this.f11955j = c2317k;
        }
    }

    private void m13645a(String str) {
        this.f11952g.post(new C23033(this, str));
    }

    private void m13646a(boolean z) {
        this.f11942I = SystemClock.currentThreadTimeMillis();
        if (z) {
            this.f11940G = true;
        } else {
            this.f11941H = true;
        }
        this.f11943J = this.f11949d.height;
        this.f11944K = this.f11949d.y;
        LayoutParams layoutParams = this.f11949d;
        layoutParams.flags |= 16;
        this.f11951f.updateViewLayout(this.f11950e, this.f11949d);
    }

    private ViewGroup m13650b(Context context) {
        ViewGroup c2310e = new C2310e(this, context);
        C2312g[] c2312gArr = this.f11953h.f11902c;
        View c2314i;
        ViewGroup.LayoutParams layoutParams;
        if (c2312gArr.length == 1) {
            c2314i = new C2314i(this, context, c2312gArr[0]);
            c2314i.setId(1);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(15);
            c2310e.addView(c2314i, layoutParams);
        } else {
            c2314i = new C2314i(this, context, c2312gArr[0]);
            c2314i.setId(1);
            View c2314i2 = new C2314i(this, context, c2312gArr[1]);
            c2314i2.setId(2);
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(14);
            layoutParams.setMargins(0, m13634a(6), 0, 0);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(14);
            layoutParams2.setMargins(0, m13634a(4), 0, 0);
            layoutParams2.addRule(3, 1);
            layoutParams2.addRule(5, 1);
            c2310e.addView(c2314i, layoutParams);
            c2310e.addView(c2314i2, layoutParams2);
        }
        c2310e.setBackgroundDrawable(m13663e());
        return c2310e;
    }

    private void m13653b(int i) {
        if (this.f11952g != null) {
            this.f11952g.post(new C23011(this, i));
        }
    }

    private C2317k m13655c(int i) {
        return i == 0 ? this.f11954i : i == 1 ? this.f11955j : C2317k.INIT;
    }

    private void m13657c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f11951f.getDefaultDisplay().getMetrics(displayMetrics);
        this.f11934A = displayMetrics.widthPixels;
        this.f11935B = displayMetrics.heightPixels;
        this.f11936C = displayMetrics.density;
    }

    private void m13661d() {
        if (this.f11949d != null) {
            this.f11949d.y = -this.f11949d.height;
        }
    }

    private void m13662d(int i) {
        m13672h();
        this.f11946N = new C2306b();
        this.f11952g.postDelayed(this.f11946N, (long) i);
    }

    private Drawable m13663e() {
        if (f11918k == null) {
            f11918k = m13635a("background.9.png", this.f11939F);
        }
        return f11918k;
    }

    private void m13664e(int i) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString(AuthActivity.ACTION_KEY, "get_gift");
        composeCGIParams.putString("task_id", this.f11953h.f11900a);
        composeCGIParams.putString("step_no", new Integer(i).toString());
        composeCGIParams.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
        HttpUtils.requestAsync(this.mToken, this.f11939F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", composeCGIParams, C2951i.f14860a, new C2309d(this, i));
        m13642a(i, C2317k.WAITTING_BACK_REWARD);
        C2186a.m13215a(this.f11939F, this.mToken, "TaskApi", "getGift");
    }

    private Drawable m13668f() {
        if (f11919l == null) {
            f11919l = m13635a("button_green.9.png", this.f11939F);
        }
        return f11919l;
    }

    private Drawable m13669g() {
        if (f11920m == null) {
            f11920m = m13635a("button_red.9.png", this.f11939F);
        }
        return f11920m;
    }

    private void m13672h() {
        this.f11952g.removeCallbacks(this.f11946N);
        if (!m13676j()) {
            this.f11952g.removeCallbacks(this.f11945M);
        }
    }

    private void m13673i() {
        if (this.f11940G) {
            m13662d(3000);
        } else {
            removeWindow();
        }
        if (this.f11940G) {
            LayoutParams layoutParams = this.f11949d;
            layoutParams.flags &= -17;
            this.f11951f.updateViewLayout(this.f11950e, this.f11949d);
        }
        this.f11940G = false;
        this.f11941H = false;
    }

    private boolean m13676j() {
        return this.f11940G || this.f11941H;
    }

    private void m13678k() {
        if (!m13676j()) {
            this.f11952g.removeCallbacks(this.f11946N);
            this.f11952g.removeCallbacks(this.f11945M);
            this.f11945M = new C2307c(this, true);
            m13646a(true);
            this.f11952g.post(this.f11945M);
        }
    }

    private void m13679l() {
        if (!m13676j()) {
            this.f11952g.removeCallbacks(this.f11946N);
            this.f11952g.removeCallbacks(this.f11945M);
            this.f11945M = new C2307c(this, false);
            m13646a(false);
            this.f11952g.post(this.f11945M);
        }
    }

    public void removeWindow() {
        if (this.f11938E) {
            this.f11951f.removeView(this.f11950e);
            this.f11938E = false;
        }
    }

    public void showTaskGuideWindow(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f11939F = activity;
        this.f11948c = iUiListener;
        if (this.f11954i != C2317k.WAITTING_BACK_TASKINFO && this.f11955j != C2317k.WAITTING_BACK_TASKINFO && !this.f11938E) {
            Bundle bundle2;
            this.f11953h = null;
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
                bundle2.putAll(composeCGIParams());
            } else {
                bundle2 = composeCGIParams();
            }
            IRequestListener c2316j = new C2316j();
            bundle2.putString(AuthActivity.ACTION_KEY, "task_list");
            bundle2.putString("auth", "mobile");
            bundle2.putString(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            HttpUtils.requestAsync(this.mToken, this.f11939F, "http://appact.qzone.qq.com/appstore_activity_task_pcpush_sdk", bundle2, C2951i.f14860a, c2316j);
            m13642a(2, C2317k.WAITTING_BACK_TASKINFO);
        }
    }

    @SuppressLint({"ResourceAsColor"})
    public void showWindow() {
        new Handler(Looper.getMainLooper()).post(new C23022(this));
        C2186a.m13215a(this.f11939F, this.mToken, "TaskApi", "showTaskWindow");
    }
}
