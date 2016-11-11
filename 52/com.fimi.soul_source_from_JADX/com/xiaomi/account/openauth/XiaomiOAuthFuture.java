package com.xiaomi.account.openauth;

import java.util.concurrent.TimeUnit;

public interface XiaomiOAuthFuture<V> {
    V getResult();

    V getResult(long j, TimeUnit timeUnit);

    void set(V v);

    void setException(Throwable th);
}
