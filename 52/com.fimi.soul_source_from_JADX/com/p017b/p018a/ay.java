package com.p017b.p018a;

import android.os.Handler;
import android.os.Message;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.b.a.ay */
class ay extends Handler {
    private ay() {
    }

    public void handleMessage(Message message) {
        ArrayList arrayList;
        Object obj;
        ArrayList arrayList2;
        int size;
        int i;
        as asVar;
        ArrayList arrayList3 = (ArrayList) as.f3806r.get();
        ArrayList arrayList4 = (ArrayList) as.f3808t.get();
        switch (message.what) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                arrayList = (ArrayList) as.f3807s.get();
                if (arrayList3.size() > 0 || arrayList4.size() > 0) {
                    obj = null;
                } else {
                    int i2 = 1;
                }
                while (arrayList.size() > 0) {
                    arrayList2 = (ArrayList) arrayList.clone();
                    arrayList.clear();
                    size = arrayList2.size();
                    for (i = 0; i < size; i++) {
                        asVar = (as) arrayList2.get(i);
                        if (asVar.f3821H == 0) {
                            asVar.m5515o();
                        } else {
                            arrayList4.add(asVar);
                        }
                    }
                }
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                obj = 1;
                break;
            default:
                return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        arrayList = (ArrayList) as.f3810v.get();
        arrayList2 = (ArrayList) as.f3809u.get();
        size = arrayList4.size();
        for (i = 0; i < size; i++) {
            asVar = (as) arrayList4.get(i);
            if (asVar.m5513g(currentAnimationTimeMillis)) {
                arrayList.add(asVar);
            }
        }
        size = arrayList.size();
        if (size > 0) {
            for (i = 0; i < size; i++) {
                asVar = (as) arrayList.get(i);
                asVar.m5515o();
                asVar.f3818E = true;
                arrayList4.remove(asVar);
            }
            arrayList.clear();
        }
        i = arrayList3.size();
        int i3 = 0;
        while (i3 < i) {
            int i4;
            as asVar2 = (as) arrayList3.get(i3);
            if (asVar2.m5535c(currentAnimationTimeMillis)) {
                arrayList2.add(asVar2);
            }
            if (arrayList3.size() == i) {
                i4 = i3 + 1;
                i3 = i;
            } else {
                i--;
                arrayList2.remove(asVar2);
                i4 = i3;
                i3 = i;
            }
            i = i3;
            i3 = i4;
        }
        if (arrayList2.size() > 0) {
            for (i3 = 0; i3 < arrayList2.size(); i3++) {
                ((as) arrayList2.get(i3)).m5514m();
            }
            arrayList2.clear();
        }
        if (obj == null) {
            return;
        }
        if (!arrayList3.isEmpty() || !arrayList4.isEmpty()) {
            sendEmptyMessageDelayed(1, Math.max(0, as.f3795I - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
        }
    }
}
