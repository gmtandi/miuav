package com.xiaomi.market.sdk;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* renamed from: com.xiaomi.market.sdk.p */
class C2543p implements Runnable {
    final /* synthetic */ C2541n aS;

    C2543p(C2541n c2541n) {
        this.aS = c2541n;
    }

    public void run() {
        Cursor query;
        Throwable th;
        if (this.aS.aR.aL == null || this.aS.aR.aK == null || this.aS.aR.aN == null) {
            this.aS.aR.aL = XiaomiUpdateAgent.m14466p(this.aS.aR.mContext);
            if (this.aS.aR.aL != null) {
                try {
                    query = C2545r.m14544j(this.aS.aR.mContext).query(C2539l.aE, C2539l.aI, "package_name=?", new String[]{this.aS.aR.aL.packageName}, null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                this.aS.aR.aM = query.getLong(query.getColumnIndex(C2539l.aF));
                                C2550w c2550w = new C2550w();
                                c2550w.versionCode = query.getInt(query.getColumnIndex(C2539l.aw));
                                c2550w.bl = query.getString(query.getColumnIndex(C2539l.ay));
                                c2550w.bm = query.getString(query.getColumnIndex(C2539l.az));
                                c2550w.bo = query.getString(query.getColumnIndex(C2539l.aB));
                                c2550w.bp = query.getString(query.getColumnIndex(C2539l.aC));
                                this.aS.aR.aK = c2550w;
                                Object string = query.getString(query.getColumnIndex(C2539l.aG));
                                if (!TextUtils.isEmpty(string)) {
                                    this.aS.aR.aN = new File(string);
                                    if (query != null) {
                                        query.close();
                                    }
                                } else if (query != null) {
                                    query.close();
                                    return;
                                } else {
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                    if (query != null) {
                        query.close();
                        return;
                    }
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            return;
        }
        String a = !TextUtils.isEmpty(this.aS.aR.aK.bo) ? this.aS.m14538e(this.aS.aR.aN.getAbsolutePath(), this.aS.aR.aK.bp) : this.aS.aR.aN.getAbsolutePath();
        if (TextUtils.isEmpty(this.aS.aR.aK.bm) || TextUtils.isEmpty(a) || TextUtils.equals(C2529b.m14486a(new File(a)), this.aS.aR.aK.bm)) {
            Uri parse = Uri.parse("file://" + a);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(parse, "application/vnd.android.package-archive");
            intent.setFlags(268435456);
            this.aS.aR.mContext.startActivity(intent);
        }
    }
}
