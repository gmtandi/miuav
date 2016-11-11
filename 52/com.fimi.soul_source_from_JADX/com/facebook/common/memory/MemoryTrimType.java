package com.facebook.common.memory;

import com.amap.api.maps.model.WeightedLatLng;

public enum MemoryTrimType {
    OnCloseToDalvikHeapLimit(0.5d),
    OnSystemLowMemoryWhileAppInForeground(0.5d),
    OnSystemLowMemoryWhileAppInBackground(WeightedLatLng.DEFAULT_INTENSITY),
    OnAppBackgrounded(WeightedLatLng.DEFAULT_INTENSITY);
    
    private double mSuggestedTrimRatio;

    private MemoryTrimType(double d) {
        this.mSuggestedTrimRatio = d;
    }

    public double getSuggestedTrimRatio() {
        return this.mSuggestedTrimRatio;
    }
}
