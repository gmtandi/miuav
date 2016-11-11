package com.fimi.soul.module.flyplannermedia;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import com.fimi.soul.biz.camera.p088b.C1275d;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.p087b.C1224m;
import com.fimi.soul.p087b.C1228q;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.MyGridView;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

public abstract class DroneBaseMediaFragment extends Fragment implements OnItemLongClickListener, C1275d, C1213e<X11RespCmd> {
    protected C1224m f8647a;
    float f8648b;
    List<X11FileInfo> f8649c;
    private MyGridView f8650d;
    private TextView f8651e;
    private C1313t f8652f;
    private boolean f8653g;
    private boolean f8654h;
    private C1433a f8655i;
    private boolean f8656j;
    private boolean f8657k;
    private boolean f8658l;

    public DroneBaseMediaFragment() {
        this.f8650d = null;
        this.f8651e = null;
        this.f8653g = false;
        this.f8654h = false;
        this.f8656j = false;
        this.f8657k = false;
        this.f8658l = false;
        this.f8648b = 0.0f;
        this.f8649c = null;
    }

    private DroneMediaTabActivity m11380o() {
        return (DroneMediaTabActivity) getActivity();
    }

    abstract void m11381a(GridView gridView);

    public void m11382a(WifiDistanceFile wifiDistanceFile) {
        if ((wifiDistanceFile == null || this.f8649c == null || this.f8649c.size() == 0) && !this.f8658l) {
            this.f8649c = m11404m().m8855k().getCurDirFileList();
        }
        this.f8658l = true;
        if (this.f8649c != null && this.f8649c.size() > 0) {
            X11FileInfo x11FileInfo = null;
            for (X11FileInfo x11FileInfo2 : this.f8649c) {
                X11FileInfo x11FileInfo22;
                if (!x11FileInfo22.getAbsolutePath().equals(wifiDistanceFile.getAbsolutePath())) {
                    x11FileInfo22 = x11FileInfo;
                }
                x11FileInfo = x11FileInfo22;
            }
            if (x11FileInfo != null) {
                this.f8649c.remove(x11FileInfo);
            }
        }
    }

    public void m11383a(List<WifiDistanceFile> list) {
        if (list != null && list.size() != 0 && this.f8649c != null && this.f8649c.size() != 0) {
            Log.d("moweiru", "toDeleteFiles=" + ((WifiDistanceFile) list.get(0)).getName());
            List<X11FileInfo> arrayList = new ArrayList();
            for (WifiDistanceFile wifiDistanceFile : list) {
                for (X11FileInfo x11FileInfo : this.f8649c) {
                    if (x11FileInfo.getName().equals(wifiDistanceFile.getName())) {
                        arrayList.add(x11FileInfo);
                    }
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                for (X11FileInfo remove : arrayList) {
                    this.f8649c.remove(remove);
                }
            }
        }
    }

    protected void m11384a(boolean z) {
        this.f8654h = z;
    }

    protected boolean m11385a() {
        return this.f8654h;
    }

    protected C1224m m11386b() {
        return this.f8647a;
    }

    protected void m11387b(boolean z) {
        m11380o().m11423b().setVisibility(z ? 0 : 8);
    }

    protected Button m11388c() {
        return (Button) m11380o().m11423b().findViewById(C1205R.id.btn_topLeft);
    }

    protected void m11389c(boolean z) {
        m11380o().m11421a().setVisibility(z ? 0 : 8);
    }

    protected TextView m11390d() {
        return this.f8651e;
    }

    protected void m11391d(boolean z) {
        if (m11380o() != null) {
            m11380o().m11422a(z);
        }
    }

    public List<X11FileInfo> m11392e() {
        return this.f8649c;
    }

    protected void m11393e(boolean z) {
        m11380o().m11421a().findViewById(C1205R.id.ll_bottomDownload).setVisibility(z ? 0 : 8);
    }

    protected Button m11394f() {
        return (Button) m11380o().m11423b().findViewById(C1205R.id.btn_topRight);
    }

    protected void m11395f(boolean z) {
        View findViewById = m11380o().m11421a().findViewById(C1205R.id.ll_bottomShare);
        if (z) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(8);
        }
    }

    protected TextView m11396g() {
        return (TextView) m11380o().m11423b().findViewById(C1205R.id.tv_topCenter);
    }

    protected void m11397g(boolean z) {
        if (m11380o() != null && m11380o().findViewById(C1205R.id.load_pb) != null) {
            m11380o().findViewById(C1205R.id.load_pb).setVisibility(z ? 0 : 8);
        }
    }

