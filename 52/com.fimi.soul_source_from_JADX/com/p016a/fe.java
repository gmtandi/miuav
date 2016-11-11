package com.p016a;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amap.api.fence.Fence;
import com.amap.api.location.AMapLocation;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.fe */
public class fe {
    Context f1192a;
    private Hashtable<PendingIntent, ArrayList<Fence>> f1193b;

    public fe(Context context) {
        this.f1193b = new Hashtable();
        this.f1192a = context;
    }

    private void m1799a(PendingIntent pendingIntent, Fence fence, int i) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("fenceid", fence.f1340b);
        bundle.putInt("event", i);
        intent.putExtras(bundle);
        try {
            pendingIntent.send(this.f1192a, 0, intent);
        } catch (Throwable th) {
            ev.m1777a(th, "FenceManager", "fcIntent");
        }
    }

    private boolean m1800a(PendingIntent pendingIntent, List<String> list) {
        boolean z = false;
        if (!(m1803b() || list == null || list.isEmpty() || !this.f1193b.containsKey(pendingIntent))) {
            Iterator it = ((ArrayList) this.f1193b.get(pendingIntent)).iterator();
            while (it != null && it.hasNext()) {
                boolean z2;
                Fence fence = (Fence) it.next();
                if (list.contains(fence.f1340b) || m1801a(fence)) {
                    it.remove();
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
        }
        return z;
    }

    private boolean m1801a(Fence fence) {
        return fence.m2068b() != -1 && fence.m2068b() <= dn.m1519b();
    }

    private boolean m1802a(List<String> list) {
        if (m1803b() || list == null || list.isEmpty()) {
            return false;
        }
        Iterator it = this.f1193b.entrySet().iterator();
        boolean z = false;
        while (it != null && it.hasNext()) {
            Entry entry = (Entry) it.next();
            Iterator it2 = ((ArrayList) entry.getValue()).iterator();
            while (it2 != null && it2.hasNext()) {
                boolean z2;
                Fence fence = (Fence) it2.next();
                if (list.contains(fence.f1340b) || m1801a(fence)) {
                    it2.remove();
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
            if (((ArrayList) entry.getValue()).isEmpty()) {
                it.remove();
            }
        }
        return z;
    }

    private boolean m1803b() {
        return this.f1193b.isEmpty();
    }

    public void m1804a() {
        this.f1193b.clear();
    }

    public void m1805a(AMapLocation aMapLocation) {
        if (!m1803b()) {
            Iterator it = this.f1193b.entrySet().iterator();
            while (it != null && it.hasNext()) {
                Entry entry = (Entry) it.next();
                Iterator it2 = ((ArrayList) entry.getValue()).iterator();
                while (it2.hasNext()) {
                    Fence fence = (Fence) it2.next();
                    if (!m1801a(fence)) {
                        float a = dn.m1497a(new double[]{fence.f1342d, fence.f1341c, aMapLocation.getLatitude(), aMapLocation.getLongitude()});
                        float accuracy = aMapLocation.getAccuracy();
                        accuracy = accuracy >= 500.0f ? a - (fence.f1343e + 500.0f) : a - (accuracy + fence.f1343e);
                        Object obj = null;
                        if (accuracy > 0.0f) {
                            if (fence.f1345g != 0) {
                                obj = 1;
                            }
                            fence.f1345g = 0;
                        } else {
                            if (fence.f1345g != 1) {
                                obj = 1;
                            }
                            fence.f1345g = 1;
                        }
                        if (obj != null) {
                            switch (fence.f1345g) {
                                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                                    fence.f1346h = -1;
                                    if ((fence.m2066a() & 2) != 2) {
                                        break;
                                    }
                                    m1799a((PendingIntent) entry.getKey(), fence, 2);
                                    break;
                                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                                    fence.f1346h = dn.m1519b();
                                    if ((fence.m2066a() & 1) != 1) {
                                        break;
                                    }
                                    m1799a((PendingIntent) entry.getKey(), fence, 1);
                                    break;
                                default:
                                    break;
                            }
                        } else if ((fence.m2066a() & 4) == 4 && fence.f1346h > 0 && dn.m1519b() - fence.f1346h > fence.m2069c()) {
                            fence.f1346h = dn.m1519b();
                            m1799a((PendingIntent) entry.getKey(), fence, 4);
                        }
                    }
                }
            }
        }
    }

    public boolean m1806a(PendingIntent pendingIntent) {
        if (pendingIntent == null || !this.f1193b.containsKey(pendingIntent)) {
            return false;
        }
        List arrayList = new ArrayList();
        Iterator it = ((ArrayList) this.f1193b.get(pendingIntent)).iterator();
        while (it.hasNext()) {
            arrayList.add(((Fence) it.next()).f1340b);
        }
        return m1802a(arrayList);
    }

    public boolean m1807a(PendingIntent pendingIntent, String str) {
        if (pendingIntent == null || !this.f1193b.containsKey(pendingIntent) || TextUtils.isEmpty(str)) {
            return false;
        }
        List arrayList = new ArrayList();
        arrayList.add(str);
        return m1800a(pendingIntent, arrayList);
    }

    public boolean m1808a(Fence fence, PendingIntent pendingIntent) {
        if (pendingIntent == null || fence == null) {
            return false;
        }
        if (TextUtils.isEmpty(fence.f1340b)) {
            return false;
        }
        if (fence.f1343e < 100.0f) {
            return false;
        }
        if (fence.f1343e > 1000.0f) {
            return false;
        }
        if (!m1803b() && !this.f1193b.containsKey(pendingIntent)) {
            return false;
        }
        if (fence.m2066a() == 0) {
            return false;
        }
        if (fence.m2066a() > 7) {
            return false;
        }
        Iterator it = this.f1193b.entrySet().iterator();
        int i = 0;
        while (it != null && it.hasNext()) {
            i = ((ArrayList) ((Entry) it.next()).getValue()).size() + i;
        }
        if (i > 20) {
            return false;
        }
        fence.f1345g = -1;
        ArrayList arrayList;
        if (m1803b()) {
            arrayList = new ArrayList();
            arrayList.add(fence);
            this.f1193b.put(pendingIntent, arrayList);
        } else {
            arrayList = (ArrayList) this.f1193b.get(pendingIntent);
            Fence fence2 = null;
            it = arrayList.iterator();
            while (it.hasNext()) {
                Fence fence3 = (Fence) it.next();
                if (!fence3.f1340b.equals(fence.f1340b)) {
                    fence3 = fence2;
                }
                fence2 = fence3;
            }
            if (fence2 != null) {
                arrayList.remove(fence2);
            }
            arrayList.add(fence);
            this.f1193b.put(pendingIntent, arrayList);
        }
        return true;
    }
}
