package com.fimi.kernel.p084e;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.fimi.kernel.e.ai */
public class ai {
    public static Boolean m8078a(Callable<Boolean> callable, int i) {
        Boolean bool;
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Boolean valueOf = Boolean.valueOf(false);
        Future submit = newSingleThreadExecutor.submit(callable);
        try {
            bool = (Boolean) submit.get((long) (i * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER), TimeUnit.MILLISECONDS);
            try {
                System.out.println(bool);
                newSingleThreadExecutor.shutdown();
            } catch (InterruptedException e) {
                try {
                    submit.cancel(true);
                    newSingleThreadExecutor.shutdown();
                } catch (Throwable th) {
                    newSingleThreadExecutor.shutdown();
                    return bool;
                }
                return bool;
            } catch (ExecutionException e2) {
                submit.cancel(true);
                newSingleThreadExecutor.shutdown();
                return bool;
            } catch (TimeoutException e3) {
                submit.cancel(true);
                newSingleThreadExecutor.shutdown();
                return bool;
            }
        } catch (InterruptedException e4) {
            bool = valueOf;
            submit.cancel(true);
            newSingleThreadExecutor.shutdown();
            return bool;
        } catch (ExecutionException e5) {
            bool = valueOf;
            submit.cancel(true);
            newSingleThreadExecutor.shutdown();
            return bool;
        } catch (TimeoutException e6) {
            bool = valueOf;
            submit.cancel(true);
            newSingleThreadExecutor.shutdown();
            return bool;
        } catch (Throwable th2) {
            bool = valueOf;
            newSingleThreadExecutor.shutdown();
            return bool;
        }
        return bool;
    }

    public static void m8079a(String[] strArr) {
        ai.m8078a(new aj("\u4f20\u9012\u7684\u53c2\u6570"), 2);
    }
}
