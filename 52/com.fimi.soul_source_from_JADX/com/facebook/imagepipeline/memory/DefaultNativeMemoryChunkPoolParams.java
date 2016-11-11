package com.facebook.imagepipeline.memory;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.SparseIntArray;
import com.p054c.p055a.p072c.C0957d;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public class DefaultNativeMemoryChunkPoolParams {
    private static final int LARGE_BUCKET_LENGTH = 2;
    private static final int SMALL_BUCKET_LENGTH = 5;

    public static PoolParams get() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(SmileConstants.MAX_SHARED_STRING_VALUES, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(Opcodes.ACC_STRICT, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(Opcodes.ACC_SYNTHETIC, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(Opcodes.ACC_ANNOTATION, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(Opcodes.ACC_ENUM, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(C0957d.f5043a, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(AccessibilityNodeInfoCompat.ACTION_CUT, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(Opcodes.ACC_DEPRECATED, SMALL_BUCKET_LENGTH);
        sparseIntArray.put(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START, LARGE_BUCKET_LENGTH);
        sparseIntArray.put(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END, LARGE_BUCKET_LENGTH);
        sparseIntArray.put(Util.BYTE_OF_MB, LARGE_BUCKET_LENGTH);
        return new PoolParams(getMaxSizeSoftCap(), getMaxSizeHardCap(), sparseIntArray);
    }

    private static int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        return min < ViewCompat.MEASURED_STATE_TOO_SMALL ? min / LARGE_BUCKET_LENGTH : (min / 4) * 3;
    }

    private static int getMaxSizeSoftCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        return min < ViewCompat.MEASURED_STATE_TOO_SMALL ? 3145728 : min < 33554432 ? 6291456 : 12582912;
    }
}