    public String m11398h() {
        int i;
        int i2;
        File file = new File(C1969i.m12492o());
        if (file == null || !file.exists()) {
            i = 0;
            i2 = 0;
        } else {
            File[] listFiles = file.listFiles();
            i = 0;
            i2 = 0;
            for (File name : listFiles) {
                String name2 = name.getName();
                if (name2.endsWith(X11FileInfo.FILE_TYPE_MP4)) {
                    i++;
                }
                if (name2.endsWith(Util.PHOTO_DEFAULT_EXT)) {
                    i2++;
                }
            }
        }
        return String.format(getActivity().getResources().getString(C1205R.string.local_media_detail), new Object[]{i2 + C2915a.f14760f, i + C2915a.f14760f});
    }

    protected void m11399h(boolean z) {
        m11387b(z);
        m11389c(z);
        if (z) {
            this.f8650d.setPadding(0, 0, 0, (int) C1186y.m8296a(getActivity(), (float) BitmapDescriptorFactory.HUE_YELLOW));
            m11386b().m8483a(C1228q.Choose);
            m11394f().setText(getString(C1205R.string.select_all));
            this.f8653g = false;
            return;
        }
        this.f8650d.setPadding(0, 0, 0, 0);
        m11386b().m8483a(C1228q.Normal);
        m11386b().m8498g();
    }

    public String m11400i() {
        if (!this.f8658l) {
            this.f8649c = m11404m().m8855k().getCurDirFileList();
        }
        if (this.f8649c != null && this.f8649c.size() == 0) {
            return C2915a.f14760f;
        }
        long j = 0;
        int i = 0;
        int i2 = 0;
        for (X11FileInfo x11FileInfo : this.f8649c) {
            int i3 = x11FileInfo.getName().endsWith(X11FileInfo.FILE_TYPE_MP4) ? i2 + 1 : i2;
            if (x11FileInfo.getName().endsWith(Util.PHOTO_DEFAULT_EXT)) {
                i++;
            }
            j = (x11FileInfo.getSize() / FimiMediaMeta.AV_CH_SIDE_RIGHT) + j;
            i2 = i3;
        }
        double freeKBSpace = (double) (((float) m11404m().m8853i().getFreeKBSpace()) / 1048576.0f);
        double totalKBSpace = (double) (((float) m11404m().m8853i().getTotalKBSpace()) / 1048576.0f);
        return String.format(getActivity().getResources().getString(C1205R.string.online_media_detail), new Object[]{i + C2915a.f14760f, i2 + C2915a.f14760f, ab.m8015a(freeKBSpace, 2) + C2915a.f14760f, ab.m8015a(totalKBSpace, 2) + C2915a.f14760f});
    }

    protected ImageButton m11401j() {
        return (ImageButton) m11380o().m11421a().findViewById(C1205R.id.btn_bottomShare);
    }

    protected ImageButton m11402k() {
        return (ImageButton) m11380o().m11421a().findViewById(C1205R.id.btn_bottomDelete);
    }

    protected ImageButton m11403l() {
        return (ImageButton) m11380o().m11421a().findViewById(C1205R.id.btn_bottomDownload);
    }

    protected C1313t m11404m() {
        if (this.f8652f == null) {
            this.f8652f = (C1313t) C1276b.m8680a().m8699d();
        }
        return this.f8652f;
    }

    public void m11405n() {
        this.f8650d.setAdapter(this.f8647a);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8655i = ((DroidPlannerApp) activity.getApplication()).f5570a;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_gridview, null);
        this.f8650d = (MyGridView) inflate.findViewById(C1205R.id.gv_content);
        if (this.f8647a == null) {
            this.f8647a = new C1224m(getActivity(), this.f8650d);
            this.f8647a.m8489a(m11385a());
            this.f8647a.m8485a(this.f8655i);
        }
        this.f8651e = (TextView) inflate.findViewById(C1205R.id.media_detail);
        be.m12359a(m11380o().getAssets(), this.f8651e);
        this.f8650d.setAdapter(this.f8647a);
        this.f8650d.setOnItemLongClickListener(this);
        this.f8650d.setOnItemClickListener(this.f8647a);
        this.f8650d.setOnScrollListener(new C1761a(this));
        this.f8650d.setOnTouchListener(new C1762b(this));
        this.f8647a.m8484a(new C1763c(this));
        m11388c().setOnClickListener(new C1764d(this));
        m11394f().setOnClickListener(new C1765e(this));
        return inflate;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        m11399h(true);
        return false;
    }

    public void onResume() {
        super.onResume();
        if (!m11404m().m8848d()) {
            m11404m().m8875t().m8790b();
        }
        m11404m().m8831a((C1275d) this);
        m11404m().m8832a((C1213e) this);
    }
}
