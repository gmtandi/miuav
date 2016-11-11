package com.fimi.soul.module.flyplannermedia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.fimi.kernel.p076b.p078b.C1113b;
import com.fimi.kernel.p076b.p078b.C1122k;
import com.fimi.soul.biz.camera.entity.X11FileInfo;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.entity.X11RespCmd.NotificationType;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.utils.C1969i;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.p122a.p123a.C2915a;

public class DroneLocalFragment extends DroneBaseMediaFragment {
    private C1122k f8659d;
    private List<WifiDistanceFile> f8660e;
    private C1770j f8661f;
    private Handler f8662g;
    private boolean f8663h;

    public DroneLocalFragment() {
        this.f8661f = new C1770j(this);
        this.f8662g = new Handler();
        this.f8663h = false;
        m11384a(false);
    }

    private void m11412p() {
        if (!this.f8663h) {
            this.f8663h = true;
            new Thread(new C1769i(this)).start();
        }
    }

    private List<WifiDistanceFile> m11413q() {
        File file = new File(C1969i.m12492o());
        Object arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (file == null || !file.exists()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        for (Object add : listFiles) {
            arrayList.add(add);
        }
        File file2 = new File(C1969i.m12491n());
        if (file2 != null) {
            listFiles = file2.listFiles();
            if (listFiles != null) {
                for (Object add2 : listFiles) {
                    arrayList2.add(add2);
                }
            }
        }
        Collections.sort(arrayList, new C1784x());
        List<WifiDistanceFile> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            file2 = (File) it.next();
            String name = file2.getName();
            WifiDistanceFile wifiDistanceFile = new WifiDistanceFile(name);
            String format = String.format("file://%s%s", new Object[]{C1969i.m12492o(), name});
            wifiDistanceFile.setLocalThumbnailPath(format);
            wifiDistanceFile.setPath(format);
            C1113b a = this.f8659d.m7803a(getActivity(), name);
            if (a != null) {
                a.m7774a(this.f8662g);
                this.f8659d.m7811c();
                wifiDistanceFile.setDownloadTaskInfo(a);
                wifiDistanceFile.setRemoteUrl(a.m7791m());
                if (name.contains(X11FileInfo.FILE_TYPE_MP4)) {
                    name = name.replace(X11FileInfo.FILE_TYPE_MP4, Util.PHOTO_DEFAULT_EXT);
                    if (a.m7773a() != null) {
                        wifiDistanceFile.setDurationString(a.m7773a());
                    }
                }
                format = name;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    file = (File) it2.next();
                    if (file.getName().contains(format)) {
                        wifiDistanceFile.setLocalDownloadCachePath(file.getPath());
                        break;
                    }
                }
            }
            if (file2.length() > 0) {
                arrayList3.add(wifiDistanceFile);
            }
        }
        return arrayList3;
    }

    public void m11414a(Intent intent) {
        String stringExtra = intent.getStringExtra("del_file");
        if (intent != null && !C2915a.f14760f.equals(stringExtra) && stringExtra != null) {
            m11386b().m8491b(stringExtra);
        }
    }

    void m11415a(GridView gridView) {
    }

    public void m11416a(String str, String str2) {
    }

    public void m11417a(boolean z, X11RespCmd x11RespCmd) {
        if (getActivity() != null) {
            if (x11RespCmd.getNotificationType() == NotificationType.StartingVideoRecord) {
                m11386b().notifyDataSetChanged();
            }
            if (x11RespCmd.getNotificationType() == NotificationType.VideoRecordComplete) {
                m11386b().notifyDataSetChanged();
            }
        }
    }

    public void m11419o() {
        m11399h(false);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f8659d = C1122k.m7798a(getActivity());
        m11402k().setOnClickListener(new C1766f(this));
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        m11393e(false);
        m11395f(true);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        m11397g(true);
        m11391d(false);
        m11412p();
    }
}
