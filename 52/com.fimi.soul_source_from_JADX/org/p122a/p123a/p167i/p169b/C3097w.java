package org.p122a.p123a.p167i.p169b;

import java.util.concurrent.FutureTask;
import org.apache.http.client.methods.HttpUriRequest;

/* renamed from: org.a.a.i.b.w */
public class C3097w<V> extends FutureTask<V> {
    private final HttpUriRequest f15341a;
    private final C3098x<V> f15342b;

    public C3097w(HttpUriRequest httpUriRequest, C3098x<V> c3098x) {
        super(c3098x);
        this.f15341a = httpUriRequest;
        this.f15342b = c3098x;
    }

    public long m17459a() {
        return this.f15342b.m17464a();
    }

    public long m17460b() {
        return this.f15342b.m17465b();
    }

    public long m17461c() {
        if (isDone()) {
            return this.f15342b.m17466c();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public boolean cancel(boolean z) {
        this.f15342b.m17467d();
        if (z) {
            this.f15341a.abort();
        }
        return super.cancel(z);
    }

    public long m17462d() {
        if (isDone()) {
            return m17461c() - m17460b();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long m17463e() {
        if (isDone()) {
            return m17461c() - m17459a();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public String toString() {
        return this.f15341a.getRequestLine().getUri();
    }
}
