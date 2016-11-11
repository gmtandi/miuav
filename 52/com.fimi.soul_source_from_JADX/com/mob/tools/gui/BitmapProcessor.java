package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import org.p122a.p123a.C2915a;

public class BitmapProcessor {
    private static final int CAPACITY = 3;
    private static final int MAX_CACHE_SIZE = 50;
    private static final int MAX_CACHE_TIME = 60000;
    private static final int MAX_REQ_TIME = 200;
    private static final int MAX_SIZE = 100;
    private static final int OVERFLOW_SIZE = 120;
    private static BitmapProcessor instance;
    private File cacheDir;
    private CachePool<String, Bitmap> cachePool;
    private ManagerThread manager;
    private int maxReqCount;
    private Vector<ImageReq> netReqTPS;
    private int overflowReqCount;
    private Vector<ImageReq> reqList;
    private int reqTimeout;
    private boolean work;
    private WorkerThread[] workerList;

    public interface BitmapCallback {
        void onImageGot(String str, Bitmap bitmap);
    }

    public class ImageReq {
        private BitmapCallback callback;
        private Bitmap image;
        private long reqTime;
        private String url;
        private WorkerThread worker;

        public ImageReq() {
            this.reqTime = System.currentTimeMillis();
        }

        private void throwComplete(Bitmap bitmap) {
            this.image = bitmap;
            if (this.callback != null) {
                this.callback.onImageGot(this.url, this.image);
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("url=").append(this.url);
            stringBuilder.append("time=").append(this.reqTime);
            stringBuilder.append("worker=").append(this.worker.getName()).append(" (").append(this.worker.getId()).append(C2915a.f14760f);
            return stringBuilder.toString();
        }
    }

    class ManagerThread extends Timer {
        private BitmapProcessor processor;

        /* renamed from: com.mob.tools.gui.BitmapProcessor.ManagerThread.1 */
        class C21621 extends TimerTask {
            private int counter;

            C21621() {
            }

            public void run() {
                if (ManagerThread.this.processor.work) {
                    this.counter--;
                    if (this.counter <= 0) {
                        this.counter = BitmapProcessor.MAX_SIZE;
                        ManagerThread.this.scan();
                    }
                }
            }
        }

        public ManagerThread(BitmapProcessor bitmapProcessor) {
            this.processor = bitmapProcessor;
            schedule(new C21621(), 0, (long) this.processor.reqTimeout);
        }

        private void scan() {
            if (this.processor.cachePool != null) {
                this.processor.cachePool.trimBeforeTime(System.currentTimeMillis() - Util.MILLSECONDS_OF_MINUTE);
            }
            MobLog.getInstance().m737d(">>>> BitmapProcessor.cachePool: " + (this.processor.cachePool == null ? 0 : this.processor.cachePool.size()), new Object[0]);
            MobLog.getInstance().m737d(">>>> BitmapProcessor.reqList: " + (this.processor.reqList == null ? 0 : this.processor.reqList.size()), new Object[0]);
            if (this.processor.work) {
                long currentTimeMillis = System.currentTimeMillis();
                int i = 0;
                while (i < this.processor.workerList.length) {
                    if (this.processor.workerList[i] == null) {
                        this.processor.workerList[i] = new WorkerThread(this.processor);
                        this.processor.workerList[i].setName("worker " + i);
                        this.processor.workerList[i].localType = i == 0;
                        this.processor.workerList[i].start();
                    } else if (currentTimeMillis - this.processor.workerList[i].lastReport > ((long) (this.processor.reqTimeout * BitmapProcessor.MAX_SIZE))) {
                        this.processor.workerList[i].interrupt();
                        boolean access$900 = this.processor.workerList[i].localType;
                        this.processor.workerList[i] = new WorkerThread(this.processor);
                        this.processor.workerList[i].setName("worker " + i);
                        this.processor.workerList[i].localType = access$900;
                        this.processor.workerList[i].start();
                    }
                    i++;
                }
            }
        }
    }

