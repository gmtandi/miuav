package com.fimi.soul.p087b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.kernel.p076b.p078b.C1115d;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.kernel.p084e.aa;
import com.fimi.kernel.p084e.ah;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.media.gallery.DroneImagePagerActivity;
import com.fimi.soul.media.gallery.ImagePagerActivity;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import com.fimi.soul.utils.bj;
import com.fimi.soul.view.MediaImageLoadingView;
import com.fimi.soul.view.MyGridView;
import com.fimi.soul.view.StrokeDigitalView;
import com.fimi.soul.view.StrokeTextView;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.b.m */
public class C1224m extends BaseAdapter implements OnItemClickListener {
    public static List<WifiDistanceFile> f5512a;
    ExecutorService f5513b;
    Object f5514c;
    private int f5515d;
    private int f5516e;
    private boolean f5517f;
    private boolean f5518g;
    private Context f5519h;
    private List<C1232u> f5520i;
    private List<WifiDistanceFile> f5521j;
    private C1228q f5522k;
    private C1229r f5523l;
    private int f5524m;
    private boolean f5525n;
    private C1433a f5526o;
    private C1313t f5527p;
    private float f5528q;
    private Set<C1230s> f5529r;
    private MyGridView f5530s;
    private bj f5531t;
    private HashMap f5532u;
    private HashMap<Integer, C1231t> f5533v;
    private Boolean f5534w;
    private int f5535x;
    private int f5536y;

    public C1224m(Context context, MyGridView myGridView) {
        this.f5517f = true;
        this.f5518g = false;
        this.f5522k = C1228q.Normal;
        this.f5524m = 0;
        this.f5525n = false;
        this.f5513b = Executors.newFixedThreadPool(3);
        this.f5532u = null;
        this.f5533v = new HashMap();
        this.f5514c = new Object();
        this.f5534w = Boolean.valueOf(true);
        this.f5535x = 0;
        this.f5536y = 0;
        this.f5519h = context;
        this.f5520i = new ArrayList();
        this.f5521j = new ArrayList();
        this.f5529r = new HashSet();
        this.f5531t = bj.m12402a();
        this.f5532u = DroidPlannerApp.m8514c();
        this.f5530s = myGridView;
        this.f5528q = C1186y.m8308b(context) < ((float) C1186y.m8298a(context)) ? C1186y.m8308b(context) : (float) C1186y.m8298a(context);
    }

    private C1231t m8464a(View view) {
        C1231t c1231t = new C1231t(this);
        c1231t.f5550c = (ImageView) view.findViewById(C1205R.id.iv_flleType);
        c1231t.f5549b = (SimpleDraweeView) view.findViewById(C1205R.id.iv_thumbnail);
        c1231t.f5551d = (ImageView) view.findViewById(C1205R.id.iv_downloadStatus);
        c1231t.f5552e = (MediaImageLoadingView) view.findViewById(C1205R.id.iv_downloadprogress);
        c1231t.f5560m = (ImageView) view.findViewById(C1205R.id.iv_downloading_mask);
        c1231t.f5553f = (StrokeDigitalView) view.findViewById(C1205R.id.tv_duration);
        c1231t.f5548a = view.findViewById(C1205R.id.v_blackMask);
        c1231t.f5554g = (ImageView) view.findViewById(C1205R.id.iv_selected);
        c1231t.f5555h = (TextView) view.findViewById(C1205R.id.tv_progress);
        c1231t.f5556i = (ImageView) view.findViewById(C1205R.id.mask_iv);
        c1231t.f5557j = (StrokeTextView) view.findViewById(C1205R.id.tv_is_down);
        be.m12359a(this.f5519h.getAssets(), c1231t.f5555h);
        return c1231t;
    }

