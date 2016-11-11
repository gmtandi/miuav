package com.tencent.mm.sdk.platformtools;

import android.os.SystemClock;
import java.util.ArrayList;

public class TimeLogger {
    private String bg;
    private String bh;
    private boolean bi;
    ArrayList<Long> bj;
    ArrayList<String> bk;

    public TimeLogger(String str, String str2) {
        reset(str, str2);
    }

    public void addSplit(String str) {
        if (!this.bi) {
            this.bj.add(Long.valueOf(SystemClock.elapsedRealtime()));
            this.bk.add(str);
        }
    }

    public void dumpToLog() {
        if (!this.bi) {
            Log.m13539d(this.bg, this.bh + ": begin");
            long longValue = ((Long) this.bj.get(0)).longValue();
            int i = 1;
            long j = longValue;
            while (i < this.bj.size()) {
                long longValue2 = ((Long) this.bj.get(i)).longValue();
                Log.m13539d(this.bg, this.bh + ":      " + (longValue2 - ((Long) this.bj.get(i - 1)).longValue()) + " ms, " + ((String) this.bk.get(i)));
                i++;
                j = longValue2;
            }
            Log.m13539d(this.bg, this.bh + ": end, " + (j - longValue) + " ms");
        }
    }

    public void reset() {
        this.bi = false;
        if (!this.bi) {
            if (this.bj == null) {
                this.bj = new ArrayList();
                this.bk = new ArrayList();
            } else {
                this.bj.clear();
                this.bk.clear();
            }
            addSplit(null);
        }
    }

    public void reset(String str, String str2) {
        this.bg = str;
        this.bh = str2;
        reset();
    }
}
