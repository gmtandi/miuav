package com.facebook.imagepipeline.listener;

import android.os.SystemClock;
import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class RequestLoggingListener implements RequestListener {
    private static final String TAG = "RequestLoggingListener";
    @GuardedBy("this")
    private final Map<Pair<String, String>, Long> mProducerStartTimeMap;
    @GuardedBy("this")
    private final Map<String, Long> mRequestStartTimeMap;

    public RequestLoggingListener() {
        this.mProducerStartTimeMap = new HashMap();
        this.mRequestStartTimeMap = new HashMap();
    }

    private static long getElapsedTime(@Nullable Long l, long j) {
        return l != null ? j - l.longValue() : -1;
    }

    private static long getTime() {
        return SystemClock.elapsedRealtime();
    }

    public synchronized void onProducerEvent(String str, String str2, String str3) {
        if (FLog.isLoggable(2)) {
            Long l = (Long) this.mProducerStartTimeMap.get(Pair.create(str, str2));
            long time = getTime();
            FLog.m7631v(TAG, "time %d: onProducerEvent: {requestId: %s, stage: %s, eventName: %s; elapsedTime: %d ms}", Long.valueOf(getTime()), str, str2, str3, Long.valueOf(getElapsedTime(l, time)));
        }
    }

    public synchronized void onProducerFinishWithCancellation(String str, String str2, @Nullable Map<String, String> map) {
        if (FLog.isLoggable(2)) {
            Long l = (Long) this.mProducerStartTimeMap.remove(Pair.create(str, str2));
            long time = getTime();
            FLog.m7631v(TAG, "time %d: onProducerFinishWithCancellation: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(time), str, str2, Long.valueOf(getElapsedTime(l, time)), map);
        }
    }

    public synchronized void onProducerFinishWithFailure(String str, String str2, Throwable th, @Nullable Map<String, String> map) {
        if (FLog.isLoggable(5)) {
            Long l = (Long) this.mProducerStartTimeMap.remove(Pair.create(str, str2));
            long time = getTime();
            FLog.m7639w(TAG, "time %d: onProducerFinishWithFailure: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s, throwable: %s}", Long.valueOf(time), str, str2, Long.valueOf(getElapsedTime(l, time)), map, th.toString());
        }
    }

    public synchronized void onProducerFinishWithSuccess(String str, String str2, @Nullable Map<String, String> map) {
        if (FLog.isLoggable(2)) {
            Long l = (Long) this.mProducerStartTimeMap.remove(Pair.create(str, str2));
            long time = getTime();
            FLog.m7631v(TAG, "time %d: onProducerFinishWithSuccess: {requestId: %s, producer: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(time), str, str2, Long.valueOf(getElapsedTime(l, time)), map);
        }
    }

    public synchronized void onProducerStart(String str, String str2) {
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            long time = getTime();
            this.mProducerStartTimeMap.put(create, Long.valueOf(time));
            FLog.m7628v(TAG, "time %d: onProducerStart: {requestId: %s, producer: %s}", Long.valueOf(time), (Object) str, (Object) str2);
        }
    }

    public synchronized void onRequestCancellation(String str) {
        if (FLog.isLoggable(2)) {
            Long l = (Long) this.mRequestStartTimeMap.remove(str);
            long time = getTime();
            FLog.m7628v(TAG, "time %d: onRequestCancellation: {requestId: %s, elapsedTime: %d ms}", Long.valueOf(time), (Object) str, Long.valueOf(getElapsedTime(l, time)));
        }
    }

    public synchronized void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        if (FLog.isLoggable(5)) {
            Long l = (Long) this.mRequestStartTimeMap.remove(str);
            long time = getTime();
            FLog.m7639w(TAG, "time %d: onRequestFailure: {requestId: %s, elapsedTime: %d ms, throwable: %s}", Long.valueOf(time), str, Long.valueOf(getElapsedTime(l, time)), th.toString());
        }
    }

    public synchronized void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        if (FLog.isLoggable(2)) {
            FLog.m7629v(TAG, "time %d: onRequestSubmit: {requestId: %s, callerContext: %s, isPrefetch: %b}", Long.valueOf(getTime()), (Object) str, obj, Boolean.valueOf(z));
            this.mRequestStartTimeMap.put(str, Long.valueOf(getTime()));
        }
    }

    public synchronized void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        if (FLog.isLoggable(2)) {
            Long l = (Long) this.mRequestStartTimeMap.remove(str);
            long time = getTime();
            FLog.m7628v(TAG, "time %d: onRequestSuccess: {requestId: %s, elapsedTime: %d ms}", Long.valueOf(time), (Object) str, Long.valueOf(getElapsedTime(l, time)));
        }
    }

    public boolean requiresExtraMap(String str) {
        return FLog.isLoggable(2);
    }
}
