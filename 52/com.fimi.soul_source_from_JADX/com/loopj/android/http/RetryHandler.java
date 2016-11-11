package com.loopj.android.http;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import org.p122a.p123a.p152c.p156c.C2955m;
import org.p122a.p123a.p159n.C2967c;

class RetryHandler implements HttpRequestRetryHandler {
    private static final int RETRY_SLEEP_TIME_MILLIS = 1500;
    private static HashSet<Class<?>> exceptionBlacklist;
    private static HashSet<Class<?>> exceptionWhitelist;
    private final int maxRetries;

    static {
        exceptionWhitelist = new HashSet();
        exceptionBlacklist = new HashSet();
        exceptionWhitelist.add(NoHttpResponseException.class);
        exceptionWhitelist.add(UnknownHostException.class);
        exceptionWhitelist.add(SocketException.class);
        exceptionBlacklist.add(InterruptedIOException.class);
        exceptionBlacklist.add(SSLHandshakeException.class);
    }

    public RetryHandler(int i) {
        this.maxRetries = i;
    }

    protected boolean isInList(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z = true;
        Boolean bool = (Boolean) httpContext.getAttribute(C2967c.f14885r);
        boolean z2 = bool != null && bool.booleanValue();
        z2 = i > this.maxRetries ? false : isInList(exceptionBlacklist, iOException) ? false : isInList(exceptionWhitelist, iOException) ? true : !z2 ? true : true;
        if (!z2) {
            z = z2;
        } else if (((HttpUriRequest) httpContext.getAttribute(C2967c.f14882o)).getMethod().equals(C2955m.f14864a)) {
            z = false;
        }
        if (z) {
            SystemClock.sleep(1500);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }
}
