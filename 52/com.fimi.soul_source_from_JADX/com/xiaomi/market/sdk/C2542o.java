package com.xiaomi.market.sdk;

import android.app.DownloadManager.Request;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;
import java.io.File;

/* renamed from: com.xiaomi.market.sdk.o */
class C2542o implements Runnable {
    final /* synthetic */ C2541n aS;

    C2542o(C2541n c2541n) {
        this.aS = c2541n;
    }

    public void run() {
        if (!C2546s.m14549f(true)) {
            Toast.makeText(this.aS.aR.mContext, C2546s.m14548b(this.aS.aR.mContext.getPackageName(), "string", "xiaomi_external_storage_unavailable"), 0).show();
        } else if (this.aS.aR.aO != null) {
            Uri parse = Uri.parse(TextUtils.isEmpty(this.aS.aR.aK.bo) ? C2530c.m14501c(this.aS.aR.aK.bi, this.aS.aR.aK.bl) : C2530c.m14501c(this.aS.aR.aK.bi, this.aS.aR.aK.bo));
            File file = new File(new StringBuilder(String.valueOf(this.aS.aR.mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath())).append("/updates").toString());
            if (!(file == null || file.exists())) {
                file.mkdirs();
            }
            this.aS.aR.aN = new File(file.getAbsolutePath() + "/" + this.aS.aR.aL.packageName + this.aS.aR.aK.versionCode);
            if (this.aS.aR.aN.exists()) {
                this.aS.aR.aN.delete();
            }
            Uri parse2 = Uri.parse("file://" + this.aS.aR.aN.getAbsolutePath());
            Request request = new Request(parse);
            request.setMimeType("application/apk-ota");
            request.setTitle(this.aS.aR.aL.aT);
            request.setDestinationUri(parse2);
            this.aS.aR.aM = this.aS.aR.aO.enqueue(request);
            ContentValues contentValues = new ContentValues();
            contentValues.put(C2539l.PACKAGE_NAME, this.aS.aR.aL.packageName);
            contentValues.put(C2539l.aF, Long.valueOf(this.aS.aR.aM));
            contentValues.put(C2539l.aw, Integer.valueOf(this.aS.aR.aK.versionCode));
            contentValues.put(C2539l.ay, this.aS.aR.aK.bl);
            contentValues.put(C2539l.az, this.aS.aR.aK.bm);
            contentValues.put(C2539l.aB, this.aS.aR.aK.bo);
            contentValues.put(C2539l.aC, this.aS.aR.aK.bp);
            contentValues.put(C2539l.aG, this.aS.aR.aN.getAbsolutePath());
            C2545r.m14544j(this.aS.aR.mContext).m14545a(contentValues);
        }
    }
}
