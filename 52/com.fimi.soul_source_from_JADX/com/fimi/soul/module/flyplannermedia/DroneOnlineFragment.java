package com.fimi.soul.module.flyplannermedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.kernel.p076b.p078b.C1126n;
import com.fimi.kernel.p076b.p078b.C1127o;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1297d;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.biz.camera.entity.X11DeviceInfo;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd.NotificationType;
import com.fimi.soul.biz.camera.p094c.C1290m;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.media.player.FermiPlayerUtils;
import com.fimi.soul.utils.C1969i;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.util.BufferRecycler;
import org.p122a.p123a.C2915a;

public class DroneOnlineFragment extends DroneBaseMediaFragment implements C1126n, C1127o {
    private static final int f8680k = 153;
    Handler f8681d;
    private int f8682e;
    private int f8683f;
    private C1775o<X11FileInfo> f8684g;
    private List<WifiDistanceFile> f8685h;
    private List<X11FileInfo> f8686i;
    private X11DeviceInfo f8687j;
    private boolean f8688l;
    private boolean f8689m;
    private boolean f8690n;

    public DroneOnlineFragment() {
        this.f8682e = 0;
        this.f8686i = new ArrayList();
        this.f8688l = false;
        this.f8689m = false;
        this.f8690n = false;
        this.f8681d = new C1783w(this);
        m11384a(true);
        this.f8690n = false;
    }

    private void m11429a(X11FileInfo x11FileInfo, String str) {
        WifiDistanceFile wifiDistanceFile = new WifiDistanceFile(x11FileInfo.getName());
        wifiDistanceFile.setLocalThumbnailPath(str);
        wifiDistanceFile.setDateString(x11FileInfo.getCreateDate());
        wifiDistanceFile.setSize(x11FileInfo.getSize());
        wifiDistanceFile.setRealPath(x11FileInfo.getRemotePath());
        wifiDistanceFile.setAbsolutePath(x11FileInfo.getAbsolutePath());
        wifiDistanceFile.setRemoteUrl(x11FileInfo.getHttpUrl());
        if (wifiDistanceFile.getType() == 2) {
            wifiDistanceFile.setRemoteThmUrl(x11FileInfo.getHttpThmUrl());
        }
        m11386b().m8486a(wifiDistanceFile);
        m11397g(false);
    }

    private void m11434b(List<X11FileInfo> list) {
        if (list == null || list.size() <= this.f8682e) {
            m11391d(true);
            m11404m().m8839a(true);
        } else if (list == null || list.size() <= 0) {
            m11391d(true);
        } else {
            this.f8684g = new C1775o(list);
            this.f8684g.m11454a(new C1782v(this));
            this.f8684g.m11455b();
            m11391d(false);
            m11397g(false);
        }
    }

    private void m11439p() {
        m11403l().setOnClickListener(new C1778r(this));
        m11402k().setOnClickListener(new C1779s(this));
    }