    class PatchInputStream extends FilterInputStream {
        InputStream in;

        protected PatchInputStream(InputStream inputStream) {
            super(inputStream);
            this.in = inputStream;
        }

        public long skip(long j) {
            long j2 = 0;
            while (j2 < j) {
                long skip = this.in.skip(j - j2);
                if (skip == 0) {
                    break;
                }
                j2 += skip;
            }
            return j2;
        }
    }

    class WorkerThread extends Thread {
        private ImageReq curReq;
        private long lastReport;
        private boolean localType;
        private BitmapProcessor processor;

        /* renamed from: com.mob.tools.gui.BitmapProcessor.WorkerThread.1 */
        class C21631 implements RawNetworkCallback {
            final /* synthetic */ File val$file;
            final /* synthetic */ ImageReq val$req;
            final /* synthetic */ boolean val$saveAsPng;

            C21631(File file, boolean z, ImageReq imageReq) {
                this.val$file = file;
                this.val$saveAsPng = z;
                this.val$req = imageReq;
            }

            public void onResponse(InputStream inputStream) {
                Bitmap bitmap = BitmapHelper.getBitmap(new PatchInputStream(inputStream), 1);
                if (bitmap == null || bitmap.isRecycled()) {
                    WorkerThread.this.curReq = null;
                    return;
                }
                WorkerThread.this.saveFile(bitmap, this.val$file, this.val$saveAsPng);
                if (bitmap != null) {
                    WorkerThread.this.processor.cachePool.put(this.val$req.url, bitmap);
                    this.val$req.throwComplete(bitmap);
                }
                WorkerThread.this.curReq = null;
            }
        }

        public WorkerThread(BitmapProcessor bitmapProcessor) {
            this.processor = bitmapProcessor;
            this.lastReport = System.currentTimeMillis();
        }

        private void doLocalTask() {
            ImageReq imageReq = this.processor.reqList.size() > 0 ? (ImageReq) this.processor.reqList.remove(0) : null;
            if (imageReq != null) {
                Bitmap bitmap = (Bitmap) this.processor.cachePool.get(imageReq.url);
                if (bitmap != null) {
                    this.curReq = imageReq;
                    this.curReq.worker = this;
                    imageReq.throwComplete(bitmap);
                } else if (new File(this.processor.cacheDir, Data.MD5(imageReq.url)).exists()) {
                    doTask(imageReq);
                    this.lastReport = System.currentTimeMillis();
                    return;
                } else {
                    if (this.processor.netReqTPS.size() > this.processor.maxReqCount) {
                        while (this.processor.reqList.size() > 0) {
                            this.processor.reqList.remove(0);
                        }
                        this.processor.netReqTPS.remove(0);
                    }
                    this.processor.netReqTPS.add(imageReq);
                }
                this.lastReport = System.currentTimeMillis();
                return;
            }
            this.lastReport = System.currentTimeMillis();
            try {
                Thread.sleep(30);
            } catch (Throwable th) {
            }
        }

        private void doNetworkTask() {
            ImageReq imageReq = null;
            if (this.processor.netReqTPS.size() > 0) {
                imageReq = (ImageReq) this.processor.netReqTPS.remove(0);
            }
            ImageReq imageReq2 = (imageReq != null || this.processor.reqList.size() <= 0) ? imageReq : (ImageReq) this.processor.reqList.remove(0);
            if (imageReq2 != null) {
                Bitmap bitmap = (Bitmap) this.processor.cachePool.get(imageReq2.url);
                if (bitmap != null) {
                    this.curReq = imageReq2;
                    this.curReq.worker = this;
                    imageReq2.throwComplete(bitmap);
                } else {
                    doTask(imageReq2);
                }
                this.lastReport = System.currentTimeMillis();
                return;
            }
            this.lastReport = System.currentTimeMillis();
            try {
                Thread.sleep(30);
            } catch (Throwable th) {
            }
        }

