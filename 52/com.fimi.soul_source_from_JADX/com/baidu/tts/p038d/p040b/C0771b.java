package com.baidu.tts.p038d.p040b;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.database.C0782a;
import com.baidu.tts.p038d.p039a.C0765e;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.MD5;
import com.baidu.tts.tools.StringTool;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.baidu.tts.d.b.b */
public class C0771b {
    private String f4283a;
    private long f4284b;
    private String f4285c;
    private volatile int f4286d;
    private volatile int f4287e;
    private String f4288f;
    private C0765e f4289g;
    private CopyOnWriteArraySet<String> f4290h;

    public C0771b(String str) {
        this.f4286d = 0;
        this.f4287e = 9;
        this.f4290h = new CopyOnWriteArraySet();
        this.f4283a = str;
    }

    private void m6625j() {
        if (this.f4289g != null) {
            LoggerProxy.m6515d("FsFileInfoFlyweight", "unregisterObserver stop");
            this.f4289g.m6599a();
            this.f4289g = null;
            if (this.f4286d == 4 || this.f4286d == 5) {
                this.f4286d = 8;
                this.f4287e = 8;
                C0770a.m6614a().m6620b().m6725a(this.f4283a, this.f4287e);
            }
        }
    }

    public int m6626a(C0772c c0772c) {
        File file = new File(this.f4283a);
        if (file.exists()) {
            this.f4284b = file.length();
            if (this.f4284b == Long.parseLong(c0772c.m6647b())) {
                this.f4285c = MD5.getInstance().getBigFileMd5(file);
                if (c0772c.m6648c().equalsIgnoreCase(this.f4285c)) {
                    this.f4286d = 7;
                } else {
                    this.f4286d = 3;
                }
            } else {
                this.f4286d = 2;
            }
        } else {
            this.f4286d = 1;
        }
        return this.f4286d;
    }

    public long m6627a() {
        return this.f4284b;
    }

    public void m6628a(long j, long j2) {
        C0770a a = C0770a.m6614a();
        this.f4284b = j;
        if (this.f4290h != null) {
            Iterator it = this.f4290h.iterator();
            while (it.hasNext()) {
                a.m6619b((String) it.next()).m6654a(this);
            }
        }
    }

    public void m6629a(TtsError ttsError) {
        LoggerProxy.m6515d("FsFileInfoFlyweight", "onDownloadFailure");
        this.f4286d = 8;
        this.f4287e = 8;
        C0770a a = C0770a.m6614a();
        a.m6620b().m6725a(this.f4283a, this.f4287e);
        if (this.f4290h != null) {
            Iterator it = this.f4290h.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                a.m6619b(str).m6655a(this, ttsError);
                this.f4290h.remove(str);
            }
        }
    }

    public void m6630a(C0765e c0765e) {
        this.f4289g = c0765e;
    }

    public void m6631a(String str) {
        if (this.f4290h != null) {
            this.f4290h.add(str);
        }
    }

    public boolean m6632a(C0782a c0782a) {
        C0772c b = C0774e.m6668a().m6672b(this.f4288f);
        if (this.f4286d == 0) {
            m6626a(b);
            m6633b(c0782a);
        } else if (this.f4286d == 8 || this.f4286d == 7) {
            m6626a(b);
        }
        if (this.f4286d == 7 && this.f4287e != 7) {
            this.f4287e = 7;
            c0782a.m6725a(this.f4283a, this.f4287e);
        }
        LoggerProxy.m6515d("FsFileInfoFlyweight", "fileId=" + this.f4288f + "--filestate=" + this.f4286d + "--dbstate=" + this.f4287e);
        return (this.f4286d == 4 || this.f4286d == 5 || this.f4286d == 7) ? false : true;
    }

    public int m6633b(C0782a c0782a) {
        String mapValue = DataTool.getMapValue(c0782a.m6728c(this.f4283a), C0794g.STATE.m6742b());
        if (StringTool.isEmpty(mapValue)) {
            this.f4287e = 9;
        } else {
            this.f4287e = Integer.parseInt(mapValue);
        }
        return this.f4287e;
    }

    public String m6634b() {
        return this.f4283a;
    }

    public void m6635b(String str) {
        boolean isSetEmpty = DataTool.isSetEmpty(this.f4290h);
        LoggerProxy.m6515d("FsFileInfoFlyweight", "unregisterObserver 1isEmpty=" + isSetEmpty);
        if (!isSetEmpty) {
            this.f4290h.remove(str);
            isSetEmpty = DataTool.isSetEmpty(this.f4290h);
            LoggerProxy.m6515d("FsFileInfoFlyweight", "unregisterObserver 2isEmpty=" + isSetEmpty);
            if (isSetEmpty) {
                m6625j();
            }
        }
    }

    public String m6636c() {
        return this.f4288f;
    }

    public void m6637c(String str) {
        this.f4288f = str;
    }

    public int m6638d() {
        return this.f4286d;
    }

    public boolean m6639e() {
        if (this.f4286d == 7 || this.f4286d == 4 || this.f4286d == 5) {
            return false;
        }
        return this.f4284b >= Long.parseLong(C0774e.m6668a().m6672b(this.f4288f).m6647b());
    }

    public boolean m6640f() {
        return new File(this.f4283a).delete();
    }

    public void m6641g() {
        LoggerProxy.m6515d("FsFileInfoFlyweight", "queueForDownload fileId=" + this.f4288f + "--filestate=" + this.f4286d);
        this.f4286d = 4;
    }

    public void m6642h() {
        this.f4286d = 5;
        this.f4287e = 6;
        C0770a.m6614a().m6620b().m6725a(this.f4283a, this.f4287e);
    }

    public void m6643i() {
        LoggerProxy.m6515d("FsFileInfoFlyweight", "onDownloadSuccess");
        this.f4286d = 7;
        this.f4287e = 7;
        C0770a a = C0770a.m6614a();
        a.m6620b().m6725a(this.f4283a, this.f4287e);
        if (this.f4290h != null) {
            Iterator it = this.f4290h.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                a.m6619b(str).m6659b(this);
                this.f4290h.remove(str);
            }
        }
    }
}
