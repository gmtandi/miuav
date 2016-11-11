package p000a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: a.d */
class C0005d implements Executor {
    private C0005d() {
    }

    public void execute(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