        private void doTask(ImageReq imageReq) {
            Bitmap bitmap;
            this.curReq = imageReq;
            this.curReq.worker = this;
            boolean z = imageReq.url.toLowerCase().endsWith("png") || imageReq.url.toLowerCase().endsWith("gif");
            File file = new File(this.processor.cacheDir, Data.MD5(imageReq.url));
            if (file.exists()) {
                bitmap = BitmapHelper.getBitmap(file.getAbsolutePath());
                if (bitmap != null) {
                    this.processor.cachePool.put(imageReq.url, bitmap);
                    imageReq.throwComplete(bitmap);
                }
                this.curReq = null;
            } else {
                new NetworkHelper().rawGet(imageReq.url, new C21631(file, z, imageReq), null);
                bitmap = null;
            }
            if (bitmap != null) {
                this.processor.cachePool.put(imageReq.url, bitmap);
                imageReq.throwComplete(bitmap);
            }
            this.curReq = null;
        }

        private void saveFile(Bitmap bitmap, File file, boolean z) {
            try {
                if (file.exists()) {
                    file.delete();
                }
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                CompressFormat compressFormat = z ? CompressFormat.PNG : CompressFormat.JPEG;
                OutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(compressFormat, BitmapProcessor.MAX_SIZE, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Throwable th) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }

        public void interrupt() {
            try {
                super.interrupt();
            } catch (Throwable th) {
            }
        }

        public void run() {
            while (this.processor.work) {
                try {
                    if (this.localType) {
                        doLocalTask();
                    } else {
                        doNetworkTask();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m750w(th);
                }
            }
        }
    }

    private BitmapProcessor(Context context, int i, int i2, int i3, float f, int i4) {
        if (i2 <= 0) {
            i2 = MAX_REQ_TIME;
        }
        this.reqTimeout = i2;
        this.maxReqCount = i3 > 0 ? i3 : MAX_SIZE;
        this.overflowReqCount = f > C2020f.f10933c ? (int) (((float) i3) * f) : OVERFLOW_SIZE;
        this.reqList = new Vector();
        this.netReqTPS = new Vector();
        if (i <= 0) {
            i = CAPACITY;
        }
        this.workerList = new WorkerThread[i];
        if (i4 <= 0) {
            i4 = MAX_CACHE_SIZE;
        }
        this.cachePool = new CachePool(i4);
        this.cacheDir = new File(C2178R.getImageCachePath(context));
        this.manager = new ManagerThread(this);
    }

    public static Bitmap getBitmapFromCache(String str) {
        return instance == null ? null : (Bitmap) instance.cachePool.get(str);
    }

    public static synchronized void prepare(Context context) {
        synchronized (BitmapProcessor.class) {
            prepare(context, 0, 0, 0, 0.0f, 0);
        }
    }

    public static synchronized void prepare(Context context, int i, int i2, int i3, float f, int i4) {
        synchronized (BitmapProcessor.class) {
            if (instance == null) {
                instance = new BitmapProcessor(context.getApplicationContext(), i, i2, i3, f, i4);
            }
        }
    }

    public static void process(String str, BitmapCallback bitmapCallback) {
        if (instance != null && str != null) {
            ImageReq imageReq = new ImageReq();
            imageReq.url = str;
            imageReq.callback = bitmapCallback;
            instance.reqList.add(imageReq);
            if (instance.reqList.size() > instance.overflowReqCount) {
                while (instance.reqList.size() > instance.maxReqCount) {
                    instance.reqList.remove(0);
                }
            }
            start();
        }
    }

    public static void start() {
        if (instance == null) {
            throw new RuntimeException("Call BitmapProcessor.prepare(String) before start");
        }
        instance.work = true;
    }

    public static void stop() {
        int i = 0;
        if (instance != null) {
            instance.work = false;
            instance.reqList.clear();
            instance.manager.cancel();
            while (i < instance.workerList.length) {
                if (instance.workerList[i] != null) {
                    instance.workerList[i].interrupt();
                }
                i++;
            }
            instance = null;
        }
    }
}
