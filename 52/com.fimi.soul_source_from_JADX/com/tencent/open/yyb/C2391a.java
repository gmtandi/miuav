package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.p133a.C2333f;
import com.tencent.tauth.AuthActivity;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.tencent.open.yyb.a */
public class C2391a {

    /* renamed from: com.tencent.open.yyb.a.a */
    public class C2389a {
        public String f12172a;
        public String f12173b;
        public long f12174c;
    }

    /* renamed from: com.tencent.open.yyb.a.b */
    class C2390b extends AsyncTask<Bundle, Void, Void> {
        private C2390b() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void m13878a(android.os.Bundle... r6) {
            /*
            r5 = this;
            r4 = 0;
            if (r6 != 0) goto L_0x0004;
        L_0x0003:
            return r4;
        L_0x0004:
            r1 = "http://analy.qq.com/cgi-bin/mapp_apptrace";
            r0 = r6.length;
            r2 = 2;
            if (r0 != r2) goto L_0x0069;
        L_0x000a:
            r0 = 1;
            r0 = r6[r0];
            r2 = "uri";
            r0 = r0.getString(r2);
            r2 = android.text.TextUtils.isEmpty(r0);
            if (r2 != 0) goto L_0x0069;
        L_0x0019:
            r1 = 0;
            r2 = "GET";
            r3 = 0;
            r3 = r6[r3];	 Catch:{ Exception -> 0x0048 }
            r0 = com.tencent.open.utils.HttpUtils.openUrl2(r1, r0, r2, r3);	 Catch:{ Exception -> 0x0048 }
            r0 = r0.response;	 Catch:{ Exception -> 0x0048 }
            r0 = com.tencent.open.utils.Util.parseJson(r0);	 Catch:{ Exception -> 0x0048 }
            r1 = "ret";
            r0 = r0.getInt(r1);	 Catch:{ Exception -> 0x0048 }
            r1 = com.tencent.open.p133a.C2333f.f12014d;	 Catch:{ Exception -> 0x0048 }
            r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0048 }
            r2.<init>();	 Catch:{ Exception -> 0x0048 }
            r3 = "-->(ViaAsyncTask)doInBackground : ret = ";
            r2 = r2.append(r3);	 Catch:{ Exception -> 0x0048 }
            r0 = r2.append(r0);	 Catch:{ Exception -> 0x0048 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x0048 }
            com.tencent.open.p133a.C2333f.m13754b(r1, r0);	 Catch:{ Exception -> 0x0048 }
            goto L_0x0003;
        L_0x0048:
            r0 = move-exception;
            r1 = com.tencent.open.p133a.C2333f.f12014d;
            r2 = new java.lang.StringBuilder;
            r2.<init>();
            r3 = "-->(ViaAsyncTask)doInBackground : Exception = ";
            r2 = r2.append(r3);
            r3 = r0.toString();
            r2 = r2.append(r3);
            r2 = r2.toString();
            com.tencent.open.p133a.C2333f.m13754b(r1, r2);
            r0.printStackTrace();
            goto L_0x0003;
        L_0x0069:
            r0 = r1;
            goto L_0x0019;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.yyb.a.b.a(android.os.Bundle[]):java.lang.Void");
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m13878a((Bundle[]) objArr);
        }
    }

    public static Drawable m13879a(String str, Context context) {
        return C2391a.m13880a(str, context, new Rect(0, 0, 0, 0));
    }

    public static Drawable m13880a(String str, Context context, Rect rect) {
        Drawable ninePatchDrawable;
        IOException e;
        InputStream inputStream;
        Throwable th;
        Context applicationContext = context.getApplicationContext();
        InputStream open;
        try {
            open = applicationContext.getAssets().open(str);
            if (open != null) {
                try {
                    if (str.endsWith(".9.png")) {
                        Bitmap decodeStream = BitmapFactory.decodeStream(open);
                        if (decodeStream != null) {
                            ninePatchDrawable = new NinePatchDrawable(applicationContext.getResources(), decodeStream, decodeStream.getNinePatchChunk(), rect, null);
                        } else if (open == null) {
                            return null;
                        } else {
                            try {
                                open.close();
                                return null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return null;
                            }
                        }
                    }
                    ninePatchDrawable = Drawable.createFromStream(open, str);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    inputStream = open;
                    try {
                        e2.printStackTrace();
                        C2333f.m13754b(C2333f.f12014d, "-->(AppbarUtil)getDrawable : IOException");
                        if (inputStream == null) {
                            ninePatchDrawable = null;
                        } else {
                            try {
                                inputStream.close();
                                ninePatchDrawable = null;
                            } catch (IOException e22) {
                                e22.printStackTrace();
                                ninePatchDrawable = null;
                            }
                        }
                        return ninePatchDrawable;
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
                return ninePatchDrawable;
            } else if (open == null) {
                return null;
            } else {
                try {
                    open.close();
                    return null;
                } catch (IOException e222) {
                    e222.printStackTrace();
                    return null;
                }
            }
        } catch (IOException e5) {
            e222 = e5;
            inputStream = null;
            e222.printStackTrace();
            C2333f.m13754b(C2333f.f12014d, "-->(AppbarUtil)getDrawable : IOException");
            if (inputStream == null) {
                inputStream.close();
                ninePatchDrawable = null;
            } else {
                ninePatchDrawable = null;
            }
            return ninePatchDrawable;
        } catch (Throwable th4) {
            th = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public static void m13881a(Context context, String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            String str5 = null;
            if (Uri.parse(str).getHost().toLowerCase().endsWith(".qq.com")) {
                str5 = ".qq.com";
            }
            instance.setCookie(str, C2391a.m13883b("logintype", "MOBILEQ", str5));
            instance.setCookie(str, C2391a.m13883b("qopenid", str2, str5));
            instance.setCookie(str, C2391a.m13883b("qaccesstoken", str3, str5));
            instance.setCookie(str, C2391a.m13883b("openappid", str4, str5));
            CookieSyncManager.getInstance().sync();
        }
    }

    public static void m13882a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("uin", Constants.DEFAULT_UIN);
        bundle.putString(AuthActivity.ACTION_KEY, str2);
        bundle.putString(SocialConstants.PARAM_APP_ID, str);
        bundle.putString("via", str3);
        new C2390b().execute(new Bundle[]{bundle});
    }

    private static String m13883b(String str, String str2, String str3) {
        String str4 = str + "=" + str2;
        if (str3 == null) {
            return str4;
        }
        return (str4 + "; path=/") + "; domain=" + str3;
    }
}
