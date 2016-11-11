package cn.sharesdk.framework;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Handler;
import cn.sharesdk.framework.p013b.C0170a;
import cn.sharesdk.framework.p013b.C0182e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.p013b.p015b.C0172a;
import cn.sharesdk.framework.p013b.p015b.C0174d;
import cn.sharesdk.framework.utils.C0205d;
import com.mob.tools.utils.C2178R;
import com.tencent.mm.sdk.contact.RContact;
import dalvik.system.DexFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: cn.sharesdk.framework.l */
public class C0191l {
    private static boolean f333a;
    private Context f334b;
    private String f335c;

    static {
        f333a = true;
    }

    public C0191l(Context context, String str) {
        this.f334b = context;
        this.f335c = str;
    }

    private ArrayList<Platform> m657a(PackageInfo packageInfo) {
        ArrayList<Platform> arrayList = new ArrayList();
        try {
            DexFile dexFile = new DexFile(packageInfo.applicationInfo.sourceDir);
            Enumeration entries = dexFile.entries();
            dexFile.close();
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return null;
        }
        while (entries != null && entries.hasMoreElements()) {
            String str = (String) entries.nextElement();
            if (str.startsWith("cn.sharesdk") && !str.contains(RContact.FAVOUR_CONTACT_SHOW_HEAD_CHAR)) {
                try {
                    Class cls = Class.forName(str);
                    if (!(cls == null || !Platform.class.isAssignableFrom(cls) || CustomPlatform.class.isAssignableFrom(cls))) {
                        Constructor constructor = cls.getConstructor(new Class[]{Context.class});
                        constructor.setAccessible(true);
                        arrayList.add((Platform) constructor.newInstance(new Object[]{this.f334b}));
                    }
                } catch (Throwable th2) {
                    C0205d.m752a().m750w(th2);
                }
            }
        }
        return arrayList;
    }

    private PackageInfo m658e() {
        try {
            PackageManager packageManager = this.f334b.getPackageManager();
            String packageName = this.f334b.getPackageName();
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(Opcodes.ACC_ANNOTATION)) {
                if (packageName.equals(packageInfo.packageName)) {
                    return packageInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            return null;
        }
    }

    private ArrayList<Platform> m659f() {
        String[] strArr = new String[]{"cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.system.bluetooth.Bluetooth", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.share.Alipay"};
        ArrayList<Platform> arrayList = new ArrayList();
        for (String cls : strArr) {
            try {
                Constructor constructor = Class.forName(cls).getConstructor(new Class[]{Context.class});
                constructor.setAccessible(true);
                arrayList.add((Platform) constructor.newInstance(new Object[]{this.f334b}));
            } catch (Throwable th) {
                C0205d.m752a().m738d(th);
            }
        }
        return arrayList;
    }

    public String m660a(int i, String str, HashMap<Integer, HashMap<String, Object>> hashMap) {
        HashMap hashMap2 = (HashMap) hashMap.get(Integer.valueOf(i));
        if (hashMap2 == null) {
            return null;
        }
        Object obj = hashMap2.get(str);
        return obj == null ? null : String.valueOf(obj);
    }

    public String m661a(Bitmap bitmap) {
        return C0170a.m499a(this.f334b, this.f335c).m508a(bitmap);
    }

    public String m662a(String str) {
        return C0170a.m499a(this.f334b, this.f335c).m509a(str);
    }

    public String m663a(String str, boolean z, int i, String str2) {
        return C0170a.m499a(this.f334b, this.f335c).m510a(str, i, z, str2);
    }

    public ArrayList<Platform> m664a() {
        ArrayList f;
        if (f333a) {
            f = m659f();
        } else {
            PackageInfo e = m658e();
            if (e == null) {
                return null;
            }
            f = m657a(e);
        }
        m670a(f);
        return f;
    }

    public void m665a(int i, int i2, HashMap<Integer, HashMap<String, Object>> hashMap) {
        hashMap.put(Integer.valueOf(i2), (HashMap) hashMap.get(Integer.valueOf(i)));
    }

    public void m666a(int i, Platform platform) {
        C0171c c0174d = new C0174d();
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                c0174d.f254a = "SHARESDK_ENTER_SHAREMENU";
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c0174d.f254a = "SHARESDK_CANCEL_SHAREMENU";
                break;
            case Type.BYTE /*3*/:
                c0174d.f254a = "SHARESDK_EDIT_SHARE";
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                c0174d.f254a = "SHARESDK_FAILED_SHARE";
                break;
            case Type.INT /*5*/:
                c0174d.f254a = "SHARESDK_CANCEL_SHARE";
                break;
        }
        if (platform != null) {
            c0174d.f255b = platform.getPlatformId();
        }
        C0182e a = C0182e.m613a(this.f334b, this.f335c);
        if (a != null) {
            a.m622a(c0174d);
        }
    }

    public void m667a(Handler handler, boolean z, int i) {
        C0182e a = C0182e.m613a(this.f334b, this.f335c);
        if (a != null) {
            a.m621a(handler);
            a.m623a(z);
            a.m620a(i);
            a.startThread();
        }
    }

    public void m668a(C0194n c0194n) {
    }

    public void m669a(String str, int i) {
        C0182e a = C0182e.m613a(this.f334b, this.f335c);
        if (a != null) {
            C0171c c0172a = new C0172a();
            c0172a.f245b = str;
            c0172a.f244a = i;
            a.m622a(c0172a);
        }
    }

    public void m670a(ArrayList<Platform> arrayList) {
        if (arrayList != null) {
            Collections.sort(arrayList, new C0192m(this));
        }
    }

    public boolean m671a(HashMap<String, Object> hashMap, HashMap<Integer, HashMap<String, Object>> hashMap2) {
        if (hashMap == null || hashMap.size() <= 0) {
            return false;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("fakelist");
        if (arrayList == null) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                int parseInt;
                try {
                    parseInt = C2178R.parseInt(String.valueOf(hashMap3.get("snsplat")));
                } catch (Throwable th) {
                    C0205d.m752a().m750w(th);
                    parseInt = -1;
                }
                if (parseInt != -1) {
                    hashMap2.put(Integer.valueOf(parseInt), hashMap3);
                }
            }
        }
        return true;
    }

    public void m672b() {
        C0182e a = C0182e.m613a(this.f334b, this.f335c);
        if (a != null) {
            a.stopThread();
        }
    }

    public String m673c() {
        return "2.7.2";
    }

    public int m674d() {
        return 60;
    }
}
