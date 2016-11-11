package cn.sharesdk.framework;

import cn.sharesdk.framework.p013b.C0182e;
import cn.sharesdk.framework.p013b.p015b.C0171c;
import cn.sharesdk.framework.p013b.p015b.C0173b;
import cn.sharesdk.framework.utils.C0205d;
import com.fimi.soul.entity.User;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.b */
class C0183b implements PlatformActionListener {
    final /* synthetic */ PlatformActionListener f304a;
    final /* synthetic */ int f305b;
    final /* synthetic */ HashMap f306c;
    final /* synthetic */ C0152a f307d;

    C0183b(C0152a c0152a, PlatformActionListener platformActionListener, int i, HashMap hashMap) {
        this.f307d = c0152a;
        this.f304a = platformActionListener;
        this.f305b = i;
        this.f306c = hashMap;
    }

    public void onCancel(Platform platform, int i) {
        this.f307d.f191a = this.f304a;
        if (this.f307d.f191a != null) {
            this.f307d.f191a.onComplete(platform, this.f305b, this.f306c);
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.f307d.f191a = this.f304a;
        if (this.f307d.f191a != null) {
            this.f307d.f191a.onComplete(platform, this.f305b, this.f306c);
        }
        C0171c c0173b = new C0173b();
        c0173b.f248a = platform.getPlatformId();
        c0173b.f249b = "TencentWeibo".equals(platform.getName()) ? platform.getDb().get(User.FN_NAME) : platform.getDb().getUserId();
        c0173b.f250c = new Hashon().fromHashMap(hashMap);
        c0173b.f251d = this.f307d.m425a(platform);
        C0182e.m613a(platform.getContext(), c0173b.g).m622a(c0173b);
        this.f307d.m428a(1, platform);
    }

    public void onError(Platform platform, int i, Throwable th) {
        C0205d.m752a().m750w(th);
        this.f307d.f191a = this.f304a;
        if (this.f307d.f191a != null) {
            this.f307d.f191a.onComplete(platform, this.f305b, this.f306c);
        }
    }
}
