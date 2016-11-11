package com.baidu.tts.p038d.p040b;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.DownloadHandler;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.database.C0782a;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.StringTool;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.baidu.tts.d.b.d */
public class C0773d {
    private String f4293a;
    private String f4294b;
    private String f4295c;
    private long f4296d;
    private C0770a f4297e;
    private CopyOnWriteArraySet<DownloadHandler> f4298f;

    public C0773d(String str) {
        this.f4296d = 0;
        this.f4297e = C0770a.m6614a();
        this.f4298f = new CopyOnWriteArraySet();
        this.f4293a = str;
    }

    private void m6649j() {
        this.f4297e.m6618a(this.f4294b, this.f4293a);
        this.f4297e.m6618a(this.f4295c, this.f4293a);
    }

    public void m6650a() {
        this.f4298f.clear();
        m6649j();
    }

    public void m6651a(DownloadHandler downloadHandler) {
        if (this.f4298f != null) {
            this.f4298f.add(downloadHandler);
        }
    }

    public void m6652a(DownloadHandler downloadHandler, TtsError ttsError) {
        downloadHandler.updateFinish(this, ttsError);
        m6658b(downloadHandler);
    }

    public void m6653a(ModelBags modelBags, C0782a c0782a) {
        c0782a.m6723a(modelBags);
        m6656a(c0782a);
    }

    public void m6654a(C0771b c0771b) {
        if (this.f4298f != null) {
            Iterator it = this.f4298f.iterator();
            while (it.hasNext()) {
                ((DownloadHandler) it.next()).updateProgress(this);
            }
        }
    }

    public void m6655a(C0771b c0771b, TtsError ttsError) {
        LoggerProxy.m6515d("ModelFlyweight", "onFileDownloadFailure");
        if (this.f4298f != null) {
            Iterator it = this.f4298f.iterator();
            while (it.hasNext()) {
                m6652a((DownloadHandler) it.next(), ttsError);
            }
        }
    }

    public boolean m6656a(C0782a c0782a) {
        Map e = c0782a.m6730e(this.f4293a);
        if (e == null || e.isEmpty()) {
            return false;
        }
        this.f4294b = DataTool.getMapValue(e, C0794g.TEXT_DATA_ID.m6742b());
        this.f4295c = DataTool.getMapValue(e, C0794g.SPEECH_DATA_ID.m6742b());
        boolean isEmpty = StringTool.isEmpty(this.f4294b);
        boolean isEmpty2 = StringTool.isEmpty(this.f4295c);
        if (!isEmpty && !isEmpty2) {
            return true;
        }
        c0782a.m6718a(this.f4293a);
        return false;
    }

    public Set<String> m6657b() {
        Set<String> hashSet = new HashSet();
        C0774e a = C0774e.m6668a();
        C0772c b = a.m6672b(this.f4294b);
        C0772c b2 = a.m6672b(this.f4295c);
        String a2 = b.m6644a();
        String a3 = b2.m6644a();
        hashSet.add(a2);
        hashSet.add(a3);
        return hashSet;
    }

    public void m6658b(DownloadHandler downloadHandler) {
        boolean isSetEmpty = DataTool.isSetEmpty(this.f4298f);
        LoggerProxy.m6515d("ModelFlyweight", "unregisterListener 1isEmpty=" + isSetEmpty);
        if (!isSetEmpty) {
            this.f4298f.remove(downloadHandler);
            isSetEmpty = DataTool.isSetEmpty(this.f4298f);
            LoggerProxy.m6515d("ModelFlyweight", "unregisterListener 2isEmpty=" + isSetEmpty);
            if (isSetEmpty) {
                m6649j();
                return;
            }
            Iterator it = this.f4298f.iterator();
            while (it.hasNext()) {
                LoggerProxy.m6515d("ModelFlyweight", "unregisterListener item=" + ((DownloadHandler) it.next()));
            }
        }
    }

    public void m6659b(C0771b c0771b) {
        boolean i = m6667i();
        LoggerProxy.m6515d("ModelFlyweight", "onFileDownloadSuccess isAllFileDownloadSuccess=" + i);
        if (i && this.f4298f != null) {
            Iterator it = this.f4298f.iterator();
            while (it.hasNext()) {
                m6652a((DownloadHandler) it.next(), null);
            }
        }
    }

    public long m6660c() {
        m6662d();
        return this.f4296d;
    }

    public void m6661c(DownloadHandler downloadHandler) {
        m6651a(downloadHandler);
        downloadHandler.updateStart(this);
    }

    public void m6662d() {
        if (this.f4296d == 0) {
            m6663e();
        }
    }

    public void m6663e() {
        C0774e a = C0774e.m6668a();
        String b = a.m6672b(this.f4294b).m6647b();
        String b2 = a.m6672b(this.f4295c).m6647b();
        Long valueOf = Long.valueOf(Long.parseLong(b));
        Long valueOf2 = Long.valueOf(Long.parseLong(b2));
        this.f4296d = valueOf2.longValue() + valueOf.longValue();
    }

    public Set<String> m6664f() {
        Set<String> hashSet = new HashSet();
        hashSet.add(this.f4294b);
        hashSet.add(this.f4295c);
        return hashSet;
    }

    public String m6665g() {
        return this.f4293a;
    }

    public long m6666h() {
        return this.f4297e.m6623d(this.f4294b) + this.f4297e.m6623d(this.f4295c);
    }

    public boolean m6667i() {
        return this.f4297e.m6624e(this.f4294b) == 7 && this.f4297e.m6624e(this.f4295c) == 7;
    }
}
