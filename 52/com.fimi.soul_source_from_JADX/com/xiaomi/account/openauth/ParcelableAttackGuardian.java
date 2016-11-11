package com.xiaomi.account.openauth;

import android.app.Activity;
import android.content.Intent;
import android.os.BadParcelableException;
import android.util.Log;
import org.p122a.p123a.C2915a;

class ParcelableAttackGuardian {
    private static final String TAG;

    static {
        TAG = ParcelableAttackGuardian.class.getSimpleName();
    }

    ParcelableAttackGuardian() {
    }

    public boolean safeCheck(Activity activity) {
        if (activity == null || activity.getIntent() == null) {
            return true;
        }
        try {
            unparcelIntent(new Intent(activity.getIntent()));
            return true;
        } catch (BadParcelableException e) {
            Log.w(TAG, "fail checking ParcelableAttack for Activity " + activity.getClass().getName());
            return false;
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof ClassNotFoundException)) {
                return true;
            }
            Log.w(TAG, "fail checking SerializableAttack for Activity " + activity.getClass().getName());
            return false;
        }
    }

    void unparcelIntent(Intent intent) {
        intent.getStringExtra(C2915a.f14760f);
    }
}
