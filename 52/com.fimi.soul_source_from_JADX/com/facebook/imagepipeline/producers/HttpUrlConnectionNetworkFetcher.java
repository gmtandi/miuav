package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetcher.Callback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<FetchState> {
    private static final int NUM_NETWORK_THREADS = 3;
    private final ExecutorService mExecutorService;

    /* renamed from: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1 */
    class C10481 implements Runnable {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ FetchState val$fetchState;

        C10481(FetchState fetchState, Callback callback) {
            this.val$fetchState = fetchState;
            this.val$callback = callback;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r4 = 0;
            r0 = r6.val$fetchState;
            r0 = r0.getUri();
            r1 = r0.getScheme();
            r0 = r6.val$fetchState;
            r0 = r0.getUri();
            r0 = r0.toString();
            r2 = r1;
            r1 = r4;
        L_0x0017:
            r3 = new java.net.URL;	 Catch:{ Exception -> 0x0071 }
            r3.<init>(r0);	 Catch:{ Exception -> 0x0071 }
            r0 = r3.openConnection();	 Catch:{ Exception -> 0x0071 }
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0071 }
            r1 = "Location";
            r1 = r0.getHeaderField(r1);	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            if (r1 != 0) goto L_0x0043;
        L_0x002a:
            r3 = r4;
        L_0x002b:
            if (r1 == 0) goto L_0x0033;
        L_0x002d:
            r2 = r3.equals(r2);	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            if (r2 == 0) goto L_0x004c;
        L_0x0033:
            r1 = r0.getInputStream();	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            r2 = r6.val$callback;	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            r3 = -1;
            r2.onResponse(r1, r3);	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            if (r0 == 0) goto L_0x0042;
        L_0x003f:
            r0.disconnect();
        L_0x0042:
            return;
        L_0x0043:
            r3 = android.net.Uri.parse(r1);	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            r3 = r3.getScheme();	 Catch:{ Exception -> 0x0056, all -> 0x0065 }
            goto L_0x002b;
        L_0x004c:
            if (r0 == 0) goto L_0x0051;
        L_0x004e:
            r0.disconnect();
        L_0x0051:
            r2 = r3;
            r5 = r1;
            r1 = r0;
            r0 = r5;
            goto L_0x0017;
        L_0x0056:
            r1 = move-exception;
            r5 = r1;
            r1 = r0;
            r0 = r5;
        L_0x005a:
            r2 = r6.val$callback;	 Catch:{ all -> 0x006f }
            r2.onFailure(r0);	 Catch:{ all -> 0x006f }
            if (r1 == 0) goto L_0x0042;
        L_0x0061:
            r1.disconnect();
            goto L_0x0042;
        L_0x0065:
            r1 = move-exception;
            r5 = r1;
            r1 = r0;
            r0 = r5;
        L_0x0069:
            if (r1 == 0) goto L_0x006e;
        L_0x006b:
            r1.disconnect();
        L_0x006e:
            throw r0;
        L_0x006f:
            r0 = move-exception;
            goto L_0x0069;
        L_0x0071:
            r0 = move-exception;
            goto L_0x005a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1.run():void");
        }
    }

    /* renamed from: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.2 */
    class C10492 extends BaseProducerContextCallbacks {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ Future val$future;

        C10492(Future future, Callback callback) {
            this.val$future = future;
            this.val$callback = callback;
        }

        public void onCancellationRequested() {
            if (this.val$future.cancel(false)) {
                this.val$callback.onCancellation();
            }
        }
    }

    public HttpUrlConnectionNetworkFetcher() {
        this.mExecutorService = Executors.newFixedThreadPool(NUM_NETWORK_THREADS);
    }

    public FetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new FetchState(consumer, producerContext);
    }

    public void fetch(FetchState fetchState, Callback callback) {
        fetchState.getContext().addCallbacks(new C10492(this.mExecutorService.submit(new C10481(fetchState, callback)), callback));
    }
}
