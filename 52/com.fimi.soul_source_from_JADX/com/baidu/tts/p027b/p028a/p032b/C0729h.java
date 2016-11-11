package com.baidu.tts.p027b.p028a.p032b;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.loopj.RequestParams;
import com.baidu.tts.p022a.p023a.C0664a;
import com.baidu.tts.p022a.p023a.C0666b;
import com.baidu.tts.p027b.p028a.p032b.C0727f.C0725b;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p041e.C0785a;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.tools.CommonUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.b.a.b.h */
public class C0729h extends C0728g {
    private C0664a<byte[], byte[]> f4164a;
    private C0725b f4165b;
    private C0828h f4166c;

    public C0729h(C0828h c0828h) {
        this.f4166c = c0828h;
        this.f4164a = new C0664a();
        this.f4164a.m5961a(new C0666b());
        this.f4164a.m5960a();
    }

    private void m6388a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt(C0794g.ERROR_NUMBER.m6741a());
            LoggerProxy.m6515d("TtsResponseHandler", "parseJSON errNo=" + optInt);
            this.f4166c.m6854a(optInt);
            if (optInt != 0) {
                this.f4166c.m6855a(C0807c.m6758a().m6760a(C0802n.ONLINE_ENGINE_REQUEST_RESULT_ERROR, optInt, jSONObject.getString(C0794g.ERROR_MESSAGE.m6741a())));
                return;
            }
            this.f4166c.m6859a(jSONObject.optString(C0794g.SERIAL_NUMBER.m6741a()));
            this.f4166c.m6862b(jSONObject.optInt(C0794g.INDEX.m6741a()));
            this.f4166c.m6864c(jSONObject.optInt(C0794g.PERCENT.m6742b()));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void m6389b(HttpEntity httpEntity) {
        byte[] bytes;
        byte[] bArr = null;
        try {
            bytes = ("--" + "--BD**TTS++LIB").getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            bytes = bArr;
        }
        try {
            bArr = EntityUtils.toByteArray(httpEntity);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        int indexOf = CommonUtility.indexOf(bArr, bytes, 0);
        if (indexOf < 0) {
            this.f4166c.m6855a(C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_HTTP_REQUEST_PARSE_ERROR));
            return;
        }
        int indexOf2 = CommonUtility.indexOf(bArr, bytes, bytes.length + indexOf);
        if (indexOf2 < 0) {
            this.f4166c.m6855a(C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_HTTP_REQUEST_PARSE_ERROR));
            return;
        }
        try {
            m6388a(new String(CommonUtility.copyBytesOfRange(bArr, indexOf + bytes.length, indexOf2), "utf-8"));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        indexOf = CommonUtility.indexOf(bArr, bytes, bytes.length + indexOf2);
        if (indexOf >= 0) {
            this.f4166c.m6860a((byte[]) this.f4164a.m5959a(CommonUtility.copyBytesOfRange(bArr, bytes.length + indexOf2, indexOf)));
            this.f4166c.m6856a(C0785a.PCM);
        }
    }

    private void m6390c(HttpEntity httpEntity) {
        String str = null;
        try {
            str = EntityUtils.toString(httpEntity, C0791d.UTF8.m6737a());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        m6388a(str);
    }

    public void m6391a(int i, Header[] headerArr, String str, HttpEntity httpEntity) {
        if (RequestParams.APPLICATION_JSON.equals(str)) {
            m6390c(httpEntity);
        } else {
            m6389b(httpEntity);
        }
    }

    public void m6392a(int i, Header[] headerArr, String str, HttpEntity httpEntity, Throwable th) {
        LoggerProxy.m6515d("TtsResponseHandler", "onFailure error = " + th.getMessage());
        this.f4166c.m6855a(C0807c.m6758a().m6761a(C0802n.ONLINE_ENGINE_HTTP_REQUEST_FAILURE, i, null, th));
    }

    public void m6393a(C0725b c0725b) {
        this.f4165b = c0725b;
    }
}
