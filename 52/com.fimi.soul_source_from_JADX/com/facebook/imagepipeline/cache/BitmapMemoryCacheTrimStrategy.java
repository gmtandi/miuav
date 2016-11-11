package com.facebook.imagepipeline.cache;

import android.os.Build.VERSION;
import com.amap.api.maps.model.WeightedLatLng;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.CountingMemoryCache.CacheTrimStrategy;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class BitmapMemoryCacheTrimStrategy implements CacheTrimStrategy {
    private static final String TAG = "BitmapMemoryCacheTrimStrategy";

    /* renamed from: com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy.1 */
    /* synthetic */ class C10061 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$memory$MemoryTrimType;

        static {
            $SwitchMap$com$facebook$common$memory$MemoryTrimType = new int[MemoryTrimType.values().length];
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnCloseToDalvikHeapLimit.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnAppBackgrounded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public double getTrimRatio(MemoryTrimType memoryTrimType) {
        switch (C10061.$SwitchMap$com$facebook$common$memory$MemoryTrimType[memoryTrimType.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return VERSION.SDK_INT >= 21 ? MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() : 0.0d;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return WeightedLatLng.DEFAULT_INTENSITY;
            default:
                FLog.wtf(TAG, "unknown trim type: %s", memoryTrimType);
                return 0.0d;
        }
    }
}
