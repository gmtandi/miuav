package com.fimi.soul.biz.camera;

import android.os.AsyncTask;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.google.gson.internal.LinkedTreeMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.fimi.soul.biz.camera.q */
class C1310q extends AsyncTask<String, Void, X11RespCmd> {
    final /* synthetic */ C1299f f5841a;

    C1310q(C1299f c1299f) {
        this.f5841a = c1299f;
    }

    protected X11RespCmd m8868a(String... strArr) {
        X11RespCmd b = this.f5841a.m8817b(strArr[0]);
        if (b != null && b.getMsg_id() == C1314u.f5864Q) {
            Map fileList = this.f5841a.f5811m.getFileList();
            if (b.getListing() != null) {
                try {
                    List<LinkedTreeMap> list = (List) b.getListing();
                    Map hashMap = new HashMap();
                    for (LinkedTreeMap linkedTreeMap : list) {
                        for (String str : linkedTreeMap.keySet()) {
                            hashMap.put(str, linkedTreeMap.get(str));
                        }
                    }
                    List a = this.f5841a.m8806a(hashMap);
                    if (a != null) {
                        fileList.put(this.f5841a.f5811m.getCurPath(), a);
                    }
                } catch (Exception e) {
                }
                this.f5841a.m8839a(false);
            }
        }
        return b;
    }

    protected void m8869a(X11RespCmd x11RespCmd) {
        this.f5841a.m8808a(x11RespCmd);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8868a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8869a((X11RespCmd) obj);
    }
}
