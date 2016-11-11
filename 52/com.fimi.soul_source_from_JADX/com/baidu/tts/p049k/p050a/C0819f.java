package com.baidu.tts.p049k.p050a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.loopj.RequestHandle;
import com.baidu.tts.loopj.RequestParams;
import com.baidu.tts.loopj.SyncHttpClient;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0803o;
import com.baidu.tts.tools.JsonTool;
import com.tencent.connect.common.Constants;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.k.a.f */
public class C0819f implements Callable<ModelFileBags> {
    private RequestHandle f4584a;
    private Set<String> f4585b;

    public C0819f(Set<String> set) {
        this.f4585b = set;
    }

    private StringEntity m6807b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(C0794g.FUNCTION.m6741a(), "getURL");
            jSONObject.put(C0794g.IVERSION.m6742b(), Constants.VIA_TO_TYPE_QQ_GROUP);
            jSONObject.put(C0794g.ID.m6742b(), JsonTool.fromSetToJson(this.f4585b));
            String jSONObject2 = jSONObject.toString();
            LoggerProxy.m6515d("GetServerModelFileInfosWork", "geturl params=" + jSONObject2);
            return new StringEntity(jSONObject2);
        } catch (JSONException e) {
            return null;
        }
    }

    public ModelFileBags m6808a() {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        String a = C0803o.MODEL_SERVER.m6754a();
        HttpEntity b = m6807b();
        Object c0817d = new C0817d();
        this.f4584a = syncHttpClient.post(null, a, b, RequestParams.APPLICATION_JSON, c0817d);
        return c0817d.m6804a();
    }

    public /* synthetic */ Object call() {
        return m6808a();
    }
}
