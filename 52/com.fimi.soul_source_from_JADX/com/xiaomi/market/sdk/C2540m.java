package com.xiaomi.market.sdk;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.Context;
import android.database.Cursor;
import android.os.HandlerThread;
import com.fimi.kernel.C1154b;
import com.tencent.mm.sdk.message.RMsgInfo;
import java.io.File;

/* renamed from: com.xiaomi.market.sdk.m */
public class C2540m {
    public static C2540m aJ;
    private C2550w aK;
    private C2544q aL;
    private long aM;
    private File aN;
    private DownloadManager aO;
    private HandlerThread aP;
    private C2541n aQ;
    private Context mContext;

    private C2540m(Context context) {
        this.aM = -1;
        this.mContext = context;
        this.aO = (DownloadManager) this.mContext.getSystemService(C1154b.f5230a);
        this.aP = new HandlerThread("Worker Thread");
        this.aP.start();
        this.aQ = new C2541n(this, this.aP.getLooper());
    }

    public static C2540m m14532i(Context context) {
        if (aJ == null) {
            aJ = new C2540m(context);
        }
        return aJ;
    }

    public void m14533a(long j) {
        if (this.aM <= 0 || this.aM == j) {
            this.aQ.m14540m();
        }
    }

    public void m14534a(C2544q c2544q, C2550w c2550w) {
        if (c2550w != null && c2544q != null) {
            this.aK = c2550w;
            this.aL = c2544q;
            this.aQ.m14539l();
        }
    }

    public boolean m14535a(C2544q c2544q) {
        long j;
        Cursor query;
        int i;
        Cursor query2 = C2545r.m14544j(this.mContext).query(C2539l.aE, C2539l.aI, "package_name=?", new String[]{c2544q.packageName}, null, null, null);
        if (query2 != null) {
            try {
                if (query2.moveToFirst()) {
                    j = query2.getLong(query2.getColumnIndex(C2539l.aF));
                    if (j != -1) {
                        if (query2 != null) {
                            query2.close();
                        }
                        return false;
                    }
                    if (query2 != null) {
                        query2.close();
                    }
                    Query query3 = new Query();
                    query3.setFilterById(new long[]{j});
                    query = this.aO.query(query3);
                    i = -1;
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                i = query.getInt(query.getColumnIndexOrThrow(RMsgInfo.COL_STATUS));
                            }
                        } catch (Throwable th) {
                            if (query != null) {
                                query.close();
                            }
                        }
                    }
                    if (i != 4 || i == 1 || i == 2) {
                        if (query != null) {
                            query.close();
                        }
                        return true;
                    }
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
            } catch (Throwable th2) {
                if (query2 != null) {
                    query2.close();
                }
            }
        }
        j = -1;
        if (j != -1) {
            if (query2 != null) {
                query2.close();
            }
            Query query32 = new Query();
            query32.setFilterById(new long[]{j});
            query = this.aO.query(query32);
            i = -1;
            if (query != null) {
                if (query.moveToFirst()) {
                    i = query.getInt(query.getColumnIndexOrThrow(RMsgInfo.COL_STATUS));
                }
            }
            if (i != 4) {
            }
            if (query != null) {
                query.close();
            }
            return true;
        }
        if (query2 != null) {
            query2.close();
        }
        return false;
    }
}