    private void m8466a(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            WifiDistanceFile wifiDistanceFile = (WifiDistanceFile) this.f5521j.get(i3);
            String str = C2915a.f14760f;
            str = C2915a.f14760f;
            if (wifiDistanceFile.getDownloadTaskInfo() == null) {
                String name;
                String remoteThmUrl;
                if (m8492b() && wifiDistanceFile.getType() == 2) {
                    name = wifiDistanceFile.getName();
                    remoteThmUrl = wifiDistanceFile.getRemoteThmUrl();
                    str = C1969i.m12476a(name, wifiDistanceFile.getDateString());
                    name = remoteThmUrl;
                } else {
                    try {
                        if (wifiDistanceFile.getType() == 1) {
                            remoteThmUrl = str;
                            str = wifiDistanceFile.getLocalThumbnailPath();
                            name = remoteThmUrl;
                        } else {
                            remoteThmUrl = str;
                            str = wifiDistanceFile.getPath();
                            name = remoteThmUrl;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (str != null) {
                    String replace = str.replace("file://", C2915a.f14760f);
                    String str2 = "getdur" + replace;
                    Bitmap a = m8477a(replace);
                    str = (String) this.f5532u.get(str2);
                    if (a == null) {
                        C1230s c1230s = new C1230s(this);
                        this.f5529r.add(c1230s);
                        c1230s.executeOnExecutor(this.f5513b, new String[]{name, replace});
                    } else {
                        ImageView imageView = (ImageView) this.f5530s.findViewWithTag(replace);
                        TextView textView = (TextView) this.f5530s.findViewWithTag(str2);
                        if (!m8492b() && wifiDistanceFile.getType() == 2) {
                            if (textView != null && str != null) {
                                textView.setVisibility(0);
                                textView.setText((String) this.f5532u.get(str2));
                            } else if (textView != null) {
                                textView.setVisibility(8);
                            }
                        }
                        if (!(imageView == null || a == null)) {
                            m8467a(imageView, replace, a);
                        }
                    }
                }
            }
        }
    }

    @TargetApi(16)
    private void m8467a(ImageView imageView, String str, Bitmap bitmap) {
        if (bitmap == null) {
            imageView.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
            return;
        }
        imageView.setBackground(aa.m7991a(bitmap));
        imageView.invalidate();
    }

    @TargetApi(16)
    private void m8468a(C1115d c1115d, C1231t c1231t) {
        ImageView imageView = c1231t.f5551d;
        String localDownloadCachePath = c1231t.f5558k.getLocalDownloadCachePath();
        switch (C1227p.f5540a[c1115d.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                c1231t.f5555h.setVisibility(0);
                imageView.setImageResource(C1205R.drawable.download_icon);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c1231t.f5555h.setText(C1205R.string.pasued);
                c1231t.f5555h.setVisibility(0);
                imageView.setImageResource(C1205R.drawable.download_icon);
            case Type.BYTE /*3*/:
                c1231t.f5555h.setText(C1205R.string.down_media_false);
                imageView.setImageResource(C1205R.drawable.continue_icon);
                c1231t.f5552e.m12635b();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                imageView.setImageResource(C1205R.drawable.pause_icon);
                c1231t.f5552e.m12634a();
                Bitmap a = m8477a(localDownloadCachePath);
                if (a != null) {
                    c1231t.f5560m.setVisibility(0);
                    c1231t.f5549b.setBackground(aa.m7991a(a));
                } else {
                    c1231t.f5560m.setVisibility(8);
                    c1231t.f5549b.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
                }
                imageView.setVisibility(0);
            case Type.INT /*5*/:
                c1231t.f5552e.setVisibility(8);
                c1231t.f5555h.setVisibility(8);
                imageView.setVisibility(8);
                imageView.setImageDrawable(null);
                c1231t.f5560m.setVisibility(8);
            default:
        }
    }

    @TargetApi(16)
    private void m8471a(C1231t c1231t, int i, C1113b c1113b) {
        String str;
        String str2 = C2915a.f14760f;
        str2 = C2915a.f14760f;
        if (m8492b() && c1231t.f5558k.getType() == 2) {
            String name = c1231t.f5558k.getName();
            str2 = c1231t.f5558k.getRemoteThmUrl();
            name = C1969i.m12476a(name, c1231t.f5558k.getDateString());
            c1231t.f5558k.setLocalThumbnailPath(name);
            str = str2;
            str2 = name;
        } else if (!m8492b() && c1113b != null && c1113b.m7785g() != C1115d.Completed) {
            str = str2;
            str2 = c1231t.f5558k.getLocalDownloadCachePath();
        } else if (c1231t.f5558k.getType() == 1) {
            str = str2;
            str2 = c1231t.f5558k.getLocalThumbnailPath();
        } else {
            str = str2;
            str2 = c1231t.f5558k.getPath();
        }
        if (str2 != null) {
            Object obj;
            String replace = str2.replace("file://", C2915a.f14760f);
            if (m8492b()) {
                obj = "getdur_online" + replace;
            } else {
                name = "getdur_local" + replace;
            }
            if (!m8492b() && c1231t.f5558k.getType() == 2) {
                c1231t.f5553f.setTag(obj);
                str2 = (String) this.f5532u.get(obj);
                if (str2 != null) {
                    c1231t.f5553f.setVisibility(0);
                    c1231t.f5553f.setText(str2);
                } else if (c1231t.f5558k.getDurationString() != null) {
                    c1231t.f5553f.setVisibility(0);
                    c1231t.f5553f.setText(c1231t.f5558k.getDurationString());
                } else {
                    c1231t.f5553f.setVisibility(8);
                }
            }
            c1231t.f5549b.setTag(replace);
            c1231t.f5553f.setTag(obj);
            Bitmap a = m8477a(replace);
            if (c1113b == null || c1113b.m7785g() == C1115d.Completed) {
                c1231t.f5560m.setVisibility(8);
            } else {
                c1231t.f5560m.setVisibility(0);
            }
            m8467a(c1231t.f5549b, replace, a);
            if (i < 12 && a == null) {
                m8472a(replace, c1231t.f5549b);
                C1230s c1230s = new C1230s(this);
                this.f5529r.add(c1230s);
                c1230s.executeOnExecutor(this.f5513b, new String[]{str, replace});
            }
        }
    }

    @TargetApi(16)
    private void m8472a(String str, ImageView imageView) {
        Bitmap a = m8477a(str);
        imageView.setBackground(null);
        if (a != null) {
            imageView.setBackground(aa.m7991a(a));
        } else {
            imageView.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
        }
    }

    public Bitmap m8477a(String str) {
        return str == null ? null : this.f5531t.m12403a(str);
    }

    public void m8478a() {
        this.f5518g = true;
    }

    public void m8479a(int i) {
        this.f5520i.remove(i);
        notifyDataSetChanged();
    }

    public void m8480a(int i, C1231t c1231t) {
        boolean z = false;
        if (this.f5522k == C1228q.Choose) {
            if (i >= 0 && i < this.f5520i.size()) {
                C1232u c1232u = (C1232u) this.f5520i.get(i);
                Log.d("Good", "total " + this.f5520i.size() + " index " + i);
                if (c1232u.f5563b) {
                    this.f5524m--;
                    c1231t.f5554g.setVisibility(8);
                    c1231t.f5556i.setVisibility(8);
                } else {
                    this.f5524m++;
                    c1231t.f5554g.setVisibility(0);
                    c1231t.f5556i.setVisibility(0);
                }
                if (!c1232u.f5563b) {
                    z = true;
                }
                c1232u.f5563b = z;
            }
            if (this.f5523l != null) {
                this.f5523l.m8503a(this.f5524m);
            }
        }
    }

    public void m8481a(AbsListView absListView, int i) {
        if (i == 0) {
            m8466a(this.f5515d, this.f5516e);
            this.f5534w = Boolean.valueOf(true);
            this.f5535x = this.f5515d;
            this.f5536y = this.f5516e;
            return;
        }
        m8501j();
        this.f5534w = Boolean.valueOf(false);
    }

    public void m8482a(AbsListView absListView, int i, int i2, int i3) {
        this.f5515d = i;
        this.f5516e = i2;
        if (this.f5517f && i2 > 0) {
            m8466a(i, i2);
            this.f5517f = false;
        }
        if (this.f5534w.booleanValue() && this.f5535x + this.f5536y < this.f5515d + this.f5516e) {
            this.f5535x = this.f5515d;
            this.f5536y = this.f5516e;
            m8466a(this.f5515d, this.f5516e);
        }
    }

    public void m8483a(C1228q c1228q) {
        this.f5522k = c1228q;
    }

    public void m8484a(C1229r c1229r) {
        this.f5523l = c1229r;
    }

    public void m8485a(C1433a c1433a) {
        this.f5526o = c1433a;
    }

    public void m8486a(WifiDistanceFile wifiDistanceFile) {
        if (!this.f5521j.contains(wifiDistanceFile)) {
            this.f5521j.add(wifiDistanceFile);
            this.f5520i.add(new C1232u(this, wifiDistanceFile));
        }
        notifyDataSetChanged();
    }

    public void m8487a(String str, Bitmap bitmap) {
        if (m8477a(str) == null) {
            this.f5531t.m12405a(str, bitmap);
        }
    }

    public void m8488a(List<WifiDistanceFile> list) {
        List arrayList = new ArrayList();
        for (WifiDistanceFile c1232u : list) {
            arrayList.add(new C1232u(this, c1232u));
        }
        this.f5520i = arrayList;
        this.f5521j = list;
    }

    public void m8489a(boolean z) {
        this.f5525n = z;
    }

    public void m8490b(WifiDistanceFile wifiDistanceFile) {
        if (this.f5521j.contains(wifiDistanceFile)) {
            int indexOf = this.f5521j.indexOf(wifiDistanceFile);
            this.f5521j.remove(indexOf);
            this.f5520i.remove(indexOf);
        }
        notifyDataSetChanged();
    }

    public void m8491b(String str) {
        for (C1232u c1232u : this.f5520i) {
            if (c1232u.f5562a.getPath().equals(str)) {
                this.f5520i.remove(c1232u);
                break;
            }
        }
        notifyDataSetChanged();
    }

    public boolean m8492b() {
        return this.f5525n;
    }

    public List<WifiDistanceFile> m8493c() {
        List<WifiDistanceFile> arrayList = new ArrayList();
        for (C1232u c1232u : this.f5520i) {
            if (c1232u.f5563b) {
                arrayList.add(c1232u.f5562a);
            }
        }
        return arrayList;
    }

    public void m8494c(WifiDistanceFile wifiDistanceFile) {
        for (int i = 0; i < this.f5521j.size(); i++) {
            if (wifiDistanceFile.getName().equals(((WifiDistanceFile) this.f5521j.get(i)).getName())) {
                this.f5521j.remove(i);
                this.f5520i.remove(i);
                notifyDataSetChanged();
            }
        }
    }

    public List<WifiDistanceFile> m8495d() {
        return this.f5521j;
    }

    public C1228q m8496e() {
        return this.f5522k;
    }

    public void m8497f() {
        for (C1232u c1232u : this.f5520i) {
            c1232u.f5563b = true;
        }
        this.f5524m = this.f5520i.size();
        if (this.f5523l != null) {
            this.f5523l.m8503a(this.f5524m);
        }
        notifyDataSetChanged();
    }

    public void m8498g() {
        for (C1232u c1232u : this.f5520i) {
            c1232u.f5563b = false;
        }
        this.f5524m = 0;
        if (this.f5523l != null) {
            this.f5523l.m8503a(this.f5524m);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f5520i != null ? this.f5520i.size() : 0;
    }

    public Object getItem(int i) {
        return this.f5520i.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @TargetApi(16)
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1231t a;
        this.f5530s = (MyGridView) viewGroup;
        C1232u c1232u = (C1232u) this.f5520i.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.f5519h).inflate(C1205R.layout.item_thumbnail_info, null);
            a = m8464a(view);
            a.f5558k = c1232u.f5562a;
            view.setLayoutParams(new LayoutParams(-1, (int) (((this.f5528q - C1186y.m8296a(this.f5519h, 50.0f)) - (4.0f * C1186y.m8296a(this.f5519h, 5.0f))) / C2020f.f10931a)));
            view.setTag(a);
        } else {
            a = (C1231t) view.getTag();
        }
        a.f5559l = i;
        C1113b downloadTaskInfo = c1232u.f5562a.getDownloadTaskInfo();
        if (!this.f5518g) {
            this.f5533v.put(Integer.valueOf(i), a);
        }
        if (this.f5525n) {
            if (C1276b.m8691c(c1232u.f5562a.getName())) {
                a.f5557j.setVisibility(8);
            } else {
                a.f5557j.setVisibility(0);
                a.f5557j.setText(this.f5519h.getString(C1205R.string.no_down));
            }
        } else if (downloadTaskInfo != null) {
            downloadTaskInfo.m7777a(Integer.valueOf(i));
            downloadTaskInfo.m7776a(new C1225n(this));
        }
        if (c1232u.f5563b) {
            a.f5554g.setVisibility(0);
            a.f5556i.setVisibility(0);
        } else {
            a.f5554g.setVisibility(8);
            a.f5556i.setVisibility(8);
        }
        a.f5558k = c1232u.f5562a;
        a.f5550c.setVisibility(8);
        a.f5553f.setVisibility(4);
        a.f5551d.setVisibility(4);
        a.f5552e.setVisibility(4);
        a.f5549b.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
        a.f5549b.invalidate();
        a.f5549b.setTag(null);
        a.f5555h.setVisibility(4);
        if (downloadTaskInfo != null) {
            a.f5551d.setVisibility(0);
            a.f5552e.setVisibility(0);
            long k = downloadTaskInfo.m7789k() / (downloadTaskInfo.m7787i() / 100);
            a.f5555h.setVisibility(0);
            a.f5552e.setSweepAngle((float) k);
            a.f5555h.setText(String.format(this.f5519h.getString(C1205R.string.downing_media), new Object[]{k + "%"}));
            if (k == 100) {
                downloadTaskInfo.m7784f();
            }
            String localDownloadCachePath = a.f5558k.getLocalDownloadCachePath();
            Bitmap a2;
            switch (C1227p.f5540a[downloadTaskInfo.m7785g().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    a2 = m8477a(localDownloadCachePath);
                    if (a2 != null) {
                        a.f5560m.setVisibility(0);
                        a.f5549b.setBackground(aa.m7991a(a2));
                    } else {
                        a.f5560m.setVisibility(8);
                        a.f5549b.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
                    }
                    a.f5551d.setImageResource(C1205R.drawable.pause_icon);
                    a.f5552e.m12634a();
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    a.f5555h.setText(C1205R.string.pasued);
                    a.f5551d.setImageResource(C1205R.drawable.download_icon);
                    a.f5552e.m12634a();
                    break;
                case Type.BYTE /*3*/:
                    a2 = m8477a(localDownloadCachePath);
                    if (a2 != null) {
                        a.f5560m.setVisibility(0);
                        a.f5549b.setBackground(aa.m7991a(a2));
                    } else {
                        a.f5560m.setVisibility(8);
                        a.f5549b.setBackgroundResource(C1205R.drawable.friends_sends_pictures_no);
                    }
                    a.f5555h.setText(C1205R.string.down_media_false);
                    a.f5551d.setImageResource(C1205R.drawable.continue_icon);
                    a.f5552e.m12635b();
                    break;
                case Type.INT /*5*/:
                    a.f5551d.setVisibility(8);
                    a.f5552e.setVisibility(8);
                    a.f5555h.setVisibility(8);
                    c1232u.f5562a.setDownloadTaskInfo(null);
                    m8471a(a, i, downloadTaskInfo);
                    ah.m8075a(new C1226o(this, downloadTaskInfo));
                    break;
            }
        }
        if (a.f5558k.getType() == 2) {
            a.f5550c.setVisibility(0);
            if (a.f5558k.getDurationString() != null) {
                a.f5553f.setVisibility(0);
                a.f5553f.setText(a.f5558k.getDurationString());
            } else {
                a.f5553f.setVisibility(8);
            }
        }
        if (this.f5518g) {
            this.f5518g = false;
        } else {
            m8471a(a, i, downloadTaskInfo);
        }
        return view;
    }

    public void m8499h() {
        this.f5521j.clear();
        this.f5520i.clear();
        notifyDataSetChanged();
    }

    protected C1313t m8500i() {
        if (this.f5527p == null) {
            this.f5527p = (C1313t) C1276b.m8680a().m8699d();
        }
        return this.f5527p;
    }

    public void m8501j() {
        if (this.f5529r != null) {
            for (C1230s cancel : this.f5529r) {
                cancel.cancel(false);
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!this.f5525n || m8500i().m8848d()) {
            f5512a = new ArrayList();
            C1231t c1231t = (C1231t) view.getTag();
            if (this.f5522k == C1228q.Choose) {
                m8480a(i, c1231t);
                return;
            }
            C1232u c1232u = (C1232u) this.f5520i.get(i);
            C1113b downloadTaskInfo = c1232u.f5562a.getDownloadTaskInfo();
            if (downloadTaskInfo == null || downloadTaskInfo.m7785g() == C1115d.Completed) {
                try {
                    List arrayList = new ArrayList();
                    List arrayList2 = new ArrayList();
                    List arrayList3 = new ArrayList();
                    Intent intent = new Intent(this.f5519h, DroneImagePagerActivity.class);
                    int i2 = 0;
                    int i3 = i;
                    while (i2 < this.f5520i.size()) {
                        C1232u c1232u2 = (C1232u) this.f5520i.get(i2);
                        if (this.f5525n) {
                            if (c1232u2.f5562a.getType() == 2) {
                                arrayList.add(c1232u2.f5562a.getRemoteThmUrl() + "&&" + c1232u2.f5562a.getRemoteUrl());
                                arrayList3.add(c1232u2.f5562a.getDurationString());
                            } else {
                                arrayList.add(c1232u2.f5562a.getRemoteUrl());
                                arrayList3.add(C2915a.f14760f);
                            }
                            arrayList2.add(c1232u2.f5562a.getLocalThumbnailPath());
                            f5512a.add(((C1232u) this.f5520i.get(i2)).f5562a);
                            intent.putExtra(DroneImagePagerActivity.f7692d, true);
                        } else {
                            downloadTaskInfo = c1232u2.f5562a.getDownloadTaskInfo();
                            if (downloadTaskInfo != null && downloadTaskInfo.m7785g() == C1115d.Completed) {
                                arrayList.add(c1232u2.f5562a.getLocalThumbnailPath());
                                arrayList2.add(c1232u2.f5562a.getPath());
                            }
                            if (downloadTaskInfo == null) {
                                arrayList.add(c1232u2.f5562a.getLocalThumbnailPath());
                                arrayList2.add(c1232u2.f5562a.getPath());
                            }
                            intent.putExtra(DroneImagePagerActivity.f7692d, false);
                        }
                        i2++;
                        i3 = c1232u.f5562a == c1232u2.f5562a ? arrayList.size() - 1 : i3;
                    }
                    intent.putExtra(ImagePagerActivity.f7774b, (Serializable) arrayList);
                    intent.putExtra(DroneImagePagerActivity.f7693e, (Serializable) arrayList2);
                    intent.putExtra(ImagePagerActivity.f7773a, i3);
                    intent.putExtra(DroneImagePagerActivity.f7690b, c1232u.f5562a.getType());
                    intent.putExtra(DroneImagePagerActivity.f7694f, (Serializable) arrayList3);
                    ((Activity) this.f5519h).startActivityForResult(intent, 0);
                    ((Activity) this.f5519h).overridePendingTransition(17432576, 17432577);
                    return;
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    return;
                }
            }
            switch (C1227p.f5540a[downloadTaskInfo.m7785g().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    downloadTaskInfo.m7783e();
                    c1231t.f5555h.setText(this.f5519h.getResources().getString(C1205R.string.pasued));
                    c1231t.f5551d.setImageResource(C1205R.drawable.download_icon);
                    c1231t.f5552e.m12634a();
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                case Type.BYTE /*3*/:
                    long k = downloadTaskInfo.m7789k() / (downloadTaskInfo.m7787i() / 100);
                    Log.d("Good", "\u6267\u884c\u70b9\u51fb\u4e8b\u4ef6" + downloadTaskInfo.m7785g());
                    if (C1122k.m7798a(this.f5519h).m7810b() < 3) {
                        downloadTaskInfo.m7781c();
                    } else {
                        downloadTaskInfo.m7782d();
                    }
                    c1231t.f5551d.setImageResource(C1205R.drawable.pause_icon);
                    c1231t.f5552e.m12634a();
                    c1231t.f5555h.setText(String.format(this.f5519h.getString(C1205R.string.downing_media), new Object[]{k + "%"}));
                    return;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    Log.d("Good", "\u6267\u884c\u70b9\u51fb\u4e8b\u4ef6->\u505c\u6b62");
                    downloadTaskInfo.m7783e();
                    c1231t.f5555h.setText(this.f5519h.getResources().getString(C1205R.string.pasued));
                    c1231t.f5551d.setImageResource(C1205R.drawable.download_icon);
                    c1231t.f5552e.m12634a();
                    C1122k.m7798a(this.f5519h).m7811c();
                    return;
                default:
                    return;
            }
        }
        ak.m8083a(this.f5519h, (int) C1205R.string.no_connect_camera, ak.f5302b);
    }
}