    private void m11440q() {
        List<WifiDistanceFile> c = m11386b().m8493c();
        C1122k a = C1122k.m7798a(getActivity());
        a.m7807a((C1127o) this);
        a.m7806a((C1126n) this);
        if (c != null && c.size() > 0) {
            m11399h(false);
            for (WifiDistanceFile wifiDistanceFile : c) {
                String a2 = C1276b.m8683a(wifiDistanceFile.getName(), wifiDistanceFile.getDateString());
                String str = C2915a.f14760f;
                if (wifiDistanceFile.getType() == 2) {
                    str = wifiDistanceFile.getDurationString();
                }
                a.m7808a(wifiDistanceFile.getRemoteUrl(), str, wifiDistanceFile.getSize(), Boolean.valueOf(false), C1969i.m12478b(a2));
            }
        }
        ak.m8085a(getActivity(), getActivity().getString(C1205R.string.media_success_add, new Object[]{Integer.valueOf(c.size())}), (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
    }

    public void m11441a(Intent intent) {
        if (intent != null) {
            WifiDistanceFile wifiDistanceFile = (WifiDistanceFile) intent.getSerializableExtra(C1236a.f5591O);
            if (!(wifiDistanceFile == null || m11386b() == null)) {
                m11386b().m8494c(wifiDistanceFile);
                if (m11386b().getCount() == 0) {
                    m11391d(true);
                }
            }
            if (this.f8682e > 0) {
                this.f8682e--;
            }
            m11382a(wifiDistanceFile);
        }
    }

    void m11442a(GridView gridView) {
        if (m11386b().m8495d().size() == this.f8682e && this.f8684g != null && this.f8684g.m11457d()) {
            this.f8684g.m11455b();
            m11386b().notifyDataSetChanged();
        }
    }

    public void m11443a(C1113b c1113b, int i) {
    }

    public void m11444a(String str, String str2) {
        try {
            if (C1276b.m8694f(str2) == C1297d.Thumbnail) {
                List curDirFileList = m11404m().m8855k().getCurDirFileList();
                if (this.f8682e - 1 < curDirFileList.size()) {
                    m11429a((X11FileInfo) curDirFileList.get(this.f8682e - 1), String.format("file://%s%s", new Object[]{str, str2}));
                    if (!this.f8684g.m11455b()) {
                    }
                }
            }
        } catch (Exception e) {
            Log.d("Good", e + C2915a.f14760f);
        }
    }

    public void m11445a(List<C1113b> list, boolean z, C1113b c1113b) {
    }

    public void m11446a(boolean z, X11RespCmd x11RespCmd) {
        if (C2915a.f14760f.equalsIgnoreCase(x11RespCmd.getErrorMsg())) {
        }
        if (getActivity() != null) {
            if (x11RespCmd.getNotificationType() == NotificationType.CardRemoved) {
                m11391d(true);
                ak.m8084a(getActivity(), getActivity().getString(C1205R.string.tf_removed));
                return;
            } else if (x11RespCmd.getNotificationType() == NotificationType.FmLsEnd) {
                if (z) {
                    m11434b(m11404m().m8855k().getCurDirFileList());
                } else {
                    m11391d(true);
                    m11397g(false);
                }
            }
        }
        switch (x11RespCmd.getMsg_id()) {
            case C2799f.f14258H /*257*/:
                m11404m().m8874s().m8778d();
                return;
            case C1314u.f5853F /*261*/:
                m11404m().m8874s().m8780e();
                return;
            case C1314u.f5861N /*1026*/:
                break;
            case C1314u.f5863P /*1281*/:
                if (z) {
                    m11404m().m8839a(true);
                    m11386b().m8490b((WifiDistanceFile) this.f8685h.get(this.f8683f));
                    if (this.f8682e > 0) {
                        this.f8682e--;
                    }
                    m11404m().m8855k().getCurDirFileList().remove(this.f8686i.get(this.f8683f));
                    if (((WifiDistanceFile) this.f8685h.get(this.f8683f)).getLocalThumbnailPath() != null) {
                        File file = new File(((WifiDistanceFile) this.f8685h.get(this.f8683f)).getLocalThumbnailPath().replace("file://", C2915a.f14760f));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    this.f8683f++;
                    if (this.f8683f >= this.f8685h.size()) {
                        m11397g(false);
                        ak.m8085a(getActivity(), getString(C1205R.string.del_success), ak.f5302b);
                        m11383a(this.f8685h);
                        m11404m().m8873r().m8749c(C1314u.cq);
                        if (m11386b().getCount() < 12 && this.f8684g != null && this.f8684g.m11457d() && !this.f8684g.m11455b() && m11386b().getCount() == 0) {
                            m11391d(true);
                        }
                        m11386b().notifyDataSetChanged();
                        break;
                    }
                    m11404m().m8874s().m8777c(((WifiDistanceFile) this.f8685h.get(this.f8683f)).getAbsolutePath());
                    break;
                }
                m11397g(false);
                ak.m8085a(getActivity(), getString(C1205R.string.del_fail), (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                return;
            case C1314u.f5864Q /*1282*/:
                m11397g(false);
                this.f8682e = 0;
                m11434b(m11404m().m8855k().getCurDirFileList());
                return;
            case C1314u.f5865R /*1283*/:
                m11404m().m8874s().m8782f();
                return;
            case C1314u.ah /*8947848*/:
                return;
            case C1314u.ai /*10066328*/:
                X11FileInfo curDownloadFile = m11404m().m8855k().getCurDownloadFile();
                m11404m().m8874s().m8769a(curDownloadFile.getName(), curDownloadFile.getAbsolutePath(), curDownloadFile.getCreateDate(), C1290m.Thumb);
                return;
            default:
                return;
        }
        if (this.f8689m) {
            List curDirFileList = m11404m().m8855k().getCurDirFileList();
            if (this.f8682e - 1 >= 0 && this.f8682e - 1 < curDirFileList.size()) {
                String timelineString;
                curDownloadFile = (X11FileInfo) curDirFileList.get(this.f8682e - 1);
                WifiDistanceFile wifiDistanceFile = new WifiDistanceFile(curDownloadFile.getName());
                if (z) {
                    try {
                        timelineString = FermiPlayerUtils.getTimelineString(Long.parseLong(x11RespCmd.getDuration()) * 1000, "mm:ss");
                    } catch (Exception e) {
                    }
                } else {
                    timelineString = "00:00";
                }
                wifiDistanceFile.setDurationString(timelineString);
                wifiDistanceFile.setDateString(curDownloadFile.getCreateDate());
                wifiDistanceFile.setRemoteUrl(C1276b.m8682a(curDownloadFile.getRemotePath()));
                wifiDistanceFile.setSize(curDownloadFile.getSize());
                wifiDistanceFile.setRealPath(curDownloadFile.getRemotePath());
                wifiDistanceFile.setAbsolutePath(curDownloadFile.getAbsolutePath());
                int indexOf = curDownloadFile.getRemotePath().indexOf(".");
                if (indexOf > 0 && wifiDistanceFile.getType() == 2) {
                    wifiDistanceFile.setRemoteThmUrl(C1276b.m8682a(curDownloadFile.getRemotePath().substring(0, indexOf) + "_THM.MP4"));
                }
                m11386b().m8486a(wifiDistanceFile);
                this.f8684g.m11455b();
                m11386b().notifyDataSetChanged();
            }
        }
    }

    public void m11448o() {
        m11399h(false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        m11404m().m8845c(new C1777q(this));
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        m11439p();
        return onCreateView;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (!this.f8688l && m11404m().m8848d() && m11386b().getCount() > 0 && m11386b().getCount() < 12 && m11404m().m8855k().getCurDirFileList().size() > m11386b().getCount()) {
            m11386b().m8499h();
            this.f8682e = 0;
            m11434b(m11404m().m8855k().getCurDirFileList());
        }
        m11386b().notifyDataSetChanged();
    }

    public void onStart() {
        super.onStart();
        m11393e(true);
        m11395f(false);
    }

    public void onStop() {
        super.onStop();
        this.f8688l = false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        m11391d(false);
        if (m11404m().m8852h()) {
            if (m11404m().m8848d() && m11386b().getCount() <= 0) {
                this.f8688l = true;
                m11397g(true);
                m11391d(false);
                if (!m11404m().m8847c() && m11404m().m8855k().getCurDirFileList().size() > 0) {
                    m11434b(m11404m().m8855k().getCurDirFileList());
                } else if (this.f8690n || !m11404m().m8847c()) {
                    m11397g(false);
                    m11391d(true);
                } else {
                    this.f8681d.sendEmptyMessageDelayed(f8680k, 300);
                    m11404m().m8873r().m8749c(C1314u.cr);
                    this.f8690n = true;
                }
            } else if (m11404m().m8848d()) {
                this.f8688l = false;
            } else {
                this.f8688l = true;
                m11391d(true);
            }
            this.f8687j = m11404m().m8853i().getX11DeviceInfo();
            return;
        }
        ak.m8083a(getActivity(), (int) C1205R.string.tf_remove_error, 3000);
        m11391d(true);
    }
}
