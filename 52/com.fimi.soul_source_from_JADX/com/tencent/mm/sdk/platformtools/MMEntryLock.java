package com.tencent.mm.sdk.platformtools;

import java.util.HashSet;
import java.util.Set;

public final class MMEntryLock {
    private static Set<String> aF;

    static {
        aF = new HashSet();
    }

    private MMEntryLock() {
    }

    public static boolean isLocked(String str) {
        return aF.contains(str);
    }

    public static boolean lock(String str) {
        if (isLocked(str)) {
            Log.m13539d("MicroMsg.MMEntryLock", "locked-" + str);
            return false;
        }
        Log.m13539d("MicroMsg.MMEntryLock", "lock-" + str);
        return aF.add(str);
    }

    public static void unlock(String str) {
        aF.remove(str);
        Log.m13539d("MicroMsg.MMEntryLock", "unlock-" + str);
    }
}
